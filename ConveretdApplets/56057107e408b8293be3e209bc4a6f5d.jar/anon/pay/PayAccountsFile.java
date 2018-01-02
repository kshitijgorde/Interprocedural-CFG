// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay;

import anon.util.captcha.IImageEncodedCaptcha;
import anon.util.captcha.ICaptchaSender;
import anon.infoservice.Database;
import anon.pay.xml.XMLErrorMessage;
import anon.infoservice.MixCascade;
import anon.pay.xml.XMLAccountCertificate;
import anon.pay.xml.XMLJapPublicKey;
import anon.pay.xml.XMLGenericText;
import java.sql.Timestamp;
import org.w3c.dom.Document;
import java.util.Enumeration;
import logging.LogHolder;
import logging.LogType;
import anon.util.XMLParseException;
import anon.pay.xml.XMLBalance;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.util.IMiscPasswordReader;
import org.w3c.dom.Element;
import anon.crypto.AsymmetricCryptoKeyPair;
import anon.crypto.DSAKeyPool;
import java.util.Vector;
import anon.util.IXMLEncodable;
import java.util.Observable;

public class PayAccountsFile extends Observable implements IXMLEncodable, IBIConnectionListener, IMessageListener
{
    public static final String XML_ELEMENT_NAME = "PayAccounts";
    public static final Integer CHANGED_AUTO_UPDATE;
    private static final String XML_ATTR_IGNORE_AI_ERRORS = "ignoreAIErrorMessages";
    private static final String XML_ATTR_ENABLE_BALANCE_AUTO_UPDATE = "autoUpdateBalance";
    private static boolean m_bIsInitialized;
    private boolean m_bIgnoreAIAccountErrorMessages;
    private boolean m_bEnableBalanceAutoUpdate;
    private Vector m_Accounts;
    private PayAccount m_ActiveAccount;
    private static PayAccountsFile ms_AccountsFile;
    private Vector m_paymentListeners;
    private Vector m_messageListeners;
    private MyAccountListener m_MyAccountListener;
    private DSAKeyPool m_keyPool;
    private static int ms_keyPoolSize;
    static /* synthetic */ Class class$anon$pay$PaymentInstanceDBEntry;
    
    private PayAccountsFile() {
        this.m_bIgnoreAIAccountErrorMessages = false;
        this.m_bEnableBalanceAutoUpdate = true;
        this.m_Accounts = new Vector();
        this.m_ActiveAccount = null;
        this.m_paymentListeners = new Vector();
        this.m_messageListeners = new Vector();
        this.m_MyAccountListener = new MyAccountListener();
        (this.m_keyPool = new DSAKeyPool(PayAccountsFile.ms_keyPoolSize)).start();
    }
    
    public static PayAccountsFile getInstance() {
        if (PayAccountsFile.ms_AccountsFile == null) {
            PayAccountsFile.ms_AccountsFile = new PayAccountsFile();
        }
        return PayAccountsFile.ms_AccountsFile;
    }
    
    public AsymmetricCryptoKeyPair createAccountKeyPair() {
        return this.m_keyPool.popKeyPair();
    }
    
    public void setIgnoreAIAccountError(final boolean bIgnoreAIAccountErrorMessages) {
        this.m_bIgnoreAIAccountErrorMessages = bIgnoreAIAccountErrorMessages;
    }
    
    public boolean isBalanceAutoUpdateEnabled() {
        return this.m_bEnableBalanceAutoUpdate;
    }
    
    public void setBalanceAutoUpdateEnabled(final boolean bEnableBalanceAutoUpdate) {
        synchronized (this) {
            if (this.m_bEnableBalanceAutoUpdate != bEnableBalanceAutoUpdate) {
                this.m_bEnableBalanceAutoUpdate = bEnableBalanceAutoUpdate;
                this.setChanged();
            }
            this.notifyObservers(PayAccountsFile.CHANGED_AUTO_UPDATE);
        }
    }
    
    public boolean isAIAccountErrorIgnored() {
        return this.m_bIgnoreAIAccountErrorMessages;
    }
    
