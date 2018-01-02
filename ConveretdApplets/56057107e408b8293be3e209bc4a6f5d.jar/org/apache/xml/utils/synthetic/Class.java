// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils.synthetic;

import java.lang.reflect.Modifier;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.net.URL;
import org.apache.xml.utils.synthetic.reflection.Field;
import org.apache.xml.utils.synthetic.reflection.Method;
import org.apache.xml.utils.synthetic.reflection.Constructor;
import java.util.Hashtable;
import java.io.Serializable;

public class Class implements Serializable
{
    private static Hashtable global_classtable;
    private String name;
    private java.lang.Class realclass;
    private int modifiers;
    private boolean isInterface;
    private Class superclass;
    private Class declaringclass;
    private Class[] interfaces;
    private Class[] allclasses;
    private Class[] declaredclasses;
    private Constructor[] allconstructors;
    private Constructor[] declaredconstructors;
    private Method[] allmethods;
    private Method[] declaredmethods;
    private Field[] allfields;
    private Field[] declaredfields;
    private Class[] innerclasses;
    static final int[] val;
    static final String[] kwd;
    static /* synthetic */ java.lang.Class class$java$lang$Object;
    
    static {
        Class.global_classtable = new Hashtable();
        val = new int[] { 1024, 16, 512, 256, 2, 4, 1, 8, 32, 128, 64 };
        kwd = new String[] { "abstract", "final", "interface", "native", "private", "protected", "public", "static", "synchronized", "transient", "volatile" };
    }
    
    Class(final java.lang.Class realclass) {
        this(realclass.getName());
        try {
            this.setRealClass(realclass);
        }
        catch (SynthesisException e) {
            e.printStackTrace();
        }
    }
    
    Class(final String fullname) {
        this.realclass = null;
        this.isInterface = false;
        this.superclass = null;
        this.declaringclass = null;
        this.interfaces = new Class[0];
        this.allclasses = new Class[0];
        this.declaredclasses = new Class[0];
        this.allconstructors = new Constructor[0];
        this.declaredconstructors = new Constructor[0];
        this.allmethods = new Method[0];
        this.declaredmethods = new Method[0];
        this.allfields = new Field[0];
        this.declaredfields = new Field[0];
        this.innerclasses = new Class[0];
        this.name = fullname;
        Class.global_classtable.put(fullname, this);
    }
    
    public void addExtends(final Class newclass) throws SynthesisException {
        if (this.realclass != null) {
            throw new SynthesisException(2);
        }
        final Class[] scratch = new Class[this.declaredclasses.length + 1];
        System.arraycopy(this.declaredclasses, 0, scratch, 0, this.declaredclasses.length);
        scratch[this.declaredclasses.length] = newclass;
        this.declaredclasses = scratch;
    }
    
    public void addImplements(final Class newclass) throws SynthesisException {
        if (this.realclass != null) {
            throw new SynthesisException(2);
        }
        final Class[] scratch = new Class[this.interfaces.length + 1];
        System.arraycopy(this.interfaces, 0, scratch, 0, this.interfaces.length);
        scratch[this.interfaces.length] = newclass;
        this.interfaces = scratch;
    }
    
    private void addInnerClass(final Class newclass) throws SynthesisException {
        if (this.realclass != null) {
            throw new SynthesisException(2);
        }
        if (newclass.getDeclaringClass() != this) {
            throw new SynthesisException(4);
        }
        final Class[] scratch = new Class[this.innerclasses.length + 1];
        System.arraycopy(this.innerclasses, 0, scratch, 0, this.innerclasses.length);
        scratch[this.innerclasses.length] = newclass;
        this.innerclasses = scratch;
    }
    
    static /* synthetic */ java.lang.Class class$(final String class$) {
        try {
            return java.lang.Class.forName(class$);
        }
        catch (ClassNotFoundException forName) {
            throw new NoClassDefFoundError(forName.getMessage());
        }
    }
    
    public static Class declareClass(final String className) throws SynthesisException {
        Class ret = Class.global_classtable.get(className);
        if (ret == null) {
            ret = new Class(className);
        }
        if (ret.realclass != null) {
            throw new SynthesisException(2);
        }
        return ret;
    }
    
