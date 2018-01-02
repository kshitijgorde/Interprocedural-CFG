import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Icon;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_r extends rp_Y
{
    private static Icon a;
    
    public rp_r(final JComponent component, final JComponent component2) {
        super(component, component2);
        final JButton button;
        (button = new JButton()).setHorizontalAlignment(0);
        button.setRolloverEnabled(false);
        button.setContentAreaFilled(false);
        button.setForeground(rp_aJ.r);
        final JButton a = button;
        (super.a = a).putClientProperty("doNotCancelPopup", new JComboBox<Object>().getClientProperty("doNotCancelPopup"));
        a.addActionListener(this);
        this.add(a, "Center");
        final JButton button2;
        (button2 = new JButton(rp_r.a)).setMargin(new Insets(0, 2, 0, 2));
        button2.setRolloverEnabled(false);
        button2.setContentAreaFilled(false);
        final JButton a2 = button2;
        (super.a = a2).putClientProperty("doNotCancelPopup", new JComboBox<Object>().getClientProperty("doNotCancelPopup"));
        a2.addActionListener(this);
        this.add(a2, "East");
    }
    
    static {
        rp_r.a = new ImageIcon(rp_r.class.getClassLoader().getResource("res/cb_open.png"));
    }
}
