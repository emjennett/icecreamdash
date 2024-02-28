import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public class IceCreamCone extends JPanel implements ActionListener {

    private Image backgroundImage = ImageIO.read(new File("resources/pics/Background.jpg"));

    // Timer for animation
    private Timer timer;

    // The bottom bun
    private Cone cone;

    // Lists of topping objects
    private ArrayList straw;
    private ArrayList chocs;
    private ArrayList sprink;
    private ArrayList cher;

    // Variables used for game scores
    int strawberryStat;
    int chocolateStat;
    int sprinkleStat;
    int cherryStat;

    //Indicates if the player won
    boolean win = false;
    //Used to control stacking
    boolean fstStack = false;
    //controls collisions
    boolean canCollide = true;
    //Represents number of toppings caught
    int stackN;
    //Represents lives left
    int lives;

    //Controls game over screen
    private boolean game;

    // integers representing the board width and height.
    private int B_WIDTH;
    private int B_HEIGHT;

    //Creates random initial positions of the toppings
    //Strawberry positions
    private int[] [] pos = {
            {getRandomX (), getRandomY () },
            {getRandomX (), getRandomY () },
            {getRandomX (), getRandomY () },


    };
    //Chocolate positions
    private int[] [] chocPos = {
            {getRandomX (), getRandomY () },
            {getRandomX (), getRandomY () },
            {getRandomX (), getRandomY () },

    };
    //Sprinkles positions
    private int[] [] sprinkPos = {
            {getRandomX (), getRandomY () },
            {getRandomX (), getRandomY () },
            {getRandomX (), getRandomY () },

    };

    //Cherry positions
    private int[] [] cherPos = {
            {getRandomX (), getRandomY () },
            {getRandomX (), getRandomY () },
            {getRandomX (), getRandomY () },

    };

    //Method for acquiring random y coordinates
    public static int getRandomY () {
        int result = (int) (Math.random () * 500);
        result = 0 - result;
        return result;
    }

    //Method for acquiring random x coordinates
    public static int getRandomX () {
        int result = (int) (Math.random () * 300 + 1);
        return result;
    }

    ////Method for acquiring number of each topping needed
    public static int orderUp () {
        int result = (int) (Math.random () * 3 + 0);
        return result;
    }

    // Constructor for the board
    public IceCreamCone () throws Exception {
        // Key listener for the keyboard input.
        this.addKeyListener (new TAdapter ());

        //Settings for the JFrame
        this.setFocusable (true);
        this.setDoubleBuffered (true);
        this.setSize (600, 500);

        //Game over screen is hidden
        this.game = true;

        //Creates the cone
        this.cone = new Cone ();

        //Method is called to initialize the toppings
        toppings ();

        //The game requirements are created
        strawberryStat = orderUp ();
        chocolateStat = orderUp ();
        sprinkleStat = orderUp ();
        cherryStat = orderUp ();

        //makes sure there is always a topping needed
        if (strawberryStat==0 && chocolateStat ==0 &&sprinkleStat ==0 &&cherryStat==0){
            strawberryStat=1;
        }

        //Starts the animation timer to update animations every 15ms.
        timer = new Timer (15, this);
        timer.start ();
    }

    public void addNotify () {
        super.addNotify ();
        B_WIDTH = getWidth ();
        B_HEIGHT = getHeight ();
    }

    // Method to create lists of toppings to be drawn to screen.
    public void toppings () {
        straw = new ArrayList ();
        chocs = new ArrayList ();
        sprink = new ArrayList ();
        cher = new ArrayList ();

        for (int i = 0 ; i < pos.length ; i++) {
            straw.add (new Strawberry (pos [i] [0], pos [i] [1]));
            chocs.add (new Chocolate (chocPos [i] [0], chocPos [i] [1]));
            sprink.add (new Sprinkles (sprinkPos [i] [0], sprinkPos [i] [1]));
            cher.add (new Cherry (cherPos [i] [0], cherPos [i] [1]));
        }
    }

    //Draws the game
    public void paint (Graphics g) {

        super.paint (g);

        g.drawImage(backgroundImage, 0, 0, null);

        if (game) { //If the player still has lives
            Graphics2D g2d = (Graphics2D) g;

            //The cone is drawn
            if (cone.isVisible ()) {
                g2d.drawImage (cone.getImage (), cone.getX (), cone.getY (), this);
            }

            //For loop to draw toppings.
            for (int i = 0 ; i < straw.size () ; i++) {
                Strawberry a = (Strawberry) straw.get (i);
                if (a.isVisible ())
                    g2d.drawImage (a.getImage (), a.getXA (), a.getYA (), this);

                Chocolate m = (Chocolate) chocs.get (i);
                if (a.isVisible ())
                    g2d.drawImage (m.getImage (), m.getXA (), m.getYA (), this );

                Sprinkles t = (Sprinkles) sprink.get (i);
                if (t.isVisible ())
                    g2d.drawImage (t.getImage (), t.getXA (), t.getYA (), this);

                Cherry b = (Cherry) cher.get (i);
                if (b.isVisible ())
                    g2d.drawImage (b.getImage (), b.getXA (), b.getYA (), this);
            }

            // Displays game stats
            Font small = new Font ("monospaced", Font.PLAIN, 20);
            FontMetrics metr = this.getFontMetrics (small);

            g2d.setColor (Color.BLACK);
            g2d.setFont (small);
            g2d.drawString ("Strawberry: " + strawberryStat, 5, 20);
            g2d.drawString ("Chocolate:   " + chocolateStat, 5, 40);
            g2d.drawString ("Sprinkles:  " + sprinkleStat, 5, 60);
            g2d.drawString ("Cherries:  " + cherryStat, 5, 80);
            g2d.drawString ("Mistakes left: " + lives, 200, 20);

            //If the player wins
            if (win) {
                canCollide = false;
                String msg = "Ice Cream Done!";
                Font small2 = new Font ("monospaced", Font.PLAIN, 30);
                FontMetrics metr2 = this.getFontMetrics (small2);
                g.setColor (Color.BLACK);
                g.setFont (small2);
                g.drawString (msg, (B_WIDTH - metr2.stringWidth (msg)) / 2, B_HEIGHT / 2);
            }

        }
        else {
            //If the player runs out of lives
            String msg = "Game Over";
            Font small = new Font ("Cooper Black", Font.PLAIN, 30);
            FontMetrics metr = this.getFontMetrics (small);
            g.setColor (Color.BLACK);
            g.setFont (small);
            g.drawString (msg, (B_WIDTH - metr.stringWidth (msg)) / 2,B_HEIGHT / 2);
        }
        Toolkit.getDefaultToolkit ().sync ();
        g.dispose ();
    }

    public void actionPerformed (ActionEvent e) {
        // Updates the animations
        // Updates bun location
        cone.move ();

        //Updates topping locations
        //Based on if it's stacked and the bun's x coordinate
        for (int i = 0 ; i < straw.size () ; i++) {
            Strawberry a = (Strawberry) straw.get (i);
            if (!a.isStack ())
                a.user ();
            else
                a.move (cone.getX ());

            Chocolate m = (Chocolate) chocs.get (i);
            if (!m.isStack ())
                m.user ();
            else
                m.move (cone.getX ());

            Sprinkles t = (Sprinkles) sprink.get (i);
            if (!t.isStack ())
                t.user ();
            else
                t.move (cone.getX ());

            Cherry b = (Cherry) cher.get (i);
            if (!b.isStack ())
                b.user ();
            else
                b.move (cone.getX ());
        }

        //Checks if toppings are stacked and repaints board
        checkCollisions ();
        repaint ();
    }

    public void checkCollisions () {
        //only executes if game isn't over
        if (canCollide == true){
            //Creates 'Hitbox' based of bun x-coordinate and the number of stacked toppings
            Rectangle rHit = new Rectangle (cone.getX (), 350 - stackN * 10, 50, 10);

            //Number of toppings stacked is set to 0 and lives to 3
            if (fstStack == false ) {
                stackN = 0;
                lives = 3;
            }

            //Rectangle is created for every topping.
            Strawberry st1 = (Strawberry) straw.get (0);
            Rectangle rL1 = st1.getBounds ();
            Strawberry st2 = (Strawberry) straw.get (1);
            Rectangle rL2 = st2.getBounds ();
            Strawberry st3 = (Strawberry) straw.get (2);
            Rectangle rL3 = st3.getBounds ();

            Chocolate c1 = (Chocolate) chocs.get (0);
            Rectangle rc1 = c1.getBounds ();
            Chocolate c2 = (Chocolate) chocs.get (1);
            Rectangle rc2 = c2.getBounds ();
            Chocolate c3 = (Chocolate) chocs.get (2);
            Rectangle rc3 = c3.getBounds ();

            Sprinkles t1 = (Sprinkles) sprink.get (0);
            Rectangle rT1 = t1.getBounds ();
            Sprinkles t2 = (Sprinkles) sprink.get (1);
            Rectangle rT2 = t2.getBounds ();
            Sprinkles t3 = (Sprinkles) sprink.get (2);
            Rectangle rT3 = t3.getBounds ();

            Cherry cy1 = (Cherry) cher.get (0);
            Rectangle rcy1 = cy1.getBounds ();
            Cherry b2 = (Cherry) cher.get (1);
            Rectangle rB2 = b2.getBounds ();
            Cherry b3 = (Cherry) cher.get (2);
            Rectangle rB3 = b3.getBounds ();

            //If the Hitbox intersects with a topping
            if (rHit.intersects (rL1) ||
                    rHit.intersects (rL2) ||
                    rHit.intersects (rL3) ||

                    rHit.intersects (rc1) ||
                    rHit.intersects (rc2) ||
                    rHit.intersects (rc3) ||

                    rHit.intersects (rT1) ||
                    rHit.intersects (rT2) ||
                    rHit.intersects (rT3) ||

                    rHit.intersects (rcy1) ||
                    rHit.intersects (rB2) ||
                    rHit.intersects (rB3) ) {
                //The first topping has been stacked
                fstStack = true;

                //If the intersection took place here and the topping is not stacked
                if (rHit.intersects (rL1) && !st1.isStack ()) {
                    rHit = st1.getBounds (); //The hitbox moves to the location of this topping
                    stackN++;   //The number of toppings stacked is increased
                    st1.stopFall ();
                    //The topping scher falling
                    //(more of a precaution now -- may not be necessary)
                    st1.stack (cone, stackN); //The topping is added to the stack

                    //The game score is updated depending on if the topping was required
                    if (strawberryStat == 0)
                        lives--;
                    if (strawberryStat > 0)
                        strawberryStat--;
                    if (strawberryStat == 0 && chocolateStat == 0 && sprinkleStat == 0 && cherryStat == 0) {
                        win = true;
                    }
                } //Repeated for every other topping
                else if (rHit.intersects (rL2) && !st2.isStack ()) {
                    rHit = st2.getBounds ();
                    stackN++;
                    st2.stopFall ();
                    st2.stack (cone, stackN);

                    if (strawberryStat == 0)
                        lives--;
                    if (strawberryStat > 0)
                        strawberryStat--;
                    if (strawberryStat == 0 && chocolateStat == 0 && sprinkleStat == 0 && cherryStat == 0) {
                        win = true;
                    }
                }
                else if (rHit.intersects (rL3) && !st3.isStack ()) {
                    rHit = st3.getBounds ();
                    stackN++;
                    st3.stopFall ();
                    st3.stack (cone, stackN);

                    if (strawberryStat == 0)
                        lives--;
                    if (strawberryStat > 0)
                        strawberryStat--;
                    if (strawberryStat == 0 && chocolateStat == 0 && sprinkleStat == 0 && cherryStat == 0) {
                        win = true;
                    }
                }

                else if (rHit.intersects (rc1) && !c1.isStack ()) {
                    rHit = c1.getBounds ();
                    stackN++;
                    c1.stopFall ();
                    c1.stack (cone, stackN);

                    if (chocolateStat == 0)
                        lives--;
                    if (chocolateStat > 0)
                        chocolateStat--;
                    if (strawberryStat == 0 && chocolateStat == 0 && sprinkleStat == 0 && cherryStat == 0) {
                        win = true;
                    }
                }
                else if (rHit.intersects (rc2) && !c2.isStack ()) {
                    rHit = c2.getBounds ();
                    stackN++;
                    c2.stopFall ();
                    c2.stack (cone, stackN);

                    if (chocolateStat == 0)
                        lives--;
                    if (chocolateStat > 0)
                        chocolateStat--;
                    if (strawberryStat == 0 && chocolateStat == 0 && sprinkleStat == 0 && cherryStat == 0) {
                        win = true;
                    }
                }
                else if (rHit.intersects (rc3) && !c3.isStack ()) {
                    rHit = c3.getBounds ();
                    stackN++;
                    c3.stopFall ();
                    c3.stack (cone, stackN);

                    if (chocolateStat == 0)
                        lives--;
                    if (chocolateStat > 0)
                        chocolateStat--;
                    if (strawberryStat == 0 && chocolateStat == 0 && sprinkleStat == 0 && cherryStat == 0) {
                        win = true;
                    }
                }

                else if (rHit.intersects (rT1) && !t1.isStack ()) {
                    rHit = t1.getBounds ();
                    stackN++;
                    t1.stopFall ();
                    t1.stack (cone, stackN);

                    if (sprinkleStat == 0)
                        lives--;
                    if (sprinkleStat > 0)
                        sprinkleStat--;
                    if (strawberryStat == 0 && chocolateStat == 0 && sprinkleStat == 0 && cherryStat == 0) {
                        win = true;
                    }
                }
                else if (rHit.intersects (rT2) && !t2.isStack ()) {
                    rHit = t2.getBounds ();
                    stackN++;
                    t2.stopFall ();
                    t2.stack (cone, stackN);

                    if (sprinkleStat == 0)
                        lives--;
                    if (sprinkleStat > 0)
                        sprinkleStat--;
                    if (strawberryStat == 0 && chocolateStat == 0 && sprinkleStat == 0 && cherryStat == 0) {
                        win = true;
                    }
                }
                else if (rHit.intersects (rT3) && !t3.isStack ()) {
                    rHit = t3.getBounds ();
                    stackN++;
                    t3.stopFall ();
                    t3.stack (cone, stackN);

                    if (sprinkleStat == 0)
                        lives--;
                    if (sprinkleStat > 0)
                        sprinkleStat--;
                    if (strawberryStat == 0 && chocolateStat == 0 && sprinkleStat == 0 && cherryStat == 0) {
                        win = true;
                    }
                }

                else if (rHit.intersects (rcy1) && !cy1.isStack ()) {
                    rHit = cy1.getBounds ();
                    stackN++;
                    cy1.stopFall ();
                    cy1.stack (cone, stackN);
                    if (cherryStat == 0)
                        lives--;
                    if (cherryStat > 0)
                        cherryStat--;

                    if (strawberryStat == 0 && chocolateStat == 0 && sprinkleStat == 0 && cherryStat == 0) {
                        win = true;
                    }
                }
                else if (rHit.intersects (rB2) && !b2.isStack ()) {
                    rHit = b2.getBounds ();
                    stackN++;
                    b2.stopFall ();
                    b2.stack (cone, stackN);
                    if (cherryStat == 0)
                        lives--;
                    if (cherryStat > 0)
                        cherryStat--;

                    if (strawberryStat == 0 && chocolateStat == 0 && sprinkleStat == 0&& cherryStat == 0 ) {
                        win = true;
                    }
                }
                else if (rHit.intersects (rB3) && !b3.isStack ()) {
                    rHit = b3.getBounds ();
                    stackN++;
                    b3.stopFall ();
                    b3.stack (cone, stackN);
                    if (cherryStat == 0)
                        lives--;
                    if (cherryStat > 0)
                        cherryStat--;

                    if (strawberryStat == 0 && chocolateStat == 0 && sprinkleStat == 0 && cherryStat == 0 ) {
                        win = true;
                    }
                }
            }

            if (lives < 0) //If there are no lives left, the game is over
                game = false;
        }
    }

    // Key listener
    private class TAdapter extends KeyAdapter {

        public void keyReleased (KeyEvent e) {
            cone.keyReleased (e);
        }

        public void keyPressed (KeyEvent e) {
            cone.keyPressed (e);
        }
    }
}