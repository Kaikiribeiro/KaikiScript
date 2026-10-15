import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Token> tokens = new ArrayList<>();

//        tokens.add(new Token(TokenType.REAL, "double", 1, 1));
//        tokens.add(new Token(TokenType.IDENTIFICADOR, "y", 1, 8));
//        tokens.add(new Token(TokenType.ATRIBUICAO, "=", 1, 10));
//        tokens.add(new Token(TokenType.NUMERO, "2.5", 1, 12));
//        tokens.add(new Token(TokenType.PONTO_VIRGULA, ";", 1, 15));
//
//        tokens.add(new Token(TokenType.INTEIRO, "int", 2, 1));
//        tokens.add(new Token(TokenType.IDENTIFICADOR, "a", 2, 5));
//        tokens.add(new Token(TokenType.PONTO_VIRGULA, ";", 2, 6));
//
        tokens.add(new Token(TokenType.EOF, "", 1, 1));

        Parser parser = new Parser(tokens);
        parser.declaracoes();
        System.out.println(parser.peek());
    }
}