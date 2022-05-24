package eleicoes;

public class Presidente extends Candidato{

    private String vicePresidente;

    public Presidente(String nome, String partido, Integer numero, Integer idade, String vicePresidente) {
        super(nome, partido, numero, idade);
        this.vicePresidente = vicePresidente;
    }

    public String getVicePresidente() {
        return vicePresidente;
    }

    public void setVicePresidente(String vicePresidente) {
        this.vicePresidente = vicePresidente;
    }


    public Boolean eleg() {
        if (getIdade() >= 35) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Presidente{" +
                "nome='" + getNome() + '\'' +
                ", vicePresidente='" + vicePresidente + '\'' +
                ", partido='" + getPartido() + '\'' +
                ", numero=" + getNumero() +
                ", idade=" + getIdade() +
                "} ";
    }
}
