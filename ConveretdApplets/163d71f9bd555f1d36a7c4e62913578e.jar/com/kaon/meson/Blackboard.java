// 
// Decompiled by Procyon v0.5.30
// 

package com.kaon.meson;

import java.lang.reflect.Array;
import java.applet.Applet;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.lang.reflect.Constructor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.awt.Component;
import java.awt.Image;
import java.lang.reflect.Method;
import java.io.PrintStream;

public final class Blackboard extends FastHashtable implements Gluon, Mb
{
    private static final Double a;
    private static final Double b;
    private static final Double c;
    private static final Double d;
    private static final Double e;
    private static final Float f;
    private static final Float g;
    private static final Float h;
    private static final Float i;
    private static final Float j;
    public static boolean quietMode;
    private FastHashtable k;
    private FastHashtable l;
    private FastHashtable m;
    private FastHashtable n;
    private FastHashtable[] o;
    private int p;
    private int q;
    private FastVector r;
    private FastVector s;
    private Object t;
    private volatile Thread u;
    private FastVector v;
    private FastVector w;
    private FastHashtable x;
    public Object[] y;
    public int z;
    private double aa;
    private double ab;
    public double frameRate;
    private String ac;
    private int ad;
    private volatile int ae;
    private boolean af;
    private boolean ag;
    private volatile int ah;
    private volatile int ai;
    public String aj;
    private boolean ak;
    private boolean al;
    private FastHashtable am;
    private static boolean an;
    private static boolean ao;
    public static Object ap;
    private static final Class aq;
    public static final Class BYTE_ARRAY_CLASS;
    private static final Class ar;
    private FastVector as;
    private FastHashtable at;
    private static Object[] au;
    private Mb av;
    public double tickTime;
    private static FastHashtable aw;
    private Object ax;
    private boolean ay;
    public static PrintStream az;
    public static int a0;
    public static String[] a1;
    private boolean a2;
    private static Class a3;
    private static Method a4;
    private static char[] a5;
    private static char[] a6;
    private static int a7;
    private static int a8;
    private static int a9;
    private static int ba;
    private static int bb;
    private static int bc;
    private static int bd;
    private static int be;
    private static int bf;
    private static int bg;
    private static int bh;
    private static int bi;
    private static int bj;
    private static int bk;
    private String bl;
    private static String bm;
    private static byte bn;
    private static byte bo;
    private static byte bp;
    private static byte bq;
    private static byte[][] br;
    public static /* synthetic */ Class bs;
    public static /* synthetic */ Class bt;
    public static /* synthetic */ Class bu;
    public static /* synthetic */ Class bv;
    public static /* synthetic */ Class bw;
    public static /* synthetic */ Class bx;
    public static /* synthetic */ Class by;
    public static /* synthetic */ Class bz;
    public static /* synthetic */ Class b0;
    public static /* synthetic */ Class b1;
    public static /* synthetic */ Class b2;
    public static /* synthetic */ Class b3;
    public static /* synthetic */ Class b4;
    public static /* synthetic */ Class b5;
    public static /* synthetic */ Class b6;
    public static /* synthetic */ Class b7;
    
    public static synchronized Blackboard getInstance(final String s) {
        final String intern = s.intern();
        Blackboard blackboard = (Blackboard)Blackboard.aw.get(intern);
        if (blackboard == null) {
            blackboard = new Blackboard(intern);
            Blackboard.aw.put(intern, blackboard);
        }
        return blackboard;
    }
    
