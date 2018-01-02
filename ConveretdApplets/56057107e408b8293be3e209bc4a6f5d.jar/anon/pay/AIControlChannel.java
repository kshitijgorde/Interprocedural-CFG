// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay;

import java.util.Observable;
import java.util.Enumeration;
import java.util.Hashtable;
import anon.infoservice.MixPosition;
import anon.pay.xml.XMLPriceCertificate;
import java.sql.Timestamp;
import anon.util.IXMLEncodable;
import anon.util.XMLUtil;
import anon.pay.xml.XMLResponse;
import anon.crypto.ByteSignature;
import org.w3c.dom.Element;
import anon.pay.xml.XMLChallenge;
import anon.pay.xml.XMLErrorMessage;
import anon.pay.xml.XMLAiLoginConfirmation;
import anon.pay.xml.XMLPayRequest;
import org.w3c.dom.Document;
import logging.LogHolder;
import logging.LogType;
import anon.IServiceContainer;
import anon.client.Multiplexer;
import anon.pay.xml.XMLEasyCC;
import anon.infoservice.MixCascade;
import java.util.Observer;
import anon.client.PacketCounter;
import java.util.Vector;
import anon.client.XmlControlChannel;

public class AIControlChannel extends XmlControlChannel
{
    public static final long MAX_PREPAID_INTERVAL = 3000000L;
    public static final long MIN_PREPAID_INTERVAL = 5000L;
    public static final long AI_LOGIN_TIMEOUT = 120000L;
    private static final int FIRST_MIX = 0;
    private static final String SYNCH_AI_LOGIN_MIXVERSION = "00.07.20";
    private static final String PREPAID_AMOUNT_IN_PAY_REQ_MIXVERSION = "00.08.42";
    public static final boolean REVERT_PRE_PREPAID = true;
    private int m_aiLogin_timeout;
    private static long m_totalBytes;
    private boolean m_bPrepaidReceived;
    private long m_prepaidBytes;
    private boolean m_bMultiplexerClosed;
    private Vector m_aiListeners;
    private PacketCounter m_packetCounter;
    private Observer m_packetCountEmptyObserver;
    private boolean m_bEmptyMessageSent;
    private MixCascade m_connectedCascade;
    private XMLEasyCC m_initialCC;
    private Vector m_aiLoginSyncObject;
    private boolean m_prepaidAmountInPayRequest;
    
    public AIControlChannel(final Multiplexer multiplexer, final PacketCounter packetCounter, final IServiceContainer serviceContainer, final MixCascade connectedCascade) {
        super(2, multiplexer, serviceContainer, true);
        this.m_aiLogin_timeout = 30000;
        this.m_bPrepaidReceived = false;
        this.m_prepaidBytes = 0L;
        this.m_bMultiplexerClosed = false;
        this.m_aiListeners = new Vector();
        this.m_bEmptyMessageSent = false;
        this.m_prepaidAmountInPayRequest = false;
        this.m_packetCounter = packetCounter;
        this.m_connectedCascade = connectedCascade;
        this.m_aiLoginSyncObject = new Vector(1);
        final String version = this.m_connectedCascade.getMixInfo(0).getServiceSoftware().getVersion();
        if (version != null) {
            this.m_prepaidAmountInPayRequest = (version.compareTo("00.08.42") >= 0);
        }
        LogHolder.log(6, LogType.PAY, "Mix " + this.m_connectedCascade.getMixInfo(0).getName() + (this.m_prepaidAmountInPayRequest ? " supports " : " does not support ") + "improved prepaid bytes negotiation.");
    }
    
    public void addAIListener(final IAIEventListener iaiEventListener) {
        synchronized (this.m_aiListeners) {
            if (!this.m_aiListeners.contains(iaiEventListener)) {
                this.m_aiListeners.addElement(iaiEventListener);
            }
        }
    }
    
