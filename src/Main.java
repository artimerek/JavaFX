import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

        stage.setTitle("Buttons");
        stage.setHeight(500);
        stage.setWidth(500);

        VBox root = new VBox();

        Button button = new Button("Klikaj");
        button.setStyle("-fx-font-size: 55px");
        button.setText("_Nope.Nope.Nope.Nope.Nope");
        button.setWrapText(true);

        button.setMinSize(5,5);
        button.setPrefSize(250,250);
        button.setMaxSize(500,500);

        button.setMnemonicParsing(true); //alt
        button.setOnAction(e -> {
            System.out.println("Klikniete");
        });

        root.getChildren().addAll(button);
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
