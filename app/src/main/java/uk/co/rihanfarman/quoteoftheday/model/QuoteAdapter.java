package uk.co.rihanfarman.quoteoftheday.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.rihanfarman.quoteoftheday.R;

/**
 * Created by farmanr on 01/03/2017.
 */
public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder> {

    private List<Quote> quotes = new ArrayList<>();

    @Override
    public QuoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_quote_item, parent, false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuoteViewHolder holder, int position) {
        holder.quote.setText(quotes.get(position).getQuote());
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    static class QuoteViewHolder extends RecyclerView.ViewHolder {

        TextView quote;

        QuoteViewHolder(View itemView) {
            super(itemView);
            quote = (TextView) itemView.findViewById(R.id.quote_textView);
        }
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
        notifyDataSetChanged();
    }

    public List<Quote> getQuotes() {
        return quotes;
    }
}
