// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.JMenuItem;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import pclient.shd.Config;
import javax.swing.JPopupMenu;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JTextArea;

public class TextAreaCopy extends JTextArea implements MouseListener, ActionListener
{
    private static final String ACT_CP = "cp";
    private static final String ACT_CUT = "ct";
    private static final String ACT_PS = "ps";
    private JPopupMenu rightPop;
    private Config paraConf;
    
    public TextAreaCopy(final Config paraConf) {
        this.rightPop = null;
        this.paraConf = null;
        this.paraConf = paraConf;
        this.addMouseListener(this);
        this.createPop();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.paraConf.printer().print("com=" + actionCommand);
        if ("cp".equals(actionCommand)) {
            this.copy();
        }
        else if ("ct".equals(actionCommand)) {
            this.cut();
        }
        else if ("ps".equals(actionCommand)) {
            this.paste();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.maybeShowPopup(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.maybeShowPopup(mouseEvent);
    }
    
    private void createPop() {
        final JPopupMenu rightPop = new JPopupMenu();
        String value = "Cut";
        if (this.paraConf != null) {
            value = this.paraConf.get("Pm.Cut", value);
        }
        final JMenuItem menuItem = new JMenuItem(value);
        menuItem.addActionListener(this);
        menuItem.setActionCommand("ct");
        rightPop.add(menuItem);
        String value2 = "Copy";
        if (this.paraConf != null) {
            value2 = this.paraConf.get("Pm.Copy", value2);
        }
        final JMenuItem menuItem2 = new JMenuItem(value2);
        menuItem2.addActionListener(this);
        menuItem2.setActionCommand("cp");
        rightPop.add(menuItem2);
        String value3 = "Paste";
        if (this.paraConf != null) {
            value3 = this.paraConf.get("Pm.Paste", value3);
        }
        final JMenuItem menuItem3 = new JMenuItem(value3);
        menuItem3.addActionListener(this);
        menuItem3.setActionCommand("ps");
        rightPop.add(menuItem3);
        this.rightPop = rightPop;
    }
    
    private void maybeShowPopup(final MouseEvent mouseEvent) {
        if (!this.paraConf.getBool("Op.RightClick", false)) {
            return;
        }
        if (this.rightPop == null) {
            this.createPop();
        }
        if (mouseEvent.isPopupTrigger()) {
            this.rightPop.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
}
