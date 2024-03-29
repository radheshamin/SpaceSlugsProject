package edu.gatech.cs2340.spacetraders.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.entity.City;
import edu.gatech.cs2340.spacetraders.entity.Planet;

/**
 * Adapter for the Universe. Extends the RecyclerView.
 */
public class UniverseAdapter extends RecyclerView.Adapter<UniverseAdapter.PlanetViewHolder> {

    private List<Planet> planetList = new ArrayList<>();

    private OnPlanetClickListener listener;

    @NonNull
    @Override
    public PlanetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        //Tell the adapter what layout to use for each course in the list
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.planet_item, parent, false);

        return new PlanetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetViewHolder holder, int position) {

        /* When the list needs to display a course, it will call this method and pass the
           position in the list it needs and a view holder for that position
        */

        //get the course located at this position
        Planet planet = planetList.get(position);

        //now use the view holder to set the appropriate information
        StringBuilder cities = new StringBuilder();
        for (City c : planet.getCities()) {
            cities.append(c.getName());
            if ((planet.getCities().indexOf(c)) != (planet.getCities().size() - 1)) {
                cities.append(", ");
            }
        }
        holder.cities.setText(new StringBuilder("Cities: ").append(cities));
        holder.coordinates.setText(new StringBuilder("Coordinates: ")
                .append(planet.getCoordinates().toString()));
        holder.techLevel.setText(new StringBuilder("Tech Level: ").append(planet.getTechLevel()));
        holder.resourceLevel.setText(new StringBuilder("Resource Type: ").
                append(planet.getResources()));
        holder.name.setText(planet.getName());


    }
    @Override
    public int getItemCount() {
        if (planetList == null) {
            return 0;
        }
        return planetList.size();
    }

    void setPlanetList(List<Planet> planets) {
        planetList = planets;
        notifyDataSetChanged();
    }

    class PlanetViewHolder extends RecyclerView.ViewHolder {
        //View holder needs reference to each widget in the individual item in the list
        private final TextView cities;
        private final TextView coordinates;
        private final TextView techLevel;
        private final TextView resourceLevel;
        private final TextView name;


        /**
         * Construct a new view holder, grab all the widget references and setup the
         * listener to detect a click on this item.
         *
         * @param itemView the current view
         */
        PlanetViewHolder(@NonNull View itemView) {
            super(itemView);
            cities = itemView.findViewById(R.id.name);
            coordinates = itemView.findViewById(R.id.location);
            techLevel = itemView.findViewById(R.id.tech_level);
            resourceLevel = itemView.findViewById(R.id.resource_level);
            name = itemView.findViewById(R.id.planet_name);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if ((listener != null) && (position != RecyclerView.NO_POSITION)) {
                        listener.onPlanetClicked(planetList.get(position));
                    }
                }
            });

        }
    }

    public interface OnPlanetClickListener {
        /**
         * Defines the action that occurs when the planet is clicked
         * @param planet the planet which was clicked
         */
        void onPlanetClicked(Planet planet);
    }

    void setOnPlanetClickListener(OnPlanetClickListener listener) {
        this.listener = listener;
    }
}
