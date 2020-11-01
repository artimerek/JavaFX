import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.*;
import javafx.scene.control.Label; // <- important for get child
import java.awt.*;
import javafx.scene.web.*;
import org.w3c.dom.ls.LSOutput;

public class Main extends  Application {

    @Override
    public void init() throws Exception {
        System.out.println("Before");
    }

    @Override
    public void start(Stage stage) throws Exception {

       stage.setTitle("Hyperlinki");
       stage.setWidth(450);
       stage.setHeight(500);

       VBox root = new VBox();

       Label text = new Label("Nie klikniete");
       ImageView image = new ImageView("https://upload.wikimedia.org/wikipedia/commons/d/d6/AndrzejGrabowski.jpg");
       Hyperlink link1 = new Hyperlink("Klikaj!",image);

       link1.setOnAction(e ->{
           text.setText("Klikniete!");
       });
       
       root.getChildren().addAll(text,link1);


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
