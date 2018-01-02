// 
// Decompiled by Procyon v0.5.30
// 

package jap.pay;

import jap.JAPUtil;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import anon.pay.xml.XMLPaymentOptions;
import anon.pay.xml.XMLPaymentOption;
import java.util.Hashtable;
import anon.pay.xml.XMLPassivePayment;
import anon.util.IXMLEncodable;
import anon.pay.xml.XMLErrorMessage;
import gui.dialog.WorkerContentPane;
import anon.util.IReturnRunnable;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.table.TableModel;
import anon.pay.BIConnection;
import anon.pay.xml.XMLTransactionOverview;
import anon.pay.PayAccount;
import anon.pay.xml.XMLTransCert;
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import logging.LogHolder;
import logging.LogType;
import anon.util.JAPMessages;
import java.awt.Component;
import gui.GUIUtils;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import gui.dialog.JAPDialog;

public class TransactionOverviewDialog extends JAPDialog implements ActionListener
{
    private static final String MSG_OK_BUTTON;
    private static final String MSG_DETAILSBUTTON;
    private static final String MSG_RELOADBUTTON;
    private static final String MSG_CANCELBUTTON;
    private static final String MSG_FETCHING;
    private static final String MSG_TAN;
    private static final String MSG_AMOUNT;
    private static final String MSG_STATUS;
    private static final String MSG_TRANSACTION_DATE;
    public static final String MSG_DETAILS_FAILED;
    public static final String MSG_FETCHING_TAN;
    private static final String MSG_ACCOUNTNUMBER;
    private static final String MSG_VOLUMEPLAN;
    private static final String MSG_PAYMENTMETHOD;
    private static final String MSG_USEDSTATUS;
    private static final String MSG_OPENSTATUS;
    private static final String MSG_EXPIREDSTATUS;
    private static final String MSG_PAYMENT_COMPLETED;
    private static final String MSG_PAYMENT_EXPIRED;
    private static final String MSG_NO_OPEN_TRANSFERS;
    private JTable m_transactionsTable;
    private JButton m_okButton;
    private JButton m_reloadButton;
    private JButton m_detailsButton;
    private JLabel m_fetchingLabel;
    private AccountSettingsPanel m_parent;
    private Vector m_accounts;
    static /* synthetic */ Class class$jap$pay$TransactionOverviewDialog;
    
