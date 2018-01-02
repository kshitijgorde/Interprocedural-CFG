// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.tree.analysis;

import java.util.List;
import org.jruby.org.objectweb.asm.Type;

public class SimpleVerifier extends BasicVerifier
{
    private final Type currentClass;
    private final Type currentSuperClass;
    private final List currentClassInterfaces;
    private final boolean isInterface;
    private ClassLoader loader;
    static /* synthetic */ Class class$java$lang$Object;
    
    public SimpleVerifier() {
        this(null, null, false);
    }
    
    public SimpleVerifier(final Type type, final Type type2, final boolean b) {
        this(type, type2, null, b);
    }
    
    public SimpleVerifier(final Type currentClass, final Type currentSuperClass, final List currentClassInterfaces, final boolean isInterface) {
        this.loader = this.getClass().getClassLoader();
        this.currentClass = currentClass;
        this.currentSuperClass = currentSuperClass;
        this.currentClassInterfaces = currentClassInterfaces;
        this.isInterface = isInterface;
    }
    
    public void setClassLoader(final ClassLoader loader) {
        this.loader = loader;
    }
    
    public Value newValue(final Type type) {
        if (type == null) {
            return BasicValue.UNINITIALIZED_VALUE;
        }
        final boolean b = type.getSort() == 9;
        if (b) {
            switch (type.getElementType().getSort()) {
                case 1:
                case 2:
                case 3:
                case 4: {
                    return new BasicValue(type);
                }
            }
        }
        Value value = super.newValue(type);
        if (BasicValue.REFERENCE_VALUE.equals(value)) {
            if (b) {
                String s = ((BasicValue)this.newValue(type.getElementType())).getType().getDescriptor();
                for (int i = 0; i < type.getDimensions(); ++i) {
                    s = '[' + s;
                }
                value = new BasicValue(Type.getType(s));
            }
            else {
                value = new BasicValue(type);
            }
        }
        return value;
    }
    
    protected boolean isArrayValue(final Value value) {
        final Type type = ((BasicValue)value).getType();
        return type != null && ("Lnull;".equals(type.getDescriptor()) || type.getSort() == 9);
    }
    
    protected Value getElementValue(final Value value) throws AnalyzerException {
        final Type type = ((BasicValue)value).getType();
        if (type != null) {
            if (type.getSort() == 9) {
                return this.newValue(Type.getType(type.getDescriptor().substring(1)));
            }
            if ("Lnull;".equals(type.getDescriptor())) {
                return value;
            }
        }
        throw new Error("Internal error");
    }
    
    protected boolean isSubTypeOf(final Value value, final Value value2) {
        final Type type = ((BasicValue)value2).getType();
        final Type type2 = ((BasicValue)value).getType();
        switch (type.getSort()) {
            case 5:
            case 6:
            case 7:
            case 8: {
                return type2.equals(type);
            }
            case 9:
            case 10: {
                return "Lnull;".equals(type2.getDescriptor()) || ((type2.getSort() == 10 || type2.getSort() == 9) && this.isAssignableFrom(type, type2));
            }
            default: {
                throw new Error("Internal error");
            }
        }
    }
    
    public Value merge(final Value value, final Value value2) {
        if (value.equals(value2)) {
            return value;
        }
        Type type = ((BasicValue)value).getType();
        final Type type2 = ((BasicValue)value2).getType();
        if (type == null || (type.getSort() != 10 && type.getSort() != 9) || type2 == null || (type2.getSort() != 10 && type2.getSort() != 9)) {
            return BasicValue.UNINITIALIZED_VALUE;
        }
        if ("Lnull;".equals(type.getDescriptor())) {
            return value2;
        }
        if ("Lnull;".equals(type2.getDescriptor())) {
            return value;
        }
        if (this.isAssignableFrom(type, type2)) {
            return value;
        }
        if (this.isAssignableFrom(type2, type)) {
            return value2;
        }
        while (type != null && !this.isInterface(type)) {
            type = this.getSuperClass(type);
            if (this.isAssignableFrom(type, type2)) {
                return this.newValue(type);
            }
        }
        return BasicValue.REFERENCE_VALUE;
    }
    
    protected boolean isInterface(final Type type) {
        if (this.currentClass != null && type.equals(this.currentClass)) {
            return this.isInterface;
        }
        return this.getClass(type).isInterface();
    }
    
    protected Type getSuperClass(final Type type) {
        if (this.currentClass != null && type.equals(this.currentClass)) {
            return this.currentSuperClass;
        }
        final Class superclass = this.getClass(type).getSuperclass();
        return (superclass == null) ? null : Type.getType(superclass);
    }
    
    protected boolean isAssignableFrom(final Type type, final Type type2) {
        if (type.equals(type2)) {
            return true;
        }
        if (this.currentClass != null && type.equals(this.currentClass)) {
            if (this.getSuperClass(type2) == null) {
                return false;
            }
            if (this.isInterface) {
                return type2.getSort() == 10 || type2.getSort() == 9;
            }
            return this.isAssignableFrom(type, this.getSuperClass(type2));
        }
        else {
            if (this.currentClass == null || !type2.equals(this.currentClass)) {
                Class class1 = this.getClass(type);
                if (class1.isInterface()) {
                    class1 = ((SimpleVerifier.class$java$lang$Object == null) ? (SimpleVerifier.class$java$lang$Object = class$("java.lang.Object")) : SimpleVerifier.class$java$lang$Object);
                }
                return class1.isAssignableFrom(this.getClass(type2));
            }
            if (this.isAssignableFrom(type, this.currentSuperClass)) {
                return true;
            }
            if (this.currentClassInterfaces != null) {
                for (int i = 0; i < this.currentClassInterfaces.size(); ++i) {
                    if (this.isAssignableFrom(type, (Type)this.currentClassInterfaces.get(i))) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
    
    protected Class getClass(final Type type) {
        try {
            if (type.getSort() == 9) {
                return Class.forName(type.getDescriptor().replace('/', '.'), false, this.loader);
            }
            return Class.forName(type.getClassName(), false, this.loader);
        }
        catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
