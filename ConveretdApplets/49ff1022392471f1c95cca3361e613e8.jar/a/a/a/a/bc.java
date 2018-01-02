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
    ae goto;
    Clip as;
    FloatControl at;
    FloatControl ap;
    AudioInputStream au;
    long aq;
    float ar;
    static /* synthetic */ Class class$0;
    
    public bc() {
        this.goto = null;
        this.at = null;
        this.ap = null;
        this.au = null;
        this.aq = 0L;
        this.ar = 1.0f;
    }
    
    public void a(final String s, final ae ae, final ac void1, final boolean else1, final boolean new1) {
        super.void = void1;
        this.goto = super.void.w.a(s, ae, false, false, false);
        super.else = else1;
        super.new = new1;
        AudioSystem.getMixer(null);
    }
    
    public boolean a(final long n) {
        if (this.goto.j != 0) {
            this.goto = this.goto.new[0];
        }
        if (this.goto.b && !super.am) {
            try {
                this.au = AudioSystem.getAudioInputStream(new ByteArrayInputStream(this.goto.k));
                AudioFormat format = this.au.getFormat();
                if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                    format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), format.getSampleSizeInBits() * 2, format.getChannels(), format.getFrameSize() * 2, format.getFrameRate(), true);
                    this.au = AudioSystem.getAudioInputStream(format, this.au);
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
                (this.as = (Clip)AudioSystem.getLine(new DataLine.Info(class$0, this.au.getFormat(), (int)this.au.getFrameLength() * format.getFrameSize()))).open(this.au);
            }
            catch (Exception ex2) {}
            if (this.as.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                this.at = (FloatControl)this.as.getControl(FloatControl.Type.MASTER_GAIN);
            }
            if (this.as.isControlSupported(FloatControl.Type.PAN)) {
                this.ap = (FloatControl)this.as.getControl(FloatControl.Type.PAN);
            }
            else if (this.as.isControlSupported(FloatControl.Type.BALANCE)) {
                this.ap = (FloatControl)this.as.getControl(FloatControl.Type.BALANCE);
            }
            else {
                this.ap = null;
            }
            super.am = true;
        }
        else if (super.else && super.b && this.as != null) {
            super.b = false;
            if (!super.ao) {
                return false;
            }
            this.as.start();
            super.ak = true;
            if (super.al) {
                this.as.loop(-1);
            }
        }
        return false;
    }
    
    public void if() {
        this.at = null;
        this.ap = null;
        if (this.as != null) {
            this.as.close();
        }
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
        if (this.ap != null) {
            this.ap.setValue(value);
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
        this.ar = (float)(Math.log(n4) / Math.log(10.0) * 20.0);
        this.at.setValue(this.ar * super.an);
    }
    
    public void a(final float an) {
        if (this.at == null) {
            return;
        }
        super.an = an;
        this.at.setValue(this.ar * super.an);
    }
    
    public void void() {
        try {
            if (super.ao && super.al) {
                return;
            }
            super.ao = true;
            if (this.as == null) {
                return;
            }
            if (super.al) {
                this.as.close();
                this.au.reset();
                this.as.open(this.au);
                this.as.start();
                this.as.loop(-1);
            }
            else {
                if (this.as.getMicrosecondPosition() >= this.as.getMicrosecondLength() - 1L) {
                    this.as.close();
                    this.au.reset();
                    this.as.open(this.au);
                }
                this.as.start();
            }
        }
        catch (Exception ex) {}
        super.ak = true;
    }
    
    public void c() {
        super.ao = false;
        if (this.as == null) {
            return;
        }
        this.as.stop();
        super.ak = false;
    }
    
    public void b() {
        try {
            super.ao = false;
            if (this.as == null) {
                return;
            }
            this.as.close();
            this.au.reset();
            this.as.open(this.au);
        }
        catch (Exception ex) {}
        super.ak = false;
    }
    
    public void for(final boolean al) {
        if (this.as == null) {
            return;
        }
        if (al) {
            this.as.loop(-1);
        }
        else {
            this.as.loop(0);
        }
        super.al = al;
    }
    
    public void int(final boolean b) {
        if (!b) {
            if (super.ak) {
                this.c();
                super.ak = true;
            }
        }
        else if (super.ak) {
            this.void();
        }
    }
}
