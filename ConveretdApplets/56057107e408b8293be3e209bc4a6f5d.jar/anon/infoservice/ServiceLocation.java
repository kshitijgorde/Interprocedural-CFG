// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.util.Vector;
import anon.crypto.AbstractX509Extension;
import anon.crypto.X509DistinguishedName;
import anon.util.XMLUtil;
import anon.util.Util;
import anon.crypto.AbstractX509AlternativeName;
import anon.crypto.X509SubjectAlternativeName;
import anon.crypto.JAPCertificate;
import org.w3c.dom.Node;

public class ServiceLocation
{
    public static final String XML_ELEMENT_NAME = "Location";
    public static final String XML_ELEMENT_CITY = "City";
    public static final String XML_ELEMENT_STATE = "State";
    public static final String XML_ELEMENT_COUNTRY = "Country";
    public static final String XML_ELEMENT_POSITION = "Position";
    public static final String XML_ELEMENT_GEO = "Geo";
    public static final String XML_ELEMENT_LONGITUDE = "Longitude";
    public static final String XML_ELEMENT_LATITUDE = "Latitude";
    private String city;
    private String state;
    private String m_country;
    private String m_commonName;
    private String longitude;
    private String latitude;
    
    public ServiceLocation(final Node node, final JAPCertificate japCertificate) {
        if (japCertificate != null) {
            final X509DistinguishedName subject = japCertificate.getSubject();
            this.city = subject.getLocalityName();
            this.state = subject.getStateOrProvince();
            this.m_country = subject.getCountryCode();
            this.m_commonName = subject.getCommonName();
            final AbstractX509Extension extension = japCertificate.getExtensions().getExtension(X509SubjectAlternativeName.IDENTIFIER);
            if (extension != null && extension instanceof X509SubjectAlternativeName) {
                final X509SubjectAlternativeName x509SubjectAlternativeName = (X509SubjectAlternativeName)extension;
                if (x509SubjectAlternativeName.getTags().size() == 2 && x509SubjectAlternativeName.getValues().size() == 2) {
                    final Vector tags = x509SubjectAlternativeName.getTags();
                    if (tags.elementAt(0).equals(AbstractX509AlternativeName.TAG_OTHER) && tags.elementAt(1).equals(AbstractX509AlternativeName.TAG_OTHER)) {
                        final Vector values = x509SubjectAlternativeName.getValues();
                        try {
                            Util.parseDouble(this.longitude = values.elementAt(0).toString());
                            this.longitude = this.longitude.trim();
                        }
                        catch (NumberFormatException ex) {
                            this.longitude = "";
                        }
                        try {
                            Util.parseDouble(this.latitude = values.elementAt(1).toString());
                            this.latitude = this.latitude.trim();
                        }
                        catch (NumberFormatException ex2) {
                            this.latitude = "";
                        }
                    }
                }
            }
        }
        if (this.city == null || this.city.trim().length() == 0) {
            this.city = XMLUtil.parseValue(XMLUtil.getFirstChildByName(node, "City"), "");
        }
        if (this.state == null || this.state.trim().length() == 0) {
            this.state = XMLUtil.parseValue(XMLUtil.getFirstChildByName(node, "State"), "");
        }
        if (this.m_country == null || this.m_country.trim().length() == 0) {
            this.m_country = XMLUtil.parseValue(XMLUtil.getFirstChildByName(node, "Country"), "");
        }
        final Node firstChildByName = XMLUtil.getFirstChildByName(XMLUtil.getFirstChildByName(node, "Position"), "Geo");
        if (this.longitude == null || this.longitude.trim().length() == 0) {
            this.longitude = XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName, "Longitude"), "");
        }
        if (this.latitude == null || this.latitude.trim().length() == 0) {
            this.latitude = XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName, "Latitude"), "");
        }
    }
    
    public String getCity() {
        return this.city;
    }
    
    public String getState() {
        return this.state;
    }
    
    public String getCountryCode() {
        return this.m_country;
    }
    
    public String getCommonName() {
        return this.m_commonName;
    }
    
    public String getLongitude() {
        return this.longitude;
    }
    
    public String getLatitude() {
        return this.latitude;
    }
    
    public Element toXMLElement(final Document document) {
        if (document == null) {
            return null;
        }
        final Element element = document.createElement("Location");
        if (this.city != null) {
            XMLUtil.createChildElementWithValue(element, "City", XMLUtil.filterXMLChars(this.city));
        }
        if (this.state != null) {
            XMLUtil.createChildElementWithValue(element, "State", XMLUtil.filterXMLChars(this.state));
        }
        if (this.m_country != null) {
            XMLUtil.createChildElementWithValue(element, "Country", XMLUtil.filterXMLChars(this.m_country));
        }
        if (this.longitude != null && this.latitude != null) {
            final Element childElement = XMLUtil.createChildElement(XMLUtil.createChildElement(element, "Position"), "Geo");
            XMLUtil.createChildElementWithValue(childElement, "Longitude", XMLUtil.filterXMLChars(this.longitude));
            XMLUtil.createChildElementWithValue(childElement, "Latitude", XMLUtil.filterXMLChars(this.latitude));
        }
        return element;
    }
}
