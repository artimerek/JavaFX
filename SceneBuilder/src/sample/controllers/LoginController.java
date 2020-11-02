package sample.controllers;

import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyEvent;

public class LoginController {

    public ChoiceBox poop = null;

    public void onKeyTyped(KeyEvent keyEvent) {
        System.out.println("Nacisnięto przycisk");
    }

    public void onKeyPress(KeyEvent keyEvent) {
        poop.setRotate(poop.getRotate()+5);
    }
}