    public Constructor declareConstructor() throws SynthesisException {
        if (this.realclass != null) {
            throw new SynthesisException(2);
        }
        final Constructor newctor = new Constructor(this);
        Constructor[] scratch = new Constructor[this.declaredconstructors.length + 1];
        System.arraycopy(this.declaredconstructors, 0, scratch, 0, this.declaredconstructors.length);
        scratch[this.declaredconstructors.length] = newctor;
        this.declaredconstructors = scratch;
        scratch = new Constructor[this.allconstructors.length + 1];
        System.arraycopy(this.allconstructors, 0, scratch, 0, this.allconstructors.length);
        scratch[this.allconstructors.length] = newctor;
        this.allconstructors = scratch;
        return newctor;
    }
    
    public Field declareField(final String name) throws SynthesisException {
        if (this.realclass != null) {
            throw new SynthesisException(2);
        }
        final Field newfield = new Field(name, this);
        Field[] scratch = new Field[this.declaredfields.length + 1];
        System.arraycopy(this.declaredfields, 0, scratch, 0, this.declaredfields.length);
        scratch[this.declaredfields.length] = newfield;
        this.declaredfields = scratch;
        scratch = new Field[this.allfields.length + 1];
        System.arraycopy(this.allfields, 0, scratch, 0, this.allfields.length);
        scratch[this.allfields.length] = newfield;
        this.allfields = scratch;
        return newfield;
    }
    
    public Class declareInnerClass(final String className) throws SynthesisException {
        if (this.realclass != null) {
            throw new SynthesisException(2);
        }
        final String relativeName = String.valueOf(this.getName()) + "$" + className;
        Class newclass = Class.global_classtable.get(relativeName);
        if (newclass != null) {
            throw new SynthesisException(0, "Inner class " + this.name + " already exists");
        }
        newclass = new Class(className);
        newclass.declaringclass = this;
        final Class[] scratch = new Class[this.innerclasses.length + 1];
        System.arraycopy(this.innerclasses, 0, scratch, 0, this.innerclasses.length);
        scratch[this.innerclasses.length] = newclass;
        this.innerclasses = scratch;
        return newclass;
    }
    
    public Class declareInterface(final Class newifce) throws SynthesisException {
        if (this.realclass != null) {
            throw new SynthesisException(2);
        }
        if (!newifce.isInterface()) {
            throw new SynthesisException(0, String.valueOf(newifce.getName()) + " isn't an interface");
        }
        Class[] scratch = new Class[this.interfaces.length + 1];
        System.arraycopy(this.interfaces, 0, scratch, 0, this.interfaces.length);
        scratch[this.interfaces.length] = newifce;
        this.interfaces = scratch;
        scratch = new Class[this.allclasses.length + 1];
        System.arraycopy(this.allclasses, 0, scratch, 0, this.allclasses.length);
        scratch[this.allclasses.length] = newifce;
        this.allclasses = scratch;
        return newifce;
    }
    
    public Method declareMethod(final String name) throws SynthesisException {
        if (this.realclass != null) {
            throw new SynthesisException(2);
        }
        final Method newMethod = new Method(name, this);
        Method[] scratch = new Method[this.declaredmethods.length + 1];
        System.arraycopy(this.declaredmethods, 0, scratch, 0, this.declaredmethods.length);
        scratch[this.declaredmethods.length] = newMethod;
        this.declaredmethods = scratch;
        scratch = new Method[this.allmethods.length + 1];
        System.arraycopy(this.allmethods, 0, scratch, 0, this.allmethods.length);
        scratch[this.allmethods.length] = newMethod;
        this.allmethods = scratch;
        return newMethod;
    }
    
    public static Class forClass(final java.lang.Class cls) {
        if (cls == null) {
            return null;
        }
        Class ret = Class.global_classtable.get(cls.getName());
        if (ret == null) {
            ret = new Class(cls);
        }
        return ret;
    }
    
