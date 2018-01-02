// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import java.net.InetAddress;
import anon.terms.template.TermsAndConditionsTemplate;
import anon.util.ClassUtil;
import anon.crypto.ExpiredSignatureException;
import java.security.SignatureException;
import anon.pay.PaymentInstanceDBEntry;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import HTTPClient.HTTPResponse;
import java.net.SocketException;
import java.net.ConnectException;
import java.io.InterruptedIOException;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import HTTPClient.HTTPConnection;
import HTTPClient.NVPair;
import java.util.StringTokenizer;
import anon.util.Util;
import java.util.Date;
import org.w3c.dom.Document;
import logging.LogHolder;
import logging.LogType;
import anon.crypto.SignatureCreator;
import java.util.Enumeration;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.crypto.SignatureVerifier;
import anon.util.XMLParseException;
import anon.crypto.MultiCertPath;
import anon.crypto.XMLSignature;
import org.w3c.dom.Element;
import java.util.Vector;
import anon.crypto.IVerifyable;

public class InfoServiceDBEntry extends AbstractDistributableCertifiedDatabaseEntry implements IVerifyable, IBoostrapable
{
    public static final String XML_ELEMENT_CONTAINER_NAME = "InfoServices";
    public static final String XML_ELEMENT_NAME = "InfoService";
    public static final String HEADER_STATISTICS = "statistics";
    public static final int DEFAULT_GET_XML_CONNECTION_TIMEOUT = 20000;
    private static final int BLOCK_FACTOR_IF_UNREACHABLE = 5;
    private static int m_getXmlConnectionTimeout;
    private static long m_timeFirstJVMSocketError;
    private static long m_timeHandleAfterJVMSocketError;
    private static Runnable m_threadHandleAfterJVMSocketError;
    private static IMutableProxyInterface ms_proxyInterface;
    private static IBrowserIdentification ms_browserIdentification;
    private String m_strInfoServiceId;
    private String m_strName;
    private boolean m_bTemp;
    private ServiceSoftware m_infoserviceSoftware;
    private Vector m_listenerInterfaces;
    private int m_preferedListenerInterface;
    private boolean m_bPrimaryForwarderList;
    private boolean m_neighbour;
    private Element m_xmlDescription;
    private boolean m_userDefined;
    private long m_creationTimeStamp;
    private XMLSignature m_signature;
    private MultiCertPath m_certPath;
    private long m_serial;
    private boolean m_bPerfServerEnabled;
    static /* synthetic */ Class class$anon$infoservice$InfoServiceDBEntry;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    static /* synthetic */ Class class$anon$pay$PaymentInstanceDBEntry;
    static /* synthetic */ Class class$anon$infoservice$MixInfo;
    static /* synthetic */ Class class$org$w3c$dom$Element;
    static /* synthetic */ Class class$anon$infoservice$MessageDBEntry;
    static /* synthetic */ Class class$anon$infoservice$JavaVersionDBEntry;
    
    private static String generateId(final ListenerInterface listenerInterface) {
        return listenerInterface.getHost() + "%3A" + listenerInterface.getPort();
    }
    
    public InfoServiceDBEntry(final Element element) throws XMLParseException {
        this(element, 0L);
    }
    
