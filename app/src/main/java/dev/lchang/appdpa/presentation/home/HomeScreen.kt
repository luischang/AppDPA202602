package dev.lchang.appdpa.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import dev.lchang.appdpa.data.model.CountryModel

val mockCountries = listOf(
    CountryModel("México", 41, "https://flagcdn.com/w320/mx.png"),
    CountryModel("Perú", 51, "https://flagcdn.com/w320/pe.png"),
    CountryModel("Argentina", 2, "https://flagcdn.com/w320/ar.png"),
    CountryModel("Portugal", 8, "https://flagcdn.com/w320/pt.png"),
    CountryModel("Brasil", 6, "https://flagcdn.com/w320/br.png"),
    CountryModel("España", 1, "https://flagcdn.com/w320/es.png"),
)


@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Ranking Fifa 2026" , style = MaterialTheme.typography.titleLarge)

        LazyColumn{
            items(mockCountries){country ->
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ){
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(country.imageUrl),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column {
                            Text(country.name, style = MaterialTheme.typography.titleMedium)
                            Text("#${country.ranking}", style = MaterialTheme.typography.bodyMedium)
                        }

                    }
                }
            }
        }

    }
}