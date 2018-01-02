// 
// Decompiled by Procyon v0.5.30
// 

package panoStudioViewer;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Point;

public class C
{
    public static final double X = 0.16666666666666666;
    public static final double q = 0.16666666666666666;
    public static final double P = 1.3;
    public static final int µ = 0;
    public static final int \u00c7 = 1;
    int f;
    int E;
    int Y;
    int o;
    int r;
    int K;
    int O;
    boolean \u00c4;
    boolean s;
    int g;
    int \u00c3;
    int ª;
    int ¥;
    int \u00c9;
    int \u00c8;
    int \u00c6;
    int \u00c5;
    int M;
    int L;
    int R;
    int Q;
    E \u00c2;
    E p;
    _A[] w;
    _A t;
    _A l;
    Point z;
    long H;
    String[] h;
    int[] e;
    int[] c;
    int[] b;
    int[] a;
    int[] _;
    PanoStudioViewer T;
    Rectangle º;
    E W;
    Image U;
    E F;
    E j;
    E y;
    E G;
    E \u00c1;
    E n;
    E ¢;
    E N;
    E \u00c0;
    E m;
    E S;
    E D;
    E C;
    E u;
    E A;
    E J;
    E ¤;
    E V;
    static byte[] v;
    static byte[] B;
    static byte[] Z;
    static byte[] x;
    static byte[] k;
    static byte[] I;
    static byte[] d;
    static byte[] £;
    static byte[] i;
    
