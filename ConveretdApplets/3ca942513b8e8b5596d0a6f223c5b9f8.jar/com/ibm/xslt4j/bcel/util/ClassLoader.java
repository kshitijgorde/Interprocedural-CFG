// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.util;

import com.ibm.xslt4j.bcel.classfile.ConstantPool;
import com.ibm.xslt4j.bcel.classfile.ConstantUtf8;
import com.ibm.xslt4j.bcel.classfile.ConstantClass;
import java.io.InputStream;
import com.ibm.xslt4j.bcel.classfile.ClassParser;
import java.io.ByteArrayInputStream;
import com.ibm.xslt4j.bcel.classfile.Utility;
import com.ibm.xslt4j.bcel.classfile.JavaClass;
import com.ibm.xslt4j.bcel.Repository;
import java.util.Hashtable;

public class ClassLoader extends java.lang.ClassLoader
{
    private Hashtable classes;
    private String[] ignored_packages;
    
    public ClassLoader() {
        this.classes = new Hashtable();
        this.ignored_packages = new String[] { "java.", "javax.", "sun." };
    }
    
    public ClassLoader(final String[] ignored_packages) {
        this.classes = new Hashtable();
        this.ignored_packages = new String[] { "java.", "javax.", "sun." };
        final String[] new_p = new String[ignored_packages.length + this.ignored_packages.length];
        System.arraycopy(this.ignored_packages, 0, new_p, 0, this.ignored_packages.length);
        System.arraycopy(ignored_packages, 0, new_p, this.ignored_packages.length, ignored_packages.length);
        this.ignored_packages = new_p;
    }
    
    protected Class loadClass(final String class_name, final boolean resolve) throws ClassNotFoundException {
        Class cl = null;
        if ((cl = this.classes.get(class_name)) == null) {
            for (int i = 0; i < this.ignored_packages.length; ++i) {
                if (class_name.startsWith(this.ignored_packages[i])) {
                    cl = Class.forName(class_name);
                    break;
                }
            }
            if (cl == null) {
                JavaClass clazz = null;
                if (class_name.indexOf("$$BCEL$$") >= 0) {
                    clazz = this.createClass(class_name);
                }
                else {
                    clazz = this.modifyClass(Repository.lookupClass(class_name));
                }
                if (clazz != null) {
                    final byte[] bytes = clazz.getBytes();
                    cl = this.defineClass(class_name, bytes, 0, bytes.length);
                }
                else {
                    cl = Class.forName(class_name);
                }
            }
            if (resolve) {
                this.resolveClass(cl);
            }
        }
        this.classes.put(class_name, cl);
        return cl;
    }
    
    protected JavaClass modifyClass(final JavaClass clazz) {
        return clazz;
    }
    
    protected JavaClass createClass(final String class_name) {
        final int index = class_name.indexOf("$$BCEL$$");
        final String real_name = class_name.substring(index + 8);
        JavaClass clazz = null;
        try {
            final byte[] bytes = Utility.decode(real_name, true);
            final ClassParser parser = new ClassParser(new ByteArrayInputStream(bytes), "foo");
            clazz = parser.parse();
        }
        catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
        final ConstantPool cp = clazz.getConstantPool();
        final ConstantClass cl = (ConstantClass)cp.getConstant(clazz.getClassNameIndex(), (byte)7);
        final ConstantUtf8 name = (ConstantUtf8)cp.getConstant(cl.getNameIndex(), (byte)1);
        name.setBytes(class_name.replace('.', '/'));
        return clazz;
    }
}