    public static Class forName(final String className) throws ClassNotFoundException {
        if (className.endsWith("]")) {
            final StringBuffer arrayname = new StringBuffer();
            for (int i = className.indexOf(91); i != -1; i = className.indexOf(91, i + 1)) {
                arrayname.append('[');
            }
            final String classname = className.substring(0, className.indexOf(91));
            if ("byte".equals(classname)) {
                arrayname.append('B');
            }
            else if ("char".equals(classname)) {
                arrayname.append('C');
            }
            else if ("double".equals(classname)) {
                arrayname.append('D');
            }
            else if ("float".equals(classname)) {
                arrayname.append('F');
            }
            else if ("int".equals(classname)) {
                arrayname.append('I');
            }
            else if ("long".equals(classname)) {
                arrayname.append('J');
            }
            else if ("short".equals(classname)) {
                arrayname.append('S');
            }
            else if ("boolean".equals(classname)) {
                arrayname.append('Z');
            }
            else {
                arrayname.append('L').append(classname).append(';');
            }
            return forName(arrayname.toString());
        }
        Class ret = Class.global_classtable.get(className);
        if (ret == null) {
            if ("boolean".equals(className)) {
                ret = new Class(className);
                ret.realclass = Boolean.TYPE;
            }
            else if ("byte".equals(className)) {
                ret = new Class(className);
                ret.realclass = Byte.TYPE;
            }
            else if ("char".equals(className)) {
                ret = new Class(className);
                ret.realclass = Character.TYPE;
            }
            else if ("short".equals(className)) {
                ret = new Class(className);
                ret.realclass = Short.TYPE;
            }
            else if ("int".equals(className)) {
                ret = new Class(className);
                ret.realclass = Integer.TYPE;
            }
            else if ("long".equals(className)) {
                ret = new Class(className);
                ret.realclass = Long.TYPE;
            }
            else if ("float".equals(className)) {
                ret = new Class(className);
                ret.realclass = Float.TYPE;
            }
            else if ("double".equals(className)) {
                ret = new Class(className);
                ret.realclass = Double.TYPE;
            }
            else if ("void".equals(className)) {
                ret = new Class(className);
                ret.realclass = java.lang.Class.forName("java.lang.Object");
            }
            else {
                ret = new Class(java.lang.Class.forName(className));
            }
        }
        return ret;
    }
    
    public Class forNameInContext(final String classname) throws ClassNotFoundException {
        for (int i = this.innerclasses.length - 1; i >= 0; --i) {
            if (classname.equals(this.innerclasses[i].getShortName())) {
                return this.innerclasses[i];
            }
        }
        return forName(classname);
    }
    
    public ClassLoader getClassLoader() {
        return (this.realclass == null) ? null : this.realclass.getClassLoader();
    }
    
    public Class[] getClasses() {
        if (this.realclass != null && this.allclasses == null) {
            final java.lang.Class[] realDE = this.realclass.getClasses();
            this.allclasses = new Class[realDE.length];
            for (int i = 0; i < realDE.length; ++i) {
                this.allclasses[i] = forClass(realDE[i]);
            }
        }
        return this.allclasses;
    }
    
    public Class getComponentType() {
        return (this.realclass == null) ? null : new Class(this.realclass.getComponentType());
    }
    
    public Constructor getConstructor(final Class[] parameterTypes) throws NoSuchMethodException, SecurityException, SynthesisException {
        if (this.realclass == null) {
            throw new SynthesisException(3);
        }
        final java.lang.Class[] real = new java.lang.Class[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; ++i) {
            if ((real[i] = parameterTypes[i].getRealClass()) == null) {
                throw new SynthesisException(3);
            }
        }
        return new Constructor(this.realclass.getConstructor((java.lang.Class[])real), this);
    }
    
    public Constructor[] getConstructors() throws SecurityException {
        if (this.realclass != null && this.allconstructors == null) {
            final java.lang.reflect.Constructor[] realDC = this.realclass.getConstructors();
            this.allconstructors = new Constructor[realDC.length];
            for (int i = 0; i < realDC.length; ++i) {
                this.allconstructors[i] = new Constructor(realDC[i], this);
            }
        }
        return this.allconstructors;
    }
    
    public Class[] getDeclaredClasses() throws SecurityException {
        if (this.realclass != null && this.declaredclasses == null) {
            final java.lang.Class[] realDE = this.realclass.getDeclaredClasses();
            this.declaredclasses = new Class[realDE.length];
            for (int i = 0; i < realDE.length; ++i) {
                this.declaredclasses[i] = forClass(realDE[i]);
                if (!realDE[i].isInterface()) {
                    this.superclass = this.declaredclasses[i];
                }
            }
        }
        return this.declaredclasses;
    }
    
