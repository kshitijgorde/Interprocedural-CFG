// 
// Decompiled by Procyon v0.5.30
// 

package com.microstar.xml;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Enumeration;
import java.io.EOFException;
import java.net.URLConnection;
import java.util.Stack;
import java.io.InputStream;
import java.io.Reader;
import java.util.Hashtable;

public class a
{
    private static final boolean W = true;
    public static final int null = 0;
    public static final int s = 1;
    public static final int af = 2;
    public static final int F = 3;
    public static final int J = 4;
    public static final int r = 0;
    public static final int q = 1;
    public static final int for = 2;
    public static final int j = 3;
    public static final int R = 0;
    public static final int try = 1;
    public static final int an = 2;
    public static final int ai = 3;
    public static final int at = 4;
    public static final int new = 5;
    public static final int A = 6;
    public static final int f = 7;
    public static final int i = 8;
    public static final int X = 9;
    public static final int B = 10;
    private static Hashtable do;
    private static final int g = 1;
    private static final int L = 2;
    private static final int ac = 3;
    private static final int T = 4;
    private static final int P = 5;
    private static final int ah = 6;
    private static final int G = 7;
    private static final int ap = 8;
    public static final int am = 0;
    public static final int ak = 1;
    public static final int ag = 2;
    public static final int y = 3;
    public static final int as = 4;
    private static final int case = 0;
    private static final int a = 1;
    private static final int p = 2;
    private static final int ae = 3;
    private static final int ar = 4;
    private static final int v = 5;
    private static final int z = 1;
    private static final int d = 2;
    private static final int x = 4;
    private static final int O = 8;
    private static final int int = 0;
    private static final int av = 1;
    private static final int H = 2;
    private static final int K = 3;
    XmlHandler if;
    private Reader goto;
    private InputStream o;
    private int char;
    private int t;
    private int c;
    private Stack m;
    private URLConnection I;
    private int au;
    private int ab;
    private int b;
    private static final int N = 16384;
    private char[] aa;
    private int D;
    private int u;
    private int C;
    private byte[] else;
    private static int w;
    private char[] al;
    private int V;
    private static int k;
    private char[] e;
    private int void;
    private Hashtable Z;
    private Hashtable ao;
    private Hashtable h;
    private String aq;
    private int S;
    private String M;
    private String l;
    private Reader byte;
    private InputStream Q;
    private Stack n;
    private int U;
    private Object[] aj;
    private static final int E = 1087;
    private String[] Y;
    private int long;
    private boolean ad;
    
    public void a(final XmlHandler if1) {
        this.if = if1;
    }
    
    public void a(final String s, final String s2, final String s3) throws Exception {
        this.a(s, s2, null, null, s3);
    }
    
    public void a(final String s, final String s2, final InputStream inputStream, final String s3) throws Exception {
        this.a(s, s2, null, inputStream, s3);
    }
    
    public void a(final String s, final String s2, final Reader reader) throws Exception {
        this.a(s, s2, reader, null, null);
    }
    
    private synchronized void a(final String l, final String m, final Reader byte1, final InputStream q, final String s) throws Exception {
        this.M = m;
        this.l = l;
        this.byte = byte1;
        this.Q = q;
        this.r();
        this.do(this.new("amp"), "&#38;");
        this.do(this.new("lt"), "&#60;");
        this.do(this.new("gt"), "&#62;");
        this.do(this.new("apos"), "&#39;");
        this.do(this.new("quot"), "&#34;");
        if (this.if != null) {
            this.if.if();
        }
        this.a("[document]", this.M, this.l, this.byte, this.Q, s);
        this.int();
        if (this.if != null) {
            this.if.a();
        }
        this.g();
    }
    
    void if(String s, final String s2, final String s3) throws Exception {
        ++this.b;
        if (s2 != null) {
            s = s + " (found \"" + s2 + "\")";
        }
        if (s3 != null) {
            s = s + " (expected \"" + s3 + "\")";
        }
        if (this.if != null) {
            String string = null;
            if (this.I != null) {
                string = this.I.getURL().toString();
            }
            this.if.a(s, string, this.char, this.t);
        }
    }
    
    void a(final String s, final char c, final String s2) throws Exception {
        this.if(s, new Character(c).toString(), s2);
    }
    
    void int() throws Exception {
        this.i();
        this.for('<');
        this.if();
        try {
            this.l();
            this.a("unexpected characters after document end", this.n(), null);
        }
        catch (EOFException ex) {}
    }
    
    void for() throws Exception {
        this.a("-->");
    }
    
    void m() throws Exception {
        final String a = this.a(true);
        if (!this.do("?>")) {
            this.do();
            this.case("?>");
        }
        if (this.if != null) {
            this.if.if(a, this.try());
        }
    }
    
    void f() throws Exception {
        this.case("]]>");
    }
    
    void i() throws Exception {
        this.l();
        if (this.do("<!DOCTYPE")) {
            this.new();
            this.l();
        }
    }
    
    void int(final boolean b) throws Exception {
        this.int("version");
        this.o();
        final String a = this.a(0);
        if (!a.equals("1.0")) {
            this.if("unsupported XML version", a, "1.0");
        }
        this.e();
        if (this.do("encoding")) {
            this.o();
            this.a(this.a(0), b);
        }
        this.e();
        if (this.do("standalone")) {
            this.o();
            this.a(0);
        }
        this.e();
        this.int("?>");
    }
    
    void new(final boolean b) throws Exception {
        if (this.do("version")) {
            this.o();
            final String a = this.a(0);
            if (!a.equals("1.0")) {
                this.if("unsupported XML version", a, "1.0");
            }
            this.do();
        }
        this.int("encoding");
        this.o();
        this.a(this.a(0), b);
        this.e();
        this.int("?>");
    }
    
