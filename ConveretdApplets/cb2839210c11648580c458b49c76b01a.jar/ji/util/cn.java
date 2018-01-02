// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import ji.ext.v;
import ji.ext.w;
import ji.v1event.af;
import java.awt.Component;
import java.util.Vector;
import java.util.Enumeration;
import ji.res.s;
import java.util.Hashtable;
import ji.res.aa;
import ji.res.z;
import ji.sec.f;
import ji.io.h;
import ji.io.ac;
import java.net.URL;

public class cn
{
    private static String a;
    private static Object b;
    
    public static final String a(final URL url, final boolean b, final String s) {
        if (d.e() && e.av()) {
            try {
                if (cn.a != null) {
                    return cn.a;
                }
                cn.a = d.ad();
                ac.e(String.valueOf(String.valueOf(cn.a)).concat("/"), s);
                if (ac.d(cn.a, s)) {
                    return cn.a;
                }
                h.d(s, "Unable to create: ".concat(String.valueOf(String.valueOf(cn.a))));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        String s2 = "";
        try {
            if (i.c(37)) {
                h.d("Pro", "PRO: isBrowserIE(): ".concat(String.valueOf(String.valueOf(d.em()))));
            }
            if (i.c(37)) {
                h.d("Pro", "PRO: isNetscape(): ".concat(String.valueOf(String.valueOf(d.av(s)))));
            }
            if (i.c(37)) {
                h.d("Pro", "PRO: isApple(): ".concat(String.valueOf(String.valueOf(d.aj(s)))));
            }
            if (!d.by(d.ad())) {
                if (i.c(37)) {
                    h.d("Pro", "PRO: Using install path : ".concat(String.valueOf(String.valueOf(d.ad()))));
                }
                return d.ad();
            }
            if (d.em()) {
                s2 = String.valueOf(String.valueOf(f.a("user.home", s))).concat("/classes");
            }
            else if (d.av(s)) {
                s2 = String.valueOf(String.valueOf(f.a("user.dir", s))).concat("/java/classes");
                if (d.a9(s).toLowerCase().startsWith("uni")) {
                    s2 = "/usr/lib/netscape/java/classes";
                }
            }
            else if (d.aj(s)) {
                if (i.c(37)) {
                    h.d("Pro", "PRO: isJava130OrLater(): ".concat(String.valueOf(String.valueOf(d.a(1, 3, s)))));
                }
                if (d.a(1, 3, s)) {
                    s2 = "/Library/Java/Extensions/";
                }
                else {
                    s2 = System.getProperty("java.class.path");
                    s2 = System.getProperty("java.ext.dirs");
                    if (d.by(s2)) {
                        s2 = String.valueOf(String.valueOf(System.getProperty("java.home"))).concat("/MRJClasses");
                    }
                }
            }
            else {
                if (b) {
                    if (i.c(37)) {
                        h.d("Pro", "PRO: isJava140OrEarlier(): ".concat(String.valueOf(String.valueOf(d.ar(s)))));
                    }
                    if (d.aj(s)) {
                        s2 = System.getProperty("java.ext.dirs");
                        if (i.c(37)) {
                            h.d("Pro", "PRO: java.ext.dirs: ".concat(String.valueOf(String.valueOf(System.getProperty("java.ext.dirs")))));
                        }
                    }
                    else if (d.ar(s)) {
                        s2 = String.valueOf(String.valueOf(System.getProperty("java.home"))).concat("/classes");
                        if (i.c(37)) {
                            h.d("Pro", "PRO: java.home: ".concat(String.valueOf(String.valueOf(System.getProperty("java.home")))));
                        }
                    }
                    else {
                        s2 = System.getProperty("java.class.path");
                        final int index = s2.indexOf(59);
                        if (index > -1) {
                            s2 = s2.substring(0, index);
                        }
                        if (i.c(37)) {
                            h.d("Pro", "PRO: java.class.path: ".concat(String.valueOf(String.valueOf(System.getProperty("java.class.path")))));
                        }
                        if (d.by(s2)) {
                            s2 = String.valueOf(String.valueOf(System.getProperty("java.home"))).concat("/classes");
                            if (i.c(37)) {
                                h.d("Pro", "PRO: java.home: ".concat(String.valueOf(String.valueOf(System.getProperty("java.home")))));
                            }
                        }
                    }
                    if (i.c(37)) {
                        h.d("Pro", "PRO: 1: destinationPath =".concat(String.valueOf(String.valueOf(s2))));
                    }
                    if (s2 == null) {
                        s2 = f.a("java.ext.dirs", s);
                        if (i.c(37)) {
                            h.d("Pro", "PRO: java.ext.dirs: ".concat(String.valueOf(String.valueOf(System.getProperty("java.ext.dirs")))));
                        }
                    }
                    else if (s2.length() == 0) {
                        s2 = f.a("java.ext.dirs", s);
                        if (i.c(37)) {
                            h.d("Pro", "PRO: java.ext.dirs: ".concat(String.valueOf(String.valueOf(System.getProperty("java.ext.dirs")))));
                        }
                    }
                    if (i.c(37)) {
                        h.d("Pro", "PRO: 2: destinationPath =".concat(String.valueOf(String.valueOf(s2))));
                    }
                    if (s2 == null) {
                        s2 = String.valueOf(String.valueOf(f.a("user.dir", s))).concat("/classes");
                        if (i.c(37)) {
                            h.d("Pro", "PRO: user.dir: ".concat(String.valueOf(String.valueOf(System.getProperty("user.dir")))));
                        }
                    }
                    else if (s2.length() == 0) {
                        s2 = String.valueOf(String.valueOf(f.a("user.dir", s))).concat("/classes");
                        if (i.c(37)) {
                            h.d("Pro", "PRO: user.dir: ".concat(String.valueOf(String.valueOf(System.getProperty("user.dir")))));
                        }
                    }
                    if (i.c(37)) {
                        h.d("Pro", "PRO: 3: destinationPath =".concat(String.valueOf(String.valueOf(s2))));
                    }
                }
                else if (d.b()) {
                    s2 = z.c(s);
                }
                else {
                    s2 = f.a("user.dir", s);
                    if (i.c(37)) {
                        h.d("Pro", "PRO: user.dir: ".concat(String.valueOf(String.valueOf(System.getProperty("user.dir")))));
                    }
                }
                if (i.c(37)) {
                    h.d("Pro", "PRO: 4: destinationPath =".concat(String.valueOf(String.valueOf(s2))));
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        try {
            s2 = ac.b(s2, s);
        }
        catch (Exception ex3) {}
        if (i.c(37)) {
            h.d("Pro", "PRO: 5: destinationPath =".concat(String.valueOf(String.valueOf(s2))));
        }
        final String b2 = d.b(d.b(s2, "\\", "/"), "//", "/");
        if (i.c(37)) {
            h.d("Pro", "PRO: 6: destinationPath =".concat(String.valueOf(String.valueOf(b2))));
        }
        return b2;
    }
    
    public static byte[] a(final URL url, final String s, final Object o, final String s2) throws Exception {
        final ac ac = new ac(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(url, d.eg(), s2)))).append(d.bu(s2)).append("msg").append(d.bu(s2)).append(s))), false, false, 0, false, o, false, s2);
        byte[] b = new byte[(int)ac.v()];
        if (b != null) {
            ac.a(b);
            ac.a(o);
            b = d.b(b, s, false, new y(), s2);
        }
        return b;
    }
    
