// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.util.List;
import java.util.ArrayList;
import org.xmodel.Xlate;
import java.util.Collections;
import java.util.Iterator;
import java.util.Collection;
import org.xmodel.Element;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public class ListData implements IData
{
    private IModelObject root;
    static final Log log;
    
    static {
        log = Log.getLog(ListData.class);
    }
    
    public ListData(final IDataAdaptor adaptor, final Object param) {
        this.root = new Element("rmi:listData");
        final Collection col = (Collection)param;
        if (col.isEmpty()) {
            return;
        }
        this.root.setAttribute("class", param.getClass().getName());
        for (final Object o : (Collection)param) {
            final String s = adaptor.transform(o);
            final IModelObject entry = new Element("rmi:entry");
            this.root.addChild(entry);
            entry.setAttribute("class", adaptor.getClassName(o));
            entry.setValue(s);
        }
    }
    
    public ListData(final IModelObject imo) {
        this.root = imo;
    }
    
    @Override
    public Object getValue(final IDataAdaptor adaptor) {
        final List<IModelObject> children = this.root.getChildren("rmi:entry");
        if (children.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        try {
            String className = Xlate.get(this.root, "class", (String)null);
            Collection objects = null;
            try {
                objects = (Collection)Class.forName(className).newInstance();
            }
            catch (Exception e2) {
                objects = new ArrayList();
            }
            for (final IModelObject o : children) {
                final String s = Xlate.get(o, (String)null);
                className = Xlate.get(o, "class", (String)null);
                objects.add(adaptor.transform(className, s));
            }
            return objects;
        }
        catch (Exception e) {
            ListData.log.error(this, e);
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
