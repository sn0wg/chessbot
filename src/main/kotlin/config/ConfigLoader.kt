package config

import java.util.*

object ConfigLoader {

    private val properties: Properties = Properties()

    init {
        this::class.java.classLoader.getResource("application-me.properties")?.openStream().use { properties.load(it); it?.close() }
    }

    fun read(propertyName: String, defaultValue: String? = null): String = properties.getProperty(propertyName, defaultValue)
}