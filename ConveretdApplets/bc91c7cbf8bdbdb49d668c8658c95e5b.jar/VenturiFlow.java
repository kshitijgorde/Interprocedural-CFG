import java.awt.Insets;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Button;
import java.awt.Font;
import java.awt.Color;
import java.awt.Label;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VenturiFlow extends Applet
{
    private Label Gjc;
    if Hjc;
    implements Ijc;
    import Jjc;
    Color Kjc;
    Color Ljc;
    Font Mjc;
    Font Njc;
    boolean Ojc;
    boolean Pjc;
    private static final int za = 0;
    private static final int Ric = 1;
    private static final int Qjc = 2;
    private static final int Rjc = 3;
    private static final int Sjc = 4;
    private static final int Tjc = 5;
    private static final int Ujc = 6;
    private static final int Vjc = 7;
    private static final int Wjc = 8;
    private static final int Xjc = 9;
    private static final int Yjc = 10;
    private static final int Yic = 11;
    private static final int Zic = 12;
    private static final int Zjc = 0;
    private static final int _kc = 1;
    private static final int akc = 2;
    private instanceof[] rjc;
    private interface[] Xa;
    Label bkc;
    Button ckc;
    Button dkc;
    Button ekc;
    private CheckboxGroup fkc;
    private Checkbox gkc;
    private Checkbox hkc;
    private static String Ea = "\u6c86\u6cb5\u6cae\u6ca6\u6cab";
    private static String Fa = "\u6c84\u6ca8\u6cb7\u6cbe\u6cb5\u6cae\u6ca0\u6caf\u6cb3\u6ce7\u6c90\u6ca6\u6cb5\u6ca9\u6cae\u6ca9\u6ca0\u6ce6\u6ce6\u6ce6";
    private static String Ga = "\u6cbf\u6cb4";
    private static String Ha = "\u6cb4";
    private static String Ia = "\u6caa";
    private static String Ja = "\u6ca3\u6cae\u6ca6\u6caa\u6ca2\u6cb3\u6ca2\u6cb5";
    private static String Ka = "\u6ce7\u6c83\u6cf6";
    private static String La = "\u6ce7\u6c83\u6cf5";
    private static String Ma = "\u6cab";
    private static String Na = "\u6cbf\u6cab";
    private static String fb = "\u6cb7\u6cb5\u6ca2\u6cb4\u6cb4\u6cb2\u6cb5\u6ca2\u6ce7\u6ca8\u6ca9\u6ce7\u6ca5\u6cae\u6ca0\u6ca0\u6ca2\u6cb5\u6ce7\u6ca3\u6cae\u6ca6\u6caa\u6ca2\u6cb3\u6ca2\u6cb5";
    private static String gb = "\u6ce7\u6cb7\u6cf6";
    private static String hb = "\u6cb7\u6cb5\u6ca2\u6cb4\u6cb4\u6cb2\u6cb5\u6ca2\u6ce7\u6ca8\u6ca9\u6ce7\u6cb4\u6caa\u6ca6\u6cab\u6cab\u6ca2\u6cb5\u6ce7\u6ca3\u6cae\u6ca6\u6caa\u6ca2\u6cb3\u6ca2\u6cb5";
    private static String ib = "\u6ce7\u6cb7\u6cf5";
    private static String jb = "\u6cb7\u6cb5\u6ca2\u6cb4\u6cb4\u6cb2\u6cb5\u6ca2\u6ce7\u6ca3\u6cb5\u6ca8\u6cb7";
    private static String kb = "\u6ce7\u6ca3\u6cb7";
    private static String lb = "\u6cb3\u6ca2\u6caa\u6cb7\u6ca2\u6ca6\u6cb5\u6cb3\u6cb2\u6cb5\u6ca2";
    private static String mb = "\u6ce7\u6ce7\u6c93";
    private static String nb = "\u6ca3\u6ca2\u6ca9\u6cb4\u6cae\u6cb3\u6cbe";
    private static String ob = "\u6cb5\u6caf\u6ca8";
    private static String pb = "\u6ca0\u6ca6\u6cb4\u6ce7\u6ca4\u6ca8\u6ca9\u6cb4\u6cb3\u6ca6\u6ca9\u6cb3";
    private static String qb = "\u6ce7\u6ce7\u6c95";
    private static String rb = "\u6ca4\u6ca8\u6ca2\u6ca1\u6ca1\u6cae\u6ca4\u6cae\u6ca2\u6ca9\u6cb3\u6ce7\u6ca8\u6ca1\u6ce7\u6ca8\u6cb2\u6cb3\u6ca1\u6cab\u6ca8\u6cb0";
    private static String sb = "\u6ce7\u6ce7\u6c84";
    private static String tb = "\u6cb1\u6ca8\u6cab\u6cb2\u6caa\u6ca2\u6cb3\u6cb5\u6cae\u6ca4\u6ce7\u6ca1\u6cab\u6ca8\u6cb0\u6ce7\u6cb5\u6ca6\u6cb3\u6ca2";
    private static String _ = "\u6ce7\u6ce7\u6c96";
    private static String a = "\u6caa\u6ca6\u6cb4\u6cb4\u6ce7\u6ca1\u6cab\u6ca8\u6cb0\u6ce7\u6cb5\u6ca6\u6cb3\u6ca2";
    private static String b = "\u6ce7\u6ce7\u6c80";
    private static String c = "\u6cb1\u6ca2\u6cab\u6ca8\u6ca4\u6cae\u6cb3\u6cbe";
    private static String d = "\u6ce7\u6c91\u6cf6";
    private static String e = "\u6ce7\u6c91\u6cf5";
    private static String f = "\u6c81\u6cab\u6ca8\u6cb0\u6cae\u6ca9\u6ca0\u6ce7\u6ca1\u6cab\u6cb2\u6cae\u6ca3\u6cfd";
    private static String g = "\u6c84\u6ca2\u6ca9\u6cb3\u6ca2\u6cb5";
    private static String h = "\u6c94\u6ca8\u6cb2\u6cb3\u6caf";
    private static String i = "\u6cf7\u6cf7\u6cf7\u6cf7\u6cf7";
    private static String j = "\u6c89\u6ca8\u6cb5\u6cb3\u6caf";
    private static String k = "\u6c94\u6ca2\u6cab\u6ca2\u6ca4\u6cb3\u6ce7\u6cb0\u6caf\u6cae\u6ca4\u6caf\u6ce7\u6ca8\u6ca9\u6ca2\u6ce7\u6cb3\u6ca8\u6ce7\u6ca4\u6ca6\u6cab\u6ca4\u6cb2\u6cab\u6ca6\u6cb3\u6ca2\u6ce7\u6ca1\u6cb5\u6ca8\u6caa\u6ce7\u6ca1\u6ca8\u6cab\u6cab\u6ca8\u6cb0\u6cae\u6ca9\u6ca0\u6ce7\u6cb3\u6caf\u6cb5\u6ca2\u6ca2\u6cfd\u6ce7";
    private static String l = "\u6c94\u6ca2\u6cab\u6ca2\u6ca4\u6cb3\u6ce7\u6cb0\u6caf\u6cae\u6ca4\u6caf\u6ce7\u6ca8\u6ca9\u6ca2\u6ce7\u6cb3\u6ca8\u6ce7\u6cae\u6ca9\u6cb7\u6cb2\u6cb3\u6ce7\u6ca1\u6cb5\u6ca8\u6caa\u6ce7\u6ca1\u6ca8\u6cab\u6cab\u6ca8\u6cb0\u6cae\u6ca9\u6ca0\u6ce7\u6cb3\u6cb0\u6ca8\u6cfd\u6ce7";
    private static String m = "\u6c91\u6ca2\u6ca9\u6cb3\u6cb2\u6cb5\u6cae\u6ce7\u6c81\u6cab\u6ca8\u6cb0\u6ce7\u6c84\u6ca6\u6cab\u6ca4\u6cb2\u6cab\u6ca6\u6cb3\u6ca8\u6cb5\u6ce7\u6ca5\u6cbe\u6ce7\u6c9d\u6ca8\u6cb5\u6ca6\u6ca9\u6ce7\u6c94\u6ca6\u6cb1\u6ca8\u6cb1\u6cae\u6ca4\u6ce9\u6ce7\u6c86\u6cab\u6cab\u6ce7\u6cb5\u6cae\u6ca0\u6caf\u6cb3\u6cb4\u6ce7\u6cb5\u6ca2\u6cb4\u6ca2\u6cb5\u6cb1\u6ca2\u6ca3\u6ce9";
    private static String n = "\u6c91\u6ca6\u6cab\u6cb2\u6ca2\u6ce7\u6ca8\u6ca1\u6ce7";
    private static String o = "\u6ce7\u6cae\u6cb4\u6ce7\u6cab\u6ca2\u6cb4\u6cb4\u6ce7\u6cb3\u6caf\u6ca6\u6ca9\u6ce7\u6cbd\u6ca2\u6cb5\u6ca8\u6ce6\u6ce7\u6c97\u6cab\u6ca2\u6ca6\u6cb4\u6ca2\u6ce7\u6ca3\u6ca2\u6ca4\u6cb5\u6ca2\u6ca6\u6cb4\u6ca2\u6ce7";
    private static String p = "\u6ce7\u6ca8\u6cb5\u6ce7\u6cae\u6ca9\u6ca4\u6cb5\u6ca2\u6ca6\u6cb4\u6ca2\u6ce7";
    private static String q = "\u6ce7\u6caa\u6cb2\u6cb4\u6cb3\u6ce7\u6ca5\u6ca2\u6ce7\u6ca0\u6cb5\u6ca2\u6ca6\u6cb3\u6ca2\u6cb5\u6ce7\u6cb3\u6caf\u6ca2\u6ca9\u6ce7";
    private static String r = "\u6c82\u6cb5\u6cb5\u6ca8\u6cb5\u6ce6";
    private static String s = "\u6c91\u6ca6\u6cab\u6cb2\u6ca2\u6ce7\u6ca1\u6ca8\u6cb5\u6ce7";
    private static String t = "\u6ce7\u6caa\u6cb2\u6cb4\u6cb3\u6ce7\u6ca5\u6ca2\u6ce7\u6ca0\u6cb5\u6ca2\u6ca6\u6cb3\u6ca2\u6cb5\u6ce7\u6cb3\u6caf\u6ca2\u6ca9\u6ce7\u6cf7";
    private static String u = "\u6cf7";
    private static String v = "\u6ca9\u6ce8\u6ca6";
    private static String w = "\u6cf6\u6cf7\u6cf7";
    private static String x = "\u6cf0\u6cf2";
    private static String y = "\u6cf6\u6cf7\u6cf6\u6cf4\u6cf5\u6cf2";
    private static String z = "\u6cf6\u6cf7\u6cf6\u6cf7\u6cf7\u6cf7";
    private static String A = "\u6cf5\u6cff\u6cff";
    private static String B = "\u6cf7\u6ce9\u6cfe\u6cff\u6cf2";
    private static String C = "\u6cf5\u6cff\u6cf0";
    private static String D = "";
    private static String E = "\u6c8c";
    private static String F = "\u6c91\u6c82\u6c89\u6c93\u6c92\u6c95\u6c8e\u6ce7\u6c93\u6c92\u6c85\u6c82\u6ce7\u6c84\u6c86\u6c8b\u6c84\u6c92\u6c8b\u6c86\u6c93\u6c88\u6c95";
    private static String G = "\u6c84\u6c86\u6c8b\u6c84\u6c92\u6c8b\u6c86\u6c93\u6c8e\u6c88\u6c89\u6ce7\u6c95\u6c82\u6c97\u6c88\u6c95\u6c93";
    private static String H = "\u6c85\u6cae\u6ca0\u6ca0\u6ca2\u6cb5\u6ce7\u6ca3\u6cae\u6ca6\u6caa\u6ca2\u6cb3\u6ca2\u6cb5\u6ce7\u6ca8\u6ca1\u6ce7\u6c91\u6ca2\u6ca9\u6cb3\u6cb2\u6cb5\u6cae\u6ce7\u6cb3\u6cb2\u6ca5\u6ca2\u6ce7\u6cef\u6c83\u6cf6\u6cee\u6cfd\u6ce7";
    private static String I = "\u6c83\u6cf6\u6ce7\u6cfa\u6ce7";
    private static String J = "\u6ce7";
    private static String K = "\u6c94\u6caa\u6ca6\u6cab\u6cab\u6ca2\u6cb5\u6ce7\u6ca3\u6cae\u6ca6\u6caa\u6ca2\u6cb3\u6ca2\u6cb5\u6ce7\u6ca8\u6ca1\u6ce7\u6c91\u6ca2\u6ca9\u6cb3\u6cb2\u6cb5\u6cae\u6ce7\u6cb3\u6cb2\u6ca5\u6ca2\u6ce7\u6cef\u6c83\u6cf5\u6cee\u6cfd\u6ce7";
    private static String L = "\u6c83\u6cf5\u6ce7\u6cfa\u6ce7";
    private static String M = "\u6c97\u6cb5\u6ca2\u6cb4\u6cb4\u6cb2\u6cb5\u6ca2\u6ce7\u6ca8\u6ca9\u6ce7\u6ca5\u6cae\u6ca0\u6ca0\u6ca2\u6cb5\u6ce7\u6ca3\u6cae\u6ca6\u6caa\u6ca2\u6cb3\u6ca2\u6cb5\u6ce7\u6cef\u6cae\u6ca9\u6cab\u6ca2\u6cb3\u6cee\u6ce7\u6ca8\u6ca1\u6ce7\u6c91\u6ca2\u6ca9\u6cb3\u6cb2\u6cb5\u6cae\u6ce7\u6cb3\u6cb2\u6ca5\u6ca2\u6ce7\u6cef\u6cb7\u6cf6\u6cee\u6cfd\u6ce7";
    private static String N = "\u6cb7\u6cf6\u6ce7\u6cfa\u6ce7";
    private static String O = "\u6c97\u6cb5\u6ca2\u6cb4\u6cb4\u6cb2\u6cb5\u6ca2\u6ce7\u6ca3\u6cb5\u6ca8\u6cb7\u6ce7\u6cb3\u6caf\u6cb5\u6ca8\u6cb2\u6ca0\u6caf\u6ce7\u6c91\u6ca2\u6ca9\u6cb3\u6cb2\u6cb5\u6cae\u6ce7\u6cb3\u6cb2\u6ca5\u6ca2\u6ce7\u6ca3\u6cb2\u6ca2\u6ce7\u6cb3\u6ca8\u6ce7\u6cb1\u6ca2\u6cab\u6ca8\u6ca4\u6cae\u6cb3\u6cbe\u6ce7\u6cae\u6ca9\u6ca4\u6cb5\u6ca2\u6ca6\u6cb4\u6ca2\u6ce7\u6cef\u6ca3\u6cb7\u6cee\u6cfd\u6ce7";
    private static String P = "\u6ca3\u6cb7\u6ce7\u6cfa\u6ce7";
    private static String Q = "\u6c80\u6ca6\u6cb4\u6ce7\u6ca4\u6ca8\u6ca9\u6cb4\u6cb3\u6ca6\u6ca9\u6cb3\u6ce7\u6cef\u6c95\u6cee\u6cfd\u6ce7";
    private static String R = "\u6c95\u6ce7\u6cfa\u6ce7";
    private static String S = "\u6c80\u6ca6\u6cb4\u6ce7\u6cb3\u6ca2\u6caa\u6cb7\u6ca2\u6cb5\u6ca6\u6cb3\u6cb2\u6cb5\u6ca2\u6ce7\u6cef\u6c93\u6cee\u6cfd\u6ce7";
    private static String T = "\u6c93\u6ce7\u6cfa\u6ce7";
    private static String U = "\u6c83\u6ca2\u6ca9\u6cb4\u6cae\u6cb3\u6cbe\u6ce7\u6cef\u6cb5\u6caf\u6ca8\u6cee\u6cfd\u6ce7";
    private static String V = "\u6cb5\u6caf\u6ca8\u6ce7\u6cfa\u6ce7";
    private static String W = "\u6c84\u6ca8\u6ca2\u6ca1\u6ca1\u6cae\u6ca4\u6cae\u6ca2\u6ca9\u6cb3\u6ce7\u6ca8\u6ca1\u6ce7\u6ca8\u6cb2\u6cb3\u6ca1\u6cab\u6ca8\u6cb0\u6ce7\u6cef\u6c84\u6cee\u6cfd\u6ce7";
    private static String X = "\u6c84\u6ce7\u6cfa\u6ce7";
    private static String Y = "\u6c91\u6ca8\u6cab\u6cb2\u6caa\u6ca2\u6cb3\u6cb5\u6cae\u6ca4\u6ce7\u6ca1\u6cab\u6ca8\u6cb0\u6ce7\u6cb5\u6ca6\u6cb3\u6ca2\u6ce7\u6cef\u6c96\u6cee\u6cfd\u6ce7";
    private static String Z = "\u6c96\u6ce7\u6cfa\u6ce7";
    private static String _a = "\u6c8a\u6ca6\u6cb4\u6cb4\u6ce7\u6ca1\u6cab\u6ca8\u6cb0\u6ce7\u6cb5\u6ca6\u6cb3\u6ca2\u6ce7\u6cef\u6c80\u6cee\u6cfd\u6ce7";
    private static String aa = "\u6c80\u6ce7\u6cfa\u6ce7";
    private static String ba = "\u6c91\u6ca2\u6cab\u6ca8\u6ca4\u6cae\u6cb3\u6cbe\u6ce7\u6ca8\u6ca9\u6ce7\u6ca5\u6cae\u6ca0\u6ca0\u6ca2\u6cb5\u6ce7\u6ca3\u6cae\u6ca6\u6caa\u6ca2\u6cb3\u6ca2\u6cb5\u6ce7\u6cef\u6cae\u6ca9\u6cab\u6ca2\u6cb3\u6cee\u6ce7\u6ca8\u6ca1\u6ce7\u6c91\u6ca2\u6ca9\u6cb3\u6cb2\u6cb5\u6cae\u6ce7\u6cb3\u6cb2\u6ca5\u6ca2\u6ce7\u6cef\u6c91\u6cf6\u6cee\u6cfd\u6ce7";
    private static String ca = "\u6c91\u6cf6\u6ce7\u6cfa\u6ce7";
    private static String da = "\u6c91\u6ca2\u6cab\u6ca8\u6ca4\u6cae\u6cb3\u6cbe\u6ce7\u6ca8\u6ca9\u6ce7\u6cb4\u6caa\u6ca6\u6cab\u6cab\u6ca2\u6cb5\u6ce7\u6ca3\u6cae\u6ca6\u6caa\u6ca2\u6cb3\u6ca2\u6cb5\u6ce7\u6ca8\u6ca1\u6ce7\u6c91\u6ca2\u6ca9\u6cb3\u6cb2\u6cb5\u6cae\u6ce7\u6cb3\u6cb2\u6ca5\u6ca2\u6ce7\u6cef\u6c91\u6cf5\u6cee\u6cfd\u6ce7";
    private static String ea = "\u6c91\u6cf5\u6ce7\u6cfa\u6ce7";
    private static String fa = "\u6c93\u6caf\u6ca6\u6ca9\u6cac\u6ce7\u6cbe\u6ca8\u6cb2\u6ce7\u6ca1\u6ca8\u6cb5\u6ce7\u6cb2\u6cb4\u6cae\u6ca9\u6ca0\u6ce7\u6cb3\u6caf\u6cae\u6cb4\u6ce7\u6cb4\u6ca8\u6ca1\u6cb3\u6cb0\u6ca6\u6cb5\u6ca2";
    private static String ga = "\u6c9d\u6ca8\u6cb5\u6ca6\u6ca9\u6ce7\u6c94\u6ca6\u6cb1\u6ca8\u6cb1\u6cae\u6ca4\u6ce7\u6cea\u6ce7\u6ca1\u6cab\u6ca8\u6cb0\u6ce9\u6ca9\u6ca2\u6cb3\u6ca1\u6cae\u6cb5\u6caa\u6cb4\u6ce9\u6ca4\u6ca8\u6caa";
    private static String ikc = "\u6ca4\u6ca8\u6cb7\u6cbe\u6cf6";
    private static String jkc = "\u6ca4\u6ca8\u6cb7\u6cbe\u6cf5";
    private static String kkc = "\u6c84\u6ca8\u6cb7\u6cbe\u6cb5\u6cae\u6ca0\u6caf\u6cb3\u6ce7\u6c9d\u6ca8\u6cb5\u6ca6\u6ca9\u6ce7\u6c94\u6ca6\u6cb1\u6ca8\u6cb1\u6cae\u6ca4\u6ce7\u6cf5\u6cf7\u6cf7\u6cf7\u6cea\u6cf5\u6cf7\u6cf7\u6cf6\u6ce9\u6ce7\u6c86\u6cab\u6cab\u6ce7\u6cb5\u6cae\u6ca0\u6caf\u6cb3\u6cb4\u6ce7\u6cb5\u6ca2\u6cb4\u6ca2\u6cb5\u6cb1\u6ca2\u6ca3";
    private static String lkc = "\u6caf\u6cb3\u6cb3\u6cb7\u6cfd\u6ce8\u6ce8\u6ca1\u6cab\u6ca8\u6cb0\u6ce9\u6ca9\u6ca2\u6cb3\u6ca1\u6cae\u6cb5\u6caa\u6cb4\u6ce9\u6ca4\u6ca8\u6caa";
    private static String mkc = "\u6c83\u6ca8\u6ce7\u6ca9\u6ca8\u6cb3\u6ce7\u6cb5\u6ca2\u6caa\u6ca8\u6cb1\u6ca2\u6ce7\u6ca4\u6ca8\u6cb7\u6cbe\u6cb5\u6cae\u6ca0\u6caf\u6cb3\u6ce7\u6cae\u6ca9\u6ca1\u6ca8\u6cb5\u6caa\u6ca6\u6cb3\u6cae\u6ca8\u6ca9\u6ce6\u6ce6\u6ce6";
    private static String nkc = "\u6c90\u6c86\u6c95\u6c89\u6c8e\u6c89\u6c80\u6ce6";
    private static String okc = "\u6c95\u6ca2\u6cb7\u6ca8\u6cb5\u6cb3\u6ce7\u6ca8\u6ca1\u6ce7\u6ca4\u6ca6\u6cab\u6ca4\u6cb2\u6cab\u6ca6\u6cb3\u6cae\u6ca8\u6ca9\u6ce7\u6ca8\u6ca1\u6ce7\u6ca1\u6cab\u6ca8\u6cb0\u6ce7\u6cb5\u6ca6\u6cb3\u6ca2\u6ce7\u6cb3\u6caf\u6cb5\u6ca8\u6cb2\u6ca0\u6caf\u6ce7\u6c91\u6ca2\u6ca9\u6cb3\u6cb2\u6cb5\u6cae\u6ce7\u6cb3\u6cb2\u6ca5\u6ca2";
    private static String pkc = "\u6c84\u6ca8\u6cb7\u6cbe\u6cb5\u6cae\u6ca0\u6caf\u6cb3\u6cfd\u6ce7\u6c9d\u6ca8\u6cb5\u6ca6\u6ca9\u6ce7\u6c94\u6ca6\u6cb1\u6ca8\u6cb1\u6cae\u6ca4\u6ce7\u6cf5\u6cf7\u6cf7\u6cf7\u6cea\u6cf5\u6cf7\u6cf7\u6cf6";
    private static String qkc = "\u6c8e\u6ca9\u6cb7\u6cb2\u6cb3\u6ce7\u6cb1\u6ca6\u6cab\u6cb2\u6ca2\u6cb4\u6ce9\u6ce9\u6ce9";
    private static String rkc = "\u6c84\u6ca6\u6cab\u6ca4\u6cb2\u6cab\u6ca6\u6cb3\u6ca2";
    private static String skc = "\u6c84\u6cb5\u6ca2\u6ca6\u6cb3\u6ca2\u6ce7\u6cb5\u6ca2\u6cb7\u6ca8\u6cb5\u6cb3";
    private static String tkc = "\u6c80\u6ca6\u6cb4";
    private static String ukc = "\u6c8b\u6cae\u6cb6\u6cb2\u6cae\u6ca3";
    
    public String getAppletInfo() {
        return native.ha;
    }
    
    public void init() {
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        final Panel panel4 = new Panel();
        final Panel panel5 = new Panel();
        final Panel panel6 = new Panel();
        final Panel panel7 = new Panel();
        new Panel();
        final Panel panel8 = new Panel();
        this.setBackground(this.Kjc = new Color(192, 239, 255));
        this.setForeground(this.Ljc = new Color(0, 0, 0));
        this.Mjc = new Font(VenturiFlow.Ea, 0, 10);
        this.Njc = new Font(VenturiFlow.Ea, 0, 10);
        this.Hjc = new if(VenturiFlow.Fa);
        this.e();
        final String[] array = new String[7];
        final new[] array2 = new new[20];
        array[0] = VenturiFlow.Ga;
        array[1] = VenturiFlow.Ha;
        array[2] = VenturiFlow.Ia;
        final new[] _ = null._(array, 3);
        this.Xa[0] = new interface(VenturiFlow.Ja, VenturiFlow.Ka, true, 1, 0, _, this.Mjc);
        this.Xa[1] = new interface(VenturiFlow.Ja, VenturiFlow.La, true, 1, 0, _, this.Mjc);
        this.Xa[0]._(false);
        this.Xa[1]._(false);
        array[0] = VenturiFlow.Ga;
        array[1] = VenturiFlow.Ha;
        array[2] = VenturiFlow.Ia;
        array[3] = VenturiFlow.Ma;
        array[4] = VenturiFlow.Na;
        final new[] a = null.a(array, 5);
        this.Xa[2] = new interface(VenturiFlow.fb, VenturiFlow.gb, true, 1, 0, a, this.Mjc);
        this.Xa[3] = new interface(VenturiFlow.hb, VenturiFlow.ib, true, 1, 0, a, this.Mjc);
        this.Xa[4] = new interface(VenturiFlow.jb, VenturiFlow.kb, false, 1, 0, a, this.Mjc);
        array[0] = VenturiFlow.Ia;
        this.Xa[5] = new interface(VenturiFlow.lb, VenturiFlow.mb, true, 1, 0, null.b(array, 1), this.Mjc);
        this.Xa[5].eb = true;
        array[0] = VenturiFlow.Ia;
        array[1] = VenturiFlow.Ma;
        this.Xa[6] = new interface(VenturiFlow.nb, VenturiFlow.ob, false, 1, 0, null.m(array, 2), this.Mjc);
        array[0] = VenturiFlow.Ia;
        array[1] = VenturiFlow.Ma;
        this.Xa[7] = new interface(VenturiFlow.pb, VenturiFlow.qb, true, 1, 0, null.n(array, 2), this.Mjc);
        this.Xa[8] = new interface(VenturiFlow.rb, VenturiFlow.sb, true, 2, 0, this.Mjc);
        array[0] = VenturiFlow.Ha;
        array[1] = VenturiFlow.Ia;
        array[2] = VenturiFlow.Ma;
        array[3] = VenturiFlow.Na;
        this.Xa[9] = new interface(VenturiFlow.tb, VenturiFlow._, false, 1, 2, null.c(array, 4), this.Mjc);
        array[0] = VenturiFlow.Ha;
        array[1] = VenturiFlow.Ia;
        array[2] = VenturiFlow.Ma;
        this.Xa[10] = new interface(VenturiFlow.a, VenturiFlow.b, false, 3, 0, null.d(array, 3), this.Mjc);
        array[0] = VenturiFlow.Ha;
        array[1] = VenturiFlow.Ia;
        array[2] = VenturiFlow.Ma;
        array[3] = VenturiFlow.Na;
        final new[] e = null.e(array, 4);
        this.Xa[11] = new interface(VenturiFlow.c, VenturiFlow.d, false, 3, 6, e, this.Mjc);
        this.Xa[12] = new interface(VenturiFlow.c, VenturiFlow.e, false, 3, 6, e, this.Mjc);
        this.setLayout(new BorderLayout());
        panel2.setLayout(new GridLayout(5, 1));
        for (int i = 0; i < 5; ++i) {
            panel2.add(this.Xa[i].b());
        }
        panel3.setLayout(new GridLayout(5, 1));
        for (int j = 5; j < 9; ++j) {
            panel3.add(this.Xa[j].b());
        }
        panel8.setLayout(new FlowLayout());
        final Label label = new Label(VenturiFlow.f);
        label.setFont(this.Mjc);
        this.gkc.setFont(this.Mjc);
        this.hkc.setFont(this.Mjc);
        panel8.add(label);
        panel8.add(this.gkc);
        panel8.add(this.hkc);
        panel3.add(panel8);
        panel4.setLayout(new GridLayout(2, 2));
        for (int k = 9; k < 13; ++k) {
            panel4.add(this.Xa[k].b());
        }
        panel5.setLayout(new GridLayout(1, 3));
        this.ckc.setFont(this.Mjc);
        this.dkc.setFont(this.Mjc);
        this.ekc.setFont(this.Mjc);
        panel5.add(this.ckc);
        panel5.add(this.dkc);
        panel5.add(this.ekc);
        panel6.setLayout(new BorderLayout());
        panel6.add(VenturiFlow.g, panel4);
        panel6.add(VenturiFlow.h, panel5);
        this.Gjc = new Label(VenturiFlow.i);
        panel7.setLayout(new GridLayout(1, 2));
        panel7.add(panel2);
        panel7.add(panel3);
        this.add(VenturiFlow.j, panel);
        this.add(VenturiFlow.g, panel7);
        this.add(VenturiFlow.h, panel6);
        final interface[] array3 = new interface[3];
        final interface[] array4 = new interface[2];
        final interface[] array5 = new interface[2];
        array3[0] = this.Xa[9];
        array3[1] = this.Xa[0];
        array3[2] = this.Xa[1];
        (this.rjc[0] = new instanceof(array3, VenturiFlow.k, 3, this.Mjc)).m(0);
        this.rjc[0].wa = true;
        this.rjc[0].a(false);
        this.rjc[0].xa = true;
        array4[0] = this.Xa[3];
        array4[1] = this.Xa[4];
        (this.rjc[1] = new instanceof(array4, VenturiFlow.l, 2, this.Mjc)).m(0);
        array5[0] = this.Xa[5];
        array5[1] = this.Xa[6];
        (this.rjc[2] = new instanceof(array5, VenturiFlow.l, 2, this.Mjc)).m(0);
        this.Ijc = new implements(this.rjc, 3, this.Mjc);
    }
    
    public void start() {
        this.f();
        this.g();
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.getAppletContext().showStatus(VenturiFlow.m);
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 10) {
            if (this.b() && !this.Pjc) {
                this.g();
            }
            return true;
        }
        return false;
    }
    
    private void g() {
        if (!this.Pjc) {
            float n = 0.0f;
            float n2 = 0.0f;
            final float a = this.Xa[2].a();
            this.Xa[3].a();
            final float _ = this.Xa[8]._();
            float n3 = 0.0f;
            float n4;
            if (!this.Xa[3].a()) {
                n4 = private._(a, this.Xa[4].a());
                this.Xa[3].b(String.valueOf(n4));
            }
            else {
                n4 = this.Xa[3].a();
                this.Xa[4].b(String.valueOf(protected._(a, n4)));
            }
            float n5;
            if (this.Ojc) {
                if (!this.Xa[6].a()) {
                    n5 = public.a(a, this.Xa[7].a(), this.Xa[5].a());
                    this.Xa[6].b(String.valueOf(n5));
                }
                else {
                    n5 = this.Xa[6].a();
                    this.Xa[5].b(String.valueOf(public.g(a, n5, this.Xa[7].a())));
                }
            }
            else {
                n5 = this.Xa[6].a();
            }
            if (!this.Xa[9].a()) {
                n = this.Xa[0].a();
                n2 = this.Xa[1].a();
                n3 = return._(a, n4, n5, n, n2, _);
                this.Xa[9].b(String.valueOf(n3));
            }
            else if (!this.Xa[0].a()) {
                n3 = this.Xa[9].a();
                n2 = this.Xa[1].a();
                n = static.b(a, n4, n5, n3, n2, _);
                if (n <= 0.0f) {
                    this.b(this.Xa[1], this.Xa[9], VenturiFlow.n + this.Xa[11].h() + VenturiFlow.o + this.Xa[1].h() + VenturiFlow.p + this.Xa[9].h());
                    return;
                }
                this.Xa[0].b(String.valueOf(n));
            }
            else if (!this.Xa[1].a()) {
                n3 = this.Xa[9].a();
                n = this.Xa[0].a();
                n2 = static._(a, n4, n5, n3, n, _);
                this.Xa[1].b(String.valueOf(n2));
            }
            this.Xa[10].b(String.valueOf(super._(n3, n5)));
            this.Xa[11].b(String.valueOf(switch._(n3, n)));
            this.Xa[12].b(String.valueOf(switch._(n3, n2)));
        }
    }
    
    private void h() {
        this.Ijc.setBackground(this.Kjc);
        this.Ijc.setForeground(this.Ljc);
        this.Ijc.pack();
        this.Ijc.show();
    }
    
    private boolean b() {
        boolean _ = true;
        for (int i = 0; i < 10; ++i) {
            if (this.Xa[i].a()) {
                if (!this.Ojc && (i == 5 || i == 7)) {
                    ++i;
                }
                _ = this.Xa[i]._(this.Xa[i].g());
                if (!_) {
                    break;
                }
            }
        }
        if (_) {
            for (int j = 0; j < 10; ++j) {
                if (this.Xa[j].a()) {
                    if (!this.Ojc && (j == 5 || j == 7)) {
                        ++j;
                    }
                    if (this.Xa[j]._() <= 0.0f) {
                        this._(this.Xa[j]);
                        _ = false;
                        break;
                    }
                }
            }
        }
        if (_) {
            final float a = this.Xa[0].a();
            final float a2 = this.Xa[1].a();
            if (this.Xa[0].a() && this.Xa[1].a() && a <= a2) {
                this.b(this.Xa[0], this.Xa[1], VenturiFlow.n + this.Xa[0].h() + VenturiFlow.q + this.Xa[1].h());
                _ = false;
            }
        }
        if (_) {
            final float a3 = this.Xa[2].a();
            if (this.Xa[3].a()) {
                if (a3 <= this.Xa[3].a()) {
                    this.b(this.Xa[2], this.Xa[3], VenturiFlow.n + this.Xa[2].h() + VenturiFlow.q + this.Xa[3].h());
                    _ = false;
                }
            }
            else if (a3 <= this.Xa[4].a()) {
                this.b(this.Xa[2], this.Xa[4], VenturiFlow.n + this.Xa[2].h() + VenturiFlow.q + this.Xa[4].h());
                _ = false;
            }
        }
        return _;
    }
    
    public void _(final interface interface1) {
        final if if1 = new if(VenturiFlow.r, interface1, true);
        if1.a(interface1._(), VenturiFlow.s + interface1.h() + VenturiFlow.t, interface1.g());
        if1.pack();
        if1.show();
    }
    
    public void b(final interface interface1, final interface interface2, final String s) {
        final interface[] array = { interface1, interface2 };
        final if if1 = new if(VenturiFlow.r, array, true, 2);
        if1.b(s, array);
        if1.pack();
        if1.show();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Checkbox) {
            if (event.target == this.gkc) {
                this.Ojc = true;
                this.rjc[2].enable();
                this.Xa[7]._(true);
                this.Xa[5].a(VenturiFlow.u);
                this.Xa[7].a(VenturiFlow.u);
            }
            if (event.target == this.hkc) {
                this.Ojc = false;
                this.Xa[5]._(false);
                this.Xa[5].a(VenturiFlow.v);
                this.Xa[7]._(false);
                this.Xa[7].a(VenturiFlow.v);
                this.Xa[6]._(true);
                this.rjc[2].b(1);
                this.rjc[2].disable();
            }
            return true;
        }
        if (event.target instanceof Button) {
            if (event.target == this.dkc) {
                if (this.b()) {
                    this.g();
                }
            }
            else if (event.target == this.ekc) {
                if (this.b()) {
                    this.g();
                    this.i();
                }
            }
            else if (event.target == this.ckc) {
                this.h();
            }
            return true;
        }
        return false;
    }
    
    private void f() {
        this.Xa[0].a(VenturiFlow.w);
        this.Xa[1].a(VenturiFlow.x);
        this.Xa[2].a(VenturiFlow.y);
        this.Xa[3].a(VenturiFlow.z);
        this.Xa[5].a(VenturiFlow.A);
        this.Xa[8].a(VenturiFlow.B);
        this.Xa[7].a(VenturiFlow.C);
    }
    
    private void i() {
        this.Jjc.resize(500, 500);
        this.Jjc.setBackground(this.Kjc);
        this.Jjc.setForeground(this.Ljc);
        this.Jjc._(this.Mjc);
        final String g = this.Xa[0].g();
        final String g2 = this.Xa[1].g();
        final String g3 = this.Xa[8].g();
        final String g4 = this.Xa[2].g();
        this.Xa[3].g();
        final String g5 = this.Xa[4].g();
        final String g6 = this.Xa[7].g();
        final String g7 = this.Xa[5].g();
        String s;
        if (this.Ojc) {
            s = String.valueOf(this.Xa[5].a());
        }
        else {
            s = VenturiFlow.D;
        }
        final String g8 = this.Xa[6].g();
        final String g9 = this.Xa[9].g();
        final String g10 = this.Xa[10].g();
        final String g11 = this.Xa[11].g();
        final String g12 = this.Xa[12].g();
        final String f = this.Xa[0].b().f();
        final String f2 = this.Xa[1].b().f();
        final String f3 = this.Xa[2].b().f();
        this.Xa[3].b().f();
        final String f4 = this.Xa[4].b().f();
        final String f5 = this.Xa[5].b().f();
        final String e = VenturiFlow.E;
        final String f6 = this.Xa[6].b().f();
        final String f7 = this.Xa[7].b().f();
        final String f8 = this.Xa[9].b().f();
        final String f9 = this.Xa[10].b().f();
        final String f10 = this.Xa[11].b().f();
        final String f11 = this.Xa[12].b().f();
        final boolean state = this.gkc.getState();
        String s2 = VenturiFlow.F + '\n' + VenturiFlow.G + '\n' + '\n' + '\t' + VenturiFlow.H + '\n' + '\t' + '\t' + VenturiFlow.I + g + VenturiFlow.J + f + '\n' + '\n' + '\t' + VenturiFlow.K + '\n' + '\t' + '\t' + VenturiFlow.L + g2 + VenturiFlow.J + f2 + '\n' + '\n' + '\t' + VenturiFlow.M + '\n' + '\t' + '\t' + VenturiFlow.N + g4 + VenturiFlow.J + f3 + '\n' + '\n' + '\t' + VenturiFlow.O + '\n' + '\t' + '\t' + VenturiFlow.P + g5 + VenturiFlow.J + f4 + '\n' + '\n';
        if (state) {
            final String string = s2 + '\t' + VenturiFlow.Q + '\n' + '\t' + '\t' + VenturiFlow.R + g6 + VenturiFlow.J + f7 + '\n' + '\n' + '\t' + VenturiFlow.S + '\n' + '\t' + '\t' + VenturiFlow.T + g7 + VenturiFlow.J + f5 + '\n';
            if (!this.Xa[5].b().f().equals(VenturiFlow.E)) {
                s2 = string + '\t' + '\t' + VenturiFlow.T + s + VenturiFlow.J + e + '\n' + '\n';
            }
            else {
                s2 = string + '\n';
            }
        }
        this.Jjc.l(s2 + '\t' + VenturiFlow.U + '\n' + '\t' + '\t' + VenturiFlow.V + g8 + VenturiFlow.J + f6 + '\n' + '\n' + '\t' + VenturiFlow.W + '\n' + '\t' + '\t' + VenturiFlow.X + g3 + '\n' + '\n' + '\t' + VenturiFlow.Y + '\n' + '\t' + '\t' + VenturiFlow.Z + g9 + VenturiFlow.J + f8 + '\n' + '\n' + '\t' + VenturiFlow._a + '\n' + '\t' + '\t' + VenturiFlow.aa + g10 + VenturiFlow.J + f9 + '\n' + '\n' + '\t' + VenturiFlow.ba + '\n' + '\t' + '\t' + VenturiFlow.ca + g11 + VenturiFlow.J + f10 + '\n' + '\n' + '\t' + VenturiFlow.da + '\n' + '\t' + '\t' + VenturiFlow.ea + g12 + VenturiFlow.J + f11 + '\n' + '\n' + '\n' + '\n' + VenturiFlow.fa + '\n' + VenturiFlow.ga);
        this.Jjc.show();
    }
    
    private void e() {
        final String parameter = this.getParameter(VenturiFlow.ikc);
        final String parameter2 = this.getParameter(VenturiFlow.jkc);
        final String kkc = VenturiFlow.kkc;
        final String lkc = VenturiFlow.lkc;
        final int n = 54;
        final int n2 = 24;
        final String s = new String(VenturiFlow.mkc);
        final int min = Math.min(kkc.lastIndexOf(VenturiFlow.D), parameter.lastIndexOf(VenturiFlow.D));
        final int min2 = Math.min(lkc.lastIndexOf(VenturiFlow.D), parameter2.lastIndexOf(VenturiFlow.D));
        if (min != n) {
            this.m(s);
        }
        if (min2 != n2) {
            this.m(s);
        }
        for (int i = 0; i < min; ++i) {
            if (parameter.charAt(i) != kkc.charAt(i)) {
                this.m(s);
            }
        }
        for (int j = 0; j < min2; ++j) {
            if (parameter2.charAt(j) != lkc.charAt(j)) {
                this.m(s);
            }
        }
    }
    
    void m(final String s) {
        this.dkc.disable();
        this.ekc.disable();
        final String nkc = VenturiFlow.nkc;
        this.Hjc.setBackground(this.Kjc);
        this.Hjc._(this.Mjc);
        this.Hjc._(nkc, s);
        this.Hjc.pack();
        this.Hjc.show();
    }
    
    public Insets insets() {
        return new Insets(5, 5, 5, 5);
    }
    
    public VenturiFlow() {
        this.Jjc = new import(VenturiFlow.okc);
        this.Ojc = true;
        this.Pjc = false;
        this.rjc = new instanceof[3];
        this.Xa = new interface[13];
        this.bkc = new Label(VenturiFlow.pkc);
        this.ckc = new Button(VenturiFlow.qkc);
        this.dkc = new Button(VenturiFlow.rkc);
        this.ekc = new Button(VenturiFlow.skc);
        this.fkc = new CheckboxGroup();
        this.gkc = new Checkbox(VenturiFlow.tkc, this.fkc, true);
        this.hkc = new Checkbox(VenturiFlow.ukc, this.fkc, false);
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u6cc7');
        }
        return new String(array);
    }
    
    static {
        VenturiFlow.Ea = _(VenturiFlow.Ea);
        VenturiFlow.Fa = _(VenturiFlow.Fa);
        VenturiFlow.Ga = _(VenturiFlow.Ga);
        VenturiFlow.Ha = _(VenturiFlow.Ha);
        VenturiFlow.Ia = _(VenturiFlow.Ia);
        VenturiFlow.Ja = _(VenturiFlow.Ja);
        VenturiFlow.Ka = _(VenturiFlow.Ka);
        VenturiFlow.La = _(VenturiFlow.La);
        VenturiFlow.Ma = _(VenturiFlow.Ma);
        VenturiFlow.Na = _(VenturiFlow.Na);
        VenturiFlow.fb = _(VenturiFlow.fb);
        VenturiFlow.gb = _(VenturiFlow.gb);
        VenturiFlow.hb = _(VenturiFlow.hb);
        VenturiFlow.ib = _(VenturiFlow.ib);
        VenturiFlow.jb = _(VenturiFlow.jb);
        VenturiFlow.kb = _(VenturiFlow.kb);
        VenturiFlow.lb = _(VenturiFlow.lb);
        VenturiFlow.mb = _(VenturiFlow.mb);
        VenturiFlow.nb = _(VenturiFlow.nb);
        VenturiFlow.ob = _(VenturiFlow.ob);
        VenturiFlow.pb = _(VenturiFlow.pb);
        VenturiFlow.qb = _(VenturiFlow.qb);
        VenturiFlow.rb = _(VenturiFlow.rb);
        VenturiFlow.sb = _(VenturiFlow.sb);
        VenturiFlow.tb = _(VenturiFlow.tb);
        VenturiFlow._ = _(VenturiFlow._);
        VenturiFlow.a = _(VenturiFlow.a);
        VenturiFlow.b = _(VenturiFlow.b);
        VenturiFlow.c = _(VenturiFlow.c);
        VenturiFlow.d = _(VenturiFlow.d);
        VenturiFlow.e = _(VenturiFlow.e);
        VenturiFlow.f = _(VenturiFlow.f);
        VenturiFlow.g = _(VenturiFlow.g);
        VenturiFlow.h = _(VenturiFlow.h);
        VenturiFlow.i = _(VenturiFlow.i);
        VenturiFlow.j = _(VenturiFlow.j);
        VenturiFlow.k = _(VenturiFlow.k);
        VenturiFlow.l = _(VenturiFlow.l);
        VenturiFlow.m = _(VenturiFlow.m);
        VenturiFlow.n = _(VenturiFlow.n);
        VenturiFlow.o = _(VenturiFlow.o);
        VenturiFlow.p = _(VenturiFlow.p);
        VenturiFlow.q = _(VenturiFlow.q);
        VenturiFlow.r = _(VenturiFlow.r);
        VenturiFlow.s = _(VenturiFlow.s);
        VenturiFlow.t = _(VenturiFlow.t);
        VenturiFlow.u = _(VenturiFlow.u);
        VenturiFlow.v = _(VenturiFlow.v);
        VenturiFlow.w = _(VenturiFlow.w);
        VenturiFlow.x = _(VenturiFlow.x);
        VenturiFlow.y = _(VenturiFlow.y);
        VenturiFlow.z = _(VenturiFlow.z);
        VenturiFlow.A = _(VenturiFlow.A);
        VenturiFlow.B = _(VenturiFlow.B);
        VenturiFlow.C = _(VenturiFlow.C);
        VenturiFlow.D = _(VenturiFlow.D);
        VenturiFlow.E = _(VenturiFlow.E);
        VenturiFlow.F = _(VenturiFlow.F);
        VenturiFlow.G = _(VenturiFlow.G);
        VenturiFlow.H = _(VenturiFlow.H);
        VenturiFlow.I = _(VenturiFlow.I);
        VenturiFlow.J = _(VenturiFlow.J);
        VenturiFlow.K = _(VenturiFlow.K);
        VenturiFlow.L = _(VenturiFlow.L);
        VenturiFlow.M = _(VenturiFlow.M);
        VenturiFlow.N = _(VenturiFlow.N);
        VenturiFlow.O = _(VenturiFlow.O);
        VenturiFlow.P = _(VenturiFlow.P);
        VenturiFlow.Q = _(VenturiFlow.Q);
        VenturiFlow.R = _(VenturiFlow.R);
        VenturiFlow.S = _(VenturiFlow.S);
        VenturiFlow.T = _(VenturiFlow.T);
        VenturiFlow.U = _(VenturiFlow.U);
        VenturiFlow.V = _(VenturiFlow.V);
        VenturiFlow.W = _(VenturiFlow.W);
        VenturiFlow.X = _(VenturiFlow.X);
        VenturiFlow.Y = _(VenturiFlow.Y);
        VenturiFlow.Z = _(VenturiFlow.Z);
        VenturiFlow._a = _(VenturiFlow._a);
        VenturiFlow.aa = _(VenturiFlow.aa);
        VenturiFlow.ba = _(VenturiFlow.ba);
        VenturiFlow.ca = _(VenturiFlow.ca);
        VenturiFlow.da = _(VenturiFlow.da);
        VenturiFlow.ea = _(VenturiFlow.ea);
        VenturiFlow.fa = _(VenturiFlow.fa);
        VenturiFlow.ga = _(VenturiFlow.ga);
        VenturiFlow.ikc = _(VenturiFlow.ikc);
        VenturiFlow.jkc = _(VenturiFlow.jkc);
        VenturiFlow.kkc = _(VenturiFlow.kkc);
        VenturiFlow.lkc = _(VenturiFlow.lkc);
        VenturiFlow.mkc = _(VenturiFlow.mkc);
        VenturiFlow.nkc = _(VenturiFlow.nkc);
        VenturiFlow.okc = _(VenturiFlow.okc);
        VenturiFlow.pkc = _(VenturiFlow.pkc);
        VenturiFlow.qkc = _(VenturiFlow.qkc);
        VenturiFlow.rkc = _(VenturiFlow.rkc);
        VenturiFlow.skc = _(VenturiFlow.skc);
        VenturiFlow.tkc = _(VenturiFlow.tkc);
        VenturiFlow.ukc = _(VenturiFlow.ukc);
    }
}
