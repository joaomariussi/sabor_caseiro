package Controller;

import DAO.CardapioJpaDao;
import Model.Cardapio;
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

    @FXML private void botaoSalvar() {

        Cardapio cardapio = new Cardapio();

        cardapio.setNome(String.valueOf(nome.getText()));

        //Faz a validação se os campos estão preenchidos.
        String valorPessoaText = valor_pessoa.getText();
        if (valorPessoaText.isEmpty() || cardapio.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os dados do formulário!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double valorPessoa = Double.parseDouble(valorPessoaText);
            cardapio.setValorPessoa(valorPessoa);

            dao.persist(cardapio);
            JOptionPane.showMessageDialog(null, "Cardápio cadastrado com sucesso");
            camposLimpos();

            //cai nesse catch, caso o valor passado pelo usuário não seja um valor double, no caso uma string.
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite um valor válido!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
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

    @FXML private void botaoExlcuir() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Excluir Cardápio");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite o ID do Cardápio que deseja excluir:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(idCardapio -> {
            try {
                int id = Integer.parseInt(idCardapio);
                Cardapio cardapio = dao.existeCardapio(id); // Verifica se o ID do cardápio existe. Se existe, cai no primeiro else.

                if (cardapio != null) {
                    dao.removerCardapio(id);
                    JOptionPane.showMessageDialog(null, "Cardápio removido com sucesso!");
                    camposLimpos();
                } else {
                    JOptionPane.showMessageDialog(null, "O ID do cardápio não existe!");
                }
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
