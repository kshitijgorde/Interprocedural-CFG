// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.Element;
import java.util.Iterator;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.Context;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.SegmentDbAccess;
import org.xmodel.IModelObject;

public class Segment extends Entity
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.segment.getQualifiedName();
    }
    
    public Segment() {
        super(Segment.tag);
    }
    
    public Segment(final String id) {
        super(Segment.tag, id);
    }
    
    public Segment(final IModelObject root) {
        super(root);
    }
    
    public Segment(final Entity end1, final Entity end2) {
        super(Segment.tag);
        this.setEndpoints(end1, end2);
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new SegmentDbAccess().fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new SegmentDbAccess().insert(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new SegmentDbAccess().delete(this);
    }
    
    public void setEndpoints(final Entity endA, final Entity endB) {
        this.setEndpoints(endA.getReference(), endB.getReference());
    }
    
    public void setEndpoints(final EntityReference refA, final EntityReference refB) {
        this.addEndpoint(refA);
        this.addEndpoint(refB);
    }
    
    public EntityReference[] getEndpoints() {
        final EntityReference[] result = new EntityReference[2];
        final String s = ".//en:endpoint/*";
        final IExpression path = XPath.createExpression(s);
        int i = 0;
        for (final Object o : path.evaluateNodes(new Context(this.root))) {
            final IModelObject ref = (IModelObject)o;
            result[i++] = new EntityReference(ref.cloneTree());
        }
        return result;
    }
    
    private void addEndpoint(final EntityReference ref) {
        final IModelObject ep = new Element("en:endpoint");
        ep.addChild(ref.cloneContent());
        this.root.addChild(ep);
    }
}
