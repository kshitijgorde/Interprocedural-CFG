// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import javax.sound.sampled.Control;
import javax.sound.sampled.Line;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Clip;

public class bc extends ai
{
    Clip aB;
    FloatControl aC;
    FloatControl ay;
    AudioInputStream aD;
    long az;
    float aA;
    static /* synthetic */ Class class$0;
    
    public bc() {
        this.aC = null;
        this.ay = null;
        this.aD = null;
        this.az = 0L;
        this.aA = 1.0f;
    }
    
    public void if() {
        super.if();
        this.aC = null;
        this.ay = null;
        if (this.aB != null) {
            this.aB.close();
        }
        this.aB = null;
        if (super.long != null) {
            super.long.a();
        }
        super.long = null;
    }
    
    public void a(final String s, final ae ae, final ac b, final boolean goto1, final boolean try1) {
        super.b = b;
        super.long = super.b.A.a(s, ae, false, false, false);
        super.goto = goto1;
        super.try = try1;
        AudioSystem.getMixer(null);
    }
    
    public boolean a(final long n) {
        if (super.long.k != 0) {
            super.long = super.long.try[0];
        }
        if (super.long.b && !super.av) {
            try {
                this.aD = AudioSystem.getAudioInputStream(new ByteArrayInputStream(super.long.l));
                AudioFormat format = this.aD.getFormat();
                if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                    format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), format.getSampleSizeInBits() * 2, format.getChannels(), format.getFrameSize() * 2, format.getFrameRate(), true);
                    this.aD = AudioSystem.getAudioInputStream(format, this.aD);
                }
                Class class$0;
                if ((class$0 = bc.class$0) == null) {
                    try {
                        class$0 = (bc.class$0 = Class.forName("javax.sound.sampled.Clip"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                (this.aB = (Clip)AudioSystem.getLine(new DataLine.Info(class$0, this.aD.getFormat(), (int)this.aD.getFrameLength() * format.getFrameSize()))).open(this.aD);
            }
            catch (Exception ex2) {}
            if (this.aB.isControlSupported(FloatControl.Type.PAN)) {
                this.ay = (FloatControl)this.aB.getControl(FloatControl.Type.PAN);
            }
            else if (this.aB.isControlSupported(FloatControl.Type.BALANCE)) {
                this.ay = (FloatControl)this.aB.getControl(FloatControl.Type.BALANCE);
            }
            else {
                this.ay = null;
            }
            if (this.aB.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                this.aC = (FloatControl)this.aB.getControl(FloatControl.Type.MASTER_GAIN);
            }
            else if (this.aB.isControlSupported(FloatControl.Type.VOLUME)) {
                this.aC = (FloatControl)this.aB.getControl(FloatControl.Type.VOLUME);
            }
            this.a(super.aw);
            super.av = true;
        }
        else if (super.goto && super.c && this.aB != null) {
            super.c = false;
            if (!super.ax) {
                return false;
            }
            this.aB.start();
            super.at = true;
            if (super.au) {
                this.aB.loop(-1);
            }
        }
        return false;
    }
    
    public void a(final float n, final float n2) {
        float n3 = n;
        if (n > 1.5707963267948966) {
            n3 = 3.1415927f - n;
        }
        else if (n < -1.5707963267948966) {
            n3 = -3.1415927f - n;
        }
        final float value = -n3 / 1.5707964f;
        if (this.ay != null) {
            this.ay.setValue(value);
        }
        float n4 = 1.0f;
        boolean b = false;
        if (n < -1.5707963267948966 || n > 1.5707963267948966) {
            b = true;
        }
        if (n2 > 1.5707963267948966 || n2 < -1.5707963267948966) {
            if (!b) {
                n4 = 1.0f - 0.3f * (-(float)Math.cos(n2) + (float)Math.cos(n));
            }
        }
        else if (b) {
            n4 = 1.0f - 0.3f * ((float)Math.cos(n2) + -(float)Math.cos(n));
        }
        this.aA = (float)(Math.log(n4) / Math.log(10.0) * 20.0);
        this.aC.setValue(this.aA * super.aw);
    }
    
    public void a(final float aw) {
        super.aw = aw;
        if (this.aC == null) {
            return;
        }
        this.aC.setValue(this.aA * super.aw);
    }
    
    public void void() {
        try {
            if (super.ax && super.au) {
                return;
            }
            super.ax = true;
            if (this.aB == null) {
                return;
            }
            if (super.au) {
                this.aB.close();
                this.aD.reset();
                this.aB.open(this.aD);
                this.aB.start();
                this.aB.loop(-1);
            }
            else {
                if (this.aB.getMicrosecondPosition() >= this.aB.getMicrosecondLength() - 1L) {
                    this.aB.close();
                    this.aD.reset();
                    this.aB.open(this.aD);
                }
                this.aB.start();
            }
        }
        catch (Exception ex) {}
        super.at = true;
    }
    
    public void c() {
        super.ax = false;
        if (this.aB == null) {
            return;
        }
        this.aB.stop();
        super.at = false;
    }
    
    public void b() {
        try {
            super.ax = false;
            if (this.aB == null) {
                return;
            }
            this.aB.close();
            this.aD.reset();
            this.aB.open(this.aD);
        }
        catch (Exception ex) {}
        super.at = false;
    }
    
    public void for(final boolean au) {
        if (this.aB == null) {
            return;
        }
        if (au) {
            this.aB.loop(-1);
        }
        else {
            this.aB.loop(0);
        }
        super.au = au;
    }
    
    public void int(final boolean b) {
        if (!b) {
            if (super.at) {
                this.c();
                super.at = true;
            }
        }
        else if (super.at) {
            this.void();
        }
    }
}
