import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Livro> livros = new ArrayList<>();
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println();
            System.out.println("--------Sistema-de-Livros--------");
            System.out.println("1- Cadastrar novo livro");
            System.out.println("2- Ver todos os livros");
            System.out.println("3- Ver todos os livros de um gênero");
            System.out.println("4- Buscar um livro por título ou autor");
            System.out.println("0- Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    verTodosLivros();
                    break;
                case 3:
                    verLivrosPorGenero();
                    break;
                case 4:
                    buscarLivro();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarLivro() {
        System.out.println();
        System.out.print("Título: ");
        String titulo = scan.nextLine();
        System.out.print("Nome do Autor: ");
        String nome = scan.nextLine();
        System.out.print("Sobrenome do Autor: ");
        String sobrenome = scan.nextLine();
        Pessoa autor = new Pessoa(nome, sobrenome);

        System.out.println();
        System.out.println("Gêneros:");
        for (Genero genero : Genero.values()) {
            System.out.println(genero.getCodigo() + " - " + genero.getNome());
        }
        System.out.print("Código do Gênero: ");
        int codigoGenero = scan.nextInt();
        scan.nextLine();
        Genero genero = Genero.fromCodigo(codigoGenero);

        Livro livro = new Livro(titulo, autor, genero);

        livros.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void verTodosLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            System.out.println();
            for (Livro livro : livros) {
                System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor().getNomeCompleto() + ", Gênero: " + livro.getGenero().getNome());
            }
        }
    }

    private static void verLivrosPorGenero() {
        System.out.println("Gêneros:");
        for (Genero genero : Genero.values()) {
            System.out.println(genero.getCodigo() + " - " + genero.getNome());
        }
        System.out.print("Código do Gênero: ");
        int codigoGenero = scan.nextInt();
        scan.nextLine();
        System.out.println();
        Genero genero = Genero.fromCodigo(codigoGenero);

        boolean encontrado = false;
        for (Livro livro : livros) {
            if (livro.getGenero() == genero) {
                System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor().getNomeCompleto());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum livro encontrado para o gênero selecionado.");
        }
    }

    private static void buscarLivro() {
        System.out.print("Digite o título ou nome do autor: ");
        String busca = scan.nextLine().toLowerCase();
        System.out.println();


        boolean encontrado = false;
        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(busca) || livro.getAutor().getNomeCompleto().toLowerCase().contains(busca)) {
                System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor().getNomeCompleto() + ", Gênero: " + livro.getGenero().getNome());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum livro encontrado.");
        }
    }
}
