// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay;

import anon.crypto.MyDSAPrivateKey;
import anon.util.XMLParseException;
import anon.crypto.MyRSAPrivateKey;
import java.util.StringTokenizer;
import anon.pay.xml.XMLGenericStrings;
import anon.client.PacketCounter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import anon.crypto.IMyPublicKey;
import java.util.Date;
import anon.pay.xml.XMLBalance;
import anon.pay.xml.XMLEasyCC;
import java.util.Hashtable;
import java.util.Enumeration;
import logging.LogHolder;
import logging.LogType;
import anon.crypto.XMLEncryption;
import anon.util.ZLibTools;
import anon.util.Base64;
import anon.pay.xml.XMLTransCert;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.util.IMiscPasswordReader;
import org.w3c.dom.Element;
import java.sql.Timestamp;
import java.util.Calendar;
import org.w3c.dom.Document;
import anon.crypto.IMyPrivateKey;
import anon.pay.xml.XMLGenericText;
import anon.pay.xml.XMLAccountInfo;
import anon.pay.xml.XMLAccountCertificate;
import java.util.Vector;
import anon.util.IXMLEncodable;

public class PayAccount implements IXMLEncodable
{
    public static final String XML_ELEMENT_NAME = "Account";
    private static final String XML_ATTR_ACTIVE = "active";
    private static final String XML_BACKUP_DONE = "backupDone";
    public static final long ACCOUNT_MIN_UPDATE_INTERVAL_MS = 60000L;
    public static final long ACCOUNT_MAX_UPDATE_INTERVAL_MS = 300000L;
    private final Object SYNC_BYTES;
    private static final long NEW_ACCOUNT_EXPIRATION_TIME = 604800000L;
    private static final String VERSION = "1.1";
    private Vector m_transCerts;
    private XMLAccountCertificate m_accountCertificate;
    private XMLAccountInfo m_accountInfo;
    private XMLGenericText m_terms;
    private IMyPrivateKey m_privateKey;
    private Document m_encryptedPrivateKey;
    private long m_currentBytes;
    private Vector m_accountListeners;
    private Vector m_messageListeners;
    private long m_lBackupDone;
    private long m_lastAccountInfoUpdate;
    private boolean m_bAccountInfoUpdateRunning;
    private Calendar m_termsDate;
    private static final long TRANSACTION_EXPIRATION = 1209600000L;
    public static final int MAX_KBYTES_COUNTING_AS_EMPTY = 5000;
    private long m_mySpent;
    private PaymentInstanceDBEntry m_theBI;
    private String m_strBiID;
    
    public boolean isTransactionExpired() {
        return new Timestamp(System.currentTimeMillis()).getTime() - this.getCreationTime().getTime() > 1209600000L;
    }
    
    public PayAccount(final Element element, final IMiscPasswordReader miscPasswordReader) throws Exception {
        this.SYNC_BYTES = new Object();
        this.m_accountListeners = new Vector();
        this.m_messageListeners = new Vector();
        this.m_lBackupDone = 0L;
        this.m_lastAccountInfoUpdate = 0L;
        this.m_bAccountInfoUpdateRunning = false;
        this.setValues(element, miscPasswordReader);
    }
    
    public PayAccount(final XMLAccountCertificate accountCertificate, final IMyPrivateKey privateKey, final PaymentInstanceDBEntry theBI, final XMLGenericText terms) {
        this.SYNC_BYTES = new Object();
        this.m_accountListeners = new Vector();
        this.m_messageListeners = new Vector();
        this.m_lBackupDone = 0L;
        this.m_lastAccountInfoUpdate = 0L;
        this.m_bAccountInfoUpdateRunning = false;
        this.m_accountCertificate = accountCertificate;
        this.m_privateKey = privateKey;
        this.m_transCerts = new Vector();
        this.m_theBI = theBI;
        this.m_strBiID = theBI.getId();
        this.setTerms(terms);
    }
    
