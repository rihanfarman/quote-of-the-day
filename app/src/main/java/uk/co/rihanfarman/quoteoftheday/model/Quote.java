package uk.co.rihanfarman.quoteoftheday.model;

import io.realm.RealmObject;

/**
 * Created by farmanr on 28/02/2017.
 */

public class Quote extends RealmObject {

    private String quote;
    private String author;
    private String category;

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

}