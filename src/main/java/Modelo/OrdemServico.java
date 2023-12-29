package Modelo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class OrdemServico {
    private int id;
    private Cliente cliente;
    private Aparelho aparelho;
    private String observacao;
    private List<Servico> servicos;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private double valorTotal;

    public OrdemServico(){

    }
    public OrdemServico(Cliente cliente, Aparelho aparelho, String observacao,
                        List<Servico> servicos, LocalDate dataEntrada, LocalDate dataSaida,
                        double valorTotal) {
        this.cliente = cliente;
        this.aparelho = aparelho;
        this.observacao = observacao;
        this.servicos = servicos;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valorTotal = valorTotal;
    }

    public OrdemServico(int id, Cliente cliente, Aparelho aparelho, String observacao,
                        List<Servico> servicos, LocalDate dataEntrada, LocalDate dataSaida,
                        double valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.aparelho = aparelho;
        this.observacao = observacao;
        this.servicos = servicos;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Aparelho getAparelho() {
        return aparelho;
    }


    public void setAparelho(Aparelho aparelho) {
        this.aparelho = aparelho;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public double getValorTotal() {
        Double tot = 0.0;
        for (Servico servico : this.getServicos()) {
            tot += servico.getValor();
        }
        return tot;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
