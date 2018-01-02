// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.JMenuItem;
import javax.swing.Icon;
import java.awt.Image;
import javax.swing.ImageIcon;
import pclient.shd.Config;
import com.pchat.sc.StringUtil;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionListener;

public class PopupVideo implements ActionListener
{
    public JPopupMenu popMenu;
    private String ACT_AV;
    private String ACT_NEW;
    private AppletSpice paraApplet;
    private StatusInput statusInput;
    protected JCheckBoxMenuItem showVideoCheck;
    
    public PopupVideo(final AppletSpice para) {
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
        if (StringUtil.isEmpty(actionCommand)) {
            return;
        }
        if (actionCommand.equals("Mn.Bcast") || actionCommand.equals("Mn.Bcast.Vid")) {
            this.paraApplet.chatModel.cmAvBcast();
        }
        else if (actionCommand.equals("Mn.VwQ") || actionCommand.equals("Mn.VwQ.Vid")) {
            this.paraApplet.vwAvViewPermit(null, null);
        }
        else if (actionCommand.equals("Mn.ShowVid") || actionCommand.equals("Mn.ShowVid.Vid")) {
            final boolean selected = this.showVideoCheck.isSelected();
            this.paraApplet.globalChoice.haveVideo = selected;
            this.paraApplet.chatModel.cmAvShowVid(selected);
            this.paraApplet.mainChat.topMenu.checkShowVideo.setState(selected);
        }
    }
    
    private void buildGUI() {
        final JPopupMenu popMenu = new JPopupMenu();
        this.showVideoCheck = createVideoItems(popMenu, this.paraApplet.paraConf, this);
        this.popMenu = popMenu;
    }
    
    private static JCheckBoxMenuItem createVideoItems(final JPopupMenu popupMenu, final Config config, final ActionListener actionListener) {
        Image image = null;
        if (!config.isSimpleCSR()) {
            if (config.isAudioOnly()) {
                image = config.getIcon("showaud.png");
            }
            else {
                image = config.getIcon("showvid.png");
            }
        }
        Icon icon = null;
        if (image != null) {
            icon = new ImageIcon(image);
        }
        JMenuItem menuItem;
        if (config.isAudioOnly()) {
            menuItem = createItem("Mn.Bcast", TopMenu.TXT_AV_CAST, popupMenu, TopMenu.DEF_ON, config, actionListener);
        }
        else {
            menuItem = createItem("Mn.Bcast.Vid", TopMenu.TXT_AV_CAST_VID, popupMenu, TopMenu.DEF_ON, config, actionListener);
        }
        menuItem.setIcon(icon);
        if (!config.getBool("Ctrl.Av", false)) {
            menuItem.setEnabled(false);
        }
        Image icon2 = null;
        if (!config.isSimpleCSR()) {
            icon2 = config.getIcon("showvq.png");
        }
        Icon icon3 = null;
        if (icon2 != null) {
            icon3 = new ImageIcon(icon2);
        }
        JMenuItem menuItem2;
        if (config.isAudioOnly()) {
            menuItem2 = createItem("Mn.VwQ", TopMenu.TXT_AV_Q, popupMenu, TopMenu.DEF_ON, config, actionListener);
        }
        else {
            menuItem2 = createItem("Mn.VwQ.Vid", TopMenu.TXT_AV_Q_VID, popupMenu, TopMenu.DEF_ON, config, actionListener);
        }
        menuItem2.setIcon(icon3);
        if (!config.getBool("Ctrl.Av", false)) {
            menuItem2.setEnabled(false);
        }
        popupMenu.addSeparator();
        JCheckBoxMenuItem checkBoxMenuItem;
        if (config.isAudioOnly()) {
            checkBoxMenuItem = createCheck("Mn.ShowVid", TopMenu.TXT_AV_SHOW, popupMenu, TopMenu.DEF_ON, config, actionListener);
        }
        else {
            checkBoxMenuItem = createCheck("Mn.ShowVid.Vid", TopMenu.TXT_AV_SHOW_VID, popupMenu, TopMenu.DEF_ON, config, actionListener);
        }
        if (!config.getBool("Ctrl.Av", false)) {
            checkBoxMenuItem.setEnabled(false);
        }
        return checkBoxMenuItem;
    }
    
    private static JMenuItem createItem(final String actionCommand, final String s, final JPopupMenu popupMenu, final String s2, final Config config, final ActionListener actionListener) {
        final JMenuItem menuItem = new JMenuItem(config.get(actionCommand, s));
        menuItem.setActionCommand(actionCommand);
        menuItem.addActionListener(actionListener);
        if (config.getBool(s2, true)) {
            popupMenu.add(menuItem);
        }
        return menuItem;
    }
    
    private static JCheckBoxMenuItem createCheck(final String actionCommand, final String s, final JPopupMenu popupMenu, final String s2, final Config config, final ActionListener actionListener) {
        final JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem(config.get(actionCommand, s));
        checkBoxMenuItem.setActionCommand(actionCommand);
        checkBoxMenuItem.addActionListener(actionListener);
        if (config.getBool(s2, true)) {
            popupMenu.add(checkBoxMenuItem);
        }
        return checkBoxMenuItem;
    }
}
