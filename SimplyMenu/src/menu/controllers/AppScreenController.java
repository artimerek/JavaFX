package menu.controllers;


import javafx.event.ActionEvent;

public class AppScreenController {

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public void onBackAction(ActionEvent actionEvent) {
        mainScreenController.loadMenu();

    }
}
