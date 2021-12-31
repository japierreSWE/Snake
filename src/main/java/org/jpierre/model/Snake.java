package org.jpierre.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A class representing the player-controlled snake.
 */
public class Snake {

    //Coordinates of the snake's head.
    //Key = row. Value = column
    private Pair<Integer, Integer> head;

    //Coordinates of all other parts of the snake.
    private ArrayList<Pair<Integer,Integer>> segments;

    //A list recording previous locations of the head.
    private LinkedList<Pair<Integer,Integer>> previousLocations;

    //The direction the snake is moving in.
    private int direction;

    static final int UP = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;
    static final int RIGHT = 4;

    public Snake() {
        int vertMid = Model.NUM_ROWS / 2;
        int horMid = Model.NUM_COLUMNS / 2;

        head = new Pair<>(vertMid, horMid);

        segments = new ArrayList<>();
        segments.add(new Pair<>(vertMid, horMid-1));
        segments.add(new Pair<>(vertMid, horMid-2));

        previousLocations = new LinkedList<>();
        previousLocations.addAll(segments);

        direction = RIGHT;
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

    /**
     * Moves the snake once in its current direction.
     */
    public void move() {
        Pair<Integer, Integer> offset = null;

        switch(direction) {
            case UP:
                offset = new Pair<>(1, 0);
                break;

            case DOWN:
                offset = new Pair<>(-1, 0);
                break;

            case LEFT:
                offset = new Pair<>(0, -1);
                break;

            case RIGHT:
                offset = new Pair<>(0, 1);
                break;
        }

        int newRow = head.getKey() + offset.getKey();
        int newCol = head.getValue() + offset.getValue();

        previousLocations.push(head);
        head = new Pair<>(newRow, newCol);

        Iterator<Pair<Integer,Integer>> previousLocationIterator = previousLocations.iterator();
        for(int i = 0; i<segments.size(); i++) {
            segments.set(i, previousLocationIterator.next());
        }
    }

}