    void a(String upperCase, final boolean b) throws Exception {
        upperCase = upperCase.toUpperCase();
        if (b) {
            return;
        }
        switch (this.au) {
            case 1: {
                if (upperCase.equals("ISO-8859-1")) {
                    this.au = 2;
                    break;
                }
                if (!upperCase.equals("UTF-8")) {
                    this.if("unsupported 8-bit encoding", upperCase, "UTF-8 or ISO-8859-1");
                    break;
                }
                break;
            }
            case 3:
            case 4: {
                if (!upperCase.equals("ISO-10646-UCS-2") && !upperCase.equals("UTF-16")) {
                    this.if("unsupported 16-bit encoding", upperCase, "ISO-10646-UCS-2");
                    break;
                }
                break;
            }
            case 5:
            case 6:
            case 7:
            case 8: {
                if (!upperCase.equals("ISO-10646-UCS-4")) {
                    this.if("unsupported 32-bit encoding", upperCase, "ISO-10646-UCS-4");
                    break;
                }
                break;
            }
        }
    }
    
    void l() throws Exception {
        while (true) {
            this.e();
            if (this.do("<?")) {
                this.m();
            }
            else {
                if (!this.do("<!--")) {
                    break;
                }
                this.for();
            }
        }
    }
    
    void new() throws Exception {
        this.do();
        final String a = this.a(true);
        this.e();
        final String[] if1 = this.if(false);
        this.e();
        if (this.a('[')) {
            while (true) {
                this.U = 1;
                this.e();
                this.U = 0;
                if (this.a(']')) {
                    break;
                }
                this.U = 1;
                this.char();
                this.U = 0;
            }
        }
        if (if1[1] != null) {
            this.a("[external subset]", if1[0], if1[1], null, null, null);
            while (true) {
                this.U = 1;
                this.e();
                this.U = 0;
                if (this.a('>')) {
                    break;
                }
                this.U = 1;
                this.char();
                this.U = 0;
            }
        }
        else {
            this.e();
            this.for('>');
        }
        if (this.if != null) {
            this.if.a(a, if1[0], if1[1]);
        }
    }
    
    void char() throws Exception {
        if (this.do("<!ELEMENT")) {
            this.x();
        }
        else if (this.do("<!ATTLIST")) {
            this.null();
        }
        else if (this.do("<!ENTITY")) {
            this.else();
        }
        else if (this.do("<!NOTATION")) {
            this.z();
        }
        else if (this.do("<?")) {
            this.m();
        }
        else if (this.do("<!--")) {
            this.for();
        }
        else if (this.do("<![")) {
            this.y();
        }
        else {
            this.if("expected markup declaration", null, null);
        }
    }
    
    void if() throws Exception {
        final int s = this.S;
        final String aq = this.aq;
        this.long = 0;
        final String a = this.a(true);
        this.aq = a;
        this.S = this.if(a);
        if (this.S == 0) {
            this.S = 1;
        }
        this.e();
        char c;
        for (c = this.n(); c != '/' && c != '>'; c = this.n()) {
            this.do(c);
            this.b(a);
            this.e();
        }
        this.do(c);
        final Enumeration void1 = this.void(a);
        if (void1 != null) {
        Label_0182:
            while (void1.hasMoreElements()) {
                final String s2 = void1.nextElement();
                for (int i = 0; i < this.long; ++i) {
                    if (this.Y[i] == s2) {
                        continue Label_0182;
                    }
                }
                if (this.if != null) {
                    this.if.a(s2, this.for(a, s2), false);
                }
            }
        }
        switch (this.n()) {
            case '>': {
                if (this.if != null) {
                    this.if.a(a);
                }
                this.b();
                break;
            }
            case '/': {
                this.for('>');
                if (this.if != null) {
                    this.if.a(a);
                    this.if.do(a);
                    break;
                }
                break;
            }
        }
        this.aq = aq;
        this.S = s;
    }
    
    void b(final String s) throws Exception {
        final String intern = this.a(true).intern();
        final int if1 = this.if(s, intern);
        this.o();
        String s2;
        if (if1 == 1 || if1 == 0) {
            s2 = this.a(3);
        }
        else {
            s2 = this.a(11);
        }
        if (this.if != null) {
            this.if.a(intern, s2, true);
        }
        this.V = 0;
        if (this.long == this.Y.length) {
            final String[] y = new String[this.Y.length * 2];
            System.arraycopy(this.Y, 0, y, 0, this.long);
            this.Y = y;
        }
        this.Y[this.long++] = intern;
    }
    
    void o() throws Exception {
        this.e();
        this.for('=');
        this.e();
    }
    
    void goto() throws Exception {
        final String a = this.a(true);
        if (a != this.aq) {
            this.if("mismatched end tag", a, this.aq);
        }
        this.e();
        this.for('>');
        if (this.if != null) {
            this.if.do(a);
        }
    }
    
    void b() throws Exception {
    Label_0232:
        while (true) {
            switch (this.S) {
                case 1:
                case 3: {
                    this.d();
                    break;
                }
                case 4: {
                    this.void();
                    break;
                }
            }
            switch (this.n()) {
                case '&': {
                    final char n = this.n();
                    if (n == '#') {
                        this.q();
                        continue;
                    }
                    this.do(n);
                    this.try(true);
                    continue;
                }
                case '<': {
                    final char n2 = this.n();
                    switch (n2) {
                        case 33: {
                            final char n3 = this.n();
                            switch (n3) {
                                case 45: {
                                    this.for('-');
                                    this.for();
                                    continue;
                                }
                                case 91: {
                                    this.int("CDATA[");
                                    this.f();
                                    continue;
                                }
                                default: {
                                    this.a("expected comment or CDATA section", n3, null);
                                    continue;
                                }
                            }
                            break;
                        }
                        case 63: {
                            this.v();
                            this.m();
                            continue;
                        }
                        case 47: {
                            break Label_0232;
                        }
                        default: {
                            this.v();
                            this.do(n2);
                            this.if();
                            continue;
                        }
                    }
                    break;
                }
            }
        }
        this.v();
        this.goto();
    }
    
    void x() throws Exception {
        this.do();
        final String a = this.a(true);
        this.do();
        this.c(a);
        this.e();
        this.for('>');
    }
    
    void c(final String s) throws Exception {
        if (this.do("EMPTY")) {
            this.a(s, 2, null, null);
            return;
        }
        if (this.do("ANY")) {
            this.a(s, 1, null, null);
            return;
        }
        this.for('(');
        this.int('(');
        this.e();
        if (this.do("#PCDATA")) {
            this.try("#PCDATA");
            this.p();
            this.a(s, 3, this.try(), null);
        }
        else {
            this.s();
            this.a(s, 4, this.try(), null);
        }
    }
    
