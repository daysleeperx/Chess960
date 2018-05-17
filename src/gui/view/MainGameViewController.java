package gui.view;

import gui.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Represent Game controller class.
 */

public class MainGameViewController {
    @FXML
    Button goBack;

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Change scene when Go Back button is pressed.
     * @throws IOException
     */
    public void handleGoBack() throws IOException {
        mainApp.goBack();
    }
}
