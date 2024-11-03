package com.example.countriesinfo
import java.text.NumberFormat

fun formatNumber(number: Long): String {
    // 144104080 -> 144 104 080
    return NumberFormat.getInstance().format(number).replace(","," ")
}
fun formatCapital(list: List<String>): String{
    // [Moscow} -> Moscow
    return list[0]
}
fun formatLanguage(languages: Any): String{
    // {prs=Dari, pus=Pushto, tuk=Turkmen} -> Dari, Pushto, Turkmen
    val string = languages.toString()
    val list = string.split(",")
    var result = ""
    for ((index, element) in list.withIndex())
        if (index != list.lastIndex)
            result += "${element.substringAfter('=')}, "
        else
            result += element.substringAfter('=')
    return result.dropLastWhile { !it.isLetter() }
}
fun formatFlag(flags: Any): String{
    /*{"png": "https://flagcdn.com/w320/ru.png",
      "svg": "https://flagcdn.com/ru.svg",
      "alt": "The flag of Russia is composed of three equal horizontal bands of white, blue and red."}
       -> "https://flagcdn.com/ru.svg" */
    val string = flags.toString()
    val list = string.split(",")
    var result = ""
    for ((index, element) in list.withIndex())
        if (index == 1)
            result = element.substringAfter('=')
    return result.dropLastWhile { !it.isLetter() }
}
fun formatName(name: Name): String {
    // Name(common=Italy) -> Italy
    val list = name.toString().split("=")
    var result = ""
    for ((index, element) in list.withIndex())
        if (index == list.lastIndex)
            result += element
    return result.dropLastWhile { !it.isLetter() }
}
fun formatContinents(continents: List<String>): String{
    /*["Europe","Asia"] -> Europe, Asia */
    return continents.joinToString()
}
fun formatMaps(maps: Any): String{
    /*{"googleMaps": "https://goo.gl/maps/4F4PpDhGJgVvLby57",
    "openStreetMaps": "https://www.openstreetmap.org/relation/60189#map=3/65.15/105.29}"
    -> https://goo.gl/maps/4F4PpDhGJgVvLby57 */
    val string = maps.toString()
    val list = string.split(",")
    var result = ""
    for ((index, element) in list.withIndex())
        if (index == 0)
            result = element.substringAfter('=')
    return result
}

