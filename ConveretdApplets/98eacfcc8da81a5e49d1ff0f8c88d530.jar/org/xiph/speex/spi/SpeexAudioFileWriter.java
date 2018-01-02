// 
// Decompiled by Procyon v0.5.30
// 

package org.xiph.speex.spi;

import java.io.FileOutputStream;
import java.io.File;
import java.io.OutputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.spi.AudioFileWriter;

public class SpeexAudioFileWriter extends AudioFileWriter
{
    private static AudioFileFormat.Type[] a;
    private static AudioFileFormat.Type[] b;
    
    public AudioFileFormat.Type[] getAudioFileTypes() {
        return SpeexAudioFileWriter.b;
    }
    
    public AudioFileFormat.Type[] getAudioFileTypes(final AudioInputStream audioInputStream) {
        if (audioInputStream.getFormat().getEncoding() instanceof e) {
            return SpeexAudioFileWriter.b;
        }
        return SpeexAudioFileWriter.a;
    }
    
    public int write(final AudioInputStream audioInputStream, final AudioFileFormat.Type type, final OutputStream outputStream) {
        final AudioFileFormat.Type[] audioFileTypes;
        if ((audioFileTypes = this.getAudioFileTypes(audioInputStream)) != null && audioFileTypes.length > 0) {
            return a(audioInputStream, outputStream);
        }
        throw new IllegalArgumentException("cannot write given file type");
    }
    
    public int write(final AudioInputStream audioInputStream, final AudioFileFormat.Type type, final File file) {
        final AudioFileFormat.Type[] audioFileTypes;
        if ((audioFileTypes = this.getAudioFileTypes(audioInputStream)) != null && audioFileTypes.length > 0) {
            return a(audioInputStream, new FileOutputStream(file));
        }
        throw new IllegalArgumentException("cannot write given file type");
    }
    
    private static int a(final AudioInputStream audioInputStream, final OutputStream outputStream) {
        final byte[] array = new byte[2048];
        int n = 0;
        int read;
        while ((read = audioInputStream.read(array, 0, 2048)) > 0) {
            outputStream.write(array, 0, read);
            n += read;
        }
        outputStream.flush();
        outputStream.close();
        return n;
    }
    
    static {
        SpeexAudioFileWriter.a = new AudioFileFormat.Type[0];
        SpeexAudioFileWriter.b = new AudioFileFormat.Type[] { d.a };
    }
}
