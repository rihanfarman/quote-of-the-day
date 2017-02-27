package uk.co.rihanfarman.quoteoftheday.presenter;

/**
 * Created by farmanr on 27/02/2017.
 */

public interface Presenter<V> {
    void attachView(V view);

    void detachView();

}
