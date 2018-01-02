// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import java.util.Hashtable;
import org.w3c.dom.Document;
import java.util.Date;
import java.net.UnknownHostException;
import java.net.InetAddress;
import org.w3c.dom.NodeList;
import logging.LogHolder;
import logging.LogType;
import anon.crypto.SignatureVerifier;
import anon.util.XMLUtil;
import org.w3c.dom.Node;
import anon.crypto.CertPath;
import anon.crypto.JAPCertificate;
import anon.util.XMLParseException;
import anon.terms.TermsAndConditionsMixInfo;
import anon.crypto.XMLSignature;
import anon.pay.xml.XMLPriceCertificate;
import anon.crypto.MultiCertPath;
import org.w3c.dom.Element;
import java.util.Vector;
import anon.crypto.IVerifyable;

public class MixInfo extends AbstractDistributableCertifiedDatabaseEntry implements IVerifyable, Database.IWebInfo
{
    public static final String NAME_TYPE_MIX = "Mix";
    public static final String NAME_TYPE_OPERATOR = "Operator";
    public static final String XML_ELEMENT_CONTAINER_NAME = "Mixes";
    public static final String XML_ELEMENT_NAME = "Mix";
    public static final String XML_ELEMENT_MIX_NAME = "Name";
    public static final String XML_ATTRIBUTE_NAME_FOR_CASCADE = "forCascade";
    public static final String XML_ELEMENT_WEBINFO_CONTAINER = "MixWebInfos";
    public static final String INFOSERVICE_COMMAND_WEBINFOS = "/mixwebinfos";
    public static final String INFOSERVICE_COMMAND_WEBINFO = "/mixwebinfo/";
    private static final String XML_ELEMENT_WEBINFO = "MixWebInfo";
    private static final String XML_ELEM_SERVER_MONITORING = "ServerMonitoring";
    private static final String XML_ATTR_PAYMENT = "payment";
    public static final int FIRST_MIX = 0;
    public static final int MIDDLE_MIX = 1;
    public static final int LAST_MIX = 2;
    private int m_type;
    private DataRetentionInformation m_drInfo;
    private boolean m_bPayment;
    private boolean m_dynamic;
    private boolean m_bSocks;
    private final Vector m_vecVisibleAdresses;
    private final Vector m_vecListenerAdresses;
    private final Vector m_vecListenerInterfaces;
    private final Vector m_vecListenerMonitoring;
    private String m_mixId;
    private long m_lastUpdate;
    private long m_serial;
    private String m_name;
    private String m_nameFragmentForCascade;
    private boolean m_bUseCascadeNameFragment;
    private ServiceLocation m_mixLocation;
    private ServiceOperator m_mixOperator;
    private ServiceSoftware m_mixSoftware;
    private boolean m_freeMix;
    private Element m_xmlStructure;
    private MultiCertPath m_mixCertPath;
    private XMLPriceCertificate m_priceCert;
    private long m_prepaidInterval;
    private XMLSignature m_mixSignature;
    private boolean m_bFromCascade;
    private TermsAndConditionsMixInfo m_mixTnCInfo;
    static /* synthetic */ Class class$anon$infoservice$ServiceOperator;
    
    public MixInfo(final Element element) throws XMLParseException {
        this(element, 0L);
    }
    
    public MixInfo(final Element element, final long n) throws XMLParseException {
        this(element, n, false);
    }
    
