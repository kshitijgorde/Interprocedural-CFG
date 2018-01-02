// 
// Decompiled by Procyon v0.5.30
// 

package jap.pay.wizardnew;

import java.net.MalformedURLException;
import java.net.URL;
import platform.AbstractOS;
import jap.JAPController;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.lang.reflect.Field;
import logging.LogHolder;
import logging.LogType;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import gui.GUIUtils;
import jap.JAPUtil;
import gui.dialog.WorkerContentPane;
import anon.util.Util;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import gui.dialog.DialogContentPaneOptions;
import anon.util.JAPMessages;
import gui.dialog.JAPDialog;
import gui.LinkMouseListener;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import anon.pay.xml.XMLTransCert;
import anon.pay.xml.XMLPaymentOption;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Container;
import java.awt.event.ActionListener;
import gui.dialog.DialogContentPane;

public class PaymentInfoPane extends DialogContentPane implements IWizardSuitable, ActionListener
{
    private static final String MSG_INFOS;
    private static final String MSG_BUTTONCOPY;
    private static final String MSG_BUTTONOPEN;
    public static final String MSG_PAYPAL_ITEM_NAME;
    private static final String MSG_COULD_OPEN;
    private static final String MSG_REMINDER_PAYMENT;
    private static final String MSG_REMINDER_PAYMENT_EXPLAIN;
    private static final String MSG_EXPLAIN_COULD_OPEN;
    private static final String MSG_NO_FURTHER_PAYMENT;
    private static final String MSG_USE_OTHER_METHOD;
    private Container m_rootPanel;
    private GridBagConstraints m_c;
    private JButton m_bttnCopy;
    private JButton m_bttnOpen;
    private String m_language;
    private XMLPaymentOption m_selectedOption;
    private XMLTransCert m_transCert;
    private JCheckBox m_linkOpenedInBrowser;
    private JLabel m_imageLabel;
    private LinkMouseListener.ILinkGenerator m_paymentLinkGenerator;
    private boolean m_bURL;
    static /* synthetic */ Class class$jap$pay$wizardnew$PaymentInfoPane;
    static /* synthetic */ Class class$jap$JAPConstants;
    
    public PaymentInfoPane(final JAPDialog japDialog, final DialogContentPane dialogContentPane) {
        super(japDialog, "Dummy", new Layout(JAPMessages.getString(PaymentInfoPane.MSG_INFOS), -1), new DialogContentPaneOptions(2, dialogContentPane));
        this.setDefaultButtonOperation(266);
        this.m_language = JAPMessages.getLocale().getLanguage();
        (this.m_rootPanel = this.getContentPane()).setLayout(new GridBagLayout());
        this.m_c = new GridBagConstraints();
        this.m_c.gridx = 0;
        this.m_c.gridy = 0;
        this.m_c.weightx = 0.0;
        this.m_c.weightx = 0.0;
        this.m_c.insets = new Insets(5, 5, 5, 5);
        this.m_c.anchor = 18;
        this.m_c.fill = 0;
        this.m_linkOpenedInBrowser = new JCheckBox(JAPMessages.getString(PaymentInfoPane.MSG_COULD_OPEN));
        this.m_rootPanel.add(this.m_linkOpenedInBrowser, this.m_c);
    }
    
    public static String createPaysafecardLink(String s, final long n, final String s2) {
        s = Util.replaceAll(s, "%t", s2);
        s = Util.replaceAll(s, "%a", amountAsString(n));
        s = Util.replaceAll(s, "%l", JAPMessages.getLocale().getLanguage());
        return s;
    }
    
    public static String createPaypalLink(String s, final long n, final String s2, final String s3) {
        final String s4 = "EUR";
        final String amountAsString = amountAsString(n);
        String upperCase = JAPMessages.getLocale().getLanguage().toUpperCase();
        final String string = JAPMessages.getString(PaymentInfoPane.MSG_PAYPAL_ITEM_NAME) + "%20-%20" + s2;
        s = Util.replaceAll(s, "%t", s3);
        s = Util.replaceAll(s, "%item%", string);
        s = Util.replaceAll(s, "%amount%", amountAsString);
        s = Util.replaceAll(s, "%currency%", s4);
        if (!upperCase.equals("DE")) {
            upperCase = "US";
        }
        s = Util.replaceAll(s, "%lang%", upperCase);
        return s;
    }
    
