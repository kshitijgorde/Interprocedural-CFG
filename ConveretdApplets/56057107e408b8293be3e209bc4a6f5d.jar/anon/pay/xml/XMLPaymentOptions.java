// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import logging.LogHolder;
import logging.LogType;
import java.util.Enumeration;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import anon.util.XMLUtil;
import java.util.Vector;
import anon.util.IXMLEncodable;

public class XMLPaymentOptions implements IXMLEncodable
{
    private Vector m_currencies;
    private Vector m_paymentOptions;
    private String m_acceptedCreditCards;
    private String m_sortingLanguage;
    
    public XMLPaymentOptions(final String s) throws Exception {
        this.m_currencies = new Vector();
        this.m_paymentOptions = new Vector();
        this.m_sortingLanguage = null;
        this.setValues(XMLUtil.toXMLDocument(s).getDocumentElement());
    }
    
    public XMLPaymentOptions() {
        this.m_currencies = new Vector();
        this.m_paymentOptions = new Vector();
        this.m_sortingLanguage = null;
    }
    
    public XMLPaymentOptions(final Element values) throws Exception {
        this.m_currencies = new Vector();
        this.m_paymentOptions = new Vector();
        this.m_sortingLanguage = null;
        this.setValues(values);
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("PaymentOptions");
        element.setAttribute("version", "1.0");
        for (int i = 0; i < this.m_currencies.size(); ++i) {
            final Element element2 = document.createElement("Currency");
            element2.appendChild(document.createTextNode(this.m_currencies.elementAt(i)));
            element.appendChild(element2);
        }
        for (int j = 0; j < this.m_paymentOptions.size(); ++j) {
            try {
                element.appendChild(((XMLPaymentOption)this.m_paymentOptions.elementAt(j)).toXmlElement(document));
            }
            catch (ClassCastException ex) {}
        }
        final Element element3 = document.createElement("AcceptedCards");
        element3.appendChild(document.createTextNode(this.m_acceptedCreditCards));
        element.appendChild(element3);
        return element;
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("PaymentOptions")) {
            throw new Exception("XMLPaymentOptions wrong XML structure");
        }
        final NodeList elementsByTagName = element.getElementsByTagName("Currency");
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            this.m_currencies.addElement(elementsByTagName.item(i).getFirstChild().getNodeValue());
        }
        final NodeList elementsByTagName2 = element.getElementsByTagName("PaymentOption");
        for (int j = 0; j < elementsByTagName2.getLength(); ++j) {
            this.m_paymentOptions.addElement(new XMLPaymentOption((Element)elementsByTagName2.item(j)));
        }
        this.m_acceptedCreditCards = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "AcceptedCards"), "");
    }
    
    public XMLPaymentOptions(final Document document) throws Exception {
        this.m_currencies = new Vector();
        this.m_paymentOptions = new Vector();
        this.m_sortingLanguage = null;
        this.setValues(document.getDocumentElement());
    }
    
    public void addOption(final XMLPaymentOption xmlPaymentOption) {
        this.m_paymentOptions.addElement(xmlPaymentOption);
    }
    
    public void addCurrency(final String s) {
        this.m_currencies.addElement(s);
    }
    
    public synchronized Enumeration getOptionHeadings(final String sortingLanguage) {
        final Vector<String> vector = new Vector<String>();
        this.setSortingLanguage(sortingLanguage);
        this.sortVector();
        final Vector vector2 = (Vector)this.m_paymentOptions.clone();
        for (int i = 0; i < vector2.size(); ++i) {
            try {
                vector.addElement(vector2.elementAt(i).getHeading(sortingLanguage));
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.PAY, "Could not get payment option heading: " + ex.getMessage());
            }
        }
        return vector.elements();
    }
    
    public Vector getAllOptions() {
        return this.getAllOptionsSortedByRank("en");
    }
    
    public synchronized Vector getAllOptionsSortedByRank(final String sortingLanguage) {
        this.setSortingLanguage(sortingLanguage);
        this.sortVector();
        return (Vector)this.m_paymentOptions.clone();
    }
    
    public XMLPaymentOption getOption(final String s) {
        for (int i = 0; i < this.m_paymentOptions.size(); ++i) {
            try {
                final XMLPaymentOption xmlPaymentOption = this.m_paymentOptions.elementAt(i);
                if (xmlPaymentOption.getName().equalsIgnoreCase(s)) {
                    return xmlPaymentOption;
                }
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.PAY, "Could not get payment option with name: " + s);
            }
        }
        LogHolder.log(5, LogType.PAY, "Could not get payment option with name: " + s);
        return null;
    }
    
    public XMLPaymentOption getOption(final String s, final String s2) {
        for (int i = 0; i < this.m_paymentOptions.size(); ++i) {
            try {
                final XMLPaymentOption xmlPaymentOption = this.m_paymentOptions.elementAt(i);
                if (xmlPaymentOption.getHeading(s2).equalsIgnoreCase(s)) {
                    return xmlPaymentOption;
                }
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.PAY, "Could not get payment option for heading: " + s + " in language " + s2);
            }
        }
        LogHolder.log(5, LogType.PAY, "Could not get payment option for heading: " + s + " in language " + s2);
        return null;
    }
    
    public Vector getCurrencies() {
        return (Vector)this.m_currencies.clone();
    }
    
    public void setAcceptedCreditCards(final String acceptedCreditCards) {
        this.m_acceptedCreditCards = acceptedCreditCards;
    }
    
    public String getAcceptedCreditCards() {
        return this.m_acceptedCreditCards;
    }
    
    public int compare(final Object o, final Object o2) {
        XMLPaymentOption xmlPaymentOption;
        XMLPaymentOption xmlPaymentOption2;
        try {
            if (o == null || o2 == null) {
                throw new Exception("can not compare null objects");
            }
            xmlPaymentOption = (XMLPaymentOption)o;
            xmlPaymentOption2 = (XMLPaymentOption)o2;
        }
        catch (Exception ex) {
            throw new ClassCastException("could not compare payment options, incompatible objects?" + ex);
        }
        String sortingLanguage = this.m_sortingLanguage;
        if (sortingLanguage == null || xmlPaymentOption.getRank(sortingLanguage) == null || xmlPaymentOption2.getRank(sortingLanguage) == null) {
            sortingLanguage = "en";
        }
        final Integer rank = xmlPaymentOption.getRank(sortingLanguage);
        final Integer rank2 = xmlPaymentOption2.getRank(sortingLanguage);
        if (rank == null || rank2 == null) {
            return 0;
        }
        if (rank < rank2) {
            return -1;
        }
        if (rank > rank2) {
            return 1;
        }
        return 0;
    }
    
    public void setSortingLanguage(final String sortingLanguage) {
        this.m_sortingLanguage = sortingLanguage;
    }
    
    private void sortVector() {
        final Vector vector = (Vector)this.m_paymentOptions.clone();
        final Vector paymentOptions = new Vector<XMLPaymentOption>();
        final Enumeration<XMLPaymentOption> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final XMLPaymentOption xmlPaymentOption = elements.nextElement();
            boolean b = false;
            for (int i = 0; i < paymentOptions.size(); ++i) {
                if (this.compare(xmlPaymentOption, paymentOptions.elementAt(i)) < 0) {
                    paymentOptions.insertElementAt(xmlPaymentOption, i);
                    b = true;
                    break;
                }
            }
            if (!b) {
                paymentOptions.addElement(xmlPaymentOption);
            }
        }
        this.m_paymentOptions = paymentOptions;
    }
}
