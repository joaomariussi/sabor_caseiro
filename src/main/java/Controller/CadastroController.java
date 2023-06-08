package Controller;

import Application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroController extends Main {

    @FXML public Button cadastrarCliente;
    @FXML public Button voltarCadastro;

    public void cadastrarCliente() throws IOException {
        Stage stage = (Stage) base.getScene().getWindow();
        Scene scene = new Scene(
                new FXMLLoader( getClass().getResource("/view/Records/cliente.fxml") ).load()
        );
        stage.setTitle("Cadastro de Cliente");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @throws IOException |
     */
    @FXML public void voltarCadastro() throws IOException {
        Stage stage = (Stage) base.getScene().getWindow();
        Scene scene = new Scene( new FXMLLoader( getClass().getResource("/view/Application/main.fxml") ).load() );
        stage.setTitle("Sabor Caseiro");
        stage.setScene(scene);
        stage.show();
    }

}
