package basics;

import basics.controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/basics/resources/sample.fxml"));
        StackPane stackPane = fxmlLoader.load();

        Controller controller = fxmlLoader.getController();

        Scene scene = new Scene(stackPane);

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Im so dumb");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
