package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

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
    public void addFavoriteNeighbourWithSuccess() {
        int count = service.getFavoriteNeighbours().size();
        for (Neighbour neighbour : service.getNeighbours()){
            if (neighbour.getFavorite() == false) {
                neighbour.favoriteOn();
                break;
            }
        }
        service.setupFavoriteNeighbours();
        assertEquals(service.getFavoriteNeighbours().size(), count+1);
    }

    @Test
    public void removeFavoriteNeighbourWithSuccess() {
        service.setupFavoriteNeighbours();
        int count = service.getFavoriteNeighbours().size();
        for (Neighbour neighbour : service.getNeighbours()){
            if (neighbour.getFavorite() == false) {
                neighbour.favoriteOn();
                break;
            }
        }
        service.setupFavoriteNeighbours();
        assertEquals(service.getFavoriteNeighbours().size(), count+1);
        for (Neighbour neighbour : service.getNeighbours()){
            if (neighbour.getFavorite() == true) {
                neighbour.favoriteOff();
                break;
            }
        }
        service.setupFavoriteNeighbours();
        assertEquals(service.getFavoriteNeighbours().size(), count);
    }

    @Test
    public void addNeighbourWithSuccess() {
        int count = service.getNeighbours().size();
        Neighbour newNeighbour = new Neighbour(
                12,
                "Benjamin",
                "https://i.pravatar.cc/150?u=",
                "Saint-Pierre-du-Mont ; 5km",
                "+33 6 86 57 90 14",
                "Test",
                false
        );
        service.createNeighbour(newNeighbour);
        assertEquals(service.getNeighbours().size(), count+1 );
    }
}