    public MixInfo(final MultiCertPath mixCertPath) {
        super(Long.MAX_VALUE);
        this.m_bPayment = false;
        this.m_dynamic = false;
        this.m_bSocks = false;
        this.m_vecVisibleAdresses = new Vector();
        this.m_vecListenerAdresses = new Vector();
        this.m_vecListenerInterfaces = new Vector();
        this.m_vecListenerMonitoring = new Vector();
        this.m_bUseCascadeNameFragment = false;
        this.m_mixTnCInfo = null;
        if (mixCertPath == null) {
            throw new IllegalArgumentException("No Mix cert path!");
        }
        final Vector<JAPCertificate> vector = new Vector<JAPCertificate>();
        final Vector paths = mixCertPath.getPaths();
        for (int i = 0; i < paths.size(); ++i) {
            vector.addElement(paths.elementAt(i).getFirstCertificate());
        }
        this.m_mixId = JAPCertificate.calculateXORofSKIs(vector);
        this.m_name = mixCertPath.getSubject().getCommonName();
        if (this.m_name == null) {
            this.m_name = "Mix";
        }
        this.m_type = -1;
        this.m_bFromCascade = true;
        this.m_mixCertPath = mixCertPath;
        this.m_lastUpdate = 0L;
        this.m_serial = 0L;
        this.m_mixLocation = new ServiceLocation(null, mixCertPath.getPath().getFirstCertificate());
        this.m_mixOperator = new ServiceOperator(null, mixCertPath, 0L);
        this.m_freeMix = false;
        this.m_prepaidInterval = 3000000L;
    }
    
    public MixInfo(final String s, final MultiCertPath mixCertPath, final XMLPriceCertificate priceCert, final long prepaidInterval) {
        super(Long.MAX_VALUE);
        this.m_bPayment = false;
        this.m_dynamic = false;
        this.m_bSocks = false;
        this.m_vecVisibleAdresses = new Vector();
        this.m_vecListenerAdresses = new Vector();
        this.m_vecListenerInterfaces = new Vector();
        this.m_vecListenerMonitoring = new Vector();
        this.m_bUseCascadeNameFragment = false;
        this.m_mixTnCInfo = null;
        this.m_mixId = s;
        this.m_name = s;
        this.m_type = -1;
        this.m_bFromCascade = true;
        this.m_mixCertPath = mixCertPath;
        this.m_lastUpdate = 0L;
        this.m_serial = 0L;
        this.m_mixLocation = new ServiceLocation(null, mixCertPath.getPath().getFirstCertificate());
        this.m_mixOperator = new ServiceOperator(null, mixCertPath, 0L);
        this.m_freeMix = false;
        this.m_priceCert = priceCert;
        if (this.m_priceCert != null) {
            this.m_bPayment = true;
        }
        this.m_prepaidInterval = prepaidInterval;
    }
    
