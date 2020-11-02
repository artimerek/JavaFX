import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends  Application {

    @Override
    public void init() throws Exception {
        System.out.println("Before");
    }

    @Override
    public void start(Stage stage) throws Exception {


        stage.setTitle("Buttons");
        stage.setHeight(500);
        stage.setWidth(500);

        VBox root = new VBox();

        ToggleButton button = new ToggleButton("Blue");
        ToggleButton button2 = new ToggleButton("Red");
        ToggleButton button3 = new ToggleButton("Yellow");
        ToggleGroup toggleGroup = new ToggleGroup();
        button.setToggleGroup(toggleGroup);
        button2.setToggleGroup(toggleGroup);
        button3.setToggleGroup(toggleGroup);

        RadioButton radioButton = new RadioButton("Pomarańczowy");
        RadioButton radioButton1 = new RadioButton("Fioletowy");

        root.getChildren().addAll(button,button2,button3,radioButton,radioButton1);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop() throws Exception {
        System.out.println("After");
    }

    public static void main(String[] args) {

       launch(args);

    }
}
