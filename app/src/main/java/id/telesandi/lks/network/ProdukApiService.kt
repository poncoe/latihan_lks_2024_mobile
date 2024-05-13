package id.telesandi.lks.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import id.telesandi.lks.model.Produk
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://gist.githubusercontent.com/poncoe/fe3df985430fe86b4e722674aae9cdfd/raw/87e3f444d2b02251e155e9f97f3a6cc845c4c7e9/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ProdukApiService {
    @GET("produk.json")
    suspend fun getProduk(): List<Produk>
}

object ProdukApi {
    val service: ProdukApiService by lazy {
        retrofit.create(ProdukApiService::class.java)
    }

    fun getProdukUrl(gambar: String): String {
        return gambar
    }

    enum class ApiStatus {LOADING, SUCCESS, FAILED}
}