// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay;

import anon.pay.xml.XMLTransactionOverview;
import anon.pay.xml.XMLPassivePayment;
import anon.util.captcha.IImageEncodedCaptcha;
import anon.util.captcha.ZipBinaryImageCaptchaClient;
import anon.pay.xml.XMLJapPublicKey;
import anon.pay.xml.XMLResponse;
import anon.crypto.ByteSignature;
import anon.pay.xml.XMLChallenge;
import anon.crypto.IMyPrivateKey;
import anon.pay.xml.XMLAccountCertificate;
import anon.pay.xml.XMLPaymentSettings;
import anon.pay.xml.XMLGenericText;
import anon.pay.xml.XMLVolumePlans;
import anon.pay.xml.XMLPaymentOptions;
import anon.util.IXMLEncodable;
import anon.pay.xml.XMLAccountInfo;
import anon.pay.xml.XMLErrorMessage;
import org.w3c.dom.Document;
import java.util.Date;
import anon.crypto.XMLSignature;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.pay.xml.XMLTransCert;
import anon.pay.xml.XMLGenericStrings;
import anon.util.XMLParseException;
import java.util.Enumeration;
import HTTPClient.ForbiddenIOException;
import anon.crypto.tinytls.TinyTLS;
import logging.LogHolder;
import logging.LogType;
import anon.infoservice.ListenerInterface;
import anon.infoservice.ImmutableProxyInterface;
import anon.infoservice.IProxyInterfaceGetter;
import java.io.IOException;
import anon.infoservice.IMutableProxyInterface;
import java.util.Vector;
import java.net.Socket;
import anon.util.captcha.ICaptchaSender;

public class BIConnection implements ICaptchaSender
{
    public static final int TIMEOUT_DEFAULT = 30000;
    public static final int TIMEOUT_MAX = 100000;
    public static final int TIMEOUT_MIN = 1000;
    public static final String XML_ATTR_CONNECTION_TIMEOUT = "timeout";
    private static int ms_connectionTimeout;
    private PaymentInstanceDBEntry m_theBI;
    private Socket m_socket;
    private HttpClient m_httpClient;
    private Vector m_biConnectionListeners;
    private byte[] m_captchaSolution;
    private boolean m_bSendNewCaptcha;
    private boolean m_bFirstCaptcha;
    private static IMutableProxyInterface ms_proxyInterface;
    
    public BIConnection(final PaymentInstanceDBEntry theBI) {
        this.m_bFirstCaptcha = true;
        if (theBI == null) {
            throw new IllegalArgumentException("PI is null! No connection is possibble.");
        }
        this.m_theBI = theBI;
        this.m_biConnectionListeners = new Vector();
    }
    
    public static void setConnectionTimeout(final int ms_connectionTimeout) {
        if (ms_connectionTimeout > 1000) {
            if (ms_connectionTimeout > 100000) {
                BIConnection.ms_connectionTimeout = 100000;
            }
            else {
                BIConnection.ms_connectionTimeout = ms_connectionTimeout;
            }
        }
        else {
            BIConnection.ms_connectionTimeout = 1000;
        }
    }
    
    public static int getConnectionTimeout() {
        return BIConnection.ms_connectionTimeout;
    }
    
    public static void setMutableProxyInterface(final IMutableProxyInterface ms_proxyInterface) {
        if (ms_proxyInterface != null) {
            BIConnection.ms_proxyInterface = ms_proxyInterface;
        }
    }
    
    public void connect() throws IOException {
        this.connect(BIConnection.ms_connectionTimeout);
    }
    
    public void connect(final int n) throws IOException {
        IOException ex = new IOException("No valid proxy available");
        boolean b = false;
        for (int n2 = 0; n2 < 2 && !Thread.currentThread().isInterrupted(); ++n2) {
            if (n2 == 1) {
                b = true;
            }
            final IProxyInterfaceGetter proxyInterface = BIConnection.ms_proxyInterface.getProxyInterface(b);
            if (proxyInterface != null) {
                try {
                    this.connect_internal(proxyInterface.getProxyInterface(), n);
                    return;
                }
                catch (IOException ex2) {
                    ex = ex2;
                }
            }
        }
        throw ex;
    }
    
