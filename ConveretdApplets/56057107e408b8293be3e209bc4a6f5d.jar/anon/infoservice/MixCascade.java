// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.crypto.CertPath;
import java.util.Enumeration;
import org.w3c.dom.Document;
import anon.crypto.X509DistinguishedName;
import anon.util.CountryMapper;
import java.util.Date;
import anon.util.Util;
import java.util.StringTokenizer;
import org.w3c.dom.NodeList;
import java.net.InetAddress;
import logging.LogHolder;
import logging.LogType;
import anon.crypto.SignatureVerifier;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.util.ZLibTools;
import anon.util.XMLParseException;
import java.util.Hashtable;
import anon.crypto.MultiCertPath;
import anon.crypto.XMLSignature;
import org.w3c.dom.Element;
import java.util.Vector;
import anon.crypto.IVerifyable;
import anon.AnonServerDescription;

public class MixCascade extends AbstractDistributableCertifiedDatabaseEntry implements AnonServerDescription, IVerifyable, IServiceContextContainer, Database.IWebInfo
{
    public static final String SUPPORTED_PAYMENT_PROTOCOL_VERSION = "2.0";
    public static final String TC_REQUIRED_VERSION_SUFFIX = "tc";
    public static final int DISTRIBUTION_MIN = 0;
    public static final int DISTRIBUTION_MAX = 6;
    public static final String XML_ELEMENT_NAME = "MixCascade";
    public static final String XML_ELEMENT_CONTAINER_NAME = "MixCascades";
    private static final String XML_ATTR_USER_DEFINED = "userDefined";
    private static final String XML_ATTR_STUDY = "study";
    private static final String XML_ATTR_MAX_USERS = "maxUsers";
    private static final String XML_ATTR_PAYMENT = "payment";
    public static final String XML_ELEMENT_WEBINFO_CONTAINER = "CascadeWebInfos";
    public static final String XML_ELEMENT_WEBINFO = "CascadeWebInfo";
    public static final String XML_ELEMENT_WEBINFO_CASCADE_NAME = "CascadeName";
    public static final String XML_ELEMENT_WEBINFO_NAME = "Name";
    public static final String XML_ELEMENT_WEBINFO_COMPOSED_NAME = "ComposedName";
    public static final String XML_ELEMENT_WEBINFO_CURR_USERS = "CurrentUsers";
    public static final String XML_ATTR_WEBINFO_MIX_COUNTRY = "mixCountry";
    public static final String XML_ATTR_WEBINFO_MIX_POSITION = "mixPosition";
    public static final String XML_ATTR_WEBINFO_OP_COUNTRY = "operatorCountry";
    public static final String INFOSERVICE_COMMAND_WEBINFOS = "/cascadewebinfos";
    public static final String INFOSERVICE_COMMAND_WEBINFO = "/cascadewebinfo/";
    public static final int MAX_CASCADE_NAME_LENGTH = 35;
    private final Object CACHE_HOSTS_PORT;
    private boolean m_bDefaultVerified;
    private boolean m_bImplicitTrust;
    private boolean m_bSock5Support;
    private DataRetentionInformation m_dataRetentionInfo;
    private String m_mixCascadeId;
    private long m_lastUpdate;
    private String m_strName;
    private final Object SYNC_NAME;
    private Vector m_decomposedCascadeName;
    private Vector m_listenerInterfaces;
    private Vector m_mixIds;
    private String m_strMixIds;
    private String m_piid;
    private MixInfo[] m_mixInfos;
    private String m_strMixNames;
    private int m_nrPriceCerts;
    private Vector m_mixNodes;
    private long m_serial;
    private Element m_xmlStructure;
    private byte[] m_compressedXmlStructure;
    private XMLSignature m_signature;
    private MultiCertPath m_certPath;
    private int m_nrCountries;
    private int m_nrOperators;
    private int m_nrOperatorsCountForDistribution;
    private int m_nrOperatorsShown;
    private int m_distributionPoints;
    private boolean[] m_mixCertVerifiedAndValid;
    private Object SYNC_OPERATORS_AND_COUNTRIES;
    private volatile boolean termsAndConditionsConfirmationRequired;
    private boolean m_userDefined;
    private boolean m_bStudy;
    private int m_maxUsers;
    private String m_strPorts;
    private String m_strHosts;
    private boolean m_isPayment;
    private long m_prepaidInterval;
    private String m_mixProtocolVersion;
    private String m_paymentProtocolVersion;
    private Hashtable m_priceCertificateHashes;
    private Vector m_priceCertificates;
    private boolean m_bFromCascade;
    private String m_context;
    static /* synthetic */ Class class$anon$infoservice$StatusInfo;
    static /* synthetic */ Class class$anon$infoservice$PerformanceEntry;
    
    public MixCascade(final byte[] array) throws XMLParseException {
        this(array, null, 0L, null);
    }
    
    public MixCascade(final Element element) throws XMLParseException {
        this(null, element, 0L, null);
    }
    
    public MixCascade(final Element element, final long n) throws XMLParseException {
        this(null, element, n, null);
    }
    
    public MixCascade(final Element element, final long n, final String s) throws XMLParseException {
        this(null, element, n, s);
    }
    
