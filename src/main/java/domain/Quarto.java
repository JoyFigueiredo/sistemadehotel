package domain;

import java.math.BigDecimal;
import java.util.UUID;
import domain.TipoQuarto;

public class Quarto {

    private UUID id;
    private String numero;
    private TipoQuarto tipo;
    private int capacidade;
    private BigDecimal preco;
    private boolean disponivel; // mudar depois, pois a disponibilidade do quarto deve ser gerenciada pelo sistema de reservas

    public Quarto(String numero, TipoQuarto tipo, int capacidade, BigDecimal preco, boolean disponivel) {
        this.id = UUID.randomUUID();
        this.numero = numero;
        this.tipo = tipo;
        setCapacidade(capacidade);
        setPreco(preco);
        this.disponivel = disponivel;
    }

    public Quarto(UUID id, String numero, TipoQuarto tipo, int capacidade, BigDecimal preco, boolean disponivel) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        setCapacidade(capacidade);
        setPreco(preco);
        this.disponivel = disponivel;
    }

    public UUID getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setPreco(BigDecimal preco) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço não pode ser nulo ou negativo.");
        }
        this.preco = preco;

    }

    public void setTipo(TipoQuarto tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de quarto não pode ser nulo.");
        }
        this.tipo = tipo;

    }

    public void setCapacidade(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser maior que zero.");
        }
        this.capacidade = capacidade;

    }

    public void setNumero(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("Número do quarto não pode ser nulo ou vazio.");
        }
        this.numero = numero;

    }
}
