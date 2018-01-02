// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import c.f;
import b.a;
import d.b.a.b;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;

public class a0 extends Thread
{
    boolean n;
    TargetDataLine void;
    t p;
    AudioFormat k;
    boolean a;
    b goto;
    b char;
    b byte;
    a4 d;
    a j;
    f o;
    public int e;
    int h;
    int i;
    String g;
    long f;
    float b;
    float m;
    boolean try;
    aw c;
    int new;
    int int;
    int l;
    int for;
    int case;
    int do;
    boolean if;
    webphone long;
    boolean else;
    static /* synthetic */ Class class$javax$sound$sampled$TargetDataLine;
    
    public void do() {
        try {
            this.n = false;
            this.try = this.c.bl;
            this.e = this.c.ap;
            if (this.p != null) {
                this.c.ey = this.p.aX;
            }
        }
        catch (Exception ex) {
            this.c.a(3, "audiorecorder.ctor ", ex);
        }
    }
    
    public a0(final aw c) {
        this.n = false;
        this.void = null;
        this.p = null;
        this.a = false;
        this.goto = null;
        this.char = null;
        this.byte = null;
        this.d = null;
        this.j = null;
        this.o = null;
        this.e = 0;
        this.h = 0;
        this.i = 0;
        this.g = "";
        this.f = 0L;
        this.b = 1.0f;
        this.m = 8000.0f;
        this.try = true;
        this.c = null;
        this.new = 1;
        this.int = 1;
        this.l = 320;
        this.for = 1;
        this.case = 20;
        this.do = 240;
        this.if = true;
        this.long = null;
        this.else = false;
        this.c = c;
        this.long = this.c.ct;
        this.do();
    }
    
    public a0(final t p) {
        this.n = false;
        this.void = null;
        this.p = null;
        this.a = false;
        this.goto = null;
        this.char = null;
        this.byte = null;
        this.d = null;
        this.j = null;
        this.o = null;
        this.e = 0;
        this.h = 0;
        this.i = 0;
        this.g = "";
        this.f = 0L;
        this.b = 1.0f;
        this.m = 8000.0f;
        this.try = true;
        this.c = null;
        this.new = 1;
        this.int = 1;
        this.l = 320;
        this.for = 1;
        this.case = 20;
        this.do = 240;
        this.if = true;
        this.long = null;
        this.else = false;
        this.p = p;
        this.c = p.d;
        this.long = this.p.E;
        this.do();
    }
    
    public boolean a(final String s) {
        if (s == null || s.length() < 1) {
            return false;
        }
        if (this.g.length() > 32) {
            this.g = "";
        }
        this.g += s;
        return true;
    }
    
    public void a(final int n) {
        if (n > 0) {
            this.a(true);
        }
        else {
            this.a(false);
        }
    }
    
    public void a() {
        this.a(false);
    }
    
    public void a(final boolean b) {
        try {
            this.n = false;
            if (this.void != null) {
                try {
                    this.void.stop();
                }
                catch (Exception ex2) {}
                try {
                    if (b || this.c.c()) {
                        this.void.close();
                    }
                }
                catch (Exception ex3) {}
                this.void = null;
            }
            this.n = false;
        }
        catch (Exception ex) {
            this.c.a(2, "audiorecorder.CloseLine", ex);
        }
    }
    
    public boolean case() {
        try {
            this.c.a(3, "EVENT,reopen audio recorder with samplerate " + this.c.if(this.m));
            this.a();
            boolean b = false;
            for (int i = 0; i < 3; ++i) {
                if (this.a(this.p, false, i, true)) {
                    b = true;
                    break;
                }
            }
            if (!b && this.m > 9000.0f) {
                this.try = false;
                this.c.bl = false;
                this.m = 8000.0f;
                for (int j = 0; j < 3; ++j) {
                    if (this.a(this.p, false, j, true)) {
                        b = true;
                        break;
                    }
                }
            }
            return b;
        }
        catch (Exception ex) {
            this.c.a(2, "audiorecorder.ReOpen", ex);
            return false;
        }
    }
    
    public boolean byte() {
        boolean b = false;
        try {
            for (int i = 0; i < 3; ++i) {
                if (this.a(null, true, i, false)) {
                    b = true;
                    break;
                }
            }
        }
        catch (Exception ex) {
            this.c.a(2, "audiorecorder.OpenCheck", ex);
        }
        return b;
    }
    
