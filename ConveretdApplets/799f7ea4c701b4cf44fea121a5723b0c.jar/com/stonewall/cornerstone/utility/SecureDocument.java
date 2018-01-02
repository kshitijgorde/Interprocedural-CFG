// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.Iterator;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.Context;
import org.xmodel.xpath.XPath;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public class SecureDocument
{
    private IModelObject root;
    public static final String Encrypted = "encrypted";
    static final String EncryptedNodes = ".//*[@encrypted=\"true\"]";
    static final String DecryptedNodes = ".//*[@encrypted=\"false\"]";
    static final Log log;
    
    static {
        log = Log.getLog(SecureDocument.class);
    }
    
    public SecureDocument(final IModelObject root) {
        this.setRoot(root);
    }
    
    public void setRoot(final IModelObject root) {
        this.root = root;
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    public void encrypt() {
        final Encrypted en = new Encrypted();
        try {
            final IExpression path = XPath.createExpression(".//*[@encrypted=\"false\"]");
            for (final Object o : path.evaluateNodes(new Context(this.root))) {
                final IModelObject e = (IModelObject)o;
                en.setContent(Xlate.get(e, ""));
                e.setValue(en.encrypt());
                e.setAttribute("encrypted", String.valueOf(true));
            }
        }
        catch (Exception e2) {
            SecureDocument.log.error(this, e2);
        }
    }
    
    public void decrypt() {
        final Encrypted en = new Encrypted();
        try {
            final IExpression path = XPath.createExpression(".//*[@encrypted=\"true\"]");
            for (final Object o : path.evaluateNodes(new Context(this.root))) {
                final IModelObject e = (IModelObject)o;
                en.setContent(Xlate.get(e, ""));
                e.setValue(en.decrypt());
                e.setAttribute("encrypted", String.valueOf(false));
            }
        }
        catch (Exception e2) {
            SecureDocument.log.error(this, e2);
        }
    }
}
