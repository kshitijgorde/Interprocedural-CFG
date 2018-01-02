import javax.sound.sampled.Mixer;
import java.awt.Component;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioFormat;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class268_Sub2 extends Class268
{
    private boolean aBoolean5162;
    private byte[] aByteArray5163;
    private AudioFormat anAudioFormat5164;
    private int anInt5165;
    private SourceDataLine aSourceDataLine5166;
    static Class aClass5167;
    
    Class268_Sub2() {
        this.aBoolean5162 = false;
    }
    
    @Override
    final void method3259() throws LineUnavailableException {
        try {
            this.aSourceDataLine5166.flush();
            if (this.aBoolean5162) {
                this.aSourceDataLine5166.close();
                this.aSourceDataLine5166 = null;
                (this.aSourceDataLine5166 = (SourceDataLine)AudioSystem.getLine(new DataLine.Info((Class268_Sub2.aClass5167 == null) ? (Class268_Sub2.aClass5167 = method3264("javax.sound.sampled.SourceDataLine")) : Class268_Sub2.aClass5167, this.anAudioFormat5164, this.anInt5165 << (Class151_Sub7.aBoolean5007 ? 2 : 1)))).open();
                this.aSourceDataLine5166.start();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final int method3258() {
        int n;
        try {
            n = this.anInt5165 - (this.aSourceDataLine5166.available() >> (Class151_Sub7.aBoolean5007 ? 2 : 1));
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return n;
    }
    
    @Override
    final void method3257() {
        int n = 256;
        if (Class151_Sub7.aBoolean5007) {
            n <<= 1;
        }
        for (int i = 0; i < n; ++i) {
            int n2 = this.anIntArray2005[i];
            if ((n2 + 8388608 & 0xFF000000) != 0x0) {
                n2 = (0x7FFFFF ^ n2 >> 31);
            }
            this.aByteArray5163[i * 2] = (byte)(n2 >> 8);
            this.aByteArray5163[i * 2 + 1] = (byte)(n2 >> 16);
        }
        this.aSourceDataLine5166.write(this.aByteArray5163, 0, n << 1);
    }
    
    @Override
    final void method3253(final Component component) {
        try {
            final Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
            if (mixerInfo != null) {
                final Mixer.Info[] array = mixerInfo;
                for (int i = 0; i < array.length; ++i) {
                    final Mixer.Info info = array[i];
                    if (null != info) {
                        final String name = info.getName();
                        if (null != name && ~name.toLowerCase().indexOf("soundmax") <= -1) {
                            this.aBoolean5162 = true;
                        }
                    }
                }
            }
            this.anAudioFormat5164 = new AudioFormat(Class64_Sub15.anInt3678, 16, Class151_Sub7.aBoolean5007 ? 2 : 1, true, false);
            this.aByteArray5163 = new byte[256 << (Class151_Sub7.aBoolean5007 ? 2 : 1)];
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method3250(final int anInt5165) throws LineUnavailableException {
        try {
            try {
                (this.aSourceDataLine5166 = (SourceDataLine)AudioSystem.getLine(new DataLine.Info((Class268_Sub2.aClass5167 == null) ? (Class268_Sub2.aClass5167 = method3264("javax.sound.sampled.SourceDataLine")) : Class268_Sub2.aClass5167, this.anAudioFormat5164, anInt5165 << (Class151_Sub7.aBoolean5007 ? 2 : 1)))).open();
                this.aSourceDataLine5166.start();
                this.anInt5165 = anInt5165;
            }
            catch (LineUnavailableException ex) {
                if (~Class98_Sub50.method1670((byte)116, anInt5165) == 0xFFFFFFFE) {
                    this.aSourceDataLine5166 = null;
                    throw ex;
                }
                this.method3250(Class48.method453(423660257, anInt5165));
            }
        }
        catch (RuntimeException ex2) {
            throw ex2;
        }
    }
    
    @Override
    final void method3262() {
        try {
            if (null != this.aSourceDataLine5166) {
                this.aSourceDataLine5166.close();
                this.aSourceDataLine5166 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static Class method3264(final String s) {
        Class<?> forName;
        try {
            forName = Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
        return forName;
    }
}
