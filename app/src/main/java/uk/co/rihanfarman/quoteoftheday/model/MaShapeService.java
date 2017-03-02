package uk.co.rihanfarman.quoteoftheday.model;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by farmanr on 28/02/2017.
 */

public interface MaShapeService {

    @POST("./")
    Observable<Quote> getQuote(@Header("X-Mashape-Key") String xMashapeKey, @Query("cat") String category);

    class Factory {
        public static MaShapeService create() {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://andruxnet-random-famous-quotes.p.mashape.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(builder.build())
                    .build();

            return retrofit.create(MaShapeService.class);
        }
    }
}
