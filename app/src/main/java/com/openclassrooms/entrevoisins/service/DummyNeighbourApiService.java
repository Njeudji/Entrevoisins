package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    /**
     * Update Favorite Neighbour
     * @param neighbour
     */
    @Override
    public void updateFabNeighbour(Neighbour neighbour) {
        for (int i = 0; i < neighbours.size(); i++) {
            Neighbour indexneighbour = neighbours.get(i);
            if (neighbour.getId() == indexneighbour.getId()) {
                indexneighbour.setFavorite(neighbour.isFavorite());
                break;
            }
        }
    }

    /**
     * Filter for Fab Neighbour
     * @return
     */
    @Override
    public List<Neighbour> filterNeighbours() {
        List<Neighbour> fabNeighbours = new ArrayList<>();
        for (int i = 0; i < neighbours.size(); i++) {
            Neighbour indexneighbour = neighbours.get(i);
            if (indexneighbour.isFavorite() ){
                fabNeighbours.add(indexneighbour);
            }
        }
        return fabNeighbours;
    }


// creer la nouvelle liste
    // parcourir la liste
    // check de si le voisin est favoris
    // si il est favoris /ajouter dans une nouvelle liste
    //retourner la nouvelle liste
}