    public boolean importAccounts(final Element element, final IMiscPasswordReader miscPasswordReader) throws XMLParseException, Exception {
        boolean b = false;
        XMLUtil.assertNodeName(element, "PayAccounts");
        Node node = XMLUtil.getFirstChildByName(XMLUtil.getFirstChildByName(element, "Accounts"), "Account");
        while (node != null) {
            final PayAccount payAccount = new PayAccount((Element)node, miscPasswordReader);
            getInstance().addAccount(payAccount);
            b = true;
            if (payAccount.getAccountInfo() != null) {
                final XMLBalance balance = payAccount.getAccountInfo().getBalance();
                if (balance != null) {
                    final PayMessage message = balance.getMessage();
                    if (message != null && !message.getShortMessage().equals("")) {
                        getInstance().messageReceived(message);
                    }
                }
            }
            while ((node = node.getNextSibling()) != null && !(node instanceof Element)) {}
        }
        return b;
    }
    
    public static synchronized boolean init(final Element element, final IMiscPasswordReader miscPasswordReader, final boolean b, final int ms_keyPoolSize) {
        if (PayAccountsFile.m_bIsInitialized) {
            return false;
        }
        if (ms_keyPoolSize >= 0) {
            PayAccountsFile.ms_keyPoolSize = ms_keyPoolSize;
        }
        if (element != null && element.getNodeName().equals("PayAccounts")) {
            if (b) {
                getInstance().m_bIgnoreAIAccountErrorMessages = false;
            }
            else {
                getInstance().m_bIgnoreAIAccountErrorMessages = XMLUtil.parseAttribute(element, "ignoreAIErrorMessages", false);
            }
            getInstance().m_bEnableBalanceAutoUpdate = XMLUtil.parseAttribute(element, "autoUpdateBalance", true);
            final long long1 = Long.parseLong(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "ActiveAccountNumber"), "0"));
            try {
                getInstance().importAccounts(element, miscPasswordReader);
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.PAY, ex);
                return false;
            }
            if (long1 > 0L) {
                final Enumeration<PayAccount> elements = getInstance().m_Accounts.elements();
                while (elements.hasMoreElements()) {
                    final PayAccount activeAccount = elements.nextElement();
                    if (activeAccount.getAccountNumber() == long1) {
                        try {
                            getInstance().setActiveAccount(activeAccount);
                        }
                        catch (Exception ex2) {}
                        break;
                    }
                }
            }
        }
        getInstance();
        return PayAccountsFile.m_bIsInitialized = true;
    }
    
    public Element toXmlElement(final Document document) {
        return this.toXmlElement(document, null);
    }
    
    public Element toXmlElement(final Document document, final String s) {
        try {
            final Element element = document.createElement("PayAccounts");
            element.setAttribute("version", "1.0");
            XMLUtil.setAttribute(element, "ignoreAIErrorMessages", this.m_bIgnoreAIAccountErrorMessages);
            XMLUtil.setAttribute(element, "autoUpdateBalance", this.m_bEnableBalanceAutoUpdate);
            final Element element2 = document.createElement("ActiveAccountNumber");
            XMLUtil.setValue(element2, Long.toString(this.getActiveAccountNumber()));
            element.appendChild(element2);
            final Element element3 = document.createElement("Accounts");
            element.appendChild(element3);
            synchronized (this) {
                for (int i = 0; i < this.m_Accounts.size(); ++i) {
                    element3.appendChild(((PayAccount)this.m_Accounts.elementAt(i)).toXmlElement(document, s));
                }
            }
            return element;
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.PAY, "Exception while creating PayAccountsFile XML: " + ex);
            return null;
        }
    }
    
    public boolean hasActiveAccount() {
        return this.m_ActiveAccount != null;
    }
    
    public PayAccount getActiveAccount() {
        return this.m_ActiveAccount;
    }
    
    public void setActiveAccount(final PayAccount payAccount) {
        PayAccount account = null;
        if (payAccount != null) {
            account = this.getAccount(payAccount.getAccountNumber(), payAccount.getPIID());
        }
        if (account != null && account.getPrivateKey() != null) {
            this.m_ActiveAccount = account;
            synchronized (this.m_paymentListeners) {
                final Enumeration<IPaymentListener> elements = this.m_paymentListeners.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().accountActivated(this.m_ActiveAccount);
                }
                return;
            }
        }
        if (account == null) {
            this.m_ActiveAccount = null;
            synchronized (this.m_paymentListeners) {
                final Enumeration<IPaymentListener> elements2 = this.m_paymentListeners.elements();
                while (elements2.hasMoreElements()) {
                    elements2.nextElement().accountActivated(this.m_ActiveAccount);
                }
            }
        }
    }
    
    public long getActiveAccountNumber() {
        final PayAccount activeAccount = this.m_ActiveAccount;
        if (activeAccount != null) {
            return activeAccount.getAccountNumber();
        }
        return -1L;
    }
    
    public synchronized PayAccount getAccount(final long n, final String s) {
        PayAccount payAccount = null;
        final Enumeration<PayAccount> elements = this.m_Accounts.elements();
        while (elements.hasMoreElements()) {
            payAccount = elements.nextElement();
            if (payAccount.getAccountNumber() == n) {
                if (s == payAccount.getPIID()) {
                    break;
                }
                if (s != null && payAccount.getPIID() != null && s.equals(payAccount.getPIID())) {
                    break;
                }
            }
            payAccount = null;
        }
        return payAccount;
    }
    
    public void deleteAccount(final PayAccount payAccount) {
        if (payAccount == null) {
            return;
        }
        PayAccount account = null;
        synchronized (this) {
            account = this.getAccount(payAccount.getAccountNumber(), payAccount.getPIID());
            if (account != null) {
                for (int i = 0; i < this.m_Accounts.size(); ++i) {
                    account = (PayAccount)this.m_Accounts.elementAt(i);
                    if (account.getAccountNumber() == payAccount.getAccountNumber()) {
                        this.m_Accounts.removeElementAt(i);
                        break;
                    }
                }
                if (account.getBalance() != null) {
                    final PayMessage message = account.getBalance().getMessage();
                    if (message != null && !message.getShortMessage().equals("")) {
                        this.fireMessageRemoved(message);
                    }
                }
                if (this.getActiveAccount() == account) {
                    if (this.m_Accounts.size() > 0) {
                        this.setActiveAccount(this.m_Accounts.elementAt(0));
                    }
                    else {
                        this.setActiveAccount(null);
                    }
                }
            }
        }
        if (account != null) {
            synchronized (this.m_paymentListeners) {
                final Enumeration<IPaymentListener> elements = (Enumeration<IPaymentListener>)this.m_paymentListeners.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().accountRemoved(account);
                }
            }
        }
    }
    
    public Enumeration getAccounts() {
        return ((Vector)this.m_Accounts.clone()).elements();
    }
    
    public static void fireKnownMessages() {
        final Enumeration accounts = getInstance().getAccounts();
        while (accounts.hasMoreElements()) {
            final PayAccount payAccount = accounts.nextElement();
            if (payAccount.getAccountInfo() != null) {
                final PayMessage message = payAccount.getAccountInfo().getBalance().getMessage();
                if (message == null || message.getShortMessage().equals("")) {
                    continue;
                }
                PayAccountsFile.ms_AccountsFile.fireMessageReceived(message);
            }
        }
    }
    
    public synchronized PayAccount getAlternativeChargedAccount(final String s) {
        return this.getChargedAccount(s, this.getActiveAccount());
    }
    
    public synchronized PayAccount getChargedAccount(final String s) {
        return this.getChargedAccount(s, null);
    }
    
    public synchronized PayAccount getChargedAccount(final String s, PayAccount payAccount) {
        if (s == null) {
            return null;
        }
        final Vector accounts = getInstance().getAccounts(s);
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        PayAccount payAccount2 = null;
        if (payAccount != null && !accounts.contains(payAccount)) {
            payAccount = null;
        }
        if (accounts.size() > 0) {
            for (int i = 0; i < accounts.size(); ++i) {
                payAccount2 = accounts.elementAt(i);
                if (payAccount2.isCharged(timestamp)) {
                    if (payAccount == null) {
                        break;
                    }
                    if (payAccount != payAccount2) {
                        break;
                    }
                }
                payAccount2 = null;
            }
        }
        return payAccount2;
    }
    
    public synchronized Vector getAccounts(final String s) {
        final Vector<PayAccount> vector = new Vector<PayAccount>();
        final Enumeration<PayAccount> elements = this.m_Accounts.elements();
        if (s != null && s.trim().length() > 0) {
            while (elements.hasMoreElements()) {
                final PayAccount payAccount = elements.nextElement();
                final PaymentInstanceDBEntry bi = payAccount.getBI();
                if (bi == null) {
                    LogHolder.log(3, LogType.PAY, "Payment instance for account nr. " + payAccount.getAccountNumber() + " not found!");
                }
                else {
                    if (!bi.getId().equals(s)) {
                        continue;
                    }
                    vector.addElement(payAccount);
                }
            }
        }
        return vector;
    }
    
    public synchronized void addAccount(final PayAccount activeAccount) throws AccountAlreadyExistingException {
        int n = 0;
        final Enumeration<PayAccount> elements = (Enumeration<PayAccount>)this.m_Accounts.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement().getAccountNumber() == activeAccount.getAccountNumber()) {
                throw new AccountAlreadyExistingException();
            }
        }
        activeAccount.addAccountListener(this.m_MyAccountListener);
        activeAccount.addMessageListener(this);
        this.m_Accounts.addElement(activeAccount);
        if (this.m_ActiveAccount == null && activeAccount.getPrivateKey() != null) {
            this.m_ActiveAccount = activeAccount;
            n = 1;
        }
        synchronized (this.m_paymentListeners) {
            final Enumeration<IPaymentListener> elements2 = (Enumeration<IPaymentListener>)this.m_paymentListeners.elements();
            while (elements2.hasMoreElements()) {
                final IPaymentListener paymentListener = elements2.nextElement();
                paymentListener.accountAdded(activeAccount);
                if (n == 1) {
                    paymentListener.accountActivated(activeAccount);
                }
            }
        }
    }
    
    public int getNumAccounts() {
        return this.m_Accounts.size();
    }
    
    public synchronized PayAccount getAccountAt(final int n) {
        return this.m_Accounts.elementAt(n);
    }
    
    public boolean isInitialized() {
        return PayAccountsFile.m_bIsInitialized;
    }
    
    public void addPaymentListener(final IPaymentListener paymentListener) {
        synchronized (this.m_paymentListeners) {
            if (paymentListener != null) {
                this.m_paymentListeners.addElement(paymentListener);
            }
        }
    }
    
    public void removePaymentListener(final IPaymentListener paymentListener) {
        synchronized (this.m_paymentListeners) {
            if (this.m_paymentListeners.contains(paymentListener)) {
                this.m_paymentListeners.removeElement(paymentListener);
            }
        }
    }
    
    public void addMessageListener(final IMessageListener messageListener) {
        synchronized (this.m_messageListeners) {
            if (messageListener != null) {
                this.m_messageListeners.addElement(messageListener);
            }
        }
    }
    
    private void fireMessageReceived(final PayMessage payMessage) {
        final Enumeration<IMessageListener> elements = ((Vector)this.m_messageListeners.clone()).elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().messageReceived(payMessage);
        }
    }
    
    private void fireMessageRemoved(final PayMessage payMessage) {
        final Enumeration<IMessageListener> elements = ((Vector)this.m_messageListeners.clone()).elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().messageRemoved(payMessage);
        }
    }
    
    public PayAccount createAccount(final PaymentInstanceDBEntry paymentInstanceDBEntry, final XMLGenericText xmlGenericText) throws Exception {
        final AsymmetricCryptoKeyPair accountKeyPair = this.createAccountKeyPair();
        if (accountKeyPair == null) {
            return null;
        }
        return this.createAccount(paymentInstanceDBEntry, accountKeyPair, xmlGenericText);
    }
    
    public PayAccount createAccount(final PaymentInstanceDBEntry paymentInstanceDBEntry, final AsymmetricCryptoKeyPair asymmetricCryptoKeyPair, final XMLGenericText xmlGenericText) throws Exception {
        final XMLJapPublicKey xmlJapPublicKey = new XMLJapPublicKey(asymmetricCryptoKeyPair.getPublic());
        LogHolder.log(7, LogType.PAY, "Attempting to create account at PI " + paymentInstanceDBEntry.getName());
        final BIConnection biConnection = new BIConnection(paymentInstanceDBEntry);
        biConnection.addConnectionListener(this);
        biConnection.connect();
        final XMLAccountCertificate registerNewAccount = biConnection.registerNewAccount(xmlJapPublicKey, asymmetricCryptoKeyPair.getPrivate());
        biConnection.disconnect();
        final PayAccount payAccount = new PayAccount(registerNewAccount, asymmetricCryptoKeyPair.getPrivate(), paymentInstanceDBEntry, xmlGenericText);
        this.addAccount(payAccount);
        return payAccount;
    }
    
    public int signalAccountRequest(final MixCascade mixCascade) {
        int accountCertRequested = 0;
        synchronized (this.m_paymentListeners) {
            final Enumeration<IPaymentListener> elements = (Enumeration<IPaymentListener>)this.m_paymentListeners.elements();
            while (elements.hasMoreElements() && (accountCertRequested = elements.nextElement().accountCertRequested(mixCascade)) == 0) {}
        }
        return accountCertRequested;
    }
    
    public void signalAccountError(final XMLErrorMessage xmlErrorMessage) {
        synchronized (this.m_paymentListeners) {
            final Enumeration<IPaymentListener> elements = this.m_paymentListeners.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().accountError(xmlErrorMessage, this.m_bIgnoreAIAccountErrorMessages);
            }
        }
    }
    
    public Vector getPaymentInstances() {
        return Database.getInstance((PayAccountsFile.class$anon$pay$PaymentInstanceDBEntry == null) ? (PayAccountsFile.class$anon$pay$PaymentInstanceDBEntry = class$("anon.pay.PaymentInstanceDBEntry")) : PayAccountsFile.class$anon$pay$PaymentInstanceDBEntry).getEntryList();
    }
    
    public PaymentInstanceDBEntry getBI(final String s) {
        return (PaymentInstanceDBEntry)Database.getInstance((PayAccountsFile.class$anon$pay$PaymentInstanceDBEntry == null) ? (PayAccountsFile.class$anon$pay$PaymentInstanceDBEntry = class$("anon.pay.PaymentInstanceDBEntry")) : PayAccountsFile.class$anon$pay$PaymentInstanceDBEntry).getEntryById(s);
    }
    
    public void gotCaptcha(final ICaptchaSender captchaSender, final IImageEncodedCaptcha imageEncodedCaptcha) {
        synchronized (this.m_paymentListeners) {
            final Enumeration<IPaymentListener> elements = this.m_paymentListeners.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().gotCaptcha(captchaSender, imageEncodedCaptcha);
            }
        }
    }
    
    public void messageReceived(final PayMessage payMessage) {
        this.fireMessageReceived(payMessage);
    }
    
    public void messageRemoved(final PayMessage payMessage) {
        this.fireMessageRemoved(payMessage);
    }
    
    protected void finalize() {
        if (this.m_keyPool != null) {
            this.m_keyPool.stop();
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
        CHANGED_AUTO_UPDATE = new Integer(0);
        PayAccountsFile.m_bIsInitialized = false;
        PayAccountsFile.ms_AccountsFile = null;
        PayAccountsFile.ms_keyPoolSize = 1;
    }
    
    public static class AccountAlreadyExistingException extends Exception
    {
    }
    
    private class MyAccountListener implements IAccountListener
    {
        public void accountChanged(final PayAccount payAccount) {
            final Enumeration<IPaymentListener> elements = ((Vector)PayAccountsFile.this.m_paymentListeners.clone()).elements();
            if (payAccount != null) {
                while (elements.hasMoreElements()) {
                    elements.nextElement().creditChanged(payAccount);
                }
            }
        }
    }
}
