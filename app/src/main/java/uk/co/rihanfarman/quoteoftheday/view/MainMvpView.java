package uk.co.rihanfarman.quoteoftheday.view;

/**
 * Created by farmanr on 27/02/2017.
 */

public interface MainMvpView extends MvpView {

    void showMessage(int stringId);

    void showProgressIndicator();
}