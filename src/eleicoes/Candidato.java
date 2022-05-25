package eleicoes;

public abstract class Candidato {
    private String nome;
    private String partido;
    private Integer numero;
    private Integer idade;

    private Integer votos = 0;

    public Candidato(String nome, String partido, Integer numero, Integer idade) {
        this.nome = nome;
        this.partido = partido;
        this.numero = numero;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    public void addVoto() {
        votos++;
    }

}
