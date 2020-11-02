import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
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

      //Menu

        stage.setTitle("Buttons");
        stage.setHeight(500);
        stage.setWidth(500);

        VBox root = new VBox();

        MenuItem item = new MenuItem("Ziemniak");
        MenuItem item1 = new MenuItem("Kabel");
        MenuItem item2 = new MenuItem("Cukier");
        MenuItem item3 = new MenuItem("Pomidor");

        MenuButton menuButton = new MenuButton("Wybierz jedzonko",null,item,item1,item2,item3);
        Label food = new Label("Nie wybrano żarcia");

        item.setOnAction(e -> {
            food.setText("Ziemniak");
        });
        item1.setOnAction(e -> {
            food.setText("Kabel");
        });
        item2.setOnAction(e -> {
            food.setText("Cukier");
        });
        item3.setOnAction(e -> {
            food.setText("Pomidor");
        });



        root.getChildren().addAll(menuButton,food);
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
