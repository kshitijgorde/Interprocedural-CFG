// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.classfile;

import com.ibm.xslt4j.bcel.Repository;
import java.util.StringTokenizer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.File;

public class JavaClass extends AccessFlags implements Cloneable, Node
{
    private String file_name;
    private String package_name;
    private String source_file_name;
    private int class_name_index;
    private int superclass_name_index;
    private String class_name;
    private String superclass_name;
    private int major;
    private int minor;
    private ConstantPool constant_pool;
    private int[] interfaces;
    private String[] interface_names;
    private Field[] fields;
    private Method[] methods;
    private Attribute[] attributes;
    private byte source;
    public static final byte HEAP = 1;
    public static final byte FILE = 2;
    public static final byte ZIP = 3;
    static boolean debug;
    static char sep;
    
    static {
        JavaClass.debug = false;
        JavaClass.sep = '/';
        final String debug = System.getProperty("JavaClass.debug");
        if (debug != null) {
            JavaClass.debug = new Boolean(debug);
        }
        final String sep = System.getProperty("file.separator");
        if (sep != null) {
            try {
                JavaClass.sep = sep.charAt(0);
            }
            catch (StringIndexOutOfBoundsException ex) {}
        }
    }
    
    public JavaClass(final int class_name_index, final int superclass_name_index, final String file_name, final int major, final int minor, final int access_flags, final ConstantPool constant_pool, int[] interfaces, Field[] fields, Method[] methods, final Attribute[] attributes, final byte source) {
        this.source_file_name = "<Unknown>";
        this.source = 1;
        if (interfaces == null) {
            interfaces = new int[0];
        }
        if (attributes == null) {
            this.attributes = new Attribute[0];
        }
        if (fields == null) {
            fields = new Field[0];
        }
        if (methods == null) {
            methods = new Method[0];
        }
        this.class_name_index = class_name_index;
        this.superclass_name_index = superclass_name_index;
        this.file_name = file_name;
        this.major = major;
        this.minor = minor;
        super.access_flags = access_flags;
        this.constant_pool = constant_pool;
        this.interfaces = interfaces;
        this.fields = fields;
        this.methods = methods;
        this.attributes = attributes;
        this.source = source;
        for (int i = 0; i < attributes.length; ++i) {
            if (attributes[i] instanceof SourceFile) {
                this.source_file_name = ((SourceFile)attributes[i]).getSourceFileName();
                break;
            }
        }
        this.class_name = constant_pool.getConstantString(class_name_index, (byte)7);
        this.class_name = Utility.compactClassName(this.class_name, false);
        final int index = this.class_name.lastIndexOf(46);
        if (index < 0) {
            this.package_name = "";
        }
        else {
            this.package_name = this.class_name.substring(0, index);
        }
        if (superclass_name_index > 0) {
            this.superclass_name = constant_pool.getConstantString(superclass_name_index, (byte)7);
            this.superclass_name = Utility.compactClassName(this.superclass_name, false);
        }
        else {
            this.superclass_name = "java.lang.Object";
        }
        this.interface_names = new String[interfaces.length];
        for (int j = 0; j < interfaces.length; ++j) {
            final String str = constant_pool.getConstantString(interfaces[j], (byte)7);
            this.interface_names[j] = Utility.compactClassName(str, false);
        }
    }
    
    public JavaClass(final int class_name_index, final int superclass_name_index, final String file_name, final int major, final int minor, final int access_flags, final ConstantPool constant_pool, final int[] interfaces, final Field[] fields, final Method[] methods, final Attribute[] attributes) {
        this(class_name_index, superclass_name_index, file_name, major, minor, access_flags, constant_pool, interfaces, fields, methods, attributes, (byte)1);
    }
    
    public void accept(final Visitor v) {
        v.visitJavaClass(this);
    }
    
    static final void Debug(final String str) {
        if (JavaClass.debug) {
            System.out.println(str);
        }
    }
    
    public void dump(final File file) throws IOException {
        final String parent = file.getParent();
        if (parent != null) {
            final File dir = new File(parent);
            if (dir != null) {
                dir.mkdirs();
            }
        }
        this.dump(new DataOutputStream(new FileOutputStream(file)));
    }
    
    public void dump(final String file_name) throws IOException {
        this.dump(new File(file_name));
    }
    
