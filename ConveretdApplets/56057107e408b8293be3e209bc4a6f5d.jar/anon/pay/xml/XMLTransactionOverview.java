// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import logging.LogHolder;
import logging.LogType;
import java.util.Enumeration;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.util.Hashtable;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import anon.util.XMLUtil;
import java.util.Vector;
import anon.util.IXMLEncodable;

public class XMLTransactionOverview implements IXMLEncodable
{
    public static final Object XML_ELEMENT_NAME;
    private Vector m_transactions;
    private String m_language;
    public static final String KEY_ACCOUNTNUMBER = "accountnumber";
    public static final String KEY_TAN = "tan";
    public static final String KEY_DATE = "date";
    public static final String KEY_CREATIONDATE = "created_on";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_VOLUMEPLAN = "volumeplan";
    public static final String KEY_PAYMENTMETHOD = "paymentmethod";
    public static final String KEY_USED = "used";
    
    public XMLTransactionOverview(final String language) {
        this.m_transactions = new Vector();
        this.m_language = language;
    }
    
    public XMLTransactionOverview(final char[] array) throws Exception {
        this.m_transactions = new Vector();
        this.setValues(XMLUtil.toXMLDocument(array).getDocumentElement());
    }
    
    public XMLTransactionOverview(final byte[] array) throws Exception {
        this.m_transactions = new Vector();
        this.setValues(XMLUtil.toXMLDocument(array).getDocumentElement());
    }
    
    public XMLTransactionOverview(final Document document) throws Exception {
        this.m_transactions = new Vector();
        this.setValues(document.getDocumentElement());
    }
    
    public XMLTransactionOverview(final Element values) throws Exception {
        this.m_transactions = new Vector();
        this.setValues(values);
    }
    
    public int size() {
        return this.m_transactions.size();
    }
    
