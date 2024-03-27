package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btnRandomAnime;
    private TextView tvAnimeTitle;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRandomAnime = findViewById(R.id.btnRandomAnime);
        tvAnimeTitle = findViewById(R.id.tvAnimeTitle);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        btnRandomAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandomAnime();
            }
        });
    }

    private void getRandomAnime() {
        Call<AnimeResponse> call = apiService.getRandomAnime();
        call.enqueue(new Callback<AnimeResponse>() {
            @Override
            public void onResponse(Call<AnimeResponse> call, Response<AnimeResponse> response) {
                if (response.isSuccessful()) {
                    AnimeResponse animeResponse = response.body();
                    if (animeResponse != null) {
                        String animeTitle = animeResponse.getData().getTitle();
                        tvAnimeTitle.setText(animeTitle);
                    }
                } else {
                    tvAnimeTitle.setText("Failed to get random anime.");
                }
            }

            @Override
            public void onFailure(Call<AnimeResponse> call, Throwable t) {
                tvAnimeTitle.setText("Failed to get random anime: " + t.getMessage());
            }
        });
    }

}
