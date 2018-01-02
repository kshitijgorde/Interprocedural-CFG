// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import com.objectbox.runner.util.JBLogger;
import java.awt.Frame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.util.Hashtable;
import java.util.Properties;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Panel;

public class SetupPanel extends Panel
{
    private Label ivjLabel1;
    private Label ivjLabel2;
    private Checkbox ivjCheckbox_bytecache;
    private Checkbox ivjCheckbox_imagecache;
    private Checkbox ivjCheckbox_version;
    private Label ivjLabel;
    private Label ivjLabel4;
    private Panel ivjPanelSetupMain;
    private Properties props;
    private Hashtable secmodels;
    private Label ivjLabel3;
    private Label ivjLabel5;
    private Panel ivjPanelProxy;
    private TextField ivjTextFieldProxyPort;
    private TextField ivjTextFieldProxyServerAddress;
    private Checkbox ivjCheckboxProxy;
    
    public SetupPanel() {
        this.ivjLabel1 = null;
        this.ivjLabel2 = null;
        this.ivjCheckbox_bytecache = null;
        this.ivjCheckbox_imagecache = null;
        this.ivjCheckbox_version = null;
        this.ivjLabel = null;
        this.ivjLabel4 = null;
        this.ivjPanelSetupMain = null;
        this.props = null;
        this.secmodels = null;
        this.ivjLabel3 = null;
        this.ivjLabel5 = null;
        this.ivjPanelProxy = null;
        this.ivjTextFieldProxyPort = null;
        this.ivjTextFieldProxyServerAddress = null;
        this.ivjCheckboxProxy = null;
        this.initialize();
    }
    
    public SetupPanel(final LayoutManager layoutManager) {
        super(layoutManager);
        this.ivjLabel1 = null;
        this.ivjLabel2 = null;
        this.ivjCheckbox_bytecache = null;
        this.ivjCheckbox_imagecache = null;
        this.ivjCheckbox_version = null;
        this.ivjLabel = null;
        this.ivjLabel4 = null;
        this.ivjPanelSetupMain = null;
        this.props = null;
        this.secmodels = null;
        this.ivjLabel3 = null;
        this.ivjLabel5 = null;
        this.ivjPanelProxy = null;
        this.ivjTextFieldProxyPort = null;
        this.ivjTextFieldProxyServerAddress = null;
        this.ivjCheckboxProxy = null;
    }
    
    public void button_proxy_ActionPerformed(final ActionEvent actionEvent) {
    }
    
    public void buttonproxyok_ActionPerformed(final ActionEvent actionEvent) {
    }
    
    public void checkbox_proxy_ItemStateChanged(final ItemEvent itemEvent) {
    }
    
