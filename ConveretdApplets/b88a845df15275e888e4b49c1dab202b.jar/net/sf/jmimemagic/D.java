// 
// Decompiled by Procyon v0.5.30
// 

package net.sf.jmimemagic;

import java.io.PrintStream;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import jmaster.util.log.A;

public class D
{
    private static A C;
    private static boolean D;
    private static C B;
    private static HashMap A;
    static /* synthetic */ Class class$net$sf$jmimemagic$D;
    
    public D() {
        net.sf.jmimemagic.D.C.D("instantiated");
    }
    
    private static void A(final String s, final I i) {
        if (net.sf.jmimemagic.D.A.keySet().contains(s)) {
            net.sf.jmimemagic.D.A.get(s).add(i);
        }
        else {
            final ArrayList<I> list = new ArrayList<I>();
            list.add(i);
            net.sf.jmimemagic.D.A.put(s, list);
        }
    }
    
    public static synchronized void B() throws E {
        net.sf.jmimemagic.D.C.D("initialize()");
        if (!net.sf.jmimemagic.D.D) {
            net.sf.jmimemagic.D.C.D("initializing");
            (net.sf.jmimemagic.D.B = new C()).B();
            for (final I i : net.sf.jmimemagic.D.B.A()) {
                final String a = i.B().A();
                if (a != null && !a.trim().equals("")) {
                    if (net.sf.jmimemagic.D.C.B()) {
                        net.sf.jmimemagic.D.C.D("adding hint mapping for extension '" + a + "'");
                    }
                    A(a, i);
                }
                else {
                    if (!i.B().F().equals("detector")) {
                        continue;
                    }
                    final String[] d = i.D();
                    for (int j = 0; j < d.length; ++j) {
                        if (net.sf.jmimemagic.D.C.B()) {
                            net.sf.jmimemagic.D.C.D("adding hint mapping for extension '" + d[j] + "'");
                        }
                        A(d[j], i);
                    }
                }
            }
            net.sf.jmimemagic.D.D = true;
        }
    }
    
    public static Collection A() throws E {
        net.sf.jmimemagic.D.C.D("getMatchers()");
        if (!net.sf.jmimemagic.D.D) {
            B();
        }
        final Iterator<I> iterator = net.sf.jmimemagic.D.B.A().iterator();
        final ArrayList<Object> list = new ArrayList<Object>();
        while (iterator.hasNext()) {
            final I i = iterator.next();
            try {
                list.add(i.clone());
            }
            catch (CloneNotSupportedException ex) {
                net.sf.jmimemagic.D.C.E("failed to clone matchers");
                throw new E("failed to clone matchers");
            }
        }
        return list;
    }
    
    public static F A(final byte[] array) throws E, H, B {
        return A(array, false);
    }
    
    public static F A(final byte[] array, final boolean b) throws E, H, B {
        net.sf.jmimemagic.D.C.D("getMagicMatch(byte[])");
        if (!net.sf.jmimemagic.D.D) {
            B();
        }
        final Collection a = net.sf.jmimemagic.D.B.A();
        net.sf.jmimemagic.D.C.D("getMagicMatch(byte[]): have " + a.size() + " matchers");
        for (final I i : a) {
            net.sf.jmimemagic.D.C.D("getMagicMatch(byte[]): trying to match: " + i.B().H());
            try {
                final F a2;
                if ((a2 = i.A(array, b)) != null) {
                    net.sf.jmimemagic.D.C.D("getMagicMatch(byte[]): matched " + i.B().H());
                    return a2;
                }
                continue;
            }
            catch (IOException ex) {
                net.sf.jmimemagic.D.C.E("getMagicMatch(byte[]): " + ex);
                throw new B(ex);
            }
            catch (G g) {
                net.sf.jmimemagic.D.C.E("getMagicMatch(byte[]): " + g);
                throw new B(g);
            }
            break;
        }
        throw new H();
    }
    
    public static F A(final File file, final boolean b) throws E, H, B {
        return A(file, b, false);
    }
    
