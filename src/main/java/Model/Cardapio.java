package Model;

import javax.persistence.*;

@Entity
@Table(name = "cardapio")

public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double valor_pessoa;

    public Cardapio() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorPessoa() {
        return valor_pessoa;
    }

    public void setValorPessoa(Double valor_pessoa) {
        this.valor_pessoa = valor_pessoa;
    }
}
