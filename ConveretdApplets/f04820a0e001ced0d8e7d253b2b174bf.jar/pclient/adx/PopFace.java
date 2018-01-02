// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.SwingUtilities;
import com.pchat.sc.StringUtil;
import java.awt.event.ActionEvent;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import javax.swing.JButton;
import javax.swing.JPanel;
import pclient.adv.AppletSpice;
import java.awt.event.ActionListener;
import pclient.adv.ComInter;
import javax.swing.JFrame;

public class PopFace extends JFrame implements ComInter, ActionListener, Runnable
{
    private AppletSpice paraApplet;
    private JPanel iconPanel;
    private String[] nameArray;
    private JButton[] allButtons;
    
    public void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        this.setTitle(this.paraApplet.paraConf.title());
        this.setSize(520, 460);
        this.setDefaultCloseOperation(1);
        WindowUtil.center(this);
        this.buildGUI();
        new Thread(this).start();
    }
    
    public void process(final int n, final String[] array) {
    }
    
    public void restart() {
        this.setVisible(true);
    }
    
    public void destroy() {
        this.setVisible(false);
        this.dispose();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (StringUtil.isEmpty(actionCommand)) {
            return;
        }
        this.setVisible(false);
        this.paraApplet.userAvatar(actionCommand);
    }
    
    public void run() {
        for (int i = 0; i < this.allButtons.length; ++i) {
            SwingUtilities.invokeLater(new PopFaceJob(this, this.allButtons[i], this.paraApplet.paraConf.getAvatar(this.nameArray[i])));
        }
    }
    
    protected void changeButton(final JButton button, final Image image) {
        Icon icon = null;
        if (image != null) {
            icon = new ImageIcon(image);
        }
        button.setIcon(icon);
    }
    
    private void buildGUI() {
        this.getContentPane().setLayout(new BorderLayout());
        final JPanel iconPanel = new JPanel();
        final int n = 6;
        String s = this.paraApplet.paraConf.get("Cf.Avatar");
        if (s == null) {
            s = "";
        }
        if (StringUtil.isTrimmedEmpty(s)) {
            s = this.paraApplet.paraConf.serverConf("Cf.Avatar", "");
        }
        final String[] splitString = StringUtil.splitString(s, ",", true);
        final int length = splitString.length;
        if (length != 0) {
            int n2 = length / n;
            if (length % n != 0) {
                ++n2;
            }
            iconPanel.setLayout(new GridLayout(n2, n, 5, 5));
        }
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(1));
        panel.add(iconPanel);
        this.addAllButtons(iconPanel, splitString);
        final boolean bool = this.paraApplet.paraConf.getBool("Xtn.Ava.Scrl", true);
        JComponent component = null;
        if (bool) {
            component = new JScrollPane(panel, 20, 30);
            component.setMinimumSize(new Dimension(30, 60));
            component.setOpaque(true);
        }
        this.getContentPane().add("North", new JLabel(""));
        if (bool) {
            this.getContentPane().add("Center", component);
        }
        else {
            this.getContentPane().add("Center", panel);
        }
        this.iconPanel = iconPanel;
        this.nameArray = splitString;
    }
    
    private void addAllButtons(final JPanel panel, final String[] array) {
        final int length = array.length;
        this.allButtons = new JButton[length];
        for (int i = 0; i < length; ++i) {
            final JButton button = this.createButton(array[i]);
            panel.add(button);
            this.allButtons[i] = button;
        }
    }
    
    private JButton createButton(final String actionCommand) {
        final JButton button = new JButton("");
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        button.setRolloverEnabled(true);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }
}
