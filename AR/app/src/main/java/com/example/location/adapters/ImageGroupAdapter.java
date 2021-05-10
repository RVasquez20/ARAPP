package com.example.location.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.location.R;
import com.example.location.interfaces.OnItemClickListener;
import com.example.location.model.GrupoDeImagenes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ImageGroupAdapter extends RecyclerView.Adapter<ImageGroupAdapter.MyViewHolder> implements Filterable {
    ArrayList<GrupoDeImagenes> arr;
    ArrayList<GrupoDeImagenes> grupoDeImagenes;
    private OnItemClickListener onItemClickListener;

    public ImageGroupAdapter(ArrayList<GrupoDeImagenes> arr, OnItemClickListener onItemClickListener) {
        this.arr = arr;
        this.onItemClickListener = onItemClickListener;
        grupoDeImagenes = new ArrayList<>();
        grupoDeImagenes.addAll(arr);
    }

    @NonNull
    @Override
    public ImageGroupAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageGroupAdapter.MyViewHolder holder, int position) {
        holder.getDataBind(arr.get(position), holder.itemView.getContext());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<GrupoDeImagenes> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0 || charSequence == "") {
                filteredList.addAll(grupoDeImagenes);
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            arr.clear();
            arr.addAll((Collection<? extends GrupoDeImagenes>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView bg;
        TextView title;
        TextView description;
        GrupoDeImagenes grupoDeImagenes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bg = itemView.findViewById(R.id.background);
            description = itemView.findViewById(R.id.description);
            title = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(grupoDeImagenes);
                }
            });
        }

        public void getDataBind(GrupoDeImagenes grupoDeImagenes, Context context) {
            this.grupoDeImagenes = grupoDeImagenes;
            bg.setImageDrawable(context.getResources().getDrawable(grupoDeImagenes.getImage()));
            title.setText(grupoDeImagenes.getName());
            description.setText(grupoDeImagenes.getDescription());

        }

    }
}
