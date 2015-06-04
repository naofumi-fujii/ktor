package ktor.application

import com.typesafe.config.*
import java.net.*

/**
 * Store application configuration.
 */
public open class ApplicationConfig(private val config: Config,
                                    public val log: ApplicationLog = NullApplicationLog(),
                                    private val classPathUrl: URL? = null) {

    public open val classPath: Array<URL>
        get() = if (classPathUrl == null) arrayOf() else arrayOf(classPathUrl)

    public val classLoader: URLClassLoader = URLClassLoader(classPath, javaClass.getClassLoader())

    public val environment: String get() = config.getString("ktor.environment")
    public val applicationClassName: String = config.getString("ktor.application.class")

    /** Directories where publicly available files (like stylesheets, scripts, and images) will go. */
    public val publicDirectories: List<String> = config.getStringList("ktor.application.folders.public")

    /** The port to run the server on. */
    public val port: Int = config.getInt("ktor.application.port")

    public fun get(configuration: String): String = config.getString(configuration)
}
