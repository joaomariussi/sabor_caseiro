package Controller;

import DAO.ClienteJpaDao;
import Model.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class ClienteController {

    private final ClienteJpaDao dao;

    public GridPane base;

    @FXML
    public javafx.scene.control.TextField id;
    @FXML
    public javafx.scene.control.TextField nome;
    @FXML
    public javafx.scene.control.TextField cpf;
    @FXML
    public javafx.scene.control.TextField telefone;
    @FXML
    public javafx.scene.control.TextField endereco;
    @FXML
    public javafx.scene.control.TextField cep;
    @FXML
    public javafx.scene.control.TextField cidade;
    @FXML
    public javafx.scene.control.TextField estado;
    @FXML
    public javafx.scene.control.TextField datanasc;
    @FXML
    public javafx.scene.control.Button botaoSalvar;
    @FXML
    public javafx.scene.control.Button botaoVoltar;
    @FXML
    public javafx.scene.control.Button botaoExlcuir;
    @FXML
    public javafx.scene.control.Button botaoBuscar;

    public ClienteController() {
        this.dao = ClienteJpaDao.getInstance();
    }

    // Salva o cliente no banco
    @FXML private void botaoSalvar() {

        Cliente cliente = new Cliente();

        cliente.setNome(String.valueOf(nome.getText()));
        cliente.setCpf(String.valueOf(cpf.getText()));
        cliente.setTelefone(String.valueOf(telefone.getText()));
        cliente.setEndereco(String.valueOf(endereco.getText()));
        cliente.setCep(String.valueOf(cep.getText()));
        cliente.setCidade(String.valueOf(cidade.getText()));
        cliente.setEstado(String.valueOf(estado.getText()));
        cliente.setDatanasc(String.valueOf(datanasc.getText()));

        // Verifica se algum campo está vazio
        boolean algumCampoVazio = cliente.getNome().isEmpty() || cliente.getCpf().isEmpty() || cliente.getTelefone().isEmpty() || cliente.getEndereco().isEmpty() ||
                cliente.getCep().isEmpty() || cliente.getCidade().isEmpty() || cliente.getEstado().isEmpty();

        if (algumCampoVazio) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os dados do formulário!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            dao.persist(cliente);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
            camposLimpos();
        }
    }

    @FXML private void botaoBuscar() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar Cliente");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite o CPF do cliente:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String cpfCliente = result.get();

            // Chama o método listarCpf
            Cliente clienteLocalizado = dao.listarCpf(cpfCliente);

            //Adiciona os dados do cliente nos campos da view
            if (clienteLocalizado != null) {
                nome.setText(clienteLocalizado.getNome());
                cpf.setText(clienteLocalizado.getCpf());
                telefone.setText(clienteLocalizado.getTelefone());
                endereco.setText(clienteLocalizado.getEndereco());
                cep.setText(clienteLocalizado.getCep());
                cidade.setText(clienteLocalizado.getCidade());
                estado.setText(clienteLocalizado.getEstado());
                datanasc.setText(String.valueOf(clienteLocalizado.getDatanasc()));

                //Formata a data de nascimento para o padrão "dd/MM/yyyy"
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dataNascimento = dateFormat.format(clienteLocalizado.getDatanasc());
                datanasc.setText(dataNascimento);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum cliente localizado com esse CPF!");
            }
        }
    }
    @FXML private void botaoExlcuir() throws IOException {

        //Caixa de diálogo para o usuário escolher Sim ou Não.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText("Deseja realmente excluir esse cliente?");

        ButtonType buttonTypeSim = new ButtonType("Sim");
        ButtonType buttonTypeNao = new ButtonType("Não");

        alert.getButtonTypes().setAll(buttonTypeNao, buttonTypeSim);

        //Se a opção for SIM, o cliente é excluído.
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeSim) {
            Stage stage = (Stage) base.getScene().getWindow();
            Scene scene = new Scene(new FXMLLoader(getClass().getResource("/view/novoCliente/novoCliente.fxml")).load());
            stage.setTitle("Sabor Caseiro");
            stage.setScene(scene);
            stage.show();

            try {
                String cpfCliente = cpf.getText();
                dao.removerClientePorCPF(cpfCliente);
                JOptionPane.showMessageDialog(null, "O cliente foi removido com sucesso!");
                camposLimpos();
            } catch (Throwable $e) {
                JOptionPane.showMessageDialog(null, $e.getMessage());
            }
        }
    }

    //Limpa os campos do formulário
    private void camposLimpos() {
        nome.setText("");
        cpf.setText("");
        telefone.setText("");
        endereco.setText("");
        cep.setText("");
        cidade.setText("");
        estado.setText("");
        datanasc.setText("");
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
