// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import javax.sound.sampled.Mixer;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import c.f;
import b.a;
import d.b.a.q;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;

public class a6 extends Thread
{
    boolean G;
    SourceDataLine b;
    AudioFormat i;
    t do;
    q m;
    q ad;
    q r;
    a4 V;
    a else;
    f S;
    boolean int;
    boolean d;
    int g;
    int B;
    long Z;
    boolean w;
    boolean if;
    int T;
    int ai;
    byte[] new;
    byte[] aj;
    int u;
    int void;
    int E;
    int H;
    int l;
    int R;
    int v;
    int U;
    int f;
    boolean O;
    boolean aa;
    boolean try;
    boolean ak;
    int e;
    long goto;
    long X;
    long Q;
    float long;
    int ac;
    int x;
    int c;
    int I;
    int char;
    int a;
    byte[] j;
    public int C;
    int W;
    int o;
    float ag;
    int q;
    boolean F;
    aw t;
    long ae;
    webphone n;
    int K;
    byte[] M;
    int k;
    byte[] p;
    byte[] A;
    int D;
    byte[] ah;
    byte[] ab;
    byte[] s;
    boolean P;
    int z;
    int af;
    byte[] J;
    int Y;
    int byte;
    byte[] y;
    int N;
    int h;
    int L;
    int case;
    int for;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    
    public void for() {
        try {
            this.ai = this.t.h;
            if (this.C == this.t.ea) {
                this.long = 32000.0f;
            }
            else if (this.C == this.t.s) {
                this.long = 16000.0f;
            }
            else {
                this.long = 8000.0f;
            }
            this.ak = this.t.bl;
            if (this.t.dK == 3 || this.t.dK == 4 || this.t.dK == 5) {
                ++this.ai;
            }
            this.G = false;
            this.j = new byte[30000];
            this.a();
            this.new = new byte[129000];
            if (this.t.W) {
                this.aj = new byte[this.l + 320];
            }
            if (this.t.dr) {
                this.ac = 2;
            }
            this.M = new byte[this.K];
            this.p = new byte[this.k];
            this.A = new byte[8];
            this.ah = new byte[this.D];
            this.ab = new byte[8];
            this.s = new byte[16500];
        }
        catch (Exception ex) {
            this.t.a(3, "audioplayer.Construnct ", ex);
        }
    }
    
    public a6(final aw t, final int c) {
        this.G = false;
        this.b = null;
        this.do = null;
        this.m = null;
        this.ad = null;
        this.r = null;
        this.V = null;
        this.else = null;
        this.S = null;
        this.int = false;
        this.d = false;
        this.g = 0;
        this.B = 0;
        this.Z = 0L;
        this.w = true;
        this.if = false;
        this.T = 0;
        this.ai = 3;
        this.new = null;
        this.aj = null;
        this.u = 0;
        this.void = 0;
        this.E = 0;
        this.H = 3;
        this.l = 1300;
        this.R = 2;
        this.v = 0;
        this.U = 0;
        this.f = -15;
        this.O = true;
        this.aa = false;
        this.try = false;
        this.ak = true;
        this.e = 0;
        this.goto = 0L;
        this.X = 0L;
        this.Q = 0L;
        this.long = 8000.0f;
        this.ac = 1;
        this.x = 0;
        this.c = 0;
        this.I = 32000;
        this.char = -8;
        this.a = -9;
        this.j = null;
        this.C = 0;
        this.W = 16000;
        this.o = 20;
        this.ag = 1.0f;
        this.q = 32000;
        this.F = false;
        this.t = null;
        this.ae = 0L;
        this.n = null;
        this.K = 4;
        this.M = null;
        this.k = 4;
        this.p = null;
        this.A = null;
        this.D = 4;
        this.ah = null;
        this.ab = null;
        this.s = null;
        this.P = false;
        this.z = 0;
        this.af = 0;
        this.J = null;
        this.Y = 0;
        this.byte = 0;
        this.y = null;
        this.N = 0;
        this.h = 0;
        this.L = 0;
        this.case = 0;
        this.for = 0;
        this.t = t;
        this.n = this.t.ct;
        if (c >= 0) {
            this.C = c;
        }
        else {
            this.C = this.t.ap;
        }
        this.for();
    }
    
    public a6(final t do1, final int c) {
        this.G = false;
        this.b = null;
        this.do = null;
        this.m = null;
        this.ad = null;
        this.r = null;
        this.V = null;
        this.else = null;
        this.S = null;
        this.int = false;
        this.d = false;
        this.g = 0;
        this.B = 0;
        this.Z = 0L;
        this.w = true;
        this.if = false;
        this.T = 0;
        this.ai = 3;
        this.new = null;
        this.aj = null;
        this.u = 0;
        this.void = 0;
        this.E = 0;
        this.H = 3;
        this.l = 1300;
        this.R = 2;
        this.v = 0;
        this.U = 0;
        this.f = -15;
        this.O = true;
        this.aa = false;
        this.try = false;
        this.ak = true;
        this.e = 0;
        this.goto = 0L;
        this.X = 0L;
        this.Q = 0L;
        this.long = 8000.0f;
        this.ac = 1;
        this.x = 0;
        this.c = 0;
        this.I = 32000;
        this.char = -8;
        this.a = -9;
        this.j = null;
        this.C = 0;
        this.W = 16000;
        this.o = 20;
        this.ag = 1.0f;
        this.q = 32000;
        this.F = false;
        this.t = null;
        this.ae = 0L;
        this.n = null;
        this.K = 4;
        this.M = null;
        this.k = 4;
        this.p = null;
        this.A = null;
        this.D = 4;
        this.ah = null;
        this.ab = null;
        this.s = null;
        this.P = false;
        this.z = 0;
        this.af = 0;
        this.J = null;
        this.Y = 0;
        this.byte = 0;
        this.y = null;
        this.N = 0;
        this.h = 0;
        this.L = 0;
        this.case = 0;
        this.for = 0;
        this.do = do1;
        this.t = do1.d;
        this.n = this.do.E;
        if (c >= 0) {
            this.C = c;
        }
        else {
            this.C = this.t.ap;
        }
        this.for();
    }
    