    public void for() {
        if (this.c.eK < 4) {
            return;
        }
        this.c.a(4, "EVENT,supported recording info:");
        final Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
        final Line.Info info = new Line.Info((a0.class$javax$sound$sampled$TargetDataLine == null) ? (a0.class$javax$sound$sampled$TargetDataLine = class$("javax.sound.sampled.TargetDataLine")) : a0.class$javax$sound$sampled$TargetDataLine);
        for (int i = 0; i < mixerInfo.length; ++i) {
            final Mixer mixer = AudioSystem.getMixer(mixerInfo[i]);
            if (mixer != null) {
                if (info != null) {
                    if (mixer.isLineSupported(info)) {
                        this.c.a(4, "EVENT,   recorder " + mixerInfo[i].getName() + ": " + mixerInfo[i].getDescription() + " " + this.c.c(mixer.getMaxLines(info)));
                    }
                }
            }
        }
    }
    
    public String a(final TargetDataLine targetDataLine) {
        if (this.c.eK < 4) {
            return "";
        }
        try {
            return targetDataLine.getLineInfo().toString();
        }
        catch (Exception ex) {
            this.c.a(3, "audiorecorder.GetLineInfo", ex);
            return "";
        }
    }
    
    public String a(final TargetDataLine targetDataLine, final Mixer mixer) {
        String string = "";
        if (this.c.eK < 4) {
            return string;
        }
        try {
            string = targetDataLine.getLineInfo().toString();
        }
        catch (Exception ex) {
            this.c.a(3, "audioplayer.GetLineInfo", ex);
        }
        return string;
    }
    
