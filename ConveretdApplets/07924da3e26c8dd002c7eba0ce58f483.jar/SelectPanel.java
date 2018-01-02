import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SelectPanel extends JPanel implements ActionListener
{
    private UpdateGates _gui;
    private JRadioButton _radio211;
    private JRadioButton _radio321;
    private JRadioButton _radio231;
    private JRadioButton _radio431;
    private JRadioButton _radio241;
    private JRadioButton _radioFF1;
    private JRadioButton _radioFF2;
    
    public SelectPanel(final UpdateGates gui, final int x, final int y, final int width, final int height) {
        this._gui = gui;
        this.setBounds(x, y, width, height);
        this.setBackground(new Color(192, 192, 192));
        this.setBorder(new BorderUIResource.EtchedBorderUIResource(1));
        this.pInitComponents();
    }
    
    private void pInitComponents() {
        (this._radio211 = new JRadioButton("1 Gate")).setBackground(this.getBackground());
        this._radio211.setSelected(true);
        this._radio211.addActionListener(this);
        (this._radio321 = new JRadioButton("2 Gates")).setBackground(this.getBackground());
        this._radio321.addActionListener(this);
        (this._radio231 = new JRadioButton("3 Gates (2 inputs)")).setBackground(this.getBackground());
        this._radio231.addActionListener(this);
        (this._radio431 = new JRadioButton("3 Gates (4 inputs)")).setBackground(this.getBackground());
        this._radio431.addActionListener(this);
        (this._radio241 = new JRadioButton("4 Gates")).setBackground(this.getBackground());
        this._radio241.addActionListener(this);
        (this._radioFF1 = new JRadioButton("Flip-Flop (Figure 1.3)")).setBackground(this.getBackground());
        this._radioFF1.addActionListener(this);
        (this._radioFF2 = new JRadioButton("Flip-Flop (Figure 1.5)")).setBackground(this.getBackground());
        this._radioFF2.addActionListener(this);
        final ButtonGroup group = new ButtonGroup();
        group.add(this._radio211);
        group.add(this._radio321);
        group.add(this._radio231);
        group.add(this._radio431);
        group.add(this._radio241);
        group.add(this._radioFF1);
        group.add(this._radioFF2);
        this.add(this._radio211);
        this.add(this._radio321);
        this.add(this._radio231);
        this.add(this._radio431);
        this.add(this._radio241);
        this.add(this._radioFF1);
        this.add(this._radioFF2);
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (this._radio211.isSelected()) {
            this._gui.setGatePanel211();
        }
        else if (this._radio321.isSelected()) {
            this._gui.setGatePanel321();
        }
        else if (this._radio231.isSelected()) {
            this._gui.setGatePanel231();
        }
        else if (this._radio431.isSelected()) {
            this._gui.setGatePanel431();
        }
        else if (this._radio241.isSelected()) {
            this._gui.setGatePanel241();
        }
        else if (this._radioFF1.isSelected()) {
            this._gui.setGatePanelFF1();
        }
        else if (this._radioFF2.isSelected()) {
            this._gui.setGatePanelFF2();
        }
    }
}
