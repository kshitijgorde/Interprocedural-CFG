// 
// Decompiled by Procyon v0.5.30
// 

package jap.pay.wizardnew;

import gui.GUIUtils;
import anon.pay.PaymentInstanceDBEntry;
import logging.LogHolder;
import anon.pay.BIConnection;
import jap.pay.AccountSettingsPanel;
import logging.LogType;
import java.util.Vector;
import javax.swing.JPanel;
import anon.util.Util;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import jap.JAPUtil;
import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import anon.pay.PayAccount;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import gui.dialog.DialogContentPaneOptions;
import anon.util.JAPMessages;
import gui.dialog.JAPDialog;
import gui.dialog.WorkerContentPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Container;
import java.awt.GridBagConstraints;
import gui.JapCouponField;
import anon.pay.xml.XMLVolumePlan;
import anon.pay.xml.XMLVolumePlans;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import gui.dialog.DialogContentPane;

public class VolumePlanSelectionPane extends DialogContentPane implements IWizardSuitable, ActionListener, DocumentListener
{
    private static final String MSG_PRICE;
    private static final String MSG_HEADING;
    private static final String MSG_VOLUME;
    private static final String MSG_UNLIMITED;
    private static final String MSG_ERROR_NO_PLAN_CHOSEN;
    private static final String MSG_VALIDUNTIL;
    private static final String MSG_CHOOSEAPLAN;
    private static final String MSG_ENTER_COUPON;
    private static final String MSG_PLAN_OR_COUPON;
    private static final String MSG_INVALID_COUPON;
    private static final String MSG_COUPON_INCOMPLETE;
    private XMLVolumePlans m_allPlans;
    private XMLVolumePlan m_selectedPlan;
    private JapCouponField m_coupon1;
    private JapCouponField m_coupon2;
    private JapCouponField m_coupon3;
    private JapCouponField m_coupon4;
    private GridBagConstraints m_c;
    private Container m_rootPanel;
    private ButtonGroup m_rbGroup;
    private JRadioButton m_couponButton;
    private WorkerContentPane m_fetchPlansPane;
    private boolean m_isNewAccount;
    private boolean m_isCouponUsed;
    private boolean m_hasBeenShown;
    static /* synthetic */ Class class$jap$pay$wizardnew$VolumePlanSelectionPane;
    
    public VolumePlanSelectionPane(final JAPDialog japDialog, final WorkerContentPane fetchPlansPane, final boolean isNewAccount) {
        super(japDialog, JAPMessages.getString(VolumePlanSelectionPane.MSG_CHOOSEAPLAN), new Layout(JAPMessages.getString(VolumePlanSelectionPane.MSG_HEADING), -1), new DialogContentPaneOptions(2, fetchPlansPane));
        this.m_c = new GridBagConstraints();
        this.m_hasBeenShown = false;
        this.setDefaultButtonOperation(266);
        this.m_fetchPlansPane = fetchPlansPane;
        this.m_isNewAccount = isNewAccount;
        this.m_rbGroup = new ButtonGroup();
        this.m_rootPanel = this.getContentPane();
        this.m_c = new GridBagConstraints();
        this.m_rootPanel.setLayout(new GridBagLayout());
        this.m_c.gridx = 0;
        this.m_c.gridy = 0;
        this.m_c.weightx = 0.0;
        this.m_c.weightx = 0.0;
        this.m_c.insets = new Insets(5, 5, 5, 5);
        this.m_c.anchor = 18;
        this.m_c.fill = 0;
        for (int i = 0; i < 10; ++i) {
            this.addPlan(new XMLVolumePlan("dummy", "Dummy        for sizing", 100, 2, "months", 2000000L));
        }
    }
    
    public XMLVolumePlan getSelectedVolumePlan() {
        return this.m_selectedPlan;
    }
    
    public String getEnteredCouponCode() {
        return this.getCouponString();
    }
    
    private void setCouponUsed(final boolean isCouponUsed) {
        this.m_isCouponUsed = isCouponUsed;
    }
    
    public boolean isCouponUsed() {
        return this.m_isCouponUsed;
    }
    
    public boolean isCouponComplete() {
        return PayAccount.checkCouponCode(this.getCouponString()) != null;
    }
    
