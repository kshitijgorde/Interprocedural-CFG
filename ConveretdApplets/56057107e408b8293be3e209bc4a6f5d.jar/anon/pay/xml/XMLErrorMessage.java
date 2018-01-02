// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import anon.util.IXMLEncodable;

public class XMLErrorMessage extends Exception implements IXMLEncodable
{
    public static final int ERR_OK = 0;
    public static final int ERR_INTERNAL_SERVER_ERROR = 1;
    public static final int ERR_WRONG_FORMAT = 2;
    public static final int ERR_WRONG_DATA = 3;
    public static final int ERR_KEY_NOT_FOUND = 4;
    public static final int ERR_BAD_SIGNATURE = 5;
    public static final int ERR_BAD_REQUEST = 6;
    public static final int ERR_NO_ACCOUNTCERT = 7;
    public static final int ERR_NO_BALANCE = 8;
    public static final int ERR_NO_CONFIRMATION = 9;
    public static final int ERR_ACCOUNT_EMPTY = 10;
    public static final int ERR_CASCADE_LENGTH = 11;
    public static final int ERR_DATABASE_ERROR = 12;
    public static final int ERR_INSUFFICIENT_BALANCE = 13;
    public static final int ERR_NO_FLATRATE_OFFERED = 14;
    public static final int ERR_INVALID_CODE = 15;
    public static final int ERR_OUTDATED_CC = 16;
    public static final int ERR_INVALID_PRICE_CERTS = 17;
    public static final int ERR_MULTIPLE_LOGIN = 18;
    public static final int ERR_NO_RECORD_FOUND = 19;
    public static final int ERR_SUCCESS_BUT_WITH_ERRORS = 20;
    public static final int ERR_BLOCKED = 21;
    private int m_iErrorCode;
    private String m_strErrMsg;
    private IXMLEncodable m_oMessageObject;
    private static final String[] m_errStrings;
    private static final String[] m_messageObjectTypes;
    public static final String XML_ELEMENT_NAME = "ErrorMessage";
    
    public XMLErrorMessage(final Document document) throws Exception {
        this.setValues(document.getDocumentElement());
    }
    
    public XMLErrorMessage(final Element values) throws Exception {
        this.setValues(values);
    }
    
    public XMLErrorMessage(final int iErrorCode, final String strErrMsg) {
        this.m_iErrorCode = iErrorCode;
        this.m_strErrMsg = strErrMsg;
    }
    
    public XMLErrorMessage(final int iErrorCode, final String strErrMsg, final IXMLEncodable oMessageObject) {
        this.m_iErrorCode = iErrorCode;
        this.m_strErrMsg = strErrMsg;
        this.m_oMessageObject = oMessageObject;
    }
    
    public XMLErrorMessage(final int iErrorCode) {
        this.m_iErrorCode = iErrorCode;
        if (this.m_iErrorCode < 0 || this.m_iErrorCode > XMLErrorMessage.m_errStrings.length) {
            this.m_strErrMsg = "Unknown Message";
        }
        else {
            this.m_strErrMsg = XMLErrorMessage.m_errStrings[iErrorCode];
        }
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("ErrorMessage");
        element.setAttribute("code", Integer.toString(this.m_iErrorCode));
        XMLUtil.setValue(element, this.m_strErrMsg);
        if (this.m_oMessageObject != null) {
            final Element element2 = document.createElement("MessageObject");
            element2.appendChild(this.m_oMessageObject.toXmlElement(document));
            element.appendChild(element2);
        }
        return element;
    }
    
    public String getErrorDescription() {
        return this.m_strErrMsg;
    }
    
    public int getErrorCode() {
        return this.m_iErrorCode;
    }
    
    public String getMessage() {
        return this.m_strErrMsg;
    }
    
    public IXMLEncodable getMessageObject() {
        return this.m_oMessageObject;
    }
    
    public Class getMessageObjectType() {
        final String s = XMLErrorMessage.m_messageObjectTypes[this.m_iErrorCode];
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    public void setMessageObject(final IXMLEncodable oMessageObject) {
        this.m_oMessageObject = oMessageObject;
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("ErrorMessage")) {
            throw new Exception("Format error: Root element wrong tagname");
        }
        this.m_iErrorCode = Integer.parseInt(element.getAttribute("code"));
        this.m_strErrMsg = XMLUtil.parseValue(element, "");
        final String s = XMLErrorMessage.m_messageObjectTypes[this.m_iErrorCode];
    }
    
    static {
        m_errStrings = new String[] { "Success", "Internal Server Error", "Wrong format", "Wrong Data", "Key not found", "Bad Signature", "Bad request", "No account certificate", "No balance", "No cost confirmation", "Account is empty", "Cascade too long", "Database error", "Insufficient balance", "No flatrate offered", "Invalid code", "outdated CC", "Invalid price certificates", "multiple login is not allowed", "no record found", "operation succeeded, but there were errors", "this account is blocked" };
        m_messageObjectTypes = new String[] { "none", "none", "none", "none", "none", "none", "none", "none", "none", "none", "XMLGenericText", "none", "none", "none", "none", "none", "XMLEasyCC", "none", "none", "none", "none", "none" };
    }
}
