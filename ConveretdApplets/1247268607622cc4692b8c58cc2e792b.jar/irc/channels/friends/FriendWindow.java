// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.friends;

import javax.swing.JMenuItem;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JPopupMenu;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreeNode;
import java.awt.Image;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.text.Position;
import javax.swing.tree.TreePath;
import irc.main;
import java.util.Enumeration;
import javax.swing.tree.TreeCellRenderer;
import irc.managers.Resources;
import irc.com.nick.NickInfos;
import javax.swing.Icon;
import java.awt.Color;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import irc.com.utils.MD5Nick;
import irc.com.utils.MySQL;
import irc.channels.textarea.NewTextDocument;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.AbstractButton;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.util.Comparator;
import javax.swing.tree.DefaultMutableTreeNode;
import irc.EIRC;
import javax.swing.event.TreeSelectionListener;
import java.awt.event.ActionListener;
import irc.channels.components.MyOpacity;

public class FriendWindow extends MyOpacity implements ActionListener, TreeSelectionListener
{
    private EIRC _eirc;
    private PopupJTree friendsTree;
    private DefaultMutableTreeNode nodeOnline;
    private DefaultMutableTreeNode nodeOffline;
    private Comparator nodeComparator;
    private ImageIcon girlOnline;
    private ImageIcon boyOnline;
    private ImageIcon useroffline;
    JPanel paneltree;
    
    public FriendWindow(final EIRC eirc) {
        this._eirc = null;
        this.friendsTree = null;
        this.nodeOnline = null;
        this.nodeOffline = null;
        this.nodeComparator = null;
        this.girlOnline = null;
        this.boyOnline = null;
        this.useroffline = null;
        this.paneltree = new JPanel(new BorderLayout());
        this._eirc = eirc;
        this.nodeComparator = TreeNodeComparatorFactory.getTreeNodeLexicographicComparator();
        this.setOpaque(false);
        this.init();
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this._eirc.revenir();
        if (actionEvent.getSource() instanceof AbstractButton) {
            if (actionEvent.getActionCommand().equals("button_add")) {
                this.addFriend();
            }
            if (actionEvent.getActionCommand().startsWith("block")) {
                this.blockFriend(actionEvent.getActionCommand().substring(actionEvent.getActionCommand().indexOf(" ")).trim());
            }
            if (actionEvent.getActionCommand().equals("delete")) {
                this.delFriend();
            }
            if (actionEvent.getActionCommand().equals("pv")) {
                this.openPrivate();
            }
        }
    }
    
    public void addFreindOffline(final String s) {
        DefaultMutableIconTreeNode defaultMutableIconTreeNode;
        if (this._eirc.getSex(s) == 1) {
            defaultMutableIconTreeNode = new DefaultMutableIconTreeNode(this.useroffline, s);
        }
        else {
            defaultMutableIconTreeNode = new DefaultMutableIconTreeNode(this.useroffline, s);
        }
        this.insertSorted(this.nodeOffline, defaultMutableIconTreeNode, (DefaultTreeModel)this.friendsTree.getModel());
        this.refreshNodeCounts();
    }
    
    public void addFreindOnline(final String s) {
        DefaultMutableIconTreeNode defaultMutableIconTreeNode;
        if (this._eirc.getSex(s) == 1) {
            defaultMutableIconTreeNode = new DefaultMutableIconTreeNode(this.useroffline, s);
        }
        else {
            defaultMutableIconTreeNode = new DefaultMutableIconTreeNode(this.useroffline, s);
        }
        this.insertSorted(this.nodeOnline, defaultMutableIconTreeNode, (DefaultTreeModel)this.friendsTree.getModel());
        this.refreshNodeCounts();
    }
    
