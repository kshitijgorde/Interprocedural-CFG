// 
// Decompiled by Procyon v0.5.30
// 

package jap.pay.wizardnew;

import java.util.StringTokenizer;
import logging.LogType;
import anon.pay.xml.XMLVolumePlan;
import java.util.Enumeration;
import javax.swing.JLabel;
import jap.JAPUtil;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.JRadioButton;
import gui.JAPHtmlMultiLineLabel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import gui.dialog.DialogContentPaneOptions;
import anon.util.JAPMessages;
import gui.dialog.WorkerContentPane;
import gui.dialog.JAPDialog;
import java.awt.Container;
import anon.pay.xml.XMLPaymentOption;
import java.awt.GridBagConstraints;
import anon.pay.xml.XMLPaymentOptions;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import gui.dialog.DialogContentPane;

public class MethodSelectionPane extends DialogContentPane implements IWizardSuitable, ActionListener
{
    private static final int SHOW_MARKUP_IF_ABOVE = 5;
    private static final String MSG_PRICE;
    private static final String MSG_SELECTOPTION;
    private static final String MSG_ERRSELECT;
    private static final String MSG_NOTSUPPORTED;
    private static final String MSG_SELECTED_PLAN;
    private static final String MSG_MARKUP;
    private static final String MSG_MARKUP_CAPTION;
    private ButtonGroup m_rbGroup;
    private XMLPaymentOptions m_paymentOptions;
    private GridBagConstraints m_c;
    private XMLPaymentOption m_selectedPaymentOption;
    private Container m_rootPanel;
    XMLPaymentOptions m_options;
    static /* synthetic */ Class class$jap$pay$wizardnew$MethodSelectionPane;
    
    public MethodSelectionPane(final JAPDialog japDialog, final WorkerContentPane workerContentPane) {
        super(japDialog, "", new Layout(JAPMessages.getString(MethodSelectionPane.MSG_SELECTOPTION), -1), new DialogContentPaneOptions(2, workerContentPane));
        this.m_c = new GridBagConstraints();
        this.setDefaultButtonOperation(266);
        this.m_rootPanel = this.getContentPane();
        this.m_c = new GridBagConstraints();
        this.m_rootPanel.setLayout(new GridBagLayout());
        this.m_rbGroup = new ButtonGroup();
        this.m_c = new GridBagConstraints();
        this.m_c.gridx = 0;
        this.m_c.gridy = 0;
        this.m_c.weightx = 0.0;
        this.m_c.weightx = 0.0;
        this.m_c.insets = new Insets(5, 5, 5, 5);
        this.m_c.anchor = 18;
        this.m_c.fill = 0;
        for (int i = 0; i < 6; ++i) {
            this.addOption("Dummy", "0");
        }
    }
    
