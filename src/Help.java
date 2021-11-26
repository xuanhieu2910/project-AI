/*
Help class, which tells about rules for playing the TT snake.
 */

import javax.swing.*;
import java.awt.*;

public class Help extends JFrame {
    static final int width = 600;
    static final int height = 600;
    final static Image help = new ImageIcon("src/img/help.png").getImage();
    Help(){
            setSize(width,height);
            setResizable(false);
            setLayout(null);
            setLocationRelativeTo(null);
            setVisible(true);
        }
        public void paint(Graphics g){
            super.paint(g);
            g.drawImage(help, 0,0, null);

        }
    }

