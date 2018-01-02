// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import java.net.URI;
import org.xmodel.xml.XmlException;
import org.xmodel.xml.XmlIO;
import java.io.IOException;
import org.xmodel.external.CachingException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.util.HashMap;
import org.xmodel.external.ICachingPolicy;
import org.xmodel.xpath.XPath;
import org.xmodel.external.ICache;
import org.xmodel.external.UnboundedCache;
import java.util.Map;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.ConfiguredCachingPolicy;

public class FileSystemCachingPolicy extends ConfiguredCachingPolicy
{
    private static final IFileAssociation u;
    private static final IFileAssociation r;
    private static final IFileAssociation q;
    private static final IFileAssociation t;
    private static final IFileAssociation p;
    private IExternalReference s;
    private Map<String, IFileAssociation> o;
    
    static {
        u = new CsvAssociation();
        r = new TxtAssociation();
        q = new XipAssociation();
        t = new XmlAssociation();
        p = new ZipAssociation();
    }
    
    public FileSystemCachingPolicy() {
        this(new UnboundedCache());
    }
    
    public FileSystemCachingPolicy(final ICache cache) {
        super(cache);
        this.setStaticAttributes(new String[] { "*" });
        this.defineNextStage(XPath.createExpression("*"), this, true);
        this.o = new HashMap<String, IFileAssociation>();
        this.addAssociation(FileSystemCachingPolicy.u);
        this.addAssociation(FileSystemCachingPolicy.r);
        this.addAssociation(FileSystemCachingPolicy.q);
        this.addAssociation(FileSystemCachingPolicy.t);
        this.addAssociation(FileSystemCachingPolicy.p);
    }
    
    public void addAssociation(final IFileAssociation fileAssociation) {
        String[] extensions;
        for (int length = (extensions = fileAssociation.getExtensions()).length, i = 0; i < length; ++i) {
            this.o.put(extensions[i], fileAssociation);
        }
    }
    
    public void removeAssociation(final String s) {
        this.o.remove(s);
    }
    
    @Override
    protected void syncImpl(final IExternalReference s) throws CachingException {
        if (this.s == null) {
            B(this.s = s);
        }
        s.removeChildren();
        final File file = new File(Xlate.get((IModelObject)s, "path", ""));
        if (file.isDirectory()) {
            String[] list;
            for (int length = (list = file.list()).length, i = 0; i < length; ++i) {
                final String s2 = list[i];
                final int lastIndex = s2.lastIndexOf(46);
                final IFileAssociation fileAssociation = this.o.get((lastIndex >= 0) ? s2.substring(lastIndex) : null);
                ICachingPolicy cachingPolicy = (fileAssociation != null) ? fileAssociation.getCachingPolicy(this, s2) : null;
                if (cachingPolicy == null) {
                    cachingPolicy = this;
                }
                final IExternalReference externalReference = (IExternalReference)s.createObject(s2);
                externalReference.setCachingPolicy(cachingPolicy);
                externalReference.setDirty(true);
                s.addChild(externalReference);
                externalReference.setAttribute("path", this.C(externalReference));
            }
        }
        else if (file.exists() && file.canRead()) {
            final String name = file.getName();
            final int lastIndex2 = name.lastIndexOf(46);
            final IFileAssociation fileAssociation2 = this.o.get((lastIndex2 >= 0) ? name.substring(lastIndex2) : null);
            if (fileAssociation2 != null) {
                try {
                    final FileInputStream fileInputStream = new FileInputStream(file);
                    fileAssociation2.apply(s, file.getPath(), fileInputStream);
                    fileInputStream.close();
                }
                catch (IOException ex) {
                    throw new CachingException("Unable to read file: " + file, ex);
                }
            }
        }
    }
    
    private static void B(final IModelObject modelObject) {
        final String value = Xlate.get(modelObject, "path", "");
        if (value.length() > 0 && value.charAt(0) == '~') {
            Xlate.set(modelObject, "path", String.valueOf(System.getProperty("user.dir")) + value.substring(1));
        }
    }
    
    private File C(final IModelObject modelObject) throws CachingException {
        return new File(Xlate.get(modelObject.getParent(), "path", ""), modelObject.getType());
    }
    
    public void flushImpl(final IExternalReference externalReference) throws CachingException {
        final File file = new File(Xlate.get((IModelObject)externalReference, "path", ""));
        if (file.isDirectory()) {
            throw new CachingException("Directory cannot be flushed: " + externalReference);
        }
        try {
            new XmlIO().write(externalReference.getChild(0), file);
        }
        catch (XmlException ex) {
            throw new CachingException("Unable to flush reference: " + externalReference, ex);
        }
    }
    
    @Override
    public URI getURI(final IExternalReference externalReference) throws CachingException {
        return new File(Xlate.get((IModelObject)externalReference, "path", "")).toURI();
    }
}
