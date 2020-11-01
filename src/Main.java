import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.*;
import javafx.scene.control.Label; // <- important for get child
import java.awt.*;

public class Main extends  Application {

    @Override
    public void init() throws Exception {
        System.out.println("Before");
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("First App");
        stage.show();

        Stage stage2 = new Stage();
        stage2.setTitle("Second app");
        stage2.setWidth(400);
        stage2.setHeight(300);

        stage2.setX(50);
        stage2.setY(100);

        VBox parent = new VBox();

        Label label1 = new Label("This is label");
        Label label2 = new Label("Second label");
        parent.getChildren().addAll(label1,label2);

        Scene scene1 = new Scene(parent);
        scene1.setCursor(Cursor.CROSSHAIR);
        stage2.setScene(scene1);


        stage2.show();

        /*
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.show();
        */

        Stage newWindow1 = new Stage();
        newWindow1.initStyle(StageStyle.DECORATED);
        newWindow1.initOwner(stage2);
        newWindow1.initModality(Modality.WINDOW_MODAL);
        newWindow1.show();

    }

    @Override
    public void stop() throws Exception {
        System.out.println("After");
    }

    public static void main(String[] args) {

       launch(args);

    }
}
