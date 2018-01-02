// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import anon.util.Util;
import org.w3c.dom.NodeList;
import java.util.Enumeration;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import anon.util.XMLUtil;
import java.util.Hashtable;
import java.util.Vector;
import anon.util.IXMLEncodable;

public class XMLPaymentOption implements IXMLEncodable
{
    public static final int MAX_CLICKS_UNLIMITED = Integer.MAX_VALUE;
    public static final String OPTION_ACTIVE = "active";
    public static final String OPTION_PASSIVE = "passive";
    public static final String OPTION_MIXED = "mixed";
    public static final String EXTRA_TEXT = "text";
    public static final String EXTRA_LINK = "link";
    public static final String EXTRA_PHONE = "phone";
    private static final String XML_ATTR_MAXCLICKS = "maxclicks";
    private static final String EXCEPTION_WRONG_XML_STRUCTURE = "XMLPaymentOption wrong XML structure";
    private static Vector m_languages;
    private String m_name;
    private String m_type;
    private int m_markup;
    private boolean m_generic;
    private Vector m_headings;
    private Vector m_detailedInfos;
    private Hashtable m_ranks;
    private Vector m_paymentDelays;
    private Vector m_extraInfos;
    private Vector m_inputFields;
    private String m_imageLink;
    private String m_minJapVersion;
    
    public XMLPaymentOption(final String s) throws Exception {
        this.m_headings = new Vector();
        this.m_detailedInfos = new Vector();
        this.m_ranks = new Hashtable();
        this.m_paymentDelays = new Vector();
        this.m_extraInfos = new Vector();
        this.m_inputFields = new Vector();
        this.setValues(XMLUtil.toXMLDocument(s).getDocumentElement());
    }
    
    public XMLPaymentOption() {
        this.m_headings = new Vector();
        this.m_detailedInfos = new Vector();
        this.m_ranks = new Hashtable();
        this.m_paymentDelays = new Vector();
        this.m_extraInfos = new Vector();
        this.m_inputFields = new Vector();
    }
    
    public XMLPaymentOption(final String name, final String type, final boolean generic) {
        this.m_headings = new Vector();
        this.m_detailedInfos = new Vector();
        this.m_ranks = new Hashtable();
        this.m_paymentDelays = new Vector();
        this.m_extraInfos = new Vector();
        this.m_inputFields = new Vector();
        this.m_name = name;
        this.m_type = type;
        this.m_generic = generic;
    }
    
    public XMLPaymentOption(final String name, final String type, final boolean generic, final String minJapVersion) {
        this.m_headings = new Vector();
        this.m_detailedInfos = new Vector();
        this.m_ranks = new Hashtable();
        this.m_paymentDelays = new Vector();
        this.m_extraInfos = new Vector();
        this.m_inputFields = new Vector();
        this.m_name = name;
        this.m_type = type;
        this.m_generic = generic;
        this.m_minJapVersion = minJapVersion;
    }
    
    public XMLPaymentOption(final String name, final String type, final boolean generic, final String minJapVersion, final int markup) {
        this.m_headings = new Vector();
        this.m_detailedInfos = new Vector();
        this.m_ranks = new Hashtable();
        this.m_paymentDelays = new Vector();
        this.m_extraInfos = new Vector();
        this.m_inputFields = new Vector();
        this.m_name = name;
        this.m_type = type;
        this.m_generic = generic;
        this.m_minJapVersion = minJapVersion;
        this.m_markup = markup;
    }
    
    public XMLPaymentOption(final String name, final String type) {
        this.m_headings = new Vector();
        this.m_detailedInfos = new Vector();
        this.m_ranks = new Hashtable();
        this.m_paymentDelays = new Vector();
        this.m_extraInfos = new Vector();
        this.m_inputFields = new Vector();
        this.m_name = name;
        this.m_type = type;
        this.m_generic = true;
    }
    
    public void addHeading(final String s, final String s2) {
        this.m_headings.addElement(new String[] { s, s2 });
        addLanguage(s2);
    }
    
    public void addDetailedInfo(final String s, final String s2) {
        this.m_detailedInfos.addElement(new String[] { s, s2 });
        addLanguage(s2);
    }
    
