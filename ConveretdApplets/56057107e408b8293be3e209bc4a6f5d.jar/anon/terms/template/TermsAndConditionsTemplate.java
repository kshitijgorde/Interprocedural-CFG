// 
// Decompiled by Procyon v0.5.30
// 

package anon.terms.template;

import anon.infoservice.InfoServiceHolder;
import java.util.Enumeration;
import anon.infoservice.AbstractDatabaseEntry;
import anon.infoservice.Database;
import java.util.Date;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import logging.LogHolder;
import logging.LogType;
import anon.util.Util;
import java.io.Writer;
import java.io.StringWriter;
import anon.infoservice.OperatorAddress;
import anon.infoservice.ServiceOperator;
import java.util.Vector;
import java.text.DateFormat;
import java.util.Locale;
import anon.terms.TermsAndConditionsTranslation;
import java.io.IOException;
import java.io.File;
import org.w3c.dom.NodeList;
import anon.terms.TCComponent;
import anon.crypto.SignatureVerifier;
import anon.util.XMLUtil;
import anon.util.XMLParseException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import anon.terms.TCComposite;
import anon.crypto.MultiCertPath;
import anon.crypto.XMLSignature;
import org.w3c.dom.Document;
import anon.infoservice.AbstractDistributableCertifiedDatabaseEntry;

public class TermsAndConditionsTemplate extends AbstractDistributableCertifiedDatabaseEntry
{
    private static final String XML_ATTR_DATE = "date";
    private static final String XML_ATTR_LOCALE = "locale";
    private static final String XML_ATTR_NAME = "name";
    private static final String XML_ATTR_TYPE = "type";
    private static final String[] REQUIRED_ATTRIBUTES;
    private static final String XML_ELEMENT_OPERATOR_COUNTRY = "OperatorCountry";
    private static final String XML_ELEMENT_SIGNATURE = "Sig";
    private static final String XML_ELEMENT_DATE = "Date";
    public static final String INFOSERVICE_PATH = "/tctemplate/";
    public static final String INFOSERVICE_CONTAINER_PATH = "/tctemplates";
    public static final String INFOSERVICE_SERIALS_PATH = "/tctemplateserials";
    public static final String[] REPLACEMENT_ELEMENT_NAMES;
    private static final String XSLT_PATH = "tac.xslt";
    public static String TERMS_AND_CONDITIONS_TYPE_COMMON_LAW;
    public static String TERMS_AND_CONDITIONS_TYPE_GERMAN_LAW;
    public static String TERMS_AND_CONDITIONS_TYPE_GENERAL_LAW;
    public static String XML_ELEMENT_CONTAINER_NAME;
    public static String XML_ELEMENT_NAME;
    private String m_strId;
    private String m_locale;
    private String m_type;
    private String m_date;
    private Document signedDocument;
    private XMLSignature m_signature;
    private MultiCertPath m_certPath;
    private String name;
    private Preamble preamble;
    private TCComposite sections;
    static /* synthetic */ Class class$anon$terms$template$TermsAndConditionsTemplate;
    
    public TermsAndConditionsTemplate(final Element element, final long n) throws XMLParseException {
        this(element);
    }
    
