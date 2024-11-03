package com.example.countriesinfo

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil3.load
import com.example.countriesinfo.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchButton.setOnClickListener() {
            val countryName = binding.countryNameEditText.text.toString()

            lifecycleScope.launch {
                try {
                    val countries = restCountriesApi.getCountryByName(countryName)
                    if (countries.size == 1) {
                        val country = countries[0]
                        filingInData(country)
                    } else {
                        val countryNames = countries.map { formatName(it.name) }
                        val listView : ListView = findViewById(R.id.listView)
                        val arrayAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, countryNames)
                        listView.adapter = arrayAdapter
                        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                            val country = parent.getItemIdAtPosition(position)
                            filingInData(countries[country.toInt()])
                        }
                        binding.statusLayout.visibility = View.INVISIBLE
                        binding.resultLayout.visibility = View.INVISIBLE
                        binding.clarifyingRequestLayout.visibility = View.VISIBLE
                    }
                } catch (e: Exception) {
                    binding.textView.text = "This country was not found"
                    binding.imageView2.setImageResource(R.drawable.baseline_error_24)
                    binding.clarifyingRequestLayout.visibility = View.INVISIBLE
                    binding.resultLayout.visibility = View.INVISIBLE
                    binding.statusLayout.visibility = View.VISIBLE
                }
            }
        }
    }
    fun filingInData(country: Country) {
        binding.imageView.load(formatFlag(country.flags))
        binding.countryNameTextView.text = formatName(country.name)
        if (country.capital?.let { formatCapital(it) } != null)
            binding.capitalTextView.text = formatCapital(country.capital)
        else
            binding.capitalTextView.text = "is missing"
        binding.areaTextView.text = formatNumber(country.area)
        binding.populationTextView.text = formatNumber(country.population)
        binding.languagesTextView.text = formatLanguage(country.languages)
        binding.continentsTextView.text = formatContinents(country.continents)
        binding.mapsTextView.text = formatMaps(country.maps)
        binding.clarifyingRequestLayout.visibility = View.INVISIBLE
        binding.resultLayout.visibility = View.VISIBLE
        binding.statusLayout.visibility = View.INVISIBLE
    }
}