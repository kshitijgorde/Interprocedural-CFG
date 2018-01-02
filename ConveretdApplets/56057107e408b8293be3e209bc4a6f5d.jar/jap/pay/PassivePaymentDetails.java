// 
// Decompiled by Procyon v0.5.30
// 

package jap.pay;

import java.awt.event.ActionEvent;
import javax.swing.Box;
import java.awt.Dimension;
import jap.JAPUtil;
import javax.swing.JLabel;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Component;
import gui.JAPHtmlMultiLineLabel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Insets;
import logging.LogHolder;
import logging.LogType;
import anon.util.JAPMessages;
import anon.pay.xml.XMLPassivePayment;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import gui.dialog.JAPDialog;

public class PassivePaymentDetails extends JAPDialog implements ActionListener
{
    private static final String MSG_HEADING;
    private static final String MSG_TITLE;
    private static final String MSG_CLOSEBUTTON;
    private static final String MSG_UNKNOWN_PAYMENT;
    private static final String MSG_NOT_SHOWN;
    private static final String MSG_PAID_BY;
    private static final String MSG_CREDITCARDWORD;
    private static final String MSG_CREDITCARDTYPE;
    private static final String MSG_NUMBER;
    private static final String MSG_OWNER;
    private static final String MSG_VALID;
    private static final String MSG_CHECKNUMBER;
    private static final String MSG_AMOUNT;
    private static final String MSG_TRANSFERNUMBER;
    private static final String MSG_ACCOUNTNUMBER;
    private GridBagConstraints m_c;
    private JButton m_closeButton;
    static /* synthetic */ Class class$jap$pay$PassivePaymentDetails;
    
    public PassivePaymentDetails(final JAPDialog japDialog, final XMLPassivePayment xmlPassivePayment, final long n, final long n2) {
        super(japDialog, JAPMessages.getString(PassivePaymentDetails.MSG_TITLE));
        try {
            this.setDefaultCloseOperation(2);
            this.buildDialog(xmlPassivePayment, n, n2);
            this.setResizable(false);
            this.pack();
            this.setVisible(true);
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.PAY, "Could not create PassivePaymentDetails: ", ex);
        }
    }
    
    private void buildDialog(final XMLPassivePayment xmlPassivePayment, final long n, final long n2) {
        this.m_c = new GridBagConstraints();
        this.m_c.anchor = 11;
        this.m_c.insets = new Insets(10, 30, 10, 30);
        this.m_c.gridx = 0;
        this.m_c.gridy = 0;
        this.m_c.weighty = 0.0;
        this.m_c.weightx = 0.0;
        this.getContentPane().setLayout(new GridBagLayout());
        this.getContentPane().add(new JAPHtmlMultiLineLabel("<h3>" + JAPMessages.getString(PassivePaymentDetails.MSG_HEADING) + "</h3"), this.m_c);
        final GridBagConstraints c = this.m_c;
        ++c.gridy;
        this.getContentPane().add(this.buildTransactionDetailsPanel(n2, n, xmlPassivePayment.getAmount()), this.m_c);
        final GridBagConstraints c2 = this.m_c;
        ++c2.gridy;
        this.getContentPane().add(this.buildPaymentDetailsPanel(xmlPassivePayment), this.m_c);
        final GridBagConstraints c3 = this.m_c;
        ++c3.gridy;
        (this.m_closeButton = new JButton(JAPMessages.getString(PassivePaymentDetails.MSG_CLOSEBUTTON))).addActionListener(this);
        final GridBagConstraints c4 = this.m_c;
        ++c4.gridy;
        this.getContentPane().add(this.m_closeButton, this.m_c);
    }
    
    private JPanel buildTransactionDetailsPanel(final long n, final long n2, final long n3) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.add(new JLabel(JAPMessages.getString(PassivePaymentDetails.MSG_ACCOUNTNUMBER) + ": " + new Long(n).toString()));
        panel.add(new JLabel(JAPMessages.getString(PassivePaymentDetails.MSG_TRANSFERNUMBER) + ": " + new Long(n2).toString()), this.m_c);
        panel.add(new JLabel(JAPMessages.getString(PassivePaymentDetails.MSG_AMOUNT) + ": " + JAPUtil.formatEuroCentValue(n3)), this.m_c);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        return panel;
    }
    
    private JPanel buildPaymentDetailsPanel(final XMLPassivePayment xmlPassivePayment) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        final String paymentName = xmlPassivePayment.getPaymentName();
        if (paymentName.equalsIgnoreCase("CreditCard")) {
            panel.add(new JLabel(JAPMessages.getString(PassivePaymentDetails.MSG_NOT_SHOWN)));
        }
        else if (!paymentName.equalsIgnoreCase("Paysafecard")) {
            panel.add(new JLabel(JAPMessages.getString(PassivePaymentDetails.MSG_UNKNOWN_PAYMENT)));
        }
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        return panel;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.m_closeButton) {
            this.setVisible(false);
        }
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
        MSG_HEADING = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_heading";
        MSG_TITLE = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_title";
        MSG_CLOSEBUTTON = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_closebutton";
        MSG_UNKNOWN_PAYMENT = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_unknownpayment";
        MSG_NOT_SHOWN = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_notshown";
        MSG_PAID_BY = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_paidby";
        MSG_CREDITCARDWORD = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_creditcardword";
        MSG_CREDITCARDTYPE = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_creditcardtype";
        MSG_NUMBER = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_number";
        MSG_OWNER = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_owner";
        MSG_VALID = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_valid";
        MSG_CHECKNUMBER = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_checknumber";
        MSG_AMOUNT = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_amount";
        MSG_TRANSFERNUMBER = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_transfernumber";
        MSG_ACCOUNTNUMBER = ((PassivePaymentDetails.class$jap$pay$PassivePaymentDetails == null) ? (PassivePaymentDetails.class$jap$pay$PassivePaymentDetails = class$("jap.pay.PassivePaymentDetails")) : PassivePaymentDetails.class$jap$pay$PassivePaymentDetails).getName() + "_accountnumber";
    }
}