    private Checkbox getCheckbox_bytecache() {
        if (this.ivjCheckbox_bytecache == null) {
            try {
                (this.ivjCheckbox_bytecache = new Checkbox()).setName("Checkbox_bytecache");
                this.ivjCheckbox_bytecache.setLabel("");
                this.ivjCheckbox_bytecache.setState(true);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckbox_bytecache;
    }
    
    private Checkbox getCheckbox_imagecache() {
        if (this.ivjCheckbox_imagecache == null) {
            try {
                (this.ivjCheckbox_imagecache = new Checkbox()).setName("Checkbox_imagecache");
                this.ivjCheckbox_imagecache.setLabel("");
                this.ivjCheckbox_imagecache.setState(true);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckbox_imagecache;
    }
    
    private Checkbox getCheckbox_version() {
        if (this.ivjCheckbox_version == null) {
            try {
                (this.ivjCheckbox_version = new Checkbox()).setName("Checkbox_version");
                this.ivjCheckbox_version.setLabel("");
                this.ivjCheckbox_version.setState(true);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckbox_version;
    }
    
    private Checkbox getCheckboxProxy() {
        if (this.ivjCheckboxProxy == null) {
            try {
                (this.ivjCheckboxProxy = new Checkbox()).setName("CheckboxProxy");
                this.ivjCheckboxProxy.setEnabled(true);
                this.ivjCheckboxProxy.setLabel("");
                this.ivjCheckboxProxy.setState(false);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckboxProxy;
    }
    
    private Label getLabel() {
        if (this.ivjLabel == null) {
            try {
                (this.ivjLabel = new Label()).setName("Label");
                this.ivjLabel.setText("Use bytecode cache");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabel;
    }
    
    private Label getLabel1() {
        if (this.ivjLabel1 == null) {
            try {
                (this.ivjLabel1 = new Label()).setName("Label1");
                this.ivjLabel1.setText("Use proxy server:");
                this.ivjLabel1.setEnabled(true);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabel1;
    }
    
    private Label getLabel2() {
        if (this.ivjLabel2 == null) {
            try {
                (this.ivjLabel2 = new Label()).setName("Label2");
                this.ivjLabel2.setText("Always check for new versions (jar's)");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabel2;
    }
    
    private Label getLabel3() {
        if (this.ivjLabel3 == null) {
            try {
                (this.ivjLabel3 = new Label()).setName("Label3");
                this.ivjLabel3.setFont(new Font("dialog", 0, 10));
                this.ivjLabel3.setText("Host");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabel3;
    }
    
    private Label getLabel4() {
        if (this.ivjLabel4 == null) {
            try {
                (this.ivjLabel4 = new Label()).setName("Label4");
                this.ivjLabel4.setText("Use image cache");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabel4;
    }
    
    private Label getLabel5() {
        if (this.ivjLabel5 == null) {
            try {
                (this.ivjLabel5 = new Label()).setName("Label5");
                this.ivjLabel5.setFont(new Font("dialog", 0, 10));
                this.ivjLabel5.setText("Port");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabel5;
    }
    
    private Panel getPanelProxy() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        if (this.ivjPanelProxy == null) {
            try {
                (this.ivjPanelProxy = new Panel()).setName("PanelProxy");
                this.ivjPanelProxy.setLayout(new GridBagLayout());
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 1;
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.fill = 2;
                gridBagConstraints.anchor = 17;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 0.0;
                gridBagConstraints.ipadx = 50;
                gridBagConstraints.insets = new Insets(0, 0, 0, 10);
                this.getPanelProxy().add(this.getTextFieldProxyServerAddress(), gridBagConstraints);
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 1;
                gridBagConstraints2.gridwidth = 2;
                gridBagConstraints2.gridheight = 1;
                gridBagConstraints2.fill = 2;
                gridBagConstraints2.anchor = 17;
                gridBagConstraints2.weightx = 0.0;
                gridBagConstraints2.weighty = 0.0;
                this.getPanelProxy().add(this.getTextFieldProxyPort(), gridBagConstraints2);
                gridBagConstraints3.gridx = 0;
                gridBagConstraints3.gridy = 0;
                gridBagConstraints3.gridwidth = 1;
                gridBagConstraints3.gridheight = 1;
                gridBagConstraints3.anchor = 17;
                gridBagConstraints3.weightx = 0.0;
                gridBagConstraints3.weighty = 0.0;
                gridBagConstraints3.insets = new Insets(0, 10, 0, 0);
                this.getPanelProxy().add(this.getLabel3(), gridBagConstraints3);
                gridBagConstraints4.gridx = 1;
                gridBagConstraints4.gridy = 0;
                gridBagConstraints4.gridwidth = 1;
                gridBagConstraints4.gridheight = 1;
                gridBagConstraints4.anchor = 17;
                gridBagConstraints4.weightx = 0.0;
                gridBagConstraints4.weighty = 0.0;
                this.getPanelProxy().add(this.getLabel5(), gridBagConstraints4);
                this.getPanelProxy().setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelProxy;
    }
    
    private Panel getPanelSetupMain() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
        if (this.ivjPanelSetupMain == null) {
            try {
                (this.ivjPanelSetupMain = new Panel()).setName("PanelSetupMain");
                this.ivjPanelSetupMain.setLayout(new GridBagLayout());
                this.ivjPanelSetupMain.setBackground(SystemColor.control);
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.fill = 2;
                gridBagConstraints.anchor = 11;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.0;
                gridBagConstraints.insets = new Insets(10, 20, 0, 0);
                this.getPanelSetupMain().add(this.getLabel2(), gridBagConstraints);
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 0;
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 1;
                gridBagConstraints2.anchor = 11;
                gridBagConstraints2.weightx = 0.0;
                gridBagConstraints2.weighty = 0.0;
                gridBagConstraints2.insets = new Insets(15, 0, 0, 0);
                this.getPanelSetupMain().add(this.getCheckbox_version(), gridBagConstraints2);
                gridBagConstraints3.gridx = 0;
                gridBagConstraints3.gridy = 1;
                gridBagConstraints3.gridwidth = 1;
                gridBagConstraints3.gridheight = 1;
                gridBagConstraints3.fill = 2;
                gridBagConstraints3.anchor = 11;
                gridBagConstraints3.weightx = 0.0;
                gridBagConstraints3.weighty = 0.0;
                gridBagConstraints3.insets = new Insets(5, 20, 0, 0);
                this.getPanelSetupMain().add(this.getLabel(), gridBagConstraints3);
                gridBagConstraints4.gridx = 1;
                gridBagConstraints4.gridy = 1;
                gridBagConstraints4.gridwidth = 1;
                gridBagConstraints4.gridheight = 1;
                gridBagConstraints4.anchor = 11;
                gridBagConstraints4.weightx = 0.0;
                gridBagConstraints4.weighty = 0.0;
                gridBagConstraints4.insets = new Insets(10, 0, 0, 0);
                this.getPanelSetupMain().add(this.getCheckbox_bytecache(), gridBagConstraints4);
                gridBagConstraints5.gridx = 0;
                gridBagConstraints5.gridy = 2;
                gridBagConstraints5.gridwidth = 1;
                gridBagConstraints5.gridheight = 1;
                gridBagConstraints5.fill = 2;
                gridBagConstraints5.anchor = 11;
                gridBagConstraints5.weightx = 0.0;
                gridBagConstraints5.weighty = 0.0;
                gridBagConstraints5.insets = new Insets(5, 20, 0, 0);
                this.getPanelSetupMain().add(this.getLabel4(), gridBagConstraints5);
                gridBagConstraints6.gridx = 1;
                gridBagConstraints6.gridy = 2;
                gridBagConstraints6.gridwidth = 1;
                gridBagConstraints6.gridheight = 1;
                gridBagConstraints6.anchor = 11;
                gridBagConstraints6.weightx = 0.0;
                gridBagConstraints6.weighty = 0.0;
                gridBagConstraints6.insets = new Insets(10, 0, 0, 0);
                this.getPanelSetupMain().add(this.getCheckbox_imagecache(), gridBagConstraints6);
                gridBagConstraints7.gridx = 0;
                gridBagConstraints7.gridy = 3;
                gridBagConstraints7.gridwidth = 1;
                gridBagConstraints7.gridheight = 1;
                gridBagConstraints7.fill = 2;
                gridBagConstraints7.anchor = 11;
                gridBagConstraints7.weightx = 0.0;
                gridBagConstraints7.weighty = 0.0;
                gridBagConstraints7.insets = new Insets(5, 20, 0, 0);
                this.getPanelSetupMain().add(this.getLabel1(), gridBagConstraints7);
                gridBagConstraints8.gridx = 1;
                gridBagConstraints8.gridy = 3;
                gridBagConstraints8.gridwidth = 1;
                gridBagConstraints8.gridheight = 1;
                gridBagConstraints8.anchor = 11;
                gridBagConstraints8.weightx = 0.0;
                gridBagConstraints8.weighty = 0.0;
                gridBagConstraints8.insets = new Insets(10, 0, 0, 0);
                this.getPanelSetupMain().add(this.getCheckboxProxy(), gridBagConstraints8);
                gridBagConstraints9.gridx = 0;
                gridBagConstraints9.gridy = 4;
                gridBagConstraints9.gridwidth = 2;
                gridBagConstraints9.gridheight = 1;
                gridBagConstraints9.fill = 2;
                gridBagConstraints9.anchor = 11;
                gridBagConstraints9.weightx = 0.0;
                gridBagConstraints9.weighty = 0.0;
                gridBagConstraints9.insets = new Insets(5, 20, 0, 0);
                this.getPanelSetupMain().add(this.getPanelProxy(), gridBagConstraints9);
                this.getPanelSetupMain().setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelSetupMain;
    }
    
    public Properties getProperties() {
        return this.props;
    }
    
    public Hashtable getSecurityModels() {
        return this.secmodels;
    }
    
    private TextField getTextFieldProxyPort() {
        if (this.ivjTextFieldProxyPort == null) {
            try {
                (this.ivjTextFieldProxyPort = new TextField()).setName("TextFieldProxyPort");
                this.ivjTextFieldProxyPort.setColumns(3);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTextFieldProxyPort;
    }
    
    private TextField getTextFieldProxyServerAddress() {
        if (this.ivjTextFieldProxyServerAddress == null) {
            try {
                (this.ivjTextFieldProxyServerAddress = new TextField()).setName("TextFieldProxyServerAddress");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTextFieldProxyServerAddress;
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initialize() {
        this.setName("SetupPanel");
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(182, 182, 200));
        this.setSize(285, 221);
        this.add(this.getPanelSetupMain(), "Center");
        this.setBackground(JBee.appcolor);
    }
    
    public static void main(final String[] array) {
        try {
            Frame frame;
            try {
                frame = (Frame)Class.forName("com.ibm.uvm.abt.edit.TestFrame").newInstance();
            }
            catch (Throwable t) {
                frame = new Frame();
            }
            final SetupPanel setupPanel = new SetupPanel();
            frame.add("Center", setupPanel);
            frame.setSize(setupPanel.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t2) {
            JBLogger.log("Exception occurred in main() of java.awt.Panel");
        }
    }
    
    public void setProperties() {
        if (this.getCheckbox_version().getState()) {
            ((Hashtable<String, String>)this.props).put("checkversion", "true");
        }
        else {
            ((Hashtable<String, String>)this.props).put("checkversion", "false");
        }
        if (this.getCheckbox_bytecache().getState()) {
            ((Hashtable<String, String>)this.props).put("usebytecodecache", "true");
        }
        else {
            ((Hashtable<String, String>)this.props).put("usebytecodecache", "false");
        }
        if (this.getCheckbox_imagecache().getState()) {
            ((Hashtable<String, String>)this.props).put("useimagecache", "true");
        }
        else {
            ((Hashtable<String, String>)this.props).put("useimagecache", "false");
        }
        if (this.getCheckboxProxy().getState()) {
            ((Hashtable<String, String>)this.props).put("useproxy", "true");
        }
        else {
            ((Hashtable<String, String>)this.props).put("useproxy", "false");
        }
        ((Hashtable<String, String>)this.props).put("proxyserver", this.getTextFieldProxyServerAddress().getText());
        ((Hashtable<String, String>)this.props).put("proxyport", this.getTextFieldProxyPort().getText());
    }
    
    public void setProperties(final Properties props) {
        this.props = props;
        this.updateProperties();
    }
    
    public void setSecurityModels(final Hashtable secmodels) {
        this.secmodels = secmodels;
        this.updateSecurity();
    }
    
    public void updateProperties() {
        final String property = this.props.getProperty("checkversion");
        final String property2 = this.props.getProperty("usebytecodecache");
        final String property3 = this.props.getProperty("useimagecache");
        final String property4 = this.props.getProperty("useproxy");
        final String property5 = this.props.getProperty("proxyserver");
        final String property6 = this.props.getProperty("proxyport");
        if (property4 == null || property4.compareTo("false") == 0) {
            this.getCheckboxProxy().setState(false);
        }
        else {
            this.getCheckboxProxy().setState(true);
        }
        if (property5 != null) {
            this.getTextFieldProxyServerAddress().setText(property5);
        }
        if (property6 != null) {
            this.getTextFieldProxyPort().setText(property6);
        }
        if (property == null || property.compareTo("true") == 0) {
            this.getCheckbox_version().setState(true);
        }
        else {
            this.getCheckbox_version().setState(false);
        }
        if (property2 == null || property2.compareTo("true") == 0) {
            this.getCheckbox_bytecache().setState(true);
        }
        else {
            this.getCheckbox_bytecache().setState(false);
        }
        if (property3 == null || property3.compareTo("true") == 0) {
            this.getCheckbox_imagecache().setState(true);
        }
        else {
            this.getCheckbox_imagecache().setState(false);
        }
    }
    
    public void updateSecurity() {
    }
}
