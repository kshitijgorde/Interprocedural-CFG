import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Point;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.30
// 

public class TestFrame extends JFrame
{
    private static JPanel antPanel;
    private static JPanel defaultPanel;
    
    public static void main(final String[] args) {
        final TestFrame frame = new TestFrame();
        frame.setTitle("CrazyCursor Demo");
        frame.setSize(300, 250);
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().add(createTestPanel());
        final Image[] images = { new ImageIcon("img/bug1.png").getImage(), new ImageIcon("img/bug2.png").getImage(), new ImageIcon("img/bug3.png").getImage() };
        CrazyCursor.create(TestFrame.antPanel, images, new Point(15, 0));
        CrazyCursor.create(TestFrame.defaultPanel);
        frame.setVisible(true);
    }
    
    public static JPanel createTestPanel() {
        final JPanel testPanel = new JPanel(new BorderLayout());
        (TestFrame.antPanel = new JPanel(new FlowLayout(1, 10, 20))).add(new JButton("dummy one"));
        TestFrame.antPanel.add(new JButton("second dummy"));
        TestFrame.antPanel.add(new JTextField("Crazy Cursor ", 10));
        TestFrame.antPanel.setBorder(BorderFactory.createTitledBorder("Ant Area"));
        TestFrame.antPanel.setBackground(new Color(255, 255, 128));
        (TestFrame.defaultPanel = new JPanel(new FlowLayout())).add(new JButton("<html><center>this is also an <h2>  useless  </h2> button<center></html>"));
        TestFrame.defaultPanel.setBorder(BorderFactory.createTitledBorder("Default Area"));
        TestFrame.defaultPanel.setBackground(new Color(128, 255, 196));
        testPanel.add(TestFrame.antPanel, "Center");
        testPanel.add(TestFrame.defaultPanel, "East");
        return testPanel;
    }
}
