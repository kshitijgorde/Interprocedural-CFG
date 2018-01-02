// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel;

import org.apache.bcel.util.ClassQueue;
import org.apache.bcel.util.ClassVector;
import java.io.InputStream;
import java.io.IOException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import java.util.HashMap;
import org.apache.bcel.util.ClassPath;

public abstract class Repository
{
    private static ClassPath class_path;
    private static HashMap classes;
    private static JavaClass OBJECT;
    
    public static JavaClass lookupClass(String class_name) {
        if (class_name == null || class_name.equals("")) {
            throw new RuntimeException("Invalid class name");
        }
        class_name = class_name.replace('/', '.');
        JavaClass clazz = Repository.classes.get(class_name);
        if (clazz == null) {
            try {
                final InputStream is = Repository.class_path.getInputStream(class_name);
                clazz = new ClassParser(is, class_name).parse();
                class_name = clazz.getClassName();
            }
            catch (IOException e) {
                return null;
            }
            Repository.classes.put(class_name, clazz);
        }
        return clazz;
    }
    
    public static ClassPath.ClassFile lookupClassFile(final String class_name) {
        try {
            return Repository.class_path.getClassFile(class_name);
        }
        catch (IOException e) {
            return null;
        }
    }
    
    public static void clearCache() {
        Repository.classes = new HashMap();
        Repository.OBJECT = lookupClass("java.lang.Object");
        if (Repository.OBJECT == null) {
            System.err.println("Warning: java.lang.Object not found on CLASSPATH!");
        }
        else {
            Repository.classes.put("java.lang.Object", Repository.OBJECT);
        }
    }
    
    public static JavaClass addClass(final JavaClass clazz) {
        final String name = clazz.getClassName();
        JavaClass cl = Repository.classes.get(name);
        if (cl == null) {
            final HashMap classes = Repository.classes;
            final String s = name;
            cl = clazz;
            classes.put(s, clazz);
        }
        return cl;
    }
    
    public static void removeClass(final String clazz) {
        Repository.classes.remove(clazz);
    }
    
    public static void removeClass(final JavaClass clazz) {
        removeClass(clazz.getClassName());
    }
    
    private static final JavaClass getSuperClass(final JavaClass clazz) {
        if (clazz == Repository.OBJECT) {
            return null;
        }
        return lookupClass(clazz.getSuperclassName());
    }
    
    public static JavaClass[] getSuperClasses(JavaClass clazz) {
        final ClassVector vec = new ClassVector();
        for (clazz = getSuperClass(clazz); clazz != null; clazz = getSuperClass(clazz)) {
            vec.addElement(clazz);
        }
        return vec.toArray();
    }
    
    public static JavaClass[] getSuperClasses(final String class_name) {
        final JavaClass jc = lookupClass(class_name);
        return (JavaClass[])((jc == null) ? null : getSuperClasses(jc));
    }
    
    public static JavaClass[] getInterfaces(JavaClass clazz) {
        final ClassVector vec = new ClassVector();
        final ClassQueue queue = new ClassQueue();
        queue.enqueue(clazz);
        while (!queue.empty()) {
            clazz = queue.dequeue();
            final String s = clazz.getSuperclassName();
            final String[] interfaces = clazz.getInterfaceNames();
            if (clazz.isInterface()) {
                vec.addElement(clazz);
            }
            else if (!s.equals("java.lang.Object")) {
                queue.enqueue(lookupClass(s));
            }
            for (int i = 0; i < interfaces.length; ++i) {
                queue.enqueue(lookupClass(interfaces[i]));
            }
        }
        return vec.toArray();
    }
    
    public static JavaClass[] getInterfaces(final String class_name) {
        return getInterfaces(lookupClass(class_name));
    }
    
    public static boolean instanceOf(final JavaClass clazz, final JavaClass super_class) {
        if (clazz == super_class) {
            return true;
        }
        final JavaClass[] super_classes = getSuperClasses(clazz);
        for (int i = 0; i < super_classes.length; ++i) {
            if (super_classes[i] == super_class) {
                return true;
            }
        }
        return super_class.isInterface() && implementationOf(clazz, super_class);
    }
    
    public static boolean instanceOf(final String clazz, final String super_class) {
        return instanceOf(lookupClass(clazz), lookupClass(super_class));
    }
    
    public static boolean instanceOf(final JavaClass clazz, final String super_class) {
        return instanceOf(clazz, lookupClass(super_class));
    }
    
    public static boolean instanceOf(final String clazz, final JavaClass super_class) {
        return instanceOf(lookupClass(clazz), super_class);
    }
    
    public static boolean implementationOf(final JavaClass clazz, final JavaClass inter) {
        if (clazz == inter) {
            return true;
        }
        final JavaClass[] super_interfaces = getInterfaces(clazz);
        for (int i = 0; i < super_interfaces.length; ++i) {
            if (super_interfaces[i] == inter) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean implementationOf(final String clazz, final String inter) {
        return implementationOf(lookupClass(clazz), lookupClass(inter));
    }
    
    public static boolean implementationOf(final JavaClass clazz, final String inter) {
        return implementationOf(clazz, lookupClass(inter));
    }
    
    public static boolean implementationOf(final String clazz, final JavaClass inter) {
        return implementationOf(lookupClass(clazz), inter);
    }
    
    static {
        Repository.class_path = new ClassPath();
        clearCache();
    }
}
