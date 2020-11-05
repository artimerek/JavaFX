package basics.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class Controller {

    @FXML
    private Button button;

    public Controller(){
        System.out.println("Kontrol");
    }

    @FXML
    void initialize() {
    button.setText("Nowa nazwa");

    EventHandler<ActionEvent> handler = e -> {
        System.out.println("Handler lambda1");
    };
    //lambdas hype

    button.addEventHandler(ActionEvent.ACTION,handler);

    EventHandler<MouseEvent> mouseHandlerLambda = a ->{
        System.out.println("Handler lambda");
    };

    button.addEventHandler(MouseEvent.MOUSE_ENTERED,mouseHandlerLambda);

    button.setOnAction(e -> {
        System.out.println("Handler set on");
    });
    }
}
