package br.com.bwg.livesteck.ui.granja;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.bwg.livesteck.databinding.FragmentConGranjaBinding;
import br.com.bwg.livesteck.model.Granja;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Granja}.
 */
public class ConGranjaRecyclerViewAdapter extends RecyclerView.Adapter<ConGranjaRecyclerViewAdapter.ViewHolder> {

    private final List<Granja> mValues;

    public ConGranjaRecyclerViewAdapter(List<Granja> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentConGranjaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getNome());
        holder.mContentView.setText(String.valueOf(mValues.get(position).getSilos()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Granja mItem;

        public ViewHolder(FragmentConGranjaBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}