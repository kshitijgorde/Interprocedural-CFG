// 
// Decompiled by Procyon v0.5.30
// 

package pclient.ady;

import com.pchat.sc.GenericResponse;
import java.awt.Color;
import java.awt.Dimension;
import pclient.shd.UserAttr;
import pclient.adv.UserNameCell;
import pclient.shd.RoomItem;
import javax.swing.JComponent;
import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Component;
import javax.swing.JTree;
import pclient.adv.AppletSpice;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.JLabel;

public class TrCellRend extends JLabel implements TreeCellRenderer
{
    private AppletSpice appletChat;
    private TrStructView treeView;
    private JLabel dummyLabel;
    private int listHeight;
    private int avaWidth;
    private int avaHeight;
    
    public TrCellRend(final AppletSpice appletChat, final TrStructView treeView) {
        this.dummyLabel = new JLabel("U");
        this.listHeight = 12;
        this.avaWidth = 16;
        this.avaHeight = 16;
        this.appletChat = appletChat;
        this.treeView = treeView;
        this.setOpaque(true);
        this.listHeight = this.appletChat.paraConf.getInt("Sz.UserH", 0);
        if (this.listHeight <= 0) {
            this.listHeight = 12;
        }
        this.avaWidth = this.appletChat.paraConf.getInt("Sz.Tr.AvW", 0);
        if (this.avaWidth <= 0) {
            this.avaWidth = 16;
        }
        this.avaHeight = this.appletChat.paraConf.getInt("Sz.Tr.AvH", 0);
        if (this.avaHeight <= 0) {
            this.avaHeight = 16;
        }
    }
    