    public String getAmount() {
        return new Integer(this.m_selectedPlan.getPrice()).toString();
    }
    
    public String getCurrency() {
        return new String("EUR");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof JRadioButton) {
            final String name = ((JRadioButton)actionEvent.getSource()).getName();
            if (name.equals("coupon")) {
                this.m_selectedPlan = null;
                this.setCouponUsed(true);
            }
            else {
                this.m_selectedPlan = this.m_allPlans.getVolumePlan(name);
                this.clearCouponFields();
                this.setCouponUsed(false);
            }
        }
    }
    
    public void insertUpdate(final DocumentEvent documentEvent) {
        this.m_selectedPlan = null;
        this.m_couponButton.setSelected(true);
        this.setCouponUsed(true);
    }
    
    public void removeUpdate(final DocumentEvent documentEvent) {
    }
    
    public void changedUpdate(final DocumentEvent documentEvent) {
    }
    
    private void addPlan(final XMLVolumePlan xmlVolumePlan) {
        this.m_c.insets = new Insets(0, 5, 0, 5);
        final GridBagConstraints c = this.m_c;
        ++c.gridy;
        String s = xmlVolumePlan.getDisplayName();
        if (s == null || s.equals("")) {
            s = xmlVolumePlan.getName();
        }
        final String name = xmlVolumePlan.getName();
        this.m_c.gridx = 0;
        final JRadioButton radioButton = new JRadioButton(s);
        radioButton.setName(name);
        radioButton.addActionListener(this);
        this.m_rbGroup.add(radioButton);
        this.m_rootPanel.add(radioButton, this.m_c);
        final GridBagConstraints c2 = this.m_c;
        ++c2.gridx;
        final JLabel label = new JLabel(JAPUtil.formatEuroCentValue(xmlVolumePlan.getPrice()));
        label.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
        this.m_rootPanel.add(label, this.m_c);
        final GridBagConstraints c3 = this.m_c;
        ++c3.gridx;
        JLabel label2;
        if (xmlVolumePlan.isDurationLimited()) {
            label2 = new JLabel(JAPUtil.getDuration(xmlVolumePlan.getDuration(), xmlVolumePlan.getDurationUnit()));
        }
        else {
            label2 = new JLabel(JAPMessages.getString(VolumePlanSelectionPane.MSG_UNLIMITED));
        }
        label2.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
        this.m_rootPanel.add(label2, this.m_c);
        final GridBagConstraints c4 = this.m_c;
        ++c4.gridx;
        JLabel label3;
        if (xmlVolumePlan.isVolumeLimited()) {
            label3 = new JLabel(Util.formatBytesValueWithUnit(xmlVolumePlan.getVolumeKbytes() * 1000L));
        }
        else {
            label3 = new JLabel(JAPMessages.getString(VolumePlanSelectionPane.MSG_UNLIMITED));
        }
        label3.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
        this.m_rootPanel.add(label3, this.m_c);
    }
    
    private void addCouponField() {
        final GridBagConstraints c = this.m_c;
        ++c.gridy;
        this.m_c.insets = new Insets(10, 5, 0, 5);
        this.m_c.gridx = 0;
        this.m_c.gridwidth = 4;
        final JPanel panel = new JPanel();
        (this.m_couponButton = new JRadioButton("")).setName("coupon");
        this.m_couponButton.addActionListener(this);
        this.m_rbGroup.add(this.m_couponButton);
        panel.add(this.m_couponButton);
        panel.add(new JLabel(JAPMessages.getString(VolumePlanSelectionPane.MSG_ENTER_COUPON)));
        this.m_rootPanel.add(panel, this.m_c);
        final GridBagConstraints c2 = this.m_c;
        ++c2.gridy;
        this.m_c.gridx = 0;
        this.m_c.gridwidth = 4;
        final JPanel panel2 = new JPanel();
        this.m_coupon1 = new JapCouponField();
        this.m_coupon1.getDocument().addDocumentListener(this);
        panel2.add(this.m_coupon1);
        panel2.add(new JLabel(" - "));
        this.m_coupon2 = new JapCouponField();
        this.m_coupon1.setNextCouponField(this.m_coupon2);
        this.m_coupon2.getDocument().addDocumentListener(this);
        panel2.add(this.m_coupon2);
        panel2.add(new JLabel(" - "));
        this.m_coupon3 = new JapCouponField();
        this.m_coupon2.setNextCouponField(this.m_coupon3);
        this.m_coupon3.getDocument().addDocumentListener(this);
        panel2.add(this.m_coupon3);
        panel2.add(new JLabel(" - "));
        this.m_coupon4 = new JapCouponField();
        this.m_coupon3.setNextCouponField(this.m_coupon4);
        this.m_coupon4.getDocument().addDocumentListener(this);
        panel2.add(this.m_coupon4);
        this.m_rootPanel.add(panel2, this.m_c);
    }
    
    public CheckError[] checkYesOK() {
        super.checkYesOK();
        final Vector vector = new Vector<CheckError>();
        if (!this.m_couponButton.isSelected() && this.isCouponUsed()) {
            vector.addElement(new CheckError(JAPMessages.getString(VolumePlanSelectionPane.MSG_PLAN_OR_COUPON), LogType.GUI));
        }
        if (this.m_rbGroup.getSelection() == null && !this.isCouponUsed()) {
            vector.addElement(new CheckError(JAPMessages.getString(VolumePlanSelectionPane.MSG_ERROR_NO_PLAN_CHOSEN), LogType.GUI));
        }
        if (this.isCouponUsed() && !this.isCouponComplete()) {
            vector.addElement(new CheckError(JAPMessages.getString(VolumePlanSelectionPane.MSG_COUPON_INCOMPLETE), LogType.GUI));
        }
        if (this.isCouponUsed() && this.isCouponComplete() && this.m_isNewAccount) {
            DialogContentPane dialogContentPane;
            for (dialogContentPane = this.m_fetchPlansPane; !(dialogContentPane instanceof JpiSelectionPane); dialogContentPane = dialogContentPane.getPreviousContentPane()) {}
            final PaymentInstanceDBEntry selectedPaymentInstance = ((JpiSelectionPane)dialogContentPane).getSelectedPaymentInstance();
            DialogContentPane dialogContentPane2;
            for (dialogContentPane2 = this.m_fetchPlansPane; !(dialogContentPane2 instanceof AccountSettingsPanel.AccountCreationPane); dialogContentPane2 = dialogContentPane2.getPreviousContentPane()) {}
            final PayAccount payAccount = (PayAccount)((AccountSettingsPanel.AccountCreationPane)dialogContentPane2).getValue();
            boolean checkCouponCode = false;
            try {
                final BIConnection biConnection = new BIConnection(selectedPaymentInstance);
                biConnection.connect();
                biConnection.authenticate(payAccount.getAccountCertificate(), payAccount.getPrivateKey());
                LogHolder.log(7, LogType.PAY, "Checking coupon code validity in VolumePlanSelectionPane");
                checkCouponCode = biConnection.checkCouponCode(this.getEnteredCouponCode());
                biConnection.disconnect();
            }
            catch (Exception ex) {
                if (!Thread.currentThread().isInterrupted()) {
                    LogHolder.log(2, LogType.NET, "Error while checking coupon validity: ", ex);
                    Thread.currentThread().interrupt();
                }
            }
            if (!checkCouponCode) {
                vector.addElement(new CheckError(JAPMessages.getString(VolumePlanSelectionPane.MSG_INVALID_COUPON), LogType.GUI));
            }
        }
        CheckError[] array;
        if (vector.size() > 0) {
            final int size = vector.size();
            array = new CheckError[size];
            for (int i = 0; i < size; ++i) {
                array[i] = vector.elementAt(i);
            }
        }
        else {
            array = null;
        }
        return array;
    }
    
    public CheckError[] checkUpdate() {
        if (!this.m_hasBeenShown) {
            this.m_hasBeenShown = true;
            this.showVolumePlans();
        }
        return null;
    }
    
    public void showVolumePlans() {
        this.m_allPlans = (XMLVolumePlans)this.m_fetchPlansPane.getValue();
        this.m_rootPanel.removeAll();
        this.m_c = new GridBagConstraints();
        this.m_c.gridx = 0;
        this.m_c.gridy = 0;
        this.m_c.weightx = 0.0;
        this.m_c.weightx = 0.0;
        this.m_c.insets = new Insets(5, 5, 5, 5);
        this.m_c.anchor = 18;
        this.m_c.fill = 0;
        final GridBagConstraints c = this.m_c;
        ++c.gridx;
        final JLabel label = new JLabel(JAPMessages.getString(VolumePlanSelectionPane.MSG_PRICE));
        GUIUtils.setFontStyle(label, 1);
        this.m_rootPanel.add(label, this.m_c);
        final GridBagConstraints c2 = this.m_c;
        ++c2.gridx;
        final JLabel label2 = new JLabel(JAPMessages.getString(VolumePlanSelectionPane.MSG_VALIDUNTIL));
        GUIUtils.setFontStyle(label2, 1);
        this.m_rootPanel.add(label2, this.m_c);
        final GridBagConstraints c3 = this.m_c;
        ++c3.gridx;
        final JLabel label3 = new JLabel(JAPMessages.getString(VolumePlanSelectionPane.MSG_VOLUME));
        GUIUtils.setFontStyle(label3, 1);
        this.m_rootPanel.add(label3, this.m_c);
        this.m_rbGroup = new ButtonGroup();
        final GridBagConstraints c4 = this.m_c;
        ++c4.gridy;
        for (int i = 0; i < this.m_allPlans.getNrOfPlans(); ++i) {
            this.addPlan(this.m_allPlans.getVolumePlan(i));
        }
        this.addCouponField();
    }
    
    public void resetSelection() {
        this.m_selectedPlan = null;
        this.clearCouponFields();
    }
    
    private void clearCouponFields() {
        this.m_coupon1.setText("");
        this.m_coupon2.setText("");
        this.m_coupon3.setText("");
        this.m_coupon4.setText("");
    }
    
    private String getCouponString() {
        return this.m_coupon1.getText() + this.m_coupon2.getText() + this.m_coupon3.getText() + this.m_coupon4.getText();
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
        MSG_PRICE = ((VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane == null) ? (VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane = class$("jap.pay.wizardnew.VolumePlanSelectionPane")) : VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane).getName() + "_price";
        MSG_HEADING = ((VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane == null) ? (VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane = class$("jap.pay.wizardnew.VolumePlanSelectionPane")) : VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane).getName() + "_heading";
        MSG_VOLUME = ((VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane == null) ? (VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane = class$("jap.pay.wizardnew.VolumePlanSelectionPane")) : VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane).getName() + "_volume";
        MSG_UNLIMITED = ((VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane == null) ? (VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane = class$("jap.pay.wizardnew.VolumePlanSelectionPane")) : VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane).getName() + "_unlimited";
        MSG_ERROR_NO_PLAN_CHOSEN = ((VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane == null) ? (VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane = class$("jap.pay.wizardnew.VolumePlanSelectionPane")) : VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane).getName() + "_errorNoPlanChosen";
        MSG_VALIDUNTIL = ((VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane == null) ? (VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane = class$("jap.pay.wizardnew.VolumePlanSelectionPane")) : VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane).getName() + "_validuntil";
        MSG_CHOOSEAPLAN = ((VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane == null) ? (VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane = class$("jap.pay.wizardnew.VolumePlanSelectionPane")) : VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane).getName() + "_chooseaplan";
        MSG_ENTER_COUPON = ((VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane == null) ? (VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane = class$("jap.pay.wizardnew.VolumePlanSelectionPane")) : VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane).getName() + "_entercouponcode";
        MSG_PLAN_OR_COUPON = ((VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane == null) ? (VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane = class$("jap.pay.wizardnew.VolumePlanSelectionPane")) : VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane).getName() + "_planorcoupon";
        MSG_INVALID_COUPON = ((VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane == null) ? (VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane = class$("jap.pay.wizardnew.VolumePlanSelectionPane")) : VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane).getName() + "_invalidcoupon";
        MSG_COUPON_INCOMPLETE = ((VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane == null) ? (VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane = class$("jap.pay.wizardnew.VolumePlanSelectionPane")) : VolumePlanSelectionPane.class$jap$pay$wizardnew$VolumePlanSelectionPane).getName() + "_couponincomplete";
    }
}
