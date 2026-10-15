import java.util.ArrayList;
import java.util.List;

public class Parser {
        private List<Token> listaToken = new ArrayList<>();
        private int pos = 0;

    public Parser(List<Token> listaToken) {
        this.listaToken = listaToken;
    }

    public int getPos() {
        return pos;
    }

    public Token peek(){
    return listaToken.get(pos);
    }

    public Token avancar(){
        Token atual = peek();
        if (atual.getTipo() != TokenType.EOF) {
            pos++;
        }
        return atual;
    }

    public boolean verificar(TokenType tokenType){
        return peek().getTipo() == tokenType;
    }
}
