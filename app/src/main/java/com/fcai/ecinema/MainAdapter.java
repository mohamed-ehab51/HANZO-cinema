package com.fcai.ecinema;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<Mainmodel> mainmodels;
    Context context;

    public MainAdapter(Context context, ArrayList<Mainmodel> mainmodels) {
        this.context=context;
        this.mainmodels=mainmodels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context)
                .load(mainmodels.get(position).getMovlogo())
                .into(holder.imageView);
        holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.textView.setText(mainmodels.get(position).movname);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // start Movie activity with the clicked movie name
                Intent intent = new Intent(view.getContext(), Movie.class);

                intent.putExtra("movname",mainmodels.get(position).movname);

                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mainmodels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            imageView=itemView.findViewById(R.id.image_view);
            textView=itemView.findViewById(R.id.text_view);
        }

        public void onClick(View v)
        {


        }
    }


}
