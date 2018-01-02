// 
// Decompiled by Procyon v0.5.30
// 

package jap.pay;

import anon.util.captcha.IImageEncodedCaptcha;
import anon.util.captcha.ICaptchaSender;
import logging.LogHolder;
import javax.swing.SwingUtilities;
import logging.LogType;
import anon.client.TrustModel;
import jap.JAPModel;
import anon.infoservice.Database;
import anon.pay.PaymentInstanceDBEntry;
import anon.infoservice.MixCascade;
import gui.GUIUtils;
import jap.JAPConstants;
import anon.pay.xml.XMLBalance;
import jap.JAPController;
import anon.pay.AIControlChannel;
import jap.JAPUtil;
import java.sql.Timestamp;
import javax.swing.Icon;
import anon.pay.xml.XMLErrorMessage;
import anon.pay.IPaymentListener;
import java.awt.event.MouseListener;
import anon.pay.PayAccount;
import java.awt.Color;
import java.awt.Cursor;
import gui.dialog.JAPDialog;
import anon.util.Util;
import anon.pay.PayAccountsFile;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Insets;
import anon.util.JAPMessages;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Window;
import jap.JAPNewView;
import anon.util.JobQueue;
import javax.swing.JLabel;
import gui.JAPProgressBar;
import javax.swing.ImageIcon;
import gui.FlippingPanel;

public class PaymentMainPanel extends FlippingPanel
{
    public static final long WARNING_AMOUNT = 50000000L;
    public static final long WARNING_TIME = 604800000L;
    public static final long FULL_AMOUNT = 200000000L;
    private static final String MSG_TITLE;
    private static final String MSG_LASTUPDATE;
    private static final String MSG_PAYMENTNOTACTIVE;
    private static final String MSG_NEARLYEMPTY_CREATE_ACCOUNT;
    private static final String MSG_NEARLYEXPIRED_CREATE_ACCOUNT;
    private static final String MSG_SESSIONSPENT;
    private static final String MSG_TOTALSPENT;
    private static final String MSG_NO_ACTIVE_ACCOUNT;
    private static final String MSG_ENABLE_AUTO_SWITCH;
    private static final String MSG_WITH_COSTS;
    private static final String MSG_CREATE_ACCOUNT_TITLE;
    private static final String MSG_CHOOSE_FREE_SERVICES_ONLY;
    private static final String MSG_EXPERIMENTAL;
    private static final String MSG_TITLE_FLAT;
    private static final String MSG_VALID_UNTIL;
    private static final String MSG_EURO_BALANCE;
    private static final String MSG_NO_FLATRATE;
    private static final String MSG_WANNA_CHARGE;
    private static final String MSG_TT_CREATE_ACCOUNT;
    private static final String MSG_FREE_OF_CHARGE;
    private static final String MSG_OPEN_TRANSACTION;
    private static final String MSG_CREATE_ACCOUNT_QUESTION;
    private static final String MSG_MAYBE_LATER;
    private static final String[] MSG_PAYMENT_ERRORS;
    private ImageIcon[] m_accountIcons;
    private JAPProgressBar m_BalanceProgressBar;
    private JAPProgressBar m_BalanceSmallProgressBar;
    private JLabel m_BalanceText;
    private JLabel m_BalanceTextSmall;
    private JobQueue m_queueUpdate;
    private JLabel m_dateLabel;
    private JAPNewView m_view;
    private MyPaymentListener m_MyPaymentListener;
    private boolean m_notifiedEmpty;
    private boolean m_bShowingError;
    private JLabel m_labelTotalSpent;
    private JLabel m_labelSessionSpent;
    private JLabel m_labelTitle;
    private JLabel m_labelTitleSmall;
    private JLabel m_labelTotalSpentHeader;
    private JLabel m_labelSessionSpentHeader;
    private JLabel m_labelValidUntilHeader;
    private JLabel m_labelValidUntil;
    private long m_spentThisSession;
    static /* synthetic */ Class class$jap$pay$PaymentMainPanel;
    
