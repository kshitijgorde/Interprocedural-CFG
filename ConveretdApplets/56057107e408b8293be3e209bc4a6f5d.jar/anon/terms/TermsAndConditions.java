// 
// Decompiled by Procyon v0.5.30
// 

package anon.terms;

import java.lang.reflect.Method;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import org.w3c.dom.NodeList;
import anon.terms.template.Section;
import anon.crypto.SignatureVerifier;
import anon.crypto.MultiCertPath;
import anon.crypto.XMLSignature;
import anon.infoservice.OperatorAddress;
import anon.util.JAPMessages;
import anon.terms.template.TermsAndConditionsTemplate;
import logging.LogHolder;
import logging.LogType;
import org.w3c.dom.Document;
import java.util.Enumeration;
import java.util.Locale;
import anon.infoservice.Database;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import java.security.SignatureException;
import anon.util.XMLParseException;
import org.w3c.dom.Element;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Date;
import anon.infoservice.ServiceOperator;
import anon.util.IXMLEncodable;

public class TermsAndConditions implements IXMLEncodable
{
    public static final String XML_ATTR_ACCEPTED = "accepted";
    public static final String XML_ATTR_DATE = "date";
    private static final String MSG_DISPLAY_ERROR;
    public static final String XML_ELEMENT_CONTAINER_NAME = "TermsAndConditionsList";
    public static final String XML_ELEMENT_NAME = "TermsAndConditions";
    public static final String XML_ELEMENT_TRANSLATION_NAME = "TCTranslation";
    public static final String DATE_FORMAT = "yyyyMMdd";
    private ServiceOperator operator;
    private Date m_date;
    private Hashtable translations;
    private Translation defaultTl;
    private boolean accepted;
    private static final Hashtable tcHashtable;
    static /* synthetic */ Class class$anon$terms$TermsAndConditions;
    static /* synthetic */ Class class$anon$infoservice$ServiceOperator;
    
    public TermsAndConditions(final ServiceOperator serviceOperator, final String s) throws ParseException {
        this(serviceOperator, new SimpleDateFormat("yyyyMMdd").parse(s));
    }
    
    public TermsAndConditions(final ServiceOperator operator, final Date date) throws ParseException {
        this.defaultTl = null;
        if (operator == null) {
            throw new NullPointerException("Operator of terms and conditions must not be null!");
        }
        this.operator = operator;
        if (date == null) {
            throw new NullPointerException("Date of terms and conditions must not be null!");
        }
        this.m_date = date;
        if (this.m_date == null) {
            throw new IllegalArgumentException("Date has not the valid format yyyyMMdd");
        }
        this.translations = new Hashtable();
        this.accepted = false;
    }
    
    public TermsAndConditions(final Element element) throws XMLParseException, ParseException, SignatureException {
        this(element, null, true);
    }
    
