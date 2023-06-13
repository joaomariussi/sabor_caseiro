package Model;

import javax.persistence.*;

@Entity
@Table(name = "cardapio")

public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "valor_pessoa")
    private Double valor_pessoa;

    public Cardapio() {
    }

    public Cardapio(String nome, Double valor_pessoa) {
        super();
        this.nome = nome;
        this.valor_pessoa = valor_pessoa;
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
