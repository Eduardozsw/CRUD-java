package lista;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "lista.txt";

    public static void main(String[] args) {
        Scanner myobj = new Scanner(System.in);
        ArrayList<String> lista = carregarLista();

        while (true) {
            System.out.println("O que deseja:");
            System.out.println("1 - Ler algo");
            System.out.println("2 - Anotar algo");
            System.out.println("3 - Apagar algo");
            System.out.println("4 - Editar algo");
            System.out.println("5 - Listar tudo");
            System.out.println("6 - Sair");

            int func = myobj.nextInt();
            myobj.nextLine();

            switch (func) {
                case 1:
                    if (lista.isEmpty()) {
                        System.out.println("A lista tá vazia");
                    } else {
                        System.out.println("Qual item da lista(0 a " + (lista.size() - 1 + ")"));
                        int i = myobj.nextInt();
                        myobj.nextLine();
                        if (i >= 0 && i < lista.size()) {
                            System.out.println(lista.get(i));
                        } else {
                            System.out.println("Índice inválido");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Digite o que deseja adicionar");
                    Scanner add = new Scanner(System.in);
                    String txt = add.nextLine();
                    lista.add(txt);
                    salvarLista(lista);
                    break;
                case 3:
                    if (lista.isEmpty()) {
                        System.out.println("A lista tá vazia");
                    } else {
                        System.out.println("Quer remover qual item da lista(0 a " + (lista.size() - 1 + ")"));
                        int index = myobj.nextInt();
                        if (index >= 0 && index < lista.size()) {
                            lista.remove(index);
                            salvarLista(lista);
                            System.out.println("Item removido");
                        } else {
                            System.out.println("Índicie inválido");
                        }
                    }
                    break;
                case 4:
                    if (lista.isEmpty()) {
                        System.out.println("A lista tá vazia");
                    } else {
                        System.out.println("Qual item deseja editar(0 a " + (lista.size() - 1 + ")"));
                        int index = myobj.nextInt();
                        myobj.nextLine();
                        if (index >= 0 && index < lista.size()) {
                            System.out.println("Novo valor:");
                            String novoValor = myobj.nextLine();
                            lista.set(index, novoValor);
                            salvarLista(lista);
                            System.out.println("Item editado");
                        } else {
                            System.out.println("Índicie inválido");
                        }
                    }
                    break;
                case 5:
                    if (lista.isEmpty()) {
                        System.out.println("A lista tá vazia");
                    } else {
                        int index = lista.size();
                        for (int i = 0; i < index; i++) {
                            System.out.println("Número "+i + ":" + lista.get(i));
                        }
                    }
                    break;
                case 6:
                    System.out.println("Saindo...");
                    myobj.close();
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private static void salvarLista(ArrayList<String> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String item : lista) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar a lista: " + e.getMessage());
        }
    }

    private static ArrayList<String> carregarLista() {
        ArrayList<String> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                lista.add(linha); // Adiciona cada linha do arquivo na lista
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum arquivo encontrado. Criando uma nova lista.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar a lista: " + e.getMessage());
        }
        return lista;
    }
}
