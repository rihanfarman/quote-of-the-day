package uk.co.rihanfarman.quoteoftheday.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import uk.co.rihanfarman.quoteoftheday.R;

/**
 * Created by farmanr on 01/03/2017.
 */
public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder> implements ItemTouchHelperAdapter {

    private List<Quote> quotes = new ArrayList<>();
    private Context context;

    public QuoteAdapter(Context context) {
        this.context = context;
    }

    @Override
    public QuoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_quote_item, parent, false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuoteViewHolder holder, int position) {
        holder.quote.setText(quotes.get(position).getQuote());
        holder.author.setText(quotes.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    static class QuoteViewHolder extends RecyclerView.ViewHolder {

        TextView quote;
        TextView author;

        QuoteViewHolder(View itemView) {
            super(itemView);
            quote = (TextView) itemView.findViewById(R.id.quote_textView);
            author = (TextView) itemView.findViewById(R.id.author_textView);
        }
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
        notifyDataSetChanged();
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    @Override
    public void onItemDismiss(int position) {
        Quote quote = quotes.get(position);
        if (quote.isManaged()) {
            Realm.init(context);
            Realm defaultInstance = Realm.getDefaultInstance();
            defaultInstance.beginTransaction();
            quote.deleteFromRealm();
            defaultInstance.commitTransaction();
        }
        quotes.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(quotes, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(quotes, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }
}
