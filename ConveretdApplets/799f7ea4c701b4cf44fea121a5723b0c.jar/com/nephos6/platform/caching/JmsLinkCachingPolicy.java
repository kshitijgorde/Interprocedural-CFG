// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.caching;

import org.xmodel.PathSyntaxException;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.xidget.JmsLink;
import java.io.IOException;
import org.xmodel.net.ILink;
import com.nephos6.platform.jms.JmsLinkRegistry;
import org.xmodel.external.CachingException;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import java.util.List;
import org.xmodel.diff.IXmlMatcher;
import org.xmodel.diff.DefaultXmlMatcher;
import java.util.HashMap;
import org.xmodel.external.ICache;
import org.xmodel.external.UnboundedCache;
import org.xmodel.net.Session;
import org.xmodel.external.IExternalReference;
import java.util.Map;
import org.xmodel.external.ConfiguredCachingPolicy;

public class JmsLinkCachingPolicy extends ConfiguredCachingPolicy
{
    private String xpath;
    private Map<IExternalReference, Session> sessions;
    
    public JmsLinkCachingPolicy() {
        this(new UnboundedCache());
    }
    
    public JmsLinkCachingPolicy(final ICache cache) {
        super(cache);
        this.sessions = new HashMap<IExternalReference, Session>();
        this.setStaticAttributes(new String[] { "id" });
        this.getDiffer().setMatcher(new DefaultXmlMatcher(true));
    }
    
    public void setStaticAttributes(final List<String> list) {
        this.setStaticAttributes(list.toArray(new String[0]));
    }
    
    @Override
    public void configure(final IContext context, final IModelObject annotation) throws CachingException {
        super.configure(context, annotation);
        this.xpath = Xlate.get(annotation, "xpath", Xlate.childGet(annotation, "xpath", (String)null));
        if (this.xpath != null) {
            this.validate(this.xpath);
        }
    }
    
    @Override
    protected void syncImpl(final IExternalReference reference) throws CachingException {
        try {
            if (this.xpath == null) {
                this.xpath = Xlate.get((IModelObject)reference, "xpath", (String)null);
                if (this.xpath == null) {
                    return;
                }
            }
            final String incomingQueue = Xlate.get((IModelObject)reference, "queue", (String)null);
            final JmsLink link = JmsLinkRegistry.getLinkByQueue(incomingQueue);
            Session session = this.sessions.get(reference);
            if (session == null) {
                session = link.getProtocol().openSession(link);
                this.sessions.put(reference, session);
            }
            session.attach(this.xpath, reference);
        }
        catch (IOException e) {
            throw new CachingException("Unable to sync reference: " + reference, e);
        }
    }
    
    private void validate(final String xpath) {
        try {
            XPath.compileExpression(xpath);
        }
        catch (PathSyntaxException e) {
            final String message = String.format("Error in remote expression: %s\n", xpath);
            throw new CachingException(message, e);
        }
    }
}
