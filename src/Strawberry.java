import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

//Class representing the patties
public class Strawberry extends Toppings {

    // Same design as the bun
    public Strawberry (int x, int y) {
        ImageIcon ii = new ImageIcon ("C:/Users/Emily/IdeaProjects/icecreamdashfr/resources/pics/StrawberryScoop.png");
        image = ii.getImage ();
        width = image.getWidth (null);
        height = image.getHeight (null);
        visible = true;
        this.x = x;
        this.y = y;
    }
}