// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jlgui.basicplayer;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import java.net.URLConnection;
import javax.sound.sampled.AudioFileFormat;
import java.net.URL;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import javazoom.spi.mpeg.sampled.convert.MpegFormatConversionProvider;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;

public class AppletMpegSPIWorkaround
{
    public static boolean DEBUG;
    public static String useragent;
    
    public static AudioInputStream getAudioInputStream(final AudioFormat targetFormat, final AudioInputStream sourceStream) {
        try {
            return AudioSystem.getAudioInputStream(targetFormat, sourceStream);
        }
        catch (IllegalArgumentException iae) {
            if (AppletMpegSPIWorkaround.DEBUG) {
                System.err.println("Using AppletMpegSPIWorkaround to get codec");
            }
            try {
                Class.forName("javazoom.spi.mpeg.sampled.convert.MpegFormatConversionProvider");
                return new MpegFormatConversionProvider().getAudioInputStream(targetFormat, sourceStream);
            }
            catch (ClassNotFoundException cnfe) {
                throw new IllegalArgumentException("Mpeg codec not properly installed");
            }
        }
    }
    
    public static AudioInputStream getAudioInputStream(final File file) throws UnsupportedAudioFileException, IOException {
        final InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        try {
            if (AppletMpegSPIWorkaround.DEBUG) {
                System.err.println("Using AppletMpegSPIWorkaround to get codec (AudioInputStream:file)");
            }
            return getAudioInputStream(inputStream);
        }
        catch (UnsupportedAudioFileException e) {
            inputStream.close();
            throw e;
        }
        catch (IOException e2) {
            inputStream.close();
            throw e2;
        }
    }
    
    public static AudioInputStream getAudioInputStream(final URL url) throws UnsupportedAudioFileException, IOException {
        try {
            Class.forName("javazoom.spi.mpeg.sampled.file.MpegAudioFileReader");
            return new MpegAudioFileReaderWorkaround().getAudioInputStream(url, AppletMpegSPIWorkaround.useragent);
        }
        catch (ClassNotFoundException cnfe) {
            throw new IllegalArgumentException("Mpeg codec not properly installed");
        }
    }
    
    public static AudioFileFormat getAudioFileFormat(final File file) throws UnsupportedAudioFileException, IOException {
        final InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        try {
            if (AppletMpegSPIWorkaround.DEBUG) {
                System.err.println("Using AppletMpegSPIWorkaround to get codec (AudioFileFormat:file)");
            }
            return getAudioFileFormat(inputStream);
        }
        finally {
            inputStream.close();
        }
    }
    
    public static AudioFileFormat getAudioFileFormat(final URL url) throws UnsupportedAudioFileException, IOException {
        InputStream inputStream = null;
        if (AppletMpegSPIWorkaround.useragent != null) {
            final URLConnection myCon = url.openConnection();
            myCon.setUseCaches(false);
            myCon.setDoInput(true);
            myCon.setDoOutput(true);
            myCon.setAllowUserInteraction(false);
            myCon.setRequestProperty("User-Agent", AppletMpegSPIWorkaround.useragent);
            myCon.setRequestProperty("Accept", "*/*");
            myCon.setRequestProperty("Icy-Metadata", "1");
            myCon.setRequestProperty("Connection", "close");
            inputStream = new BufferedInputStream(myCon.getInputStream());
        }
        else {
            inputStream = new BufferedInputStream(url.openStream());
        }
        try {
            if (AppletMpegSPIWorkaround.DEBUG) {
                System.err.println("Using AppletMpegSPIWorkaround to get codec (AudioFileFormat:url)");
            }
            return getAudioFileFormatForUrl(inputStream);
        }
        finally {
            inputStream.close();
        }
    }
    
    public static AudioFileFormat getAudioFileFormatForUrl(final InputStream is) throws UnsupportedAudioFileException, IOException {
        try {
            throw new Exception();
        }
        catch (Exception iae) {
            if (AppletMpegSPIWorkaround.DEBUG) {
                System.err.println("Using AppletMpegSPIWorkaround to get codec (AudioFileFormat)");
            }
            try {
                Class.forName("javazoom.spi.mpeg.sampled.file.MpegAudioFileReader");
                return new MpegAudioFileReader().getAudioFileFormat(is, -1L);
            }
            catch (ClassNotFoundException cnfe) {
                throw new IllegalArgumentException("Mpeg codec not properly installed");
            }
        }
    }
    
    public static AudioFileFormat getAudioFileFormat(final InputStream is) throws UnsupportedAudioFileException, IOException {
        try {
            throw new Exception();
        }
        catch (Exception iae) {
            if (AppletMpegSPIWorkaround.DEBUG) {
                System.err.println("Using AppletMpegSPIWorkaround to get codec (AudioFileFormat)");
            }
            try {
                Class.forName("javazoom.spi.mpeg.sampled.file.MpegAudioFileReader");
                is.mark(4096);
                return new MpegAudioFileReader().getAudioFileFormat(is);
            }
            catch (ClassNotFoundException cnfe) {
                throw new IllegalArgumentException("Mpeg codec not properly installed");
            }
        }
        finally {
            is.reset();
        }
    }
    
    public static AudioInputStream getAudioInputStream(final InputStream is) throws UnsupportedAudioFileException, IOException {
        try {
            throw new Exception();
        }
        catch (Exception iae) {
            if (AppletMpegSPIWorkaround.DEBUG) {
                System.err.println("Using AppletMpegSPIWorkaround to get codec");
            }
            try {
                Class.forName("javazoom.spi.mpeg.sampled.file.MpegAudioFileReader");
                return new MpegAudioFileReader().getAudioInputStream(is);
            }
            catch (ClassNotFoundException cnfe) {
                throw new IllegalArgumentException("Mpeg codec not properly installed");
            }
        }
    }
    
    static {
        AppletMpegSPIWorkaround.DEBUG = false;
        AppletMpegSPIWorkaround.useragent = null;
    }
}