    public static String createEgoldLink(String s, final long n, final String s2, final String s3) {
        final String amountAsString = amountAsString(n);
        String lowerCase = JAPMessages.getLocale().getLanguage().toLowerCase();
        if (!lowerCase.equals("en") && !lowerCase.equals("de")) {
            lowerCase = "en";
        }
        final String string = JAPMessages.getString(PaymentInfoPane.MSG_PAYPAL_ITEM_NAME) + "%20-%20" + s2;
        s = Util.replaceAll(s, "%t", s3);
        s = Util.replaceAll(s, "%item%", string);
        s = Util.replaceAll(s, "%amount%", amountAsString);
        s = Util.replaceAll(s, "%currency%", "EUR");
        s = Util.replaceAll(s, "%2fen%2f", "%2f" + lowerCase + "%2f");
        return s;
    }
    
    private static String amountAsString(final long n) {
        final String string = new Long(n).toString();
        string.trim();
        String substring;
        String s;
        if (string.length() == 1) {
            substring = "0";
            s = "0" + string;
        }
        else if (string.length() < 3) {
            substring = "0";
            s = string;
        }
        else {
            substring = string.substring(0, string.length() - 2);
            s = string.substring(string.length() - 2, string.length());
        }
        return substring + "%2e" + s;
    }
    
