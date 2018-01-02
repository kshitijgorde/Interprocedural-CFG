// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.Icon;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import java.awt.Frame;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class PrivSmile extends JDialog implements ActionListener
{
    private PrivWindow privateChat;
    
    public PrivSmile(final PrivWindow privateChat) {
        super(privateChat, true);
        this.privateChat = privateChat;
        this.setTitle(this.privateChat.getConf().title());
        this.setSize(320, 280);
        this.setDefaultCloseOperation(1);
        this.buildGUI();
        WindowUtil.center(this);
        this.pack();
    }
    
    public void restart() {
        this.setVisible(true);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.privateChat.getConf().printer().print("command=" + actionCommand);
        if (actionCommand == null) {
            return;
        }
        this.setVisible(false);
        this.privateChat.textInput.insert(" ::" + actionCommand + " ", this.privateChat.textInput.getCaretPosition());
    }
    
    private void buildGUI() {
        this.getContentPane().setLayout(new BorderLayout());
        final JPanel buildGrid = this.buildGrid();
        this.getContentPane().add("North", new JLabel(" "));
        this.getContentPane().add("Center", buildGrid);
    }
    
    private JPanel buildGrid() {
        final int n = 6;
        final String[] names = this.privateChat.getConf().getSmiley().getNames();
        final int length = names.length;
        int n2 = length / n;
        if (length % n != 0) {
            ++n2;
        }
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(n2, n, 5, 5));
        for (int i = 0; i < length; ++i) {
            panel.add(this.getButton(names[i]));
        }
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(1));
        panel2.add(panel);
        return panel2;
    }
    
    private JButton getButton(final String actionCommand) {
        final Image image = this.privateChat.getConf().getSmiley().getImage(actionCommand);
        Icon icon = null;
        if (image != null) {
            icon = new ImageIcon(image);
        }
        final JButton button = new JButton(" ");
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        button.setRolloverEnabled(true);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }
}
