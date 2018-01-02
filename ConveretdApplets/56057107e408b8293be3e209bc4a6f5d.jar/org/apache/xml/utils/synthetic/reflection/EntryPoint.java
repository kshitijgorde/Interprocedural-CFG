// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils.synthetic.reflection;

import java.lang.reflect.Modifier;
import org.apache.xml.utils.synthetic.SynthesisException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.apache.xml.utils.synthetic.Class;

public abstract class EntryPoint implements Member
{
    protected Object realep;
    private Class declaringclass;
    protected Class returntype;
    private String[] parameternames;
    private Class[] parametertypes;
    private Class[] exceptiontypes;
    private int modifiers;
    protected String name;
    private StringBuffer body;
    private String language;
    java.lang.Class[] realE;
    java.lang.Class[] realP;
    
    protected EntryPoint(final Object ep) throws IllegalArgumentException {
        this(ep, null);
    }
    
    protected EntryPoint(final Object ep, Class declaringclass) throws IllegalArgumentException {
        this.declaringclass = null;
        this.returntype = null;
        this.parameternames = new String[0];
        this.parametertypes = new Class[0];
        this.exceptiontypes = new Class[0];
        this.name = null;
        this.body = null;
        this.language = null;
        this.realep = ep;
        this.declaringclass = declaringclass;
        if (ep instanceof Method) {
            final Method m = (Method)ep;
            if (declaringclass == null) {
                declaringclass = Class.forClass(m.getDeclaringClass());
            }
            this.name = m.getName();
            this.modifiers = m.getModifiers();
            this.returntype = Class.forClass(m.getReturnType());
            this.realP = m.getParameterTypes();
            this.realE = m.getExceptionTypes();
        }
        else {
            if (!(ep instanceof Constructor)) {
                throw new IllegalArgumentException();
            }
            final Constructor c = (Constructor)ep;
            if (declaringclass == null) {
                declaringclass = Class.forClass(c.getDeclaringClass());
            }
            this.name = declaringclass.getShortName();
            this.modifiers = c.getModifiers();
            this.returntype = declaringclass;
            this.realP = c.getParameterTypes();
            this.realE = c.getExceptionTypes();
        }
    }
    
    public EntryPoint(final Class declaringclass) {
        this.declaringclass = null;
        this.returntype = null;
        this.parameternames = new String[0];
        this.parametertypes = new Class[0];
        this.exceptiontypes = new Class[0];
        this.name = null;
        this.body = null;
        this.language = null;
        this.declaringclass = declaringclass;
    }
    
    public void addExceptionType(final Class exception) throws SynthesisException {
        if (this.realep != null) {
            throw new SynthesisException(2);
        }
        final Class[] e = new Class[this.exceptiontypes.length + 1];
        System.arraycopy(this.exceptiontypes, 0, e, 0, this.exceptiontypes.length);
        e[this.exceptiontypes.length] = exception;
        this.exceptiontypes = e;
    }
    
    public void addParameter(final Class type, final String name) throws SynthesisException {
        if (this.realep != null) {
            throw new SynthesisException(2);
        }
        final Class[] types = new Class[this.parametertypes.length + 1];
        System.arraycopy(this.parametertypes, 0, types, 0, this.parametertypes.length);
        types[this.parametertypes.length] = type;
        this.parametertypes = types;
        final String[] names = new String[this.parameternames.length + 1];
        System.arraycopy(this.parameternames, 0, names, 0, this.parameternames.length);
        names[this.parameternames.length] = name;
        this.parameternames = names;
    }
    
    public boolean equals(final Object obj) {
        EntryPoint otherep = null;
        if (obj instanceof EntryPoint) {
            otherep = (EntryPoint)obj;
        }
        else if (obj instanceof Constructor || obj instanceof Method) {
            otherep = (EntryPoint)obj;
        }
        return otherep != null && ((this instanceof org.apache.xml.utils.synthetic.reflection.Constructor && otherep instanceof org.apache.xml.utils.synthetic.reflection.Constructor) || (this instanceof org.apache.xml.utils.synthetic.reflection.Method && otherep instanceof org.apache.xml.utils.synthetic.reflection.Method && this.getName().equals(otherep.getName()))) && otherep.getDeclaringClass().equals(this.declaringclass) && otherep.getParameterTypes().equals(this.parametertypes);
    }
    
    public StringBuffer getBody() {
        if (this.body == null) {
            this.body = new StringBuffer();
        }
        return this.body;
    }
    
    public Class getDeclaringClass() {
        return this.declaringclass;
    }
    
