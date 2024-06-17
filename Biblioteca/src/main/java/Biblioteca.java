import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Biblioteca {
    private List<Livros> livros;
    private static final String json = "livros.json";
    private ObjectMapper objectMapper;

    public Biblioteca() throws IOException {
        objectMapper = new ObjectMapper();
        carregarLivros();
    }

    private void carregarLivros() throws IOException {
        File file = new File(json);
        if (file.exists()) {
            System.out.println("Carregando livros...");
            JsonNode rootNode = objectMapper.readTree(file);
            JsonNode livrosNode = rootNode.path("livros");
            livros = objectMapper.convertValue(livrosNode, new TypeReference<List<Livros>>() {});
            System.out.println("Livros carregados com sucesso!");
        } else {
            System.out.println("Não foi possível carregar livros");
            livros = new ArrayList<>();
        }
    }
}
