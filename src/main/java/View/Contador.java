package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Contador extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label labelTitulo = new Label("Contador");
        Label labelNumero = new Label("0");

        Button botaoDecremento = new Button("-");
        Button botaoIncremento = new Button("+");

        HBox boxBotoes = new HBox();
        boxBotoes.getChildren().add(botaoDecremento);
        boxBotoes.getChildren().add(botaoIncremento);

        VBox boxPrincipal = new VBox();
        boxPrincipal.getChildren().add(labelTitulo);
        boxPrincipal.getChildren().add(labelNumero);
        boxPrincipal.getChildren().add(boxBotoes);

        Scene cenaPrincipal = new Scene(boxPrincipal, 400, 400);

        primaryStage.setScene(cenaPrincipal);
        primaryStage.show();

    }

}
