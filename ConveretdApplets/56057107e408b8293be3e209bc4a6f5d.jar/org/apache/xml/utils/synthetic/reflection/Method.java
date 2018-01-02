// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils.synthetic.reflection;

import org.apache.xml.utils.synthetic.SynthesisException;
import java.lang.reflect.InvocationTargetException;
import org.apache.xml.utils.synthetic.Class;

public class Method extends EntryPoint implements Member
{
    public Method(final String name, final Class declaringclass) {
        super(declaringclass);
        super.name = name;
    }
    
    public Method(final java.lang.reflect.Method realmethod) {
        super(realmethod);
    }
    
    public Method(final java.lang.reflect.Method ctor, final Class declaringclass) {
        super(ctor, declaringclass);
    }
    
    public int hashCode() {
        return this.getDeclaringClass().getName().hashCode() ^ this.getName().hashCode();
    }
    
    public Object invoke(final Object obj, final Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (super.realep != null) {
            return ((java.lang.reflect.Method)super.realep).invoke(obj, args);
        }
        throw new IllegalAccessException("Un-reified org.apache.xml.utils.synthetic.Class doesn't yet support invocation");
    }
    
    public void setReturnType(final Class returntype) throws SynthesisException {
        if (super.realep != null) {
            throw new SynthesisException(2);
        }
        super.returntype = returntype;
    }
}
