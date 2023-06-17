package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class LoginController {

    public TextField usuario;
    public PasswordField senha;
    public Pane base;
    public Button entrar;

    public void entrar() throws IOException {
        if (
                Objects.equals(usuario.getText(), "saborcaseiro") &&
                        Objects.equals(senha.getText(), "123")
        ) {
            Scene scene = new Scene(
                    new FXMLLoader(getClass().getResource("/view/Application/main.fxml")).load()
            );
            Stage stage = (Stage) base.getScene().getWindow();
            stage.setTitle("Sabor Caseiro");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/logo1.png")));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Verifique os dados e tente novamente",
                    "Falha ao realizar login",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
