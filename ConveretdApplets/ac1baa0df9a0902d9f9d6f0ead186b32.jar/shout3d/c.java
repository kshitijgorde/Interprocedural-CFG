// 
// Decompiled by Procyon v0.5.30
// 

package shout3d;

import shout3d.core.StringField;
import shout3d.core.DoubleField;
import shout3d.core.BooleanField;
import shout3d.core.IntField;
import shout3d.core.FloatField;
import shout3d.core.BooleanArrayField;
import shout3d.core.DoubleArrayField;
import shout3d.core.StringArrayField;
import shout3d.core.IntArrayField;
import shout3d.core.FloatArrayField;
import shout3d.core.ArrayField;
import shout3d.core.NodeField;
import shout3d.core.NodeArrayField;
import shout3d.core.Field;
import shout3d.core.Shout3DException;
import shout3d.core.Node;
import java.util.Vector;
import java.net.URLConnection;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Hashtable;
import shout3d.core.Group;
import java.net.URL;
import shout3d.core.e;
import java.io.StreamTokenizer;
import shout3d.core.Shout3DViewer;

class c implements shout3d.core.c
{
    static final String a = "STOP IMMEDIATELY!";
    static final String b = "Copyright (c) 1997-1999 Shout Interactive, Inc.";
    static final String c = "Contents of this file are property of Shout Interactive, Inc.";
    static final String d = "If you are reading this text, then you are in direct violation";
    static final String e = "of the terms of use and the terms of the license agreement.";
    static final String f = "YOU MUST CEASE YOUR EXAMINATION OF THIS FILE AND DESTROY THIS FILE IMMEDIATELY.";
    private Shout3DViewer g;
    private StreamTokenizer h;
    private a i;
    private e j;
    private URL k;
    private Group l;
    private Hashtable m;
    private float n;
    private float o;
    private float p;
    private float q;
    private float r;
    private boolean s;
    private boolean t;
    private float u;
    private float v;
    private int w;
    private String x;
    private Shout3DPanel y;
    private static final int z = 31;
    private int A;
    private int B;
    private int C;
    private int D;
    Hashtable E;
    private boolean F;
    private boolean G;
    private boolean H;
    private boolean I;
    private int J;
    private float K;
    private long L;
    private long M;
    private String N;
    private int O;
    private int P;
    
    public Object a() {
        return this.j;
    }
    
    private void f() {
        try {
            this.m = new Hashtable();
            this.i = new a(this.j.a());
            if (!this.t && this.j.a()[0] == 31) {
                this.t = true;
            }
            if (this.t) {
                final a[] array = { null };
                new b().a(this.i, array);
                this.i.close();
                this.i = array[0];
                this.h = new StreamTokenizer(array[0]);
                this.w = (int)(this.n * this.v + 0.5f);
            }
            else {
                this.h = new StreamTokenizer(this.i);
                this.w = (int)(this.n * this.u + 0.5f);
            }
            if (this.h != null) {
                this.h.ordinaryChar(this.A);
                this.h.ordinaryChar(this.B);
                this.h.ordinaryChar(this.C);
                this.h.ordinaryChar(this.D);
                this.h.wordChars(95, 95);
                this.h.wordChars(33, 33);
                this.h.wordChars(36, 43);
                this.h.wordChars(45, 45);
                this.h.whitespaceChars(44, 44);
                this.h.wordChars(63, 63);
                this.h.eolIsSignificant(true);
                this.h.parseNumbers();
                if (this.y != null) {
                    this.x = "Parsing Scene";
                    this.o = 0.0f;
                    this.p = 0.0f;
                    this.q = 0.0f;
                    this.y.i();
                }
                this.a(this.l);
                this.p = 1.0f;
                this.s = false;
            }
        }
        catch (Exception ex) {
            System.err.println("Failed to parse scene: " + this.e());
        }
    }
    
    private boolean g() {
        this.u();
        return this.h.ttype == this.A;
    }
    
    protected boolean h() {
        return this.s;
    }
    
    protected void a(final URL k, final boolean t, final Group l) {
        this.k = k;
        this.t = t;
        this.l = l;
        this.d();
    }
    
