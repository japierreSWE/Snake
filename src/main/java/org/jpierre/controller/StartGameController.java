package org.jpierre.controller;

import javafx.fxml.FXML;
import org.jpierre.App;

import java.io.IOException;

/**
 * A Controller that facilitates beginning the game after the
 * user clicks "Start Game".
 */
public class StartGameController {

    @FXML
    private void startGame() throws IOException {
        App.setRoot("gamescene");
    }

}
