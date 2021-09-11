package pl.artimerek;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.artimerek.model.Datasource;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        //  Querying artist before ui starts
        controller.listArtist();
        primaryStage.setTitle("MusicDB");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }


    //  Overwritten method used to connect to the database at the start of the application
    @Override
    public void init() throws Exception {
        super.init();
        //  openConnection() method returns boolean so we can check status of connection
        if(!Datasource.getInstance().openConnection()){
            System.out.println("Database connection error");
            Platform.exit();
        }
    }

    //  Similarly to the method above, the method for closing the connection
    @Override
    public void stop() throws Exception {
        super.stop();
        Datasource.getInstance().closeConnection();
    }
}
