package uk.co.rihanfarman.quoteoftheday.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.rihanfarman.quoteoftheday.BuildConfig;
import uk.co.rihanfarman.quoteoftheday.model.MaShapeService;
import uk.co.rihanfarman.quoteoftheday.model.Quote;
import uk.co.rihanfarman.quoteoftheday.model.QuoteAdapter;
import uk.co.rihanfarman.quoteoftheday.view.MainMvpView;

/**
 * Created by farmanr on 27/02/2017.
 */

public class MainPresenter implements Presenter<MainMvpView> {

    private QuoteAdapter quoteAdapter;
    private MainMvpView view;
    private Quote quote;

    public MainPresenter(Context context) {
        quoteAdapter = new QuoteAdapter(context);
    }

    @Override
    public void attachView(MainMvpView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public void fetchQuote() {
        view.showProgressIndicator();
        //ç
        MaShapeService maShapeService = MaShapeService.Factory.create();

        maShapeService.getQuote(BuildConfig.API_KEY, "Famous")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Quote>() {
                    @Override
                    public void onCompleted() {
                        quoteAdapter.getQuotes().add(0, quote);
                        quoteAdapter.notifyDataSetChanged();
                        view.hideProgressIndicator();
                    }

                    @Override
                    public void onError(Throwable e) {

                        view.showMessage(e.toString());
                    }

                    @Override
                    public void onNext(Quote quote) {
                        Realm.init(view.getContext());
                        Realm defaultInstance = Realm.getDefaultInstance();
                        defaultInstance.beginTransaction();
                        defaultInstance.copyToRealm(quote);
                        defaultInstance.commitTransaction();
                        MainPresenter.this.quote = quote;
                    }
                });
    }

    public RecyclerView.Adapter getQuoteAdapter() {
        return quoteAdapter;
    }

    public void loadQuotes() {
        quoteAdapter.getQuotes().clear();
        Realm.init(view.getContext());
        Realm defaultInstance = Realm.getDefaultInstance();
        ArrayList<Quote> list = new ArrayList(defaultInstance.where(Quote.class).findAll());
        Collections.reverse(list);
        quoteAdapter.setQuotes(list);
    }

    public boolean isQuoteListEmpty() {
        Realm.init(view.getContext());
        Realm defaultInstance = Realm.getDefaultInstance();
        ArrayList<Quote> list = new ArrayList(defaultInstance.where(Quote.class).findAll());
        return list.isEmpty();
    }
}
