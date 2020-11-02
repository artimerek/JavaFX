import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
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


        stage.setTitle("BordePane");
        stage.setWidth(400);
        stage.setHeight(400);

        BorderPane root = new BorderPane();

        root.setTop(new TextField("Góra"));

        Button button = new Button("st");
        Button button1 = new Button("nd");
        Button button2 = new Button("rd");
        Button button3 = new Button("th");
        Button button4 = new Button("thh");

        root.setCenter(button);
        root.setBottom(button2);
        root.setLeft(button3);
        root.setRight(button4);

        BorderPane.setAlignment(button1,Pos.CENTER);
        BorderPane.setAlignment(button3,Pos.TOP_LEFT);


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
