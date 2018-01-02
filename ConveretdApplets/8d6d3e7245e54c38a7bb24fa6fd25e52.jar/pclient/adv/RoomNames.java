// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionListener;
import pclient.shd.RoomItem;
import javax.swing.JComponent;

public class RoomNames
{
    public static final String A_JN = "jn";
    public static final String A_RF = "Rf";
    private JComponent mainComp;
    protected RegRoomList roamingList;
    protected AppletSpice appletChat;
    
    public RoomNames(final AppletSpice appletChat) {
        this.mainComp = null;
        this.appletChat = appletChat;
        this.buildUI();
    }
    
    public static boolean isCurrentRoom(String simpleForm, final AppletSpice appletSpice) {
        final String cmRoomName = appletSpice.chatModel.cmRoomName();
        if (cmRoomName == null) {
            return false;
        }
        simpleForm = RoomItem.simpleForm(simpleForm);
        return simpleForm.equals(cmRoomName);
    }
    
    public static boolean roomOnList(final String s, final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (RoomItem.sameRoom(s, array[i])) {
                return true;
            }
        }
        return false;
    }
    
    public JComponent getBox() {
        return this.mainComp;
    }
    
    private void buildUI() {
        this.roamingList = new RegRoomList(this.appletChat);
        this.mainComp = this.roamingList.getBox();
    }
    
    public static JPopupMenu createPop(final ActionListener actionListener, final AppletSpice appletSpice) {
        final JPopupMenu popupMenu = new JPopupMenu();
        final JMenuItem menuItem = new JMenuItem(appletSpice.paraConf.get("Pm.Join", "Join Room"));
        menuItem.addActionListener(actionListener);
        menuItem.setActionCommand("jn");
        popupMenu.add(menuItem);
        final JMenuItem menuItem2 = new JMenuItem(appletSpice.paraConf.get("Pm.Refresh", "Refresh"));
        menuItem2.addActionListener(actionListener);
        menuItem2.setActionCommand("Rf");
        popupMenu.add(menuItem2);
        return popupMenu;
    }
}