    void s() throws Exception {
        this.e();
        this.a();
        this.e();
        final char n = this.n();
        switch (n) {
            case 41: {
                this.int(')');
                final char n2 = this.n();
                switch (n2) {
                    case 42:
                    case 43:
                    case 63: {
                        this.int(n2);
                        break;
                    }
                    default: {
                        this.do(n2);
                        break;
                    }
                }
            }
            case 44:
            case 124: {
                final char c = n;
                this.int(n);
                while (true) {
                    this.e();
                    this.a();
                    this.e();
                    final char n3 = this.n();
                    if (n3 == ')') {
                        this.int(')');
                        final char n4 = this.n();
                        switch (n4) {
                            case 42:
                            case 43:
                            case 63: {
                                this.int(n4);
                                return;
                            }
                            default: {
                                this.do(n4);
                                return;
                            }
                        }
                    }
                    else {
                        if (n3 != c) {
                            this.a("bad separator in content model", n3, null);
                            return;
                        }
                        this.int(n3);
                    }
                }
                break;
            }
            default: {
                this.a("bad separator in content model", n, null);
            }
        }
    }
    
    void a() throws Exception {
        if (this.a('(')) {
            this.int('(');
            this.s();
        }
        else {
            this.try(this.a(true));
            final char n = this.n();
            switch (n) {
                case 42:
                case 43:
                case 63: {
                    this.int(n);
                    break;
                }
                default: {
                    this.do(n);
                    break;
                }
            }
        }
    }
    
    void p() throws Exception {
        this.e();
        if (this.a(')')) {
            this.try(")*");
            this.a('*');
            return;
        }
        this.e();
        while (!this.do(")*")) {
            this.for('|');
            this.int('|');
            this.e();
            this.try(this.a(true));
            this.e();
        }
        this.try(")*");
    }
    
    void null() throws Exception {
        this.do();
        final String a = this.a(true);
        this.do();
        while (!this.a('>')) {
            this.long(a);
            this.e();
        }
    }
    
    void long(final String s) throws Exception {
        String try1 = null;
        final String a = this.a(true);
        this.do();
        final int case1 = this.case();
        if (case1 == 9 || case1 == 10) {
            try1 = this.try();
        }
        this.do();
        this.a(s, a, case1, try1);
    }
    
    int case() throws Exception {
        if (this.a('(')) {
            this.byte();
            return 9;
        }
        final String a = this.a(true);
        if (a.equals("NOTATION")) {
            this.k();
        }
        final Integer n = com.microstar.xml.a.do.get(a);
        if (n == null) {
            this.if("illegal attribute type", a, null);
            return 0;
        }
        return n;
    }
    
    void byte() throws Exception {
        this.int('(');
        this.e();
        this.try(this.a(true));
        this.e();
        while (!this.a(')')) {
            this.for('|');
            this.int('|');
            this.e();
            this.try(this.a(true));
            this.e();
        }
        this.int(')');
    }
    
    void k() throws Exception {
        this.do();
        this.for('(');
        this.byte();
    }
    
    void a(final String s, final String s2, final int n, final String s3) throws Exception {
        int n2 = 1;
        String s4 = null;
        if (this.a('#')) {
            if (this.do("FIXED")) {
                n2 = 4;
                this.do();
                this.U = 3;
                s4 = this.a(1);
                this.U = 1;
            }
            else if (this.do("REQUIRED")) {
                n2 = 3;
            }
            else if (this.do("IMPLIED")) {
                n2 = 2;
            }
            else {
                this.if("illegal keyword for attribute default value", null, null);
            }
        }
        else {
            this.U = 3;
            s4 = this.a(1);
            this.U = 1;
        }
        this.a(s, s2, n, s3, s4, n2);
    }
    
    void y() throws Exception {
        this.e();
        if (this.do("INCLUDE")) {
            this.e();
            this.for('[');
            this.e();
            while (!this.do("]]>")) {
                this.char();
                this.e();
            }
        }
        else if (this.do("IGNORE")) {
            this.e();
            this.for('[');
            int i = 1;
            while (i > 0) {
                switch (this.n()) {
                    case '<': {
                        if (this.do("![")) {
                            ++i;
                        }
                    }
                    case ']': {
                        if (this.do("]>")) {
                            --i;
                            continue;
                        }
                        continue;
                    }
                    default: {
                        continue;
                    }
                }
            }
        }
        else {
            this.if("conditional section must begin with INCLUDE or IGNORE", null, null);
        }
    }
    
