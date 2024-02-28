import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

//Class representing the patties
public class Cherry extends Toppings {


    // Same design as the bun
    public Cherry (int x, int y) {
        ImageIcon ii = new ImageIcon ("C:/Users/Emily/IdeaProjects/icecreamdashfr/resources/pics/Cherry.png");
        image = ii.getImage ();
        width = image.getWidth (null);
        height = image.getHeight (null);
        visible = true;
        this.x = x;
        this.y = y;
    }
}