    private void addOption(final String name, final String s) {
        this.m_c.insets = new Insets(0, 5, 0, 5);
        final GridBagConstraints c = this.m_c;
        ++c.gridy;
        final JRadioButton radioButton = new JRadioButton(new JAPHtmlMultiLineLabel(name).getHTMLDocumentText() + s);
        radioButton.setName(name);
        radioButton.addActionListener(this);
        this.m_rbGroup.add(radioButton);
        this.m_rootPanel.add(radioButton, this.m_c);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof JRadioButton) {
            this.m_selectedPaymentOption = this.m_paymentOptions.getOption(((JRadioButton)actionEvent.getSource()).getName(), JAPMessages.getLocale().getLanguage());
        }
    }
    
    public XMLPaymentOption getSelectedPaymentOption() {
        return this.m_selectedPaymentOption;
    }
    
    public void showPaymentOptions() {
        final WorkerContentPane workerContentPane = (WorkerContentPane)this.getPreviousContentPane();
        final XMLPaymentOptions xmlPaymentOptions = (XMLPaymentOptions)workerContentPane.getValue();
        if (this.m_options != null && this.m_selectedPaymentOption != null && this.m_options == xmlPaymentOptions) {
            return;
        }
        this.m_selectedPaymentOption = null;
        this.m_options = xmlPaymentOptions;
        this.m_rootPanel.removeAll();
        this.m_c = new GridBagConstraints();
        this.m_c.gridx = 0;
        this.m_c.gridy = 0;
        this.m_c.weightx = 0.0;
        this.m_c.weightx = 0.0;
        this.m_c.insets = new Insets(5, 5, 5, 5);
        this.m_c.anchor = 18;
        this.m_c.fill = 0;
        this.m_paymentOptions = xmlPaymentOptions;
        final Enumeration<XMLPaymentOption> elements = (Enumeration<XMLPaymentOption>)this.m_paymentOptions.getAllOptionsSortedByRank(JAPMessages.getLocale().getLanguage()).elements();
        while (elements.hasMoreElements()) {
            final XMLPaymentOption xmlPaymentOption = elements.nextElement();
            if (!xmlPaymentOption.worksWithJapVersion("00.12.005")) {
                continue;
            }
            final String language = JAPMessages.getLocale().getLanguage();
            xmlPaymentOption.getMarkup();
            this.addOption(xmlPaymentOption.getHeading(language), "");
        }
        DialogContentPane previousContentPane;
        for (previousContentPane = workerContentPane; !(previousContentPane instanceof VolumePlanSelectionPane); previousContentPane = previousContentPane.getPreviousContentPane()) {}
        final XMLVolumePlan selectedVolumePlan = ((VolumePlanSelectionPane)previousContentPane).getSelectedVolumePlan();
        final String displayName = selectedVolumePlan.getDisplayName();
        final String formatEuroCentValue = JAPUtil.formatEuroCentValue(selectedVolumePlan.getPrice());
        final JLabel label = new JLabel(JAPMessages.getString(MethodSelectionPane.MSG_SELECTED_PLAN));
        final JLabel label2 = new JLabel(displayName + " (" + formatEuroCentValue + ")");
        this.m_c.insets = new Insets(30, 5, 0, 5);
        final GridBagConstraints c = this.m_c;
        ++c.gridy;
        this.m_rootPanel.add(label, this.m_c);
        this.m_c.insets = new Insets(5, 5, 0, 5);
        final GridBagConstraints c2 = this.m_c;
        ++c2.gridy;
        this.m_rootPanel.add(label2, this.m_c);
    }
    
    public CheckError[] checkYesOK() {
        final CheckError[] array = { null };
        boolean b = false;
        if (this.m_selectedPaymentOption == null) {
            array[0] = new CheckError(JAPMessages.getString(MethodSelectionPane.MSG_ERRSELECT), LogType.PAY);
            return array;
        }
        if (!this.m_selectedPaymentOption.isGeneric()) {
            final StringTokenizer stringTokenizer = new StringTokenizer("CreditCard", ",");
            while (stringTokenizer.hasMoreTokens()) {
                if (this.m_selectedPaymentOption.getName().equalsIgnoreCase(stringTokenizer.nextToken())) {
                    b = true;
                }
            }
            if (!b) {
                array[0] = new CheckError(JAPMessages.getString(MethodSelectionPane.MSG_NOTSUPPORTED), LogType.PAY);
                return array;
            }
        }
        return null;
    }
    
    public CheckError[] checkUpdate() {
        this.showPaymentOptions();
        return null;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MSG_PRICE = ((MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane == null) ? (MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane = class$("jap.pay.wizardnew.MethodSelectionPane")) : MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane).getName() + "_price";
        MSG_SELECTOPTION = ((MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane == null) ? (MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane = class$("jap.pay.wizardnew.MethodSelectionPane")) : MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane).getName() + "_selectoption";
        MSG_ERRSELECT = ((MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane == null) ? (MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane = class$("jap.pay.wizardnew.MethodSelectionPane")) : MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane).getName() + "_errselect";
        MSG_NOTSUPPORTED = ((MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane == null) ? (MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane = class$("jap.pay.wizardnew.MethodSelectionPane")) : MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane).getName() + "_notsupported";
        MSG_SELECTED_PLAN = ((MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane == null) ? (MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane = class$("jap.pay.wizardnew.MethodSelectionPane")) : MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane).getName() + "_selectedplan";
        MSG_MARKUP = ((MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane == null) ? (MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane = class$("jap.pay.wizardnew.MethodSelectionPane")) : MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane).getName() + "_markup";
        MSG_MARKUP_CAPTION = ((MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane == null) ? (MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane = class$("jap.pay.wizardnew.MethodSelectionPane")) : MethodSelectionPane.class$jap$pay$wizardnew$MethodSelectionPane).getName() + "_markupcaption";
    }
}