    private Blackboard(final String s) {
        super(65537);
        this.k = new FastHashtable(251);
        this.l = new FastHashtable(251);
        this.m = new FastHashtable(251);
        this.n = new FastHashtable(67);
        this.o = new FastHashtable[0];
        this.p = 0;
        this.q = 0;
        this.r = new FastVector(64, 64);
        this.v = new FastVector(512, 64);
        this.w = new FastVector(512, 64);
        this.x = new FastHashtable(10007);
        this.y = new Object[65536];
        this.z = 0;
        this.frameRate = 1.0;
        this.aj = "";
        this.as = new FastVector(8, 8);
        this.ax = new Object();
        this.bl = "";
        super.d = true;
        this.av = this;
        this.ab = this.av.getTime();
        this.ac = s.intern();
        this.registerFunctionHandlers(new String[] { "foreach", "try", "Meson.length", "Meson.assert", "Meson.throw", "Meson.dump", "Meson.log", "Meson.echo", "Meson.newInstance", "Meson.getMethod", "Meson.callMethod", "Meson.getConstructor", "Meson.callConstructor", "Meson.parse", "Meson.load", "Meson.include", "Meson.gluon", "Meson.newTrigger", "Meson.showDocument", "Meson.showStatus", "Meson.addContextMenu", "Meson.clearContextMenu", "Meson.unique", "Meson.unicode", "Meson.getProperty", "Meson.checkpoint", "Meson.restore", "Meson.saveExpr", "Meson.preserveExpr", "Meson.moveToDialog", "Meson.inDialog", "Meson.closeDialog", "Meson.logAction", "Meson.static", "Meson.trace" }, this);
        try {
            this.a("Meson.quietMode", (Object)(Blackboard.quietMode ? "true" : "false"));
            this.load("System Properties", this.getClass().getResourceAsStream("version.txt"));
            this.load("System Properties", this.getClass().getResourceAsStream("meson.txt"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public String toString() {
        return this.ac;
    }
    
    public void setTimeSource(final Mb av) {
        this.av = av;
        this.ab = this.av.getTime();
    }
    
    public void zeroClock() throws MesonException {
        this.ab = this.av.getTime();
        this.aa = 0.0;
        this.setGlobal("Meson.clock", new Double(0.0));
    }
    
    public double getTime() {
        return System.currentTimeMillis() * 0.001;
    }
    
    public double getActualTime() {
        return this.getTime();
    }
    
    public long getTimeMicros() {
        return (long)(this.av.getActualTime() * 1000000.0);
    }
    
    public Image createImage(int n, int n2) {
        if (n == 0) {
            n = 1;
        }
        if (n2 == 0) {
            n2 = 1;
        }
        if (Blackboard.ao) {
            try {
                return (Image)Class.forName("java.awt.image.BufferedImage").getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(new Integer(n), new Integer(n2), new Integer(1));
            }
            catch (Exception ex) {
                Blackboard.ao = false;
            }
        }
        final FastVector instances = this.getInstances("Applet");
        if (instances.size() == 0) {
            return null;
        }
        final Component component = (Component)this.getGlobal(instances.get(0) + ".javaApplet", (Blackboard.bs == null) ? (Blackboard.bs = class$("java.awt.Component")) : Blackboard.bs);
        if (component == null) {
            return null;
        }
        return component.createImage(n, n2);
    }
    
    public void invokeAWT(final Method method, final Object o, final Object[] array) {
        final FastVector instances = this.getInstances("Applet");
        if (instances.size() == 0) {
            try {
                method.invoke(o, array);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            ((MesonApplet)this.getGlobal(instances.get(0) + ".javaApplet", (Blackboard.bt == null) ? (Blackboard.bt = class$("com.kaon.meson.MesonApplet")) : Blackboard.bt)).a(method, o, array);
        }
    }
    
    public synchronized void referenceCount(final int n) {
        this.af = true;
        this.ae += n;
        if (this.ae == 0) {
            this.ag = true;
            for (long n2 = this.n.iterator(); n2 != -1L; n2 = this.n.advance(n2)) {
                try {
                    ((Gluon)this.n.getValue(n2)).destroy(this);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            Blackboard.aw.remove(this.ac);
            System.err.println("Releasing Blackboard " + this.ac);
        }
        else if (this.ag) {
            this.ag = false;
            for (long n3 = this.n.iterator(); n3 != -1L; n3 = this.n.advance(n3)) {
                ((Gluon)this.n.getValue(n3)).reinit(this);
            }
            Blackboard.aw.put(this.ac, this);
            System.err.println("Enabling Blackboard " + this.ac);
        }
        this.notifyAll();
    }
    
    public void acquireSupplementalLock() {
        synchronized (this.ax) {
            while (this.ay) {
                try {
                    this.ax.wait(500L);
                }
                catch (InterruptedException ex) {
                    throw new RuntimeException("Interrupted waiting for lock");
                }
                if (this.isDead()) {
                    throw new RuntimeException("Blackboard Died");
                }
            }
            this.ay = true;
        }
    }
    
    public void releaseSupplementalLock() {
        synchronized (this.ax) {
            this.ay = false;
            this.ax.notifyAll();
        }
    }
    
    public synchronized Object checkpoint() {
        final FastVector fastVector = new FastVector(6, 0);
        this.a(fastVector, this.k);
        this.a(fastVector, this.l);
        this.a(fastVector, this);
        return fastVector;
    }
    
    public synchronized void restore(final Object o) {
        final FastVector fastVector = (FastVector)o;
        this.a(fastVector, 0, this.k);
        this.a(fastVector, 2, this.l);
        this.a(fastVector, 4, this);
        this.c();
    }
    
    public synchronized Object saveExpr(final String s) throws MesonException {
        final FastVector fastVector = new FastVector(8, 8);
        for (long n = this.iterator(); n != -1L; n = this.advance(n)) {
            final Object key_ = this.getKey_(n);
            if (key_.toString().startsWith(s)) {
                final Object d = this.d(key_);
                fastVector.add(key_);
                fastVector.add(d);
            }
        }
        final int size = fastVector.size();
        if (size == 0) {
            return "";
        }
        final Object[] array = new Object[1 + size];
        final char[] array2 = new char[size * 2 - 1];
        int i = 0;
        int n2 = 0;
        int n3 = 1;
        while (i < size / 2) {
            array2[n2++] = ' ';
            array2[n2++] = ' ';
            array2[n2++] = '=';
            array[n3++] = fastVector.get(2 * i);
            array[n3++] = fastVector.get(2 * i + 1);
            if (n2 < array2.length) {
                array2[n2++] = ';';
            }
            ++i;
        }
        array[0] = array2;
        return array;
    }
    
    private void a(final FastVector fastVector, final FastHashtable fastHashtable) {
        final FastVector fastVector2 = new FastVector(fastHashtable.size(), 0);
        final FastVector fastVector3 = new FastVector(fastHashtable.size(), 0);
        fastVector.add(fastVector2);
        fastVector.add(fastVector3);
        for (long n = fastHashtable.iterator(); n != -1L; n = fastHashtable.advance(n)) {
            fastVector2.add(fastHashtable.getKey_(n));
            Object o = fastHashtable.getValue(n);
            if (((Object[])o).getClass() == Blackboard.aq) {
                final Object[] array = (Object[])(o = ((Object[])o).clone());
                if (fastHashtable == this && array.length == 3) {
                    if (array[0] instanceof FastVector) {
                        array[0] = ((FastVector)array[0]).clone();
                    }
                    array[1] = null;
                    if (array[2] != null) {
                        array[2] = ((FastVector)array[2]).clone();
                    }
                }
            }
            else if (o instanceof FastVector) {
                o = ((FastVector)o).clone();
            }
            else if (o instanceof FastHashtable) {
                o = ((FastHashtable)o).clone();
            }
            fastVector3.add(o);
        }
    }
    
    private void a(final FastVector fastVector, final int n, final FastHashtable fastHashtable) {
        fastHashtable.clear();
        final FastVector fastVector2 = (FastVector)fastVector.get(n);
        final FastVector fastVector3 = (FastVector)fastVector.get(n + 1);
        for (int i = 0; i < fastVector2.size(); ++i) {
            Object o = fastVector3.get(i);
            if (((Object[])o).getClass() == Blackboard.aq) {
                final Object[] array = (Object[])(o = ((Object[])o).clone());
                if (fastHashtable == this && array.length == 3) {
                    if (array[0] instanceof FastVector) {
                        array[0] = ((FastVector)array[0]).clone();
                    }
                    array[1] = null;
                    if (array[2] != null) {
                        array[2] = ((FastVector)array[2]).clone();
                    }
                }
            }
            else if (o instanceof FastVector) {
                o = ((FastVector)o).clone();
            }
            else if (o instanceof FastHashtable) {
                o = ((FastHashtable)o).clone();
            }
            fastHashtable.put_(fastVector2.get(i), o);
        }
    }
    
    public boolean isDead() {
        return this.af && this.ae == 0;
    }
    
    public int getReferenceCounter() {
        return this.ae;
    }
    
    public void registerFunctionHandlers(final String[] array, final Gluon gluon) {
        for (int i = 0; i < array.length; ++i) {
            this.m.put(array[i], gluon);
            try {
                this.a(array[i], new Object[] { { ' ', '\u0192' }, array[i] });
                ((Object[])this.get(array[i]))[2] = null;
            }
            catch (MesonException ex) {
                this.a("Built-in", ex);
            }
        }
    }
    
    public synchronized void load(final String s, final String s2) {
        try {
            this.a(s, new StringReader(s2), false);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public synchronized void load(final String s, final InputStream inputStream) throws IOException {
        this.a(s, new InputStreamReader(inputStream, "UTF8"), false);
    }
    
    public void setVT100Mode(final boolean ak) {
        this.ak = ak;
    }
    
    public void loadParameter(final String s) {
        try {
            this.r.addSync(this.parse("Meson.echo(" + s + ")"));
        }
        catch (MesonException ex) {
            this.a("loadParameter", ex);
        }
    }
    
    private String a(final String s, final Reader reader, final boolean b) throws IOException {
        BufferedReader bufferedReader;
        if (System.getProperty("java.version").startsWith("1.1")) {
            bufferedReader = new BufferedReader(reader, 1);
        }
        else {
            bufferedReader = new BufferedReader(reader, 1024);
        }
        String s2 = null;
        Object g = "";
        int i = 0;
        int n = 0;
        while (i == 0) {
            String line = bufferedReader.readLine();
            ++n;
            if (line == null) {
                i = 1;
                line = "";
            }
            if (s2 == null) {
                s2 = line;
            }
            else {
                s2 += line;
            }
            if (s2.endsWith("\\")) {
                s2 = s2.substring(0, s2.length() - 1);
                if (i == 0) {
                    continue;
                }
            }
            if (i != 0 && s2.equals("")) {
                break;
            }
            try {
                if (this.c(s2)) {
                    if (i != 0) {
                        throw new MesonException("Syntax Error: Unbalanced {, [ or (");
                    }
                    s2 += "\n";
                    continue;
                }
                else {
                    g = this.g(this.parse(s2));
                    if (b & !Blackboard.quietMode) {
                        System.out.print(this.t(g).append(this.aj));
                    }
                }
            }
            catch (Throwable t) {
                this.a(s + ":" + n + ": " + s2, t);
                if (!Blackboard.quietMode) {
                    System.out.print(this.aj);
                }
            }
            s2 = null;
        }
        return this.t(g).toString();
    }
    
    private FastHashtable a(final char c) throws MesonException {
        final int n = this.p + ((c == '^') ? 1 : 0);
        if (n >= this.o.length) {
            final int n2 = (int)(0.5 + (double)this.a("Meson.notebookPages", (Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu, true));
            if (n2 <= n) {
                throw new MesonException("Runaway evaluation exceeded limit of " + n2 + ".\n" + "Meson.notebookPages" + " must be increased.");
            }
            final FastHashtable[] o = new FastHashtable[n2];
            System.arraycopy(this.o, 0, o, 0, this.o.length);
            for (int i = this.o.length; i < o.length; ++i) {
                o[i] = new FastHashtable(251);
            }
            this.o = o;
        }
        return this.o[n];
    }
    
    private void c() {
        for (int i = this.p; i < this.o.length; ++i) {
            this.o[i].clear();
        }
    }
    
    public Object getGlobalDirect(final String s) throws MesonException {
        return this.d((Object)s);
    }
    
    private Object d(final Object o) throws MesonException {
        Object[] array = (Object[])this.get_(o);
        if (array == null) {
            this.a(o, (Object)null);
            array = (Object[])this.get_(o);
        }
        if (array.length == 1) {
            return this.d(array[0]);
        }
        return array[0];
    }
    
    private Object a(final Object t, final Class clazz, final boolean b) throws MesonException {
        Object[] array = (Object[])this.get_(t);
        if (array == null || array == Blackboard.au) {
            this.a(t, (Object)null);
            array = (Object[])this.get_(t);
        }
        if (array.length == 1) {
            return this.a(array[0], clazz, b);
        }
        if (this.t != null && array[2] != null) {
            ((FastVector)array[2]).setAdd(this.t);
        }
        if (b) {
            final Object o = array[1];
            if (o != null && o != Blackboard.ap) {
                if (clazz == null || o.getClass() == clazz || clazz.isInstance(o)) {
                    return o;
                }
                return this.a(o, clazz);
            }
        }
        else {
            array[1] = null;
        }
        final Object o2 = array[0];
        final Class<?> class1 = o2.getClass();
        if (class1 == clazz || (clazz == null && class1 != Blackboard.aq)) {
            return o2;
        }
        if (clazz == Blackboard.BYTE_ARRAY_CLASS) {
            return this.a(o2, clazz);
        }
        final Object t2 = this.t;
        if (b) {
            this.t = t;
        }
        try {
            array[1] = Blackboard.ap;
            final Object a = this.a(o2, clazz);
            if (array[1] != null) {
                array[1] = a;
            }
            return a;
        }
        finally {
            this.t = t2;
        }
    }
    
    public void queueAssignments(final Object[] array) {
        this.r.addSync(this.a(array, array.length));
    }
    
    public void queueAssignment(final Object o, final Object o2) {
        this.r.addSync(new Object[] { { ' ', ' ', '=' }, o, o2 });
    }
    
    public FastVector getInstances(final String s) {
        return (FastVector)this.k.get(s);
    }
    
    private Object a(final Object[] array, final int n) {
        final int n2 = n / 2;
        if (n2 == 0) {
            return "";
        }
        final Object[] array2 = new Object[1 + 2 * n2];
        final char[] array3 = new char[4 * n2 - 1];
        int i = 0;
        int n3 = 0;
        int n4 = 1;
        while (i < n2) {
            array3[n3++] = ' ';
            array3[n3++] = ' ';
            array3[n3++] = '=';
            array2[n4++] = array[2 * i];
            array2[n4++] = array[2 * i + 1];
            if (i < n2 - 1) {
                array3[n3++] = ';';
            }
            ++i;
        }
        array2[0] = array3;
        return array2;
    }
    
    public static String intern(final String s) {
        int n = (Integer.MAX_VALUE & s.hashCode()) % Blackboard.a0;
        for (int i = 0; i < 8; ++i) {
            final String s2 = Blackboard.a1[n];
            if (s2 == null) {
                return Blackboard.a1[n] = s.intern();
            }
            if (s2.equals(s)) {
                return s2;
            }
            n = (n + 17) % Blackboard.a0;
        }
        return s.intern();
    }
    
    private static String[] e(final Object o) {
        if (o instanceof StringPair) {
            final StringPair stringPair = (StringPair)o;
            final String first = stringPair.getFirst();
            final int index = first.indexOf(46);
            if (index == -1) {
                return new String[] { first, stringPair.getLast() };
            }
            return new String[] { intern(first.substring(0, index)), first, first.substring(index + 1), stringPair.getLast() };
        }
        else {
            final String s = (String)o;
            final int index2 = s.indexOf(46);
            if (index2 == -1) {
                return new String[] { s };
            }
            final int index3 = s.indexOf(46, index2 + 1);
            if (index3 == -1) {
                return new String[] { intern(s.substring(0, index2)), s.substring(index2) };
            }
            return new String[] { intern(s.substring(0, index2)), s.substring(0, index3), s.substring(index2 + 1, index3), intern(s.substring(index3)) };
        }
    }
    
    private void a(final Object o, final Object o2) throws MesonException {
        if (o == "") {
            return;
        }
        if (this.al && this.am.get_(o) != null) {
            System.err.println("Trace: " + o + "=" + o2);
            new Throwable().printStackTrace();
        }
        Object[] array = (Object[])this.get_(o);
        if (array == null) {
            array = new Object[] { "", null, new FastVector(0, 1) };
            this.put_(o, array);
            final String[] e = e(o);
            if (e.length == 2 && ".extends".equals(e[1])) {
                final String s = e[0];
                this.k.put(s, new FastVector(32, 32));
                final FastHashtable fastHashtable = new FastHashtable(97);
                this.l.put(s, fastHashtable);
                if (o2 != "") {
                    final FastVector fastVector = (FastVector)this.a(o2, (Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv);
                    for (int i = 0; i < fastVector.size(); ++i) {
                        final String s2 = (String)fastVector.get(i);
                        final FastHashtable fastHashtable2 = (FastHashtable)this.l.get(s2);
                        if (fastHashtable2 == null) {
                            throw new MesonException("Unknown base class " + s2);
                        }
                        for (long n = fastHashtable2.iterator(); n != -1L; n = fastHashtable2.advance(n)) {
                            final String key = fastHashtable2.getKey(n);
                            final String s3 = ((String[])fastHashtable2.getValue(n))[0];
                            final String[] array2 = (String[])fastHashtable.get(key);
                            if (array2 == null) {
                                fastHashtable.put(key, new String[] { s3 });
                            }
                            else {
                                boolean b = false;
                                for (int j = 0; j < array2.length; ++j) {
                                    if (array2[j] == s3) {
                                        b = true;
                                        break;
                                    }
                                }
                                if (!b) {
                                    final String[] array3 = new String[array2.length + 1];
                                    for (int k = 0; k < array2.length; ++k) {
                                        array3[k] = array2[k];
                                    }
                                    array3[array2.length] = s3;
                                    fastHashtable.put(key, array3);
                                }
                            }
                        }
                    }
                }
            }
            else if (e.length == 4) {
                final String s4 = e[0];
                final FastVector fastVector2 = (FastVector)this.k.get(s4);
                if (fastVector2 != null) {
                    final String s5 = e[1];
                    final String s6 = e[2];
                    final String s7 = e[3];
                    if (s6.equals("Class")) {
                        if (s7 != ".new") {
                            final FastHashtable fastHashtable3 = (FastHashtable)this.l.get(s4);
                            if (fastHashtable3.get(s7) == null) {
                                fastHashtable3.put(s7, new String[] { s4 });
                                for (int l = 0; l < fastVector2.size(); ++l) {
                                    this.a(this.newStringPair((String)fastVector2.get(l), s7), o2);
                                }
                            }
                        }
                    }
                    else {
                        boolean b2 = false;
                        boolean a = false;
                        if (this.d((Object)s5) != s5 && fastVector2.indexOf(s5) == -1) {
                            b2 = true;
                            a = this.a(s4, s6);
                            this.b(s4, s6);
                        }
                        final Object[] array4 = (Object[])((FastHashtable)this.l.get(s4)).get(s7);
                        if (array4 != null && array4[0] != s4) {
                            array = new Object[] { this.newStringPair(intern(array4[0] + "." + s6), s7) };
                            this.put_(o, array);
                        }
                        if (b2) {
                            this.a(s4, s6, "." + s6, s5, a);
                            this.c(s4, s6);
                            array = (Object[])this.get_(o);
                        }
                    }
                }
            }
        }
        if (array == Blackboard.au) {
            array = new Object[] { "", null, new FastVector(0, 1) };
            this.put_(o, array);
        }
        if (o2 == null) {
            return;
        }
        if (array.length == 1) {
            this.a(array[0], o2);
        }
        else {
            if (o2.equals(array[0])) {
                return;
            }
            array[0] = o2;
            array[1] = null;
            this.a(o, (FastVector)array[2]);
        }
    }
    
    private void f(final Object o) {
        final Object[] array = (Object[])this.get_(o);
        array[1] = null;
        this.a(o, (FastVector)array[2]);
    }
    
    private void a(final Object add, final FastVector fastVector) {
        if (fastVector == null) {
            return;
        }
        boolean b = false;
        for (int i = 0; i < fastVector.size(); ++i) {
            final Object value = fastVector.get(i);
            if (value == "__TRIGGER__") {
                b = true;
            }
            else {
                this.f(value);
            }
        }
        if (b) {
            fastVector.setSize(1);
            this.v.setAdd(add);
        }
        else {
            fastVector.setSize(0);
        }
    }
    
    public synchronized void a() {
        final long timeMicros = this.getTimeMicros();
        ++this.ah;
        final int ai = this.ai;
        try {
            if (this.ah >= this.ae) {
                try {
                    this.u = Thread.currentThread();
                    this.e();
                }
                finally {
                    this.u = null;
                }
                ++this.ai;
                if (this.ae > 1) {
                    this.notifyAll();
                }
            }
            else {
                while (this.ai == ai && this.ah < this.ae) {
                    this.wait(2000L);
                }
            }
        }
        catch (InterruptedException ex) {}
        finally {
            --this.ah;
        }
        this.tickTime = mix(this.tickTime, this.getTimeMicros() - timeMicros);
    }
    
    private void d() {
        this.s = this.r.copyAndFlush();
        for (int i = 0; i < this.s.size(); ++i) {
            final Object value = this.s.get(i);
            try {
                if (value != null) {
                    this.g(value);
                }
            }
            catch (Throwable t) {
                this.a("tick()", t);
            }
        }
        this.s.clear();
    }
    
    private void e() {
        final double time = this.av.getTime();
        double n = time - this.aa;
        if (this.aa == 0.0) {
            n = 0.0;
        }
        else if (n < 0.0 || n > 10.0) {
            this.ab = time - (this.aa - this.ab);
            n = 0.0;
        }
        else {
            if (n == 0.0) {
                n = 1.0E-6;
            }
            this.frameRate = 1.0 / (0.2 * n + 0.8 / this.frameRate);
        }
        this.aa = time;
        try {
            this.g(this.a(new Object[] { "Meson.clock", new Double(time - this.ab), "Meson.frameStep", new Double(n), "Meson.frameRate", new Double(this.frameRate) }, 6));
        }
        catch (MesonException ex) {
            this.a("tick()", ex);
        }
        this.d();
        double n2 = 0.0;
        final FastVector fastVector = (FastVector)this.k.get("Ticker");
        do {
            final double n3 = n2;
            n2 = Double.POSITIVE_INFINITY;
            for (int i = 0; i < fastVector.size(); ++i) {
                final String s = (String)fastVector.get(i);
                try {
                    final double doubleValue = (double)this.a(this.newStringPair(s, ".order"), (Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu, true);
                    if (doubleValue == n3) {
                        this.g(this.d(this.newStringPair(s, ".exec")));
                    }
                    else if (doubleValue > n3 && doubleValue < n2) {
                        n2 = doubleValue;
                    }
                }
                catch (Throwable t) {
                    this.a("tick() " + s, t);
                    try {
                        this.a(s + ".order", new Double(-1.0));
                    }
                    catch (Exception ex2) {}
                }
            }
        } while (n2 != Double.POSITIVE_INFINITY);
    }
    
    private void a(final String s, final Throwable t) {
        String message = t.getMessage();
        if (t instanceof MesonException) {
            final Throwable nested = ((MesonException)t).nested;
            if (nested != null) {
                nested.printStackTrace();
            }
        }
        else {
            t.printStackTrace();
            message = "Internal Error";
        }
        System.err.println(s + "\n" + message);
    }
    
    private FastVector a(final String s) {
        final Object[] array = (Object[])this.get(s, ".extends");
        if (array == null || array[0] == "") {
            return new FastVector(0, 0);
        }
        if (array[0] instanceof FastVector) {
            return (FastVector)array[0];
        }
        final FastVector fastVector = new FastVector(1, 0);
        fastVector.add(array[0]);
        return fastVector;
    }
    
    private boolean a(final String s, final String s2) throws MesonException {
        boolean b = false;
        final FastVector a = this.a(s);
        for (int i = 0; i < a.size(); ++i) {
            if (this.a((String)a.get(i), s2)) {
                b = true;
            }
        }
        final String intern = intern(s + "." + s2);
        final FastVector fastVector = (FastVector)this.k.get(s);
        if (fastVector.indexOf(intern) == -1) {
            fastVector.add(intern);
            this.a(intern, (Object)intern);
        }
        else {
            b = true;
        }
        return b;
    }
    
    private void b(final String s, final String s2) throws MesonException {
        final FastHashtable fastHashtable = (FastHashtable)this.l.get(s);
        if (fastHashtable == null) {
            return;
        }
        final String string = "." + s2;
        for (long n = fastHashtable.iterator(); n != -1L; n = fastHashtable.advance(n)) {
            final String key = fastHashtable.getKey(n);
            final String[] array = (String[])fastHashtable.getValue(n);
            final StringPair stringPair = this.newStringPair(intern(array[0] + string), key);
            if (array.length > 1) {
                for (int i = 1; i < array.length; ++i) {
                    this.put(this.newStringPair(intern(array[i] + string), key), new Object[] { stringPair });
                }
            }
            else if (this.get(stringPair) == null) {
                this.put(stringPair, Blackboard.au);
            }
        }
    }
    
    private void a(final String s, final String s2, final String s3, final String s4, final boolean b) throws MesonException {
        final FastVector a = this.a(s);
        for (int i = a.size() - 1; i >= 0; --i) {
            this.a((String)a.get(i), s2, s3, s4, b);
        }
        final FastHashtable fastHashtable = (FastHashtable)this.l.get(s);
        if (fastHashtable != null) {
            final String intern = intern(s + ".Class");
            final String intern2 = intern(s + s3);
            for (long n = fastHashtable.iterator(); n != -1L; n = fastHashtable.advance(n)) {
                final String key = fastHashtable.getKey(n);
                final Object[] array = (Object[])this.get(intern2, key);
                if (b || s4 == intern2 || array == null || array == Blackboard.au) {
                    final Object[] array2 = (Object[])this.get(intern, key);
                    if (array2 != null) {
                        this.a(this.newStringPair(intern2, key), this.a(array2[0], s + s3, s2));
                    }
                }
            }
        }
    }
    
    private void c(final String s, final String s2) throws MesonException {
        final FastVector a = this.a(s);
        for (int i = 0; i < a.size(); ++i) {
            this.c((String)a.get(i), s2);
        }
        final Object d = this.d((Object)(s + ".Class.new"));
        if (d != "") {
            final Object t = this.t;
            try {
                this.t = null;
                this.a(this.a(d, s + "." + s2, s2), (Class)null);
            }
            finally {
                this.t = t;
            }
        }
    }
    
    public Object getLocal(final String s, final Class clazz) {
        try {
            Object value = this.a('#').get(s);
            if (value == null) {
                value = "";
            }
            if (clazz == null || value.getClass() == clazz) {
                return value;
            }
            return this.a(value, clazz);
        }
        catch (MesonException ex) {
            this.a("Get #" + s, ex);
            ex.printStackTrace();
            return null;
        }
    }
    
    public void setLocal(final String s, final Object o) {
        try {
            this.a('#').put(s, o);
        }
        catch (MesonException ex) {
            this.a("Set #" + s, ex);
            ex.printStackTrace();
        }
    }
    
    public Object getGlobal(final Object o, final Class clazz) {
        try {
            if (Thread.currentThread() == this.u) {
                return this.a(o, clazz, true);
            }
            synchronized (this) {
                return this.a(o, clazz, true);
            }
        }
        catch (MesonException ex) {
            this.a("Get " + o, ex);
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean globalExists(final String s) {
        try {
            if (this.get(s) != null) {
                return true;
            }
            final String[] e = e(s);
            if (e.length != 4) {
                return false;
            }
            final String s2 = e[0];
            final FastVector fastVector = (FastVector)this.k.get(s2);
            if (fastVector == null) {
                return false;
            }
            final String s3 = e[1];
            final String s4 = e[2];
            final String s5 = e[3];
            if (this.d((Object)s3) != s3 && fastVector.indexOf(s3) == -1) {
                return false;
            }
            final Object[] array = (Object[])((FastHashtable)this.l.get(s2)).get(s5);
            if (array != null && array[0] != s2) {
                this.put(this.newStringPair(s3, s5), new Object[] { this.newStringPair(intern(array[0] + "." + s4), s5) });
                return true;
            }
            return false;
        }
        catch (MesonException ex) {
            return false;
        }
    }
    
    public synchronized void setGlobal(final Object o, final Object o2) {
        try {
            this.g(new Object[] { { ' ', ' ', '=' }, o, o2 });
        }
        catch (MesonException ex) {
            this.a("Set " + o, ex);
        }
    }
    
    public synchronized void setGlobals(final Object[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        this.setGlobals(array, array.length);
    }
    
    public synchronized void setGlobals(final Object[] array, final int n) {
        if (array == null || n == 0) {
            return;
        }
        try {
            this.g(this.a(array, n));
        }
        catch (MesonException ex) {
            this.a("Set globals", ex);
        }
    }
    
    public void setGlobalDirect(final String s, final Object o) throws MesonException {
        this.a(s, o);
    }
    
    private synchronized Object g(final Object o) throws MesonException {
        if (this.a2) {
            return this.a(o, (Class)null);
        }
        try {
            this.a2 = true;
            final Object a = this.a(o, (Class)null);
            for (int i = 0; i < 2; ++i) {
                final FastVector v = this.v;
                this.v = this.w;
                this.w = v;
                for (int j = 0; j < v.size(); ++j) {
                    final Object value = v.get(j);
                    final StringPair stringPair = (StringPair)this.x.get_(value);
                    try {
                        this.a(value, null, true);
                        this.a(stringPair, null, false);
                        if (Blackboard.an) {
                            Thread.yield();
                        }
                    }
                    catch (MesonException ex) {
                        this.a(stringPair, "");
                        this.a("Exception evaluating trigger " + value + " after " + (Object)this.t(o), ex);
                    }
                }
                v.setSize(0);
            }
            if (Blackboard.an) {
                Thread.yield();
            }
            return a;
        }
        finally {
            this.a2 = false;
        }
    }
    
    public synchronized Object invoke(final Object o, final Object o2) {
        if (this.u != null) {
            return this.b(o, o2);
        }
        try {
            this.u = Thread.currentThread();
            return this.b(o, o2);
        }
        finally {
            this.u = null;
        }
    }
    
    private Object b(final Object o, final Object o2) {
        final int p2 = this.p;
        try {
            this.a('^').put("arg", o2);
            ++this.p;
            return this.g(this.d(o));
        }
        catch (MesonException ex) {
            this.a("invoke " + o + "(" + o2 + ")", ex);
            return null;
        }
        finally {
            this.p = p2;
        }
    }
    
    public synchronized Object evaluate(final Object o, final Class clazz) throws MesonException {
        return this.a(o, clazz);
    }
    
    public synchronized FastVector evaluate(final FastVector fastVector, final Class[] array) throws MesonException {
        final FastVector fastVector2 = new FastVector(fastVector.size(), 0);
        for (int i = 0; i < fastVector.size(); ++i) {
            Object o = fastVector.get(i);
            if (o != null) {
                Class<?> clazz;
                if (i < array.length) {
                    clazz = (Class<?>)array[i];
                }
                else {
                    clazz = null;
                }
                if (o.getClass() == Blackboard.aq) {
                    o = this.a(o, clazz);
                }
                else if (clazz != null && o.getClass() != clazz) {
                    o = this.a(o, clazz);
                    if (o != null) {
                        fastVector.add(i, o);
                    }
                }
            }
            fastVector2.add(o);
        }
        return fastVector2;
    }
    
    private Object c(Object d, Object d2) throws MesonException {
        if (d instanceof String || d instanceof StringPair) {
            d = this.d(d);
        }
        if (d2 instanceof String || d2 instanceof StringPair) {
            d2 = this.d(d2);
        }
        if (d.getClass() != Blackboard.aq) {
            d = new Object[] { { ' ' }, d };
        }
        if (d2.getClass() != Blackboard.aq) {
            d2 = new Object[] { { ' ' }, d2 };
        }
        final Object[] array = (Object[])d;
        final Object[] array2 = (Object[])d2;
        final Object[] array3 = new Object[array.length + array2.length - 1];
        final char[] array4 = (char[])array[0];
        final char[] array5 = (char[])array2[0];
        final char[] array6 = new char[array4.length + array5.length + 1];
        System.arraycopy(array4, 0, array6, 0, array4.length);
        array6[array4.length] = ';';
        System.arraycopy(array5, 0, array6, array4.length + 1, array5.length);
        array3[0] = array6;
        System.arraycopy(array, 1, array3, 1, array.length - 1);
        System.arraycopy(array2, 1, array3, array.length, array2.length - 1);
        return array3;
    }
    
    public void a(final Object o) {
        this.y[this.z++] = o;
    }
    
    public Object b() {
        final Object[] y = this.y;
        final int z = this.z - 1;
        this.z = z;
        final Object o = y[z];
        this.y[this.z] = "";
        return o;
    }
    
    private Object a(Object b, Class clazz) throws MesonException {
        if (b.getClass() == clazz) {
            return b;
        }
        final Object o = b;
        final Class<?> clazz2 = clazz;
        clazz = null;
        final int z = this.z;
        try {
            if (++this.q > 2048) {
                throw new MesonException("Runaway Evaluation");
            }
            while (true) {
                Object d = null;
                if (b.getClass() == Blackboard.aq) {
                    final Object[] array = (Object[])b;
                    final char[] array2 = (char[])array[0];
                    int n = 1;
                    for (int i = 0; i < array2.length; ++i) {
                        final char c = array2[i];
                        final boolean b2 = i == array2.length - 1;
                        final boolean b3 = !b2 && array2[i + 1] == ';';
                        switch (c) {
                            case 32: {
                                this.a(array[n++]);
                                break;
                            }
                            case 112: {
                                final Object b4 = this.b();
                                final Object b5 = this.b();
                                if (b3) {
                                    ++i;
                                }
                                else {
                                    this.a(b4);
                                }
                                this.a('#').put(b5.toString(), b4);
                                break;
                            }
                            case 99: {
                                final Object b6 = this.b();
                                final Object b7 = this.b();
                                if (b3) {
                                    ++i;
                                }
                                else {
                                    this.a(b6);
                                }
                                this.a('^').put(b7.toString(), b6);
                                break;
                            }
                            case 97: {
                                final Object b8 = this.b();
                                final Object b9 = this.b();
                                final Object b10 = this.b();
                                final Object a = this.a(b10, null, true);
                                if (!(a instanceof FastVector)) {
                                    throw new MesonException(b10 + " is not an array");
                                }
                                final FastVector fastVector = (FastVector)a;
                                final int j = (int)(0.5 + this.h(b9));
                                while (j >= fastVector.size()) {
                                    fastVector.add("");
                                }
                                fastVector.add(j, b8);
                                this.f(b10);
                                if (b3) {
                                    ++i;
                                }
                                else {
                                    this.a(b8);
                                }
                                break;
                            }
                            case 61: {
                                final Object b11 = this.b();
                                final Object b12 = this.b();
                                if (b3) {
                                    ++i;
                                }
                                else {
                                    this.a(b11);
                                }
                                this.a(b12, b11);
                                break;
                            }
                            case 35:
                            case 94: {
                                Object value = this.a(c).get(this.b().toString());
                                if (value == null) {
                                    value = "";
                                }
                                this.a(value);
                                break;
                            }
                            case 68: {
                                clazz = (Class<?>)((Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu);
                            }
                            case 64: {
                                final Object b13 = this.b();
                                if (b2 && b13.equals(this.t)) {
                                    d = this.d(b13);
                                }
                                else if (b13 instanceof String || b13 instanceof StringPair) {
                                    this.a(this.a(b13, clazz, true));
                                }
                                else if (b2) {
                                    d = b13;
                                }
                                else {
                                    this.a(this.a(b13, (Class)null));
                                }
                                clazz = null;
                                break;
                            }
                            case 69: {
                                clazz = (Class<?>)((Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu);
                            }
                            case 172: {
                                this.a(this.a(this.newStringPair(this.b().toString(), "." + this.b()), clazz, true));
                                clazz = null;
                                break;
                            }
                            case 100: {
                                this.a(this.newStringPair(this.b().toString(), "." + this.b()));
                                break;
                            }
                            case 402: {
                                final String string = this.b().toString();
                                final Gluon gluon = (Gluon)this.m.get(string);
                                if (gluon == null) {
                                    throw new MesonException("The function " + string + " is not built-in");
                                }
                                this.a(gluon.builtIn(this, string));
                                break;
                            }
                            case 33: {
                                this.a((Object)((this.b() == "true") ? "false" : "true"));
                                break;
                            }
                            case 126: {
                                final String k = this.i(this.b());
                                final String l = this.i(this.b());
                                if (l == "") {
                                    this.a((Object)k);
                                }
                                else if (k == "") {
                                    this.a((Object)l);
                                }
                                else {
                                    this.a((Object)new StringBuffer(l.length() + k.length()).append(l).append(k).toString());
                                }
                                break;
                            }
                            case 38: {
                                final Object b14 = this.b();
                                if (this.b() != "true") {
                                    this.a((Object)"false");
                                }
                                else {
                                    this.a((Object)((this.a(b14, (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw) == "true") ? "true" : "false"));
                                }
                                break;
                            }
                            case 124: {
                                final Object b15 = this.b();
                                if (this.b() == "true") {
                                    this.a((Object)"true");
                                }
                                else {
                                    this.a((Object)((this.a(b15, (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw) == "true") ? "true" : "false"));
                                }
                                break;
                            }
                            case 101: {
                                this.a(this.c(this.b(), this.b()));
                                break;
                            }
                            case 43: {
                                final Object b16 = this.b();
                                final Object b17 = this.b();
                                final Class<? extends FastVector> class1 = ((FastVector)b17).getClass();
                                final Class<? extends FastVector> class2 = ((FastVector)b16).getClass();
                                if (class1 == ((Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv) || class2 == ((Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv)) {
                                    FastVector fastVector2;
                                    if (class1 == ((Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv)) {
                                        fastVector2 = (FastVector)b17;
                                    }
                                    else {
                                        fastVector2 = new FastVector(1, 1);
                                        fastVector2.add(b17);
                                    }
                                    FastVector fastVector3;
                                    if (class2 == ((Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv)) {
                                        fastVector3 = (FastVector)b16;
                                    }
                                    else {
                                        fastVector3 = new FastVector(1, 1);
                                        fastVector3.add(b16);
                                    }
                                    this.a(fastVector2.union(fastVector3));
                                }
                                else {
                                    this.a(new Double(this.h(b17) + this.h(b16)));
                                }
                                break;
                            }
                            case 45: {
                                this.a(new Double(this.h(this.b()) - this.h(this.b())));
                                break;
                            }
                            case 42: {
                                this.a(new Double(this.h(this.b()) * this.h(this.b())));
                                break;
                            }
                            case 47: {
                                this.a(new Double(this.h(this.b()) / this.h(this.b())));
                                break;
                            }
                            case 37: {
                                this.a(new Double(this.j(this.b()) % this.j(this.b())));
                                break;
                            }
                            case 8776:
                            case 8800: {
                                Object o2 = this.b();
                                Object o3 = this.b();
                                final Class<? extends Double> class3 = ((Double)o3).getClass();
                                final Class<? extends FastVector> class4 = ((FastVector)o2).getClass();
                                if (class3 == ((Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu) || class4 == ((Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu)) {
                                    if (class3 != ((Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu)) {
                                        o3 = this.l(o3);
                                    }
                                    if (class4 != ((Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu)) {
                                        o2 = this.l(o2);
                                    }
                                    final double n2 = (double)o3 - (double)o2;
                                    this.a((Object)(((n2 == 0.0 || (n2 < 9.999999747378752E-6 && n2 > -9.999999747378752E-6)) == (c == '\u2248')) ? "true" : "false"));
                                }
                                else if (class3 == ((Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv) && class4 == ((Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv)) {
                                    this.a((Object)((this.a((FastVector)o3, (FastVector)o2) == (c == '\u2248')) ? "true" : "false"));
                                }
                                else {
                                    this.a((Object)((o3.equals(o2) == (c == '\u2248')) ? "true" : "false"));
                                }
                                break;
                            }
                            case 60:
                            case 8805: {
                                this.a((Object)((this.h(this.b()) < this.h(this.b()) == (c == '<')) ? "true" : "false"));
                                break;
                            }
                            case 62:
                            case 8804: {
                                this.a((Object)((this.h(this.b()) > this.h(this.b()) == (c == '>')) ? "true" : "false"));
                                break;
                            }
                            case 8746: {
                                this.a(this.k(this.b()).setUnion(this.k(this.b())));
                                break;
                            }
                            case 8726: {
                                this.a(this.k(this.b()).setMinus(this.k(this.b())));
                                break;
                            }
                            case 63: {
                                final Object b18 = this.b();
                                if (this.b() == "true") {
                                    if (b2) {
                                        d = b18;
                                    }
                                    else {
                                        this.a(this.a(b18, (Class)null));
                                    }
                                }
                                else {
                                    this.a((Object)"false");
                                }
                                break;
                            }
                            case 58: {
                                final Object b19 = this.b();
                                final Object b20 = this.b();
                                if (this.b() == "true") {
                                    if (b2) {
                                        d = b20;
                                    }
                                    else {
                                        this.a(this.a(b20, (Class)null));
                                    }
                                }
                                else if (b2) {
                                    d = b19;
                                }
                                else {
                                    this.a(this.a(b19, (Class)null));
                                }
                                break;
                            }
                            case 40: {
                                final Object b21 = this.b();
                                final Object b22 = this.b();
                                final int p = this.p;
                                try {
                                    final String string2 = b22 + "\u03b4";
                                    if (this.get(string2) != null) {
                                        ++this.p;
                                        final Object a2 = this.a(string2, (Class)null, false);
                                        --this.p;
                                        if (b21 == ")") {
                                            this.a('^').put("arg", a2);
                                        }
                                        else {
                                            this.a('^').put("arg", this.a(b21, (Class)null));
                                        }
                                    }
                                    else {
                                        this.a('^').put("arg", this.a(b21, (Class)null));
                                    }
                                    ++this.p;
                                    this.a(this.a(b22, clazz, false));
                                }
                                finally {
                                    if (this.p != p + 1) {
                                        throw new MesonException("callStackPtr (" + this.p + ") was modified unexpectedly");
                                    }
                                    this.p = p;
                                }
                                break;
                            }
                            case 44:
                            case 59: {
                                this.b();
                                break;
                            }
                            case 91: {
                                final Object b23 = this.b();
                                final Object b24 = this.b();
                                if (!(b24 instanceof FastVector)) {
                                    throw new MesonException("Expected array.  Found: " + (Object)this.t(b24));
                                }
                                final FastVector fastVector4 = (FastVector)b24;
                                final int n3 = (int)(this.h(b23) + 0.5);
                                if (n3 > fastVector4.size()) {
                                    throw new MesonException("Array index " + n3 + " out of bounds 0..." + (fastVector4.size() - 1));
                                }
                                this.a(fastVector4.get(n3));
                                break;
                            }
                            case 93: {
                                final Object b25 = this.b();
                                final FastVector fastVector5 = new FastVector((int)this.b(), 8);
                                fastVector5.add(b25);
                                this.a(fastVector5);
                                break;
                            }
                            case 46: {
                                final Object b26 = this.b();
                                final Object b27 = this.b();
                                ((FastVector)b27).add(b26);
                                this.a(b27);
                                break;
                            }
                            default: {
                                throw new MesonException("Syntax Error (operator '" + c + "')");
                            }
                        }
                    }
                    if (d == null) {
                        b = this.b();
                    }
                }
                if (d == null) {
                    clazz = clazz2;
                    if (b == null) {
                        b = "";
                    }
                    if (clazz == null) {
                        return b;
                    }
                    final Class<?> class5 = b.getClass();
                    if (clazz == ((Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv) && class5 != clazz) {
                        final FastVector fastVector6 = new FastVector(8, 8);
                        fastVector6.add(b);
                        return fastVector6;
                    }
                    if (class5 == clazz) {
                        return b;
                    }
                    if (class5 == Blackboard.aq) {
                        return this.a(b, clazz);
                    }
                    if (clazz == ((Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw)) {
                        return b.toString();
                    }
                    if (clazz == ((Blackboard.bx == null) ? (Blackboard.bx = class$("java.lang.Integer")) : Blackboard.bx)) {
                        return this.b(b.toString());
                    }
                    if (clazz == ((Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu)) {
                        return this.l(b);
                    }
                    if (clazz == Blackboard.BYTE_ARRAY_CLASS) {
                        return this.n(b);
                    }
                    if (b == "") {
                        return null;
                    }
                    return b;
                }
                else {
                    b = d;
                    clazz = null;
                }
            }
        }
        catch (MesonException ex) {
            final String message = ex.getMessage();
            String string3 = "\n     " + (Object)this.t(o);
            if (message.startsWith("Runaway") && message.lastIndexOf(string3) > 100) {
                if (message.endsWith("...")) {
                    throw ex;
                }
                string3 = "\n...";
            }
            throw new MesonException(message + string3, ex.nested);
        }
        catch (Throwable t) {
            throw new MesonException(this.t(o).toString(), t);
        }
        finally {
            if (z != this.z) {
                this.z = z;
            }
            --this.q;
        }
    }
    
    private boolean a(final FastVector fastVector, final FastVector fastVector2) {
        if (fastVector.size() != fastVector2.size()) {
            return false;
        }
        for (int i = 0; i < fastVector.size(); ++i) {
            if (!fastVector.get(i).equals(fastVector2.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    private void a(final PrintStream printStream, final String s, final boolean b) {
        if (b) {
            printStream.println("---------------\nBlackboard Dump " + s + "\n---------------\n");
        }
        for (long n = this.iterator(); n != -1L; n = this.advance(n)) {
            final String key = this.getKey(n);
            if (b) {
                if (!key.startsWith(s)) {
                    continue;
                }
            }
            else if (!key.equals(s)) {
                continue;
            }
            final Object[] array = (Object[])this.getValue(n);
            if (array.length == 1) {
                printStream.print(key + " --> ");
                this.a(printStream, array[0].toString(), false);
            }
            else {
                final StringBuffer sb = new StringBuffer(key);
                sb.append(" ::= ").append((Object)this.t(array[0]));
                if (array[1] != null) {
                    sb.append(" <<").append((Object)this.t(array[1])).append(">>");
                }
                printStream.println(sb.toString());
                final FastVector fastVector = (FastVector)array[2];
                if (fastVector != null && fastVector.size() > 0) {
                    printStream.println("    Derived values:");
                    for (int i = 0; i < fastVector.size(); ++i) {
                        printStream.println("      " + fastVector.get(i));
                    }
                }
            }
        }
    }
    
    public void interceptGetMethodCall(final String s, final String s2, final String s3) {
        if (this.at == null) {
            this.at = new FastHashtable(65);
        }
        this.at.put(s + "." + s2, s3);
    }
    
    public void declareStatic(final String[] array) throws MesonException {
        for (int i = 0; i < array.length; ++i) {
            final Object[] array2 = (Object[])this.get(array[i]);
            if (array2 == null) {
                throw new MesonException("Attempt to declare unknown attribute " + array[i] + " static.");
            }
            array2[2] = null;
        }
    }
    
    public void init(final Blackboard blackboard) {
    }
    
    public void reinit(final Blackboard blackboard) {
    }
    
    public void destroy(final Blackboard blackboard) {
    }
    
    public void restoring(final Blackboard blackboard) {
    }
    
    public Object builtIn(final Blackboard blackboard, final String s) throws Exception {
        if (s == "foreach") {
            final FastVector fastVector = (FastVector)this.getLocal("in", (Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv);
            if (fastVector.size() == 0) {
                return fastVector;
            }
            final String s2 = (String)this.getLocal("elem", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
            final Object local = this.getLocal("do", null);
            for (int i = 0; i < fastVector.size(); ++i) {
                this.setLocal(s2, fastVector.get(i));
                this.a(local, (Class)null);
            }
            return fastVector;
        }
        else {
            if (s == "try") {
                final Object local2 = this.getLocal("exec", null);
                final Object local3 = this.getLocal("catch", null);
                try {
                    return this.a(local2, (Class)null);
                }
                catch (Exception ex) {
                    this.setLocal("exception", ex.getClass().getName());
                    this.setLocal("message", ex.getMessage());
                    this.setLocal("javaException", ex);
                    return this.a(local3, (Class)null);
                }
            }
            if (s == "Meson.length") {
                return new Double(((FastVector)this.getLocal("arg", (Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv)).size());
            }
            if (s == "Meson.assert") {
                if (this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw) != "true") {
                    throw new MesonException("Assertion Failed");
                }
                return "";
            }
            else {
                if (s == "Meson.throw") {
                    throw new MesonException((String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw));
                }
                if (s == "Meson.dump") {
                    this.a(System.out, (String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw), true);
                    return "";
                }
                if (s == "Meson.log") {
                    System.out.println((String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw));
                    return "";
                }
                if (s == "Meson.logAction") {
                    MesonApplet.h = (String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                    return "";
                }
                if (s == "Meson.trace") {
                    final String s3 = (String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                    System.err.println("Tracing writes to " + s3);
                    this.al = true;
                    if (this.am == null) {
                        this.am = new FastHashtable(65);
                    }
                    this.am.put(s3, s3);
                    return "";
                }
                if (s == "Meson.static") {
                    final FastVector fastVector2 = (FastVector)this.getLocal("arg", (Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv);
                    for (int j = 0; j < fastVector2.size(); ++j) {
                        final Object[] array = (Object[])this.get_(fastVector2.get(j));
                        if (array == null) {
                            throw new MesonException("Attempt to declare unknown attribute " + fastVector2.get(j) + " static.");
                        }
                        array[2] = null;
                    }
                }
                else {
                    if (s == "Meson.echo") {
                        System.out.println(this.t(this.getLocal("arg", null)).toString());
                        return "";
                    }
                    if (s == "Meson.newInstance") {
                        return Class.forName((String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw)).newInstance();
                    }
                    if (s == "Meson.getMethod" || s == "Meson.getConstructor") {
                        String string = (String)this.getLocal("class", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                        final String s4 = (String)this.getLocal("method", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                        if (this.at != null) {
                            final Object value = this.at.get(string + "." + s4);
                            if (value != null) {
                                string = value.toString();
                            }
                        }
                        final Class<?> forName = Class.forName(string);
                        final FastVector fastVector3 = (FastVector)this.getLocal("args", (Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv);
                        final Class[] array2 = new Class[fastVector3.size()];
                        for (int k = 0; k < fastVector3.size(); ++k) {
                            final String s5 = (String)fastVector3.get(k);
                            if (s5 == "float") {
                                array2[k] = Float.TYPE;
                            }
                            else if (s5 == "double") {
                                array2[k] = Double.TYPE;
                            }
                            else if (s5 == "int") {
                                array2[k] = Integer.TYPE;
                            }
                            else if (s5 == "short") {
                                array2[k] = Short.TYPE;
                            }
                            else if (s5 == "long") {
                                array2[k] = Long.TYPE;
                            }
                            else if (s5 == "boolean") {
                                array2[k] = Boolean.TYPE;
                            }
                            else if (s5 == "char") {
                                array2[k] = Character.TYPE;
                            }
                            else {
                                array2[k] = Class.forName(s5);
                            }
                        }
                        if (s == "Meson.getMethod") {
                            return forName.getMethod(s4, (Class[])array2);
                        }
                        return forName.getConstructor((Class<?>[])array2);
                    }
                    else {
                        if (s == "Meson.callMethod" || s == "Meson.callConstructor") {
                            Method method = null;
                            Constructor<Object> constructor = null;
                            Class<?>[] array3;
                            if (s == "Meson.callMethod") {
                                method = (Method)this.getLocal("method", (Blackboard.by == null) ? (Blackboard.by = class$("java.lang.reflect.Method")) : Blackboard.by);
                                array3 = method.getParameterTypes();
                            }
                            else {
                                constructor = (Constructor<Object>)this.getLocal("constructor", (Blackboard.bz == null) ? (Blackboard.bz = class$("java.lang.reflect.Constructor")) : Blackboard.bz);
                                array3 = constructor.getParameterTypes();
                            }
                            final FastVector fastVector4 = (FastVector)this.getLocal("args", (Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv);
                            final Object[] array4 = new Object[array3.length];
                            Object o = null;
                            int n = 0;
                            try {
                                for (int l = 0; l < fastVector4.size(); ++l) {
                                    final Object evaluate = this.evaluate(fastVector4.get(l), null);
                                    if (o == null && fastVector4.size() == array3.length + 1) {
                                        o = evaluate;
                                    }
                                    else {
                                        array4[n] = this.a(array3[n], evaluate);
                                        ++n;
                                    }
                                }
                                if (s == "Meson.callConstructor") {
                                    return constructor.newInstance(array4);
                                }
                                Object invoke = null;
                                try {
                                    invoke = method.invoke(o, array4);
                                }
                                catch (SecurityException ex3) {
                                    System.err.println("Unable to execute " + method + " for security reasons");
                                }
                                if (invoke != null && invoke instanceof Boolean) {
                                    return invoke ? "true" : "false";
                                }
                                return invoke;
                            }
                            catch (Exception ex2) {
                                System.err.println(fastVector4.size() + " " + array3.length);
                                System.err.println("Calling " + method + " on " + o + " with ");
                                for (int n2 = 0; n2 < array4.length; ++n2) {
                                    System.err.println("  (" + array3[n2] + ")" + array4[n2]);
                                }
                                throw ex2;
                            }
                        }
                        if (s == "Meson.parse") {
                            return this.parse((String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw));
                        }
                        if (s == "Meson.load") {
                            this.load("Meson.load", (String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw));
                            return "";
                        }
                        if (s == "Meson.include") {
                            final String s6 = (String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                            String s7 = (String)this.getGlobal("Meson.currentDataSource", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                            if ("".equals(s7)) {
                                s7 = (String)this.getGlobal("Meson.documentBase", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                            }
                            try {
                                if (s7.indexOf(58) != -1 || s6.indexOf(58) != -1) {
                                    final URL url = new URL(new URL(s7), s6);
                                    final InputStream inputStream = url.openConnection().getInputStream();
                                    this.load(url.toString(), inputStream);
                                    inputStream.close();
                                }
                                else {
                                    final String string2 = s7 + "." + s6;
                                    final byte[] array5 = (byte[])this.getGlobal(string2, Blackboard.BYTE_ARRAY_CLASS);
                                    if (array5 == null) {
                                        throw new MesonException("Included file " + s6 + " must appear before main files in JAR");
                                    }
                                    this.load(string2, new ByteArrayInputStream(array5));
                                }
                            }
                            catch (Throwable t) {
                                throw new MesonException("Error reading included file " + s6, t);
                            }
                            return "";
                        }
                        if (s == "Meson.gluon") {
                            final String s8 = (String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                            final Gluon gluon = (Gluon)this.n.get(s8);
                            if (gluon != null) {
                                return gluon;
                            }
                            try {
                                final Gluon gluon2 = (Gluon)Class.forName(s8).newInstance();
                                this.n.put(s8, gluon2);
                                gluon2.init(this);
                                return gluon2;
                            }
                            catch (Throwable t2) {
                                t2.printStackTrace();
                                System.err.println("Unable to load class " + s8 + " check archive parameter");
                                return "";
                            }
                        }
                        if (s == "Meson.newTrigger") {
                            final String add = (String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                            ((FastVector)((Object[])this.get(add))[2]).add(0, "__TRIGGER__");
                            this.x.put(add, this.newStringPair(intern(add.substring(0, add.length() - 5)), ".update"));
                            this.v.setAdd(add);
                        }
                        else {
                            if (s == "Meson.showDocument") {
                                final Applet applet = (Applet)blackboard.getGlobal((String)blackboard.getLocal("applet", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw) + ".javaApplet", (Blackboard.b0 == null) ? (Blackboard.b0 = class$("java.applet.Applet")) : Blackboard.b0);
                                final String s9 = (String)blackboard.getLocal("url", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                                final String s10 = (String)blackboard.getLocal("target", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                                if (applet != null && s9 != null && s10 != null) {
                                    applet.getAppletContext().showDocument(new URL(applet.getDocumentBase(), s9), s10);
                                }
                                return "";
                            }
                            if (s == "Meson.showStatus") {
                                final Applet applet2 = (Applet)blackboard.getGlobal((String)blackboard.getLocal("applet", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw) + ".javaApplet", (Blackboard.b0 == null) ? (Blackboard.b0 = class$("java.applet.Applet")) : Blackboard.b0);
                                final String s11 = (String)blackboard.getLocal("status", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                                if (applet2 != null) {
                                    applet2.getAppletContext().showStatus(s11);
                                }
                                return "";
                            }
                            if (s == "Meson.addContextMenu") {
                                final MesonApplet mesonApplet = (MesonApplet)blackboard.getGlobal((String)blackboard.getLocal("applet", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw) + ".javaApplet", (Blackboard.bt == null) ? (Blackboard.bt = class$("com.kaon.meson.MesonApplet")) : Blackboard.bt);
                                final String s12 = (String)blackboard.getLocal("label", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                                if (mesonApplet != null) {
                                    mesonApplet.a(s12);
                                }
                                return "";
                            }
                            if (s == "Meson.clearContextMenu") {
                                final MesonApplet mesonApplet2 = (MesonApplet)blackboard.getGlobal((String)blackboard.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw) + ".javaApplet", (Blackboard.bt == null) ? (Blackboard.bt = class$("com.kaon.meson.MesonApplet")) : Blackboard.bt);
                                if (mesonApplet2 != null) {
                                    mesonApplet2.e();
                                }
                                return "";
                            }
                            if (s == "Meson.unique") {
                                return ("U" + this.ad++).intern();
                            }
                            if (s == "Meson.unicode") {
                                final String s13 = (String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw);
                                if (s13.length() > 1) {
                                    return new Double(-1.0);
                                }
                                if (s13.length() == 0) {
                                    return new Double(0.0);
                                }
                                return new Double(s13.charAt(0));
                            }
                            else {
                                if (s == "Meson.getProperty") {
                                    return System.getProperty((String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw));
                                }
                                if (s == "Meson.checkpoint") {
                                    return this.checkpoint();
                                }
                                if (s == "Meson.restore") {
                                    final Object local4 = blackboard.getLocal("arg", null);
                                    for (long n3 = this.n.iterator(); n3 != -1L; n3 = this.n.advance(n3)) {
                                        ((Gluon)this.n.getValue(n3)).restoring(this);
                                    }
                                    this.r.copyAndFlush();
                                    if (this.s != null) {
                                        this.s.clear();
                                    }
                                    final Double n4 = (Double)blackboard.getGlobal("Meson.clock", (Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu);
                                    final Double n5 = (Double)blackboard.getGlobal("Meson.twistDisplay", (Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu);
                                    final FastVector fastVector5 = new FastVector(this.as.size(), 0);
                                    for (int n6 = 0; n6 < this.as.size(); ++n6) {
                                        fastVector5.add(this.saveExpr((String)this.as.get(n6)));
                                    }
                                    this.restore(local4);
                                    this.setGlobal("Meson.clock", n4);
                                    this.setGlobal("Meson.twistDisplay", n5);
                                    for (int n7 = 0; n7 < fastVector5.size(); ++n7) {
                                        this.g(fastVector5.get(n7));
                                    }
                                    final FastVector instances = this.getInstances("Trigger");
                                    for (int n8 = 0; n8 < instances.size(); ++n8) {
                                        this.v.setAdd(this.newStringPair((String)instances.get(n8), ".test"));
                                    }
                                    this.notifyAll();
                                    return local4;
                                }
                                if (s == "Meson.saveExpr") {
                                    return this.saveExpr((String)this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw));
                                }
                                if (s == "Meson.preserveExpr") {
                                    this.as.add(this.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw));
                                    return "";
                                }
                                if (s == "Meson.moveToDialog") {
                                    ((MesonApplet)blackboard.getGlobal((String)blackboard.getLocal("applet", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw) + ".javaApplet", (Blackboard.bt == null) ? (Blackboard.bt = class$("com.kaon.meson.MesonApplet")) : Blackboard.bt)).a((String)blackboard.getLocal("title", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw), (int)blackboard.getLocal("width", (Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu), (int)blackboard.getLocal("height", (Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu));
                                }
                                else {
                                    if (s == "Meson.inDialog") {
                                        return ((MesonApplet)blackboard.getGlobal((String)blackboard.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw) + ".javaApplet", (Blackboard.bt == null) ? (Blackboard.bt = class$("com.kaon.meson.MesonApplet")) : Blackboard.bt)).a4 ? "true" : "false";
                                    }
                                    if (s == "Meson.closeDialog") {
                                        ((MesonApplet)blackboard.getGlobal((String)blackboard.getLocal("arg", (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw) + ".javaApplet", (Blackboard.bt == null) ? (Blackboard.bt = class$("com.kaon.meson.MesonApplet")) : Blackboard.bt)).c();
                                        return "";
                                    }
                                }
                            }
                        }
                    }
                }
                return "";
            }
        }
    }
    
    private double h(final Object o) throws MesonException {
        if (o instanceof Double) {
            return (double)o;
        }
        if (o.getClass() == Blackboard.aq) {
            return (double)this.a(o, (Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu);
        }
        return this.l(o);
    }
    
    private String i(final Object o) throws MesonException {
        if (o.getClass() == Blackboard.aq) {
            return this.a(o, (Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw).toString();
        }
        return o.toString();
    }
    
    private int j(final Object o) throws MesonException {
        final double n = 0.5 + this.h(o);
        final int n2 = (int)n;
        if (n > 0.0 || n == n2) {
            return n2;
        }
        return n2 - 1;
    }
    
    private FastVector k(final Object o) {
        if (o instanceof FastVector) {
            return (FastVector)o;
        }
        final FastVector fastVector = new FastVector(8, 8);
        fastVector.add(o);
        return fastVector;
    }
    
    private Double l(final Object o) throws MesonException {
        if (o == "") {
            return Blackboard.a;
        }
        if (o == "_N") {
            return Blackboard.c;
        }
        if (o == "_M") {
            return Blackboard.e;
        }
        if (o == "_P") {
            return Blackboard.d;
        }
        if (o == "true") {
            return Blackboard.b;
        }
        if (o == "false") {
            return Blackboard.a;
        }
        try {
            return new Double(o.toString());
        }
        catch (NumberFormatException ex) {
            throw new MesonException("Error converting \"" + o + "\" to Number");
        }
    }
    
    private Object m(final Object o) throws MesonException {
        if (o == "") {
            return Blackboard.f;
        }
        if (o == "_N") {
            return Blackboard.h;
        }
        if (o == "_M") {
            return Blackboard.j;
        }
        if (o == "_P") {
            return Blackboard.i;
        }
        if (o == "true") {
            return Blackboard.g;
        }
        if (o == "false") {
            return Blackboard.f;
        }
        try {
            return new Float(o.toString());
        }
        catch (NumberFormatException ex) {
            throw new MesonException("Error converting \"" + o + "\" to Number");
        }
    }
    
    private Object n(final Object o) {
        try {
            if (Blackboard.a3 == null) {
                Blackboard.a3 = Class.forName("com.kaon.meson.MesonByteBuffer");
                Blackboard.a4 = Blackboard.a3.getMethod("toByteArray", (Class[])new Class[0]);
            }
            if (Blackboard.a3.isInstance(o)) {
                return Blackboard.a4.invoke(o, (Object[])null);
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private Object a(Class componentType, final Object o) throws MesonException {
        if (o == "java.lang.null") {
            return null;
        }
        if (!componentType.isArray()) {
            if (componentType.isPrimitive()) {
                if (componentType == Float.TYPE) {
                    componentType = (Class<?>)((Blackboard.b1 == null) ? (Blackboard.b1 = class$("java.lang.Float")) : Blackboard.b1);
                }
                else if (componentType == Double.TYPE) {
                    componentType = (Class<?>)((Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu);
                }
                else if (componentType == Integer.TYPE) {
                    componentType = (Class<?>)((Blackboard.bx == null) ? (Blackboard.bx = class$("java.lang.Integer")) : Blackboard.bx);
                }
                else if (componentType == Long.TYPE) {
                    componentType = (Class<?>)((Blackboard.b2 == null) ? (Blackboard.b2 = class$("java.lang.Long")) : Blackboard.b2);
                }
                else if (componentType == Short.TYPE) {
                    componentType = (Class<?>)((Blackboard.b3 == null) ? (Blackboard.b3 = class$("java.lang.Short")) : Blackboard.b3);
                }
                else if (componentType == Boolean.TYPE) {
                    componentType = (Class<?>)((Blackboard.b4 == null) ? (Blackboard.b4 = class$("java.lang.Boolean")) : Blackboard.b4);
                }
                else if (componentType == Character.TYPE) {
                    componentType = (Class<?>)((Blackboard.b5 == null) ? (Blackboard.b5 = class$("java.lang.Character")) : Blackboard.b5);
                }
            }
            final Class<?> class1 = o.getClass();
            if (class1 == componentType || componentType.isInstance(o)) {
                return o;
            }
            if (componentType == ((Blackboard.b6 == null) ? (Blackboard.b6 = class$("java.util.Vector")) : Blackboard.b6) && class1 == ((Blackboard.bv == null) ? (Blackboard.bv = class$("com.kaon.meson.FastVector")) : Blackboard.bv)) {
                return ((FastVector)o).createVector();
            }
            if (class1 == ((Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu)) {
                if (componentType == ((Blackboard.bx == null) ? (Blackboard.bx = class$("java.lang.Integer")) : Blackboard.bx)) {
                    return new Integer((int)o);
                }
                if (componentType == ((Blackboard.b2 == null) ? (Blackboard.b2 = class$("java.lang.Long")) : Blackboard.b2)) {
                    return new Long((long)o);
                }
                if (componentType == ((Blackboard.b3 == null) ? (Blackboard.b3 = class$("java.lang.Short")) : Blackboard.b3)) {
                    return new Short((short)o);
                }
            }
            try {
                if (componentType == ((Blackboard.bx == null) ? (Blackboard.bx = class$("java.lang.Integer")) : Blackboard.bx)) {
                    final String string = o.toString();
                    if (string.startsWith("0x")) {
                        return new Integer((int)Long.parseLong(string.substring(2), 16));
                    }
                    return Integer.valueOf(string);
                }
                else {
                    if (componentType == ((Blackboard.bu == null) ? (Blackboard.bu = class$("java.lang.Double")) : Blackboard.bu)) {
                        return this.l(o);
                    }
                    if (componentType == ((Blackboard.b1 == null) ? (Blackboard.b1 = class$("java.lang.Float")) : Blackboard.b1)) {
                        return this.m(o);
                    }
                    if (componentType == ((Blackboard.b5 == null) ? (Blackboard.b5 = class$("java.lang.Character")) : Blackboard.b5)) {
                        return new Character(o.toString().charAt(0));
                    }
                    if (componentType == ((Blackboard.b2 == null) ? (Blackboard.b2 = class$("java.lang.Long")) : Blackboard.b2)) {
                        return new Long((long)(Object)this.l(o));
                    }
                    if (componentType == ((Blackboard.b3 == null) ? (Blackboard.b3 = class$("java.lang.Short")) : Blackboard.b3)) {
                        return new Short((short)(Object)this.l(o));
                    }
                    if (componentType == ((Blackboard.b7 == null) ? (Blackboard.b7 = class$("java.lang.Class")) : Blackboard.b7)) {
                        return Class.forName(o.toString());
                    }
                    return componentType.getConstructor((Blackboard.bw == null) ? (Blackboard.bw = class$("java.lang.String")) : Blackboard.bw).newInstance(o.toString());
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                throw new MesonException("Unable to coerce " + o + " from " + o.getClass() + " to " + componentType);
            }
        }
        if (!(o instanceof FastVector)) {
            throw new MesonException("Unable to coerce " + o + " to array");
        }
        final FastVector fastVector = (FastVector)o;
        final int size = fastVector.size();
        componentType = componentType.getComponentType();
        if (!componentType.isPrimitive()) {
            final Object[] array = (Object[])Array.newInstance(componentType, size);
            for (int i = 0; i < size; ++i) {
                array[i] = this.a(componentType, fastVector.get(i));
            }
            return array;
        }
        if (componentType == Float.TYPE) {
            final float[] array2 = new float[size];
            for (int j = 0; j < size; ++j) {
                final Object value = fastVector.get(j);
                if (value instanceof Double) {
                    array2[j] = (float)value;
                }
                else if (value == "_N") {
                    array2[j] = Float.NaN;
                }
                else if (value == "_M") {
                    array2[j] = Float.NEGATIVE_INFINITY;
                }
                else if (value == "_P") {
                    array2[j] = Float.POSITIVE_INFINITY;
                }
                else {
                    array2[j] = Float.valueOf(value.toString());
                }
            }
            return array2;
        }
        if (componentType == Integer.TYPE) {
            final int[] array3 = new int[size];
            for (int k = 0; k < size; ++k) {
                final Object value2 = fastVector.get(k);
                if (value2 instanceof Double) {
                    array3[k] = (int)value2;
                }
                else {
                    final String string2 = value2.toString();
                    if (string2.startsWith("0x")) {
                        array3[k] = (int)Long.parseLong(string2.substring(2), 16);
                    }
                    else {
                        array3[k] = Integer.parseInt(string2);
                    }
                }
            }
            return array3;
        }
        throw new MesonException("Marshalling of arrays of " + componentType + " not supported");
    }
    
    private Object d(final String s, final String s2) {
        if (s.indexOf(46) != -1) {
            final int lastIndex = s2.lastIndexOf(46);
            if (lastIndex == 0) {
                return this.newStringPair(intern(s), intern(s2));
            }
            if (lastIndex != -1) {
                return this.newStringPair(intern(s + s2.substring(0, lastIndex)), intern(s2.substring(lastIndex)));
            }
        }
        return intern(s + s2);
    }
    
    private Object a(Object o, final String s, final String s2) throws MesonException {
        if (o == null) {
            return null;
        }
        if (o.getClass() != Blackboard.aq) {
            if (o instanceof String || o instanceof StringPair) {
                final String string = o.toString();
                final int index = string.indexOf(36);
                if (index != -1) {
                    String s3;
                    String s4;
                    if (index == string.length() - 1 || string.charAt(index + 1) != '$') {
                        s3 = string.substring(0, index) + s2;
                        s4 = string.substring(index + 1);
                    }
                    else {
                        s3 = string.substring(0, index) + s;
                        s4 = string.substring(index + 2);
                    }
                    if ("".equals(s4)) {
                        return intern(s3);
                    }
                    return this.d(s3, s4);
                }
            }
            else if (o instanceof FastVector) {
                final FastVector fastVector = (FastVector)((FastVector)o).clone();
                for (int i = 0; i < fastVector.size(); ++i) {
                    fastVector.add(i, this.a(fastVector.get(i), s, s2));
                }
                o = fastVector;
            }
            return o;
        }
        final Object[] array = (Object[])o;
        final Object[] array2 = new Object[array.length];
        array2[0] = array[0];
        boolean b = false;
        for (int j = 1; j < array2.length; ++j) {
            array2[j] = this.a(array[j], s, s2);
            b |= (array[j] != array2[j]);
        }
        if (!b) {
            return o;
        }
        return array2;
    }
    
    private Integer b(final String s) throws MesonException {
        if (s == "") {
            return null;
        }
        if (s.startsWith("0x")) {
            return new Integer((int)Long.parseLong(s.substring(2), 16));
        }
        final char[] charArray = s.toCharArray();
        int n = 0;
        final int length = s.length();
        int n2;
        int n3;
        int n4;
        int n5;
        if (length == 3 || length == 4) {
            if (length == 3) {
                n2 = 255;
            }
            else {
                final int digit = Character.digit(charArray[n++], 16);
                n2 = (digit << 4 | digit);
            }
            final int digit2 = Character.digit(charArray[n++], 16);
            n3 = (digit2 << 4 | digit2);
            final int digit3 = Character.digit(charArray[n++], 16);
            n4 = (digit3 << 4 | digit3);
            final int digit4 = Character.digit(charArray[n++], 16);
            n5 = (digit4 << 4 | digit4);
        }
        else {
            if (length != 6 && length != 8) {
                throw new MesonException("Error converting \"" + s + "\" to Color (should be one of RGB ARGB RRGGBB AARRGGBB");
            }
            if (length == 6) {
                n2 = 255;
            }
            else {
                n2 = (Character.digit(charArray[n++], 16) << 4 | Character.digit(charArray[n++], 16));
            }
            n3 = (Character.digit(charArray[n++], 16) << 4 | Character.digit(charArray[n++], 16));
            n4 = (Character.digit(charArray[n++], 16) << 4 | Character.digit(charArray[n++], 16));
            n5 = (Character.digit(charArray[n++], 16) << 4 | Character.digit(charArray[n++], 16));
        }
        return new Integer(n2 << 24 | n3 << 16 | n4 << 8 | n5);
    }
    
    private boolean c(final String s) throws MesonException {
        final char[] charArray = s.toCharArray();
        int n = 0;
        char c = ' ';
        int n2 = 0;
        int n3 = 0;
        final char[] array = new char[charArray.length];
        int n4 = 0;
        for (int i = 0; i < charArray.length; ++i) {
            final char c2 = charArray[i];
            final char c3 = (i == charArray.length - 1) ? '\0' : charArray[i + 1];
            if (c != ' ') {
                if (n != 0) {
                    n = 0;
                }
                else if (c2 == c) {
                    c = ' ';
                }
                else if (c2 == '\\') {
                    n = 1;
                }
            }
            else if (n3 != 0) {
                if (c2 == '\n') {
                    n3 = 0;
                }
            }
            else {
                if (n2 != 0 && c2 == '*' && c3 == '/') {
                    n2 = 0;
                }
                if (c2 == '/' && c3 == '*') {
                    n2 = 1;
                }
                if (c2 == '/' && c3 == '/') {
                    n3 = 1;
                }
                else if (c2 == '\"' || c2 == '\'') {
                    c = c2;
                }
                else if (c2 == '{' || (n2 == 0 && (c2 == '[' || c2 == '('))) {
                    array[n4++] = c2;
                }
                else if (c2 == '}' || (n2 == 0 && (c2 == ']' || c2 == ')'))) {
                    if (n4 == 0) {
                        throw new MesonException("Syntax Error: extra " + c2 + " in line");
                    }
                    final char c4 = array[--n4];
                    if ((c4 != '{' || c2 != '}') && (c4 != '[' || c2 != ']') && (c4 != '(' || c2 != ')')) {
                        throw new MesonException("Syntax Error: brace mismatch " + c4 + " " + c2);
                    }
                }
            }
        }
        return n4 != 0;
    }
    
    public Object parse(final String s) throws MesonException {
        final char[] charArray = s.toCharArray();
        final Object[] array = { new Integer(0), null, new Integer(Blackboard.bi) };
        final Object[][] array2 = new Object[65536][];
        int n = -1;
        array2[++n] = new Object[] { new Integer(Blackboard.bi), "$" };
        int n2 = Blackboard.bi;
        int n3 = this.a(charArray, array);
        if (n3 == Blackboard.bh) {
            this.bl = "";
            n3 = this.a(charArray, array);
        }
        if (n3 == Blackboard.bi) {
            return "";
        }
        while (true) {
            for (int i = n; i >= 0; --i) {
                n2 = (int)array2[i][0];
                if (n2 < Blackboard.bj) {
                    break;
                }
            }
            if (n2 == Blackboard.bi && n3 == Blackboard.bi) {
                return this.r(this.o(this.a(array2[n])));
            }
            final byte b = Blackboard.br[n2][n3];
            if (b == Blackboard.bn || b == Blackboard.bp) {
                array2[++n] = new Object[] { new Integer(n3), array[1] };
                n3 = this.a(charArray, array);
                if (n3 == Blackboard.bg) {
                    this.bl = (String)array2[n--][1];
                }
                else {
                    if (n3 != Blackboard.bh) {
                        continue;
                    }
                    this.bl = "";
                }
            }
            else {
                if (b != Blackboard.bo) {
                    throw new MesonException("Syntax Error");
                }
                int bi = Blackboard.bi;
                int j;
                for (j = n; j > 0; --j) {
                    n2 = (int)array2[j][0];
                    if (n2 != Blackboard.bk) {
                        if (Blackboard.br[n2][bi] == Blackboard.bn) {
                            break;
                        }
                        bi = n2;
                    }
                }
                final int n4 = j;
                Object[] array3;
                if (n - n4 == 1) {
                    array3 = new Object[] { new Integer(Blackboard.bk), "(", this.a(array2[n]), null };
                }
                else {
                    final boolean b2 = n - n4 == 2;
                    final int n5 = n4 + (b2 ? 1 : 2);
                    if (!b2 && array2[n5 - 1][1] == "(" && array2[n5 + 1][1] == ")") {
                        if (array2[n5][1] == "(" && array2[n5 - 1].length == 4) {
                            array3 = array2[n5 - 1];
                            array3[3] = ")";
                        }
                        else {
                            array3 = array2[n5];
                        }
                    }
                    else if (!b2 && ((array2[n5 - 1][1] == "{" && array2[n5 + 1][1] == "}") || (array2[n5 - 1][1] == "[" && array2[n5 + 1][1] == "]"))) {
                        array3 = new Object[] { new Integer(Blackboard.bk), this.a(array2[n5 - 1]), null, this.a(array2[n5]) };
                    }
                    else if (b2 && (array2[n5 + 1][1] == ";" || array2[n5 + 1][1] == ",")) {
                        array3 = array2[n5];
                    }
                    else {
                        array3 = new Object[] { new Integer(Blackboard.bk), this.a(array2[n5]), b2 ? null : this.a(array2[n5 - 1]), this.a(array2[n5 + 1]) };
                    }
                }
                n = n4;
                array2[++n] = array3;
            }
        }
    }
    
    public boolean b(final Object o) {
        return o.getClass() == Blackboard.aq && ((Object[])o)[0] == "@";
    }
    
    public boolean c(final Object o) {
        return o.getClass() == Blackboard.aq && ((Object[])o)[0] == "{";
    }
    
    private boolean d(final String s) {
        return "+-*/%<\u2265>\u2264".indexOf(s) != -1;
    }
    
    private Object o(final Object o) throws MesonException {
        if (o == null || o.getClass() != Blackboard.aq) {
            return o;
        }
        final Object[] array = (Object[])o;
        if (!(array[0] instanceof String)) {
            throw new MesonException("Syntax error");
        }
        final String s = (String)array[0];
        if (this.d(s)) {
            if (array[1] instanceof String) {
                array[1] = this.l(array[1]);
            }
            if (array[2] instanceof String) {
                array[2] = this.l(array[2]);
            }
        }
        else if (s == "[" && array[1] == null && this.p(array)) {
            return this.q(array);
        }
        array[1] = this.o(array[1]);
        array[2] = this.o(array[2]);
        if ((s == "#" || s == "^" || s == "¬") && this.b(array[2])) {
            array[2] = ((Object[])array[2])[2];
        }
        else if (s == "(" && this.b(array[1])) {
            array[1] = ((Object[])array[1])[2];
        }
        else if (s == "+" && (this.c(array[1]) || this.c(array[2]))) {
            if (this.b(array[1])) {
                array[1] = ((Object[])array[1])[2];
            }
            if (this.b(array[2])) {
                array[2] = ((Object[])array[2])[2];
            }
        }
        else if (s == "=" && array[1].getClass() == Blackboard.aq) {
            final Object[] array2 = (Object[])array[1];
            if (array2[0] == "@") {
                array[1] = array2[2];
            }
            else if (array2[0] == "[" && array2[1].getClass() == Blackboard.aq) {
                final Object[] array3 = (Object[])array2[1];
                if (array3[0] == "@") {
                    array2[1] = array3[2];
                }
            }
        }
        else if (s == "(" && array[1] == "@") {
            array[0] = "@";
            array[1] = null;
        }
        else if (s == "{" && array[1] != null) {
            Object o2 = array[1];
            if (o2.getClass() == Blackboard.aq) {
                o2 = ((Object[])o2)[2];
            }
            final Object o3 = array[2];
            array[0] = ";";
            array[1] = new Object[] { "=", o2 + "\u03b4", { "{", null, this.o(o3) } };
            array[2] = o2;
        }
        return o;
    }
    
    private boolean p(Object o) {
        if (o == null || o.getClass() != Blackboard.aq) {
            return true;
        }
        while (o.getClass() == Blackboard.aq) {
            final Object[] array = (Object[])o;
            if (array[0] != "[" && array[0] != "," && array[0] != ";") {
                return false;
            }
            if (!this.p(array[2])) {
                return false;
            }
            o = array[1];
            if (o == null) {
                return true;
            }
        }
        return true;
    }
    
    private FastVector q(Object o) {
        final FastVector fastVector = new FastVector(0, 8);
        final Object[] array = (Object[])o;
        if (array[2] == "]") {
            return fastVector;
        }
        final Object o2;
        o = (o2 = array[2]);
        int n = 1;
        while (o.getClass() == Blackboard.aq) {
            final Object[] array2 = (Object[])o;
            if (array2[0] != "," && array2[0] != ";") {
                break;
            }
            ++n;
            o = array2[1];
        }
        Object[] array3;
        Object o3;
        for (o = o2; o.getClass() == Blackboard.aq; o = array3[1]) {
            array3 = (Object[])o;
            if (array3[0] != "," && array3[0] != ";") {
                fastVector.add(--n, this.q(array3));
                return fastVector;
            }
            o3 = array3[2];
            if (o3.getClass() == Blackboard.aq) {
                fastVector.add(--n, this.q(o3));
            }
            else {
                fastVector.add(--n, o3);
            }
        }
        fastVector.add(--n, o);
        return fastVector;
    }
    
    private Object a(final Object[] array) throws MesonException {
        if (array.length == 2) {
            if ((int)array[0] == Blackboard.a7) {
                return new Object[] { "@", null, array[1] };
            }
            return array[1];
        }
        else {
            final Object o = array[1];
            if (o == "(" && array[3] == null) {
                return array[2];
            }
            return new Object[] { o, array[2], array[3] };
        }
    }
    
    private Object r(final Object o) {
        final int[] array = new int[2];
        this.a(o, array);
        final char[] array2 = new char[array[0]];
        final Object[] array3 = new Object[1 + array[1]];
        array[0] = 0;
        array[1] = 1;
        this.a(o, array, (char[])(array3[0] = array2), array3, false);
        return array3;
    }
    
    private void a(final Object o, final int[] array) {
        if (o == null) {
            return;
        }
        if (o.getClass() != Blackboard.aq) {
            final int n = 0;
            ++array[n];
            final int n2 = 1;
            ++array[n2];
        }
        else {
            final Object[] array2 = (Object[])o;
            if (array2[0] == "{") {
                final int n3 = 1;
                ++array[n3];
            }
            else {
                final Object o2 = array2[0];
                if (o2 == "[" && array2[1] == null) {
                    final int n4 = 0;
                    ++array[n4];
                    final int n5 = 1;
                    ++array[n5];
                }
                else if (o2 == ":" && array2[1].getClass() == Blackboard.aq) {
                    final int n6 = 0;
                    ++array[n6];
                    final int n7 = 1;
                    ++array[n7];
                }
                else {
                    this.a(array2[1], array);
                }
                if (o2 == "=" && array2[1].getClass() == Blackboard.aq) {
                    final Object o3 = ((Object[])array2[1])[0];
                    if (o3 == "#" || o3 == "^" || o3 == "[") {
                        final int n8 = 0;
                        --array[n8];
                    }
                }
                if ((o2 == "&" || o2 == "|" || o2 == ":" || o2 == "(") && array2[2].getClass() == Blackboard.aq) {
                    final int n9 = 0;
                    ++array[n9];
                    final int n10 = 1;
                    ++array[n10];
                }
                else if (o2 == "?" && array2[2].getClass() == Blackboard.aq) {
                    if (((Object[])array2[2])[0] == ":") {
                        this.a(array2[2], array);
                        return;
                    }
                    final int n11 = 0;
                    ++array[n11];
                    final int n12 = 1;
                    ++array[n12];
                }
                else {
                    this.a(array2[2], array);
                }
            }
            final int n13 = 0;
            ++array[n13];
        }
    }
    
    private void a(final Object o, final int[] array, final char[] array2, final Object[] array3, final boolean b) {
        if (o == null) {
            return;
        }
        if (o.getClass() != Blackboard.aq) {
            array2[array[0]++] = ' ';
            array3[array[1]++] = o;
        }
        else {
            final Object[] array4 = (Object[])o;
            if (array4[0] == "{") {
                array2[array[0]++] = ' ';
                array3[array[1]++] = this.r(array4[2]);
            }
            else {
                Object o2 = array4[0];
                final Object o3 = array4[1];
                final Object o4 = array4[2];
                if (o2 == "@" && b) {
                    o2 = "D";
                }
                else if (o2 == "¬" && b) {
                    o2 = "E";
                }
                boolean b2 = this.d((String)o2) && o2 != "+";
                if (o2 == "[" && o3 == null) {
                    this.a(o4, array, array2, array3, 1);
                    return;
                }
                if (o2 == ":" && o3.getClass() == Blackboard.aq) {
                    array2[array[0]++] = ' ';
                    array3[array[1]++] = this.r(o3);
                }
                else if (o2 == "=" && o3.getClass() == Blackboard.aq) {
                    final Object o5 = ((Object[])o3)[0];
                    if (o5 == "#") {
                        o2 = "p";
                        this.a(((Object[])o3)[2], array, array2, array3, false);
                    }
                    else if (o5 == "^") {
                        o2 = "c";
                        this.a(((Object[])o3)[2], array, array2, array3, false);
                    }
                    else if (o5 == "[") {
                        o2 = "a";
                        this.a(((Object[])o3)[1], array, array2, array3, false);
                        this.a(((Object[])o3)[2], array, array2, array3, true);
                    }
                    else if (o5 == "¬") {
                        this.a(((Object[])o3)[1], array, array2, array3, false);
                        this.a(((Object[])o3)[2], array, array2, array3, false);
                        array2[array[0]++] = 'd';
                    }
                    else {
                        this.a(o3, array, array2, array3, b2);
                    }
                }
                else if (o2 == "(" && o3.getClass() == Blackboard.aq && ((Object[])o3)[0] == "¬") {
                    this.a(((Object[])o3)[1], array, array2, array3, false);
                    this.a(((Object[])o3)[2], array, array2, array3, false);
                    array2[array[0]++] = 'd';
                }
                else {
                    this.a(o3, array, array2, array3, b2);
                }
                if (o2 == "[") {
                    b2 = true;
                }
                if ((o2 == "&" || o2 == "|" || o2 == ":" || o2 == "(") && o4.getClass() == Blackboard.aq) {
                    array2[array[0]++] = ' ';
                    array3[array[1]++] = this.r(o4);
                }
                else {
                    if (o2 == "," || o2 == ";") {
                        array2[array[0]++] = ((String)o2).charAt(0);
                        this.a(o4, array, array2, array3, b2);
                        return;
                    }
                    if (o2 == "?" && o4.getClass() == Blackboard.aq) {
                        if (((Object[])o4)[0] == ":") {
                            this.a(o4, array, array2, array3, b2);
                            return;
                        }
                        array2[array[0]++] = ' ';
                        array3[array[1]++] = this.r(o4);
                    }
                    else {
                        this.a(o4, array, array2, array3, b2);
                    }
                }
                if (o2 == "+" && (this.c(o3) || this.c(o4))) {
                    o2 = "e";
                }
                array2[array[0]++] = ((String)o2).charAt(0);
            }
        }
    }
    
    private void a(final Object o, final int[] array, final char[] array2, final Object[] array3, final int n) {
        if (o == null) {
            return;
        }
        if (o.getClass() == Blackboard.aq) {
            final Object[] array4 = (Object[])o;
            if (array4[0] == ";" || array4[0] == ",") {
                this.a(array4[1], array, array2, array3, n + 1);
                this.a(array4[2], array, array2, array3, false);
                array2[array[0]++] = '.';
                return;
            }
        }
        array2[array[0]++] = ' ';
        array3[array[1]++] = new Integer(n);
        this.a(o, array, array2, array3, false);
        array2[array[0]++] = ']';
    }
    
    private boolean s(final Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != Blackboard.aq) {
            return false;
        }
        final Object[] array = (Object[])o;
        return array.length > 0 && array[0].getClass() == Blackboard.ar;
    }
    
    private StringBuffer t(final Object o) {
        if (o == null) {
            return new StringBuffer();
        }
        if (!this.s(o)) {
            StringBuffer sb;
            if (o instanceof FastVector) {
                sb = new StringBuffer(" [");
                final FastVector fastVector = (FastVector)o;
                for (int i = 0; i < fastVector.size(); ++i) {
                    sb.append((Object)this.t(fastVector.get(i)));
                    if (i < fastVector.size() - 1) {
                        sb.append(',');
                    }
                }
                sb.append("] ");
            }
            else if (o instanceof byte[]) {
                sb = new StringBuffer("bytes(" + ((byte[])o).length + ")");
            }
            else if (o == "") {
                sb = new StringBuffer("\"\"");
            }
            else {
                sb = new StringBuffer(o.toString());
            }
            return sb;
        }
        final StringBuffer sb2 = new StringBuffer();
        final FastVector fastVector2 = new FastVector(64, 8);
        final Object[] array = (Object[])o;
        final char[] array2 = (char[])array[0];
        int n = 1;
        for (int j = 0; j < array2.length; ++j) {
            final char c = array2[j];
            if (c == ' ') {
                final Object o2 = array[n++];
                final StringBuffer t = this.t(o2);
                if (this.s(o2)) {
                    t.insert(0, '{').append('}');
                }
                fastVector2.add(t);
            }
            else if (c == '@' || c == 'D') {
                ((StringBuffer)fastVector2.get(fastVector2.size() - 1)).insert(0, this.ak ? "\u001b[4m" : "_").append(this.ak ? "\u001b[m" : "_");
            }
            else if (c == '#' || c == '^' || c == '!' || c == '\u0192') {
                ((StringBuffer)fastVector2.get(fastVector2.size() - 1)).insert(0, c);
            }
            else if (c == ':' || c == 'a') {
                final StringBuffer sb3 = (StringBuffer)fastVector2.get(fastVector2.size() - 1);
                fastVector2.setSize(fastVector2.size() - 1);
                final StringBuffer sb4 = (StringBuffer)fastVector2.get(fastVector2.size() - 1);
                fastVector2.setSize(fastVector2.size() - 1);
                final StringBuffer sb5 = (StringBuffer)fastVector2.get(fastVector2.size() - 1);
                if (c == ':') {
                    sb5.append('?').append((Object)sb4).append(':').append((Object)sb3);
                }
                else {
                    sb5.append('[').append((Object)sb4).append("]=").append((Object)sb3);
                }
            }
            else if (c == ']') {
                final StringBuffer sb6 = (StringBuffer)fastVector2.get(fastVector2.size() - 1);
                fastVector2.setSize(fastVector2.size() - 1);
                fastVector2.setSize(fastVector2.size() - 1);
                fastVector2.add(sb6.insert(0, '[').append(']'));
            }
            else if (c == '.') {
                final StringBuffer sb7 = (StringBuffer)fastVector2.get(fastVector2.size() - 1);
                fastVector2.setSize(fastVector2.size() - 1);
                final StringBuffer sb8 = (StringBuffer)fastVector2.get(fastVector2.size() - 1);
                sb8.setCharAt(sb8.length() - 1, ',');
                sb8.append((Object)sb7).append(']');
            }
            else if (c == ',' || c == ';') {
                final StringBuffer sb9 = (StringBuffer)fastVector2.get(fastVector2.size() - 1);
                fastVector2.setSize(fastVector2.size() - 1);
                sb2.append((Object)sb9).append(c);
            }
            else if (c != '\0') {
                final StringBuffer sb10 = (StringBuffer)fastVector2.get(fastVector2.size() - 1);
                fastVector2.setSize(fastVector2.size() - 1);
                final StringBuffer sb11 = (StringBuffer)fastVector2.get(fastVector2.size() - 1);
                if (c == 'p') {
                    sb11.insert(0, '#').append('=').append((Object)sb10);
                }
                else if (c == 'c') {
                    sb11.insert(0, '^').append('=').append((Object)sb10);
                }
                else if (c == 'd') {
                    sb11.append('®').append((Object)sb10);
                }
                else if (c == 'E') {
                    sb11.append('®').append((Object)sb10);
                }
                else if (c == 'e') {
                    sb11.append('+').append((Object)sb10);
                }
                else {
                    sb11.append(c).append((Object)sb10);
                }
                if (c == '[') {
                    sb11.append(']');
                }
                else if (c == '(') {
                    sb11.insert(0, this.ak ? "\u001b[1m" : "").append(this.ak ? "\u001b[1m)\u001b[0m" : ")");
                }
            }
        }
        return sb2.append(fastVector2.get(0));
    }
    
    private int a(final char[] array, final Object[] array2) {
        int intValue;
        int i = intValue = (int)array2[0];
        final int intValue2 = (int)array2[2];
        if (i >= array.length) {
            return Blackboard.bi;
        }
        final boolean b = intValue2 == Blackboard.bi;
        char c = array[i++];
        boolean b2;
        do {
            b2 = false;
            while (Character.isWhitespace(c)) {
                if (i == array.length) {
                    return Blackboard.bi;
                }
                c = array[i++];
                intValue = i - 1;
                b2 = true;
            }
            if (c == '/' && i < array.length && array[i] == '*') {
                while (i < array.length - 1 && (c != '*' || array[i] != '/')) {
                    c = array[i++];
                }
                if (i > array.length - 2) {
                    return Blackboard.bi;
                }
                c = array[++i];
                intValue = i++;
                b2 = true;
            }
            if (c == '/' && i < array.length && array[i] == '/') {
                while (i < array.length && c != '\n') {
                    c = array[i++];
                }
                if (i > array.length - 1) {
                    return Blackboard.bi;
                }
                intValue = i;
                b2 = true;
            }
        } while (b2);
        if (c == '\"' || c == '\'') {
            final char c2 = c;
            final StringBuffer sb = new StringBuffer();
            int n = 0;
            while (i < array.length) {
                final char c3 = array[i++];
                if (n != 0) {
                    if (c3 == 'n') {
                        sb.append('\n');
                    }
                    else if (c3 == 't') {
                        sb.append('\t');
                    }
                    else if (c3 == 'u') {
                        int n2 = 0;
                        for (int n3 = 0; n3 < 4 && i < array.length; n2 = n2 * 16 + Character.digit(array[i++], 16), ++n3) {}
                        sb.append((char)n2);
                    }
                    else {
                        sb.append(c3);
                    }
                    n = 0;
                }
                else {
                    if (c3 == c2) {
                        break;
                    }
                    if (c3 == '\\') {
                        n = 1;
                    }
                    else {
                        sb.append(c3);
                    }
                }
            }
            if (array[i - 1] != c2) {
                System.err.println("WARNING: Unterminated String: " + new String(array));
            }
            String s;
            int index;
            for (s = sb.toString(); this.bl != "" && (index = s.indexOf("$$$")) != -1; s = s.substring(0, index) + this.bl + s.substring(index + 3)) {}
            array2[0] = new Integer(i);
            array2[1] = s.intern();
            array2[2] = new Integer(Blackboard.a8);
        }
        else {
            int j;
            for (j = Blackboard.a8 + 1; j < Blackboard.bi; ++j) {
                if (c == Blackboard.a5[j] && (c != '-' || Blackboard.a6[intValue2] != '-')) {
                    if (i < array.length) {
                        if (array[i] == '=') {
                            ++i;
                            switch (c) {
                                case '=': {
                                    c = '\u2248';
                                    j = Blackboard.bc;
                                    break;
                                }
                                case '<': {
                                    c = '\u2264';
                                    j = Blackboard.be;
                                    break;
                                }
                                case '>': {
                                    c = '\u2265';
                                    j = Blackboard.bf;
                                    break;
                                }
                                case '!': {
                                    c = '\u2260';
                                    j = Blackboard.bd;
                                    break;
                                }
                                default: {
                                    --i;
                                    break;
                                }
                            }
                        }
                        if (array[i] == c) {
                            if (c == '+') {
                                c = '\u222a';
                                j = Blackboard.ba;
                                ++i;
                            }
                            else if (c == '-') {
                                c = '\u2216';
                                j = Blackboard.bb;
                                ++i;
                            }
                            else if (c == '<') {
                                c = '«';
                                j = Blackboard.bg;
                                ++i;
                            }
                            else if (c == '>') {
                                c = '»';
                                j = Blackboard.bh;
                                ++i;
                            }
                        }
                        if (c == '-' && array[i] == '>') {
                            c = '¬';
                            j = Blackboard.a9;
                            ++i;
                        }
                    }
                    array2[0] = new Integer(i);
                    array2[1] = new Character(c).toString().intern();
                    array2[2] = new Integer(j);
                    break;
                }
            }
            if (j == Blackboard.bi) {
                if (c == '-') {
                    ++i;
                }
                char c4 = ' ';
                while (i < array.length) {
                    final char c5 = c4;
                    c4 = c;
                    c = array[i];
                    if (Character.isWhitespace(c)) {
                        break;
                    }
                    if ((c != '-' && c != '+') || (c4 != 'e' && c4 != 'E') || !Character.isDigit(c5)) {
                        int n4;
                        for (n4 = Blackboard.a8 + 1; n4 < Blackboard.bi && c != Blackboard.a5[n4]; ++n4) {}
                        if (n4 != Blackboard.bi) {
                            break;
                        }
                    }
                    ++i;
                }
                array2[0] = new Integer(i);
                String s2 = new String(array, intValue, i - intValue);
                if (b && this.bl != "") {
                    s2 = this.bl + s2;
                }
                int index2;
                while (this.bl != "" && (index2 = s2.indexOf("$$$")) != -1) {
                    s2 = s2.substring(0, index2) + this.bl + s2.substring(index2 + 3);
                }
                final String intern = s2.intern();
                array2[1] = intern;
                final char c6 = array[intValue];
                array2[2] = new Integer((c6 == '-' || c6 == '_' || c6 == '.' || (c6 >= '0' && c6 <= '9') || intern == "true" || intern == "false") ? Blackboard.a8 : Blackboard.a7);
            }
        }
        return (int)array2[2];
    }
    
    public static double mix(final double n, final double n2) {
        return 0.95 * n + 0.05 * n2;
    }
    
    public static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        a = new Double(0.0);
        b = new Double(1.0);
        c = new Double(Double.NaN);
        d = new Double(Double.POSITIVE_INFINITY);
        e = new Double(Double.NEGATIVE_INFINITY);
        f = new Float(0.0f);
        g = new Float(1.0f);
        h = new Float(Float.NaN);
        i = new Float(Float.POSITIVE_INFINITY);
        j = new Float(Float.NEGATIVE_INFINITY);
        Blackboard.an = System.getProperty("os.name").equals("Mac OS");
        Blackboard.ao = !System.getProperty("java.version").startsWith("1.1");
        Blackboard.ap = new Object();
        aq = new Object[0].getClass();
        BYTE_ARRAY_CLASS = new byte[0].getClass();
        ar = new char[0].getClass();
        Blackboard.au = new Object[0];
        Blackboard.aw = new FastHashtable(11);
        Blackboard.az = System.out;
        Blackboard.a0 = 65537;
        Blackboard.a1 = new String[Blackboard.a0];
        Blackboard.a5 = "IL#^@¬!~&|\u222a\u2216+-*/%<\u2248>\u2260\u2264\u2265?:=()[]{},;«»$E".toCharArray();
        Blackboard.a6 = "xxxxxxx--------------------x-x-x-----x".toCharArray();
        Blackboard.a7 = 0;
        Blackboard.a8 = 1;
        Blackboard.a9 = 5;
        Blackboard.ba = 10;
        Blackboard.bb = 11;
        Blackboard.bc = 18;
        Blackboard.bd = 20;
        Blackboard.be = 21;
        Blackboard.bf = 22;
        Blackboard.bg = 34;
        Blackboard.bh = 35;
        Blackboard.bi = Blackboard.a5.length - 2;
        Blackboard.bj = Blackboard.a5.length - 1;
        Blackboard.bk = Blackboard.a5.length - 1;
        Blackboard.bm = "EE<<<><>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>EE<<<><>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<><>>>>>>>>>>>>>>>>>>><><>>>>>>>><<<<<><>>>>>>>>>>>>>>>>>>><><>>>>>>>><<<<<><>>>>>>>>>>>>>>>>>>>>>>><>>>>>><<<<<><>>>>>>>>>>>>>>>>>>>>>>>E>>>>>><<<<<<<>>>>>>>>>>>>>>>>>>><><><>>>>>><<<<<<<><<<<<<<<<>>>>>>>>><><><>>>>>><<<<<<<>>><<<<<<<<<<<<<>>><><><>>>>>><<<<<<<><><<<<<<<<<<<<<>>><><><>>>>>><<<<<<<>>>>>>><<<>>>>>>>>><><><>>>>>><<<<<<<>>>>>>><<<>>>>>>>>><><><>>>>>><<<<<<<>>>>>>><<<>>>>>>>>><><><>>>>>><<<<<<<>>>>>>><<<>>>>>>>>><><><>>>>>><<<<<<<>>>>>>>>>>>>>>>>>>><><><>>>>>><<<<<<<>>>>>>>>>>>>>>>>>>><><><>>>>>><<<<<<<>>>>>>>>>>>>>>>>>>><><><>>>>>><<<<<<<<>><<<<<<<>>>>>>>>><><><>>>>>><<<<<<<<>><<<<<<<><>>>>>><<><><>>>>>><<<<<<<<>><<<<<<<>>>>>>>>><><><>>>>>><<<<<<<<>><<<<<<<>>>>>>>>><><><>>>>>><<<<<<<<>><<<<<<<>>>>>>>>><><><>>>>>><<<<<<<<>><<<<<<<>>>>>>>>><><><>>>>>><<<<<<<<<<<<<<<<<<<<<<<><<<><><>>>>>><<<<<<<<<<<<<<<<<<<<<<<<><<><><>>>>>><<<<<<<<<<<<<<<<<><>>>>>><<><><>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<=<=<=<<<<EEE<<<><>>>>>>>>>>>>>>>>>>>>>>>E>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<=<=<=<<<<EEE<<<><>>>>>>>>>>>>>>>>>>>E>>>E>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<=<=<=<<<<EEE<<<><>>>>>>>>>>>>>>>>>>>E>E>E>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<><><>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<><><><>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<><><><>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<><><><>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<E";
        Blackboard.bn = 0;
        Blackboard.bo = 1;
        Blackboard.bp = 2;
        Blackboard.bq = 3;
        Blackboard.br = new byte[Blackboard.bj][Blackboard.bj];
        for (int k = 0; k < Blackboard.bj; ++k) {
            for (int l = 0; l < Blackboard.bj; ++l) {
                final char char1 = Blackboard.bm.charAt(k * Blackboard.bj + l);
                byte b2 = Blackboard.bq;
                if (char1 == '<') {
                    b2 = Blackboard.bn;
                }
                else if (char1 == '>') {
                    b2 = Blackboard.bo;
                }
                else if (char1 == '=') {
                    b2 = Blackboard.bp;
                }
                Blackboard.br[k][l] = b2;
            }
        }
    }
}
