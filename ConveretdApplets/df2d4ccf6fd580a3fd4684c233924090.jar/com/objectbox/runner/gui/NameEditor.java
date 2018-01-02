// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.Enumeration;
import com.objectbox.runner.gui.tree.Node;
import java.util.Vector;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import com.objectbox.gui.lwcomp.DoubleBufferPanel;
import java.awt.TextField;
import java.awt.Label;
import com.objectbox.gui.lwcomp.FlatButton;
import java.awt.Panel;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class NameEditor extends Frame implements ActionListener, WindowListener
{
    private Panel ivjContentsPane;
    private FlatButton ivjFlatButtonCancel;
    private FlatButton ivjFlatButtonOK;
    private Label ivjLabelRename;
    private TextField ivjTextFieldFolderName;
    private DoubleBufferPanel ivjDoubleBufferPanelButtons;
    static Class class$java$awt$Window;
    
    public NameEditor() {
        this.ivjContentsPane = null;
        this.ivjFlatButtonCancel = null;
        this.ivjFlatButtonOK = null;
        this.ivjLabelRename = null;
        this.ivjTextFieldFolderName = null;
        this.ivjDoubleBufferPanelButtons = null;
        this.initialize();
    }
    
    public NameEditor(final String s) {
        super(s);
        this.ivjContentsPane = null;
        this.ivjFlatButtonCancel = null;
        this.ivjFlatButtonOK = null;
        this.ivjLabelRename = null;
        this.ivjTextFieldFolderName = null;
        this.ivjDoubleBufferPanelButtons = null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.getFlatButtonCancel()) {
            this.connEtoM1();
        }
        if (actionEvent.getSource() == this.getFlatButtonOK()) {
            this.connEtoC2();
        }
        if (actionEvent.getSource() == this.getTextFieldFolderName()) {
            this.connEtoC3(actionEvent);
        }
    }
    
    private void connEtoC1(final WindowEvent windowEvent) {
        try {
            this.dispose();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC2() {
        try {
            this.flatButtonOK_Action();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC3(final ActionEvent actionEvent) {
        try {
            this.flatButtonOK_Action();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoM1() {
        try {
            this.dispose();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    public void flatButtonOK_Action() {
        final JBManagerPanel jbManagerPanel = (JBManagerPanel)AppRegistry.getInstance().lookup("Manager");
        Node selectedNode = jbManagerPanel.getTreeBasePublic().getSelectedNode();
        if (selectedNode != null && selectedNode.getType().equals("Folder")) {
            selectedNode.setText(this.getTextFieldFolderName().getText());
            ((JBee)AppRegistry.getInstance().lookup("JBee")).updateName(selectedNode, this.getTextFieldFolderName().getText());
            final Object lookup = AppRegistry.getInstance().lookup("LabelFolder");
            if (lookup != null && selectedNode != null) {
                final Vector<String> vector = new Vector<String>();
                while (selectedNode != jbManagerPanel.rootNode) {
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
            jbManagerPanel.updateVisual();
        }
        this.dispose();
    }
    
    private Panel getContentsPane() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        if (this.ivjContentsPane == null) {
            try {
                (this.ivjContentsPane = new Panel()).setName("ContentsPane");
                this.ivjContentsPane.setLayout(new GridBagLayout());
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 1;
                gridBagConstraints.gridwidth = 2;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.fill = 2;
                gridBagConstraints.anchor = 10;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 0.0;
                gridBagConstraints.ipadx = 106;
                gridBagConstraints.insets = new Insets(10, 25, 10, 25);
                this.getContentsPane().add(this.getTextFieldFolderName(), gridBagConstraints);
                gridBagConstraints2.gridx = 0;
                gridBagConstraints2.gridy = 0;
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 1;
                gridBagConstraints2.fill = 2;
                gridBagConstraints2.anchor = 10;
                gridBagConstraints2.weightx = 1.0;
                gridBagConstraints2.weighty = 0.0;
                gridBagConstraints2.ipadx = 15;
                gridBagConstraints2.insets = new Insets(10, 25, 0, 25);
                this.getContentsPane().add(this.getLabelRename(), gridBagConstraints2);
                gridBagConstraints3.gridx = 0;
                gridBagConstraints3.gridy = 2;
                gridBagConstraints3.gridwidth = 1;
                gridBagConstraints3.gridheight = 1;
                gridBagConstraints3.fill = 2;
                gridBagConstraints3.anchor = 10;
                gridBagConstraints3.weightx = 0.0;
                gridBagConstraints3.weighty = 0.0;
                gridBagConstraints3.insets = new Insets(5, 10, 0, 10);
                this.getContentsPane().add(this.getDoubleBufferPanelButtons(), gridBagConstraints3);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjContentsPane;
    }
    
    private DoubleBufferPanel getDoubleBufferPanelButtons() {
        if (this.ivjDoubleBufferPanelButtons == null) {
            try {
                (this.ivjDoubleBufferPanelButtons = new DoubleBufferPanel()).setName("DoubleBufferPanelButtons");
                this.ivjDoubleBufferPanelButtons.setLayout(new FlowLayout());
                this.ivjDoubleBufferPanelButtons.setHasframe(false);
                this.getDoubleBufferPanelButtons().add(this.getFlatButtonOK(), this.getFlatButtonOK().getName());
                this.ivjDoubleBufferPanelButtons.add(this.getFlatButtonCancel());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjDoubleBufferPanelButtons;
    }
    
    private FlatButton getFlatButtonCancel() {
        if (this.ivjFlatButtonCancel == null) {
            try {
                (this.ivjFlatButtonCancel = new FlatButton()).setName("FlatButtonCancel");
                this.ivjFlatButtonCancel.setFixedsize(new Dimension(73, 25));
                this.ivjFlatButtonCancel.setLabel("Cancel");
                this.ivjFlatButtonCancel.setImageResource("/images/cancel.gif", 3);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonCancel;
    }
    
    private FlatButton getFlatButtonOK() {
        if (this.ivjFlatButtonOK == null) {
            try {
                (this.ivjFlatButtonOK = new FlatButton()).setName("FlatButtonOK");
                this.ivjFlatButtonOK.setFixedsize(new Dimension(73, 25));
                this.ivjFlatButtonOK.setLabel("OK");
                this.ivjFlatButtonOK.setImageResource("/images/ok.gif", 3);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonOK;
    }
    
    private Label getLabelRename() {
        if (this.ivjLabelRename == null) {
            try {
                (this.ivjLabelRename = new Label()).setName("LabelRename");
                this.ivjLabelRename.setText("New folder name:");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelRename;
    }
    
    private TextField getTextFieldFolderName() {
        if (this.ivjTextFieldFolderName == null) {
            try {
                (this.ivjTextFieldFolderName = new TextField()).setName("TextFieldFolderName");
                this.ivjTextFieldFolderName.setBackground(Color.white);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTextFieldFolderName;
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initConnections() {
        this.addWindowListener(this);
        this.getFlatButtonCancel().addActionListener(this);
        this.getFlatButtonOK().addActionListener(this);
        this.getTextFieldFolderName().addActionListener(this);
    }
    
    private void initialize() {
        this.setIconImage(JBee.getIcon());
        this.setName("NameEditor");
        this.setTitle("JBee - rename folder");
        this.setLayout(new BorderLayout());
        this.setSize(282, 149);
        this.setResizable(false);
        this.add(this.getContentsPane(), "Center");
        this.initConnections();
        this.setBackground(JBee.appcolor);
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().height / 2);
    }
    
    public static void main(final String[] array) {
        try {
            final NameEditor nameEditor = new NameEditor();
            try {
                final Class<?> forName = Class.forName("com.ibm.uvm.abt.edit.WindowCloser");
                final Class[] array2 = { null };
                final int n = 0;
                Class class$java$awt$Window;
                if ((class$java$awt$Window = NameEditor.class$java$awt$Window) == null) {
                    try {
                        class$java$awt$Window = (NameEditor.class$java$awt$Window = Class.forName("java.awt.Window"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array2[n] = class$java$awt$Window;
                forName.getConstructor((Class<?>[])array2).newInstance(nameEditor);
            }
            catch (Throwable t2) {}
            nameEditor.setVisible(true);
        }
        catch (Throwable t) {
            System.err.println("Exception occurred in main() of java.awt.Frame");
            t.printStackTrace(System.out);
        }
    }
    
    public void setFolderName(final String text) {
        this.getTextFieldFolderName().setText(text);
        this.getTextFieldFolderName().setSelectionStart(0);
        this.getTextFieldFolderName().setSelectionEnd(text.length());
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (windowEvent.getSource() == this) {
            this.connEtoC1(windowEvent);
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
