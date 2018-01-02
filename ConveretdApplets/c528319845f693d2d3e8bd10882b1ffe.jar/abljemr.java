import java.io.FileOutputStream;
import java.io.IOException;
import ABLwidgets.utils;
import java.io.DataInputStream;
import java.awt.Component;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class abljemr extends Thread
{
    public Thread a;
    public abljemrr b;
    public Boolean c;
    public Boolean d;
    private abljema e;
    private int f;
    private String g;
    private Vector h;
    private Vector i;
    private boolean j;
    private boolean k;
    private Boolean l;
    private Boolean m;
    private boolean n;
    
    abljemr(final abljema e) {
        super(String.valueOf(e.l) + "abljemr");
        this.f = 1;
        this.h = new Vector();
        this.i = new Vector();
        this.j = true;
        this.k = false;
        this.e = e;
    }
    
    public void run() {
        if (Thread.currentThread() == this.a) {
            this.d();
        }
        else if (this.e.d1 == 0) {
            this.e();
        }
        else if (this.e.d0) {
            this.d();
        }
        else {
            (this.a = new Thread(this)).start();
            this.e();
        }
    }
    
    private void d() {
        int n = 1;
        while (this.e.az) {
            Label_0070: {
                synchronized (this.h) {
                    // monitorexit(this.h)
                    while (true) {
                        Label_0026: {
                            if (n == 0) {
                                break Label_0026;
                            }
                            Block_6: {
                                if (this.h.size() <= 0) {
                                    break Block_6;
                                }
                                byte[] array;
                                while ((array = (byte[])abljema.a(this.h)) != null) {
                                    this.b(array, array.length);
                                }
                                break Label_0070;
                            }
                            try {
                                this.h.wait();
                            }
                            catch (InterruptedException ex) {}
                        }
                        continue;
                    }
                }
            }
            if (this.e.az) {
                this.e.a(false);
            }
            n = 0;
        }
    }
    
    private void e() {
        while (this.e.az) {
            try {
                byte[] array;
                int n;
                try {
                    array = this.h();
                    if (array == null) {
                        this.e.az = false;
                        break;
                    }
                    n = array.length;
                }
                catch (Throwable t) {
                    t.printStackTrace();
                    this.e.a2 = "Failed reading input";
                    this.e.az = false;
                    break;
                }
                this.e.bd = true;
                if (n <= 0) {
                    continue;
                }
                if (n <= 0) {
                    continue;
                }
                if (array[0] == 74 && array[1] == 69 && array[2] == 69) {
                    final byte b = array[3];
                    this.e.gd = array[4] + 256 * array[5];
                    this.e.gf = array[6];
                    this.e.gh[0] = array[7];
                    this.e.gh[1] = array[8];
                    this.e.gh[2] = array[9];
                    this.e.gh[3] = array[10];
                    this.e.gh[4] = array[11];
                    this.e.gh[5] = array[12];
                    this.e.gh[6] = array[13];
                    this.e.ge = array[271] + 256 * array[272];
                    this.e.gg = array[273];
                    this.e.gi[0] = array[274];
                    this.e.gi[1] = array[275];
                    this.e.gi[2] = array[276];
                    this.e.gi[3] = array[277];
                    this.e.gi[4] = array[278];
                    this.e.gi[5] = array[279];
                    this.e.gi[6] = array[280];
                    this.e.h(array, 100, b);
                    this.e.i(array, 100, b);
                    this.e.gb = true;
                }
                else if (array[0] == 74 && array[1] == 82 && array[2] == 49) {
                    this.e.dh = array[3];
                    for (int i = 0; i < 5; ++i) {
                        final byte[] array2 = array;
                        final int n2 = 4 + i;
                        array2[n2] += (byte)(i + 131);
                    }
                    for (int j = 0; j < 5; ++j) {
                        final byte[] array3 = array;
                        final int n3 = 9 + j;
                        array3[n3] += (byte)(j + 137);
                    }
                    this.e.j(array, 4, 5);
                    this.e.k(array, 4, 5);
                    this.e.df = true;
                    this.e.dg = true;
                    if (n > 14) {
                        switch (array[14]) {
                            case 88: {
                                this.e.gu = true;
                                this.e.gt = true;
                                break;
                            }
                            case 80: {
                                this.e.gu = true;
                                break;
                            }
                        }
                    }
                    if (n > 15) {
                        int n4 = array[15];
                        switch (n4) {
                            case 0: {
                                this.e.u = true;
                                break;
                            }
                            default: {
                                n4 = 0;
                                break;
                            }
                        }
                        if (n4 != 0) {
                            abljem.d("Server type " + (char)n4);
                        }
                    }
                    if (n <= 16 || array[16] != 79) {
                        continue;
                    }
                    this.e.w = true;
                }
                else {
                    if (this.e.dg) {
                        this.e.m(array, 0, n);
                    }
                    if (this.e.gb) {
                        this.e.i(array, 0, n);
                    }
                    if (n >= 3 && abljema.a(array, 0, n, "X02")) {
                        final byte[] g = abljema.g(array, 3, n - 3);
                        if (g.length > array.length) {
                            array = g;
                        }
                        else {
                            System.arraycopy(g, 0, array, 0, g.length);
                        }
                        n = g.length;
                    }
                    if (array[0] == 82 && array[1] == 79 && array[2] == 75 && array[3] == 89) {
                        this.e.j(this.e.gy, 0, this.e.gz);
                        this.e.dg = true;
                        if (!this.e.df) {
                            continue;
                        }
                        this.e.gs = true;
                        if (this.e.go) {
                            this.e.gr = true;
                        }
                        abljem.d("Link encryption is RC4 with a " + (this.e.gr ? "secure " : "") + this.e.gz * 8 + "-bit key");
                        if (this.e.g8 != null && this.e.gr) {
                            this.e.g8.a();
                        }
                        if (!this.e.c8) {
                            continue;
                        }
                        this.e.c9 = true;
                    }
                    else if (array[0] == 80 && array[1] == 79 && array[2] == 75) {
                        if (array[3] == 89) {
                            this.e.a0 = true;
                            this.b(array, 4, n);
                            this.e.v();
                            this.e.c2 = this.e.c5;
                            this.e.fb.x();
                        }
                        else {
                            abljem.d("jem error 15");
                        }
                    }
                    else if (array[0] == 78 && array[1] == 69 && array[2] == 68) {
                        if (abljema.a(array, 3, n, "CAP")) {
                            if (n > 6 && array[6] == 49) {
                                this.e.v = true;
                            }
                            if (!this.e.v) {
                                continue;
                            }
                            abljem.d("Data stream will be compressed");
                        }
                        else if (abljema.a(array, 3, n, "CSG")) {
                            final int n5 = n - 6;
                            this.d(this.f = abljema.d(new String(array, 0, 6, (n5 > 10) ? 10 : n5).trim()));
                        }
                        else if (abljema.a(array, 3, n, "CSD")) {
                            this.a(array, 6, n);
                        }
                        else if (abljema.a(array, 3, n, "RCIOKN")) {
                            this.e.a2 = new String(array, 0, 9, n - 9);
                            this.e.az = false;
                        }
                        else if (abljema.a(array, 3, n, "OON")) {
                            this.e.hd = true;
                        }
                        else if (abljema.a(array, 3, n, "NAMOKN")) {
                            final String s = new String(array, 0, 9, n - 9);
                            if (s.equals("Emulation not configured for you")) {
                                this.e.j.h2 = false;
                                this.e.a1 = true;
                                this.e.fb.l = true;
                                this.n = true;
                                this.e.fb.setCursor(0);
                                this.j = false;
                                this.a(null, array, n);
                                if (!this.e.g9.c) {
                                    continue;
                                }
                                this.e.g9.a();
                            }
                            else if (this.e.c8) {
                                this.e.a2 = "Server error: ".concat(s);
                                this.e.az = false;
                            }
                            else {
                                this.e.g8.b(s);
                            }
                        }
                        else if (abljema.a(array, 3, n, "LSD")) {
                            boolean b2 = false;
                            if (this.e.g9 != null && this.e.g9.isVisible()) {
                                b2 = true;
                            }
                            this.e.r();
                            this.e.g9.a(array, 6, n);
                            if (!this.e.hq && (b2 || this.e.hr || this.e.g9.c)) {
                                this.e.g9.a();
                            }
                            this.e.hr = false;
                            this.e.hq = false;
                            this.e.g9.b();
                        }
                        else {
                            if (abljema.a(array, 3, n, "LZR") || abljema.a(array, 3, n, "LZW") || abljema.a(array, 3, n, "LSU")) {
                                continue;
                            }
                            if (abljema.a(array, 3, n, "OOP")) {
                                this.e.hc = new String(array, 0, 6, n - 6);
                                if (this.e.g9 != null) {
                                    this.e.g9.a.enable();
                                }
                                if (this.e.g9 == null) {
                                    continue;
                                }
                                this.e.g9.b();
                            }
                            else if (abljema.a(array, 3, n, "CLR")) {
                                if (this.c(this.f)) {
                                    continue;
                                }
                                this.e.fb.f();
                            }
                            else if (abljema.a(array, 3, n, "TBM")) {
                                long b3 = 0L;
                                int k;
                                for (k = 6; k < n; ++k) {
                                    final byte b4 = array[k];
                                    if (b4 < 48) {
                                        break;
                                    }
                                    if (b4 > 57) {
                                        break;
                                    }
                                    b3 = b3 * 10L + (b4 - 48);
                                }
                                if (k < n) {
                                    continue;
                                }
                                this.e.bm.b = b3;
                            }
                            else {
                                if (!abljema.a(array, 3, n, "UAV")) {
                                    continue;
                                }
                                if (!this.e.di) {
                                    this.e.di = true;
                                }
                                if (n <= 26) {
                                    continue;
                                }
                                final String s2 = new String(array, 0, 6, 20);
                                final String s3 = new String(array, 0, 26, n - 26);
                                if (this.e.fb == null) {
                                    continue;
                                }
                                this.e.fb.a(s2, s3);
                            }
                        }
                    }
                    else {
                        this.e.hq = false;
                        if (this.c(this.f)) {
                            continue;
                        }
                        if (array[0] == 88 && array[1] == 48 && array[2] == 49) {
                            array = this.a(array);
                            n = array.length;
                        }
                        for (int l = 0; l < n; ++l) {
                            if (array[l] == 0) {
                                array[l] = 32;
                            }
                        }
                        if (!this.e.az) {
                            return;
                        }
                        this.b(array, n);
                    }
                }
            }
            catch (Throwable t2) {
                if (!(t2 instanceof ThreadDeath)) {
                    t2.printStackTrace();
                }
                this.e.az = false;
            }
            finally {
                if (this.e.az) {
                    this.e.a(false);
                }
            }
        }
    }
    
    private boolean c(final int n) {
        if (n != this.e.ag) {
            abljem.d("Ignored host data for non-current session number " + n);
            return true;
        }
        if (n >= 0 && n < this.e.dy.length && this.e.dy[n] != null) {
            abljem.d("Ignored host data for playback session number " + n);
            if (this.j) {
                this.c();
            }
            return true;
        }
        return false;
    }
    
    private void a(final byte[] array, final int n, final int n2) {
        abljemf fb = null;
        if (this.e.p > 0) {
            fb = this.e.o[this.e.p].fb;
        }
        if (!abljema.a(array, n, n2, "ESM0002")) {
            this.e.fb.a(array, n, n2);
            if (fb != null) {
                fb.a(array, n, n2);
            }
        }
        if (this.c != null) {
            this.e.fb.b(this.c);
            if (fb != null) {
                fb.b(this.c);
            }
        }
        this.c = null;
        if (!this.e.fb.ea) {
            if (!this.e.fb.l) {
                this.e.fb.a("R", 'P');
            }
            if (fb != null) {
                fb.a("R", 'P');
            }
        }
    }
    
    private void d(final int ag) {
        this.c = null;
        if (ag != this.e.ag) {
            try {
                synchronized (this.e.fb.e0.e) {
                    this.e.fb.a(null, 'R');
                    this.e.fb.e0.a(this.e.ag, ag);
                }
                // monitorexit(this.e.fb.e0.e)
            }
            catch (Throwable t) {
                abljem.d("Base session typeahead switch failed");
                t.printStackTrace();
            }
            if (this.e.p > 0) {
                try {
                    final abljemf fb = this.e.o[this.e.p].fb;
                    synchronized (fb.e0.e) {
                        fb.a(null, 'R');
                        fb.e0.a(this.e.ag, ag);
                    }
                    // monitorexit(fb.e0.e)
                }
                catch (Throwable t2) {
                    abljem.d("GuiStyle session typeahead switch failed");
                    t2.printStackTrace();
                }
                if (!this.j) {
                    final abljemf b = this.e.b();
                    if (b != null) {
                        b.c();
                        if (!this.e.af) {
                            b.d("Switching sessions");
                        }
                    }
                }
                this.k = true;
            }
        }
        this.e.ag = ag;
        if (this.e.ag != 1) {
            this.e.ae = true;
        }
        if (this.e.ae) {
            this.g = String.valueOf(this.e.ai) + ag;
            this.e.a('A');
        }
    }
    
    public void a(final int n) {
        final String string = String.valueOf(this.e.ai) + "1";
        this.e.ae = true;
        this.e.fb.a(string);
        this.e.j.ah = string;
        for (int n2 = 1; n2 < this.e.j.aj.length && n2 <= n; ++n2) {
            this.e.j.aj[n2] = 'A';
        }
    }
    
    private void a(final byte[] array, final int n) {
        if (!this.e.m) {
            return;
        }
        if (this.b == null) {
            try {
                (this.b = new abljemrr(this.e, this.e.n)).start();
            }
            catch (Throwable t) {
                t.printStackTrace();
                this.e.az = false;
            }
        }
        this.b.a(array, n);
    }
    
    private void b(final byte[] array, final int n) {
        if (n > 3 && array[0] == 65 && array[1] == 48 && array[2] == 49) {
            if (this.e.d2 != null) {
                this.a(array, n, this.e.d7, this.e.d8);
            }
            this.c(array, n);
        }
        else {
            if (this.e.d2 != null) {
                this.i.removeAllElements();
                this.a(array, n, this.e.d5, this.e.d6);
            }
            this.e(array, n);
        }
    }
    
    private void b(final String s) {
        if (s.startsWith("Data stream recording") || s.startsWith("Data stream dumped")) {
            this.e.fb.b(false);
            this.e.fb.a(null, 'P');
            try {
                this.e.o[this.e.p].fb.b(false);
                this.e.o[this.e.p].fb.a(null, 'P');
            }
            catch (Throwable t) {}
        }
    }
    
    private void c(final byte[] array, final int n) {
        this.d = null;
        this.a(array, n);
        if (array[3] == 49 && array[4] == 50 && array[5] == 77) {
            abljema.a(array, 5, this.e);
        }
        else if (abljema.a(array, 3, n, "3081L") || abljema.a(array, 3, n, "3133L")) {
            this.e.fb.a(array, 8, n);
            if (!this.j) {
                this.f(array, n);
            }
            try {
                if (this.e.p > 0) {
                    this.e.o[this.e.p].fb.a(array, 8, n);
                }
            }
            catch (Throwable t) {}
        }
        else if (array[3] == 52 && array[8] == 85) {
            final String trim = new String(array, 0, 18, n - 18).trim();
            this.b(trim);
            this.e.fb.i(trim);
            try {
                if (this.j) {
                    this.e.g8.a(trim);
                }
                else {
                    this.f(array, n);
                }
                if (this.e.p > 0) {
                    this.e.o[this.e.p].fb.i(trim);
                }
            }
            catch (Throwable t2) {}
        }
        else {
            final StringBuffer sb = new StringBuffer();
            int a;
            for (int n2 = 3, n3 = 1000; n2 < n && --n3 > 0; n2 = a) {
                a = this.a(array, n2, n, sb);
                if (sb.length() >= 1) {
                    switch (sb.charAt(0)) {
                        case 'V': {
                            final String trim2 = sb.toString().substring(1).trim();
                            this.b(trim2);
                            this.e.fb.i(trim2);
                            if (this.j) {
                                this.e.g8.a(trim2);
                            }
                            else {
                                this.f(array, n);
                            }
                            if (this.e.p > 0) {
                                this.e.o[this.e.p].fb.i(trim2);
                                break;
                            }
                            break;
                        }
                        case 'I': {
                            final byte[] a2 = abljema.a(sb);
                            this.e.b(a2, 0, a2.length);
                            break;
                        }
                    }
                }
            }
        }
        if (this.d != null) {
            this.e.fb.b(this.d);
        }
        if (!this.e.fb.ea && !this.e.fb.l) {
            this.e.fb.a("R", 'P');
        }
    }
    
    private Integer f() {
        final Integer n = null;
        if (!this.k) {
            return n;
        }
        this.e.ap = true;
        Integer n2 = new Integer(0);
        try {
            final int ag = this.e.ag;
            final int p = this.e.p;
            for (int n3 = this.e.ao[ag], i = 1; i < n3; ++i) {
                final byte[] array = this.e.am[ag][i];
                final abljema abljema = this.e.o[i];
                final abljemf abljemf = (abljema == null) ? null : abljema.fb;
                if (array == null && abljemf != null) {
                    abljemf.d("Screen detail not retained on session switch");
                    abljemf.y();
                    abljemf.bl = true;
                }
                if (array != null) {
                    this.d(array, array.length);
                    this.e.b(this.n);
                    try {
                        if (this.e.p > 0) {
                            this.e.o[this.e.p].fb.y();
                        }
                    }
                    catch (Throwable t2) {}
                    n2 = new Integer(i);
                    final byte[] array2 = this.e.an[ag][i];
                    if (array2 != null) {
                        this.c(array2, array2.length);
                    }
                }
            }
        }
        catch (Throwable t) {
            abljem.d("Session switch screen stack restore failed");
            t.printStackTrace();
        }
        this.e.ap = false;
        this.k = false;
        return n2;
    }
    
    private void d(final byte[] array, int length) {
        Boolean b = null;
        this.n = false;
        this.l = null;
        this.m = null;
        this.d = null;
        if (!abljema.a(array, 44, length, "Sign On")) {
            this.a(array, length);
        }
        this.e.a(true);
        this.e.fb.f();
        this.e.ed = (this.e.fb.bn == '1');
        if (this.g != null) {
            this.e.fb.a(this.g);
            this.e.j.ah = this.g;
            this.g = null;
        }
        if (array[0] != 74 || array[1] != 48 || array[2] != 49) {
            for (int i = 0; i < this.e.el.length; ++i) {
                this.e.el[i] = 32;
            }
            if (length < 300) {
                System.arraycopy(array, 0, this.e.el, 0, length);
            }
            else {
                final String string = "Invalid data version " + new String(array, 0, 0, this.e.dt);
                length = string.length();
                string.getBytes(0, length, this.e.el, 0);
            }
            if (abljema.a(this.e.el, 0, length, "Signed off")) {
                this.e.a2 = null;
            }
            else {
                final String s = new String(array, 0, 0, (length > 100) ? 100 : length);
                String s2 = "Server error: ";
                if (s.startsWith("Automatic disconnect") || s.startsWith("Session timed out") || s.startsWith("Session disconnected")) {
                    s2 = "";
                }
                this.e.a2 = String.valueOf(s2) + s;
            }
            this.e.az = false;
        }
        else {
            this.e.bu = this.e.bt;
            if (this.e.fb.cb && this.e.bv != this.e.bu) {
                this.e.fb.ac();
            }
            if (!this.e.al[this.e.ag] && !this.e.w) {
                final String string2 = "*A9" + this.e.f();
                if (this.e.bm != null) {
                    this.e.bm.a(string2);
                }
                this.e.al[this.e.ag] = true;
            }
            final String s3 = new String(array, 0, 0, this.e.du);
            if (s3.equals("J0141921S")) {
                if (this.e.es != 80) {
                    this.e.et = 24;
                    this.e.es = 80;
                    this.e.eu = 1920;
                    this.e.fb.q();
                    if (this.e.ax) {
                        this.e.fb.z();
                    }
                }
            }
            else {
                if (!s3.equals("J0143565S")) {
                    abljem.d("jem error 17 " + s3);
                    return;
                }
                if (this.e.es != 132) {
                    this.e.bq = true;
                    this.e.et = 27;
                    this.e.es = 132;
                    this.e.eu = 3564;
                    this.e.fb.q();
                    if (this.e.ax) {
                        this.e.fb.z();
                    }
                }
            }
            System.arraycopy(array, this.e.du, this.e.el, 0, this.e.eu);
            try {
                this.e.fb.ad();
            }
            catch (Throwable t) {}
            this.e.e5 = 1;
            this.e.e6 = 1;
            b = Boolean.FALSE;
            this.l = Boolean.FALSE;
            this.m = Boolean.FALSE;
            this.e.h1.a(this.e.es);
            int j = this.e.eu + this.e.du;
            int n = 3000;
            while (j < length) {
                if (n-- < 0) {
                    abljem.d("joc error 1");
                    break;
                }
                final byte b2 = (byte)(array[j++] - 48);
                if (b2 < 0 || b2 > 9999) {
                    abljem.d("joc error 2");
                    break;
                }
                final int c = abljema.c(array, j, b2);
                final byte b3 = (byte)(j + b2);
                if (c > 0 && array[b3] == 42) {
                    b = Boolean.TRUE;
                    if (c > 1 && array[b3 + 1] == 69) {
                        this.l = Boolean.TRUE;
                        try {
                            this.e.fb.e0.a();
                            this.e.j.q.a();
                        }
                        catch (Throwable t2) {}
                        if (abljema.a(this.e.el, 1, this.e.eu, "Signed off")) {
                            this.m = Boolean.TRUE;
                        }
                        if (!this.e.ae) {
                            if (this.m != null && this.m) {
                                this.e.a2 = null;
                            }
                            else {
                                final String trim = new String(this.e.el, 0, 0, 80).trim();
                                String s4 = "";
                                if (trim.startsWith("Connection failed")) {
                                    s4 = "Server error: ";
                                }
                                this.e.a2 = String.valueOf(s4) + trim;
                            }
                            this.e.az = false;
                            break;
                        }
                        this.e.b();
                        final int n2 = ((this.e.eu == 3564) ? 132 : 80) + 1;
                        final String s5 = "To restart this session, switch to another session and back again.";
                        final byte[] array2 = new byte[s5.length()];
                        s5.getBytes(0, array2.length, array2, 0);
                        System.arraycopy(array2, 0, this.e.el, n2, array2.length);
                    }
                    if (this.j) {
                        this.e.g8.a(new String(this.e.el, 0, 1, this.e.es - 1).trim());
                    }
                }
                else {
                    final int a = abljemf.a(array, b3, this.e.du, this.e, c);
                    if (a <= 0) {
                        abljem.d("joc error 3");
                    }
                    if (a != c) {
                        abljem.d("joc error 4 " + c + ", " + a);
                        break;
                    }
                    if (array[b3] == 73 && this.e.fb.cb && this.e.bv != this.e.bu) {
                        this.e.fb.ac();
                    }
                }
                j = b3 + c;
            }
        }
        this.e.k();
        if (this.e.az) {
            this.e.m();
            this.e.l();
            if (this.e.e1 == 1 && this.e.e4 != null && this.e.e4.ae == 1919) {
                final int n3 = (this.e.e5 - 1) * this.e.es + (this.e.e6 - 1) - 3;
                if ((this.e.e6 < 3 && this.e.e5 != 1) || (this.e.e6 > 3 && abljema.a(this.e.el, n3, this.e.eu, "=> "))) {
                    this.e.e4.a(this.e.e5, this.e.e6);
                }
            }
            if (this.e.fw == 0) {
                this.e.q();
            }
            this.e.i();
            this.e.fb.ep = true;
        }
        if (this.e.az) {
            if (!this.e.bu || !this.e.he) {
                this.e.fb.add(new abljemfb(5, this.e.et + 1, this.e.es - 20, "Macro", this.e));
                this.e.fb.add(new abljemfb(7, this.e.et + 1, this.e.es - 6, "ENT", this.e));
                this.e.fb.add(new abljemfb(3, this.e.et + 1, this.e.es - 14, "DWN", this.e));
                this.e.fb.add(new abljemfb(3, this.e.et + 1, this.e.es - 10, "UPP", this.e));
            }
            this.n = true;
            if (this.d == null) {
                this.d = Boolean.FALSE;
            }
        }
        if (this.e.az && this.e.fb != null) {
            if (b != null) {
                this.e.fb.l = b;
            }
            if (this.j && !this.e.fb.l) {
                this.j = false;
            }
        }
    }
    
    private boolean g() {
        try {
            final Integer f = this.f();
            final int n = f + 1;
            final byte[] array = this.e.am[this.e.ag][n];
            final byte[] array2 = this.e.an[this.e.ag][n];
            if (array == null) {
                return false;
            }
            this.d(array, array.length);
            this.a(f, array, array.length);
            if (array2 != null) {
                this.c(array2, array2.length);
            }
        }
        catch (Throwable t) {
            abljem.d("Stack switch failed");
            t.printStackTrace();
            return false;
        }
        return true;
    }
    
    private void e(final byte[] array, final int n) {
        final Integer f = this.f();
        this.d(array, n);
        this.a(f, array, n);
    }
    
    private void a(final Integer n, final byte[] array, final int n2) {
        if (!this.e.az || this.e.fb == null) {
            return;
        }
        if (this.l != null) {
            if (this.l) {
                this.e.a('E', this.m != null && this.m);
            }
            else if (this.e.aj[this.e.ag] == 'E') {
                this.e.a('A');
            }
        }
        if (this.d != null) {
            this.e.fb.b(this.d);
        }
        if (!this.e.ae || this.m == null || !this.m) {
            if (!this.j) {
                if (!this.e.b(this.n)) {
                    this.e.fb.show();
                }
                if (n != null) {
                    for (int i = 1; i < this.e.p; ++i) {
                        try {
                            final abljemf fb = this.e.o[i].fb;
                            if (fb.l) {
                                fb.d("Screen detail not available after session switch");
                                fb.bl = true;
                            }
                            else if (i > n) {
                                this.e.am[this.e.ag][i] = null;
                                this.e.an[this.e.ag][i] = null;
                            }
                            fb.c(this.e.o[this.e.p].fb.bk);
                        }
                        catch (Throwable t) {}
                    }
                }
            }
            this.g(array, n2);
            if (!this.e.fb.ea && !this.e.fb.l) {
                this.e.fb.a("R", 'I');
            }
            return;
        }
        final int n3 = this.e.n();
        if (n3 < 0) {
            this.e.a2 = null;
            this.e.az = false;
            return;
        }
        this.e.fb.a(n3, false);
    }
    
    private boolean a(final DataInputStream dataInputStream, final byte[] array, final int n, final int n2) {
        try {
            int read;
            for (int n3 = n, i = n2; i > 0; i -= read, n3 += read) {
                read = dataInputStream.read(array, n3, i);
                if (read < 0) {
                    throw new RuntimeException("Input stream ended");
                }
            }
        }
        catch (Throwable t) {
            if (!this.e.az) {
                return false;
            }
            throw new RuntimeException("Input stream failed: " + t);
        }
        return true;
    }
    
    private byte[] h() {
        if (!this.e.ie) {
            final byte[] array = new byte[4];
            final byte[] array2 = new byte[4];
            byte[] array7;
            try {
                if (!this.a(this.e.as, array, 0, 4)) {
                    return null;
                }
                if (!abljema.a(array, 0, this.e.dm, 0, 4) && !abljema.a(array, 0, this.e.do, 0, 4)) {
                    final byte[] array3 = new byte[16];
                    this.e.dj.getBytes(0, 16, array3, 0);
                    if (this.e.bd || !abljema.a(array, 0, array3, 0, 4)) {
                        abljem.d("jem error 11 " + array[0] + "," + array[1] + "," + array[2] + "," + array[3] + " " + utils.a());
                        if (this.e.hp) {
                            final byte[] array4 = new byte[200];
                            final int read = this.e.as.read(array4, 0, 200);
                            this.e.a2 = "Server response invalid";
                            if (read > 10) {
                                this.e.a2 = "Server: ".concat(new String(array, 0, 0, 4)).concat(new String(array4, 0, 0, read));
                            }
                        }
                        return null;
                    }
                    final byte[] array5 = new byte[16];
                    System.arraycopy(array, 0, array5, 0, 4);
                    if (!this.a(this.e.as, array5, 4, 12)) {
                        return null;
                    }
                    if (abljema.a(array5, 0, array3, 0, 10) && array5[10] == array3[10] && array5[12] == array3[12] && array5[14] == array3[14]) {
                        final byte[] array6 = new byte[16];
                        this.e.dk.getBytes(0, 16, array6, 0);
                        this.e.at.write(array6, 0, 16);
                        this.e.at.flush();
                        return new byte[0];
                    }
                    for (int i = 0; i < 16; ++i) {
                        abljem.c(String.valueOf((i == 0) ? "jem error 18 " : "") + array5[i] + ((i == 15) ? "\n" : ","));
                    }
                    this.e.a2 = "Invalid protocol version";
                    return null;
                }
                else {
                    this.e.hp = false;
                    this.e.a2 = "Failed - see Java Console for detail";
                    final int int1 = this.e.as.readInt();
                    if (int1 < 0 || int1 > 1999999) {
                        abljem.d("jem error 12 " + int1);
                        return null;
                    }
                    array7 = new byte[int1];
                    if (int1 > 0 && !this.a(this.e.as, array7, 0, int1)) {
                        return null;
                    }
                    if (!this.a(this.e.as, array2, 0, 4)) {
                        return null;
                    }
                    if (!abljema.a(array2, 0, this.e.dn, 0, 4) && !abljema.a(array2, 0, this.e.dp, 0, 4)) {
                        abljem.d("jem error 13 " + array2[0] + array2[1] + array2[2] + array2[3]);
                        return null;
                    }
                }
            }
            catch (IOException ex) {
                if (this.e.az) {
                    abljem.d("IOException 10");
                }
                return null;
            }
            return array7;
        }
        final byte[] w = this.e.w();
        if (w == null || w.length < 12) {
            return null;
        }
        int n = w.length - 12;
        if (n < 0) {
            n = 0;
        }
        if (n == 0) {
            return new byte[0];
        }
        final byte[] array8 = new byte[n];
        System.arraycopy(w, 8, array8, 0, n);
        return array8;
    }
    
    private byte[] a(final byte[] array) {
        final int n = 7;
        final int n2 = array.length - n;
        final int c = abljema.c(array, 3, 4);
        final byte[] array2 = new byte[n2];
        System.arraycopy(array, n, array2, 0, n2);
        final byte[] array3 = new byte[c];
        int i = 0;
        int n3 = 0;
        while (i < n2) {
            final byte b = array2[i];
            int j = b & 0xFF;
            if (j < 128) {
                array3[n3++] = b;
            }
            else if (j == 128) {
                byte b2 = array2[i + 1];
                ++i;
                if (b2 == -77) {
                    b2 = 124;
                }
                if (b2 == -101) {
                    b2 = -94;
                }
                if (b2 == -86) {
                    b2 = -84;
                }
                array3[n3++] = b2;
            }
            else {
                if (j == 129) {
                    break;
                }
                if (j < 192) {
                    for (j -= 128; j > 0; --j) {
                        array3[n3++] = 32;
                    }
                }
                else if (j == 192) {
                    array3[n3++] = 50;
                    array3[n3++] = 50;
                    array3[n3++] = 48;
                    array3[n3++] = 84;
                }
                else if (j == 193) {
                    array3[n3++] = 50;
                    array3[n3++] = 49;
                    array3[n3++] = 50;
                    array3[n3++] = 65;
                }
                else {
                    if (j == 194) {
                        break;
                    }
                    if (j < 208) {
                        j -= 192;
                        final int n4 = array2[i + 1] & 0xFF;
                        while (j > 0) {
                            array3[n3] = array3[n3 - n4];
                            --j;
                            ++n3;
                        }
                        ++i;
                    }
                    else if (j >= 208 && j <= 217) {
                        array3[n3++] = 48;
                        array3[n3++] = (byte)(48 + j - 208);
                    }
                    else if (j >= 224 && j <= 233) {
                        array3[n3++] = 48;
                        array3[n3++] = 48;
                        array3[n3++] = (byte)(48 + j - 224);
                    }
                    else {
                        if (j < 240 || j > 249) {
                            break;
                        }
                        array3[n3++] = 48;
                        array3[n3++] = 48;
                        array3[n3++] = 48;
                        array3[n3++] = (byte)(48 + j - 240);
                    }
                }
            }
            ++i;
        }
        if (n3 != c) {
            abljem.d("joc error 5, values " + n3 + "," + c);
            this.e.az = false;
        }
        return array3;
    }
    
    private int a(final byte[] array, final int n, final int n2, final StringBuffer sb) {
        sb.setLength(0);
        if (n >= n2) {
            return n2;
        }
        final char c = (char)array[n];
        if (c < '0' || c > '9') {
            return n2;
        }
        final int n3 = n + 1;
        final char c2 = (char)(c - '0');
        if (c2 < '\u0001') {
            return n3;
        }
        final int c3 = abljema.c(array, n3, c2);
        int n4 = n3 + c2;
        if (c3 < 1) {
            return n4;
        }
        for (int i = 0; i < c3; ++i, ++n4) {
            sb.append((char)array[n4]);
        }
        return n4;
    }
    
    private void b(final byte[] array, final int n, final int n2) {
        if (n < n2 && !this.e.bs) {
            switch (array[n]) {
                case 71: {
                    this.e.bt = false;
                    break;
                }
                case 84: {
                    this.e.bt = true;
                    break;
                }
            }
        }
    }
    
    private void a(final byte[] array, final int n, final String s, final String s2) {
        final int length = s.length();
        final int length2 = s2.length();
        final int n2 = length + n;
        final byte[] array2 = new byte[n2 + length2];
        s.getBytes(0, length, array2, 0);
        System.arraycopy(array, 0, array2, length, n);
        s2.getBytes(0, length2, array2, n2);
        this.i.addElement(array2);
    }
    
    private void f(final byte[] array, final int n) {
        this.a(array, n, this.e.an);
    }
    
    private void g(final byte[] array, final int n) {
        try {
            for (int i = this.e.p; i < 10; ++i) {
                this.e.am[this.e.ag][i] = null;
                this.e.an[this.e.ag][i] = null;
            }
            this.a(array, n, this.e.am);
            this.e.ao[this.e.ag] = this.e.p;
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    private void a(final byte[] array, final int n, final byte[][][] array2) {
        try {
            final byte[] array3 = new byte[n];
            System.arraycopy(array, 0, array3, 0, n);
            array2[this.e.ag][this.e.p] = array3;
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public String a() {
        final FileOutputStream d2 = this.e.d2;
        if (d2 == null) {
            return "ERROR: Recording not enabled";
        }
        try {
            d2.write(this.e.d3.getBytes());
            byte[] array;
            while ((array = (byte[])abljema.a(this.i)) != null) {
                d2.write(array);
            }
            d2.write(this.e.d4.getBytes());
        }
        catch (Throwable t) {
            t.printStackTrace();
            return "ERROR: Recording to file failed - see Java Console";
        }
        return "Screen recorded";
    }
    
    public void a(final String s) {
        int n = this.e.dz[this.e.ag];
        if (s.equals("ENT")) {
            n = this.e(n + 1);
        }
        else if (s.equals("UPP")) {
            n = this.e(n + 1);
        }
        else if (s.equals("DWN")) {
            n = this.f(n);
        }
        else if (s.equals("F01")) {
            n = 0;
        }
        else if (s.equals("F03")) {
            if (this.e.d0) {
                this.e.fb.r();
                return;
            }
            n = this.e(n + 1);
        }
        else if (!s.equals("F05")) {
            n = this.e(n + 1);
            abljem.d(String.valueOf(s) + " treated as Enter for playback purposes");
        }
        this.e.dz[this.e.ag] = n;
        final String b = this.b();
        if (b != null) {
            this.e.fb.i("ERROR: " + b);
        }
    }
    
    public String b() {
        final byte[] array = this.e.dy[this.e.ag];
        final int length = array.length;
        final int n = this.e.dz[this.e.ag];
        int n2 = 0;
        synchronized (this.h) {
            this.h.removeAllElements();
            final int n3 = n + utils.c(array, n);
            if (!abljema.a(array, n3, length, this.e.d3)) {
                // monitorexit(this.h)
                return "Screen begin tag not in place";
            }
            final int n4 = n3 + this.e.d3.length();
            int n5 = n4 + utils.c(array, n4);
            if (abljema.a(array, n5, length, this.e.d5)) {
                final int n6 = n5 + this.e.d5.length();
                final int a = this.a(n6, this.e.d6);
                if (a < 0) {
                    // monitorexit(this.h)
                    return "Body end tag not found";
                }
                n2 += a - n6;
                this.c(array, n6, a);
                final int n7 = a + this.e.d6.length();
                n5 = n7 + utils.c(array, n7);
            }
            else {
                this.e.fb.b(false);
                if (this.e.p > 0) {
                    try {
                        final abljemf fb = this.e.o[this.e.p].fb;
                        fb.a("R", 'P');
                        fb.b(false);
                    }
                    catch (Throwable t) {
                        abljem.d("GuiStyle input/playback enable failed " + t);
                    }
                }
            }
            while (abljema.a(array, n5, length, this.e.d7)) {
                final int n8 = n5 + this.e.d7.length();
                final int a2 = this.a(n8, this.e.d8);
                if (a2 < 0) {
                    // monitorexit(this.h)
                    return "Asynch end tag not found";
                }
                n2 += a2 - n8;
                this.c(array, n8, a2);
                final int n9 = a2 + this.e.d8.length();
                n5 = n9 + utils.c(array, n9);
            }
            if (n2 == 0) {
                abljem.d("Recorded screen was empty, no change on playback");
            }
            this.h.notify();
        }
        // monitorexit(this.h)
        return null;
    }
    
    public String c() {
        this.d(this.e.ag);
        return this.b();
    }
    
    private void c(final byte[] array, final int n, final int n2) {
        final byte[] array2 = new byte[n2 - n];
        System.arraycopy(array, n, array2, 0, array2.length);
        this.h.addElement(array2);
    }
    
    private int a(final int n, final String s) {
        final byte[] array = this.e.dy[this.e.ag];
        final int length = array.length;
        if (s == null || s.length() == 0) {
            return -1;
        }
        final byte b = (byte)s.charAt(0);
        for (int i = n; i < length; ++i) {
            if (array[i] == b && abljema.a(array, i, length, s)) {
                return i;
            }
        }
        return -1;
    }
    
    private int e(final int n) {
        final int length = this.e.dy[this.e.ag].length;
        int a = this.a(n, this.e.d3);
        if (a < 0) {
            a = 0;
        }
        return a;
    }
    
    private int f(final int n) {
        int i = 0;
        int n2;
        do {
            n2 = i;
            i = this.e(i + 1);
            if (n > 0 && i >= n) {
                return n2;
            }
        } while (i > 0);
        return n2;
    }
    
    public String b(final int n) {
        String s = null;
        try {
            if (this.e.dy[n] == null) {
                if (this.e.d0) {
                    s = "No playback available for session " + n;
                    return null;
                }
                if (n != this.f) {
                    return "NEDCSR" + (char)(48 + n) + "         ";
                }
                if (this.e.ak[this.f]) {
                    s = "Cannot switch back to signed-off current host session " + n;
                    return null;
                }
                this.d(n);
                return "RFR";
            }
            else {
                this.d(n);
                if (!this.g()) {
                    this.b();
                }
            }
        }
        catch (Throwable t) {
            s = "Session switch failed - see Java console for details";
            t.printStackTrace();
        }
        finally {
            if (s != null) {
                abljem.d(s);
                final byte[] bytes = s.getBytes();
                try {
                    this.a(bytes, 0, bytes.length);
                }
                catch (Throwable t2) {}
            }
        }
        return null;
    }
}
