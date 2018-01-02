// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan;

import com.ibm.xml.b2b.util.EncodingSupport;
import com.ibm.xml.b2b.util.XMLString;

public final class ExternalEntityState
{
    public XMLString version;
    public XMLString encName;
    public XMLString standalone;
    private XMLString versionString;
    private XMLString encNameString;
    private XMLString standaloneString;
    
    public ExternalEntityState() {
        this.versionString = new XMLString();
        this.encNameString = new XMLString();
        this.standaloneString = new XMLString();
    }
    
    public void reset() {
        if (this.version != null) {
            this.version.handle = -1;
            this.version.str = null;
            this.version = null;
        }
        if (this.encName != null) {
            this.encName.handle = -1;
            this.encName.str = null;
            this.encName = null;
        }
        if (this.standalone != null) {
            this.standalone.handle = -1;
            this.standalone.str = null;
            this.standalone = null;
        }
    }
    
    public XMLString setVersion(final byte[] array, final int n, final int n2, final EncodingSupport encodingSupport) {
        (this.version = this.versionString).setValues(array, n, n2, encodingSupport);
        return this.version;
    }
    
    public XMLString setVersion(final char[] array, final int n, final int n2) {
        (this.version = this.versionString).setValues(array, n, n2);
        return this.version;
    }
    
    public XMLString setEncName(final byte[] array, final int n, final int n2, final EncodingSupport encodingSupport) {
        (this.encName = this.encNameString).setValues(array, n, n2, encodingSupport);
        return this.encName;
    }
    
    public XMLString setEncName(final char[] array, final int n, final int n2) {
        (this.encName = this.encNameString).setValues(array, n, n2);
        return this.encName;
    }
    
    public XMLString setStandalone(final byte[] array, final int n, final int n2, final EncodingSupport encodingSupport) {
        (this.standalone = this.standaloneString).setValues(array, n, n2, encodingSupport);
        return this.standalone;
    }
    
    public XMLString setStandalone(final char[] array, final int n, final int n2) {
        (this.standalone = this.standaloneString).setValues(array, n, n2);
        return this.standalone;
    }
}
