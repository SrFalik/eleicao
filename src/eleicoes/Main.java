package eleicoes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Presidente> list = new ArrayList<>();
        System.out.println("Bem vindo ao sistema eleitoral brasileiro.");
        System.out.println();
        System.out.println("Digite a quantidade de candidatos a presidência que deseja inserir.");
        String nome;
        String partido;
        int numero;
        int idade;
        String vicePresidente;
        String ladrao;
        Scanner sc = new Scanner(System.in);
            int qtd = sc.nextInt();
            for (int i = 0; i < qtd; i++) {
                System.out.print("Insira o nome do candidato: ");
                nome = sc.next();
                System.out.print("Insira a sigla do partido: ");
                partido = sc.next();
                System.out.print("Insira o número do candidato: ");
                numero = sc.nextInt();
                    System.out.print("Insira a idade do candidato: ");
                    idade = sc.nextInt();
                System.out.print("Insira o nome do vice presidente: ");
                vicePresidente = sc.next();
                while (true) {
                    System.out.println("Candidato é ladrão? (S/N)");
                    ladrao = String.valueOf(sc.next().charAt(0));
                    if (ladrao.equalsIgnoreCase("s")) {
                        System.out.println("Imaginei...");
                        System.out.println();
                        break;
                    } else if (ladrao.equalsIgnoreCase("n")) {
                        System.out.println("Tem certeza?");
                        System.out.println("Vou dar outra chance...");
                    } else {
                        System.out.println("S ou N, não sabe ler mano?");
                    }
                }
                Presidente candidato = new Presidente(nome, partido, numero, idade, vicePresidente);
                if (candidato.eleg() == true) {
                    list.add(candidato);
                } else {
                    System.out.println("Candidato novo demais para eleição presidencial, tente novamente em " + (35 - candidato.getIdade()));
                }
            }
        System.out.println("Candidatos a presidência elegíveis cadastrados:");
        for (Presidente p : list) {
            System.out.println(p);
        }

        System.out.println("Iniciando votação!");
        System.out.println("Quantos votantes teremos?");
        int votantes = sc.nextInt();
        int voto;
        for (int i = 0; i < votantes; i++) {
            System.out.print("Votante número" + i + ", digite o número do seu candidato: ");
            voto = sc.nextInt();
            for (Presidente p : list) {
                if (p.getNumero() == voto) {
                    p.addVoto();
                }
            }
        }
        int ganhador = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getVotos() > list.get(ganhador).getVotos()) {
                ganhador = i;
            }
        }
        System.out.print("VENCEDOR: " + list.get(ganhador) + "COM " + list.get(ganhador).getVotos() + " VOTOS!");
    }
}
