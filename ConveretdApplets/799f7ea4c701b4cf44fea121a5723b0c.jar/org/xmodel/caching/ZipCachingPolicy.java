// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import org.xmodel.xml.XmlIO;
import org.xmodel.external.ExternalReference;
import java.util.Enumeration;
import java.io.IOException;
import org.xmodel.external.CachingException;
import org.xmodel.ModelObject;
import java.util.StringTokenizer;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.File;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.external.IExternalReference;
import java.util.HashMap;
import org.xmodel.external.ICachingPolicy;
import org.xmodel.xpath.XPath;
import org.xmodel.external.ICache;
import org.xmodel.external.UnboundedCache;
import java.util.Map;
import org.xmodel.external.ConfiguredCachingPolicy;

public class ZipCachingPolicy extends ConfiguredCachingPolicy
{
    private static final IFileAssociation a;
    private static final IFileAssociation _;
    private static final IFileAssociation b;
    private Map<String, IFileAssociation> Z;
    
    static {
        a = new TxtAssociation();
        _ = new XipAssociation();
        b = new XmlAssociation();
    }
    
    public ZipCachingPolicy() {
        this(new UnboundedCache());
    }
    
    public ZipCachingPolicy(final ICache cache) {
        super(cache);
        this.setStaticAttributes(new String[] { "path", "zipFile" });
        this.defineNextStage(XPath.createExpression(".//*[ @entry]"), new ZipEntryCachingPolicy(), true);
        this.Z = new HashMap<String, IFileAssociation>();
        this.addAssociation(ZipCachingPolicy.a);
        this.addAssociation(ZipCachingPolicy._);
        this.addAssociation(ZipCachingPolicy.b);
    }
    
    public void addAssociation(final IFileAssociation fileAssociation) {
        String[] extensions;
        for (int length = (extensions = fileAssociation.getExtensions()).length, i = 0; i < length; ++i) {
            this.Z.put(extensions[i], fileAssociation);
        }
    }
    
    public void removeAssociation(final String s) {
        this.Z.remove(s);
    }
    
    public IFileAssociation getAssociation(final String s) {
        return this.Z.get(s);
    }
    
    @Override
    protected void syncImpl(final IExternalReference externalReference) throws CachingException {
        final File file = new File(Xlate.get((IModelObject)externalReference, "path", "."));
        if (file.canRead()) {
            try {
                final ZipFile zipFile = new ZipFile(file);
                externalReference.setAttribute("zipFile", zipFile);
                final IModelObject cloneObject = externalReference.cloneObject();
                String b = null;
                final Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    final ZipEntry zipEntry = (ZipEntry)entries.nextElement();
                    if (zipEntry.isDirectory()) {
                        continue;
                    }
                    final String name = zipEntry.getName();
                    if (b == null) {
                        b = B(name);
                    }
                    new StatefulContext(externalReference).set("path", name);
                    int n = 0;
                    final int lastIndex = name.lastIndexOf(46);
                    if (lastIndex >= 0) {
                        n = ((this.Z.get(name.substring(lastIndex)) != null) ? 1 : 0);
                    }
                    if (n == 0) {
                        continue;
                    }
                    if (b != null) {
                        IModelObject createChild = cloneObject;
                        final StringTokenizer stringTokenizer = new StringTokenizer(name, b);
                        while (stringTokenizer.hasMoreTokens()) {
                            createChild = createChild.getCreateChild(stringTokenizer.nextToken());
                        }
                        if (zipEntry.isDirectory()) {
                            continue;
                        }
                        createChild.setAttribute("entry", zipEntry);
                    }
                    else {
                        final ModelObject modelObject = new ModelObject(zipEntry.getName());
                        modelObject.setAttribute("entry", zipEntry);
                        cloneObject.addChild(modelObject);
                    }
                }
                cloneObject.setAttribute("separator", b);
                this.update(externalReference, cloneObject);
            }
            catch (IOException ex) {
                throw new CachingException("Unable to load zip file: " + file, ex);
            }
        }
    }
    
    private static String B(final String s) {
        if (s.indexOf("\\ ") >= 0) {
            return "/";
        }
        if (s.indexOf("/") >= 0) {
            return "/";
        }
        return null;
    }
    
    public static void main(final String[] array) throws Exception {
        final StringTokenizer stringTokenizer = new StringTokenizer("main.xml", "/");
        if (stringTokenizer.hasMoreTokens()) {
            System.out.println(stringTokenizer.nextToken());
        }
        System.exit(1);
        final ExternalReference externalReference = new ExternalReference("zip");
        externalReference.setCachingPolicy(new ZipCachingPolicy());
        externalReference.setAttribute("path", "/Users/bdunnagan/xmodel.jar");
        externalReference.setDirty(true);
        System.out.println(XmlIO.toString(externalReference));
    }
}
