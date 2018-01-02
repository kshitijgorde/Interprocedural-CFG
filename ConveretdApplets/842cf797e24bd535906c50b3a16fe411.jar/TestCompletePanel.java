import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class TestCompletePanel extends JPanel
{
    private JLabel starsLabel;
    private ImageIcon stars;
    private JButton theButton;
    
    public TestCompletePanel() {
        this.stars = Toolbox.createImageIcon("images/stars.gif");
        this.theButton = new JButton("Retry");
        this.starsLabel = new JLabel(this.stars);
        this.setLayout(new BorderLayout());
        this.add(this.starsLabel, "North");
        this.add(this.theButton, "Center");
    }
    
    public void setBackground(final Color c) {
        super.setBackground(c);
        if (this.theButton != null) {
            this.theButton.setBackground(c);
        }
    }
    
    public void showTheComponents(final boolean b) {
        this.starsLabel.setVisible(b);
        this.theButton.setVisible(b);
    }
    
    public void addButtonActionListener(final ActionListener listener) {
        this.theButton.addActionListener(listener);
    }
}
