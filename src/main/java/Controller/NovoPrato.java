package Controller;

import Model.Prato;
import PadraoDAO.DAO;

public class NovoPrato {

    public static void main(String[] args) {

        Prato prato = new Prato("Massa e Galeto", 30.0, "600 gramas de Massa, incluindo 3 galetos");

        DAO<Prato> dao = new DAO<>(Prato.class);
        dao.incluirAtomico(prato).fecharTrasacao();
    }
}
