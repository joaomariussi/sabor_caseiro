package Controller;

import DAO.PratoJpaDao;
import Model.Cardapio;
import Model.PratosCardapio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PratoController {
    private final PratoJpaDao dao;

    public GridPane base;
    @FXML
    public javafx.scene.control.TextField desc_prato;
    @FXML
    private ChoiceBox<Cardapio> selecioneCardapio;
    @FXML
    public javafx.scene.control.Button botaoVoltar;
    @FXML
    public javafx.scene.control.Button botaoSalvar;
    @FXML

    public javafx.scene.control.Button botaoExlcuir;

    public PratoController() {
        this.dao = PratoJpaDao.getInstance();
    }

    public void initialize() {
        PratoJpaDao pratoDao = PratoJpaDao.getInstance();

        // Recupera os cardápios do banco de dados
        List<Cardapio> cardapios = pratoDao.getAllCardapios();

        // Preenche o ChoiceBox com os cardápios
        ObservableList<Cardapio> cardapiosList = FXCollections.observableArrayList(cardapios);
        selecioneCardapio.setItems(cardapiosList);
    }

    @FXML private void botaoSalvar() {

        Cardapio cardapioSelecionado = selecioneCardapio.getValue();

        PratosCardapio pratosCardapio = new PratosCardapio();
        pratosCardapio.setCardapio(cardapioSelecionado);

        pratosCardapio.setDescPrato(String.valueOf(desc_prato.getText()));


        // Verifica se algum campo está vazio
        boolean algumCampoVazio = pratosCardapio.getDescPrato() == null || pratosCardapio.getDescPrato().isEmpty() || pratosCardapio.getCardapio() == null;

        if (algumCampoVazio) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os dados!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            dao.persist(pratosCardapio);
            JOptionPane.showMessageDialog(null, "Prato cadastrado com sucesso");
            camposLimpos();
        }
    }

    @FXML private void botaoExlcuir() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Excluir Prato");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite o ID do Prato que deseja excluir:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(idPrato -> {
            try {
                int id = Integer.parseInt(idPrato);
                PratosCardapio pratosCardapio = dao.existePrato(id); // Verifica se o ID do prato existe. Se existe, cai no primeiro else.

                if (pratosCardapio != null) {
                    dao.removerPrato(id);
                    JOptionPane.showMessageDialog(null, "Prato removido com sucesso!");
                    camposLimpos();
                } else {
                    JOptionPane.showMessageDialog(null, "O ID do prato não existe!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID inválido. Digite um número válido!");
            } catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o prato.");
                t.printStackTrace();
            }
        });
    }

    //Limpa os campos do formulário
    private void camposLimpos() {
        desc_prato.setText("");
        selecioneCardapio.setValue(null);
    }

    public void botaoVoltar() throws IOException {
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

