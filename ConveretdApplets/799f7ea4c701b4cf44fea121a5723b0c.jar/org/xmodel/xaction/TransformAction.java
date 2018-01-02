// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.expression.IContext;
import java.util.Iterator;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import java.util.List;

public class TransformAction extends GuardedAction
{
    private ScriptAction d;
    private ScriptAction e;
    private List<IXAction> b;
    private List<IXAction> c;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.d = xActionDocument.createChildScript("fromLeft", new String[0]);
        this.e = xActionDocument.createChildScript("fromRight", new String[0]);
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final ArrayList<IModelObject> list2 = new ArrayList<IModelObject>();
        boolean b = true;
        for (final IModelObject modelObject : xActionDocument.getRoot().getChildren()) {
            if (modelObject.isType("fromLeft") || modelObject.isType("fromRight")) {
                b = false;
            }
            else {
                if (modelObject.isType("transform")) {
                    continue;
                }
                if (modelObject.isType("package")) {
                    continue;
                }
                if (b) {
                    list.add(modelObject);
                }
                else {
                    list2.add(modelObject);
                }
            }
        }
        this.b = new ArrayList<IXAction>();
        final Iterator<Object> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            final IXAction action = xActionDocument.getAction(iterator2.next());
            if (action != null) {
                this.b.add(action);
            }
        }
        this.c = new ArrayList<IXAction>();
        final Iterator<Object> iterator3 = list2.iterator();
        while (iterator3.hasNext()) {
            final IXAction action2 = xActionDocument.getAction(iterator3.next());
            if (action2 != null) {
                this.c.add(action2);
            }
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final Object value = context.getScope().get("transform_direction");
        if (value == null) {
            throw new IllegalArgumentException("Transform direction must be defined in variable, $transform_direction:" + this);
        }
        final Iterator<IXAction> iterator = this.b.iterator();
        while (iterator.hasNext()) {
            final Object[] run = iterator.next().run(context);
            if (run != null) {
                return run;
            }
        }
        final String string = value.toString();
        if (string.equals("fromLeft")) {
            if (this.d != null) {
                return this.d.run(context);
            }
        }
        else {
            if (!string.equals("fromRight")) {
                throw new IllegalArgumentException("Transform direction value must be one of: \"fromLeft\", \"fromRight\": " + this);
            }
            if (this.e != null) {
                return this.e.run(context);
            }
        }
        final Iterator<IXAction> iterator2 = this.c.iterator();
        while (iterator2.hasNext()) {
            final Object[] run2 = iterator2.next().run(context);
            if (run2 != null) {
                return run2;
            }
        }
        return null;
    }
}
