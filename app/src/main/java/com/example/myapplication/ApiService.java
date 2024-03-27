package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("random/anime")
    Call<AnimeResponse> getRandomAnime();
  //  @GET("anime")
  //  Call<List<Anime>> getAnimeList(@Query("page") int page, @Query("limit") int limit);

}
