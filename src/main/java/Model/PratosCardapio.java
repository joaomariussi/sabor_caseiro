package Model;

import javax.persistence.*;

@Entity
@Table(name = "pratos_cardapio")

public class PratosCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "desc_prato")
    private String desc_prato;

    @ManyToOne
    @JoinColumn(name = "id_cardapio")
    private Cardapio cardapio;

    public PratosCardapio() {

    }

    public PratosCardapio(String desc_prato) {
        super();
        this.desc_prato = desc_prato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescPrato() {
        return desc_prato;
    }

    public void setDescPrato(String desc_prato) {
        this.desc_prato = desc_prato;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

}
