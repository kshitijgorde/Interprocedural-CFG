// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import java.lang.reflect.Method;
import org.xmodel.xml.IXmlIO;
import java.lang.reflect.Constructor;
import com.stonewall.cornerstone.entity.EntityReference;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.ElementFactory;
import org.xmodel.log.Log;
import org.xmodel.IModelObjectFactory;

public class TypedDataAdaptor implements IDataAdaptor
{
    private IModelObjectFactory factory;
    static final Log log;
    
    static {
        log = Log.getLog(TypedDataAdaptor.class);
    }
    
    public TypedDataAdaptor() {
        this(new ElementFactory());
    }
    
    public TypedDataAdaptor(final IModelObjectFactory factory) {
        this.factory = factory;
    }
    
    @Override
    public String getClassName(final Object o) {
        return o.getClass().getName();
    }
    
    @Override
    public Object transform(final String className, final String s) {
        try {
            final Class c = Class.forName(className);
            if (className.equals(String.class.getName())) {
                return s;
            }
            if (className.equals(Integer.class.getName())) {
                return new Integer(s);
            }
            if (className.equals(Boolean.class.getName())) {
                return new Boolean(s);
            }
            IModelObject mo = null;
            try {
                final ModelBuilder builder = new ModelBuilder();
                builder.setFactory(this.factory);
                mo = builder.buildModel(s);
            }
            catch (Exception ex) {
                TypedDataAdaptor.log.error("cannot build document: " + s, ex);
                return null;
            }
            if (c.isAssignableFrom(IModelObject.class)) {
                return mo;
            }
            if (className.equals(EntityReference.class.getName())) {
                return new EntityReference(mo);
            }
            final Constructor con = c.getConstructor(IModelObject.class);
            return con.newInstance(mo);
        }
        catch (Exception ex2) {
            TypedDataAdaptor.log.error("Cannot construct class: " + className, ex2);
            return null;
        }
    }
    
    @Override
    public String transform(Object o) {
        try {
            final Method m = o.getClass().getMethod("getRoot", (Class<?>[])new Class[0]);
            if (m != null) {
                o = m.invoke(o, new Object[0]);
            }
        }
        catch (Exception ex) {}
        if (o instanceof IModelObject) {
            final IModelObject e = (IModelObject)o;
            final ModelBuilder builder = new ModelBuilder();
            builder.setFactory(this.factory);
            return builder.writeModel(e, IXmlIO.Style.compact);
        }
        return o.toString();
    }
}
