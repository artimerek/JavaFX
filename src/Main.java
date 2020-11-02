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

      //CSS

        stage.setTitle("CSS");
        stage.setHeight(500);
        stage.setWidth(500);

        VBox root = new VBox();
        root.getStylesheets().add("stylesheets/drugi.css");


        Label label1 = new Label("This is label with css.");
        Label label2 = new Label("Drugi css");
        label2.setId("drugi");

        Hyperlink link = new Hyperlink("Click me!");
        link.setStyle("-fx-background-color: azure; -fx-font-size: 15px");
        root.getChildren().addAll(label1,label2,link);


        Scene scene = new Scene(root);
        scene.getStylesheets().add("stylesheets/styles.css");
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
