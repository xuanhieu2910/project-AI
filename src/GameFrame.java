//Main Frame
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
    final  static Image icon = new ImageIcon("src/img/snake.png").getImage();
    GameFrame(){
        add(new GamePanel());
        setTitle("TT Snake");
        setSize(750,700);
        setIconImage(icon);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
