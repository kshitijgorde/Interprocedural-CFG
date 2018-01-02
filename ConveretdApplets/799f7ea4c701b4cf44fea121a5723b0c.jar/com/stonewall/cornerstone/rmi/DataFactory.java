// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import java.util.Iterator;
import org.xmodel.IModelObject;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class DataFactory
{
    private IDataAdaptor adaptor;
    
    public DataFactory(final IDataAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    
    public List<IData> createData(final Object... objects) {
        final List<IData> data = new ArrayList<IData>();
        for (final Object o : objects) {
            if (o instanceof Collection) {
                data.add(new ListData(this.adaptor, o));
            }
            else if (o.getClass().isArray()) {
                data.add(new ArrayData(this.adaptor, o));
            }
            else {
                data.add(new SingleData(this.adaptor, o));
            }
        }
        return data;
    }
    
    public List<Object> createObjects(final List<IModelObject> data) {
        final List<Object> params = new ArrayList<Object>();
        for (final IModelObject imo : data) {
            IData d = null;
            if (imo.getType().equals("rmi:listData")) {
                d = new ListData(imo);
            }
            else if (imo.getType().equals("rmi:arrayData")) {
                d = new ArrayData(imo);
            }
            else {
                d = new SingleData(imo);
            }
            params.add(d.getValue(this.adaptor));
        }
        return params;
    }
}
