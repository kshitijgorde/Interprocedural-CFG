// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net;

import org.xmodel.PathSyntaxException;
import org.xmodel.xpath.XPath;
import org.xmodel.external.IExternalReference;
import java.io.IOException;
import org.xmodel.external.CachingException;
import org.xmodel.Xlate;
import org.xmodel.xml.XmlIO;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import java.util.List;
import org.xmodel.diff.IXmlMatcher;
import org.xmodel.diff.DefaultXmlMatcher;
import org.xmodel.external.ICache;
import org.xmodel.external.UnboundedCache;
import org.xmodel.log.Log;
import org.xmodel.external.ConfiguredCachingPolicy;

public class NetworkCachingPolicy extends ConfiguredCachingPolicy
{
    private static final Log W;
    private Client V;
    private String Y;
    private int X;
    
    static {
        W = Log.getLog(NetworkCachingPolicy.class);
    }
    
    public NetworkCachingPolicy() {
        this(new UnboundedCache());
    }
    
    public NetworkCachingPolicy(final ICache cache) {
        super(cache);
        this.setStaticAttributes(new String[] { "id" });
        this.getDiffer().setMatcher(new DefaultXmlMatcher(true));
    }
    
    public Client getClient() {
        return this.V;
    }
    
    public void setStaticAttributes(final List<String> list) {
        this.setStaticAttributes(list.toArray(new String[0]));
    }
    
    @Override
    public void configure(final IContext context, final IModelObject modelObject) throws CachingException {
        super.configure(context, modelObject);
        final XmlIO xmlIO = new XmlIO();
        final String value = Xlate.get(modelObject, "host", Xlate.childGet(modelObject, "host", (String)null));
        if (value == null) {
            throw new CachingException("Host not defined in annotation: \n" + xmlIO.write(modelObject));
        }
        final int value2 = Xlate.get(modelObject, "port", Xlate.childGet(modelObject, "port", 0));
        if (value2 == 0) {
            throw new CachingException("Port not defined in annotation: \n" + xmlIO.write(modelObject));
        }
        this.X = Xlate.get(modelObject, "timeout", Xlate.childGet(modelObject, "timeout", 1000));
        try {
            this.V = new Client(value, value2, this.X, true);
        }
        catch (IOException ex) {
            throw new CachingException("Problem creating client.", ex);
        }
        this.Y = Xlate.get(modelObject, "query", Xlate.childGet(modelObject, "query", (String)null));
        if (this.Y != null) {
            this.A(this.Y);
        }
    }
    
    @Override
    protected void syncImpl(final IExternalReference externalReference) throws CachingException {
        if (this.Y == null) {
            this.Y = Xlate.get((IModelObject)externalReference, "query", (String)null);
            if (this.Y == null) {
                throw new CachingException("Query not defined.");
            }
        }
        Session connect = null;
        while (connect == null) {
            try {
                connect = this.V.connect(this.X);
                break;
            }
            catch (Exception ex) {
                NetworkCachingPolicy.W.error(ex.getMessage());
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex3) {}
            }
        }
        if (connect != null) {
            try {
                connect.attach(this.Y, externalReference);
            }
            catch (IOException ex2) {
                throw new CachingException(String.format("Unable to attach to xpath, %s.", this.Y), ex2);
            }
        }
    }
    
    @Override
    protected void flushImpl(final IExternalReference externalReference) throws CachingException {
    }
    
    private void A(final String s) {
        try {
            XPath.compileExpression(s);
        }
        catch (PathSyntaxException ex) {
            throw new CachingException(String.format("Error in remote expression: %s\n", s), ex);
        }
    }
}
