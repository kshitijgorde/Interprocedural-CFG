// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.util.Iterator;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.diff.ConfiguredXmlMatcher;
import java.util.List;
import java.util.ArrayList;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.AnnotatingChangeSet;
import org.xmodel.IntersectChangeSet;
import org.xmodel.UnionChangeSet;
import org.xmodel.diff.RegularChangeSet;
import org.xmodel.Xlate;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ModelObjectFactory;
import org.xmodel.IChangeSet;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.diff.IXmlMatcher;
import org.xmodel.diff.XmlDiffer;

public class CopyAction extends GuardedAction
{
    private XmlDiffer \u00fc;
    private IXmlMatcher \u0102;
    private IExpression \u0101;
    private IExpression \u0103;
    private IExpression \u00ff;
    private IExpression \u00fe;
    private IChangeSet \u00fd;
    private boolean \u0100;
    
    public CopyAction() {
        this.\u00fc = new XmlDiffer();
    }
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final IModelObject root = xActionDocument.getRoot();
        this.\u0102 = this.getMatcher(root);
        this.\u00fc.setMatcher(this.\u0102);
        this.\u00fc.setFactory(new ModelObjectFactory());
        this.\u0101 = xActionDocument.getExpression("source", true);
        this.\u0103 = xActionDocument.getExpression("target", true);
        this.\u00ff = xActionDocument.getExpression("ignore", true);
        this.\u00fe = xActionDocument.getExpression("ordered", true);
        this.\u0100 = Xlate.get(root, "orderAll", false);
        if (this.\u0101 == null) {
            this.\u0101 = xActionDocument.getExpression();
        }
        if (this.\u0103 == null) {
            this.\u0103 = xActionDocument.getExpression();
        }
        final String value = Xlate.get(xActionDocument.getRoot(), "op", "copy");
        if (value.equals("copy")) {
            this.\u00fd = new RegularChangeSet();
        }
        else if (value.equals("union")) {
            this.\u00fd = new UnionChangeSet();
        }
        else if (value.equals("intersect")) {
            this.\u00fd = new IntersectChangeSet();
        }
        else if (value.equals("annotate")) {
            this.\u00fd = new AnnotatingChangeSet();
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final IModelObject queryFirst = this.\u0101.queryFirst(context);
        if (queryFirst == null) {
            return null;
        }
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final ArrayList<IModelObject> ordered = new ArrayList<IModelObject>();
        for (final IModelObject modelObject : this.\u0103.query(context, null)) {
            if (this.\u0102 instanceof ConfiguredXmlMatcher) {
                final ConfiguredXmlMatcher matcher = (ConfiguredXmlMatcher)this.\u0102;
                matcher.setOrderAll(this.\u0100);
                this.\u00fc.setMatcher(matcher);
                final StatefulContext statefulContext = new StatefulContext(context, queryFirst);
                final StatefulContext statefulContext2 = new StatefulContext(context, modelObject);
                if (this.\u00ff != null) {
                    list.clear();
                    this.\u00ff.query(statefulContext, list);
                    this.\u00ff.query(statefulContext2, list);
                    matcher.ignore(list);
                }
                if (this.\u00fe != null) {
                    ordered.clear();
                    this.\u00fe.query(statefulContext, ordered);
                    this.\u00fe.query(statefulContext2, ordered);
                    matcher.setOrdered(ordered);
                }
            }
            this.\u00fd.clearChanges();
            this.\u00fc.diff(modelObject, queryFirst, this.\u00fd);
            this.\u00fd.applyChanges();
        }
        return null;
    }
}