    public int b() {
        if (this.t) {
            return 1;
        }
        return 0;
    }
    
    protected String i() {
        return this.x;
    }
    
    protected void j() {
        if (this.m != null) {
            this.m.clear();
        }
    }
    
    private boolean[] l() {
        this.g();
        int n = 0;
        boolean[] array = new boolean[10];
        this.u();
        while (this.h.ttype == -3) {
            if (n >= array.length) {
                final boolean[] array2 = new boolean[array.length + 10];
                System.arraycopy(array, 0, array2, 0, array.length);
                array = array2;
            }
            array[n++] = new Boolean(this.h.sval);
            this.u();
        }
        final boolean[] array3 = new boolean[n];
        System.arraycopy(array, 0, array3, 0, n);
        return array3;
    }
    
    private float[] m() {
        boolean b = false;
        if (this.g()) {
            b = true;
        }
        else {
            this.h.pushBack();
        }
        int n = 0;
        float[] array = new float[10];
        this.u();
        while (this.h.ttype == -2) {
            if (n >= array.length) {
                final float[] array2 = new float[array.length + 1000];
                System.arraycopy(array, 0, array2, 0, array.length);
                array = array2;
            }
            array[n++] = (float)this.a(this.h.nval);
            this.u();
        }
        this.h.pushBack();
        final float[] array3 = new float[n];
        System.arraycopy(array, 0, array3, 0, n);
        if (b) {
            this.z();
        }
        return array3;
    }
    
    public void c() {
        if (this.g != null) {
            this.g.a().a(this.g, this);
        }
    }
    
    public void d() {
        System.gc();
        System.gc();
        System.gc();
        this.g.a().a(this.E());
        final Object b = this.g.a().b(this.k);
        if (b != null) {
            final e e = (e)b;
            if (e.a() != null) {
                this.j.a(e.a());
            }
            else {
                this.n();
            }
        }
        else {
            this.n();
        }
        this.f();
    }
    
