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

    @Column(name = "data_entrega")
    private Date data_entrega;

    @Column(name = "valor_total")
    private Double valor_total;

    @Column(name = "observacoes")
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_cardapio")
    private Cardapio cardapio;

    @ManyToOne
    @JoinColumn(name = "id_status_pedido")
    private StatusPedido statusPedido;

    public Pedido() {
    }

    public Pedido(String data_entrega, Double valor_total, String observacoes) {
        super();
        this.valor_total = valor_total;
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

    public Double getValorTotal() {
        return valor_total;
    }

    public void setValorTotal(Double valor_total) {
        this.valor_total = valor_total;
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

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }
}
