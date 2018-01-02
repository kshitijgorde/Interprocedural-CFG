// 
// Decompiled by Procyon v0.5.30
// 

package BsscXML;

import java.util.Hashtable;
import java.util.Vector;

public class BsscXMLElement implements IBsscXMLElementBuilder, IBsscXMLElementReader
{
    private BsscXMLElement m_oParent;
    private Vector m_vChildren;
    private Hashtable m_hAttr;
    private String m_sValue;
    private String m_sName;
    
    public int findChild(final IBsscXMLElementReader bsscXMLElementReader) {
        if (bsscXMLElementReader instanceof BsscXMLElement) {
            return this.m_vChildren.indexOf(bsscXMLElementReader);
        }
        return -1;
    }
    
    public IBsscXMLElementReader getChild(final int n) {
        IBsscXMLElementReader bsscXMLElementReader = null;
        try {
            bsscXMLElementReader = this.m_vChildren.elementAt(n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        return bsscXMLElementReader;
    }
    
    public void setAttribute(final String s, final String s2) {
        this.m_hAttr.put(s, s2);
    }
    
    public void setValue(final String sValue) {
        this.m_sValue = sValue;
    }
    
    public String getValue() {
        return this.m_sValue;
    }
    
    public String getAttribute(final String s) {
        return this.m_hAttr.get(s);
    }
    
    public IBsscXMLElementReader getNextSibling() {
        if (this.m_oParent == null) {
            return null;
        }
        final int child = this.m_oParent.findChild(this);
        if (child != -1) {
            return this.m_oParent.getChild(child + 1);
        }
        return null;
    }
    
    public void setParent(final IBsscXMLElementBuilder bsscXMLElementBuilder) throws BsscXMLException {
        if (!(bsscXMLElementBuilder instanceof BsscXMLElement)) {
            throw new BsscXMLException("Type Mismatch!");
        }
        if (this.m_oParent == null) {
            this.m_oParent = (BsscXMLElement)bsscXMLElementBuilder;
            return;
        }
        throw new BsscXMLException("Internal Error!");
    }
    
    public IBsscXMLElementReader getParent() {
        return this.m_oParent;
    }
    
    public BsscXMLElement(final String sName) {
        this.m_oParent = null;
        this.m_vChildren = null;
        this.m_hAttr = null;
        this.m_sValue = null;
        this.m_sName = null;
        this.m_sName = sName;
        this.m_vChildren = new Vector();
        this.m_hAttr = new Hashtable();
        this.m_sValue = "";
    }
    
    public IBsscXMLElementReader getPrevSibling() {
        if (this.m_oParent == null) {
            return null;
        }
        final int child = this.m_oParent.findChild(this);
        if (child != -1) {
            return this.m_oParent.getChild(child - 1);
        }
        return null;
    }
    
    public boolean checkName(final String s) {
        return s == null || s.length() == 0 || s.equals(this.m_sName);
    }
    
    public void addChild(final IBsscXMLElementBuilder bsscXMLElementBuilder) throws BsscXMLException {
        if (bsscXMLElementBuilder instanceof BsscXMLElement) {
            this.m_vChildren.addElement(bsscXMLElementBuilder);
            return;
        }
        throw new BsscXMLException("Type Mismatch!");
    }
    
    public String getName() {
        return this.m_sName;
    }
}
