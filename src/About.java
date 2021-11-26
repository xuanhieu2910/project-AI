/*
About screen, shows the Project overview, & Developer contacts(Social media handlers).
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;

public class About extends JFrame implements MouseListener  {
    final static JPanel handler_panel = new JPanel();                //Panel for 3 social media icons

    //Images for Twitter, Github & Linkedin Icon
    final static Image help = new ImageIcon("src/img/about.png").getImage();
    JLabel twitter,github, linkedin;        //Label for handlers
    ImageIcon twitter_img = new ImageIcon("src/img/twitter.png"),
            github_img = new ImageIcon("src/img/github.png"),
            linkedin_img = new ImageIcon("src/img/linkedin.png");

    About() {
        handler_panel.setBounds(110, 440, 200, 200);
        handler_panel.setOpaque(true);
        handler_panel.setBackground(Color.white);

        setSize(600, 600);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

        //Properties of Handlers
        twitter = new JLabel("");
        twitter.setIcon(twitter_img);
        twitter.setSize(60, 60);

        github = new JLabel("");
        github.setIcon(github_img);
        github.setSize(60, 60);

        linkedin = new JLabel("");
        linkedin.setIcon(linkedin_img);
        linkedin.setSize(60, 60);
        linkedin.addMouseListener(this);
        github.addMouseListener(this);
        twitter.addMouseListener(this);

        github.setCursor(new Cursor(Cursor.HAND_CURSOR)); // If I Move The Cursor In The LabelImg Change The Cursor Design By Hand
        linkedin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        twitter.setCursor(new Cursor(Cursor.HAND_CURSOR));

        handler_panel.add(twitter);
        handler_panel.add(github);
        handler_panel.add(linkedin);


        add(handler_panel);
    }


    //Drawing image of Developer
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(help, 0,0, null);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(linkedin))
        try {
            Audio clicked = new Audio("src/sounds/click.wav");
            clicked.audio.start();
            Desktop.getDesktop().browse(URI.create("https://www.linkedin.com/in/talha-tariq-7511631b0/"));

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        //This is for audio playing when we'll ckick the icon
        if(e.getSource().equals(twitter))
            try {
                Audio clicked = new Audio("src/sounds/click.wav");
                clicked.audio.start();
                Desktop.getDesktop().browse(URI.create("https://twitter.com/Talha_Tariq_TT"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        if(e.getSource().equals(github))
            try {
                Audio clicked = new Audio("src/sounds/click.wav");
                clicked.audio.start();

                Desktop.getDesktop().browse(URI.create("https://github.com/TT-talhatariq"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


    }

    @Override
    public void mousePressed(MouseEvent e) {

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