    public boolean a(final t p4, final boolean b, final int n, final boolean b2) {
        int n2 = 0;
        try {
            n2 = 1;
            if (p4 != null) {
                this.p = p4;
            }
            if (!b2 && (this.try || b)) {
                if ((this.e == this.c.ea || this.e == 106) && this.c.cL > 0 && this.m < 31000.0f) {
                    this.c.a(4, "EVENT,audio recorder set sample rate to 32000 before open");
                    this.m = 32000.0f;
                }
                else if ((this.e == this.c.s || this.e == 105) && this.c.dl > 0 && (this.m < 15000.0f || this.m > 17000.0f)) {
                    this.c.a(4, "EVENT,audio recorder set sample rate to 16000 before open");
                    this.m = 16000.0f;
                }
            }
            if (n > 1) {
                this.c.aL = true;
            }
            this.new = this.c.a(this.e, true);
            Label_0238: {
                if (!this.try && !b) {
                    final int new1 = this.new;
                    final aw c = this.c;
                    if (new1 != 4) {
                        final int new2 = this.new;
                        final aw c2 = this.c;
                        if (new2 != 5) {
                            break Label_0238;
                        }
                    }
                    final aw c3 = this.c;
                    this.new = 1;
                }
            }
            this.int = this.c.else(this.new);
            this.l = this.c.d(this.new);
            this.for = this.c.case(this.new);
            this.case = this.c.do(this.new);
            this.do = 8 * this.int * this.c.for(this.new);
            final boolean b3 = true;
            n2 = 2;
            n2 = 3;
            this.c.a(3, "EVENT,open audio recorder " + this.c.try);
            this.a(n);
            this.k = new AudioFormat(this.m, 16, b3 ? 1 : 0, true, false);
            n2 = 4;
            int n3 = 0;
            DataLine.Info info;
            if (n < 1) {
                info = new DataLine.Info((a0.class$javax$sound$sampled$TargetDataLine == null) ? (a0.class$javax$sound$sampled$TargetDataLine = class$("javax.sound.sampled.TargetDataLine")) : a0.class$javax$sound$sampled$TargetDataLine, this.k, this.int * this.l * this.c.av);
            }
            else {
                info = new DataLine.Info((a0.class$javax$sound$sampled$TargetDataLine == null) ? (a0.class$javax$sound$sampled$TargetDataLine = class$("javax.sound.sampled.TargetDataLine")) : a0.class$javax$sound$sampled$TargetDataLine, this.k);
            }
            Label_1583: {
                if (this.c.try.length() >= 1 && (!this.c.try.equals("Default") || n > 1)) {
                    if (this.c.try.equals("Default") || n <= 1) {
                        n2 = 109;
                        try {
                            final Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
                            int i = 0;
                            while (i < mixerInfo.length) {
                                if (mixerInfo[i].getName().equals(this.c.try) || i == mixerInfo.length - 1) {
                                    final Mixer mixer = AudioSystem.getMixer(mixerInfo[i]);
                                    if (mixer == null) {
                                        break;
                                    }
                                    n2 = 110;
                                    this.void = (TargetDataLine)mixer.getLine(info);
                                    if (this.void != null) {
                                        if (n < 1) {
                                            n2 = 111;
                                            this.void.open(this.k, this.int * this.l * this.c.av);
                                        }
                                        else {
                                            n2 = 104;
                                            this.void.open(this.k);
                                        }
                                        n2 = 112;
                                        if (!b) {
                                            this.new();
                                            this.void.start();
                                            this.c.a(3, "EVENT,audiorecorder opened from 3 " + this.a(this.void, mixer));
                                        }
                                        n2 = 113;
                                        n3 = 1;
                                        break;
                                    }
                                    break;
                                }
                                else {
                                    ++i;
                                }
                            }
                        }
                        catch (Exception ex) {
                            this.c.a(2, "audiorecorder.Openpreferred (" + this.c.c(n2) + ")", ex);
                            if (this.if) {
                                this.if = false;
                                this.for();
                            }
                        }
                        break Label_1583;
                    }
                }
                try {
                    n2 = 5;
                    this.void = (TargetDataLine)AudioSystem.getLine(info);
                    if (n < 1) {
                        n2 = 105;
                        this.void.open(this.k, this.int * this.l * this.c.av);
                    }
                    else {
                        n2 = 106;
                        this.void.open(this.k);
                    }
                    n2 = 107;
                    if (!b) {
                        this.new();
                        this.void.start();
                        this.c.a(3, "EVENT,audiorecorder opened from 1 " + this.a(this.void));
                    }
                    n3 = 1;
                    n2 = 108;
                }
                catch (Exception ex2) {
                    this.c.a(2, "audiorecorder.Opendefault (" + this.c.c(n2) + ")", ex2);
                    if (this.if) {
                        this.if = false;
                        this.for();
                    }
                }
                if (n3 == 0 && this.long.ringtone != null) {
                    try {
                        this.c.dI = true;
                        this.c.a(3, "EVENT,stop ringplayback");
                        for (int n4 = 0; n4 < 5 && this.long.ringtone != null; ++n4) {
                            Thread.sleep(50L);
                        }
                    }
                    catch (Exception ex13) {}
                }
                if (n3 == 0) {
                    try {
                        n2 = 2005;
                        this.void = (TargetDataLine)AudioSystem.getLine(info);
                        n2 = 2106;
                        this.void.open(this.k);
                        n2 = 2107;
                        if (!b) {
                            this.new();
                            this.void.start();
                            this.c.a(3, "EVENT,audiorecorder opened from 2 " + this.a(this.void));
                        }
                        n3 = 1;
                        n2 = 2108;
                    }
                    catch (Exception ex3) {
                        this.c.a(2, "audiorecorder.OpendefaultEx2 (" + this.c.c(n2) + ")", ex3);
                        if (this.if) {
                            this.if = false;
                            this.for();
                        }
                    }
                }
                if (n3 == 0) {
                    try {
                        final Mixer.Info[] mixerInfo2 = AudioSystem.getMixerInfo();
                        for (int j = 0; j < mixerInfo2.length; ++j) {
                            try {
                                if (mixerInfo2[j].getName().equals("Primary Sound Driver") || mixerInfo2[j].getName().equals("Java Sound Audio Engine")) {
                                    final Mixer mixer2 = AudioSystem.getMixer(mixerInfo2[j]);
                                    if (mixer2 != null) {
                                        n2 = 40110;
                                        this.void = (TargetDataLine)mixer2.getLine(info);
                                        if (this.void != null) {
                                            this.void.open(this.k);
                                            n2 = 40112;
                                            if (!b) {
                                                this.new();
                                                this.void.start();
                                                this.c.a(3, "EVENT,audiorecorder opened from 21 " + this.a(this.void, mixer2));
                                            }
                                            n2 = 40113;
                                            n3 = 1;
                                            break;
                                        }
                                    }
                                }
                            }
                            catch (Exception ex4) {
                                this.c.a(2, "audiorecorder.Openpreferred 232 (" + this.c.c(n2) + ")", ex4);
                                if (this.if) {
                                    this.if = false;
                                    this.for();
                                }
                            }
                        }
                    }
                    catch (Exception ex5) {
                        this.c.a(2, "audiorecorder.Openpreferred 22 (" + this.c.c(n2) + ")", ex5);
                        if (this.if) {
                            this.if = false;
                            this.for();
                        }
                    }
                }
            }
            if (n3 == 0 && b) {
                this.c.a(3, "ERROR,cannot open audio recorder device with high sample rate checkonly");
                this.a(n);
                return false;
            }
            if (n3 == 0 && this.long.ringtone != null) {
                try {
                    this.c.dI = true;
                    this.c.a(3, "EVENT,stop ringplayback");
                    for (int n5 = 0; n5 < 5 && this.long.ringtone != null; ++n5) {
                        Thread.sleep(50L);
                    }
                }
                catch (Exception ex14) {}
            }
            if (n3 == 0 && this.m > 9000.0f) {
                this.c.a(2, "ERROR,cannot open audio recorder device with high sample rate");
                this.m = 8000.0f;
                this.try = false;
                this.c.bl = false;
                this.a(n);
                return false;
            }
            if (n3 == 0 && !b) {
                info = new DataLine.Info((a0.class$javax$sound$sampled$TargetDataLine == null) ? (a0.class$javax$sound$sampled$TargetDataLine = class$("javax.sound.sampled.TargetDataLine")) : a0.class$javax$sound$sampled$TargetDataLine, this.k);
                Label_2386: {
                    if (this.c.try.length() >= 1 && (!this.c.try.equals("Default") || n > 1)) {
                        if (this.c.try.equals("Default") || n <= 1) {
                            n2 = 50109;
                            try {
                                final Mixer.Info[] mixerInfo3 = AudioSystem.getMixerInfo();
                                int k = 0;
                                while (k < mixerInfo3.length) {
                                    if (mixerInfo3[k].getName().equals(this.c.try) || k == mixerInfo3.length - 1) {
                                        final Mixer mixer3 = AudioSystem.getMixer(mixerInfo3[k]);
                                        if (mixer3 == null) {
                                            break;
                                        }
                                        n2 = 50110;
                                        this.void = (TargetDataLine)mixer3.getLine(info);
                                        if (this.void != null) {
                                            n2 = 50104;
                                            this.void.open(this.k);
                                            n2 = 5012;
                                            if (!b) {
                                                this.new();
                                                this.void.start();
                                                this.c.a(3, "EVENT,audiorecorder opened from 6 " + this.a(this.void, mixer3));
                                            }
                                            n2 = 50113;
                                            n3 = 1;
                                            break;
                                        }
                                        break;
                                    }
                                    else {
                                        ++k;
                                    }
                                }
                            }
                            catch (Exception ex6) {
                                this.c.a(2, "audiorecorder.Openpreferred5 (" + this.c.c(n2) + ")", ex6);
                                if (this.if) {
                                    this.if = false;
                                    this.for();
                                }
                            }
                            break Label_2386;
                        }
                    }
                    try {
                        n2 = 50005;
                        this.void = (TargetDataLine)AudioSystem.getLine(info);
                        n2 = 50106;
                        this.void.open(this.k);
                        n2 = 50107;
                        if (!b) {
                            this.new();
                            this.void.start();
                            this.c.a(3, "EVENT,audiorecorder opened from 4 " + this.a(this.void));
                        }
                        n3 = 1;
                        n2 = 50108;
                    }
                    catch (Exception ex7) {
                        this.c.a(2, "audiorecorder.Opendefault5 (" + this.c.c(n2) + ")", ex7);
                        if (this.if) {
                            this.if = false;
                            this.for();
                        }
                    }
                    if (n3 == 0) {
                        try {
                            n2 = 52005;
                            this.void = (TargetDataLine)AudioSystem.getLine(info);
                            n2 = 52106;
                            this.void.open(this.k);
                            n2 = 52107;
                            if (!b) {
                                this.new();
                                this.void.start();
                                this.c.a(3, "EVENT,audiorecorder opened from 5 " + this.a(this.void));
                            }
                            n3 = 1;
                            n2 = 52108;
                        }
                        catch (Exception ex8) {
                            this.c.a(2, "audiorecorder.OpendefaultEx5 (" + this.c.c(n2) + ")", ex8);
                        }
                    }
                }
                if (n3 == 0) {
                    try {
                        n2 = 60114;
                        this.void = (TargetDataLine)AudioSystem.getLine(info);
                        n2 = 60115;
                        this.void.open(this.k);
                        n2 = 60116;
                        if (!b) {
                            this.new();
                            this.void.start();
                            this.c.a(3, "EVENT,audiorecorder opened from 7 " + this.a(this.void));
                        }
                        n3 = 1;
                        n2 = 60117;
                    }
                    catch (Exception ex9) {
                        this.c.a(2, "audiorecorder.Opendefault52 (" + this.c.c(n2) + ")", ex9);
                    }
                }
            }
            if (n3 == 0 && !b) {
                try {
                    final Mixer.Info[] mixerInfo4 = AudioSystem.getMixerInfo();
                    for (int l = 0; l < mixerInfo4.length; ++l) {
                        try {
                            final Mixer mixer4 = AudioSystem.getMixer(mixerInfo4[l]);
                            if (mixer4 != null) {
                                n2 = 2110;
                                this.void = (TargetDataLine)mixer4.getLine(info);
                                if (this.void != null) {
                                    n2 = 2111;
                                    this.void.open(this.k);
                                    n2 = 2112;
                                    if (!b) {
                                        this.new();
                                        this.void.start();
                                        this.c.a(3, "EVENT,audiorecorder opened from 8 " + this.a(this.void));
                                    }
                                    n2 = 2113;
                                    n3 = 1;
                                    break;
                                }
                            }
                        }
                        catch (Exception ex10) {
                            this.c.a(3, "audiorecorder.OpenAllIntern (" + this.c.c(l) + ":" + this.c.c(n2) + ")", ex10);
                        }
                    }
                }
                catch (Exception ex11) {
                    this.c.a(3, "audiorecorder.OpenAllExtern (" + this.c.c(n2) + ")", ex11);
                }
            }
            if (n3 == 0) {
                if (b) {
                    this.c.a(3, "WARNING,cannot open audio recorder device checkonly");
                }
                else {
                    this.c.a(1, "ERROR,cannot open audio recorder device");
                }
                this.for();
                this.a(n);
                return false;
            }
            this.n = true;
            n2 = 9;
            if (this.void != null && this.c.eK >= 3) {
                n2 = 10;
                this.k = this.void.getFormat();
                if (this.k != null) {
                    this.c.a(3, "EVENT, audio in opened as " + this.k.toString());
                }
                n2 = 11;
            }
            return true;
        }
        catch (Exception ex12) {
            this.c.a(1, "audiorecorder.Open (" + this.c.c(n2) + ")", ex12);
            this.a(n);
            return false;
        }
    }
    
