// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import org.xmodel.Xlate;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.xmodel.Element;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public class Method
{
    protected IModelObject root;
    static final Log log;
    
    static {
        log = Log.getLog(Method.class);
    }
    
    public Method() {
    }
    
    public Method(final DataFactory factory, final String name, final Object... params) {
        final List<IData> data = factory.createData(params);
        (this.root = new Element("rmi:method")).setAttribute("name", name);
        final IModelObject parent = this.root.getCreateChild("rmi:data");
        for (final IData d : data) {
            parent.addChild(d.getRoot());
        }
    }
    
    public Method(final IModelObject e) {
        this.root = e;
    }
    
    Object invoke(final DataFactory factory, Object target) throws RMIException {
        final IModelObject dataParent = this.root.getFirstChild("rmi:data");
        List<Object> args = Collections.emptyList();
        if (dataParent != null) {
            args = factory.createObjects(dataParent.getChildren());
        }
        final Class targetClass = target.getClass();
        final java.lang.reflect.Method reflect = Reflect.locateMethod(targetClass, this.getName(), args);
        final int modifiers = reflect.getModifiers();
        if (Modifier.isStatic(modifiers)) {
            target = targetClass;
        }
        try {
            return reflect.invoke(target, args.toArray());
        }
        catch (Exception ite) {
            Method.log.error(this, ite);
            throw new RMIException(ite);
        }
    }
    
    String getName() {
        return Xlate.get(this.root, "name", (String)null);
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
}