    void a() {
        try {
            if (this.ai == 0) {
                this.W = 2500;
                this.o = 3;
                this.R = 1;
                this.H = 1;
            }
            else if (this.ai == 1) {
                this.W = 5000;
                this.o = 6;
                this.R = 1;
                this.H = 2;
            }
            else if (this.ai == 2) {
                this.W = 8000;
                this.o = 12;
                this.R = 1;
                this.H = 2;
            }
            else if (this.ai == 3) {
                this.W = 10000;
                this.o = 20;
                this.R = 2;
                this.H = 3;
            }
            else if (this.ai == 4) {
                this.W = 15000;
                this.o = 30;
                this.R = 2;
                this.H = 3;
            }
            else if (this.ai == 5) {
                this.W = 30000;
                this.o = 60;
                this.R = 3;
                this.H = 4;
            }
            else if (this.ai == 6) {
                this.W = 60000;
                this.o = 95;
                this.R = 3;
                this.H = 4;
            }
            else {
                this.W = 10000;
                this.o = 20;
            }
            if (this.t.b2 < this.o) {
                this.o = this.t.b2;
            }
            if (this.long < 9000.0f) {
                this.q = 32000;
            }
            else if (this.long < 17000.0f) {
                this.q = 64000;
                this.W *= 2;
            }
            else {
                this.q = 128000;
                this.W *= 4;
            }
            this.if = false;
            if (this.b != null) {
                final int bufferSize = this.b.getBufferSize();
                if (bufferSize >= 320 && bufferSize < 65000) {
                    this.I = bufferSize;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void try() {
    }
    
    public float a(final float n) {
        try {
            if (this.b == null) {
                return n;
            }
            final FloatControl floatControl = (FloatControl)this.b.getControl(FloatControl.Type.VOLUME);
            if (floatControl != null) {
                return floatControl.getValue();
            }
            final FloatControl floatControl2 = (FloatControl)this.b.getControl(FloatControl.Type.MASTER_GAIN);
            if (floatControl2 != null) {
                return floatControl2.getValue();
            }
        }
        catch (Exception ex) {
            this.t.a(3, "audioplayer.GetVolume ", ex);
        }
        return n;
    }
    
    public void new() {
    }
    
    public void a(final int n) {
        if (n > 0) {
            this.a(true);
        }
        else {
            this.a(false);
        }
    }
    
    public void if() {
        this.a(false);
    }
    
    public void a(final boolean b) {
        try {
            this.G = false;
            if (this.b != null) {
                try {
                    if (this.ae != 0L && this.t.do() - this.ae < 100L) {
                        int n = this.b.getBufferSize() - this.b.available();
                        if (n < 160) {
                            n = 160;
                        }
                        else if (n > 9999) {
                            n = 320;
                        }
                        Thread.sleep(0L);
                        Thread.sleep(1L);
                        Thread.sleep(20L);
                        Thread.sleep(n / 16);
                        Thread.sleep(1L);
                        Thread.sleep(0L);
                    }
                }
                catch (Exception ex2) {}
                try {
                    this.ae = 0L;
                    this.b.flush();
                }
                catch (Exception ex3) {}
                try {
                    this.b.stop();
                }
                catch (Exception ex4) {}
                try {
                    if (b || this.t.c()) {
                        this.b.close();
                    }
                }
                catch (Exception ex5) {}
                this.b = null;
            }
            this.G = false;
        }
        catch (Exception ex) {
            this.t.a(2, "audioplayer.CloseLine", ex);
        }
    }
    
    public boolean char() {
        try {
            this.t.a(3, "EVENT,reopen audio player with samplerate " + this.t.if(this.long));
            this.if();
            boolean b = false;
            for (int i = 0; i < 3; ++i) {
                if (this.a(false, i)) {
                    b = true;
                    break;
                }
            }
            if (!b && this.long > 9000.0f) {
                this.long = 8000.0f;
                for (int j = 0; j < 3; ++j) {
                    if (this.a(false, j)) {
                        b = true;
                        this.ak = false;
                        this.t.bl = false;
                        break;
                    }
                }
            }
            return b;
        }
        catch (Exception ex) {
            this.t.a(2, "audioplayer.ReOpen", ex);
            return false;
        }
    }
    
    public boolean case() {
        boolean b = false;
        try {
            for (int i = 0; i < 3; ++i) {
                if (this.a(true, i)) {
                    b = true;
                    break;
                }
            }
        }
        catch (Exception ex) {
            this.t.a(2, "audioplayer.OpenCheck", ex);
        }
        return b;
    }
    
    public boolean a(final boolean b, final int n) {
        int n2 = 0;
        try {
            n2 = 1;
            n2 = 2;
            this.t.a(3, "EVENT,open audio player " + this.t.c);
            this.a(n);
            if (n > 0 && this.ac > 1) {
                this.ac = 1;
            }
            this.E = 0;
            this.u = 0;
            this.x = 0;
            this.c = 0;
            this.X = 0L;
            this.i = new AudioFormat(this.long, 16, this.ac, true, false);
            n2 = 3;
            final DataLine.Info info = new DataLine.Info((a6.class$javax$sound$sampled$SourceDataLine == null) ? (a6.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : a6.class$javax$sound$sampled$SourceDataLine, this.i);
            n2 = 4;
            int n3 = 0;
            Label_1363: {
                Label_0763: {
                    if (!this.P) {
                        if (this.t.c.length() >= 1 && (!this.t.c.equals("Default") || n > 1)) {
                            if (this.t.c.equals("Default") || n <= 1) {
                                break Label_0763;
                            }
                        }
                        try {
                            n2 = 5;
                            this.b = (SourceDataLine)AudioSystem.getLine(info);
                            n2 = 106;
                            this.b.open(this.i);
                            n2 = 3210;
                            if (this.b != null) {
                                n2 = 107;
                                if (!b) {
                                    n2 = 3220;
                                    this.try();
                                    n2 = 3230;
                                    this.b.start();
                                    this.t.a(3, "EVENT,audioplayer opened from 1 " + this.a(this.b));
                                    n2 = 3240;
                                }
                                this.a();
                            }
                            else {
                                this.t.a(5, "EVENT,open audio player ret line 1 is null");
                            }
                            n3 = 1;
                            n2 = 108;
                        }
                        catch (Exception ex) {
                            this.t.a(2, "audioplayer.Opendefault (" + this.t.c(n2) + ")", ex);
                            if (this.w) {
                                this.w = false;
                                this.int();
                            }
                        }
                        if (n3 == 0 && this.n.ringtone != null) {
                            try {
                                this.t.dI = true;
                                this.t.a(3, "EVENT,stop ringplayback");
                                for (int n4 = 0; n4 < 5 && this.n.ringtone != null; ++n4) {
                                    Thread.sleep(50L);
                                }
                            }
                            catch (Exception ex9) {}
                        }
                        if (n3 == 0) {
                            try {
                                final Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
                                for (int i = 0; i < mixerInfo.length; ++i) {
                                    try {
                                        if (mixerInfo[i].getName().equals("Primary Sound Driver") || mixerInfo[i].getName().equals("Java Sound Audio Engine")) {
                                            final Mixer mixer = AudioSystem.getMixer(mixerInfo[i]);
                                            if (mixer != null) {
                                                n2 = 110;
                                                this.b = (SourceDataLine)mixer.getLine(info);
                                                if (this.b != null) {
                                                    n2 = 111;
                                                    this.b.open(this.i);
                                                    n2 = 112;
                                                    if (!b) {
                                                        this.try();
                                                        this.b.start();
                                                        this.t.a(3, "EVENT,audioplayer opened from 22 ");
                                                    }
                                                    this.a();
                                                    n2 = 113;
                                                    n3 = 1;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    catch (Exception ex2) {
                                        this.t.a(2, "audiorecorder.Openpreferred 229 (" + this.t.c(n2) + ")", ex2);
                                        if (this.w) {
                                            this.w = false;
                                            this.int();
                                        }
                                    }
                                }
                            }
                            catch (Exception ex3) {
                                this.t.a(2, "audioplayer.Openpreferred 23 (" + this.t.c(n2) + ")", ex3);
                                if (this.w) {
                                    this.w = false;
                                    this.int();
                                }
                            }
                        }
                        break Label_1363;
                    }
                }
                if (!this.P) {
                    n2 = 109;
                    try {
                        final Mixer.Info[] mixerInfo2 = AudioSystem.getMixerInfo();
                        int j = 0;
                        while (j < mixerInfo2.length) {
                            if (mixerInfo2[j].getName().equals(this.t.c) || j == mixerInfo2.length - 1) {
                                final Mixer mixer2 = AudioSystem.getMixer(mixerInfo2[j]);
                                if (mixer2 == null) {
                                    break;
                                }
                                n2 = 110;
                                this.b = (SourceDataLine)mixer2.getLine(info);
                                if (this.b != null) {
                                    n2 = 111;
                                    this.b.open(this.i);
                                    n2 = 112;
                                    if (!b) {
                                        this.try();
                                        this.b.start();
                                        this.t.a(3, "EVENT,audioplayer opened from 2 " + this.a(this.b, mixer2));
                                    }
                                    this.a();
                                    n2 = 113;
                                    n3 = 1;
                                    break;
                                }
                                break;
                            }
                            else {
                                ++j;
                            }
                        }
                    }
                    catch (Exception ex4) {
                        this.t.a(2, "audioplayer.Openpreferred (" + this.t.c(n2) + ")", ex4);
                        if (this.w) {
                            this.w = false;
                            this.int();
                        }
                    }
                    if (n3 == 0 && b) {
                        this.t.a(3, "ERROR,cannot open audio player device with high sample rate checkonly");
                        this.a(n);
                        return false;
                    }
                    if (n3 == 0 && this.n.ringtone != null) {
                        try {
                            this.t.dI = true;
                            this.t.a(3, "EVENT,stop ringplayback");
                            for (int n5 = 0; n5 < 5 && this.n.ringtone != null; ++n5) {
                                Thread.sleep(50L);
                            }
                        }
                        catch (Exception ex10) {}
                    }
                    if (n3 == 0 && this.long > 9000.0f) {
                        this.t.a(2, "ERROR,cannot open audio player device with high sample rate");
                        this.long = 8000.0f;
                        this.ak = false;
                        this.t.bl = false;
                        this.a(n);
                        return false;
                    }
                    if (n3 == 0) {
                        try {
                            n2 = 114;
                            this.b = (SourceDataLine)AudioSystem.getLine(info);
                            n2 = 115;
                            this.b.open(this.i);
                            n2 = 3310;
                            if (this.b != null) {
                                n2 = 116;
                                if (!b) {
                                    n2 = 3311;
                                    this.try();
                                    n2 = 3312;
                                    this.b.start();
                                    this.t.a(3, "EVENT,audioplayer opened from 3 " + this.a(this.b));
                                    n2 = 3313;
                                }
                                this.a();
                                n3 = 1;
                            }
                            else {
                                this.t.a(5, "EVENT,open audio player ret line 2 is null");
                            }
                            n2 = 117;
                        }
                        catch (Exception ex5) {
                            this.t.a(2, "audioplayer.Opendefault2 (" + this.t.c(n2) + ")", ex5);
                            if (this.w) {
                                this.w = false;
                                this.int();
                            }
                        }
                    }
                }
            }
            if (n3 == 0) {
                try {
                    final Mixer.Info[] mixerInfo3 = AudioSystem.getMixerInfo();
                    for (int k = 0; k < mixerInfo3.length; ++k) {
                        try {
                            final Mixer mixer3 = AudioSystem.getMixer(mixerInfo3[k]);
                            if (mixer3 != null) {
                                n2 = 2110;
                                this.b = (SourceDataLine)mixer3.getLine(info);
                                if (this.b != null) {
                                    n2 = 2111;
                                    this.b.open(this.i);
                                    if (!this.P) {
                                        n2 = 2112;
                                        if (!b) {
                                            this.try();
                                            this.b.start();
                                            this.t.a(3, "EVENT,audioplayer opened from 4 " + this.a(this.b, mixer3));
                                        }
                                        this.a();
                                        n2 = 2113;
                                        n3 = 1;
                                        break;
                                    }
                                    this.t.a(3, "EVENT,audioplayer dlist " + this.t.c(k) + " " + this.a(this.b, mixer3));
                                }
                            }
                        }
                        catch (Exception ex6) {
                            this.t.a(3, "audioplayer.OpenAllIntern (" + this.t.c(k) + ":" + this.t.c(n2) + ")", ex6);
                        }
                    }
                }
                catch (Exception ex7) {
                    this.t.a(3, "audioplayer.OpenAllExtern (" + this.t.c(n2) + ")", ex7);
                }
            }
            n2 = 7;
            if (n3 == 0) {
                this.t.a(1, "ERROR,cannot open audio player device");
                this.int();
                this.a(n);
                return false;
            }
            this.G = true;
            n2 = 8;
            if (this.b != null && this.t.eK >= 3) {
                n2 = 10;
                this.i = this.b.getFormat();
                if (this.i != null) {
                    this.t.a(3, "EVENT, audio out opened as " + this.i.toString());
                }
                n2 = 11;
            }
            return true;
        }
        catch (Exception ex8) {
            this.t.a(1, "audioplayer.Open (" + this.t.c(n2) + ")", ex8);
            try {
                this.a(n);
            }
            catch (Exception ex11) {}
            return false;
        }
    }
    
    public String a(final SourceDataLine sourceDataLine) {
        if (this.t.eK < 4) {
            return "";
        }
        try {
            return sourceDataLine.getLineInfo().toString();
        }
        catch (Exception ex) {
            this.t.a(3, "audiorecorder.GetLineInfo", ex);
            return "";
        }
    }
    
    public String a(final SourceDataLine sourceDataLine, final Mixer mixer) {
        String string = "";
        if (this.t.eK < 4) {
            return string;
        }
        try {
            string = sourceDataLine.getLineInfo().toString();
        }
        catch (Exception ex) {
            this.t.a(3, "audioplayer.GetLineInfo", ex);
        }
        return string;
    }
    
    public void int() {
        if (this.t.eK < 4) {
            return;
        }
        this.t.a(4, "EVENT,supported playback info:");
        final int n = 0;
        try {
            final Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
            final Line.Info info = new Line.Info((a6.class$javax$sound$sampled$SourceDataLine == null) ? (a6.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : a6.class$javax$sound$sampled$SourceDataLine);
            for (int i = 0; i < mixerInfo.length; ++i) {
                final Mixer mixer = AudioSystem.getMixer(mixerInfo[i]);
                if (mixer != null) {
                    if (info != null) {
                        if (mixer.isLineSupported(info)) {
                            this.t.a(4, "EVENT,   recorder " + mixerInfo[i].getName() + ": " + mixerInfo[i].getDescription() + " " + this.t.c(mixer.getMaxLines(info)));
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            this.t.a(3, "audioplayer.PrintSupportedFormats " + this.t.c(n), ex);
        }
    }
    
    public void byte() {
        this.int = true;
        this.do();
        this.int = true;
    }
    
    public boolean do() {
        try {
            this.if();
            return true;
        }
        catch (Exception ex) {
            this.t.a(3, "audioplayer.Close", ex);
            return false;
        }
    }
    
    public boolean if(final byte[] array, final int n) {
        return this.a(array, n, true, true);
    }
    
    public boolean a(final byte[] array, final int n, final boolean b, final boolean b2) {
        int n2 = 0;
        try {
            if (!this.G) {
                return false;
            }
            if (this.t.cu) {
                this.O = true;
                this.aa = false;
                this.try = false;
                this.d = true;
                this.U = 0;
                this.v = 0;
                return true;
            }
            if (n < 10 || (b2 && n < 20) || n > this.q / 3 || n > 16000) {
                if (this.t.eK > 4) {
                    this.t.a(4, "WARNING, (rtp) packet_length is " + this.t.c(n));
                }
                this.d = true;
                return false;
            }
            int void1;
            if (!b2) {
                System.arraycopy(array, 0, this.j, 0, n);
                void1 = n;
                this.d = false;
            }
            else {
                n2 = 1;
                System.arraycopy(array, 0, this.M, 0, this.K);
                n2 = 2;
                final int a = webphone.r.a(this.M);
                n2 = 3;
                final int n3 = a >>> 23 & 0x1;
                this.C = (a >>> 16 & 0x7F);
                final int f = a & 0xFFFF;
                final int k = this.K;
                n2 = 4;
                System.arraycopy(array, k, this.p, 0, this.k);
                System.arraycopy(this.p, 0, this.A, 4, 4);
                webphone.r.do(this.A);
                final int n4 = k + this.k;
                n2 = 5;
                n2 = 6;
                System.arraycopy(array, n4, this.ah, 0, this.D);
                System.arraycopy(this.ah, 0, this.ab, 4, 4);
                final long do1 = webphone.r.do(this.ab);
                final int n5 = n4 + this.D;
                n2 = 7;
                final int n6 = n - n5;
                if (n6 < 10) {
                    if (this.t.eK > 4) {
                        this.t.a(4, "WARNING, (rtp) payload_length is " + this.t.c(n6));
                    }
                    this.d = true;
                    return false;
                }
                if (this.case < 15 && this.t.ey == do1) {
                    ++this.case;
                    if (this.t.eK > 4) {
                        this.t.a(4, "WARNING, (rtp) media loopback detected with ssrc " + this.t.if(do1));
                    }
                    this.d = true;
                    return false;
                }
                this.case = 0;
                if (this.f + 1 == f || this.f == -15) {
                    this.d = false;
                    this.B = 0;
                }
                else if (this.f == f) {
                    if (this.t.eK > 2) {
                        this.t.a(3, "WARNING, (rtp)  same cseq received " + this.t.c(this.f) + " " + this.t.c(f));
                    }
                    if (++this.B < 5) {
                        return false;
                    }
                }
                else if (b) {
                    if (f < this.f && f > this.f - 30) {
                        this.d = true;
                        if (this.t.eK > 2) {
                            this.t.a(3, "WARNING, (rtp)  notordered packet " + this.t.c(this.f) + " " + this.t.c(f));
                        }
                    }
                    else if (this.f > f || f > this.f + 250) {
                        this.d = true;
                        this.B = 0;
                        if (this.t.eK > 2) {
                            this.t.a(3, "WARNING, (rtp)  cseq received jump " + this.t.c(this.f) + " " + this.t.c(f));
                        }
                    }
                    else if (this.t.et && f < this.f + 6) {
                        int n7 = 0;
                        if (this.J != null && this.Y > 0) {
                            n7 = (webphone.r.a(this.J) & 0xFFFF);
                        }
                        if (n7 < f && n7 + 7 > f && this.J != null && this.Y > 0) {
                            if (this.y == null || this.h < n) {
                                this.y = new byte[n];
                                this.h = n;
                            }
                            if (this.t.eK > 3) {
                                this.t.a(4, "WARNING, (rtp) recovering " + this.t.c(this.L) + " outoforder packets " + this.t.c(f));
                            }
                            System.arraycopy(array, 0, this.y, 0, n);
                            this.N = n;
                            this.a(this.J, this.Y, false, true);
                            this.Y = 0;
                            final t do2 = this.do;
                            do2.i -= this.L;
                            return this.a(this.y, this.N, false, true);
                        }
                        this.B = 0;
                        this.L = f - this.f - 1;
                        this.do.i += this.L;
                        if (this.t.eK > 3) {
                            if (f == this.f + 2) {
                                this.t.a(4, "WARNING, (rtp) short lost one packet " + this.t.c(f));
                            }
                            else {
                                this.d = true;
                                this.t.a(3, "WARNING, (rtp) short  lost " + this.t.c(f - this.f - 1) + " packets  " + this.t.c(this.f) + " " + this.t.c(f));
                            }
                        }
                        if (this.J == null || this.byte < n) {
                            this.J = new byte[n];
                            this.byte = n;
                        }
                        System.arraycopy(array, 0, this.J, 0, n);
                        this.Y = n;
                        return true;
                    }
                    else {
                        this.B = 0;
                        this.do.i += f - this.f - 1;
                        if (this.t.eK > 3) {
                            if (f == this.f + 2) {
                                this.t.a(3, "WARNING, (rtp) lost one packet " + this.t.c(f));
                            }
                            else {
                                this.d = true;
                                this.t.a(3, "WARNING, (rtp) lost " + this.t.c(f - this.f - 1) + " packets  " + this.t.c(this.f) + " " + this.t.c(f));
                            }
                        }
                    }
                }
                if (n3 > 0) {
                    if (this.t.eK > 2) {
                        this.t.a(3, "EVENT, (rtp)  marker received " + this.t.c(n3));
                    }
                    this.d = true;
                }
                if (this.t.eK > 4 && (this.t.do() - this.ae > 600L || (this.t.dK != 3 && this.t.dK != 4 && this.t.dK != 5 && this.t.do() - this.ae > 300L)) && this.ae != 0L) {
                    if (this.t.eK > 2) {
                        this.t.a(3, "WARNING, (rtp) packet delayed with " + this.t.if(this.t.do() - this.ae) + " msec");
                    }
                    this.d = true;
                }
                this.f = f;
                this.ae = this.t.do();
                final byte[] array2 = new byte[n6];
                n2 = 8;
                System.arraycopy(array, n5, array2, 0, n6);
                n2 = 9;
                Label_2229: {
                    if (n6 == this.a) {
                        if (this.char >= 0) {
                            if (this.C != 0) {
                                if (this.C != 8) {
                                    if (this.C != 3) {
                                        if (this.C != 18) {
                                            if (this.C != this.t.p || this.t.bv <= 0) {
                                                if (this.C != this.t.T || this.t.z <= 0) {
                                                    if (this.C != this.t.ea || this.t.cL <= 0) {
                                                        if (this.C != this.t.s || this.t.dl <= 0) {
                                                            if (this.C != 97 || this.t.bv <= 0) {
                                                                if (this.C != 104 || this.t.z <= 0) {
                                                                    if (this.C != 105 || this.t.dl <= 0) {
                                                                        if (this.C != 106 || this.t.cL <= 0) {
                                                                            if (this.t.dl <= 0 || (this.C != this.t.s && (this.C != 105 || this.C == this.t.ea || this.C == this.t.T)) || !this.ak) {
                                                                                if (this.t.cL <= 0 || (this.C != this.t.ea && (this.C != 106 || this.C == this.t.s || this.C == this.t.T)) || !this.ak) {
                                                                                    if (this.t.z > 0) {
                                                                                        if (this.C == this.t.T) {
                                                                                            break Label_2229;
                                                                                        }
                                                                                        if (this.C == 104) {
                                                                                            break Label_2229;
                                                                                        }
                                                                                    }
                                                                                    if (this.C != 19) {
                                                                                        if (this.C != 13) {
                                                                                            this.C = this.char;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (this.C == 0) {
                    webphone.ah.int(array2, 0, this.j, 0, n6, false);
                    void1 = n6 * 2;
                    if (this.long > 9000.0f) {
                        this.d = true;
                        this.long = 8000.0f;
                        this.char();
                    }
                }
                else if (this.C == 8) {
                    webphone.ah.for(array2, 0, this.j, 0, n6, false);
                    void1 = n6 * 2;
                    if (this.long > 9000.0f) {
                        this.d = true;
                        this.long = 8000.0f;
                        this.char();
                    }
                }
                else if (this.C == 3) {
                    if (this.V == null) {
                        this.d = true;
                        this.V = new a4(this.t);
                    }
                    void1 = this.V.a(array2, n6, this.j);
                    if (this.long > 9000.0f) {
                        this.d = true;
                        this.long = 8000.0f;
                        this.char();
                    }
                }
                else if (this.C == 18) {
                    if (this.else == null) {
                        this.d = true;
                        (this.else = new a(this.t)).a(false);
                    }
                    void1 = this.else.a(array2, n6, this.j);
                    if (this.long > 9000.0f) {
                        this.d = true;
                        this.long = 8000.0f;
                        this.char();
                    }
                }
                else if (this.C == this.t.p) {
                    if (this.S == null) {
                        this.d = true;
                        (this.S = new f(this.t)).a(false);
                    }
                    void1 = this.S.do(array2, n6, this.j);
                    if (this.long > 9000.0f) {
                        this.d = true;
                        this.long = 8000.0f;
                        this.char();
                    }
                }
                else if (this.t.dl > 0 && (this.C == this.t.s || (this.C == 105 && this.C != this.t.ea && this.C != this.t.T)) && this.ak) {
                    if (this.ad == null) {
                        this.d = true;
                        (this.ad = new q()).a(1, 16000, 1, true);
                    }
                    this.ad.a(array2, 0, n6);
                    void1 = this.ad.a(this.j, 0);
                    if (this.long < 15000.0f || this.long > 17000.0f) {
                        this.d = true;
                        this.long = 16000.0f;
                        this.char();
                    }
                }
                else if (this.t.cL > 0 && (this.C == this.t.ea || (this.C == 106 && this.C != this.t.s && this.C != this.t.T)) && this.ak) {
                    if (this.r == null) {
                        this.d = true;
                        (this.r = new q()).a(2, 32000, 1, true);
                    }
                    this.r.a(array2, 0, n6);
                    void1 = this.r.a(this.j, 0);
                    if (this.long < 31000.0f) {
                        this.d = true;
                        this.long = 32000.0f;
                        this.char();
                    }
                }
                else if (this.t.z > 0 && (this.C == this.t.T || this.C == 104)) {
                    if (this.m == null) {
                        this.d = true;
                        (this.m = new q()).a(0, 8000, 1, true);
                    }
                    this.m.a(array2, 0, n6);
                    void1 = this.m.a(this.j, 0);
                    if (this.long > 9000.0f) {
                        this.d = true;
                        this.long = 8000.0f;
                        this.char();
                    }
                }
                else if (this.C > 96 && (this.t.z > 0 || this.C == this.t.T) && this.m != null) {
                    this.m.a(array2, 0, n6);
                    void1 = this.m.a(this.j, 0);
                    if (this.long > 9000.0f) {
                        this.d = true;
                        this.long = 8000.0f;
                        this.char();
                    }
                }
                else if (this.C > 96 && (this.t.dl > 0 || this.C == this.t.s) && this.ad != null && this.ak) {
                    this.ad.a(array2, 0, n6);
                    void1 = this.ad.a(this.j, 0);
                    if (this.long < 15000.0f || this.long > 17000.0f) {
                        this.d = true;
                        this.long = 16000.0f;
                        this.char();
                    }
                }
                else if (this.C > 96 && (this.t.cL > 0 || this.C == this.t.ea) && this.r != null && this.ak) {
                    this.r.a(array2, 0, n6);
                    void1 = this.r.a(this.j, 0);
                    if (this.long < 31000.0f) {
                        this.d = true;
                        this.long = 32000.0f;
                        this.char();
                    }
                }
                else if (this.C == 97) {
                    if (this.S == null) {
                        this.d = true;
                        (this.S = new f(this.t)).a(false);
                    }
                    void1 = this.S.do(array2, n6, this.j);
                    if (this.long > 9000.0f) {
                        this.d = true;
                        this.long = 8000.0f;
                        this.char();
                    }
                }
                else {
                    if (this.C == 19 || this.C == 13) {
                        return this.d = true;
                    }
                    this.d = true;
                    ++this.af;
                    if (this.af == 1 || (this.af > 20 && this.af % 30 == 0)) {
                        this.t.a(3, "EVENT,invalid payload data received " + this.t.c(this.C) + "  " + this.t.c(n) + "  " + this.t.c(n6));
                    }
                    return true;
                }
            }
            if (this.new == null) {
                this.d = true;
                this.t.a(3, "EVENT,jitterbuffer not initialized");
                return true;
            }
            if (this.do != null) {
                this.do.a(false, this.j, void1);
            }
            this.char = this.C;
            this.a = void1;
            if (this.d) {
                if (this.t.do() - this.Z > 14000L) {
                    this.g = 0;
                }
                else if (this.t.do() - this.Z > 7000L) {
                    --this.g;
                    if (this.g < 0) {
                        this.g = 0;
                    }
                }
                else if (this.t.do() - this.Z < 2000L) {
                    this.g += 2;
                }
                else if (this.t.do() - this.Z < 4000L) {
                    ++this.g;
                }
                this.Z = this.t.do();
                if (this.g > 9) {
                    this.d = false;
                }
            }
            n2 = 303;
            int n8 = this.b.getBufferSize() - this.b.available();
            if (n8 < 0 || n8 > 64000) {
                n8 = 160;
            }
            if (!this.O && !this.aa && n8 > 320 && this.t.do() - this.X > 2000L && (n8 > this.W || (this.d && n8 > this.W * 2 / 3) || (this.void > 0 && (n8 / this.void > this.o || (this.d && n8 / this.void > this.o * 2 / 3))))) {
                n2 = 307;
                this.t.a(3, "EVENT,rtp out dropping avail " + this.t.c(n8) + ", jitter: " + this.t.c(this.U) + " packets (" + this.t.c(this.v) + " bytes). tresholds: " + this.t.c(this.void) + "," + this.t.c(this.o) + "," + this.t.c(this.W));
                this.c = 0;
                this.Q = this.t.do();
                this.d = true;
                if (this.d) {
                    this.T += 6;
                    try {
                        this.b.flush();
                        int n9 = this.b.getBufferSize() - this.b.available();
                        if (n9 < 0) {
                            n9 = 0;
                        }
                        else if (n9 > 64000) {
                            n9 = 160;
                        }
                        if (n9 < 330 || n9 < this.W / 3 || (this.void > 0 && n9 / this.void > this.o / 3)) {
                            this.t.a(4, "EVENT,rtp player flush succ");
                            return true;
                        }
                        this.t.a(4, "EVENT,rtp player flush fail");
                    }
                    catch (Exception ex2) {}
                }
                else {
                    this.T += 8;
                }
                this.aa = true;
                this.try = false;
                this.U = 0;
                this.v = 0;
                this.c = 0;
                this.d = true;
                this.Q = this.t.do();
                return true;
            }
            if (!this.O && !this.aa && n8 > 320 && this.t.do() - this.X > 1500L && (n8 > this.W / 2 || (this.d && n8 > this.W / 3) || (this.void > 0 && (n8 / this.void > this.o / 2 || (this.d && n8 / this.void > this.o / 3))))) {
                n2 = 2307;
                this.t.a(3, "EVENT,rtp out dropping one packet (" + this.t.c(n) + ") avail " + this.t.c(n8) + ", jitter: " + this.t.c(this.U) + " packets (" + this.t.c(this.v) + " bytes). tresholds: " + this.t.c(this.void) + "," + this.t.c(this.o) + "," + this.t.c(this.W));
                ++this.T;
                if (this.d) {
                    ++this.T;
                    try {
                        this.b.flush();
                        int n10 = this.b.getBufferSize() - this.b.available();
                        if (n10 < 0) {
                            n10 = 0;
                        }
                        else if (n10 > 64000) {
                            n10 = 160;
                        }
                        if (n10 < 170 || n10 < this.W / 4 || (this.void > 0 && n10 / this.void > this.o / 4)) {
                            this.t.a(4, "EVENT,rtp player flush2 succ");
                        }
                        else {
                            this.t.a(4, "EVENT,rtp player flush2 fail");
                        }
                    }
                    catch (Exception ex3) {}
                }
                else {
                    this.T += 2;
                }
                this.c = 0;
                this.Q = this.t.do();
                return this.d = true;
            }
            if (!this.O && this.aa && (this.t.do() - this.Q > 2000L || n8 < this.W / 8 || (this.void > 0 && n8 / this.void <= 2) || (this.e > 2 && this.t.do() - this.X < 7000L && (n8 < this.W / 6 || (this.void > 0 && n8 / this.void <= 3))) || (this.e > 4 && this.t.do() - this.X < 7000L && (n8 < this.W / 3 || (this.void > 0 && n8 / this.void <= 5))) || (this.e > 6 && this.t.do() - this.X < 7000L && (n8 < this.W / 2 || (this.void > 0 && n8 / this.void <= 7))))) {
                n2 = 308;
                this.c = 0;
                this.Q = this.t.do();
                this.t.a(4, "EVENT,rtp out dropping dropping finished");
                this.aa = false;
                this.try = false;
                this.d = true;
            }
            else if (!this.O && this.aa) {
                this.U = 0;
                this.v = 0;
                return this.d = true;
            }
            if (void1 > this.q / 2) {
                this.d = true;
                this.t.a(3, "EVENT,too big packet received " + this.t.c(void1));
                return true;
            }
            if (this.v + void1 <= this.q) {
                n2 = 11;
                System.arraycopy(this.j, 0, this.new, this.v, void1);
                if (void1 < this.l && void1 > 4) {
                    if (this.t.W) {
                        System.arraycopy(this.j, 0, this.aj, 0, void1);
                    }
                    this.u = void1;
                    this.void = void1;
                }
                this.v += void1;
                ++this.U;
                this.F = false;
            }
            else {
                this.d = true;
                this.t.a(3, "EVENT,jitter buffer overflow " + this.t.c(void1) + " " + this.t.c(this.v));
                this.F = true;
            }
            n2 = 12;
            if (this.O) {
                n2 = 301;
                if (this.ai != 0 && !this.F && this.U < this.t.b2 && (this.ai != 1 || (this.U <= 4 && this.v <= this.long / 10.0f && (this.v < this.I / 3 || this.U <= 1) && (this.e >= 4 || this.U <= 0) && (this.e >= 12 || this.U <= 1) && (this.e >= 40 || this.U <= 2) && (this.e >= 140 || this.U <= 3))) && (this.ai != 2 || (this.U <= 6 && this.v <= this.long / 5.0f && (this.v < this.I / 2 || this.U <= 1) && (this.e >= 4 || this.U <= 1) && (this.e >= 10 || this.U <= 2) && (this.e >= 32 || this.U <= 3) && (this.e >= 100 || this.U <= 4) && (this.e >= 240 || this.U <= 5))) && (this.ai != 3 || (this.U <= 7 && this.v <= this.long / 3.0f && (this.v < this.I * 4 / 5 || this.U <= 2) && (this.e >= 1 || this.U <= 1) && (this.e >= 3 || this.U <= 2) && (this.e >= 8 || this.U <= 3) && (this.e >= 30 || this.U <= 4) && (this.e >= 70 || this.U <= 5) && (this.e >= 150 || this.U <= 6))) && (this.ai != 4 || (this.U <= 10 && this.v <= this.long / 2.0f && (this.v < this.I || this.U <= 2) && (this.e >= 1 || this.U <= 2) && (this.e >= 2 || this.U <= 3) && (this.e >= 4 || this.U <= 4) && (this.e >= 8 || this.U <= 5) && (this.e >= 16 || this.U <= 6) && (this.e >= 32 || this.U <= 7) && (this.e >= 64 || this.U <= 8))) && (this.ai != 5 || (this.U <= 22 && this.v <= this.long * 2.0f && (this.v < this.I || this.U <= 3) && (this.e >= 1 || this.U <= 3) && (this.e >= 2 || this.U <= 5) && (this.e >= 4 || this.U <= 7) && (this.e >= 8 || this.U <= 9) && (this.e >= 16 || this.U <= 11) && (this.e >= 32 || this.U <= 13) && (this.e >= 64 || this.U <= 15) && (this.e >= 96 || this.U <= 18))) && (this.ai != 6 || (this.U <= 40 && this.v <= this.long * 4.0f && (this.e >= 1 || this.U <= 6) && (this.e >= 2 || this.U <= 9) && (this.e >= 4 || this.U <= 12) && (this.e >= 7 || this.U <= 15) && (this.e >= 10 || this.U <= 18) && (this.e >= 15 || this.U <= 21) && (this.e >= 20 || this.U <= 25) && (this.e >= 30 || this.U <= 30)))) {
                    return true;
                }
                n2 = 302;
                this.t.a(3, "EVENT,prebuffering finished (" + this.t.c(this.e) + ") at " + this.t.c(this.U) + " jcount, " + this.t.c(this.v) + " bytes");
                if (this.goto != 0L && this.e > 0 && this.t.do() - this.goto > 7000L) {
                    if (this.t.do() - this.goto > 80000L) {
                        this.e -= 3;
                    }
                    else if (this.t.do() - this.goto > 17000L) {
                        this.e -= 2;
                    }
                    else {
                        --this.e;
                    }
                    if (this.e < 0) {
                        this.e = 0;
                    }
                }
                if (this.e == 3 && !this.if && this.T > 10) {
                    this.if = true;
                    this.W *= 2;
                    this.o *= 2;
                }
                this.d = true;
                this.goto = this.t.do();
                this.X = this.goto;
                ++this.e;
                this.O = false;
                this.aa = false;
                this.try = false;
            }
            else {
                n2 = 304;
                if (!this.aa && (n8 < 10 || (this.d && this.u > 0 && n8 < this.u * 2) || n8 < 160)) {
                    n2 = 305;
                    if ((this.x <= 4 || this.e <= 7) && this.t.do() - this.X >= 600 + this.e * 200 && this.t.do() - this.Q >= 2000L) {
                        n2 = 306;
                        this.O = true;
                        if (this.t.do() - this.X < 600 + this.e * 200 + 300) {
                            ++this.x;
                            if (this.x > 30) {
                                this.x = 30;
                            }
                        }
                        else if (this.t.do() - this.X > 8000 + this.e * 200) {
                            this.x -= 4;
                            if (this.x < 0) {
                                this.x = 0;
                            }
                        }
                        else if (this.t.do() - this.X > (600 + this.e * 200) * 2 + 300) {
                            this.x -= 3;
                            if (this.x < 0) {
                                this.x = 0;
                            }
                        }
                        this.t.a(3, "EVENT,rtp out prebuffering " + this.t.c(this.e) + " " + this.t.c(n8) + " " + this.t.c(this.u));
                        this.d = true;
                        this.X = this.t.do();
                        this.try = false;
                        this.aa = false;
                        this.d = false;
                        this.c = 0;
                        return true;
                    }
                    if (this.x == 7) {
                        this.t.a(4, "WARNING,rtp out not prebuffering " + this.t.c(this.x) + " " + this.t.c(this.e) + " " + this.t.if(this.t.do() - this.X) + " " + this.t.if(this.t.do() - this.Q));
                    }
                }
                else if (this.x > 5) {
                    n2 = 309;
                    if (++this.c > 200) {
                        this.c = 0;
                        this.x = 0;
                        this.t.a(4, "EVENT,normal rtp prebuff enabled again");
                    }
                }
            }
            n2 = 13;
            if (this.v % 2 == 1) {
                n2 = 310;
                if (this.t.eC) {
                    this.t.a(3, "EVENT,jittersize is not pair");
                }
                --this.v;
            }
            if (!this.G) {
                return false;
            }
            n2 = 311;
            this.a(this.new, this.v);
            this.E = 0;
            n2 = 14;
            this.U = 0;
            this.v = 0;
            return true;
        }
        catch (Exception ex) {
            this.t.a(3, "audioplayer.Play (" + this.t.c(n2) + ")", ex);
            return false;
        }
    }
    
    public synchronized void a(final byte[] array, int n) {
        final int n2 = 0;
        if (this.t.bK != 1.0f) {
            try {
                for (int i = 0; i < n / 2; ++i) {
                    final int round = Math.round(this.t.bK * webphone.ah.if(array, i * 2, false));
                    short n3;
                    if (round < -32768) {
                        n3 = -32768;
                    }
                    else if (round > 32767) {
                        n3 = 32767;
                    }
                    else {
                        n3 = (short)round;
                    }
                    webphone.ah.a(n3, array, i * 2, false);
                }
            }
            catch (Exception ex) {
                this.t.a(3, "audioplayer.setvolume (" + this.t.c(n2) + ")", ex);
            }
        }
        if (this.ac == 2) {
            try {
                for (int j = n - 1; j >= 1; j -= 2) {
                    array[j * 2 + 1] = array[j];
                    array[j * 2] = array[j - 1];
                    array[j * 2 - 1] = array[j];
                    array[j * 2 - 2] = array[j - 1];
                }
                n *= 2;
            }
            catch (Exception ex2) {
                this.t.a(3, "audioplayer.converttostereo (" + this.t.c(n2) + ")", ex2);
            }
        }
        if (!this.G) {
            return;
        }
        if (this.t.fo > 0 && this.do != null && this.do.bf != null && this.long == this.do.bf.m) {
            try {
                if (this.do.z != null && this.long != this.do.z.X) {
                    this.do.z = null;
                }
                if (this.do.z == null) {
                    this.t.a(4, "EVENT, creating aec on play with samplerate " + this.t.c((int)this.long));
                    if (this.do.z == null) {
                        this.do.z = new an(this.do);
                        this.do.z.X = this.long;
                    }
                }
                this.do.z.if(array, n);
            }
            catch (Exception ex3) {
                if (this.t.eK > 4) {
                    this.t.a(3, "audioplayer.aec (" + this.t.c(n2) + ")", ex3);
                }
            }
        }
        try {
            if (!this.G) {
                return;
            }
            this.b.write(array, 0, n);
        }
        catch (Exception ex4) {
            this.t.a(3, "audioplayer.linewrite (" + this.t.c(n2) + ")", ex4);
        }
    }
    
    public void run() {
        try {
            if (!this.t.W) {
                this.setPriority(1);
            }
            int n = 0;
            while (!this.int) {
                if (!this.t.W) {
                    Thread.sleep(1000L);
                }
                else if (!this.G || this.O || this.t.cu) {
                    Thread.sleep(300L);
                }
                else {
                    int n2 = this.b.getBufferSize() - this.b.available();
                    if (n2 < 0 || n2 > 64000) {
                        n2 = 160;
                    }
                    if (n2 > 0 && this.u > 0 && this.E < 3 && (this.X == 0L || this.t.do() - this.X > 500L) && (this.Q == 0L || this.t.do() - this.Q > 2000L) && n2 < this.u * this.R && this.aj != null && n2 < 4000) {
                        if (n == 0) {
                            n = 1;
                            this.setPriority(10);
                        }
                        if (this.t.eK > 4) {
                            this.t.a(4, "EVENT,plc " + this.t.c(this.E) + " at avail " + this.t.c(n2));
                        }
                        this.a(this.aj, this.u);
                        if (++this.E < this.H) {
                            continue;
                        }
                        this.u = 0;
                    }
                    else {
                        Thread.sleep(0L, 1);
                    }
                }
            }
        }
        catch (Exception ex) {
            this.t.a(3, "audioplayer.run", ex);
        }
        this.int = true;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
