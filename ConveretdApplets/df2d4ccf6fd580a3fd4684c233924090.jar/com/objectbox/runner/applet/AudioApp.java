// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.applet;

import sun.audio.AudioPlayer;
import java.io.FileOutputStream;
import java.io.InputStream;
import sun.audio.AudioStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import com.objectbox.runner.beans.DownloadView;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import com.objectbox.runner.util.JBLogger;
import java.io.File;
import com.objectbox.runner.gui.JBee;
import java.net.URL;
import sun.audio.ContinuousAudioDataStream;
import sun.audio.AudioDataStream;
import sun.audio.AudioData;
import java.applet.AudioClip;

public class AudioApp implements AudioClip
{
    private AudioData audiodata;
    private AudioDataStream audiostream;
    private ContinuousAudioDataStream continuousaudiostream;
    
    public AudioApp() {
    }
    
    public AudioApp(final String s) throws IOException {
        final URL url = new URL(s);
        final String string = url.toString();
        final String string2 = string.hashCode() + "_" + string.substring(string.lastIndexOf("/") + 1, string.length());
        String s2 = JBee.getPreference("javabee_home");
        if (!s2.endsWith(System.getProperty("file.separator"))) {
            s2 = String.valueOf(s2) + System.getProperty("file.separator");
        }
        final File file = new File(String.valueOf(s2) + "cache", string2);
        byte[] byteArray;
        if (file.exists()) {
            JBLogger.log("Using cached sound");
            final FileInputStream fileInputStream = new FileInputStream(file);
            byteArray = new byte[(int)file.length()];
            fileInputStream.read(byteArray);
            fileInputStream.close();
            JBLogger.log("Using cached sound file.");
        }
        else {
            final DataInputStream dataInputStream = new DataInputStream(url.openConnection().getInputStream());
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                try {
                    byteArrayOutputStream.write(dataInputStream.readByte());
                    DownloadView.addBytes(1L);
                }
                catch (IOException ex) {
                    break;
                }
            }
            byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            dataInputStream.close();
        }
        final AudioStream audioStream = new AudioStream(new ByteArrayInputStream(byteArray));
        this.audiodata = audioStream.getData();
        audioStream.close();
        if (!file.exists()) {
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(byteArray);
            fileOutputStream.close();
        }
        this.audiostream = null;
        this.continuousaudiostream = null;
    }
    
    public void finalize() {
        try {
            if (this.audiostream != null) {
                this.audiostream.close();
            }
            if (this.continuousaudiostream != null) {
                this.continuousaudiostream.close();
            }
        }
        catch (Throwable t) {}
        finally {
            this.audiostream = null;
            this.continuousaudiostream = null;
            JBLogger.log("AudioApp " + this.toString() + " finalized");
        }
        this.audiostream = null;
        this.continuousaudiostream = null;
        JBLogger.log("AudioApp " + this.toString() + " finalized");
    }
    
    public void loadAudioFromResource(final String s) throws IOException {
        try {
            final DataInputStream dataInputStream = new DataInputStream(this.getClass().getResourceAsStream(s));
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(dataInputStream.readByte());
                DownloadView.addBytes(1L);
            }
            catch (IOException ex2) {
                final byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                dataInputStream.close();
                final AudioStream audioStream = new AudioStream(new ByteArrayInputStream(byteArray));
                this.audiodata = audioStream.getData();
                audioStream.close();
                this.audiostream = null;
                this.continuousaudiostream = null;
            }
        }
        catch (Exception ex) {
            JBLogger.log("Ex in loadAudiofromRes: " + ex);
        }
    }
    
    public void loop() {
        if (this.continuousaudiostream == null) {
            this.continuousaudiostream = new ContinuousAudioDataStream(this.audiodata);
        }
        AudioPlayer.player.start(this.continuousaudiostream);
    }
    
    public static void main(final String[] array) throws Exception {
        new AudioApp("http://box1/lyd/boing.au").play();
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException ex) {}
    }
    
    public void play() {
        this.audiostream = new AudioDataStream(this.audiodata);
        AudioPlayer.player.start(this.audiostream);
    }
    
    public void stop() {
        if (this.audiostream != null) {
            AudioPlayer.player.stop(this.audiostream);
        }
        if (this.continuousaudiostream != null) {
            AudioPlayer.player.stop(this.continuousaudiostream);
        }
    }
}
