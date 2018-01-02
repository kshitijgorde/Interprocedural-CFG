// 
// Decompiled by Procyon v0.5.30
// 

package jap.pay.wizardnew;

import javax.swing.Icon;
import gui.GUIUtils;
import java.util.GregorianCalendar;
import javax.swing.JPanel;
import logging.LogType;
import java.util.Enumeration;
import anon.util.Base64;
import anon.pay.xml.XMLTransCert;
import gui.dialog.WorkerContentPane;
import anon.pay.xml.XMLPassivePayment;
import java.awt.Component;
import java.util.StringTokenizer;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import gui.dialog.DialogContentPaneOptions;
import anon.util.JAPMessages;
import gui.dialog.JAPDialog;
import gui.JAPJIntField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import anon.pay.xml.XMLPaymentOptions;
import anon.pay.xml.XMLPaymentOption;
import java.util.Vector;
import java.awt.GridBagConstraints;
import java.awt.Container;
import gui.dialog.DialogContentPane;

public class PassivePaymentPane extends DialogContentPane implements IWizardSuitable
{
    private static final String MSG_ENTER;
    private static final String MSG_ERRALLFIELDS;
    private static final String MSG_CARDCOMPANY;
    private static final String MSG_CARDOWNER;
    private static final String MSG_CARDVALIDITY;
    private static final String MSG_CARDNUMBER;
    private static final String MSG_CARDCHECKNUMBER;
    public static final String IMG_CREDITCARDSECURITY;
    public static final String[] creditCardDataKeys;
    private Container m_rootPanel;
    private GridBagConstraints m_c;
    private String m_language;
    private Vector m_inputFields;
    private XMLPaymentOption m_selectedOption;
    private XMLPaymentOptions m_paymentOptions;
    private JComboBox m_cbCompany;
    private JComboBox m_cbMonth;
    private JComboBox m_cbYear;
    private JTextField m_tfCardOwner;
    private JAPJIntField m_tfCardNumber1;
    private JAPJIntField m_tfCardNumber2;
    private JAPJIntField m_tfCardNumber3;
    private JAPJIntField m_tfCardNumber4;
    private JAPJIntField m_tfCardCheckNumber;
    static /* synthetic */ Class class$jap$pay$wizardnew$PassivePaymentPane;
    
    public PassivePaymentPane(final JAPDialog japDialog, final DialogContentPane dialogContentPane) {
        super(japDialog, "Dummy Text<br>Dummy Text<br>DummyText", new Layout(JAPMessages.getString(PassivePaymentPane.MSG_ENTER), -1), new DialogContentPaneOptions(2, dialogContentPane));
        this.setDefaultButtonOperation(266);
        this.m_language = JAPMessages.getLocale().getLanguage();
        this.m_rootPanel = this.getContentPane();
        this.m_c = new GridBagConstraints();
        this.m_rootPanel.setLayout(new GridBagLayout());
        this.m_c = new GridBagConstraints();
        this.m_c.gridx = 0;
        this.m_c.gridy = 0;
        this.m_c.weightx = 0.0;
        this.m_c.weightx = 0.0;
        this.m_c.insets = new Insets(5, 5, 5, 5);
        this.m_c.anchor = 18;
        this.m_c.fill = 0;
    }
    
