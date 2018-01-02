// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import com.stonewall.cornerstone.entity.EntityReference;
import org.xmodel.xpath.expression.Context;
import org.xmodel.IModelObject;
import org.xmodel.xpath.XPath;
import org.xmodel.log.Log;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class ContentExpander
{
    final IExpression referentExpr;
    final IExpression refDataExpr;
    private IContext context;
    private ReferenceData data;
    boolean referenced;
    static final Log log;
    
    static {
        log = Log.getLog(ContentExpander.class);
    }
    
    public ContentExpander() {
        this.referentExpr = XPath.createExpression("./*/*[name()=$type and @id=$id]");
        this.refDataExpr = XPath.createExpression("ancestor::*/en:references");
        this.referenced = false;
    }
    
    public void setData(final ReferenceData data) {
        this.data = data;
    }
    
    public static boolean isReference(final IModelObject o) {
        return o.getID() != null && !o.getID().equals("") && (o.isType("en:any") || o.isType("en:network") || o.isType("en:host") || o.isType("en:addressGroup") || o.isType("en:ipInterface") || o.isType("en:ipService") || o.isType("en:ipServiceGroup") || o.isType("en:p1Proposal") || o.isType("en:p2Proposal"));
    }
    
    public IModelObject expand(final IModelObject o) {
        if (this.data == null) {
            this.data = new ReferenceData(this.refDataExpr.queryFirst(new Context(o)));
        }
        this.context = new Context(this.data.getRoot());
        final IModelObject clone = o.cloneTree();
        this._expand(clone);
        return clone;
    }
    
    private IModelObject _expand(final IModelObject object) {
        if (isReference(object)) {
            object.removeChildren();
            final EntityReference ref = new EntityReference(object);
            ref.setData(this.data);
            final IModelObject referent = ref.getReferent().getRoot();
            for (final IModelObject o : referent.getChildren()) {
                object.addChild(o.cloneTree());
            }
        }
        final List<IModelObject> children = new ArrayList<IModelObject>(object.getChildren());
        for (final IModelObject child : children) {
            this._expand(child);
        }
        return object;
    }
    
    private IModelObject getReferent(final IModelObject object) {
        this.referentExpr.setVariable("id", object.getID());
        this.referentExpr.setVariable("type", object.getType());
        final IModelObject referent = this.referentExpr.queryFirst(this.context);
        if (referent == null) {
            ContentExpander.log.info("Could not find referent for object: " + object);
        }
        return referent.cloneTree();
    }
    
    public void prune(final IModelObject o) {
        this._prune(o);
    }
    
    private void _prune(final IModelObject object) {
        if (isReference(object)) {
            object.removeChildren();
        }
        final List<IModelObject> children = object.getChildren();
        for (final IModelObject child : children) {
            this._prune(child);
        }
    }
}
