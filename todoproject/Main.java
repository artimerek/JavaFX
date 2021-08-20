package todoproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import todoproject.data.Items;
import todoproject.data.ItemsData;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("todoMainWindow.fxml"));
        primaryStage.setTitle("TodoList");
        primaryStage.setScene(new Scene(root, 700, 375));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        try{
            ItemsData.getInstance().loadItems();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void stop() throws Exception {
        try{
            ItemsData.getInstance().saveItems();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