    public MixInfo(final Element xmlStructure, final long n, final boolean bFromCascade) throws XMLParseException {
        super((n <= 0L) ? (System.currentTimeMillis() + 900000L) : n);
        this.m_bPayment = false;
        this.m_dynamic = false;
        this.m_bSocks = false;
        this.m_vecVisibleAdresses = new Vector();
        this.m_vecListenerAdresses = new Vector();
        this.m_vecListenerInterfaces = new Vector();
        this.m_vecListenerMonitoring = new Vector();
        this.m_bUseCascadeNameFragment = false;
        this.m_mixTnCInfo = null;
        this.m_bFromCascade = bFromCascade;
        this.m_mixId = XMLUtil.parseAttribute(xmlStructure, "id", null);
        if (this.m_mixId == null) {
            throw new XMLParseException("##__null__##", "id");
        }
        try {
            this.m_mixSignature = SignatureVerifier.getInstance().getVerifiedXml(xmlStructure, 1);
            if (this.m_mixSignature != null) {
                this.m_mixCertPath = this.m_mixSignature.getMultiCertPath();
            }
            else {
                LogHolder.log(7, LogType.MISC, "No signature node found while looking for MixCascade certificate.");
            }
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.MISC, "Error while looking for appended certificates in the MixInfo structure: " + ex.toString());
        }
        if (!this.checkId()) {
            throw new XMLParseException("##__root__##", "Malformed Mix ID: " + this.m_mixId);
        }
        this.m_bSocks = XMLUtil.parseAttribute(XMLUtil.getFirstChildByName(xmlStructure, "Proxies"), "socks5Support", false);
        final Node firstChildByName = XMLUtil.getFirstChildByName(xmlStructure, "Operator");
        final Node firstChildByName2 = XMLUtil.getFirstChildByName(xmlStructure, "Location");
        final Node firstChildByName3 = XMLUtil.getFirstChildByName(xmlStructure, "LastUpdate");
        final Node firstChildByName4 = XMLUtil.getFirstChildByName(xmlStructure, "Software");
        final Node firstChildByName5 = XMLUtil.getFirstChildByName(xmlStructure, "PrepaidIntervalKbytes");
        final Node firstChildByName6 = XMLUtil.getFirstChildByName(xmlStructure, "PriceCertificate");
        if (firstChildByName6 != null) {
            this.m_priceCert = new XMLPriceCertificate((Element)firstChildByName6);
            if (!this.m_priceCert.getSubjectKeyIdentifier().equals(this.getId())) {
                LogHolder.log(3, LogType.PAY, "SKI in price certificate differs from Mix ID! SKI: $" + this.m_priceCert.getSubjectKeyIdentifier() + "$ MixID: $" + this.getId() + "$");
            }
        }
        if (n < Long.MAX_VALUE) {
            this.parseVisibleAdresses(xmlStructure);
            this.parseListenerAdresses(xmlStructure);
        }
        final Element element = (Element)XMLUtil.getFirstChildByName(xmlStructure, "ListenerInterfaces");
        XMLUtil.assertNotNull(element);
        final NodeList elementsByTagName = element.getElementsByTagName("ListenerInterface");
        if (elementsByTagName.getLength() == 0) {
            throw new XMLParseException("First Mix has no ListenerInterfaces in its XML structure.");
        }
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            this.m_vecListenerInterfaces.addElement(new ListenerInterface((Element)elementsByTagName.item(i)));
        }
        final Element element2 = (Element)XMLUtil.getFirstChildByName(xmlStructure, "ServerMonitoring");
        if (element2 != null) {
            final Element element3 = (Element)XMLUtil.getFirstChildByName(element2, "ListenerInterfaces");
            if (element3 != null) {
                final NodeList elementsByTagName2 = element3.getElementsByTagName("ListenerInterface");
                for (int j = 0; j < elementsByTagName2.getLength(); ++j) {
                    this.m_vecListenerMonitoring.addElement(new ListenerInterface((Element)elementsByTagName2.item(j)));
                }
            }
            else {
                final String value = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element2, "Host"), null);
                final int value2 = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element2, "Port"), -1);
                if (value != null && value2 >= 0) {
                    this.m_vecListenerMonitoring.addElement(new ListenerInterface(value, value2));
                }
            }
        }
        if (!bFromCascade) {
            final Node firstChildByName7 = XMLUtil.getFirstChildByName(xmlStructure, "MixType");
            XMLUtil.assertNotNull(firstChildByName7);
            this.m_type = this.parseMixType(XMLUtil.parseValue(firstChildByName7, null));
            this.m_bPayment = XMLUtil.parseAttribute(firstChildByName7, "payment", false);
            this.m_dynamic = XMLUtil.parseValue(XMLUtil.getFirstChildByName(xmlStructure, "Dynamic"), false);
            if (firstChildByName4 == null) {
                throw new XMLParseException("Software", this.m_mixId);
            }
            if (firstChildByName3 == null) {
                throw new XMLParseException("LastUpdate", this.m_mixId);
            }
            this.m_lastUpdate = XMLUtil.parseValue(firstChildByName3, 0L);
        }
        else {
            this.m_lastUpdate = System.currentTimeMillis() - 900000L;
        }
        this.m_prepaidInterval = XMLUtil.parseValue(firstChildByName5, 3000L) * 1000L;
        this.m_serial = XMLUtil.parseValue(firstChildByName3, 0L);
        final Node firstChildByName8 = XMLUtil.getFirstChildByName(xmlStructure, "TermsAndConditionsInfos");
        this.m_mixTnCInfo = ((firstChildByName8 != null) ? new TermsAndConditionsMixInfo(firstChildByName8) : null);
        if (firstChildByName4 != null) {
            this.m_mixSoftware = new ServiceSoftware(firstChildByName4);
        }
        final CertPath path = this.m_mixCertPath.getPath();
        if (path != null) {
            this.m_mixLocation = new ServiceLocation(firstChildByName2, path.getFirstCertificate());
            this.m_mixOperator = new ServiceOperator(firstChildByName, this.m_mixCertPath, this.m_lastUpdate);
        }
        else {
            this.m_mixLocation = new ServiceLocation(firstChildByName2, null);
            this.m_mixOperator = new ServiceOperator(firstChildByName, null, this.m_lastUpdate);
        }
        Node node = XMLUtil.getFirstChildByName(xmlStructure, "DataRetention");
        if (node != null) {
            if (this.m_mixOperator.getOrganization() != null && (this.m_mixOperator.getOrganization().indexOf("JAP-Team") >= 0 || this.m_mixOperator.getOrganization().indexOf("Independent Centre") >= 0) && XMLUtil.getFirstChildByName(node, "Description") == null) {
                node = XMLUtil.importNode(XMLUtil.createDocument(), node, true);
                final Element element4 = node.getOwnerDocument().createElement("Description");
                XMLUtil.setAttribute(element4, "lang", "en");
                final Element element5 = node.getOwnerDocument().createElement("URL");
                XMLUtil.setValue(element5, "http://anon.inf.tu-dresden.de/dataretention_en.html");
                element4.appendChild(element5);
                node.appendChild(element4);
                final Element element6 = node.getOwnerDocument().createElement("Description");
                XMLUtil.setAttribute(element6, "lang", "de");
                final Element element7 = node.getOwnerDocument().createElement("URL");
                XMLUtil.setValue(element7, "http://anon.inf.tu-dresden.de/dataretention_de.html");
                element6.appendChild(element7);
                node.appendChild(element6);
            }
            this.m_drInfo = new DataRetentionInformation((Element)node);
        }
        final ServiceOperator serviceOperator = (ServiceOperator)Database.getInstance((MixInfo.class$anon$infoservice$ServiceOperator == null) ? (MixInfo.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : MixInfo.class$anon$infoservice$ServiceOperator).getEntryById(this.m_mixOperator.getId());
        if (this.m_mixOperator.getOrganization() != null && (serviceOperator == null || serviceOperator.getOrganization() == null || serviceOperator.getCertPath() == null)) {
            Database.getInstance((MixInfo.class$anon$infoservice$ServiceOperator == null) ? (MixInfo.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : MixInfo.class$anon$infoservice$ServiceOperator).update(this.m_mixOperator);
        }
        else if (this.m_mixOperator.getCertPath() != null) {
            final Vector paths = this.m_mixOperator.getCertPath().getPaths();
            final Vector paths2 = serviceOperator.getCertPath().getPaths();
            if (paths.size() < paths2.size()) {
                LogHolder.log(1, LogType.DB, "Illegal DB object state: ServiceOperator object have same ID but different cert path lengths!");
                Database.getInstance((MixInfo.class$anon$infoservice$ServiceOperator == null) ? (MixInfo.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : MixInfo.class$anon$infoservice$ServiceOperator).update(this.m_mixOperator);
            }
            else {
                for (int k = 0; k < paths.size(); ++k) {
                    final JAPCertificate secondCertificate = paths.elementAt(k).getSecondCertificate();
                    final JAPCertificate secondCertificate2 = paths2.elementAt(k).getSecondCertificate();
                    if (secondCertificate == null) {
                        break;
                    }
                    if (secondCertificate2 == null || secondCertificate.getValidity().getValidTo().after(secondCertificate2.getValidity().getValidTo())) {
                        Database.getInstance((MixInfo.class$anon$infoservice$ServiceOperator == null) ? (MixInfo.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : MixInfo.class$anon$infoservice$ServiceOperator).update(this.m_mixOperator);
                        break;
                    }
                }
            }
        }
        this.m_freeMix = false;
        this.m_xmlStructure = xmlStructure;
        final Node firstChildByName9 = XMLUtil.getFirstChildByName(xmlStructure, "Name");
        this.m_name = XMLUtil.parseValue(firstChildByName9, null);
        final String attribute = XMLUtil.parseAttribute(firstChildByName9, "forCascade", "");
        if (attribute.equals("Operator") && this.m_mixOperator != null) {
            this.m_nameFragmentForCascade = ((this.m_mixOperator != null) ? this.m_mixOperator.getCommonName() : null);
            this.m_bUseCascadeNameFragment = true;
        }
        else if (attribute.equals("Mix") && this.m_mixLocation != null) {
            this.m_nameFragmentForCascade = this.m_mixLocation.getCommonName();
            this.m_bUseCascadeNameFragment = true;
        }
        if (this.m_nameFragmentForCascade != null && this.m_nameFragmentForCascade.equals("AN.ON Operator Certificate")) {
            if (this.m_mixLocation != null && this.m_mixLocation.getCommonName() != null && !this.m_mixLocation.getCommonName().startsWith("<Mix id=")) {
                this.m_nameFragmentForCascade = this.m_mixLocation.getCommonName();
            }
            else {
                this.m_nameFragmentForCascade = null;
            }
        }
        if (this.m_nameFragmentForCascade == null || this.m_nameFragmentForCascade.startsWith("<Mix id=")) {
            if (this.m_name != null) {
                this.m_nameFragmentForCascade = this.m_name;
            }
            else {
                LogHolder.log(4, LogType.MISC, "Could not set cascade name fragment for Mix!");
                this.m_nameFragmentForCascade = "Unknown Mix";
            }
        }
        if (this.m_name == null) {
            if (this.m_mixLocation != null && this.m_mixLocation.getCommonName() != null && !this.m_mixLocation.getCommonName().startsWith("<Mix id=")) {
                this.m_name = this.m_mixLocation.getCommonName();
            }
            else {
                this.m_name = this.m_nameFragmentForCascade;
            }
        }
    }
    
    private void parseListenerAdresses(final Node node) {
        this.parseVisibleAdresses(node, "ListenerInterfaces", "ListenerInterface", this.m_vecListenerAdresses);
    }
    
    private void parseVisibleAdresses(final Node node) {
        final Node firstChildByName = XMLUtil.getFirstChildByName(node, "Proxies");
        if (firstChildByName == null) {
            return;
        }
        for (Node node2 = XMLUtil.getFirstChildByName(firstChildByName, "Proxy"); node2 != null; node2 = node2.getNextSibling()) {
            if (node2.getNodeName().equals("Proxy")) {
                this.parseVisibleAdresses(node2, "VisibleAddresses", "VisibleAddress", this.m_vecVisibleAdresses);
            }
        }
    }
    
    private void parseVisibleAdresses(final Node node, final String s, final String s2, final Vector vector) {
        for (Node node2 = XMLUtil.getFirstChildByName(XMLUtil.getFirstChildByName(node, s), s2); node2 != null; node2 = node2.getNextSibling()) {
            if (node2.getNodeName().equals(s2)) {
                final String value = XMLUtil.parseValue(XMLUtil.getFirstChildByName(node2, "Host"), null);
                if (value != null) {
                    try {
                        final InetAddress byName = InetAddress.getByName(value);
                        if (MixCascadeExitAddresses.isValidAddress(byName) && !vector.contains(byName)) {
                            vector.addElement(byName);
                        }
                    }
                    catch (UnknownHostException ex) {
                        LogHolder.log(6, LogType.NET, ex);
                    }
                    catch (Exception ex2) {
                        LogHolder.log(2, LogType.NET, ex2);
                    }
                }
            }
        }
    }
    
    private int parseMixType(final String s) throws XMLParseException {
        if ("FirstMix".equals(s)) {
            return 0;
        }
        if ("MiddleMix".equals(s)) {
            return 1;
        }
        if ("LastMix".equals(s)) {
            return 2;
        }
        throw new XMLParseException("MixType", "Unkonwn type: " + s);
    }
    
    public boolean isPersistanceDeletionAllowed() {
        return XMLUtil.getStorageMode() == 2;
    }
    
    public void deletePersistence() {
        if (this.isPersistanceDeletionAllowed()) {
            this.m_mixSignature = null;
            this.m_xmlStructure = null;
        }
    }
    
    public Vector getVisibleAddresses() {
        return (Vector)this.m_vecVisibleAdresses.clone();
    }
    
    public Vector getListenerAddresses() {
        return (Vector)this.m_vecListenerAdresses.clone();
    }
    
    public Vector getListenerInterfaces() {
        return (Vector)this.m_vecListenerInterfaces.clone();
    }
    
    public Vector getMonitoringListenerInterfaces() {
        return (Vector)this.m_vecListenerMonitoring.clone();
    }
    
    public String getId() {
        return this.m_mixId;
    }
    
    public boolean isSocks5Supported() {
        return this.m_bSocks;
    }
    
    public boolean isFromCascade() {
        return this.m_bFromCascade;
    }
    
    public long getLastUpdate() {
        return this.m_lastUpdate;
    }
    
    public long getVersionNumber() {
        return this.m_serial;
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public DataRetentionInformation getDataRetentionInformation() {
        return this.m_drInfo;
    }
    
    public boolean isVerified() {
        return this.m_mixCertPath != null && this.m_mixCertPath.isVerified();
    }
    
    public boolean isValid() {
        return this.m_mixCertPath != null && this.m_mixCertPath.isValid(new Date());
    }
    
    public XMLPriceCertificate getPriceCertificate() {
        return this.m_priceCert;
    }
    
    public long getPrepaidInterval() {
        return this.m_prepaidInterval;
    }
    
    public void setPriceCertificate(final XMLPriceCertificate priceCert) {
        this.m_priceCert = priceCert;
    }
    
    public MultiCertPath getCertPath() {
        return this.m_mixCertPath;
    }
    
    public XMLSignature getSignature() {
        return this.m_mixSignature;
    }
    
    public ServiceLocation getServiceLocation() {
        return this.m_mixLocation;
    }
    
    public ServiceOperator getServiceOperator() {
        return this.m_mixOperator;
    }
    
    public ServiceSoftware getServiceSoftware() {
        return this.m_mixSoftware;
    }
    
    public boolean isFreeMix() {
        return this.m_freeMix;
    }
    
    public void setFreeMix(final boolean freeMix) {
        this.m_freeMix = freeMix;
    }
    
    public String getPostFile() {
        String s = "/helo";
        if (this.isFreeMix()) {
            s = "/configure";
        }
        return s;
    }
    
    public Element getXmlStructure() {
        return this.m_xmlStructure;
    }
    
    public int getType() {
        return this.m_type;
    }
    
    public boolean isPayment() {
        return this.m_bPayment;
    }
    
    public String getTypeAsString() {
        switch (this.m_type) {
            case 0: {
                return "First Mix";
            }
            case 1: {
                return "Middle Mix";
            }
            case 2: {
                return "Last Mix";
            }
            default: {
                return "Unknown type!";
            }
        }
    }
    
    public boolean isDynamic() {
        return this.m_dynamic;
    }
    
    public String getFirstHostName() throws Exception {
        for (int i = 0; i < this.m_vecListenerInterfaces.size(); ++i) {
            final ListenerInterface listenerInterface = this.m_vecListenerInterfaces.elementAt(i);
            if (!listenerInterface.isHidden()) {
                return listenerInterface.getHost();
            }
        }
        return "";
    }
    
    public int getFirstPort() throws Exception {
        for (int i = 0; i < this.m_vecListenerInterfaces.size(); ++i) {
            final ListenerInterface listenerInterface = this.m_vecListenerInterfaces.elementAt(i);
            if (!listenerInterface.isHidden()) {
                return listenerInterface.getPort();
            }
        }
        return -1;
    }
    
    public boolean isCascadaNameFragmentUsed() {
        return this.m_bUseCascadeNameFragment;
    }
    
    public String getNameFragmentForCascade() {
        return this.m_nameFragmentForCascade;
    }
    
    public TermsAndConditionsMixInfo getTermsAndConditionMixInfo() {
        return this.m_mixTnCInfo;
    }
    
    public Element getWebInfo(final Document document) {
        if (document == null) {
            return null;
        }
        final Element element = document.createElement("MixWebInfo");
        XMLUtil.setAttribute(element, "payment", this.isPayment());
        XMLUtil.setAttribute(element, "id", this.getId());
        if (this.getCertPath() == null) {
            return null;
        }
        XMLUtil.createChildElementWithValue(element, "Name", this.getName());
        final CertPath path = this.getCertPath().getPath();
        final Element xmlElement = new ServiceOperator(null, this.getCertPath(), 0L).toXMLElement(document);
        final Element xmlElement2 = new ServiceLocation(null, path.getFirstCertificate()).toXMLElement(document);
        if (xmlElement != null) {
            element.appendChild(xmlElement);
        }
        if (xmlElement2 != null) {
            element.appendChild(xmlElement2);
        }
        this.appendListenerInterfaces(element, this.m_vecListenerInterfaces);
        if (this.m_vecListenerMonitoring.size() > 0) {
            this.appendListenerInterfaces(XMLUtil.createChildElement(element, "ServerMonitoring"), this.m_vecListenerMonitoring);
        }
        element.appendChild(this.m_mixCertPath.toXmlElement(document));
        return element;
    }
    
    private void appendListenerInterfaces(final Element element, final Vector vector) {
        final Element childElement = XMLUtil.createChildElement(element, "ListenerInterfaces");
        final Hashtable<String, Element> hashtable = new Hashtable<String, Element>();
        final Hashtable hashtable2 = new Hashtable<String, Element>();
        final Hashtable hashtable3 = new Hashtable<String, Element>();
        for (int i = 0; i < vector.size(); ++i) {
            final ListenerInterface listenerInterface = vector.elementAt(i);
            Element childElement2;
            if (listenerInterface.isHidden() && hashtable2.containsKey(listenerInterface.getHost())) {
                childElement2 = hashtable2.get(listenerInterface.getHost());
            }
            else if (listenerInterface.isVirtual() && hashtable3.containsKey(listenerInterface.getHost())) {
                childElement2 = hashtable3.get(listenerInterface.getHost());
            }
            else if (hashtable.containsKey(listenerInterface.getHost())) {
                childElement2 = hashtable.get(listenerInterface.getHost());
            }
            else {
                childElement2 = XMLUtil.createChildElement(childElement, "ListenerInterface");
                if (listenerInterface.isVirtual()) {
                    XMLUtil.setAttribute(childElement2, "virtual", listenerInterface.isVirtual());
                    hashtable3.put(listenerInterface.getHost(), childElement2);
                }
                else if (listenerInterface.isHidden()) {
                    XMLUtil.setAttribute(childElement2, "hidden", listenerInterface.isHidden());
                    hashtable2.put(listenerInterface.getHost(), childElement2);
                }
                else {
                    hashtable.put(listenerInterface.getHost(), childElement2);
                }
                XMLUtil.setAttribute(childElement2, "Host", listenerInterface.getHost());
            }
            if (listenerInterface.getProtocol() != 5) {
                XMLUtil.createChildElementWithValue(childElement2, "Port", "" + listenerInterface.getPort());
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
}