    private void connect_internal(final ImmutableProxyInterface immutableProxyInterface, final int soTimeout) throws IOException {
        boolean b = false;
        ListenerInterface listenerInterface = null;
        boolean b2 = false;
        final Enumeration listenerInterfaces = this.m_theBI.getListenerInterfaces();
        while (listenerInterfaces.hasMoreElements()) {
            listenerInterface = listenerInterfaces.nextElement();
            LogHolder.log(7, LogType.PAY, "Trying to connect to Payment Instance at " + listenerInterface.getHost() + ":" + listenerInterface.getPort() + ".");
            try {
                TinyTLS socket;
                if (immutableProxyInterface == null) {
                    socket = new TinyTLS(listenerInterface.getHost(), listenerInterface.getPort());
                }
                else {
                    LogHolder.log(6, LogType.PAY, "Using proxy at " + immutableProxyInterface.getHost() + ":" + immutableProxyInterface.getPort());
                    socket = new TinyTLS(listenerInterface.getHost(), listenerInterface.getPort(), immutableProxyInterface);
                }
                this.m_socket = socket;
                if (soTimeout < 1000 || soTimeout > 100000) {
                    socket.setSoTimeout(BIConnection.ms_connectionTimeout);
                }
                else {
                    socket.setSoTimeout(soTimeout);
                }
                if (this.m_theBI.getCertPath().getFirstVerifiedPath() == null) {}
                socket.setRootKey(this.m_theBI.getCertPath().getFirstVerifiedPath().getFirstCertificate().getPublicKey());
                socket.startHandshake();
                this.m_httpClient = new HttpClient(this.m_socket);
                b2 = true;
                break;
            }
            catch (Exception ex3) {
                if (this.m_httpClient != null) {
                    try {
                        this.m_httpClient.close();
                    }
                    catch (Exception ex) {
                        LogHolder.log(3, LogType.NET, ex);
                    }
                }
                else if (this.m_socket != null) {
                    try {
                        this.m_socket.close();
                    }
                    catch (IOException ex2) {
                        LogHolder.log(3, LogType.NET, ex2);
                    }
                }
                if (ex3 instanceof ForbiddenIOException) {
                    b = true;
                }
                if (listenerInterfaces.hasMoreElements()) {
                    LogHolder.log(3, LogType.PAY, "Could not connect to Payment Instance at " + listenerInterface.getHost() + ":" + listenerInterface.getPort() + ". Trying next interface...", ex3);
                }
                else {
                    LogHolder.log(2, LogType.PAY, "Could not connect to Payment Instance at " + listenerInterface.getHost() + ":" + listenerInterface.getPort() + ". No more interfaces left.", ex3);
                }
            }
        }
        if (b2) {
            LogHolder.log(6, LogType.PAY, "Connected to Payment Instance at " + listenerInterface.getHost() + ":" + listenerInterface.getPort() + ".", true);
            return;
        }
        final String s = "Could not connect to Payment Instance";
        if (b) {
            throw new ForbiddenIOException(s);
        }
        throw new IOException(s);
    }
    
    public void disconnect() throws IOException, XMLParseException {
        this.m_httpClient.close();
    }
    
    public XMLTransCert charge(final XMLGenericStrings xmlGenericStrings) throws Exception {
        this.m_httpClient.writeRequest("POST", "charge", XMLUtil.toString(xmlGenericStrings.toXmlElement(XMLUtil.createDocument())));
        final Document answer = this.m_httpClient.readAnswer();
        final XMLTransCert xmlTransCert = new XMLTransCert(answer);
        if (!XMLSignature.verifyFast(answer, this.m_theBI.getCertPath().getEndEntityKeys())) {
            throw new Exception("The BI's signature under the transfer certificate is invalid");
        }
        xmlTransCert.setReceivedDate(new Date());
        return xmlTransCert;
    }
    
    public XMLErrorMessage buyFlatrate(final long n) throws Exception {
        this.m_httpClient.writeRequest("POST", "buyflat", new Long(n).toString());
        return new XMLErrorMessage(this.m_httpClient.readAnswer());
    }
    
    public XMLAccountInfo getAccountInfo() throws Exception {
        this.m_httpClient.writeRequest("GET", "balance", null);
        final XMLAccountInfo xmlAccountInfo = new XMLAccountInfo(this.m_httpClient.readAnswer());
        if (!XMLSignature.verifyFast(XMLUtil.toXMLDocument(xmlAccountInfo.getBalance()), this.m_theBI.getCertPath().getEndEntityKeys())) {
            throw new Exception("The BI's signature under the balance certificate is Invalid!");
        }
        return xmlAccountInfo;
    }
    
