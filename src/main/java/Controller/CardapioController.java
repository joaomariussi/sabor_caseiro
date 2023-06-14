package Controller;

import DAO.CardapioJpaDao;
import Model.Cardapio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;


public class CardapioController {

    private final CardapioJpaDao dao;

    public GridPane base;
    @FXML
    public javafx.scene.control.TextField id;
    @FXML
    public javafx.scene.control.TextField nome;
    @FXML
    public javafx.scene.control.TextField valor_pessoa;
    @FXML
    public javafx.scene.control.Button botaoVoltar;
    @FXML
    public javafx.scene.control.Button botaoBuscar;
    @FXML
    public javafx.scene.control.Button botaoSalvar;
    @FXML
    public javafx.scene.control.Button botaoExlcuir;

    public CardapioController() {
        this.dao = CardapioJpaDao.getInstance();
    }

    @FXML private void botaoSalvar(ActionEvent actionEvent) throws IOException {

        Cardapio cardapio = new Cardapio();

        cardapio.setNome(String.valueOf(nome.getText()));
        cardapio.setValorPessoa(Double.valueOf(String.valueOf(valor_pessoa.getText())));

        // Verifica se algum campo está vazio
        boolean algumCampoVazio = cardapio.getNome().isEmpty() || cardapio.getValorPessoa().equals("");


        if (algumCampoVazio) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os dados do formulário!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            dao.persist(cardapio);
            JOptionPane.showMessageDialog(null, "Cardápio cadastrado com sucesso");
            camposLimpos();
        }
    }

    @FXML private void botaoBuscar() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar Cardápio");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite o ID do Cardápio:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String idCardapio = result.get();

            // Chama o método getById
            Cardapio cardapioLocalizado = CardapioJpaDao.getInstance().getById(Integer.parseInt(idCardapio));

            if (cardapioLocalizado != null) {
                nome.setText(cardapioLocalizado.getNome());
                valor_pessoa.setText(String.valueOf(cardapioLocalizado.getValorPessoa()));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum cardápio localizado com esse ID!");
            }
        }
    }

    @FXML private void botaoExlcuir() throws IOException {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Excluir Cardápio");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite o ID do Cardápio que deseja excluir:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(idCardapio -> {
            try {
                int id = Integer.parseInt(idCardapio);
                dao.removerCardapio(id);
                JOptionPane.showMessageDialog(null, "Cardápio removido com sucesso!");
                camposLimpos();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID inválido. Digite um número válido!");
            } catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o cardápio.");
                t.printStackTrace();
            }
        });
    }

    private void camposLimpos() {
        nome.setText("");
        valor_pessoa.setText("");
    }

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
                    new FXMLLoader( getClass().getResource("/view/Application/main.fxml") ).load()
            );
            stage.setTitle("Sabor Caseiro");
            stage.setScene(scene);
            stage.show();
        }
    }
}
