// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.net.URLConnection;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.IModelObject;
import org.xmodel.compress.CompressorException;
import org.xmodel.compress.TabularCompressor;
import org.xmodel.xml.XmlException;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.util.Collections;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.compress.ICompressor;
import org.xmodel.xml.XmlIO;

public class UrlLoadAction extends GuardedAction
{
    private XmlIO \u00e5;
    private ICompressor \u00e7;
    private String \u00e4;
    private IExpression \u00e6;
    private IExpression \u00e3;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.\u00e4 = Conventions.getVarName(xActionDocument.getRoot(), false, "assign");
        this.\u00e6 = xActionDocument.getExpression("target", true);
        this.\u00e3 = xActionDocument.getExpression("url", true);
        if (this.\u00e3 == null) {
            this.\u00e3 = xActionDocument.getExpression();
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final IModelObject modelObject = (this.\u00e6 != null) ? this.\u00e6.queryFirst(context) : null;
        final IVariableScope scope = context.getScope();
        if (this.\u00e4 != null) {
            scope.set(this.\u00e4, Collections.emptyList());
        }
        final String evaluateString = this.\u00e3.evaluateString(context);
        try {
            final URLConnection openConnection = new URL(evaluateString).openConnection();
            final long n = openConnection.getContentLength();
            if (n == 0L) {
                return null;
            }
            final byte[] array = new byte[(int)n];
            try {
                new DataInputStream(openConnection.getInputStream()).readFully(array);
            }
            catch (IOException ex) {
                throw new IllegalArgumentException("Unable to open file: " + this, ex);
            }
            int n2;
            for (n2 = 0; n2 < n && Character.isWhitespace(array[n2]); ++n2) {}
            IModelObject modelObject2 = null;
            Label_0327: {
                if (array[n2] == 60) {
                    try {
                        if (this.\u00e5 == null) {
                            this.\u00e5 = new XmlIO();
                        }
                        modelObject2 = this.\u00e5.read(new String(array));
                        break Label_0327;
                    }
                    catch (XmlException ex2) {
                        throw new IllegalArgumentException("Unable to parse file: " + this, ex2);
                    }
                }
                try {
                    if (this.\u00e7 == null) {
                        this.\u00e7 = new TabularCompressor();
                    }
                    modelObject2 = this.\u00e7.decompress(array, 0);
                }
                catch (CompressorException ex3) {
                    throw new IllegalArgumentException("Unable to decompress xml: " + this, ex3);
                }
            }
            if (this.\u00e4 != null) {
                scope.set(this.\u00e4, modelObject2);
            }
            if (modelObject != null) {
                modelObject.addChild(modelObject2);
            }
        }
        catch (Exception ex4) {
            throw new XActionException("Unable to load url: " + evaluateString, ex4);
        }
        return null;
    }
}
