// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import anon.util.Base64;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import anon.util.XMLUtil;
import anon.util.IXMLEncodable;

public class XMLChallenge implements IXMLEncodable
{
    private byte[] m_arbChallenge;
    private int m_prepaidBytes;
    public static final String XML_ELEMENT_NAME = "Challenge";
    
    public XMLChallenge(final String s) throws Exception {
        this.setValues(XMLUtil.toXMLDocument(s).getDocumentElement());
    }
    
    public XMLChallenge(final Element values) throws Exception {
        this.setValues(values);
    }
    
    public XMLChallenge(final Document document) throws Exception {
        this.setValues(document.getDocumentElement());
    }
    
    public XMLChallenge(final byte[] arbChallenge) {
        this.m_arbChallenge = arbChallenge;
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("Challenge")) {
            throw new Exception("XMLChallenge wrong XML structure");
        }
        this.m_arbChallenge = Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "DontPanic"), ""));
        this.m_prepaidBytes = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "PrepaidBytes"), 0);
    }
    
    public int getPrepaidBytes() {
        return this.m_prepaidBytes;
    }
    
    public byte[] getChallengeForSigning() {
        final byte[] array = new byte[this.m_arbChallenge.length];
        System.arraycopy(this.m_arbChallenge, 0, array, 0, array.length);
        return array;
    }
    
    public byte[] getChallengeForCaptcha() {
        return ("<DontPanic>" + Base64.encodeBytes(this.m_arbChallenge) + "</DontPanic>").getBytes();
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("Challenge");
        final Element element2 = document.createElement("DontPanic");
        element.appendChild(element2);
        XMLUtil.setValue(element2, Base64.encodeBytes(this.m_arbChallenge));
        return element;
    }
}
