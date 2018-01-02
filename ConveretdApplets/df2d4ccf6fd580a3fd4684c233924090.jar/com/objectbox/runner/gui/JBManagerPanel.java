// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import com.objectbox.runner.model.JBAppletProperties;
import com.objectbox.runner.model.JBProcessModel;
import com.objectbox.runner.model.JBPropertyEditor;
import java.awt.Frame;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import com.objectbox.gui.lwcomp.FlatButton;
import com.objectbox.runner.command.NewFolderCommand;
import com.objectbox.runner.command.DeleteCommand;
import com.objectbox.runner.command.CutCommand;
import com.objectbox.runner.command.PasteCommand;
import com.objectbox.runner.command.CopyCommand;
import com.objectbox.runner.gui.tree.TreeNode;
import com.objectbox.runner.model.SecurityManagerIF;
import com.objectbox.runner.model.JBSecurityModel;
import com.objectbox.runner.command.AbstractCommand;
import com.objectbox.runner.command.RenameCommand;
import com.objectbox.runner.gui.listbox.ItemTextChangedEvent;
import com.objectbox.runner.model.JBAppletHolder;
import com.objectbox.runner.model.JBProperties;
import java.awt.Component;
import java.awt.BorderLayout;
import com.objectbox.gui.lwcomp.PopupItemSelectedEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Label;
import java.util.Vector;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import com.objectbox.runner.util.JBLogger;
import com.objectbox.runner.gui.listbox.ListItem;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.util.Enumeration;
import com.objectbox.runner.model.JBHolder;
import com.objectbox.runner.model.JBObjectWithProperties;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import com.objectbox.gui.lwcomp.JBPopupMenu;
import com.objectbox.runner.command.CommandManager;
import com.objectbox.runner.gui.tree.TreeBase;
import java.beans.PropertyChangeSupport;
import com.objectbox.runner.gui.tree.Node;
import java.util.Hashtable;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import com.objectbox.runner.gui.listbox.ItemTextChangedListener;
import com.objectbox.gui.lwcomp.PopupItemSelectedListener;
import java.awt.Panel;

public class JBManagerPanel extends Panel implements PopupItemSelectedListener, ItemTextChangedListener, ActionListener, ItemListener, KeyListener, MouseListener
{
    public Hashtable images;
    protected Node rootNode;
    protected transient FolderChangedListener aFolderChangedListener;
    protected transient PropertyChangeSupport propertyChange;
    protected transient TreeChangedListener aTreeChangedListener;
    private TreeBase ivjTreeBase;
    private CommandManager commandmanager;
    private JBPopupMenu popup_properties;
    byte[] node_clipboard;
    protected String clip_description;
    private Hashtable securityHash;
    private long timesnap;
    private NameEditor name_editor;
    private boolean shouldsave;
    private long shouldsave_reqtime;
    
    public JBManagerPanel() {
        this.images = new Hashtable();
        this.rootNode = null;
        this.aFolderChangedListener = null;
        this.aTreeChangedListener = null;
        this.ivjTreeBase = null;
        this.commandmanager = new CommandManager();
        this.popup_properties = null;
        this.node_clipboard = new byte[0];
        this.clip_description = "";
        this.securityHash = new Hashtable();
        this.timesnap = 0L;
        this.name_editor = new NameEditor();
        this.shouldsave = false;
        this.shouldsave_reqtime = 0L;
        this.initialize();
    }
    
