// 
// Decompiled by Procyon v0.5.30
// 

package jap.pay;

import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.DefaultListCellRenderer;
import java.awt.event.ActionEvent;
import HTTPClient.ForbiddenIOException;
import javax.swing.event.ListSelectionEvent;
import anon.util.SingleStringPasswordReader;
import anon.util.IMiscPasswordReader;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.io.FileOutputStream;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.util.GregorianCalendar;
import gui.dialog.CaptchaContentPane;
import anon.crypto.DSAKeyPair;
import java.io.IOException;
import anon.crypto.AsymmetricCryptoKeyPair;
import jap.pay.wizardnew.JpiSelectionPane;
import jap.JAPConf;
import java.util.Hashtable;
import anon.pay.xml.XMLTransactionOverview;
import anon.client.TrustModel;
import gui.dialog.SimpleWizardContentPane;
import gui.dialog.DialogContentPaneOptions;
import anon.pay.xml.XMLVolumePlan;
import anon.pay.xml.XMLPassivePayment;
import jap.pay.wizardnew.PassivePaymentPane;
import jap.pay.wizardnew.PaymentInfoPane;
import anon.pay.xml.XMLGenericStrings;
import anon.pay.xml.XMLTransCert;
import jap.pay.wizardnew.MethodSelectionPane;
import anon.pay.xml.XMLPaymentOptions;
import jap.pay.wizardnew.VolumePlanSelectionPane;
import anon.pay.BIConnection;
import anon.pay.xml.XMLVolumePlans;
import anon.util.IReturnRunnable;
import java.util.Date;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import anon.pay.xml.XMLGenericText;
import gui.dialog.DialogContentPane;
import gui.dialog.WorkerContentPane;
import gui.dialog.TermsAndConditionsPane;
import java.util.Calendar;
import anon.pay.xml.XMLBalance;
import anon.pay.xml.XMLAccountInfo;
import java.sql.Timestamp;
import anon.util.Util;
import jap.JAPUtil;
import java.text.SimpleDateFormat;
import anon.pay.PaymentInstanceDBEntry;
import java.util.Vector;
import gui.dialog.PasswordContentPane;
import gui.dialog.JAPDialog;
import logging.LogHolder;
import logging.LogType;
import java.util.Enumeration;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import jap.JAPConfInfoService;
import java.awt.Dimension;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ListCellRenderer;
import java.awt.GridBagConstraints;
import javax.swing.plaf.ProgressBarUI;
import gui.GUIUtils;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import jap.JAPModel;
import anon.util.JAPMessages;
import jap.JAPControllerMessage;
import java.util.Observable;
import anon.util.captcha.IImageEncodedCaptcha;
import anon.util.captcha.ICaptchaSender;
import javax.swing.SwingUtilities;
import anon.pay.PayAccount;
import anon.pay.xml.XMLErrorMessage;
import anon.infoservice.MixCascade;
import jap.JAPController;
import anon.pay.PayAccountsFile;
import jap.IJAPConfSavePoint;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import anon.pay.IPaymentListener;
import java.util.Observer;
import javax.swing.event.ListSelectionListener;
import jap.AbstractJAPConfModule;

public class AccountSettingsPanel extends AbstractJAPConfModule implements ListSelectionListener, Observer, IPaymentListener
{
    protected static final String MSG_ACCOUNT_FLAT_VOLUME;
    protected static final String MSG_ACCOUNT_VALID;
    protected static final String MSG_PAYMENT_INSTANCE;
    protected static final String IMG_COINS_DISABLED;
    private static final String MSG_BUTTON_TRANSACTIONS;
    private static final String MSG_BUTTON_DELETE;
    private static final String MSG_BTN_CREATE;
    private static final String MSG_BUTTON_EXPORT;
    private static final String MSG_BUTTONRELOAD;
    private static final String MSG_TRANSACTION_OVERVIEW_DIALOG;
    private static final String MSG_ACCOUNT_SPENT;
    private static final String MSG_ACCOUNT_DEPOSIT;
    private static final String MSG_ACCOUNT_BALANCE;
    private static final String MSG_ACCOUNT_FLAT_ENDDATE;
    private static final String MSG_ACCOUNT_NOFLAT;
    private static final String MSG_ACCOUNT_DETAILS;
    private static final String MSG_ACCOUNT_CREATION_DATE;
    private static final String MSG_ACCOUNT_STATEMENT_DATE;
    private static final String MSG_BUTTON_CHARGE;
    private static final String MSG_BUTTON_BUYFLAT;
    private static final String MSG_FLATTITLE;
    private static final String MSG_BUTTON_SELECT;
    private static final String MSG_BUTTON_CHANGE_PASSWORD;
    private static final String MSG_ACCOUNT_INVALID;
    private static final String MSG_ACCOUNTCREATE;
    private static final String MSG_CREATEERROR;
    private static final String MSG_DIRECT_CONNECTION_FORBIDDEN;
    private static final String MSG_ANON_CONNECTION_FORBIDDEN;
    private static final String MSG_NO_ANONYMITY_POSSIBLY_BLOCKED;
    private static final String MSG_ERROR_FORBIDDEN;
    private static final String MSG_GETACCOUNTSTATEMENT;
    private static final String MSG_GETACCOUNTSTATEMENTTITLE;
    private static final String MSG_ACCOUNTCREATEDESC;
    private static final String MSG_ACCPASSWORDTITLE;
    private static final String MSG_EXPORTENCRYPT;
    private static final String MSG_ACCPASSWORD;
    private static final String MSG_OLDSTATEMENT;
    private static final String MSG_EXPORTED;
    private static final String MSG_ENCRYPT_ACCOUNTS;
    private static final String MSG_NOTEXPORTED;
    private static final String MSG_CONNECTIONACTIVE_SELECT_QUESTION;
    private static final String MSG_CONNECTIONACTIVE_QUESTION;
    private static final String MSG_FETCHINGOPTIONS;
    private static final String MSG_FETCHINGPLANS;
    private static final String MSG_FETCHINGTERMS;
    private static final String MSG_FETCHINGPOLICY;
    private static final String MSG_FETCHINGTAN;
    private static final String MSG_CHARGEWELCOME;
    private static final String MSG_CHARGETITLE;
    private static final String MSG_SENDINGPASSIVE;
    private static final String MSG_SENTPASSIVE;
    private static final String MSG_NOTSENTPASSIVE;
    private static final String MSG_NEWCAPTCHA;
    private static final String MSG_NEWCAPTCHAEASTEREGG;
    private static final String MSG_SHOW_PAYMENT_CONFIRM_DIALOG;
    private static final String MSG_TEST_PI_CONNECTION;
    private static final String MSG_CREATE_KEY_PAIR;
    private static final String MSG_KEY_PAIR_CREATE_ERROR;
    private static final String MSG_FETCHING_BIS;
    private static final String MSG_SAVE_CONFIG;
    private static final String MSG_CREATED_ACCOUNT_NOT_SAVED;
    private static final String MSG_ACCOUNT_IMPORT_FAILED;
    private static final String MSG_ACCOUNT_ALREADY_EXISTING;
    private static final String MSG_ALLOW_DIRECT_CONNECTION;
    private static final String MSG_BI_CONNECTION_LOST;
    private static final String MSG_BUTTON_UNLOCK;
    private static final String MSG_BUTTON_ACTIVATE;
    private static final String MSG_BUTTON_DEACTIVATE;
    private static final String MSG_ERROR_DELETING;
    private static final String MSG_ACCOUNT_DISABLED;
    private static final String MSG_GIVE_ACCOUNT_PASSWORD;
    private static final String MSG_ACTIVATION_SUCCESSFUL;
    private static final String MSG_ACTIVATION_FAILED;
    private static final String MSG_SHOW_AI_ERRORS;
    private static final String MSG_BALANCE_AUTO_UPDATE_ENABLED;
    private static final String MSG_NO_BACKUP;
    private static final String MSG_TOOL_TIP_NO_BACKUP;
    private static final String MSG_TOOL_TIP_ACTIVATE;
    private static final String MSG_TOOL_TIP_EXPIRED;
    private static final String MSG_PASSWORD_EXPORT;
    private static final String MSG_ASK_IF_NOT_SAVED;
    private static final String MSG_NEW_CAPTCHA_HINT;
    private static final String MSG_BILLING_ERROR;
    public static final String MSG_BILLING_ERROR_EXPLAIN;
    public static final String MSG_BILLING_ERROR_TOOLTIP;
    public static final String MSG_SHOW_TRANSACTION_DETAILS;
    public static final String MSG_NO_TRANSACTION;
    public static final String MSG_EXPIRED;
    public static final String MSG_NO_CREDIT;
    private static final String MSG_TERMS_AND_COND_DESC;
    private static final String MSG_TERMS_AND_COND;
    private static final String MSG_TERMS_AND_COND_HINT;
    private static final String MSG_THANK_YOU;
    private static final String MSG_CHARGING_SUCCESSFUL;
    private static final String MSG_BACKUP_WARNING;
    private static final String MSG_ACTIVE_COMPLETE;
    private static final String MSG_COUPON_SENT;
    private static final String MSG_COUPON_FAILED;
    private static final String MSG_COUPON;
    private static final String MSG_FILE_EXISTS;
    private static final Integer[] CONNECT_TIMEOUTS;
    private JButton m_btnCreateAccount;
    private JButton m_btnChargeAccount;
    private JButton m_btnDeleteAccount;
    private JButton m_btnExportAccount;
    private JButton m_btnImportAccount;
    private JButton m_btnTransactions;
    private JButton m_btnSelect;
    private JButton m_btnPassword;
    private JButton m_btnReload;
    private JButton m_btnActivate;
    private JComboBox m_comboAnonymousConnection;
    private JCheckBox m_cbxShowAIErrors;
    private JCheckBox m_cbxBalanceAutoUpdateEnabled;
    private JCheckBox m_cbxAskIfNotSaved;
    private JLabel m_paymentInstance;
    private JLabel m_labelTermsAndConditions;
    private JLabel m_labelCreationDate;
    private JLabel m_labelStatementDate;
    private JLabel m_labelDeposit;
    private JLabel m_labelSpent;
    private JLabel m_labelValid;
    private JLabel m_labelVolume;
    private JLabel m_labelVolumeWarning;
    private JLabel m_lblInactiveMessage;
    private JLabel m_lblNoBackupMessage;
    private JProgressBar m_coinstack;
    private JList m_listAccounts;
    private JComboBox m_comboTimeout;
    private JPanel m_tabBasicSettings;
    private JPanel m_tabAdvancedSettings;
    private boolean m_bReady;
    private boolean m_bDoNotCloseDialog;
    private MyActionListener myActionListener;
    private JTabbedPane m_tabPane;
    static /* synthetic */ Class class$jap$pay$AccountSettingsPanel;
    
    public AccountSettingsPanel() {
        super(null);
        this.m_bReady = true;
        this.m_bDoNotCloseDialog = false;
        this.updateAccountList();
    }
    
    protected boolean initObservers() {
        if (super.initObservers()) {
            synchronized (super.LOCK_OBSERVABLE) {
                PayAccountsFile.getInstance().addPaymentListener(this);
                JAPController.getInstance().addObserver(this);
                return true;
            }
        }
        return false;
    }
    
    public int accountCertRequested(final MixCascade mixCascade) {
        return 0;
    }
    
    public void accountError(final XMLErrorMessage xmlErrorMessage, final boolean b) {
    }
    
    public void accountActivated(final PayAccount payAccount) {
        this.updateAccountList();
    }
    
    public void accountRemoved(final PayAccount payAccount) {
    }
    
    public void accountAdded(final PayAccount payAccount) {
    }
    