    public void processXmlMessage(final Document document) {
        final Element documentElement = document.getDocumentElement();
        final String tagName = documentElement.getTagName();
        try {
            if (tagName.equals(XMLPayRequest.XML_ELEMENT_NAME)) {
                this.processPayRequest(new XMLPayRequest(documentElement));
            }
            else {
                if (tagName.equals("LoginConfirmation")) {
                    final XMLAiLoginConfirmation xmlAiLoginConfirmation = new XMLAiLoginConfirmation(documentElement);
                    synchronized (this.m_aiLoginSyncObject) {
                        if (xmlAiLoginConfirmation.getCode() == 0) {
                            this.m_aiLoginSyncObject.addElement(new Object());
                        }
                        this.m_aiLoginSyncObject.notifyAll();
                        return;
                    }
                }
                if (tagName.equals("ErrorMessage")) {
                    final XMLErrorMessage xmlErrorMessage = new XMLErrorMessage(documentElement);
                    LogHolder.log(2, LogType.PAY, "For account " + PayAccountsFile.getInstance().getActiveAccountNumber() + ", processing AI ErrorMessage " + xmlErrorMessage.getErrorCode() + ": " + xmlErrorMessage.getMessage());
                    if (xmlErrorMessage.getErrorCode() == 10) {
                        this.updateBalance(PayAccountsFile.getInstance().getActiveAccount(), false);
                        final PayAccount alternativeChargedAccount = PayAccountsFile.getInstance().getAlternativeChargedAccount(this.m_connectedCascade.getPIID());
                        if (alternativeChargedAccount != null) {
                            PayAccountsFile.getInstance().setActiveAccount(alternativeChargedAccount);
                        }
                        else {
                            this.getServiceContainer().keepCurrentService(false);
                            this.processErrorMessage(new XMLErrorMessage(documentElement));
                        }
                    }
                    else {
                        this.getServiceContainer().keepCurrentService(false);
                        this.processErrorMessage(new XMLErrorMessage(documentElement));
                    }
                }
                else if (tagName.equals("Challenge")) {
                    this.processChallenge(new XMLChallenge(documentElement));
                }
                else if (tagName.equals("CC")) {
                    this.processInitialCC(new XMLEasyCC(documentElement));
                }
                else {
                    LogHolder.log(4, LogType.PAY, "Received unknown payment control channel message '" + tagName + "'");
                }
            }
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.PAY, ex);
            this.getServiceContainer().keepCurrentService(false);
            PayAccountsFile.getInstance().signalAccountError(new XMLErrorMessage(6, ex.getClass().getName() + ": " + ex.getMessage()));
        }
    }
    
    private synchronized void handlePrepaidBytesReceived(final int n, final PayAccount payAccount) {
        if (payAccount == null) {
            throw new NullPointerException("Active Account must not be null!");
        }
        if (n > 0 && !this.m_bPrepaidReceived) {
            this.m_prepaidBytes = n;
            this.m_bPrepaidReceived = true;
            payAccount.updateCurrentBytes(n * -1);
        }
    }
    
    private synchronized void processChallenge(final XMLChallenge xmlChallenge) throws Exception {
        final byte[] challengeForSigning = xmlChallenge.getChallengeForSigning();
        LogHolder.log(5, LogType.PAY, "Received " + xmlChallenge.getPrepaidBytes() + " prepaid bytes.");
        final PayAccount activeAccount = PayAccountsFile.getInstance().getActiveAccount();
        if (activeAccount == null) {
            throw new Exception("Received Challenge from AI but ActiveAccount not set!");
        }
        if (!this.m_prepaidAmountInPayRequest) {
            this.handlePrepaidBytesReceived(xmlChallenge.getPrepaidBytes(), activeAccount);
        }
        this.sendXmlMessage(XMLUtil.toXMLDocument(new XMLResponse(ByteSignature.sign(challengeForSigning, activeAccount.getPrivateKey()))));
    }
    
    private void processErrorMessage(final XMLErrorMessage xmlErrorMessage) {
        PayAccountsFile.getInstance().signalAccountError(xmlErrorMessage);
    }
    
    private synchronized void processPayRequest(final XMLPayRequest xmlPayRequest) {
        if (xmlPayRequest.isInitialCCRequest()) {
            if (this.m_prepaidAmountInPayRequest) {
                this.handlePrepaidBytesReceived(xmlPayRequest.getPrepaidBytes(), PayAccountsFile.getInstance().getActiveAccount());
            }
            this.processInitialCC(xmlPayRequest.getCC());
            return;
        }
        if (xmlPayRequest.isAccountRequest() && this.sendAccountCert() != 0) {
            LogHolder.log(1, LogType.PAY, "Could not send account certificate!");
        }
        try {
            this.processCcToSign(xmlPayRequest.getCC());
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.PAY, ex);
        }
    }
    
    private void updateBalance(final PayAccount payAccount, final boolean b) {
        if (payAccount == null) {
            return;
        }
        final Runnable runnable = new Runnable() {
            public void run() {
                try {
                    if (b) {
                        payAccount.fetchAccountInfo(false, 2000);
                    }
                    else {
                        payAccount.fetchAccountInfo(false);
                    }
                }
                catch (Exception ex) {
                    LogHolder.log(7, LogType.PAY, ex);
                }
            }
        };
        if (b) {
            LogHolder.log(7, LogType.PAY, "Fetching new Balance from BI.");
            runnable.run();
        }
        else {
            LogHolder.log(7, LogType.PAY, "Fetching new Balance from BI asynchronously.");
            final Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.start();
        }
    }
    
    private synchronized void processCcToSign(XMLEasyCC initialCC) throws Exception {
        if (initialCC == null) {
            return;
        }
        final PayAccount activeAccount = PayAccountsFile.getInstance().getActiveAccount();
        if (activeAccount == null || activeAccount.getAccountNumber() != initialCC.getAccountNumber()) {
            throw new Exception("Received CC with wrong accountnumber");
        }
        activeAccount.updateCurrentBytes(this.m_packetCounter);
        final XMLEasyCC cc = activeAccount.getAccountInfo().getCC(initialCC.getConcatenatedPriceCertHashes());
        long transferredBytes = 0L;
        if (cc != null) {
            if (this.m_initialCC == null) {
                LogHolder.log(4, LogType.PAY, "No initial CC available! The Mix might have lost its CC.");
                if (this.m_prepaidBytes > 0L) {
                    activeAccount.updateCurrentBytes(this.m_prepaidBytes);
                }
            }
            else {
                transferredBytes = cc.getTransferredBytes();
                LogHolder.log(7, LogType.PAY, "Transferred bytes of last CC: " + transferredBytes);
            }
        }
        long currentBytes = AIControlChannel.m_totalBytes = activeAccount.getCurrentBytes();
        final long n = initialCC.getTransferredBytes() - currentBytes - this.m_connectedCascade.getPrepaidInterval();
        if (n > 0L) {
            LogHolder.log(4, LogType.PAY, "Illegal number of prepaid bytes for signing. Difference/Spent/CC/PrevCC: " + n + "/" + currentBytes + "/" + initialCC.getTransferredBytes() + "/" + transferredBytes);
            if (currentBytes < 0L) {
                LogHolder.log(4, LogType.PAY, "The mix might have lost a CC. Resetting transferred bytes to zero for now...");
                activeAccount.updateCurrentBytes(currentBytes * -1L);
                currentBytes = activeAccount.getCurrentBytes();
            }
            else if (initialCC.getTransferredBytes() < transferredBytes) {
                LogHolder.log(4, LogType.PAY, "Requested less than confirmed before! Maybe a CC did get lost!");
            }
        }
        initialCC.setTransferredBytes(currentBytes + this.m_connectedCascade.getPrepaidInterval());
        if (initialCC.getTransferredBytes() > transferredBytes) {
            initialCC.setPriceCerts(this.m_connectedCascade.getPriceCertificateHashes());
            initialCC.setPIID(activeAccount.getAccountCertificate().getPIID());
            initialCC.setCascadeID(this.m_connectedCascade.getId());
            initialCC.sign(activeAccount.getPrivateKey());
            if (activeAccount.addCostConfirmation(initialCC) <= 0L) {
                LogHolder.log(4, LogType.PAY, "Added old cost confirmation!");
            }
        }
        else if (cc != null && this.m_initialCC != null) {
            initialCC = cc;
        }
        else {
            LogHolder.log(0, LogType.PAY, "Creating zero CC!!");
            initialCC.setTransferredBytes(0L);
            initialCC.setPriceCerts(this.m_connectedCascade.getPriceCertificateHashes());
            initialCC.setPIID(activeAccount.getAccountCertificate().getPIID());
            initialCC.setCascadeID(this.m_connectedCascade.getId());
            initialCC.sign(activeAccount.getPrivateKey());
            activeAccount.addCostConfirmation(initialCC);
        }
        if (this.m_initialCC == null) {
            LogHolder.log(5, LogType.PAY, "Seems to be the first connection to service. Setting initial CC to current CC...");
            this.m_initialCC = initialCC;
        }
        if (!PayAccountsFile.getInstance().getActiveAccount().isCharged(new Timestamp(System.currentTimeMillis()))) {
            synchronized (this.m_aiLoginSyncObject) {
                if (!this.m_bMultiplexerClosed && this.m_packetCountEmptyObserver == null) {
                    this.m_packetCountEmptyObserver = new EmptyAccountPacketObserver(initialCC.getConcatenatedPriceCertHashes());
                    this.m_packetCounter.addObserver(this.m_packetCountEmptyObserver);
                }
            }
        }
        this.sendXmlMessage(XMLUtil.toXMLDocument(initialCC));
    }
    
    public int sendAccountCert() {
        String s = null;
        final Vector priceCertificates = this.m_connectedCascade.getPriceCertificates();
        final Vector mixIds = this.m_connectedCascade.getMixIds();
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        final PayAccount activeAccount = PayAccountsFile.getInstance().getActiveAccount();
        if (activeAccount == null || !activeAccount.isCharged(timestamp) || activeAccount.getBI() == null || !activeAccount.getBI().getId().equals(this.m_connectedCascade.getPIID())) {
            PayAccount activeAccount2 = null;
            PayAccount activeAccount3 = null;
            if (activeAccount != null && activeAccount.getCurrentSpent() == 0L) {
                activeAccount3 = PayAccountsFile.getInstance().getActiveAccount();
            }
            final Vector accounts = PayAccountsFile.getInstance().getAccounts(this.m_connectedCascade.getPIID());
            if (accounts.size() > 0) {
                for (int i = 0; i < accounts.size(); ++i) {
                    activeAccount2 = accounts.elementAt(i);
                    if (activeAccount2.isCharged(timestamp)) {
                        break;
                    }
                    if (activeAccount3 == null && (activeAccount == null || activeAccount.getCurrentSpent() == 0L) && !activeAccount2.hasExpired()) {
                        activeAccount3 = activeAccount2;
                    }
                    activeAccount2 = null;
                }
                if (activeAccount2 != null) {
                    PayAccountsFile.getInstance().setActiveAccount(activeAccount2);
                }
                else if (activeAccount3 != null) {
                    PayAccountsFile.getInstance().setActiveAccount(activeAccount3);
                }
                if ((PayAccountsFile.getInstance().getActiveAccount() == null || !PayAccountsFile.getInstance().getActiveAccount().isCharged(timestamp)) && accounts.size() > 0) {
                    LogHolder.log(4, LogType.PAY, "No charged account is available for connecting. Trying to update balances...");
                    for (int j = 0; j < accounts.size(); ++j) {
                        final PayAccount activeAccount4 = accounts.elementAt(j);
                        if (activeAccount4.getBalance() == null || activeAccount4.shouldUpdateAccountInfo()) {
                            this.updateBalance(activeAccount4, true);
                            if (activeAccount4.isCharged(timestamp)) {
                                PayAccountsFile.getInstance().setActiveAccount(activeAccount4);
                                break;
                            }
                        }
                    }
                }
            }
        }
        final int signalAccountRequest = PayAccountsFile.getInstance().signalAccountRequest(this.m_connectedCascade);
        if (signalAccountRequest != 0) {
            return signalAccountRequest;
        }
        final PayAccount activeAccount5 = PayAccountsFile.getInstance().getActiveAccount();
        if (activeAccount5 == null) {
            return -1;
        }
        if (priceCertificates.size() != mixIds.size()) {
            s = "Not all Mixes in cascade " + this.m_connectedCascade.getId() + " have price certs! " + "PriceCerts/MixIDs:" + priceCertificates.size() + "/" + mixIds.size();
        }
        else {
            for (int k = 0; k < mixIds.size(); ++k) {
                final XMLPriceCertificate xmlPriceCertificate = priceCertificates.elementAt(k);
                final String s2 = mixIds.elementAt(k);
                if (!xmlPriceCertificate.verify(PayAccountsFile.getInstance().getActiveAccount().getBI())) {
                    s = "Price certificate of cascade " + this.m_connectedCascade.getId() + " for mix " + s2 + " cannot be verified!";
                    break;
                }
                if (!xmlPriceCertificate.getSubjectKeyIdentifier().equals(s2)) {
                    s = "SKI in price certificate of cascade " + this.m_connectedCascade.getId() + " differs from Mix ID! SKI:" + xmlPriceCertificate.getSubjectKeyIdentifier() + " MixID: " + s2;
                    break;
                }
            }
        }
        if (s != null) {
            LogHolder.log(3, LogType.PAY, s);
            this.getServiceContainer().keepCurrentService(false);
            PayAccountsFile.getInstance().signalAccountError(new XMLErrorMessage(17, s));
            return -1;
        }
        activeAccount5.resetCurrentBytes();
        this.sendXmlMessage(XMLUtil.toXMLDocument(activeAccount5.getAccountCertificate()));
        int n = -1;
        synchronized (this.m_aiLoginSyncObject) {
            LogHolder.log(6, LogType.PAY, "Performing new synchronous AI login");
            try {
                this.m_aiLoginSyncObject.wait(this.m_aiLogin_timeout);
            }
            catch (InterruptedException ex) {
                n = -24;
            }
            if (this.m_aiLoginSyncObject.size() > 0) {
                n = 0;
            }
            this.m_aiLoginSyncObject.removeAllElements();
        }
        return n;
    }
    
    public static long getBytes() {
        return AIControlChannel.m_totalBytes;
    }
    
    private synchronized void processInitialCC(final XMLEasyCC initialCC) {
        final PayAccount activeAccount = PayAccountsFile.getInstance().getActiveAccount();
        final String s = "AI has sent a INVALID last cost confirmation.";
        Label_0594: {
            if (initialCC.verify(activeAccount.getPublicKey())) {
                try {
                    if (initialCC.getNrOfPriceCerts() != this.m_connectedCascade.getNrOfPriceCerts()) {
                        LogHolder.log(2, LogType.PAY, "number of price certificates in cost confirmation does not match number of price certs in cascade");
                        this.getServiceContainer().keepCurrentService(false);
                        PayAccountsFile.getInstance().signalAccountError(new XMLErrorMessage(17, "AI sent CC will illegal number of price certs" + initialCC.getNrOfPriceCerts()));
                        return;
                    }
                    final Hashtable priceCertHashes = initialCC.getPriceCertHashes();
                    final Enumeration<MixPosition> keys = this.m_connectedCascade.getPriceCertificateHashes().keys();
                    final Hashtable priceCertificateHashes = this.m_connectedCascade.getPriceCertificateHashes();
                    int n = 0;
                    while (keys.hasMoreElements()) {
                        final MixPosition mixPosition = keys.nextElement();
                        final String s2 = priceCertificateHashes.get(mixPosition);
                        final String s3 = priceCertHashes.get(mixPosition);
                        if (s3 == null || !s2.equals(s3)) {
                            String s4 = "AI sent CC with illegal price cert hash for mix " + (mixPosition.getPosition() + 1) + " (" + (n + 1) + ")" + "!";
                            if (s3 == null) {
                                s4 += " Price certificate for this Mix was not found in CC!";
                            }
                            LogHolder.log(4, LogType.PAY, s4);
                            this.getServiceContainer().keepCurrentService(false);
                            PayAccountsFile.getInstance().signalAccountError(new XMLErrorMessage(17, s4));
                            return;
                        }
                        priceCertHashes.remove(mixPosition);
                        ++n;
                    }
                    LogHolder.log(7, LogType.PAY, "AI has sent a valid last cost confirmation. Adding it to account.");
                    if (this.m_initialCC == null) {
                        activeAccount.updateCurrentBytes(initialCC.getTransferredBytes());
                        this.m_initialCC = initialCC;
                    }
                    else {
                        final long n2 = initialCC.getTransferredBytes() - this.m_initialCC.getTransferredBytes();
                        LogHolder.log(4, LogType.PAY, "Updated initial CostConfirmation! Difference: " + n2);
                        activeAccount.updateCurrentBytes(n2);
                    }
                    long transferredBytes = initialCC.getTransferredBytes();
                    if (activeAccount.addCostConfirmation(initialCC) < 0L) {
                        transferredBytes = 0L;
                        LogHolder.log(4, LogType.PAY, "Received old cost confirmation!");
                    }
                    final long n3 = this.m_connectedCascade.getPrepaidInterval() - (transferredBytes - activeAccount.getCurrentBytes());
                    final long transferredBytes2 = initialCC.getTransferredBytes();
                    final XMLEasyCC xmlEasyCC = new XMLEasyCC(initialCC);
                    if (n3 > 0L) {
                        xmlEasyCC.setTransferredBytes(transferredBytes + n3);
                    }
                    else {
                        xmlEasyCC.setTransferredBytes(transferredBytes);
                    }
                    xmlEasyCC.sign(activeAccount.getPrivateKey());
                    if (n3 > 0L && activeAccount.addCostConfirmation(xmlEasyCC) <= 0L) {
                        LogHolder.log(4, LogType.PAY, "Sending old cost confirmation! Diff (ShoulBe)/Old/New:" + n3 + "/" + transferredBytes2 + "/" + xmlEasyCC.getTransferredBytes());
                    }
                    this.sendXmlMessage(XMLUtil.toXMLDocument(xmlEasyCC));
                    return;
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.PAY, s, ex);
                    break Label_0594;
                }
            }
            LogHolder.log(3, LogType.PAY, s);
        }
        this.getServiceContainer().keepCurrentService(false);
        PayAccountsFile.getInstance().signalAccountError(new XMLErrorMessage(3, s));
    }
    
    public void multiplexerClosed() {
        synchronized (this.m_aiLoginSyncObject) {
            this.m_bMultiplexerClosed = true;
            if (this.m_packetCountEmptyObserver != null) {
                this.m_packetCounter.deleteObserver(this.m_packetCountEmptyObserver);
            }
            this.m_aiLoginSyncObject.notifyAll();
        }
    }
    
    public void setAILoginTimeout(final int aiLogin_timeout) {
        synchronized (this.m_aiLoginSyncObject) {
            this.m_aiLogin_timeout = aiLogin_timeout;
        }
    }
    
    public boolean isPrepaidAmountInPayRequest() {
        return this.m_prepaidAmountInPayRequest;
    }
    
    public void setPrepaidAmountInPayRequest(final boolean prepaidAmountInPayRequest) {
        this.m_prepaidAmountInPayRequest = prepaidAmountInPayRequest;
    }
    
    static {
        AIControlChannel.m_totalBytes = 0L;
    }
    
    private final class EmptyAccountPacketObserver implements Observer
    {
        private String m_concatenatedPCHashes;
        
        private EmptyAccountPacketObserver(final String concatenatedPCHashes) {
            this.m_concatenatedPCHashes = concatenatedPCHashes;
        }
        
        public void update(final Observable observable, final Object o) {
            synchronized (AIControlChannel.this.m_aiLoginSyncObject) {
                if (!AIControlChannel.this.m_bEmptyMessageSent) {
                    final PayAccount activeAccount = PayAccountsFile.getInstance().getActiveAccount();
                    try {
                        activeAccount.updateCurrentBytes(AIControlChannel.this.m_packetCounter);
                        long n = activeAccount.getAccountInfo().getCC(this.m_concatenatedPCHashes).getTransferredBytes() - activeAccount.getCurrentBytes();
                        final long currentCreditCalculatedAlsoNegative = activeAccount.getCurrentCreditCalculatedAlsoNegative();
                        if (currentCreditCalculatedAlsoNegative < 0L) {
                            n += currentCreditCalculatedAlsoNegative;
                        }
                        if (n > 0L) {
                            return;
                        }
                    }
                    catch (Exception ex) {
                        LogHolder.log(2, LogType.PAY, ex);
                    }
                    PayAccountsFile.getInstance().signalAccountError(new XMLErrorMessage(10));
                    for (int i = 0; i < AIControlChannel.this.m_aiListeners.size(); ++i) {
                        ((IAIEventListener)AIControlChannel.this.m_aiListeners.elementAt(i)).accountEmpty(PayAccountsFile.getInstance().getActiveAccount(), AIControlChannel.this.m_connectedCascade);
                    }
                    AIControlChannel.this.m_bEmptyMessageSent = true;
                }
            }
        }
    }
}
