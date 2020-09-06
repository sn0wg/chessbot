package config

import java.util.*

object ConfigLoader {

    private val properties: Properties = Properties()

    init {
        this::class.java.classLoader.getResource("application.properties")?.openStream().use { properties.load(it) }
    }

    fun read(propertyName: String, defaultValue: String? = null): String = properties.getProperty(propertyName, defaultValue)
}