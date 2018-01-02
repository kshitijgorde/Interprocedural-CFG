// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import java.lang.reflect.Constructor;
import java.util.Collections;
import org.xmodel.Xlate;
import java.util.Iterator;
import java.util.List;
import org.xmodel.Element;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public class Target
{
    private IModelObject root;
    protected static final Log log;
    
    static {
        log = Log.getLog(Target.class);
    }
    
    public Target(final DataFactory factory, String className, final Object... params) {
        final List<IData> data = factory.createData(params);
        if (className.charAt(0) == '~') {
            className = "com.stonewall.cornerstone" + className.substring(1);
        }
        (this.root = new Element("rmi:target")).setAttribute("class", className);
        for (final IData d : data) {
            this.root.getCreateChild("rmi:data").addChild(d.getRoot());
        }
    }
    
    Target(final IModelObject e) {
        this.root = e;
    }
    
    String getClassName() {
        return Xlate.get(this.root, "class", (String)null);
    }
    
    Class getTargetClass() throws ClassNotFoundException {
        return Class.forName(this.getClassName());
    }
    
    Object construct(final DataFactory factory) {
        final IModelObject dataParent = this.root.getFirstChild("rmi:data");
        List<Object> data = Collections.emptyList();
        if (dataParent != null) {
            data = factory.createObjects(dataParent.getChildren());
        }
        try {
            final Constructor con = Reflect.locateConstructor(this.getTargetClass(), data);
            return con.newInstance(data.toArray());
        }
        catch (Exception e) {
            return null;
        }
    }
    
    IModelObject getRoot() {
        return this.root;
    }
}
