package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage historyStage = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/calculator.fxml"));
        primaryStage.setTitle("Kalkulator");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        CreateHistoryWindows();

    }

    public static Stage getHistoryStage() {
        return historyStage;
    }

    public void CreateHistoryWindows() throws  Exception{
        historyStage = new Stage();
        historyStage.setTitle("Historia");
        historyStage.setAlwaysOnTop(true);
        historyStage.setResizable(false);
        historyStage.initModality(Modality.APPLICATION_MODAL);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