    public TransactionOverviewDialog(final AccountSettingsPanel parent, final String s, final boolean b, final Vector accounts) {
        super(GUIUtils.getParentWindow(parent.getRootPanel()), s, b);
        this.m_parent = parent;
        if (accounts.size() == 0) {
            JAPDialog.showMessageDialog(this, JAPMessages.getString(TransactionOverviewDialog.MSG_NO_OPEN_TRANSFERS));
        }
        else {
            try {
                this.m_accounts = accounts;
                this.setDefaultCloseOperation(2);
                this.buildDialog();
                this.setModal(true);
                this.setSize(700, 300);
                this.setVisible(true);
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.PAY, "Could not create TransactionOverviewDialog: " + ex.getMessage());
            }
        }
    }
    
    private void buildDialog() throws Exception {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.m_transactionsTable = new JTable();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        panel.add(new JScrollPane(this.m_transactionsTable), gridBagConstraints);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 0;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        (this.m_fetchingLabel = new JLabel(JAPMessages.getString(TransactionOverviewDialog.MSG_FETCHING), GUIUtils.loadImageIcon("busy.gif", true), 10)).setHorizontalTextPosition(10);
        panel.add(this.m_fetchingLabel, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 14;
        panel.add(this.buildButtonPanel(), gridBagConstraints);
        this.getContentPane().add(panel);
        this.showTransactions();
    }
    
    private JPanel buildButtonPanel() {
        final JPanel panel = new JPanel(new FlowLayout(0));
        (this.m_detailsButton = new JButton(JAPMessages.getString(TransactionOverviewDialog.MSG_DETAILSBUTTON))).addActionListener(this);
        panel.add(this.m_detailsButton);
        (this.m_reloadButton = new JButton(JAPMessages.getString(TransactionOverviewDialog.MSG_RELOADBUTTON))).addActionListener(this);
        panel.add(this.m_reloadButton);
        (this.m_okButton = new JButton(JAPMessages.getString(TransactionOverviewDialog.MSG_CANCELBUTTON))).addActionListener(this);
        panel.add(this.m_okButton);
        return panel;
    }
    
    private void showTransactions() {
        this.m_reloadButton.setEnabled(false);
        this.m_fetchingLabel.setVisible(true);
        final Thread thread = new Thread(new Runnable() {
            private final /* synthetic */ TransactionOverviewDialog this$0;
            
            public void run() {
                final Vector vector = new Vector<XMLTransCert>();
                final Enumeration<PayAccount> elements = (Enumeration<PayAccount>)TransactionOverviewDialog.this.m_accounts.elements();
                while (elements.hasMoreElements()) {
                    final Enumeration elements2 = elements.nextElement().getTransCerts().elements();
                    while (elements2.hasMoreElements()) {
                        vector.addElement(elements2.nextElement());
                    }
                }
                final XMLTransactionOverview xmlTransactionOverview = new XMLTransactionOverview(JAPMessages.getLocale().getLanguage());
                for (int i = 0; i < vector.size(); ++i) {
                    xmlTransactionOverview.addTan(vector.elementAt(i).getTransferNumber());
                }
                XMLTransactionOverview fetchTransactionOverview;
                try {
                    final PayAccount payAccount = TransactionOverviewDialog.this.m_accounts.elementAt(0);
                    final BIConnection biConnection = new BIConnection(payAccount.getBI());
                    biConnection.connect();
                    biConnection.authenticate(payAccount.getAccountCertificate(), payAccount.getPrivateKey());
                    fetchTransactionOverview = biConnection.fetchTransactionOverview(xmlTransactionOverview);
                    biConnection.disconnect();
                    if (fetchTransactionOverview == null) {
                        throw new Exception("JPI returned error message rather than transaction overview");
                    }
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.PAY, "Cannot connect to Payment Instance: " + ex.getMessage());
                    LogHolder.log(2, LogType.PAY, ex);
                    TransactionOverviewDialog.this.m_parent.showPIerror(TransactionOverviewDialog.this.getRootPane(), ex);
                    TransactionOverviewDialog.this.m_fetchingLabel.setVisible(false);
                    TransactionOverviewDialog.this.setVisible(false);
                    return;
                }
                final MyTableModel model = new MyTableModel(fetchTransactionOverview);
                TransactionOverviewDialog.this.m_transactionsTable.setEnabled(true);
                TransactionOverviewDialog.this.m_transactionsTable.setModel(model);
                TransactionOverviewDialog.this.m_transactionsTable.addMouseListener(new MouseAdapter() {
                    private final /* synthetic */ TransactionOverviewDialog$1 this$1 = this$1;
                    
                    public void mouseClicked(final MouseEvent mouseEvent) {
                        if (mouseEvent.getClickCount() == 2) {
                            this.this$1.this$0.showTransactionDetailsDialog();
                        }
                    }
                });
                TransactionOverviewDialog.this.m_okButton.setText(JAPMessages.getString(TransactionOverviewDialog.MSG_OK_BUTTON));
                TransactionOverviewDialog.this.m_fetchingLabel.setVisible(false);
                TransactionOverviewDialog.this.m_reloadButton.setEnabled(true);
            }
        }, "TransactionOverviewDialog");
        thread.setDaemon(true);
        thread.start();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.m_okButton) {
            this.dispose();
        }
        else if (actionEvent.getSource() == this.m_reloadButton) {
            this.showTransactions();
        }
        else if (actionEvent.getSource() == this.m_detailsButton) {
            this.showTransactionDetailsDialog();
        }
    }
    
    public void showTransactionDetailsDialog() {
        try {
            final int selectedRow = this.m_transactionsTable.getSelectedRow();
            final String s = (String)this.m_transactionsTable.getModel().getValueAt(selectedRow, 1);
            final long longValue = ((TablecellAmount)this.m_transactionsTable.getModel().getValueAt(selectedRow, 3)).getLongValue();
            final String s2 = (String)this.m_transactionsTable.getModel().getValueAt(selectedRow, 6);
            final String s3 = (String)this.m_transactionsTable.getModel().getValueAt(selectedRow, 4);
            final String s4 = (String)this.m_transactionsTable.getModel().getValueAt(selectedRow, 5);
            boolean b = false;
            boolean b2 = false;
            if (s2.equalsIgnoreCase(JAPMessages.getString(TransactionOverviewDialog.MSG_USEDSTATUS))) {
                b = true;
            }
            else if (s2.equalsIgnoreCase(JAPMessages.getString(TransactionOverviewDialog.MSG_EXPIREDSTATUS))) {
                b2 = true;
            }
            final PayAccount payAccount = this.m_accounts.elementAt(0);
            final JAPDialog japDialog = new JAPDialog(this, JAPMessages.getString(TransactionOverviewDialog.MSG_FETCHING_TAN));
            final IReturnRunnable returnRunnable = new IReturnRunnable() {
                Object xmlReply;
                
                public void run() {
                    try {
                        final BIConnection biConnection = new BIConnection(payAccount.getBI());
                        biConnection.connect();
                        biConnection.authenticate(payAccount.getAccountCertificate(), payAccount.getPrivateKey());
                        biConnection.connect();
                        biConnection.authenticate(payAccount.getAccountCertificate(), payAccount.getPrivateKey());
                        this.xmlReply = biConnection.fetchPaymentData(new Long(s).toString());
                    }
                    catch (Exception xmlReply) {
                        this.xmlReply = xmlReply;
                    }
                }
                
                public Object getValue() {
                    return this.xmlReply;
                }
            };
            new WorkerContentPane(japDialog, JAPMessages.getString(TransactionOverviewDialog.MSG_FETCHING_TAN), returnRunnable).updateDialog();
            japDialog.pack();
            japDialog.setVisible(true);
            if (returnRunnable.getValue() == null) {
                return;
            }
            if (returnRunnable.getValue() instanceof Exception && !(returnRunnable.getValue() instanceof XMLErrorMessage)) {
                throw (Exception)returnRunnable.getValue();
            }
            if (!(returnRunnable.getValue() instanceof IXMLEncodable)) {
                throw new Exception("Illegal return value!");
            }
            final IXMLEncodable ixmlEncodable = (IXMLEncodable)returnRunnable.getValue();
            if (ixmlEncodable instanceof XMLErrorMessage) {
                if (((XMLErrorMessage)ixmlEncodable).getErrorCode() == 19) {
                    if (b) {
                        JAPDialog.showMessageDialog(this, JAPMessages.getString(TransactionOverviewDialog.MSG_PAYMENT_COMPLETED));
                    }
                    else if (b2) {
                        JAPDialog.showMessageDialog(this, JAPMessages.getString(TransactionOverviewDialog.MSG_PAYMENT_EXPIRED));
                    }
                    else {
                        showActivePaymentDialog(this, s, longValue, payAccount, s3, s4);
                    }
                }
                else {
                    JAPDialog.showMessageDialog(this, JAPMessages.getString(TransactionOverviewDialog.MSG_DETAILS_FAILED));
                }
            }
            else {
                showPassivePaymentDialog(this, (XMLPassivePayment)ixmlEncodable, Long.parseLong(s), payAccount.getAccountNumber());
            }
        }
        catch (Exception ex) {
            LogHolder.log(7, LogType.PAY, "could not get transaction details");
        }
    }
    
    public static void showActivePaymentDialog(final JAPDialog japDialog, final String s, final long n, final PayAccount payAccount, final String s2, final String s3) {
        final ActivePaymentDetails activePaymentDetails = new ActivePaymentDetails(japDialog, getLocalizedActivePaymentsData(JAPMessages.getLocale().getLanguage(), payAccount, s3), s, n, s2);
    }
    
    private static Vector getLocalizedActivePaymentsData(String s, final PayAccount payAccount, final String s2) {
        final Vector<Hashtable<String, String>> vector = new Vector<Hashtable<String, String>>();
        try {
            final BIConnection biConnection = new BIConnection(payAccount.getBI());
            biConnection.connect();
            biConnection.authenticate(payAccount.getAccountCertificate(), payAccount.getPrivateKey());
            final XMLPaymentOptions fetchPaymentOptions = biConnection.fetchPaymentOptions();
            if (s.equals("")) {
                s = "en";
            }
            final Enumeration elements = fetchPaymentOptions.getAllOptions().elements();
            while (elements.hasMoreElements()) {
                final XMLPaymentOption xmlPaymentOption = elements.nextElement();
                if (!xmlPaymentOption.getType().equals("passive")) {
                    if (!xmlPaymentOption.worksWithJapVersion("00.12.005")) {
                        continue;
                    }
                    final Hashtable<String, String> hashtable = new Hashtable<String, String>();
                    hashtable.put("name", xmlPaymentOption.getName());
                    hashtable.put("heading", xmlPaymentOption.getHeading(s));
                    hashtable.put("detailedInfo", xmlPaymentOption.getDetailedInfo(s));
                    hashtable.put("extraInfos", (String)xmlPaymentOption.getLocalizedExtraInfoText(s));
                    vector.addElement(hashtable);
                }
            }
        }
        catch (Exception ex) {
            LogHolder.log(7, LogType.PAY, "could not get payment options");
        }
        return vector;
    }
    
    public static void showPassivePaymentDialog(final JAPDialog japDialog, final XMLPassivePayment xmlPassivePayment, final long n, final long n2) {
        final PassivePaymentDetails passivePaymentDetails = new PassivePaymentDetails(japDialog, xmlPassivePayment, n, n2);
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
        MSG_OK_BUTTON = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_ok_button";
        MSG_DETAILSBUTTON = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_detailsbutton";
        MSG_RELOADBUTTON = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_reloadbutton";
        MSG_CANCELBUTTON = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_cancelbutton";
        MSG_FETCHING = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_fetching";
        MSG_TAN = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_tan";
        MSG_AMOUNT = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_amount";
        MSG_STATUS = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_status";
        MSG_TRANSACTION_DATE = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_transaction_date";
        MSG_DETAILS_FAILED = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_details_failed";
        MSG_FETCHING_TAN = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_fetchingTAN";
        MSG_ACCOUNTNUMBER = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_accountnumber";
        MSG_VOLUMEPLAN = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_volumeplan";
        MSG_PAYMENTMETHOD = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_paymentmethod";
        MSG_USEDSTATUS = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_usedstatus";
        MSG_OPENSTATUS = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_openstatus";
        MSG_EXPIREDSTATUS = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_expiredstatus";
        MSG_PAYMENT_COMPLETED = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_paymentcompleted";
        MSG_PAYMENT_EXPIRED = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_paymentexpired";
        MSG_NO_OPEN_TRANSFERS = ((TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog == null) ? (TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog = class$("jap.pay.TransactionOverviewDialog")) : TransactionOverviewDialog.class$jap$pay$TransactionOverviewDialog).getName() + "_noopentransfers";
    }
    
    private class MyTableModel extends AbstractTableModel
    {
        private XMLTransactionOverview m_overview;
        static /* synthetic */ Class class$java$lang$String;
        static /* synthetic */ Class class$java$util$Date;
        static /* synthetic */ Class class$jap$pay$TransactionOverviewDialog$TablecellAmount;
        static /* synthetic */ Class class$java$lang$Object;
        
        public MyTableModel(final XMLTransactionOverview overview) {
            this.m_overview = overview;
        }
        
        public int getColumnCount() {
            return 7;
        }
        
        public int getRowCount() {
            return this.m_overview.size();
        }
        
        public Class getColumnClass(final int n) {
            switch (n) {
                case 0: {
                    return (MyTableModel.class$java$lang$String == null) ? (MyTableModel.class$java$lang$String = class$("java.lang.String")) : MyTableModel.class$java$lang$String;
                }
                case 1: {
                    return (MyTableModel.class$java$lang$String == null) ? (MyTableModel.class$java$lang$String = class$("java.lang.String")) : MyTableModel.class$java$lang$String;
                }
                case 2: {
                    return (MyTableModel.class$java$util$Date == null) ? (MyTableModel.class$java$util$Date = class$("java.util.Date")) : MyTableModel.class$java$util$Date;
                }
                case 3: {
                    return (MyTableModel.class$jap$pay$TransactionOverviewDialog$TablecellAmount == null) ? (MyTableModel.class$jap$pay$TransactionOverviewDialog$TablecellAmount = class$("jap.pay.TransactionOverviewDialog$TablecellAmount")) : MyTableModel.class$jap$pay$TransactionOverviewDialog$TablecellAmount;
                }
                case 5: {
                    return (MyTableModel.class$java$lang$String == null) ? (MyTableModel.class$java$lang$String = class$("java.lang.String")) : MyTableModel.class$java$lang$String;
                }
                case 6: {
                    return (MyTableModel.class$java$lang$String == null) ? (MyTableModel.class$java$lang$String = class$("java.lang.String")) : MyTableModel.class$java$lang$String;
                }
                case 7: {
                    return (MyTableModel.class$java$lang$String == null) ? (MyTableModel.class$java$lang$String = class$("java.lang.String")) : MyTableModel.class$java$lang$String;
                }
                default: {
                    return (MyTableModel.class$java$lang$Object == null) ? (MyTableModel.class$java$lang$Object = class$("java.lang.Object")) : MyTableModel.class$java$lang$Object;
                }
            }
        }
        
        public Object getValueAt(final int n, final int n2) {
            final Hashtable<K, String> hashtable = this.m_overview.getTans().elementAt(n);
            switch (n2) {
                case 0: {
                    final String s = hashtable.get("accountnumber");
                    if (s == null) {
                        return new String("");
                    }
                    return s;
                }
                case 1: {
                    return hashtable.get("tan");
                }
                case 2: {
                    try {
                        return new Date(Long.parseLong(hashtable.get("date")));
                    }
                    catch (Exception ex) {
                        return null;
                    }
                }
                case 3: {
                    try {
                        return new TablecellAmount(Long.parseLong(hashtable.get("amount")));
                    }
                    catch (Exception ex2) {
                        return new String("");
                    }
                }
                case 4: {
                    try {
                        return hashtable.get("volumeplan");
                    }
                    catch (Exception ex3) {
                        return new String("");
                    }
                }
                case 5: {
                    try {
                        final String s2 = hashtable.get("paymentmethod");
                        if (s2.equalsIgnoreCase("null")) {
                            return new String("");
                        }
                        return "<html>" + s2 + "</html>";
                    }
                    catch (Exception ex4) {
                        return new String("");
                    }
                }
                case 6: {
                    try {
                        return this.transactionStatus(hashtable);
                    }
                    catch (Exception ex5) {
                        return new String("");
                    }
                    break;
                }
            }
            return JAPMessages.getString("unknown");
        }
        
        private String transactionStatus(final Hashtable hashtable) {
            if (new Boolean(hashtable.get("used"))) {
                return JAPMessages.getString(TransactionOverviewDialog.MSG_USEDSTATUS);
            }
            return JAPMessages.getString(TransactionOverviewDialog.MSG_OPENSTATUS);
        }
        
        public String getColumnName(final int n) {
            switch (n) {
                case 0: {
                    return JAPMessages.getString(TransactionOverviewDialog.MSG_ACCOUNTNUMBER);
                }
                case 1: {
                    return JAPMessages.getString(TransactionOverviewDialog.MSG_TAN);
                }
                case 2: {
                    return JAPMessages.getString(TransactionOverviewDialog.MSG_TRANSACTION_DATE);
                }
                case 3: {
                    return JAPMessages.getString(TransactionOverviewDialog.MSG_AMOUNT);
                }
                case 4: {
                    return JAPMessages.getString(TransactionOverviewDialog.MSG_VOLUMEPLAN);
                }
                case 5: {
                    return JAPMessages.getString(TransactionOverviewDialog.MSG_PAYMENTMETHOD);
                }
                case 6: {
                    return JAPMessages.getString(TransactionOverviewDialog.MSG_STATUS);
                }
                default: {
                    return "---";
                }
            }
        }
        
        public boolean isCellEditable(final int n, final int n2) {
            return false;
        }
        
        static /* synthetic */ Class class$(final String s) {
            try {
                return Class.forName(s);
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
    }
    
    protected class TablecellAmount
    {
        long m_theAmount;
        
        public TablecellAmount(final long theAmount) {
            this.m_theAmount = theAmount;
        }
        
        public String toString() {
            return JAPUtil.formatEuroCentValue(this.m_theAmount);
        }
        
        public long getLongValue() {
            return this.m_theAmount;
        }
    }
}
