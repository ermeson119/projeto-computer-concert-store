package Modelo;

import java.util.Objects;

public class Cliente{
    private int id;
    private String nome;
    private String telefone;
    private String endereco;

    public Cliente() {
        super();
    }

    public Cliente(int codigoCliente, String nome, String telefone, String endereco) {
        this.id = codigoCliente;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Cliente(String nome, String telefone, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Cliente cliente = (Cliente) o;

            return Objects.equals(this.getNome(), cliente.getNome())
                    || Objects.equals(this.getTelefone(), cliente.getTelefone());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + nome.hashCode();
        result = 31 * result + telefone.hashCode();
        return result;
    }
}

