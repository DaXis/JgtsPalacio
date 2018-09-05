package com.jgtspalacio.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgtspalacio.R;
import com.jgtspalacio.interfaces.OnItemClick;
import com.jgtspalacio.objs.ToyObj;

import java.util.ArrayList;

public class ToysRecyclerAdapter extends RecyclerView.Adapter<ToysRecyclerAdapter.ViewHolder> {

    private static ArrayList<ToyObj> array = new ArrayList<>();
    private FragmentManager fragmentManager;
    private static OnItemClick onItemClick;

    public ToysRecyclerAdapter(ArrayList<ToyObj> array, FragmentManager fragmentManager) {
        this.array = array;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ToysRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.toy_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.toy_name.setText(array.get(position).toy);
        holder.toy_desc.setText(array.get(position).descrip);
        holder.toy_value.setText("$999 x "+array.get(position).cant);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public ToyObj getItem(int position){
        return array.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView toy_name, toy_desc, toy_value;
        public final ImageView mas, menos;

        public ViewHolder(View view){
            super(view);
            toy_name = (TextView)view.findViewById(R.id.toy_name);
            toy_desc = (TextView)view.findViewById(R.id.toy_desc);
            toy_value = (TextView)view.findViewById(R.id.toy_value);

            mas = (ImageView)view.findViewById(R.id.mas);
            menos = (ImageView)view.findViewById(R.id.menos);
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