    public Constructor getDeclaredConstructor(final Class[] parameterTypes) throws NoSuchMethodException, SecurityException {
        throw new IllegalStateException();
    }
    
    public Constructor[] getDeclaredConstructors() throws SecurityException {
        if (this.realclass != null && this.declaredconstructors == null) {
            final java.lang.reflect.Constructor[] realDC = this.realclass.getDeclaredConstructors();
            this.declaredconstructors = new Constructor[realDC.length];
            for (int i = 0; i < realDC.length; ++i) {
                this.declaredconstructors[i] = new Constructor(realDC[i], this);
            }
        }
        return this.declaredconstructors;
    }
    
    public Field getDeclaredField(final String name) throws NoSuchFieldException, SecurityException {
        throw new IllegalStateException();
    }
    
    public Field[] getDeclaredFields() throws SecurityException {
        if (this.realclass != null && this.declaredfields == null) {
            final java.lang.reflect.Field[] realDF = this.realclass.getDeclaredFields();
            this.declaredfields = new Field[realDF.length];
            for (int i = 0; i < realDF.length; ++i) {
                this.declaredfields[i] = new Field(realDF[i], this);
            }
        }
        return this.declaredfields;
    }
    
    public Method getDeclaredMethod(final String name, final Class[] parameterTypes) throws NoSuchMethodException, SecurityException {
        throw new IllegalStateException();
    }
    
    public Method[] getDeclaredMethods() throws SecurityException {
        if (this.realclass != null && this.declaredmethods == null) {
            final java.lang.reflect.Method[] realDM = this.realclass.getDeclaredMethods();
            this.declaredmethods = new Method[realDM.length];
            for (int i = 0; i < realDM.length; ++i) {
                this.declaredmethods[i] = new Method(realDM[i], this);
            }
        }
        return this.declaredmethods;
    }
    
    public Class getDeclaringClass() {
        if (this.realclass != null && this.declaringclass == null) {
            final java.lang.Class dc = this.realclass.getDeclaringClass();
            if (dc == null) {
                this.declaringclass = null;
            }
            else {
                this.declaringclass = forClass(dc);
            }
        }
        return this.declaringclass;
    }
    
    public Field getField(final String name) throws NoSuchFieldException, SecurityException {
        throw new IllegalStateException();
    }
    
    public Field[] getFields() throws SecurityException {
        if (this.realclass != null && this.allfields == null) {
            final java.lang.reflect.Field[] realDF = this.realclass.getFields();
            this.allfields = new Field[realDF.length];
            for (int i = 0; i < realDF.length; ++i) {
                this.allfields[i] = new Field(realDF[i], this);
            }
        }
        return this.allfields;
    }
    
    public Class[] getInnerClasses() {
        return this.innerclasses;
    }
    
    public Class[] getInterfaces() {
        if (this.realclass != null && this.interfaces == null) {
            final java.lang.Class[] realI = this.realclass.getInterfaces();
            this.interfaces = new Class[realI.length];
            for (int i = 0; i < realI.length; ++i) {
                this.interfaces[i] = forClass(realI[i]);
            }
        }
        return this.interfaces;
    }
    
    public String getJavaName() {
        if (this.name.charAt(0) != '[') {
            return this.name;
        }
        int count = this.name.lastIndexOf(91);
        final StringBuffer jname = new StringBuffer(this.name.substring(count + 2));
        jname.setLength(jname.length() - 1);
        while (count-- >= 0) {
            jname.append("[]");
        }
        return jname.toString();
    }
    
    public String getJavaShortName() {
        final String shortname = this.getShortName();
        if (shortname.charAt(0) != '[') {
            return shortname;
        }
        int count = shortname.lastIndexOf(91);
        final StringBuffer jname = new StringBuffer(shortname.substring(count + 2));
        jname.setLength(jname.length() - 1);
        while (count-- >= 0) {
            jname.append("[]");
        }
        return jname.toString();
    }
    
