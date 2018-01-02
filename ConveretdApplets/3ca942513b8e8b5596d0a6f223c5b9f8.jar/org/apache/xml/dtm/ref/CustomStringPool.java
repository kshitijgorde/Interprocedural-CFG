// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref;

import java.util.Hashtable;

public class CustomStringPool extends DTMStringPool
{
    final Hashtable m_stringToInt;
    public static final int NULL = -1;
    
    public CustomStringPool() {
        this.m_stringToInt = new Hashtable();
    }
    
    public void removeAllElements() {
        super.m_intToString.removeAllElements();
        if (this.m_stringToInt != null) {
            this.m_stringToInt.clear();
        }
    }
    
    public String indexToString(final int i) throws ArrayIndexOutOfBoundsException {
        return super.m_intToString.elementAt(i);
    }
    
    public int stringToIndex(final String s) {
        if (s == null) {
            return -1;
        }
        Integer iobj = this.m_stringToInt.get(s);
        if (iobj == null) {
            super.m_intToString.addElement(s);
            iobj = new Integer(super.m_intToString.size());
            this.m_stringToInt.put(s, iobj);
        }
        return iobj;
    }
}