    public JBManagerPanel(final LayoutManager layoutManager) {
        super(layoutManager);
        this.images = new Hashtable();
        this.rootNode = null;
        this.aFolderChangedListener = null;
        this.aTreeChangedListener = null;
        this.ivjTreeBase = null;
        this.commandmanager = new CommandManager();
        this.popup_properties = null;
        this.node_clipboard = new byte[0];
        this.clip_description = "";
        this.securityHash = new Hashtable();
        this.timesnap = 0L;
        this.name_editor = new NameEditor();
        this.shouldsave = false;
        this.shouldsave_reqtime = 0L;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.getTreeBase()) {
            this.connEtoC1(actionEvent);
        }
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().addPropertyChangeListener(propertyChangeListener);
    }
    
    public void addTreeChangedListener(final TreeChangedListener treeChangedListener) {
        this.aTreeChangedListener = TreeChangedEventMulticaster.add(this.aTreeChangedListener, treeChangedListener);
    }
    
    public void buttonAdd_ActionPerformed(final ActionEvent actionEvent) {
        this.newFolder("New Folder", null);
    }
    
    public void buttonApp_ActionPerformed(final ActionEvent actionEvent) {
    }
    
    public void cleanupTree(final Node node) {
        final Enumeration breadthFirstEnumeration = node.breadthFirstEnumeration();
        while (breadthFirstEnumeration.hasMoreElements()) {
            final Node node2 = breadthFirstEnumeration.nextElement();
            final Object data = node2.getData();
            if (data != null) {
                ((JBHolder)data).cleanup();
                node2.setData(null);
            }
        }
        this.getTreeBase().delNodeFromTree(node, false);
    }
    
    private void connEtoC1(final ActionEvent actionEvent) {
        try {
            this.treeBase1_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC11(final ItemEvent itemEvent) {
        try {
            this.treeBase1_ItemStateChanged(itemEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC2(final KeyEvent keyEvent) {
        try {
            this.treeBase_KeyReleased(keyEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC3(final KeyEvent keyEvent) {
        try {
            this.treeBase_KeyPressed(keyEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC5(final MouseEvent mouseEvent) {
        try {
            this.treeBase1_MouseClicked(mouseEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    public void copy() {
        try {
            final Object[] selectedObjects = this.getTreeBase().getSelectedObjects();
            if (selectedObjects == null || selectedObjects.length < 1) {
                return;
            }
            final NamedVector namedVector = new NamedVector();
            String text = "";
            int i;
            for (i = 0; i < selectedObjects.length; ++i) {
                final Node node = (Node)((ListItem)selectedObjects[i]).getItemData();
                if (i == 0) {
                    text = node.getText();
                }
                if (node != this.rootNode) {
                    namedVector.addElement(node);
                }
            }
            final Node node2 = namedVector.elementAt(0);
            if (node2 != null && node2.getType().equals("Folder")) {
                this.node_clipboard = this.saveNodeToMem(node2);
            }
            else {
                this.node_clipboard = this.saveNodeToMem(namedVector);
            }
            this.clip_description = ((i == 1) ? text : (i + " objects"));
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManager::copy: " + t);
        }
    }
    
    public Object createNodeFromMem(final byte[] array) {
        try {
            final ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(array)));
            final Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        }
        catch (Throwable t) {
            JBLogger.log("Exception in loadTree: " + t);
            return null;
        }
    }
    
    public void cut() {
        this.copy();
        this.delete();
    }
    
    public void delete() {
        try {
            final Hashtable hashtable = (Hashtable)AppRegistry.getInstance().lookup("nodehash");
            final TreeBase treeBase = this.getTreeBase();
            final Object[] selectedObjects = treeBase.getSelectedObjects();
            if (selectedObjects == null || selectedObjects.length < 1) {
                return;
            }
            final Vector<Node> vector = new Vector<Node>();
            for (int i = 0; i < selectedObjects.length; ++i) {
                final Node node = (Node)((ListItem)selectedObjects[i]).getItemData();
                vector.addElement(node);
                if (node != this.rootNode) {
                    final Enumeration breadthFirstEnumeration = node.breadthFirstEnumeration();
                    while (breadthFirstEnumeration.hasMoreElements()) {
                        hashtable.remove(breadthFirstEnumeration.nextElement());
                    }
                    hashtable.get(node.getParent()).deleteMenuItem(node);
                    final Object data = node.getData();
                    if (data != null) {
                        ((JBHolder)data).cleanup();
                        node.setData(null);
                    }
                }
            }
            treeBase.deselectAll(false);
            final Enumeration<Node> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final Node node2 = elements.nextElement();
                if (node2 != this.rootNode) {
                    treeBase.delNodeFromTree(node2, false);
                }
            }
            this.setShouldSave();
            ((Label)AppRegistry.getInstance().lookup("LabelFolder")).setText("/");
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManager::delete: " + t);
        }
    }
    
    public Vector deleteBackup() {
        final Vector<JBPopupMenu> vector = new Vector<JBPopupMenu>();
        try {
            final Hashtable hashtable = (Hashtable)AppRegistry.getInstance().lookup("nodehash");
            final Object[] selectedObjects = this.getTreeBase().getSelectedObjects();
            if (selectedObjects == null || selectedObjects.length < 1) {
                return vector;
            }
            final Node node = (Node)((ListItem)selectedObjects[0]).getItemData();
            final Node node2 = (Node)node.getPrevSibling();
            vector.addElement(hashtable.get(node.getParent()));
            final Vector<Node> vector2 = new Vector<Node>();
            for (int i = 0; i < selectedObjects.length; ++i) {
                vector2.addElement((Node)((ListItem)selectedObjects[i]).getItemData());
            }
            vector.addElement((JBPopupMenu)vector2);
            vector.addElement((JBPopupMenu)node.getParent());
            if (node2 == null || node.getParent() != node2.getParent()) {
                vector.addElement((JBPopupMenu)"FIRST");
            }
            else {
                vector.addElement((JBPopupMenu)node2);
            }
            System.out.println("debug (backupVec). " + vector);
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManager::deleteBackup: " + t);
        }
        return vector;
    }
    
    public void fireOnFolderChanged(final FolderChangedEvent folderChangedEvent) {
        if (this.aFolderChangedListener == null) {
            return;
        }
        this.aFolderChangedListener.onFolderChanged(folderChangedEvent);
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        this.getPropertyChange().firePropertyChange(s, o, o2);
    }
    
    public void flatButtonNewApp_ActionPerformed(final ActionEvent actionEvent) {
    }
    
    public CommandManager getCommandManager() {
        return this.commandmanager;
    }
    
    public NameEditor getNameEditor() {
        return this.name_editor;
    }
    
    public byte[] getNodeClipboard() {
        return this.node_clipboard;
    }
    
    protected PropertyChangeSupport getPropertyChange() {
        if (this.propertyChange == null) {
            this.propertyChange = new PropertyChangeSupport(this);
        }
        return this.propertyChange;
    }
    
    private TreeBase getTreeBase() {
        if (this.ivjTreeBase == null) {
            try {
                (this.ivjTreeBase = new TreeBase()).setName("TreeBase");
                this.ivjTreeBase.setLineSpacing(1);
                this.ivjTreeBase.setMultipleSelections(true);
                this.ivjTreeBase.setBackground(Color.white);
                this.ivjTreeBase.setDottedLineFill(1);
                this.ivjTreeBase.setOverlapEditMode(false);
                this.ivjTreeBase.setLeftIndent(1);
                this.ivjTreeBase.setShowDotRect(false);
                this.ivjTreeBase.setFont(new Font("sansserif", 0, 12));
                this.ivjTreeBase.setDottedLineSpace(1);
                this.ivjTreeBase.setVScrollbarWidth(15);
                this.ivjTreeBase.addItemTextChangedListener(this);
                this.ivjTreeBase.setEditMode(false);
                this.rootNode = new Node(System.getProperty("user.name"), 2, 4);
                this.ivjTreeBase.addRootItem(this.rootNode);
                this.ivjTreeBase.expand(this.rootNode, 1, true);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTreeBase;
    }
    
    public TreeBase getTreeBasePublic() {
        return this.getTreeBase();
    }
    
    private void handleException(final Throwable t) {
        JBLogger.log("--------- UNCAUGHT EXCEPTION ---------");
        t.printStackTrace(System.out);
    }
    
    public void handlePopupSelection(final PopupItemSelectedEvent popupItemSelectedEvent) {
    }
    
    private void initConnections() {
        this.getTreeBase().addActionListener(this);
        this.getTreeBase().addMouseListener(this);
        this.getTreeBase().addItemListener(this);
        this.getTreeBase().addKeyListener(this);
    }
    
    private void initialize() {
        AppRegistry.getInstance().put("Manager", this);
        this.setName("JBManager");
        this.setLayout(new BorderLayout());
        this.setSize(312, 309);
        this.add(this.getTreeBase(), "Center");
        this.initConnections();
        try {
            this.loadTreeImages();
            this.validate();
            this.startSaver();
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManager::initialize:" + t);
        }
    }
    
    public void installApp(final String s, final JBProperties properties) {
        final JBAppletHolder jbAppletHolder = new JBAppletHolder();
        jbAppletHolder.setProperties(properties);
        this.newApp(s, jbAppletHolder, true);
        this.setShouldSave();
    }
    
    public void installAppList(final Vector vector) {
        try {
            final Enumeration<Vector<String>> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final Vector<String> vector2 = elements.nextElement();
                final String s = vector2.elementAt(0);
                final JBProperties properties = (JBProperties)vector2.elementAt(1);
                final JBAppletHolder jbAppletHolder = new JBAppletHolder();
                jbAppletHolder.setProperties(properties);
                this.newApp(s, jbAppletHolder, false);
            }
            this.updateVisual();
            this.setShouldSave();
        }
        catch (Throwable t) {
            JBLogger.log("Exception i installAppList: " + t);
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.getTreeBase()) {
            this.connEtoC11(itemEvent);
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getSource() == this.getTreeBase()) {
            this.connEtoC3(keyEvent);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getSource() == this.getTreeBase()) {
            this.connEtoC2(keyEvent);
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void loadTree(final Node rootNode) {
        try {
            this.getTreeBase().delNodeFromTree(this.rootNode, true);
            this.getTreeBase().addRootItem(rootNode);
            this.rootNode = rootNode;
            this.getTreeBase().expand(rootNode, 1, true);
            final Enumeration breadthFirstEnumeration = rootNode.breadthFirstEnumeration();
            while (breadthFirstEnumeration.hasMoreElements()) {
                final Node node = breadthFirstEnumeration.nextElement();
                JBLogger.log(node.getText());
                node.getData();
            }
            this.validate();
        }
        catch (Throwable t) {
            JBLogger.log("Exception in loadTree: " + t);
        }
    }
    
    public void loadTreeFromMem(final byte[] array) {
        try {
            final ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(array));
            final Object object = objectInputStream.readObject();
            objectInputStream.close();
            final Node rootNode = (Node)object;
            this.cleanupTree(this.rootNode);
            this.getTreeBase().addRootItem(rootNode);
            this.rootNode = rootNode;
            this.getTreeBase().validate();
        }
        catch (Throwable t) {
            JBLogger.log("Exception in loadTreeFromMem: " + t);
        }
    }
    
    public void loadTreeImages() {
        try {
            this.getTreeBase().setImageList(JBee.resources.getImageResource("/images/folder.gif"));
            this.getTreeBase().setImageList(JBee.resources.getImageResource("/images/folderopen.gif"));
            this.getTreeBase().setImageList(JBee.resources.getImageResource("/images/computer.gif"));
            this.getTreeBase().setImageList(JBee.resources.getImageResource("/images/File.gif"));
            this.getTreeBase().setImageList(JBee.resources.getImageResource("/images/computer.gif"));
        }
        catch (Throwable t) {
            JBLogger.log("Exception in loadTreeImages: " + t);
        }
    }
    
    public static void main(final String[] array) {
        try {
            final CloseableFrame closeableFrame = new CloseableFrame();
            closeableFrame.setLayout(new BorderLayout());
            closeableFrame.add(new JBManagerPanel(), "Center");
            closeableFrame.setSize(400, 400);
            closeableFrame.setLocation(100, 100);
            closeableFrame.setVisible(true);
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManager::main: " + t);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this.getTreeBase()) {
            this.connEtoC5(mouseEvent);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void moveMenuItems(final Object[] array, final Node node) {
        try {
            final Hashtable hashtable = (Hashtable)AppRegistry.getInstance().lookup("nodehash");
            final JBPopupMenu jbPopupMenu = hashtable.get(node);
            for (int i = 0; i < array.length; ++i) {
                final Node node2 = (Node)((ListItem)array[i]).getItemData();
                if (node2 == node) {
                    JBLogger.log("Illegal drag operation");
                    return;
                }
                final Object detachMenuItem = hashtable.get(node2.getParent()).detachMenuItem(node2);
                hashtable.remove(node2);
                hashtable.put(node2, jbPopupMenu);
                if (node2.getType().equals("Folder")) {
                    jbPopupMenu.addPopupMenu(node2.getText(), (JBPopupMenu)detachMenuItem, node2);
                    this.setShouldSave();
                }
                else if (node2.getType().equals("App")) {
                    jbPopupMenu.addMenuItemWithObject(node2.getText(), node2);
                    this.setShouldSave();
                }
            }
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManagerPanel::moveMenuItems" + t.toString());
        }
    }
    
    public void newApp(final String s, final JBObjectWithProperties data, final boolean b) {
        try {
            final TreeBase treeBase = this.getTreeBase();
            int n = treeBase.getSelectedIndex();
            if (n < 0) {
                treeBase.select(treeBase.getIndex(this.rootNode.getListItem()));
                n = treeBase.getSelectedIndex();
            }
            Node node = (Node)((ListItem)treeBase.getItem(n)).getItemData();
            if (node.getType().compareTo("Folder") != 0) {
                node = (Node)node.getParent();
            }
            final Node node2 = new Node(s, 3, 3);
            node2.setType("App");
            if (data != null) {
                node2.setData(data);
            }
            treeBase.insertItem(node2, node, null);
            Node node3 = (Node)node2.getParent();
            if (node3 == this.rootNode) {
                treeBase.expand(this.rootNode, 1, true);
            }
            while (node3 != this.rootNode) {
                treeBase.expand(node3, 1, true);
                node3 = (Node)node3.getParent();
            }
            final Hashtable hashtable = (Hashtable)AppRegistry.getInstance().lookup("nodehash");
            final JBPopupMenu jbPopupMenu = hashtable.get(node);
            jbPopupMenu.addMenuItemWithObject(s, node2);
            hashtable.put(node2, jbPopupMenu);
            if (b) {
                this.updateVisual();
            }
        }
        catch (Throwable t) {
            JBLogger.log("Exception in newApp: " + t);
        }
    }
    
    public void newFolder(final String s, final JBObjectWithProperties data) {
        try {
            final TreeBase treeBase = this.getTreeBase();
            final int selectedIndex = treeBase.getSelectedIndex();
            if (selectedIndex < 0) {
                return;
            }
            final Node node = (Node)((ListItem)treeBase.getItem(selectedIndex)).getItemData();
            if (node.getType().compareTo("Folder") != 0) {
                return;
            }
            final Node node2 = new Node(s, 0, 1);
            node2.setType("Folder");
            if (data != null) {
                node2.setData(data);
            }
            treeBase.insertItem(node2, node, null);
            Node node3 = (Node)node2.getParent();
            if (node3 == this.rootNode) {
                treeBase.expand(this.rootNode, 1, true);
            }
            while (node3 != this.rootNode) {
                treeBase.expand(node3, 1, true);
                node3 = (Node)node3.getParent();
            }
            ((JBee)AppRegistry.getInstance().lookup("JBee")).insertFolderPopup(s, node, node2);
            this.updateVisual();
            this.setShouldSave();
        }
        catch (Throwable t) {
            JBLogger.log(t.toString());
        }
    }
    
    public void onItemTextChanged(final ItemTextChangedEvent itemTextChangedEvent) {
        this.getCommandManager().invokeCommand(new RenameCommand(this.getTreeBase()));
        Node selectedNode = this.getTreeBase().getSelectedNode();
        if (selectedNode != null) {
            final Object lookup = AppRegistry.getInstance().lookup("LabelFolder");
            if (lookup != null) {
                final Vector<String> vector = new Vector<String>();
                while (selectedNode != this.rootNode) {
                    if (selectedNode.getType().compareTo("Folder") == 0) {
                        vector.insertElementAt(selectedNode.getText(), 0);
                    }
                    selectedNode = (Node)selectedNode.getParent();
                }
                String string = "";
                final Enumeration<String> elements = vector.elements();
                while (elements.hasMoreElements()) {
                    string = String.valueOf(string) + "/" + (Object)elements.nextElement();
                }
                if (string.equals("")) {
                    string = "/";
                }
                ((Label)lookup).setText(string);
            }
            this.fireOnFolderChanged(new FolderChangedEvent(selectedNode));
            this.updateVisual();
        }
    }
    
    public void onSecurityChanged(final SecurityChangedEvent securityChangedEvent) {
        try {
            final SecurityPanel securityPanel = (SecurityPanel)securityChangedEvent.getSource();
            final TreeBase treeBase = this.getTreeBase();
            final int selectedIndex = treeBase.getSelectedIndex();
            if (selectedIndex < 0) {
                return;
            }
            final Node node = (Node)((ListItem)treeBase.getItem(selectedIndex)).getItemData();
            if (node.getType().compareTo("App") == 0) {
                final JBHolder jbHolder = (JBHolder)node.getData();
                final JBSecurityModel securityModel = JBSecurityModel.getSecurityModel(securityPanel.getSecurityLevel());
                final JBProperties properties = jbHolder.getProperties();
                final String string = "-" + properties.getProps().getProperty("codebase").hashCode() + properties.getProps().getProperty("documentbase").hashCode() + properties.getProps().getProperty("code").hashCode();
                final Object lookup = AppRegistry.getInstance().lookup("JBee");
                if (lookup != null) {
                    ((JBee)lookup).setSecurity(string, securityModel, this);
                }
            }
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManager::onSecurityChanged: " + t);
        }
    }
    
    public void paste() {
        try {
            final TreeBase treeBase = this.getTreeBase();
            if (treeBase.getSelectedIndex() < 0) {
                treeBase.select(0);
            }
            Node selectedNode = this.getTreeBase().getSelectedNode();
            if (selectedNode.getType().equals("App")) {
                selectedNode = (Node)selectedNode.getParent();
            }
            final JBee bee = (JBee)AppRegistry.getInstance().lookup("JBee");
            final Hashtable hashtable = (Hashtable)AppRegistry.getInstance().lookup("nodehash");
            if (selectedNode != null && this.node_clipboard != null && this.node_clipboard.length > 0) {
                if (selectedNode.getType().compareTo("App") == 0) {}
                final Object nodeFromMem = this.createNodeFromMem(this.node_clipboard);
                if (nodeFromMem instanceof NamedVector) {
                    final Enumeration<Object> elements = ((NamedVector)nodeFromMem).elements();
                    while (elements.hasMoreElements()) {
                        final Node node = elements.nextElement();
                        treeBase.insertItem(node, selectedNode, null);
                        final JBPopupMenu jbPopupMenu = hashtable.get(selectedNode);
                        jbPopupMenu.addMenuItemWithObject(node.getText(), node);
                        hashtable.put(node, jbPopupMenu);
                    }
                }
                else {
                    final Node node2 = (Node)nodeFromMem;
                    treeBase.insertItem(node2, selectedNode, null);
                    bee.insertFolderPopup(node2.getText(), selectedNode, node2);
                    bee.addMenuItemsForNode(node2, hashtable.get(node2));
                }
            }
            Node node3 = selectedNode;
            if (node3 == this.rootNode) {
                treeBase.expand(this.rootNode, 1, true);
            }
            while (node3 != this.rootNode) {
                treeBase.expand(node3, 1, true);
                node3 = (Node)node3.getParent();
            }
            this.setShouldSave();
            treeBase.validate();
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManager::paste: " + t);
        }
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().removePropertyChangeListener(propertyChangeListener);
    }
    
    public void removeTreeChangedListener(final TreeChangedListener treeChangedListener) {
        this.aTreeChangedListener = TreeChangedEventMulticaster.remove(this.aTreeChangedListener, treeChangedListener);
    }
    
    public void restoreVec(final Vector vector) {
        try {
            if (vector.size() != 4) {
                return;
            }
            final JBPopupMenu jbPopupMenu = vector.elementAt(0);
            final Vector vector2 = (Vector)vector.elementAt(1);
            final Node node = (Node)vector.elementAt(2);
            final JBPopupMenu element = vector.elementAt(3);
            Node node2 = null;
            if (element instanceof Node) {
                node2 = (Node)element;
            }
            final JBee bee = (JBee)AppRegistry.getInstance().lookup("JBee");
            final Node node3 = new Node();
            int n = 0;
            final Enumeration<Node> elements = vector2.elements();
            while (elements.hasMoreElements()) {
                final Node node4 = elements.nextElement();
                if (node4.getType().equals("App")) {
                    System.out.println(String.valueOf(node4.getType()) + ": " + node4.getData().toString());
                }
                final Node node5 = new Node(node4.getText(), node4.getImage(), node4.getExpandedImage());
                node5.setType(node4.getType());
                if (node4.getData() != null) {
                    node5.setData(node4.getData());
                }
                node4.setData(null);
                this.getTreeBase().insertItem(node5, node, node2);
                node2 = node4;
                ++n;
                if (node4.getType().equals("Folder")) {
                    bee.restoreMenuItems(node4, jbPopupMenu);
                    break;
                }
                node3.addChild(node4);
            }
            if (node3.hasChildren()) {
                bee.addMenuItemsForNode(node3, jbPopupMenu);
            }
            this.updateVisual();
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManagerPanel::restoreVec: " + t);
        }
    }
    
    public void runCommand(final String s) {
        try {
            final Node selectedNode = this.getTreeBase().getSelectedNode();
            if (!s.startsWith("Undo")) {
                if (s.startsWith("Copy")) {
                    if (selectedNode == null) {
                        return;
                    }
                    this.getCommandManager().invokeCommand(new CopyCommand(this));
                }
                else if (s.startsWith("Brows")) {
                    if (selectedNode == null) {
                        return;
                    }
                    final JBHolder jbHolder = (JBHolder)this.getTreeBase().getSelectedNode().getData();
                    JBee.displayURL(String.valueOf(jbHolder.getProperties().getProps().get("documentbase")) + (String)jbHolder.getProperties().getProps().get("webpage"));
                }
                else if (s.startsWith("Paste")) {
                    if (selectedNode == null) {
                        return;
                    }
                    this.getCommandManager().invokeCommand(new PasteCommand(this));
                    this.getTreeBase().repaint();
                    this.getTreeBase().validate();
                }
                else if (s.startsWith("Cut")) {
                    if (selectedNode == null) {
                        return;
                    }
                    this.getCommandManager().invokeCommand(new CutCommand(this));
                    this.getTreeBase().repaint();
                    this.getTreeBase().validate();
                }
                else if (s.startsWith("Delete")) {
                    if (selectedNode == null) {
                        return;
                    }
                    this.getCommandManager().invokeCommand(new DeleteCommand(this));
                    this.getTreeBase().repaint();
                    this.getTreeBase().validate();
                }
                else if (s.startsWith("NewFolder")) {
                    if (selectedNode == null) {
                        this.getTreeBase().select(0);
                    }
                    this.getCommandManager().invokeCommand(new NewFolderCommand(this, "New Folder", null));
                    this.getTreeBase().repaint();
                    this.getTreeBase().validate();
                }
                else if (s.startsWith("Prefe")) {
                    if (selectedNode != this.rootNode) {
                        this.showProperties();
                    }
                    else {
                        final SetupFrame setupFrame = new SetupFrame(JBee.getRunningInstanceFrame(), JBee.preferences);
                        setupFrame.setVisible(true);
                        setupFrame.toFront();
                    }
                }
                else if (s.startsWith("Run")) {
                    final Node selectedNode2 = this.getTreeBase().getSelectedNode();
                    if (selectedNode2.getType().compareTo("Folder") == 0) {
                        Node node = (Node)selectedNode2.getFirstChild();
                        int n = 0;
                        while (node != null) {
                            if (++n > 10) {
                                break;
                            }
                            this.startApp(node);
                            final TreeNode nextSibling = node.getNextSibling();
                            if (nextSibling == null) {
                                break;
                            }
                            node = (Node)nextSibling;
                        }
                        if (n > 10) {
                            JBee.showMessage("Can't start all applets:", false);
                            JBee.showMessage("Maximum allowed is 10", true);
                            JBee.showMessage("", true);
                        }
                    }
                    else {
                        this.startApp(selectedNode2);
                    }
                    return;
                }
            }
            final FlatButton flatButton = (FlatButton)AppRegistry.getInstance().lookup("FlatButtonUndo");
            final FlatButton flatButton2 = (FlatButton)AppRegistry.getInstance().lookup("FlatButtonPaste");
            final FlatButton flatButton3 = (FlatButton)AppRegistry.getInstance().lookup("FlatButtonCopy");
            final FlatButton flatButton4 = (FlatButton)AppRegistry.getInstance().lookup("FlatButtonDelete");
            final FlatButton flatButton5 = (FlatButton)AppRegistry.getInstance().lookup("FlatButtonCut");
            if (this.getCommandManager().getHistoryLength() < 1) {
                flatButton.setEnabled(false);
                flatButton.repaint();
            }
            else {
                flatButton.setEnabled(true);
                flatButton.validate();
            }
            if (this.getNodeClipboard().length > 0 && this.getTreeBase().getSelectedIndex() > 0) {
                flatButton2.setEnabled(true);
                flatButton2.repaint();
            }
            else {
                flatButton2.setEnabled(false);
                flatButton2.repaint();
            }
            if (this.getTreeBase().getSelectedIndex() < 1) {
                flatButton3.setEnabled(false);
                flatButton4.setEnabled(false);
                flatButton5.setEnabled(false);
                flatButton3.repaint();
                flatButton4.repaint();
                flatButton5.repaint();
            }
            else {
                flatButton3.setEnabled(true);
                flatButton4.setEnabled(true);
                flatButton5.setEnabled(true);
                flatButton3.repaint();
                flatButton4.repaint();
                flatButton5.repaint();
            }
        }
        catch (Throwable t) {
            JBLogger.log("Exception i runCommand: " + t);
        }
    }
    
    public void save(final boolean b) {
        try {
            this.shouldsave = false;
            final String property = JBee.preferences.getProperty("javabee_home");
            String s;
            if (property == null) {
                s = System.getProperty("file.separator");
            }
            else {
                s = String.valueOf(property) + System.getProperty("file.separator");
            }
            String s2 = "~jbee.dat";
            if (b) {
                s2 = "jbee.dat";
            }
            this.saveTree(new File(String.valueOf(s) + s2));
            final Object lookup = AppRegistry.getInstance().lookup("SearchFrame");
            if (lookup != null) {
                ((JBSearchPanelFrame)lookup).saveState();
            }
            JBLogger.log("Tree saved OK");
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManagerPanel::save. " + t);
        }
    }
    
    public byte[] saveNodeToMem(final Object o) {
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(40000);
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            if (o != null) {
                objectOutputStream.writeObject(o);
            }
            else {
                JBLogger.log("Missing node?!?");
            }
            objectOutputStream.close();
            JBLogger.log("saveNodeToMem: " + (System.currentTimeMillis() - currentTimeMillis) / 1000.0 + " secs");
            return byteArrayOutputStream.toByteArray();
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManager::saveNodeToMem: " + t);
            return null;
        }
    }
    
    public void saveTree(final File file) {
        try {
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            if (this.rootNode != null) {
                objectOutputStream.writeObject(this.rootNode);
            }
            else {
                JBLogger.log("Missing rootnode?!?");
            }
            objectOutputStream.close();
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManager::saveTree: " + t);
        }
    }
    
    public byte[] saveTreeToMem() {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            if (this.rootNode != null) {
                objectOutputStream.writeObject(this.rootNode);
            }
            else {
                JBLogger.log("Missing rootnode?!?");
            }
            objectOutputStream.close();
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            JBLogger.log("saveTreeToMem: " + byteArray.length);
            return byteArray;
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManager::saveTreeToMem: " + t);
            return null;
        }
    }
    
    public void saveTreeToMem(final OutputStream outputStream) {
        try {
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            if (this.rootNode != null) {
                objectOutputStream.writeObject(this.rootNode);
            }
            else {
                JBLogger.log("Missing rootnode?!?");
            }
            objectOutputStream.close();
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBManager::saveTree: " + t);
        }
    }
    
    public void setShouldSave() {
        this.shouldsave = true;
        this.shouldsave_reqtime = System.currentTimeMillis();
    }
    
    public boolean shouldSave() {
        if (System.currentTimeMillis() - this.shouldsave_reqtime > 15000L) {
            this.shouldsave_reqtime = System.currentTimeMillis();
            return this.shouldsave;
        }
        return false;
    }
    
    public void showProperties() {
        final Node selectedNode = this.getTreeBase().getSelectedNode();
        final Object data = selectedNode.getData();
        if (data != null && data instanceof JBObjectWithProperties) {
            final JBProperties properties = ((JBObjectWithProperties)data).getProperties();
            if (properties != null) {
                ((Hashtable<String, String>)properties.getJBeeProps()).put("Name", selectedNode.getText());
                final JBPropertyEditor propertyEditor = properties.getPropertyEditor();
                if (propertyEditor != null) {
                    propertyEditor.edit(properties);
                    ((Frame)propertyEditor).toFront();
                }
            }
        }
    }
    
    public void startApp(final Node node) {
        try {
            final JBHolder jbHolder = (JBHolder)node.getData();
            final Object lookup = AppRegistry.getInstance().lookup("JBee");
            if (lookup != null) {
                ThreadGroup threadGroup = ((JBee)lookup).getThreadGroup();
                if (threadGroup == null) {
                    threadGroup = new ThreadGroup("jbee");
                }
                final BeanRunner run = jbHolder.run(threadGroup);
                final JBProcessModel processModel = ((JBee)lookup).getProcessModel();
                final JBProcessButton jbProcessButton = new JBProcessButton();
                jbProcessButton.setLabel(node.getText());
                jbProcessButton.setVisual(true);
                jbProcessButton.setState(1);
                processModel.addBeanRunner(run, jbProcessButton);
            }
        }
        catch (Throwable t) {
            JBLogger.log("Exception i JBManager startApp: " + t);
        }
    }
    
    public void startSaver() {
        final JBManagerPanel$1.Saver saver = new JBManagerPanel$1.Saver();
        ((Thread)saver).setPriority(1);
        ((Thread)saver).start();
    }
    
    public void treeBase_KeyPressed(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        if (keyCode == 10) {
            final Node selectedNode = this.getTreeBase().getSelectedNode();
            if (selectedNode != null && selectedNode != this.rootNode && selectedNode.getType().equals("App")) {
                this.runCommand("Prefe");
                return;
            }
            if (selectedNode != null && selectedNode.getType().equals("Folder")) {
                this.name_editor.setFolderName(selectedNode.getText());
                this.name_editor.setVisible(true);
                this.name_editor.toFront();
            }
        }
        else {
            String s = "";
            if (keyEvent.isControlDown() && !keyEvent.isShiftDown()) {
                switch (keyCode) {
                    case 67:
                    case 155: {
                        s = "Copy";
                        break;
                    }
                    case 69: {
                        s = "Run";
                        break;
                    }
                    case 85: {
                        s = "Undo";
                        break;
                    }
                    case 86: {
                        s = "Paste";
                        break;
                    }
                    case 80: {
                        s = "Prefe";
                        break;
                    }
                    case 88: {
                        s = "Cut";
                        break;
                    }
                    default: {
                        return;
                    }
                }
            }
            if (!keyEvent.isControlDown() && keyEvent.isShiftDown()) {
                switch (keyCode) {
                    case 155: {
                        s = "Paste";
                        break;
                    }
                }
            }
            if (!keyEvent.isControlDown() && !keyEvent.isShiftDown()) {
                switch (keyCode) {
                    case 127: {
                        s = "Delete";
                        break;
                    }
                }
            }
            if (s.compareTo("") == 0) {
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.timesnap != 0L && currentTimeMillis - this.timesnap < 500L) {
                return;
            }
            this.timesnap = currentTimeMillis;
            this.runCommand(s);
        }
    }
    
    public void treeBase_KeyReleased(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        if (keyCode == 38 || keyCode == 40 || keyCode == 37 || keyCode == 39) {
            final Node selectedNode = this.getTreeBase().getSelectedNode();
            if (selectedNode != null && selectedNode != this.rootNode) {
                if (selectedNode.getType().equals("Folder")) {
                    this.name_editor.setFolderName(selectedNode.getText());
                }
                else if (selectedNode.getType().equals("App") && JBAppletProperties.editorFrame.isVisible()) {
                    this.showProperties();
                }
            }
        }
    }
    
    public void treeBase1_ActionPerformed(final ActionEvent actionEvent) {
        try {
            final Object source = actionEvent.getSource();
            final TreeBase treeBase = this.getTreeBase();
            if (source == treeBase && actionEvent.getActionCommand().equals("Drag_Drop")) {
                Node node = treeBase.getNodeAt(treeBase.getDropTargetItem());
                if (node.getType().compareTo("Folder") == 0) {
                    this.moveMenuItems(treeBase.getSelectedObjects(), node);
                    treeBase.moveItems(treeBase.getSelectedItems(), treeBase.getDropTargetItem());
                    treeBase.select(treeBase.getDropTargetItem());
                    final Object lookup = AppRegistry.getInstance().lookup("LabelFolder");
                    if (lookup != null) {
                        final Vector<String> vector = new Vector<String>();
                        while (node != this.rootNode) {
                            if (node.getType().compareTo("Folder") == 0) {
                                vector.insertElementAt(node.getText(), 0);
                            }
                            node = (Node)node.getParent();
                        }
                        String string = "";
                        final Enumeration<String> elements = vector.elements();
                        while (elements.hasMoreElements()) {
                            string = String.valueOf(string) + "/" + (Object)elements.nextElement();
                        }
                        if (string.equals("")) {
                            string = "/";
                        }
                        ((Label)lookup).setText(string);
                    }
                    this.updateVisual();
                    this.shouldsave = true;
                }
            }
        }
        catch (Throwable t) {
            JBLogger.log("JBManagerPanel::treeBase1_ActionPerformed" + t.toString());
        }
    }
    
    public void treeBase1_ItemStateChanged(final ItemEvent itemEvent) {
        final TreeBase treeBase = (TreeBase)itemEvent.getSource();
        final Object lookup = AppRegistry.getInstance().lookup("LabelFolder");
        if (lookup != null) {
            Node selectedNode = treeBase.getSelectedNode();
            if (selectedNode != null) {
                final Vector<String> vector = new Vector<String>();
                while (selectedNode != this.rootNode) {
                    if (selectedNode.getType().compareTo("Folder") == 0) {
                        vector.insertElementAt(selectedNode.getText(), 0);
                    }
                    selectedNode = (Node)selectedNode.getParent();
                }
                String string = "";
                final Enumeration<String> elements = vector.elements();
                while (elements.hasMoreElements()) {
                    string = String.valueOf(string) + "/" + (Object)elements.nextElement();
                }
                if (string.equals("")) {
                    string = "/";
                }
                ((Label)lookup).setText(string);
            }
        }
        final Object[] selectedObjects = treeBase.getSelectedObjects();
        boolean b = false;
        if (selectedObjects.length > 1) {
            final int distanceFromRoot = ((Node)((ListItem)selectedObjects[0]).getItemData()).getDistanceFromRoot();
            for (int i = 0; i < selectedObjects.length; ++i) {
                final Node node = (Node)((ListItem)selectedObjects[i]).getItemData();
                if (!node.isMultiSelectable()) {
                    b = true;
                    break;
                }
                if (((Node)node.getParent()).getType().compareTo("Folder") != 0) {
                    b = true;
                    break;
                }
                if (node.getDistanceFromRoot() != distanceFromRoot) {
                    b = true;
                    break;
                }
            }
            if (b) {
                JBLogger.log("Invalid selection");
                treeBase.deselectAll(true);
                return;
            }
            JBLogger.log("Selected: " + selectedObjects.length + " nodes");
        }
        Node selectedNode2 = treeBase.getSelectedNode();
        if (selectedNode2 == null) {
            return;
        }
        treeBase.m_oldName = selectedNode2.getText();
        if (selectedNode2.getType().compareTo("Folder") != 0) {
            selectedNode2 = (Node)selectedNode2.getParent();
        }
        this.fireOnFolderChanged(new FolderChangedEvent(selectedNode2));
    }
    
    public void treeBase1_MouseClicked(final MouseEvent mouseEvent) {
        try {
            final Node selectedNode = this.getTreeBase().getSelectedNode();
            if (selectedNode == this.rootNode) {
                return;
            }
            if (selectedNode != null && selectedNode.getType().equals("App") && (mouseEvent.getClickCount() == 2 || JBAppletProperties.editorFrame.isVisible())) {
                this.runCommand("Prefe");
            }
            if (selectedNode != null && selectedNode.getType().equals("Folder")) {
                this.name_editor.setFolderName(selectedNode.getText());
                if (mouseEvent.getClickCount() == 2) {
                    this.name_editor.setVisible(true);
                    this.name_editor.toFront();
                }
            }
        }
        catch (Throwable t) {
            JBLogger.log("Exception in treeBase1_MouseClicked: " + t);
        }
    }
    
    public void updateVisual() {
        this.getTreeBase().doLayout();
        this.getTreeBase().updateScrollbar(true);
        this.getTreeBase().repaint();
    }
}
