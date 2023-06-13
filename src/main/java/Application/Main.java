package Application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @FXML public Pane base;
    @FXML public Button novoPedido;
    @FXML public Button cadastrarCliente;
    @FXML public Button cadastrarCardapio;
    @FXML public Button cadastrarPrato;

    /**
     * @param args |
     */
    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene;
        scene = new Scene(
                new FXMLLoader( getClass().getResource("/view/Login/login.fxml") ).load()
        );
        stage.setTitle("Sabor Caseiro");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/logo1.png")));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void novoPedido() throws IOException {
        Stage stage = (Stage) base.getScene().getWindow();
        Scene scene = new Scene(
                new FXMLLoader( getClass().getResource("/view/NovoPedido/novoPedido.fxml") ).load()
        );
        stage.setTitle("Novo Pedido");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void cadastrarCardapio() throws IOException {
        Stage stage = (Stage) base.getScene().getWindow();
        Scene scene = new Scene(
                new FXMLLoader( getClass().getResource("/view/NovoCardapio/novoCardapio.fxml") ).load()
        );
        stage.setTitle("Cadastro de Cardápio");
        stage.setScene(scene);
        stage.show();
    }

    public void cadastrarCliente() throws IOException {
        Stage stage = (Stage) base.getScene().getWindow();
        Scene scene = new Scene(
                new FXMLLoader( getClass().getResource("/view/NovoCliente/novoCliente.fxml") ).load()
        );
        stage.setTitle("Cadastro de Cliente");
        stage.setScene(scene);
        stage.show();
    }

    public void cadastrarPrato() throws IOException {
        Stage stage = (Stage) base.getScene().getWindow();
        Scene scene = new Scene(
                new FXMLLoader( getClass().getResource("/view/NovoPrato/novoPrato.fxml") ).load()
        );
        stage.setTitle("Cadastro de Pratos");
        stage.setScene(scene);
        stage.show();
    }
}