    public TermsAndConditions(final Element element, final ServiceOperator operator, final boolean b) throws XMLParseException, ParseException, SignatureException {
        this.defaultTl = null;
        if (operator != null) {
            this.operator = operator;
        }
        else {
            final String attribute = XMLUtil.parseAttribute(element, "id", null);
            if (attribute == null) {
                throw new XMLParseException("attribute 'id' of TermsAndConditions must not be null!");
            }
            final String upperCase = attribute.toUpperCase();
            this.operator = (ServiceOperator)Database.getInstance((TermsAndConditions.class$anon$infoservice$ServiceOperator == null) ? (TermsAndConditions.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : TermsAndConditions.class$anon$infoservice$ServiceOperator).getEntryById(upperCase);
            if (this.operator == null) {
                throw new XMLParseException("invalid  id " + upperCase + ": no operator found with this subject key identifier");
            }
        }
        final String attribute2 = XMLUtil.parseAttribute(element, "date", null);
        if (attribute2 == null) {
            throw new XMLParseException("attribute 'date' must not be null!");
        }
        this.m_date = new SimpleDateFormat("yyyyMMdd").parse(attribute2);
        this.translations = new Hashtable();
        for (Element element2 = (Element)XMLUtil.getFirstChildByName(element, "TCTranslation"); element2 != null; element2 = (Element)XMLUtil.getNextSiblingByName(element2, "TCTranslation")) {
            this.addTranslation(new Translation(element2), b);
        }
        this.accepted = XMLUtil.parseAttribute(element, "accepted", false);
    }
    
    public String getDateString() {
        return new SimpleDateFormat("yyyyMMdd").format(this.m_date);
    }
    
    public void addTranslation(final Element element) throws XMLParseException, SignatureException {
        this.addTranslation(new Translation(element), true);
    }
    
    public TermsAndConditionsTranslation removeTranslation(final String s) {
        final Translation translation = this.translations.remove(s.trim().toLowerCase());
        if (this.defaultTl == translation) {
            this.defaultTl = null;
        }
        return translation;
    }
    
    public TermsAndConditionsTranslation removeTranslation(final Locale locale) {
        return this.removeTranslation(locale.getLanguage());
    }
    
    public TermsAndConditionsTranslation initializeEmptyTranslation(final String s) {
        final Translation translation = new Translation();
        translation.setLocale(s.trim().toLowerCase());
        try {
            this.addTranslation(translation, false);
        }
        catch (SignatureException ex) {}
        return translation;
    }
    
    public TermsAndConditionsTranslation initializeEmptyTranslation(final Locale locale) {
        return this.initializeEmptyTranslation(locale.getLanguage());
    }
    
    private synchronized void addTranslation(final Translation defaultTl, final boolean b) throws SignatureException {
        if (b) {
            if (!defaultTl.isVerified()) {
                throw new SignatureException("Translation [" + defaultTl.getLocale() + "] of " + this.operator.getOrganization() + " is not verified");
            }
            if (!defaultTl.checkId()) {
                throw new SignatureException("Translation [" + defaultTl.getLocale() + "] is not signed by its operator '" + this.operator.getOrganization() + "'");
            }
        }
        synchronized (this) {
            if (defaultTl.isDefaultTranslation()) {
                this.defaultTl = defaultTl;
            }
        }
        this.translations.put(defaultTl.getLocale(), defaultTl);
    }
    
    public synchronized TermsAndConditionsTranslation getDefaultTranslation() {
        return this.defaultTl;
    }
    
    public TermsAndConditionsTranslation getTranslation(final Locale locale) {
        return this.getTranslation(locale.getLanguage());
    }
    
    public TermsAndConditionsTranslation getTranslation(final String s) {
        return this.translations.get(s.trim().toLowerCase());
    }
    
    public Enumeration getAllTranslations() {
        return this.translations.elements();
    }
    
    public String getTemplateReferenceId(final String s) {
        final Translation translation = this.translations.get(s.trim().toLowerCase());
        return (translation != null) ? translation.getTemplateReferenceId() : null;
    }
    
    public boolean hasTranslation(final String s) {
        return this.translations.containsKey(s.trim().toLowerCase());
    }
    
    public boolean hasTranslation(final Locale locale) {
        return this.hasTranslation(locale.getLanguage());
    }
    
    public boolean hasTranslations() {
        return !this.translations.isEmpty();
    }
    
    public synchronized boolean hasDefaultTranslation() {
        return this.defaultTl != null;
    }
    
    public ServiceOperator getOperator() {
        return this.operator;
    }
    
    public void setDate(final Date date) {
        this.m_date = date;
    }
    
    public Date getDate() {
        return this.m_date;
    }
    
    public synchronized void setAccepted(final boolean accepted) {
        this.accepted = accepted;
    }
    
    public boolean isAccepted() {
        return this.accepted;
    }
    
    public static void storeTermsAndConditions(final TermsAndConditions termsAndConditions) {
        TermsAndConditions.tcHashtable.put(termsAndConditions.operator, termsAndConditions);
    }
    
    public static TermsAndConditions getTermsAndConditions(final ServiceOperator serviceOperator) {
        return TermsAndConditions.tcHashtable.get(serviceOperator);
    }
    
    public static void removeTermsAndConditions(final TermsAndConditions termsAndConditions) {
        TermsAndConditions.tcHashtable.remove(termsAndConditions.operator);
    }
    
    public static void removeTermsAndConditions(final ServiceOperator serviceOperator) {
        TermsAndConditions.tcHashtable.remove(serviceOperator);
    }
    
    public static Element getAllTermsAndConditionsAsXMLElement(final Document document) {
        final Element element = document.createElement("TermsAndConditionsList");
        final Enumeration<TermsAndConditions> elements = TermsAndConditions.tcHashtable.elements();
        while (elements.hasMoreElements()) {
            final TermsAndConditions termsAndConditions = elements.nextElement();
            if (termsAndConditions.hasTranslations()) {
                element.appendChild(termsAndConditions.toXmlElement(document));
            }
        }
        return element;
    }
    
    public static void loadTermsAndConditionsFromXMLElement(final Element element) {
        if (element == null) {
            LogHolder.log(4, LogType.MISC, "TC list root is null!");
            return;
        }
        for (Element element2 = (Element)XMLUtil.getFirstChildByName(element, "TermsAndConditions"); element2 != null; element2 = (Element)XMLUtil.getNextSiblingByName(element2, "TermsAndConditions")) {
            try {
                storeTermsAndConditions(new TermsAndConditions(element2));
            }
            catch (XMLParseException ex) {
                LogHolder.log(4, LogType.MISC, "XML error occured while parsing the TC node:", ex);
            }
            catch (ParseException ex2) {
                LogHolder.log(4, LogType.MISC, "Could not parse the TC node:", ex2);
            }
            catch (SignatureException ex3) {
                LogHolder.log(4, LogType.MISC, "Terms and Condition cannot be loaded due to a wrong signature:", ex3);
            }
        }
    }
    
    public String getHTMLText(final Locale locale) {
        return this.getHTMLText(locale.getLanguage());
    }
    
    public String getHTMLText(final String s) {
        TermsAndConditionsTranslation termsAndConditionsTranslation = this.getTranslation(s);
        if (termsAndConditionsTranslation == null) {
            termsAndConditionsTranslation = this.getDefaultTranslation();
        }
        return getHTMLText(termsAndConditionsTranslation);
    }
    
    public static String getHTMLText(final TermsAndConditionsTranslation termsAndConditionsTranslation) {
        try {
            if (termsAndConditionsTranslation == null) {
                throw new NullPointerException("Translation is null!)");
            }
            final TermsAndConditionsTemplate byId = TermsAndConditionsTemplate.getById(termsAndConditionsTranslation.getTemplateReferenceId(), false);
            if (byId == null) {
                throw new NullPointerException("Associated template '" + termsAndConditionsTranslation.getTemplateReferenceId() + "' for" + " translation [" + termsAndConditionsTranslation.getLocale() + "] of terms and conditions for operator '" + termsAndConditionsTranslation.getOperator().getOrganization() + "' not found.");
            }
            return byId.transform(termsAndConditionsTranslation);
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.MISC, ex);
            final StringBuffer sb = new StringBuffer();
            sb.append("<html><head><title>");
            sb.append(JAPMessages.getString("error"));
            sb.append("</title></head><body><head><h1>");
            sb.append(JAPMessages.getString("error"));
            sb.append("</h1><h2>");
            sb.append(JAPMessages.getString(TermsAndConditions.MSG_DISPLAY_ERROR));
            sb.append("</h2><p>");
            sb.append(ex);
            sb.append("</p></body></html>");
            return sb.toString();
        }
    }
    