    void q() throws Exception {
        int n = 0;
        Label_0405: {
            if (this.a('x')) {
                while (true) {
                    final char n2 = this.n();
                    switch (n2) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102: {
                            n = n * 16 + Integer.parseInt(new Character(n2).toString(), 16);
                            continue;
                        }
                        case 59: {
                            break Label_0405;
                        }
                        default: {
                            this.a("illegal character in character reference", n2, null);
                            break Label_0405;
                        }
                    }
                }
            }
            else {
                while (true) {
                    final char n3 = this.n();
                    switch (n3) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            n = n * 10 + Integer.parseInt(new Character(n3).toString(), 10);
                            continue;
                        }
                        case 59: {
                            break Label_0405;
                        }
                        default: {
                            this.a("illegal character in character reference", n3, null);
                            break Label_0405;
                        }
                    }
                }
            }
        }
        if (n <= 65535) {
            this.int((char)n);
        }
        else if (n <= 1048575) {
            this.int((char)(0xD8 | (n & 0xFFC00) >> 10));
            this.int((char)(0xDC | (n & 0x3FF)));
        }
        else {
            this.if("character reference " + n + " is too large for UTF-16", new Integer(n).toString(), null);
        }
    }
    
    void try(final boolean b) throws Exception {
        final String a = this.a(true);
        this.for(';');
        switch (this.byte(a)) {
            case 0: {
                this.if("reference to undeclared entity", a, null);
                break;
            }
            case 1: {
                this.a(a, this.else(a));
                break;
            }
            case 3: {
                if (b) {
                    this.a(a, this.goto(a), this.for(a), null, null, null);
                    break;
                }
                this.if("reference to external entity in attribute value.", a, null);
                break;
            }
            case 2: {
                if (b) {
                    this.if("data entity reference in content", a, null);
                    break;
                }
                this.if("reference to external entity in attribute value.", a, null);
                break;
            }
        }
    }
    
    void do(final boolean b) throws Exception {
        final String string = "%" + this.a(true);
        this.for(';');
        switch (this.byte(string)) {
            case 0: {
                this.if("reference to undeclared parameter entity", string, null);
                break;
            }
            case 1: {
                if (b) {
                    this.a(string, this.else(string));
                    break;
                }
                this.a(string, " " + this.else(string) + ' ');
                break;
            }
            case 3: {
                if (b) {
                    this.a(null, " ");
                }
                this.a(string, this.goto(string), this.for(string), null, null, null);
                if (b) {
                    this.a(null, " ");
                    break;
                }
                break;
            }
        }
    }
    
    void else() throws Exception {
        boolean b = false;
        this.do();
        if (this.a('%')) {
            b = true;
            this.do();
        }
        String s = this.a(true);
        if (b) {
            s = "%" + s;
        }
        this.do();
        final char n = this.n();
        this.do(n);
        if (n == '\"' || n == '\'') {
            this.U = 2;
            final String a = this.a(5);
            this.U = 1;
            this.do(s, a);
        }
        else {
            final String[] if1 = this.if(false);
            if (if1[1] == null) {
                this.if("system identifer missing", s, null);
            }
            this.e();
            if (this.do("NDATA")) {
                this.do();
                this.a(s, if1[0], if1[1], this.a(true));
            }
            else {
                this.do(s, if1[0], if1[1]);
            }
        }
        this.e();
        this.for('>');
    }
    
    void z() throws Exception {
        this.do();
        final String a = this.a(true);
        this.do();
        final String[] if1 = this.if(true);
        if (if1[0] == null && if1[1] == null) {
            this.if("external identifer missing", a, null);
        }
        this.for(a, if1[0], if1[1]);
        this.e();
        this.for('>');
    }
    
    void d() throws Exception {
        int n = 0;
        int t = 0;
        for (int i = this.D; i < this.u; ++i) {
            switch (this.aa[i]) {
                case '\n': {
                    ++n;
                    t = 0;
                    break;
                }
                case '&':
                case '<': {
                    final int d = this.D;
                    ++t;
                    this.D = i;
                    if (n > 0) {
                        this.char += n;
                        this.t = t;
                    }
                    else {
                        this.t += t;
                    }
                    this.if(this.aa, d, i - d);
                    return;
                }
                default: {
                    ++t;
                    break;
                }
            }
        }
        char n2 = '\0';
    Label_0176:
        while (true) {
            n2 = this.n();
            switch (n2) {
                case 38:
                case 60: {
                    break Label_0176;
                }
                default: {
                    this.int(n2);
                    continue;
                }
            }
        }
        this.do(n2);
    }
    
    void do() throws Exception {
        final char n = this.n();
        if (this.if(n)) {
            this.e();
        }
        else {
            this.a("whitespace expected", n, null);
        }
    }
    
    void void() throws Exception {
        char c;
        for (c = this.n(); this.if(c); c = this.n()) {
            this.int(c);
        }
        this.do(c);
    }
    
    void e() throws Exception {
        int n = 0;
        int t = 0;
        int i = this.D;
    Label_0150:
        while (i < this.u) {
            Label_0101: {
                switch (this.aa[i]) {
                    case '\t':
                    case '\r':
                    case ' ': {
                        ++t;
                        break;
                    }
                    case '\n': {
                        ++n;
                        t = 0;
                        break;
                    }
                    case '%': {
                        if (this.U == 1) {
                            break Label_0150;
                        }
                        if (this.U == 2) {
                            break Label_0150;
                        }
                        break Label_0101;
                    }
                }
                ++i;
                continue;
            }
            this.D = i;
            if (n > 0) {
                this.char += n;
                this.t = t;
            }
            else {
                this.t += t;
            }
            return;
        }
        char c;
        for (c = this.n(); this.if(c); c = this.n()) {}
        this.do(c);
    }
    
    String a(final boolean b) throws Exception {
        int i = this.D;
    Label_0267:
        while (i < this.u) {
            switch (this.aa[i]) {
                case '%': {
                    if (this.U == 1) {
                        break Label_0267;
                    }
                    if (this.U == 2) {
                        break Label_0267;
                    }
                }
                case '\t':
                case '\n':
                case '\r':
                case ' ':
                case '\"':
                case '#':
                case '&':
                case '\'':
                case ')':
                case '*':
                case '+':
                case ',':
                case '/':
                case ';':
                case '<':
                case '=':
                case '>':
                case '?':
                case '[':
                case '|': {
                    final int d = this.D;
                    if (i == d) {
                        this.a("name expected", this.aa[i], null);
                    }
                    this.D = i;
                    return this.a(this.aa, d, i - d);
                }
                default: {
                    ++i;
                    continue;
                }
            }
        }
        this.void = 0;
        char n = '\0';
    Label_0452:
        while (true) {
            n = this.n();
            switch (n) {
                case 9:
                case 10:
                case 13:
                case 32:
                case 34:
                case 37:
                case 38:
                case 39:
                case 41:
                case 42:
                case 43:
                case 44:
                case 47:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 91:
                case 124: {
                    break Label_0452;
                }
                default: {
                    (this.e = (char[])this.a((Object)this.e, this.e.length, this.void))[this.void++] = n;
                    continue;
                }
            }
        }
        this.do(n);
        if (this.void == 0) {
            this.if("name expected", null, null);
        }
        final String a = this.a(this.e, 0, this.void);
        this.void = 0;
        return a;
    }
    
    String a(final int n) throws Exception {
        final int char1 = this.char;
        final char n2 = this.n();
        if (n2 != '\"' && n2 != '\'' && n2 != '\0') {
            this.a("expected '\"' or \"'\"", n2, null);
            return null;
        }
        try {
            char c = this.n();
            while (c != n2) {
                switch (c) {
                    case '\n':
                    case '\r': {
                        c = ' ';
                        break;
                    }
                    case '&': {
                        if ((n & 0x1) <= 0) {
                            break;
                        }
                        c = this.n();
                        if (c == '#') {
                            this.q();
                            c = this.n();
                            continue;
                        }
                        if ((n & 0x2) > 0) {
                            this.do(c);
                            this.try(false);
                            c = this.n();
                            continue;
                        }
                        this.int('&');
                        break;
                    }
                }
                this.int(c);
                c = this.n();
            }
        }
        catch (EOFException ex) {
            this.if("end of input while looking for delimiter (started on line " + char1 + ')', null, new Character(n2).toString());
        }
        if ((n & 0x8) > 0) {
            this.j();
        }
        return this.try();
    }
    
    String[] if(final boolean b) throws Exception {
        final String[] array = new String[2];
        if (this.do("PUBLIC")) {
            this.do();
            array[0] = this.a(8);
            if (b) {
                this.e();
                if (this.a('\"') || this.a('\'')) {
                    array[1] = this.a(0);
                }
            }
            else {
                this.do();
                array[1] = this.a(0);
            }
        }
        else if (this.do("SYSTEM")) {
            this.do();
            array[1] = this.a(0);
        }
        return array;
    }
    
    final boolean if(final char c) {
        switch (c) {
            case '\t':
            case '\n':
            case '\r':
            case ' ': {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    void int(final char c) {
        (this.al = (char[])this.a((Object)this.al, this.al.length, this.V))[this.V++] = c;
    }
    
    void try(final String s) {
        this.if(s.toCharArray(), 0, s.length());
    }
    
    void if(final char[] array, final int n, final int n2) {
        System.arraycopy(array, n, this.al = (char[])this.a((Object)this.al, this.al.length, this.V + n2), this.V, n2);
        this.V += n2;
    }
    
    void j() {
        int v = 0;
        int i;
        int j;
        for (i = 0, j = this.V; i < j; ++i) {
            if (!this.if(this.al[i])) {
                break;
            }
        }
        while (j > i) {
            if (!this.if(this.al[j - 1])) {
                break;
            }
            --j;
        }
        while (i < j) {
            final char c = this.al[i++];
            if (this.if(c)) {
                while (i < j && this.if(this.al[i++])) {}
                this.al[v++] = ' ';
                this.al[v++] = this.al[i - 1];
            }
            else {
                this.al[v++] = c;
            }
        }
        this.V = v;
    }
    
    String try() {
        final String s = new String(this.al, 0, this.V);
        this.V = 0;
        return s;
    }
    
    void v() throws Exception {
        if (this.V > 0) {
            switch (this.S) {
                case 1:
                case 3: {
                    if (this.if != null) {
                        this.if.if(this.al, 0, this.V);
                        break;
                    }
                    break;
                }
                case 4: {
                    if (this.if != null) {
                        this.if.a(this.al, 0, this.V);
                        break;
                    }
                    break;
                }
            }
            this.V = 0;
        }
    }
    
    void int(final String s) throws Exception {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            this.for(charArray[i]);
        }
    }
    
    void for(final char c) throws Exception {
        final char n = this.n();
        if (n != c) {
            this.a("expected character", n, new Character(c).toString());
        }
    }
    
    public String new(final String s) {
        final char[] charArray = s.toCharArray();
        return this.a(charArray, 0, charArray.length);
    }
    
    public String a(final char[] array, final int n, final int n2) {
        int n3 = 0;
        for (int i = n; i < n + n2; ++i) {
            n3 = (n3 << 1 & 0xFFFFFF) + array[i];
        }
        final int n4 = n3 % 1087;
        Object o = this.aj[n4];
        if (o == null) {
            o = (this.aj[n4] = new Object[8]);
        }
        int j;
        for (j = 0; j < o.length; j += 2) {
            final char[] array2 = (char[])o[j];
            if (array2 == null) {
                break;
            }
            if (array2.length == n2) {
                for (int k = 0; k < array2.length; ++k) {
                    if (array[n + k] != array2[k]) {
                        break;
                    }
                    if (k == n2 - 1) {
                        return (String)o[j + 1];
                    }
                }
            }
        }
        final Object[] array3 = (Object[])this.a(o, o.length, j);
        final String s = new String(array, n, n2);
        array3[j] = s.toCharArray();
        array3[j + 1] = s;
        this.aj[n4] = array3;
        return s;
    }
    
    Object a(final Object o, final int n, final int n2) {
        if (n2 < n) {
            return o;
        }
        Object o2 = null;
        if (n * 2 <= n2) {}
        if (o instanceof char[]) {
            o2 = new char[n * 2];
        }
        else if (o instanceof Object[]) {
            o2 = new Object[n * 2];
        }
        System.arraycopy(o, 0, o2, 0, n);
        return o2;
    }
    
    public int if(final String s) {
        final Object[] array = this.Z.get(s);
        if (array == null) {
            return 0;
        }
        return (int)array[0];
    }
    
    void a(final String s, final int n, final String s2, final Hashtable hashtable) throws Exception {
        Object[] array = this.Z.get(s);
        if (array == null) {
            array = new Object[] { new Integer(0), null, null };
        }
        else if (n != 0 && (int)array[0] != 0) {
            this.if("multiple declarations for element type", s, null);
            return;
        }
        if (n != 0) {
            array[0] = new Integer(n);
        }
        if (s2 != null) {
            array[1] = s2;
        }
        if (hashtable != null) {
            array[2] = hashtable;
        }
        this.Z.put(s, array);
    }
    
    Hashtable char(final String s) {
        final Object[] array = this.Z.get(s);
        if (array == null) {
            return null;
        }
        return (Hashtable)array[2];
    }
    
    public Enumeration void(final String s) {
        final Hashtable char1 = this.char(s);
        if (char1 == null) {
            return null;
        }
        return char1.keys();
    }
    
    public String for(final String s, final String s2) {
        final Object[] int1 = this.int(s, s2);
        if (int1 == null) {
            return null;
        }
        if (int1[4] == null && int1[1] != null) {
            try {
                this.a(null, '\0' + (String)int1[1] + '\0');
                int1[4] = this.a(11);
            }
            catch (Exception ex) {}
        }
        return (String)int1[4];
    }
    
    public int if(final String s, final String s2) {
        final Object[] int1 = this.int(s, s2);
        if (int1 == null) {
            return 0;
        }
        return (int)int1[2];
    }
    
    void a(final String s, final String s2, final int n, final String s3, final String s4, final int n2) throws Exception {
        Hashtable<String, Object[]> char1 = (Hashtable<String, Object[]>)this.char(s);
        if (char1 == null) {
            char1 = new Hashtable<String, Object[]>();
        }
        if (char1.get(s2) != null) {
            return;
        }
        char1.put(s2.intern(), new Object[] { new Integer(n), s4, new Integer(n2), s3, null });
        this.a(s, 0, null, char1);
    }
    
    Object[] int(final String s, final String s2) {
        final Hashtable char1 = this.char(s);
        if (char1 == null) {
            return null;
        }
        return char1.get(s2);
    }
    
    public int byte(final String s) {
        final Object[] array = this.ao.get(s);
        if (array == null) {
            return 0;
        }
        return (int)array[0];
    }
    
    public String goto(final String s) {
        final Object[] array = this.ao.get(s);
        if (array == null) {
            return null;
        }
        return (String)array[1];
    }
    
    public String for(final String s) {
        final Object[] array = this.ao.get(s);
        if (array == null) {
            return null;
        }
        return (String)array[2];
    }
    
    public String else(final String s) {
        final Object[] array = this.ao.get(s);
        if (array == null) {
            return null;
        }
        return (String)array[3];
    }
    
    void do(final String s, final String s2) {
        this.a(s, 1, null, null, s2, null);
    }
    
    void a(final String s, final String s2, final String s3, final String s4) {
        this.a(s, 2, s2, s3, null, s4);
    }
    
    void do(final String s, final String s2, final String s3) {
        this.a(s, 3, s2, s3, null, null);
    }
    
    void a(final String s, final int n, final String s2, final String s3, final String s4, final String s5) {
        if (this.ao.get(s) == null) {
            this.ao.put(s, new Object[] { new Integer(n), s2, s3, s4, s5 });
        }
    }
    
    void for(final String s, final String s2, final String s3) throws Exception {
        if (this.h.get(s) == null) {
            this.h.put(s, new Object[] { s2, s3 });
        }
        else {
            this.if("multiple declarations of notation", s, null);
        }
    }
    
    char n() throws Exception {
        while (this.D >= this.u) {
            switch (this.c) {
                case 2:
                case 3:
                case 5: {
                    this.t();
                    while (this.u < 1) {
                        this.u();
                        if (this.u < 1) {
                            this.t();
                        }
                    }
                    continue;
                }
                default: {
                    this.u();
                    continue;
                }
            }
        }
        final char c = this.aa[this.D++];
        if (c == '%' && (this.U == 1 || this.U == 2)) {
            final char n = this.n();
            this.do(n);
            if (!this.if(n)) {
                this.do(this.U == 2);
                return this.n();
            }
        }
        if (c == '\n') {
            ++this.char;
            this.t = 0;
        }
        else {
            ++this.t;
        }
        return c;
    }
    
    void do(final char c) throws Exception {
        if (c == '\n') {
            --this.char;
            this.t = -1;
        }
        if (this.D > 0) {
            this.aa[--this.D] = c;
        }
        else {
            this.a(null, new Character(c).toString());
        }
    }
    
    void a(final char[] array, final int n) throws Exception {
        for (int i = 0; i < n; ++i) {
            if (array[i] == '\n') {
                --this.char;
                this.t = -1;
            }
        }
        if (n < this.D) {
            this.D -= n;
        }
        else {
            this.a(null, array, 0, n);
            this.c = 4;
        }
    }
    
    void a(final String s, final String s2, String s3, Reader goto1, InputStream o, String contentEncoding) throws Exception {
        this.null(s);
        this.aa = new char[16388];
        this.D = 0;
        this.u = 0;
        this.C = -1;
        this.o = null;
        this.char = 1;
        this.ab = 0;
        this.v();
        if (s3 != null && this.I != null) {
            s3 = new URL(this.I.getURL(), s3).toString();
        }
        else if (this.l != null) {
            try {
                s3 = new URL(new URL(this.l), s3).toString();
            }
            catch (Exception ex) {}
        }
        if (s3 != null && this.if != null) {
            final Object a = this.if.a(s2, s3);
            if (a != null) {
                if (a instanceof String) {
                    s3 = (String)a;
                }
                else if (a instanceof InputStream) {
                    o = (InputStream)a;
                }
                else if (a instanceof Reader) {
                    goto1 = (Reader)a;
                }
            }
        }
        if (this.if != null) {
            if (s3 != null) {
                this.if.if(s3);
            }
            else {
                this.if.if("[external stream]");
            }
        }
        if (goto1 != null) {
            this.c = 5;
            this.goto = goto1;
            this.for(true);
            return;
        }
        if (o != null) {
            this.c = 3;
            this.o = o;
        }
        else {
            this.c = 2;
            (this.I = new URL(s3).openConnection()).connect();
            this.o = this.I.getInputStream();
        }
        if (!this.o.markSupported()) {
            this.o = new BufferedInputStream(this.o);
        }
        if (contentEncoding == null && this.I != null) {
            contentEncoding = this.I.getContentEncoding();
        }
        boolean b;
        if (contentEncoding != null) {
            this.a(contentEncoding, false);
            b = true;
        }
        else {
            this.c();
            b = false;
        }
        this.for(b);
    }
    
    void for(final boolean b) throws Exception {
        if (this.do("<?xml")) {
            if (this.w()) {
                if (this.m.size() > 0) {
                    this.new(b);
                }
                else {
                    this.int(b);
                }
            }
            else {
                this.a("xml".toCharArray(), 3);
                this.m();
            }
        }
    }
    
    void c() throws Exception {
        final byte[] array = new byte[4];
        this.o.mark(4);
        this.o.read(array);
        this.o.reset();
        if (this.a(array, (byte)0, (byte)0, (byte)0, (byte)60)) {
            this.au = 5;
        }
        else if (this.a(array, (byte)60, (byte)0, (byte)0, (byte)0)) {
            this.au = 6;
        }
        else if (this.a(array, (byte)0, (byte)0, (byte)60, (byte)0)) {
            this.au = 7;
        }
        else if (this.a(array, (byte)0, (byte)60, (byte)0, (byte)0)) {
            this.au = 8;
        }
        else if (this.a(array, (byte)(-2), (byte)(-1))) {
            this.au = 3;
            this.o.read();
            this.o.read();
        }
        else if (this.a(array, (byte)(-1), (byte)(-2))) {
            this.au = 4;
            this.o.read();
            this.o.read();
        }
        else if (this.a(array, (byte)0, (byte)60, (byte)0, (byte)63)) {
            this.au = 3;
            this.if("no byte-order mark for UCS-2 entity", null, null);
        }
        else if (this.a(array, (byte)60, (byte)0, (byte)63, (byte)0)) {
            this.au = 4;
            this.if("no byte-order mark for UCS-2 entity", null, null);
        }
        else if (this.a(array, (byte)60, (byte)63, (byte)120, (byte)109)) {
            this.au = 1;
            this.long();
        }
        else {
            this.au = 1;
        }
    }
    
    boolean a(final byte[] array, final byte b, final byte b2, final byte b3, final byte b4) {
        return array[0] == b && array[1] == b2 && array[2] == b3 && array[3] == b4;
    }
    
    boolean a(final byte[] array, final byte b, final byte b2) {
        return array[0] == b && array[1] == b2;
    }
    
    void a(final String s, final String s2) throws Exception {
        final char[] charArray = s2.toCharArray();
        this.a(s, charArray, 0, charArray.length);
    }
    
    void a(final String s, final char[] aa, final int d, final int u) throws Exception {
        this.null(s);
        this.c = 1;
        this.aa = aa;
        this.D = d;
        this.u = u;
        this.C = -1;
    }
    
    void null(final String s) throws Exception {
        final Object[] array = new Object[12];
        if (s != null) {
            final Enumeration elements = this.n.elements();
            while (elements.hasMoreElements()) {
                if (elements.nextElement() == s) {
                    this.if("recursive reference to entity", s, null);
                }
            }
        }
        this.n.push(s);
        if (this.c == 0) {
            return;
        }
        array[0] = new Integer(this.c);
        array[1] = this.I;
        array[2] = this.aa;
        array[3] = new Integer(this.D);
        array[4] = new Integer(this.u);
        array[5] = new Integer(this.char);
        array[6] = new Integer(this.au);
        array[7] = new Integer(this.C);
        array[8] = this.o;
        array[9] = new Integer(this.ab);
        array[10] = new Integer(this.t);
        array[11] = this.goto;
        this.m.push(array);
    }
    
    void u() throws Exception {
        switch (this.c) {
            case 2: {
                this.v();
                if (this.if != null && this.I != null) {
                    this.if.for(this.I.getURL().toString());
                    break;
                }
                break;
            }
            case 3: {
                this.v();
                if (this.l != null && this.if != null) {
                    this.if.for(this.l);
                    break;
                }
                break;
            }
            case 5: {
                this.v();
                if (this.l != null && this.if != null) {
                    this.if.for(this.l);
                    break;
                }
                break;
            }
        }
        if (this.m.isEmpty()) {
            throw new EOFException();
        }
        final Object[] array = this.m.pop();
        final String s = this.n.pop();
        this.c = (int)array[0];
        this.I = (URLConnection)array[1];
        this.aa = (char[])array[2];
        this.D = (int)array[3];
        this.u = (int)array[4];
        this.char = (int)array[5];
        this.au = (int)array[6];
        this.C = (int)array[7];
        this.o = (InputStream)array[8];
        this.ab = (int)array[9];
        this.t = (int)array[10];
        this.goto = (Reader)array[11];
    }
    
    boolean a(final char c) throws Exception {
        final char n = this.n();
        if (n == c) {
            return true;
        }
        this.do(n);
        return false;
    }
    
    boolean do(final String s) throws Exception {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            final char n = this.n();
            if (n != charArray[i]) {
                this.do(n);
                if (i != 0) {
                    this.a(charArray, i);
                }
                return false;
            }
        }
        return true;
    }
    
    boolean w() throws Exception {
        final char n = this.n();
        if (this.if(n)) {
            this.e();
            return true;
        }
        this.do(n);
        return false;
    }
    
    void case(final String s) throws Exception {
        final int char1 = this.char;
        try {
            while (!this.do(s)) {
                this.int(this.n());
            }
        }
        catch (EOFException ex) {
            this.if("end of input while looking for delimiter (started on line " + char1 + ')', null, s);
        }
    }
    
    void a(final String s) throws Exception {
        while (!this.do(s)) {
            this.n();
        }
    }
    
    void long() throws Exception {
        final boolean b = false;
        this.u = (b ? 1 : 0);
        this.D = (b ? 1 : 0);
        while (true) {
            final int read = this.o.read();
            this.aa[this.u++] = (char)read;
            switch (read) {
                case 62: {}
                case -1: {
                    this.if("end of file before end of XML or encoding declaration.", null, "?>");
                }
                default: {
                    if (this.aa.length == this.u) {
                        this.if("unfinished XML or encoding declaration", null, null);
                        continue;
                    }
                    continue;
                }
            }
        }
    }
    
    void t() throws Exception {
        if (this.C > -1) {
            this.aa[0] = (char)this.C;
            this.C = -1;
            this.D = 1;
            this.ad = true;
        }
        else {
            this.D = 0;
            this.ad = false;
        }
        if (this.c == 5) {
            final int read = this.goto.read(this.aa, this.D, 16383);
            if (read < 0) {
                this.u = -1;
            }
            else {
                this.u = this.D + read;
                this.h();
                this.ad = false;
            }
            return;
        }
        final int read2 = this.o.read(this.else, 0, 16384);
        switch (this.au) {
            case 1: {
                this.if(read2);
                break;
            }
            case 2: {
                this.do(read2);
                break;
            }
            case 3: {
                this.a(read2, 8, 0);
                break;
            }
            case 4: {
                this.a(read2, 0, 8);
                break;
            }
            case 5: {
                this.a(read2, 24, 16, 8, 0);
                break;
            }
            case 6: {
                this.a(read2, 0, 8, 16, 24);
                break;
            }
            case 7: {
                this.a(read2, 16, 24, 0, 8);
                break;
            }
            case 8: {
                this.a(read2, 8, 0, 24, 16);
                break;
            }
        }
        if (this.ad) {
            this.h();
            this.ad = false;
        }
        this.D = 0;
        this.ab += read2;
    }
    
    void h() {
        this.C = -1;
        int u = 0;
    Label_0126:
        for (int i = 0; i < this.u; ++i) {
            switch (this.aa[i]) {
                case '\r': {
                    if (i == this.u - 1) {
                        this.C = 13;
                        --this.u;
                        break Label_0126;
                    }
                    if (this.aa[i + 1] == '\n') {
                        ++i;
                    }
                    this.aa[u] = '\n';
                    break;
                }
                default: {
                    this.aa[u] = this.aa[i];
                    break;
                }
            }
            ++u;
        }
        this.u = u;
    }
    
    void if(final int n) throws Exception {
        int i = 0;
        int d = this.D;
        while (i < n) {
            final byte b = this.else[i++];
            if ((b & 0x80) == 0x0) {
                this.aa[d++] = (char)b;
            }
            else if ((b & 0xE0) == 0xC0) {
                this.aa[d++] = (char)((b & 0x1F) << 6 | this.a(i++, n));
            }
            else if ((b & 0xF0) == 0xE0) {
                this.aa[d++] = (char)((b & 0xF) << 12 | this.a(i++, n) << 6 | this.a(i++, n));
            }
            else if ((b & 0xF8) == 0xF0) {
                final int a = this.a(i++, n);
                final int a2 = this.a(i++, n);
                final int a3 = this.a(i++, n);
                this.aa[d++] = (char)(0xD800 | ((b & 0x7) << 2 | ((a & 0x30) >> 4) - 1) << 6 | (a & 0xF) << 2 | (a2 & 0x30) >> 4);
                this.aa[d++] = (char)(0xDC | (a2 & 0xF) << 6 | a3);
            }
            else {
                this.a("bad start for UTF-8 multi-byte sequence", b, i);
            }
            if (this.aa[d - 1] == '\r') {
                this.ad = true;
            }
        }
        this.u = d;
    }
    
    int a(final int n, final int n2) throws Exception {
        int read;
        if (n < n2) {
            read = this.else[n];
        }
        else {
            read = this.o.read();
            if (read == -1) {
                this.a("unfinished multi-byte UTF-8 sequence at EOF", -1, n);
            }
        }
        if ((read & 0xC0) != 0x80) {
            this.a("bad continuation of multi-byte UTF-8 sequence", read, n + 1);
        }
        return read & 0x3F;
    }
    
    void do(final int n) {
        int i;
        int d;
        for (i = 0, d = this.D; i < n; ++i, ++d) {
            this.aa[d] = (char)(this.else[i] & 0xFF);
            if (this.aa[d] == '\r') {
                this.ad = true;
            }
        }
        this.u = d;
    }
    
    void a(final int n, final int n2, final int n3) throws Exception {
        int d = this.D;
        if (n > 0 && n % 2 != 0) {
            this.a("odd number of bytes in UCS-2 encoding", -1, n);
        }
        for (int i = 0; i < n; i += 2) {
            this.aa[d++] = (char)((this.else[i] & 0xFF) << n2 | (this.else[i + 1] & 0xFF) << n3);
            if (this.aa[d - 1] == '\r') {
                this.ad = true;
            }
        }
        this.u = d;
    }
    
    void a(final int n, final int n2, final int n3, final int n4, final int n5) throws Exception {
        int d = this.D;
        if (n > 0 && n % 4 != 0) {
            this.a("number of bytes in UCS-4 encoding not divisible by 4", -1, n);
        }
        for (int i = 0; i < n; i += 4) {
            final int n6 = (this.else[i] & 0xFF) << n2 | (this.else[i + 1] & 0xFF) << n3 | (this.else[i + 2] & 0xFF) << n4 | (this.else[i + 3] & 0xFF) << n5;
            if (n6 < 65535) {
                this.aa[d++] = (char)n6;
                if (n6 == 13) {
                    this.ad = true;
                }
            }
            else if (n6 < 1048575) {
                this.aa[d++] = (char)(0xD8 | (n6 & 0xFFC00) >> 10);
                this.aa[d++] = (char)(0xDC | (n6 & 0x3FF));
            }
            else {
                this.a("value cannot be represented in UTF-16", n6, i);
            }
        }
        this.u = d;
    }
    
    void a(String string, final int n, final int n2) throws Exception {
        if (n >= 0) {
            string = string + " (byte value: 0x" + Integer.toHexString(n) + ')';
        }
        String s;
        if (this.I != null) {
            s = this.I.getURL().toString();
        }
        else {
            s = this.l;
        }
        this.if.a(string, s, -1, n2 + this.ab);
    }
    
    void r() {
        this.b = 0;
        this.char = 1;
        this.t = 0;
        this.V = 0;
        this.al = new char[com.microstar.xml.a.w];
        this.void = 0;
        this.e = new char[com.microstar.xml.a.k];
        this.Z = new Hashtable();
        this.ao = new Hashtable();
        this.h = new Hashtable();
        this.aq = null;
        this.S = 0;
        this.c = 0;
        this.m = new Stack();
        this.n = new Stack();
        this.I = null;
        this.long = 0;
        this.Y = new String[100];
        this.else = new byte[16384];
        this.C = -1;
        this.U = 0;
        this.aj = new Object[1087];
    }
    
    void g() {
        this.b = -1;
        this.char = -1;
        this.t = -1;
        this.al = null;
        this.e = null;
        this.aq = null;
        this.S = 0;
        this.c = 0;
        this.m = null;
        this.I = null;
        this.n = null;
    }
    
    static {
        (com.microstar.xml.a.do = new Hashtable()).put("CDATA", new Integer(1));
        com.microstar.xml.a.do.put("ID", new Integer(2));
        com.microstar.xml.a.do.put("IDREF", new Integer(3));
        com.microstar.xml.a.do.put("IDREFS", new Integer(4));
        com.microstar.xml.a.do.put("ENTITY", new Integer(5));
        com.microstar.xml.a.do.put("ENTITIES", new Integer(6));
        com.microstar.xml.a.do.put("NMTOKEN", new Integer(7));
        com.microstar.xml.a.do.put("NMTOKENS", new Integer(8));
        com.microstar.xml.a.do.put("NOTATION", new Integer(10));
        com.microstar.xml.a.w = 4096;
        com.microstar.xml.a.k = 1024;
    }
}
