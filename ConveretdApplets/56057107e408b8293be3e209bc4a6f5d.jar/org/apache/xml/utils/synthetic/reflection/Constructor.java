// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils.synthetic.reflection;

import java.lang.reflect.InvocationTargetException;
import org.apache.xml.utils.synthetic.Class;

public class Constructor extends EntryPoint implements Member
{
    private Class declaringclass;
    private java.lang.reflect.Constructor realconstructor;
    private Class[] parametertypes;
    private String[] parameternames;
    private Class[] exceptiontypes;
    private int modifiers;
    
    public Constructor(final java.lang.reflect.Constructor realconstructor) {
        super(realconstructor);
        this.declaringclass = null;
        this.realconstructor = null;
    }
    
    public Constructor(final java.lang.reflect.Constructor ctor, final Class declaringclass) {
        super(ctor, declaringclass);
        this.declaringclass = null;
        this.realconstructor = null;
    }
    
    public Constructor(final Class declaringclass) {
        super(declaringclass);
        this.declaringclass = null;
        this.realconstructor = null;
    }
    
    public int hashCode() {
        return this.getDeclaringClass().getName().hashCode();
    }
    
    public Object newInstance(final Object[] initargs) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (super.realep != null) {
            return ((java.lang.reflect.Constructor)super.realep).newInstance(initargs);
        }
        throw new InstantiationException("Un-reified org.apache.xml.utils.synthetic.Class doesn't yet support invocation");
    }
}
