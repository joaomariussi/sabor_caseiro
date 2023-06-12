package Controller;

import DAO.PratoJpaDao;
import Model.PratosCardapio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;


public class PratoController {

    private final PratoJpaDao dao;

    public GridPane base;

    @FXML
    public javafx.scene.control.TextField id;
    @FXML
    public javafx.scene.control.TextField desc_prato;
    @FXML
    public javafx.scene.control.TextField id_cardapio;

    public PratoController() {
        this.dao = PratoJpaDao.getInstance();
    }

    // Salva o cliente no banco
    @FXML private void botaoSalvar(ActionEvent actionEvent) throws IOException {

        PratosCardapio pratosCardapio = new PratosCardapio();

        ///  pratosCardapio.setCardapio(String.valueOf(id_cardapio.getText()));
        pratosCardapio.setDescPrato(String.valueOf(desc_prato.getText()));

        // Verifica se algum campo está vazio
        boolean algumCampoVazio = pratosCardapio.getCardapio().isEmpty() || pratosCardapio.getDescPrato().isEmpty();

        if (algumCampoVazio) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os dados do formulário!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            dao.persist(pratosCardapio);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");

            // Limpa os campos do formulário
            id_cardapio.setText("");
            desc_prato.setText("");
        }
    }

    @FXML private void botaoExlcuir() throws IOException {

        try {
            dao.removeById(Integer.parseInt(id.getText()));

            JOptionPane.showMessageDialog(null,"O prato foi removido com sucesso!");
            camposLimpos();
        } catch (Throwable $e) {
            JOptionPane.showMessageDialog(null, $e.getMessage());

        }
    }

    private void camposLimpos() {
        id_cardapio.setText("");
        desc_prato.setText("");
    }

    //public Cliente buscaCpf(String cpf) {
      //  dao.getByCpf(cpf);
        //return null;
    //}

    /**
     *
     * @throws IOException |
     */
    public void botaoVoltar() throws IOException {
        Object[] opcoes = { "Cancelar", "Confirmar" };
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
                    new FXMLLoader( getClass().getResource("/view/Cadastros/cadastros.fxml") ).load()
            );
            stage.setTitle("Sabor Caseiro");
            stage.setScene(scene);
            stage.show();
        }
    }


}
