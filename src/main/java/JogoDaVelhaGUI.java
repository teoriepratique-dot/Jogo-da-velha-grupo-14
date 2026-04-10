import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JogoDaVelhaGUI extends JFrame {

    private JButton[][] botoes = new JButton[3][3];
    private Tabuleiro tabuleiro = new Tabuleiro();
    private char jogadorAtual = 'X';
    private JLabel status;

    public JogoDaVelhaGUI() {
        setTitle("Jogo da Velha");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelTabuleiro = new JPanel();
        painelTabuleiro.setLayout(new GridLayout(3, 3));

        ActionListener clique = e -> {
            JButton btn = (JButton) e.getSource();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (btn == botoes[i][j]) {
                        if (tabuleiro.fazerJogada(i, j, jogadorAtual)) {
                            btn.setText(String.valueOf(jogadorAtual));
                            verificarEstado();
                            alternarJogador();
                        }
                    }
                }
            }
        };

        // Criar botões
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton("");
                botoes[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                botoes[i][j].addActionListener(clique);
                painelTabuleiro.add(botoes[i][j]);
            }
        }

        status = new JLabel("Jogador: X", JLabel.CENTER);

        JButton reset = new JButton("Reiniciar");
        reset.addActionListener(e -> reiniciarJogo());

        add(status, BorderLayout.NORTH);
        add(painelTabuleiro, BorderLayout.CENTER);
        add(reset, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void alternarJogador() {
        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        status.setText("Jogador: " + jogadorAtual);
    }

    private void verificarEstado() {
        if (tabuleiro.verificarVitoria(jogadorAtual)) {
            JOptionPane.showMessageDialog(this, "Jogador " + jogadorAtual + " venceu!");
            reiniciarJogo();
        } else if (tabuleiro.verificarEmpate()) {
            JOptionPane.showMessageDialog(this, "Empate!");
            reiniciarJogo();
        }
    }

    private void reiniciarJogo() {
        tabuleiro.reiniciar();
        jogadorAtual = 'X';
        status.setText("Jogador: X");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
            }
        }
    }

    public static void main(String[] args) {
        new JogoDaVelhaGUI();
    }
}