    public void showGenericForm() {
        this.m_rootPanel.removeAll();
        this.m_rootPanel = this.getContentPane();
        this.m_c = new GridBagConstraints();
        this.m_rootPanel.setLayout(new GridBagLayout());
        this.m_c = new GridBagConstraints();
        this.m_c.gridx = 0;
        this.m_c.gridy = 0;
        this.m_c.weightx = 0.0;
        this.m_c.weightx = 0.0;
        this.m_c.insets = new Insets(5, 5, 5, 5);
        this.m_c.anchor = 18;
        this.m_c.fill = 0;
        this.m_c.gridwidth = 1;
        this.setText(this.m_selectedOption.getDetailedInfo(this.m_language));
        this.m_inputFields = new Vector();
        final Vector inputFields = this.m_selectedOption.getInputFields();
        for (int i = 0; i < inputFields.size(); ++i) {
            final String[] array = inputFields.elementAt(i);
            if (array[2].equalsIgnoreCase(this.m_language)) {
                final JLabel label = new JLabel("<html>" + array[1] + "</html>");
                if (array[0].equalsIgnoreCase("creditcardtype")) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(this.m_paymentOptions.getAcceptedCreditCards(), ",");
                    final JComboBox<String> comboBox = new JComboBox<String>();
                    comboBox.setName(array[0]);
                    while (stringTokenizer.hasMoreTokens()) {
                        comboBox.addItem(stringTokenizer.nextToken());
                    }
                    this.m_inputFields.addElement(comboBox);
                    this.m_rootPanel.add(label, this.m_c);
                    final GridBagConstraints c = this.m_c;
                    ++c.gridx;
                    this.m_rootPanel.add(comboBox, this.m_c);
                }
                else {
                    final JTextField textField = new JTextField(15);
                    textField.setName(array[0]);
                    this.m_inputFields.addElement(textField);
                    this.m_rootPanel.add(label, this.m_c);
                    final GridBagConstraints c2 = this.m_c;
                    ++c2.gridx;
                    this.m_rootPanel.add(textField, this.m_c);
                }
                final GridBagConstraints c3 = this.m_c;
                ++c3.gridy;
                this.m_c.gridx = 0;
            }
        }
    }
    
    public XMLPassivePayment getEnteredInfo() {
        if (this.m_selectedOption.isGeneric()) {
            return this.getEnteredGenericInfo();
        }
        if (this.m_selectedOption.getName().equalsIgnoreCase("creditcard")) {
            return this.getEnteredCreditCardInfo();
        }
        return null;
    }
    
    private XMLPassivePayment getEnteredCreditCardInfo() {
        XMLTransCert xmlTransCert = null;
        DialogContentPane dialogContentPane = this.getPreviousContentPane();
        while (xmlTransCert == null) {
            if (dialogContentPane instanceof WorkerContentPane) {
                final Object value = ((WorkerContentPane)dialogContentPane).getValue();
                if (value instanceof XMLTransCert) {
                    xmlTransCert = (XMLTransCert)value;
                }
                else {
                    dialogContentPane = dialogContentPane.getPreviousContentPane();
                }
            }
            else {
                dialogContentPane = dialogContentPane.getPreviousContentPane();
            }
        }
        DialogContentPane dialogContentPane2;
        for (dialogContentPane2 = this.getPreviousContentPane(); !(dialogContentPane2 instanceof VolumePlanSelectionPane); dialogContentPane2 = dialogContentPane2.getPreviousContentPane()) {}
        final VolumePlanSelectionPane volumePlanSelectionPane = (VolumePlanSelectionPane)dialogContentPane2;
        final String amount = volumePlanSelectionPane.getAmount();
        final String currency = volumePlanSelectionPane.getCurrency();
        final XMLPassivePayment xmlPassivePayment = new XMLPassivePayment();
        xmlPassivePayment.setTransferNumber(xmlTransCert.getTransferNumber());
        xmlPassivePayment.setAmount(Long.parseLong(amount));
        xmlPassivePayment.setCurrency(currency);
        xmlPassivePayment.setPaymentName(this.m_selectedOption.getName());
        xmlPassivePayment.addData("creditcardtype", (String)this.m_cbCompany.getSelectedItem());
        xmlPassivePayment.addData("number", this.m_tfCardNumber1.getText() + this.m_tfCardNumber2.getText() + this.m_tfCardNumber3.getText() + this.m_tfCardNumber4.getText());
        xmlPassivePayment.addData("owner", Base64.encode(this.m_tfCardOwner.getText().getBytes(), false));
        xmlPassivePayment.addData("valid", (String)this.m_cbMonth.getSelectedItem() + "/" + (String)this.m_cbYear.getSelectedItem());
        xmlPassivePayment.addData("checknumber", this.m_tfCardCheckNumber.getText());
        return xmlPassivePayment;
    }
    
    public XMLPassivePayment getEnteredGenericInfo() {
        XMLTransCert xmlTransCert = null;
        DialogContentPane dialogContentPane = this.getPreviousContentPane();
        while (xmlTransCert == null) {
            if (dialogContentPane instanceof WorkerContentPane) {
                final Object value = ((WorkerContentPane)dialogContentPane).getValue();
                if (value instanceof XMLTransCert) {
                    xmlTransCert = (XMLTransCert)value;
                }
                else {
                    dialogContentPane = dialogContentPane.getPreviousContentPane();
                }
            }
            else {
                dialogContentPane = dialogContentPane.getPreviousContentPane();
            }
        }
        DialogContentPane dialogContentPane2;
        for (dialogContentPane2 = this.getPreviousContentPane(); !(dialogContentPane2 instanceof VolumePlanSelectionPane); dialogContentPane2 = dialogContentPane2.getPreviousContentPane()) {}
        final VolumePlanSelectionPane volumePlanSelectionPane = (VolumePlanSelectionPane)dialogContentPane2;
        final String amount = volumePlanSelectionPane.getAmount();
        final String currency = volumePlanSelectionPane.getCurrency();
        final XMLPassivePayment xmlPassivePayment = new XMLPassivePayment();
        xmlPassivePayment.setTransferNumber(xmlTransCert.getTransferNumber());
        xmlPassivePayment.setAmount(Long.parseLong(amount));
        xmlPassivePayment.setCurrency(currency);
        xmlPassivePayment.setPaymentName(this.m_selectedOption.getName());
        final Enumeration elements = this.m_inputFields.elements();
        while (elements.hasMoreElements()) {
            final Component component = elements.nextElement();
            if (component instanceof JTextField) {
                final JTextField textField = (JTextField)component;
                xmlPassivePayment.addData(textField.getName(), textField.getText());
            }
            else {
                if (!(component instanceof JComboBox)) {
                    continue;
                }
                xmlPassivePayment.addData(((JComboBox<?>)component).getName(), (String)((JComboBox<?>)component).getSelectedItem());
            }
        }
        return xmlPassivePayment;
    }
    
    public CheckError[] checkYesOK() {
        final CheckError[] array = { null };
        if (this.m_selectedOption.getType().equals("passive")) {
            if (this.m_selectedOption.isGeneric()) {
                final Enumeration<Component> elements = (Enumeration<Component>)this.m_inputFields.elements();
                while (elements.hasMoreElements()) {
                    final Component component = elements.nextElement();
                    if (component instanceof JTextField) {
                        final JTextField textField = (JTextField)component;
                        if (textField.getText() == null || textField.getText().trim().equals("")) {
                            array[0] = new CheckError(JAPMessages.getString(PassivePaymentPane.MSG_ERRALLFIELDS), LogType.PAY);
                            return array;
                        }
                        continue;
                    }
                }
            }
            else {
                boolean b = true;
                if (this.m_tfCardCheckNumber.getText() == null || this.m_tfCardCheckNumber.getText().trim().equals("")) {
                    b = false;
                }
                if (this.m_tfCardNumber1.getText() == null || this.m_tfCardNumber1.getText().trim().equals("")) {
                    b = false;
                }
                if (this.m_tfCardNumber2.getText() == null || this.m_tfCardNumber2.getText().trim().equals("")) {
                    b = false;
                }
                if (this.m_tfCardNumber3.getText() == null || this.m_tfCardNumber3.getText().trim().equals("")) {
                    b = false;
                }
                if (this.m_tfCardNumber4.getText() == null || this.m_tfCardNumber4.getText().trim().equals("")) {
                    b = false;
                }
                if (this.m_tfCardOwner.getText() == null || this.m_tfCardOwner.getText().trim().equals("")) {
                    b = false;
                }
                if (!b) {
                    array[0] = new CheckError(JAPMessages.getString(PassivePaymentPane.MSG_ERRALLFIELDS), LogType.PAY);
                    return array;
                }
                return null;
            }
        }
        return null;
    }
    
    public CheckError[] checkUpdate() {
        this.showForm();
        return null;
    }
    
    public void showForm() {
        DialogContentPane dialogContentPane;
        for (dialogContentPane = this.getPreviousContentPane(); !(dialogContentPane instanceof MethodSelectionPane); dialogContentPane = dialogContentPane.getPreviousContentPane()) {}
        this.m_selectedOption = ((MethodSelectionPane)dialogContentPane).getSelectedPaymentOption();
        this.m_paymentOptions = null;
        while (this.m_paymentOptions == null) {
            if (dialogContentPane instanceof WorkerContentPane) {
                final Object value = ((WorkerContentPane)dialogContentPane).getValue();
                if (value instanceof XMLPaymentOptions) {
                    this.m_paymentOptions = (XMLPaymentOptions)value;
                }
                else {
                    dialogContentPane = dialogContentPane.getPreviousContentPane();
                }
            }
            else {
                dialogContentPane = dialogContentPane.getPreviousContentPane();
            }
        }
        if (this.m_selectedOption.isGeneric()) {
            this.showGenericForm();
        }
        else if (this.m_selectedOption.getName().equalsIgnoreCase("creditcard")) {
            this.showCreditCardForm();
        }
    }
    
    private void showCreditCardForm() {
        this.m_rootPanel.removeAll();
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
        this.m_c.gridwidth = 1;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.m_paymentOptions.getAcceptedCreditCards(), ",");
        this.m_cbCompany = new JComboBox();
        while (stringTokenizer.hasMoreTokens()) {
            this.m_cbCompany.addItem(stringTokenizer.nextToken());
        }
        this.m_rootPanel.add(this.m_cbCompany, this.m_c);
        final JAPJIntField.IntFieldUnlimitedZerosBounds intFieldUnlimitedZerosBounds = new JAPJIntField.IntFieldUnlimitedZerosBounds(9999);
        final GridBagConstraints c = this.m_c;
        ++c.gridy;
        this.m_c.gridwidth = 1;
        this.m_c.fill = 2;
        this.m_rootPanel.add(new JLabel(JAPMessages.getString(PassivePaymentPane.MSG_CARDNUMBER)), this.m_c);
        final JPanel panel = new JPanel(new GridBagLayout());
        this.m_c.gridwidth = 4;
        final GridBagConstraints c2 = this.m_c;
        ++c2.gridx;
        this.m_c.weightx = 1.0;
        this.m_c.insets = new Insets(0, 5, 0, 5);
        this.m_rootPanel.add(panel, this.m_c);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 0, 5, 5);
        panel.add(this.m_tfCardNumber1 = new JAPJIntField(intFieldUnlimitedZerosBounds, true), gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridx;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(this.m_tfCardNumber2 = new JAPJIntField(intFieldUnlimitedZerosBounds, true), gridBagConstraints);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridx;
        panel.add(this.m_tfCardNumber3 = new JAPJIntField(intFieldUnlimitedZerosBounds, true), gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridx;
        gridBagConstraints.insets = new Insets(5, 5, 5, 0);
        panel.add(this.m_tfCardNumber4 = new JAPJIntField(intFieldUnlimitedZerosBounds, true), gridBagConstraints);
        this.m_c.insets = new Insets(5, 5, 5, 5);
        this.m_c.weightx = 0.0;
        this.m_c.gridx = 0;
        final GridBagConstraints c3 = this.m_c;
        ++c3.gridy;
        this.m_c.gridwidth = 1;
        this.m_rootPanel.add(new JLabel(JAPMessages.getString(PassivePaymentPane.MSG_CARDOWNER)), this.m_c);
        final GridBagConstraints c4 = this.m_c;
        ++c4.gridx;
        this.m_c.gridwidth = 4;
        this.m_c.fill = 2;
        this.m_tfCardOwner = new JTextField();
        this.m_rootPanel.add(this.m_tfCardOwner, this.m_c);
        this.m_c.weightx = 0.0;
        this.m_c.gridx = 0;
        final GridBagConstraints c5 = this.m_c;
        ++c5.gridy;
        this.m_c.gridwidth = 1;
        this.m_rootPanel.add(new JLabel(JAPMessages.getString(PassivePaymentPane.MSG_CARDVALIDITY)), this.m_c);
        final GridBagConstraints c6 = this.m_c;
        ++c6.gridx;
        this.m_cbMonth = new JComboBox();
        for (int i = 1; i < 13; ++i) {
            this.m_cbMonth.addItem(String.valueOf(i));
        }
        this.m_rootPanel.add(this.m_cbMonth, this.m_c);
        this.m_c.gridx = 2;
        final int value = new GregorianCalendar().get(1);
        this.m_cbYear = new JComboBox();
        for (int j = 0; j < 21; ++j) {
            this.m_cbYear.addItem(String.valueOf(value + j));
        }
        this.m_rootPanel.add(this.m_cbYear, this.m_c);
        final GridBagConstraints c7 = this.m_c;
        ++c7.gridx;
        this.m_c.gridwidth = 2;
        this.m_c.gridheight = 2;
        this.m_rootPanel.add(new JLabel(GUIUtils.loadImageIcon(PassivePaymentPane.IMG_CREDITCARDSECURITY, true)), this.m_c);
        this.m_c.gridx = 0;
        final GridBagConstraints c8 = this.m_c;
        ++c8.gridy;
        this.m_c.gridwidth = 1;
        this.m_c.gridheight = 1;
        this.m_c.fill = 2;
        this.m_rootPanel.add(new JLabel(JAPMessages.getString(PassivePaymentPane.MSG_CARDCHECKNUMBER)), this.m_c);
        final GridBagConstraints c9 = this.m_c;
        ++c9.gridx;
        this.m_c.gridwidth = 1;
        this.m_tfCardCheckNumber = new JAPJIntField(intFieldUnlimitedZerosBounds, true);
        this.m_rootPanel.add(this.m_tfCardCheckNumber, this.m_c);
        this.setText(this.m_selectedOption.getDetailedInfo(this.m_language));
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
        MSG_ENTER = ((PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane == null) ? (PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane = class$("jap.pay.wizardnew.PassivePaymentPane")) : PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane).getName() + "_enter";
        MSG_ERRALLFIELDS = ((PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane == null) ? (PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane = class$("jap.pay.wizardnew.PassivePaymentPane")) : PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane).getName() + "_errallfields";
        MSG_CARDCOMPANY = ((PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane == null) ? (PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane = class$("jap.pay.wizardnew.PassivePaymentPane")) : PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane).getName() + "_cardcompany";
        MSG_CARDOWNER = ((PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane == null) ? (PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane = class$("jap.pay.wizardnew.PassivePaymentPane")) : PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane).getName() + "_cardowner";
        MSG_CARDVALIDITY = ((PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane == null) ? (PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane = class$("jap.pay.wizardnew.PassivePaymentPane")) : PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane).getName() + "_cardvalidity";
        MSG_CARDNUMBER = ((PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane == null) ? (PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane = class$("jap.pay.wizardnew.PassivePaymentPane")) : PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane).getName() + "_cardnumber";
        MSG_CARDCHECKNUMBER = ((PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane == null) ? (PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane = class$("jap.pay.wizardnew.PassivePaymentPane")) : PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane).getName() + "_cardchecknumber";
        IMG_CREDITCARDSECURITY = ((PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane == null) ? (PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane = class$("jap.pay.wizardnew.PassivePaymentPane")) : PassivePaymentPane.class$jap$pay$wizardnew$PassivePaymentPane).getName() + "_creditcardsecurity.gif";
        creditCardDataKeys = new String[] { "creditcardtype", "number", "owner", "valid", "checknumber" };
    }
}