    private void addFriend() {
        final String showInputDialog = JOptionPane.showInputDialog(this, "Tapez le nom du contact \u00e0 ajouter.", "Chat-Land", 1);
        if (showInputDialog != null && showInputDialog.length() > 0) {
            final String replace = showInputDialog.trim().toLowerCase().replace(' ', '_');
            if (!this._eirc.get_friends_list().contains(replace + ":0") && !this._eirc.get_friends_list().contains(replace + ":1")) {
                this._eirc.get_friends_list().addElement(replace + ":0");
                DefaultMutableIconTreeNode defaultMutableIconTreeNode;
                if (this._eirc.getSex(replace) == 1) {
                    defaultMutableIconTreeNode = new DefaultMutableIconTreeNode(this.useroffline, replace);
                }
                else {
                    defaultMutableIconTreeNode = new DefaultMutableIconTreeNode(this.useroffline, replace);
                }
                this.insertSorted(this.nodeOffline, defaultMutableIconTreeNode, (DefaultTreeModel)this.friendsTree.getModel());
                this._eirc.sendCommand("watch +" + replace, null);
                this._eirc.sendMessage("NOTICE", new String[] { "#applet-amis", "add: " + replace });
                final MySQL mySQL = new MySQL(this._eirc.getFriendURL());
                mySQL.addParam("a", "add");
                mySQL.addParam("b", MD5Nick.getEncodedNick(this._eirc.getNick().toLowerCase()));
                mySQL.addParam("c", replace);
                mySQL.execute();
            }
        }
    }
    
    private void blockFriend(final String s) {
        if (!this._eirc.cmd_ignore(s.toLowerCase(), this._eirc.getCurrentPanel()).equals("true")) {
            if (this._eirc.cmd_ignore(s.toLowerCase(), this._eirc.getCurrentPanel()).equals("false")) {
                this._eirc.getCurrentPanel().printInfo("N'abusez pas du syst\u00e9me bloquer/d\u00e9bloquer s.v.p !");
            }
        }
    }
    
