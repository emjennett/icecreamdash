import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

//Class representing the patties
public class Chocolate extends Toppings {


    // Same design as the bun
    public Chocolate (int x, int y) {
        ImageIcon ii = new ImageIcon ("C:/Users/Emily/IdeaProjects/icecreamdashfr/resources/pics/ChocolateScoop.png");
        image = ii.getImage ();
        width = image.getWidth (null);
        height = image.getHeight (null);
        visible = true;
        this.x = x;
        this.y = y;
    }
}