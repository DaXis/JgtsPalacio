package com.jgtspalacio.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jgtspalacio.R;
import com.jgtspalacio.interfaces.CardsPresenter;
import com.jgtspalacio.interfaces.OnItemClick;
import com.jgtspalacio.objs.CardObj;

import java.util.ArrayList;

public class CardsRecyclerAdapter extends RecyclerView.Adapter<CardsRecyclerAdapter.ViewHolder> {

    private static ArrayList<CardObj> array = new ArrayList<>();
    private FragmentManager fragmentManager;
    private static OnItemClick onItemClick;

    public CardsRecyclerAdapter(ArrayList<CardObj> array , FragmentManager fragmentManager) {
        this.array = array;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(array.get(position).name);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public CardObj getItem(int position){
        return array.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView name;

        public ViewHolder(View view){
            super(view);
            name = (TextView)view.findViewById(R.id.name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClick != null)
                onItemClick.onItemClick(v, getPosition());
        }

    }

    public void SetOnItemClickListener(final OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

}
