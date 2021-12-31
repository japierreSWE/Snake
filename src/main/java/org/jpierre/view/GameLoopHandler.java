package org.jpierre.view;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.jpierre.model.Model;

import java.util.ArrayList;

/**
 * Manages the game loop and carries out all actions
 * that need to take place during each loop, such as
 * handling input, updating the view, and updating the model.
 */
public class GameLoopHandler extends AnimationTimer {

    Canvas canvas;
    GraphicsContext gc;
    Model model;

    //The amount of nanoseconds it takes for a move to happen in game.
    long timeToMove = (long)Math.pow(10, 9);

    //The timestamp of our last move.
    Long lastMoveTimestamp;

    public GameLoopHandler() {}

    public GameLoopHandler(Canvas canvas, Model model) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.model = model;
    }

    /**
     * Clears and draws the user interface again, updating it if necessary.
     */
    void updateView() {
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        drawGrid();
        drawSnake();
    }

    /**
     * Draws the grid for the user interface.
     */
    void drawGrid() {
        double vertSpace = canvas.getWidth() / Model.NUM_COLUMNS;
        double horSpace = canvas.getHeight() / Model.NUM_ROWS;

        for(double currX = vertSpace; currX<canvas.getWidth(); currX+=vertSpace) {
            gc.setStroke(Color.BLACK);
            gc.strokeLine(currX,0, currX, canvas.getHeight());
        }

        for(double currY = horSpace; currY<canvas.getHeight(); currY+=horSpace) {
            gc.setStroke(Color.BLACK);
            gc.strokeLine(0, currY, canvas.getWidth(), currY);
        }

    }

    /**
     * This method draws all of the snake segments.
     */
    void drawSnake() {
        ArrayList<Pair<Integer,Integer>> snakeSegments = model.getSnake().getAllSegments();
        double colSpace = canvas.getWidth() / Model.NUM_COLUMNS;
        double rowSpace = canvas.getHeight() / Model.NUM_ROWS;

        for(Pair<Integer,Integer> coords : snakeSegments) {
            gc.setFill(Color.GREEN);
            gc.fillRect(colSpace * coords.getValue(), rowSpace * coords.getKey(), colSpace, rowSpace);
        }
    }

    /**
     * This method executes during each loop of the game loop.
     * @param l The current system timestamp, in nanoseconds.
     */
    @Override
    public void handle(long l) {
        if(lastMoveTimestamp == null) {
            lastMoveTimestamp = l;
        }
        updateView();

        //if the time since our last move is >= timeToMove, we should move.
        if(l - lastMoveTimestamp >= timeToMove) {
            model.updateModel();
            lastMoveTimestamp = l;
        }

    }
}
