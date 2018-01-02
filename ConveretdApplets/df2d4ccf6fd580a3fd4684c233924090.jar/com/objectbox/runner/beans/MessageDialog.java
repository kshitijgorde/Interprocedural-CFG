// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.beans;

import com.objectbox.runner.util.JBLogger;
import com.objectbox.runner.gui.SecurityWarningDialog;
import java.awt.Toolkit;
import com.objectbox.runner.gui.AppRegistry;
import com.objectbox.runner.gui.JBee;
import java.awt.Insets;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import com.objectbox.runner.gui.CustomTextArea;
import java.awt.Label;
import com.objectbox.gui.lwcomp.FlatButton;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class MessageDialog extends Frame implements ActionListener
{
    private Panel ivjContentsPane;
    private FlatButton ivjFlatButtonOK;
    private Panel ivjPanelButtons;
    private Label ivjLabelMessage;
    private CustomTextArea ivjTextAreaMessage;
    static Class class$java$awt$Window;
    
    public MessageDialog() {
        this.ivjContentsPane = null;
        this.ivjFlatButtonOK = null;
        this.ivjPanelButtons = null;
        this.ivjLabelMessage = null;
        this.ivjTextAreaMessage = null;
        this.initialize();
    }
    
    public MessageDialog(final String text) {
        this.ivjContentsPane = null;
        this.ivjFlatButtonOK = null;
        this.ivjPanelButtons = null;
        this.ivjLabelMessage = null;
        this.ivjTextAreaMessage = null;
        this.getTextAreaMessage().setText(text);
        this.initialize();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.getFlatButtonOK()) {
            this.connEtoC2(actionEvent);
        }
    }
    
    public void appendMessage(final String s) {
        if (this.getTextAreaMessage().getText().length() > 30000) {
            this.getTextAreaMessage().setText("");
        }
        this.getTextAreaMessage().append("\n\n" + s);
    }
    
    private void connEtoC2(final ActionEvent actionEvent) {
        try {
            this.flatButtonOK_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    public void flatButtonCancel_ActionPerformed(final ActionEvent actionEvent) {
        this.dispose();
    }
    
    public void flatButtonOK_ActionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
        this.setMessage("");
    }
    
    private Panel getContentsPane() {
        if (this.ivjContentsPane == null) {
            try {
                (this.ivjContentsPane = new Panel()).setName("ContentsPane");
                this.ivjContentsPane.setLayout(new BorderLayout());
                this.getContentsPane().add(this.getPanelButtons(), "South");
                this.getContentsPane().add(this.getLabelMessage(), "North");
                this.getContentsPane().add(this.getTextAreaMessage(), "Center");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjContentsPane;
    }
    
    private FlatButton getFlatButtonOK() {
        if (this.ivjFlatButtonOK == null) {
            try {
                (this.ivjFlatButtonOK = new FlatButton()).setName("FlatButtonOK");
                this.ivjFlatButtonOK.setFixedsize(new Dimension(75, 35));
                this.ivjFlatButtonOK.setLabel("Close");
                this.ivjFlatButtonOK.setImageResource("/images/ok.gif", 3);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonOK;
    }
    
    private Label getLabelMessage() {
        if (this.ivjLabelMessage == null) {
            try {
                (this.ivjLabelMessage = new Label()).setName("LabelMessage");
                this.ivjLabelMessage.setText("");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelMessage;
    }
    
    private Panel getPanelButtons() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        if (this.ivjPanelButtons == null) {
            try {
                (this.ivjPanelButtons = new Panel()).setName("PanelButtons");
                this.ivjPanelButtons.setLayout(new GridBagLayout());
                this.ivjPanelButtons.setBackground(new Color(182, 182, 200));
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.anchor = 10;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.0;
                gridBagConstraints.insets = new Insets(10, 10, 10, 10);
                this.getPanelButtons().add(this.getFlatButtonOK(), gridBagConstraints);
                this.ivjPanelButtons.setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelButtons;
    }
    
    private CustomTextArea getTextAreaMessage() {
        if (this.ivjTextAreaMessage == null) {
            try {
                (this.ivjTextAreaMessage = new CustomTextArea()).setName("TextAreaMessage");
                this.ivjTextAreaMessage.setForeground(Color.blue);
                this.ivjTextAreaMessage.setEditable(false);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTextAreaMessage;
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initConnections() {
        this.getFlatButtonOK().addActionListener(this);
    }
    
    private void initialize() {
        AppRegistry.getInstance().put("SecurityWarningDialog", this);
        this.setName("MessageDialog");
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(182, 182, 200));
        this.setSize(307, 244);
        this.setTitle("JBee message");
        this.add(this.getContentsPane(), "Center");
        this.initConnections();
        this.setBackground(JBee.appcolor);
        this.setIconImage(JBee.getIcon());
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().height / 2);
    }
    
    public static void main(final String[] array) {
        try {
            final SecurityWarningDialog securityWarningDialog = new SecurityWarningDialog();
            try {
                final Class<?> forName = Class.forName("com.ibm.uvm.abt.edit.WindowCloser");
                final Class[] array2 = { null };
                final int n = 0;
                Class class$java$awt$Window;
                if ((class$java$awt$Window = MessageDialog.class$java$awt$Window) == null) {
                    try {
                        class$java$awt$Window = (MessageDialog.class$java$awt$Window = Class.forName("java.awt.Window"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array2[n] = class$java$awt$Window;
                forName.getConstructor((Class<?>[])array2).newInstance(securityWarningDialog);
            }
            catch (Throwable t) {}
            securityWarningDialog.setVisible(true);
        }
        catch (Throwable t2) {
            JBLogger.log("Exception occurred in main() of java.awt.Dialog");
        }
    }
    
    public void setMessage(final String text) {
        this.getTextAreaMessage().setText(text);
    }
}
