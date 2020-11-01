import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
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

public class Main extends  Application {

    @Override
    public void init() throws Exception {
        System.out.println("Before");
    }

    @Override
    public void start(Stage stage) throws Exception {

       stage.setTitle("Obrazek");
       stage.setWidth(300);
       stage.setHeight(500);

       Stage stage1 = new Stage();
       stage1.setTitle("ObrazekLabel");
       stage1.setWidth(300);
       stage1.setHeight(500);




       //obrazek z napisem
       VBox root1 = new VBox();
       ImageView image1 = new ImageView("https://upload.wikimedia.org/wikipedia/commons/d/d6/AndrzejGrabowski.jpg");
       Label label1 = new Label("This is label");
       Label label2 = new Label("Ziemniaki");
       label2.setTextFill(Color.web("#34eb8f"));
       label2.setFont(new Font("Cambria",45));
       root1.getChildren().addAll(label1,image1,label2);
       label2.setRotate(45);


       //sam obrazek
       VBox root = new VBox();
       Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/d/d6/AndrzejGrabowski.jpg");
       ImageView imageView = new ImageView(image);
       root.getChildren().addAll(imageView);


       Scene scene = new Scene(root);
       Scene scene2 = new Scene(root1);

       stage.setScene(scene);
       stage1.setScene(scene2);
       stage.show();
       stage1.show();

    }

    @Override
    public void stop() throws Exception {
        System.out.println("After");
    }

    public static void main(String[] args) {

       launch(args);

    }
}
