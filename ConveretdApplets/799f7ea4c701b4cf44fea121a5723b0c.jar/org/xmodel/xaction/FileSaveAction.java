// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.IModelObject;
import org.xmodel.xml.IXmlIO;
import org.xmodel.compress.CompressorException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import org.xmodel.compress.TabularCompressor;
import java.io.IOException;
import java.io.File;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.compress.ICompressor;
import org.xmodel.xml.XmlIO;

public class FileSaveAction extends GuardedAction
{
    private XmlIO \u00dc;
    private ICompressor \u00df;
    private IExpression \u00dd;
    private IExpression \u00db;
    private String \u00de;
    private boolean \u00da;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.\u00de = Xlate.get(xActionDocument.getRoot(), "mode", "printable");
        this.\u00da = Xlate.get(xActionDocument.getRoot(), "overwrite", false);
        this.\u00dd = xActionDocument.getExpression("source", true);
        if (this.\u00dd == null) {
            this.\u00dd = xActionDocument.getExpression();
        }
        this.\u00db = xActionDocument.getExpression("file", true);
        if (this.\u00db == null) {
            this.\u00db = xActionDocument.getExpression();
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final File file = new File(this.\u00db.evaluateString(context));
        if (!this.\u00da && file.exists()) {
            throw new IllegalArgumentException("File already exists: " + this);
        }
        final IModelObject queryFirst = this.\u00dd.queryFirst(context);
        if (queryFirst == null) {
            try {
                file.createNewFile();
            }
            catch (IOException ex) {
                throw new XActionException("Unable to write file: " + file, ex);
            }
            return null;
        }
        if (this.\u00de.equals("compressed")) {
            if (this.\u00df == null) {
                this.\u00df = new TabularCompressor();
            }
            try {
                final FileOutputStream fileOutputStream = new FileOutputStream(file);
                this.\u00df.compress(queryFirst, fileOutputStream);
                fileOutputStream.close();
                return null;
            }
            catch (CompressorException ex4) {
                return null;
            }
            catch (IOException ex2) {
                throw new XActionException("Unable to write file: " + file, ex2);
            }
        }
        if (this.\u00dc == null) {
            this.\u00dc = new XmlIO();
        }
        this.\u00dc.setOutputStyle(this.\u00de.equals("compact") ? IXmlIO.Style.compact : IXmlIO.Style.printable);
        final String write = this.\u00dc.write(queryFirst);
        try {
            file.createNewFile();
            final FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            fileOutputStream2.write(write.getBytes("UTF-8"));
            fileOutputStream2.close();
        }
        catch (IOException ex3) {
            throw new XActionException("Unable to write file: " + file, ex3);
        }
        return null;
    }
}
