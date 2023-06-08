package Controller;

import DAO.ClienteJpaDao;
import Model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ClienteController {

    private final ClienteJpaDao dao;

    public Cliente clienteLocalizado;

    public GridPane base;

    @FXML public javafx.scene.control.TextField id;
    @FXML public javafx.scene.control.TextField nome;
    @FXML public javafx.scene.control.TextField cpf;
    @FXML public javafx.scene.control.TextField telefone;
    @FXML public javafx.scene.control.TextField endereco;
    @FXML public javafx.scene.control.TextField cep;
    @FXML public javafx.scene.control.TextField cidade;
    @FXML public javafx.scene.control.TextField estado;
    @FXML public javafx.scene.control.TextField datanasc;
    @FXML public javafx.scene.control.Button botaoSalvar;

    public ClienteController() {
        this.dao = ClienteJpaDao.getInstance();
    }

    @FXML private void botaoSalvar(String action, Cliente cliente) throws IOException {

        cliente.setNome(String.valueOf(nome.getText()));
        cliente.setCpf(String.valueOf(cpf.getText()));
        cliente.setTelefone(String.valueOf(telefone.getText()));
        cliente.setEndereco(String.valueOf(telefone.getText()));
        cliente.setCep(String.valueOf(cep.getText()));
        cliente.setCidade(String.valueOf(cidade.getText()));
        cliente.setEstado(String.valueOf(estado.getText()));
        cliente.setDatanasc(String.valueOf(datanasc.getText()));
    }


    public void botaoSalvar(ActionEvent actionEvent) {
    }
}
