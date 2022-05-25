package eleicoes;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        List<Presidente> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem vindo ao sistema eleitoral brasileiro.");
        System.out.println();
        System.out.println("Você deseja inserir os candidatos por um arquivo de texto? (S/N)");
        if (String.valueOf(sc.next().charAt(0)).equalsIgnoreCase("s")) {
            System.out.println("Digite o caminho do arquivo de texto:");
            String path = sc.next();
                try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                    br.lines().forEach(linha -> {
                        String[] info = linha.split(",");
                        Presidente p = new Presidente(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), info[4] );
                        if (p.eleg()) {
                            list.add(p);
                        }
                    });
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
        } else {
            System.out.println("Digite a quantidade de candidatos a presidência que deseja inserir.");
            String nome;
            String partido;
            int numero;
            int idade;
            String vicePresidente;
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
                    if (String.valueOf(sc.next().charAt(0)).equalsIgnoreCase("s")) {
                        System.out.println("Imaginei...");
                        System.out.println();
                        break;
                    } else if (String.valueOf(sc.next().charAt(0)).equalsIgnoreCase("n")) {
                        System.out.println("Tem certeza?");
                        System.out.println("Vou dar outra chance...");
                    } else {
                        System.out.println("S ou N, não sabe ler mano?");
                    }
                }
                Presidente candidato = new Presidente(nome, partido, numero, idade, vicePresidente);
                if (candidato.eleg()) {
                    list.add(candidato);
                } else {
                    System.out.println("Candidato novo demais para eleição presidencial, tente novamente em " + (35 - candidato.getIdade()));
                }
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
        boolean segundoTurno = false;
        int aux = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i != ganhador && list.get(i).getVotos().equals(list.get(ganhador).getVotos())) {
                segundoTurno = true;
                aux = i;
                break;
            }
        }
        List<Presidente> list2 = new ArrayList<>();
        if (segundoTurno) {
            System.out.println("SEGUNDO TURNO!!!");
            System.out.println(list.get(ganhador));
            System.out.println(list.get(aux));
            list2.add(list.get(ganhador));
            list2.add(list.get(aux));
            for (int i = 0; i < votantes; i++) {
                System.out.print("Votante número" + i + ", digite o número do seu candidato para o segundo turno: ");
                voto = sc.nextInt();
                for (Presidente p : list2) {
                    if (p.getNumero() == voto) {
                        p.addVoto();
                    }
                }
            }
            ganhador = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getVotos() > list.get(ganhador).getVotos()) {
                    ganhador = i;
                }
            }
        }
        System.out.print("VENCEDOR: " + list.get(ganhador));
    }
}
