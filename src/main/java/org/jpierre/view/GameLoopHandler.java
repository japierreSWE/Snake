package org.jpierre.view;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Manages the game loop and carries out all actions
 * that need to take place during each loop, such as
 * handling input, updating the view, and updating the model.
 */
public class GameLoopHandler extends AnimationTimer {

    Canvas canvas;
    GraphicsContext gc;

    public GameLoopHandler() {}

    public GameLoopHandler(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    /**
     * Clears and draws the user interface again, updating it if necessary.
     */
    void updateView() {
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        drawGrid();
    }

    /**
     * Draws the grid for the user interface.
     */
    void drawGrid() {
        //number of vertical lines: 20
        //number of horizontal lines: 19

        double vertSpace = canvas.getWidth() / (20 + 1);
        double horSpace = canvas.getHeight() / (19 + 1);

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
     * This method executes during each loop of the game loop.
     * @param l The current system timestamp, in nanoseconds.
     */
    @Override
    public void handle(long l) {
        updateView();
    }
}
