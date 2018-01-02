// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import com.stonewall.cornerstone.entity.EntityFactory;
import org.xmodel.external.IExternalReference;
import org.xmodel.IModelObject;
import org.xmodel.ElementFactory;
import org.xmodel.log.Log;
import org.xmodel.IModelObjectFactory;

public class UntypedDataAdaptor implements IDataAdaptor
{
    private IModelObjectFactory factory;
    static final Log log;
    
    static {
        log = Log.getLog(UntypedDataAdaptor.class);
    }
    
    public UntypedDataAdaptor() {
        this(new ElementFactory());
    }
    
    public UntypedDataAdaptor(final IModelObjectFactory factory) {
        this.factory = factory;
    }
    
    @Override
    public String getClassName(final Object o) {
        if (o instanceof IModelObject || o instanceof IExternalReference) {
            return EntityFactory.EntityType.valueOf((IModelObject)o).getEntityClass().getName();
        }
        return o.getClass().getName();
    }
    
    @Override
    public String transform(final Object o) {
        if (o instanceof IModelObject || o instanceof IExternalReference) {
            final ModelBuilder builder = new ModelBuilder();
            builder.setFactory(this.factory);
            builder.skipOutputPrefix("xm");
            final IModelObject imo = (IModelObject)o;
            return builder.writeModel(imo, IXmlIO.Style.compact);
        }
        return o.toString();
    }
    
    @Override
    public Object transform(final String className, final String s) {
        if (className.equals(String.class.getName())) {
            return s;
        }
        if (className.equals(Integer.class.getName())) {
            return new Integer(s);
        }
        if (className.equals(Boolean.class.getName())) {
            return new Boolean(s);
        }
        try {
            final ModelBuilder builder = new ModelBuilder();
            builder.setFactory(this.factory);
            return builder.buildModel(s);
        }
        catch (Exception ex) {
            UntypedDataAdaptor.log.error("cannot build document: " + s, ex);
            return null;
        }
    }
}
