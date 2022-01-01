package org.jpierre.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

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

    //Whether the game is paused.
    boolean paused = false;

    //Whether the player has lost.
    boolean hasLost = false;

    //The position on the board where the fruit
    //is located.
    Pair<Integer,Integer> fruitLocation;

    public Model() {
        this.snake = new Snake();
        grid = new int[NUM_ROWS][NUM_COLUMNS];
        addSnakeToGrid();
        addFruitToGrid();
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
     * Returns true if player will lose the game during the next move.
     * Returns false otherwise.
     */
    private boolean checkLoss(Pair<Integer,Integer> nextHeadPosition, ArrayList<Pair<Integer,Integer>> nextSegments) {
        int nextHeadRow = nextHeadPosition.getKey();
        int nextHeadColumn = nextHeadPosition.getValue();

        //if the head is outside of bounds, return true.
        //if the head is in the same position as a snake
        //segment, return true.
        if(nextHeadRow < 0 || nextHeadRow >= NUM_ROWS || nextHeadColumn < 0 || nextHeadColumn >= NUM_COLUMNS ||
        nextSegments.contains(nextHeadPosition)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates the current model during a game loop.
     */
    public void updateModel() {

        //if the player lost,
        //the snake should stop moving.
        if(!hasLost) {
            Pair<Integer,Integer> nextHeadPosition = snake.getNextHeadPosition();
            ArrayList<Pair<Integer,Integer>> nextSegments = snake.getNextSegmentsPositions();

            if(checkLoss(nextHeadPosition, nextSegments)) {
                hasLost = true;
            } else {
                removeSnakeFromGrid();
                snake.move();
                addSnakeToGrid();

                if(fruitLocation == null) {
                    addFruitToGrid();
                }

            }
        }

    }

    /**
     * Places a fruit in an open space on the grid.
     */
    private void addFruitToGrid() {
        ArrayList<Pair<Integer,Integer>> possibleLocations = new ArrayList<>();

        for(int r = 0; r<grid.length; r++) {
            for(int c = 0; c<grid[r].length; c++) {
                if(grid[r][c] == 0) {
                    possibleLocations.add(new Pair<>(r,c));
                }
            }
        }

        Random random = new Random();
        int index = random.nextInt(possibleLocations.size());

        fruitLocation = possibleLocations.get(index);
        grid[fruitLocation.getKey()][fruitLocation.getValue()] = FRUIT;
    }

    public void togglePause() {
        paused = !paused;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean hasLost() {
        return hasLost;
    }

    public Pair<Integer, Integer> getFruitLocation() {
        return fruitLocation;
    }
}