    public boolean isSignatureObsolete() {
        return false;
    }
    
    public boolean equals(final Object o) {
        return this.operator.equals(((TermsAndConditions)o).operator);
    }
    
    public int compareTo(final Object o) {
        final TermsAndConditions termsAndConditions = (TermsAndConditions)o;
        return this.m_date.equals(termsAndConditions.getDate()) ? 0 : (this.m_date.before(termsAndConditions.getDate()) ? -1 : 1);
    }
    
    public boolean isMostRecent(final String s) throws ParseException {
        return this.isMostRecent(new SimpleDateFormat("yyyyMMdd").parse(s));
    }
    
    public boolean isMostRecent(final Date date) {
        return this.m_date.equals(date) || this.m_date.after(date);
    }
    
    private Element xmlOut(final Document document, final boolean b) {
        final Element tcRoot = this.createTCRoot(document);
        Enumeration elements = null;
        while (true) {
            Label_0097: {
                synchronized (this) {
                    if (this.accepted) {
                        XMLUtil.setAttribute(tcRoot, "accepted", this.accepted);
                    }
                    elements = this.translations.elements();
                    break Label_0097;
                }
                tcRoot.appendChild(b ? elements.nextElement().toXmlElement(document) : elements.nextElement().createXMLOutput(document));
            }
            if (!elements.hasMoreElements()) {
                return tcRoot;
            }
            continue;
        }
    }
    
