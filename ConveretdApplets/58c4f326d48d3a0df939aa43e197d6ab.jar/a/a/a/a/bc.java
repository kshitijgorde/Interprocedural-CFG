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
    Clip aC;
    FloatControl aD;
    FloatControl az;
    AudioInputStream aE;
    long aA;
    float aB;
    static /* synthetic */ Class class$0;
    
    public bc() {
        this.aD = null;
        this.az = null;
        this.aE = null;
        this.aA = 0L;
        this.aB = 1.0f;
    }
    
    public void if() {
        super.if();
        this.aD = null;
        this.az = null;
        if (this.aC != null) {
            this.aC.close();
        }
        this.aC = null;
        if (super.long != null) {
            super.long.a();
        }
        super.long = null;
    }
    
    public void a(final String s, final ae ae, final ac b, final boolean goto1, final boolean try1) {
        super.b = b;
        super.long = super.b.B.a(s, ae, false, false, false);
        super.goto = goto1;
        super.try = try1;
        AudioSystem.getMixer(null);
    }
    
    public boolean a(final long n) {
        if (super.long.k != 0) {
            super.long = super.long.try[0];
        }
        if (super.long.b && !super.aw) {
            try {
                this.aE = AudioSystem.getAudioInputStream(new ByteArrayInputStream(super.long.l));
                AudioFormat format = this.aE.getFormat();
                if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                    format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), format.getSampleSizeInBits() * 2, format.getChannels(), format.getFrameSize() * 2, format.getFrameRate(), true);
                    this.aE = AudioSystem.getAudioInputStream(format, this.aE);
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
                (this.aC = (Clip)AudioSystem.getLine(new DataLine.Info(class$0, this.aE.getFormat(), (int)this.aE.getFrameLength() * format.getFrameSize()))).open(this.aE);
            }
            catch (Exception ex2) {}
            if (this.aC.isControlSupported(FloatControl.Type.PAN)) {
                this.az = (FloatControl)this.aC.getControl(FloatControl.Type.PAN);
            }
            else if (this.aC.isControlSupported(FloatControl.Type.BALANCE)) {
                this.az = (FloatControl)this.aC.getControl(FloatControl.Type.BALANCE);
            }
            else {
                this.az = null;
            }
            if (this.aC.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                this.aD = (FloatControl)this.aC.getControl(FloatControl.Type.MASTER_GAIN);
            }
            else if (this.aC.isControlSupported(FloatControl.Type.VOLUME)) {
                this.aD = (FloatControl)this.aC.getControl(FloatControl.Type.VOLUME);
            }
            this.a(super.ax);
            super.aw = true;
        }
        else if (super.goto && super.c && this.aC != null) {
            super.c = false;
            if (!super.ay) {
                return false;
            }
            this.aC.start();
            super.au = true;
            if (super.av) {
                this.aC.loop(-1);
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
        if (this.az != null) {
            this.az.setValue(value);
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
        this.aB = (float)(Math.log(n4) / Math.log(10.0) * 20.0);
        this.aD.setValue(this.aB * super.ax);
    }
    
    public void a(final float ax) {
        super.ax = ax;
        if (this.aD == null) {
            return;
        }
        if (this.aD.getType() == FloatControl.Type.VOLUME) {
            this.aD.setValue(this.aB * super.ax);
        }
        else if (this.aD.getType() == FloatControl.Type.MASTER_GAIN) {
            float value = (float)(Math.log(this.aB * super.ax) / Math.log(10.0) * 20.0);
            if (value > this.aD.getMaximum()) {
                value = this.aD.getMaximum();
            }
            else if (value < this.aD.getMinimum()) {
                value = this.aD.getMinimum();
            }
            this.aD.setValue(value);
        }
    }
    
    public void else() {
        try {
            if (super.ay && super.av) {
                return;
            }
            super.ay = true;
            if (this.aC == null) {
                return;
            }
            if (super.av) {
                this.aC.close();
                this.aE.reset();
                this.aC.open(this.aE);
                this.aC.start();
                this.aC.loop(-1);
            }
            else {
                if (this.aC.getMicrosecondPosition() >= this.aC.getMicrosecondLength() - 1L) {
                    this.aC.close();
                    this.aE.reset();
                    this.aC.open(this.aE);
                }
                this.aC.start();
            }
        }
        catch (Exception ex) {}
        super.au = true;
    }
    
    public void long() {
        super.ay = false;
        if (this.aC == null) {
            return;
        }
        this.aC.stop();
        super.au = false;
    }
    
    public void goto() {
        try {
            super.ay = false;
            if (this.aC == null) {
                return;
            }
            this.aC.close();
            this.aE.reset();
            this.aC.open(this.aE);
        }
        catch (Exception ex) {}
        super.au = false;
    }
    
    public void for(final boolean av) {
        if (this.aC == null) {
            return;
        }
        if (av) {
            this.aC.loop(-1);
        }
        else {
            this.aC.loop(0);
        }
        super.av = av;
    }
    
    public void int(final boolean b) {
        if (!b) {
            if (super.au) {
                this.long();
                super.au = true;
            }
        }
        else if (super.au) {
            this.else();
        }
    }
}
