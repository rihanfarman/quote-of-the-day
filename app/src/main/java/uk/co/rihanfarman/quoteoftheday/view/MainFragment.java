package uk.co.rihanfarman.quoteoftheday.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.rihanfarman.quoteoftheday.R;
import uk.co.rihanfarman.quoteoftheday.presenter.MainPresenter;

/**
 * Created by farmanr on 27/02/2017.
 */
public class MainFragment extends Fragment implements MainMvpView {

    @BindView(R.id.textView)
    TextView textView;

    private MainPresenter mainPresenter;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void showMessage(int stringId) {
        textView.setText(getString(stringId));
    }

    @Override
    public void showProgressIndicator() {

    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        mainPresenter.loadQuote();
    }
}
