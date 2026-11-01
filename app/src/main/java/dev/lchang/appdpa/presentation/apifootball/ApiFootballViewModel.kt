package dev.lchang.appdpa.presentation.apifootball

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.lchang.appdpa.data.local.AppDatabase
import dev.lchang.appdpa.data.local.FavoriteTeamEntity
import dev.lchang.appdpa.data.remote.apifootball.Country
import dev.lchang.appdpa.data.remote.apifootball.RetrofitInstance
import dev.lchang.appdpa.data.remote.apifootball.Team
import dev.lchang.appdpa.data.remote.apifootball.TeamWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ApiFootballViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val favoriteTeamDao = db.favoriteTeamDao()

    val favoriteTeamIds: StateFlow<Set<Int>> = favoriteTeamDao.getFavoriteIds()
        .map { it.toSet() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries
    private val _selectedCountry = MutableStateFlow<Country?>(null)
    val selectedCountry: StateFlow<Country?> = _selectedCountry
    private val _teams = MutableStateFlow<List<TeamWrapper>>(emptyList())
    val teams: StateFlow<List<TeamWrapper>> = _teams
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadCountries()
    }

    fun loadCountries() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getCountries()
                _countries.value = response.response
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadTeamsByCountry(country: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getTeamsByCountry(country)
                _teams.value = response.response
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun onCountrySelected(country: Country) {
        _selectedCountry.value = country
        loadTeamsByCountry(country.name)
    }

    fun toggleFavorite(team: Team) {
        viewModelScope.launch {
            val isFav = favoriteTeamIds.value.contains(team.id)
            if (isFav) {
                favoriteTeamDao.deleteFavoriteById(team.id)
            } else {
                val entity = FavoriteTeamEntity(
                    id = team.id,
                    name = team.name,
                    code = team.code,
                    logo = team.logo,
                    country = team.country,
                    founded = team.founded,
                    national = team.national,
                    createdAt = System.currentTimeMillis()
                )
                favoriteTeamDao.insertFavorite(entity)
            }
        }
    }
}
