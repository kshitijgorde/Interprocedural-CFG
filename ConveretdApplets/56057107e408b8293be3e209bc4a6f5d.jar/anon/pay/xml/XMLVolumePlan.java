// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.InputStream;
import anon.util.XMLUtil;
import java.io.ByteArrayInputStream;
import org.w3c.dom.Document;
import anon.util.IXMLEncodable;

public class XMLVolumePlan implements IXMLEncodable
{
    public static final String XML_ELEMENT_NAME = "VolumePlan";
    private Document m_docTheVolumePlan;
    private String m_name;
    private String m_displayName;
    private int m_price;
    private boolean m_volumeLimited;
    private boolean m_durationLimited;
    private long m_volumeKbytes;
    private int m_duration;
    private String m_durationUnit;
    
    public XMLVolumePlan() {
    }
    
    public XMLVolumePlan(final String s) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(s.getBytes()));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheVolumePlan = xmlDocument;
    }
    
    public XMLVolumePlan(final Element values) throws Exception {
        this.setValues(values);
        (this.m_docTheVolumePlan = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docTheVolumePlan, values, true));
    }
    
    public XMLVolumePlan(final Document docTheVolumePlan) throws Exception {
        this.setValues(docTheVolumePlan.getDocumentElement());
        this.m_docTheVolumePlan = docTheVolumePlan;
    }
    
    public XMLVolumePlan(final String name, final String displayName, final int price) {
        this.m_name = name;
        this.m_price = price;
        this.m_displayName = displayName;
        (this.m_docTheVolumePlan = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheVolumePlan));
    }
    
    public XMLVolumePlan(final String name, final String displayName, final int price, final long volumeKbytes) {
        this.m_name = name;
        this.m_displayName = displayName;
        this.m_price = price;
        this.m_volumeKbytes = volumeKbytes;
        this.m_volumeLimited = true;
        this.m_durationLimited = false;
        (this.m_docTheVolumePlan = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheVolumePlan));
    }
    
    public XMLVolumePlan(final String name, final String displayName, final int price, final int duration, final String durationUnit) {
        this.m_name = name;
        this.m_displayName = displayName;
        this.m_price = price;
        this.m_volumeLimited = false;
        this.m_durationLimited = true;
        this.m_duration = duration;
        this.m_durationUnit = durationUnit;
        (this.m_docTheVolumePlan = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheVolumePlan));
    }
    
    public XMLVolumePlan(final String name, final String displayName, final int price, final int duration, final String durationUnit, final long volumeKbytes) {
        this.m_name = name;
        this.m_displayName = displayName;
        this.m_price = price;
        this.m_durationLimited = true;
        this.m_duration = duration;
        this.m_durationUnit = durationUnit;
        this.m_volumeKbytes = volumeKbytes;
        this.m_volumeLimited = true;
        (this.m_docTheVolumePlan = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheVolumePlan));
    }
    
    public XMLVolumePlan(final String name, final String displayName, final int price, final boolean durationLimited, final boolean volumeLimited, final int duration, final String durationUnit, final long volumeKbytes) {
        this.m_name = name;
        this.m_displayName = displayName;
        this.m_price = price;
        this.m_durationLimited = durationLimited;
        this.m_duration = duration;
        this.m_durationUnit = durationUnit;
        this.m_volumeKbytes = volumeKbytes;
        this.m_volumeLimited = volumeLimited;
        (this.m_docTheVolumePlan = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheVolumePlan));
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public String getDisplayName() {
        if (this.m_displayName != null && !this.m_displayName.equals("")) {
            return this.m_displayName;
        }
        return this.m_name;
    }
    
    public int getPrice() {
        return this.m_price;
    }
    
    public boolean isVolumeLimited() {
        return this.m_volumeLimited;
    }
    
    public boolean isDurationLimited() {
        return this.m_durationLimited;
    }
    
    public int getDuration() {
        return this.m_duration;
    }
    
    public String getDurationUnit() {
        return this.m_durationUnit;
    }
    
    public int getDurationInDays() {
        return 0;
    }
    
    public long getVolumeKbytes() {
        return this.m_volumeKbytes;
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheVolumePlan.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    protected void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("VolumePlan")) {
            throw new Exception("XMLVolumePlan: wrong XML structure");
        }
        this.m_name = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Name"), null);
        this.m_displayName = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "DisplayName"), null);
        this.m_price = Integer.parseInt(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Price"), null));
        this.m_volumeLimited = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "VolumeLimited"), false);
        this.m_durationLimited = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "DurationLimited"), false);
        this.m_volumeKbytes = Long.parseLong(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "VolumeKbytes"), null));
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "Duration");
        this.m_duration = Integer.parseInt(XMLUtil.parseValue(element2, (String)null));
        this.m_durationUnit = XMLUtil.parseAttribute(element2, "unit", "");
    }
    
    private Element internal_toXmlElement(final Document document) {
        final Element element = document.createElement("VolumePlan");
        final Element element2 = document.createElement("Name");
        XMLUtil.setValue(element2, this.m_name);
        element.appendChild(element2);
        final Element element3 = document.createElement("DisplayName");
        XMLUtil.setValue(element3, this.m_displayName);
        element.appendChild(element3);
        final Element element4 = document.createElement("Price");
        XMLUtil.setValue(element4, this.m_price);
        element.appendChild(element4);
        final Element element5 = document.createElement("DurationLimited");
        XMLUtil.setValue(element5, this.m_durationLimited);
        element.appendChild(element5);
        final Element element6 = document.createElement("VolumeLimited");
        XMLUtil.setValue(element6, this.m_volumeLimited);
        element.appendChild(element6);
        final Element element7 = document.createElement("VolumeKbytes");
        XMLUtil.setValue(element7, this.m_volumeKbytes);
        element.appendChild(element7);
        final Element element8 = document.createElement("Duration");
        XMLUtil.setValue(element8, this.m_duration);
        element8.setAttribute("unit", this.m_durationUnit);
        element.appendChild(element8);
        return element;
    }
}
