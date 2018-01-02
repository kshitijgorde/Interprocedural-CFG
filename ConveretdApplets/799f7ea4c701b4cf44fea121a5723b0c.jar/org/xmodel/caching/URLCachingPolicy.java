// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import java.net.URISyntaxException;
import java.net.URI;
import java.net.URL;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.CachingException;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import java.util.HashMap;
import org.xmodel.external.ICache;
import org.xmodel.xpath.XPath;
import java.util.Map;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.external.ConfiguredCachingPolicy;

public class URLCachingPolicy extends ConfiguredCachingPolicy
{
    private static final IFileAssociation n;
    private static final IFileAssociation l;
    private static final IFileAssociation k;
    private static final IFileAssociation m;
    private static final IExpression h;
    private IExpression i;
    private Map<String, IFileAssociation> j;
    
    static {
        n = new CsvAssociation();
        l = new TxtAssociation();
        k = new XipAssociation();
        m = new XmlAssociation();
        h = XPath.createExpression("@url");
    }
    
    public URLCachingPolicy(final ICache cache) {
        super(cache);
        this.setStaticAttributes(new String[] { "id", "url" });
        this.j = new HashMap<String, IFileAssociation>();
        this.addAssociation(URLCachingPolicy.n);
        this.addAssociation(URLCachingPolicy.l);
        this.addAssociation(URLCachingPolicy.k);
        this.addAssociation(URLCachingPolicy.m);
        this.i = URLCachingPolicy.h;
    }
    
    public void addAssociation(final IFileAssociation fileAssociation) {
        String[] extensions;
        for (int length = (extensions = fileAssociation.getExtensions()).length, i = 0; i < length; ++i) {
            this.j.put(extensions[i], fileAssociation);
        }
    }
    
    public void removeAssociation(final String s) {
        this.j.remove(s);
    }
    
    @Override
    public void configure(final IContext context, final IModelObject modelObject) throws CachingException {
        super.configure(context, modelObject);
        this.i = Xlate.get(modelObject, "url", (IExpression)null);
        if (this.i == null) {
            this.i = URLCachingPolicy.h;
        }
    }
    
    @Override
    protected void syncImpl(final IExternalReference externalReference) throws CachingException {
        final String evaluateString = this.i.evaluateString(new StatefulContext(externalReference));
        if (evaluateString == null) {
            return;
        }
        URL url = null;
        try {
            final int lastIndex = evaluateString.lastIndexOf(46);
            if (lastIndex >= 0) {
                final IFileAssociation fileAssociation = this.j.get(evaluateString.substring(lastIndex));
                if (fileAssociation != null) {
                    url = new URL(evaluateString);
                    fileAssociation.apply(externalReference, externalReference.getType(), url.openStream());
                }
            }
        }
        catch (Exception ex) {
            throw new CachingException("Unable to sync url: " + url, ex);
        }
    }
    
    @Override
    public URI getURI(final IExternalReference externalReference) throws CachingException {
        try {
            final String value = Xlate.get((IModelObject)externalReference, "url", (String)null);
            if (value == null) {
                throw new CachingException("External reference does not have a url attribute: " + externalReference);
            }
            return new URI(value);
        }
        catch (URISyntaxException ex) {
            throw new CachingException("Unable to create URI for external reference: " + externalReference, ex);
        }
    }
}
