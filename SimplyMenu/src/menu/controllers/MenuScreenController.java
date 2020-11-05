package menu.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuScreenController {

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    public void openApp(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/menu/resources/AppScreen.fxml"));

        Pane pane = null;

        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AppScreenController appScreenController = loader.getController();
        appScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(pane);

    }

    @FXML
    public void openOptions(ActionEvent actionEvent) {

    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        System.exit(1);

    }
}
