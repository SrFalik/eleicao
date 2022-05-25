package eleicoes;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        List<Presidente> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int contador = 1;
        System.out.println("Bem vindo ao sistema eleitoral brasileiro.");
        System.out.println();
        System.out.println("Você deseja inserir os candidatos por um arquivo de texto? (S/N)");
        if (String.valueOf(sc.next().charAt(0)).equalsIgnoreCase("s")) {
            System.out.println("Digite o caminho do arquivo de texto:");
            String path = sc.next();
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                br.lines().forEach(linha -> {
                    String[] info = linha.split(",");
                    Presidente p = new Presidente(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), info[4]);
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
        contador = 1;
        for (Presidente p : list) {
            System.out.println("Candidato #" + contador);
            System.out.println(p);
            System.out.println();
            contador++;
        }

        System.out.println("Iniciando votação!");
        System.out.println("Quantos votantes teremos?");
        int votantes = sc.nextInt();
        int voto;
        for (int i = 0; i < votantes; i++) {
            System.out.print("Votante número #" + (i + 1) + ", digite o número do seu candidato: ");
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
            if (i != ganhador && list.get(i).getVotos() == list.get(ganhador).getVotos()) {
                segundoTurno = true;
                aux = i;
                break;
            }
        }
        List<Presidente> list2 = new ArrayList<>();
        if (segundoTurno) {
            System.out.println();
            System.out.println("SEGUNDO TURNO!!!");
            list2.add(list.get(ganhador));
            list2.add(list.get(aux));
            contador = 1;
            for (Presidente p : list2) {
                System.out.println("Candidato #" + contador);
                System.out.println(p);
                System.out.println();
                contador++;
            }
            for (int i = 0; i < votantes; i++) {
                System.out.print("Votante #" + (i + 1) + ", digite o número do seu candidato para o segundo turno: ");
                voto = sc.nextInt();
                for (Presidente p : list2) {
                    if (p.getNumero() == voto) {
                        p.addVoto();
                    }
                }
            }
            ganhador = 0;
            for (int i = 0; i < list2.size(); i++) {
                if (list2.get(i).getVotos() > list2.get(ganhador).getVotos()) {
                    ganhador = i;
                }
            }
            System.out.println("VENCEDOR - " + list2.get(ganhador).getNome());
            System.out.println("\nEscrevendo relatório da votação em " + System.getProperty("user.home") + "\\Desktop\\relatorio.txt");
        } else {
            System.out.println("VENCEDOR - " + list.get(ganhador).getNome());
            System.out.println("\nEscrevendo relatório da votação em " + System.getProperty("user.home") + "\\Desktop\\relatorio.txt");
        }
        String pathOut = System.getProperty("user.home") + "\\Desktop\\relatorio.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathOut))) {

            bw.write("Relatorio da eleição:");
            bw.newLine();
            bw.write("Candidatos primeiro turno:");
            contador = 1;
            for (Presidente p : list) {
                bw.newLine();
                bw.write("Candidato #" + contador);
                bw.newLine();
                bw.write(p.toString());
                bw.newLine();
                contador++;
            }
            if (segundoTurno) {
                bw.newLine();
                bw.write("Candidatos segundo turno:");
                contador = 1;
                for (Presidente p : list2) {
                    bw.newLine();
                    bw.write("Candidato #" + contador);
                    bw.newLine();
                    bw.write(p.toString());
                    bw.newLine();
                    contador++;
                }
                bw.newLine();
                bw.write("Vencedor: " + list2.get(ganhador).getNome());
            } else {
                bw.newLine();
                bw.write("Vencedor: " + list.get(ganhador).getNome());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
