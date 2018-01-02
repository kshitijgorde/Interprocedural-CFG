// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import javax.swing.JFrame;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import javax.swing.ImageIcon;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.Icon;
import javax.swing.tree.DefaultTreeCellRenderer;
import com.itt.J2KViewer.util.Helper;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.JFileChooser;
import com.itt.J2KViewer.util.XmlPropertiesParser;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.JButton;
import com.itt.J2KViewer.util.Log;
import javax.swing.JDialog;

public class XmlPropertiesDialog extends JDialog
{
    private static final long serialVersionUID = 1L;
    private Log log;
    private boolean cancelled;
    private JButton btnClose;
    private JPanel btnPanel;
    private JTree theTree;
    private JScrollPane treeScrollPane;
    private XmlPropertiesParser m_parser;
    final JFileChooser m_fc;
    private String currentURL;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$XmlPropertiesDialog;
    
    public XmlPropertiesDialog(final Frame frame, final boolean b, final String currentURL) {
        super(frame, b);
        this.log = new Log((XmlPropertiesDialog.class$com$itt$J2KViewer$gui$XmlPropertiesDialog == null) ? (XmlPropertiesDialog.class$com$itt$J2KViewer$gui$XmlPropertiesDialog = class$("com.itt.J2KViewer.gui.XmlPropertiesDialog")) : XmlPropertiesDialog.class$com$itt$J2KViewer$gui$XmlPropertiesDialog);
        this.cancelled = false;
        this.m_parser = null;
        this.m_fc = new JFileChooser();
        this.initComponents();
        this.setSize(370, 370);
        this.getRootPane().setDefaultButton(this.btnClose);
        this.currentURL = currentURL;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    private void initComponents() {
        this.treeScrollPane = new JScrollPane();
        this.setupTree();
        this.setupBtnPanel();
        this.setDefaultCloseOperation(2);
        this.setTitle("XML Properties");
        this.treeScrollPane.setViewportView(this.theTree);
        this.getContentPane().add(this.treeScrollPane, "Center");
        this.getContentPane().add(this.btnPanel, "South");
        this.pack();
    }
    
    private void setupBtnPanel() {
        (this.btnPanel = new JPanel()).setLayout(new FlowLayout(2));
        final JButton button = new JButton();
        button.setMnemonic('E');
        button.setText("Expand All");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                XmlPropertiesDialog.this.expandAllNodes();
            }
        });
        this.btnPanel.add(button);
        final JButton button2 = new JButton();
        button2.setMnemonic('o');
        button2.setText("Collapse All");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                XmlPropertiesDialog.this.collapseAllNodes();
            }
        });
        this.btnPanel.add(button2);
        final JButton button3 = new JButton();
        button3.setMnemonic('s');
        button3.setText("Save...");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final String substring = XmlPropertiesDialog.this.currentURL.substring(XmlPropertiesDialog.this.currentURL.lastIndexOf("/") + 1);
                String s;
                if (substring.lastIndexOf(".") != -1) {
                    s = substring.substring(0, substring.lastIndexOf(".")) + ".xml";
                }
                else {
                    s = substring + ".xml";
                }
                XmlPropertiesDialog.this.m_fc.setSelectedFile(new File(s));
                XmlPropertiesDialog.this.m_fc.setFileFilter(new FileFilter() {
                    public boolean accept(final File file) {
                        return !file.isFile() || file.getName().toLowerCase().endsWith(".xml");
                    }
                    
                    public String getDescription() {
                        return "XML Files";
                    }
                });
                if (XmlPropertiesDialog.this.getSaveFilename(button3) == 0) {
                    XmlPropertiesDialog.this.saveToFile(XmlPropertiesDialog.this.m_fc.getSelectedFile().getAbsolutePath());
                }
            }
        });
        this.btnPanel.add(button3);
        (this.btnClose = new JButton()).setMnemonic('C');
        this.btnClose.setText("Close");
        this.btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                XmlPropertiesDialog.this.btnCancelActionPerformed(actionEvent);
            }
        });
        this.btnPanel.add(this.btnClose);
    }
    
    private int getSaveFilename(final Component component) {
        int showSaveDialog = 1;
        int i = 1;
        while (i != 0) {
            showSaveDialog = this.m_fc.showSaveDialog(component);
            if (showSaveDialog == 0) {
                final String absolutePath = this.m_fc.getSelectedFile().getAbsolutePath();
                if (new File(absolutePath).exists()) {
                    if (JOptionPane.showConfirmDialog(null, absolutePath + " already exists." + System.getProperty("line.separator") + "Do you want to replace it?", "Confirm", 0) != 0) {
                        continue;
                    }
                    i = 0;
                }
                else {
                    i = 0;
                }
            }
            else {
                i = 0;
            }
        }
        return showSaveDialog;
    }
    
    private void setXMLData(final DefaultMutableTreeNode defaultMutableTreeNode) {
        this.theTree.setModel(new DefaultTreeModel(defaultMutableTreeNode));
        this.theTree.updateUI();
        this.expandAllVisibleRows();
        this.expandAllVisibleRows();
    }
    
    public void setXMLData(final XmlPropertiesParser parser) {
        this.setXMLData(parser.getRootNode());
        this.m_parser = parser;
    }
    
    public void expandAllNodes() {
        for (int i = 0; i < this.theTree.getRowCount(); ++i) {
            this.theTree.expandRow(i);
        }
    }
    
    public void collapseAllNodes() {
        for (int i = this.theTree.getRowCount() - 1; i >= 0; --i) {
            this.theTree.collapseRow(i);
        }
    }
    
    private void expandAllVisibleRows() {
        final int visibleRowCount = this.theTree.getVisibleRowCount();
        if (visibleRowCount > 0) {
            for (int i = visibleRowCount - 1; i >= 0; --i) {
                this.theTree.expandRow(i);
            }
            this.theTree.updateUI();
        }
    }
    
    private void setupTree() {
        (this.theTree = new JTree()).putClientProperty("JTree.lineStyle", "Angled");
        this.theTree.setRootVisible(false);
        final ImageIcon loadImage = Helper.loadImage("TreeLine.gif", "");
        final ImageIcon loadImage2 = Helper.loadImage("TreeBracket.gif", "");
        final DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer();
        cellRenderer.setOpenIcon(loadImage2);
        cellRenderer.setClosedIcon(loadImage2);
        cellRenderer.setLeafIcon(loadImage);
        this.theTree.setCellRenderer(cellRenderer);
        this.theTree.updateUI();
    }
    
    private void btnCancelActionPerformed(final ActionEvent actionEvent) {
        this.cancelled = true;
        this.setVisible(false);
    }
    
    private void saveToFile(final String s) {
        try {
            final String s2 = "\n";
            final List xmlBoxStringsList = this.m_parser.getXMLBoxStringsList();
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(s));
            bufferedOutputStream.write(("<?xml version=\"1.0\"?>" + s2).getBytes());
            bufferedOutputStream.write(("<XML_DATA>" + s2).getBytes());
            final Iterator<String> iterator = xmlBoxStringsList.iterator();
            int n = 1;
            while (iterator.hasNext()) {
                final String s3 = iterator.next();
                if (s3.length() > 0) {
                    if (s3.startsWith("<?")) {
                        bufferedOutputStream.write(s3.substring(s3.indexOf(">") + 1).getBytes());
                    }
                    else {
                        bufferedOutputStream.write(s3.getBytes());
                    }
                }
                ++n;
            }
            bufferedOutputStream.write((s2 + "</XML_DATA>").getBytes());
            bufferedOutputStream.close();
        }
        catch (FileNotFoundException ex) {
            this.log.error(ex);
        }
        catch (IOException ex2) {
            this.log.error(ex2);
        }
    }
    
    public static void main(final String[] array) {
        new XmlPropertiesDialog(new JFrame(), true, null).setVisible(true);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
