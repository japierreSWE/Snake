package org.jpierre.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import org.jpierre.model.Model;
import org.jpierre.view.GameLoopHandler;

/**
 * Controller for all gameplay interactions
 */
public class GameController {

    @FXML Canvas gameCanvas;
    @FXML Label scoreValueLabel;
    Model model;

    @FXML
    public void initialize() {
        model = new Model();
        GameLoopHandler gameLoopHandler = new GameLoopHandler(gameCanvas, model);
        gameLoopHandler.start();
        scoreValueLabel.textProperty().bind(model.getScore().asString());
    }
}
