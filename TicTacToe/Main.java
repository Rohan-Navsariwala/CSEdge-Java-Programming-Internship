
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import java.util.EventObject;

class TicTacToe extends JFrame implements ActionListener {
    JPanel stats_area = new JPanel();
    JPanel play_area = new JPanel();
    JPanel matrix = new JPanel();

    JButton aa = new JButton("");
    JButton ab = new JButton("");
    JButton ac = new JButton("");
    JButton ba = new JButton("");
    JButton bb = new JButton("");
    JButton bc = new JButton("");
    JButton ca = new JButton("");
    JButton cb = new JButton("");
    JButton cc = new JButton("");
    JButton new_game = new JButton("C");

    static String turn = "X";
    static boolean flag = false;
    static int playerX = 0;
    static int playerY = 0;
    static int box_filled = 0;

    JLabel title = new JLabel("TicTacToe", SwingConstants.CENTER);
    JLabel turn_l = new JLabel("Turn - " + turn, SwingConstants.CENTER);
    JLabel score = new JLabel("Score", SwingConstants.CENTER);
    JLabel plX = new JLabel("Player X : " + playerX, SwingConstants.CENTER);
    JLabel plY = new JLabel("Player Y : " + playerY, SwingConstants.CENTER);
    JLabel container = new JLabel("", SwingConstants.CENTER);

    Font b_font = new Font("arial", Font.BOLD, 18);
    Font h_font = new Font("arial", Font.BOLD, 26);
    Font g_font = new Font("arial", Font.BOLD, 34);

    TicTacToe() {
        setLayout(null);
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setTitle("Tic-Tac-Toe");

        stats_area.setBackground(new Color(0x2C3E50));
        stats_area.setLayout(null);
        stats_area.setBounds(0, 0, 250, 400);
        stats_area.setVisible(true);

        title.setForeground(new Color(0xffffff));
        title.setBounds(0, 50, 250, 30);
        title.setFont(h_font);
        turn_l.setForeground(new Color(0xffffff));
        turn_l.setBounds(0, 150, 250, 30);
        turn_l.setFont(b_font);
        score.setForeground(new Color(0xffffff));
        score.setBounds(0, 200, 250, 30);
        score.setFont(b_font);
        plX.setForeground(new Color(0xffffff));
        plX.setBounds(0, 250, 250, 30);
        plX.setFont(b_font);
        plY.setForeground(new Color(0xffffff));
        plY.setBounds(0, 300, 250, 30);
        plY.setFont(b_font);

        stats_area.add(title);
        stats_area.add(turn_l);
        stats_area.add(score);
        stats_area.add(plX);
        stats_area.add(plY);

        play_area.setBackground(new Color(0xffffff));
        play_area.setLayout(null);
        play_area.setBounds(250, 0, 400, 400);
        play_area.setVisible(true);

        container.setForeground(new Color(0xC0392B));
        container.setBounds(0, 10, 400, 30);
        container.setFont(b_font);

        matrix.setBackground(new Color(0x2C3E50));
        matrix.setLayout(null);
        matrix.setBounds(50, 50, 280, 280);
        matrix.setVisible(true);

        configureButton(aa, 10, 10);
        configureButton(ab, 100, 10);
        configureButton(ac, 190, 10);
        configureButton(ba, 10, 100);
        configureButton(bb, 100, 100);
        configureButton(bc, 190, 100);
        configureButton(ca, 10, 190);
        configureButton(cb, 100, 190);
        configureButton(cc, 190, 190);

        new_game.setBackground(new Color(0xC0392B)); new_game.setForeground(new Color(0xffffff));
        new_game.setFont(new Font("Arial", Font.PLAIN, 12)); new_game.setBounds(330,50,35,35);
        new_game.setEnabled(false); new_game.addActionListener(this);

        play_area.add(container);
        play_area.add(matrix);
        play_area.add(new_game);

        add(stats_area);
        add(play_area);

    }

