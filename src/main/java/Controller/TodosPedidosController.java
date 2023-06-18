package Controller;

import DAO.TodosPedidoJpaDao;
import Model.Cardapio;
import Model.Cliente;
import Model.Pedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TodosPedidosController {

    public AnchorPane base;

    @FXML
    private TableView<Pedido> tabelaPedidos;

    @FXML
    private TableColumn<Pedido, Integer> id;

    @FXML
    private TableColumn<Pedido, Cliente> cliente;

    @FXML
    private TableColumn<Pedido, Pedido> data_entrega;

    @FXML
    private TableColumn<Pedido, Pedido> valor_total;

    @FXML
    private TableColumn<Pedido, Pedido> statusPedido;

    @FXML
    private TableColumn<Pedido, Cardapio> cardapio;

    @FXML
    public javafx.scene.control.Button botaoVoltar;

    @FXML
    public javafx.scene.control.Button botaoExcluir;

    public void initialize() {

        // Configura as colunas para corresponder aos atributos do objeto Pedido
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        data_entrega.setCellValueFactory(new PropertyValueFactory<>("data_entrega"));
        valor_total.setCellValueFactory(new PropertyValueFactory<>("valor_total"));
        statusPedido.setCellValueFactory(new PropertyValueFactory<>("statusPedido"));
        cardapio.setCellValueFactory(new PropertyValueFactory<>("cardapio"));

        // Chama um método para carregar os pedidos do banco de dados e preencher a tabela
        carregarPedidos();
    }

    private void carregarPedidos() {
        TodosPedidoJpaDao todosPedidoJpaDao = TodosPedidoJpaDao.getInstance();
        List<Pedido> pedidos = TodosPedidoJpaDao.getAllPedidos();

        ObservableList<Pedido> observableList = FXCollections.observableArrayList(pedidos);
        tabelaPedidos.setItems(observableList);
    }

    public void botaoExcluir() throws IOException {

        Pedido pedidoSelecionado = tabelaPedidos.getSelectionModel().getSelectedItem();
        if (pedidoSelecionado != null) {
            int id = pedidoSelecionado.getId();

            // Exibe uma caixa de diálogo de confirmação
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType buttonTypeSim = new ButtonType("Sim");
            ButtonType buttonTypeNao = new ButtonType("Não");
            alert.getButtonTypes().setAll(buttonTypeNao, buttonTypeSim);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Confirme a exclusão do pedido");
            alert.setContentText("Tem certeza de que deseja excluir o pedido?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == buttonTypeSim) {

                // O usuário confirmou a exclusão, remove o pedido selecionado
                TodosPedidoJpaDao.getInstance().removerPedidos(id);

                // Atualiza a tabela após excluir o pedido
                carregarPedidos();

                // Obtém a cena atual do palco
                Stage stage = (Stage) base.getScene().getWindow();
                Scene scene = stage.getScene();

                // Recarrega a mesma cena
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/todosPedidos.fxml"));
                scene.setRoot(loader.load());

                // Atualiza o título do palco
                stage.setTitle("Sabor Caseiro");

                // Obtém a referência do controlador da cena
                TodosPedidosController controller = loader.getController();

                // Atualiza a lista de pedidos no controlador
                controller.carregarPedidos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um pedido para excluir.");
        }
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
