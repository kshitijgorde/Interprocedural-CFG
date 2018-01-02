import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_aP extends JPanel
{
    rp_Z a;
    
    public rp_aP(final rp_bE rp_bE, final ActionListener actionListener, final Color color, final Color color2) {
        final JRadioButton radioButton;
        (radioButton = new JRadioButton("border")).setActionCommand("CB");
        radioButton.addActionListener(actionListener);
        final JRadioButton radioButton2;
        (radioButton2 = new JRadioButton("fill")).setActionCommand("CF");
        radioButton2.addActionListener(actionListener);
        final JPanel panel;
        (panel = new JPanel()).setLayout(new BoxLayout(panel, 1));
        panel.add(radioButton);
        panel.add(radioButton2);
        this.add(panel);
        this.add(this.a = new rp_Z(rp_bE, color, color2));
        final ButtonGroup buttonGroup;
        (buttonGroup = new ButtonGroup()).add(radioButton);
        buttonGroup.add(radioButton2);
        radioButton2.setSelected(true);
    }
}
