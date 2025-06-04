package br.com.fiap.waterguardapp.retrofit


import retrofit2.Call
import retrofit2.http.GET

// Modelo de resposta do endpoint "posts"
data class Post(val id: Int, val title: String, val body: String)

interface ApiService {
    @GET("posts") // Endereço da API para buscar posts
    fun getPosts(): Call<List<Post>>
}