    public Component getTreeCellRendererComponent(final JTree tree, final Object o, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)o;
        final TrCellData trCellData = (TrCellData)defaultMutableTreeNode.getUserObject();
        this.setIcon(null);
        this.setColorsDefault(tree, b);
        JComponent component = this;
        if (trCellData.type == TrCellData.T_ROOT) {
            this.setText(trCellData.name);
            this.setToolTipText(trCellData.name);
            return component;
        }
        if (trCellData.type == TrCellData.T_ROOM) {
            if (trCellData.name.equals(this.appletChat.chatModel.cmRoomName())) {
                component = this.getCellForCurrentRoom(defaultMutableTreeNode, tree, b, b2, b3, n, b4);
            }
            else {
                component = this.getCellForRoamRoom(defaultMutableTreeNode, tree, b, b2, b3, n, b4);
            }
        }
        if (trCellData.type == TrCellData.T_USER) {
            if (this.treeView.isUserInCurrentRoom(defaultMutableTreeNode)) {
                component = this.getCellForCurrentUser(defaultMutableTreeNode, tree, b, b2, b3, n, b4);
            }
            else {
                component = this.getCellForRoamUser(defaultMutableTreeNode, tree, b, b2, b3, n, b4);
            }
        }
        return component;
    }
    
    private JComponent getCellForCurrentRoom(final DefaultMutableTreeNode defaultMutableTreeNode, final JTree tree, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
        final TrCellData trCellData = (TrCellData)defaultMutableTreeNode.getUserObject();
        final String name = trCellData.name;
        this.setIcon(null);
        final RoomItem lookupRoom = this.appletChat.lookupRoom(name);
        if (lookupRoom == null) {
            this.appletChat.paraConf.printer().print("no item for  " + name);
            this.setText(name);
            this.setToolTipText(name);
            this.setColorsRoom(true, tree, this.appletChat, b);
            return this;
        }
        this.appletChat.paraConf.printer().print("cur room= " + trCellData.name + " " + lookupRoom);
        if (lookupRoom.locked) {
            this.setIcon(this.appletChat.getLock());
        }
        final String encodeSimple = lookupRoom.encodeSimple();
        this.setText(encodeSimple);
        this.setToolTipText(encodeSimple);
        this.setColorsRoom(true, tree, this.appletChat, b);
        return this;
    }
    
    private JComponent getCellForRoamRoom(final DefaultMutableTreeNode defaultMutableTreeNode, final JTree tree, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
        final TrCellData trCellData = (TrCellData)defaultMutableTreeNode.getUserObject();
        final String name = trCellData.name;
        this.setIcon(null);
        final RoomItem lookupRoom = this.appletChat.lookupRoom(name);
        if (lookupRoom == null) {
            this.setText(name);
            this.setToolTipText(name);
            this.setColorsRoom(false, tree, this.appletChat, b);
            this.treeView.checkFixTree();
            return this;
        }
        this.appletChat.paraConf.printer().print("roam= " + trCellData.name + " " + lookupRoom);
        if (lookupRoom.locked) {
            this.setIcon(this.appletChat.getLock());
        }
        final String encodeSimple = lookupRoom.encodeSimple();
        this.setText(encodeSimple);
        this.setToolTipText(encodeSimple);
        this.setColorsRoom(false, tree, this.appletChat, b);
        return this;
    }
    
    private JComponent getCellForCurrentUser(final DefaultMutableTreeNode defaultMutableTreeNode, final JTree tree, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
        final TrCellData trCellData = (TrCellData)defaultMutableTreeNode.getUserObject();
        if (trCellData == null) {
            System.out.println("Err 86836");
            return this;
        }
        final UserAttr cmUserAttr = this.appletChat.chatModel.cmUserAttr(trCellData.name);
        final String setDisplayName = UserNameCell.setDisplayName(trCellData.name, cmUserAttr);
        this.appletChat.paraConf.printer().print("Inv=" + UserNameCell.setInvisible(this, this.appletChat, trCellData.name) + " " + trCellData.name);
        this.setColorsUser(cmUserAttr, tree, b);
        this.setIcon(trCellData.icon = this.createIcon(tree, trCellData, cmUserAttr, this.getBackground()));
        this.appletChat.paraConf.printer().print("cur icon=" + trCellData.icon);
        this.appletChat.paraConf.printer().print("cur txt=" + setDisplayName);
        this.setText(setDisplayName);
        this.setToolTipText(setDisplayName);
        this.setEnabled(!this.appletChat.chatModel.cmIsIgnored(trCellData.name));
        this.appletChat.paraConf.printer().print("data=" + trCellData.name);
        this.appletChat.paraConf.printer().print("cell prefer=" + this.getPreferredSize());
        return this;
    }
    
    private JComponent getCellForRoamUser(final DefaultMutableTreeNode defaultMutableTreeNode, final JTree tree, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
        final TrCellData trCellData = (TrCellData)defaultMutableTreeNode.getUserObject();
        this.setText(trCellData.name);
        this.setToolTipText(trCellData.name);
        return this;
    }
    
    public Dimension getPreferredSize() {
        if (!this.appletChat.isAvatar()) {
            return new Dimension(560, 16);
        }
        return new Dimension(560, this.avaHeight);
    }
    
    private void setColorsDefault(final JTree tree, final boolean b) {
        Color background = tree.getBackground();
        Color foreground = tree.getForeground();
        if (b) {
            final Color color = foreground;
            foreground = background;
            background = color;
        }
        this.setBackground(background);
        this.setForeground(foreground);
    }
    
    private void setColorsUser(final UserAttr userAttr, final JTree tree, final boolean b) {
        Color background = tree.getBackground();
        Color foreground = tree.getForeground();
        if (userAttr != null) {
            if (userAttr.background != null) {
                background = userAttr.background;
            }
            if (userAttr.foreground != null) {
                foreground = userAttr.foreground;
            }
        }
        if (b) {
            final Color color = foreground;
            foreground = background;
            background = color;
        }
        this.setBackground(background);
        this.setForeground(foreground);
    }
    
    private void setColorsRoom(final boolean b, final JTree tree, final AppletSpice appletSpice, final boolean b2) {
        Color background = tree.getBackground();
        Color foreground = tree.getForeground();
        if (b) {
            foreground = appletSpice.paraConf.getPref().selfColor;
        }
        if (b2) {
            final Color color = foreground;
            foreground = background;
            background = color;
        }
        this.setForeground(foreground);
        this.setBackground(background);
    }
    
    private void checkPrefer(final boolean b) {
        Dimension preferredSize = this.dummyLabel.getPreferredSize();
        if (preferredSize.height < this.avaHeight && this.appletChat.isAvatar()) {
            preferredSize = new Dimension(320, this.avaHeight);
        }
        this.appletChat.paraConf.printer().print("ListH=" + this.listHeight);
        this.appletChat.paraConf.printer().print("Min=" + preferredSize);
        if (!b || !this.appletChat.isAvatar()) {
            this.setPreferredSize(preferredSize);
            return;
        }
        if (this.getPreferredSize().height < preferredSize.height) {
            this.setPreferredSize(preferredSize);
        }
    }
    
    private Icon createIcon(final JTree tree, final TrCellData trCellData, final UserAttr userAttr, final Color color) {
        if (userAttr == null) {
            System.err.println("err7826," + trCellData);
            return null;
        }
        if (!trCellData.dirty) {
            return trCellData.icon;
        }
        trCellData.dirty = false;
        final GenericResponse generateIcon = UserNameCell.generateIcon(tree, userAttr, this.appletChat, this.avaWidth, this.avaHeight);
        if (!generateIcon.successful) {
            trCellData.dirty = true;
            System.out.println("err6598." + trCellData);
        }
        return trCellData.icon = (Icon)generateIcon.handle;
    }
}
