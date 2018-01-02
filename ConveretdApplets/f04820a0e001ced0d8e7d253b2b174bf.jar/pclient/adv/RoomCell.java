// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.awt.Color;
import pclient.shd.RoomItem;
import javax.swing.Icon;
import java.awt.Component;
import javax.swing.JList;
import com.pchat.sc.WindowUtil;
import javax.swing.BorderFactory;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;

public class RoomCell extends JLabel implements ListCellRenderer
{
    private AppletSpice appletChat;
    private RegRoomList parentList;
    
    public RoomCell(final AppletSpice appletChat, final RegRoomList parentList) {
        this.appletChat = appletChat;
        this.parentList = parentList;
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 1));
        final int int1 = this.appletChat.paraConf.getInt("Sz.User.Font", -1);
        if (int1 > 0) {
            this.setFont(WindowUtil.changeSize(this.getFont(), int1));
        }
    }
    
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        final String s = (String)o;
        if (s == null) {
            System.out.println("Err P85902.");
            return this;
        }
        final RoomItem lookupRoom = this.appletChat.lookupRoom(s);
        if (lookupRoom == null) {
            System.out.println("alert P88273." + s);
            this.setText(s);
            this.setToolTipText(s);
            this.parentList.checkFixTree();
            return this;
        }
        if (lookupRoom.locked) {
            this.setIcon(this.appletChat.getLock());
        }
        else {
            this.setIcon(null);
        }
        final String encodeSimple = lookupRoom.encodeSimple();
        this.setText(encodeSimple);
        this.setToolTipText(encodeSimple);
        Color background = list.getBackground();
        Color foreground = list.getForeground();
        if (RoomNames.isCurrentRoom(s, this.appletChat)) {
            foreground = this.appletChat.paraConf.getPref().selfColor;
        }
        if (b) {
            final Color color = foreground;
            foreground = background;
            background = color;
        }
        this.setForeground(foreground);
        this.setBackground(background);
        return this;
    }
}
