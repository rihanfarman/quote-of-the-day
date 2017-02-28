package uk.co.rihanfarman.quoteoftheday.model;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by farmanr on 28/02/2017.
 */

public interface MaShapeService {

    @POST("./")
    Observable<Quote> getQuote(@Header("X-Mashape-Key") String xMashapeKey);

    class Factory {
        public static MaShapeService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://andruxnet-random-famous-quotes.p.mashape.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            return retrofit.create(MaShapeService.class);
        }
    }
}
