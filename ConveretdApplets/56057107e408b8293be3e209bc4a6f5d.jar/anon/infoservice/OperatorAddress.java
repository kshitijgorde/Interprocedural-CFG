// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Vector;
import java.util.Enumeration;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;
import anon.util.XMLParseException;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import java.util.Hashtable;

public class OperatorAddress
{
    public static final String NODE_NAME_STREET = "Street";
    public static final String NODE_NAME_POSTALCODE = "PostalCode";
    public static final String NODE_NAME_CITY = "City";
    public static final String NODE_NAME_VAT = "Vat";
    public static final String NODE_NAME_FAX = "Fax";
    public static final String NODE_NAME_VENUE = "Venue";
    public static final String NODE_NAME_ADDITIONALINFO = "AdditionalInfo";
    public static final String NODE_NAME_OPERATORCOUNTRY = "OperatorCountry";
    public static final String PROPERTY_NAME_STREET = "street";
    public static final String PROPERTY_NAME_POSTALCODE = "postalCode";
    public static final String PROPERTY_NAME_CITY = "city";
    public static final String PROPERTY_NAME_VAT = "vat";
    public static final String PROPERTY_NAME_FAX = "fax";
    public static final String PROPERTY_NAME_VENUE = "venue";
    public static final String PROPERTY_NAME_ADDITIONALINFO = "additionalInfo";
    private String street;
    private String postalCode;
    private String city;
    private String vat;
    private String operatorCountry;
    private String fax;
    private String venue;
    private String additionalInfo;
    private static Hashtable propertyDescriptors;
    static /* synthetic */ Class class$anon$infoservice$OperatorAddress;
    static /* synthetic */ Class class$anon$terms$TermsAndConditionsTranslation;
    
    public OperatorAddress() {
    }
    
    public OperatorAddress(final Element element) throws XMLParseException {
        final NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            if (childNodes.item(i).getNodeType() == 1) {
                final Element element2 = (Element)childNodes.item(i);
                try {
                    final String tagName = element2.getTagName();
                    this.getClass().getDeclaredField(Character.toLowerCase(tagName.charAt(0)) + tagName.substring(1)).set(this, XMLUtil.parseValue(element2, (String)null));
                }
                catch (SecurityException ex2) {}
                catch (NoSuchFieldException ex3) {}
                catch (IllegalArgumentException ex4) {}
                catch (DOMException ex) {
                    throw new XMLParseException(ex.getMessage());
                }
                catch (IllegalAccessException ex5) {}
            }
        }
    }
    
    public String getStreet() {
        return this.street;
    }
    
    public void setStreet(final String street) {
        this.street = street;
    }
    
    public void setAdditionalInfo(final String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    
    public String getAdditionalInfo() {
        return this.additionalInfo;
    }
    
    public String getPostalCode() {
        return this.postalCode;
    }
    
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public void setCity(final String city) {
        this.city = city;
    }
    
    public String getVat() {
        return this.vat;
    }
    
    public void setVat(final String vat) {
        this.vat = vat;
    }
    
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(final String fax) {
        this.fax = fax;
    }
    
    public String getOperatorCountry() {
        return this.operatorCountry;
    }
    
    public void setOperatorCountry(final String operatorCountry) {
        this.operatorCountry = operatorCountry;
    }
    
    public String getVenue() {
        return this.venue;
    }
    
    public void setVenue(final String venue) {
        this.venue = venue;
    }
    
    public Enumeration getAddressAsNodeList(final Document document) {
        final Vector<Element> vector = new Vector<Element>();
        final Field[] declaredFields = this.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; ++i) {
            if (!Modifier.isFinal(declaredFields[i].getModifiers()) && !Modifier.isStatic(declaredFields[i].getModifiers())) {
                try {
                    final Object value = declaredFields[i].get(this);
                    if (value != null && !value.toString().equals("")) {
                        final Element element = document.createElement(this.getClass().getDeclaredField("NODE_NAME_" + declaredFields[i].getName().toUpperCase()).get(this).toString());
                        XMLUtil.setValue(element, value.toString());
                        vector.addElement(element);
                    }
                }
                catch (SecurityException ex) {
                    ex.printStackTrace();
                }
                catch (NoSuchFieldException ex2) {
                    ex2.printStackTrace();
                }
                catch (DOMException ex3) {
                    ex3.printStackTrace();
                }
                catch (IllegalArgumentException ex4) {
                    ex4.printStackTrace();
                }
                catch (IllegalAccessException ex5) {
                    ex5.printStackTrace();
                }
            }
        }
        return vector.elements();
    }
    
    public static PropertyDescriptor getDescriptor(final String s) {
        return OperatorAddress.propertyDescriptors.get(s);
    }
    
    public static void main(final String[] array) {
        try {
            final PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo((OperatorAddress.class$anon$terms$TermsAndConditionsTranslation == null) ? (OperatorAddress.class$anon$terms$TermsAndConditionsTranslation = class$("anon.terms.TermsAndConditionsTranslation")) : OperatorAddress.class$anon$terms$TermsAndConditionsTranslation).getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; ++i) {
                System.out.println("Property " + propertyDescriptors[i].getName() + " has writeMethod: " + propertyDescriptors[i].getWriteMethod());
            }
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
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
    
    static {
        OperatorAddress.propertyDescriptors = new Hashtable();
        try {
            final PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo((OperatorAddress.class$anon$infoservice$OperatorAddress == null) ? (OperatorAddress.class$anon$infoservice$OperatorAddress = class$("anon.infoservice.OperatorAddress")) : OperatorAddress.class$anon$infoservice$OperatorAddress).getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; ++i) {
                OperatorAddress.propertyDescriptors.put(propertyDescriptors[i].getName(), propertyDescriptors[i]);
            }
        }
        catch (IntrospectionException ex) {}
    }
}