    private void configureButton(JButton button, int x, int y) {
        button.setBackground(new Color(0xffffff));
        button.setForeground(new Color(0x2C3E50));
        button.setBounds(x, y, 80, 80);
        button.setFont(g_font);
        button.addActionListener(this);
        matrix.add(button);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if(clicked == new_game){
            reset();
        }

        if (box_filled != 9 && flag == false) {

            container.setText("");

            if (clicked.getText() == "X" || clicked.getText() == "O") {
                container.setText("Bro Invalid Box");
            } else {
                clicked.setText(turn);
                box_filled++;

                if (clicked == aa) {

                    if ((ba.getText() == turn && ca.getText() == turn) || (ab.getText() == turn && ac.getText() == turn)
                            || (bb.getText() == turn && cc.getText() == turn)) {
                        container.setText("Player " + turn + " won");
                        flag = true;
                        
                        new_game.setEnabled(true);
                    } else {
                        turn = (turn == "X") ? "O" : "X";
                    }

                } else if (clicked == ab) {

                    if ((aa.getText() == turn && ac.getText() == turn)
                            || (bb.getText() == turn && cb.getText() == turn)) {
                        container.setText("Player " + turn + " won");
                        flag = true;
                        
                        new_game.setEnabled(true);
                    } else {
                        turn = (turn == "X") ? "O" : "X";
                    }

                } else if (clicked == ac) {

                    if ((aa.getText() == turn && ab.getText() == turn) || (bc.getText() == turn && cc.getText() == turn)
                            || (bb.getText() == turn && ca.getText() == turn)) {
                        container.setText("Player " + turn + " won");
                        flag = true;
                        
                        new_game.setEnabled(true);
                    } else {
                        turn = (turn == "X") ? "O" : "X";
                    }

                } else if (clicked == ba) {

                    if ((aa.getText() == turn && ca.getText() == turn)
                            || (bb.getText() == turn && bc.getText() == turn)) {
                        container.setText("Player " + turn + " won");
                        flag = true;
                        
                        new_game.setEnabled(true);
                    } else {
                        turn = (turn == "X") ? "O" : "X";
                    }

                } else if (clicked == bb) {

                    if ((aa.getText() == turn && cc.getText() == turn) || (ac.getText() == turn && ca.getText() == turn)
                            || (ab.getText() == turn && cb.getText() == turn)
                            || (ba.getText() == turn && bc.getText() == turn)) {
                        container.setText("Player " + turn + " won");
                        flag = true;
                        
                        new_game.setEnabled(true);
                    } else {
                        turn = (turn == "X") ? "O" : "X";
                    }

                } else if (clicked == bc) {

                    if ((ac.getText() == turn && cc.getText() == turn)
                            || (bb.getText() == turn && ba.getText() == turn)) {
                        container.setText("Player " + turn + " won");
                        flag = true;
                        
                        new_game.setEnabled(true);
                    } else {
                        turn = (turn == "X") ? "O" : "X";
                    }

                } else if (clicked == ca) {

                    if ((aa.getText() == turn && ba.getText() == turn) || (cb.getText() == turn && cc.getText() == turn)
                            || (bb.getText() == turn && ac.getText() == turn)) {
                        container.setText("Player " + turn + " won");
                        flag = true;
                        
                        new_game.setEnabled(true);
                    } else {
                        turn = (turn == "X") ? "O" : "X";
                    }

                } else if (clicked == cb) {

                    if ((ca.getText() == turn && cc.getText() == turn)
                            || (bb.getText() == turn && ab.getText() == turn)) {
                        container.setText("Player " + turn + " won");
                        flag = true;
                        
                        new_game.setEnabled(true);
                    } else {
                        turn = (turn == "X") ? "O" : "X";
                    }

                } else if (clicked == cc) {

                    if ((ac.getText() == turn && bc.getText() == turn) || (ca.getText() == turn && cb.getText() == turn)
                            || (bb.getText() == turn && aa.getText() == turn)) {
                        container.setText("Player " + turn + " won");
                        flag = true;
                        
                        new_game.setEnabled(true);
                    } else {
                        turn = (turn == "X") ? "O" : "X";
                    }
                }
            }
        } else {
            new_game.setEnabled(true);
        }
    }

    void reset() {
        if (flag == true) {
            if (turn == "X") {
                ++playerX;
                plX.setText("Player X : " + playerX);
            } else {
                ++playerY;
                plY.setText("Player Y : " + playerY);
            }
        }
        container.setText("");
        turn = "X";
        box_filled = 0;
        flag = false;

        aa.setText("");
        ab.setText("");
        ac.setText("");
        ba.setText("");
        bb.setText("");
        bc.setText("");
        ca.setText("");
        cb.setText("");
        cc.setText("");

        stats_area.repaint();

    }

}

public class Main {
    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe();
    }
}
