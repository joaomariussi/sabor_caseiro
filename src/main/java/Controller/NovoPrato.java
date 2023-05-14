package Controller;

import Model.Pratos;
import PadraoDAO.DAO;

public class NovoPrato {

    public static void main(String[] args) {

        Pratos pratos = new Pratos("Massa e Galeto", 30.0, "600 gramas de Massa, incluindo 3 galetos");

        DAO<Pratos> dao = new DAO<>(Pratos.class);
        dao.incluirAtomico(pratos).fechar();
    }
}
