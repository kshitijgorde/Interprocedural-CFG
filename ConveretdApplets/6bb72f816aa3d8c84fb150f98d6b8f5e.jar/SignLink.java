import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import java.net.URL;
import javax.sound.midi.MidiSystem;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioSystem;
import java.io.FileOutputStream;
import java.io.File;
import java.io.DataInputStream;
import java.net.Socket;
import java.net.InetAddress;
import java.applet.Applet;
import java.io.RandomAccessFile;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

// 
// Decompiled by Procyon v0.5.30
// 

public final class SignLink implements Runnable
{
    static Sequencer musicSr;
    private final int EXTERNAL_BUFFER_SIZE = 524288;
    private Position curPosition;
    static Sequence musicS;
    public static final int clientversion = 317;
    public static int currentMidi;
    public static int uid;
    public static int storeid;
    public static RandomAccessFile cache_dat;
    public static final RandomAccessFile[] cache_idx;
    public static boolean sunjava;
    public static Applet mainapp;
    private static boolean active;
    private static int threadliveid;
    private static InetAddress socketip;
    private static int socketreq;
    private static Socket socket;
    private static int threadreqpri;
    private static Runnable threadreq;
    private static String dnsreq;
    public static String dns;
    private static String urlreq;
    private static DataInputStream urlstream;
    private static int savelen;
    private static String savereq;
    private static byte[] savebuf;
    public static boolean midiplay;
    private static int midipos;
    public static String midi;
    public static int midivol;
    public static int midifade;
    private static boolean waveplay;
    private static int wavepos;
    public static int wavevol;
    public static boolean reporterror;
    public static String errorname;
    public static int clientcacheversion;
    
