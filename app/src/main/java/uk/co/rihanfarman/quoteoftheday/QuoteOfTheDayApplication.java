package uk.co.rihanfarman.quoteoftheday;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by farmanr on 27/02/2017.
 */

public class QuoteOfTheDayApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
