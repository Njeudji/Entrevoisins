package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void updateFabNeighbourWithSuccess() {
        Neighbour caroline = service.getNeighbours().get(0);
        caroline.setFavorite(true);

        service.updateFabNeighbour(caroline);

        assertTrue(service.getNeighbours().get(0).isFavorite());


    }

    @Test
    public void neighbourNotFoundWithSuccess() {
        Neighbour neighbour = new Neighbour(99, "Caroline", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint-Pierre-du-Mont ; 5km",
                "+33 6 86 57 90 14", "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");

        service.updateFabNeighbour(neighbour);

        assertFalse(service.getNeighbours().contains(neighbour));
        for (int i = 0; i < service.getNeighbours().size(); i++) {
            Neighbour indexneighbour = service.getNeighbours().get(i);

            assertFalse(indexneighbour.isFavorite());
        }
    }

    @Test
    public void filterNeighboursWithSuccess() {
        service.getNeighbours().get(0).setFavorite(true);
        service.getNeighbours().get(1).setFavorite(true);

        List<Neighbour> filter = service.filterNeighbours();

        assertTrue(filter.size() == 2);
        for (int i = 0; i < service.getNeighbours().size(); i++) {
            assertTrue(service.getNeighbours().get(i).isFavorite());
        }
    }

    @Test
    public void fabNeighboursWithSuccess() {
        List<Neighbour> neighbourList = service.getNeighbours();
        neighbourList.clear();
        neighbourList.add(new Neighbour(99, "Caroline", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint-Pierre-du-Mont ; 5km",
                "+33 6 86 57 90 14", "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."));

        List<Neighbour> filter = service.filterNeighbours();

        assertTrue(filter.size() == 1);

    }

}


