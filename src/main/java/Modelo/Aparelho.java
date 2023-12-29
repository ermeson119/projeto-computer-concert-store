package Modelo;


import java.util.Objects;

public class Aparelho {
    private int id;
    private String nome;
    private String modelo;
    private String marca;
    private String numeroDeSerie;

    public Aparelho() {

    }

    public Aparelho(int id, String nome, String modelo, String marca, String numeroDeSerie) {
        this.id = id;
        this.nome = nome;
        this.modelo = modelo;
        this.marca = marca;
        this.numeroDeSerie = numeroDeSerie;
    }

    public Aparelho(String nome, String modelo, String marca, String numeroDeSerie) {
        this.nome = nome;
        this.modelo = modelo;
        this.marca = marca;
        this.numeroDeSerie = numeroDeSerie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aparelho aparelho = (Aparelho) o;

        return Objects.equals(modelo, aparelho.modelo) &&
            Objects.equals(numeroDeSerie, aparelho.numeroDeSerie);
    }

    @Override
    public int hashCode() {
        int result = modelo != null ? modelo.hashCode() : 0;
        result = 31 * result + (numeroDeSerie != null ? numeroDeSerie.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}