    private DefaultTreeCellRenderer buildCellRenderer() {
        return new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(final JTree tree, final Object o, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
                tree.setRowHeight(20);
                super.getTreeCellRendererComponent(tree, o, b, b2, b3, n, b4);
                this.setOpaque(false);
                this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
                FriendWindow.this._eirc;
                this.setForeground(EIRC.mainfg);
                if (o instanceof DefaultMutableIconTreeNode) {
                    this.setFont(this.getFont().deriveFont(0));
                    final DefaultMutableIconTreeNode defaultMutableIconTreeNode = (DefaultMutableIconTreeNode)o;
                    this.setIcon(defaultMutableIconTreeNode.getIcon());
                    if (NickInfos.getSex(defaultMutableIconTreeNode.getUserObject().toString()) == 1) {
                        this.setForeground(Color.decode("0x0033CC"));
                    }
                    else if (NickInfos.getSex(defaultMutableIconTreeNode.getUserObject().toString()) == 2) {
                        this.setForeground(Color.decode("0xB02DB9"));
                    }
                    else {
                        this.setForeground(Color.black);
                    }
                    return this;
                }
                if (o instanceof DefaultMutableTreeNode) {
                    this.setFont(this.getFont().deriveFont(1));
                }
                return this;
            }
        };
    }
    
    private PopupJTree buildFriendsTree() {
        this.nodeOnline = new DefaultMutableTreeNode();
        this.nodeOffline = new DefaultMutableTreeNode();
        int n = 0;
        int n2 = 0;
        final Enumeration elements = this._eirc.get_friends_list().elements();
        while (elements.hasMoreElements()) {
            final String s = elements.nextElement();
            final String substring = s.substring(0, s.indexOf(58));
            if (Integer.parseInt(s.substring(s.indexOf(58) + 1)) == 0) {
                DefaultMutableIconTreeNode defaultMutableIconTreeNode;
                if (this._eirc.getSex(substring) == 1) {
                    defaultMutableIconTreeNode = new DefaultMutableIconTreeNode(this.useroffline, substring);
                }
                else {
                    defaultMutableIconTreeNode = new DefaultMutableIconTreeNode(this.useroffline, substring);
                }
                ++n2;
                this.insertSorted(this.nodeOffline, defaultMutableIconTreeNode);
            }
            else {
                DefaultMutableIconTreeNode defaultMutableIconTreeNode2;
                if (NickInfos.getSex(substring) == 1) {
                    defaultMutableIconTreeNode2 = new DefaultMutableIconTreeNode(this.boyOnline, substring);
                }
                else {
                    defaultMutableIconTreeNode2 = new DefaultMutableIconTreeNode(this.girlOnline, substring);
                }
                ++n;
                this.insertSorted(this.nodeOnline, defaultMutableIconTreeNode2);
            }
        }
        this.nodeOnline.setUserObject("en ligne (" + new Integer(n).toString() + ")");
        this.nodeOffline.setUserObject("hors ligne (" + new Integer(n2).toString() + ")");
        final DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode();
        defaultMutableTreeNode.add(this.nodeOnline);
        defaultMutableTreeNode.add(this.nodeOffline);
        final PopupJTree popupJTree = new PopupJTree(defaultMutableTreeNode);
        final DefaultTreeCellRenderer buildCellRenderer = this.buildCellRenderer();
        buildCellRenderer.setOpenIcon(new ImageIcon(Resources.getImages("friends.icon.node_open")));
        buildCellRenderer.setClosedIcon(new ImageIcon(Resources.getImages("friends.icon.node_closed")));
        buildCellRenderer.setLeafIcon(new ImageIcon(Resources.getImages("friends.icon.node_open")));
        buildCellRenderer.setOpaque(false);
        popupJTree.setCellRenderer(buildCellRenderer);
        popupJTree.expandRow(2);
        popupJTree.expandRow(1);
        popupJTree.setRootVisible(false);
        return popupJTree;
    }
    
    private void delFriend() {
        final TreePath selectionPath = this.friendsTree.getSelectionPath();
        if (selectionPath != null) {
            final MutableTreeNode mutableTreeNode = (MutableTreeNode)selectionPath.getLastPathComponent();
            final String string = mutableTreeNode.toString();
            if (JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer " + string + " de votre liste d'amis?", Resources.getStringEirc("friends.delete_confirm.title"), 2) != 0) {
                return;
            }
            if (!(mutableTreeNode instanceof DefaultMutableIconTreeNode)) {
                return;
            }
            ((DefaultTreeModel)this.friendsTree.getModel()).removeNodeFromParent(mutableTreeNode);
            if (this._eirc.get_friends_list().contains(string + ":0")) {
                this._eirc.get_friends_list().removeElement(string + ":0");
            }
            else {
                this._eirc.get_friends_list().removeElement(string + ":1");
            }
            this._eirc.sendCommand("watch -" + string, null);
            final MySQL mySQL = new MySQL("http://" + main.http + ".chat-land.org/modules/amis/suppression-securiser.php");
            mySQL.addParam("a", this._eirc.getNick().toLowerCase());
            mySQL.addParam("b", MD5Nick.getMD5_2(this._eirc.getNick().toLowerCase()).substring(4, 14));
            mySQL.addParam("c", string);
            mySQL.addParam("d", MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(string)).substring(4, 14));
            mySQL.execute();
        }
        this.refreshNodeCounts();
    }
    
    private MutableTreeNode findNode(final String s) {
        final TreePath nextMatch = this.friendsTree.getNextMatch(s, 0, Position.Bias.Forward);
        if (nextMatch != null) {
            return (MutableTreeNode)nextMatch.getLastPathComponent();
        }
        return null;
    }
    
    public void init() {
        final Image images = Resources.getImages("friend_girl_online");
        images.flush();
        this.girlOnline = new ImageIcon(images);
        final Image images2 = Resources.getImages("friend_boy_online");
        images2.flush();
        this.boyOnline = new ImageIcon(images2);
        final Image images3 = Resources.getImages("friend_user_offline");
        images3.flush();
        this.useroffline = new ImageIcon(images3);
        this.setFont(this._eirc.getFont());
        this.setLayout(new GridLayout(1, 1));
        (this.friendsTree = this.buildFriendsTree()).addTreeSelectionListener(this);
        this.friendsTree.setOpaque(false);
        this.paneltree.setLayout(new BorderLayout());
        this.paneltree.add(this.friendsTree, "Center");
        this.paneltree.setOpaque(false);
        final JScrollPane scrollPane = new JScrollPane(this.paneltree);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        this.add(scrollPane);
    }
    
    private void insertSorted(final DefaultMutableTreeNode defaultMutableTreeNode, final MutableTreeNode mutableTreeNode) {
        for (int i = 0; i < defaultMutableTreeNode.getChildCount(); ++i) {
            if (defaultMutableTreeNode.getChildAt(i).toString().compareToIgnoreCase(mutableTreeNode.toString()) > 0) {
                defaultMutableTreeNode.insert(mutableTreeNode, i);
                return;
            }
        }
        defaultMutableTreeNode.add(mutableTreeNode);
    }
    
    private void insertSorted(final DefaultMutableTreeNode defaultMutableTreeNode, final MutableTreeNode mutableTreeNode, final DefaultTreeModel defaultTreeModel) {
        for (int i = 0; i < defaultMutableTreeNode.getChildCount(); ++i) {
            if (this.nodeComparator.compare(defaultMutableTreeNode.getChildAt(i), mutableTreeNode) > 0) {
                defaultTreeModel.insertNodeInto(mutableTreeNode, defaultMutableTreeNode, i);
                return;
            }
        }
        defaultMutableTreeNode.add(mutableTreeNode);
    }
    
    private void openPrivate() {
        final TreePath selectionPath = this.friendsTree.getSelectionPath();
        if (selectionPath != null) {
            final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)selectionPath.getLastPathComponent();
            if (defaultMutableTreeNode instanceof DefaultMutableIconTreeNode) {
                this._eirc.getPrivates().openPrivate(defaultMutableTreeNode.toString(), 1);
                this._eirc.getChat_panel().ShowPrivate(defaultMutableTreeNode.toString().toLowerCase());
            }
        }
    }
    
    public void refresh() {
        this.paneltree.remove(this.friendsTree);
        this.friendsTree.removeAll();
        (this.friendsTree = this.buildFriendsTree()).addTreeSelectionListener(this);
        this.friendsTree.setOpaque(false);
        this.paneltree.add(this.friendsTree, "Center");
        this.repaint();
    }
    
    private void refreshNodeCounts() {
        this.nodeOnline.setUserObject("en ligne (" + new Integer(this.nodeOnline.getChildCount()).toString() + ")");
        this.nodeOffline.setUserObject("Hors ligne (" + new Integer(this.nodeOffline.getChildCount()).toString() + ")");
    }
    
    public void setConnected(final boolean enabled) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            this.getComponent(i).setEnabled(enabled);
        }
    }
    
    public void setOffline(final String s) {
        final MutableTreeNode node = this.findNode(s);
        if (node != null) {
            if (node instanceof DefaultMutableIconTreeNode) {
                if (NickInfos.getSex(s) == 1) {
                    ((DefaultMutableIconTreeNode)node).setIcon(this.useroffline);
                }
                else {
                    ((DefaultMutableIconTreeNode)node).setIcon(this.useroffline);
                }
            }
            final DefaultTreeModel defaultTreeModel = (DefaultTreeModel)this.friendsTree.getModel();
            defaultTreeModel.removeNodeFromParent(node);
            this.insertSorted(this.nodeOffline, node, defaultTreeModel);
            this.refreshNodeCounts();
        }
    }
    
    public void setOnline(final String s) {
        final MutableTreeNode node = this.findNode(s);
        if (node != null) {
            if (node instanceof DefaultMutableIconTreeNode) {
                if (NickInfos.getSex(s) == 1) {
                    ((DefaultMutableIconTreeNode)node).setIcon(this.boyOnline);
                }
                else {
                    ((DefaultMutableIconTreeNode)node).setIcon(this.girlOnline);
                }
            }
            final DefaultTreeModel defaultTreeModel = (DefaultTreeModel)this.friendsTree.getModel();
            defaultTreeModel.removeNodeFromParent(node);
            this.insertSorted(this.nodeOnline, node, defaultTreeModel);
            this.refreshNodeCounts();
        }
    }
    
    @Override
    public void valueChanged(final TreeSelectionEvent treeSelectionEvent) {
    }
    
    class PopupJTree extends JTree
    {
        JPopupMenu popup;
        
        PopupJTree(final DefaultMutableTreeNode defaultMutableTreeNode) {
            super(defaultMutableTreeNode);
            this.setOpaque(true);
            this.setBackground(Color.white);
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent mouseEvent) {
                    FriendWindow.this._eirc.revenir();
                    final TreePath selectionPath = FriendWindow.this.friendsTree.getSelectionPath();
                    if (selectionPath != null) {
                        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)selectionPath.getLastPathComponent();
                        if (defaultMutableTreeNode.toString().startsWith("hors ligne") || defaultMutableTreeNode.toString().startsWith("en ligne")) {
                            final int rowForLocation = FriendWindow.this.friendsTree.getRowForLocation(mouseEvent.getX(), mouseEvent.getY());
                            if (!FriendWindow.this.friendsTree.isExpanded(rowForLocation)) {
                                FriendWindow.this.friendsTree.expandRow(rowForLocation);
                                return;
                            }
                            FriendWindow.this.friendsTree.collapseRow(rowForLocation);
                            return;
                        }
                    }
                    final TreePath closestPathForLocation = PopupJTree.this.getClosestPathForLocation(mouseEvent.getX(), mouseEvent.getY());
                    if (mouseEvent.getClickCount() == 2) {
                        final DefaultMutableTreeNode defaultMutableTreeNode2 = (DefaultMutableTreeNode)closestPathForLocation.getLastPathComponent();
                        PopupJTree.this.setSelectionPath(closestPathForLocation);
                        FriendWindow.this.openPrivate();
                        return;
                    }
                    final DefaultMutableTreeNode defaultMutableTreeNode3 = (DefaultMutableTreeNode)closestPathForLocation.getLastPathComponent();
                    if (defaultMutableTreeNode3.toString().startsWith("hors ligne") || defaultMutableTreeNode3.toString().startsWith("en ligne")) {
                        return;
                    }
                    if (defaultMutableTreeNode3 instanceof DefaultMutableIconTreeNode) {
                        if (((DefaultMutableIconTreeNode)defaultMutableTreeNode3).getIcon() == FriendWindow.this.useroffline) {
                            PopupJTree.this.popup = PopupJTree.this.buildPopupMenu(false);
                        }
                        else {
                            PopupJTree.this.popup = PopupJTree.this.buildPopupMenu(true);
                        }
                    }
                    PopupJTree.this.popup.repaint();
                    if (PopupJTree.this.popup.isEnabled()) {
                        PopupJTree.this.popup.show((Component)mouseEvent.getSource(), mouseEvent.getX(), mouseEvent.getY());
                    }
                    PopupJTree.this.setSelectionPath(closestPathForLocation);
                }
                
                @Override
                public void mouseReleased(final MouseEvent mouseEvent) {
                    FriendWindow.this._eirc.revenir();
                    PopupJTree.this.setSelectionPath(PopupJTree.this.getClosestPathForLocation(mouseEvent.getX(), mouseEvent.getY()));
                }
            });
        }
        
        private JPopupMenu buildPopupMenu(final boolean b) {
            final JPopupMenu popupMenu = new JPopupMenu();
            if (b) {
                final JMenuItem menuItem = new JMenuItem(Resources.getStringEirc("friends.popup.pv"));
                menuItem.addActionListener(FriendWindow.this);
                menuItem.setActionCommand("pv");
                popupMenu.add(menuItem);
            }
            else {
                final JMenuItem menuItem2 = new JMenuItem("Envoyer un message diff\u00e9r\u00e9");
                menuItem2.addActionListener(FriendWindow.this);
                menuItem2.setActionCommand("pv");
                popupMenu.add(menuItem2);
            }
            final JMenuItem menuItem3 = new JMenuItem(Resources.getStringEirc("friends.popup.email"));
            menuItem3.addActionListener(FriendWindow.this);
            menuItem3.setActionCommand("email");
            String string = "";
            if (FriendWindow.this.friendsTree != null) {
                final TreePath selectionPath = FriendWindow.this.friendsTree.getSelectionPath();
                if (selectionPath != null) {
                    final MutableTreeNode mutableTreeNode = (MutableTreeNode)selectionPath.getLastPathComponent();
                    if (mutableTreeNode instanceof DefaultMutableIconTreeNode) {
                        string = mutableTreeNode.toString();
                    }
                }
                JMenuItem menuItem4;
                if (FriendWindow.this._eirc.getIgnore_list().contains(string.toLowerCase())) {
                    menuItem4 = new JMenuItem("D\u00e9bloquer");
                }
                else {
                    menuItem4 = new JMenuItem("Bloquer");
                }
                menuItem4.addActionListener(FriendWindow.this);
                menuItem4.setActionCommand("block " + string);
                popupMenu.add(menuItem4);
            }
            final JMenuItem menuItem5 = new JMenuItem(Resources.getStringEirc("friends.popup.remove"));
            menuItem5.addActionListener(FriendWindow.this);
            menuItem5.setActionCommand("delete");
            popupMenu.add(menuItem5);
            popupMenu.setOpaque(false);
            popupMenu.setLightWeightPopupEnabled(true);
            return popupMenu;
        }
    }
    
    public class DefaultMutableIconTreeNode extends DefaultMutableTreeNode
    {
        private ImageIcon icon;
        
        public DefaultMutableIconTreeNode(final ImageIcon icon, final Object o) {
            super(o);
            this.icon = icon;
        }
        
        public DefaultMutableIconTreeNode(final ImageIcon icon, final Object o, final boolean b) {
            super(o, b);
            this.icon = icon;
        }
        
        public ImageIcon getIcon() {
            return this.icon;
        }
        
        public void setIcon(final ImageIcon icon) {
            this.icon = icon;
        }
    }
}