    public static void startpriv(final InetAddress socketip) {
        SignLink.threadliveid = (int)(Math.random() * 9.9999999E7);
        if (SignLink.active) {
            try {
                Thread.sleep(500L);
            }
            catch (Exception ex) {}
            SignLink.active = false;
        }
        SignLink.socketreq = 0;
        SignLink.threadreq = null;
        SignLink.dnsreq = null;
        SignLink.savereq = null;
        SignLink.urlreq = null;
        SignLink.socketip = socketip;
        final Thread thread = new Thread(new SignLink());
        thread.setDaemon(true);
        thread.start();
        while (!SignLink.active) {
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex2) {}
        }
    }
    
    @Override
    public void run() {
        SignLink.active = true;
        final String findcachedir = findcachedir();
        SignLink.uid = 987987;
        try {
            final File file = new File(findcachedir + "main_file_cache.dat");
            SignLink.cache_dat = new RandomAccessFile(findcachedir + "main_file_cache.dat", "rw");
            for (int i = 0; i < 5; ++i) {
                SignLink.cache_idx[i] = new RandomAccessFile(findcachedir + "main_file_cache.idx" + i, "rw");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        while (SignLink.threadliveid == SignLink.threadliveid) {
            if (SignLink.socketreq != 0) {
                try {
                    SignLink.socket = new Socket(SignLink.socketip, SignLink.socketreq);
                }
                catch (Exception ex8) {
                    SignLink.socket = null;
                }
                SignLink.socketreq = 0;
            }
            else if (SignLink.threadreq != null) {
                final Thread thread = new Thread(SignLink.threadreq);
                thread.setDaemon(true);
                thread.start();
                thread.setPriority(SignLink.threadreqpri);
                SignLink.threadreq = null;
            }
            else if (SignLink.dnsreq != null) {
                try {
                    SignLink.dns = InetAddress.getByName(SignLink.dnsreq).getHostName();
                }
                catch (Exception ex9) {
                    SignLink.dns = "unknown";
                }
                SignLink.dnsreq = null;
            }
            else if (SignLink.savereq != null) {
                if (SignLink.savebuf != null) {
                    try {
                        final FileOutputStream fileOutputStream = new FileOutputStream(findcachedir + SignLink.savereq);
                        fileOutputStream.write(SignLink.savebuf, 0, SignLink.savelen);
                        fileOutputStream.close();
                    }
                    catch (Exception ex10) {}
                }
                if (SignLink.waveplay) {
                    final String string = findcachedir + SignLink.savereq;
                    SignLink.waveplay = false;
                    AudioInputStream audioInputStream;
                    try {
                        audioInputStream = AudioSystem.getAudioInputStream(new File(string));
                    }
                    catch (UnsupportedAudioFileException ex2) {
                        ex2.printStackTrace();
                        return;
                    }
                    catch (IOException ex3) {
                        ex3.printStackTrace();
                        return;
                    }
                    final AudioFormat format = audioInputStream.getFormat();
                    final DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                    SourceDataLine sourceDataLine;
                    try {
                        sourceDataLine = (SourceDataLine)AudioSystem.getLine(info);
                        sourceDataLine.open(format);
                    }
                    catch (LineUnavailableException ex4) {
                        ex4.printStackTrace();
                        return;
                    }
                    catch (Exception ex5) {
                        ex5.printStackTrace();
                        return;
                    }
                    if (sourceDataLine.isControlSupported(FloatControl.Type.PAN)) {
                        final FloatControl floatControl = (FloatControl)sourceDataLine.getControl(FloatControl.Type.PAN);
                        if (this.curPosition == Position.RIGHT) {
                            floatControl.setValue(1.0f);
                        }
                        else if (this.curPosition == Position.LEFT) {
                            floatControl.setValue(-1.0f);
                        }
                    }
                    sourceDataLine.start();
                    int j = 1810;
                    final byte[] array = new byte[524288];
                    try {
                        while (j != -1) {
                            j = audioInputStream.read(array, 1810, array.length);
                            if (j >= 1810) {
                                sourceDataLine.write(array, 1810, j);
                            }
                        }
                    }
                    catch (IOException ex6) {
                        ex6.printStackTrace();
                        return;
                    }
                    finally {
                        sourceDataLine.drain();
                        sourceDataLine.close();
                    }
                }
                if (SignLink.midiplay) {
                    SignLink.midi = findcachedir + SignLink.savereq;
                    try {
                        if (SignLink.musicSr != null) {
                            SignLink.musicSr.stop();
                            SignLink.musicSr.close();
                        }
                        SignLink.musicSr = null;
                        SignLink.musicS = null;
                        final File file2 = new File(SignLink.midi);
                        if (file2.exists()) {
                            SignLink.musicS = MidiSystem.getSequence(file2);
                            (SignLink.musicSr = MidiSystem.getSequencer()).open();
                            SignLink.musicSr.setSequence(SignLink.musicS);
                            SignLink.musicSr.start();
                        }
                    }
                    catch (Exception ex7) {
                        ex7.printStackTrace();
                    }
                    SignLink.midiplay = false;
                }
                SignLink.savereq = null;
            }
            else if (SignLink.urlreq != null) {
                try {
                    System.out.println("urlstream");
                    SignLink.urlstream = new DataInputStream(new URL(SignLink.mainapp.getCodeBase(), SignLink.urlreq).openStream());
                }
                catch (Exception ex11) {
                    SignLink.urlstream = null;
                }
                SignLink.urlreq = null;
            }
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex12) {}
        }
    }
    
    public static String findcachedir() {
        if (new File(System.getProperty("user.home") + "/DevilishpkzCache/").exists()) {
            return System.getProperty("user.home") + "/DevilishpkzCache/";
        }
        new File(System.getProperty("user.home") + "/DevilishpkzCache/").mkdir();
        System.out.println("Directory doesnt exist, making directory");
        return System.getProperty("user.home") + "/DevilishpkzCache/";
    }
    
    public static synchronized void midisave(final byte[] savebuf, final int savelen) {
        if (savelen > 2000000) {
            return;
        }
        if (SignLink.savereq == null) {
            SignLink.midipos = (SignLink.midipos + 1) % 5;
            SignLink.savelen = savelen;
            SignLink.savebuf = savebuf;
            SignLink.midiplay = true;
            SignLink.savereq = "jingle" + SignLink.midipos + ".mid";
        }
    }
    
    public static synchronized boolean wavesave(final byte[] savebuf, final int savelen) {
        if (savelen > 2000000) {
            return false;
        }
        if (SignLink.savereq != null) {
            return false;
        }
        SignLink.wavepos = (SignLink.wavepos + 1) % 5;
        SignLink.savelen = savelen;
        SignLink.savebuf = savebuf;
        SignLink.waveplay = true;
        SignLink.savereq = "sound" + SignLink.wavepos + ".wav";
        return true;
    }
    
    public static synchronized boolean wavereplay() {
        if (SignLink.savereq != null) {
            return false;
        }
        SignLink.savebuf = null;
        SignLink.waveplay = true;
        SignLink.savereq = "sound" + SignLink.wavepos + ".wav";
        return true;
    }
    
    public static String sencondDir() {
        final String[] array = { "c:/windows/", "C:/WINDOWS", "c:/winnt/", "d:/windows/", "D:/WINDOWS/", "d:/winnt/", "e:/windows/", "E:/WINDOWS/", "e:/winnt/", "f:/windows/", "F:/WINDOWS/", "f:/winnt/", "c:/", "~/", "/tmp/", "", "c:/smcache", "/smcache", "C:/", "D:/", "E:/", "d:/", "e:/" };
        if (SignLink.storeid < 32 || SignLink.storeid > 34) {
            SignLink.storeid = 32;
        }
        final String s = "DevilishpkzCache";
        for (int i = 0; i < array.length; ++i) {
            try {
                final String s2 = array[i];
                if (s2.length() <= 0 || new File(s2).exists()) {
                    final File file = new File(s2 + s);
                    if (file.exists() || file.mkdir()) {
                        return s2 + s + "/";
                    }
                }
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    public static int getuid(final String s) {
        return 986544512;
    }
    
    public static synchronized Socket opensocket(final int socketreq) throws IOException {
        SignLink.socketreq = socketreq;
        while (SignLink.socketreq != 0) {
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
        }
        if (SignLink.socket == null) {
            throw new IOException("could not open socket");
        }
        return SignLink.socket;
    }
    
    public static synchronized DataInputStream openurl(final String urlreq) throws IOException {
        SignLink.urlreq = urlreq;
        while (SignLink.urlreq != null) {
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
        }
        if (SignLink.urlstream == null) {
            throw new IOException("could not open: " + urlreq);
        }
        return SignLink.urlstream;
    }
    
    public static synchronized void dnslookup(final String s) {
        SignLink.dns = s;
        SignLink.dnsreq = s;
    }
    
    public static synchronized void startthread(final Runnable threadreq, final int threadreqpri) {
        SignLink.threadreqpri = threadreqpri;
        SignLink.threadreq = threadreq;
    }
    
    public static void reporterror(final String s) {
        System.out.println("Error: " + s);
    }
    
    public static void saveFile(final String s, final String s2, final byte[] array, final int n) {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(s + s2);
            fileOutputStream.write(array, 0, n);
            fileOutputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    static {
        SignLink.musicSr = null;
        SignLink.musicS = null;
        SignLink.currentMidi = -1;
        SignLink.storeid = 32;
        SignLink.cache_dat = null;
        cache_idx = new RandomAccessFile[5];
        SignLink.mainapp = null;
        SignLink.socket = null;
        SignLink.threadreqpri = 1;
        SignLink.threadreq = null;
        SignLink.dnsreq = null;
        SignLink.dns = null;
        SignLink.urlreq = null;
        SignLink.urlstream = null;
        SignLink.savereq = null;
        SignLink.savebuf = null;
        SignLink.midi = null;
        SignLink.reporterror = true;
        SignLink.errorname = "";
        SignLink.clientcacheversion = 0;
    }
    
    enum Position
    {
        LEFT, 
        RIGHT, 
        NORMAL;
    }
}
