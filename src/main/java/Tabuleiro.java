public class Tabuleiro {
    private char[][] matriz;

    public Tabuleiro() {
        matriz = new char[3][3];
        reiniciar();
    }

    public void reiniciar() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[i][j] = ' ';
            }
        }
    }

    public boolean fazerJogada(int linha, int coluna, char jogador) {
        if (matriz[linha][coluna] == ' ') {
            matriz[linha][coluna] = jogador;
            return true;
        }
        return false;
    }

    public char getPosicao(int linha, int coluna) {
        return matriz[linha][coluna];
    }

    public boolean verificarVitoria(char jogador) {
        // Linhas e colunas
        for (int i = 0; i < 3; i++) {
            if ((matriz[i][0] == jogador && matriz[i][1] == jogador && matriz[i][2] == jogador) ||
                    (matriz[0][i] == jogador && matriz[1][i] == jogador && matriz[2][i] == jogador)) {
                return true;
            }
        }

        // Diagonais
        if ((matriz[0][0] == jogador && matriz[1][1] == jogador && matriz[2][2] == jogador) ||
                (matriz[0][2] == jogador && matriz[1][1] == jogador && matriz[2][0] == jogador)) {
            return true;
        }

        return false;
    }

    public boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}