package uk.co.rihanfarman.quoteoftheday.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import uk.co.rihanfarman.quoteoftheday.R;

/**
 * Created by farmanr on 27/02/2017.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            MainFragment mainFragment = MainFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.content, mainFragment).commit();
        }
    }
}
