package View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrimeiroFX extends Application {


    @Override
    public void start(Stage primaryStage) {

        Button button1 = new Button("A");
        Button button2 = new Button("B");
        Button button3 = new Button("C");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().add(button1);
        vBox.getChildren().add(button2);
        vBox.getChildren().add(button3);

        Scene scene = new Scene(vBox, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