    private void setValues(final Element element) throws Exception {
        this.m_transactions = new Vector();
        if (!element.getTagName().equals(XMLTransactionOverview.XML_ELEMENT_NAME) || !element.getAttribute("version").equals("1.1")) {
            throw new Exception("TransactionOverview wrong format or wrong version number");
        }
        this.m_language = element.getAttribute("language");
        final NodeList elementsByTagName = element.getElementsByTagName("TransferNumber");
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            final Hashtable<String, String> hashtable = new Hashtable<String, String>();
            final Element element2 = (Element)elementsByTagName.item(i);
            element2.getFirstChild().getNodeValue();
            hashtable.put("tan", XMLUtil.parseValue(element2, ""));
            String attribute;
            if (element2.getAttribute("used") != null) {
                attribute = element2.getAttribute("used");
            }
            else {
                attribute = "false";
            }
            hashtable.put("used", attribute);
            String attribute2;
            if (element2.getAttribute("created_on") != null) {
                attribute2 = element2.getAttribute("created_on");
            }
            else {
                attribute2 = "0";
            }
            hashtable.put("created_on", attribute2);
            String attribute3;
            if (element2.getAttribute("date") != null) {
                attribute3 = element2.getAttribute("date");
            }
            else {
                attribute3 = "0";
            }
            hashtable.put("date", attribute3);
            String attribute4;
            if (element2.getAttribute("amount") != null) {
                attribute4 = element2.getAttribute("amount");
            }
            else {
                attribute4 = "0";
            }
            hashtable.put("amount", attribute4);
            String attribute5;
            if (element2.getAttribute("accountnumber") != null) {
                attribute5 = element2.getAttribute("accountnumber");
            }
            else {
                attribute5 = "";
            }
            hashtable.put("accountnumber", attribute5);
            String attribute6;
            if (element2.getAttribute("volumeplan") != null) {
                attribute6 = element2.getAttribute("volumeplan");
            }
            else {
                attribute6 = "";
            }
            hashtable.put("volumeplan", attribute6);
            String attribute7;
            if (element2.getAttribute("paymentmethod") != null) {
                attribute7 = element2.getAttribute("paymentmethod");
            }
            else {
                attribute7 = "";
            }
            hashtable.put("paymentmethod", attribute7);
            this.m_transactions.addElement(hashtable);
        }
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("TransactionOverview");
        element.setAttribute("version", "1.1");
        element.setAttribute("language", this.m_language);
        final Enumeration<Hashtable<K, String>> elements = this.m_transactions.elements();
        while (elements.hasMoreElements()) {
            final Hashtable<K, String> hashtable = elements.nextElement();
            final Element element2 = document.createElement("TransferNumber");
            final String s = hashtable.get("created_on");
            element2.setAttribute("created_on", (s == null) ? "" : s);
            final String s2 = hashtable.get("accountnumber");
            element2.setAttribute("accountnumber", (s2 == null) ? "" : s2);
            final String s3 = hashtable.get("date");
            element2.setAttribute("date", (s3 == null) ? "" : s3);
            final String s4 = hashtable.get("amount");
            element2.setAttribute("amount", (s4 == null) ? "" : s4);
            final String s5 = hashtable.get("volumeplan");
            element2.setAttribute("volumeplan", (s5 == null) ? "" : s5);
            final String s6 = hashtable.get("paymentmethod");
            element2.setAttribute("paymentmethod", (s6 == null) ? "" : s6);
            final String s7 = hashtable.get("used");
            element2.setAttribute("used", (s7 == null) ? "" : s7);
            element2.appendChild(document.createTextNode(hashtable.get("tan")));
            element.appendChild(element2);
        }
        return element;
    }
    
    public Vector getTans() {
        return this.m_transactions;
    }
    
    public String getLanguage() {
        return this.m_language;
    }
    
    public boolean isUsed(final long n) {
        boolean booleanValue = false;
        final Hashtable dataForTransaction = this.getDataForTransaction(n);
        if (dataForTransaction != null) {
            booleanValue = Boolean.valueOf(dataForTransaction.get("used"));
        }
        return booleanValue;
    }
    
    public Hashtable getDataForTransaction(final long n) {
        Hashtable<K, String> hashtable = null;
        final Enumeration<Hashtable<K, String>> elements = (Enumeration<Hashtable<K, String>>)this.m_transactions.elements();
        while (elements.hasMoreElements()) {
            final Hashtable<K, String> hashtable2 = elements.nextElement();
            final String s = hashtable2.get("tan");
            try {
                if (Long.parseLong(s) == n) {
                    hashtable = hashtable2;
                    break;
                }
                continue;
            }
            catch (NumberFormatException ex) {
                LogHolder.log(3, LogType.PAY, ex);
            }
        }
        return hashtable;
    }
    
    public void setTransactionData(final long n, final long n2, final boolean b, final long n3, final long n4, final long n5, String s, String s2) {
        String string;
        if (n5 == 0L) {
            string = new String("");
        }
        else {
            string = new Long(n5).toString();
        }
        String string2;
        if (n4 == 0L) {
            string2 = new String("");
        }
        else {
            string2 = new Long(n4).toString();
        }
        String string3;
        if (n2 == 0L) {
            string3 = new String("");
        }
        else {
            string3 = new Long(n2).toString();
        }
        String string4;
        if (n3 == 0L) {
            string4 = new String("");
        }
        else {
            string4 = new Long(n3).toString();
        }
        if (s == null) {
            s = new String("");
        }
        if (s2 == null) {
            s2 = new String("");
        }
        final Hashtable dataForTransaction = this.getDataForTransaction(n);
        dataForTransaction.put("used", new Boolean(b).toString());
        dataForTransaction.put("date", string4);
        dataForTransaction.put("created_on", string3);
        dataForTransaction.put("accountnumber", string);
        dataForTransaction.put("amount", string2);
        dataForTransaction.put("volumeplan", s);
        dataForTransaction.put("paymentmethod", s2);
    }
    
    public void addTan(final long n) {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("tan", new Long(n).toString());
        this.m_transactions.addElement(hashtable);
    }
    
    static {
        XML_ELEMENT_NAME = "TransactionOverview";
    }
}