    public Element createTCRoot(final Document document) {
        final Element element = document.createElement("TermsAndConditions");
        XMLUtil.setAttribute(element, "id", this.operator.getId());
        XMLUtil.setAttribute(element, "date", this.getDateString());
        return element;
    }
    
    public Element toXmlElement(final Document document) {
        return this.xmlOut(document, true);
    }
    
    public Element createXMLOutput(final Document document) {
        return this.xmlOut(document, false);
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
        MSG_DISPLAY_ERROR = ((TermsAndConditions.class$anon$terms$TermsAndConditions == null) ? (TermsAndConditions.class$anon$terms$TermsAndConditions = class$("anon.terms.TermsAndConditions")) : TermsAndConditions.class$anon$terms$TermsAndConditions).getName() + "_displayError";
        tcHashtable = new Hashtable();
    }
    
    private class Translation implements IXMLEncodable, TermsAndConditionsTranslation
    {
        private String templateReferenceId;
        private String locale;
        private boolean defaultTranslation;
        private Element translationElement;
        private String privacyPolicyUrl;
        private String legalOpinionsUrl;
        private String operationalAgreementUrl;
        private OperatorAddress operatorAddress;
        private XMLSignature signature;
        private MultiCertPath certPath;
        private TCComposite sections;
        static /* synthetic */ Class class$anon$infoservice$OperatorAddress;
        
        Translation(final TermsAndConditions termsAndConditions, final Element element) throws XMLParseException {
            this(termsAndConditions, element, true);
        }
        
        private Translation() {
            this.signature = null;
            this.certPath = null;
            this.sections = new TCComposite();
        }
        
        private Translation(final Element translationElement, final boolean b) throws XMLParseException {
            this.signature = null;
            this.certPath = null;
            this.sections = new TCComposite();
            this.templateReferenceId = XMLUtil.parseAttribute(translationElement, "referenceId", "");
            if (b && this.templateReferenceId.equals("")) {
                LogHolder.log(4, LogType.MISC, "TC translation must refer to a valid TC template");
            }
            this.locale = XMLUtil.parseAttribute(translationElement, "locale", "");
            if (b && this.locale.equals("")) {
                throw new XMLParseException("TC translation must set attribute 'locale'");
            }
            this.locale = this.locale.trim().toLowerCase();
            this.setDefaultTranslation(XMLUtil.parseAttribute(translationElement, "default", false));
            this.privacyPolicyUrl = XMLUtil.parseValue(XMLUtil.getFirstChildByName(translationElement, "PrivacyPolicyUrl"), "");
            this.legalOpinionsUrl = XMLUtil.parseValue(XMLUtil.getFirstChildByName(translationElement, "LegalOpinionsUrl"), "");
            this.operationalAgreementUrl = XMLUtil.parseValue(XMLUtil.getFirstChildByName(translationElement, "OperationalAgreementUrl"), "");
            final Element element = (Element)XMLUtil.getFirstChildByName(translationElement, "Operator");
            if (element != null) {
                this.operatorAddress = new OperatorAddress(element);
            }
            else {
                this.operatorAddress = null;
            }
            this.translationElement = translationElement;
            this.signature = SignatureVerifier.getInstance().getVerifiedXml(translationElement, 1);
            if (this.signature != null) {
                this.certPath = this.signature.getMultiCertPath();
            }
            final NodeList elementsByTagName = translationElement.getElementsByTagName(Section.XML_ELEMENT_NAME);
            for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                this.sections.addTCComponent(new Section(elementsByTagName.item(i)));
            }
        }
        
