import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static Biblioteca biblioteca;

    public Servidor() throws IOException {
        this.biblioteca = new Biblioteca();
    }

    private String processarOpcao(String opcao, Livros livro) {
        switch (opcao) {
            case "1":
                try {
                    biblioteca.cadastrarLivro(livro);
                } catch (IOException e) {
                    System.out.println("Erro ao cadastrar livro");
                    throw new RuntimeException(e);
                }
                return "Livro " + livro + " cadastrado com sucesso!";
            case "2":
                try {
                    biblioteca.alugarLivro(livro.getTitulo());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return "Livro alugado com sucesso!";
            case "3":
                try {
                    biblioteca.devolverLivro(livro.getTitulo());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return "Livro devolvido com sucesso!";
            case "4":
                return "Lista de Livros \n" + biblioteca.listarLivros();
            case "5":
                return "Saindo...";
            default:
                return "Opção invalida";
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Servidor server = new Servidor();
        ServerSocket servidor = new ServerSocket(50000);
        System.out.println("Servidor conectado na porta 50000");
        System.out.println("Aguardando clientes de servidor...");

        while (true) {
            try (Socket cliente = servidor.accept();
                 PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                 ObjectInputStream objectInput = new ObjectInputStream(cliente.getInputStream())) {

                String option;
                while ((option = in.readLine()) != null) {
                    System.out.println("Opção recebida: " + option);

                    Livros livro = null;
                    if (!option.equals("4") && !option.equals("5")) {
                        livro = (Livros) objectInput.readObject();
                        System.out.println("Livro recebido: " + livro);
                    } else if (option.equals("5")) {
                        out.println("Saindo...");
                        break;
                    }

                    String resposta = server.processarOpcao(option, livro);
                    out.println(resposta);
                    out.println("END_OF_RESPONSE");
                }

            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
