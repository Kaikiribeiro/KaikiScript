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

    private void erro(String esperado){
        Token token = peek();

        System.out.printf("Erro na linha %d, coluna %d: esperado '%s', mas encontrado '%s'%n",
                token.getLinha(), token.getColuna(), esperado, token.getTexto());
        System.exit(1);
    }

    public void consumir(TokenType tipo, String esperado){
        if (verificar(tipo)){
            avancar();
        } else{
            erro(esperado);
        }
    }

    public void declaracao() {
        if (verificar(TokenType.INTEIRO) || verificar(TokenType.REAL)) {
            avancar();
        } else {
            erro("int ou double");
        }

        consumir(TokenType.IDENTIFICADOR, "nome da variavel");

        if (verificar(TokenType.ATRIBUICAO)) {
            consumir(TokenType.ATRIBUICAO, "=");
            consumir(TokenType.NUMERO, "numero");
        }

        consumir(TokenType.PONTO_VIRGULA, ";");
    }

    public void declaracoes(){
        while (verificar(TokenType.INTEIRO) || verificar(TokenType.REAL)){
            declaracao();
        }
    }

}
