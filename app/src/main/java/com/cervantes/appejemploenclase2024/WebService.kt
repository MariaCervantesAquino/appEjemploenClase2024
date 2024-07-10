package com.cervantes.appejemploenclase2024

import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

object AppConstantes {
        const val BASE_URL = "http://192.168.0.8:3000"
    }
//Aqui vamos a definir los metodos que van a consumir el servicio, 1ro debemos crear una clase con la estructura del Objeto que arroja la rura de la conexion al bd
interface WebService {

    @GET("/personas")
    //este metodo obteberPersonas() nos va devolver un objeto Response del tipo PersonasResponse,
    // el cual esta hecho para devolver un arraylist del tipo Persona
    //Ahora solo queda crear la interfaz para mostrar esos datos!!!
    suspend fun obtenerPersonas() : Response<PersonasResponse>

    @POST("/personas/agregar")
    suspend fun agregarPersona(@Body persona: Persona): Response<String>

}

//este metodo es para poder conectaron y poder jalar la informacion
object RetrofitClient {
    val webService : WebService by lazy {
    Retrofit.Builder()
        .baseUrl(AppConstantes.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(WebService::class.java)

    }
}
