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

    //The direction the snake was moving in during the
    //previous move.
    private int prevDirection;

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

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
        prevDirection = RIGHT;
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
     * Returns the offset of coordinates for the snake head's
     * next move.
     */
    private Pair<Integer, Integer> getDirectionOffset() throws RuntimeException {
        switch(direction) {
            case UP:
                return new Pair<>(-1, 0);

            case DOWN:
                return new Pair<>(1, 0);

            case LEFT:
                return new Pair<>(0, -1);

            case RIGHT:
                return new Pair<>(0, 1);

            default:
                throw new RuntimeException("Invalid direction: " + direction);
        }
    }

    /**
     * Moves the snake once in its current direction.
     */
    public void move() {
        Pair<Integer, Integer> offset = getDirectionOffset();

        int newRow = head.getKey() + offset.getKey();
        int newCol = head.getValue() + offset.getValue();

        previousLocations.push(head);
        head = new Pair<>(newRow, newCol);

        Iterator<Pair<Integer,Integer>> previousLocationIterator = previousLocations.iterator();
        for(int i = 0; i<segments.size(); i++) {
            segments.set(i, previousLocationIterator.next());
        }
        prevDirection = direction;
    }

    /**
     * Returns the position of the head after making the next move.
     * @return A pair representing the row and column of the head after
     * making its next move.
     */
    public Pair<Integer,Integer> getNextHeadPosition() {
        Pair<Integer, Integer> offset = getDirectionOffset();

        int newRow = head.getKey() + offset.getKey();
        int newCol = head.getValue() + offset.getValue();
        return new Pair<>(newRow, newCol);
    }

    /**
     * Returns a list of positions representing the snake
     * segments after they have made their next move.
     */
    public ArrayList<Pair<Integer,Integer>> getNextSegmentsPositions() {
        //this represents what previousLocations would look like if we
        //made the next move.
        ArrayList<Pair<Integer,Integer>> futurePreviousLocations = new ArrayList<>();
        futurePreviousLocations.add(head);
        futurePreviousLocations.addAll(previousLocations);

        ArrayList<Pair<Integer,Integer>> result = new ArrayList<>();

        Iterator<Pair<Integer,Integer>> previousLocationIterator = futurePreviousLocations.iterator();
        for(int i = 0; i<segments.size(); i++) {
            result.add(previousLocationIterator.next());
        }

        return result;
    }

    public void setDirection(int newDirection) {

        boolean isValidDirection = !((prevDirection == LEFT && newDirection == RIGHT) ||
                (prevDirection == RIGHT && newDirection == LEFT) ||
                (prevDirection == UP && newDirection == DOWN) ||
                (prevDirection == DOWN && newDirection == UP));

        //If this new direction doesn't cause the snake
        //to move into itself, then we can move in the direction.
        if(isValidDirection) {
            this.direction = newDirection;
        }
    }
}
