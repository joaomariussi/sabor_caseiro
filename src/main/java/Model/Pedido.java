package Model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date data_entrega;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String pessoas;

    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_status_pedido")
    private StatusPedido statusPedido;

    public Pedido() {
    }

    public Pedido(String data_entrega, Double valor, String pessoas, String observacoes) {
        super();
        this.valor = valor;
        this.pessoas = pessoas;
        this.observacoes = observacoes;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.data_entrega = formatter.parse(String.valueOf(data_entrega));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(String data_entrega) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.data_entrega = formatter.parse(data_entrega);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getPessoas() {
        return pessoas;
    }

    public void setPessoas(String pessoas) {
        this.pessoas = pessoas;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }
}