    public void try() {
        this.a = true;
        this.if();
        this.a = true;
    }
    
    public boolean if() {
        try {
            this.p = null;
            this.a();
            return true;
        }
        catch (Exception ex) {
            this.c.a(2, "audiorecorder.Close", ex);
            return false;
        }
    }
    
    public void new() {
    }
    
    public float a(final float n) {
        try {
            if (this.void == null) {
                return n;
            }
            final FloatControl floatControl = (FloatControl)this.void.getControl(FloatControl.Type.VOLUME);
            if (floatControl != null) {
                return floatControl.getValue();
            }
            final FloatControl floatControl2 = (FloatControl)this.void.getControl(FloatControl.Type.MASTER_GAIN);
            if (floatControl2 != null) {
                return floatControl2.getValue();
            }
        }
        catch (Exception ex) {
            this.c.a(3, "audiorecorder.GetVolume ", ex);
        }
        return n;
    }
    
    public void int() {
    }
    
    public void run() {
        int n = 0;
        try {
            try {
                this.setPriority(10);
            }
            catch (Exception ex4) {}
            n = 1;
            final int n2 = this.int * this.l;
            final byte[] array = new byte[n2 * 2 + 32];
            final byte[] array2 = new byte[n2 * 2 + 32];
            final byte[] array3 = new byte[n2 * 2 + 32];
            n = 2;
            int n3 = 1;
            this.c.a(4, "EVENT, audio buff size is " + this.c.c(this.void.getBufferSize()));
            while (!this.a) {
                n = 3;
                if (!this.n) {
                    Thread.sleep(0L, 1);
                }
                else if (n3 == 0 && this.c.j != 1 && this.void.available() < n2) {
                    if (this.c.j == 0 || this.c.j == 4) {
                        Thread.sleep(2L, 0);
                    }
                    else if (this.c.j == 2) {
                        yield();
                    }
                    else if (this.c.j == 3) {
                        Thread.sleep(0L, 1);
                    }
                    else if (this.c.j == 5) {
                        yield();
                        Thread.sleep(2L, 0);
                    }
                    else if (this.c.j == 6) {
                        yield();
                        Thread.sleep(10L, 0);
                    }
                    else {
                        Thread.sleep(2L, 0);
                    }
                }
                else {
                    if (n3 != 0) {
                        n3 = 0;
                    }
                    int read = this.void.read(array, 0, n2);
                    n = 4;
                    if (read < 80) {
                        continue;
                    }
                    if (!this.n) {
                        continue;
                    }
                    if (read % 2 == 1) {
                        --read;
                    }
                    n = 5;
                    if ((this.c.d7 && this.c.cu) || (this.p != null && (this.p.aI == 2 || this.p.aI == 4))) {
                        for (int i = 0; i < read; ++i) {
                            array[i] = 0;
                        }
                    }
                    if (this.else) {
                        this.else = false;
                        this.case();
                    }
                    else {
                        if (this.c.fo > 0 && this.p != null && this.p.s != null && this.m == this.p.s.long) {
                            try {
                                if (this.p.z != null && this.m != this.p.z.X) {
                                    this.p.z = null;
                                }
                                if (this.p.z == null) {
                                    this.c.a(4, "EVENT, creating aec on rec with samplerate " + this.c.c((int)this.m));
                                    if (this.p.z == null) {
                                        this.p.z = new an(this.p);
                                        this.p.z.X = this.m;
                                    }
                                }
                                if (!this.p.z.a(array, read)) {
                                    continue;
                                }
                            }
                            catch (Exception ex) {
                                if (this.c.eK > 4) {
                                    this.c.a(3, "audiorecorder.aec (" + this.c.c(n) + ")", ex);
                                }
                            }
                        }
                        if (this.c.aW != 1.0f) {
                            try {
                                for (int j = 0; j < read / 2; ++j) {
                                    final int round = Math.round(this.c.aW * ah.a(array, j * 2, false));
                                    short n4;
                                    if (round < -32768) {
                                        n4 = -32768;
                                    }
                                    else if (round > 32767) {
                                        n4 = 32767;
                                    }
                                    else {
                                        n4 = (short)round;
                                    }
                                    ah.a(n4, array, j * 2, false);
                                }
                            }
                            catch (Exception ex2) {
                                this.c.a(3, "audiorecorder.setvolume (" + this.c.c(n) + ")", ex2);
                            }
                        }
                        if (this.p != null) {
                            this.p.a(true, array, read);
                        }
                        n = 6;
                        if (this.p != null && this.p.af > 0) {
                            if (this.e == this.c.s || this.e == this.c.ea) {
                                this.c.a(4, "WARNING, wideband rec codec on conf");
                                this.e = 0;
                            }
                            if (this.m > 9000.0f) {
                                this.m = 8000.0f;
                                this.case();
                                this.c.a(4, "WARNING, wideband rec samplerate on conf");
                            }
                            else {
                                if (!this.n || (this.c.cu && !this.c.d7)) {
                                    continue;
                                }
                                this.p.a("127.0.0.1", 3, array, read);
                            }
                        }
                        else {
                            int n5;
                            if (this.e == 8) {
                                if (this.m > 9000.0f) {
                                    this.m = 8000.0f;
                                    this.case();
                                    continue;
                                }
                                n5 = read / 2;
                                ah.if(array, 0, array2, 0, n5, false);
                            }
                            else if (this.e == 0) {
                                if (this.m > 9000.0f) {
                                    this.m = 8000.0f;
                                    this.case();
                                    continue;
                                }
                                n5 = read / 2;
                                ah.try(array, 0, array2, 0, n5, false);
                            }
                            else if (this.e == 3 && this.c.e5 > 0) {
                                if (this.m > 9000.0f) {
                                    this.m = 8000.0f;
                                    this.case();
                                    continue;
                                }
                                if (this.d == null) {
                                    this.d = new a4(this.c);
                                }
                                n5 = this.d.if(array, read, array2);
                            }
                            else if (this.e == 18 && this.c.bY > 0) {
                                if (this.m > 9000.0f) {
                                    this.m = 8000.0f;
                                    this.case();
                                    continue;
                                }
                                if (this.j == null) {
                                    (this.j = new a(this.c)).a(true);
                                }
                                n5 = this.j.if(array, read, array2);
                            }
                            else if (this.e == this.c.p && this.c.bv > 0) {
                                if (this.m > 9000.0f) {
                                    this.m = 8000.0f;
                                    this.case();
                                    continue;
                                }
                                if (this.o == null) {
                                    (this.o = new f(this.c)).a(true);
                                }
                                n5 = this.o.if(array, read, array2);
                            }
                            else if (this.e == this.c.T && this.c.z > 0) {
                                if (this.m > 9000.0f) {
                                    this.m = 8000.0f;
                                    this.case();
                                    continue;
                                }
                                if (this.goto == null) {
                                    (this.goto = new b()).a(0, 3, 8000, 1);
                                    this.goto.int().int(3);
                                }
                                this.goto.a(array, 0, read);
                                n5 = this.goto.a(array2, 0);
                            }
                            else if (this.e == this.c.s && this.c.dl > 0 && this.try) {
                                if (this.m < 15000.0f || this.m > 17000.0f) {
                                    this.m = 16000.0f;
                                    this.case();
                                    continue;
                                }
                                if (this.char == null) {
                                    (this.char = new b()).a(1, 6, 16000, 1);
                                    this.char.int().int(3);
                                }
                                this.char.a(array, 0, read);
                                n5 = this.char.a(array2, 0);
                            }
                            else if (this.e == this.c.ea && this.c.cL > 0 && this.try) {
                                if (this.m < 31000.0f) {
                                    this.m = 32000.0f;
                                    this.case();
                                    continue;
                                }
                                if (this.byte == null) {
                                    (this.byte = new b()).a(2, 8, 32000, 1);
                                    this.byte.int().int(3);
                                }
                                this.byte.a(array, 0, read);
                                n5 = this.byte.a(array2, 0);
                                if (n5 < this.for) {
                                    continue;
                                }
                                if (!this.n) {
                                    continue;
                                }
                            }
                            else if (this.e == 104 && this.c.z > 0) {
                                if (this.m > 9000.0f) {
                                    this.m = 8000.0f;
                                    this.case();
                                    continue;
                                }
                                if (this.goto == null) {
                                    (this.goto = new b()).a(0, 3, 8000, 1);
                                    this.goto.int().int(3);
                                }
                                this.goto.a(array, 0, read);
                                n5 = this.goto.a(array2, 0);
                            }
                            else if (this.e == 105 && this.c.dl > 0 && this.try) {
                                if (this.m < 15000.0f || this.m > 17000.0f) {
                                    this.m = 16000.0f;
                                    this.case();
                                    continue;
                                }
                                if (this.char == null) {
                                    (this.char = new b()).a(1, 6, 16000, 1);
                                    this.char.int().int(3);
                                }
                                this.char.a(array, 0, read);
                                n5 = this.char.a(array2, 0);
                            }
                            else if (this.e == 106 && this.c.cL > 0 && this.try) {
                                if (this.m < 31000.0f) {
                                    this.m = 32000.0f;
                                    this.case();
                                    continue;
                                }
                                if (this.byte == null) {
                                    (this.byte = new b()).a(2, 8, 32000, 1);
                                    this.byte.int().int(3);
                                }
                                this.byte.a(array, 0, read);
                                n5 = this.byte.a(array2, 0);
                                if (n5 < this.for) {
                                    continue;
                                }
                                if (!this.n) {
                                    continue;
                                }
                            }
                            else {
                                n5 = read / 2;
                                ah.try(array, 0, array2, 0, n5, false);
                            }
                            if (n5 < this.for) {
                                continue;
                            }
                            if (!this.n) {
                                continue;
                            }
                            final t p = this.p;
                            ++p.V;
                            if (this.p.V > 65533) {
                                this.p.V = 2;
                            }
                            final int n6 = 2;
                            final boolean b = false;
                            final boolean b2 = false;
                            final boolean b3 = false;
                            boolean b4 = false;
                            final boolean b5 = false;
                            if (this.g.length() > 0) {
                                if (this.i == 0 && (this.f == 0L || this.c.do() > this.f)) {
                                    b4 = true;
                                    final t p2 = this.p;
                                    p2.bs += this.do;
                                    if (this.f != 0L) {
                                        this.f = 0L;
                                    }
                                }
                                else if (this.f != 0L && this.c.do() <= this.f) {
                                    final t p3 = this.p;
                                    p3.bs += this.do;
                                }
                            }
                            else {
                                final t p4 = this.p;
                                p4.bs += this.do;
                            }
                            final int n7 = ((((((b5 ? 1 : 0) | n6) << 1 | (b ? 1 : 0)) << 1 | (b2 ? 1 : 0)) << 4 | (b3 ? 1 : 0)) << 1 | (b4 ? 1 : 0)) << 7;
                            int n8;
                            if (this.g.length() > 0 && (this.f == 0L || this.c.do() > this.f)) {
                                n8 = (n7 | this.c.fj);
                            }
                            else {
                                n8 = (n7 | this.e);
                            }
                            final byte[] for1 = r.for(n8 << 16 | this.p.V);
                            final byte[] for2 = r.for((int)this.p.bs);
                            final byte[] for3 = r.for((int)this.p.aX);
                            final int n9 = 0;
                            System.arraycopy(for1, 0, array3, n9, for1.length);
                            final int n10 = n9 + for1.length;
                            System.arraycopy(for2, 0, array3, n10, for2.length);
                            final int n11 = n10 + for2.length;
                            System.arraycopy(for3, 0, array3, n11, for3.length);
                            int n12 = n11 + for3.length;
                            if (this.g.length() > 0 && (this.f == 0L || this.c.do() > this.f)) {
                                final String trim = this.g.substring(0, 1).trim();
                                if (trim.length() < 1) {
                                    this.f = this.c.do() + 1600L;
                                    this.i = 0;
                                    if (this.g.length() > 1) {
                                        this.g = this.g.substring(1);
                                    }
                                    else {
                                        this.g = "";
                                    }
                                }
                                else {
                                    this.f = 0L;
                                    array3[n12] = this.c.case(trim);
                                    ++n12;
                                    int n13 = 7;
                                    if (this.i >= 6) {
                                        n13 |= 0x80;
                                    }
                                    array3[n12] = (byte)n13;
                                    ++n12;
                                    int n14 = this.do * this.i;
                                    if (this.i >= 6) {
                                        n14 = this.do * 5;
                                    }
                                    array3[n12] = (byte)(n14 >> 8);
                                    ++n12;
                                    array3[n12] = (byte)(n14 & 0xFF);
                                    ++n12;
                                    ++this.i;
                                    if (this.i >= 8) {
                                        this.i = 0;
                                        if (this.g.length() > 1) {
                                            this.g = this.g.substring(1);
                                        }
                                        else {
                                            this.g = "";
                                        }
                                    }
                                    n = 8;
                                    if (!this.n) {
                                        continue;
                                    }
                                    if (this.p != null) {
                                        this.p.a("127.0.0.1", 3, array3, n12);
                                    }
                                    n = 9;
                                }
                            }
                            else {
                                System.arraycopy(array2, 0, array3, n12, n5);
                                n = 78;
                                if (!this.n) {
                                    continue;
                                }
                                if ((!this.c.cu || this.c.d7) && this.p != null) {
                                    this.p.a("127.0.0.1", 3, array3, n5 + n12);
                                }
                                n = 79;
                            }
                        }
                    }
                }
            }
            n = 10;
        }
        catch (Exception ex3) {
            this.c.a(3, "audiorecorder.run (" + this.c.c(n) + ")", ex3);
        }
        this.a = true;
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
