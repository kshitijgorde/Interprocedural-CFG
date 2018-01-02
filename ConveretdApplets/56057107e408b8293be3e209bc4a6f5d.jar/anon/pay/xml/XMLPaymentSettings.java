// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.util.Enumeration;
import java.util.Calendar;
import org.w3c.dom.Element;
import java.io.InputStream;
import anon.util.XMLUtil;
import java.io.ByteArrayInputStream;
import org.w3c.dom.Document;
import java.util.Hashtable;
import anon.util.IXMLEncodable;

public class XMLPaymentSettings implements IXMLEncodable
{
    private Hashtable m_paymentSettings;
    private Document m_docTheSettings;
    
    public XMLPaymentSettings(final String s) throws Exception {
        this.m_paymentSettings = new Hashtable();
        this.m_docTheSettings = null;
        this.setValues(XMLUtil.readXMLDocument(new ByteArrayInputStream(s.getBytes())).getDocumentElement());
    }
    
    public XMLPaymentSettings() {
        this.m_paymentSettings = new Hashtable();
        this.m_docTheSettings = null;
    }
    
    public XMLPaymentSettings(final Hashtable paymentSettings) {
        this.m_paymentSettings = new Hashtable();
        this.m_docTheSettings = null;
        this.m_paymentSettings = paymentSettings;
        this.internal_toXmlElement(this.m_docTheSettings = XMLUtil.createDocument());
    }
    
    public XMLPaymentSettings(final Element values) throws Exception {
        this.m_paymentSettings = new Hashtable();
        this.m_docTheSettings = null;
        this.setValues(values);
    }
    
    public XMLPaymentSettings(final Document document) throws Exception {
        this.m_paymentSettings = new Hashtable();
        this.m_docTheSettings = null;
        this.setValues(document.getDocumentElement());
    }
    
    public void addSetting(final String s, final String s2) {
        this.m_paymentSettings.put(s, s2);
    }
    
    public String getSettingValue(final String s) {
        return this.m_paymentSettings.get(s);
    }
    
    public Calendar getEndDate() {
        final Calendar instance = Calendar.getInstance();
        final String settingValue = this.getSettingValue("FlatrateDurationUnit");
        if (settingValue.equalsIgnoreCase("day") || settingValue.equalsIgnoreCase("days")) {
            instance.set(6, (instance.get(6) + Integer.parseInt(this.getSettingValue("FlatrateDuration"))) % instance.getMaximum(6));
        }
        else if (settingValue.equalsIgnoreCase("week") || settingValue.equalsIgnoreCase("weeks")) {
            instance.set(3, (instance.get(3) + Integer.parseInt(this.getSettingValue("FlatrateDuration"))) % instance.getMaximum(3));
        }
        else if (settingValue.equalsIgnoreCase("month") || settingValue.equalsIgnoreCase("months")) {
            instance.set(2, (instance.get(2) + Integer.parseInt(this.getSettingValue("FlatrateDuration"))) % instance.getMaximum(2));
        }
        else if (settingValue.equalsIgnoreCase("year") || settingValue.equalsIgnoreCase("years")) {
            instance.set(1, instance.get(1) + Integer.parseInt(this.getSettingValue("FlatrateDuration")));
        }
        return instance;
    }
    
    public Enumeration getSettingNames() {
        return this.m_paymentSettings.keys();
    }
    
    private Element internal_toXmlElement(final Document document) {
        final Element element = document.createElement("PaymentSettings");
        element.setAttribute("version", "1.0");
        document.appendChild(element);
        final Enumeration<String> keys = (Enumeration<String>)this.m_paymentSettings.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String s2 = this.m_paymentSettings.get(s);
            final Element element2 = document.createElement("Setting");
            XMLUtil.setAttribute(element2, "name", s);
            XMLUtil.setValue(element2, s2);
            element.appendChild(element2);
        }
        return element;
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("PaymentSettings") || !element.getAttribute("version").equals("1.0")) {
            throw new Exception("wrong XML format");
        }
        final NodeList elementsByTagName = element.getElementsByTagName("Setting");
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            final Element element2 = (Element)elementsByTagName.item(i);
            this.m_paymentSettings.put(XMLUtil.parseAttribute(element2, "name", null), XMLUtil.parseValue(element2, (String)null));
        }
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheSettings.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
