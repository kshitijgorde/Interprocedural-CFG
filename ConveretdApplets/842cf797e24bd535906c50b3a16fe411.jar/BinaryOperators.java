import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.StringTokenizer;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BinaryOperators extends JApplet
{
    private final boolean _boolApplet;
    
    public BinaryOperators() {
        this(true);
    }
    
    public BinaryOperators(final boolean applet) {
        this._boolApplet = applet;
        if (this._boolApplet) {
            this.getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        }
    }
    
    public void init() {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        final String selString = this.pGetParameter("selections", "2 4 6 8 16");
        final StringTokenizer st = new StringTokenizer(selString);
        final String[] selections = new String[st.countTokens()];
        int index = 0;
        while (st.hasMoreTokens()) {
            selections[index++] = st.nextToken();
        }
        Color bgcolor;
        try {
            final int colorValue = Integer.decode(this.pGetParameter("backGroundColor", "0x7B7B7B"));
            bgcolor = new Color(colorValue);
        }
        catch (Exception e2) {
            bgcolor = Color.blue;
            e2.printStackTrace();
        }
        final JLabel titleLabel = new JLabel("Binary Logic Operators");
        final BinaryOperatorsPanel panel = new BinaryOperatorsPanel("", selections);
        this.getContentPane().setBackground(bgcolor);
        titleLabel.setHorizontalAlignment(0);
        titleLabel.setFont(new Font("Dialog", 1, 32));
        titleLabel.setForeground(Color.orange);
        panel.setBackground(bgcolor);
        this.getContentPane().setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = 11;
        c.insets = new Insets(0, 0, 20, 0);
        this.getContentPane().add(titleLabel, c);
        c.gridy = 1;
        c.weighty = 1.0;
        c.insets = new Insets(0, 0, 0, 0);
        this.getContentPane().add(panel, c);
    }
    
    private String pGetParameter(final String key, final String def) {
        if (this._boolApplet) {
            final String param = this.getParameter(key);
            return (param == null) ? def : param;
        }
        return System.getProperty(key, def);
    }
}
