import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.Style;

public class Instructions extends JPanel {

    public Instructions() throws Exception{
        this.setLayout(null);
        JTextArea title = new JTextArea();
        title.append("♡ HOW TO PLAY ♡");
        title.setOpaque(false);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBounds(225,200,170,60);
        JTextArea textArea = new JTextArea(5,10);
        textArea.setBounds(143,225,520,165);
        textArea.setFont(new Font("SansSerif", Font.BOLD, 14));
        textArea.append(" The objective of Ice Cream Dash is to catch \n" +
                "    the correct amount of toppings on the \n" +
                "ice cream cone using the left and right arrows\n" +
                "      on your keyboard. But be careful!\n" +
                "     Each incorrect topping you catch will\n" +
                " count as a mistake. 3 mistakes and you're out!");
        textArea.setOpaque(false);
        this.add(title);
        this.add(textArea);
        this.setOpaque(false);
    }
}