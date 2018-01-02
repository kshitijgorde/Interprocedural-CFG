import java.awt.Component;
import java.awt.Insets;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import java.util.StringTokenizer;
import java.awt.Color;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TwosComplement extends JApplet
{
    private GridBagLayout layout;
    private GridBagConstraints c;
    private String selString;
    private String[] selections;
    
    public TwosComplement() {
        this.layout = new GridBagLayout();
        this.c = new GridBagConstraints();
    }
    
    public void init() {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Color bgcolor;
        try {
            final int colorValue = Integer.decode(this.getParameter("backGroundColor"));
            bgcolor = new Color(colorValue);
        }
        catch (Exception e2) {
            bgcolor = Color.blue;
            e2.printStackTrace();
        }
        if (this.getParameter("selections") != null) {
            this.selString = this.getParameter("selections");
            final StringTokenizer st = new StringTokenizer(this.selString);
            this.selections = new String[st.countTokens()];
            int index = 0;
            while (st.hasMoreTokens()) {
                this.selections[index++] = st.nextToken();
            }
        }
        else {
            (this.selections = new String[1])[0] = "4";
        }
        this.getContentPane().setBackground(bgcolor);
        final JLabel titleLabel = new JLabel("Two's Complement");
        final MainPanel panel = new MainPanel("+", this.selections);
        this.c.anchor = 11;
        titleLabel.setBackground(bgcolor);
        panel.setBackground(bgcolor);
        this.getContentPane().setLayout(this.layout);
        titleLabel.setHorizontalAlignment(0);
        titleLabel.setFont(new Font("Dialog", 1, 32));
        titleLabel.setForeground(Color.orange);
        this.c.insets = new Insets(0, 0, 40, 0);
        this.getContentPane().add(titleLabel, this.c);
        this.c.gridy = 1;
        this.c.weighty = 1.0;
        this.getContentPane().add(panel, this.c);
    }
}
