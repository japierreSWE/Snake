package org.jpierre.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import org.jpierre.view.GameLoopHandler;

/**
 * Controller for all gameplay interactions
 */
public class GameController {

    @FXML Canvas gameCanvas;
    @FXML Label scoreValueLabel;

    @FXML
    public void initialize() {
        GameLoopHandler gameLoopHandler = new GameLoopHandler(gameCanvas);
        gameLoopHandler.start();
    }

}
