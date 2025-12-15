package com.example.smart_ev.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_ev.Modals.Station;
import com.example.smart_ev.R;

import java.util.List;


public class StationAdapters extends RecyclerView.Adapter<StationAdapters.ViewHolder> {

    List<Station> stationList;
    Context context;
    ButtonClickListener buttonClickListener;

    public StationAdapters(List<Station> stationList, Context context, ButtonClickListener buttonClickListener) {
        this.stationList = stationList;
        this.context = context;
        this.buttonClickListener = buttonClickListener;
    }

    @NonNull
    @Override
    public StationAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.station_view , parent, false);
        return new ViewHolder(view, buttonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StationAdapters.ViewHolder holder, int position) {
        Station station = stationList.get(position);
        holder.name.setText(station.getName());
        holder.contact.setText(station.getContact());
//        holder.km.setText(station.getName());

    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name, contact, km;
        Button map;
        ButtonClickListener buttonClickListener;
        public ViewHolder(@NonNull View itemView, ButtonClickListener buttonClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.station_name);
            contact = itemView.findViewById(R.id.station_contact);
//            km = itemView.findViewById(R.id.station_dist);
            image = itemView.findViewById(R.id.station_image);
            map = itemView.findViewById(R.id.map);
            this.buttonClickListener = buttonClickListener;
            map.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.map)
            {
                buttonClickListener.onMapButtonClick(getAdapterPosition());
            }
        }
    }
    public interface ButtonClickListener{
        void onMapButtonClick(int position);
    }
}
