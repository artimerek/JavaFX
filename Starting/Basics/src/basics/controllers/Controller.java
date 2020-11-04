package basics.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private Button button;

    @FXML
    void initialize(){
        button.setText("Initialize method");
    }

    @FXML
    public void onActionButton(ActionEvent actionEvent) {
        System.out.println("Atak! " + actionEvent.getSource());
        if(actionEvent.getSource() instanceof CheckBox)
            System.out.println("+1 Checkbox");

    }

    @FXML
    public void onMouseEnteredButton(MouseEvent mouseEvent) {
        System.out.println("Obrona " + mouseEvent.getSource());
        System.out.println(mouseEvent.getClickCount());


    }

}
