import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Toppings {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    //Booleans determine if its falling or stacked
    protected boolean fall = true;
    protected boolean stacked = false;
    protected Image image;


    //Method to make it fall
    public void user () {
        if (fall == true) {
            if (this.y > 500) {
                this.y = getRandomY ();
                this.x = getRandomX ();
            }
            this.y += 1;
        }
    }

    //Method to make it move after stacked
    public void move (int x) {
        this.x = x;
    }

    //Acquires random y-coordinate for falling
    public static int getRandomY () {
        int result = (int) (Math.random () * 3000 + 1);
        result = 0 - result;
        return result;
    }

    //Acquires random x-coordinate for falling
    public static int getRandomX () {
        int result = (int) (Math.random () * 500 + 1);
        return result;
    }

    //Sets the fall boolean to false
    public void stopFall () {
        this.fall = false;
    }
    //a.getY() is the cone's Y coord. b represents the # of stacked toppings.
    //Stacks the topping using cone coordinates and number of toppings stacked
    public void stack (Cone a, int b) {
        this.stacked = true; //shows if the topping gets stacked
        this.x = a.getX ();
        this.y = a.getY()- 26* b;
    }

    //These methods return things
    public int getXA () {
        return x;
    }

    public int getYA () {
        return y;
    }

    public boolean isVisible () {
        return visible;
    }

    public boolean isStack () {
        return stacked;
    }

    public void setVisible (boolean visible) {
        this.visible = visible;
    }

    public Image getImage () {
        return image;
    }

    //Creates Rectangle using topping bounds
    public Rectangle getBounds () {
        return new Rectangle (x, y, width, height);
    }
}
