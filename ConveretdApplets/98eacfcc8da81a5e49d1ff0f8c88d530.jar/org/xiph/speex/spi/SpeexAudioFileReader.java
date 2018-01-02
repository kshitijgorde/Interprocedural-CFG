// 
// Decompiled by Procyon v0.5.30
// 

package org.xiph.speex.spi;

import java.io.SequenceInputStream;
import java.io.ByteArrayInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import VT_6_1_0_11.f;
import java.io.DataInputStream;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import javax.sound.sampled.AudioFileFormat;
import java.io.File;
import javax.sound.sampled.spi.AudioFileReader;

public class SpeexAudioFileReader extends AudioFileReader
{
    public AudioFileFormat getAudioFileFormat(final File file) {
        InputStream inputStream = null;
        try {
            final FileInputStream fileInputStream;
            inputStream = (fileInputStream = new FileInputStream(file));
            final int n = (int)file.length();
            return a(fileInputStream, null);
        }
        finally {
            inputStream.close();
        }
    }
    
    public AudioFileFormat getAudioFileFormat(URL openStream) {
        openStream = (URL)openStream.openStream();
        try {
            return this.getAudioFileFormat((InputStream)openStream);
        }
        finally {
            ((InputStream)openStream).close();
        }
    }
    
    public AudioFileFormat getAudioFileFormat(final InputStream inputStream) {
        return a(inputStream, null);
    }
    
    private static AudioFileFormat a(final InputStream inputStream, ByteArrayOutputStream byteArrayOutputStream) {
        AudioFormat audioFormat;
        try {
            if (inputStream.markSupported()) {
                inputStream.mark(675);
            }
            float n = -1.0f;
            final byte[] array = new byte[128];
            final DataInputStream dataInputStream = new DataInputStream(inputStream);
            if (byteArrayOutputStream == null) {
                byteArrayOutputStream = new ByteArrayOutputStream(128);
            }
            dataInputStream.readFully(array, 0, 27);
            byteArrayOutputStream.write(array, 0, 27);
            final int a = a(array, 22);
            array[23] = (array[22] = 0);
            array[25] = (array[24] = 0);
            final int a2 = f.a(0, array, 0, 27);
            if (!"OggS".equals(new String(array, 0, 4))) {
                throw new UnsupportedAudioFileException("missing ogg id!");
            }
            final int n2;
            if ((n2 = (array[26] & 0xFF)) > 1) {
                throw new UnsupportedAudioFileException("Corrupt Speex Header: more than 1 segments");
            }
            dataInputStream.readFully(array, 27, n2);
            byteArrayOutputStream.write(array, 27, n2);
            final int a3 = f.a(a2, array, 27, n2);
            final int n3;
            if ((n3 = (array[27] & 0xFF)) != 80) {
                throw new UnsupportedAudioFileException("Corrupt Speex Header: size=" + n3);
            }
            dataInputStream.readFully(array, 28, n3);
            byteArrayOutputStream.write(array, 28, n3);
            final int a4 = f.a(a3, array, 28, n3);
            if (!"Speex   ".equals(new String(array, 28, 8))) {
                throw new UnsupportedAudioFileException("Corrupt Speex Header: missing Speex ID");
            }
            final int a5 = a(array, 68);
            final int a6 = a(array, 64);
            final int a7 = a(array, 76);
            final int a8 = a(array, 92);
            if (a4 != a) {
                throw new IOException("Ogg CheckSums do not match");
            }
            if (a5 >= 0 && a5 <= 2 && a8 > 0) {
                n = a6 / (((a5 == 0) ? 160.0f : ((a5 == 1) ? 320.0f : 640.0f)) * a8);
            }
            audioFormat = new AudioFormat((AudioFormat.Encoding)e.a, a6, -1, a7, -1, n, false);
        }
        catch (UnsupportedAudioFileException ex) {
            if (inputStream.markSupported()) {
                inputStream.reset();
            }
            throw ex;
        }
        catch (IOException ex2) {
            if (inputStream.markSupported()) {
                inputStream.reset();
            }
            throw new UnsupportedAudioFileException(ex2.getMessage());
        }
        return new AudioFileFormat(d.a, audioFormat, -1);
    }
    
    public AudioInputStream getAudioInputStream(final File file) {
        final FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return this.a(fileInputStream, (int)file.length());
        }
        catch (UnsupportedAudioFileException ex) {
            fileInputStream.close();
            throw ex;
        }
        catch (IOException ex2) {
            fileInputStream.close();
            throw ex2;
        }
    }
    
    public AudioInputStream getAudioInputStream(URL openStream) {
        openStream = (URL)openStream.openStream();
        try {
            return this.getAudioInputStream((InputStream)openStream);
        }
        catch (UnsupportedAudioFileException ex) {
            ((InputStream)openStream).close();
            throw ex;
        }
        catch (IOException ex2) {
            ((InputStream)openStream).close();
            throw ex2;
        }
    }
    
    public AudioInputStream getAudioInputStream(final InputStream inputStream) {
        return this.a(inputStream, -1);
    }
    
    private AudioInputStream a(final InputStream inputStream, final int n) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(128);
        final AudioFileFormat a = a(inputStream, byteArrayOutputStream);
        return new AudioInputStream(new SequenceInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), inputStream), a.getFormat(), a.getFrameLength());
    }
    
    private static int a(final byte[] array, final int n) {
        return (array[n] & 0xFF) | (array[n + 1] & 0xFF) << 8 | (array[n + 2] & 0xFF) << 16 | array[n + 3] << 24;
    }
}
