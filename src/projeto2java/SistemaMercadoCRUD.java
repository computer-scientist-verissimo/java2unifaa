package projeto2java;

import java.util.ArrayList;
import java.util.Scanner;


public class SistemaMercadoCRUD {

    private static ArrayList<Produto> produtos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int proximoId = 1;

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    editarProduto();
                    break;
                case 3:
                    deletarProduto();
                    break;
                case 4:
                    visualizarProdutos();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("0 - Sair do sistema");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Editar produto");
        System.out.println("3 - Deletar produto");
        System.out.println("4 - Visualizar lista de produtos");
    }

    private static void cadastrarProduto() {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();

        System.out.print("Digite o valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer do scanner

        String dataCadastro = java.time.LocalDate.now().toString();

        Produto produto = new Produto(proximoId, nome, quantidade, valor, dataCadastro);
        produtos.add(produto);

        System.out.println("Produto cadastrado com sucesso! ID do produto: " + proximoId);
        proximoId++;
    }

    private static void editarProduto() {
        System.out.print("Digite o ID do produto que deseja editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        boolean encontrado = false;
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                System.out.println("Digite o novo nome do produto: ");
                String novoNome = scanner.nextLine();
                produto.setNome(novoNome);

                System.out.println("Digite a nova quantidade: ");
                int novaQuantidade = scanner.nextInt();
                produto.setQuantidade(novaQuantidade);

                System.out.println("Digite o novo valor: ");
                double novoValor = scanner.nextDouble();
                produto.setValor(novoValor);
                scanner.nextLine(); // Limpar o buffer do scanner

                System.out.println("Produto editado com sucesso!");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Produto não encontrado com o ID informado.");
        }
    }

    private static void deletarProduto() {
        System.out.print("Digite o ID do produto que deseja deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        boolean removido = produtos.removeIf(produto -> produto.getId() == id);

        if (removido) {
            System.out.println("Produto deletado com sucesso!");
        } else {
            System.out.println("Produto não encontrado com o ID informado.");
        }
    }

    private static void visualizarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Não há produtos cadastrados.");
        } else {
            System.out.println("\nLista de Produtos:");
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }
}