    public static final boolean a(final URL url, final Object o, final String s) {
        boolean b = true;
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(url, d.eg(), s)))).append("/").append("vtest.tmp")));
        try {
            final ac ac = new ac(value, true, false, 0, false, o, true, s);
            ac.b("test".getBytes());
            ac.a(o);
        }
        catch (Exception ex) {
            b = false;
            ex.printStackTrace();
        }
        try {
            if (b && !ac.c(value, s)) {
                b = false;
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return b;
    }
    
    public static final boolean b(final URL url, final Object o, final String s) {
        boolean b = false;
        String value = null;
        try {
            value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(url, d.eg(), s)))).append("/").append("vlocal.tmp")));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (ac.d(value, s)) {
            h.d(s, "LOCAL INSTALL APPEARS TO BE CORRUPT AND REQUIRED REMOVAL...");
            return true;
        }
        final co a = aa.a(url, o, s);
        if (a == null) {
            return false;
        }
        if (a.c()) {
            h.d(s, "LOCAL INSTALL NEEDS REMOVING...");
            h.d(s, "Due to earlier non cacheable version on server...");
            b = true;
            if (!i.c(25)) {
                h.d(s, "... but blocked due to ACMAutoCache=FALSE");
                b = false;
            }
            else if (!i.c(26)) {
                h.d(s, "... but blocked due to ACMCanRemoveCachedVersion=FALSE");
                b = false;
            }
        }
        else if (!i.c(25) && a.b()) {
            h.d(s, "LOCAL INSTALL NEEDS REMOVING...");
            h.d(s, "Due to ACMAutoCache=FALSE...");
            b = true;
        }
        if (b && !a(url, o, s)) {
            h.d(s, "Unable to modify files in install path: ".concat(String.valueOf(String.valueOf(a(url, d.eg(), s)))));
            h.d(s, "Blocking Auto-Remove attempt until files can be removed.");
            b = false;
        }
        if (b) {
            if (d.em()) {
                if (d.f9 != null) {
                    if (d.f9.toLowerCase().indexOf("ji.cab") < 0) {
                        h.d(s, "Missing ji.cab value from CABBASE parameter!");
                        h.d(s, "Blocking Auto-Remove until ji.cab is specified otherwise viewONE will not work.");
                        b = false;
                    }
                }
                else {
                    h.d(s, "Missing CABBASE parameter!");
                    h.d(s, "Blocking Auto-Remove until ji.cab is specified otherwise viewONE will not work.");
                    b = false;
                }
            }
            else if (d.f8 != null) {
                if (d.f8.toLowerCase().indexOf("ji.jar") < 0) {
                    h.d(s, "Missing ji.jar value from ARCHIVE parameter!");
                    h.d(s, "Blocking Auto-Remove until ji.jar is specified otherwise viewONE will not work.");
                    b = false;
                }
            }
            else {
                h.d(s, "Missing ARCHIVE parameter!");
                h.d(s, "Blocking Auto-Remove until ji.jar is specified otherwise viewONE will not work.");
                b = false;
            }
        }
        aa.b("".concat(String.valueOf(String.valueOf(a.h))));
        return b;
    }
    
    public static final long c(final URL url, final Object o, final String s) {
        return d.p(a(url, d.eg(), s), s);
    }
    
    public static final void d(final URL url, final Object o, final String s) {
        String value = null;
        ac ac = null;
        try {
            value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(url, d.eg(), s)))).append("/").append("vlocal.v1")));
            ac = new ac(value, false, false, 0, false, o, false, s);
            final long n = ac.p();
            ac.a(new byte[1024]);
            String s2 = ac.n();
            try {
                final Hashtable hashtable = new Hashtable<String, String>();
                while (s2 != null) {
                    hashtable.put(s2, s2);
                    s2 = ac.n();
                }
                Enumeration<String> enumeration = hashtable.keys();
                int n2 = 0;
                hashtable.size();
                s.d();
                while (enumeration.hasMoreElements()) {
                    final int size = hashtable.size();
                    int n3 = 0;
                    while (enumeration.hasMoreElements()) {
                        final String s3 = enumeration.nextElement();
                        final String s4 = hashtable.get(s3);
                        try {
                            if (ji.io.ac.c(s4, s)) {
                                hashtable.remove(s3);
                                ++n2;
                            }
                            else {
                                ++n3;
                            }
                        }
                        catch (Exception ex5) {}
                    }
                    if (n3 == size) {
                        break;
                    }
                    enumeration = hashtable.keys();
                }
            }
            catch (Exception ex) {
                if (ex.toString().toLowerCase().indexOf("null") < 0) {
                    ex.printStackTrace();
                }
            }
        }
        catch (Exception ex2) {
            if (ex2.toString().toLowerCase().indexOf("null") < 0) {
                ex2.printStackTrace();
            }
        }
        finally {
            try {
                if (ac != null) {
                    ac.a(o);
                }
            }
            catch (Exception ex6) {}
            try {
                if (value != null) {
                    ji.io.ac.c(value, s);
                }
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
        try {
            ji.io.ac.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(url, d.eg(), s)))).append("/").append("ji").append(".").append("jar"))), s);
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
        try {
            ji.io.ac.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(url, d.eg(), s)))).append("/").append("vlocal.tmp"))), s);
        }
        catch (Exception ex7) {}
        a(url, s);
        b(url, s);
    }
    
    public static final void a(final URL url, final String s) {
        try {
            final String a = a(url, d.eg(), s);
            ac.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a))).append("/").append("vlocal.v1"))), s);
            aa.a(a, s);
            ac.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a))).append("/").append("daeja1.v1"))), s);
            try {
                final String[] h = ac.h(a, s);
                if (h != null) {
                    for (int i = 0; i < h.length; ++i) {
                        if (h[i] != null && h[i].toLowerCase().endsWith(".v1")) {
                            ac.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a))).append("/").append(h[i]))), s);
                        }
                    }
                }
            }
            catch (Exception ex2) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static final void b(final URL url, final String s) {
        final String a = a(url, d.eg(), s);
        final Vector vector = new Vector<String>();
        final Vector vector2 = new Vector<String>();
        vector.addElement(a);
        final String bu = d.bu(s);
        try {
            while (vector.size() > 0) {
                final String s2 = vector.elementAt(0);
                vector.removeElementAt(0);
                final String[] h = ac.h(s2, s);
                if (h != null) {
                    for (int i = 0; i < h.length; ++i) {
                        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(bu).append(h[i])));
                        if (ac.f(value, s)) {
                            final String[] h2 = ac.h(value, s);
                            if (h2 != null) {
                                for (int j = 0; j < h2.length; ++j) {
                                    if (h2[j].toLowerCase().indexOf("v1.v1") >= 0) {
                                        vector.addElement(value);
                                        vector2.addElement(value);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final int size = vector2.size();
        int n = 0;
        boolean b = true;
        while (vector2.size() > 0) {
            final String s3 = vector2.elementAt(0);
            vector2.removeElementAt(0);
            int n2 = 1;
            final String[] h3 = ac.h(s3, s);
            if (h3 != null) {
                for (int k = 0; k < h3.length; ++k) {
                    final String s4 = h3[k];
                    try {
                        if (!ac.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(bu).append(s4))), s)) {
                            n2 = 0;
                        }
                    }
                    catch (Exception ex2) {}
                }
            }
            if (n2 == 0) {
                vector2.addElement(s3);
                ++n;
            }
            else {
                try {
                    if (ac.d(s3, s) && !ac.c(s3, s)) {
                        n2 = 0;
                    }
                }
                catch (Exception ex3) {}
                if (n2 == 0) {
                    vector2.addElement(s3);
                    ++n;
                }
                else {
                    n = 0;
                }
            }
            if (n >= size) {
                b = false;
                if (size > 0) {
                    h.d(s, "Failed to delete files after running backup tidy process - check security priveleges of ".concat(String.valueOf(String.valueOf(a))));
                    break;
                }
                break;
            }
        }
        if (b) {
            a(url, s);
        }
    }
    
    public static final void a(final boolean b, final Component component, final String s, final af af) throws Exception {
        a(b, "daeja1", 768, component, s, af);
    }
    
    public static final void b(final boolean b, final Component component, final String s, final af af) throws Exception {
        a(b, "daeja2", 968, component, s, af);
    }
    
    public static final String a() {
        return w.a();
    }
    
    public static final void c(final boolean b, final Component component, final String s, final af af) throws Exception {
        try {
            if (i.c(37)) {
                h.d(s, "PRO: Install JP2...");
            }
            if (i.c(37)) {
                h.d(s, "PRO: Install PNG first...");
            }
            a(b, component, s, af);
            if (i.c(37)) {
                h.d(s, "PRO: PNG done...");
            }
        }
        catch (Exception ex) {}
        if (i.c(37)) {
            h.d(s, "PRO: now JP2...");
        }
        a(b, "daeja3", 947, component, s, af);
        if (i.c(37)) {
            h.d(s, "PRO: JP2 done...");
        }
    }
    
    public static final String a(final String s) {
        return a((String)null, s);
    }
    
    public static final String a(final String s, final String s2) {
        if (d.ax(s2)) {
            return String.valueOf(String.valueOf(System.getProperty("user.home"))).concat("/Library/Frameworks");
        }
        if (d.av(s2)) {
            return String.valueOf(String.valueOf(d.a1(s2))).concat("/java/bin");
        }
        if (d.by(s)) {
            return z.a(s2);
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(z.a(s2)))).append("/").append(s)));
    }
    
    private static final String c(final String s, final String s2) {
        String s3 = "dll";
        final StringBuffer sb = new StringBuffer();
        if (d.ax(s2)) {
            s3 = "framework";
        }
        if (!s.toLowerCase().endsWith(s3)) {
            sb.append(s);
            sb.append(".");
            sb.append(s3);
        }
        else {
            sb.append(s);
        }
        if (d.ax(s2)) {
            sb.append("/");
            sb.append(s);
        }
        return sb.toString();
    }
    
    public static final String b(final String s, final String s2) {
        return a(null, s, s2);
    }
    
    public static final String a(final String s, final String s2, final String s3) {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(s, s3)))).append("/").append(c(s2, s3))));
    }
    
    public static final String b(final String s, final String s2, final String s3) {
        if (d.ax(s3)) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(s, s3)))).append("/").append(s2).append(".framework")));
        }
        return null;
    }
    
    public static final boolean a(final Component component, final String s, final af af, final String s2, final String[] array, final String s3, final boolean b, final boolean b2, final boolean b3) {
        boolean b4 = true;
        synchronized (cn.b) {
            Label_0123: {
                if (!b) {
                    try {
                        Block_9: {
                            for (int i = 0; i < array.length; ++i) {
                                if (!ac.d(b3 ? array[i] : a(s2, array[i], s), s)) {
                                    break Block_9;
                                }
                            }
                            break Label_0123;
                        }
                        if (i.c(37)) {
                            final int i;
                            h.d(s, String.valueOf(String.valueOf(new StringBuffer("PRO: The library ").append(array[i]).append(" has not been cached."))));
                        }
                        b4 = false;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        b4 = false;
                    }
                }
            }
            Label_0176: {
                if (b4) {
                    if (!b) {
                        break Label_0176;
                    }
                }
                try {
                    a(component, s, s2, array, b2, b3);
                    if (d.by(s2)) {
                        ac.c(a(s2, s), s);
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        // monitorexit(cn.b)
        return b4;
    }
    
    private static void a(final Component component, final String s, final String s2, final String[] array, final boolean b, final boolean b2) {
        v v = null;
        Vector vector = null;
        boolean b3 = false;
        if (b) {
            vector = new Vector<String>();
        }
        for (int i = 0; i < array.length; ++i) {
            final String s3 = b2 ? array[i] : a(s2, array[i], s);
            try {
                if (ac.d(s3, s)) {
                    try {
                        if (ac.c(s3, s)) {
                            if (i.c(37)) {
                                h.d(s, String.valueOf(String.valueOf(new StringBuffer("PRO: ").append(array[i]).append(" deleted ok."))));
                            }
                            b3 = true;
                        }
                        else {
                            if (v == null) {
                                v = new v();
                            }
                            if (d.bh(s3).toLowerCase().equalsIgnoreCase("dll")) {
                                boolean b4 = false;
                                final long c = v.c(s3, s, component);
                                if (c != 0) {
                                    if (v.a(c, s, component)) {
                                        if (!ac.c(s3, s)) {
                                            if (i.c(37)) {
                                                h.d(s, String.valueOf(String.valueOf(new StringBuffer("PRO: Removing ").append(array[i]).append(" failed, even tried unload"))));
                                            }
                                        }
                                        else {
                                            if (i.c(37)) {
                                                h.d(s, String.valueOf(String.valueOf(new StringBuffer("PRO: ").append(array[i]).append(" unloaded and deleted ok."))));
                                            }
                                            b4 = true;
                                            b3 = true;
                                        }
                                    }
                                    else if (i.c(37)) {
                                        h.d(s, "PRO: Couldn't free ".concat(String.valueOf(String.valueOf(array[i]))));
                                    }
                                }
                                else if (i.c(37)) {
                                    h.d(s, String.valueOf(String.valueOf(new StringBuffer("PRO: ").append(array[i]).append(" was not loaded."))));
                                }
                                if (!b4 && b) {
                                    vector.add(array[i]);
                                }
                            }
                        }
                    }
                    catch (Exception ex) {
                        d.a(ex);
                    }
                }
                final String b5 = b(s2, array[i], s);
                if (b5 != null && ac.d(b5, s)) {
                    ac.c(b5, s);
                }
            }
            catch (Exception ex2) {
                d.a(ex2);
            }
        }
        if (b && vector.size() > 0) {
            final int size = vector.size();
            if (b3) {
                if (i.c(37)) {
                    h.d(s, String.valueOf(String.valueOf(new StringBuffer("PRO: Could not remove ").append(size).append(" libraries, retrying..."))));
                }
                final String[] array2 = new String[size];
                for (int j = 0; j < size; ++j) {
                    if (i.c(37)) {
                        h.d(s, String.valueOf(String.valueOf(new StringBuffer("PRO: ").append(array[j]).append(" did not exist so no need to attempt to delete."))));
                    }
                    array2[j] = vector.get(j);
                }
                a(component, s, s2, array2, b, b2);
            }
            else if (i.c(37)) {
                h.d(s, String.valueOf(String.valueOf(new StringBuffer("PRO: Could not remove ").append(size).append(" libraries, no point retrying as last attempt did not unload any."))));
            }
        }
    }
    
    private static final void a(boolean b, final String s, final int n, final Component component, final String s2, final af af) throws Exception {
        if (i.c(37)) {
            h.d(s2, String.valueOf(String.valueOf(new StringBuffer("PRO: (").append(s).append(") Install: ACM-ENABLED = ").append(i.c(25)))));
        }
        if (i.c(25) && aa.b(component, s2)) {
            if (!b) {
                b = !w.a(s, s2);
            }
            if (i.c(37)) {
                h.d(s2, String.valueOf(String.valueOf(new StringBuffer("PRO: (").append(s).append(") Installed all ready = ").append(!b))));
            }
            if (b) {
                w.a(s, af, n, component, s2);
            }
        }
    }
    
    public static final String b() {
        return "ji";
    }
    
    public static final String c() {
        return "daeja1";
    }
    
    public static final String d() {
        return "daeja2";
    }
    
    public static final String e() {
        return "daeja3";
    }
    
    public static final void a(final Component component, final String s, final String s2, final String[] array) {
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(e.am(), d.eg(), s)))).append("/").append("vlocal.v1")));
        ac ac = null;
        ac ac2 = null;
        boolean al = false;
        try {
            al = d.al;
            d.al = true;
            ac = new ac(value, false, false, 0, false, component, false, s);
            final byte[] array2 = new byte[(int)ac.v()];
            ac.a(array2);
            ac.a(component);
            ji.io.ac.c(value, s);
            ac2 = new ac(value, true, false, 0, false, component, true, s);
            ac2.b(array2);
            for (int i = 0; i < array.length; ++i) {
                ac2.b(String.valueOf(String.valueOf(d.b(ji.io.ac.b(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("/").append(array[i]))), s), "\\", "/"))).concat("\r\n").getBytes());
            }
        }
        catch (Exception ex) {
            d.a(ex);
        }
        finally {
            try {
                if (ac != null) {
                    ac.a(component);
                }
            }
            catch (Exception ex2) {}
            try {
                if (ac2 != null) {
                    ac2.a(component);
                }
            }
            catch (Exception ex3) {}
            d.al = al;
        }
    }
    
    static {
        cn.a = null;
        cn.b = new Object();
    }
}
