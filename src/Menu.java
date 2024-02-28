import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Menu extends JPanel implements ActionListener{

    private Image backgroundImage;
    CardLayout card;
    JButton playButton;
    JButton instrButton;
    JButton exitButton;
    JButton homeButton;
    Container c;
    IceCreamCone game;
    Instructions instructions;
    JPanel menu;

    public Menu()throws Exception{
        backgroundImage = new ImageIcon("resources/pics/bg.png").getImage();

        card=new CardLayout();
        this.setLayout(card);

        menu = new JPanel();
        menu.setOpaque(false);
        instructions = new Instructions();
        menu.setLayout(null);
//sets constraints
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        //arranges constraints
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //creates border for buttons
        Border border = new LineBorder(new Color (255, 122, 207), 4);
        //creates dimensions for buttons
        Dimension d = new Dimension (250,40);

        //creates play button
        playButton =new JButton("Play Now!");
        playButton.addActionListener(this);
        playButton.setPreferredSize(d);
        playButton.setBorder(border);
        //creates instructions button
        instrButton =new JButton("How To Play");
        instrButton.addActionListener(this);
        instrButton.setPreferredSize(d);
        instrButton.setBorder(border);
        //creates exit button
        exitButton =new JButton("Exit");
        exitButton.addActionListener(this);
        exitButton.setPreferredSize(d);
        exitButton.setBorder(border);
        //creates back button
        homeButton = new JButton("Back");
        homeButton.addActionListener(this);
        homeButton.setPreferredSize(d);
        homeButton.setBorder(border);
        //panel for buttons using gridbaglayout
        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setBounds(40,220,520,165);
        buttons.setOpaque(false);
        //adds buttons to panel
        buttons.add(playButton, gbc);
        buttons.add(instrButton , gbc);
        buttons.add(exitButton, gbc );

        gbc.weighty = 1;
        //adds buttons to menu panel
        menu.add(buttons, gbc);

        this.add("menu", menu);
        instructions.add("home button",homeButton );
        homeButton.setBounds(260,360, 70, 30);
        this.add("instructions", instructions);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }

    public void actionPerformed(ActionEvent event)
    {
        Object source = event.getSource();

        try
        {
            if (source == playButton)
            {
                game = new IceCreamCone();
                this.add("game", game);
                card.show(this, "game");
                game.requestFocusInWindow();
            }
            if (source == instrButton )
            {
                card.show(this, "instructions");
            }

            if (source == homeButton )
            {
                card.show(this, "menu");

            }
            if (source == exitButton )
            {
                ((Window) getRootPane().getParent()).dispose();

            }
        }
        catch(Exception e) {

            e.printStackTrace();
        }
    }

}
