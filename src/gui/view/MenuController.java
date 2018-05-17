package gui.view;

import gui.Main;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    AnchorPane pane;
    @FXML
    ImageView play;
    @FXML
    ImageView crown;
    @FXML
    ImageView settings;
    @FXML
    ImageView stockFish;
    @FXML
    Label infoLabel1;
    @FXML
    Label infoLabel2;
    @FXML
    ImageView arrow;


    private Main mainApp;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set label opacity
        infoLabel1.setOpacity(0);
        infoLabel2.setOpacity(0);
        arrow.setOpacity(0);
        arrow.setDisable(true);


        // set icon coordinates
        play.setLayoutX(play.getLayoutX() - 300);
        crown.setLayoutX(crown.getLayoutX() + 300);
        settings.setLayoutX(settings.getLayoutX() + 300);
        stockFish.setLayoutX(stockFish.getLayoutX() - 300);

        // set hover effect
        setOnHover(play);
        setOnHover(crown);
        setOnHover(settings);
        setOnHover(stockFish);
        setOnHover(arrow);

        // slide int buttons
        slideInButtons();


    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Handle play button.
     */
    @FXML
    public void handlePlayButton() {
        // animate icons and change scene
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), play);
        translateTransition.setToX(-300);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), crown);
        transition.setToX(300);
        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1), settings);
        transition1.setToX(300);
        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1), stockFish);
        transition2.setToX(-300);

        ParallelTransition parallelTransition = new ParallelTransition(translateTransition, transition, transition1, transition2);
        parallelTransition.play();
        parallelTransition.setOnFinished(event -> {
            try {
                mainApp.toGameScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Handle info button.
     */
    public void handleInfoButton() {
        slideOutButtons();

        arrow.setDisable(false);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), infoLabel1);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(2), infoLabel2);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(2), arrow);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);

        SequentialTransition sequentialTransition = new SequentialTransition(fadeTransition, fadeTransition1, fadeTransition2);
        sequentialTransition.play();
    }

    public void handleBackArrow() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), infoLabel1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), infoLabel2);
        fadeTransition1.setFromValue(1);
        fadeTransition1.setToValue(0);

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(0.5), arrow);
        fadeTransition2.setFromValue(1);
        fadeTransition2.setToValue(0);

        SequentialTransition sequentialTransition = new SequentialTransition(fadeTransition, fadeTransition1, fadeTransition2);
        sequentialTransition.play();
        sequentialTransition.setOnFinished(event -> {
            arrow.setDisable(true);
            slideInButtons();
        });

    }

    /**
     * Slide buttons out of view.
     */
    public void slideOutButtons() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), play);
        translateTransition.setToX(-300);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), crown);
        transition.setToX(300);
        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1), settings);
        transition1.setToX(300);
        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1), stockFish);
        transition2.setToX(-300);

        ParallelTransition parallelTransition = new ParallelTransition(translateTransition, transition, transition1, transition2);
        parallelTransition.play();
    }

    /**
     * Slide buttons into view.
     */
    private void slideInButtons() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), play);
        translateTransition.setToX(300);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), crown);
        transition.setToX(-300);
        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1), settings);
        transition1.setToX(-300);
        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1), stockFish);
        transition2.setToX(300);

        ParallelTransition parallelTransition = new ParallelTransition(translateTransition, transition, transition1, transition2);
        parallelTransition.play();
    }

    private void setOnHover(ImageView icon) {
        icon.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), icon);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.play();
        });

        icon.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), icon);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });

    }


}