    public Class[] getExceptionTypes() {
        if (this.realep != null && this.exceptiontypes == null) {
            this.exceptiontypes = new Class[this.realE.length];
            for (int i = 0; i < this.realE.length; ++i) {
                this.exceptiontypes[i] = Class.forClass(this.realE[i]);
            }
            this.realE = null;
        }
        return this.exceptiontypes;
    }
    
    public String getLanguage() {
        return this.language;
    }
    
    public int getModifiers() {
        return this.modifiers;
    }
    
    public String getName() {
        if (this instanceof org.apache.xml.utils.synthetic.reflection.Constructor) {
            return this.declaringclass.getShortName();
        }
        return this.name;
    }
    
    public String[] getParameterNames() {
        return this.parameternames;
    }
    
    public Class[] getParameterTypes() {
        if (this.realep != null && this.parametertypes == null) {
            this.parametertypes = new Class[this.realP.length];
            for (int i = 0; i < this.realP.length; ++i) {
                this.parametertypes[i] = Class.forClass(this.realP[i]);
            }
            this.realP = null;
        }
        return this.parametertypes;
    }
    
    public Class getReturnType() {
        return this.returntype;
    }
    
    public abstract int hashCode();
    
    public void setBody(final String language, final StringBuffer body) throws SynthesisException {
        if (this.realep != null) {
            throw new SynthesisException(2);
        }
        this.language = language;
        this.body = body;
    }
    
    public void setDeclaringClass(final Class declaringClass) throws SynthesisException {
        if (this.realep != null) {
            throw new SynthesisException(2);
        }
        this.declaringclass = declaringClass;
    }
    
    public void setModifiers(final int modifiers) throws SynthesisException {
        if (this.realep != null) {
            throw new SynthesisException(2);
        }
        this.modifiers = modifiers;
    }
    
    public void setName(final String name) throws SynthesisException {
        if (this.realep != null) {
            throw new SynthesisException(2);
        }
        this.name = name;
    }
    
    public String toSource(final String basetab) {
        final StringBuffer sb = new StringBuffer();
        sb.append(basetab).append(Modifier.toString(this.getModifiers()));
        if (this instanceof org.apache.xml.utils.synthetic.reflection.Method) {
            if (this.returntype != null) {
                sb.append(" ").append(this.getReturnType().getJavaName());
            }
            else {
                sb.append(" void");
            }
        }
        sb.append(" ").append(this.getName()).append("(");
        Class[] types = this.getParameterTypes();
        if (types != null & types.length > 0) {
            sb.append(types[0].getJavaName());
            if (this.parameternames != null) {
                sb.append(' ').append(this.parameternames[0]);
            }
            for (int i = 1; i < types.length; ++i) {
                sb.append(',').append(types[i].getJavaName());
                if (this.parameternames != null) {
                    sb.append(' ').append(this.parameternames[i]);
                }
            }
        }
        sb.append(')');
        types = this.getExceptionTypes();
        if (types != null & types.length > 0) {
            sb.append(" throws ").append(types[0].getJavaName());
            for (int i = 1; i < types.length; ++i) {
                sb.append(',').append(types[i].getJavaName());
            }
        }
        if (this.body == null) {
            sb.append("; // No method body available\n");
        }
        else {
            sb.append("\n" + basetab + "{\n");
            if (this.language == null || "java".equals(this.language)) {
                sb.append(String.valueOf(basetab) + "// ***** Should prettyprint this code...\n");
                sb.append(String.valueOf(basetab) + (Object)this.body + "\n");
            }
            else {
                sb.append(String.valueOf(basetab) + "// ***** Generate BSF invocation!?\n");
            }
            sb.append(String.valueOf(basetab) + "}\n");
        }
        return sb.toString();
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(Modifier.toString(this.getModifiers()));
        if (this instanceof org.apache.xml.utils.synthetic.reflection.Method) {
            sb.append(' ').append(this.getReturnType()).append(this.getDeclaringClass().getName()).append('.').append(this.getName());
        }
        else {
            sb.append(this.getDeclaringClass().getName());
        }
        sb.append('(');
        Class[] p = this.getParameterTypes();
        if (p != null && p.length > 0) {
            sb.append(p[0].getName());
            for (int i = 1; i < p.length; ++i) {
                sb.append(',').append(p[i].getName());
            }
        }
        sb.append(')');
        if (this instanceof org.apache.xml.utils.synthetic.reflection.Method) {
            p = this.getExceptionTypes();
            if (p != null && p.length > 0) {
                sb.append(" throws ").append(p[0].getName());
                for (int i = 1; i < p.length; ++i) {
                    sb.append(',').append(p[i].getName());
                }
            }
        }
        return sb.toString();
    }
}
