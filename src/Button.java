/* Button class

 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements MouseListener{

    Button(String text){
        setText(text);
        setFont(new Font("Georgia", Font.PLAIN, 25));
        setFocusable(false);
        setBackground(new Color(170, 215, 81));
        setForeground(Color.black);
        addMouseListener(this);
       // setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Audio clicked = new Audio("src/sounds/click.wav");
        clicked.audio.start();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
