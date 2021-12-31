package org.jpierre.model;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Top level model representing the entire game state.
 */
public class Model {
    public static final int NUM_ROWS = 21;
    public static final int NUM_COLUMNS = 20;
    static final int SNAKE_SEGMENT = 1;
    static final int FRUIT = 2;

    //Player controlled snake.
    private Snake snake;

    //The grid that contains snake segments and fruit.
    private int[][] grid;

    public Model() {
        this.snake = new Snake();
        grid = new int[NUM_ROWS][NUM_COLUMNS];
        addSnakeToGrid();
    }

    /**
     * Sets grid positions accordingly for the coordinate of each snake segment.
     */
    void addSnakeToGrid() {
        for(Pair<Integer,Integer> coords : snake.getAllSegments()) {
            grid[coords.getKey()][coords.getValue()] = SNAKE_SEGMENT;
        }
    }

    /**
     * Returns all snake segments in this game.
     */
    public ArrayList<Pair<Integer,Integer>> getSnakeSegments() {
        return snake.getAllSegments();
    }

}