    public static F A(final File file, final boolean b, final boolean b2) throws E, H, B {
        net.sf.jmimemagic.D.C.D("getMagicMatch(File)");
        if (!net.sf.jmimemagic.D.D) {
            B();
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final ArrayList list = new ArrayList<I>();
        if (b) {
            net.sf.jmimemagic.D.C.D("trying to use hints first");
            final String name = file.getName();
            final int lastIndex = name.lastIndexOf(46);
            if (lastIndex > -1) {
                final String substring = name.substring(lastIndex + 1, name.length());
                if (substring != null && !substring.equals("")) {
                    if (net.sf.jmimemagic.D.C.B()) {
                        net.sf.jmimemagic.D.C.D("using extension '" + substring + "' for hinting");
                    }
                    final Collection<I> collection = net.sf.jmimemagic.D.A.get(substring);
                    if (collection != null) {
                        for (final I i : collection) {
                            net.sf.jmimemagic.D.C.D("getMagicMatch(File): trying to match: " + i.B().K());
                            try {
                                final F a;
                                if ((a = i.A(file, b2)) != null) {
                                    net.sf.jmimemagic.D.C.D("getMagicMatch(File): matched " + i.B().K());
                                    if (net.sf.jmimemagic.D.C.B()) {
                                        net.sf.jmimemagic.D.C.D("found match in '" + (System.currentTimeMillis() - currentTimeMillis) + "' milliseconds");
                                    }
                                    return a;
                                }
                            }
                            catch (G g) {
                                net.sf.jmimemagic.D.C.E("getMagicMatch(File): " + g);
                                throw new B(g);
                            }
                            catch (IOException ex) {
                                net.sf.jmimemagic.D.C.E("getMagicMatch(File): " + ex);
                                throw new B(ex);
                            }
                            list.add(i);
                        }
                    }
                }
                else {
                    net.sf.jmimemagic.D.C.D("no file extension, ignoring hints");
                }
            }
            else {
                net.sf.jmimemagic.D.C.D("no file extension, ignoring hints");
            }
        }
        final Collection a2 = net.sf.jmimemagic.D.B.A();
        net.sf.jmimemagic.D.C.D("getMagicMatch(File): have " + a2.size() + " matches");
        for (final I j : a2) {
            if (!list.contains(j)) {
                net.sf.jmimemagic.D.C.D("getMagicMatch(File): trying to match: " + j.B().K());
                try {
                    final F a3;
                    if ((a3 = j.A(file, b2)) != null) {
                        net.sf.jmimemagic.D.C.D("getMagicMatch(File): matched " + j.B().K());
                        if (net.sf.jmimemagic.D.C.B()) {
                            net.sf.jmimemagic.D.C.D("found match in '" + (System.currentTimeMillis() - currentTimeMillis) + "' milliseconds");
                        }
                        return a3;
                    }
                    continue;
                }
                catch (G g2) {
                    net.sf.jmimemagic.D.C.E("getMagicMatch(File): " + g2);
                    throw new B(g2);
                }
                catch (IOException ex2) {
                    net.sf.jmimemagic.D.C.E("getMagicMatch(File): " + ex2);
                    throw new B(ex2);
                }
            }
            net.sf.jmimemagic.D.C.D("getMagicMatch(File): already checked, skipping: " + j.B().K());
        }
        throw new H();
    }
    
    public static void A(final PrintStream printStream) throws E {
        if (!net.sf.jmimemagic.D.D) {
            B();
        }
        final Collection a = A();
        net.sf.jmimemagic.D.C.D("have " + a.size() + " matches");
        for (final I i : a) {
            net.sf.jmimemagic.D.C.D("printing");
            A(printStream, i, "");
        }
    }
    
    private static void A(final PrintStream printStream, final I i, final String s) {
        printStream.println(s + "name: " + i.B().K());
        printStream.println(s + "children: ");
        final Iterator<I> iterator = i.C().iterator();
        while (iterator.hasNext()) {
            A(printStream, iterator.next(), s + "  ");
        }
    }
    
    public static void A(final PrintStream printStream, final F f, final String s) {
        printStream.println(s + "=============================");
        printStream.println(s + "mime type: " + f.H());
        printStream.println(s + "description: " + f.K());
        printStream.println(s + "extension: " + f.A());
        printStream.println(s + "test: " + new String(f.I().array()));
        printStream.println(s + "bitmask: " + f.D());
        printStream.println(s + "offset: " + f.G());
        printStream.println(s + "length: " + f.B());
        printStream.println(s + "type: " + f.F());
        printStream.println(s + "comparator: " + f.E());
        printStream.println(s + "=============================");
        final Iterator<F> iterator = f.J().iterator();
        while (iterator.hasNext()) {
            A(printStream, iterator.next(), s + "    ");
        }
    }
    
    public static void A(final String[] array) {
        try {
            final File file = new File(array[0]);
            if (file.exists()) {
                final F a = A(file, true, false);
                System.out.println("filename: " + array[0]);
                A(System.out, a, "");
            }
            else {
                System.err.println("file '" + file.getCanonicalPath() + "' not found");
            }
        }
        catch (H h) {
            System.out.println("no match found");
        }
        catch (Exception ex) {
            System.err.println("error: " + ex);
            ex.printStackTrace(System.err);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        net.sf.jmimemagic.D.C = jmaster.util.log.B.getInstance().getLog((net.sf.jmimemagic.D.class$net$sf$jmimemagic$D == null) ? (net.sf.jmimemagic.D.class$net$sf$jmimemagic$D = class$("net.sf.jmimemagic.D")) : net.sf.jmimemagic.D.class$net$sf$jmimemagic$D);
        net.sf.jmimemagic.D.D = false;
        net.sf.jmimemagic.D.B = null;
        net.sf.jmimemagic.D.A = new HashMap();
    }
}
