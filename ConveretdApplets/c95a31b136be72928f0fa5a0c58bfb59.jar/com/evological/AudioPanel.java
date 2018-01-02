// 
// Decompiled by Procyon v0.5.30
// 

package com.evological;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import java.awt.Graphics;
import javax.sound.sampled.SourceDataLine;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JPanel;

class AudioPanel extends JPanel implements Runnable
{
    private URL codebase;
    private String audio;
    private Thread thread;
    private boolean quitNow;
    private InputStream inputStream;
    private SourceDataLine outputStream;
    private int outputStreamBufferSize;
    private int chunkSize;
    private byte[] buffer;
    private double soundBufferFull;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    
    public AudioPanel(final URL codebase, final String audio) {
        this.codebase = null;
        this.audio = null;
        this.thread = null;
        this.quitNow = false;
        this.inputStream = null;
        this.outputStream = null;
        this.outputStreamBufferSize = 0;
        this.chunkSize = 8000;
        this.buffer = new byte[this.chunkSize];
        this.soundBufferFull = 0.0;
        this.codebase = codebase;
        this.audio = audio;
        this.setOpaque(true);
    }
    
    public void startStream() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stopStream() {
        if (this.thread != null) {
            this.quitNow = true;
            this.thread = null;
        }
    }
    
    public void paintComponentX(final Graphics g) {
        final int x = (int)(this.soundBufferFull * this.getWidth());
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.drawString("" + this.soundBufferFull * 100.0, 0, 10);
    }
    
    public void run() {
        try {
            final AudioFormat outputFormat = new AudioFormat(8000.0f, 8, 1, false, false);
            (this.outputStream = (SourceDataLine)AudioSystem.getLine(new DataLine.Info((AudioPanel.class$javax$sound$sampled$SourceDataLine == null) ? (AudioPanel.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : AudioPanel.class$javax$sound$sampled$SourceDataLine, outputFormat))).open(outputFormat);
            this.outputStream.start();
            this.outputStreamBufferSize = this.outputStream.getBufferSize();
        }
        catch (Exception e) {
            this.quitNow = true;
        }
        while (!this.quitNow) {
            try {
                final URL url = new URL(this.codebase, this.audio);
                final HttpURLConnection huc = (HttpURLConnection)url.openConnection();
                if (huc.getResponseCode() != 200) {
                    return;
                }
                final InputStream is = huc.getInputStream();
                final BufferedInputStream inputStream = new BufferedInputStream(is);
                int readBytes = 0;
                while (readBytes != -1) {
                    readBytes = inputStream.read(this.buffer);
                    if (readBytes > 0) {
                        this.outputStream.write(this.buffer, 0, readBytes);
                        this.soundBufferFull = 1.0 - this.outputStream.available() / this.outputStreamBufferSize;
                    }
                }
            }
            catch (IOException e2) {
                try {
                    Thread.currentThread();
                    Thread.sleep(5000L);
                }
                catch (Exception ex) {}
            }
        }
        try {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
            if (this.outputStream != null) {
                this.outputStream.close();
            }
        }
        catch (IOException ex2) {}
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
