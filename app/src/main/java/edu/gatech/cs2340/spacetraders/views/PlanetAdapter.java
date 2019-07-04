package edu.gatech.cs2340.spacetraders.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.entity.City;
import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.SpaceShip;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.CityViewHolder> {

    private List<City> cityList = new ArrayList<>();

    private Player player;

    private OnTravelClickListener listener;

    private Planet planet;
     private SpaceShip ship;

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        //Tell the adapter what layout to use for each course in the list
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_item, parent, false);

        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {

        /* When the list needs to display a city it will call this method and pass the
           position in the list it needs and a view holder for that position
        */

        //get the city located at this position
        City city = cityList.get(position);

        //now use the view holder to set the appropriate information
        holder.name.setText(new StringBuilder("City: ").append(city.getName()));
        holder.location.setText(new StringBuilder("Location On Planet: " ).append(city.getLocation().toString()));
        if (player.getCoordinates().get(0) < 0 || player.getCoordinates().get(1) < 0){
            holder.travel.setText(new StringBuilder("Travel To This Planet And City"));
        } else if (player.getCurrentPlanet().equals(planet.getName()) && player.getLocation().equals(city.getLocation())) {
            holder.travel.setText(new StringBuilder("Visit Marketplace"));
        } else if (player.getCurrentPlanet().equals(planet.getName())){
            holder.travel.setText(new StringBuilder("Travel To This City"));
        } else if (ship.getFuel() < 5) {
            holder.travel.setText(new StringBuilder("You Do Not Have Enough Fuel"));
        } else {
            holder.travel.setText(new StringBuilder("Travel To This Planet And City"));
        }



    }
    @Override
    public int getItemCount() {
        if (cityList == null) return 0;
        return cityList.size();
    }

    void setCityList(List<City> cities) {
        cityList = cities;
        notifyDataSetChanged();
    }

    public void setPlayer(Player player) {
        this.player = player;
        notifyDataSetChanged();
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
        notifyDataSetChanged();
    }

    public void setShip (SpaceShip ship) {
        this.ship = ship;
    }

    class CityViewHolder extends RecyclerView.ViewHolder {
        //View holder needs reference to each widget in the individual item in the list
        private TextView name;
        private TextView location;
        private Button travel;


        /**
         * Construct a new view holder, grab all the widget references and setup the
         * listener to detect a click on this item.
         *
         * @param itemView the current view
         */
        CityViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            location = itemView.findViewById(R.id.location);
            travel = itemView.findViewById(R.id.travelButton);

            travel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onTravelClicked(cityList.get(position));
                    }
                }
            });
        }
    }

    public interface OnTravelClickListener {
        void onTravelClicked(City city);
    }

    void setOnTravelClickListener(OnTravelClickListener listener) {
        this.listener = listener;
    }

}
