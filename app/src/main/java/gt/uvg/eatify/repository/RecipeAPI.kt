package gt.uvg.eatify.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import gt.uvg.eatify.model.RecipeResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object RecipeAPI {

    private val BASE_URL = "https://api.spoonacular.com/"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val retrofitService : RecipeService by lazy {
        retrofit.create(RecipeService::class.java)
    }
}

interface RecipeService {
    @GET("recipes/random?apiKey=88ee5f7c9c874c8ba76eabced84e38ec&number=15")
    fun getFirst10Recipes(): Call<RecipeResponse>
}