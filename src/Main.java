import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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

        //Label label = new Label("Elo420");
        ImageView img = new ImageView("https://static-cdn.jtvnw.net/jtv_user_pictures/fximba-profile_image-8779ecca7c5d7fb1-300x300.png");
        Button button = new Button("Click me hehe");
        StackPane root = new StackPane(img,button);

//        StackPane.setAlignment(button,Pos.CENTER_LEFT);
        StackPane.setMargin(button,new Insets(30));


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