    public void addRank(final int n, final String s) {
        this.m_ranks.put(s, new Integer(n));
    }
    
    public void addPaymentDelay(final String s, final String s2) {
        this.m_paymentDelays.addElement(new String[] { s, s2 });
        addLanguage(s2);
    }
    
    public void addExtraInfo(final String s, final String s2, final String s3) {
        this.m_extraInfos.addElement(new String[] { s, s2, s3 });
        addLanguage(s3);
    }
    
    public void addInputField(final String s, final String s2, final String s3) {
        this.m_inputFields.addElement(new String[] { s, s2, s3 });
        addLanguage(s3);
    }
    
    public void setImageLink(final String imageLink) {
        this.m_imageLink = imageLink;
    }
    
    public XMLPaymentOption(final Element values) throws Exception {
        this.m_headings = new Vector();
        this.m_detailedInfos = new Vector();
        this.m_ranks = new Hashtable();
        this.m_paymentDelays = new Vector();
        this.m_extraInfos = new Vector();
        this.m_inputFields = new Vector();
        this.setValues(values);
    }
    
    public XMLPaymentOption(final Document document) throws Exception {
        this.m_headings = new Vector();
        this.m_detailedInfos = new Vector();
        this.m_ranks = new Hashtable();
        this.m_paymentDelays = new Vector();
        this.m_extraInfos = new Vector();
        this.m_inputFields = new Vector();
        this.setValues(document.getDocumentElement());
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("PaymentOption");
        element.setAttribute("name", this.m_name);
        element.setAttribute("type", this.m_type);
        element.setAttribute("generic", String.valueOf(this.m_generic));
        element.setAttribute("japversion", this.m_minJapVersion);
        final Element element2 = document.createElement("Markup");
        XMLUtil.setValue(element2, this.m_markup);
        element.appendChild(element2);
        for (int i = 0; i < this.m_headings.size(); ++i) {
            final String[] array = this.m_headings.elementAt(i);
            final Element element3 = document.createElement("Heading");
            element3.setAttribute("lang", array[1]);
            element3.appendChild(document.createTextNode(array[0]));
            element.appendChild(element3);
        }
        for (int j = 0; j < this.m_detailedInfos.size(); ++j) {
            final String[] array2 = this.m_detailedInfos.elementAt(j);
            final Element element4 = document.createElement("DetailedInfo");
            element4.setAttribute("lang", array2[1]);
            element4.appendChild(document.createTextNode(array2[0]));
            element.appendChild(element4);
        }
        final Enumeration<String> keys = this.m_ranks.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final Integer n = this.m_ranks.get(s);
            final Element element5 = document.createElement("Rank");
            element5.setAttribute("lang", s);
            element5.appendChild(document.createTextNode(n.toString()));
            element.appendChild(element5);
        }
        for (int k = 0; k < this.m_paymentDelays.size(); ++k) {
            final String[] array3 = this.m_paymentDelays.elementAt(k);
            final Element element6 = document.createElement("PaymentDelay");
            element6.setAttribute("lang", array3[1]);
            element6.appendChild(document.createTextNode(array3[0]));
            element.appendChild(element6);
        }
        for (int l = 0; l < this.m_extraInfos.size(); ++l) {
            final String[] array4 = this.m_extraInfos.elementAt(l);
            final Element element7 = document.createElement("ExtraInfo");
            element7.setAttribute("type", array4[1]);
            if (array4[2] != null) {
                element7.setAttribute("lang", array4[2]);
            }
            element7.appendChild(document.createTextNode(array4[0]));
            element.appendChild(element7);
        }
        if (this.m_imageLink != null) {
            final Element element8 = document.createElement("ImageLink");
            element8.appendChild(document.createTextNode(this.m_imageLink));
            element.appendChild(element8);
        }
        for (int n2 = 0; n2 < this.m_inputFields.size(); ++n2) {
            final String[] array5 = this.m_inputFields.elementAt(n2);
            final Element element9 = document.createElement("input");
            element9.setAttribute("ref", array5[0]);
            final Element element10 = document.createElement("label");
            element9.appendChild(element10);
            if (array5[2] != null) {
                element10.setAttribute("lang", array5[2]);
            }
            element10.appendChild(document.createTextNode(array5[1]));
            element.appendChild(element9);
        }
        return element;
    }
    
    protected void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("PaymentOption")) {
            throw new Exception("XMLPaymentOption wrong XML structure");
        }
        this.m_type = element.getAttribute("type");
        this.m_name = element.getAttribute("name");
        this.m_generic = XMLUtil.parseAttribute(element, "generic", true);
        this.m_minJapVersion = XMLUtil.parseAttribute(element, "japversion", "00.00.000");
        this.m_markup = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Markup"), 0);
        final NodeList elementsByTagName = element.getElementsByTagName("Heading");
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            final String value = XMLUtil.parseValue(elementsByTagName.item(i), null);
            final String attribute = ((Element)elementsByTagName.item(i)).getAttribute("lang");
            if (attribute == null || value == null) {
                throw new Exception("XMLPaymentOption wrong XML structure");
            }
            this.m_headings.addElement(new String[] { value, attribute });
        }
        final NodeList elementsByTagName2 = element.getElementsByTagName("DetailedInfo");
        for (int j = 0; j < elementsByTagName2.getLength(); ++j) {
            final String value2 = XMLUtil.parseValue(elementsByTagName2.item(j), null);
            final String attribute2 = ((Element)elementsByTagName2.item(j)).getAttribute("lang");
            if (attribute2 == null || value2 == null) {
                throw new Exception("XMLPaymentOption wrong XML structure");
            }
            this.m_detailedInfos.addElement(new String[] { value2, attribute2 });
        }
        final NodeList elementsByTagName3 = element.getElementsByTagName("Rank");
        for (int k = 0; k < elementsByTagName3.getLength(); ++k) {
            this.m_ranks.put(((Element)elementsByTagName3.item(k)).getAttribute("lang"), new Integer(XMLUtil.parseValue(elementsByTagName3.item(k), Integer.MAX_VALUE)));
        }
        final NodeList elementsByTagName4 = element.getElementsByTagName("PaymentDelay");
        for (int l = 0; l < elementsByTagName4.getLength(); ++l) {
            final String value3 = XMLUtil.parseValue(elementsByTagName4.item(l), "");
            final String attribute3 = ((Element)elementsByTagName4.item(l)).getAttribute("lang");
            if (attribute3 == null || value3 == null) {
                throw new Exception("XMLPaymentOption wrong XML structure");
            }
            this.m_paymentDelays.addElement(new String[] { value3, attribute3 });
        }
        final NodeList elementsByTagName5 = element.getElementsByTagName("ExtraInfo");
        for (int n = 0; n < elementsByTagName5.getLength(); ++n) {
            final String value4 = XMLUtil.parseValue(elementsByTagName5.item(n), null);
            final String attribute4 = ((Element)elementsByTagName5.item(n)).getAttribute("lang");
            final String attribute5 = ((Element)elementsByTagName5.item(n)).getAttribute("type");
            if (attribute4 == null || value4 == null || attribute5 == null) {
                throw new Exception("XMLPaymentOption wrong XML structure");
            }
            this.m_extraInfos.addElement(new String[] { value4, attribute5, attribute4 });
        }
        final NodeList elementsByTagName6 = element.getElementsByTagName("input");
        for (int n2 = 0; n2 < elementsByTagName6.getLength(); ++n2) {
            final String value5 = XMLUtil.parseValue(elementsByTagName6.item(n2).getFirstChild(), null);
            final String attribute6 = ((Element)elementsByTagName6.item(n2).getFirstChild()).getAttribute("lang");
            final String attribute7 = ((Element)elementsByTagName6.item(n2)).getAttribute("ref");
            if (attribute6 == null || value5 == null || attribute7 == null) {
                throw new Exception("XMLPaymentOption wrong XML structure");
            }
            this.m_inputFields.addElement(new String[] { attribute7, value5, attribute6 });
        }
        try {
            final String value6 = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "ImageLink").getFirstChild(), "0");
            if (!value6.equals("0")) {
                this.m_imageLink = value6;
            }
        }
        catch (Exception ex) {
            this.m_imageLink = null;
        }
    }
    
    public void setType(final String type) {
        this.m_type = type;
    }
    
    public String getHeading(final String s) {
        this.m_headings.size();
        for (int i = 0; i < this.m_headings.size(); ++i) {
            final String[] array = this.m_headings.elementAt(i);
            if (array[1].equalsIgnoreCase(s)) {
                return array[0];
            }
        }
        if (s.equals("en")) {
            return null;
        }
        return this.getHeading("en");
    }
    
    public String getDetailedInfo(final String s) {
        for (int i = 0; i < this.m_detailedInfos.size(); ++i) {
            final String[] array = this.m_detailedInfos.elementAt(i);
            if (array[1].equalsIgnoreCase(s)) {
                return array[0];
            }
        }
        if (s.equals("en")) {
            return null;
        }
        return this.getDetailedInfo("en");
    }
    
    public Integer getRank(final String s) {
        Integer n = this.m_ranks.get(s);
        if (n == null && s.equalsIgnoreCase("en")) {
            n = new Integer(Integer.MAX_VALUE);
        }
        return n;
    }
    
    public String getPaymentDelay(final String s) {
        for (int i = 0; i < this.m_paymentDelays.size(); ++i) {
            final String[] array = this.m_paymentDelays.elementAt(i);
            if (array[1].equalsIgnoreCase(s)) {
                return array[0];
            }
        }
        if (!s.equalsIgnoreCase("en")) {
            return this.getPaymentDelay("en");
        }
        return null;
    }
    
    public String getExtraInfo(final String s) {
        for (int i = 0; i < this.m_extraInfos.size(); ++i) {
            final String[] array = this.m_extraInfos.elementAt(i);
            if (array[2].equalsIgnoreCase(s)) {
                return array[0];
            }
        }
        if (s.equals("en")) {
            return null;
        }
        return this.getExtraInfo("en");
    }
    
    public Vector getExtraInfos() {
        return (Vector)this.m_extraInfos.clone();
    }
    
    public Vector getLocalizedExtraInfoText(final String s) {
        final Vector extraInfos = this.getExtraInfos();
        Vector localizedExtraInfoText = new Vector<String>();
        final Enumeration<String[]> elements = extraInfos.elements();
        while (elements.hasMoreElements()) {
            final String[] array = elements.nextElement();
            if (array[2].equals(s)) {
                localizedExtraInfoText.addElement(array[0]);
            }
        }
        if (localizedExtraInfoText.size() < 1) {
            localizedExtraInfoText = this.getLocalizedExtraInfoText("en");
        }
        return localizedExtraInfoText;
    }
    
    public String getType() {
        return this.m_type;
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public String getExtraInfoType(final String s) {
        for (int i = 0; i < this.m_extraInfos.size(); ++i) {
            final String[] array = this.m_extraInfos.elementAt(i);
            if (array[2].equalsIgnoreCase(s)) {
                return array[1];
            }
        }
        return this.getExtraInfoType("en");
    }
    
    public Vector getInputFields() {
        return (Vector)this.m_inputFields.clone();
    }
    
    public Vector getLanguages() {
        return (Vector)XMLPaymentOption.m_languages.clone();
    }
    
    public boolean isGeneric() {
        return this.m_generic;
    }
    
    public int getMarkup() {
        return this.m_markup;
    }
    
    public String getMinJapVersion() {
        return this.m_minJapVersion;
    }
    
    public boolean isNewer(final XMLPaymentOption xmlPaymentOption) {
        return this.m_minJapVersion != null && (xmlPaymentOption.getMinJapVersion() == null || Util.convertVersionStringToNumber(this.m_minJapVersion) > Util.convertVersionStringToNumber(xmlPaymentOption.getMinJapVersion()));
    }
    
    public boolean worksWithJapVersion(final String s) {
        return this.m_minJapVersion == null || Util.convertVersionStringToNumber(this.m_minJapVersion) <= Util.convertVersionStringToNumber(s);
    }
    
    private static void addLanguage(final String s) {
        if (!XMLPaymentOption.m_languages.contains(s)) {
            XMLPaymentOption.m_languages.addElement(s);
        }
    }
    
    static {
        XMLPaymentOption.m_languages = new Vector();
    }
}