        public boolean hasContent() {
            return this.sections.hasContent();
        }
        
        public void setTemplateReferenceId(final String templateReferenceId) {
            this.templateReferenceId = templateReferenceId;
        }
        
        public String getTemplateReferenceId() {
            return this.templateReferenceId;
        }
        
        public void setLocale(final String locale) {
            this.locale = locale;
        }
        
        public String getLocale() {
            return this.locale;
        }
        
        public boolean isDefaultTranslation() {
            return this.defaultTranslation;
        }
        
        public void setDefaultTranslation(final boolean defaultTranslation) {
            this.defaultTranslation = defaultTranslation;
            if (defaultTranslation) {
                if (TermsAndConditions.this.defaultTl != null) {
                    TermsAndConditions.this.defaultTl.setDefaultTranslation(false);
                }
                TermsAndConditions.this.defaultTl = this;
            }
        }
        
        public Element getTranslationElement() {
            return (this.translationElement != null) ? ((Element)this.translationElement.cloneNode(true)) : null;
        }
        
        public XMLSignature getSignature() {
            return this.signature;
        }
        
        public MultiCertPath getCertPath() {
            return this.certPath;
        }
        
        public boolean isVerified() {
            return this.signature != null && this.signature.isVerified();
        }
        
        public boolean isValid() {
            return this.certPath != null && this.certPath.isValid(new Date());
        }
        
        public boolean checkId() {
            return this.certPath != null && this.certPath.getPath().getSecondCertificate().getSubjectKeyIdentifierConcatenated().equals(this.getOperator().getId());
        }
        
        public boolean equals(final Object o) {
            return o != null && o instanceof TermsAndConditionsTranslation && this.locale.equals(((Translation)o).locale);
        }
        
        public Element toXmlElement(final Document document) {
            if (document.equals(this.translationElement.getOwnerDocument())) {
                return this.translationElement;
            }
            try {
                return (Element)XMLUtil.importNode(document, this.translationElement, true);
            }
            catch (XMLParseException ex) {
                return null;
            }
        }
        
        public Element createXMLOutput(final Document document) {
            final Element element = document.createElement("TCTranslation");
            element.setAttribute("referenceId", this.templateReferenceId);
            element.setAttribute("locale", this.locale);
            if (this.defaultTranslation) {
                element.setAttribute("default", "true");
            }
            if (this.privacyPolicyUrl != null && !this.privacyPolicyUrl.equals("")) {
                XMLUtil.createChildElementWithValue(element, "PrivacyPolicyUrl", this.privacyPolicyUrl);
            }
            if (this.legalOpinionsUrl != null && !this.legalOpinionsUrl.equals("")) {
                XMLUtil.createChildElementWithValue(element, "LegalOpinionsUrl", this.legalOpinionsUrl);
            }
            if (this.operationalAgreementUrl != null && !this.operationalAgreementUrl.equals("")) {
                XMLUtil.createChildElementWithValue(element, "OperationalAgreementUrl", this.operationalAgreementUrl);
            }
            if (this.operatorAddress != null) {
                final Enumeration addressAsNodeList = this.operatorAddress.getAddressAsNodeList(document);
                Node element2 = null;
                if (addressAsNodeList.hasMoreElements()) {
                    element2 = document.createElement("Operator");
                    element.appendChild(element2);
                }
                while (addressAsNodeList.hasMoreElements()) {
                    element2.appendChild(addressAsNodeList.nextElement());
                }
            }
            if (this.sections != null) {
                final TCComponent[] tcComponents = this.sections.getTCComponents();
                for (int i = 0; i < tcComponents.length; ++i) {
                    final Element xmlElement = ((Section)tcComponents[i]).toXmlElement(document, true);
                    if (xmlElement != null) {
                        element.appendChild(xmlElement);
                    }
                }
            }
            return element;
        }
        
