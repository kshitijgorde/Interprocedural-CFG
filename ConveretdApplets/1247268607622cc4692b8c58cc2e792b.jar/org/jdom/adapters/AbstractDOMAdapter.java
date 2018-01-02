// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.adapters;

import java.lang.reflect.Method;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMImplementation;
import org.jdom.DocType;
import org.jdom.JDOMException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import org.w3c.dom.Document;
import java.io.File;

public abstract class AbstractDOMAdapter implements DOMAdapter
{
    private static final String CVS_ID = "@(#) $RCSfile: AbstractDOMAdapter.java,v $ $Revision: 1.21 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
    static /* synthetic */ Class class$java$lang$String;
    
    public Document getDocument(final File filename, final boolean validate) throws IOException, JDOMException {
        return this.getDocument(new FileInputStream(filename), validate);
    }
    
    public abstract Document getDocument(final InputStream p0, final boolean p1) throws IOException, JDOMException;
    
    public abstract Document createDocument() throws JDOMException;
    
    public Document createDocument(final DocType doctype) throws JDOMException {
        if (doctype == null) {
            return this.createDocument();
        }
        final DOMImplementation domImpl = this.createDocument().getImplementation();
        final DocumentType domDocType = domImpl.createDocumentType(doctype.getElementName(), doctype.getPublicID(), doctype.getSystemID());
        this.setInternalSubset(domDocType, doctype.getInternalSubset());
        return domImpl.createDocument("http://temporary", doctype.getElementName(), domDocType);
    }
    
    protected void setInternalSubset(final DocumentType dt, final String s) {
        if (dt == null || s == null) {
            return;
        }
        try {
            final Class dtclass = dt.getClass();
            final Method setInternalSubset = dtclass.getMethod("setInternalSubset", (AbstractDOMAdapter.class$java$lang$String == null) ? (AbstractDOMAdapter.class$java$lang$String = class$("java.lang.String")) : AbstractDOMAdapter.class$java$lang$String);
            setInternalSubset.invoke(dt, s);
        }
        catch (Exception ex) {}
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
