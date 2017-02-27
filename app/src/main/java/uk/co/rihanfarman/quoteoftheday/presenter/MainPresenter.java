package uk.co.rihanfarman.quoteoftheday.presenter;

import uk.co.rihanfarman.quoteoftheday.R;
import uk.co.rihanfarman.quoteoftheday.view.MainMvpView;

/**
 * Created by farmanr on 27/02/2017.
 */

public class MainPresenter implements Presenter<MainMvpView> {

    private MainMvpView view;

    @Override
    public void attachView(MainMvpView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public void loadQuote() {
        view.showMessage(R.string.quote);
    }
}