    public void creditChanged(final PayAccount payAccount) {
        if (payAccount != null && payAccount == this.getSelectedAccount()) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    AccountSettingsPanel.this.doShowDetails(AccountSettingsPanel.this.getSelectedAccount());
                }
            });
        }
    }
    
    public void gotCaptcha(final ICaptchaSender captchaSender, final IImageEncodedCaptcha imageEncodedCaptcha) {
    }
    
    public void update(final Observable observable, final Object o) {
        if (observable instanceof JAPController && ((JAPControllerMessage)o).getMessageCode() == 3) {
            this.m_cbxAskIfNotSaved.setSelected(JAPController.getInstance().isAskSavePayment());
        }
    }
    
    public String getTabTitle() {
        return JAPMessages.getString("ngPaymentTabTitle");
    }
    
    public void recreateRootPanel() {
        final JPanel rootPanel = this.getRootPanel();
        rootPanel.removeAll();
        if (JAPModel.getDefaultView() == 2) {
            rootPanel.setBorder(new TitledBorder(JAPMessages.getString("ngPayment")));
        }
        this.myActionListener = new MyActionListener();
        this.m_tabPane = new JTabbedPane();
        this.m_tabBasicSettings = this.createBasicSettingsTab();
        this.m_tabPane.insertTab(JAPMessages.getString("ngPseudonymAccounts"), null, this.m_tabBasicSettings, null, 0);
        this.m_tabAdvancedSettings = this.createAdvancedSettingsTab();
        rootPanel.setLayout(new GridBagLayout());
        final GridBagConstraints tabbedRootPanelContraints = AbstractJAPConfModule.createTabbedRootPanelContraints();
        if (JAPModel.getDefaultView() != 2) {
            this.m_tabPane.insertTab(JAPMessages.getString("settingsInfoServiceConfigAdvancedSettingsTabTitle"), null, this.m_tabAdvancedSettings, null, 1);
            rootPanel.add(this.m_tabPane, tabbedRootPanelContraints);
        }
        else {
            tabbedRootPanelContraints.weightx = 0.0;
            tabbedRootPanelContraints.weighty = 0.0;
            rootPanel.add(this.m_tabBasicSettings, tabbedRootPanelContraints);
            tabbedRootPanelContraints.weightx = 1.0;
            tabbedRootPanelContraints.weighty = 1.0;
            rootPanel.add(new JLabel(), tabbedRootPanelContraints);
        }
        this.m_coinstack.setUI(new CoinstackProgressBarUI(GUIUtils.loadImageIcon("coinstack.gif", true), 0, 8));
    }
    
    private JPanel createBasicSettingsTab() {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        (this.m_listAccounts = new JList()).setCellRenderer(new CustomRenderer());
        this.m_listAccounts.addListSelectionListener(this);
        this.m_listAccounts.getSelectionModel().setSelectionMode(0);
        this.m_listAccounts.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    AccountSettingsPanel.this.doSelectAccount(AccountSettingsPanel.this.getSelectedAccount());
                }
            }
        });
        final JPanel panel2 = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        (this.m_btnCreateAccount = new JButton(JAPMessages.getString(AccountSettingsPanel.MSG_BTN_CREATE))).addActionListener(this.myActionListener);
        panel2.add(this.m_btnCreateAccount, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridx;
        (this.m_btnTransactions = new JButton(JAPMessages.getString(AccountSettingsPanel.MSG_BUTTON_TRANSACTIONS))).addActionListener(this.myActionListener);
        panel2.add(this.m_btnTransactions, gridBagConstraints);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridx;
        (this.m_btnPassword = new JButton(JAPMessages.getString(AccountSettingsPanel.MSG_BUTTON_CHANGE_PASSWORD))).addActionListener(this.myActionListener);
        panel2.add(this.m_btnPassword, gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridx;
        gridBagConstraints.weighty = 1.0;
        (this.m_btnImportAccount = new JButton(JAPMessages.getString("ngImportAccount"))).addActionListener(this.myActionListener);
        panel2.add(this.m_btnImportAccount, gridBagConstraints);
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.fill = 1;
        gridBagConstraints5.anchor = 18;
        gridBagConstraints5.weightx = 2.0;
        gridBagConstraints5.weighty = 1.0;
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.gridheight = 6;
        gridBagConstraints5.insets = new Insets(5, 5, 5, 5);
        final JScrollPane scrollPane = new JScrollPane(this.m_listAccounts);
        scrollPane.setHorizontalScrollBarPolicy(31);
        panel.add(scrollPane, gridBagConstraints5);
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints5;
        ++gridBagConstraints6.gridx;
        gridBagConstraints5.fill = 0;
        gridBagConstraints5.gridheight = 1;
        gridBagConstraints5.weighty = 0.0;
        gridBagConstraints5.weightx = 0.0;
        panel.add(new JLabel(JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNT_CREATION_DATE)), gridBagConstraints5);
        final GridBagConstraints gridBagConstraints7 = gridBagConstraints5;
        ++gridBagConstraints7.gridx;
        gridBagConstraints5.weightx = 1.0;
        panel.add(this.m_labelCreationDate = new JLabel(), gridBagConstraints5);
        this.m_labelStatementDate = new JLabel();
        final GridBagConstraints gridBagConstraints8 = gridBagConstraints5;
        --gridBagConstraints8.gridx;
        final GridBagConstraints gridBagConstraints9 = gridBagConstraints5;
        ++gridBagConstraints9.gridy;
        gridBagConstraints5.weightx = 0.0;
        panel.add(new JLabel(JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNT_VALID)), gridBagConstraints5);
        final GridBagConstraints gridBagConstraints10 = gridBagConstraints5;
        ++gridBagConstraints10.gridx;
        gridBagConstraints5.weightx = 1.0;
        panel.add(this.m_labelValid = new JLabel(), gridBagConstraints5);
        final GridBagConstraints gridBagConstraints11 = gridBagConstraints5;
        ++gridBagConstraints11.gridy;
        final GridBagConstraints gridBagConstraints12 = gridBagConstraints5;
        --gridBagConstraints12.gridx;
        gridBagConstraints5.weightx = 0.0;
        panel.add(new JLabel(JAPMessages.getString(AccountSettingsPanel.MSG_PAYMENT_INSTANCE) + ":"), gridBagConstraints5);
        final GridBagConstraints gridBagConstraints13 = gridBagConstraints5;
        ++gridBagConstraints13.gridx;
        gridBagConstraints5.weightx = 1.0;
        panel.add(this.m_paymentInstance = new JLabel(), gridBagConstraints5);
        final GridBagConstraints gridBagConstraints14 = gridBagConstraints5;
        ++gridBagConstraints14.gridy;
        final GridBagConstraints gridBagConstraints15 = gridBagConstraints5;
        --gridBagConstraints15.gridx;
        gridBagConstraints5.gridwidth = 2;
        (this.m_labelTermsAndConditions = new JLabel()).setToolTipText(JAPMessages.getString(AccountSettingsPanel.MSG_TERMS_AND_COND_HINT));
        this.m_labelTermsAndConditions.setCursor(Cursor.getPredefinedCursor(12));
        this.m_labelTermsAndConditions.setForeground(Color.blue);
        this.m_labelTermsAndConditions.addMouseListener(this.myActionListener);
        panel.add(this.m_labelTermsAndConditions, gridBagConstraints5);
        gridBagConstraints5.gridwidth = 1;
        final GridBagConstraints gridBagConstraints16 = gridBagConstraints5;
        ++gridBagConstraints16.gridy;
        gridBagConstraints5.gridwidth = 2;
        gridBagConstraints5.weightx = 1.0;
        (this.m_lblInactiveMessage = new JLabel()).setCursor(Cursor.getPredefinedCursor(12));
        this.m_lblInactiveMessage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                AccountSettingsPanel.this.m_btnActivate.doClick();
            }
        });
        panel.add(this.m_lblInactiveMessage, gridBagConstraints5);
        final GridBagConstraints gridBagConstraints17 = gridBagConstraints5;
        ++gridBagConstraints17.gridy;
        (this.m_lblNoBackupMessage = new JLabel()).addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                AccountSettingsPanel.this.m_btnExportAccount.doClick();
            }
        });
        this.m_lblNoBackupMessage.setCursor(Cursor.getPredefinedCursor(12));
        panel.add(this.m_lblNoBackupMessage, gridBagConstraints5);
        final GridBagConstraints gridBagConstraints18 = gridBagConstraints5;
        ++gridBagConstraints18.gridy;
        gridBagConstraints5.weightx = 0.0;
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridwidth = 3;
        panel.add(panel2, gridBagConstraints5);
        final GridBagConstraints gridBagConstraints19 = gridBagConstraints5;
        ++gridBagConstraints19.gridy;
        final JSeparator separator = new JSeparator(0);
        separator.setPreferredSize(new Dimension(520, 10));
        panel.add(separator, gridBagConstraints5);
        gridBagConstraints5.weightx = 1.0;
        gridBagConstraints5.weighty = 1.0;
        final GridBagConstraints gridBagConstraints20 = gridBagConstraints5;
        ++gridBagConstraints20.gridy;
        gridBagConstraints5.fill = 2;
        panel.add(this.createDetailsPanel(this.myActionListener), gridBagConstraints5);
        this.enableDisableButtons();
        return panel;
    }
    
    private JPanel createAdvancedSettingsTab() {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 10, 5);
        final JPanel panel2 = new JPanel();
        panel2.add(new JLabel(JAPMessages.getString(AccountSettingsPanel.MSG_ALLOW_DIRECT_CONNECTION) + ":"));
        final String[] array = new String[JAPModel.getMsgConnectionAnonymous().length];
        System.arraycopy(JAPModel.getMsgConnectionAnonymous(), 0, array, 0, array.length);
        for (int i = 0; i < array.length; ++i) {
            array[i] = JAPMessages.getString(array[i]);
        }
        panel2.add(this.m_comboAnonymousConnection = new JComboBox(array));
        panel.add(panel2, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridy = 2;
        (this.m_cbxShowAIErrors = new JCheckBox(JAPMessages.getString(AccountSettingsPanel.MSG_SHOW_AI_ERRORS))).setVisible(false);
        panel.add(this.m_cbxShowAIErrors, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        panel.add(this.m_cbxBalanceAutoUpdateEnabled = new JCheckBox(JAPMessages.getString(AccountSettingsPanel.MSG_BALANCE_AUTO_UPDATE_ENABLED)), gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        panel.add(this.m_cbxAskIfNotSaved = new JCheckBox(JAPMessages.getString(AccountSettingsPanel.MSG_ASK_IF_NOT_SAVED)), gridBagConstraints);
        gridBagConstraints.weightx = 0.0;
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        panel.add(new JLabel(JAPMessages.getString(JAPConfInfoService.MSG_CONNECT_TIMEOUT) + " (s):"), gridBagConstraints);
        this.m_comboTimeout = new JComboBox((E[])AccountSettingsPanel.CONNECT_TIMEOUTS);
        gridBagConstraints.fill = 0;
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridx;
        panel.add(this.m_comboTimeout, gridBagConstraints);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        ++gridBagConstraints5.gridy;
        panel.add(new JLabel(), gridBagConstraints);
        this.updateValues(false);
        return panel;
    }
    
    private JPanel createDetailsPanel(final ActionListener actionListener) {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 2;
        panel.add(new JLabel(JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNT_DETAILS)), gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(5, 10, 5, 5);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        gridBagConstraints.gridheight = 5;
        (this.m_coinstack = new JProgressBar(0, 8)).setBorderPainted(false);
        panel.add(this.m_coinstack, gridBagConstraints);
        gridBagConstraints.gridheight = 1;
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridx;
        panel.add(new JLabel(JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNT_FLAT_VOLUME) + ":"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridx;
        gridBagConstraints.gridwidth = 1;
        (this.m_labelVolume = new JLabel()).addMouseListener(this.myActionListener);
        panel.add(this.m_labelVolume, gridBagConstraints);
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        ++gridBagConstraints5.gridx;
        gridBagConstraints.gridwidth = 1;
        (this.m_labelVolumeWarning = new JLabel()).setCursor(Cursor.getPredefinedCursor(12));
        this.m_labelVolumeWarning.addMouseListener(this.myActionListener);
        panel.add(this.m_labelVolumeWarning, gridBagConstraints);
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
        --gridBagConstraints6.gridx;
        gridBagConstraints.gridwidth = 2;
        final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
        --gridBagConstraints7.gridx;
        final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
        ++gridBagConstraints8.gridy;
        panel.add(new JLabel(JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNT_SPENT)), gridBagConstraints);
        final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
        ++gridBagConstraints9.gridx;
        panel.add(this.m_labelSpent = new JLabel(), gridBagConstraints);
        final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
        --gridBagConstraints10.gridx;
        final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
        ++gridBagConstraints11.gridy;
        panel.add(new JLabel(JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNT_DEPOSIT)), gridBagConstraints);
        final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
        ++gridBagConstraints12.gridx;
        panel.add(this.m_labelDeposit = new JLabel(), gridBagConstraints);
        final GridBagConstraints gridBagConstraints13 = gridBagConstraints;
        --gridBagConstraints13.gridx;
        final GridBagConstraints gridBagConstraints14 = gridBagConstraints;
        ++gridBagConstraints14.gridy;
        panel.add(new JLabel(JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNT_STATEMENT_DATE) + ":"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints15 = gridBagConstraints;
        ++gridBagConstraints15.gridx;
        panel.add(this.m_labelStatementDate = new JLabel(), gridBagConstraints);
        final GridBagConstraints gridBagConstraints16 = gridBagConstraints;
        ++gridBagConstraints16.gridy;
        final JPanel panel2 = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
        gridBagConstraints17.fill = 2;
        gridBagConstraints17.anchor = 18;
        gridBagConstraints17.weightx = 0.0;
        gridBagConstraints17.weighty = 0.0;
        gridBagConstraints17.gridx = 0;
        gridBagConstraints17.gridy = 0;
        gridBagConstraints17.insets = new Insets(5, 5, 5, 5);
        (this.m_btnSelect = new JButton(JAPMessages.getString(AccountSettingsPanel.MSG_BUTTON_ACTIVATE))).addActionListener(actionListener);
        panel2.add(this.m_btnSelect, gridBagConstraints17);
        final GridBagConstraints gridBagConstraints18 = gridBagConstraints17;
        ++gridBagConstraints18.gridx;
        (this.m_btnReload = new JButton(JAPMessages.getString(AccountSettingsPanel.MSG_BUTTONRELOAD))).addActionListener(actionListener);
        panel2.add(this.m_btnReload, gridBagConstraints17);
        final GridBagConstraints gridBagConstraints19 = gridBagConstraints17;
        ++gridBagConstraints19.gridx;
        (this.m_btnActivate = new JButton(JAPMessages.getString(AccountSettingsPanel.MSG_BUTTON_UNLOCK))).setVisible(false);
        this.m_btnActivate.addActionListener(actionListener);
        panel2.add(this.m_btnActivate, gridBagConstraints17);
        final GridBagConstraints gridBagConstraints20 = gridBagConstraints17;
        ++gridBagConstraints20.gridx;
        (this.m_btnDeleteAccount = new JButton(JAPMessages.getString(AccountSettingsPanel.MSG_BUTTON_DELETE))).addActionListener(actionListener);
        panel2.add(this.m_btnDeleteAccount, gridBagConstraints17);
        final GridBagConstraints gridBagConstraints21 = gridBagConstraints17;
        ++gridBagConstraints21.gridx;
        gridBagConstraints17.weightx = 1.0;
        gridBagConstraints17.weighty = 1.0;
        (this.m_btnExportAccount = new JButton(JAPMessages.getString(AccountSettingsPanel.MSG_BUTTON_EXPORT))).addActionListener(actionListener);
        panel2.add(this.m_btnExportAccount, gridBagConstraints17);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints22 = gridBagConstraints;
        ++gridBagConstraints22.gridy;
        gridBagConstraints.gridwidth = 4;
        panel.add(panel2, gridBagConstraints);
        return panel;
    }
    
    private void updateAccountList() {
        final Runnable runnable = new Runnable() {
            public void run() {
                synchronized (AccountSettingsPanel.this.m_listAccounts) {
                    int n = -1;
                    final DefaultListModel<PayAccount> model = new DefaultListModel<PayAccount>();
                    final Enumeration accounts = PayAccountsFile.getInstance().getAccounts();
                    int selectedIndex = AccountSettingsPanel.this.m_listAccounts.getSelectedIndex();
                    int n2 = 0;
                    while (accounts.hasMoreElements()) {
                        final PayAccount payAccount = accounts.nextElement();
                        if (PayAccountsFile.getInstance().getActiveAccount() == payAccount) {
                            n = n2;
                        }
                        model.addElement(payAccount);
                        ++n2;
                    }
                    AccountSettingsPanel.this.m_listAccounts.setModel(model);
                    AccountSettingsPanel.this.m_listAccounts.revalidate();
                    if (AccountSettingsPanel.this.m_listAccounts.getModel().getSize() > 0) {
                        if (selectedIndex < 0) {
                            if (n >= 0) {
                                selectedIndex = n;
                            }
                            else {
                                selectedIndex = 0;
                            }
                        }
                        else if (selectedIndex >= AccountSettingsPanel.this.m_listAccounts.getModel().getSize()) {
                            selectedIndex = AccountSettingsPanel.this.m_listAccounts.getModel().getSize() - 1;
                        }
                        AccountSettingsPanel.this.m_listAccounts.setSelectedIndex(selectedIndex);
                        AccountSettingsPanel.this.m_listAccounts.scrollRectToVisible(AccountSettingsPanel.this.m_listAccounts.getCellBounds(selectedIndex, selectedIndex));
                    }
                }
            }
        };
        if (SwingUtilities.isEventDispatchThread()) {
            runnable.run();
        }
        else {
            try {
                SwingUtilities.invokeAndWait(runnable);
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.GUI, ex);
            }
        }
    }
    
    private void enableDisableButtons() {
        final boolean enabled = this.getSelectedAccount() != null && this.getSelectedAccount().getPrivateKey() != null;
        this.m_btnActivate.setEnabled(this.getSelectedAccount() != null && this.getSelectedAccount().getPrivateKey() == null);
        this.m_btnTransactions.setEnabled(enabled);
        this.m_btnExportAccount.setEnabled(enabled);
        this.m_btnReload.setEnabled(enabled);
        this.m_btnSelect.setEnabled(this.getSelectedAccount() != null && this.getSelectedAccount() != PayAccountsFile.getInstance().getActiveAccount());
        this.m_btnDeleteAccount.setEnabled(this.getSelectedAccount() != null);
    }
    
    private void doChangePassword() {
        final JAPDialog japDialog = new JAPDialog(this.getRootPanel(), JAPMessages.getString(AccountSettingsPanel.MSG_ACCPASSWORDTITLE), true);
        PasswordContentPane passwordContentPane;
        if (JAPController.getInstance().getPaymentPassword() != null) {
            passwordContentPane = new PasswordContentPane(japDialog, 3, JAPMessages.getString(AccountSettingsPanel.MSG_ENCRYPT_ACCOUNTS)) {
                public char[] getComparedPassword() {
                    return JAPController.getInstance().getPaymentPassword().toCharArray();
                }
            };
        }
        else {
            passwordContentPane = new PasswordContentPane(japDialog, 1, JAPMessages.getString(AccountSettingsPanel.MSG_ENCRYPT_ACCOUNTS));
        }
        passwordContentPane.updateDialog();
        japDialog.pack();
        japDialog.setVisible(true);
        if (passwordContentPane.getButtonValue() != 2 && passwordContentPane.getButtonValue() != -1) {
            String paymentPassword = new String(passwordContentPane.getPassword());
            if (paymentPassword.equals("")) {
                paymentPassword = null;
            }
            JAPController.getInstance().setPaymentPassword(paymentPassword);
        }
    }
    
    private void doShowTransactions() {
        final Vector<PayAccount> vector = new Vector<PayAccount>();
        final Enumeration accounts = PayAccountsFile.getInstance().getAccounts();
        final PaymentInstanceDBEntry bi = this.getSelectedAccount().getBI();
        while (accounts.hasMoreElements()) {
            final PayAccount payAccount = accounts.nextElement();
            if (payAccount.isTransactionExpired()) {
                continue;
            }
            final PaymentInstanceDBEntry bi2 = payAccount.getBI();
            if (bi2 != null && bi != null) {
                if (!bi2.getId().equalsIgnoreCase(bi.getId())) {
                    continue;
                }
                vector.addElement(payAccount);
            }
            else {
                LogHolder.log(2, LogType.PAY, "JPI is null! Current account: " + bi2 + " " + "Active account: " + bi + " " + "Current account ID: " + payAccount.getAccountNumber());
            }
        }
        new TransactionOverviewDialog(this, JAPMessages.getString(AccountSettingsPanel.MSG_TRANSACTION_OVERVIEW_DIALOG), true, vector);
    }
    
    private synchronized void doShowDetails(final PayAccount payAccount) {
        if (payAccount == null) {
            this.m_coinstack.setValue(0);
            this.m_labelCreationDate.setText("");
            this.m_labelStatementDate.setText("");
            this.m_labelDeposit.setText("");
            this.m_labelSpent.setText("");
            this.m_labelVolume.setText("");
            this.m_labelValid.setText("");
            this.m_paymentInstance.setText("");
            this.m_lblInactiveMessage.setText("");
            this.m_lblNoBackupMessage.setText("");
            return;
        }
        if (payAccount.getPrivateKey() == null) {
            this.m_lblInactiveMessage.setIcon(GUIUtils.loadImageIcon("warning.gif"));
            this.m_lblInactiveMessage.setText(JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNT_DISABLED));
            this.m_lblInactiveMessage.setToolTipText(JAPMessages.getString(AccountSettingsPanel.MSG_TOOL_TIP_ACTIVATE));
        }
        else {
            this.m_lblInactiveMessage.setIcon(null);
            this.m_lblInactiveMessage.setText("");
            this.m_lblInactiveMessage.setToolTipText("");
        }
        if (!payAccount.isBackupDone()) {
            this.m_lblNoBackupMessage.setIcon(GUIUtils.loadImageIcon("warning.gif"));
            this.m_lblNoBackupMessage.setText(JAPMessages.getString(AccountSettingsPanel.MSG_NO_BACKUP));
            this.m_lblNoBackupMessage.setToolTipText(JAPMessages.getString(AccountSettingsPanel.MSG_TOOL_TIP_NO_BACKUP));
        }
        else {
            this.m_lblNoBackupMessage.setIcon(null);
            this.m_lblNoBackupMessage.setText("");
            this.m_lblNoBackupMessage.setToolTipText("");
        }
        final XMLAccountInfo accountInfo = payAccount.getAccountInfo();
        if (accountInfo != null) {
            final XMLBalance balance = accountInfo.getBalance();
            final PaymentInstanceDBEntry bi = payAccount.getBI();
            if (bi == null) {
                this.m_paymentInstance.setText("");
            }
            else {
                this.m_paymentInstance.setText(bi.getName());
            }
            final Calendar termsDate = payAccount.getTermsDate();
            String string = "";
            if (termsDate != null) {
                string = "(" + new SimpleDateFormat("yyyy-MM-dd").format(termsDate.getTime()) + ")";
            }
            this.m_labelTermsAndConditions.setText(JAPMessages.getString(AccountSettingsPanel.MSG_TERMS_AND_COND, string));
            this.m_labelCreationDate.setText(JAPUtil.formatTimestamp(payAccount.getCreationTime(), false, JAPMessages.getLocale().getLanguage()));
            if (balance == null) {
                this.m_labelStatementDate.setText("");
                this.m_labelDeposit.setText("");
                this.m_labelSpent.setText("");
                this.m_coinstack.setValue(0);
                this.m_labelVolume.setText("");
                this.m_labelValid.setText("");
            }
            else {
                final long deposit = balance.getDeposit();
                boolean b = true;
                this.m_labelStatementDate.setText(JAPUtil.formatTimestamp(balance.getTimestamp(), false, JAPMessages.getLocale().getLanguage()));
                this.m_labelSpent.setText(Util.formatBytesValueWithUnit(payAccount.getCurrentSpent()));
                final String language = JAPMessages.getLocale().getLanguage();
                final Timestamp flatEnddate = balance.getFlatEnddate();
                final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                boolean b2 = false;
                if (payAccount.getCurrentCredit() == 0L) {
                    this.m_labelValid.setText("");
                }
                else if (flatEnddate != null && flatEnddate.after(timestamp)) {
                    this.m_labelValid.setText(JAPUtil.formatTimestamp(flatEnddate, false, language));
                }
                else {
                    b2 = true;
                    this.m_labelValid.setText(JAPUtil.formatTimestamp(flatEnddate, false, language) + " (" + JAPMessages.getString(AccountSettingsPanel.MSG_EXPIRED) + ")");
                }
                this.m_labelVolumeWarning.setIcon(null);
                this.m_labelVolumeWarning.setText("");
                this.m_labelVolumeWarning.setToolTipText(null);
                if (payAccount.getCurrentCredit() > 0L) {
                    this.m_labelVolume.setText((b2 ? "(" : "") + Util.formatBytesValueWithUnit(payAccount.getCurrentCredit()) + (b2 ? ")" : ""));
                    this.m_labelVolume.setForeground(this.m_labelValid.getForeground());
                    this.m_labelVolume.setToolTipText(null);
                    this.m_labelVolume.setCursor(Cursor.getDefaultCursor());
                }
                else if (payAccount.getCurrentSpent() == 0L && !b2) {
                    b = false;
                    if (payAccount.getTransCerts().size() > 0 && !payAccount.isTransactionExpired()) {
                        this.m_labelVolume.setText(JAPMessages.getString(AccountSettingsPanel.MSG_NO_TRANSACTION));
                        this.m_labelVolume.setToolTipText(JAPMessages.getString(AccountSettingsPanel.MSG_SHOW_TRANSACTION_DETAILS));
                        this.m_labelVolume.setForeground(Color.blue);
                        this.m_labelVolume.setCursor(Cursor.getPredefinedCursor(12));
                    }
                    else {
                        this.m_labelVolume.setText("");
                        this.m_labelVolume.setToolTipText(null);
                        this.m_labelVolume.setForeground(this.m_labelValid.getForeground());
                        this.m_labelVolume.setCursor(Cursor.getDefaultCursor());
                    }
                }
                else {
                    this.m_labelVolume.setText(JAPMessages.getString(AccountSettingsPanel.MSG_NO_CREDIT));
                    this.m_labelVolume.setToolTipText(null);
                    this.m_labelVolume.setForeground(this.m_labelValid.getForeground());
                    this.m_labelVolume.setCursor(Cursor.getDefaultCursor());
                }
                if (deposit <= 0L && b) {
                    this.m_labelDeposit.setText(JAPMessages.getString(AccountSettingsPanel.MSG_COUPON));
                }
                else {
                    this.m_labelDeposit.setText(JAPUtil.formatEuroCentValue(deposit));
                }
                if (b2) {
                    this.m_coinstack.setValue(0);
                }
                else {
                    final double n = payAccount.getCurrentCredit() / 200000000L;
                    if (n > 0.87) {
                        this.m_coinstack.setValue(8);
                    }
                    else if (n > 0.75) {
                        this.m_coinstack.setValue(7);
                    }
                    else if (n > 0.63) {
                        this.m_coinstack.setValue(6);
                    }
                    else if (n > 0.5) {
                        this.m_coinstack.setValue(5);
                    }
                    else if (n > 0.38) {
                        this.m_coinstack.setValue(4);
                    }
                    else if (n > 0.25) {
                        this.m_coinstack.setValue(3);
                    }
                    else if (n > 0.13) {
                        this.m_coinstack.setValue(2);
                    }
                    else if (n > 0.01) {
                        this.m_coinstack.setValue(1);
                    }
                    else {
                        this.m_coinstack.setValue(0);
                    }
                }
            }
        }
        else {
            this.m_coinstack.setValue(0);
            this.m_labelCreationDate.setText("");
            this.m_labelStatementDate.setText("");
            this.m_labelDeposit.setText("");
            this.m_labelSpent.setText("");
            this.m_labelValid.setText("");
            this.m_labelVolume.setText("");
        }
    }
    
    private PayAccount getSelectedAccount() {
        try {
            return this.m_listAccounts.getSelectedValue();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public void backupAccount() {
        final PayAccount activeAccount = PayAccountsFile.getInstance().getActiveAccount();
        if (activeAccount == null) {
            return;
        }
        if (!activeAccount.isBackupDone()) {
            this.doExportAccount(activeAccount);
        }
        else {
            final Enumeration accounts = PayAccountsFile.getInstance().getAccounts();
            while (accounts.hasMoreElements()) {
                final PayAccount payAccount = accounts.nextElement();
                if (!payAccount.isBackupDone()) {
                    this.doExportAccount(payAccount);
                    break;
                }
            }
        }
    }
    
    public void showTermsAndConditions(final PayAccount payAccount) {
        final JAPDialog japDialog = new JAPDialog(this.getRootPanel(), JAPMessages.getString(TermsAndConditionsPane.MSG_HEADING), true);
        japDialog.setDefaultCloseOperation(2);
        final WorkerContentPane workerContentPane = new WorkerContentPane(japDialog, JAPMessages.getString(AccountSettingsPanel.MSG_FETCHINGTERMS), new FetchTermsRunnable(japDialog, payAccount.getBI(), payAccount.getTerms()));
        workerContentPane.setInterruptThreadSafe(false);
        new TermsAndConditionsPane(japDialog, (TermsAndConditionsPane.IMessages)new TermsAndConditionsPane.TermsAndConditionsMessages()) {
            public CheckError[] checkUpdate() {
                if (payAccount.getTerms() == null) {
                    payAccount.setTerms((XMLGenericText)workerContentPane.getValue());
                }
                return super.checkUpdate();
            }
        }.getButtonCancel().setVisible(false);
        DialogContentPane.updateDialogOptimalSized(workerContentPane);
        japDialog.addWindowListener(new WindowAdapter() {
            public void windowClosed(final WindowEvent windowEvent) {
                AccountSettingsPanel.this.updateAccountList();
            }
        });
        japDialog.setVisible(true);
    }
    
    public void doChargeAccount(final PayAccount payAccount) {
        if (payAccount == null) {
            return;
        }
        if (payAccount.getBalanceValidTime().before(new Date())) {
            JAPDialog.showMessageDialog(this.getRootPanel(), JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNT_INVALID));
            return;
        }
        final JAPDialog japDialog = new JAPDialog(this.getRootPanel(), JAPMessages.getString(AccountSettingsPanel.MSG_CHARGETITLE), true);
        japDialog.setDefaultCloseOperation(2);
        japDialog.setResizable(false);
        this.doChargeAccount(new FixedReturnAccountRunnable(payAccount), japDialog, null, null, new Vector(), true);
        japDialog.setLocationCenteredOnOwner();
        japDialog.setVisible(true);
    }
    
    private void doChargeAccount(final IReturnAccountRunnable returnAccountRunnable, final JAPDialog japDialog, final DialogContentPane dialogContentPane, final IReturnBooleanRunnable returnBooleanRunnable, final Vector vector, final boolean b) {
        final WorkerContentPane workerContentPane = new WorkerContentPane(japDialog, (Runnable)new IReturnRunnable() {
            private XMLVolumePlans m_volumePlans;
            
            public void run() {
                BIConnection biConnection = null;
                try {
                    biConnection = new BIConnection(returnAccountRunnable.getAccount().getBI());
                    biConnection.connect();
                    biConnection.authenticate(returnAccountRunnable.getAccount().getAccountCertificate(), returnAccountRunnable.getAccount().getPrivateKey());
                    LogHolder.log(7, LogType.PAY, "Fetching volume plans");
                    this.m_volumePlans = biConnection.getVolumePlans();
                    biConnection.disconnect();
                }
                catch (Exception ex) {
                    if (biConnection != null) {
                        try {
                            biConnection.disconnect();
                        }
                        catch (Exception ex2) {}
                    }
                    if (!Thread.currentThread().isInterrupted()) {
                        LogHolder.log(2, LogType.NET, "Error fetching payment options: ", ex);
                        AccountSettingsPanel.this.showPIerror(japDialog.getContentPane(), ex);
                        Thread.currentThread().interrupt();
                    }
                }
            }
            
            public Object getValue() {
                return this.m_volumePlans;
            }
        }) {
            public boolean isMoveForwardAllowed() {
                return japDialog.isVisible() && returnBooleanRunnable != null && !returnBooleanRunnable.isTrue();
            }
        };
        workerContentPane.setInterruptThreadSafe(false);
        final VolumePlanSelectionPane volumePlanSelectionPane = new VolumePlanSelectionPane(japDialog, workerContentPane, b);
        final WorkerContentPane workerContentPane2 = new WorkerContentPane(JAPMessages.getString(AccountSettingsPanel.MSG_FETCHINGOPTIONS), (DialogContentPane)new TermsAndConditionsPane((WorkerContentPane)new WorkerContentPane((String)null, (DialogContentPane)volumePlanSelectionPane, (Runnable)null) {
            public Object getValue() {
                return returnAccountRunnable.getAccount().getTerms();
            }
        }, true, (TermsAndConditionsPane.IMessages)new TermsAndConditionsPane.TermsAndConditionsMessages()) {
            public boolean isSkippedAsNextContentPane() {
                return volumePlanSelectionPane.isCouponUsed() || this.isTermsAccepted();
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return true;
            }
        }, (Runnable)new IReturnRunnable() {
            private XMLPaymentOptions m_paymentOptions;
            
            public void run() {
                BIConnection biConnection = null;
                try {
                    biConnection = new BIConnection(returnAccountRunnable.getAccount().getBI());
                    biConnection.connect();
                    biConnection.authenticate(returnAccountRunnable.getAccount().getAccountCertificate(), returnAccountRunnable.getAccount().getPrivateKey());
                    LogHolder.log(7, LogType.PAY, "Fetching payment options");
                    this.m_paymentOptions = biConnection.getPaymentOptions();
                    biConnection.disconnect();
                }
                catch (Exception ex) {
                    if (biConnection != null) {
                        try {
                            biConnection.disconnect();
                        }
                        catch (Exception ex2) {}
                    }
                    if (!Thread.currentThread().isInterrupted()) {
                        LogHolder.log(2, LogType.NET, "Error fetching payment options: " + ex.getMessage());
                        AccountSettingsPanel.this.showPIerror(japDialog.getContentPane(), ex);
                        Thread.currentThread().interrupt();
                    }
                }
            }
            
            public Object getValue() {
                return this.m_paymentOptions;
            }
        }) {
            public boolean isSkippedAsNextContentPane() {
                if (volumePlanSelectionPane.isCouponUsed()) {
                    LogHolder.log(7, LogType.PAY, "Coupon entered, skipping payment options pane");
                    return true;
                }
                return false;
            }
        };
        workerContentPane2.setInterruptThreadSafe(false);
        final MethodSelectionPane methodSelectionPane = new MethodSelectionPane((WorkerContentPane)workerContentPane2) {
            public boolean isSkippedAsNextContentPane() {
                if (volumePlanSelectionPane.isCouponUsed()) {
                    LogHolder.log(7, LogType.PAY, "Coupon entered, skipping payment options pane");
                    return true;
                }
                return false;
            }
        };
        final IReturnRunnable returnRunnable = new IReturnRunnable() {
            private XMLTransCert m_transCert;
            private final /* synthetic */ MethodSelectionPane val$methodSelectionPane = methodSelectionPane;
            
            public void run() {
                if (this.m_transCert == null) {
                    try {
                        LogHolder.log(7, LogType.PAY, "Fetching Transaction Certificate from Payment Instance");
                        String s;
                        String name;
                        String string;
                        if (volumePlanSelectionPane.isCouponUsed()) {
                            name = (s = JAPMessages.getString(AccountSettingsPanel.MSG_COUPON));
                            string = "0";
                        }
                        else {
                            name = volumePlanSelectionPane.getSelectedVolumePlan().getName();
                            s = this.val$methodSelectionPane.getSelectedPaymentOption().getName();
                            string = new Integer(volumePlanSelectionPane.getSelectedVolumePlan().getPrice()).toString();
                        }
                        final XMLGenericStrings xmlGenericStrings = new XMLGenericStrings();
                        xmlGenericStrings.addEntry("plan", name);
                        xmlGenericStrings.addEntry("method", s);
                        xmlGenericStrings.addEntry("amount", string);
                        xmlGenericStrings.addEntry("language", JAPMessages.getLocale().getLanguage());
                        this.m_transCert = returnAccountRunnable.getAccount().charge(xmlGenericStrings);
                        if (this.m_transCert != null) {
                            vector.addElement(this.m_transCert);
                        }
                        this.val$methodSelectionPane.getButtonNo().setVisible(false);
                    }
                    catch (Exception ex) {
                        if (!Thread.currentThread().isInterrupted()) {
                            LogHolder.log(2, LogType.NET, "Error fetching TransCert: ", ex);
                            AccountSettingsPanel.this.showPIerror(japDialog.getContentPane(), ex);
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
            
            public Object getValue() {
                return this.m_transCert;
            }
        };
        final WorkerContentPane workerContentPane3 = new WorkerContentPane(JAPMessages.getString(AccountSettingsPanel.MSG_FETCHINGTAN), (DialogContentPane)methodSelectionPane, (Runnable)returnRunnable) {
            private final /* synthetic */ IReturnRunnable val$fetchTan = returnRunnable;
            
            public boolean isSkippedAsNextContentPane() {
                return this.val$fetchTan.getValue() != null;
            }
        };
        workerContentPane3.setInterruptThreadSafe(false);
        final PassivePaymentPane passivePaymentPane = new PassivePaymentPane(japDialog) {
            private final /* synthetic */ MethodSelectionPane val$methodSelectionPane = methodSelectionPane;
            
            public boolean isSkippedAsNextContentPane() {
                return volumePlanSelectionPane.isCouponUsed() || this.val$methodSelectionPane.getSelectedPaymentOption().getType().equalsIgnoreCase("active");
            }
        };
        final WorkerContentPane workerContentPane4 = new WorkerContentPane(japDialog, (Runnable)new IReturnRunnable() {
            private Boolean m_successful = new Boolean(true);
            private final /* synthetic */ WorkerContentPane val$fetchTanPane = workerContentPane3;
            private final /* synthetic */ MethodSelectionPane val$methodSelectionPane = methodSelectionPane;
            private final /* synthetic */ PassivePaymentPane val$passivePaymentPane = passivePaymentPane;
            
            public void run() {
                final BIConnection biConnection = new BIConnection(returnAccountRunnable.getAccount().getBI());
                XMLPassivePayment enteredInfo = new XMLPassivePayment();
                if (volumePlanSelectionPane.isCouponUsed()) {
                    enteredInfo.addData("code", volumePlanSelectionPane.getEnteredCouponCode());
                    enteredInfo.setPaymentName("Coupon");
                    enteredInfo.addData("accountnumber", new Long(returnAccountRunnable.getAccount().getAccountNumber()).toString());
                    enteredInfo.addData("transfernumber", new Long(((XMLTransCert)this.val$fetchTanPane.getValue()).getTransferNumber()).toString());
                }
                else if (this.val$methodSelectionPane.getSelectedPaymentOption().getType().equalsIgnoreCase("passive")) {
                    enteredInfo = this.val$passivePaymentPane.getEnteredInfo();
                    final XMLVolumePlan selectedVolumePlan = volumePlanSelectionPane.getSelectedVolumePlan();
                    final String name = selectedVolumePlan.getName();
                    enteredInfo.setAmount(selectedVolumePlan.getPrice());
                    enteredInfo.addData("volumeplan", name);
                    enteredInfo.addData("accountnumber", new Long(returnAccountRunnable.getAccount().getAccountNumber()).toString());
                }
                try {
                    biConnection.connect();
                    biConnection.authenticate(returnAccountRunnable.getAccount().getAccountCertificate(), returnAccountRunnable.getAccount().getPrivateKey());
                    if (!biConnection.sendPassivePayment(enteredInfo)) {
                        this.m_successful = new Boolean(false);
                    }
                    biConnection.disconnect();
                }
                catch (Exception ex) {
                    if (biConnection != null) {
                        try {
                            biConnection.disconnect();
                        }
                        catch (Exception ex2) {}
                    }
                    this.m_successful = new Boolean(false);
                    if (!Thread.currentThread().isInterrupted()) {
                        LogHolder.log(2, LogType.PAY, "Could not send PassivePayment to payment instance: " + ex.getMessage());
                        AccountSettingsPanel.this.showPIerror(japDialog.getContentPane(), ex);
                        Thread.currentThread().interrupt();
                    }
                }
            }
            
            public Object getValue() {
                return this.m_successful;
            }
        }) {
            private final /* synthetic */ MethodSelectionPane val$methodSelectionPane = methodSelectionPane;
            
            public boolean isSkippedAsNextContentPane() {
                return !volumePlanSelectionPane.isCouponUsed() && this.val$methodSelectionPane.getSelectedPaymentOption().getType().equalsIgnoreCase("active");
            }
        };
        final SimpleWizardContentPane simpleWizardContentPane = new SimpleWizardContentPane((DialogContentPane.Layout)null) {
            private final /* synthetic */ MethodSelectionPane val$methodSelectionPane = methodSelectionPane;
            private final /* synthetic */ WorkerContentPane val$sendPassivePane = workerContentPane4;
            
            public boolean isSkippedAsNextContentPane() {
                return false;
            }
            
            public CheckError[] checkUpdate() {
                final Vector<String> vector = new Vector<String>();
                final String s = "<Font color='red'><b>";
                final String s2 = "</b></Font>";
                final String string = s + JAPMessages.getString(AccountSettingsPanel.MSG_BACKUP_WARNING) + s2;
                final String string2 = s + JAPMessages.getString(AccountSettingsPanel.MSG_NOTSENTPASSIVE) + s2;
                final String string3 = JAPMessages.getString(AccountSettingsPanel.MSG_SENTPASSIVE);
                final String string4 = JAPMessages.getString(AccountSettingsPanel.MSG_COUPON_SENT);
                final String string5 = s + JAPMessages.getString(AccountSettingsPanel.MSG_COUPON_FAILED) + s2;
                final String string6 = JAPMessages.getString(AccountSettingsPanel.MSG_ACTIVE_COMPLETE);
                final String language = JAPMessages.getLocale().getLanguage();
                String paymentDelay = null;
                final boolean charged = returnAccountRunnable.getAccount().isCharged(new Timestamp(new Date().getTime()));
                String type;
                if (volumePlanSelectionPane.isCouponUsed()) {
                    type = "coupon";
                }
                else {
                    type = this.val$methodSelectionPane.getSelectedPaymentOption().getType();
                    paymentDelay = this.val$methodSelectionPane.getSelectedPaymentOption().getPaymentDelay(language);
                }
                if (type.equalsIgnoreCase("active")) {
                    if (charged) {
                        vector.addElement(JAPMessages.getString(AccountSettingsPanel.MSG_CHARGING_SUCCESSFUL));
                    }
                    else {
                        vector.addElement(string6);
                        vector.addElement(paymentDelay);
                    }
                    vector.addElement(string);
                }
                else if (type.equals("coupon")) {
                    if (this.val$sendPassivePane.getValue()) {
                        vector.addElement(string4);
                        vector.addElement(string);
                    }
                    else {
                        vector.addElement(string5);
                    }
                }
                else if (this.val$sendPassivePane.getValue()) {
                    if (charged) {
                        vector.addElement(JAPMessages.getString(AccountSettingsPanel.MSG_CHARGING_SUCCESSFUL));
                    }
                    else {
                        vector.addElement(string3);
                        vector.addElement(paymentDelay);
                    }
                    vector.addElement(string);
                }
                else {
                    vector.addElement(string2);
                }
                vector.addElement(JAPMessages.getString(AccountSettingsPanel.MSG_THANK_YOU));
                String string7 = "";
                final Enumeration<String> elements = vector.elements();
                while (elements.hasMoreElements()) {
                    final String s3 = elements.nextElement();
                    if (s3 != null) {
                        string7 = string7 + "<p>" + s3 + "</p><br>";
                    }
                }
                this.setText(string7);
                final MixCascade currentMixCascade = JAPController.getInstance().getCurrentMixCascade();
                if (charged && currentMixCascade.isPayment() && currentMixCascade.getPIID().equals(returnAccountRunnable.getAccount().getBI().getId()) && JAPModel.isAutomaticallyReconnected()) {
                    if (TrustModel.getCurrentTrustModel() == TrustModel.getTrustModelDefault() && TrustModel.getTrustModelPremium().isTrusted(currentMixCascade)) {
                        TrustModel.setCurrentTrustModel(TrustModel.getTrustModelPremium());
                    }
                    JAPController.getInstance().setAnonMode(true);
                }
                return null;
            }
        };
        simpleWizardContentPane.getButtonCancel().setVisible(false);
        simpleWizardContentPane.getButtonNo().setVisible(false);
        if (dialogContentPane == null) {
            DialogContentPane.updateDialogOptimalSized(workerContentPane);
        }
    }
    
    public void showOpenTransaction(final PayAccount payAccount) {
        final Vector transCerts = payAccount.getTransCerts();
        if (payAccount != null && transCerts.size() > 0) {
            try {
                final long transferNumber = transCerts.elementAt(0).getTransferNumber();
                final BIConnection biConnection = new BIConnection(payAccount.getBI());
                final JAPDialog japDialog = new JAPDialog(this.getRootPanel(), JAPMessages.getString(TransactionOverviewDialog.MSG_FETCHING_TAN), true);
                final IReturnRunnable returnRunnable = new IReturnRunnable() {
                    Object data;
                    
                    public void run() {
                        try {
                            biConnection.connect();
                            biConnection.authenticate(payAccount.getAccountCertificate(), payAccount.getPrivateKey());
                            final XMLTransactionOverview xmlTransactionOverview = new XMLTransactionOverview(JAPMessages.getLocale().getLanguage());
                            xmlTransactionOverview.addTan(transferNumber);
                            final XMLTransactionOverview fetchTransactionOverview = biConnection.fetchTransactionOverview(xmlTransactionOverview);
                            biConnection.disconnect();
                            if (fetchTransactionOverview == null) {
                                throw new Exception("JPI returned error message rather than transaction overview");
                            }
                            this.data = fetchTransactionOverview.getDataForTransaction(transferNumber);
                        }
                        catch (Exception data) {
                            if (biConnection != null) {
                                try {
                                    biConnection.disconnect();
                                }
                                catch (Exception ex) {}
                            }
                            this.data = data;
                        }
                    }
                    
                    public Object getValue() {
                        return this.data;
                    }
                };
                new WorkerContentPane(japDialog, JAPMessages.getString(TransactionOverviewDialog.MSG_FETCHING_TAN), returnRunnable).updateDialog();
                japDialog.pack();
                japDialog.setVisible(true);
                if (returnRunnable.getValue() == null) {
                    return;
                }
                if (returnRunnable.getValue() instanceof Exception) {
                    throw (Exception)returnRunnable.getValue();
                }
                if (!(returnRunnable.getValue() instanceof Hashtable)) {
                    throw new Exception("Illegal return value!");
                }
                TransactionOverviewDialog.showActivePaymentDialog(JAPConf.getInstance(), new Long(transferNumber).toString(), Long.parseLong((String)((Hashtable)returnRunnable.getValue()).get("amount")), payAccount, (String)((Hashtable)returnRunnable.getValue()).get("volumeplan"), (String)((Hashtable)returnRunnable.getValue()).get("paymentmethod"));
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.PAY, "Cannot connect to Payment Instance!", ex);
                this.showPIerror(JAPConf.getInstance().getContentPane(), ex);
            }
        }
    }
    
    public void doCreateAccount(final String s) {
        final JAPDialog japDialog = new JAPDialog(this.getRootPanel(), JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNTCREATE), true);
        japDialog.setDefaultCloseOperation(0);
        final WorkerContentPane workerContentPane = new WorkerContentPane("", "", (Runnable)new IReturnRunnable() {
            private Vector allJpis;
            
            public void run() {
                if (s != null) {
                    (this.allJpis = new Vector()).addElement(PayAccountsFile.getInstance().getBI(s));
                }
                else {
                    this.allJpis = PayAccountsFile.getInstance().getPaymentInstances();
                }
            }
            
            public Object getValue() {
                return this.allJpis;
            }
        }) {
            public boolean isMoveForwardAllowed() {
                return japDialog.isVisible();
            }
        };
        final JpiSelectionPane jpiSelectionPane = new JpiSelectionPane(japDialog) {
            private final /* synthetic */ WorkerContentPane val$fetchJpisPane = workerContentPane;
            
            public boolean isSkippedAsNextContentPane() {
                return this.isSkippedAsContentPane();
            }
            
            public boolean isSkippedAsContentPane() {
                final Vector vector = (Vector)this.val$fetchJpisPane.getValue();
                return s != null || vector.size() <= 1;
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return this.isSkippedAsContentPane();
            }
        };
        new WorkerContentPane(japDialog, JAPMessages.getString(AccountSettingsPanel.MSG_TEST_PI_CONNECTION) + "...", jpiSelectionPane, new Runnable() {
            private final /* synthetic */ JpiSelectionPane val$jpiPane = jpiSelectionPane;
            
            public void run() {
                if (this.val$jpiPane.getSelectedPaymentInstance() == null) {
                    Thread.currentThread().interrupt();
                    return;
                }
                BIConnection biConnection = null;
                try {
                    biConnection = new BIConnection(this.val$jpiPane.getSelectedPaymentInstance());
                    biConnection.connect();
                    biConnection.disconnect();
                }
                catch (Exception ex) {
                    if (biConnection != null) {
                        try {
                            biConnection.disconnect();
                        }
                        catch (Exception ex2) {}
                    }
                    if (!Thread.currentThread().isInterrupted()) {
                        AccountSettingsPanel.this.showPIerror(japDialog.getContentPane(), ex);
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).setInterruptThreadSafe(false);
        final WorkerContentPane workerContentPane2 = new WorkerContentPane(JAPMessages.getString(AccountSettingsPanel.MSG_FETCHINGTERMS), (DialogContentPane)jpiSelectionPane, (Runnable)new FetchTermsRunnable(japDialog, jpiSelectionPane)) {
            public boolean isSkippedAsNextContentPane() {
                return this.getValue() != null;
            }
            
            public boolean isMoveForwardAllowed() {
                return japDialog.isVisible();
            }
        };
        workerContentPane2.setInterruptThreadSafe(false);
        final IReturnRunnable returnRunnable = new IReturnRunnable() {
            private AsymmetricCryptoKeyPair m_keyPair;
            
            public void run() {
                AccountSettingsPanel.this.m_bDoNotCloseDialog = true;
                this.m_keyPair = PayAccountsFile.getInstance().createAccountKeyPair();
                if (this.m_keyPair == null) {
                    JAPDialog.showErrorDialog(japDialog, JAPMessages.getString(AccountSettingsPanel.MSG_KEY_PAIR_CREATE_ERROR), LogType.PAY);
                    Thread.currentThread().interrupt();
                }
                AccountSettingsPanel.this.m_bDoNotCloseDialog = false;
            }
            
            public Object getValue() {
                return this.m_keyPair;
            }
        };
        final WorkerContentPane workerContentPane3 = new WorkerContentPane(japDialog, JAPMessages.getString(AccountSettingsPanel.MSG_CREATE_KEY_PAIR) + "...", workerContentPane2, returnRunnable);
        workerContentPane3.getButtonCancel().setEnabled(false);
        this.m_bReady = true;
        final IReturnAccountRunnable returnAccountRunnable = new IReturnAccountRunnable() {
            private PayAccount m_payAccount;
            private IOException m_connectionError;
            private final /* synthetic */ JpiSelectionPane val$jpiPane = jpiSelectionPane;
            private final /* synthetic */ WorkerContentPane val$fetchTermsPane = workerContentPane2;
            
            public void run() {
                AccountSettingsPanel.this.m_bReady = false;
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        (this.m_payAccount = PayAccountsFile.getInstance().createAccount(this.val$jpiPane.getSelectedPaymentInstance(), (AsymmetricCryptoKeyPair)workerContentPane3.getValue(), (XMLGenericText)this.val$fetchTermsPane.getValue())).fetchAccountInfo(true);
                        break;
                    }
                    catch (IOException connectionError) {
                        this.m_connectionError = connectionError;
                    }
                    catch (Exception ex) {
                        if (!Thread.currentThread().isInterrupted() && ex.getMessage() != null && !ex.getMessage().equals("CAPTCHA")) {
                            AccountSettingsPanel.this.showPIerror(japDialog.getContentPane(), ex);
                        }
                        else {
                            LogHolder.log(4, LogType.GUI, ex);
                        }
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                this.m_connectionError = null;
            }
            
            public PayAccount getAccount() {
                final Object value = this.getValue();
                if (value instanceof PayAccount) {
                    return (PayAccount)value;
                }
                return null;
            }
            
            public Object getValue() {
                if (this.m_connectionError != null) {
                    return this.m_connectionError;
                }
                return this.m_payAccount;
            }
        };
        final AccountCreationPane accountCreationPane = new AccountCreationPane(japDialog, JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNTCREATEDESC), workerContentPane3, returnAccountRunnable);
        accountCreationPane.setInterruptThreadSafe(false);
        final CaptchaContentPane captchaContentPane = new CaptchaContentPane((DialogContentPane)accountCreationPane) {
            private final /* synthetic */ IReturnRunnable val$keyCreationThread = returnRunnable;
            
            public void gotCaptcha(final ICaptchaSender captchaSender, final IImageEncodedCaptcha imageEncodedCaptcha) {
                if (this.val$keyCreationThread.getValue() != null) {
                    super.gotCaptcha(captchaSender, imageEncodedCaptcha);
                }
            }
        };
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        if ((gregorianCalendar.get(5) == 27 && gregorianCalendar.get(2) == 8) || (gregorianCalendar.get(5) == 4 && gregorianCalendar.get(2) == 10)) {
            captchaContentPane.getButtonNo().setText(JAPMessages.getString(AccountSettingsPanel.MSG_NEWCAPTCHAEASTEREGG));
        }
        else {
            captchaContentPane.getButtonNo().setText(JAPMessages.getString(AccountSettingsPanel.MSG_NEWCAPTCHA));
        }
        PayAccountsFile.getInstance().addPaymentListener(captchaContentPane);
        captchaContentPane.addComponentListener(new ComponentAdapter() {
            private final /* synthetic */ IReturnAccountRunnable val$doIt = returnAccountRunnable;
            private final /* synthetic */ CaptchaContentPane val$captcha = captchaContentPane;
            
            public void componentShown(final ComponentEvent componentEvent) {
                try {
                    if (this.val$doIt.getValue() instanceof IOException) {
                        this.val$captcha.printErrorStatusMessage(JAPMessages.getString(AccountSettingsPanel.MSG_BI_CONNECTION_LOST), LogType.NET);
                    }
                }
                catch (Exception ex) {}
                AccountSettingsPanel.this.m_bDoNotCloseDialog = false;
            }
        });
        final PasswordContentPane passwordContentPane = new PasswordContentPane((DialogContentPane)captchaContentPane, 1, JAPMessages.getString(AccountSettingsPanel.MSG_ACCPASSWORD)) {
            public CheckError[] checkYesOK() {
                final CheckError[] checkYesOK = super.checkYesOK();
                if (checkYesOK == null || checkYesOK.length == 0) {
                    this.setButtonValue(0);
                    if (this.getPassword() != null) {
                        JAPController.getInstance().setPaymentPassword(new String(this.getPassword()));
                    }
                    else {
                        JAPController.getInstance().setPaymentPassword("");
                    }
                }
                return checkYesOK;
            }
            
            public boolean isSkippedAsNextContentPane() {
                return japDialog.isVisible();
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return japDialog.isVisible();
            }
        };
        final IReturnBooleanRunnable returnBooleanRunnable = new IReturnBooleanRunnable() {
            private Boolean m_bAccountSaved = new Boolean(false);
            private final /* synthetic */ IReturnAccountRunnable val$doIt = returnAccountRunnable;
            
            public void run() {
                AccountSettingsPanel.this.m_bDoNotCloseDialog = true;
                if (JAPController.getInstance().saveConfigFile()) {
                    JAPDialog.showErrorDialog(japDialog, JAPMessages.getString(JAPController.MSG_ERROR_SAVING_CONFIG, JAPModel.getInstance().getConfigFile()), LogType.MISC);
                    try {
                        if (AccountSettingsPanel.this.exportAccount(this.val$doIt.getAccount(), japDialog.getContentPane(), JAPController.getInstance().getPaymentPassword())) {
                            this.m_bAccountSaved = new Boolean(true);
                        }
                        else {
                            this.m_bAccountSaved = new Boolean(false);
                        }
                    }
                    catch (Exception ex) {
                        LogHolder.log(2, LogType.MISC, ex);
                    }
                }
                else {
                    this.m_bAccountSaved = new Boolean(true);
                }
                AccountSettingsPanel.this.m_bDoNotCloseDialog = false;
            }
            
            public Object getValue() {
                if (!japDialog.isVisible()) {
                    return new Boolean(true);
                }
                return this.m_bAccountSaved;
            }
            
            public boolean isTrue() {
                return (boolean)this.getValue();
            }
        };
        final WorkerContentPane workerContentPane4 = new WorkerContentPane(japDialog, JAPMessages.getString(AccountSettingsPanel.MSG_SAVE_CONFIG) + "...", (DialogContentPane)passwordContentPane, (Runnable)returnBooleanRunnable) {
            public boolean isMoveBackAllowed() {
                return false;
            }
        };
        workerContentPane4.getButtonCancel().setEnabled(false);
        final SimpleWizardContentPane simpleWizardContentPane = new SimpleWizardContentPane("<Font color=\"red\">" + JAPMessages.getString(AccountSettingsPanel.MSG_CREATED_ACCOUNT_NOT_SAVED) + "</Font>", new DialogContentPane.Layout("", 0), new DialogContentPaneOptions(workerContentPane4)) {
            private final /* synthetic */ IReturnBooleanRunnable val$exportThread = returnBooleanRunnable;
            
            public boolean isSkippedAsNextContentPane() {
                return (boolean)this.val$exportThread.getValue();
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return true;
            }
        };
        simpleWizardContentPane.getButtonCancel().setVisible(false);
        final Vector vector = new Vector();
        japDialog.addWindowListener(new WindowAdapter() {
            private final /* synthetic */ CaptchaContentPane val$captcha = captchaContentPane;
            private final /* synthetic */ IReturnAccountRunnable val$doIt = returnAccountRunnable;
            
            public void windowClosing(final WindowEvent windowEvent) {
                if (!AccountSettingsPanel.this.m_bDoNotCloseDialog) {
                    if (this.val$captcha.isVisible()) {
                        this.val$captcha.setButtonValue(-1);
                        this.val$captcha.checkCancel();
                    }
                    japDialog.dispose();
                }
            }
            
            public void windowClosed(final WindowEvent windowEvent) {
                PayAccountsFile.getInstance().removePaymentListener(this.val$captcha);
                final Object value = this.val$doIt.getValue();
                if (vector.size() == 0 && value != null && value instanceof PayAccount) {
                    PayAccountsFile.getInstance().deleteAccount((PayAccount)value);
                }
                AccountSettingsPanel.this.updateAccountList();
                if (vector.size() != 0 && value != null && value instanceof PayAccount) {
                    AccountSettingsPanel.this.m_listAccounts.setSelectedValue(value, true);
                    final PayAccount access$000 = AccountSettingsPanel.this.getSelectedAccount();
                    if (access$000 != null) {
                        access$000.updated();
                    }
                }
            }
        });
        this.m_bDoNotCloseDialog = false;
        this.doChargeAccount(returnAccountRunnable, japDialog, simpleWizardContentPane, returnBooleanRunnable, vector, true);
        DialogContentPane.updateDialogOptimalSized(workerContentPane);
        captchaContentPane.setText(captchaContentPane.getText() + " " + JAPMessages.getString(AccountSettingsPanel.MSG_NEW_CAPTCHA_HINT, JAPMessages.getString(AccountSettingsPanel.MSG_NEWCAPTCHA)));
        japDialog.setLocationCenteredOnOwner();
        japDialog.setVisible(true);
    }
    
    private void doSelectAccount(final PayAccount activeAccount) {
        boolean hasDisconnected = false;
        if (activeAccount == null) {
            return;
        }
        if (JAPController.getInstance().getAnonMode() && !(hasDisconnected = this.hasDisconnected(true))) {
            return;
        }
        final PayAccountsFile instance = PayAccountsFile.getInstance();
        try {
            instance.setActiveAccount(activeAccount);
        }
        catch (Exception ex) {
            JAPDialog.showErrorDialog(GUIUtils.getParentWindow(this.getRootPanel()), JAPMessages.getString("Could not select account!"), LogType.PAY, ex);
        }
        if (hasDisconnected) {
            this.reconnect();
        }
    }
    
    private DialogContentPane createUpdateAccountPane(final IReturnAccountRunnable returnAccountRunnable, final MethodSelectionPane methodSelectionPane, final JAPDialog japDialog, final DialogContentPane dialogContentPane) {
        final WorkerContentPane workerContentPane = new WorkerContentPane(japDialog, JAPMessages.getString(AccountSettingsPanel.MSG_GETACCOUNTSTATEMENT), dialogContentPane, (Runnable)new Runnable() {
            public void run() {
                try {
                    returnAccountRunnable.getAccount().fetchAccountInfo(true);
                    AccountSettingsPanel.this.updateAccountList();
                }
                catch (Exception ex) {
                    if (!Thread.currentThread().isInterrupted()) {
                        AccountSettingsPanel.this.showPIerror(japDialog.getContentPane(), ex);
                        LogHolder.log(2, LogType.PAY, "Could not get account statement");
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }) {
            public boolean isSkippedAsNextContentPane() {
                return false;
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return this.isSkippedAsNextContentPane();
            }
        };
        workerContentPane.setInterruptThreadSafe(false);
        return workerContentPane;
    }
    
    public void updateAccountShown() {
        this.doGetStatement(this.m_listAccounts.getSelectedValue());
    }
    
    private void doGetStatement(final PayAccount payAccount) {
        if (payAccount == null) {
            return;
        }
        final JAPDialog japDialog = new JAPDialog(GUIUtils.getParentWindow(this.getRootPanel()), JAPMessages.getString(AccountSettingsPanel.MSG_GETACCOUNTSTATEMENTTITLE), true);
        this.createUpdateAccountPane(new FixedReturnAccountRunnable(payAccount), null, japDialog, null).updateDialog();
        japDialog.pack();
        japDialog.setLocationCenteredOnOwner();
        japDialog.setVisible(true);
    }
    
    private void doExportAccount(final PayAccount payAccount) {
        if (payAccount == null) {
            return;
        }
        if (payAccount.getPrivateKey() != null) {
            final JAPDialog japDialog = new JAPDialog(GUIUtils.getParentWindow(this.getRootPanel()), JAPMessages.getString(AccountSettingsPanel.MSG_ACCPASSWORDTITLE), true);
            PasswordContentPane passwordContentPane;
            if (JAPController.getInstance().getPaymentPassword() != null) {
                passwordContentPane = new PasswordContentPane(japDialog, 3, JAPMessages.getString(AccountSettingsPanel.MSG_EXPORTENCRYPT, "" + payAccount.getAccountNumber())) {
                    public char[] getComparedPassword() {
                        return JAPController.getInstance().getPaymentPassword().toCharArray();
                    }
                    
                    public String getOldPasswordLabel() {
                        return JAPMessages.getString(PasswordContentPane.MSG_ENTER_LBL);
                    }
                    
                    public String getNewPasswordLabel() {
                        return JAPMessages.getString(AccountSettingsPanel.MSG_PASSWORD_EXPORT);
                    }
                };
            }
            else {
                passwordContentPane = new PasswordContentPane(japDialog, 1, JAPMessages.getString(AccountSettingsPanel.MSG_EXPORTENCRYPT, "" + payAccount.getAccountNumber())) {
                    public String getNewPasswordLabel() {
                        return JAPMessages.getString(AccountSettingsPanel.MSG_PASSWORD_EXPORT);
                    }
                };
            }
            passwordContentPane.updateDialog();
            japDialog.pack();
            japDialog.setVisible(true);
            if (passwordContentPane.getButtonValue() == 0 && this.exportAccount(payAccount, this.getRootPanel(), new String(passwordContentPane.getPassword()))) {
                payAccount.setBackupDone(System.currentTimeMillis());
                this.doShowDetails(payAccount);
            }
        }
        else if (this.exportAccount(payAccount, this.getRootPanel(), null)) {
            payAccount.setBackupDone(System.currentTimeMillis());
            this.doShowDetails(payAccount);
        }
    }
    
    private boolean exportAccount(final PayAccount payAccount, final Component component, final String s) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(payAccount.getAccountNumber() + ".acc"));
        fileChooser.setFileFilter(new MyFileFilter());
        while (fileChooser.showSaveDialog(component) == 0) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                if (!selectedFile.getName().toLowerCase().endsWith(".acc")) {
                    selectedFile = new File(selectedFile.getParent(), selectedFile.getName() + ".acc");
                }
                if (selectedFile.exists() && !JAPDialog.showYesNoDialog(GUIUtils.getParentWindow(this.getRootPanel()), JAPMessages.getString(AccountSettingsPanel.MSG_FILE_EXISTS))) {
                    continue;
                }
                final Document document = XMLUtil.createDocument();
                document.appendChild(payAccount.toXmlElement(document, s));
                final String string = XMLUtil.toString(XMLUtil.formatHumanReadable(document));
                final FileOutputStream fileOutputStream = new FileOutputStream(selectedFile);
                fileOutputStream.write(string.getBytes());
                fileOutputStream.close();
                JAPDialog.showMessageDialog(GUIUtils.getParentWindow(this.getRootPanel()), JAPMessages.getString(AccountSettingsPanel.MSG_EXPORTED));
                return true;
            }
            catch (Exception ex) {
                JAPDialog.showErrorDialog(GUIUtils.getParentWindow(component), JAPMessages.getString(AccountSettingsPanel.MSG_NOTEXPORTED) + ": " + ex, LogType.PAY);
            }
            break;
        }
        return false;
    }
    
    private void doImportAccount() {
        LogHolder.log(4, LogType.GUI, "Begin method import account...");
        Element element = null;
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new MyFileFilter());
        LogHolder.log(4, LogType.GUI, "Opening file chooser for importing account...");
        final int showMonitoredFileChooser = GUIUtils.showMonitoredFileChooser(fileChooser, this.getRootPanel());
        LogHolder.log(4, LogType.GUI, "File chooser for importing account returned!");
        if (showMonitoredFileChooser == 0) {
            final File selectedFile = fileChooser.getSelectedFile();
            try {
                final Document xmlDocument = XMLUtil.readXMLDocument(selectedFile);
                XMLUtil.removeComments(xmlDocument);
                final Element documentElement = xmlDocument.getDocumentElement();
                if (documentElement.getNodeName().equals("root")) {
                    element = (Element)XMLUtil.getFirstChildByName(documentElement, "Account");
                }
                else {
                    element = documentElement;
                }
            }
            catch (Exception ex) {
                JAPDialog.showErrorDialog(this.getRootPanel(), JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNT_IMPORT_FAILED), LogType.MISC, ex);
            }
            try {
                if (element != null) {
                    XMLUtil.removeComments(element);
                    final PayAccount payAccount = new PayAccount(element, null);
                    payAccount.setBackupDone(selectedFile.lastModified());
                    PayAccountsFile.getInstance().addAccount(payAccount);
                    this.doActivateAccount(payAccount);
                    this.updateAccountList();
                    this.doGetStatement(payAccount);
                }
            }
            catch (Exception ex2) {
                String string = "";
                if (ex2 instanceof PayAccountsFile.AccountAlreadyExistingException) {
                    string = JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNT_ALREADY_EXISTING);
                }
                JAPDialog.showErrorDialog(this.getRootPanel(), JAPMessages.getString(AccountSettingsPanel.MSG_ACCOUNT_IMPORT_FAILED) + string, LogType.MISC, ex2);
            }
        }
    }
    
    private void doActivateAccount(final PayAccount activeAccount) {
        if (activeAccount != null) {
            final JAPDialog japDialog = new JAPDialog(this.getRootPanel(), JAPMessages.getString(AccountSettingsPanel.MSG_ACCPASSWORDTITLE));
            japDialog.setDefaultCloseOperation(1);
            final PasswordContentPane passwordContentPane = new PasswordContentPane(japDialog, 2, JAPMessages.getString(AccountSettingsPanel.MSG_GIVE_ACCOUNT_PASSWORD));
            passwordContentPane.setDefaultButtonOperation(1);
            passwordContentPane.updateDialog();
            japDialog.pack();
            try {
                activeAccount.decryptPrivateKey(passwordContentPane);
                try {
                    final Enumeration accounts = PayAccountsFile.getInstance().getAccounts();
                    while (accounts.hasMoreElements()) {
                        accounts.nextElement().decryptPrivateKey(new SingleStringPasswordReader(passwordContentPane.getPassword()));
                    }
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.GUI, ex);
                }
                if (PayAccountsFile.getInstance().getActiveAccount() == null) {
                    if (activeAccount.getPrivateKey() != null) {
                        PayAccountsFile.getInstance().setActiveAccount(activeAccount);
                    }
                    else {
                        final Enumeration accounts2 = PayAccountsFile.getInstance().getAccounts();
                        while (accounts2.hasMoreElements()) {
                            final PayAccount activeAccount2 = accounts2.nextElement();
                            if (activeAccount2.getPrivateKey() != null) {
                                PayAccountsFile.getInstance().setActiveAccount(activeAccount2);
                            }
                        }
                    }
                }
                this.doShowDetails(activeAccount);
                this.enableDisableButtons();
                this.m_listAccounts.repaint();
                if (activeAccount.getPrivateKey() != null) {
                    JAPDialog.showMessageDialog(this.getRootPanel(), JAPMessages.getString(AccountSettingsPanel.MSG_ACTIVATION_SUCCESSFUL));
                }
            }
            catch (Exception ex2) {
                JAPDialog.showErrorDialog(this.getRootPanel(), JAPMessages.getString(AccountSettingsPanel.MSG_ACTIVATION_FAILED), LogType.PAY, ex2);
            }
            japDialog.dispose();
        }
    }
    
    private boolean hasDisconnected(final boolean b) {
        if (0 == JAPDialog.showConfirmDialog(GUIUtils.getParentWindow(this.getRootPanel()), b ? JAPMessages.getString(AccountSettingsPanel.MSG_CONNECTIONACTIVE_SELECT_QUESTION) : JAPMessages.getString(AccountSettingsPanel.MSG_CONNECTIONACTIVE_QUESTION), JAPMessages.getString(JAPDialog.MSG_TITLE_WARNING), new JAPDialog.Options(2) {
            public String getYesOKText() {
                return JAPMessages.getString(JAPDialog.MSG_BTN_PROCEED);
            }
        }, 2, null)) {
            final JAPDialog japDialog = new JAPDialog(GUIUtils.getParentWindow(this.getRootPanel()), JAPMessages.getString(WorkerContentPane.MSG_PLEASE_WAIT) + "...");
            new WorkerContentPane(japDialog, JAPMessages.getString(WorkerContentPane.MSG_PLEASE_WAIT), new Runnable() {
                public void run() {
                    JAPController.getInstance().stopAnonModeWait();
                }
            }).updateDialog();
            japDialog.pack();
            japDialog.setResizable(false);
            japDialog.setVisible(true);
            return true;
        }
        return false;
    }
    
    private void doDeleteAccount(final PayAccount payAccount) {
        boolean hasDisconnected = false;
        if (payAccount == null) {
            return;
        }
        final PayAccountsFile instance = PayAccountsFile.getInstance();
        final Timestamp timestamp = new Timestamp(new Date().getTime());
        if (instance.getActiveAccount() == payAccount && JAPController.getInstance().getAnonMode() && !(hasDisconnected = this.hasDisconnected(false))) {
            return;
        }
        if (payAccount.getBalance() == null && JAPDialog.showYesNoDialog(GUIUtils.getParentWindow(this.getRootPanel()), JAPMessages.getString("ngDeleteAccountStatement"))) {
            this.doGetStatement(payAccount);
        }
        String s;
        if (!payAccount.hasExpired(timestamp)) {
            if (payAccount.getBalance().getTimestamp().getTime() < System.currentTimeMillis() - 86400000L && JAPDialog.showYesNoDialog(GUIUtils.getParentWindow(this.getRootPanel()), JAPMessages.getString(AccountSettingsPanel.MSG_OLDSTATEMENT))) {
                this.doGetStatement(payAccount);
            }
            if (payAccount.isCharged(timestamp)) {
                s = JAPMessages.getString("ngDeleteAccountCreditLeft");
            }
            else {
                s = JAPMessages.getString("ngReallyDeleteAccount");
            }
        }
        else {
            s = JAPMessages.getString("ngReallyDeleteAccount");
        }
        if (JAPDialog.showYesNoDialog(GUIUtils.getParentWindow(this.getRootPanel()), s)) {
            try {
                this.m_listAccounts.clearSelection();
                instance.deleteAccount(payAccount);
                this.updateAccountList();
                this.doShowDetails(this.getSelectedAccount());
            }
            catch (Exception ex) {
                JAPDialog.showErrorDialog(GUIUtils.getParentWindow(this.getRootPanel()), JAPMessages.getString(AccountSettingsPanel.MSG_ERROR_DELETING), LogType.MISC, ex);
            }
        }
        if (hasDisconnected) {
            this.reconnect();
        }
    }
    
    private void reconnect() {
        final MixCascade currentMixCascade = JAPController.getInstance().getCurrentMixCascade();
        final PayAccount activeAccount = PayAccountsFile.getInstance().getActiveAccount();
        PaymentInstanceDBEntry bi = null;
        if (activeAccount != null) {
            bi = activeAccount.getBI();
        }
        if (JAPModel.isAutomaticallyReconnected() && (!currentMixCascade.isPayment() || (currentMixCascade.getPIID() != null && bi.getId().equals(currentMixCascade.getPIID()) && activeAccount.isCharged(new Timestamp(System.currentTimeMillis()))))) {
            JAPController.getInstance().setAnonMode(true);
        }
    }
    
    public String getHelpContext() {
        return "payment";
    }
    
    protected void onRootPanelShown() {
    }
    
    protected boolean onOkPressed() {
        JAPModel.getInstance().setPaymentAnonymousConnectionSetting(this.m_comboAnonymousConnection.getSelectedIndex());
        PayAccountsFile.getInstance().setIgnoreAIAccountError(!this.m_cbxShowAIErrors.isSelected());
        PayAccountsFile.getInstance().setBalanceAutoUpdateEnabled(this.m_cbxBalanceAutoUpdateEnabled.isSelected());
        JAPController.getInstance().setAskSavePayment(this.m_cbxAskIfNotSaved.isSelected());
        BIConnection.setConnectionTimeout((int)this.m_comboTimeout.getSelectedItem() * 1000);
        return true;
    }
    
    protected void onCancelPressed() {
    }
    
    protected void onResetToDefaultsPressed() {
        this.m_comboAnonymousConnection.setSelectedIndex(0);
        this.m_cbxShowAIErrors.setSelected(true);
        this.m_cbxAskIfNotSaved.setSelected(true);
        this.m_cbxBalanceAutoUpdateEnabled.setSelected(true);
        this.setConnectionTimeout(30000);
    }
    
    protected void onUpdateValues() {
        this.m_comboAnonymousConnection.setSelectedIndex(JAPModel.getInstance().getPaymentAnonymousConnectionSetting());
        this.m_cbxAskIfNotSaved.setSelected(JAPController.getInstance().isAskSavePayment());
        this.m_cbxShowAIErrors.setSelected(!PayAccountsFile.getInstance().isAIAccountErrorIgnored());
        this.m_cbxBalanceAutoUpdateEnabled.setSelected(PayAccountsFile.getInstance().isBalanceAutoUpdateEnabled());
        this.setConnectionTimeout(BIConnection.getConnectionTimeout());
    }
    
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent.getSource() == this.m_listAccounts && this.m_listAccounts.getModel().getSize() > 0) {
            this.doShowDetails(this.getSelectedAccount());
            this.enableDisableButtons();
        }
    }
    
    public void showPIerror(final Component component, final Exception ex) {
        LogHolder.log(3, LogType.PAY, ex);
        if (ex instanceof XMLErrorMessage) {
            JAPDialog.showErrorDialog(component, PaymentMainPanel.translateBIError((XMLErrorMessage)ex), LogType.PAY);
        }
        else if (!JAPModel.getInstance().isAnonConnected() && JAPModel.getInstance().getPaymentAnonymousConnectionSetting() == 1) {
            if (JAPDialog.showConfirmDialog(component, JAPMessages.getString(AccountSettingsPanel.MSG_DIRECT_CONNECTION_FORBIDDEN), 0, 0) == 0) {
                this.m_comboAnonymousConnection.setSelectedIndex(0);
                JAPModel.getInstance().setPaymentAnonymousConnectionSetting(0);
            }
        }
        else if (JAPModel.getInstance().isAnonConnected() && JAPModel.getInstance().getPaymentAnonymousConnectionSetting() == 2) {
            if (JAPDialog.showConfirmDialog(component, JAPMessages.getString(AccountSettingsPanel.MSG_ANON_CONNECTION_FORBIDDEN), 0, 0) == 0) {
                this.m_comboAnonymousConnection.setSelectedIndex(0);
                JAPModel.getInstance().setPaymentAnonymousConnectionSetting(0);
            }
        }
        else if (!JAPModel.getInstance().isAnonConnected()) {
            JAPDialog.showErrorDialog(component, JAPMessages.getString(AccountSettingsPanel.MSG_NO_ANONYMITY_POSSIBLY_BLOCKED), LogType.PAY);
        }
        else if (ex instanceof ForbiddenIOException) {
            JAPDialog.showErrorDialog(component, JAPMessages.getString(AccountSettingsPanel.MSG_ERROR_FORBIDDEN), LogType.PAY);
        }
        else {
            JAPDialog.showErrorDialog(component, JAPMessages.getString(AccountSettingsPanel.MSG_CREATEERROR), LogType.PAY);
        }
    }
    
    private void setConnectionTimeout(final int n) {
        final int n2 = n / 1000;
        if (n2 >= this.m_comboTimeout.getItemAt(this.m_comboTimeout.getItemCount() - 1)) {
            this.m_comboTimeout.setSelectedIndex(this.m_comboTimeout.getItemCount() - 1);
            BIConnection.setConnectionTimeout((int)this.m_comboTimeout.getSelectedItem() * 1000);
        }
        else if (n2 <= this.m_comboTimeout.getItemAt(0)) {
            this.m_comboTimeout.setSelectedIndex(0);
            BIConnection.setConnectionTimeout((int)this.m_comboTimeout.getSelectedItem() * 1000);
        }
        else {
            for (int i = 1; i < this.m_comboTimeout.getItemCount(); ++i) {
                if (n2 <= (int)this.m_comboTimeout.getItemAt(i)) {
                    this.m_comboTimeout.setSelectedIndex(i);
                    break;
                }
            }
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
        MSG_ACCOUNT_FLAT_VOLUME = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_account_flat_volume";
        MSG_ACCOUNT_VALID = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_account_valid";
        MSG_PAYMENT_INSTANCE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_paymentInstance";
        IMG_COINS_DISABLED = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_coins-disabled.gif";
        MSG_BUTTON_TRANSACTIONS = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_button_transactions";
        MSG_BUTTON_DELETE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_button_delete";
        MSG_BTN_CREATE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_btnCreate";
        MSG_BUTTON_EXPORT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_button_export";
        MSG_BUTTONRELOAD = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_buttonreload";
        MSG_TRANSACTION_OVERVIEW_DIALOG = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_transaction_overview_dialog";
        MSG_ACCOUNT_SPENT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_account_spent";
        MSG_ACCOUNT_DEPOSIT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_account_deposit";
        MSG_ACCOUNT_BALANCE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_account_balance";
        MSG_ACCOUNT_FLAT_ENDDATE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_account_flat_enddate";
        MSG_ACCOUNT_NOFLAT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_account_noflat";
        MSG_ACCOUNT_DETAILS = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_account_details";
        MSG_ACCOUNT_CREATION_DATE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_account_creation_date";
        MSG_ACCOUNT_STATEMENT_DATE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_account_statement_date";
        MSG_BUTTON_CHARGE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_button_charge";
        MSG_BUTTON_BUYFLAT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_button_buyflat";
        MSG_FLATTITLE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_flat_title";
        MSG_BUTTON_SELECT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_button_select";
        MSG_BUTTON_CHANGE_PASSWORD = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_button_change_password";
        MSG_ACCOUNT_INVALID = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_account_invalid";
        MSG_ACCOUNTCREATE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_accountcreate";
        MSG_CREATEERROR = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_createerror";
        MSG_DIRECT_CONNECTION_FORBIDDEN = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_directConnectionForbidden";
        MSG_ANON_CONNECTION_FORBIDDEN = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_anonConnectionForbidden";
        MSG_NO_ANONYMITY_POSSIBLY_BLOCKED = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_noAnonymityPossiblyBlocked";
        MSG_ERROR_FORBIDDEN = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_errorForbidden";
        MSG_GETACCOUNTSTATEMENT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_getaccountstatement";
        MSG_GETACCOUNTSTATEMENTTITLE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_getaccountstatementtitle";
        MSG_ACCOUNTCREATEDESC = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_accountcreatedesc";
        MSG_ACCPASSWORDTITLE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_accpasswordtitle";
        MSG_EXPORTENCRYPT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_exportencrypt";
        MSG_ACCPASSWORD = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_accpassword";
        MSG_OLDSTATEMENT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_oldstatement";
        MSG_EXPORTED = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_exported";
        MSG_ENCRYPT_ACCOUNTS = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_encryptAccounts";
        MSG_NOTEXPORTED = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_notexported";
        MSG_CONNECTIONACTIVE_SELECT_QUESTION = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_connectionactive";
        MSG_CONNECTIONACTIVE_QUESTION = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_connectionActiveQuestion";
        MSG_FETCHINGOPTIONS = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_fetchingoptions";
        MSG_FETCHINGPLANS = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_fetchingplans";
        MSG_FETCHINGTERMS = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_fetchingterms";
        MSG_FETCHINGPOLICY = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_fetchingpolicy";
        MSG_FETCHINGTAN = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_fetchingtan";
        MSG_CHARGEWELCOME = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_chargewelcome";
        MSG_CHARGETITLE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_chargetitle";
        MSG_SENDINGPASSIVE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_sendingpassive";
        MSG_SENTPASSIVE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_sentpassive";
        MSG_NOTSENTPASSIVE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_notsentpassive";
        MSG_NEWCAPTCHA = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_newcaptcha";
        MSG_NEWCAPTCHAEASTEREGG = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_newcaptchaEasterEgg";
        MSG_SHOW_PAYMENT_CONFIRM_DIALOG = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_showPaymentConfirmDialog";
        MSG_TEST_PI_CONNECTION = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_testingPIConnection";
        MSG_CREATE_KEY_PAIR = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_creatingKeyPair";
        MSG_KEY_PAIR_CREATE_ERROR = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_keyPairCreateError";
        MSG_FETCHING_BIS = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_fetchingBIs";
        MSG_SAVE_CONFIG = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_savingConfig";
        MSG_CREATED_ACCOUNT_NOT_SAVED = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_createdAccountNotSaved";
        MSG_ACCOUNT_IMPORT_FAILED = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_accountImportFailed";
        MSG_ACCOUNT_ALREADY_EXISTING = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_accountAlreadyExisting";
        MSG_ALLOW_DIRECT_CONNECTION = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_allowDirectConnection";
        MSG_BI_CONNECTION_LOST = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_biConnectionLost";
        MSG_BUTTON_UNLOCK = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_unlockAccount";
        MSG_BUTTON_ACTIVATE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_activateAccount";
        MSG_BUTTON_DEACTIVATE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_deactivateAccount";
        MSG_ERROR_DELETING = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_errorDeletingAccount";
        MSG_ACCOUNT_DISABLED = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_accountDisabled";
        MSG_GIVE_ACCOUNT_PASSWORD = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_giveAccountPassword";
        MSG_ACTIVATION_SUCCESSFUL = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_activationSuccessful";
        MSG_ACTIVATION_FAILED = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_activationFailed";
        MSG_SHOW_AI_ERRORS = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_showAIErrors";
        MSG_BALANCE_AUTO_UPDATE_ENABLED = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_balanceAutoUpdateEnabled";
        MSG_NO_BACKUP = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_noBackup";
        MSG_TOOL_TIP_NO_BACKUP = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_toolTipNoBackup";
        MSG_TOOL_TIP_ACTIVATE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_toolTipActivate";
        MSG_TOOL_TIP_EXPIRED = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_toolTipExpired";
        MSG_PASSWORD_EXPORT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_passwordExport";
        MSG_ASK_IF_NOT_SAVED = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_askIfNotSaved";
        MSG_NEW_CAPTCHA_HINT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_newCaptchaHint";
        MSG_BILLING_ERROR = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_billingError";
        MSG_BILLING_ERROR_EXPLAIN = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_billingErrorExplain";
        MSG_BILLING_ERROR_TOOLTIP = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_billingErrorClick";
        MSG_SHOW_TRANSACTION_DETAILS = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_showTransactionDetails";
        MSG_NO_TRANSACTION = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_noTransaction";
        MSG_EXPIRED = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_expired";
        MSG_NO_CREDIT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_noCredit";
        MSG_TERMS_AND_COND_DESC = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_termsAndConditionsDescription";
        MSG_TERMS_AND_COND = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_termsAndConditions";
        MSG_TERMS_AND_COND_HINT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_termsAndConditionsHint";
        MSG_THANK_YOU = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_thankYou";
        MSG_CHARGING_SUCCESSFUL = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_chargingSuccessful";
        MSG_BACKUP_WARNING = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_backupwarning";
        MSG_ACTIVE_COMPLETE = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_activecomplete";
        MSG_COUPON_SENT = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_couponsent";
        MSG_COUPON_FAILED = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_couponfailed";
        MSG_COUPON = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_coupon";
        MSG_FILE_EXISTS = ((AccountSettingsPanel.class$jap$pay$AccountSettingsPanel == null) ? (AccountSettingsPanel.class$jap$pay$AccountSettingsPanel = class$("jap.pay.AccountSettingsPanel")) : AccountSettingsPanel.class$jap$pay$AccountSettingsPanel).getName() + "_fileExists";
        CONNECT_TIMEOUTS = new Integer[] { new Integer(10), new Integer(20), new Integer(30), new Integer(40), new Integer(50), new Integer(60), new Integer(80), new Integer(100) };
    }
    
    private class MyActionListener extends MouseAdapter implements ActionListener
    {
        private boolean m_bButtonClicked;
        private final /* synthetic */ AccountSettingsPanel this$0;
        
        private MyActionListener() {
            this.m_bButtonClicked = false;
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            this.doAction(mouseEvent.getSource());
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.doAction(actionEvent.getSource());
        }
        
        public void doAction(final Object o) {
            final Thread thread = new Thread(new Runnable() {
                private final /* synthetic */ MyActionListener this$1 = this$1;
                
                public void run() {
                    if (o == this.this$1.this$0.m_btnCreateAccount) {
                        this.this$1.this$0.doCreateAccount(null);
                    }
                    else if (o == this.this$1.this$0.m_btnDeleteAccount) {
                        this.this$1.this$0.doDeleteAccount(this.this$1.this$0.getSelectedAccount());
                    }
                    else if (o == this.this$1.this$0.m_btnImportAccount) {
                        this.this$1.this$0.doImportAccount();
                    }
                    else if (o == this.this$1.this$0.m_btnExportAccount) {
                        this.this$1.this$0.doExportAccount(this.this$1.this$0.getSelectedAccount());
                    }
                    else if (o == this.this$1.this$0.m_btnTransactions) {
                        this.this$1.this$0.doShowTransactions();
                    }
                    else if (o == this.this$1.this$0.m_btnSelect) {
                        if (this.this$1.this$0.getSelectedAccount() != null && this.this$1.this$0.getSelectedAccount().getPrivateKey() == null) {
                            this.this$1.this$0.doActivateAccount(this.this$1.this$0.getSelectedAccount());
                        }
                        if (this.this$1.this$0.getSelectedAccount() != null && this.this$1.this$0.getSelectedAccount().getPrivateKey() != null) {
                            this.this$1.this$0.doSelectAccount(this.this$1.this$0.getSelectedAccount());
                        }
                    }
                    else if (o == this.this$1.this$0.m_btnPassword) {
                        this.this$1.this$0.doChangePassword();
                    }
                    else if (o == this.this$1.this$0.m_btnReload) {
                        this.this$1.this$0.doGetStatement(this.this$1.this$0.getSelectedAccount());
                    }
                    else if (o == this.this$1.this$0.m_btnActivate) {
                        this.this$1.this$0.doActivateAccount(this.this$1.this$0.getSelectedAccount());
                    }
                    else if (o == this.this$1.this$0.m_labelVolume) {
                        if (this.this$1.this$0.m_labelVolume.getForeground() == Color.blue) {
                            this.this$1.this$0.showOpenTransaction(this.this$1.this$0.getSelectedAccount());
                        }
                    }
                    else if (o == this.this$1.this$0.m_labelVolumeWarning) {
                        final PayAccount access$000 = this.this$1.this$0.getSelectedAccount();
                        if (access$000 != null) {
                            String name = "";
                            if (access$000.getBI() != null) {
                                name = access$000.getBI().getName();
                            }
                            JAPDialog.showWarningDialog(this.this$1.this$0.getRootPanel(), JAPMessages.getString(AccountSettingsPanel.MSG_BILLING_ERROR_EXPLAIN, new String[] { name, "" + Util.formatBytesValueWithUnit(access$000.getCurrentCreditCalculated() - access$000.getCurrentCreditFromBalance()), "" + access$000.getAccountNumber() }), new JAPDialog.LinkedInformation("payment@jondos.de"));
                        }
                    }
                    else if (o == this.this$1.this$0.m_labelTermsAndConditions) {
                        this.this$1.this$0.showTermsAndConditions(this.this$1.this$0.getSelectedAccount());
                    }
                    this.this$1.m_bButtonClicked = false;
                }
            });
            if (!this.m_bButtonClicked) {
                this.m_bButtonClicked = true;
                thread.start();
            }
        }
    }
    
    private final class FetchTermsRunnable implements IReturnRunnable
    {
        private XMLGenericText m_termsAndConditions;
        private JAPDialog m_parentDialog;
        private JpiSelectionPane m_jpiPane;
        PaymentInstanceDBEntry m_jpi;
        
        public FetchTermsRunnable(final JAPDialog parentDialog, final JpiSelectionPane jpiPane) {
            this.m_parentDialog = parentDialog;
            this.m_jpiPane = jpiPane;
        }
        
        public FetchTermsRunnable(final JAPDialog parentDialog, final PaymentInstanceDBEntry jpi, final XMLGenericText termsAndConditions) {
            this.m_parentDialog = parentDialog;
            this.m_jpi = jpi;
            this.m_termsAndConditions = termsAndConditions;
        }
        
        public void run() {
            BIConnection biConnection = null;
            try {
                if (this.m_termsAndConditions != null) {
                    return;
                }
                PaymentInstanceDBEntry paymentInstanceDBEntry;
                if (this.m_jpiPane != null) {
                    paymentInstanceDBEntry = this.m_jpiPane.getSelectedPaymentInstance();
                }
                else {
                    paymentInstanceDBEntry = this.m_jpi;
                }
                biConnection = new BIConnection(paymentInstanceDBEntry);
                biConnection.connect();
                LogHolder.log(7, LogType.PAY, "Fetching terms and conditions");
                this.m_termsAndConditions = biConnection.getTerms(JAPMessages.getLocale().getLanguage());
                biConnection.disconnect();
            }
            catch (Exception ex) {
                if (biConnection != null) {
                    try {
                        biConnection.disconnect();
                    }
                    catch (Exception ex2) {}
                }
                if (!Thread.currentThread().isInterrupted()) {
                    LogHolder.log(2, LogType.NET, "Error fetching terms and conditions: ", ex);
                    AccountSettingsPanel.this.showPIerror(this.m_parentDialog.getContentPane(), ex);
                    Thread.currentThread().interrupt();
                }
            }
        }
        
        public Object getValue() {
            return this.m_termsAndConditions;
        }
    }
    
    private static class MyFileFilter extends FileFilter
    {
        public static final String ACCOUNT_EXTENSION = ".acc";
        private final String ACCOUNT_DESCRIPTION = "JAP Accountfile (*.acc)";
        private int filterType;
        
        public int getFilterType() {
            return this.filterType;
        }
        
        public boolean accept(final File file) {
            return file.isDirectory() || file.getName().endsWith(".acc");
        }
        
        public String getDescription() {
            return "JAP Accountfile (*.acc)";
        }
    }
    
    class CustomRenderer extends DefaultListCellRenderer
    {
        private static final long serialVersionUID = 1L;
        
        public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
            JLabel label;
            if (super.getListCellRendererComponent(list, o, n, b, b2) instanceof JComponent && o != null && o instanceof PayAccount) {
                if (((PayAccount)o).getPrivateKey() == null) {
                    label = new JLabel(String.valueOf(((PayAccount)o).getAccountNumber()), GUIUtils.loadImageIcon(AccountSettingsPanel.IMG_COINS_DISABLED, true), 2);
                }
                else {
                    label = new JLabel(String.valueOf(((PayAccount)o).getAccountNumber()), GUIUtils.loadImageIcon("coins-full.gif", true), 2);
                }
                if (b) {
                    label.setOpaque(true);
                    label.setBackground(Color.lightGray);
                }
                final Font font = label.getFont();
                if (((PayAccount)o).equals(PayAccountsFile.getInstance().getActiveAccount())) {
                    label.setFont(new Font(font.getName(), 1, font.getSize()));
                }
                else {
                    label.setFont(new Font(font.getName(), 0, font.getSize()));
                }
            }
            else if (o != null) {
                label = new JLabel(o.toString());
            }
            else {
                label = new JLabel();
            }
            return label;
        }
    }
    
    private static final class FixedReturnAccountRunnable implements IReturnAccountRunnable
    {
        private PayAccount m_account;
        
        public FixedReturnAccountRunnable(final PayAccount account) {
            this.m_account = account;
        }
        
        public Object getValue() {
            return this.m_account;
        }
        
        public PayAccount getAccount() {
            return this.m_account;
        }
        
        public void run() {
        }
    }
    
    public class AccountCreationPane extends WorkerContentPane
    {
        public AccountCreationPane(final JAPDialog japDialog, final String s, final WorkerContentPane workerContentPane, final Runnable runnable) {
            super(japDialog, s, workerContentPane, runnable);
        }
        
        public boolean isReady() {
            return AccountSettingsPanel.this.m_bReady;
        }
        
        public boolean isSkippedAsPreviousContentPane() {
            return false;
        }
    }
}
