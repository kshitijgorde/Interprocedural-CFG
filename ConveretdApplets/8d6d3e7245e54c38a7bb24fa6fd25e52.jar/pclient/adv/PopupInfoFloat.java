// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import javax.swing.JOptionPane;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class PopupInfoFloat extends JFrame implements ActionListener, WindowListener
{
    private AppletSpice appletChat;
    private int numCounter;
    private JOptionPane optionPane;
    
    public PopupInfoFloat(final AppletSpice para) {
        this.numCounter = 0;
        this.setPara(para);
    }
    
    private void setPara(final AppletSpice appletChat) {
        this.appletChat = appletChat;
        this.setTitle(this.appletChat.paraConf.title());
        this.setSize(260, 180);
        this.addWindowListener(this);
        this.setDefaultCloseOperation(0);
        this.buildGUI();
        try {
            WindowUtil.center(this.appletChat.paraConf.getApplet(), this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void resetCount() {
        this.numCounter = 0;
    }
    
    public void increment() {
        ++this.numCounter;
    }
    
    public int getCount() {
        return this.numCounter;
    }
    
    public void setMsg(final String message) {
        this.optionPane.setMessage(message);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.appletChat.BTN_OK.equals(actionEvent.getActionCommand())) {
            this.closeWin();
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.closeWin();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    private void closeWin() {
        this.setVisible(false);
        this.resetCount();
    }
    
    private void buildGUI() {
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.getOption(), "Center");
    }
    
    private JComponent getOption() {
        final JButton button = new JButton(this.appletChat.BTN_OK);
        button.setActionCommand(this.appletChat.BTN_OK);
        button.addActionListener(this);
        return this.optionPane = new JOptionPane("", 1, 2, null, new Object[] { button }, null);
    }
}
