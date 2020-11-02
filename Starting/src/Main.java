import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class Main extends  Application {

    @Override
    public void init() throws Exception {
        System.out.println("Before");
    }

    @Override
    public void start(Stage stage) throws Exception {



        stage.setTitle("GridPaneane");
        stage.setHeight(250);
        stage.setWidth(250);

        Label label = new Label("user");
        Label label1 = new Label("email");
        Label label2 = new Label("psswd");

        TextField textField = new TextField();
        TextField textField1 = new TextField();
        PasswordField textField2 = new PasswordField();

        Button button = new Button("Utwórz");

        GridPane root = new GridPane();

        //kolumna, wiersz
        root.add(label, 0, 0);
        root.add(label1, 0, 1);
        root.add(label2, 0, 2);
        root.add(textField,1,0);
        root.add(textField1,1,1);
        root.add(textField2,1,2);
        root.add(button,1,3);
        //customize
        root.setVgap(15);
        root.setHgap(10);
        root.setPadding(new Insets(60));


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
