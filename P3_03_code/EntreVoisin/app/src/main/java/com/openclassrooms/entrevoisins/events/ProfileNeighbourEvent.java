package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user want to access in a Neighbour Profile
 */
public class ProfileNeighbourEvent {

    /**
     * Neighbour we want to access
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public ProfileNeighbourEvent(Neighbour neighbour) { this.neighbour = neighbour; }
}