    public XMLPaymentOptions getPaymentOptions() throws Exception {
        this.m_httpClient.writeRequest("GET", "paymentoptions", null);
        return new XMLPaymentOptions(this.m_httpClient.readAnswer());
    }
    
    public XMLVolumePlans getVolumePlans() throws Exception {
        this.m_httpClient.writeRequest("GET", "volumeplans", null);
        return new XMLVolumePlans(this.m_httpClient.readAnswer());
    }
    
    public XMLGenericText getTerms(final String s) throws Exception {
        this.m_httpClient.writeRequest("POST", "terms", s);
        final Document answer = this.m_httpClient.readAnswer();
        XMLGenericText xmlGenericText;
        try {
            xmlGenericText = new XMLGenericText(answer);
        }
        catch (Exception ex) {
            return null;
        }
        return xmlGenericText;
    }
    
    public XMLGenericText getCancellationPolicy(final String s) throws Exception {
        this.m_httpClient.writeRequest("POST", "cancellationpolicy", s);
        final Document answer = this.m_httpClient.readAnswer();
        XMLGenericText xmlGenericText;
        try {
            xmlGenericText = new XMLGenericText(answer);
        }
        catch (Exception ex) {
            return null;
        }
        return xmlGenericText;
    }
    
    public XMLPaymentSettings getPaymentSettings() throws Exception {
        this.m_httpClient.writeRequest("GET", "paymentsettings", null);
        return new XMLPaymentSettings(this.m_httpClient.readAnswer());
    }
    
    public void authenticate(final XMLAccountCertificate xmlAccountCertificate, final IMyPrivateKey myPrivateKey) throws Exception {
        this.m_httpClient.writeRequest("POST", "authenticate", XMLUtil.toString(XMLUtil.toXMLDocument(xmlAccountCertificate)));
        final Document answer = this.m_httpClient.readAnswer();
        final String tagName = answer.getDocumentElement().getTagName();
        if (tagName.equals("Challenge")) {
            this.m_httpClient.writeRequest("POST", "response", XMLUtil.toString(XMLUtil.toXMLDocument(new XMLResponse(ByteSignature.sign(new XMLChallenge(answer).getChallengeForSigning(), myPrivateKey)))));
            final XMLErrorMessage xmlErrorMessage = new XMLErrorMessage(this.m_httpClient.readAnswer());
            if (xmlErrorMessage.getErrorCode() >= 0 && xmlErrorMessage.getErrorCode() != 0) {
                throw xmlErrorMessage;
            }
        }
        else if (tagName.equals("ErrorMessage")) {
            throw new Exception("The BI sent an errormessage: " + new XMLErrorMessage(answer).getErrorDescription());
        }
    }
    
    public XMLAccountCertificate registerNewAccount(final XMLJapPublicKey xmlJapPublicKey, final IMyPrivateKey myPrivateKey) throws Exception {
        byte[] array = null;
        this.m_bSendNewCaptcha = true;
        while (this.m_bSendNewCaptcha) {
            if (!this.m_bFirstCaptcha) {
                try {
                    this.disconnect();
                }
                catch (Exception ex) {
                    LogHolder.log(6, LogType.PAY, "Not connected to payment instance while trying to disconnect");
                }
                this.connect();
            }
            this.m_httpClient.writeRequest("POST", "register", XMLUtil.toString(XMLUtil.toXMLDocument(xmlJapPublicKey)));
            final Document answer = this.m_httpClient.readAnswer();
            try {
                array = new XMLChallenge(answer.getDocumentElement()).getChallengeForSigning();
                this.m_bSendNewCaptcha = false;
                break;
            }
            catch (Exception ex2) {
                LogHolder.log(4, LogType.PAY, "No challenge sent directly while registering account, trying capchta...");
                final ZipBinaryImageCaptchaClient zipBinaryImageCaptchaClient = new ZipBinaryImageCaptchaClient(answer.getDocumentElement());
                this.m_bSendNewCaptcha = false;
                this.fireGotCaptcha(zipBinaryImageCaptchaClient);
            }
        }
        if (this.m_captchaSolution != null) {
            final String s = new String(this.m_captchaSolution);
            final String substring = s.substring(0, s.lastIndexOf(">") + 1);
            array = ("<DontPanic>" + substring.substring(substring.indexOf(">") + 1, substring.lastIndexOf("<")) + "</DontPanic>").getBytes();
        }
        else if (array == null) {
            throw new Exception("CAPTCHA");
        }
        this.m_httpClient.writeRequest("POST", "response", XMLUtil.toString(XMLUtil.toXMLDocument(new XMLResponse(ByteSignature.sign(array, myPrivateKey)))));
        final Document answer2 = this.m_httpClient.readAnswer();
        if (!XMLSignature.verifyFast(answer2, this.m_theBI.getCertPath().getEndEntityKeys())) {
            throw new Exception("AccountCertificate: Wrong signature!");
        }
        final XMLAccountCertificate xmlAccountCertificate = new XMLAccountCertificate(answer2.getDocumentElement());
        if (!xmlAccountCertificate.getPublicKey().equals(xmlJapPublicKey.getPublicKey())) {
            throw new Exception("The JPI is evil (sent a valid certificate, but with a wrong publickey)");
        }
        return xmlAccountCertificate;
    }
    
