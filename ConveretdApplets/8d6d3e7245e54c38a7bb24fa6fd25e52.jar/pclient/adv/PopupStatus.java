// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.util.StringTokenizer;
import javax.swing.JMenuItem;
import com.pchat.sc.StringUtil;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.event.ActionListener;

public class PopupStatus implements ActionListener
{
    public JPopupMenu popMenu;
    private String ACT_AV;
    private String ACT_NEW;
    private AppletSpice paraApplet;
    private StatusInput statusInput;
    
    public PopupStatus(final AppletSpice para) {
        this.ACT_AV = "";
        this.ACT_NEW = "___";
        this.statusInput = null;
        this.setPara(para);
    }
    
    private void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        this.buildGUI();
        this.popMenu.pack();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (this.ACT_AV.equals(actionCommand)) {
            this.paraApplet.mainChat.statusChanged(false);
            this.paraApplet.chatModel.cmStatus(this.ACT_AV, false);
            return;
        }
        if (this.ACT_NEW.equals(actionCommand)) {
            if (this.statusInput == null) {
                this.statusInput = new StatusInput(this.paraApplet);
            }
            this.statusInput.setVisible(true);
            return;
        }
        if (!StringUtil.isEmpty(actionCommand)) {
            this.paraApplet.mainChat.statusChanged(true);
            this.paraApplet.chatModel.cmStatus(actionCommand, true);
        }
    }
    
    private void buildGUI() {
        final JPopupMenu popMenu = new JPopupMenu();
        final JMenuItem menuItem = new JMenuItem(this.paraApplet.paraConf.get("Pm.Available", "Available"));
        menuItem.addActionListener(this);
        menuItem.setActionCommand(this.ACT_AV);
        popMenu.add(menuItem);
        popMenu.addSeparator();
        final StringTokenizer stringTokenizer = new StringTokenizer(this.paraApplet.paraConf.get("Cf.Status", ""), ",");
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            this.paraApplet.paraConf.printer().print("status=" + trim);
            final JMenuItem menuItem2 = new JMenuItem(trim);
            menuItem2.addActionListener(this);
            menuItem2.setActionCommand(trim);
            popMenu.add(menuItem2);
        }
        popMenu.addSeparator();
        final JMenuItem menuItem3 = new JMenuItem(this.paraApplet.paraConf.get("Pm.NewStatus", "New Status Message"));
        menuItem3.addActionListener(this);
        menuItem3.setActionCommand(this.ACT_NEW);
        popMenu.add(menuItem3);
        this.popMenu = popMenu;
    }
}
