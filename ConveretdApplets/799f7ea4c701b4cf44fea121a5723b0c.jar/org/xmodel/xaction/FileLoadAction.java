// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.compress.CompressorException;
import java.io.ByteArrayInputStream;
import org.xmodel.compress.TabularCompressor;
import org.xmodel.xml.XmlException;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.List;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.compress.ICompressor;
import org.xmodel.xml.XmlIO;

public class FileLoadAction extends GuardedAction
{
    private XmlIO \u00c2;
    private ICompressor \u00c4;
    private String \u00c0;
    private IExpression \u00c3;
    private IExpression \u00c1;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.\u00c0 = Conventions.getVarName(xActionDocument.getRoot(), false, "assign");
        this.\u00c3 = xActionDocument.getExpression("target", true);
        this.\u00c1 = xActionDocument.getExpression("file", true);
        if (this.\u00c1 == null) {
            this.\u00c1 = xActionDocument.getExpression();
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final IModelObject modelObject = (this.\u00c3 != null) ? this.\u00c3.queryFirst(context) : null;
        final IVariableScope scope = context.getScope();
        if (scope != null) {
            scope.set(this.\u00c0, new ArrayList<IModelObject>(0));
        }
        final File file = new File(this.\u00c1.evaluateString(context));
        final long length = file.length();
        if (length == 0L) {
            return null;
        }
        final byte[] array = new byte[(int)length];
        try {
            new DataInputStream(new FileInputStream(file)).readFully(array);
        }
        catch (IOException ex) {
            throw new IllegalArgumentException("Unable to open file: " + this, ex);
        }
        int n;
        for (n = 0; n < length && Character.isWhitespace(array[n]); ++n) {}
        IModelObject modelObject2 = null;
        Label_0328: {
            if (array[n] == 60) {
                try {
                    if (this.\u00c2 == null) {
                        this.\u00c2 = new XmlIO();
                    }
                    modelObject2 = this.\u00c2.read(new String(array));
                    break Label_0328;
                }
                catch (XmlException ex2) {
                    throw new IllegalArgumentException("Unable to parse file: " + this, ex2);
                }
            }
            try {
                if (this.\u00c4 == null) {
                    this.\u00c4 = new TabularCompressor();
                }
                modelObject2 = this.\u00c4.decompress(new ByteArrayInputStream(array));
            }
            catch (CompressorException ex3) {
                throw new IllegalArgumentException("Unable to decompress xml: " + this, ex3);
            }
        }
        if (this.\u00c0 != null && scope != null) {
            scope.set(this.\u00c0, modelObject2);
        }
        if (modelObject != null) {
            modelObject.addChild(modelObject2);
        }
        return null;
    }
}
