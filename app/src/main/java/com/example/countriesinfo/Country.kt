package com.example.countriesinfo

data class Country(val name: Name, val capital: List<String>?, val population: Long, val area: Long, val languages: Any, val flags: Any, val continents: List<String>, val maps: Any)

data class Name(val common: String)


