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
     * Sets all grid positions that contain corresponding snake segments.
     * to
     */
    private void addSnakeToGrid() {
        for(Pair<Integer,Integer> coords : snake.getAllSegments()) {
            grid[coords.getKey()][coords.getValue()] = SNAKE_SEGMENT;
        }
    }

    /**
     * Clears all grid positions that contain corresponding snake segments.
     */
    private void removeSnakeFromGrid() {
        for(Pair<Integer,Integer> coords : snake.getAllSegments()) {
            grid[coords.getKey()][coords.getValue()] = 0;
        }
    }

    /**
     * Returns the snake in this game.
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Updates the current model during a game loop.
     */
    public void updateModel() {
        removeSnakeFromGrid();
        snake.move();
        addSnakeToGrid();
    }

}