    public Method getMethod(final String name, final Class[] parameterTypes) throws NoSuchMethodException, SecurityException {
        throw new IllegalStateException();
    }
    
    public Method[] getMethods() throws SecurityException {
        if (this.realclass != null && this.allmethods == null) {
            final java.lang.reflect.Method[] realDM = this.realclass.getMethods();
            this.allmethods = new Method[realDM.length];
            for (int i = 0; i < realDM.length; ++i) {
                this.allmethods[i] = new Method(realDM[i], this);
            }
        }
        return this.allmethods;
    }
    
    public int getModifiers() {
        return this.modifiers;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getPackageName() {
        final int start = this.name.lastIndexOf(".");
        return this.name.substring(0, start);
    }
    
    public java.lang.Class getRealClass() {
        return this.realclass;
    }
    
    public URL getResource(final String name) {
        throw new IllegalStateException();
    }
    
    public InputStream getResourceAsStream(final String name) {
        throw new IllegalStateException();
    }
    
    public String getShortName() {
        int start = this.name.lastIndexOf(".");
        if (start != 0 || this.name.charAt(0) == '.') {
            ++start;
        }
        if (this.declaringclass != null) {
            final int d = this.name.lastIndexOf(36, start);
            if (d != 0) {
                start = d + 1;
            }
        }
        return this.name.substring(start);
    }
    
    public Object[] getSigners() {
        throw new IllegalStateException();
    }
    
    public Class getSuperclass() {
        if (this.realclass != null && this.superclass == null) {
            this.superclass = forClass(this.realclass.getSuperclass());
        }
        if (this.superclass == null) {
            this.superclass = forClass((Class.class$java$lang$Object != null) ? Class.class$java$lang$Object : (Class.class$java$lang$Object = class$("java.lang.Object")));
        }
        return this.superclass;
    }
    
    public boolean isArray() {
        return this.realclass != null && this.realclass.isArray();
    }
    
    public boolean isAssignableFrom(final java.lang.Class cls) {
        if (this.realclass != null) {
            return this.realclass.isAssignableFrom(cls);
        }
        throw new IllegalStateException();
    }
    
    public boolean isAssignableFrom(final Class cls) {
        if (this.realclass != null && cls.realclass != null) {
            return this.realclass.isAssignableFrom(cls.realclass);
        }
        throw new IllegalStateException();
    }
    
    public boolean isInstance(final Object obj) {
        if (this.realclass != null) {
            return this.realclass.isInstance(obj);
        }
        throw new IllegalStateException();
    }
    
    public boolean isInterface() {
        return (this.realclass != null) ? this.realclass.isInterface() : this.isInterface;
    }
    
    public void isInterface(final boolean isInterface) throws SynthesisException {
        if (this.realclass == null) {
            this.isInterface = isInterface;
        }
        else if (this.realclass.isInterface() != isInterface) {
            throw new SynthesisException(2);
        }
    }
    
    public boolean isPrimitive() {
        return this.realclass != null && this.realclass.isPrimitive();
    }
    
    public static int modifierFromString(final String t) {
        for (int i = 0; i < Class.kwd.length; ++i) {
            if (Class.kwd[i].equals(t)) {
                return Class.val[i];
            }
        }
        return 0;
    }
    
    public static int modifiersFromString(final String s) {
        int mods = 0;
        final StringTokenizer parts = new StringTokenizer(s);
        while (parts.hasMoreTokens()) {
            final String t = parts.nextToken();
            mods |= modifierFromString(t);
        }
        return mods;
    }
    
    public Object newInstance() throws InstantiationException, IllegalAccessException {
        throw new IllegalStateException();
    }
    
    public static Class reallyDeclareClass(final String className) {
        Class ret = Class.global_classtable.get(className);
        if (ret != null) {
            Class.global_classtable.remove(ret);
        }
        ret = new Class(className);
        return ret;
    }
    
    public void setModifiers(final int modifiers) throws SynthesisException {
        if (this.realclass != null) {
            throw new SynthesisException(2);
        }
        this.modifiers = modifiers;
    }
    
    public void setRealClass(final java.lang.Class realclass) throws SynthesisException {
        if (this.realclass != null) {
            throw new SynthesisException(2);
        }
        this.realclass = realclass;
        this.modifiers = realclass.getModifiers();
        this.isInterface = realclass.isInterface();
        this.declaringclass = null;
        this.interfaces = null;
        this.declaredconstructors = null;
        this.allconstructors = null;
        this.declaredmethods = null;
        this.allmethods = null;
        this.declaredfields = null;
        this.allfields = null;
        this.declaredclasses = null;
        this.allclasses = null;
        this.superclass = null;
    }
    
    public void setSuperClass(final java.lang.Class superclass) throws ClassNotFoundException, SynthesisException {
        if (this.realclass != null) {
            throw new SynthesisException(2);
        }
        this.superclass = forClass(superclass);
    }
    
    public void setSuperClass(final Class superclass) throws SynthesisException {
        if (this.realclass != null) {
            throw new SynthesisException(2);
        }
        this.superclass = superclass;
    }
    
    private String tabset(int depth) {
        final StringBuffer t = new StringBuffer();
        while (depth-- > 0) {
            t.append("    ");
        }
        return t.toString();
    }
    
    public void toSource(final OutputStream out, final int depth) {
        final PrintWriter writer = new PrintWriter(out);
        this.toSource(writer, depth);
    }
    
    public void toSource(final PrintWriter out, int depth) {
        String tab = this.tabset(depth);
        if (this.realclass != null) {
            out.println(String.valueOf(tab) + "/** Code back-generated from a \"real\" Class; accuracy limited by reflection APIs. */");
        }
        else {
            out.println(String.valueOf(tab) + "/** Code generated via org.apache.xml.utils.synthetic.Class */");
        }
        if (this.getDeclaringClass() == null) {
            out.println(String.valueOf(tab) + "package " + this.getPackageName() + ";");
        }
        out.print(String.valueOf(tab) + Modifier.toString(this.getModifiers()));
        if (this.isInterface()) {
            out.print(" interface ");
        }
        else {
            out.print(" class ");
        }
        out.println(this.getJavaShortName());
        if (this.superclass != null) {
            out.print(String.valueOf('\n') + tab + " extends " + this.superclass.getJavaName());
        }
        final Class[] ext = this.getInterfaces();
        if (ext != null & ext.length > 0) {
            out.print(String.valueOf('\n') + tab + (this.isInterface ? " extends " : " implements ") + ext[0].getName());
            for (int i = 1; i < ext.length; ++i) {
                out.print(", " + ext[i].getJavaName());
            }
            out.print("\n");
        }
        out.print(String.valueOf(tab) + "{\n");
        tab = this.tabset(++depth);
        Field[] fields = null;
        try {
            fields = this.getDeclaredFields();
        }
        catch (SecurityException ex) {
            out.println(String.valueOf(tab) + "//SecurityException retrieving fields");
        }
        if (fields != null) {
            for (int j = 0; j < fields.length; ++j) {
                out.println(String.valueOf(tab) + fields[j].toSource());
            }
        }
        Constructor[] ctors = null;
        try {
            ctors = this.getDeclaredConstructors();
        }
        catch (SecurityException ex2) {
            out.println(String.valueOf(tab) + "//SecurityException retrieving ctors");
        }
        if (ctors != null) {
            for (int k = 0; k < ctors.length; ++k) {
                out.print(ctors[k].toSource(tab));
            }
        }
        Method[] methods = null;
        try {
            methods = this.getDeclaredMethods();
        }
        catch (SecurityException ex3) {
            out.println(String.valueOf(tab) + "//SecurityException retrieving methods");
        }
        if (methods != null) {
            for (int l = 0; l < methods.length; ++l) {
                out.print('\n');
                out.print(methods[l].toSource(tab));
            }
        }
        final Class[] inners = this.getInnerClasses();
        if (inners != null) {
            for (int m = 0; m < inners.length; ++m) {
                out.print('\n');
                inners[m].toSource(out, depth);
            }
        }
        tab = this.tabset(--depth);
        out.print(String.valueOf(tab) + "}\n");
        out.flush();
    }
    
    public String toString() {
        if (this.realclass != null) {
            return this.realclass.toString();
        }
        if (this.isInterface()) {
            return "interface " + this.name;
        }
        return "class " + this.name;
    }
}
