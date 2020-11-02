package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    //scenebuilder

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
       Parent homeRoot = FXMLLoader.load(getClass().getResource("resources/home.fxml"));
       Scene home = new Scene(homeRoot);
       Parent loginRoot = FXMLLoader.load(getClass().getResource("resources/login.fxml"));
       Scene login = new Scene(loginRoot);


        primaryStage.setScene(login);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
