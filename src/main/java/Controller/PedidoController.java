package Controller;

import DAO.PedidoJpaDao;
import Model.Cardapio;
import Model.Cliente;
import Model.Pedido;
import Model.StatusPedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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

    public void initialize() {

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


    @FXML private void botaoSalvar(ActionEvent actionEvent) throws IOException {
        Pedido pedido = new Pedido();

        Cliente clienteSelecionado = selecioneCliente.getValue();
        StatusPedido statusPedidoSelecionado = selecioneStatus.getValue();
        Cardapio cardapioSelecionado = selecioneCardapio.getValue();
        LocalDate dataEntrega = data_entrega.getValue();
        String valorTotalStr = valor_total.getText();
        pedido.setObservacoes(String.valueOf(observacoes.getText()));


        // Verifica se os campos obrigatórios estão preenchidos
        if (clienteSelecionado == null || statusPedidoSelecionado == null || cardapioSelecionado == null || dataEntrega == null || valorTotalStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {

            // Configura os valores no pedido
            pedido.setCliente(clienteSelecionado);
            pedido.setStatusPedido(statusPedidoSelecionado);
            pedido.setCardapio(cardapioSelecionado);
            pedido.setObservacoes(String.valueOf(observacoes));
            pedido.setValorTotal(Double.parseDouble(valorTotalStr));
            pedido.setData_entrega(dataEntrega);

            // Salva o pedido no banco de dados
            dao.persist(pedido);
            JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso");
            camposLimpos();

        }
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