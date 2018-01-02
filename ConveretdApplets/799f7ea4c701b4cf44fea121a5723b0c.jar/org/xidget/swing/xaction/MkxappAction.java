// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.xaction;

import org.xmodel.IModelObject;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.xmodel.xaction.XActionException;
import java.io.File;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.compress.TabularCompressor;
import org.xmodel.compress.ISerializer;
import org.xmodel.compress.serial.JavaSerializer;
import javax.swing.ImageIcon;
import org.xmodel.compress.DefaultSerializer;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.compress.ICompressor;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class MkxappAction extends GuardedAction
{
    private IExpression sourceExpr;
    private IExpression fileExpr;
    private ICompressor compressor;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.sourceExpr = xActionDocument.getExpression("source", true);
        if (this.sourceExpr == null) {
            this.sourceExpr = xActionDocument.getExpression();
        }
        this.fileExpr = xActionDocument.getExpression("file", true);
        if (this.fileExpr == null) {
            this.fileExpr = xActionDocument.getExpression();
        }
        final DefaultSerializer serializer = new DefaultSerializer();
        serializer.register(ImageIcon.class, new JavaSerializer());
        (this.compressor = new TabularCompressor()).setSerializer(serializer);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final File file = new File(this.fileExpr.evaluateString(context));
        final IModelObject queryFirst = this.sourceExpr.queryFirst(context);
        if (queryFirst == null) {
            try {
                file.createNewFile();
            }
            catch (IOException ex) {
                throw new XActionException("Unable to write file: " + file, ex);
            }
            return null;
        }
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            this.compressor.compress(queryFirst, fileOutputStream);
            fileOutputStream.close();
        }
        catch (Exception ex2) {
            throw new XActionException("Unable to write file: " + file, ex2);
        }
        return null;
    }
}