        public void setOperatorAddress(final OperatorAddress operatorAddress) {
            this.operatorAddress = operatorAddress;
        }
        
        public OperatorAddress getOperatorAddress() {
            return this.operatorAddress;
        }
        
        public String toString() {
            return new Locale(this.locale, "").getDisplayLanguage(JAPMessages.getLocale()) + (this.defaultTranslation ? " (default)" : "");
        }
        
        public Date getDate() {
            return TermsAndConditions.this.getDate();
        }
        
        public ServiceOperator getOperator() {
            return TermsAndConditions.this.operator;
        }
        
        public String getPrivacyPolicyUrl() {
            return this.privacyPolicyUrl;
        }
        
        public void setPrivacyPolicyUrl(final String privacyPolicyUrl) {
            this.privacyPolicyUrl = privacyPolicyUrl;
        }
        
        public String getLegalOpinionsUrl() {
            return this.legalOpinionsUrl;
        }
        
        public void setLegalOpinionsUrl(final String legalOpinionsUrl) {
            this.legalOpinionsUrl = legalOpinionsUrl;
        }
        
        public String getOperationalAgreementUrl() {
            return this.operationalAgreementUrl;
        }
        
        public void setOperationalAgreementUrl(final String operationalAgreementUrl) {
            this.operationalAgreementUrl = operationalAgreementUrl;
        }
        
        public void setSections(final TCComposite sections) {
            this.sections = sections;
        }
        
        public TCComposite getSections() {
            return (TCComposite)this.sections.clone();
        }
        
        public TermsAndConditionsTranslation duplicateWithImports(final Element element) {
            try {
                final Translation translation = new Translation(element, false);
                final PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(this.getClass()).getPropertyDescriptors();
                for (int i = 0; i < propertyDescriptors.length; ++i) {
                    if (!propertyDescriptors[i].getName().equals("operatorAddress") && !propertyDescriptors[i].getName().equals("defaultTranslation") && propertyDescriptors[i].getWriteMethod() != null) {
                        final Method readMethod = propertyDescriptors[i].getReadMethod();
                        final Method writeMethod = propertyDescriptors[i].getWriteMethod();
                        final Object invoke = readMethod.invoke(this, (Object[])null);
                        if (invoke != null && !invoke.toString().equals("")) {
                            writeMethod.invoke(translation, invoke);
                        }
                    }
                }
                if (translation.getOperator() == null) {
                    translation.setOperatorAddress(this.operatorAddress);
                }
                else if (this.operatorAddress != null) {
                    final PropertyDescriptor[] propertyDescriptors2 = Introspector.getBeanInfo((Translation.class$anon$infoservice$OperatorAddress == null) ? (Translation.class$anon$infoservice$OperatorAddress = class$("anon.infoservice.OperatorAddress")) : Translation.class$anon$infoservice$OperatorAddress).getPropertyDescriptors();
                    for (int j = 0; j < propertyDescriptors2.length; ++j) {
                        if (propertyDescriptors2[j].getWriteMethod() != null) {
                            final Method readMethod2 = propertyDescriptors2[j].getReadMethod();
                            final Method writeMethod2 = propertyDescriptors2[j].getWriteMethod();
                            final Object invoke2 = readMethod2.invoke(this.operatorAddress, (Object[])null);
                            if (invoke2 != null && !invoke2.toString().equals("")) {
                                writeMethod2.invoke(translation.operatorAddress, invoke2);
                            }
                        }
                    }
                }
                return translation;
            }
            catch (XMLParseException ex) {}
            catch (IntrospectionException ex2) {}
            catch (IllegalArgumentException ex3) {}
            catch (IllegalAccessException ex4) {}
            catch (InvocationTargetException ex5) {}
            return null;
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
