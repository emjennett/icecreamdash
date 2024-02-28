import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

//Class representing the patties
public class Sprinkles extends Toppings {

    // Same design as the bun
    public Sprinkles (int x, int y) {
        ImageIcon ii = new ImageIcon ("C:/Users/Emily/IdeaProjects/icecreamdashfr/resources/pics/Sprinkles.png");
        image = ii.getImage ();
        width = image.getWidth (null);
        height = image.getHeight (null);
        visible = true;
        this.x = x;
        this.y = y;
    }
}