import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;


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

        HBox root = new HBox();


        Button button = new Button("st");
        Button button1 = new Button("th");
        Button button2 = new Button("rd");
        Button button3 = new Button("th");

        root.setSpacing(5);
        root.setAlignment(Pos.BOTTOM_RIGHT);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setMargin(button,new Insets(10,10,10,10));
        root.setMargin(button2,new Insets(30,10,25,10));

        root.getChildren().addAll(button,button1,button2,button3);
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
