import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Cone {

    //The name of the image representing the Cone
    private Image image;

    //Movement variables
    int dx;
    int dy;
    public int x;
    public int y;
    public int speed = 2;
    private Timer timer;

    //Represents the width and height of the Cone
    private int width;
    public int height;

    //Controls visibility of the Cone
    private boolean visible;

    //Constructs the Cone
    public Cone () {
        //Creates the image representing the Cone
        ImageIcon ii = new ImageIcon ("C:/Users/Emily/IdeaProjects/icecreamdashfr/resources/pics/Cone.png");
        image = ii.getImage ();

        //Calculcates the size of the Cone image.
        width = image.getWidth (null);
        height = image.getHeight (null);

        visible = true;

        //Sets the initial position of the Cone
        x = 230;
        y = 350;
    }

    // Moves the Cone
    public void move () {
        x += dx;

        //If statement keeps it within the board width
        if (x < 1) {
            x = 1;
        }
        else if (x > 500) {
            x = 500;
        }
    }

    // Returns the x-coordinate of the Cone
    public int getX () {
        return x;
    }

    // Returns the y-coordinate of the Cone
    public int getY () {
        return y;
    }

    // Returns the image of the Cone
    public Image getImage () {
        return image;
    }

    //Sets the visibility of the Cone
    public void setVisible (boolean visible) {
        this.visible = visible;
    }

    //Returns whether or not the Cone is visible
    public boolean isVisible () {
        return visible;
    }

    //Returns a Rectangle object representing the shape of the Cone
    public Rectangle getBounds () {
        return new Rectangle (x, y, width, height);
    }

    //Movement in reaction to pressed keys
    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode ();

        if (key == KeyEvent.VK_LEFT) {
            dx = speed * -1;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = speed;
        }
    }

    //Movement in reaction to released keys
    public void keyReleased (KeyEvent e) {
        int key = e.getKeyCode ();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}