package com.fcai.ecinema;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ticket_adapter extends RecyclerView.Adapter<ticket_adapter.Viewholder> {

    public static class Viewholder extends RecyclerView.ViewHolder{
        TextView movie_n, movie_d,seat,cinema_n;
        public Viewholder(@NonNull View itemview){
            super(itemview);
            movie_n=itemview.findViewById(R.id.t_mov_name);
            movie_d=itemview.findViewById(R.id.t_date);
            seat=itemview.findViewById(R.id.t_seat);
            cinema_n=itemview.findViewById(R.id.t_cinema);

        }

    }

    @NonNull

     Context context;
    List<ticket_class> ticketsl;

    public ticket_adapter(Context c, List<ticket_class>ticketsl2){
        this.context=c;
        ticketsl=ticketsl2;


    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket,parent,false);

        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        ticket_class p =ticketsl.get(position);
        holder.seat.setText(p.getSeat());
        holder.movie_n.setText(p.getMovie_name());
        holder.movie_d.setText(p.getMovie_date());
        holder.cinema_n.setText(p.getCinema_name());
    }

    @Override
    public int getItemCount() {

        return ticketsl.size();
    }
}
