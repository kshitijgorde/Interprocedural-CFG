// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import javax.sound.sampled.AudioFileFormat;
import java.io.File;
import java.util.Date;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import java.text.SimpleDateFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;

public class AudioCapture implements Runnable
{
    TargetDataLine line;
    Thread thread;
    double duration;
    AudioFormat format;
    private String fileName;
    private AudioInputStream audioInputStream;
    SimpleDateFormat dateFormat;
    
    public AudioCapture() {
        this.dateFormat = new SimpleDateFormat();
        this.format = new AudioFormat(44100.0f, 16, 1, true, false);
        this.dateFormat.applyPattern("dd-MM-yyyy HHmmss");
    }
    
    public void start() {
        (this.thread = new Thread(this)).setName("Capture");
        this.thread.start();
    }
    
    public void stop() {
        this.thread = null;
    }
    
    public void run() {
        this.duration = 0.0;
        final DataLine.Info info = new DataLine.Info(TargetDataLine.class, this.format);
        if (!AudioSystem.isLineSupported(info)) {
            Logger.log("Not supported");
            return;
        }
        try {
            (this.line = (TargetDataLine)AudioSystem.getLine(info)).open(this.format, this.line.getBufferSize());
        }
        catch (LineUnavailableException ex) {
            Logger.log("Unable to open the line: " + ex);
            return;
        }
        catch (SecurityException ex2) {
            Logger.log(ex2.toString());
            return;
        }
        catch (Exception ex3) {
            Logger.log(ex3.toString());
            return;
        }
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final int frameSizeInBytes = this.format.getFrameSize();
        final int bufferLengthInFrames = this.line.getBufferSize() / 8;
        final int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
        final byte[] data = new byte[bufferLengthInBytes];
        this.line.start();
        int numBytesRead;
        while (this.thread != null && (numBytesRead = this.line.read(data, 0, bufferLengthInBytes)) != -1) {
            out.write(data, 0, numBytesRead);
        }
        this.line.stop();
        this.line.close();
        this.line = null;
        try {
            out.flush();
            out.close();
        }
        catch (IOException ex4) {
            ex4.printStackTrace();
        }
        final byte[] audioBytes = out.toByteArray();
        final ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
        this.audioInputStream = new AudioInputStream(bais, this.format, audioBytes.length / frameSizeInBytes);
        final long milliseconds = (long)(this.audioInputStream.getFrameLength() * 1000L / this.format.getFrameRate());
        this.duration = milliseconds / 1000.0;
        try {
            this.audioInputStream.reset();
        }
        catch (Exception ex5) {
            ex5.printStackTrace();
            return;
        }
        this.saveToFile();
    }
    
    public void saveToFile() {
        final Date now = new Date();
        final long start = System.currentTimeMillis();
        now.setTime(start);
        this.fileName = MattProperties.instance().getProperty("tunometerPath") + System.getProperty("file.separator") + "query " + this.dateFormat.format(now) + ".wav";
        final File file = new File(this.fileName);
        try {
            if (AudioSystem.write(this.audioInputStream, AudioFileFormat.Type.WAVE, file) == -1) {
                throw new IOException("Problems writing to file");
            }
        }
        catch (Exception ex) {
            Logger.log(ex.toString());
        }
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
}
