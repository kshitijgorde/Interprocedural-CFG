// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter;

import javax.sound.sampled.AudioInputStream;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.Line;
import javax.sound.sampled.DataLine;
import java.io.InputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import javax.sound.sampled.Clip;
import java.awt.Image;

public class y
{
    public static Image[] p;
    public static Image[] u;
    public static Image[] Z;
    public static Image[] m;
    public static Image[] D;
    public static Image[] I;
    public static Image[] x;
    public static Image[] W;
    public static Image[] aa;
    public static Image[] R;
    public static Image[] i;
    public static Image[] ab;
    public static Image[] H;
    public static Image[] e;
    public static Image[] n;
    public static Image[] y;
    public static Image[] z;
    public static Image[] G;
    public static Image[] g;
    public static Image[] U;
    public static Image[] b;
    public static Image[] C;
    public static Image[] M;
    public static Image[] k;
    public static Image[] L;
    public static Image[] a;
    public static Image[] d;
    public static Image X;
    public static Image h;
    public static Image r;
    public static Image q;
    public static Image J;
    public static Image c;
    public static Image P;
    public static Image f;
    public static Image l;
    public static Image A;
    public static Image B;
    public static Image O;
    public static Image E;
    public static Image S;
    public static Image K;
    public static Image Q;
    public static Image t;
    public static Image V;
    public static Image j;
    public static Image w;
    public static Image F;
    public static Image N;
    public static Image v;
    private static Clip T;
    private static Clip Y;
    private static Clip o;
    static Class s;
    