    private void setValues(final Element element, final IMiscPasswordReader miscPasswordReader) throws Exception {
        XMLUtil.assertNodeName(element, "Account");
        final boolean attribute = XMLUtil.parseAttribute(element, "active", true);
        if (XMLUtil.parseAttribute(element, "backupDone", false)) {
            this.m_lBackupDone = System.currentTimeMillis();
        }
        else {
            this.m_lBackupDone = XMLUtil.parseAttribute(element, "backupDone", 0L);
        }
        this.m_transCerts = new Vector();
        for (Element element2 = (Element)XMLUtil.getFirstChildByName(XMLUtil.getFirstChildByName(element, "TransferCertificates"), "TransferCertificate"); element2 != null; element2 = (Element)XMLUtil.getNextSiblingByName(element2, "TransferCertificate")) {
            this.m_transCerts.addElement(new XMLTransCert(element2));
        }
        final Element element3 = (Element)XMLUtil.getFirstChildByName(element, "AccountCertificate");
        this.m_accountCertificate = new XMLAccountCertificate(element3);
        final Element element4 = (Element)XMLUtil.getFirstChildByName(element, "AccountInfo");
        if (element4 != null) {
            this.m_accountInfo = new XMLAccountInfo(element4);
        }
        final Element element5 = (Element)XMLUtil.getFirstChildByName(element, "GenericText");
        if (element5 != null && XMLUtil.getStorageMode() != 2) {
            XMLGenericText terms = new XMLGenericText(element5);
            try {
                terms = new XMLGenericText(new String(ZLibTools.decompress(Base64.decode(terms.getText()))));
            }
            catch (Exception ex) {}
            this.setTerms(terms);
        }
        this.m_strBiID = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element3, "BiID"), "-1");
        this.m_theBI = null;
        this.decryptPrivateKey(element, miscPasswordReader, !attribute);
    }
    
    public Element toXmlElement(final Document document) {
        return this.toXmlElement(document, null);
    }
    
    public Element toXmlElement(final Document document, final String s) {
        try {
            if (s != null && s.trim().equals("")) {
                return this.toXmlElement(document, null);
            }
            final Element element = document.createElement("Account");
            element.setAttribute("version", "1.1");
            element.appendChild(this.m_accountCertificate.toXmlElement(document));
            XMLUtil.setAttribute(element, "backupDone", this.m_lBackupDone);
            if (this.m_encryptedPrivateKey != null) {
                XMLUtil.setAttribute(element, "active", false);
                element.appendChild(XMLUtil.importNode(document, this.m_encryptedPrivateKey.getDocumentElement(), true));
            }
            else {
                XMLUtil.setAttribute(element, "active", true);
                final Element xmlElement = this.m_privateKey.toXmlElement(document);
                element.appendChild(xmlElement);
                if (s != null) {
                    try {
                        XMLEncryption.encryptElement(xmlElement, s);
                    }
                    catch (Exception ex) {
                        LogHolder.log(2, LogType.PAY, "Could not encrypt account key: " + ex);
                    }
                }
            }
            final Element element2 = document.createElement("TransferCertificates");
            element.appendChild(element2);
            if (this.m_transCerts != null) {
                final Enumeration<XMLTransCert> elements = this.m_transCerts.elements();
                while (elements.hasMoreElements()) {
                    element2.appendChild(elements.nextElement().toXmlElement(document));
                }
            }
            if (this.m_accountInfo != null) {
                element.appendChild(this.m_accountInfo.toXmlElement(document));
            }
            if (this.m_terms != null) {
                element.appendChild(new XMLGenericText(Base64.encode(ZLibTools.compress(this.m_terms.getText().getBytes()), true)).toXmlElement(document));
            }
            return element;
        }
        catch (Exception ex2) {
            LogHolder.log(2, LogType.PAY, "Exception while creating PayAccount XML: " + ex2);
            return null;
        }
    }
    
    public void addTransCert(final XMLTransCert xmlTransCert) throws Exception {
        this.m_transCerts.addElement(xmlTransCert);
    }
    
    private void setAccountInfo(final XMLAccountInfo accountInfo) throws Exception {
        int n = 0;
        if (this.m_accountInfo == null) {
            this.m_accountInfo = accountInfo;
            n = 1;
        }
        else {
            synchronized (this.m_accountInfo) {
                final XMLBalance balance = accountInfo.getBalance();
                final XMLBalance balance2 = this.m_accountInfo.getBalance();
                final PayMessage message = balance2.getMessage();
                final PayMessage message2 = balance.getMessage();
                if (balance.getTimestamp().after(balance2.getTimestamp())) {
                    this.m_accountInfo.setBalance(balance);
                    n = 1;
                    if (message2 != null && !message2.getShortMessage().equals("")) {
                        if (message == null) {
                            this.fireMessageReceived(message2);
                        }
                        else if (!message2.equals(message)) {
                            this.fireMessageRemoved(message);
                            this.fireMessageReceived(message2);
                        }
                    }
                    else if (message != null && !message.getShortMessage().equals("")) {
                        this.fireMessageRemoved(message);
                    }
                }
                balance2.setMessage(message2);
                final Enumeration[] array = { this.m_accountInfo.getCCs(), accountInfo.getCCs() };
                final Hashtable<String, String> hashtable = new Hashtable<String, String>();
                for (int i = 0; i < array.length; ++i) {
                    while (array[i].hasMoreElements()) {
                        final String concatenatedPriceCertHashes = array[i].nextElement().getConcatenatedPriceCertHashes();
                        hashtable.put(concatenatedPriceCertHashes, concatenatedPriceCertHashes);
                    }
                }
                final Enumeration<String> keys = hashtable.keys();
                while (keys.hasMoreElements()) {
                    final String s = keys.nextElement();
                    final XMLEasyCC cc = this.m_accountInfo.getCC(s);
                    final XMLEasyCC cc2 = accountInfo.getCC(s);
                    if (cc == null && cc2 == null) {
                        throw new NullPointerException("no CC available for " + s + " This must NEVER happen!");
                    }
                    if (cc2 == null || (cc != null && cc2.getTransferredBytes() <= cc.getTransferredBytes())) {
                        continue;
                    }
                    if (!cc2.verify(this.m_accountCertificate.getPublicKey())) {
                        throw new Exception("The BI is trying to betray you with faked CostConfirmations");
                    }
                    this.addCostConfirmation(cc2);
                    n = 0;
                }
            }
        }
        if (n == 1) {
            this.fireChangeEvent();
        }
    }
    
    public boolean equals(final Object o) {
        if (o == null || !(o instanceof PayAccount)) {
            return false;
        }
        final PayAccount payAccount = (PayAccount)o;
        return payAccount.getAccountNumber() == this.getAccountNumber() && (payAccount.m_strBiID == this.m_strBiID || (payAccount.m_strBiID != null && this.m_strBiID != null && payAccount.m_strBiID.equals(this.m_strBiID)));
    }
    
    public int hashCode() {
        return (this.getAccountNumber() + this.m_strBiID).hashCode();
    }
    
    public long getAccountNumber() {
        return this.m_accountCertificate.getAccountNumber();
    }
    
    public boolean hasExpired() {
        return this.hasExpired(new Timestamp(System.currentTimeMillis()));
    }
    
    public boolean hasExpired(final Timestamp timestamp) {
        final XMLBalance balance = this.getBalance();
        return (balance != null && (this.getCurrentCredit() > 0L || this.getCurrentSpent() > 0L) && balance.getFlatEnddate() != null && balance.getFlatEnddate().before(timestamp)) || this.getCreationTime().before(new Date(System.currentTimeMillis() - 604800000L));
    }
    
    public boolean isCharged(final Timestamp timestamp) {
        final XMLBalance balance = this.getBalance();
        return balance != null && this.getCurrentCredit() > 0L && balance.getFlatEnddate() != null && balance.getFlatEnddate().after(timestamp);
    }
    
    public boolean isBackupDone() {
        return this.m_lBackupDone > 0L;
    }
    
    public long getBackupTime() {
        return this.m_lBackupDone;
    }
    
    public void setBackupDone(final long lBackupDone) {
        this.m_lBackupDone = lBackupDone;
    }
    
    public boolean hasAccountInfo() {
        return this.m_accountInfo != null;
    }
    
    public XMLAccountCertificate getAccountCertificate() {
        return this.m_accountCertificate;
    }
    
    public Timestamp getCreationTime() {
        return this.m_accountCertificate.getCreationTime();
    }
    
    public Timestamp getBalanceValidTime() {
        if (this.m_accountInfo != null) {
            return this.m_accountInfo.getBalance().getValidTime();
        }
        return this.m_accountCertificate.getCreationTime();
    }
    
    public IMyPrivateKey getPrivateKey() {
        return this.m_privateKey;
    }
    
    public IMyPublicKey getPublicKey() {
        return this.m_accountCertificate.getPublicKey();
    }
    
    public long getSpent() {
        if (this.m_accountInfo != null) {
            return this.m_accountInfo.getBalance().getSpent();
        }
        return 0L;
    }
    
    public long getDeposit() {
        if (this.m_accountInfo != null) {
            return this.m_accountInfo.getBalance().getDeposit();
        }
        return 0L;
    }
    
    public long getCurrentCreditCalculated() {
        if (this.m_accountInfo == null) {
            return Long.MIN_VALUE;
        }
        long currentCreditCalculatedAlsoNegative = this.getCurrentCreditCalculatedAlsoNegative();
        if (currentCreditCalculatedAlsoNegative < 0L) {
            currentCreditCalculatedAlsoNegative = 0L;
        }
        return currentCreditCalculatedAlsoNegative;
    }
    
    public long getCurrentCreditCalculatedAlsoNegative() {
        if (this.m_accountInfo == null) {
            return Long.MIN_VALUE;
        }
        final long n;
        synchronized (this.m_accountInfo) {
            n = this.m_accountInfo.getBalance().getSpent() + this.m_accountInfo.getBalance().getVolumeBytesLeft() - this.m_accountInfo.getAllCCsTransferredBytes();
        }
        return n;
    }
    
    public long getCurrentCreditFromBalance() {
        return this.getBalance().getVolumeBytesLeft();
    }
    
    public long getCurrentCredit() {
        if (this.m_accountInfo != null) {
            synchronized (this.m_accountInfo) {
                final long currentCreditCalculated = this.getCurrentCreditCalculated();
                if (currentCreditCalculated < 0L || currentCreditCalculated > this.getBalance().getVolumeBytesLeft()) {
                    return this.getBalance().getVolumeBytesLeft();
                }
                return currentCreditCalculated;
            }
        }
        return 0L;
    }
    
    public long getCurrentSpent() {
        if (this.m_accountInfo != null) {
            synchronized (this.m_accountInfo) {
                return this.m_accountInfo.getBalance().getSpent() + this.m_accountInfo.getBalance().getVolumeBytesLeft() - this.getCurrentCredit();
            }
        }
        return 0L;
    }
    
    public XMLAccountInfo getAccountInfo() {
        return this.m_accountInfo;
    }
    
    public XMLGenericText getTerms() {
        return this.m_terms;
    }
    
    public void setTerms(final XMLGenericText terms) {
        this.m_termsDate = null;
        if (terms == null) {
            this.m_terms = null;
        }
        String text = null;
        if (terms != null) {
            text = terms.getText();
        }
        if (text == null || text.trim().equals("")) {
            this.m_terms = null;
        }
        else {
            this.m_terms = terms;
            final int index = text.indexOf("<title>");
            final int index2 = text.indexOf("</title>");
            if (index >= 0 && index2 >= 0) {
                try {
                    final String substring = text.substring(index + "<title>".length(), index2);
                    final Calendar instance = Calendar.getInstance();
                    instance.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(substring));
                    this.m_termsDate = instance;
                }
                catch (ParseException ex) {
                    LogHolder.log(4, LogType.PAY, ex);
                    this.m_terms = null;
                }
            }
            else {
                LogHolder.log(4, LogType.PAY, "No valid title tag was found!");
                this.m_terms = null;
            }
        }
    }
    
    public Calendar getTermsDate() {
        return this.m_termsDate;
    }
    
    public Vector getTransCerts() {
        return this.m_transCerts;
    }
    
    public long updateCurrentBytes(final PacketCounter packetCounter) throws Exception {
        if (PayAccountsFile.getInstance().getActiveAccount() != this) {
            throw new Exception("Error: Inactive account called to count used bytes!");
        }
        final long andResetBytesForPayment;
        synchronized (this.SYNC_BYTES) {
            andResetBytesForPayment = packetCounter.getAndResetBytesForPayment();
            if (andResetBytesForPayment > 0L) {
                this.m_currentBytes += andResetBytesForPayment;
            }
        }
        if (andResetBytesForPayment > 0L) {
            this.fireChangeEvent();
        }
        else if (andResetBytesForPayment < 0L) {
            throw new Exception("Negative payment bytes added! Bytes: " + andResetBytesForPayment);
        }
        return andResetBytesForPayment;
    }
    
    public void resetCurrentBytes() {
        this.m_currentBytes = 0L;
    }
    
    public void updateCurrentBytes(final long n) {
        synchronized (this.SYNC_BYTES) {
            this.m_currentBytes += n;
        }
    }
    
    public long getCurrentBytes() {
        return this.m_currentBytes;
    }
    
    public long addCostConfirmation(final XMLEasyCC xmlEasyCC) throws Exception {
        final long addCC;
        synchronized (this.SYNC_BYTES) {
            if (this.m_accountInfo == null) {
                this.m_accountInfo = new XMLAccountInfo();
            }
            addCC = this.m_accountInfo.addCC(xmlEasyCC);
            if (addCC > 0L) {
                this.m_mySpent += addCC;
            }
        }
        this.fireChangeEvent();
        return addCC;
    }
    
    public void addAccountListener(final IAccountListener accountListener) {
        synchronized (this.m_accountListeners) {
            if (accountListener != null) {
                this.m_accountListeners.addElement(accountListener);
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
    
    public void removeMessageListener(final IMessageListener messageListener) {
        synchronized (this.m_messageListeners) {
            this.m_messageListeners.removeElement(messageListener);
        }
    }
    
    private void fireChangeEvent() {
        final Enumeration<IAccountListener> elements = ((Vector)this.m_accountListeners.clone()).elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().accountChanged(this);
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
    
    public XMLBalance getBalance() {
        if (this.m_accountInfo == null) {
            return null;
        }
        return this.m_accountInfo.getBalance();
    }
    
    public boolean isFlatrateActive() {
        boolean b = false;
        final Timestamp flatEnddate = this.m_accountInfo.getBalance().getFlatEnddate();
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        final long currentCredit = this.getCurrentCredit();
        if (flatEnddate != null && flatEnddate.after(timestamp) && currentCredit > 0L) {
            b = true;
        }
        return b;
    }
    
    public void fetchAccountInfo(final boolean b) throws SecurityException, Exception {
        this.fetchAccountInfo(b, 0);
    }
    
    public void fetchAccountInfo(final boolean b, final int n) throws SecurityException, Exception {
        if (!b && (!PayAccountsFile.getInstance().isBalanceAutoUpdateEnabled() || this.m_bAccountInfoUpdateRunning)) {
            return;
        }
        if (this.getPrivateKey() == null) {
            throw new SecurityException("Account is encrypted and not usable!");
        }
        this.m_theBI = this.getBI();
        if (this.m_theBI == null) {
            return;
        }
        BIConnection biConnection = null;
        this.m_bAccountInfoUpdateRunning = true;
        XMLAccountInfo accountInfo;
        try {
            biConnection = new BIConnection(this.m_theBI);
            if (n > 0) {
                biConnection.connect(n);
            }
            else {
                biConnection.connect();
            }
            biConnection.authenticate(this.m_accountCertificate, this.m_privateKey);
            accountInfo = biConnection.getAccountInfo();
            biConnection.disconnect();
        }
        catch (Exception ex) {
            try {
                biConnection.disconnect();
            }
            catch (Exception ex2) {}
            this.m_bAccountInfoUpdateRunning = false;
            throw ex;
        }
        this.m_bAccountInfoUpdateRunning = false;
        if (accountInfo != null) {
            this.m_lastAccountInfoUpdate = System.currentTimeMillis();
            this.setAccountInfo(accountInfo);
            this.fireChangeEvent();
        }
    }
    
    public boolean isAccountInfoUpdated() {
        return this.m_lastAccountInfoUpdate > 0L;
    }
    
    public boolean shouldUpdateAccountInfo() {
        if (!PayAccountsFile.getInstance().isBalanceAutoUpdateEnabled() || this.m_bAccountInfoUpdateRunning) {
            return false;
        }
        long n;
        if (PayAccountsFile.getInstance().getActiveAccount() == this) {
            n = 60000L;
        }
        else {
            n = 300000L;
        }
        if (this.m_lastAccountInfoUpdate == 0L) {
            if (this.m_accountInfo == null) {
                return true;
            }
            synchronized (this.m_accountInfo) {
                if (this.m_accountInfo.getBalance() == null || this.m_accountInfo.getBalance().getTimestamp() == null || this.m_accountInfo.getBalance().getTimestamp().getTime() > System.currentTimeMillis() || this.m_accountInfo.getBalance().getTimestamp().getTime() < System.currentTimeMillis() - n) {
                    return true;
                }
                return false;
            }
        }
        if (this.m_lastAccountInfoUpdate < System.currentTimeMillis() - n) {
            return true;
        }
        return false;
    }
    
    public XMLTransCert charge(final XMLGenericStrings xmlGenericStrings) throws SecurityException, Exception {
        if (this.getPrivateKey() == null) {
            throw new SecurityException("Account is encrypted and not usable!");
        }
        BIConnection biConnection = null;
        XMLTransCert charge;
        try {
            biConnection = new BIConnection(this.m_theBI);
            biConnection.connect();
            biConnection.authenticate(this.m_accountCertificate, this.m_privateKey);
            charge = biConnection.charge(xmlGenericStrings);
            biConnection.disconnect();
        }
        catch (Exception ex) {
            try {
                biConnection.disconnect();
            }
            catch (Exception ex2) {}
            throw ex;
        }
        this.m_transCerts.addElement(charge);
        return charge;
    }
    
    public void updated() {
        this.fireChangeEvent();
    }
    
    public String getPIID() {
        return this.m_strBiID;
    }
    
    public PaymentInstanceDBEntry getBI() {
        if (this.m_theBI == null) {
            this.m_theBI = PayAccountsFile.getInstance().getBI(this.m_strBiID);
        }
        return this.m_theBI;
    }
    
    public static String checkCouponCode(String s) {
        if (s == null) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        s = "";
        while (stringTokenizer.hasMoreTokens()) {
            s += stringTokenizer.nextToken();
        }
        if (s.length() != 16) {
            return null;
        }
        s = s.toUpperCase();
        for (int i = 0; i < s.length(); ++i) {
            if ((s.charAt(i) < '0' || s.charAt(i) > '9') && (s.charAt(i) < 'A' || s.charAt(i) > 'F')) {
                return null;
            }
        }
        return s;
    }
    
    public void decryptPrivateKey(final IMiscPasswordReader miscPasswordReader) throws Exception {
        if (this.m_encryptedPrivateKey != null) {
            this.decryptPrivateKey(this.m_encryptedPrivateKey, miscPasswordReader, false);
        }
    }
    
    private void decryptPrivateKey(final Node node, final IMiscPasswordReader miscPasswordReader, final boolean b) throws Exception {
        if (this.m_privateKey != null || node == null) {
            return;
        }
        final Element element = (Element)XMLUtil.getFirstChildByName(node, "EncryptedData");
        if (element != null) {
            try {
                if (b) {
                    this.deactivate(element);
                    return;
                }
                IMiscPasswordReader miscPasswordReader2;
                if (miscPasswordReader != null) {
                    miscPasswordReader2 = new IMiscPasswordReader() {
                        public String readPassword(final Object o) {
                            return miscPasswordReader.readPassword(new String("" + PayAccount.this.m_accountCertificate.getAccountNumber()));
                        }
                    };
                }
                else {
                    miscPasswordReader2 = miscPasswordReader;
                }
                LogHolder.log(7, LogType.PAY, "Decrypting account " + this.m_accountCertificate.getAccountNumber());
                XMLEncryption.decryptElement(element, miscPasswordReader2);
            }
            catch (Exception ex) {
                this.deactivate(element);
                return;
            }
        }
        final Element element2 = (Element)XMLUtil.getFirstChildByName(node, "RSAPrivateKey");
        final Element element3 = (Element)XMLUtil.getFirstChildByName(node, "DSAPrivateKey");
        if (element2 != null) {
            if (b) {
                this.deactivate(element2);
                return;
            }
            this.m_privateKey = new MyRSAPrivateKey(element2);
        }
        else {
            if (element3 == null) {
                throw new XMLParseException("No RSA and no DSA private key found");
            }
            if (b) {
                this.deactivate(element3);
                return;
            }
            this.m_privateKey = new MyDSAPrivateKey(element3);
        }
        this.m_encryptedPrivateKey = null;
    }
    
    private void deactivate(final Element element) throws Exception {
        this.m_privateKey = null;
        (this.m_encryptedPrivateKey = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_encryptedPrivateKey, element, true));
    }
}
