package Controller;

import Application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroController extends Main {

    public void cadastroCliente() throws IOException {
        Stage stage = (Stage) base.getScene().getWindow();
        Scene scene = new Scene(
                new FXMLLoader( getClass().getResource("/view/Records/client.fxml") ).load()
        );
        stage.setTitle("Cadastro de Cliente");
        stage.setScene(scene);
        stage.show();
    }


}