    public static void a(final Map map) {
        if (com.pokw.shooter.y.p[0] == null) {
            com.pokw.shooter.y.p[0] = map.get("planes/player/1.gif");
        }
        if (com.pokw.shooter.y.p[1] == null) {
            com.pokw.shooter.y.p[1] = map.get("planes/player/2.gif");
        }
        if (com.pokw.shooter.y.p[2] == null) {
            com.pokw.shooter.y.p[2] = map.get("planes/player/3.gif");
        }
        if (com.pokw.shooter.y.u[0] == null) {
            com.pokw.shooter.y.u[0] = map.get("planes/bomber1/1.gif");
        }
        if (com.pokw.shooter.y.u[1] == null) {
            com.pokw.shooter.y.u[1] = map.get("planes/bomber1/2.gif");
        }
        if (com.pokw.shooter.y.u[2] == null) {
            com.pokw.shooter.y.u[2] = map.get("planes/bomber1/3.gif");
        }
        if (com.pokw.shooter.y.Z[0] == null) {
            com.pokw.shooter.y.Z[0] = map.get("planes/bomber2/1.gif");
        }
        if (com.pokw.shooter.y.Z[1] == null) {
            com.pokw.shooter.y.Z[1] = map.get("planes/bomber2/2.gif");
        }
        if (com.pokw.shooter.y.Z[2] == null) {
            com.pokw.shooter.y.Z[2] = map.get("planes/bomber2/3.gif");
        }
        if (com.pokw.shooter.y.m[0] == null) {
            com.pokw.shooter.y.m[0] = map.get("planes/bomber3/1.gif");
        }
        if (com.pokw.shooter.y.m[1] == null) {
            com.pokw.shooter.y.m[1] = map.get("planes/bomber3/2.gif");
        }
        if (com.pokw.shooter.y.m[2] == null) {
            com.pokw.shooter.y.m[2] = map.get("planes/bomber3/3.gif");
        }
        if (com.pokw.shooter.y.D[0] == null) {
            com.pokw.shooter.y.D[0] = map.get("planes/bomber4/1.gif");
        }
        if (com.pokw.shooter.y.D[1] == null) {
            com.pokw.shooter.y.D[1] = map.get("planes/bomber4/2.gif");
        }
        if (com.pokw.shooter.y.D[2] == null) {
            com.pokw.shooter.y.D[2] = map.get("planes/bomber4/3.gif");
        }
        if (com.pokw.shooter.y.I[0] == null) {
            com.pokw.shooter.y.I[0] = map.get("planes/bomber5/1.gif");
        }
        if (com.pokw.shooter.y.x[0] == null) {
            com.pokw.shooter.y.x[0] = map.get("planes/fighter1/1u.gif");
        }
        if (com.pokw.shooter.y.x[1] == null) {
            com.pokw.shooter.y.x[1] = map.get("planes/fighter1/2u.gif");
        }
        if (com.pokw.shooter.y.x[2] == null) {
            com.pokw.shooter.y.x[2] = map.get("planes/fighter1/3u.gif");
        }
        if (com.pokw.shooter.y.i[0] == null) {
            com.pokw.shooter.y.i[0] = map.get("planes/fighter1/1d.gif");
        }
        if (com.pokw.shooter.y.i[1] == null) {
            com.pokw.shooter.y.i[1] = map.get("planes/fighter1/2d.gif");
        }
        if (com.pokw.shooter.y.i[2] == null) {
            com.pokw.shooter.y.i[2] = map.get("planes/fighter1/3d.gif");
        }
        if (com.pokw.shooter.y.W[0] == null) {
            com.pokw.shooter.y.W[0] = map.get("planes/fighter2/1u.gif");
        }
        if (com.pokw.shooter.y.W[1] == null) {
            com.pokw.shooter.y.W[1] = map.get("planes/fighter2/2u.gif");
        }
        if (com.pokw.shooter.y.W[2] == null) {
            com.pokw.shooter.y.W[2] = map.get("planes/fighter2/3u.gif");
        }
        if (com.pokw.shooter.y.ab[0] == null) {
            com.pokw.shooter.y.ab[0] = map.get("planes/fighter2/1d.gif");
        }
        if (com.pokw.shooter.y.ab[1] == null) {
            com.pokw.shooter.y.ab[1] = map.get("planes/fighter2/2d.gif");
        }
        if (com.pokw.shooter.y.ab[2] == null) {
            com.pokw.shooter.y.ab[2] = map.get("planes/fighter2/3d.gif");
        }
        if (com.pokw.shooter.y.aa[0] == null) {
            com.pokw.shooter.y.aa[0] = map.get("planes/fighter3/1u.gif");
        }
        if (com.pokw.shooter.y.aa[1] == null) {
            com.pokw.shooter.y.aa[1] = map.get("planes/fighter3/2u.gif");
        }
        if (com.pokw.shooter.y.aa[2] == null) {
            com.pokw.shooter.y.aa[2] = map.get("planes/fighter3/3u.gif");
        }
        if (com.pokw.shooter.y.H[0] == null) {
            com.pokw.shooter.y.H[0] = map.get("planes/fighter3/1d.gif");
        }
        if (com.pokw.shooter.y.H[1] == null) {
            com.pokw.shooter.y.H[1] = map.get("planes/fighter3/2d.gif");
        }
        if (com.pokw.shooter.y.H[2] == null) {
            com.pokw.shooter.y.H[2] = map.get("planes/fighter3/3d.gif");
        }
        if (com.pokw.shooter.y.R[0] == null) {
            com.pokw.shooter.y.R[0] = map.get("planes/fighter4/1u.gif");
        }
        if (com.pokw.shooter.y.R[1] == null) {
            com.pokw.shooter.y.R[1] = map.get("planes/fighter4/2u.gif");
        }
        if (com.pokw.shooter.y.R[2] == null) {
            com.pokw.shooter.y.R[2] = map.get("planes/fighter4/3u.gif");
        }
        if (com.pokw.shooter.y.e[0] == null) {
            com.pokw.shooter.y.e[0] = map.get("planes/fighter4/1d.gif");
        }
        if (com.pokw.shooter.y.e[1] == null) {
            com.pokw.shooter.y.e[1] = map.get("planes/fighter4/2d.gif");
        }
        if (com.pokw.shooter.y.e[2] == null) {
            com.pokw.shooter.y.e[2] = map.get("planes/fighter4/3d.gif");
        }
        if (com.pokw.shooter.y.n[0] == null) {
            com.pokw.shooter.y.n[0] = map.get("planes/fighter5/1u.gif");
        }
        if (com.pokw.shooter.y.y[0] == null) {
            com.pokw.shooter.y.y[0] = map.get("planes/fighter5/1d.gif");
        }
        if (com.pokw.shooter.y.z[0] == null) {
            com.pokw.shooter.y.z[0] = map.get("pickups/shields.gif");
        }
        if (com.pokw.shooter.y.G[0] == null) {
            com.pokw.shooter.y.G[0] = map.get("pickups/twinmg.gif");
        }
        if (com.pokw.shooter.y.g[0] == null) {
            com.pokw.shooter.y.g[0] = map.get("pickups/quadmg.gif");
        }
        if (com.pokw.shooter.y.U[0] == null) {
            com.pokw.shooter.y.U[0] = map.get("pickups/browningmg.gif");
        }
        if (com.pokw.shooter.y.b[0] == null) {
            com.pokw.shooter.y.b[0] = map.get("pickups/twinmissiles.gif");
        }
        if (com.pokw.shooter.y.C[0] == null) {
            com.pokw.shooter.y.C[0] = map.get("pickups/spreadcannon.gif");
        }
        if (com.pokw.shooter.y.M[0] == null) {
            com.pokw.shooter.y.M[0] = map.get("pickups/gatlinggun.gif");
        }
        if (com.pokw.shooter.y.k[0] == null) {
            com.pokw.shooter.y.k[0] = map.get("pickups/dualgatlingguns.gif");
        }
        if (com.pokw.shooter.y.L[0] == null) {
            com.pokw.shooter.y.L[0] = map.get("pickups/tailgun.gif");
        }
        if (com.pokw.shooter.y.a[0] == null) {
            com.pokw.shooter.y.a[0] = map.get("pickups/defenseguns.gif");
        }
        if (com.pokw.shooter.y.d[0] == null) {
            com.pokw.shooter.y.d[0] = map.get("pickups/22mmcannons.gif");
        }
        if (com.pokw.shooter.y.r == null) {
            com.pokw.shooter.y.r = map.get("core/health.gif");
        }
        if (com.pokw.shooter.y.q == null) {
            com.pokw.shooter.y.q = map.get("core/lives.gif");
        }
        if (com.pokw.shooter.y.X == null) {
            com.pokw.shooter.y.X = map.get("core/logo.gif");
        }
        if (com.pokw.shooter.y.h == null) {
            com.pokw.shooter.y.h = map.get("core/gamelogo.gif");
        }
        if (com.pokw.shooter.y.J == null) {
            com.pokw.shooter.y.J = map.get("planes/explosion.gif");
        }
        if (com.pokw.shooter.y.c == null) {
            com.pokw.shooter.y.c = map.get("core/land1.gif");
        }
        if (com.pokw.shooter.y.P == null) {
            com.pokw.shooter.y.P = map.get("core/land2.gif");
        }
        if (com.pokw.shooter.y.f == null) {
            com.pokw.shooter.y.f = map.get("core/land3.gif");
        }
        if (com.pokw.shooter.y.l == null) {
            com.pokw.shooter.y.l = map.get("bullets/bullet1.gif");
        }
        if (com.pokw.shooter.y.A == null) {
            com.pokw.shooter.y.A = map.get("bullets/bullet2.gif");
        }
        if (com.pokw.shooter.y.B == null) {
            com.pokw.shooter.y.B = map.get("bullets/bullet3.gif");
        }
        if (com.pokw.shooter.y.O == null) {
            com.pokw.shooter.y.O = map.get("bullets/bullet4.gif");
        }
        if (com.pokw.shooter.y.E == null) {
            com.pokw.shooter.y.E = map.get("bullets/missile1.gif");
        }
        if (com.pokw.shooter.y.S == null) {
            com.pokw.shooter.y.S = map.get("ui/play_game1.gif");
        }
        if (com.pokw.shooter.y.K == null) {
            com.pokw.shooter.y.K = map.get("ui/play_game2.gif");
        }
        if (com.pokw.shooter.y.V == null) {
            com.pokw.shooter.y.V = map.get("ui/credits1.gif");
        }
        if (com.pokw.shooter.y.j == null) {
            com.pokw.shooter.y.j = map.get("ui/credits2.gif");
        }
        if (com.pokw.shooter.y.Q == null) {
            com.pokw.shooter.y.Q = map.get("ui/high_scores1.gif");
        }
        if (com.pokw.shooter.y.t == null) {
            com.pokw.shooter.y.t = map.get("ui/high_scores2.gif");
        }
        if (com.pokw.shooter.y.N == null) {
            com.pokw.shooter.y.N = map.get("ui/refresh_list1.gif");
        }
        if (com.pokw.shooter.y.v == null) {
            com.pokw.shooter.y.v = map.get("ui/refresh_list2.gif");
        }
        if (com.pokw.shooter.y.w == null) {
            com.pokw.shooter.y.w = map.get("ui/back_to_main1.gif");
        }
        if (com.pokw.shooter.y.F == null) {
            com.pokw.shooter.y.F = map.get("ui/back_to_main2.gif");
        }
        if (com.pokw.shooter.y.T == null) {
            com.pokw.shooter.y.T = a(((ByteArrayOutputStream)map.get("sounds/fire.wav")).toByteArray());
        }
        if (com.pokw.shooter.y.Y == null) {
            com.pokw.shooter.y.Y = a(((ByteArrayOutputStream)map.get("sounds/explosion.wav")).toByteArray());
        }
        if (com.pokw.shooter.y.o == null) {
            com.pokw.shooter.y.o = a(((ByteArrayOutputStream)map.get("sounds/clickfast.wav")).toByteArray());
        }
    }
    