    private MixCascade(final byte[] compressedXmlStructure, Element xmlStructure, final long n, final String mixCascadeId) throws XMLParseException {
        super((n <= 0L) ? (System.currentTimeMillis() + 900000L) : n);
        this.CACHE_HOSTS_PORT = new Object();
        this.m_bDefaultVerified = false;
        this.m_bImplicitTrust = false;
        this.m_bSock5Support = false;
        this.SYNC_NAME = new Object();
        this.m_piid = "";
        this.m_nrPriceCerts = 0;
        this.m_nrCountries = 0;
        this.m_nrOperators = 0;
        this.m_nrOperatorsCountForDistribution = 0;
        this.m_nrOperatorsShown = 0;
        this.m_distributionPoints = 0;
        this.SYNC_OPERATORS_AND_COUNTRIES = new Object();
        this.termsAndConditionsConfirmationRequired = false;
        this.m_bStudy = false;
        this.m_maxUsers = 0;
        this.m_prepaidInterval = 3000000L;
        this.m_priceCertificateHashes = new Hashtable();
        this.m_priceCertificates = new Vector();
        this.m_bFromCascade = (mixCascadeId != null);
        if (xmlStructure == null && compressedXmlStructure == null) {
            throw new XMLParseException("##__null__##");
        }
        if (xmlStructure == null) {
            xmlStructure = (Element)XMLUtil.getFirstChildByName(XMLUtil.toXMLDocument(ZLibTools.decompress(compressedXmlStructure)), "MixCascade");
        }
        try {
            this.m_signature = SignatureVerifier.getInstance().getVerifiedXml(xmlStructure, 1);
            if (this.m_signature != null) {
                this.m_certPath = this.m_signature.getMultiCertPath();
            }
            else {
                LogHolder.log(7, LogType.MISC, "No signature node found while looking for MixCascade certificate.");
            }
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.MISC, "Error while looking for appended certificates in the MixCascade structure: " + ex.toString());
        }
        this.m_userDefined = XMLUtil.parseAttribute(xmlStructure, "userDefined", false);
        this.m_bStudy = XMLUtil.parseAttribute(xmlStructure, "study", false);
        this.m_maxUsers = XMLUtil.parseAttribute(xmlStructure, "maxUsers", 0);
        this.m_maxUsers = Math.min(this.m_maxUsers, 9999);
        if (xmlStructure == null || !xmlStructure.getNodeName().equals("MixCascade")) {
            throw new XMLParseException("MixCascade");
        }
        this.m_mixCascadeId = XMLUtil.parseAttribute(xmlStructure, "id", null);
        if (this.m_mixCascadeId == null) {
            this.m_mixCascadeId = XMLUtil.parseAttribute(XMLUtil.getFirstChildByName(XMLUtil.getFirstChildByName(xmlStructure, "Mixes"), "Mix"), "id", mixCascadeId);
        }
        if (!this.checkId()) {
            throw new XMLParseException("##__root__##", "Malformed Mix-Cascade ID: " + this.m_mixCascadeId);
        }
        this.m_mixProtocolVersion = XMLUtil.parseValue(XMLUtil.getFirstChildByName(xmlStructure, "MixProtocolVersion"), null);
        if (this.m_mixProtocolVersion != null) {
            this.m_mixProtocolVersion = this.m_mixProtocolVersion.trim();
            if (this.m_mixProtocolVersion.endsWith("tc")) {
                this.m_mixProtocolVersion = this.m_mixProtocolVersion.substring(0, this.m_mixProtocolVersion.length() - "tc".length());
                this.termsAndConditionsConfirmationRequired = true;
            }
        }
        final Node firstChildByName = XMLUtil.getFirstChildByName(xmlStructure, "Payment");
        this.m_isPayment = XMLUtil.parseAttribute(firstChildByName, "required", false);
        this.m_paymentProtocolVersion = XMLUtil.parseAttribute(firstChildByName, "version", "2.0");
        this.m_prepaidInterval = XMLUtil.parseAttribute(firstChildByName, "prepaidInterval", 3000001L);
        this.m_piid = XMLUtil.parseAttribute(firstChildByName, "piid", "");
        this.m_context = XMLUtil.parseAttribute(xmlStructure, "context", null);
        if (this.m_context == null || this.m_context.equals("de.jondos.jondonym")) {
            if (this.m_isPayment) {
                this.m_context = "jondonym.premium";
            }
            else {
                this.m_context = "jondonym";
            }
        }
        if (!this.m_bFromCascade) {
            final NodeList elementsByTagName = xmlStructure.getElementsByTagName("Network");
            if (elementsByTagName.getLength() == 0) {
                throw new XMLParseException("Network");
            }
            final NodeList elementsByTagName2 = ((Element)elementsByTagName.item(0)).getElementsByTagName("ListenerInterfaces");
            if (elementsByTagName2.getLength() == 0) {
                throw new XMLParseException("ListenerInterfaces");
            }
            final NodeList elementsByTagName3 = ((Element)elementsByTagName2.item(0)).getElementsByTagName("ListenerInterface");
            if (elementsByTagName3.getLength() == 0) {
                throw new XMLParseException("ListenerInterface");
            }
            this.m_listenerInterfaces = new Vector();
            for (int i = 0; i < elementsByTagName3.getLength(); ++i) {
                this.m_listenerInterfaces.addElement(new ListenerInterface((Element)elementsByTagName3.item(i)));
            }
        }
        final NodeList elementsByTagName4 = xmlStructure.getElementsByTagName("Mixes");
        if (elementsByTagName4.getLength() == 0) {
            throw new XMLParseException("Mixes");
        }
        final Element element = (Element)elementsByTagName4.item(0);
        final int int1 = Integer.parseInt(element.getAttribute("count"));
        final NodeList elementsByTagName5 = element.getElementsByTagName("Mix");
        if (elementsByTagName5.getLength() == 0 || int1 != elementsByTagName5.getLength()) {
            throw new XMLParseException("Mix");
        }
        this.m_mixIds = new Vector();
        this.m_mixNodes = new Vector();
        for (int j = 0; j < elementsByTagName5.getLength(); ++j) {
            final Element element2 = (Element)elementsByTagName5.item(j);
            this.m_mixIds.addElement(element2.getAttribute("id"));
            if (j == 0 && !this.isUserDefined() && !this.m_mixIds.lastElement().equals(this.m_mixCascadeId)) {
                throw new XMLParseException("##__root__##", "Cascade ID not ID of first mix: " + this.m_mixCascadeId);
            }
            this.m_mixNodes.addElement(element2);
        }
        this.m_mixInfos = new MixInfo[elementsByTagName5.getLength()];
        int n2 = 0;
        for (int k = 0; k < elementsByTagName5.getLength(); ++k) {
            try {
                this.m_mixInfos[k] = new MixInfo((Element)elementsByTagName5.item(k), n, true);
                if (k + 1 == elementsByTagName5.getLength()) {
                    this.m_bSock5Support = this.m_mixInfos[k].isSocks5Supported();
                }
                if (this.m_mixInfos[k].getPriceCertificate() != null) {
                    if (k == 0) {
                        this.m_piid = this.m_mixInfos[k].getPriceCertificate().getBiID();
                    }
                    if (k == 0 && this.m_prepaidInterval > 3000000L) {
                        this.m_prepaidInterval = this.m_mixInfos[k].getPrepaidInterval();
                    }
                    this.m_priceCertificates.addElement(this.m_mixInfos[k].getPriceCertificate());
                    this.m_priceCertificateHashes.put(new MixPosition(k, this.m_mixInfos[k].getId()), this.m_mixInfos[k].getPriceCertificate().getHashValue());
                    ++this.m_nrPriceCerts;
                }
                if (this.m_mixInfos[k].getDataRetentionInformation() != null) {
                    ++n2;
                }
            }
            catch (XMLParseException ex2) {
                this.m_mixInfos[k] = null;
            }
        }
        this.m_strName = XMLUtil.parseValue(XMLUtil.getFirstChildByName(xmlStructure, "Name"), null);
        if (!this.m_bFromCascade) {
            this.getDecomposedCascadeName();
        }
        if (n == 0L && this.m_mixInfos.length > 0) {
            Vector vector = this.m_mixInfos[this.m_mixInfos.length - 1].getVisibleAddresses();
            if (vector.size() == 0) {
                vector = this.m_mixInfos[this.m_mixInfos.length - 1].getListenerAddresses();
            }
            for (int l = 0; l < vector.size(); ++l) {
                MixCascadeExitAddresses.addInetAddress(this.getId(), vector.elementAt(l));
            }
        }
        if (this.m_mixCascadeId == null) {
            this.m_mixCascadeId = this.m_mixIds.elementAt(0);
        }
        final Node firstChildByName2 = XMLUtil.getFirstChildByName(xmlStructure, "LastUpdate");
        this.m_lastUpdate = XMLUtil.parseValue(firstChildByName2, System.currentTimeMillis() - 900000L);
        if (!this.m_bFromCascade) {
            if (firstChildByName2 == null) {
                throw new XMLParseException("LastUpdate");
            }
            this.m_serial = XMLUtil.parseAttribute(xmlStructure, "serial", this.m_lastUpdate);
        }
        else {
            this.m_serial = XMLUtil.parseAttribute(xmlStructure, "serial", Long.MIN_VALUE);
        }
        if (compressedXmlStructure != null) {
            this.m_compressedXmlStructure = compressedXmlStructure;
        }
        else {
            this.m_compressedXmlStructure = ZLibTools.compress(XMLSignature.toCanonical(xmlStructure));
        }
        this.m_xmlStructure = xmlStructure;
        if (this.m_bFromCascade && mixCascadeId.trim().length() > 0) {
            this.m_mixCascadeId = mixCascadeId;
        }
        this.createMixIDString();
        this.calculateOperatorsAndCountries();
        if (this.isPayment()) {
            if (this.m_piid == null) {
                throw new XMLParseException("Payment instance id is null on paid cascade!");
            }
            if (this.m_prepaidInterval > 3000000L) {
                LogHolder.log(4, LogType.PAY, "Prepaid interval of cascade " + this.getId() + "is too high: " + this.m_prepaidInterval);
            }
            else if (this.m_prepaidInterval < 5000L) {
                LogHolder.log(4, LogType.PAY, "Prepaid interval of cascade " + this.getId() + "is too low: " + this.m_prepaidInterval);
            }
        }
        this.m_prepaidInterval = Math.min(this.m_prepaidInterval, 3000000L);
        this.m_prepaidInterval = Math.max(this.m_prepaidInterval, 5000L);
        this.m_dataRetentionInfo = DataRetentionInformation.getCascadeDataRetentionInformation(this);
    }
    
    public MixCascade(final String s, final int n) throws Exception {
        this(null, null, s, n);
    }
    
    public MixCascade(final String s, final String s2, final String s3, final int n) throws Exception {
        this(s, s2, new ListenerInterface(s3, n, 2).toVector());
    }
    
    public MixCascade(final String s, final String s2, final Vector vector) throws Exception {
        this(s, s2, null, vector);
    }
    
    public MixCascade(final String s, final String s2, final Vector vector, final Vector vector2) throws Exception {
        this(s, s2, vector, vector2, Long.MAX_VALUE);
    }
    
    public MixCascade(final String strName, final String mixCascadeId, final Vector vector, final Vector listenerInterfaces, final long n) throws Exception {
        super(n);
        this.CACHE_HOSTS_PORT = new Object();
        this.m_bDefaultVerified = false;
        this.m_bImplicitTrust = false;
        this.m_bSock5Support = false;
        this.SYNC_NAME = new Object();
        this.m_piid = "";
        this.m_nrPriceCerts = 0;
        this.m_nrCountries = 0;
        this.m_nrOperators = 0;
        this.m_nrOperatorsCountForDistribution = 0;
        this.m_nrOperatorsShown = 0;
        this.m_distributionPoints = 0;
        this.SYNC_OPERATORS_AND_COUNTRIES = new Object();
        this.termsAndConditionsConfirmationRequired = false;
        this.m_bStudy = false;
        this.m_maxUsers = 0;
        this.m_prepaidInterval = 3000000L;
        this.m_priceCertificateHashes = new Hashtable();
        this.m_priceCertificates = new Vector();
        final ListenerInterface listenerInterface = listenerInterfaces.elementAt(0);
        final String host = listenerInterface.getHost();
        final String string = Integer.toString(listenerInterface.getPort());
        if (mixCascadeId == null || mixCascadeId.length() == 0) {
            this.m_mixCascadeId = "(user)" + host + "%3A" + string;
        }
        else {
            this.m_mixCascadeId = mixCascadeId;
        }
        if (strName != null) {
            this.m_strName = strName;
        }
        else {
            this.m_strName = host + ":" + string;
        }
        this.m_listenerInterfaces = listenerInterfaces;
        this.m_lastUpdate = System.currentTimeMillis();
        this.m_mixNodes = new Vector();
        if (vector == null || vector.size() == 0) {
            (this.m_mixIds = new Vector()).addElement(this.m_mixCascadeId);
        }
        else {
            this.m_mixIds = (Vector)vector.clone();
        }
        this.m_mixInfos = new MixInfo[this.m_mixIds.size()];
        for (int i = 0; i < this.m_mixInfos.length; ++i) {
            this.m_mixInfos[i] = null;
        }
        this.m_userDefined = true;
        this.m_bDefaultVerified = true;
        this.m_xmlStructure = this.generateXmlRepresentation();
        this.m_compressedXmlStructure = ZLibTools.compress(XMLSignature.toCanonical(this.m_xmlStructure));
        this.createMixIDString();
        this.calculateOperatorsAndCountries();
    }
    
    public boolean isPersistanceDeletionAllowed() {
        return XMLUtil.getStorageMode() == 2;
    }
    
    public void deletePersistence() {
        if (this.isPersistanceDeletionAllowed()) {
            this.m_signature = null;
            this.m_compressedXmlStructure = null;
            this.m_xmlStructure = null;
        }
    }
    
    public boolean compareMixIDs(final MixCascade mixCascade) {
        return mixCascade != null && mixCascade.getMixIDsAsString().equals(this.getMixIDsAsString());
    }
    
    public String getId() {
        return this.m_mixCascadeId;
    }
    
    public String getMixProtocolVersion() {
        return this.m_mixProtocolVersion;
    }
    
    public String getPaymentProtocolVersion() {
        return this.m_paymentProtocolVersion;
    }
    
    public String getPIID() {
        return this.m_piid;
    }
    
    public long getPrepaidInterval() {
        return this.m_prepaidInterval;
    }
    
    public boolean isFromCascade() {
        return this.m_bFromCascade;
    }
    
    public long getVersionNumber() {
        return this.m_serial;
    }
    
    public long getLastUpdate() {
        return this.m_lastUpdate;
    }
    
    public String getMixNames() {
        synchronized (this.m_mixInfos) {
            if (this.m_strMixNames == null) {
                this.m_strMixNames = "";
                for (int i = 0; i < this.m_mixInfos.length; ++i) {
                    if (this.m_mixInfos[i] != null) {
                        if (this.m_strMixNames.length() > 0) {
                            this.m_strMixNames += "-";
                        }
                        this.m_strMixNames += this.m_mixInfos[i].getName();
                    }
                }
                if (this.m_strMixNames.length() == 0) {
                    this.m_strMixNames = this.m_strName;
                }
                else if (!this.m_strName.equals(this.m_strMixNames)) {
                    this.m_strMixNames = this.m_strName + "|" + this.m_strMixNames;
                }
            }
        }
        return this.m_strMixNames;
    }
    
    public String getName() {
        this.getDecomposedCascadeName();
        return this.m_strName;
    }
    
    public int getMaxUsers() {
        return this.m_maxUsers;
    }
    
    public String toString() {
        return this.getName();
    }
    
    public Vector getDecomposedCascadeName() {
        synchronized (this.SYNC_NAME) {
            if (this.m_decomposedCascadeName == null) {
                this.m_decomposedCascadeName = new Vector();
                if (this.m_strName != null && (this.isUserDefined() || (this.m_mixInfos.length == 0 && this.isShownAsTrusted()))) {
                    this.m_decomposedCascadeName.addElement(this.m_strName);
                    return this.m_decomposedCascadeName;
                }
                if (this.m_mixInfos.length == 0) {
                    this.m_decomposedCascadeName.addElement("Unknown");
                    return this.m_decomposedCascadeName;
                }
                boolean b = false;
                boolean b2 = true;
                final Vector vector = new Vector<ServiceOperator>();
                for (int i = 0; i < this.m_mixInfos.length; ++i) {
                    if (this.m_mixInfos[i] == null || this.m_mixInfos[i].getServiceOperator() == null) {
                        b = true;
                        break;
                    }
                    final ServiceOperator serviceOperator = this.m_mixInfos[i].getServiceOperator();
                    if (vector.contains(serviceOperator)) {
                        b2 = false;
                        break;
                    }
                    vector.addElement(serviceOperator);
                }
                if (this.m_strName != null) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(this.m_strName, "-/\\");
                    if (stringTokenizer.countTokens() == this.getNumberOfMixes()) {
                        while (stringTokenizer.hasMoreTokens()) {
                            final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken().trim());
                            if (!stringTokenizer2.hasMoreTokens()) {
                                break;
                            }
                            final String trim = stringTokenizer2.nextToken().trim();
                            if (trim.length() == 0) {
                                break;
                            }
                            this.m_decomposedCascadeName.addElement(trim);
                        }
                    }
                    if (this.m_decomposedCascadeName.size() == 0) {
                        int n;
                        for (n = 0; n < this.m_mixInfos.length && (this.m_mixInfos[n] == null || !this.m_mixInfos[n].isCascadaNameFragmentUsed()); ++n) {}
                        if (n == this.m_mixInfos.length) {
                            this.m_strName = Util.cutString(Util.stripString(this.m_strName, "-/\\"), 35);
                            this.m_decomposedCascadeName.addElement(this.m_strName);
                            return this.m_decomposedCascadeName;
                        }
                    }
                }
                if (b || this.m_mixInfos[0].getServiceOperator().equals(this.m_mixInfos[this.m_mixInfos.length - 1].getServiceOperator())) {
                    if (this.m_mixInfos[0] != null && this.m_mixInfos[0].isCascadaNameFragmentUsed()) {
                        this.m_strName = this.m_mixInfos[0].getNameFragmentForCascade();
                    }
                    else if (this.m_decomposedCascadeName.size() > 0) {
                        this.m_strName = this.m_decomposedCascadeName.elementAt(0);
                    }
                    else if (this.m_mixInfos[0] != null) {
                        this.m_strName = this.m_mixInfos[0].getName();
                    }
                    else {
                        this.m_strName = "Unknown";
                    }
                    this.m_strName = Util.cutString(Util.stripString(this.m_strName, "-/\\"), 35);
                    this.m_decomposedCascadeName.removeAllElements();
                    this.m_decomposedCascadeName.addElement(this.m_strName);
                }
                else {
                    if (!b2) {
                        this.m_decomposedCascadeName.removeAllElements();
                    }
                    final Vector<ServiceOperator> vector2 = new Vector<ServiceOperator>();
                    final boolean b3 = this.m_decomposedCascadeName.size() > 0;
                    for (int j = 0; j < this.m_mixInfos.length; ++j) {
                        final ServiceOperator serviceOperator2 = this.m_mixInfos[j].getServiceOperator();
                        if (!vector2.contains(serviceOperator2)) {
                            vector2.addElement(serviceOperator2);
                            if (b3) {
                                if (this.m_mixInfos[j].isCascadaNameFragmentUsed()) {
                                    this.m_decomposedCascadeName.setElementAt(this.m_mixInfos[j].getNameFragmentForCascade(), j);
                                }
                            }
                            else {
                                if (this.m_mixInfos[j].isCascadaNameFragmentUsed()) {
                                    this.m_decomposedCascadeName.addElement(this.m_mixInfos[j].getNameFragmentForCascade());
                                }
                                else {
                                    this.m_decomposedCascadeName.addElement(this.m_mixInfos[j].getName());
                                }
                                if (this.m_decomposedCascadeName.elementAt(j) == null) {
                                    this.m_decomposedCascadeName.setElementAt("Unknown", j);
                                }
                            }
                            this.m_decomposedCascadeName.setElementAt(Util.stripString(this.m_decomposedCascadeName.elementAt(j).toString(), "-/\\"), j);
                        }
                    }
                    this.m_strName = "";
                    for (int k = 0; k < this.m_decomposedCascadeName.size(); ++k) {
                        this.m_strName += (this.m_strName.equals("") ? "" : "-");
                        this.m_strName += this.m_decomposedCascadeName.elementAt(k);
                    }
                    int n2 = 15;
                    while (this.m_strName.length() > 35) {
                        this.m_strName = "";
                        for (int l = 0; l < this.m_decomposedCascadeName.size(); ++l) {
                            this.m_strName += (this.m_strName.equals("") ? "" : "-");
                            this.m_decomposedCascadeName.setElementAt(Util.cutString(this.m_decomposedCascadeName.elementAt(l).toString(), n2), l);
                            this.m_strName += this.m_decomposedCascadeName.elementAt(l);
                        }
                        --n2;
                    }
                }
            }
        }
        return this.m_decomposedCascadeName;
    }
    
    public boolean equals(final Object o) {
        boolean equals = false;
        if (o != null && o instanceof MixCascade) {
            equals = this.getId().equals(((MixCascade)o).getId());
        }
        return equals;
    }
    
    public boolean checkId() {
        return this.m_userDefined || super.checkId();
    }
    
    public int hashCode() {
        return this.getId().hashCode();
    }
    
    public int getNumberOfListenerInterfaces() {
        if (this.m_listenerInterfaces != null) {
            return this.m_listenerInterfaces.size();
        }
        return 0;
    }
    
    public ListenerInterface getListenerInterface(final int n) {
        ListenerInterface listenerInterface = null;
        if (n >= 0 && n < this.getNumberOfListenerInterfaces()) {
            listenerInterface = this.m_listenerInterfaces.elementAt(n);
        }
        return listenerInterface;
    }
    
    public String getHostsAsString() {
        this.cacheHostAndPortsAsString();
        return this.m_strHosts;
    }
    
    public String getPortsAsString() {
        this.cacheHostAndPortsAsString();
        return this.m_strPorts;
    }
    
    public Vector getHosts() {
        final Vector vector = new Vector<String>();
        for (int i = 0; i < this.getNumberOfListenerInterfaces(); ++i) {
            final String host = this.m_listenerInterfaces.elementAt(0).getHost();
            if (!vector.contains(host)) {
                vector.addElement(host);
            }
        }
        return vector;
    }
    
    public int getNumberOfMixes() {
        return this.m_mixIds.size();
    }
    
    public String getMixIDsAsString() {
        return this.m_strMixIds;
    }
    
    public MixInfo getMixInfo(final int n) {
        if (n < 0 || n >= this.getNumberOfMixes()) {
            return null;
        }
        return this.m_mixInfos[n];
    }
    
    public MixInfo getMixInfo(final String s) {
        if (s == null) {
            return null;
        }
        for (int i = 0; i < this.m_mixIds.size(); ++i) {
            if (this.m_mixIds.elementAt(i).equals(s)) {
                return this.m_mixInfos[i];
            }
        }
        return null;
    }
    
    public String getMixId(final int n) {
        if (n < 0 || n >= this.getNumberOfMixes()) {
            return null;
        }
        return this.m_mixIds.elementAt(n).toString();
    }
    
    public Vector getMixIds() {
        return (Vector)this.m_mixIds.clone();
    }
    
    public boolean isUserDefined() {
        return this.m_userDefined;
    }
    
    public boolean isSocks5Supported() {
        return this.m_bSock5Support;
    }
    
    public void showAsTrusted(final boolean bImplicitTrust) {
        this.m_bImplicitTrust = bImplicitTrust;
    }
    
    public boolean isShownAsTrusted() {
        return this.m_bImplicitTrust;
    }
    
    public void setUserDefined(final boolean userDefined, final MixCascade mixCascade) throws XMLParseException {
        this.m_userDefined = userDefined;
        if (this.m_userDefined && mixCascade != null && mixCascade.getId().equals(this.getId())) {
            this.m_strName = mixCascade.m_strName;
            (this.m_decomposedCascadeName = new Vector()).addElement(this.m_strName);
            this.m_listenerInterfaces = mixCascade.m_listenerInterfaces;
            this.m_lastUpdate = System.currentTimeMillis();
        }
        this.m_xmlStructure = this.generateXmlRepresentation();
        this.m_compressedXmlStructure = ZLibTools.compress(XMLSignature.toCanonical(this.m_xmlStructure));
        this.calculateOperatorsAndCountries();
    }
    
    public StatusInfo fetchCurrentStatus() {
        return this.fetchCurrentStatus(-1L);
    }
    
    public StatusInfo fetchCurrentStatus(final long n) {
        if (this.getMixId(0) == null) {
            this.getId();
        }
        StatusInfo statusInfo;
        if (n <= 0L) {
            statusInfo = InfoServiceHolder.getInstance().getStatusInfo(this);
        }
        else {
            statusInfo = InfoServiceHolder.getInstance().getStatusInfo(this, n);
        }
        return statusInfo;
    }
    
    public StatusInfo getCurrentStatus() {
        StatusInfo dummyStatusInfo = (StatusInfo)Database.getInstance((MixCascade.class$anon$infoservice$StatusInfo == null) ? (MixCascade.class$anon$infoservice$StatusInfo = class$("anon.infoservice.StatusInfo")) : MixCascade.class$anon$infoservice$StatusInfo).getEntryById(this.getId());
        if (dummyStatusInfo == null) {
            dummyStatusInfo = StatusInfo.createDummyStatusInfo(this.getId());
        }
        return dummyStatusInfo;
    }
    
    public String getPostFile() {
        return "/cascade";
    }
    
    public int getPostEncoding() {
        return 1;
    }
    
    public byte[] getPostData() {
        return this.m_compressedXmlStructure;
    }
    
    public byte[] getCompressedData() {
        return this.m_compressedXmlStructure;
    }
    
    public Element getXmlStructure() {
        return this.m_xmlStructure;
    }
    
    public Hashtable getPriceCertificateHashes() {
        return (Hashtable)this.m_priceCertificateHashes.clone();
    }
    
    public Vector getPriceCertificates() {
        return (Vector)this.m_priceCertificates.clone();
    }
    
    public int getNrOfPriceCerts() {
        return this.m_nrPriceCerts;
    }
    
    public XMLSignature getSignature() {
        return this.m_signature;
    }
    
    public MultiCertPath getCertPath() {
        return this.m_certPath;
    }
    
    public boolean isVerified() {
        if (this.m_certPath != null) {
            return this.m_certPath.isVerified();
        }
        return this.m_bDefaultVerified;
    }
    
    public boolean isValid() {
        return this.m_certPath != null && this.m_certPath.isValid(new Date());
    }
    
    public DataRetentionInformation getDataRetentionInformation() {
        return this.m_dataRetentionInfo;
    }
    
    public boolean isActiveStudy() {
        return this.m_bStudy || this.m_userDefined;
    }
    
    public int getNumberOfOperators() {
        this.calculateOperatorsAndCountries();
        return this.m_nrOperators;
    }
    
    public int getNumberOfOperatorsShown() {
        this.calculateOperatorsAndCountries();
        return this.m_nrOperatorsShown;
    }
    
    public int getNumberOfCountries() {
        this.calculateOperatorsAndCountries();
        return this.m_nrCountries;
    }
    
    public int getDistribution() {
        this.calculateOperatorsAndCountries();
        return this.m_distributionPoints;
    }
    
    private void cacheHostAndPortsAsString() {
        synchronized (this.CACHE_HOSTS_PORT) {
            if (this.m_strPorts != null || this.m_strHosts != null) {
                return;
            }
            String strHosts = "";
            String strPorts = "";
            final int[] array = new int[this.getNumberOfListenerInterfaces()];
            for (int i = 0; i < this.getNumberOfListenerInterfaces(); ++i) {
                if (strHosts.indexOf(this.getListenerInterface(i).getHost()) == -1) {
                    if (strHosts.length() > 0) {
                        strHosts += "\n";
                    }
                    strHosts += this.getListenerInterface(i).getHost();
                }
                array[i] = this.getListenerInterface(i).getPort();
            }
            for (int j = 0; j < array.length; ++j) {
                for (int k = j + 1; k < array.length; ++k) {
                    if (array[j] > array[k]) {
                        final int n = array[k];
                        array[k] = array[j];
                        array[j] = n;
                    }
                }
            }
            final Vector vector = new Vector<Integer>(array.length);
            for (int l = 0; l < array.length; ++l) {
                if (!vector.contains(new Integer(array[l]))) {
                    vector.addElement(new Integer(array[l]));
                }
            }
            for (int n2 = 0; n2 < vector.size(); ++n2) {
                strPorts += vector.elementAt(n2).toString();
                if (n2 != vector.size() - 1) {
                    strPorts += ", ";
                }
            }
            this.m_strHosts = strHosts;
            this.m_strPorts = strPorts;
        }
    }
    
    private void calculateOperatorsAndCountries() {
        synchronized (this.SYNC_OPERATORS_AND_COUNTRIES) {
            final Hashtable hashtable = new Hashtable<String, String>();
            final Hashtable hashtable2 = new Hashtable<String, String>();
            final Hashtable hashtable3 = new Hashtable<String, String>();
            boolean b;
            if (b = (this.m_mixCertVerifiedAndValid == null)) {
                this.m_mixCertVerifiedAndValid = new boolean[this.getNumberOfMixes()];
                for (int i = 0; i < this.m_mixCertVerifiedAndValid.length; ++i) {
                    this.m_mixCertVerifiedAndValid[i] = false;
                }
            }
            for (int j = 0; j < this.getNumberOfMixes(); ++j) {
                final boolean b2 = this.getMixInfo(j) != null && this.getMixInfo(j).getCertPath() != null && this.getMixInfo(j).getCertPath().isVerified() && this.getMixInfo(j).getCertPath().isValid(new Date());
                if (this.m_mixCertVerifiedAndValid[j] != b2) {
                    b = true;
                }
                this.m_mixCertVerifiedAndValid[j] = b2;
            }
            if (!b) {
                return;
            }
            this.m_nrOperatorsCountForDistribution = 0;
            this.m_nrOperators = 0;
            this.m_nrOperatorsShown = 0;
            this.m_nrCountries = 0;
            for (int k = 0; k < this.getNumberOfMixes(); ++k) {
                if (this.getMixInfo(k) != null) {
                    if (this.getMixInfo(k).getCertPath() != null) {
                        final X509DistinguishedName issuer = this.getMixInfo(k).getCertPath().getIssuer();
                        if (issuer == null || issuer.getOrganisation() == null || hashtable.contains(issuer.getOrganisation()) || hashtable3.contains(this.getMixInfo(k).getId())) {
                            if (this.m_nrOperators <= 0) {
                                for (int l = 0; l < this.getNumberOfMixes(); ++l) {
                                    if (this.m_mixCertVerifiedAndValid[l]) {
                                        this.m_nrOperators = 1;
                                        this.m_nrOperatorsCountForDistribution = 1;
                                        break;
                                    }
                                }
                            }
                            else {
                                this.m_nrOperators = 1;
                                this.m_nrOperatorsCountForDistribution = 1;
                            }
                            this.m_nrOperatorsShown = 1;
                            this.m_nrCountries = Math.min(this.m_nrOperatorsCountForDistribution, 1);
                            break;
                        }
                        String countryCode = issuer.getCountryCode();
                        String countryCode2 = this.getMixInfo(k).getCertPath().getSubject().getCountryCode();
                        try {
                            if (new CountryMapper(countryCode).toString() == countryCode) {
                                countryCode = null;
                            }
                            else if (new CountryMapper(countryCode2).toString() == countryCode2) {
                                countryCode2 = null;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            countryCode = null;
                            countryCode2 = null;
                        }
                        if (countryCode != null && countryCode2 != null && !hashtable2.contains(countryCode2) && !hashtable2.contains(countryCode) && this.m_mixCertVerifiedAndValid[k] && (k <= 1 || k + 1 == this.getNumberOfMixes())) {
                            ++this.m_nrCountries;
                        }
                        if (this.m_mixCertVerifiedAndValid[k]) {
                            if (countryCode != null) {
                                hashtable2.put(countryCode, countryCode);
                            }
                            if (countryCode2 != null) {
                                hashtable2.put(countryCode2, countryCode2);
                            }
                        }
                        hashtable.put(issuer.getOrganisation(), issuer.getOrganisation());
                        hashtable3.put(this.getMixInfo(k).getId(), this.getMixInfo(k).getId());
                        if (this.m_mixCertVerifiedAndValid[k]) {
                            ++this.m_nrOperators;
                            if (k <= 1 || k + 1 == this.getNumberOfMixes()) {
                                ++this.m_nrOperatorsCountForDistribution;
                            }
                        }
                        ++this.m_nrOperatorsShown;
                    }
                }
            }
            if (this.m_nrOperatorsCountForDistribution == 3 && this.m_nrCountries == 1) {
                this.m_distributionPoints = 3;
            }
            else if (this.m_nrOperatorsCountForDistribution == 2 && this.m_nrCountries == 1) {
                this.m_distributionPoints = 2;
            }
            else if (this.m_nrOperatorsCountForDistribution == 1) {
                this.m_distributionPoints = 1;
            }
            else {
                this.m_distributionPoints = this.m_nrOperatorsCountForDistribution + this.m_nrCountries;
            }
            this.calculateOperatorsAndCountries();
        }
    }
    
    private Element generateXmlRepresentation() {
        final Document document = XMLUtil.createDocument();
        final Element element = document.createElement("MixCascade");
        XMLUtil.setAttribute(element, "id", this.getId());
        if (this.m_isPayment) {
            final Element element2 = document.createElement("Payment");
            XMLUtil.setAttribute(element2, "required", this.m_isPayment);
            XMLUtil.setAttribute(element2, "version", this.m_paymentProtocolVersion);
            element.appendChild(element2);
        }
        final Element element3 = document.createElement("Name");
        XMLUtil.setValue(element3, this.getName());
        final Element element4 = document.createElement("Network");
        final Element element5 = document.createElement("ListenerInterfaces");
        for (int i = 0; i < this.getNumberOfListenerInterfaces(); ++i) {
            element5.appendChild(this.getListenerInterface(i).toXmlElement(document));
        }
        element4.appendChild(element5);
        final Element element6 = document.createElement("Mixes");
        XMLUtil.setAttribute(element6, "count", this.getNumberOfMixes());
        final Enumeration<String> elements = this.m_mixIds.elements();
        int n = 0;
        while (elements.hasMoreElements()) {
            if (this.m_mixNodes.size() > n) {
                elements.nextElement();
                try {
                    element6.appendChild(XMLUtil.importNode(document, (Node)this.m_mixNodes.elementAt(n), true));
                }
                catch (XMLParseException ex) {
                    LogHolder.log(2, LogType.MISC, "Could not import node " + ((Node)this.m_mixNodes.elementAt(n)).getNodeName() + "!");
                }
            }
            else {
                final Element element7 = document.createElement("Mix");
                XMLUtil.setAttribute(element7, "id", elements.nextElement());
                element6.appendChild(element7);
            }
            ++n;
        }
        final Element element8 = document.createElement("LastUpdate");
        XMLUtil.setValue(element8, this.getLastUpdate());
        element.appendChild(element3);
        element.appendChild(element4);
        element.appendChild(element6);
        element.appendChild(element8);
        if (this.isUserDefined()) {
            XMLUtil.setAttribute(element, "userDefined", true);
            if (this.m_signature != null) {
                final Element[] xmlElements = this.m_signature.getXMLElements(document);
                for (int j = 0; j < xmlElements.length; ++j) {
                    element.appendChild(xmlElements[j]);
                }
            }
        }
        return element;
    }
    
    public boolean isPaymentProtocolSupported() {
        return !this.m_isPayment || (this.m_isPayment && this.getPaymentProtocolVersion().equals("2.0"));
    }
    
    public boolean isPayment() {
        return this.m_isPayment;
    }
    
    public boolean isTermsAndConditionsConfirmationRequired() {
        return this.termsAndConditionsConfirmationRequired;
    }
    
    private void createMixIDString() {
        this.m_strMixIds = "";
        for (int i = 0; i < this.m_mixIds.size(); ++i) {
            this.m_strMixIds += this.m_mixIds.elementAt(i);
        }
    }
    
    public String getContext() {
        if (this.m_context != null) {
            return this.m_context;
        }
        if (this.isPayment()) {
            return "jondonym.premium";
        }
        return "jondonym";
    }
    
    public Element getWebInfo(final Document document) {
        if (document == null) {
            return null;
        }
        final Vector decomposedCascadeName = this.getDecomposedCascadeName();
        final Vector vector = new Vector<String>(decomposedCascadeName.size());
        for (int i = 0; i < decomposedCascadeName.size(); ++i) {
            String s = decomposedCascadeName.elementAt(i);
            if (s == null || vector.contains(s)) {
                s = "";
            }
            vector.insertElementAt(s, i);
        }
        final Element element = document.createElement("CascadeWebInfo");
        XMLUtil.setAttribute(element, "payment", this.isPayment());
        XMLUtil.setAttribute(element, "id", this.getId());
        if (this.getMaxUsers() > 0) {
            XMLUtil.setAttribute(element, "maxUsers", this.getMaxUsers());
        }
        if (this.getContext() != null) {
            XMLUtil.setAttribute(element, "context", this.getContext());
        }
        final Element childElement = XMLUtil.createChildElement(element, "CascadeName");
        final Element element2 = document.createElement("Mixes");
        XMLUtil.createChildElementWithValue(element, "CurrentUsers", "" + this.getCurrentStatus().getNrOfActiveUsers());
        final PerformanceEntry performanceEntry = (PerformanceEntry)Database.getInstance((MixCascade.class$anon$infoservice$PerformanceEntry == null) ? (MixCascade.class$anon$infoservice$PerformanceEntry = class$("anon.infoservice.PerformanceEntry")) : MixCascade.class$anon$infoservice$PerformanceEntry).getEntryById(this.getId());
        if (performanceEntry != null) {
            element.appendChild(performanceEntry.toXmlElement(document));
        }
        element.appendChild(element2);
        for (int j = 0; j < this.getNumberOfMixes(); ++j) {
            final MixInfo mixInfo = this.getMixInfo(j);
            if (mixInfo != null) {
                if (mixInfo.getCertPath() == null) {
                    return null;
                }
                final CertPath path = mixInfo.getCertPath().getPath();
                final ServiceOperator serviceOperator = new ServiceOperator(null, mixInfo.getCertPath(), 0L);
                final ServiceLocation serviceLocation = new ServiceLocation(null, path.getFirstCertificate());
                final String name = mixInfo.getName();
                if (serviceOperator == null || serviceLocation == null) {
                    return null;
                }
                String s2 = null;
                if (j < vector.size()) {
                    s2 = vector.elementAt(j);
                    if (s2 != null && s2.equals("")) {
                        s2 = null;
                    }
                }
                if (s2 != null) {
                    final Element childElementWithValue = XMLUtil.createChildElementWithValue(childElement, "Name", s2);
                    if (serviceLocation.getCountryCode() != null) {
                        XMLUtil.setAttribute(childElementWithValue, "mixCountry", serviceLocation.getCountryCode());
                    }
                    if (serviceOperator.getCountryCode() != null) {
                        XMLUtil.setAttribute(childElementWithValue, "operatorCountry", serviceOperator.getCountryCode());
                    }
                    XMLUtil.setAttribute(childElementWithValue, "mixPosition", j);
                }
                final Element childElement2 = XMLUtil.createChildElement(element2, "Mix");
                XMLUtil.setAttribute(childElement2, "id", mixInfo.getId());
                if (name != null) {
                    XMLUtil.createChildElementWithValue(childElement2, "Name", name);
                }
                final Element xmlElement = serviceOperator.toXMLElement(document);
                final Element xmlElement2 = serviceLocation.toXMLElement(document);
                if (xmlElement != null) {
                    childElement2.appendChild(xmlElement);
                }
                if (xmlElement2 != null) {
                    childElement2.appendChild(xmlElement2);
                }
            }
        }
        XMLUtil.createChildElementWithValue(childElement, "ComposedName", this.getName());
        final Element childElement3 = XMLUtil.createChildElement(element, "ListenerInterfaces");
        final Hashtable<Object, Element> hashtable = (Hashtable<Object, Element>)new Hashtable<String, Element>();
        for (int k = 0; k < this.getNumberOfListenerInterfaces(); ++k) {
            final ListenerInterface listenerInterface = this.getListenerInterface(k);
            if (!listenerInterface.isHidden()) {
                Element childElement4;
                if (hashtable.containsKey(listenerInterface.getHost())) {
                    childElement4 = hashtable.get(listenerInterface.getHost());
                }
                else {
                    childElement4 = XMLUtil.createChildElement(childElement3, "ListenerInterface");
                    XMLUtil.setAttribute(childElement4, "Host", listenerInterface.getHost());
                    hashtable.put(listenerInterface.getHost(), childElement4);
                }
                if (listenerInterface.getProtocol() != 5) {
                    XMLUtil.createChildElementWithValue(childElement4, "Port", "" + listenerInterface.getPort());
                }
            }
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
}
