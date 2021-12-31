package org.jpierre.model;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 * A class representing the player-controlled snake.
 */
public class Snake {

    //Coordinates of the snake's head.
    //Key = row. Value = column
    private Pair<Integer, Integer> head;

    //Coordinates of all other parts of the snake.
    private ArrayList<Pair<Integer,Integer>> segments;

    public Snake() {
        int vertMid = Model.NUM_ROWS / 2;
        int horMid = Model.NUM_COLUMNS / 2;

        head = new Pair<>(vertMid, horMid);

        segments = new ArrayList<>();
        segments.add(new Pair<>(vertMid, horMid-1));
        segments.add(new Pair<>(vertMid, horMid-2));
    }

    /**
     * Returns a list of all of this snake's segments, including its head.
     */
    public ArrayList<Pair<Integer,Integer>> getAllSegments() {
        ArrayList<Pair<Integer,Integer>> result = new ArrayList<>();
        result.add(head);
        result.addAll(segments);
        return result;
    }


}
