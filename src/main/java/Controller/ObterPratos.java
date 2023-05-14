package Controller;

import Model.Pratos;
import PadraoDAO.PratosDAO;

import java.util.List;

public class ObterPratos {

    public static void main(String[] args) {

        PratosDAO dao = new PratosDAO(Pratos.class);
        List<Pratos> pratos = dao.obterTodos();

        for (Pratos pratos1: pratos) {
            System.out.println("ID: " + pratos1.getId() + ", Nome: " + pratos1.getNome());
        }

        double precoTotal = pratos.stream().map(Pratos::getValor).reduce(0.0, Double::sum);
        System.out.println("O valor total dos pratos é de: R$" + precoTotal);
    }










}