    public static void c() {
        if (com.pokw.shooter.y.Y == null) {
            return;
        }
        if (!com.pokw.shooter.y.Y.isActive()) {
            com.pokw.shooter.y.Y.setFramePosition(0);
            com.pokw.shooter.y.Y.start();
        }
    }
    
    public static void b() {
        if (com.pokw.shooter.y.T == null) {
            return;
        }
        if (!com.pokw.shooter.y.T.isActive()) {
            com.pokw.shooter.y.T.setFramePosition(0);
            com.pokw.shooter.y.T.start();
        }
    }
    
    public static void a() {
        if (com.pokw.shooter.y.o == null) {
            return;
        }
        if (!com.pokw.shooter.y.o.isActive()) {
            com.pokw.shooter.y.o.setFramePosition(0);
            com.pokw.shooter.y.o.start();
        }
    }
    
    private static Clip a(final byte[] array) {
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(array));
            final Clip clip = (Clip)AudioSystem.getLine(new DataLine.Info((com.pokw.shooter.y.s == null) ? (com.pokw.shooter.y.s = a("javax.sound.sampled.Clip")) : com.pokw.shooter.y.s, audioInputStream.getFormat(), (int)audioInputStream.getFrameLength() * audioInputStream.getFormat().getFrameSize()));
            clip.open(audioInputStream);
            return clip;
        }
        catch (UnsupportedAudioFileException ex) {
            return null;
        }
        catch (LineUnavailableException ex2) {
            return null;
        }
        catch (IOException ex3) {
            return null;
        }
    }
    
    static Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        com.pokw.shooter.y.p = new Image[3];
        com.pokw.shooter.y.u = new Image[3];
        com.pokw.shooter.y.Z = new Image[3];
        com.pokw.shooter.y.m = new Image[3];
        com.pokw.shooter.y.D = new Image[3];
        com.pokw.shooter.y.I = new Image[1];
        com.pokw.shooter.y.x = new Image[3];
        com.pokw.shooter.y.W = new Image[3];
        com.pokw.shooter.y.aa = new Image[3];
        com.pokw.shooter.y.R = new Image[3];
        com.pokw.shooter.y.i = new Image[3];
        com.pokw.shooter.y.ab = new Image[3];
        com.pokw.shooter.y.H = new Image[3];
        com.pokw.shooter.y.e = new Image[3];
        com.pokw.shooter.y.n = new Image[1];
        com.pokw.shooter.y.y = new Image[1];
        com.pokw.shooter.y.z = new Image[1];
        com.pokw.shooter.y.G = new Image[1];
        com.pokw.shooter.y.g = new Image[1];
        com.pokw.shooter.y.U = new Image[1];
        com.pokw.shooter.y.b = new Image[1];
        com.pokw.shooter.y.C = new Image[1];
        com.pokw.shooter.y.M = new Image[1];
        com.pokw.shooter.y.k = new Image[1];
        com.pokw.shooter.y.L = new Image[1];
        com.pokw.shooter.y.a = new Image[1];
        com.pokw.shooter.y.d = new Image[1];
    }
}