    public void showInfo() {
        DialogContentPane dialogContentPane;
        for (dialogContentPane = this.getPreviousContentPane(); !(dialogContentPane instanceof MethodSelectionPane); dialogContentPane = dialogContentPane.getPreviousContentPane()) {}
        final XMLPaymentOption selectedPaymentOption = ((MethodSelectionPane)dialogContentPane).getSelectedPaymentOption();
        this.m_transCert = (XMLTransCert)((WorkerContentPane)this.getPreviousContentPane()).getValue();
        String text = "";
        this.m_selectedOption = selectedPaymentOption;
        this.m_rootPanel.removeAll();
        (this.m_rootPanel = this.getContentPane()).setLayout(new GridBagLayout());
        this.m_c = new GridBagConstraints();
        this.m_c.gridx = 0;
        this.m_c.gridy = 0;
        this.m_c.weightx = 0.0;
        this.m_c.weightx = 0.0;
        this.m_c.insets = new Insets(5, 5, 5, 5);
        this.m_c.anchor = 18;
        this.m_c.fill = 0;
        final String extraInfo = selectedPaymentOption.getExtraInfo(this.m_language);
        this.m_bURL = false;
        if (extraInfo != null) {
            DialogContentPane dialogContentPane2;
            for (dialogContentPane2 = this.getPreviousContentPane(); !(dialogContentPane2 instanceof VolumePlanSelectionPane); dialogContentPane2 = dialogContentPane2.getPreviousContentPane()) {}
            final VolumePlanSelectionPane volumePlanSelectionPane = (VolumePlanSelectionPane)dialogContentPane2;
            this.m_paymentLinkGenerator = new LinkMouseListener.ILinkGenerator() {
                private final /* synthetic */ String val$strOptionName = selectedPaymentOption.getName();
                private final /* synthetic */ int val$intAmount = Integer.parseInt(volumePlanSelectionPane.getAmount());
                private final /* synthetic */ String val$planName = volumePlanSelectionPane.getSelectedVolumePlan().getDisplayName();
                private final /* synthetic */ String val$tan = String.valueOf(PaymentInfoPane.this.m_transCert.getTransferNumber());
                
                public String createLink() {
                    String s;
                    if (this.val$strOptionName.toLowerCase().indexOf("paypal") != -1) {
                        s = PaymentInfoPane.createPaypalLink(extraInfo, this.val$intAmount, this.val$planName, this.val$tan);
                    }
                    else if (this.val$strOptionName.toLowerCase().indexOf("gold") != -1) {
                        s = PaymentInfoPane.createEgoldLink(extraInfo, this.val$intAmount, this.val$planName, this.val$tan);
                    }
                    else if (this.val$strOptionName.toLowerCase().indexOf("paysafecard") != -1) {
                        s = PaymentInfoPane.createPaysafecardLink(extraInfo, this.val$intAmount, this.val$tan);
                    }
                    else {
                        s = Util.replaceAll(Util.replaceAll(Util.replaceAll(extraInfo, "%t", this.val$tan), "%a", JAPUtil.formatEuroCentValue(this.val$intAmount)), "%c", "");
                    }
                    return s;
                }
            };
            final GridBagConstraints c = this.m_c;
            ++c.gridy;
            this.m_rootPanel.add(new JLabel(" "), this.m_c);
            final ImageIcon loadImageIcon = GUIUtils.loadImageIcon(getMethodImageFilename(selectedPaymentOption.getName()), false, false);
            final GridBagConstraints c2 = this.m_c;
            ++c2.gridy;
            if (loadImageIcon != null) {
                final JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, 0));
                (this.m_imageLabel = new JLabel(loadImageIcon)).setAlignmentX(0.5f);
                if (extraInfo.indexOf("://") > 0 || extraInfo.toLowerCase().startsWith("mailto:")) {
                    this.m_imageLabel.addMouseListener(new LinkMouseListener(this.m_paymentLinkGenerator) {
                        public void mouseClicked(final MouseEvent mouseEvent) {
                            super.mouseClicked(mouseEvent);
                            PaymentInfoPane.this.actionPerformed(null);
                        }
                    });
                }
                panel.add(this.m_imageLabel);
                this.m_c.gridwidth = 2;
                this.m_rootPanel.add(panel, this.m_c);
                this.m_c.gridwidth = 1;
                final GridBagConstraints c3 = this.m_c;
                ++c3.gridy;
            }
            (this.m_bttnOpen = new JButton(JAPMessages.getString(PaymentInfoPane.MSG_BUTTONOPEN))).addActionListener(this);
            this.m_rootPanel.add(this.m_bttnOpen, this.m_c);
            this.m_bttnOpen.setVisible(false);
            final GridBagConstraints c4 = this.m_c;
            ++c4.gridx;
            (this.m_bttnCopy = new JButton(JAPMessages.getString(PaymentInfoPane.MSG_BUTTONCOPY))).addActionListener(this);
            this.m_rootPanel.add(this.m_bttnCopy, this.m_c);
            this.m_bttnCopy.setEnabled(false);
            this.m_bURL = selectedPaymentOption.getExtraInfoType(this.m_language).equalsIgnoreCase("link");
            if (this.m_bURL) {
                this.m_bttnOpen.setVisible(true);
                this.m_bttnOpen.setEnabled(true);
                text = selectedPaymentOption.getDetailedInfo(this.m_language);
            }
            else {
                this.m_bttnCopy.setEnabled(true);
                text = selectedPaymentOption.getDetailedInfo(this.m_language) + "<br><b>" + this.m_paymentLinkGenerator.createLink() + "</b>";
            }
        }
        this.m_c.gridx = 0;
        final GridBagConstraints c5 = this.m_c;
        ++c5.gridy;
        this.m_rootPanel.add(new JLabel(" "), this.m_c);
        this.setText(text);
        final GridBagConstraints c6 = this.m_c;
        ++c6.gridy;
        this.m_c.anchor = 15;
        this.m_c.gridwidth = 2;
        this.m_rootPanel.add(this.m_linkOpenedInBrowser, this.m_c);
        if (this.m_bURL) {
            this.m_linkOpenedInBrowser.setText(JAPMessages.getString(PaymentInfoPane.MSG_COULD_OPEN));
        }
        else {
            this.m_linkOpenedInBrowser.setText(JAPMessages.getString(PaymentInfoPane.MSG_REMINDER_PAYMENT));
        }
    }
    
    public XMLTransCert getTransCert() {
        return this.m_transCert;
    }
    
    public static String getMethodImageFilename(final String s) {
        final Class clazz = (PaymentInfoPane.class$jap$JAPConstants == null) ? (PaymentInfoPane.class$jap$JAPConstants = class$("jap.JAPConstants")) : PaymentInfoPane.class$jap$JAPConstants;
        final String string = "IMAGE_" + s.toUpperCase();
        Field declaredField;
        try {
            declaredField = clazz.getDeclaredField(string);
        }
        catch (NoSuchFieldException ex2) {
            LogHolder.log(7, LogType.PAY, "could not load image for payment method " + s + ", there is not variable " + string + " in JAPConstants");
            return null;
        }
        String s2;
        try {
            s2 = (String)declaredField.get(null);
        }
        catch (Exception ex) {
            LogHolder.log(7, LogType.PAY, "could not load image for payment method" + s + " , reason: " + ex);
            return null;
        }
        return s2;
    }
    
    private void copyExtraInfoToClipboard() {
        final Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        final String link = this.m_paymentLinkGenerator.createLink();
        String s;
        if (this.m_selectedOption.getExtraInfoType(this.m_language).equalsIgnoreCase("text")) {
            s = Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(link, "<br>", "\n"), "<p>", "\n\n"), "&uuml;", "\u00fc"), "&Uuml;", "\u00dc"), "&auml;", "\u00e4"), "&Auml;", "\u00c4"), "&ouml;", "\u00f6"), "&Ouml;", "\u00d6"), "&szlig;", "\u00df"), "&nbsp;", " ");
        }
        else {
            s = Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(link, "<br>", ""), "<p>", ""), "&nbsp;", "%20"), " ", "%20");
        }
        systemClipboard.setContents(new StringSelection(Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(s, "<html>", " "), "</html>", " "), "<font color=blue><u>", ""), "</u></font>", "").trim()), null);
    }
    
    public void openURL() {
        final String link = this.m_paymentLinkGenerator.createLink();
        if (!JAPController.getInstance().isAnonConnected() && JAPController.getInstance().getAnonMode()) {
            JAPController.getInstance().stopAnonModeWait();
        }
        final AbstractOS instance = AbstractOS.getInstance();
        final String trim = Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(link, "<br>", ""), "<p>", ""), "<html>", " "), "</html>", " "), "&nbsp;", "%20"), " ", "%20"), "<font color=blue><u>", ""), "</u></font>", "").trim();
        LogHolder.log(7, LogType.PAY, "Opening " + trim + " in browser.");
        try {
            instance.openURL(new URL(trim));
        }
        catch (MalformedURLException ex) {
            LogHolder.log(2, LogType.PAY, "Malformed URL");
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent != null) {
            if (actionEvent.getSource() == this.m_bttnCopy) {
                this.copyExtraInfoToClipboard();
            }
            else if (actionEvent.getSource() == this.m_bttnOpen) {
                this.openURL();
            }
        }
        this.m_bttnCopy.setEnabled(true);
    }
    
    public CheckError[] checkUpdate() {
        this.m_linkOpenedInBrowser.setSelected(false);
        this.showInfo();
        return null;
    }
    
    public CheckError[] checkYesOK() {
        if (this.m_linkOpenedInBrowser.isSelected()) {
            return null;
        }
        if (this.m_bURL) {
            return new CheckError[] { new CheckError(JAPMessages.getString(PaymentInfoPane.MSG_EXPLAIN_COULD_OPEN), LogType.PAY) };
        }
        return new CheckError[] { new CheckError(JAPMessages.getString(PaymentInfoPane.MSG_REMINDER_PAYMENT_EXPLAIN), LogType.PAY) };
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
        MSG_INFOS = ((PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane == null) ? (PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane = class$("jap.pay.wizardnew.PaymentInfoPane")) : PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane).getName() + "_infos";
        MSG_BUTTONCOPY = ((PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane == null) ? (PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane = class$("jap.pay.wizardnew.PaymentInfoPane")) : PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane).getName() + "_buttoncopy";
        MSG_BUTTONOPEN = ((PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane == null) ? (PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane = class$("jap.pay.wizardnew.PaymentInfoPane")) : PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane).getName() + "_buttonopen";
        MSG_PAYPAL_ITEM_NAME = ((PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane == null) ? (PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane = class$("jap.pay.wizardnew.PaymentInfoPane")) : PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane).getName() + "_paypalitemname";
        MSG_COULD_OPEN = ((PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane == null) ? (PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane = class$("jap.pay.wizardnew.PaymentInfoPane")) : PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane).getName() + "_reminderLink";
        MSG_REMINDER_PAYMENT = ((PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane == null) ? (PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane = class$("jap.pay.wizardnew.PaymentInfoPane")) : PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane).getName() + "_reminderPayment";
        MSG_REMINDER_PAYMENT_EXPLAIN = ((PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane == null) ? (PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane = class$("jap.pay.wizardnew.PaymentInfoPane")) : PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane).getName() + "_reminderPaymentExplain";
        MSG_EXPLAIN_COULD_OPEN = ((PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane == null) ? (PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane = class$("jap.pay.wizardnew.PaymentInfoPane")) : PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane).getName() + "_reminderLinkExplain";
        MSG_NO_FURTHER_PAYMENT = ((PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane == null) ? (PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane = class$("jap.pay.wizardnew.PaymentInfoPane")) : PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane).getName() + "_noFurtherPayment";
        MSG_USE_OTHER_METHOD = ((PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane == null) ? (PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane = class$("jap.pay.wizardnew.PaymentInfoPane")) : PaymentInfoPane.class$jap$pay$wizardnew$PaymentInfoPane).getName() + "_useOtherMethod";
    }
}
