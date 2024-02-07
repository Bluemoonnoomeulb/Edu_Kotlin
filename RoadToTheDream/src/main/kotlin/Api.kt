import java.lang.Exception

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiVersion(val version: Float)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Deprecated(val message: String)

abstract class BaseApi {

    abstract fun fetchMovies()

    fun isAvailable(): BaseApi {
        val deprecated = this.javaClass.annotations.find { it is Deprecated } as Deprecated?
        if (deprecated != null) throw Exception("" + this.javaClass.name + " is deprecated: " + deprecated.message)
        return this
    }
}

@ApiVersion(1.3f)
open class NewApi: BaseApi() {

    override fun fetchMovies() {
        println("FETCHING MOVIES...")
    }
}

@ApiVersion(0.9f)
@Deprecated(message = "PLEASE USE NewApi class instead")
open class OldApi: BaseApi() {

    override fun fetchMovies() {
        println("FETCHING MOVIES...")
    }
}
