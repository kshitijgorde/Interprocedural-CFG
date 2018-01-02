// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import org.xmodel.Xlate;
import java.lang.reflect.Array;
import org.xmodel.Element;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public class ArrayData implements IData
{
    private IModelObject root;
    static final Log log;
    
    static {
        log = Log.getLog(ArrayData.class);
    }
    
    public ArrayData(final IDataAdaptor adaptor, final Object param) {
        this.root = new Element("rmi:arrayData");
        if (Array.getLength(param) == 0) {
            return;
        }
        this.root.setAttribute("class", param.getClass().getComponentType().getName());
        for (int i = 0; i < Array.getLength(param); ++i) {
            final Object o = Array.get(param, i);
            final String s = adaptor.transform(o);
            final IModelObject entry = new Element("rmi:entry");
            this.root.addChild(entry);
            entry.setAttribute("class", adaptor.getClassName(o));
            entry.setValue(s);
        }
    }
    
    public ArrayData(final IModelObject imo) {
        this.root = imo;
    }
    
    @Override
    public Object getValue(final IDataAdaptor adaptor) {
        try {
            final List<IModelObject> children = this.root.getChildren("rmi:entry");
            if (children.isEmpty()) {
                return new Object[0];
            }
            String className = Xlate.get(this.root, "class", (String)null);
            final Object a = Array.newInstance(Class.forName(className), children.size());
            final List<Object> values = new ArrayList<Object>();
            for (final IModelObject o : this.root.getChildren("rmi:entry")) {
                final String s = Xlate.get(o, (String)null);
                className = Xlate.get(o, "class", (String)null);
                values.add(adaptor.transform(className, s));
            }
            System.arraycopy(values.toArray(), 0, a, 0, values.size());
            return a;
        }
        catch (Exception e) {
            ArrayData.log.error(this, e);
            return null;
        }
    }
    
    @Override
    public IModelObject getRoot() {
        return this.root;
    }
    
    @Override
    public String toString() {
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(this.root, IXmlIO.Style.printable);
    }
}