    public TermsAndConditionsTemplate(final Node node) throws XMLParseException {
        super(Long.MAX_VALUE);
        this.m_strId = null;
        this.m_locale = null;
        this.m_type = null;
        this.signedDocument = null;
        this.m_signature = null;
        this.m_certPath = null;
        this.name = "";
        this.preamble = null;
        this.sections = new TCComposite();
        Element documentElement;
        if (node.getNodeType() == 9) {
            documentElement = ((Document)node).getDocumentElement();
        }
        else {
            if (node.getNodeType() != 1) {
                throw new XMLParseException("Invalid node type");
            }
            documentElement = (Element)node;
        }
        this.name = XMLUtil.parseAttribute(documentElement, "name", "");
        this.m_date = XMLUtil.parseAttribute(documentElement, "date", "");
        this.m_locale = XMLUtil.parseAttribute(documentElement, "locale", "");
        this.m_type = XMLUtil.parseAttribute(documentElement, "type", TermsAndConditionsTemplate.TERMS_AND_CONDITIONS_TYPE_COMMON_LAW);
        this.m_strId = this.m_type + "_" + this.m_locale + "_" + this.m_date;
        this.m_signature = SignatureVerifier.getInstance().getVerifiedXml(documentElement, 5);
        if (this.m_signature != null) {
            this.m_certPath = this.m_signature.getMultiCertPath();
            if (node.getNodeType() == 9) {
                this.signedDocument = (Document)node;
            }
            else {
                (this.signedDocument = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.signedDocument, documentElement, true));
            }
        }
        final NodeList elementsByTagName = documentElement.getElementsByTagName(Section.XML_ELEMENT_NAME);
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            this.sections.addTCComponent(new Section(elementsByTagName.item(i)));
        }
        final Node firstChildByName = XMLUtil.getFirstChildByName(documentElement, Preamble.XML_ELEMENT_NAME);
        this.preamble = ((firstChildByName != null) ? new Preamble(firstChildByName) : new Preamble());
    }
    
    public TermsAndConditionsTemplate(final File file) throws XMLParseException, IOException {
        this(XMLUtil.readXMLDocument(file));
    }
    
    public Document createTCDocument(final TermsAndConditionsTranslation termsAndConditionsTranslation) {
        final Document document = XMLUtil.createDocument();
        final Element element = document.createElement(TermsAndConditionsTemplate.XML_ELEMENT_NAME);
        final Element element2 = document.createElement("City");
        final Element element3 = document.createElement("Venue");
        final Element element4 = document.createElement("Date");
        ServiceOperator operator = null;
        OperatorAddress operatorAddress = null;
        final Element element5 = document.createElement("Sig");
        element5.appendChild(element2);
        element5.appendChild(element4);
        final TCComposite sections = this.getSections();
        final String[] array = { this.m_type, this.m_locale, this.m_date, this.m_strId, this.name };
        for (int i = 0; i < TermsAndConditionsTemplate.REQUIRED_ATTRIBUTES.length; ++i) {
            element.setAttribute(TermsAndConditionsTemplate.REQUIRED_ATTRIBUTES[i], array[i]);
        }
        document.appendChild(element);
        if (termsAndConditionsTranslation != null) {
            operator = termsAndConditionsTranslation.getOperator();
            operatorAddress = termsAndConditionsTranslation.getOperatorAddress();
            final Locale locale = new Locale(termsAndConditionsTranslation.getLocale(), operator.getCountryCode());
            final Locale locale2 = new Locale(termsAndConditionsTranslation.getLocale(), "", "");
            if (operatorAddress != null) {
                operatorAddress.setOperatorCountry(locale.getDisplayCountry(locale2));
            }
            final Element xmlElement = operator.toXMLElement(document, operatorAddress, false);
            final Element element6 = document.createElement("OperatorCountry");
            XMLUtil.setValue(element2, (operatorAddress != null) ? termsAndConditionsTranslation.getOperatorAddress().getCity() : "");
            XMLUtil.setValue(element3, (operatorAddress != null) ? termsAndConditionsTranslation.getOperatorAddress().getVenue() : "");
            XMLUtil.setValue(element4, DateFormat.getDateInstance(2, locale2).format(termsAndConditionsTranslation.getDate()));
            xmlElement.appendChild(element6);
            XMLUtil.setValue(element6, locale.getDisplayCountry(locale2));
            final TCComponent[] tcComponents = termsAndConditionsTranslation.getSections().getTCComponents();
            for (int j = 0; j < tcComponents.length; ++j) {
                final Section section = (Section)tcComponents[j];
                final Section section2 = (Section)sections.getTCComponent(section.getId());
                if (!section.hasContent() || section2 == null) {
                    sections.addTCComponent(tcComponents[j]);
                }
                else {
                    if (section.getContent() != null) {
                        section2.setContent(section.getContent());
                    }
                    final TCComponent[] tcComponents2 = section.getTCComponents();
                    for (int k = 0; k < tcComponents2.length; ++k) {
                        section2.addTCComponent(tcComponents2[k]);
                    }
                }
            }
            final String[] array2 = { termsAndConditionsTranslation.getPrivacyPolicyUrl(), termsAndConditionsTranslation.getLegalOpinionsUrl(), termsAndConditionsTranslation.getOperationalAgreementUrl() };
            final Vector<Element> vector = new Vector<Element>();
            for (int l = 0; l < array2.length; ++l) {
                final Element element7 = document.createElement(TermsAndConditionsTemplate.REPLACEMENT_ELEMENT_NAMES[l]);
                element7.appendChild(document.createTextNode(array2[l]));
                vector.addElement(element7);
            }
            vector.addElement(xmlElement);
            vector.addElement(element6);
            vector.addElement(element3);
            final NodeList nodeList = new NodeList() {
                private final /* synthetic */ Vector val$replaceNodes = vector;
                
                public int getLength() {
                    return this.val$replaceNodes.size();
                }
                
                public Node item(final int n) {
                    return this.val$replaceNodes.elementAt(n);
                }
            };
            final TCComponent[] tcComponents3 = sections.getTCComponents();
            for (int n = 0; n < tcComponents3.length; ++n) {
                ((Section)tcComponents3[n]).replaceElementNodes(nodeList);
            }
        }
        this.preamble.setOperator(operator);
        this.preamble.setOperatorAddress(operatorAddress);
        element.appendChild(this.preamble.toXmlElement(document));
        final Element element8 = document.createElement(Section.XML_ELEMENT_CONTAINER_NAME);
        final TCComponent[] tcComponents4 = sections.getTCComponents();
        for (int n2 = 0; n2 < tcComponents4.length; ++n2) {
            final Element xmlElement2 = ((Section)tcComponents4[n2]).toXmlElement(document);
            if (xmlElement2 != null) {
                element8.appendChild(xmlElement2);
            }
        }
        element.appendChild(element8);
        element.appendChild(element5);
        return document;
    }
    
    public String transform(final TermsAndConditionsTranslation termsAndConditionsTranslation) {
        try {
            final StringWriter stringWriter = new StringWriter();
            this.transform(stringWriter, termsAndConditionsTranslation);
            stringWriter.close();
            return Util.replaceAll(stringWriter.toString(), "<br/>", "<br>");
        }
        catch (IOException ex2) {
            LogHolder.log(3, LogType.MISC, "IOException caught while transforming terms and conditions.");
            return null;
        }
        catch (TransformerException ex) {
            LogHolder.log(3, LogType.MISC, "Could not transform terms and conditions.");
            ex.printStackTrace();
            return null;
        }
    }
    
    public void transform(final Writer writer, final TermsAndConditionsTranslation termsAndConditionsTranslation) throws IOException, TransformerException {
        TransformerFactory.newInstance().newTransformer(new StreamSource(this.getClass().getResourceAsStream("tac.xslt"))).transform(new DOMSource(this.createTCDocument(termsAndConditionsTranslation)), new StreamResult(writer));
    }
    
    public String getType() {
        return this.m_type;
    }
    
    public String getId() {
        return this.m_strId;
    }
    
    public long getLastUpdate() {
        return 0L;
    }
    
    public long getVersionNumber() {
        return 0L;
    }
    
    public String getLanguage() {
        return this.m_locale;
    }
    
    public String getDate() {
        return this.m_date;
    }
    
    public String getPostFile() {
        return "/posttcframework";
    }
    
    public boolean isVerified() {
        return this.m_signature != null && this.m_signature.isVerified();
    }
    
    public boolean isValid() {
        return this.m_certPath != null && this.m_certPath.isValid(new Date());
    }
    
    public TCComposite getSections() {
        return (TCComposite)this.sections.clone();
    }
    
    public static synchronized void store(final Element element) {
        Element element2 = (Element)XMLUtil.getFirstChildByName(element, TermsAndConditionsTemplate.XML_ELEMENT_NAME);
        while (element2 != null) {
            try {
                Database.getInstance((TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate == null) ? (TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate = class$("anon.terms.template.TermsAndConditionsTemplate")) : TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate).update(new TermsAndConditionsTemplate(element2));
                element2 = (Element)XMLUtil.getNextSiblingByName(element2, TermsAndConditionsTemplate.XML_ELEMENT_NAME);
            }
            catch (XMLParseException ex) {
                LogHolder.log(3, LogType.MISC, "one tc templates could not be stored in the DB");
            }
        }
    }
    
    public static synchronized Enumeration getAllStoredRefIDs() {
        return new Enumeration() {
            private final /* synthetic */ Enumeration val$e = Database.getInstance((TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate == null) ? (TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate = class$("anon.terms.template.TermsAndConditionsTemplate")) : TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate).getEntryList().elements();
            
            public boolean hasMoreElements() {
                return this.val$e.hasMoreElements();
            }
            
            public Object nextElement() {
                return this.val$e.nextElement().getId();
            }
        };
    }
    
    public static void loadFromDirectory(final File file) {
        if (file == null) {
            return;
        }
        final String[] list = file.list();
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.length; ++i) {
            try {
                Database.getInstance((TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate == null) ? (TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate = class$("anon.terms.template.TermsAndConditionsTemplate")) : TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate).update(new TermsAndConditionsTemplate(new File(file.getAbsolutePath() + File.separator + list[i])));
            }
            catch (XMLParseException ex) {
                LogHolder.log(2, LogType.MISC, "XMLParseException while loading Terms & Conditions: ", ex);
            }
            catch (IOException ex2) {
                LogHolder.log(2, LogType.MISC, "IOException while loading Terms & Conditions: ", ex2);
            }
        }
    }
    
    public static TermsAndConditionsTemplate getById(final String s, final boolean b) {
        final TermsAndConditionsTemplate termsAndConditionsTemplate = (TermsAndConditionsTemplate)Database.getInstance((TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate == null) ? (TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate = class$("anon.terms.template.TermsAndConditionsTemplate")) : TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate).getEntryById(s);
        if (!b || termsAndConditionsTemplate != null) {
            return termsAndConditionsTemplate;
        }
        final TermsAndConditionsTemplate tcTemplate = InfoServiceHolder.getInstance().getTCTemplate(s);
        Database.getInstance((TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate == null) ? (TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate = class$("anon.terms.template.TermsAndConditionsTemplate")) : TermsAndConditionsTemplate.class$anon$terms$template$TermsAndConditionsTemplate).update(tcTemplate);
        return tcTemplate;
    }
    
    public boolean equals(final Object o) {
        boolean equals = false;
        if (o != null && o instanceof TermsAndConditionsTemplate) {
            equals = this.getId().equals(((TermsAndConditionsTemplate)o).getId());
        }
        return equals;
    }
    
    public int hashCode() {
        return this.getId().hashCode();
    }
    
    public XMLSignature getSignature() {
        return this.m_signature;
    }
    
    public MultiCertPath getCertPath() {
        return this.m_certPath;
    }
    
    public Document getDocument() {
        return (this.signedDocument != null) ? this.signedDocument : this.createTCDocument(null);
    }
    
    public Document getSignedDocument() {
        return this.signedDocument;
    }
    
    public void setSignedDocument(final Document signedDocument) {
        this.signedDocument = signedDocument;
    }
    
    public Element getXmlStructure() {
        return this.getDocument().getDocumentElement();
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
        REQUIRED_ATTRIBUTES = new String[] { "type", "locale", "date", "id", "name" };
        REPLACEMENT_ELEMENT_NAMES = new String[] { "PrivacyPolicyUrl", "LegalOpinionsUrl", "OperationalAgreementUrl" };
        TermsAndConditionsTemplate.TERMS_AND_CONDITIONS_TYPE_COMMON_LAW = "CommonLaw";
        TermsAndConditionsTemplate.TERMS_AND_CONDITIONS_TYPE_GERMAN_LAW = "GermanLaw";
        TermsAndConditionsTemplate.TERMS_AND_CONDITIONS_TYPE_GENERAL_LAW = "GeneralLaw";
        TermsAndConditionsTemplate.XML_ELEMENT_CONTAINER_NAME = "TermsAndConditionsTemplates";
        TermsAndConditionsTemplate.XML_ELEMENT_NAME = "TermsAndConditionsTemplate";
    }
}