    public byte[] getBytes() {
        final ByteArrayOutputStream s = new ByteArrayOutputStream();
        final DataOutputStream ds = new DataOutputStream(s);
        try {
            this.dump(ds);
            ds.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return s.toByteArray();
    }
    
    public void dump(final OutputStream file) throws IOException {
        this.dump(new DataOutputStream(file));
    }
    
    public void dump(final DataOutputStream file) throws IOException {
        file.writeInt(-889275714);
        file.writeShort(this.minor);
        file.writeShort(this.major);
        this.constant_pool.dump(file);
        file.writeShort(super.access_flags);
        file.writeShort(this.class_name_index);
        file.writeShort(this.superclass_name_index);
        file.writeShort(this.interfaces.length);
        for (int i = 0; i < this.interfaces.length; ++i) {
            file.writeShort(this.interfaces[i]);
        }
        file.writeShort(this.fields.length);
        for (int i = 0; i < this.fields.length; ++i) {
            this.fields[i].dump(file);
        }
        file.writeShort(this.methods.length);
        for (int i = 0; i < this.methods.length; ++i) {
            this.methods[i].dump(file);
        }
        if (this.attributes != null) {
            file.writeShort(this.attributes.length);
            for (int i = 0; i < this.attributes.length; ++i) {
                this.attributes[i].dump(file);
            }
        }
        else {
            file.writeShort(0);
        }
        file.close();
    }
    
    public Attribute[] getAttributes() {
        return this.attributes;
    }
    
    public String getClassName() {
        return this.class_name;
    }
    
    public String getPackageName() {
        return this.package_name;
    }
    
    public int getClassNameIndex() {
        return this.class_name_index;
    }
    
    public ConstantPool getConstantPool() {
        return this.constant_pool;
    }
    
    public Field[] getFields() {
        return this.fields;
    }
    
    public String getFileName() {
        return this.file_name;
    }
    
    public String[] getInterfaceNames() {
        return this.interface_names;
    }
    
    public int[] getInterfaces() {
        return this.interfaces;
    }
    
    public int getMajor() {
        return this.major;
    }
    
    public Method[] getMethods() {
        return this.methods;
    }
    
    public int getMinor() {
        return this.minor;
    }
    
    public String getSourceFileName() {
        return this.source_file_name;
    }
    
    public String getSuperclassName() {
        return this.superclass_name;
    }
    
    public int getSuperclassNameIndex() {
        return this.superclass_name_index;
    }
    
    public void setAttributes(final Attribute[] attributes) {
        this.attributes = attributes;
    }
    
    public void setClassName(final String class_name) {
        this.class_name = class_name;
    }
    
    public void setClassNameIndex(final int class_name_index) {
        this.class_name_index = class_name_index;
    }
    
    public void setConstantPool(final ConstantPool constant_pool) {
        this.constant_pool = constant_pool;
    }
    
    public void setFields(final Field[] fields) {
        this.fields = fields;
    }
    
    public void setFileName(final String file_name) {
        this.file_name = file_name;
    }
    
    public void setInterfaceNames(final String[] interface_names) {
        this.interface_names = interface_names;
    }
    
    public void setInterfaces(final int[] interfaces) {
        this.interfaces = interfaces;
    }
    
    public void setMajor(final int major) {
        this.major = major;
    }
    
    public void setMethods(final Method[] methods) {
        this.methods = methods;
    }
    
    public void setMinor(final int minor) {
        this.minor = minor;
    }
    
    public void setSourceFileName(final String source_file_name) {
        this.source_file_name = source_file_name;
    }
    
    public void setSuperclassName(final String superclass_name) {
        this.superclass_name = superclass_name;
    }
    
    public void setSuperclassNameIndex(final int superclass_name_index) {
        this.superclass_name_index = superclass_name_index;
    }
    
    public String toString() {
        String access = Utility.accessToString(super.access_flags, true);
        access = (access.equals("") ? "" : (String.valueOf(access) + " "));
        final StringBuffer buf = new StringBuffer(String.valueOf(access) + Utility.classOrInterface(super.access_flags) + " " + this.class_name + " extends " + Utility.compactClassName(this.superclass_name, false) + '\n');
        final int size = this.interfaces.length;
        if (size > 0) {
            buf.append("implements\t\t");
            for (int i = 0; i < size; ++i) {
                buf.append(this.interface_names[i]);
                if (i < size - 1) {
                    buf.append(", ");
                }
            }
            buf.append('\n');
        }
        buf.append("filename\t\t" + this.file_name + '\n');
        buf.append("compiled from\t\t" + this.source_file_name + '\n');
        buf.append("compiler version\t" + this.major + "." + this.minor + '\n');
        buf.append("access flags\t\t" + super.access_flags + '\n');
        buf.append("constant pool\t\t" + this.constant_pool.getLength() + " entries\n");
        buf.append("ACC_SUPER flag\t\t" + this.isSuper() + "\n");
        if (this.attributes.length > 0) {
            buf.append("\nAttribute(s):\n");
            for (int i = 0; i < this.attributes.length; ++i) {
                buf.append(indent(this.attributes[i]));
            }
        }
        if (this.fields.length > 0) {
            buf.append("\n" + this.fields.length + " fields:\n");
            for (int i = 0; i < this.fields.length; ++i) {
                buf.append("\t" + this.fields[i] + '\n');
            }
        }
        if (this.methods.length > 0) {
            buf.append("\n" + this.methods.length + " methods:\n");
            for (int i = 0; i < this.methods.length; ++i) {
                buf.append("\t" + this.methods[i] + '\n');
            }
        }
        return buf.toString();
    }
    
    private static final String indent(final Object obj) {
        final StringTokenizer tok = new StringTokenizer(obj.toString(), "\n");
        final StringBuffer buf = new StringBuffer();
        while (tok.hasMoreTokens()) {
            buf.append("\t" + tok.nextToken() + "\n");
        }
        return buf.toString();
    }
    
    public JavaClass copy() {
        JavaClass c = null;
        try {
            c = (JavaClass)this.clone();
        }
        catch (CloneNotSupportedException ex) {}
        c.constant_pool = this.constant_pool.copy();
        c.interfaces = this.interfaces.clone();
        c.interface_names = this.interface_names.clone();
        c.fields = new Field[this.fields.length];
        for (int i = 0; i < this.fields.length; ++i) {
            c.fields[i] = this.fields[i].copy(c.constant_pool);
        }
        c.methods = new Method[this.methods.length];
        for (int i = 0; i < this.methods.length; ++i) {
            c.methods[i] = this.methods[i].copy(c.constant_pool);
        }
        c.attributes = new Attribute[this.attributes.length];
        for (int i = 0; i < this.attributes.length; ++i) {
            c.attributes[i] = this.attributes[i].copy(c.constant_pool);
        }
        return c;
    }
    
    public final boolean instanceOf(final JavaClass super_class) {
        return Repository.instanceOf(this, super_class);
    }
    
    public final boolean isSuper() {
        return (super.access_flags & 0x20) != 0x0;
    }
    
    public final boolean isClass() {
        return (super.access_flags & 0x200) == 0x0;
    }
    
    public final byte getSource() {
        return this.source;
    }
}
