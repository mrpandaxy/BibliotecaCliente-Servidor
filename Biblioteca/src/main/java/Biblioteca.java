import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Biblioteca {
    private List<Livros> livros;
    private static final String json = "livros.json";
    private ObjectMapper objectMapper;

    public Biblioteca() throws IOException {
        objectMapper = new ObjectMapper();

    }
}