    public XMLPaymentOptions fetchPaymentOptions() throws Exception {
        this.m_httpClient.writeRequest("GET", "paymentoptions", null);
        return new XMLPaymentOptions(this.m_httpClient.readAnswer().getDocumentElement());
    }
    
    public IXMLEncodable fetchPaymentData(final String s) throws Exception {
        this.m_httpClient.writeRequest("POST", "paymentdata", s);
        final Document answer = this.m_httpClient.readAnswer();
        if (answer == null) {
            return null;
        }
        IXMLEncodable ixmlEncodable;
        if (answer.getDocumentElement().getTagName().equalsIgnoreCase("PassivePayment")) {
            ixmlEncodable = new XMLPassivePayment(answer.getDocumentElement());
        }
        else {
            ixmlEncodable = new XMLErrorMessage(answer.getDocumentElement());
        }
        return ixmlEncodable;
    }
    
    public XMLTransactionOverview fetchTransactionOverview(final XMLTransactionOverview xmlTransactionOverview) throws Exception {
        this.m_httpClient.writeRequest("POST", "transactionoverview", XMLUtil.toString(xmlTransactionOverview.toXmlElement(XMLUtil.createDocument())));
        final Document answer = this.m_httpClient.readAnswer();
        if (answer.getDocumentElement().getTagName().equalsIgnoreCase("ErrorMessage")) {
            return null;
        }
        return new XMLTransactionOverview(answer.getDocumentElement());
    }
    
    public boolean sendPassivePayment(final XMLPassivePayment xmlPassivePayment) {
        try {
            this.m_httpClient.writeRequest("POST", "passivepayment", XMLUtil.toString(xmlPassivePayment.toXmlElement(XMLUtil.createDocument())));
            return new XMLErrorMessage(this.m_httpClient.readAnswer().getDocumentElement()).getErrorCode() == 0;
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.PAY, "Could not send PassivePayment to payment instance: " + ex);
            return false;
        }
    }
    
    public boolean checkCouponCode(final String s) {
        try {
            this.m_httpClient.writeRequest("POST", "coupon", s);
            final XMLErrorMessage xmlErrorMessage = new XMLErrorMessage(this.m_httpClient.readAnswer().getDocumentElement());
            if (xmlErrorMessage.getErrorCode() == 0) {
                return true;
            }
            LogHolder.log(3, LogType.PAY, "User entered an invalid coupon, reply from jpi was: " + xmlErrorMessage.getMessage());
            return false;
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.PAY, "BIConnection.checkCouponCode: Could not check coupon validity due to: " + ex + " so I'll return false");
            return false;
        }
    }
    
    public void addConnectionListener(final IBIConnectionListener ibiConnectionListener) {
        if (!this.m_biConnectionListeners.contains(ibiConnectionListener)) {
            this.m_biConnectionListeners.addElement(ibiConnectionListener);
        }
    }
    
    private void fireGotCaptcha(final IImageEncodedCaptcha imageEncodedCaptcha) {
        for (int i = 0; i < this.m_biConnectionListeners.size(); ++i) {
            ((IBIConnectionListener)this.m_biConnectionListeners.elementAt(i)).gotCaptcha(this, imageEncodedCaptcha);
        }
    }
    
    public void setCaptchaSolution(final byte[] captchaSolution) {
        this.m_captchaSolution = captchaSolution;
    }
    
    public void getNewCaptcha() {
        this.m_bSendNewCaptcha = true;
        this.m_bFirstCaptcha = false;
    }
    
    static {
        BIConnection.ms_connectionTimeout = 30000;
        BIConnection.ms_proxyInterface = new IMutableProxyInterface.DummyMutableProxyInterface();
    }
}
