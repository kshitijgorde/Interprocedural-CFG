// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import java.net.URI;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import org.xmodel.ModelAlgorithms;
import java.io.InputStream;
import org.xmodel.xml.XmlIO;
import java.io.FileInputStream;
import java.io.File;
import org.xmodel.xpath.expression.Context;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.CachingException;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.xpath.XPath;
import org.xmodel.external.ICache;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.external.ConfiguredCachingPolicy;

public class FileCachingPolicy extends ConfiguredCachingPolicy
{
    private final IExpression y;
    private IContext v;
    private IExpression x;
    private boolean w;
    
    public FileCachingPolicy(final ICache cache) {
        super(cache);
        this.y = XPath.createExpression("@path");
        this.x = this.y;
        this.setStaticAttributes(new String[] { "*" });
    }
    
    @Override
    public void configure(final IContext v, final IModelObject modelObject) throws CachingException {
        this.v = v;
        this.x = Xlate.get(modelObject, "path", this.y);
        this.w = Xlate.get(modelObject, "create", false);
    }
    
    public void checkout(final IExternalReference externalReference) {
    }
    
    public void checkin(final IExternalReference externalReference) {
    }
    
    public void syncImpl(final IExternalReference externalReference) throws CachingException {
        final String evaluateString = this.x.evaluateString(new Context(this.v, externalReference));
        if (evaluateString == null) {
            throw new CachingException("File reference path not defined.");
        }
        final File file = new File(evaluateString);
        try {
            final FileInputStream fileInputStream = new FileInputStream(file);
            final IModelObject cloneObject = externalReference.cloneObject();
            ModelAlgorithms.moveChildren(new XmlIO().read(fileInputStream), cloneObject);
            this.update(externalReference, cloneObject);
        }
        catch (FileNotFoundException ex2) {}
        catch (Exception ex) {
            throw new CachingException("Unable to sync file: " + file, ex);
        }
    }
    
    public void flushImpl(final IExternalReference externalReference) throws CachingException {
        if (externalReference.isDirty()) {
            return;
        }
        final String evaluateString = this.x.evaluateString(new Context(this.v, externalReference));
        if (evaluateString == null) {
            throw new CachingException("File reference path not defined.");
        }
        final File file = new File(evaluateString);
        try {
            if (this.w) {
                file.createNewFile();
            }
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            new XmlIO().write(externalReference, fileOutputStream);
            fileOutputStream.close();
        }
        catch (Exception ex) {
            throw new CachingException("Unable to flush changes to file: " + evaluateString, ex);
        }
    }
    
    @Override
    public void insert(final IExternalReference externalReference, final IModelObject modelObject, final int n, final boolean b) throws CachingException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void remove(final IExternalReference externalReference, final IModelObject modelObject) throws CachingException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public URI getURI(final IExternalReference externalReference) throws CachingException {
        final String evaluateString = this.x.evaluateString(new Context(this.v, externalReference));
        if (evaluateString == null) {
            throw new CachingException("File reference path not defined.");
        }
        return new File(evaluateString).toURI();
    }
}
