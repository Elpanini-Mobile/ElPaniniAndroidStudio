package be.samysah.elpanini.restApi

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


private const val BASE_URL_GEBRUIKER = "https://samysah.be/php/api/gebruikers/"
private const val BASE_URL_BROODJES = "https://samysah.be/php/api/broodjes/"


private val retrofit_Broodjes = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_BROODJES)
    .build()

private val retrofit_Gebruiker = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_GEBRUIKER)
    .build()

interface BroodjesApiService {

    @GET("read.php")
    fun getProperties(): Call<Broodjes>

    @GET("readOne.php")
    fun getPropertiesWithCategory(
        @Query("id") id : String
    ): Call<Broodjes>

    @GET("login.php")
    fun checklogin(
        @Query("email") email: String,
        @Query("password") password: String) : Call<Gebruiker>

    @Headers("Content-Type: application/json")
    @POST("create.php")
    fun addGebruiker(@Body gebruiker: Gebruiker): Call<Gebruiker>
}

object Api_Broodjes {
    val retrofitService : BroodjesApiService by lazy{
        retrofit_Broodjes.create(BroodjesApiService::class.java)
    }
}
object Api_Gebruiker {
    val retrofitService : BroodjesApiService by lazy{
        retrofit_Gebruiker.create(BroodjesApiService::class.java)
    }
}