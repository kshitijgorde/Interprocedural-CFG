// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.serialize;

import org.apache.xml.utils.StringToIntTable;

class ElemDesc
{
    int m_flags;
    StringToIntTable m_attrs;
    static final int EMPTY = 2;
    static final int FLOW = 4;
    static final int BLOCK = 8;
    static final int BLOCKFORM = 16;
    static final int BLOCKFORMFIELDSET = 32;
    static final int CDATA = 64;
    static final int PCDATA = 128;
    static final int RAW = 256;
    static final int INLINE = 512;
    static final int INLINEA = 1024;
    static final int INLINELABEL = 2048;
    static final int FONTSTYLE = 4096;
    static final int PHRASE = 8192;
    static final int FORMCTRL = 16384;
    static final int SPECIAL = 32768;
    static final int ASPECIAL = 65536;
    static final int HEADMISC = 131072;
    static final int HEAD = 262144;
    static final int LIST = 524288;
    static final int PREFORMATTED = 1048576;
    static final int WHITESPACESENSITIVE = 2097152;
    static final int HEADELEM = 4194304;
    static final int ATTRURL = 2;
    static final int ATTREMPTY = 4;
    
    ElemDesc(final int flags) {
        this.m_attrs = null;
        this.m_flags = flags;
    }
    
    boolean is(final int flags) {
        return (this.m_flags & flags) != 0x0;
    }
    
    boolean isAttrFlagSet(final String name, final int flags) {
        return this.m_attrs != null && (this.m_attrs.getIgnoreCase(name) & flags) != 0x0;
    }
    
    void setAttr(final String name, final int flags) {
        if (this.m_attrs == null) {
            this.m_attrs = new StringToIntTable();
        }
        this.m_attrs.put(name, flags);
    }
}