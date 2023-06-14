package Controller;

import DAO.PratoJpaDao;
import Model.Cardapio;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.collections.ObservableList;


import javax.swing.*;
import java.io.IOException;
import java.util.List;


public class PratoController {

    private final PratoJpaDao dao;

    public GridPane base;

    @FXML
    private ChoiceBox<Cardapio> selecioneCardapio;


    public PratoController() {
        this.dao = PratoJpaDao.getInstance();
    }

    public void initialize() {
        PratoJpaDao pratoDao = PratoJpaDao.getInstance();

        // Recuperar os cardápios do banco de dados
        List<Cardapio> cardapios = pratoDao.getAllCardapios();

        // Preencher o ChoiceBox com os cardápios
        ObservableList<Cardapio> cardapiosList = FXCollections.observableArrayList(cardapios);
        selecioneCardapio.setItems(cardapiosList);
    }
    public void botaoVoltar () throws IOException {
        Object[] opcoes = {"Cancelar", "Confirmar"};
        int back = JOptionPane.showOptionDialog(
                null,
                "Ao voltar, todos os seus dados não salvos serão perdidos!",
                "Atenção",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null, opcoes, opcoes[0]
            );
        if (back == 1) {
            Stage stage = (Stage) base.getScene().getWindow();
            Scene scene = new Scene(
                    new FXMLLoader(getClass().getResource("/view/Application/main.fxml")).load()
            );
            stage.setTitle("Sabor Caseiro");
            stage.setScene(scene);
            stage.show();
        }
    }
}
