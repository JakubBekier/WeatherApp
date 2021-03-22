import javax.swing.*;
import java.awt.*;

public class AppFrame {
    public JFrame frame;
    public AppFrame(JPanel panel){
        this.frame = new JFrame();
        this.frame.setSize(new Dimension(400, 400));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setTitle("Weather app");
        this.frame.setLocationRelativeTo(null);
        this.frame.add(panel);
        this.frame.setVisible(true);
    }

    public void changePanel(JPanel panel){
        this.frame.getContentPane().removeAll();
        this.frame.add(panel);
        this.frame.validate();
        this.frame.repaint();
    }

}