    private void n() {
        this.n = 0.0f;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = 0.0f;
        this.r = 0.1f;
        this.s = true;
        this.x = "Loading models";
        if (this.y != null) {
            this.y.i();
        }
        this.l = this.l;
        InputStream inputStream = null;
        try {
            final URLConnection openConnection = this.k.openConnection();
            openConnection.setUseCaches(true);
            this.n = openConnection.getContentLength();
            if (this.n == 0.0f) {
                this.n = Float.MAX_VALUE;
            }
            inputStream = openConnection.getInputStream();
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            final byte[] array = new byte[512];
            this.r = 0.1f;
            this.q = 0.0f;
            this.o = 0.0f;
            this.p = 0.0f;
            while (true) {
                final int read = inputStream.read(array);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(array, 0, read);
                byteArrayOutputStream.flush();
                this.o += read;
                this.p = this.o / this.n;
                if (this.y == null || this.p - this.q < this.r) {
                    continue;
                }
                this.y.i();
                this.q = this.p;
            }
            this.p = 1.0f;
            if (this.y != null) {
                this.y.i();
            }
            this.j.a(byteArrayOutputStream.toByteArray());
            inputStream.close();
        }
        catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        catch (IOException ex2) {
            System.err.println(ex2);
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException ex3) {
                    System.err.println(ex3);
                }
            }
            if (this.i != null) {
                try {
                    this.i.close();
                }
                catch (IOException ex4) {
                    System.err.println(ex4);
                }
            }
            this.i = null;
        }
        this.p = 1.0f;
        if (this.y != null) {
            this.y.i();
        }
    }
    
    protected float o() {
        if (this.p > 1.0f) {
            this.p = 1.0f;
        }
        else if (this.p < 0.0f) {
            this.p = 0.0f;
        }
        return this.p;
    }
    
    private String p() {
        this.u();
        return this.h.sval;
    }
    
    private void a(final Group group) {
        final Vector vector = new Vector<Node>();
        while (this.h.ttype != -1) {
            this.C();
            final Node a = this.A();
            if (a != null) {
                vector.addElement(a);
            }
            this.u();
        }
        if (vector.size() > 0) {
            final Node[] value = new Node[vector.size()];
            vector.copyInto(value);
            group.children.setValue(value);
        }
        if (this.y != null) {
            this.p = 1.0f;
            this.y.i();
        }
    }
    
    private double[] q() {
        final float[] m = this.m();
        final double[] array = new double[m.length];
        System.arraycopy(m, 0, array, 0, m.length);
        return array;
    }
    
    private int[] r() {
        final float[] m = this.m();
        final int[] array = new int[m.length];
        for (int i = 0; i < m.length; ++i) {
            array[i] = (int)m[i];
        }
        return array;
    }
    
    private String[] s() {
        if (!this.g()) {
            this.h.pushBack();
            return new String[] { this.p() };
        }
        int n = 0;
        String[] array = new String[10];
        this.u();
        while (this.h.ttype != this.B) {
            if (n >= array.length) {
                final String[] array2 = new String[array.length + 10];
                System.arraycopy(array, 0, array2, 0, array.length);
                array = array2;
            }
            array[n++] = this.h.sval;
            this.u();
        }
        final String[] array3 = new String[n];
        System.arraycopy(array, 0, array3, 0, n);
        return array3;
    }
    
    private void t() {
        Field field = null;
        Field field2 = null;
        final boolean b = false;
        this.I = b;
        this.H = b;
        this.G = b;
        this.F = b;
        final String p = this.p();
        final String substring = p.substring(p.lastIndexOf(".") + 1);
        final Node node = this.m.get(p.substring(0, p.lastIndexOf(".")));
        if (node == null) {
            this.F = true;
        }
        else {
            try {
                field = node.getField(substring);
            }
            catch (Shout3DException ex) {
                this.H = true;
            }
            if (this.H && substring.lastIndexOf("_changed") == substring.length() - 8) {
                this.H = false;
                try {
                    field = node.getField(substring.substring(0, substring.length() - 8));
                }
                catch (Shout3DException ex2) {
                    this.H = true;
                }
            }
        }
        this.p();
        final String p2 = this.p();
        final String substring2 = p2.substring(p2.lastIndexOf(".") + 1);
        final Node node2 = this.m.get(p2.substring(0, p2.lastIndexOf(".")));
        if (node2 == null) {
            this.G = true;
        }
        else {
            try {
                field2 = node2.getField(substring2);
            }
            catch (Shout3DException ex3) {
                this.I = true;
            }
            if (this.I && substring2.indexOf("set_") == 0) {
                this.I = false;
                try {
                    field2 = node2.getField(substring2.substring(4));
                }
                catch (Shout3DException ex4) {
                    this.I = true;
                }
            }
        }
        if (this.F || this.G || this.H || this.I) {
            System.out.println("Skipping Route from " + p + " to " + p2);
            if (this.F) {
                System.out.println("    node " + p.substring(0, p.lastIndexOf(".")) + " not found.");
            }
            if (this.H) {
                System.out.println("    field " + substring + " not found in node " + p.substring(0, p.lastIndexOf(".")));
            }
            if (this.G) {
                System.out.println("    node " + p2.substring(0, p2.lastIndexOf(".")) + " not found.");
            }
            if (this.I) {
                System.out.println("    field " + substring2 + " not found in node " + p2.substring(0, p2.lastIndexOf(".")));
            }
        }
        else {
            try {
                this.g.addRoute(field, field2);
            }
            catch (Exception ex5) {
                System.out.println("Skipping ROUTE due to field type mismatch: from " + p + " to " + p2);
            }
        }
    }
    
    private void u() {
        try {
            this.h.nextToken();
            if (this.i != null) {
                final float p = this.p;
                final float a = this.i.a();
                this.K = a;
                if (p < a) {
                    this.p = this.K;
                    this.y.i();
                }
            }
            if (this.h.ttype == 35) {
                while (this.h.ttype != 10 && this.h.ttype != -1) {
                    this.h.nextToken();
                }
                this.u();
            }
            if (this.h.ttype == 10) {
                this.u();
            }
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    private boolean v() {
        this.u();
        return this.h.ttype == this.D;
    }
    
    private boolean w() {
        this.u();
        return new Boolean(this.h.sval);
    }
    
    private float x() {
        return (float)this.y();
    }
    
    public c(final Shout3DViewer g, final Shout3DPanel y) {
        this.j = new e();
        this.l = new Group();
        this.m = null;
        this.s = false;
        this.t = false;
        this.u = 0.0125f;
        this.v = 0.03921f;
        this.w = 0;
        this.x = "";
        this.A = 91;
        this.B = 93;
        this.C = 123;
        this.D = 125;
        this.E = new Hashtable();
        this.J = 0;
        this.K = -1.0f;
        this.L = 0L;
        this.M = 150L;
        this.g = g;
        this.y = y;
    }
    
    public c(final Shout3DViewer g, final Shout3DPanel y, final URL k, final boolean t, final Group l) {
        this.j = new e();
        this.l = new Group();
        this.m = null;
        this.s = false;
        this.t = false;
        this.u = 0.0125f;
        this.v = 0.03921f;
        this.w = 0;
        this.x = "";
        this.A = 91;
        this.B = 93;
        this.C = 123;
        this.D = 125;
        this.E = new Hashtable();
        this.J = 0;
        this.K = -1.0f;
        this.L = 0L;
        this.M = 150L;
        this.g = g;
        this.y = y;
        this.k = k;
        this.l = l;
        this.t = t;
    }
    
    private double y() {
        double a = 0.0;
        try {
            this.u();
            if (this.h.ttype == -1) {
                return a;
            }
            a = this.a(this.h.nval);
        }
        catch (Exception ex) {}
        return a;
    }
    
    private boolean z() {
        this.u();
        return this.h.ttype == this.B;
    }
    
    private Node A() {
        this.u();
        if (this.h.ttype == -1) {
            return null;
        }
        if (this.h.ttype == -3) {
            final String sval = this.h.sval;
            String p = null;
            String p2;
            if (sval.equals("DEF")) {
                p = this.p();
                p2 = this.p();
            }
            else {
                if (sval.equals("USE")) {
                    return this.m.get(this.p());
                }
                if (sval.equals("Route") || sval.equals("ROUTE")) {
                    this.t();
                    return null;
                }
                if (sval.equals("null") || sval.equals("NULL")) {
                    return null;
                }
                p2 = sval;
            }
            Class<?> clazz = null;
            final Object value = this.E.get(p2);
            if (value == null) {
                final String string = "shout3d.core." + p2;
                try {
                    clazz = Class.forName(string);
                }
                catch (ClassNotFoundException ex3) {}
                if (clazz == null) {
                    final String string2 = "custom_nodes." + p2;
                    try {
                        clazz = Class.forName(string2);
                    }
                    catch (ClassNotFoundException ex4) {}
                }
                if (clazz == null) {
                    final String string3 = "shout3d.sound." + p2;
                    try {
                        clazz = Class.forName(string3);
                    }
                    catch (ClassNotFoundException ex5) {}
                }
                if (clazz != null) {
                    this.E.put(p2, clazz);
                }
            }
            else {
                clazz = (Class<Node>)value;
            }
            if (clazz == null) {
                System.out.println("Skipping node of type " + p2);
                this.B();
                int i = 1;
                int n = 0;
                while (i != n) {
                    this.u();
                    if (this.h.ttype == this.D) {
                        ++n;
                    }
                    else {
                        if (this.h.ttype != this.C) {
                            continue;
                        }
                        ++i;
                    }
                }
            }
            else {
                try {
                    this.B();
                    final Node node = clazz.newInstance();
                    node.setViewer(this.g);
                    if (p != null) {
                        this.m.put(p, node);
                        node.setDEFName(p);
                    }
                    while (!this.v()) {
                        this.C();
                        final String p3 = this.p();
                        Field field = null;
                        try {
                            field = node.getField(p3);
                        }
                        catch (Shout3DException ex6) {}
                        if (field == null) {
                            if (p3.equals("Route") || p3.equals("ROUTE")) {
                                this.t();
                            }
                            else {
                                System.out.println("Skipping fieldName " + p3);
                                this.u();
                                int n2 = 0;
                                while (true) {
                                    if (this.h.ttype == this.C) {
                                        ++n2;
                                    }
                                    if (this.h.ttype == this.D) {
                                        if (n2 == 0) {
                                            this.C();
                                            break;
                                        }
                                        --n2;
                                    }
                                    if (this.h.ttype == -3) {
                                        try {
                                            field = node.getField(this.h.sval);
                                        }
                                        catch (Shout3DException ex7) {}
                                        if (field != null) {
                                            break;
                                        }
                                    }
                                    this.u();
                                }
                            }
                        }
                        if (field != null) {
                            if (field instanceof NodeArrayField) {
                                final Vector vector = new Vector<Node>();
                                if (this.g()) {
                                    while (!this.z()) {
                                        this.C();
                                        final Node a = this.A();
                                        if (a != null) {
                                            vector.addElement(a);
                                        }
                                    }
                                }
                                else {
                                    this.C();
                                    final Node a2 = this.A();
                                    if (a2 != null) {
                                        vector.addElement(a2);
                                    }
                                }
                                if (vector.size() <= 0) {
                                    continue;
                                }
                                final Node[] value2 = new Node[vector.size()];
                                vector.copyInto(value2);
                                ((NodeArrayField)field).setValue(value2);
                            }
                            else if (field instanceof NodeField) {
                                ((NodeField)field).setValue(this.A());
                            }
                            else if (field instanceof ArrayField) {
                                if (field instanceof FloatArrayField) {
                                    ((FloatArrayField)field).setValue(this.m());
                                }
                                else if (field instanceof IntArrayField) {
                                    ((IntArrayField)field).setValue(this.r());
                                }
                                else if (field instanceof StringArrayField) {
                                    ((StringArrayField)field).setValue(this.s());
                                }
                                else if (field instanceof DoubleArrayField) {
                                    ((DoubleArrayField)field).setValue(this.q());
                                }
                                else {
                                    if (!(field instanceof BooleanArrayField)) {
                                        continue;
                                    }
                                    ((BooleanArrayField)field).setValue(this.l());
                                }
                            }
                            else if (field instanceof FloatField) {
                                ((FloatField)field).setValue(this.x());
                            }
                            else if (field instanceof IntField) {
                                ((IntField)field).setValue(this.F());
                            }
                            else if (field instanceof BooleanField) {
                                ((BooleanField)field).setValue(this.w());
                            }
                            else if (field instanceof DoubleField) {
                                ((DoubleField)field).setValue(this.y());
                            }
                            else {
                                if (!(field instanceof StringField)) {
                                    continue;
                                }
                                ((StringField)field).setValue(this.p());
                            }
                        }
                    }
                    return node;
                }
                catch (InstantiationException ex) {
                    System.err.println(ex);
                }
                catch (IllegalAccessException ex2) {
                    System.err.println(ex2);
                }
            }
        }
        return null;
    }
    
    private boolean B() {
        this.u();
        return this.h.ttype == this.C;
    }
    
    private double a(double n) {
        this.u();
        if (this.h.ttype == -3) {
            final String lowerCase = this.h.sval.toLowerCase();
            this.N = lowerCase;
            if (lowerCase.startsWith("e")) {
                try {
                    this.N = this.N.substring(this.N.startsWith("e+") ? 2 : 1);
                    this.P = this.N.charAt(0);
                    if (this.P == 43 || this.P == 45 || (this.P > 47 && this.P < 58)) {
                        n *= Math.pow(10.0, Integer.valueOf(this.N));
                    }
                    else {
                        this.C();
                    }
                }
                catch (Exception ex) {}
            }
            else {
                this.C();
            }
        }
        else {
            this.C();
        }
        return n;
    }
    
    public URL e() {
        return this.k;
    }
    
    public void a(final Object o) {
    }
    
    private void C() {
        this.h.pushBack();
    }
    
    private e D() {
        return this.j;
    }
    
    public URL E() {
        if (this.k != null) {
            try {
                return new URL(this.k.toString().substring(0, this.k.toString().lastIndexOf("/") + 1));
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    private int F() {
        this.u();
        return (int)this.h.nval;
    }
}
