package pl.mylibrary.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ResourceBundle;

public class FxmlUtils {

    public static Pane fxmlLoader(String fxmlPath){
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        loader.setResources(getResourceBundle());
        try {
            return loader.load();
        } catch (IOException e) {
           DialogUtils.errorDialog(e.getMessage());
        }
        return null;
    }

    public static ResourceBundle getResourceBundle(){
        return  ResourceBundle.getBundle("bundles.messages");
    }
}
