import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 50000);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
        String opcao = null;

        do {
            try {
                System.out.println("Menu de opções:");
                System.out.println("1. Cadastrar Livro");
                System.out.println("2. Alugar Livro");
                System.out.println("3. Devolver Livro");
                System.out.println("4. Listar Livros");
                System.out.println("5. Sair");

                opcao = entrada.next();
                System.out.println("opcao enviada: " + opcao);
                out.println(opcao);

                if (opcao.equals("1")) {
                    System.out.println("Cadastro");
                    objectOutput.writeObject(cadastroLivro());
                } else if (opcao.equals("2") || opcao.equals("3")) {
                    objectOutput.writeObject(alugarOuDevolverLivro());
                }

                String linha;
                System.out.println("Resposta do servidor: ");
                while (!(linha = in.readLine()).equals("END_OF_RESPONSE")) {
                    System.out.println(linha);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } while (!opcao.equals("5"));

        in.close();
        out.close();
        objectOutput.close();
        socket.close();
    }

    private static Livros cadastroLivro() {
        System.out.println("Digite o titulo do livro");
        String titulo = entrada.next();
        System.out.println("Digite o autor do livro");
        String autor = entrada.next();
        System.out.println("Digite o genero do livro");
        String genero = entrada.next();
        System.out.println("Digite o numero de exemplares do livro");
        int numExemplares = entrada.nextInt();

        return new Livros(titulo, autor, genero, numExemplares);
    }

    private static Livros alugarOuDevolverLivro() {
        System.out.println("Digite o titulo do livro");
        String titulo = entrada.next();

        return new Livros(titulo, null, null, 0);
    }
}
