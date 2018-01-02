// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common15;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.SourceDataLine;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioSystem;
import java.net.URL;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;

public class Play implements Runnable
{
    private static final boolean DEBUGGING = false;
    private final AudioFormat af;
    private final AudioInputStream ais;
    private final DataLine.Info info;
    private final int buffSize;
    
    public static void play(final URL url) throws UnsupportedAudioFileException, IOException {
        play(AudioSystem.getAudioInputStream(url));
    }
    
    public static void play(final File file) throws UnsupportedAudioFileException, IOException {
        play(AudioSystem.getAudioInputStream(file));
    }
    
    public static void play(final Class resourceOwner, final String resourceName) {
        try {
            final URL url = resourceOwner.getResource(resourceName);
            if (url == null) {
                System.err.println("Can't find sound effect resource " + resourceName);
                return;
            }
            play(url);
        }
        catch (UnsupportedAudioFileException e) {
            System.err.println("Unsupported Audio file format");
        }
        catch (IOException e2) {
            System.err.println("Problems fetching sound effect data");
        }
    }
    
    public void run() {
        synchronized (this.getClass()) {
            try {
                final SourceDataLine line = (SourceDataLine)AudioSystem.getLine(this.info);
                line.open(this.af, this.buffSize);
                line.start();
                final byte[] data = new byte[this.buffSize];
                int bytesRead;
                while ((bytesRead = this.ais.read(data, 0, data.length)) != -1) {
                    line.write(data, 0, bytesRead);
                }
                line.drain();
                line.stop();
                line.close();
            }
            catch (LineUnavailableException e) {
                System.err.println("Line unavailable to play a sound");
            }
            catch (IOException e2) {
                System.err.println("Problems fetching data to play a sound");
            }
        }
    }
    
    private static synchronized void play(final AudioInputStream ais) {
        final AudioFormat af = ais.getFormat();
        final DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
        if (!AudioSystem.isLineSupported(info)) {
            throw new IllegalArgumentException("Cannot play an unsupported audio format");
        }
        final int frameRate = (int)af.getFrameRate();
        final int frameSize = af.getFrameSize();
        final int buffSize = frameRate * frameSize / 10;
        new Thread(new Play(ais, af, info, buffSize)).start();
    }
    
    private Play(final AudioInputStream ais, final AudioFormat af, final DataLine.Info info, final int buffSize) {
        this.ais = ais;
        this.af = af;
        this.info = info;
        this.buffSize = buffSize;
    }
}
