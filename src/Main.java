import java.util.ArrayList;
import java.util.List;

public class Main {

    // Cria um token na linha 1. A "coluna" é só a posição do token na lista,
    // o que já basta para conferir onde o erro aparece.
    static void add(List<Token> lista, TokenType tipo, String texto) {
        lista.add(new Token(tipo, texto, 1, lista.size() + 1));
    }

    public static void main(String[] args) {

        int caso = 10;   // <-- troque este número para escolher o teste

        List<Token> tokens = new ArrayList<>();
        String regra = "expressao";   // qual método do Parser será testado

        switch (caso) {

            // ---------- testes da expressao ----------
            case 1: // 5  -> sem erro
                add(tokens, TokenType.NUMERO, "5");
                break;

            case 2: // 2 + 3  -> sem erro
                add(tokens, TokenType.NUMERO, "2");
                add(tokens, TokenType.MAIS, "+");
                add(tokens, TokenType.NUMERO, "3");
                break;

            case 3: // 2 + 3 * 4 - x  -> sem erro
                add(tokens, TokenType.NUMERO, "2");
                add(tokens, TokenType.MAIS, "+");
                add(tokens, TokenType.NUMERO, "3");
                add(tokens, TokenType.VEZES, "*");
                add(tokens, TokenType.NUMERO, "4");
                add(tokens, TokenType.MENOS, "-");
                add(tokens, TokenType.IDENTIFICADOR, "x");
                break;

            case 4: // 2 + ;  -> erro: esperado numero ou variavel, encontrado ;
                add(tokens, TokenType.NUMERO, "2");
                add(tokens, TokenType.MAIS, "+");
                add(tokens, TokenType.PONTO_VIRGULA, ";");
                break;

            case 5: // 2 + * 4  -> erro: esperado numero ou variavel, encontrado *
                add(tokens, TokenType.NUMERO, "2");
                add(tokens, TokenType.MAIS, "+");
                add(tokens, TokenType.VEZES, "*");
                add(tokens, TokenType.NUMERO, "4");
                break;

            // ---------- testes das declaracoes ----------
            case 6: // double y = 2.5; int a;  -> sem erro
                regra = "declaracoes";
                add(tokens, TokenType.REAL, "double");
                add(tokens, TokenType.IDENTIFICADOR, "y");
                add(tokens, TokenType.ATRIBUICAO, "=");
                add(tokens, TokenType.NUMERO, "2.5");
                add(tokens, TokenType.PONTO_VIRGULA, ";");
                add(tokens, TokenType.INTEIRO, "int");
                add(tokens, TokenType.IDENTIFICADOR, "a");
                add(tokens, TokenType.PONTO_VIRGULA, ";");
                break;

            case 7: // int x = 2 + 3 * y;  -> só passa depois de ligar a expressao na declaracao
                regra = "declaracoes";
                add(tokens, TokenType.INTEIRO, "int");
                add(tokens, TokenType.IDENTIFICADOR, "x");
                add(tokens, TokenType.ATRIBUICAO, "=");
                add(tokens, TokenType.NUMERO, "2");
                add(tokens, TokenType.MAIS, "+");
                add(tokens, TokenType.NUMERO, "3");
                add(tokens, TokenType.VEZES, "*");
                add(tokens, TokenType.IDENTIFICADOR, "y");
                add(tokens, TokenType.PONTO_VIRGULA, ";");
                break;

            case 8: // nenhuma declaração  -> sem erro
                regra = "declaracoes";
                break;

            case 9: // int x = ;  -> erro: esperado numero ou variavel, encontrado ;
                regra = "declaracoes";
                add(tokens, TokenType.INTEIRO, "int");
                add(tokens, TokenType.IDENTIFICADOR, "x");
                add(tokens, TokenType.ATRIBUICAO, "=");
                add(tokens, TokenType.PONTO_VIRGULA, ";");
                break;

            case 10: // x < 10  -> sem erro
                regra = "condicao";
                add(tokens, TokenType.IDENTIFICADOR, "x");
                add(tokens, TokenType.MENOR_QUE, "<");
                add(tokens, TokenType.NUMERO, "10");
                break;

            case 11: // x + 1 >= y * 2  -> sem erro
                regra = "condicao";
                add(tokens, TokenType.IDENTIFICADOR, "x");
                add(tokens, TokenType.MAIS, "+");
                add(tokens, TokenType.NUMERO, "1");
                add(tokens, TokenType.MAIOR_OU_IGUAL_QUE, ">=");
                add(tokens, TokenType.IDENTIFICADOR, "y");
                add(tokens, TokenType.VEZES, "*");
                add(tokens, TokenType.NUMERO, "2");
                break;

            case 12: // x == 5  -> sem erro
                regra = "condicao";
                add(tokens, TokenType.IDENTIFICADOR, "x");
                add(tokens, TokenType.IGUAL, "==");
                add(tokens, TokenType.NUMERO, "5");
                break;

            case 13: // x 10  -> erro: esperado operador relacional, encontrado 10
                regra = "condicao";
                add(tokens, TokenType.IDENTIFICADOR, "x");
                add(tokens, TokenType.NUMERO, "10");
                break;

            case 14: // x < ;  -> erro: esperado numero ou variavel, encontrado ;
                regra = "condicao";
                add(tokens, TokenType.IDENTIFICADOR, "x");
                add(tokens, TokenType.MENOR_QUE, "<");
                add(tokens, TokenType.PONTO_VIRGULA, ";");
                break;

            default:
                System.out.println("Caso inexistente: " + caso);
                return;
        }

        add(tokens, TokenType.EOF, "");

        Parser parser = new Parser(tokens);

        if (regra.equals("declaracoes")) {
            parser.declaracoes();
        } else {
            parser.expressao();
        }

        System.out.println("Sem erro. O parser parou em: " + parser.peek());
    }
}