    public InfoServiceDBEntry(final Element xmlDescription, final long n) throws XMLParseException {
        super((n <= 0L) ? (System.currentTimeMillis() + 900000L) : n);
        this.m_bTemp = false;
        if (xmlDescription == null) {
            throw new XMLParseException("##__null__##");
        }
        this.m_xmlDescription = xmlDescription;
        this.m_signature = SignatureVerifier.getInstance().getVerifiedXml(xmlDescription, 2);
        if (this.m_signature != null) {
            this.m_certPath = this.m_signature.getMultiCertPath();
        }
        if (XMLUtil.getFirstChildByName(xmlDescription, "UserDefined") == null) {
            this.m_userDefined = false;
        }
        else {
            this.m_userDefined = true;
        }
        if (XMLUtil.getFirstChildByName(xmlDescription, "PerformanceServer") == null) {
            this.m_bPerfServerEnabled = false;
        }
        else {
            this.m_bPerfServerEnabled = true;
        }
        this.m_strInfoServiceId = xmlDescription.getAttribute("id");
        if (!this.checkId()) {
            throw new XMLParseException("##__root__##", "Malformed InfoService ID: " + this.m_strInfoServiceId);
        }
        this.m_strName = XMLUtil.parseValue(XMLUtil.getFirstChildByName(xmlDescription, "Name"), null);
        if (this.m_strName == null) {
            throw new XMLParseException("Name");
        }
        this.m_infoserviceSoftware = new ServiceSoftware(XMLUtil.getFirstChildByName(xmlDescription, ServiceSoftware.getXmlElementName()));
        final Node firstChildByName = XMLUtil.getFirstChildByName(xmlDescription, "Network");
        if (firstChildByName == null) {
            throw new XMLParseException("Network");
        }
        final Element element = (Element)XMLUtil.getFirstChildByName(firstChildByName, "ListenerInterfaces");
        if (firstChildByName == null) {
            throw new XMLParseException("ListenerInterfaces");
        }
        final NodeList elementsByTagName = element.getElementsByTagName("ListenerInterface");
        if (elementsByTagName.getLength() == 0) {
            throw new XMLParseException("ListenerInterface");
        }
        this.m_listenerInterfaces = new Vector();
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            this.m_listenerInterfaces.addElement(new ListenerInterface((Element)elementsByTagName.item(i)));
        }
        this.m_preferedListenerInterface = 0;
        this.m_creationTimeStamp = XMLUtil.parseValue(XMLUtil.getFirstChildByName(xmlDescription, "LastUpdate"), -1L);
        if (this.m_creationTimeStamp == -1L) {
            throw new XMLParseException("LastUpdate");
        }
        this.m_serial = XMLUtil.parseAttribute(xmlDescription, "serial", 0L);
        if (XMLUtil.getFirstChildByName(xmlDescription, "ForwarderList") == null) {
            this.m_bPrimaryForwarderList = false;
        }
        else {
            this.m_bPrimaryForwarderList = true;
        }
        this.m_neighbour = true;
    }
    
    public InfoServiceDBEntry(final String s, final int n) throws IllegalArgumentException {
        this(null, null, new ListenerInterface(s, n).toVector(), false, true, 0L, 0L, false);
        this.setUserDefined(true);
    }
    
    public InfoServiceDBEntry(final Vector vector) throws IllegalArgumentException {
        this(null, null, vector, false, true, 0L, 0L, false);
        this.setUserDefined(true);
    }
    
    public InfoServiceDBEntry(final String strName, final String strInfoServiceId, final Vector vector, final boolean bPrimaryForwarderList, final boolean b, final long creationTimeStamp, final long serial, final boolean bPerfServerEnabled) throws IllegalArgumentException {
        super(b ? Long.MAX_VALUE : (System.currentTimeMillis() + 900000L));
        this.m_bTemp = false;
        if (vector == null) {
            throw new IllegalArgumentException("No listener interfaces!");
        }
        final Enumeration<Object> elements = vector.elements();
        this.m_listenerInterfaces = new Vector();
        while (elements.hasMoreElements()) {
            this.m_listenerInterfaces.addElement(elements.nextElement());
            if (!(this.m_listenerInterfaces.lastElement() instanceof ListenerInterface)) {
                throw new IllegalArgumentException("Invalid listener interface!");
            }
        }
        if (strInfoServiceId == null) {
            this.m_strInfoServiceId = generateId(this.m_listenerInterfaces.firstElement());
        }
        else {
            this.m_strInfoServiceId = strInfoServiceId;
        }
        this.m_strName = strName;
        if (this.m_strName == null) {
            final ListenerInterface listenerInterface = this.m_listenerInterfaces.firstElement();
            this.m_strName = listenerInterface.getHost() + ":" + Integer.toString(listenerInterface.getPort());
        }
        this.m_bPrimaryForwarderList = bPrimaryForwarderList;
        this.m_infoserviceSoftware = new ServiceSoftware("IS.08.044");
        this.m_preferedListenerInterface = 0;
        this.m_creationTimeStamp = creationTimeStamp;
        this.m_serial = serial;
        this.m_neighbour = false;
        this.m_bPerfServerEnabled = bPerfServerEnabled;
        this.m_xmlDescription = this.generateXmlRepresentation();
    }
    
    public boolean isPersistanceDeletionAllowed() {
        return XMLUtil.getStorageMode() == 2;
    }
    
    public void deletePersistence() {
        if (this.isPersistanceDeletionAllowed()) {
            this.m_xmlDescription = null;
        }
    }
    
    public static void setConnectionTimeout(final int getXmlConnectionTimeout) {
        if (getXmlConnectionTimeout >= 1000) {
            InfoServiceDBEntry.m_getXmlConnectionTimeout = getXmlConnectionTimeout;
        }
    }
    
    public static int getConnectionTimeout() {
        return InfoServiceDBEntry.m_getXmlConnectionTimeout;
    }
    
    public static void setBrowserIdentification(final IBrowserIdentification ms_browserIdentification) {
        if (ms_browserIdentification != null) {
            InfoServiceDBEntry.ms_browserIdentification = ms_browserIdentification;
        }
    }
    
    public static void setMutableProxyInterface(final IMutableProxyInterface ms_proxyInterface) {
        if (ms_proxyInterface != null) {
            InfoServiceDBEntry.ms_proxyInterface = ms_proxyInterface;
        }
    }
    
    public static void setJVMNetworkErrorHandling(final Runnable threadHandleAfterJVMSocketError, final long timeHandleAfterJVMSocketError) {
        if (timeHandleAfterJVMSocketError >= 0L && threadHandleAfterJVMSocketError != null) {
            InfoServiceDBEntry.m_threadHandleAfterJVMSocketError = threadHandleAfterJVMSocketError;
            InfoServiceDBEntry.m_timeHandleAfterJVMSocketError = timeHandleAfterJVMSocketError;
            return;
        }
        throw new IllegalArgumentException("Runnable: " + threadHandleAfterJVMSocketError + " " + "Timeout: " + timeHandleAfterJVMSocketError);
    }
    
    private Element generateXmlRepresentation() {
        final Document document = XMLUtil.createDocument();
        final Element element = document.createElement("InfoService");
        XMLUtil.setAttribute(element, "id", this.m_strInfoServiceId);
        XMLUtil.setAttribute(element, "serial", this.m_serial);
        final Element element2 = document.createElement("Name");
        XMLUtil.setValue(element2, this.m_strName);
        final Element element3 = document.createElement("Network");
        final Element element4 = document.createElement("ListenerInterfaces");
        final Enumeration<ListenerInterface> elements = this.m_listenerInterfaces.elements();
        while (elements.hasMoreElements()) {
            element4.appendChild(elements.nextElement().toXmlElement(document));
        }
        element3.appendChild(element4);
        final Element element5 = document.createElement("LastUpdate");
        XMLUtil.setValue(element5, this.m_creationTimeStamp);
        element.appendChild(element2);
        element.appendChild(this.m_infoserviceSoftware.toXmlElement(document));
        element.appendChild(element3);
        element.appendChild(element5);
        if (this.m_bPrimaryForwarderList) {
            element.appendChild(document.createElement("ForwarderList"));
        }
        if (this.m_userDefined) {
            element.appendChild(document.createElement("UserDefined"));
        }
        if (this.m_bPerfServerEnabled) {
            element.appendChild(document.createElement("PerformanceServer"));
        }
        try {
            this.m_signature = SignatureCreator.getInstance().getSignedXml(2, element);
            if (this.m_signature != null) {
                this.m_certPath = this.m_signature.getMultiCertPath();
            }
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.MISC, "Document could not be signed!");
        }
        return element;
    }
    
    public String getId() {
        return this.m_strInfoServiceId;
    }
    
    public Element getXmlStructure() {
        return this.m_xmlDescription;
    }
    
    public String getName() {
        return this.m_strName;
    }
    
    public boolean isVerified() {
        return this.m_signature != null && this.m_signature.isVerified();
    }
    
    public boolean isValid() {
        return this.m_certPath != null && this.m_certPath.isValid(new Date());
    }
    
    public boolean isPerfServerEnabled() {
        return this.m_bPerfServerEnabled;
    }
    
    public boolean checkId() {
        return this.m_userDefined || super.checkId();
    }
    
    public XMLSignature getSignature() {
        return this.m_signature;
    }
    
    public MultiCertPath getCertPath() {
        return this.m_certPath;
    }
    
    public long getLastUpdate() {
        return this.m_creationTimeStamp;
    }
    
    public long getVersionNumber() {
        return this.m_serial;
    }
    
    public boolean hasPrimaryForwarderList() {
        return this.m_bPrimaryForwarderList;
    }
    
    public Vector getListenerInterfaces() {
        final Vector<Object> vector = new Vector<Object>();
        final Enumeration<Object> elements = this.m_listenerInterfaces.elements();
        while (elements.hasMoreElements()) {
            vector.addElement(elements.nextElement());
        }
        return vector;
    }
    
    public ListenerInterface[] getListenerInterfacesArray() {
        final ListenerInterface[] array = new ListenerInterface[this.m_listenerInterfaces.size()];
        final Enumeration<ListenerInterface> elements = this.m_listenerInterfaces.elements();
        for (int i = 0; i < array.length; ++i) {
            array[i] = elements.nextElement();
        }
        return array;
    }
    
    public boolean isUserDefined() {
        return this.m_userDefined;
    }
    
    public void setUserDefined(final boolean userDefined) {
        this.m_userDefined = userDefined;
        if (this.m_userDefined) {
            this.m_infoserviceSoftware = new ServiceSoftware("unknown");
        }
        else {
            this.m_infoserviceSoftware = new ServiceSoftware("IS.08.044");
        }
        this.m_xmlDescription = this.generateXmlRepresentation();
    }
    
    public void markAsBootstrap() {
        this.m_bTemp = true;
    }
    
    public boolean isBootstrap() {
        return this.m_bTemp;
    }
    
    public String toString() {
        return this.m_strName;
    }
    
    public boolean equals(final Object o) {
        boolean equals = false;
        if (o != null && o instanceof InfoServiceDBEntry) {
            equals = this.getId().equals(((InfoServiceDBEntry)o).getId());
        }
        return equals;
    }
    
    public int hashCode() {
        return this.getId().hashCode();
    }
    
    public String getPostFile() {
        return "/infoservice";
    }
    
    public boolean isNeighbour() {
        return this.m_neighbour;
    }
    
    public void setNeighbour(final boolean neighbour) {
        this.m_neighbour = neighbour;
    }
    
    private HTTPConnectionDescriptor connectToInfoService(final HTTPConnectionDescriptor httpConnectionDescriptor, final ImmutableProxyInterface immutableProxyInterface, final int n) {
        int preferedListenerInterface = this.m_preferedListenerInterface;
        if (httpConnectionDescriptor != null) {
            preferedListenerInterface = (this.m_listenerInterfaces.indexOf(httpConnectionDescriptor.getTargetInterface()) + 1) % this.m_listenerInterfaces.size();
        }
        this.m_preferedListenerInterface = preferedListenerInterface;
        final ListenerInterface listenerInterface = this.m_listenerInterfaces.elementAt(preferedListenerInterface);
        final Vector vector = new Vector();
        try {
            final Vector<String> vector2 = new Vector<String>();
            vector2.addElement("java.version");
            vector2.addElement("java.vm.vendor");
            addPropertyHeader(vector2, vector);
            final Vector<String> vector3 = new Vector<String>();
            vector3.addElement("os.name");
            addPropertyHeader(vector3, vector);
            addPropertyHeader("anonlib.version", "00.12.003", vector);
            final String browserName = InfoServiceDBEntry.ms_browserIdentification.getBrowserName();
            if (browserName != null) {
                addPropertyHeader("browser.name", browserName, vector);
            }
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.NET, ex);
        }
        return new HTTPConnectionDescriptor(HTTPConnectionFactory.getInstance().createHTTPConnection(listenerInterface, immutableProxyInterface, n, true, vector), listenerInterface);
    }
    
    private static void addPropertyHeader(final String s, final Vector vector) {
        addPropertyHeader(s, null, vector);
    }
    
    private static void addPropertyHeader(final String s, final String s2, final Vector vector) {
        addPropertyHeader(Util.toVector(s), s2, vector);
    }
    
    private static void addPropertyHeader(final Vector vector, final Vector vector2) {
        addPropertyHeader(vector, null, vector2);
    }
    
    private static void addPropertyHeader(final Vector vector, String s, final Vector vector2) {
        if (vector == null || vector.size() == 0 || vector2 == null || vector.elementAt(0) == null) {
            return;
        }
        String s2 = null;
        if (s == null) {
            s = "";
            for (int i = 0; i < vector.size(); ++i) {
                final String s3 = vector.elementAt(i);
                if (s3 != null) {
                    if (s3.trim().length() != 0) {
                        try {
                            final String property = System.getProperty(s3);
                            if (property != null && !property.trim().equals("Sun Microsystems Inc.")) {
                                s += property.trim();
                            }
                        }
                        catch (Exception ex) {
                            continue;
                        }
                        if (i + 1 < vector.size()) {
                            s += " / ";
                        }
                        final String trim = Util.replaceAll(s3, ".", "-").trim();
                        if (s2 == null) {
                            s2 = trim;
                        }
                        else {
                            while (s2.length() > 0) {
                                if (trim.startsWith(s2)) {
                                    break;
                                }
                                final int lastIndex = s2.lastIndexOf("-");
                                if (lastIndex < 0) {
                                    s2 = "";
                                }
                                else {
                                    s2 = s2.substring(0, lastIndex);
                                }
                            }
                        }
                    }
                }
            }
            if (s.trim().length() == 0) {
                return;
            }
        }
        else {
            s2 = Util.replaceAll(vector.elementAt(0), ".", "-").trim();
        }
        if (s != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\r\n");
            if (!stringTokenizer.hasMoreTokens()) {
                return;
            }
            s = stringTokenizer.nextToken();
            if (s2.length() > 0) {
                s2 = "-" + s2;
            }
            vector2.addElement(new NVPair("statistics" + s2, s));
        }
    }
    
    private Document getXmlDocument(final HttpRequestStructure httpRequestStructure) throws Exception {
        return this.getXmlDocument(httpRequestStructure, 0);
    }
    
    private Document getXmlDocument(final HttpRequestStructure httpRequestStructure, final int n) throws Exception {
        return XMLUtil.toXMLDocument(this.doHttpRequest(httpRequestStructure, n));
    }
    
    private byte[] doHttpRequest(final HttpRequestStructure httpRequestStructure, final int n) throws Exception {
        int n2 = 0;
        HTTPConnectionDescriptor connectToInfoService = null;
        while (n2 < this.m_listenerInterfaces.size() && !Thread.currentThread().isInterrupted()) {
            ++n2;
            boolean b = false;
            for (int n3 = 0; n3 < 2 && !Thread.currentThread().isInterrupted(); ++n3) {
                if (n3 == 1) {
                    b = true;
                }
                final IProxyInterfaceGetter proxyInterface = InfoServiceDBEntry.ms_proxyInterface.getProxyInterface(b);
                if (proxyInterface != null) {
                    connectToInfoService = this.connectToInfoService(connectToInfoService, proxyInterface.getProxyInterface(), n);
                    if (connectToInfoService.getTargetInterface().isValid()) {
                        final ListenerInterface targetInterface = connectToInfoService.getTargetInterface();
                        final HTTPConnection connection = connectToInfoService.getConnection();
                        connection.setTimeout(InfoServiceDBEntry.m_getXmlConnectionTimeout);
                        final Vector<byte[]> vector = new Vector<byte[]>();
                        final Thread thread = new Thread(new Runnable() {
                            private final /* synthetic */ Vector val$headerContentEncoding = new Vector();
                            private final /* synthetic */ Vector val$responseStorage = vector;
                            
                            public void run() {
                                try {
                                    HTTPResponse httpResponse = null;
                                    if (httpRequestStructure.getRequestCommand() == 0) {
                                        LogHolder.log(7, LogType.NET, "Get: " + connection.getHost() + ":" + Integer.toString(connection.getPort()) + httpRequestStructure.getRequestFileName());
                                        httpResponse = connection.Get(httpRequestStructure.getRequestFileName());
                                    }
                                    else if (httpRequestStructure.getRequestCommand() == 1) {
                                        LogHolder.log(7, LogType.NET, "Post: " + connection.getHost() + ":" + Integer.toString(connection.getPort()) + httpRequestStructure.getRequestFileName());
                                        String string = "";
                                        if (httpRequestStructure.getRequestPostDocument() != null) {
                                            string = XMLUtil.toString(httpRequestStructure.getRequestPostDocument());
                                        }
                                        httpResponse = connection.Post(httpRequestStructure.getRequestFileName(), string);
                                    }
                                    else {
                                        LogHolder.log(3, LogType.NET, "Invalid HTTP command.");
                                    }
                                    if (httpResponse != null) {
                                        this.val$headerContentEncoding.addElement(httpResponse.getHeader("Content-Encoding"));
                                        final DataInputStream dataInputStream = new DataInputStream(httpResponse.getInputStream());
                                        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                        final byte[] array = new byte[1000];
                                        while (!Thread.interrupted()) {
                                            final int read = dataInputStream.read(array, 0, array.length);
                                            if (read == -1) {
                                                byteArrayOutputStream.flush();
                                                this.val$responseStorage.addElement(byteArrayOutputStream.toByteArray());
                                                return;
                                            }
                                            byteArrayOutputStream.write(array, 0, read);
                                        }
                                        throw new InterruptedIOException("Communication was interrupted.");
                                    }
                                    InfoServiceDBEntry.m_timeFirstJVMSocketError = Long.MAX_VALUE;
                                }
                                catch (Exception ex) {
                                    LogHolder.log(3, LogType.NET, "Connection to infoservice interface failed: " + connection.getHost() + ":" + Integer.toString(connection.getPort()) + httpRequestStructure.getRequestFileName(), ex);
                                    try {
                                        if (ex instanceof ConnectException) {
                                            targetInterface.blockInterface(InfoServiceDBEntry.m_getXmlConnectionTimeout * 5);
                                        }
                                        else if (Class.forName("java.net.SocketTimeoutException").isAssignableFrom(ex.getClass())) {
                                            targetInterface.blockInterface(InfoServiceDBEntry.m_getXmlConnectionTimeout * 5);
                                        }
                                    }
                                    catch (ClassNotFoundException ex2) {}
                                    if (ex instanceof SocketException && ex.getMessage() != null && ex.getMessage().toLowerCase().indexOf("getsockname") >= 0) {
                                        if (InfoServiceDBEntry.m_timeFirstJVMSocketError > System.currentTimeMillis()) {
                                            LogHolder.log(1, LogType.NET, "JVM error detected!");
                                            InfoServiceDBEntry.m_timeFirstJVMSocketError = System.currentTimeMillis();
                                        }
                                        else if (System.currentTimeMillis() - InfoServiceDBEntry.m_timeFirstJVMSocketError >= InfoServiceDBEntry.m_timeHandleAfterJVMSocketError) {
                                            LogHolder.log(1, LogType.NET, "JVM error detected! Further network access seems to be impossible...");
                                            if (InfoServiceDBEntry.m_threadHandleAfterJVMSocketError != null) {
                                                new Thread(InfoServiceDBEntry.m_threadHandleAfterJVMSocketError).start();
                                            }
                                        }
                                    }
                                    else {
                                        InfoServiceDBEntry.m_timeFirstJVMSocketError = Long.MAX_VALUE;
                                    }
                                }
                            }
                        });
                        thread.setName("InfoServiceDBEntry - Communication Thread");
                        thread.setDaemon(true);
                        thread.start();
                        try {
                            thread.join();
                            try {
                                if (vector.size() > 0) {
                                    return vector.firstElement();
                                }
                            }
                            catch (NoSuchElementException ex) {}
                        }
                        catch (InterruptedException ex2) {
                            LogHolder.log(6, LogType.NET, "Current operation was interrupted.");
                            Thread.currentThread().interrupt();
                            connection.stop();
                            thread.interrupt();
                        }
                    }
                }
            }
        }
        throw new Exception("Can't connect to infoservice " + this.getId() + ". Connections to all ListenerInterfaces failed.");
    }
    
    public MixCascade getMixCascadeInfo(final String s) throws Exception {
        final Element documentElement = this.getXmlDocument(HttpRequestStructure.createGetRequest("/cascadeinfo/" + s), 1).getDocumentElement();
        if (!SignatureVerifier.getInstance().verifyXml(documentElement, 1)) {
            throw new Exception("Cannot verify the signature for MixCascade entry: " + XMLUtil.toString(documentElement));
        }
        return new MixCascade(documentElement, Long.MAX_VALUE);
    }
    
    public Hashtable getMixCascades() throws Exception {
        return this.getMixCascades(true);
    }
    
    public Hashtable getPaymentInstances() throws Exception {
        return this.getPaymentInstances(true);
    }
    
    public PaymentInstanceDBEntry getPaymentInstance(final String s) throws Exception {
        final PaymentInstanceDBEntry paymentInstanceDBEntry = new PaymentInstanceDBEntry(this.getXmlDocument(HttpRequestStructure.createGetRequest("/paymentinstance/" + s)).getDocumentElement());
        if (!paymentInstanceDBEntry.isVerified()) {
            throw new SignatureException("Document could not be verified!");
        }
        return paymentInstanceDBEntry;
    }
    
    private Hashtable getEntries(final EntryGetter entryGetter) throws ExpiredSignatureException, SignatureException, Exception {
        final Document xmlDocument = this.getXmlDocument(HttpRequestStructure.createGetRequest(entryGetter.m_postFile), 1);
        if (xmlDocument == null) {
            throw new SignatureException("Document could not be verified!");
        }
        final XMLSignature verifiedXml = SignatureVerifier.getInstance().getVerifiedXml(xmlDocument.getDocumentElement(), 2);
        boolean b = false;
        if (verifiedXml == null || !verifiedXml.isVerified() || (b = !verifiedXml.getMultiCertPath().isValid(new Date()))) {
            if (b) {
                throw new ExpiredSignatureException("Document signature validity has expired for InfoService " + this.getId() + "!");
            }
            throw new SignatureException("Document could not be verified for InfoService " + this.getId() + "!");
        }
        else {
            final NodeList elementsByTagName = xmlDocument.getElementsByTagName(XMLUtil.getXmlElementContainerName(entryGetter.m_dbEntryClass));
            if (elementsByTagName.getLength() == 0) {
                throw new XMLParseException(XMLUtil.getXmlElementContainerName(entryGetter.m_dbEntryClass), "Error in XML structure.");
            }
            final NodeList elementsByTagName2 = ((Element)elementsByTagName.item(0)).getElementsByTagName(XMLUtil.getXmlElementName(entryGetter.m_dbEntryClass));
            final Hashtable<String, AbstractDistributableCertifiedDatabaseEntry> hashtable = new Hashtable<String, AbstractDistributableCertifiedDatabaseEntry>();
            for (int i = 0; i < elementsByTagName2.getLength(); ++i) {
                final Element element = (Element)elementsByTagName2.item(i);
                try {
                    IVerifyable verifyable;
                    if (entryGetter.m_dbEntryClass == ((InfoServiceDBEntry.class$anon$infoservice$InfoServiceDBEntry == null) ? (InfoServiceDBEntry.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : InfoServiceDBEntry.class$anon$infoservice$InfoServiceDBEntry)) {
                        verifyable = new InfoServiceDBEntry(element, entryGetter.m_bJAPContext ? Long.MAX_VALUE : 0L);
                    }
                    else if (entryGetter.m_dbEntryClass == ((InfoServiceDBEntry.class$anon$infoservice$MixCascade == null) ? (InfoServiceDBEntry.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : InfoServiceDBEntry.class$anon$infoservice$MixCascade)) {
                        if (entryGetter.m_bJAPContext) {
                            verifyable = new MixCascade(element, Long.MAX_VALUE);
                        }
                        else {
                            verifyable = new MixCascade(element);
                        }
                    }
                    else if (entryGetter.m_dbEntryClass == ((InfoServiceDBEntry.class$anon$pay$PaymentInstanceDBEntry == null) ? (InfoServiceDBEntry.class$anon$pay$PaymentInstanceDBEntry = class$("anon.pay.PaymentInstanceDBEntry")) : InfoServiceDBEntry.class$anon$pay$PaymentInstanceDBEntry)) {
                        if (entryGetter.m_bJAPContext) {
                            verifyable = new PaymentInstanceDBEntry(element, Long.MAX_VALUE);
                        }
                        else {
                            verifyable = new PaymentInstanceDBEntry(element);
                        }
                    }
                    else if (entryGetter.m_bJAPContext) {
                        verifyable = new MixInfo(element, Long.MAX_VALUE, false);
                    }
                    else {
                        verifyable = new MixInfo(element);
                    }
                    if (((AbstractDistributableCertifiedDatabaseEntry)verifyable).isVerified()) {
                        hashtable.put(((AbstractDatabaseEntry)verifyable).getId(), (AbstractDistributableCertifiedDatabaseEntry)verifyable);
                    }
                    else {
                        String s = XMLUtil.parseAttribute(element, "id", null);
                        if (s == null) {
                            s = XMLUtil.toString(element);
                        }
                        LogHolder.log(4, LogType.MISC, "Cannot verify the signature for " + ClassUtil.getShortClassName(entryGetter.m_dbEntryClass) + " entry: " + s);
                    }
                }
                catch (Exception ex) {
                    LogHolder.log(3, LogType.MISC, "Error in " + ClassUtil.getShortClassName(entryGetter.m_dbEntryClass) + " XML node!", ex);
                }
            }
            return hashtable;
        }
    }
    
    public Hashtable getInfoServices(final boolean bjapContext) throws Exception {
        final EntryGetter entryGetter = new EntryGetter();
        entryGetter.m_bJAPContext = bjapContext;
        entryGetter.m_dbEntryClass = ((InfoServiceDBEntry.class$anon$infoservice$InfoServiceDBEntry == null) ? (InfoServiceDBEntry.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : InfoServiceDBEntry.class$anon$infoservice$InfoServiceDBEntry);
        entryGetter.m_postFile = "/infoservices";
        return this.getEntries(entryGetter);
    }
    
    public Hashtable getMixCascades(final boolean bjapContext) throws Exception {
        final EntryGetter entryGetter = new EntryGetter();
        entryGetter.m_bJAPContext = bjapContext;
        entryGetter.m_dbEntryClass = ((InfoServiceDBEntry.class$anon$infoservice$MixCascade == null) ? (InfoServiceDBEntry.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : InfoServiceDBEntry.class$anon$infoservice$MixCascade);
        entryGetter.m_postFile = "/cascades";
        return this.getEntries(entryGetter);
    }
    
    public TermsAndConditionsTemplate getTCTemplate(final String s) throws Exception {
        final NodeList elementsByTagName = this.getXmlDocument(HttpRequestStructure.createGetRequest("/tctemplate/" + s), 1).getElementsByTagName(TermsAndConditionsTemplate.XML_ELEMENT_NAME);
        if (elementsByTagName.getLength() == 0) {
            throw new Exception("Error in XML structure for mix with ID " + s);
        }
        final Element element = (Element)elementsByTagName.item(0);
        final TermsAndConditionsTemplate termsAndConditionsTemplate = new TermsAndConditionsTemplate(element);
        if (!termsAndConditionsTemplate.isVerified()) {
            throw new Exception("Cannot verify the signature for Mix entry: " + XMLUtil.toString(element));
        }
        return termsAndConditionsTemplate;
    }
    
    public Hashtable getPaymentInstances(final boolean bjapContext) throws Exception {
        final EntryGetter entryGetter = new EntryGetter();
        entryGetter.m_bJAPContext = bjapContext;
        entryGetter.m_dbEntryClass = ((InfoServiceDBEntry.class$anon$pay$PaymentInstanceDBEntry == null) ? (InfoServiceDBEntry.class$anon$pay$PaymentInstanceDBEntry = class$("anon.pay.PaymentInstanceDBEntry")) : InfoServiceDBEntry.class$anon$pay$PaymentInstanceDBEntry);
        entryGetter.m_postFile = "/paymentinstances";
        return this.getEntries(entryGetter);
    }
    
    public Hashtable getMixes(final boolean bjapContext) throws Exception {
        final EntryGetter entryGetter = new EntryGetter();
        entryGetter.m_bJAPContext = bjapContext;
        entryGetter.m_dbEntryClass = ((InfoServiceDBEntry.class$anon$infoservice$MixInfo == null) ? (InfoServiceDBEntry.class$anon$infoservice$MixInfo = class$("anon.infoservice.MixInfo")) : InfoServiceDBEntry.class$anon$infoservice$MixInfo);
        entryGetter.m_postFile = "/mixes";
        return this.getEntries(entryGetter);
    }
    
    public Hashtable getInfoServices() throws Exception {
        return this.getInfoServices(true);
    }
    
    public Hashtable getMixCascadeSerials() throws Exception {
        final Document xmlDocument = this.getXmlDocument(HttpRequestStructure.createGetRequest("/cascadeserials"), 1);
        if (!SignatureVerifier.getInstance().verifyXml(xmlDocument, 2)) {
            throw new SignatureException("Cannot verify the signature: " + XMLUtil.toString(xmlDocument));
        }
        return new Serials((InfoServiceDBEntry.class$anon$infoservice$MixCascade == null) ? (InfoServiceDBEntry.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : InfoServiceDBEntry.class$anon$infoservice$MixCascade).parse(xmlDocument.getDocumentElement());
    }
    
    public Hashtable getInfoServiceSerials() throws Exception {
        final Document xmlDocument = this.getXmlDocument(HttpRequestStructure.createGetRequest("/infoserviceserials"), 1);
        if (!SignatureVerifier.getInstance().verifyXml(xmlDocument, 2)) {
            throw new SignatureException("Cannot verify the signature: " + XMLUtil.toString(xmlDocument));
        }
        return new Serials((InfoServiceDBEntry.class$anon$infoservice$InfoServiceDBEntry == null) ? (InfoServiceDBEntry.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : InfoServiceDBEntry.class$anon$infoservice$InfoServiceDBEntry).parse(xmlDocument.getDocumentElement());
    }
    
    public MixInfo getMixInfo(final String s) throws Exception {
        final NodeList elementsByTagName = this.getXmlDocument(HttpRequestStructure.createGetRequest("/mixinfo/" + s)).getElementsByTagName("Mix");
        if (elementsByTagName.getLength() == 0) {
            throw new Exception("Error in XML structure for mix with ID " + s);
        }
        final Element element = (Element)elementsByTagName.item(0);
        final MixInfo mixInfo = new MixInfo(element, Long.MAX_VALUE, false);
        if (!mixInfo.isVerified()) {
            throw new Exception("Cannot verify the signature for Mix entry: " + XMLUtil.toString(element));
        }
        return mixInfo;
    }
    
    public StatusInfo getStatusInfo(final MixCascade mixCascade) throws Exception {
        return this.getStatusInfo(mixCascade, -1L);
    }
    
    public Object getExitAddresses() throws Exception {
        boolean b = false;
        final Document xmlDocument = this.getXmlDocument(HttpRequestStructure.createGetRequest("/exitaddresses"));
        if (xmlDocument == null) {
            return null;
        }
        final Element documentElement = xmlDocument.getDocumentElement();
        if (documentElement == null) {
            return null;
        }
        for (Node node = XMLUtil.getFirstChildByName(documentElement, "ExitAddresses"); node != null; node = XMLUtil.getNextSiblingByName(node, "ExitAddresses")) {
            final String attribute = XMLUtil.parseAttribute(node, "id", "");
            if (!attribute.equals("")) {
                for (Node node2 = XMLUtil.getFirstChildByName(node, "ExitAddress"); node2 != null; node2 = XMLUtil.getNextSiblingByName(node2, "ExitAddress")) {
                    final String value = XMLUtil.parseValue(node2, "");
                    if (!value.equals("")) {
                        MixCascadeExitAddresses.addInetAddress(attribute, InetAddress.getByName(value));
                        b = true;
                    }
                }
            }
        }
        if (b) {
            return new Object();
        }
        return null;
    }
    
    public StatusInfo getStatusInfo(final MixCascade mixCascade, final long n) throws Exception {
        final Document xmlDocument = this.getXmlDocument(HttpRequestStructure.createGetRequest("/mixcascadestatus/" + mixCascade.getId()));
        final NodeList elementsByTagName = xmlDocument.getElementsByTagName("MixCascadeStatus");
        if (elementsByTagName.getLength() == 0) {
            if (XMLUtil.getFirstChildByName(xmlDocument, "<HTML>") == null) {
                throw new Exception("Error in XML structure for cascade with ID " + mixCascade.getId());
            }
            throw new Exception("No status data found for cascade with ID " + mixCascade.getId());
        }
        else {
            final Element element = (Element)elementsByTagName.item(0);
            StatusInfo statusInfo;
            if (n > 0L) {
                statusInfo = new StatusInfo(element, n);
            }
            else {
                statusInfo = new StatusInfo(element);
            }
            if (!statusInfo.isVerified()) {
                throw new Exception("Cannot verify the signature for MixCascadeStatus entry: " + XMLUtil.toString(element));
            }
            return statusInfo;
        }
    }
    
    public JAPMinVersion getNewVersionNumber() throws Exception {
        final Element element = (Element)XMLUtil.getFirstChildByName(this.getXmlDocument(HttpRequestStructure.createGetRequest("/currentjapversion")), JAPMinVersion.getXmlElementName());
        if (!SignatureVerifier.getInstance().verifyXml(element, 3)) {
            throw new Exception("Cannot verify the signature for JAPMinVersion entry: " + XMLUtil.toString(element));
        }
        return new JAPMinVersion(element);
    }
    
    private Hashtable getUpdateEntries(final Class clazz, final boolean b) throws Exception {
        Document document;
        if (b) {
            document = this.getXmlDocument(HttpRequestStructure.createGetRequest(AbstractDistributableDatabaseEntry.getHttpSerialsRequestString(clazz)));
        }
        else {
            document = this.getXmlDocument(HttpRequestStructure.createGetRequest(AbstractDistributableDatabaseEntry.getHttpRequestString(clazz)));
        }
        if (!SignatureVerifier.getInstance().verifyXml(document.getDocumentElement(), 2)) {
            LogHolder.log(6, LogType.MISC, "Cannot verify the signature for " + clazz.getName() + " document: " + XMLUtil.toString(document));
            return new Hashtable();
        }
        if (b) {
            return new Serials(clazz).parse(document.getDocumentElement());
        }
        final Node firstChildByName = XMLUtil.getFirstChildByName(document, XMLUtil.getXmlElementContainerName(clazz));
        if (firstChildByName == null || !(firstChildByName instanceof Element)) {
            throw new XMLParseException(XMLUtil.getXmlElementContainerName(clazz), "Node missing!");
        }
        final NodeList elementsByTagName = ((Element)firstChildByName).getElementsByTagName(XMLUtil.getXmlElementName(clazz));
        final Hashtable<String, AbstractDistributableDatabaseEntry> hashtable = new Hashtable<String, AbstractDistributableDatabaseEntry>();
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            final Element element = (Element)elementsByTagName.item(i);
            if (SignatureVerifier.getInstance().verifyXml(element, 3)) {
                try {
                    final AbstractDistributableDatabaseEntry abstractDistributableDatabaseEntry = clazz.getConstructor((InfoServiceDBEntry.class$org$w3c$dom$Element == null) ? (InfoServiceDBEntry.class$org$w3c$dom$Element = class$("org.w3c.dom.Element")) : InfoServiceDBEntry.class$org$w3c$dom$Element).newInstance(element);
                    hashtable.put(abstractDistributableDatabaseEntry.getId(), abstractDistributableDatabaseEntry);
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.MISC, "Error in " + clazz.getName() + " XML node.");
                }
            }
            else {
                LogHolder.log(6, LogType.MISC, "Cannot verify the signature for " + clazz.getName() + " entry: " + XMLUtil.toString(element));
            }
        }
        return hashtable;
    }
    
    public Hashtable getMessages() throws Exception {
        return this.getUpdateEntries((InfoServiceDBEntry.class$anon$infoservice$MessageDBEntry == null) ? (InfoServiceDBEntry.class$anon$infoservice$MessageDBEntry = class$("anon.infoservice.MessageDBEntry")) : InfoServiceDBEntry.class$anon$infoservice$MessageDBEntry, false);
    }
    
    public Hashtable getMessageSerials() throws Exception {
        return this.getUpdateEntries((InfoServiceDBEntry.class$anon$infoservice$MessageDBEntry == null) ? (InfoServiceDBEntry.class$anon$infoservice$MessageDBEntry = class$("anon.infoservice.MessageDBEntry")) : InfoServiceDBEntry.class$anon$infoservice$MessageDBEntry, true);
    }
    
    public PerformanceInfo getPerformanceInfo() throws Exception {
        final PerformanceInfo performanceInfo = new PerformanceInfo((Element)XMLUtil.getFirstChildByName(this.getXmlDocument(HttpRequestStructure.createGetRequest("/performanceinfo"), 0), "PerformanceInfo"));
        if (!performanceInfo.isVerified()) {
            throw new SignatureException("Document could not be verified!");
        }
        return performanceInfo;
    }
    
    public Hashtable getLatestJava() throws Exception {
        return this.getUpdateEntries((InfoServiceDBEntry.class$anon$infoservice$JavaVersionDBEntry == null) ? (InfoServiceDBEntry.class$anon$infoservice$JavaVersionDBEntry = class$("anon.infoservice.JavaVersionDBEntry")) : InfoServiceDBEntry.class$anon$infoservice$JavaVersionDBEntry, false);
    }
    
    public Hashtable getLatestJavaSerials() throws Exception {
        return this.getUpdateEntries((InfoServiceDBEntry.class$anon$infoservice$JavaVersionDBEntry == null) ? (InfoServiceDBEntry.class$anon$infoservice$JavaVersionDBEntry = class$("anon.infoservice.JavaVersionDBEntry")) : InfoServiceDBEntry.class$anon$infoservice$JavaVersionDBEntry, true);
    }
    
    public JAPVersionInfo getJAPVersionInfo(final int n) throws Exception {
        Document document;
        if (n == 1) {
            document = this.getXmlDocument(HttpRequestStructure.createGetRequest("/japRelease.jnlp"));
        }
        else {
            if (n != 2) {
                throw new Exception("InfoServiceDBEntry: getJAPVersionInfo: Invalid version info requested.");
            }
            document = this.getXmlDocument(HttpRequestStructure.createGetRequest("/japDevelopment.jnlp"));
        }
        final Element element = (Element)XMLUtil.getFirstChildByName(document, JAPVersionInfo.getXmlElementName());
        final XMLSignature verifiedXml = SignatureVerifier.getInstance().getVerifiedXml(element, 3);
        if (!verifiedXml.isVerified()) {
            throw new Exception("Cannot verify the signature for JAPVersionInfo entry: " + XMLUtil.toString(element));
        }
        final JAPVersionInfo japVersionInfo = new JAPVersionInfo(element, n);
        if (!verifiedXml.getMultiCertPath().isValid(new Date())) {
            LogHolder.log(4, LogType.MISC, "Found an expired JAP/JonDo update entry for " + japVersionInfo.getPostFile() + "! The update verification certificate might become invalid soon.");
        }
        return japVersionInfo;
    }
    
    public byte[] getTorNodesList() throws Exception {
        byte[] array = null;
        try {
            array = this.doHttpRequest(HttpRequestStructure.createGetRequest("/tornodes"), 1);
        }
        catch (Exception ex) {}
        if (array == null) {
            try {
                array = this.doHttpRequest(HttpRequestStructure.createGetRequest("/tornodes"), 0);
            }
            catch (Exception ex2) {}
        }
        if (array == null) {
            throw new Exception("Error while parsing the TOR nodes list XML structure.");
        }
        return array;
    }
    
    public byte[] getMixminionNodesList() throws Exception {
        byte[] doHttpRequest = null;
        try {
            doHttpRequest = this.doHttpRequest(HttpRequestStructure.createGetRequest("/mixminionnodes"), 1);
        }
        catch (Exception ex) {}
        if (doHttpRequest == null) {
            throw new Exception("Error while parsing the TOR nodes list XML structure.");
        }
        return doHttpRequest;
    }
    
    public Element postNewForwarder(final Element element) throws Exception {
        if (!this.hasPrimaryForwarderList()) {
            throw new Exception("InfoService: postNewForwarder: The InfoService " + this.getName() + " has no forwarder list.");
        }
        final NodeList elementsByTagName = this.getXmlDocument(HttpRequestStructure.createPostRequest("/addforwarder", element.getOwnerDocument())).getElementsByTagName("JapForwarder");
        if (elementsByTagName.getLength() == 0) {
            throw new Exception("InfoService: postNewForwarder: Error in XML structure.");
        }
        return (Element)elementsByTagName.item(0);
    }
    
    public Element postRenewForwarder(final Element element) throws Exception {
        if (!this.hasPrimaryForwarderList()) {
            throw new Exception("InfoService: postRenewForwarder: The InfoService " + this.getName() + " has no forwarder list.");
        }
        final NodeList elementsByTagName = this.getXmlDocument(HttpRequestStructure.createPostRequest("/renewforwarder", element.getOwnerDocument())).getElementsByTagName("JapForwarder");
        if (elementsByTagName.getLength() == 0) {
            throw new Exception("InfoService: postRenewForwarder: Error in XML structure.");
        }
        return (Element)elementsByTagName.item(0);
    }
    
    public Element getForwarder() throws Exception {
        final NodeList elementsByTagName = this.getXmlDocument(HttpRequestStructure.createGetRequest("/getforwarder")).getElementsByTagName("JapForwarder");
        if (elementsByTagName.getLength() == 0) {
            throw new Exception("InfoService: getForwarder: Error in XML structure.");
        }
        final Element element = (Element)elementsByTagName.item(0);
        final NodeList elementsByTagName2 = element.getElementsByTagName("ErrorInformation");
        if (elementsByTagName2.getLength() > 0) {
            final Element element2 = (Element)elementsByTagName2.item(0);
            throw new Exception("InfoService: getForwarder: The infoservice returned error " + element2.getAttribute("code") + ": " + element2.getFirstChild().getNodeValue());
        }
        return element;
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
        InfoServiceDBEntry.m_getXmlConnectionTimeout = 20000;
        InfoServiceDBEntry.m_timeFirstJVMSocketError = Long.MAX_VALUE;
        InfoServiceDBEntry.m_timeHandleAfterJVMSocketError = Long.MAX_VALUE;
        InfoServiceDBEntry.ms_proxyInterface = new IMutableProxyInterface.DummyMutableProxyInterface();
        InfoServiceDBEntry.ms_browserIdentification = new IBrowserIdentification() {
            public String getBrowserName() {
                return null;
            }
        };
    }
    
    private static class EntryGetter
    {
        String m_postFile;
        Class m_dbEntryClass;
        boolean m_bJAPContext;
    }
}
