// 
// Decompiled by Procyon v0.5.30
// 

package org.xiph.speex.spi;

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.spi.FormatConversionProvider;

public class SpeexFormatConvertionProvider extends FormatConversionProvider
{
    public AudioFormat.Encoding[] getSourceEncodings() {
        return new AudioFormat.Encoding[] { e.a, AudioFormat.Encoding.PCM_SIGNED };
    }
    
    public AudioFormat.Encoding[] getTargetEncodings() {
        return new AudioFormat.Encoding[] { e.b, e.c, e.d, e.e, e.f, e.g, e.h, e.i, e.j, e.k, e.l, e.m, e.n, e.o, e.p, e.q, e.r, e.s, e.t, e.u, e.v, e.w, AudioFormat.Encoding.PCM_SIGNED };
    }
    
    public AudioFormat.Encoding[] getTargetEncodings(final AudioFormat audioFormat) {
        if (audioFormat.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED)) {
            return new AudioFormat.Encoding[] { e.b, e.c, e.d, e.e, e.f, e.g, e.h, e.i, e.j, e.k, e.l, e.m, e.n, e.o, e.p, e.q, e.r, e.s, e.t, e.u, e.v, e.w };
        }
        if (audioFormat.getEncoding() instanceof e) {
            return new AudioFormat.Encoding[] { AudioFormat.Encoding.PCM_SIGNED };
        }
        return new AudioFormat.Encoding[0];
    }
    
    public AudioFormat[] getTargetFormats(final AudioFormat.Encoding encoding, final AudioFormat audioFormat) {
        if (audioFormat.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED) && encoding instanceof e) {
            if (audioFormat.getChannels() > 2 || audioFormat.getChannels() <= 0 || audioFormat.isBigEndian()) {
                return new AudioFormat[0];
            }
            return new AudioFormat[] { new AudioFormat(encoding, audioFormat.getSampleRate(), -1, audioFormat.getChannels(), -1, -1.0f, false) };
        }
        else {
            if (audioFormat.getEncoding() instanceof e && encoding.equals(AudioFormat.Encoding.PCM_SIGNED)) {
                return new AudioFormat[] { new AudioFormat(audioFormat.getSampleRate(), 16, audioFormat.getChannels(), true, false) };
            }
            return new AudioFormat[0];
        }
    }
    
    public AudioInputStream getAudioInputStream(final AudioFormat.Encoding encoding, final AudioInputStream audioInputStream) {
        if (!this.isConversionSupported(encoding, audioInputStream.getFormat())) {
            throw new IllegalArgumentException("conversion not supported");
        }
        final AudioFormat[] targetFormats;
        if ((targetFormats = this.getTargetFormats(encoding, audioInputStream.getFormat())) == null || targetFormats.length <= 0) {
            throw new IllegalArgumentException("target format not found");
        }
        final AudioFormat format = audioInputStream.getFormat();
        final AudioFormat audioFormat = targetFormats[0];
        if (format.equals(audioFormat)) {
            return audioInputStream;
        }
        if (format.getEncoding() instanceof e && audioFormat.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED)) {
            return new b(audioInputStream, audioFormat, -1L);
        }
        if (format.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED) && audioFormat.getEncoding() instanceof e) {
            return new c(audioInputStream, audioFormat, -1L);
        }
        throw new IllegalArgumentException("unable to convert " + format.toString() + " to " + audioFormat.toString());
    }
    
    public AudioInputStream getAudioInputStream(final AudioFormat audioFormat, final AudioInputStream audioInputStream) {
        if (!this.isConversionSupported(audioFormat, audioInputStream.getFormat())) {
            throw new IllegalArgumentException("conversion not supported");
        }
        final AudioFormat[] targetFormats;
        if ((targetFormats = this.getTargetFormats(audioFormat.getEncoding(), audioInputStream.getFormat())) == null || targetFormats.length <= 0) {
            throw new IllegalArgumentException("target format not found");
        }
        final AudioFormat format;
        if ((format = audioInputStream.getFormat()).equals(audioFormat)) {
            return audioInputStream;
        }
        if (format.getEncoding() instanceof e && audioFormat.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED)) {
            return new b(audioInputStream, audioFormat, -1L);
        }
        if (format.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED) && audioFormat.getEncoding() instanceof e) {
            return new c(audioInputStream, audioFormat, -1L);
        }
        throw new IllegalArgumentException("unable to convert " + format.toString() + " to " + audioFormat.toString());
    }
    
    static {
        (new AudioFormat.Encoding[1])[0] = AudioFormat.Encoding.PCM_SIGNED;
        (new AudioFormat.Encoding[1])[0] = e.a;
        final AudioFormat.Encoding[] array = { e.a, AudioFormat.Encoding.PCM_SIGNED };
    }
}
