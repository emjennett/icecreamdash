import javax.swing.JFrame;
public class Main {

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        Menu panel = new Menu();
        panel.setOpaque(false);
        frame.add(panel);
        frame.setSize(600,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

