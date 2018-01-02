// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Box;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class StatusInput extends JFrame implements ActionListener
{
    private static final String ACT_OK = "K";
    private static final String ACT_CNL = "CNL";
    private AppletSpice paraApplet;
    private JTextField textInput;
    private JCheckBox showBusy;
    
    public StatusInput(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        this.buildUI();
        this.setDefaultCloseOperation(1);
        this.setSize(380, 220);
        WindowUtil.center(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.paraApplet.paraConf.printer().print("command=" + actionCommand);
        if ("K".equals(actionCommand)) {
            this.setVisible(false);
            final String text = this.textInput.getText();
            this.paraApplet.mainChat.statusChanged(this.showBusy.isSelected());
            this.paraApplet.chatModel.cmStatus(text, this.showBusy.isSelected());
            return;
        }
        if ("CNL".equals(actionCommand)) {
            this.setVisible(false);
        }
    }
    
    private void buildUI() {
        (this.textInput = new JTextField(32)).setMaximumSize(new Dimension(320, 42));
        this.showBusy = new JCheckBox(this.paraApplet.paraConf.get("Lb.Busy", "Show Busy"), false);
        final JComponent buttons = this.getButtons();
        this.textInput.setAlignmentX(0.5f);
        this.showBusy.setAlignmentX(0.5f);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), 1));
        this.getContentPane().add(Box.createRigidArea(new Dimension(3, 16)));
        this.getContentPane().add(this.textInput);
        this.getContentPane().add(Box.createRigidArea(new Dimension(3, 3)));
        this.getContentPane().add(this.showBusy);
        this.getContentPane().add(Box.createRigidArea(new Dimension(9, 9)));
        this.getContentPane().add(buttons);
    }
    
    private JComponent getButtons() {
        final JButton button = new JButton(this.paraApplet.BTN_OK);
        button.addActionListener(this);
        button.setActionCommand("K");
        final JButton button2 = new JButton(this.paraApplet.BTN_CANCEL);
        button2.addActionListener(this);
        button2.setActionCommand("CNL");
        final JPanel panel = new JPanel(new FlowLayout(1));
        panel.add(button);
        panel.add(button2);
        return panel;
    }
}