    public PaymentMainPanel(final JAPNewView view, final JLabel label) {
        super(view);
        this.m_MyPaymentListener = new MyPaymentListener();
        this.m_notifiedEmpty = false;
        this.m_bShowingError = false;
        this.m_view = view;
        this.m_queueUpdate = new JobQueue("Payment Panel Update");
        this.loadIcons();
        final JPanel fullPanel = new JPanel();
        fullPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.m_labelTitle = new JLabel(JAPMessages.getString(PaymentMainPanel.MSG_TITLE) + ":");
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        fullPanel.add(this.m_labelTitle, gridBagConstraints);
        final JPanel panel = new JPanel();
        final Dimension preferredSize = new Dimension(this.m_labelTitle.getFontMetrics(this.m_labelTitle.getFont()).charWidth('9') * 6, 1);
        panel.setPreferredSize(preferredSize);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        fullPanel.add(panel, gridBagConstraints);
        (this.m_BalanceText = new JLabel(" ")).setHorizontalAlignment(4);
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 2;
        fullPanel.add(this.m_BalanceText, gridBagConstraints);
        final JLabel label2 = new JLabel(4) {
            public Dimension getPreferredSize() {
                return label.getPreferredSize();
            }
        };
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        fullPanel.add(label2, gridBagConstraints);
        (this.m_BalanceProgressBar = new JAPProgressBar()).setMinimum(0);
        this.m_BalanceProgressBar.setMaximum(5);
        this.m_BalanceProgressBar.setBorderPainted(false);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        fullPanel.add(this.m_BalanceProgressBar, gridBagConstraints);
        this.m_labelValidUntilHeader = new JLabel(JAPMessages.getString(PaymentMainPanel.MSG_VALID_UNTIL));
        gridBagConstraints.insets = new Insets(10, 20, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        fullPanel.add(this.m_labelValidUntilHeader, gridBagConstraints);
        final JPanel panel2 = new JPanel();
        panel2.setPreferredSize(preferredSize);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        fullPanel.add(panel2, gridBagConstraints);
        (this.m_labelValidUntil = new JLabel(" ")).setHorizontalAlignment(4);
        gridBagConstraints.insets = new Insets(10, 5, 0, 0);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.0;
        fullPanel.add(this.m_labelValidUntil, gridBagConstraints);
        (this.m_labelSessionSpentHeader = new JLabel(JAPMessages.getString(PaymentMainPanel.MSG_SESSIONSPENT))).setVisible(false);
        gridBagConstraints.insets = new Insets(10, 20, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        fullPanel.add(this.m_labelSessionSpentHeader, gridBagConstraints);
        final JPanel panel3 = new JPanel();
        panel3.setPreferredSize(preferredSize);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        fullPanel.add(panel3, gridBagConstraints);
        (this.m_labelSessionSpent = new JLabel(" ")).setVisible(false);
        this.m_labelSessionSpent.setHorizontalAlignment(4);
        gridBagConstraints.insets = new Insets(10, 5, 0, 0);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.0;
        fullPanel.add(this.m_labelSessionSpent, gridBagConstraints);
        this.m_labelTotalSpentHeader = new JLabel(JAPMessages.getString(PaymentMainPanel.MSG_TOTALSPENT));
        gridBagConstraints.insets = new Insets(10, 20, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        fullPanel.add(this.m_labelTotalSpentHeader, gridBagConstraints);
        final JPanel panel4 = new JPanel();
        panel4.setPreferredSize(preferredSize);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        fullPanel.add(panel4, gridBagConstraints);
        (this.m_labelTotalSpent = new JLabel(" ")).setHorizontalAlignment(4);
        gridBagConstraints.insets = new Insets(10, 5, 0, 0);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.0;
        fullPanel.add(this.m_labelTotalSpent, gridBagConstraints);
        (this.m_dateLabel = new JLabel(JAPMessages.getString(PaymentMainPanel.MSG_LASTUPDATE))).setVisible(false);
        gridBagConstraints.insets = new Insets(10, 20, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        fullPanel.add(this.m_dateLabel, gridBagConstraints);
        final JPanel panel5 = new JPanel();
        panel5.setPreferredSize(preferredSize);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        fullPanel.add(panel5, gridBagConstraints);
        this.setFullPanel(fullPanel);
        final JPanel smallPanel = new JPanel();
        smallPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        this.m_labelTitleSmall = new JLabel(JAPMessages.getString(PaymentMainPanel.MSG_TITLE) + ":");
        gridBagConstraints2.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.fill = 2;
        smallPanel.add(this.m_labelTitleSmall, gridBagConstraints2);
        final JPanel panel6 = new JPanel();
        panel6.setPreferredSize(new Dimension(label2.getFontMetrics(label2.getFont()).charWidth('9') * 6, 1));
        gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.weightx = 1.0;
        smallPanel.add(panel6, gridBagConstraints2);
        gridBagConstraints2.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.gridx = 2;
        (this.m_BalanceTextSmall = new JLabel(" ")).setHorizontalAlignment(4);
        smallPanel.add(this.m_BalanceTextSmall, gridBagConstraints2);
        final JLabel label3 = new JLabel(4) {
            public Dimension getPreferredSize() {
                return label.getPreferredSize();
            }
        };
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.insets = new Insets(0, 10, 0, 0);
        smallPanel.add(label3, gridBagConstraints2);
        gridBagConstraints2.gridx = 4;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.insets = new Insets(0, 5, 0, 0);
        (this.m_BalanceSmallProgressBar = new JAPProgressBar()).setMinimum(0);
        this.m_BalanceSmallProgressBar.setMaximum(5);
        this.m_BalanceSmallProgressBar.setBorderPainted(false);
        smallPanel.add(this.m_BalanceSmallProgressBar, gridBagConstraints2);
        this.setSmallPanel(smallPanel);
        final MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                final String toolTipText = ((JLabel)mouseEvent.getSource()).getToolTipText();
                if (toolTipText != null && toolTipText.equals(JAPMessages.getString(AccountSettingsPanel.MSG_BILLING_ERROR_TOOLTIP))) {
                    final PayAccount activeAccount = PayAccountsFile.getInstance().getActiveAccount();
                    if (activeAccount != null) {
                        String name = "";
                        if (activeAccount.getBI() != null) {
                            name = activeAccount.getBI().getName();
                        }
                        JAPDialog.showWarningDialog(PaymentMainPanel.this.m_view, JAPMessages.getString(AccountSettingsPanel.MSG_BILLING_ERROR_EXPLAIN, new String[] { name, "" + Util.formatBytesValueWithUnit(activeAccount.getCurrentCreditCalculated() - activeAccount.getCurrentCreditFromBalance()), "" + activeAccount.getAccountNumber() }), new JAPDialog.LinkedInformation("payment@jondos.de"));
                    }
                }
                else if (((JLabel)mouseEvent.getSource()).getCursor() != Cursor.getDefaultCursor()) {
                    if (((JLabel)mouseEvent.getSource()).getForeground() == Color.blue) {
                        PaymentMainPanel.this.m_view.showConfigDialog("PAYMENT_TAB", PayAccountsFile.getInstance().getActiveAccount());
                    }
                    else {
                        PaymentMainPanel.this.m_view.showConfigDialog("PAYMENT_TAB", new Boolean(true));
                    }
                }
            }
        };
        this.m_BalanceTextSmall.addMouseListener(mouseAdapter);
        this.m_BalanceText.addMouseListener(mouseAdapter);
        PayAccountsFile.getInstance().addPaymentListener(this.m_MyPaymentListener);
        this.updateDisplay(PayAccountsFile.getInstance().getActiveAccount(), false);
    }
    
    public static String translateBIError(final XMLErrorMessage xmlErrorMessage) {
        final String s = "";
        String s2;
        if (xmlErrorMessage.getErrorCode() >= 0 && xmlErrorMessage.getErrorCode() < PaymentMainPanel.MSG_PAYMENT_ERRORS.length) {
            s2 = s + JAPMessages.getString(PaymentMainPanel.MSG_PAYMENT_ERRORS[xmlErrorMessage.getErrorCode()]);
        }
        else {
            s2 = s + xmlErrorMessage.getErrorDescription();
        }
        return s2;
    }
    
    public void stopUpdateQueue() {
        this.m_queueUpdate.stop();
    }
    
    private void updateDisplay(final PayAccount payAccount, final boolean b) {
        if (PayAccountsFile.getInstance().getActiveAccount() != payAccount) {
            return;
        }
        this.m_queueUpdate.addJob(new JobQueue.Job(true) {
            private final /* synthetic */ PaymentMainPanel this$0;
            
            public void runJob() {
                if (payAccount == null) {
                    PaymentMainPanel.this.m_labelValidUntil.setText("");
                    PaymentMainPanel.this.m_BalanceText.setCursor(Cursor.getPredefinedCursor(12));
                    PaymentMainPanel.this.m_BalanceText.setToolTipText(JAPMessages.getString(PaymentMainPanel.MSG_TT_CREATE_ACCOUNT));
                    PaymentMainPanel.this.m_BalanceText.setIcon(null);
                    PaymentMainPanel.this.m_BalanceText.setText(JAPMessages.getString(PaymentMainPanel.MSG_PAYMENTNOTACTIVE));
                    PaymentMainPanel.this.m_BalanceText.setForeground(PaymentMainPanel.this.m_labelValidUntil.getForeground());
                    PaymentMainPanel.this.m_BalanceProgressBar.setValue(0);
                    PaymentMainPanel.this.m_BalanceProgressBar.setEnabled(false);
                    PaymentMainPanel.this.m_spentThisSession = 0L;
                    PaymentMainPanel.this.m_labelSessionSpent.setText("");
                    PaymentMainPanel.this.m_labelTotalSpent.setText("");
                }
                else {
                    final XMLBalance balance = payAccount.getBalance();
                    final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    if (balance != null && payAccount.isCharged(timestamp)) {
                        PaymentMainPanel.this.m_BalanceProgressBar.setEnabled(false);
                        PaymentMainPanel.this.m_BalanceText.setText(Util.formatBytesValueWithUnit(payAccount.getCurrentCredit()));
                        PaymentMainPanel.this.m_BalanceText.setForeground(PaymentMainPanel.this.m_labelValidUntil.getForeground());
                        PaymentMainPanel.this.m_BalanceText.setIcon(null);
                        PaymentMainPanel.this.m_BalanceText.setCursor(Cursor.getDefaultCursor());
                        PaymentMainPanel.this.m_BalanceText.setToolTipText(null);
                        PaymentMainPanel.this.m_labelValidUntil.setText(JAPUtil.formatTimestamp(balance.getFlatEnddate(), false, JAPMessages.getLocale().getLanguage()));
                        final long n = 200000000L;
                        final long currentCredit = payAccount.getCurrentCredit();
                        final double n2 = currentCredit / n;
                        if (n2 > 0.83) {
                            PaymentMainPanel.this.m_BalanceProgressBar.setValue(5);
                        }
                        else if (n2 > 0.66) {
                            PaymentMainPanel.this.m_BalanceProgressBar.setValue(4);
                        }
                        else if (n2 > 0.49) {
                            PaymentMainPanel.this.m_BalanceProgressBar.setValue(3);
                        }
                        else if (n2 > 0.32) {
                            PaymentMainPanel.this.m_BalanceProgressBar.setValue(2);
                        }
                        else if (currentCredit > 0.15) {
                            PaymentMainPanel.this.m_BalanceProgressBar.setValue(1);
                        }
                        else {
                            PaymentMainPanel.this.m_BalanceProgressBar.setValue(0);
                        }
                        PaymentMainPanel.this.m_BalanceProgressBar.setEnabled(true);
                    }
                    else {
                        PaymentMainPanel.this.m_BalanceText.setIcon(null);
                        PaymentMainPanel.this.m_BalanceProgressBar.setValue(0);
                        PaymentMainPanel.this.m_BalanceProgressBar.setEnabled(false);
                        if (balance == null) {
                            PaymentMainPanel.this.m_labelValidUntil.setText("");
                            PaymentMainPanel.this.m_BalanceText.setCursor(Cursor.getDefaultCursor());
                            PaymentMainPanel.this.m_BalanceText.setToolTipText(null);
                            PaymentMainPanel.this.m_BalanceText.setText(Util.formatBytesValueWithUnit(0L));
                            PaymentMainPanel.this.m_BalanceText.setForeground(PaymentMainPanel.this.m_labelValidUntil.getForeground());
                        }
                        else {
                            final Timestamp flatEnddate = balance.getFlatEnddate();
                            final String formatTimestamp = JAPUtil.formatTimestamp(flatEnddate, false, JAPMessages.getLocale().getLanguage());
                            boolean b = false;
                            PaymentMainPanel.this.m_BalanceText.setCursor(Cursor.getPredefinedCursor(12));
                            PaymentMainPanel.this.m_BalanceText.setToolTipText(JAPMessages.getString(PaymentMainPanel.MSG_TT_CREATE_ACCOUNT));
                            if (payAccount.getCurrentCredit() == 0L) {
                                PaymentMainPanel.this.m_labelValidUntil.setText("");
                            }
                            else if (flatEnddate != null && flatEnddate.after(timestamp)) {
                                PaymentMainPanel.this.m_labelValidUntil.setText(formatTimestamp);
                            }
                            else {
                                b = true;
                                PaymentMainPanel.this.m_labelValidUntil.setText(JAPMessages.getString(AccountSettingsPanel.MSG_EXPIRED));
                            }
                            if (payAccount.getCurrentBytes() > 0L && b) {
                                PaymentMainPanel.this.m_BalanceText.setText(JAPMessages.getString(AccountSettingsPanel.MSG_EXPIRED));
                                PaymentMainPanel.this.m_BalanceText.setForeground(PaymentMainPanel.this.m_labelValidUntil.getForeground());
                            }
                            else if (payAccount.getCurrentBytes() <= 0L && payAccount.getCurrentSpent() == 0L && !b) {
                                if (payAccount.getTransCerts().size() > 0 && !payAccount.isTransactionExpired()) {
                                    PaymentMainPanel.this.m_BalanceText.setText(JAPMessages.getString(AccountSettingsPanel.MSG_NO_TRANSACTION));
                                    PaymentMainPanel.this.m_BalanceText.setToolTipText(JAPMessages.getString(AccountSettingsPanel.MSG_SHOW_TRANSACTION_DETAILS));
                                    PaymentMainPanel.this.m_BalanceText.setForeground(Color.blue);
                                }
                                else {
                                    PaymentMainPanel.this.m_BalanceText.setText("");
                                    PaymentMainPanel.this.m_BalanceText.setToolTipText(null);
                                    PaymentMainPanel.this.m_BalanceText.setForeground(PaymentMainPanel.this.m_labelValidUntil.getForeground());
                                }
                            }
                            else {
                                PaymentMainPanel.this.m_BalanceText.setText(JAPMessages.getString(AccountSettingsPanel.MSG_NO_CREDIT));
                                PaymentMainPanel.this.m_BalanceText.setForeground(PaymentMainPanel.this.m_labelValidUntil.getForeground());
                            }
                        }
                    }
                    PaymentMainPanel.this.m_spentThisSession = AIControlChannel.getBytes();
                    PaymentMainPanel.this.m_labelSessionSpent.setText(Util.formatBytesValueWithUnit(PaymentMainPanel.this.m_spentThisSession));
                    PaymentMainPanel.this.m_labelTotalSpent.setText(Util.formatBytesValueWithUnit(payAccount.getCurrentSpent()));
                    if (b && payAccount.getCurrentCredit() <= 50000000L && !PaymentMainPanel.this.m_notifiedEmpty && payAccount.isCharged(timestamp) && PayAccountsFile.getInstance().getAlternativeChargedAccount(JAPController.getInstance().getCurrentMixCascade().getPIID()) == null) {
                        PaymentMainPanel.this.m_notifiedEmpty = true;
                        new Thread(new Runnable() {
                            private final /* synthetic */ PaymentMainPanel$4 this$1 = this$1;
                            
                            public void run() {
                                if (JAPDialog.showYesNoDialog(JAPController.getInstance().getCurrentView(), JAPMessages.getString(PaymentMainPanel.MSG_NEARLYEMPTY_CREATE_ACCOUNT))) {
                                    this.this$1.this$0.m_view.showConfigDialog("PAYMENT_TAB", JAPController.getInstance().getCurrentMixCascade().getPIID());
                                }
                            }
                        }).start();
                    }
                    final Timestamp timestamp2 = new Timestamp(System.currentTimeMillis() + 604800000L);
                    if (b && !PaymentMainPanel.this.m_notifiedEmpty && payAccount.isCharged(timestamp) && !payAccount.isCharged(timestamp2) && PayAccountsFile.getInstance().getAlternativeChargedAccount(JAPController.getInstance().getCurrentMixCascade().getPIID()) == null) {
                        PaymentMainPanel.this.m_notifiedEmpty = true;
                        new Thread(new Runnable() {
                            private final /* synthetic */ PaymentMainPanel$4 this$1 = this$1;
                            
                            public void run() {
                                if (JAPDialog.showYesNoDialog(JAPController.getInstance().getCurrentView(), JAPMessages.getString(PaymentMainPanel.MSG_NEARLYEXPIRED_CREATE_ACCOUNT))) {
                                    this.this$1.this$0.m_view.showConfigDialog("PAYMENT_TAB", JAPController.getInstance().getCurrentMixCascade().getPIID());
                                }
                            }
                        }).start();
                    }
                }
                PaymentMainPanel.this.m_BalanceTextSmall.setText(PaymentMainPanel.this.m_BalanceText.getText());
                PaymentMainPanel.this.m_BalanceTextSmall.setIcon(PaymentMainPanel.this.m_BalanceText.getIcon());
                PaymentMainPanel.this.m_BalanceTextSmall.setForeground(PaymentMainPanel.this.m_BalanceText.getForeground());
                PaymentMainPanel.this.m_BalanceTextSmall.setToolTipText(PaymentMainPanel.this.m_BalanceText.getToolTipText());
                PaymentMainPanel.this.m_BalanceTextSmall.setCursor(PaymentMainPanel.this.m_BalanceText.getCursor());
                PaymentMainPanel.this.m_BalanceSmallProgressBar.setValue(PaymentMainPanel.this.m_BalanceProgressBar.getValue());
                PaymentMainPanel.this.m_BalanceSmallProgressBar.setEnabled(PaymentMainPanel.this.m_BalanceProgressBar.isEnabled());
            }
        });
    }
    
    private void loadIcons() {
        this.m_accountIcons = new ImageIcon[JAPConstants.ACCOUNTICONFNARRAY.length];
        for (int i = 0; i < JAPConstants.ACCOUNTICONFNARRAY.length; ++i) {
            this.m_accountIcons[i] = GUIUtils.loadImageIcon(JAPConstants.ACCOUNTICONFNARRAY[i], false);
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
        MSG_TITLE = AccountSettingsPanel.MSG_ACCOUNT_FLAT_VOLUME;
        MSG_LASTUPDATE = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_lastupdate";
        MSG_PAYMENTNOTACTIVE = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_paymentnotactive";
        MSG_NEARLYEMPTY_CREATE_ACCOUNT = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_nearlyEmptyCreateAccount";
        MSG_NEARLYEXPIRED_CREATE_ACCOUNT = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_nearlyExpiredCreateAccount";
        MSG_SESSIONSPENT = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_sessionspent";
        MSG_TOTALSPENT = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_totalspent";
        MSG_NO_ACTIVE_ACCOUNT = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_noActiveAccount";
        MSG_ENABLE_AUTO_SWITCH = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_enableAutoSwitch";
        MSG_WITH_COSTS = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_withCosts";
        MSG_CREATE_ACCOUNT_TITLE = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_createAccountTitle";
        MSG_CHOOSE_FREE_SERVICES_ONLY = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_chooseFreeServicesOnly";
        MSG_EXPERIMENTAL = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_experimental";
        MSG_TITLE_FLAT = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_title_flat";
        MSG_VALID_UNTIL = AccountSettingsPanel.MSG_ACCOUNT_VALID;
        MSG_EURO_BALANCE = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_euro_balance";
        MSG_NO_FLATRATE = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_no_flatrate";
        MSG_WANNA_CHARGE = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_wannaCharge";
        MSG_TT_CREATE_ACCOUNT = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_ttCreateAccount";
        MSG_FREE_OF_CHARGE = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_freeOfCharge";
        MSG_OPEN_TRANSACTION = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_openTransaction";
        MSG_CREATE_ACCOUNT_QUESTION = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_createAccountQuestion";
        MSG_MAYBE_LATER = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + "_maybeLater";
        MSG_PAYMENT_ERRORS = new String[] { "_xmlSuccess", "_xmlErrorInternal", "_xmlErrorWrongFormat", "_xmlErrorWrongData", "_xmlErrorKeyNotFound", "_xmlErrorBadSignature", "_xmlErrorBadRequest", "_xmlErrorNoAccountCert", "_xmlErrorNoBalance", "_xmlErrorNoConfirmation", "_accountempty", "_xmlErrorCascadeLength", "_xmlErrorDatabase", "_xmlErrorInsufficientBalance", "_xmlErrorNoFlatrateOffered", "_xmlErrorInvalidCode", "_xmlErrorInvalidCC", "_xmlErrorInvalidPriceCerts", "_xmlErrorMultipleLogin", "_xmlErrorNoRecordFound", "_xmlErrorPartialSuccess", "_xmlErrorAccountBlocked" };
        for (int i = 0; i < PaymentMainPanel.MSG_PAYMENT_ERRORS.length; ++i) {
            PaymentMainPanel.MSG_PAYMENT_ERRORS[i] = ((PaymentMainPanel.class$jap$pay$PaymentMainPanel == null) ? (PaymentMainPanel.class$jap$pay$PaymentMainPanel = class$("jap.pay.PaymentMainPanel")) : PaymentMainPanel.class$jap$pay$PaymentMainPanel).getName() + PaymentMainPanel.MSG_PAYMENT_ERRORS[i];
        }
    }
    
    private class MyPaymentListener implements IPaymentListener
    {
        static /* synthetic */ Class class$anon$pay$PaymentInstanceDBEntry;
        static /* synthetic */ Class class$anon$infoservice$MixCascade;
        private final /* synthetic */ PaymentMainPanel this$0;
        
        public void accountActivated(final PayAccount payAccount) {
            PaymentMainPanel.this.updateDisplay(payAccount, true);
        }
        
        public void accountAdded(final PayAccount payAccount) {
        }
        
        public void accountRemoved(final PayAccount payAccount) {
        }
        
        public void creditChanged(final PayAccount payAccount) {
            PaymentMainPanel.this.updateDisplay(payAccount, true);
        }
        
        private String formatCascadeName(final MixCascade mixCascade) {
            String string;
            if (mixCascade == null || mixCascade.getName() == null) {
                string = "";
            }
            else {
                string = ", <b>" + mixCascade.getName() + "</b>,";
            }
            return string;
        }
        
        private String formatOrganisation(String s) {
            s = "JonDos GmbH";
            String string;
            if (s == null) {
                string = "";
            }
            else {
                string = "<b>" + s + "</b>";
            }
            return string;
        }
        
        public int accountCertRequested(final MixCascade mixCascade) {
            final PayAccountsFile instance = PayAccountsFile.getInstance();
            int n = 0;
            final JAPDialog.LinkedHelpContext linkedHelpContext = new JAPDialog.LinkedHelpContext("premium") {
                public boolean isOnTop() {
                    return true;
                }
            };
            Runnable runnable = null;
            final PaymentInstanceDBEntry paymentInstanceDBEntry = (PaymentInstanceDBEntry)Database.getInstance((MyPaymentListener.class$anon$pay$PaymentInstanceDBEntry == null) ? (MyPaymentListener.class$anon$pay$PaymentInstanceDBEntry = class$("anon.pay.PaymentInstanceDBEntry")) : MyPaymentListener.class$anon$pay$PaymentInstanceDBEntry).getEntryById(mixCascade.getPIID());
            if (paymentInstanceDBEntry != null) {
                paymentInstanceDBEntry.getOrganisation();
            }
            final MixCascade mixCascade2 = (MixCascade)Database.getInstance((MyPaymentListener.class$anon$infoservice$MixCascade == null) ? (MyPaymentListener.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : MyPaymentListener.class$anon$infoservice$MixCascade).getEntryById(mixCascade.getId());
            final PayAccount activeAccount = instance.getActiveAccount();
            if (instance.getNumAccounts() == 0 || (activeAccount != null && !activeAccount.getPIID().equals(mixCascade.getPIID()))) {
                JAPController.getInstance().setAnonMode(false);
                n = -32;
                runnable = new Runnable() {
                    private final /* synthetic */ JAPDialog.LinkedHelpContext val$helpAdapter = linkedHelpContext;
                    private final /* synthetic */ MyPaymentListener this$1 = this$1;
                    
                    public void run() {
                        JAPDialog.Options options;
                        if (JAPModel.getInstance().isCascadeAutoSwitched() && !TrustModel.getCurrentTrustModel().isPaymentForced()) {
                            options = new JAPDialog.Options(1) {
                                public String getCancelText() {
                                    return JAPMessages.getString(PaymentMainPanel.MSG_MAYBE_LATER);
                                }
                            };
                        }
                        else {
                            options = new JAPDialog.Options(2);
                        }
                        final int showConfirmDialog = JAPDialog.showConfirmDialog(JAPController.getInstance().getCurrentView(), "" + JAPMessages.getString(PaymentMainPanel.MSG_WITH_COSTS, this.this$1.formatCascadeName(mixCascade2)) + " " + JAPMessages.getString(PaymentMainPanel.MSG_CREATE_ACCOUNT_QUESTION), JAPMessages.getString(PaymentMainPanel.MSG_CREATE_ACCOUNT_TITLE), options, 3, this.val$helpAdapter);
                        if (showConfirmDialog == 0) {
                            JAPController.getInstance().setAllowPaidServices(true);
                            this.this$1.this$0.m_view.showConfigDialog("PAYMENT_TAB", mixCascade.getPIID());
                        }
                        else if (options.getOptionType() == 1) {
                            if (showConfirmDialog == 1 && !TrustModel.getCurrentTrustModel().isEditable()) {
                                TrustModel.forceFreeTrustModel();
                            }
                            JAPController.getInstance().setAllowPaidServices(false);
                            JAPController.getInstance().switchToNextMixCascade();
                            JAPController.getInstance().setAnonMode(true);
                        }
                    }
                };
            }
            else if (activeAccount == null) {
                JAPController.getInstance().setAnonMode(false);
                n = -32;
                runnable = new Runnable() {
                    private final /* synthetic */ JAPDialog.LinkedHelpContext val$helpAdapter = linkedHelpContext;
                    private final /* synthetic */ MyPaymentListener this$1 = this$1;
                    
                    public void run() {
                        JAPDialog.showErrorDialog(JAPController.getInstance().getCurrentView(), "" + JAPMessages.getString(PaymentMainPanel.MSG_NO_ACTIVE_ACCOUNT), LogType.PAY, this.val$helpAdapter);
                        this.this$1.this$0.m_view.showConfigDialog("PAYMENT_TAB", null);
                    }
                };
            }
            else if (!activeAccount.isCharged(new Timestamp(System.currentTimeMillis()))) {
                JAPController.getInstance().setAnonMode(false);
                n = -32;
                runnable = new Runnable() {
                    private final /* synthetic */ JAPDialog.LinkedHelpContext val$helpAdapter = linkedHelpContext;
                    private final /* synthetic */ MyPaymentListener this$1 = this$1;
                    
                    public void run() {
                        final String string = "" + JAPMessages.getString(PaymentMainPanel.MSG_WITH_COSTS, this.this$1.formatCascadeName(mixCascade2)) + " ";
                        boolean b = false;
                        String string2 = null;
                        String s;
                        if (activeAccount.getCurrentSpent() <= 0L && !activeAccount.hasExpired()) {
                            s = string + JAPMessages.getString(PaymentMainPanel.MSG_OPEN_TRANSACTION);
                            b = true;
                        }
                        else {
                            s = string + JAPMessages.getString(PaymentMainPanel.MSG_CREATE_ACCOUNT_QUESTION);
                            string2 = JAPMessages.getString(PaymentMainPanel.MSG_CREATE_ACCOUNT_TITLE);
                        }
                        JAPController.getInstance().setAnonMode(false);
                        JAPDialog.Options options;
                        if (JAPModel.getInstance().isCascadeAutoSwitched() && !TrustModel.getCurrentTrustModel().isPaymentForced()) {
                            options = new JAPDialog.Options(1) {
                                public String getCancelText() {
                                    return JAPMessages.getString(PaymentMainPanel.MSG_MAYBE_LATER);
                                }
                            };
                        }
                        else {
                            options = new JAPDialog.Options(2);
                        }
                        final int showConfirmDialog = JAPDialog.showConfirmDialog(JAPController.getInstance().getCurrentView(), s, string2, options, 3, this.val$helpAdapter);
                        if (showConfirmDialog == 0) {
                            JAPController.getInstance().setAllowPaidServices(true);
                            if (b) {
                                this.this$1.this$0.m_view.showConfigDialog("PAYMENT_TAB", activeAccount);
                            }
                            else {
                                this.this$1.this$0.m_view.showConfigDialog("PAYMENT_TAB", mixCascade.getPIID());
                            }
                        }
                        else if (options.getOptionType() == 1) {
                            if (showConfirmDialog == 1 && !TrustModel.getCurrentTrustModel().isEditable()) {
                                TrustModel.forceFreeTrustModel();
                            }
                            JAPController.getInstance().setAllowPaidServices(false);
                            JAPController.getInstance().switchToNextMixCascade();
                            JAPController.getInstance().setAnonMode(true);
                        }
                    }
                };
            }
            if (runnable != null) {
                if (JAPDialog.isConsoleOnly()) {
                    runnable.run();
                }
                else {
                    SwingUtilities.invokeLater(runnable);
                }
            }
            return n;
        }
        
        public void accountError(final XMLErrorMessage xmlErrorMessage, final boolean b) {
            if (xmlErrorMessage.getErrorCode() <= 0 || xmlErrorMessage.getErrorCode() < 0) {
                return;
            }
            final MixCascade currentMixCascade = JAPController.getInstance().getCurrentMixCascade();
            if (xmlErrorMessage.getErrorCode() == 10) {
                if (!currentMixCascade.isPayment() || PayAccountsFile.getInstance().getChargedAccount(currentMixCascade.getPIID()) != null) {
                    return;
                }
            }
            else if (!currentMixCascade.equals(JAPController.getInstance().switchToNextMixCascade())) {
                return;
            }
            LogHolder.log(4, LogType.NET, "There are no other cascades to choose!");
            for (int n = 0; n < 5 && (JAPController.getInstance().getAnonMode() || JAPController.getInstance().isAnonConnected()); ++n) {
                JAPController.getInstance().setAnonMode(false);
                try {
                    Thread.sleep(200L);
                }
                catch (InterruptedException ex) {
                    break;
                }
            }
            final PaymentInstanceDBEntry paymentInstanceDBEntry = (PaymentInstanceDBEntry)Database.getInstance((MyPaymentListener.class$anon$pay$PaymentInstanceDBEntry == null) ? (MyPaymentListener.class$anon$pay$PaymentInstanceDBEntry = class$("anon.pay.PaymentInstanceDBEntry")) : MyPaymentListener.class$anon$pay$PaymentInstanceDBEntry).getEntryById(currentMixCascade.getPIID());
            if (paymentInstanceDBEntry != null) {
                paymentInstanceDBEntry.getOrganisation();
            }
            if (!PaymentMainPanel.this.m_bShowingError && !b) {
                PaymentMainPanel.this.m_bShowingError = true;
                new Thread(new Runnable() {
                    private final /* synthetic */ MyPaymentListener this$1 = this$1;
                    
                    public void run() {
                        final String translateBIError = PaymentMainPanel.translateBIError(xmlErrorMessage);
                        Component component = this.this$1.this$0;
                        final JAPDialog.LinkedHelpContext linkedHelpContext = new JAPDialog.LinkedHelpContext("premium") {
                            public boolean isOnTop() {
                                return true;
                            }
                        };
                        final JAPDialog.LinkedInformationAdapter linkedInformationAdapter = new JAPDialog.LinkedInformationAdapter() {
                            public boolean isOnTop() {
                                return true;
                            }
                        };
                        final Window parentWindow = GUIUtils.getParentWindow(component);
                        if (parentWindow == null || !parentWindow.isVisible()) {
                            component = JAPController.getInstance().getCurrentView();
                        }
                        if (xmlErrorMessage.getErrorCode() == 10) {
                            final String string = translateBIError + "<br><br>" + JAPMessages.getString(PaymentMainPanel.MSG_WITH_COSTS, this.this$1.formatCascadeName(currentMixCascade)) + " " + JAPMessages.getString(PaymentMainPanel.MSG_CREATE_ACCOUNT_QUESTION);
                            JAPDialog.Options options;
                            if (JAPModel.getInstance().isCascadeAutoSwitched() && !TrustModel.getCurrentTrustModel().isPaymentForced()) {
                                options = new JAPDialog.Options(1) {
                                    public String getCancelText() {
                                        return JAPMessages.getString(PaymentMainPanel.MSG_MAYBE_LATER);
                                    }
                                };
                            }
                            else {
                                options = new JAPDialog.Options(2);
                            }
                            final int showConfirmDialog = JAPDialog.showConfirmDialog(component, string, JAPMessages.getString(PaymentMainPanel.MSG_CREATE_ACCOUNT_TITLE), options, 3, linkedHelpContext);
                            if (showConfirmDialog == 0) {
                                JAPController.getInstance().setAllowPaidServices(true);
                                new Thread(new Runnable() {
                                    private final /* synthetic */ PaymentMainPanel$13 this$2 = this$2;
                                    
                                    public void run() {
                                        if (currentMixCascade.isPayment()) {
                                            this.this$2.this$1.this$0.m_view.showConfigDialog("PAYMENT_TAB", currentMixCascade.getPIID());
                                        }
                                        else {
                                            this.this$2.this$1.this$0.m_view.showConfigDialog("PAYMENT_TAB", new Boolean(true));
                                        }
                                    }
                                }).start();
                            }
                            else if (options.getOptionType() == 1) {
                                if (showConfirmDialog == 1 && !TrustModel.getCurrentTrustModel().isEditable()) {
                                    TrustModel.forceFreeTrustModel();
                                }
                                JAPController.getInstance().setAllowPaidServices(false);
                                JAPController.getInstance().switchToNextMixCascade();
                                JAPController.getInstance().setAnonMode(true);
                            }
                        }
                        else if (!JAPModel.getInstance().isCascadeAutoSwitched()) {
                            if (0 == JAPDialog.showConfirmDialog(component, translateBIError + "<br><br>" + JAPMessages.getString(PaymentMainPanel.MSG_ENABLE_AUTO_SWITCH), JAPMessages.getString(JAPDialog.MSG_TITLE_WARNING), 0, 2, linkedInformationAdapter)) {
                                JAPModel.getInstance().setCascadeAutoSwitch(true);
                                JAPController.getInstance().switchToNextMixCascade();
                                JAPController.getInstance().setAnonMode(true);
                            }
                        }
                        else {
                            JAPDialog.showErrorDialog(component, translateBIError, LogType.PAY, linkedInformationAdapter);
                        }
                        this.this$1.this$0.m_bShowingError = false;
                    }
                }).start();
            }
        }
        
        public void gotCaptcha(final ICaptchaSender captchaSender, final IImageEncodedCaptcha imageEncodedCaptcha) {
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
}
