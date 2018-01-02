import java.awt.event.ActionEvent;
import javax.swing.Box;
import java.awt.Component;
import java.net.MalformedURLException;
import java.awt.Dimension;
import javax.swing.Icon;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ArrowPane extends JPanel implements ActionListener
{
    private imgViewer applet;
    private JButton rightButton;
    private JButton leftButton;
    private JButton upButton;
    private JButton downButton;
    private ImageIcon upIcon;
    private ImageIcon downIcon;
    private ImageIcon leftIcon;
    private ImageIcon rightIcon;
    
    public ArrowPane(final imgViewer applet) {
        this.setLayout(new BoxLayout(this, 0));
        this.applet = applet;
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 1));
        this.upButton = new JButton();
        this.downButton = new JButton();
        this.leftButton = new JButton();
        this.rightButton = new JButton();
        try {
            this.upIcon = new ImageIcon(new URL(applet.getCodeBase(), "graphics/upbutton.gif"));
            this.downIcon = new ImageIcon(new URL(applet.getCodeBase(), "graphics/downbutton.gif"));
            this.leftIcon = new ImageIcon(new URL(applet.getCodeBase(), "graphics/leftbutton.gif"));
            this.rightIcon = new ImageIcon(new URL(applet.getCodeBase(), "graphics/rightbutton.gif"));
            this.upButton.setIcon(this.upIcon);
            this.downButton.setIcon(this.downIcon);
            this.leftButton.setIcon(this.leftIcon);
            this.rightButton.setIcon(this.rightIcon);
            final Dimension dimension = new Dimension(32, 35);
            this.upButton.setPreferredSize(dimension);
            this.downButton.setPreferredSize(dimension);
            this.leftButton.setPreferredSize(dimension);
            this.rightButton.setPreferredSize(dimension);
        }
        catch (MalformedURLException ex) {
            this.upButton.setText("U");
            this.downButton.setText("D");
            this.leftButton.setText("L");
            this.rightButton.setText("R");
        }
        panel.add(this.leftButton);
        panel2.add(this.rightButton);
        panel3.add(this.upButton);
        panel3.add(Box.createVerticalStrut(3));
        panel3.add(this.downButton);
        this.add(panel);
        this.add(Box.createHorizontalStrut(3));
        this.add(panel3);
        this.add(Box.createHorizontalStrut(3));
        this.add(panel2);
        this.rightButton.setToolTipText("Move right");
        this.leftButton.setToolTipText("Move left");
        this.upButton.setToolTipText("Move up");
        this.downButton.setToolTipText("Move down");
        this.rightButton.addActionListener(this);
        this.leftButton.addActionListener(this);
        this.upButton.addActionListener(this);
        this.downButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        final JButton button = (JButton)actionEvent.getSource();
        if (button == this.rightButton) {
            n = 1;
        }
        else if (button == this.leftButton) {
            n2 = 1;
        }
        else if (button == this.upButton) {
            n3 = 1;
        }
        else if (button == this.downButton) {
            n4 = 1;
        }
        this.applet.imgArea.md.scrollInDirection(n, n2, n3, n4);
    }
    
    public void cleanup() {
        if (this.upIcon != null) {
            this.upIcon.getImage().flush();
        }
        if (this.downIcon != null) {
            this.downIcon.getImage().flush();
        }
        if (this.leftIcon != null) {
            this.leftIcon.getImage().flush();
        }
        if (this.rightIcon != null) {
            this.rightIcon.getImage().flush();
        }
        this.upIcon = null;
        this.downIcon = null;
        this.leftIcon = null;
        this.rightIcon = null;
    }
}
