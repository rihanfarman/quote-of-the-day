package uk.co.rihanfarman.quoteoftheday.presenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.rihanfarman.quoteoftheday.model.MaShapeService;
import uk.co.rihanfarman.quoteoftheday.model.Quote;
import uk.co.rihanfarman.quoteoftheday.view.MainMvpView;

/**
 * Created by farmanr on 27/02/2017.
 */

public class MainPresenter implements Presenter<MainMvpView> {

    private MainMvpView view;
    private Quote quote;

    @Override
    public void attachView(MainMvpView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public void loadQuote() {
        view.showProgressIndicator();
        //ç
        MaShapeService maShapeService = MaShapeService.Factory.create();

        maShapeService.getQuote("ztzfKAxQebmshkc9GNUrGgh19bx9p1c4r6Djsnu6sJ75x4IxZC")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Quote>() {
                    @Override
                    public void onCompleted() {
                        view.showMessage(quote.getQuote());
                    }

                    @Override
                    public void onError(Throwable e) {

                        view.showMessage(e.toString());
                    }

                    @Override
                    public void onNext(Quote quote) {
                        MainPresenter.this.quote = quote;
                    }
                });


    }
}