    static {
        C.v = new byte[] { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        C.B = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        C.Z = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        C.x = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        C.k = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        C.I = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        C.d = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        C.£ = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        C.i = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
    
    public C(final PanoStudioViewer t) {
        this.f = 0;
        this.E = 0;
        this.Y = 0;
        this.o = 0;
        this.r = 128;
        this.K = 192;
        this.O = 255;
        this.\u00c4 = true;
        this.s = false;
        this.g = 24;
        this.\u00c3 = 20;
        this.t = null;
        this.l = null;
        this.h = new String[] { "Abspielen", "Nach links", "Nach rechts", "Nach oben", "Nach unten", "Einzoomen", "Auszoomen", "Hotspots ein-/ausblenden", "Pause" };
        this.e = new int[] { -1250856, -1250856, -16761740, -197380, -1907998, -3217409, -9862418 };
        this.c = new int[] { -10066330, -16777216, -1, -16777216, -7829368, -7829249, -30720 };
        this.b = new int[] { -16777093, -16777216, -1, -16777216, -15592715, -30720, -7829249 };
        this.a = new int[] { -12886883, -16777216, -16768933, -65794, -4339231, -1, -10111259 };
        this._ = new int[] { -2039837, -2039837, -16761740, -1, -3750441, -3217409, -9862418 };
        this.T = t;
        this.f = this.T.u;
        this.E = this.T.L;
        this.Y = this.T.\u00f1;
        this.o = this.T.J;
        this.r = this.T.\u00d5;
        this.K = this.T.\u00da;
        this.O = this.T.t;
        this.\u00c4 = this.T.Z;
        this.s = this.T.\u00cf;
        this.g = this.T.Q;
        this.\u00c3 = this.T.R;
        if (this.o < 0) {
            this.o = 0;
        }
        else if (this.o >= 5) {
            this.o = 0;
        }
        int[] array;
        if (this.o == 0) {
            array = this.e;
        }
        else if (this.o == 1) {
            array = this.c;
        }
        else if (this.o == 2) {
            array = this.b;
        }
        else if (this.o == 3) {
            array = this.a;
        }
        else {
            array = this._;
        }
        this.ª = array[0];
        this.\u00c9 = array[2];
        this.M = array[3];
        this.L = array[4];
        this.R = array[5];
        this.Q = array[6];
        this.\u00c8 = this.A(this.\u00c9, this.M, 0.25);
        this.\u00c6 = this.A(this.\u00c9, this.M, 0.5);
        this.\u00c5 = this.A(this.\u00c9, this.M, 0.75);
        this.\u00c2 = new E(this.g, this.\u00c3);
        this.p = new E(this.g, this.\u00c3);
        this.C(this.\u00c2, this.K);
        this.A(this.p, this.O);
        if (this.T.\u00d3 == PanoStudioViewer.U) {
            this.h[0] = "Play";
            this.h[1] = "Left";
            this.h[2] = "Right";
            this.h[3] = "Up";
            this.h[4] = "Down";
            this.h[5] = "Zoom in";
            this.h[6] = "Zoom out";
            this.h[7] = "Show/Hide hotspots";
            this.h[8] = "Pause";
        }
        else if (this.T.\u00d3 == PanoStudioViewer.\u00fc) {
            this.h[0] = "Lecture";
            this.h[1] = "Gauche";
            this.h[2] = "Droite";
            this.h[3] = "Haut";
            this.h[4] = "Bas";
            this.h[5] = "Agrandir";
            this.h[6] = "R\u00e9duire";
            this.h[7] = "Afficher/masquer les hotspots";
            this.h[8] = "Pause";
        }
        else if (this.T.\u00d3 == PanoStudioViewer.\u00e4) {
            this.h[0] = "G\u00f6ster";
            this.h[1] = "Sol tarafa";
            this.h[2] = "Sa\u011f tarafa";
            this.h[3] = "Yukar\u0131";
            this.h[4] = "A\u015fa\u011f\u0131";
            this.h[5] = "Yakla\u015ft\u0131r";
            this.h[6] = "Uzakla\u015ft\u0131r";
            this.h[7] = "Ge\u00e7i\u015fleri g\u00f6ster / gizle";
            this.h[8] = "Durdur";
        }
        else {
            this.h[0] = "Abspielen";
            this.h[1] = "Nach links";
            this.h[2] = "Nach rechts";
            this.h[3] = "Nach oben";
            this.h[4] = "Nach unten";
            this.h[5] = "Einzoomen";
            this.h[6] = "Auszoomen";
            this.h[7] = "Hotspots ein-/ausblenden";
            this.h[8] = "Pause";
        }
        this.A();
        this.º = this.B();
        this.B(this.W = new E(this.º.width, this.º.height), this.r);
        this.U = this.T.createImage(this.W.W);
    }
    
    _A D(final int n, final int n2) {
        for (int i = 0; i < this.w.length; ++i) {
            if (this.w[i] != null && this.w[i].A(n, n2) && (!this.T.o || i != 0)) {
                return this.w[i];
            }
        }
        return null;
    }
    
    _A A(final int n, final int n2) {
        return this.t = this.D(n, n2);
    }
    
    _A C(final int n, final int n2) {
        this.l = this.D(n, n2);
        this.z = new Point(n, n2);
        this.H = System.currentTimeMillis();
        return this.l;
    }
    
    void A(final _A a) {
        if (a == this.w[8]) {
            this.T.toggleHotspots();
        }
    }
    
    int A(final int n, final int n2, final double n3) {
        int n4 = (int)((n >> 24 & 0xFF) * (1.0 - n3) + (n2 >> 24 & 0xFF) * n3);
        if (this.T.V && n4 != 0) {
            n4 = 255;
        }
        return (int)(((n & 0xFF0000) >> 16) * (1.0 - n3) + ((n2 & 0xFF0000) >> 16) * n3) << 16 | (int)(((n & 0xFF00) >> 8) * (1.0 - n3) + ((n2 & 0xFF00) >> 8) * n3) << 8 | (int)((n & 0xFF) * (1.0 - n3) + (n2 & 0xFF) * n3) | n4 << 24;
    }
    
    int B(final int n, final int n2) {
        return (n & 0xFFFFFF) | (n2 & 0xFF) << 24;
    }
    
    public void C(final E e, int n) {
        if (this.T.V) {
            n = 255;
        }
        final int n2 = n;
        final int b = this.B(this.\u00c9, n2);
        final int b2 = this.B(this.\u00c8, n2);
        final int b3 = this.B(this.\u00c6, n2);
        final int b4 = this.B(this.\u00c5, n2);
        final int b5 = this.B(this.M, n2);
        final int b6 = this.B(this.L, n2);
        final int v = e.V;
        final int q = e.Q;
        if (this.Y == 0) {
            for (int i = 4; i < v - 4; ++i) {
                e.f[1 * v + i] = b;
                e.f[(q - 2) * v + i] = b;
            }
            for (int j = 4; j < q - 4; ++j) {
                e.f[j * v + 1] = b;
                e.f[j * v + v - 2] = b;
            }
            for (int k = 2; k < q - 2; ++k) {
                final int a = this.A(b5, b6, (k - 2) / (q - 4));
                for (int l = 2; l < v - 2; ++l) {
                    e.f[k * v + l] = a;
                }
            }
            e.f[1 * v + 3] = b2;
            e.f[2 * v + 2] = b2;
            e.f[3 * v + 1] = b2;
            e.f[1 * v + 2] = b3;
            e.f[2 * v + 1] = b3;
            e.f[2 * v + 3] = b4;
            e.f[3 * v + 2] = b4;
            e.f[(q - 2) * v + v - 4] = b2;
            e.f[(q - 3) * v + v - 3] = b2;
            e.f[(q - 4) * v + v - 2] = b2;
            e.f[(q - 2) * v + v - 3] = b3;
            e.f[(q - 3) * v + v - 2] = b3;
            e.f[(q - 3) * v + v - 4] = b4;
            e.f[(q - 4) * v + v - 3] = b4;
            e.f[1 * v + v - 4] = b2;
            e.f[2 * v + v - 3] = b2;
            e.f[3 * v + v - 2] = b2;
            e.f[1 * v + v - 3] = b3;
            e.f[2 * v + v - 2] = b3;
            e.f[2 * v + v - 4] = b4;
            e.f[3 * v + v - 3] = b4;
            e.f[(q - 2) * v + 3] = b2;
            e.f[(q - 3) * v + 2] = b2;
            e.f[(q - 4) * v + 1] = b2;
            e.f[(q - 2) * v + 2] = b3;
            e.f[(q - 3) * v + 1] = b3;
            e.f[(q - 3) * v + 3] = b4;
            e.f[(q - 4) * v + 2] = b4;
        }
        else {
            for (int n3 = 1; n3 < v - 1; ++n3) {
                e.f[1 * v + n3] = b;
                e.f[(q - 2) * v + n3] = b;
                e.f[(q - 1) * v + n3 + 1] = b3;
            }
            for (int n4 = 1; n4 < q - 1; ++n4) {
                e.f[n4 * v + 1] = b;
                e.f[n4 * v + v - 2] = b;
                e.f[(n4 + 1) * v + v - 1] = b3;
            }
            for (int n5 = 2; n5 < q - 2; ++n5) {
                final int a2 = this.A(b5, b6, (n5 - 2) / (q - 4));
                for (int n6 = 2; n6 < v - 2; ++n6) {
                    e.f[n5 * v + n6] = a2;
                }
            }
        }
    }
    
    public void A(final E e, int n) {
        if (this.T.V) {
            n = 255;
        }
        this.C(e, n);
        final int b = this.B(this.R, n);
        final int b2 = this.B(this.Q, n);
        final int v = e.V;
        final int q = e.Q;
        if (this.Y == 0) {
            for (int i = 3; i < v - 3; ++i) {
                e.f[2 * v + i] = b;
                e.f[(q - 3) * v + i] = b2;
            }
        }
        else {
            for (int j = 2; j < v - 2; ++j) {
                e.f[2 * v + j] = b;
                e.f[(q - 3) * v + j] = b2;
            }
        }
        for (int k = 3; k < q - 3; ++k) {
            final int a = this.A(b, b2, (k - 2) / (q - 4) / 2.0 + 0.25);
            if (k == 3 || k == q - 4) {
                for (int l = 2; l < v - 2; ++l) {
                    e.f[k * v + l] = a;
                }
            }
            else {
                for (int n2 = 2; n2 < 4; ++n2) {
                    e.f[k * v + n2] = a;
                }
                for (int n3 = v - 4; n3 < v - 2; ++n3) {
                    e.f[k * v + n3] = a;
                }
            }
        }
    }
    
    void B(final E e, int n) {
        if (this.T.V) {
            n = 255;
        }
        final int b = this.B(this.ª, n);
        final int b2 = this.B(this.\u00c9, n);
        for (int i = 0; i < e.Q; ++i) {
            for (int j = 0; j < e.V; ++j) {
                e.f[i * e.V + j] = b;
            }
        }
        for (int k = 0; k < e.V; ++k) {
            e.f[k] = b2;
            e.f[(e.Q - 1) * e.V + k] = b2;
        }
        for (int l = 0; l < e.Q; ++l) {
            e.f[l * e.V] = b2;
            e.f[l * e.V + e.V - 1] = b2;
        }
        if (this.Y == 0) {
            e.f[0] = 0;
            e.f[e.V - 1] = 0;
            e.f[(e.Q - 1) * e.V + 0] = 0;
            e.f[(e.Q - 1) * e.V + e.V - 1] = 0;
        }
    }
    
    public void A(final Graphics graphics, final int n, final int n2) {
        if (this.s) {
            graphics.drawImage(this.U, this.º.x, this.º.y, null);
        }
        this.B(graphics, n, n2);
    }
    
    void B(final Graphics graphics, final int n, final int n2) {
        for (int i = 0; i < this.w.length; ++i) {
            if (!this.T.o || i != 0) {
                if (this.T.o || i != 8) {
                    if (this.w[i] != null) {
                        this.w[i].A(graphics);
                    }
                }
            }
        }
    }
    
    Rectangle A(final int n, final int n2, final boolean b) {
        final Dimension size = this.T.getSize();
        int width = 1;
        int height = 1;
        if (size != null) {
            width = size.width;
            height = size.height;
        }
        if (!b) {
            return new Rectangle(0, height - 30, width, 30);
        }
        if (this.E == 1) {
            return new Rectangle(this.º.x + this.º.width, 0, width - (this.º.x + this.º.width), 30);
        }
        return new Rectangle(this.º.x + this.º.width, height - 30, width - (this.º.x + this.º.width), 30);
    }
    
    void B(final E e, final byte[] array, final int n) {
        final int n2 = 255;
        final int b = this.B(this.\u00c9, n2);
        final int n3 = (this.Y == 0) ? this.B(this.\u00c6, n2) : this.B(this.\u00c8, n2);
        final int v = e.V;
        final int q = e.Q;
        for (int i = 1; i < q; ++i) {
            for (int j = 0; j < v; ++j) {
                if (array[i * v + j] != 0) {
                    e.f[(i - 1) * v + j] = n3;
                }
            }
        }
        for (int k = 0; k < q; ++k) {
            for (int l = 0; l < v; ++l) {
                if (array[k * v + l] != 0) {
                    e.f[k * v + l] = b;
                }
            }
        }
    }
    
    void A(final E e, final byte[] array, final int n) {
        final int n2 = 255;
        final int b = this.B(this.\u00c9, n2);
        final int n3 = (this.Y == 0) ? this.B(this.\u00c6, n2) : this.B(this.\u00c8, n2);
        final int v = e.V;
        final int q = e.Q;
        for (int i = 1; i < q; ++i) {
            for (int j = 0; j < v; ++j) {
                if (array[4 * i * v + 2 * j] != 0) {
                    e.f[(i - 1) * v + j] = n3;
                }
            }
        }
        for (int k = 0; k < q; ++k) {
            for (int l = 0; l < v; ++l) {
                if (array[4 * k * v + 2 * l] != 0) {
                    e.f[k * v + l] = b;
                }
            }
        }
    }
    
    void A() {
        final E f = new E(24, 24);
        this.B(f, panoStudioViewer.C.v, this.K);
        this.F = f;
        final E j = new E(24, 24);
        this.B(j, panoStudioViewer.C.B, this.K);
        this.j = j;
        final E y = new E(24, 24);
        this.B(y, panoStudioViewer.C.Z, this.K);
        this.y = y;
        final E g = new E(24, 24);
        this.B(g, panoStudioViewer.C.x, this.K);
        this.G = g;
        final E \u00e1 = new E(24, 24);
        this.B(\u00e1, panoStudioViewer.C.k, this.K);
        this.\u00c1 = \u00e1;
        final E n = new E(24, 24);
        this.B(n, panoStudioViewer.C.I, this.K);
        this.n = n;
        final E ¢ = new E(24, 24);
        this.B(¢, panoStudioViewer.C.d, this.K);
        this.¢ = ¢;
        final E n2 = new E(24, 24);
        this.B(n2, panoStudioViewer.C.£, this.K);
        this.N = n2;
        final E \u00e0 = new E(24, 24);
        this.B(\u00e0, panoStudioViewer.C.i, this.K);
        this.\u00c0 = \u00e0;
        final E m = new E(12, 12);
        this.A(m, panoStudioViewer.C.v, this.K);
        this.m = m;
        final E s = new E(12, 12);
        this.A(s, panoStudioViewer.C.B, this.K);
        this.S = s;
        final E d = new E(12, 12);
        this.A(d, panoStudioViewer.C.Z, this.K);
        this.D = d;
        final E c = new E(12, 12);
        this.A(c, panoStudioViewer.C.x, this.K);
        this.C = c;
        final E u = new E(12, 12);
        this.A(u, panoStudioViewer.C.k, this.K);
        this.u = u;
        final E a = new E(12, 12);
        this.A(a, panoStudioViewer.C.I, this.K);
        this.A = a;
        final E i = new E(12, 12);
        this.A(i, panoStudioViewer.C.d, this.K);
        this.J = i;
        final E ¤ = new E(12, 12);
        this.A(¤, panoStudioViewer.C.£, this.K);
        this.¤ = ¤;
        final E v = new E(12, 12);
        this.A(v, panoStudioViewer.C.i, this.K);
        this.V = v;
    }
    
    _A A(final E e, final E e2, final E e3, final int n, final int n2, final _B b, final _B b2, final int n3, final int n4, final String s) {
        final E e4 = new E(e.V, e.Q);
        System.arraycopy(e.f, 0, e4.f, 0, e4.Q * e4.V);
        e4.A(e3, (e4.V - e3.V) / 2, (e4.Q - e3.Q) / 2, n3);
        final E e5 = new E(e2.V, e2.Q);
        System.arraycopy(e2.f, 0, e5.f, 0, e5.Q * e5.V);
        e5.A(e3, (e5.V - e3.V) / 2, (e5.Q - e3.Q) / 2, n4);
        return new _A(e4, e5, null, n, n2, b, b2, s);
    }
    
    Rectangle B() {
        this.w = new _A[9];
        final E[] array = { this.m, this.D, this.C, this.u, this.A, this.J, this.¤, this.V, this.S };
        final E[] array2 = { this.F, this.y, this.G, this.\u00c1, this.n, this.¢, this.N, this.\u00c0, this.j };
        final E[] array3 = (this.g >= 30 && this.\u00c3 >= 30) ? array2 : array;
        final _B[] array4 = new _B[9];
        final _B[] array5 = new _B[9];
        array4[0] = new _B(2);
        array4[8] = new _B(2);
        array4[7] = new _B(0);
        array5[1] = new _B(1, -0.16666666666666666, 0.0, 1.0, 0.0);
        array5[2] = new _B(1, 0.16666666666666666, 0.0, 1.0, 0.0);
        array5[3] = new _B(1, 0.0, -0.16666666666666666, 1.0, 0.0);
        array5[4] = new _B(1, 0.0, 0.16666666666666666, 1.0, 0.0);
        array5[5] = new _B(1, 0.0, 0.0, 0.7692307692307692, 0.0);
        array5[6] = new _B(1, 0.0, 0.0, 1.3, 0.0);
        final Dimension size = this.T.getSize();
        int height = 1;
        if (size != null) {
            final int width = size.width;
            height = size.height;
        }
        final boolean b = this.E == 1;
        int n = 4;
        final int n2 = b ? 4 : (height - this.\u00c3 - 4);
        final Rectangle rectangle = new Rectangle();
        rectangle.x = 1;
        rectangle.y = 0;
        if (this.f == 0) {
            if (this.T.\u00f6.y) {
                this.w[7] = this.A(this.\u00c2, this.p, array3[7], n, n2, array4[7], array5[7], this.K, this.O, this.h[7]);
                n += this.g + 1;
            }
            for (int i = 1; i < 7; ++i) {
                this.w[i] = this.A(this.\u00c2, this.p, array3[i], n, n2, array4[i], array5[i], this.K, this.O, this.h[i]);
                n += this.g + 1;
            }
            if (this.\u00c4) {
                this.w[0] = this.A(this.\u00c2, this.p, array3[0], n, n2, array4[0], array5[0], this.K, this.O, this.h[0]);
                this.w[8] = this.A(this.\u00c2, this.p, array3[8], n, n2, array4[8], array5[8], this.K, this.O, this.h[8]);
                n += this.g + 1;
            }
            rectangle.y = ((this.E == 1) ? 1 : (n2 - 3));
            rectangle.height = ((this.E == 1) ? (1 * (this.\u00c3 + 2) + 3) : (height - rectangle.y - 1));
        }
        else if (this.f == 1) {
            final int n3 = b ? 0 : (0 - (2 * this.\u00c3 + 1));
            final int n4 = b ? (0 + (this.\u00c3 + 1)) : (0 - (this.\u00c3 + 1));
            final int n5 = b ? (0 + (2 * this.\u00c3 + 1)) : 0;
            if (this.T.\u00f6.y) {
                this.w[7] = this.A(this.\u00c2, this.p, array3[7], n, n2 + (b ? n5 : n3), array4[7], array5[7], this.K, this.O, this.h[7]);
            }
            this.w[1] = this.A(this.\u00c2, this.p, array3[1], n, n2 + n4, array4[1], array5[1], this.K, this.O, this.h[1]);
            this.w[5] = this.A(this.\u00c2, this.p, array3[5], n, n2 + (b ? n3 : n5), array4[5], array5[5], this.K, this.O, this.h[5]);
            final int n6 = n + (this.g + 1);
            this.w[3] = this.A(this.\u00c2, this.p, array3[3], n6, n2 + n3, array4[3], array5[3], this.K, this.O, this.h[3]);
            if (this.\u00c4) {
                this.w[0] = this.A(this.\u00c2, this.p, array3[0], n6, n2 + n4, array4[0], array5[0], this.K, this.O, this.h[0]);
                this.w[8] = this.A(this.\u00c2, this.p, array3[8], n6, n2 + n4, array4[8], array5[8], this.K, this.O, this.h[8]);
            }
            this.w[4] = this.A(this.\u00c2, this.p, array3[4], n6, n2 + n5, array4[4], array5[4], this.K, this.O, this.h[4]);
            final int n7 = n6 + (this.g + 1);
            this.w[2] = this.A(this.\u00c2, this.p, array3[2], n7, n2 + n4, array4[2], array5[2], this.K, this.O, this.h[2]);
            this.w[6] = this.A(this.\u00c2, this.p, array3[6], n7, n2 + (b ? n3 : n5), array4[6], array5[6], this.K, this.O, this.h[6]);
            n = n7 + (this.g + 1);
            rectangle.y = ((this.E == 1) ? 1 : (n2 - 2 * (this.\u00c3 + 2)));
            rectangle.height = ((this.E == 1) ? (3 * (this.\u00c3 + 2) + 3) : (height - rectangle.y - 1));
        }
        else if (this.f == 2) {
            final int n8 = b ? 0 : (0 - (this.\u00c3 + 2));
            final int n9 = b ? (0 + (this.\u00c3 + 2)) : 0;
            final int n10 = b ? (this.\u00c3 / 2 + 1) : (-(this.\u00c3 / 2 + 1));
            this.w[5] = this.A(this.\u00c2, this.p, array3[5], n, n2 + n8, array4[5], array5[5], this.K, this.O, this.h[5]);
            this.w[6] = this.A(this.\u00c2, this.p, array3[6], n, n2 + n9, array4[6], array5[6], this.K, this.O, this.h[6]);
            final int n11 = n + (this.g + 1);
            this.w[1] = this.A(this.\u00c2, this.p, array3[1], n11, n2 + n10, array4[1], array5[1], this.K, this.O, this.h[1]);
            final int n12 = n11 + (this.g + 1);
            this.w[3] = this.A(this.\u00c2, this.p, array3[3], n12, n2 + n8, array4[3], array5[3], this.K, this.O, this.h[3]);
            this.w[4] = this.A(this.\u00c2, this.p, array3[4], n12, n2 + n9, array4[4], array5[4], this.K, this.O, this.h[4]);
            final int n13 = n12 + (this.g + 1);
            this.w[2] = this.A(this.\u00c2, this.p, array3[2], n13, n2 + n10, array4[2], array5[2], this.K, this.O, this.h[2]);
            final int n14 = n13 + (this.g + 1);
            if (this.T.\u00f6.y) {
                this.w[7] = this.A(this.\u00c2, this.p, array3[7], n14, n2 + (b ? n8 : n9), array4[7], array5[7], this.K, this.O, this.h[7]);
            }
            if (this.\u00c4) {
                this.w[0] = this.A(this.\u00c2, this.p, array3[0], n14, n2 + (b ? n9 : n8), array4[0], array5[0], this.K, this.O, this.h[0]);
                this.w[8] = this.A(this.\u00c2, this.p, array3[8], n14, n2 + (b ? n9 : n8), array4[8], array5[8], this.K, this.O, this.h[8]);
            }
            n = n14 + (this.g + 1);
            rectangle.y = ((this.E == 1) ? 1 : (n2 - (this.\u00c3 + 2) - 3));
            rectangle.height = ((this.E == 1) ? (2 * (this.\u00c3 + 2) + 3) : (height - rectangle.y - 1));
        }
        rectangle.width = n - rectangle.x + 1;
        return rectangle;
    }
    
    class _A
    {
        int I;
        int H;
        int J;
        int E;
        Image F;
        Image M;
        Image L;
        E K;
        E A;
        E B;
        _B G;
        _B D;
        String C;
        
        _A(final E k, final E a, final E b, final int i, final int h, final _B g, final _B d, final String c) {
            this.G = g;
            this.D = d;
            this.C = c;
            this.I = i;
            this.H = h;
            this.K = k;
            this.B = b;
            this.A = a;
            if (k != null) {
                this.F = panoStudioViewer.C.this.T.createImage(k.W);
            }
            if (a != null) {
                this.M = panoStudioViewer.C.this.T.createImage(a.W);
            }
            if (b != null) {
                this.L = panoStudioViewer.C.this.T.createImage(b.W);
            }
            this.J = this.F.getWidth(null);
            this.E = this.F.getHeight(null);
        }
        
        void A(final Graphics graphics) {
            int i = this.I;
            int h = this.H;
            if (panoStudioViewer.C.this.l == this) {
                ++i;
                ++h;
            }
            if (panoStudioViewer.C.this.t == this && this.M != null) {
                graphics.drawImage(this.M, i, h, null);
            }
            else {
                graphics.drawImage(this.F, i, h, null);
            }
        }
        
        boolean A(int n, int n2) {
            n -= this.I;
            n2 -= this.H;
            return n >= 0 && n2 >= 0 && n < this.J && n2 < this.E && (this.K.f[n2 * this.K.V + n] >> 24 & 0xFF) > 0;
        }
    }
    
    public class _B
    {
        static final int D = 0;
        static final int A = 1;
        static final int B = 2;
        int C;
        double H;
        double G;
        double F;
        double E;
        
        public _B(final int c, final double h, final double g, final double f, final double e) {
            this.C = c;
            this.H = h;
            this.G = g;
            this.F = f;
            this.E = e;
        }
        
        public _B(final int c) {
            this.C = c;
            this.H = 0.0;
            this.G = 0.0;
            this.F = 0.0;
            this.E = 0.0;
        }
        
        public void A(final PanoStudioViewer panoStudioViewer, final D._A a) {
            switch (this.C) {
                case 0: {
                    panoStudioViewer.toggleHotspots();
                    break;
                }
                case 1: {
                    if (a != null) {
                        a.B += a.C * this.H;
                        a.A += a.C * this.G;
                        a.C *= this.F;
                        break;
                    }
                    break;
                }
                case 2: {
                    panoStudioViewer.A();
                    break;
                }
            }
        }
    }
}
