package Controller;

import DAO.PedidoJpaDao;
import Model.Cardapio;
import Model.Cliente;
import Model.Pedido;
import Model.StatusPedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PedidoController {

    private final PedidoJpaDao dao;

    public AnchorPane base;

    @FXML
    private ChoiceBox<Cliente> selecioneCliente;

    @FXML
    private ChoiceBox<StatusPedido> selecioneStatus;

    @FXML
    public javafx.scene.control.TextField valor_total;
    @FXML
    public javafx.scene.control.TextField observacoes;

    @FXML
    private ChoiceBox<Cardapio> selecioneCardapio;

    @FXML
    private DatePicker data_entrega;
    @FXML
    public javafx.scene.control.Button botaoVoltar;
    @FXML
    public javafx.scene.control.Button botaoBuscar;
    @FXML
    public javafx.scene.control.Button botaoSalvar;
    @FXML
    public javafx.scene.control.Button botaoExlcuir;


    public PedidoController() {
        this.dao = PedidoJpaDao.getInstance();
    }

    //Método para fazer as devidas recuperações dos dados no banco de dados e jogar na view de novo pedido.
    public void initialize() {

        //Nesse método ele pega o valor_pessoa da tabela Cardápio e adiciona no campo "valor_total" da minha tabela Pedidos.
        selecioneCardapio.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                double valorPessoa = newValue.getValorPessoa();
                valor_total.setText(String.valueOf(valorPessoa));
            }
            //Desabilita a edição do campo "Valor Total".
            valor_total.setEditable(false);
        });

        PedidoJpaDao pedidoJpaDao = PedidoJpaDao.getInstance();

        // Recupera os clientes do banco de dados
        List<Cliente> clientes = pedidoJpaDao.getAllClientes();

        // Preenche o ChoiceBox com os clientes
        ObservableList<Cliente> clienteList = FXCollections.observableArrayList(clientes);
        selecioneCliente.setItems(clienteList);

        // Recupera os status do pedido do banco de dados
        List<StatusPedido> statusPedidos = pedidoJpaDao.getAllStatusPedido();

        // Preenche o ChoiceBox com os status do pedido
        ObservableList<StatusPedido> statusPedidoList = FXCollections.observableArrayList(statusPedidos);
        selecioneStatus.setItems(statusPedidoList);

        // Recupera os cardápios do banco de dados
        List<Cardapio> cardapios = pedidoJpaDao.getAllCardapio();

        // Preenche o ChoiceBox com os cardápios
        ObservableList<Cardapio> cardapioList = FXCollections.observableArrayList(cardapios);
        selecioneCardapio.setItems(cardapioList);
    }

    @FXML private void botaoSalvar() {

        Pedido pedido = new Pedido();

        Cliente clienteSelecionado = selecioneCliente.getValue();
        StatusPedido statusPedidoSelecionado = selecioneStatus.getValue();
        Cardapio cardapioSelecionado = selecioneCardapio.getValue();
        LocalDate dataEntrega = data_entrega.getValue();
        String valorTotalStr = valor_total.getText();
        pedido.setObservacoes(observacoes.getText());


        // Verifica se os campos obrigatórios estão preenchidos
        if (clienteSelecionado == null || statusPedidoSelecionado == null || cardapioSelecionado == null || dataEntrega == null || valorTotalStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {

            // Configura os valores no pedido
            pedido.setCliente(clienteSelecionado);
            pedido.setStatusPedido(statusPedidoSelecionado);
            pedido.setCardapio(cardapioSelecionado);
            pedido.setObservacoes(observacoes.getText());
            pedido.setValorTotal(Double.parseDouble(valorTotalStr));
            pedido.setData_entrega(dataEntrega);

            // Salva o pedido no banco de dados
            dao.persist(pedido);
            JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso");
            camposLimpos();

        }
    }

    @FXML private void botaoBuscar() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar Pedido");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite o ID do Pedido:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String idPedido = result.get();

            // Chama o método getById
            Pedido pedidoLocalizado = PedidoJpaDao.getInstance().getById(Integer.parseInt(idPedido));

            if (pedidoLocalizado != null) {
                selecioneCliente.setValue(pedidoLocalizado.getCliente());
                selecioneStatus.setValue(pedidoLocalizado.getStatusPedido());
                selecioneCardapio.setValue(pedidoLocalizado.getCardapio());
                data_entrega.setValue(pedidoLocalizado.getData_entrega());
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum cardápio localizado com esse ID!");
            }
        }
    }
    @FXML private void botaoExlcuir() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Excluir Pedido");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite o ID do Pedido que deseja excluir:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(idPedido -> {
            try {
                int id = Integer.parseInt(idPedido);
                dao.removerPedido(id);
                JOptionPane.showMessageDialog(null, "Pedido removido com sucesso!");
                camposLimpos();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID inválido. Digite um número válido!");
            } catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o pedido.");
                t.printStackTrace();
            }
        });
    }

    //limpa os campos do formulário
    private void camposLimpos() {
        selecioneCliente.setValue(null);
        selecioneStatus.setValue(null);
        selecioneCardapio.setValue(null);
        data_entrega.setValue(null);
        valor_total.setText("");
        observacoes.setText("");
    }

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