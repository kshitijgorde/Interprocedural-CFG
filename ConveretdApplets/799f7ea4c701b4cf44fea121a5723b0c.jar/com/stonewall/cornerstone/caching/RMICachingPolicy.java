// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.caching;

import com.stonewall.cornerstone.jms.msg.event.Event;
import java.util.Map;
import java.util.HashMap;
import org.xmodel.ModelAlgorithms;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.CachingException;
import java.util.Iterator;
import com.stonewall.cornerstone.rmi.IDataAdaptor;
import java.util.ArrayList;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.Context;
import com.stonewall.cornerstone.entity.Server;
import com.stonewall.cornerstone.entity.PolicyServer;
import com.stonewall.cornerstone.rmi.UntypedDataAdaptor;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.XPath;
import org.xmodel.external.ICache;
import java.util.List;
import com.stonewall.cornerstone.rmi.RMIClient;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.utility.ILog;

public class RMICachingPolicy extends EventProcessingCachingPolicy implements ILog
{
    private final IExpression targetExpr;
    private RMIClient client;
    private String target;
    protected Object[] targetParams;
    private String method;
    private String stub;
    private String treeType;
    private List<IExpression> paramExprs;
    
    public RMICachingPolicy(final ICache cache) {
        super(cache);
        this.targetExpr = XPath.createExpression("ancestor-or-self::*/target");
    }
    
    @Override
    public void configure(final IContext context, final IModelObject annotation) throws CachingException {
        super.configure(context, annotation);
        final IDataAdaptor adaptor = new UntypedDataAdaptor(this.getFactory());
        this.client = new RMIClient(new PolicyServer(), adaptor, this.getFactory());
        this.target = this.targetExpr.evaluateString(new Context(annotation));
        this.method = Xlate.get(annotation.getFirstChild("method"), (String)null);
        this.stub = Xlate.get(annotation.getFirstChild("stub"), (String)null);
        this.treeType = Xlate.get(annotation.getFirstChild("tree"), "none");
        this.paramExprs = new ArrayList<IExpression>(3);
        for (final IModelObject param : annotation.getChildren("param")) {
            this.paramExprs.add(XPath.createExpression(Xlate.get(param, (String)null)));
        }
    }
    
    @Override
    protected void syncImpl(final IExternalReference reference) throws CachingException {
        RMICachingPolicy.log.info("syncing: " + reference.getType() + "(" + reference.getID() + ")");
        final IContext context = new Context(reference);
        final List<Object> params = new ArrayList<Object>();
        for (int i = 0; i < this.paramExprs.size(); ++i) {
            params.add(this.paramExprs.get(i).evaluateString(context));
        }
        if (this.stub != null) {
            params.add(this.stub.equals("true"));
        }
        try {
            final long t0 = System.nanoTime();
            if (this.targetParams != null) {
                this.client.setTarget(this.target, this.targetParams);
            }
            else {
                this.client.setTarget(this.target, new Object[0]);
            }
            final Object result = this.client.invoke(this.method, params.toArray());
            final long t2 = System.nanoTime();
            final int msec = Math.round((t2 - t0) / 1000000.0f);
            RMICachingPolicy.log.info("   rmi: " + msec + " ms");
            if (result instanceof List) {
                if (this.treeType.equals("none")) {
                    final IModelObject clone = this.getFactory().createObject(null, reference.getType());
                    ModelAlgorithms.copyAttributes(reference, clone);
                    for (final IModelObject object : (List)result) {
                        clone.addChild(object);
                    }
                    this.update(reference, clone);
                }
                else if (this.treeType.equals("one")) {
                    final List<IModelObject> roots = this.buildTree((List<IModelObject>)result);
                    this.update(reference, roots.get(0));
                }
                else if (this.treeType.equals("many")) {
                    final List<IModelObject> roots = this.buildTree((List<IModelObject>)result);
                    final IModelObject clone2 = this.getFactory().createObject(null, reference.getType());
                    ModelAlgorithms.copyAttributes(reference, clone2);
                    for (final IModelObject object2 : roots) {
                        clone2.addChild(object2);
                    }
                    this.update(reference, clone2);
                }
            }
            else {
                this.update(reference, (IModelObject)result);
            }
        }
        catch (Exception e) {
            throw new CachingException("Unable to sync reference: " + reference, e);
        }
    }
    
    private List<IModelObject> buildTree(final List<IModelObject> objects) throws CachingException {
        final Map<String, IModelObject> map = new HashMap<String, IModelObject>();
        for (final IModelObject object : objects) {
            map.put(object.getID(), object);
        }
        final List<IModelObject> roots = new ArrayList<IModelObject>();
        for (final IModelObject object2 : objects) {
            final String parentID = Xlate.get(object2, "parent", (String)null);
            if (parentID == null) {
                roots.add(object2);
            }
            else {
                final IModelObject parent = map.get(parentID);
                if (parent == null) {
                    throw new CachingException("Parent " + object2.getType() + "[@id = " + parentID + "] not found.");
                }
                parent.addChild(object2);
            }
        }
        return roots;
    }
    
    @Override
    public void handleEvent(final Event event) {
        throw new UnsupportedOperationException();
    }
}
