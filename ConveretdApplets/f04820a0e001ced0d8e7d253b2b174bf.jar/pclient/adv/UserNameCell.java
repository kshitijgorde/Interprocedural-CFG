// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.image.ImageObserver;
import com.pchat.sc.StringUtil;
import com.pchat.sc.GenericResponse;
import javax.swing.Icon;
import java.awt.Color;
import pclient.shd.UserAttr;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JList;
import com.pchat.sc.WindowUtil;
import javax.swing.BorderFactory;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;

public class UserNameCell extends JLabel implements ListCellRenderer
{
    private LoginNames userList;
    private JLabel dummyLabel;
    
    public UserNameCell(final LoginNames userList) {
        this.dummyLabel = new JLabel("U");
        this.userList = userList;
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 1));
        final int int1 = this.userList.appletChat.paraConf.getInt("Sz.User.Font", -1);
        if (int1 > 0) {
            this.setFont(WindowUtil.changeSize(this.getFont(), int1));
            this.dummyLabel.setFont(WindowUtil.changeSize(this.dummyLabel.getFont(), int1));
        }
    }
    
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        final CellData cellData = (CellData)o;
        if (cellData == null) {
            System.out.println("Err 8273");
            return this;
        }
        final UserAttr cmUserAttr = this.userList.appletChat.chatModel.cmUserAttr(cellData.username);
        final String setDisplayName = setDisplayName(cellData.username, cmUserAttr);
        this.userList.appletChat.paraConf.printer().print("inv=" + setInvisible(this, this.userList.appletChat, cellData.username) + " " + cellData.username);
        this.setIcon(cellData.icon = this.createIcon(list, cellData, cmUserAttr));
        this.userList.appletChat.paraConf.printer().print("icon=" + cellData.icon);
        if (cellData.icon != null && this.userList.appletChat.isAvatar()) {
            final int iconHeight = cellData.icon.getIconHeight();
            this.userList.appletChat.paraConf.printer().print("icon H=" + iconHeight);
            if (iconHeight > this.userList.listHeight) {
                this.setPreferredSize(new Dimension(320, iconHeight));
            }
        }
        this.userList.appletChat.paraConf.printer().print("cell=" + setDisplayName);
        this.setText(setDisplayName);
        this.setToolTipText(setDisplayName);
        this.setEnabled(!this.userList.appletChat.chatModel.cmIsIgnored(cellData.username));
        Color background = list.getBackground();
        Color foreground = list.getForeground();
        if (cmUserAttr != null) {
            if (cmUserAttr.background != null) {
                background = cmUserAttr.background;
            }
            if (cmUserAttr.foreground != null) {
                foreground = cmUserAttr.foreground;
            }
        }
        if (b) {
            final Color color = foreground;
            foreground = background;
            background = color;
        }
        this.setBackground(background);
        this.setForeground(foreground);
        this.userList.appletChat.paraConf.printer().print("col=" + foreground + cellData.username);
        this.userList.appletChat.paraConf.printer().print("cell prefer=" + this.getPreferredSize());
        this.userList.appletChat.paraConf.printer().print("list=" + this.userList.nameList.getFixedCellHeight());
        this.checkPrefer(cellData.icon != null);
        this.userList.appletChat.paraConf.printer().print("after cell prefer=" + this.getPreferredSize());
        this.userList.appletChat.paraConf.printer().print("list=" + this.userList.nameList.getFixedCellHeight());
        return this;
    }
    
    private void checkPrefer(final boolean b) {
        Dimension preferredSize = this.dummyLabel.getPreferredSize();
        if (preferredSize.height < this.userList.listHeight && this.userList.appletChat.isAvatar()) {
            preferredSize = new Dimension(120, this.userList.listHeight);
        }
        this.userList.appletChat.paraConf.printer().print("listH=" + this.userList.listHeight);
        this.userList.appletChat.paraConf.printer().print("min=" + preferredSize);
        if (!b || !this.userList.appletChat.isAvatar()) {
            this.setPreferredSize(preferredSize);
            return;
        }
        if (this.getPreferredSize().height < preferredSize.height) {
            this.setPreferredSize(preferredSize);
        }
    }
    
    private Icon createIcon(final JList list, final CellData cellData, final UserAttr userAttr) {
        if (userAttr == null) {
            System.err.println("err94328," + cellData);
            return null;
        }
        if (!cellData.dirty) {
            return cellData.icon;
        }
        cellData.dirty = false;
        final GenericResponse generateIcon = generateIcon(list, userAttr, this.userList.appletChat, -1, -1);
        if (!generateIcon.successful) {
            cellData.dirty = true;
            System.out.println("err5498." + cellData);
        }
        return cellData.icon = (Icon)generateIcon.handle;
    }
    
    public static String setDisplayName(String string, final UserAttr userAttr) {
        if (userAttr != null && !StringUtil.isEmpty(userAttr.status)) {
            string += userAttr.encodeStatus();
        }
        return string;
    }
    
    public static boolean setInvisible(final Component component, final AppletSpice appletSpice, final String s) {
        final boolean cmIsInvisible = appletSpice.chatModel.cmIsInvisible(s);
        if (cmIsInvisible) {
            WindowUtil.changeStyle(component, true, true);
        }
        else {
            WindowUtil.changeStyle(component, true, false);
        }
        return cmIsInvisible;
    }
    
    public static GenericResponse generateIcon(final Component component, final UserAttr userAttr, final AppletSpice appletSpice, final int n, final int n2) {
        final GenericResponse genericResponse = new GenericResponse();
        genericResponse.successful = false;
        genericResponse.handle = null;
        Image showVideo = null;
        Image busy = null;
        Image avatar = null;
        int n3 = 0;
        int n4 = 0;
        if (!StringUtil.isEmpty(userAttr.avatar) && appletSpice.isAvatar()) {
            avatar = appletSpice.paraConf.getAvatar(userAttr.avatar);
            if (n > 0 && n2 > 0 && avatar != null) {
                final Image image = component.createImage(n, n2);
                image.getGraphics().drawImage(avatar, 0, 0, n, n2, component.getBackground(), null);
                avatar = image;
            }
        }
        if (userAttr.busy) {
            busy = appletSpice.getBusy();
        }
        if (userAttr.showVid) {
            showVideo = appletSpice.getShowVideo();
        }
        if (showVideo != null) {
            n3 += showVideo.getWidth(null);
            n4 = compHeight(n4, showVideo.getHeight(null));
        }
        if (busy != null) {
            n3 += busy.getWidth(null);
            n4 = compHeight(n4, busy.getHeight(null));
        }
        if (avatar != null) {
            n3 += avatar.getWidth(null);
            n4 = compHeight(n4, avatar.getHeight(null));
        }
        appletSpice.paraConf.printer().print("total w=" + n3 + " h=" + n4);
        if (n3 == 0 || n4 == 0) {
            genericResponse.successful = true;
            genericResponse.handle = null;
            return genericResponse;
        }
        final int n5 = n3 + 0;
        final Image image2 = component.createImage(n5, n4);
        if (image2 == null) {
            System.out.println("err549=" + userAttr);
            genericResponse.successful = false;
            genericResponse.handle = null;
            return genericResponse;
        }
        final Graphics graphics = image2.getGraphics();
        graphics.setColor(component.getBackground());
        graphics.fillRect(0, 0, n5, n4);
        int n6 = 0;
        if (showVideo != null) {
            graphics.drawImage(showVideo, n6, getUpperY(showVideo, n4), null);
            n6 += showVideo.getWidth(null);
        }
        if (busy != null) {
            graphics.drawImage(busy, n6, getUpperY(busy, n4), null);
            n6 += busy.getWidth(null);
        }
        if (avatar != null) {
            graphics.drawImage(avatar, n6, 0, null);
            final int n7 = n6 + avatar.getWidth(null);
        }
        genericResponse.successful = true;
        genericResponse.handle = new ImageIcon(image2);
        return genericResponse;
    }
    
    private static int getUpperY(final Image image, final int n) {
        return (n - image.getHeight(null)) / 2;
    }
    
    private static int compHeight(final int n, final int n2) {
        if (n2 > n) {
            return n2;
        }
        return n;
    }
}
