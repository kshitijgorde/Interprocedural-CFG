// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import java.net.URI;
import java.util.regex.Matcher;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Pattern;
import org.xmodel.external.CachingException;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.external.IExternalReference;
import java.io.File;
import org.xmodel.external.ICachingPolicy;
import org.xmodel.xpath.XPath;
import org.xmodel.external.ICache;
import java.io.FilenameFilter;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.log.Log;
import org.xmodel.external.ConfiguredCachingPolicy;

public class FolderCachingPolicy extends ConfiguredCachingPolicy
{
    private static Log g;
    final IExpression d;
    final IExpression e;
    private FilenameFilter f;
    private FileCachingPolicy c;
    
    static {
        FolderCachingPolicy.g = Log.getLog("org.xmodel.external.caching");
    }
    
    public FolderCachingPolicy(final ICache cache) {
        super(cache);
        this.d = XPath.createExpression("*[ @type='folder']");
        this.e = XPath.createExpression("file/*");
        this.setStaticAttributes(new String[] { "id", "type", "path" });
        this.c = new FileCachingPolicy(cache);
        this.defineNextStage(this.d, this, true);
        this.defineNextStage(this.e, this.c, true);
        this.f = new FilenameFilter() {
            @Override
            public boolean accept(final File file, final String s) {
                return new File(file, s).isDirectory() || s.endsWith(".xml");
            }
        };
    }
    
    public void setFilenameFilter(final FilenameFilter f) {
        this.f = f;
    }
    
    public void checkout(final IExternalReference externalReference) {
    }
    
    public void checkin(final IExternalReference externalReference) {
    }
    
    public void syncImpl(final IExternalReference externalReference) throws CachingException {
        final File file = new File(Xlate.get((IModelObject)externalReference, "path", ""));
        if (file.isDirectory()) {
            final File[] listFiles = file.listFiles(this.f);
            try {
                final IModelObject object = this.getFactory().createObject(null, externalReference.getType());
                ModelAlgorithms.copyAttributes(externalReference, object);
                File[] array;
                for (int length = (array = listFiles).length, i = 0; i < length; ++i) {
                    final File file2 = array[i];
                    if (file2.isDirectory()) {
                        final IModelObject object2 = this.getFactory().createObject(object, file2.getName());
                        object2.setAttribute("path", file2.getPath());
                        object2.setAttribute("type", "folder");
                        object.addChild(object2);
                    }
                    else if (getRootTag(file2) != null) {
                        final IModelObject object3 = this.getFactory().createObject(object, "file");
                        object3.setID(file2.getName());
                        object3.setAttribute("path", file2.getParent());
                        object.addChild(object3);
                    }
                }
                this.update(externalReference, object);
            }
            catch (Exception ex) {
                throw new CachingException("Unable to sync reference: " + externalReference, ex);
            }
        }
    }
    
    public static String getRootTag(final File file) {
        final Pattern compile = Pattern.compile("^\\s*<\\s*([a-zA-Z0-9_-]+)\\s*>");
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (bufferedReader.ready()) {
                final Matcher matcher = compile.matcher(bufferedReader.readLine());
                if (matcher.matches()) {
                    return matcher.group(1);
                }
            }
        }
        catch (IOException ex) {
            FolderCachingPolicy.g.exception(ex);
        }
        return null;
    }
    
    @Override
    public URI getURI(final IExternalReference externalReference) throws CachingException {
        final String value = Xlate.get((IModelObject)externalReference, "path", (String)null);
        if (value == null) {
            throw new CachingException("Path not defined for reference: " + externalReference);
        }
        return new File(value).toURI();
    }
}
