package sample.controllers;

import javafx.scene.input.MouseEvent;

public class HomeController {

    public void onClickEvent(MouseEvent mouseEvent) {
        System.out.println("Kliknięto!");
    }

    public void dragEvent(MouseEvent mouseEvent) {
        System.out.println("Najechano");
    }
}
