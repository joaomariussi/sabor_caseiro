package Controller;

import Model.Prato;
import PadraoDAO.PratosDAO;

import java.util.List;

public class ObterPratos {

    public static void main(String[] args) {

        PratosDAO dao = new PratosDAO(Prato.class);
        List<Prato> pratoes = dao.obterTodos();

        for (Prato prato1 : pratoes) {
            System.out.println("ID: " + prato1.getId() + ", Nome: " + prato1.getNome());
        }

        double precoTotal = pratoes.stream().map(Prato::getValor).reduce(0.0, Double::sum);
        System.out.println("O valor total dos pratoes é de: R$" + precoTotal);
    }










}
