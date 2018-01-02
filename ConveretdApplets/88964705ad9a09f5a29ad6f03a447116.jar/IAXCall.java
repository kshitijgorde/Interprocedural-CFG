import java.nio.ShortBuffer;
import java.nio.ByteBuffer;
import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.InputStream;
import java.io.IOException;
import javax.sound.sampled.Control;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import java.net.InetSocketAddress;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioInputStream;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class IAXCall
{
    static Vector<IAXCall> callarray;
    static final boolean DEFAULT_MUTE_STATE = false;
    static final int DEFAULTFRAMELENGTH = 33;
    static final int portno = 4569;
    String peerAddress;
    String txAddress;
    int txPort;
    int txCallno;
    int txID;
    short scallno;
    short dcallno;
    int timestamp;
    byte oseqno;
    byte iseqno;
    int codec;
    private STATE callState;
    long endTime;
    boolean metacall;
    CircularInputStream incis;
    AudioInputStream inais;
    SourceDataLine sdline;
    int nBytesRead;
    byte[] abData;
    byte[] playAudioData;
    boolean playdebug;
    AudioInputStream outais;
    TargetDataLine tdline;
    boolean recorddebug;
    boolean muted;
    
    public IAXCall(final String h) {
        this.playAudioData = new byte[320];
        this.playdebug = false;
        this.recorddebug = false;
        this.muted = false;
        this.scallno = this.genScallno();
        this.timestamp = 0;
        final boolean b = false;
        this.iseqno = (byte)(b ? 1 : 0);
        this.oseqno = (byte)(b ? 1 : 0);
        this.codec = 0;
        this.callState = STATE.DOWN;
        this.abData = new byte[33];
        final InetSocketAddress isa = new InetSocketAddress(h, 4569);
        this.peerAddress = isa.getAddress().getHostAddress();
        this.endTime = 0L;
        this.metacall = false;
    }
    
    public IAXCall(final String h, final short destcallno) {
        this(h);
        this.dcallno = destcallno;
    }
    
    public IAXCall() {
        this.playAudioData = new byte[320];
        this.playdebug = false;
        this.recorddebug = false;
        this.muted = false;
        this.scallno = 0;
        this.timestamp = 0;
        final boolean b = false;
        this.iseqno = (byte)(b ? 1 : 0);
        this.oseqno = (byte)(b ? 1 : 0);
        this.codec = 0;
        this.callState = STATE.DOWN;
        this.abData = new byte[33];
        this.peerAddress = "";
        this.endTime = 0L;
        this.metacall = false;
    }
    
    public STATE getCallState() {
        return this.callState;
    }
    
    public void setCallState(final STATE i) {
        if (this.callState == STATE.HOLD && i == STATE.UP) {
            this.tdline.flush();
            this.tdline.start();
            this.sdline.flush();
            this.sdline.start();
        }
        this.callState = i;
        if (this.callState == STATE.HOLD && i == STATE.HOLD) {
            this.tdline.stop();
            this.sdline.stop();
        }
    }
    
    private short genScallno() {
        for (int i = 1; i < IAXCall.callarray.size(); ++i) {
            if (IAXCall.callarray.get(i).scallno == -1) {
                return (short)i;
            }
        }
        return (short)IAXCall.callarray.size();
    }
    
    private void printCallVars() {
        Starphone.log("peerAddress = " + this.peerAddress);
        Starphone.log("portno = " + 4569);
        Starphone.log("scallno = " + this.scallno);
        Starphone.log("dcallno = " + this.dcallno);
        Starphone.log("timestamp = " + this.timestamp);
        Starphone.log("oseqno = " + this.oseqno);
        Starphone.log("iseqno = " + this.iseqno);
        Starphone.log("codec = " + this.codec);
        Starphone.log("callState = " + this.callState);
        if (this.txAddress != null) {
            Starphone.log("txAddress = " + this.txAddress);
        }
    }
    
    public void hangup() {
        this.setCallState(STATE.DOWN);
        this.endTime = System.currentTimeMillis();
        networkThread.cancelRetransmits(this);
        if (this.tdline != null && this.tdline.isOpen()) {
            this.tdline.stop();
            this.tdline.flush();
            this.tdline.close();
        }
        if (this.sdline != null && this.sdline.isOpen()) {
            this.sdline.stop();
            this.sdline.flush();
            this.sdline = null;
        }
        this.muted = false;
    }
    
    public void initMic() {
        if (this.tdline != null) {
            return;
        }
        final AudioFormat sf = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100.0f, 16, 1, 2, 44100.0f, false);
        final AudioFormat tf = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 8000.0f, 16, 1, 2, 8000.0f, false);
        if (this.recorddebug) {
            Starphone.log("mic: source format = " + sf);
            Starphone.log("mic: target format = " + tf);
        }
        final DataLine.Info info = new DataLine.Info(TargetDataLine.class, sf, -1);
        try {
            (this.tdline = (TargetDataLine)AudioSystem.getLine(info)).open(sf, -1);
            final Control[] c = this.tdline.getControls();
            if (c.length > 0) {
                Starphone.log("tdline has " + c.length + " controls.");
                for (int i = 0; i < c.length; ++i) {
                    Starphone.log("tdline control: " + c[i]);
                }
            }
        }
        catch (LineUnavailableException lue) {
            this.muted = true;
            Starphone.log("\n\n*** No compatible microphone found. Audio transmission disabled. ***\n\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.tdline.start();
        this.outais = new AudioInputStream(this.tdline);
        this.outais = AudioSystem.getAudioInputStream(tf, this.outais);
        this.outais = AudioSystem.getAudioInputStream(new AudioFormat.Encoding("GSM0610"), this.outais);
        if (this.recorddebug) {
            Starphone.log("mic: converted AIS: " + this.outais);
            Starphone.log("mic: target format: " + this.outais.getFormat());
        }
    }
    
    public IAXFrame sendAudio(final int full) {
        if (this.callState != STATE.UP) {
            return null;
        }
        if (this.tdline == null && full == 1) {
            this.initMic();
            return new IAXFullFrame(this, IAXFrame.FRAMETYPES.VOICE, 2, this.readMic());
        }
        if (this.muted) {
            return null;
        }
        if (full == 1) {
            return new IAXFullFrame(this, IAXFrame.FRAMETYPES.VOICE, 2, this.readMic());
        }
        return new IAXMiniFrame(this.scallno, this.timestamp, this.readMic());
    }
    
    public IAXFullFrame sendDTMF(final String s) {
        if (this.callState != STATE.UP || s == null) {
            return null;
        }
        if (this.outais == null) {
            return null;
        }
        return new IAXFullFrame(this, IAXFrame.FRAMETYPES.DTMF, s.charAt(0));
    }
    
    public IAXFullFrame sendText(final String s) {
        if (this.callState != STATE.UP || s == null) {
            return null;
        }
        return new IAXFullFrame(this, IAXFrame.FRAMETYPES.TEXT, 0, s);
    }
    
    private byte[] readMic() {
        this.nBytesRead = 0;
        if (this.outais == null) {
            return new byte[33];
        }
        try {
            this.nBytesRead = this.outais.read(this.abData, 0, 33);
        }
        catch (IOException e) {
            Starphone.log("Exception in readMic...");
            e.printStackTrace();
        }
        if (this.recorddebug) {
            Starphone.log("read " + this.nBytesRead + " from AudioInputStream");
        }
        if (this.nBytesRead > 0) {
            this.timestamp += 20;
            return this.abData;
        }
        return new byte[33];
    }
    
    private void initNetworkStream(final IAXFrame ifr) {
        (this.incis = new CircularInputStream(132)).add(ifr.streamData, 0, ifr.streamData.length);
        try {
            this.inais = AudioSystem.getAudioInputStream(this.incis);
            AudioFormat af = this.inais.getFormat();
            if (this.playdebug) {
                Starphone.log("primary AIS: " + this.inais);
                Starphone.log("primary format: " + af);
            }
            final DataLine.Info info = new DataLine.Info(SourceDataLine.class, af, -1);
            if (!AudioSystem.isLineSupported(info)) {
                final int nSampleSizeInBits = 16;
                final AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, af.getSampleRate(), nSampleSizeInBits, af.getChannels(), af.getChannels() * (nSampleSizeInBits / 8), af.getSampleRate(), false);
                if (this.playdebug) {
                    Starphone.log("play: source format: " + af);
                    Starphone.log("play: target format: " + targetFormat);
                }
                this.inais = AudioSystem.getAudioInputStream(targetFormat, this.inais);
                af = this.inais.getFormat();
                if (this.playdebug) {
                    Starphone.log("play: converted AIS: " + this.inais);
                    Starphone.log("play: converted format: " + af);
                }
            }
            try {
                (this.sdline = (SourceDataLine)AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, af, -1))).open(af, -1);
            }
            catch (LineUnavailableException lue) {
                lue.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (this.playdebug) {
                Starphone.log("sdline: " + this.sdline);
                Starphone.log("sdline format: " + this.sdline.getFormat());
                Starphone.log("sdline buffer size: " + this.sdline.getBufferSize());
            }
            this.sdline.start();
        }
        catch (UnsupportedAudioFileException uafe) {
            uafe.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public void playAudio(final IAXFrame ifr) {
        if (this.callState != STATE.UP) {
            return;
        }
        if (this.sdline == null) {
            this.initNetworkStream(ifr);
        }
        else {
            this.incis.add(ifr.streamData, 0, ifr.streamData.length);
        }
        this.nBytesRead = 0;
        try {
            this.nBytesRead = this.inais.read(this.playAudioData, 0, this.playAudioData.length);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        if (this.nBytesRead >= 0) {
            this.sdline.write(this.playAudioData, 0, this.nBytesRead);
        }
    }
    
    public void toggleMute() {
        this.muted = !this.muted;
        Starphone.log("Mute: " + this.muted);
    }
    
    public void setMute(final boolean muteState) {
        this.muted = muteState;
        Starphone.log("Mute: " + this.muted);
    }
    
    public void setSpeaker(final int value) {
        FloatControl gainControl = null;
        try {
            gainControl = (FloatControl)this.sdline.getControl(FloatControl.Type.MASTER_GAIN);
        }
        catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
        try {
            if (gainControl != null) {
                if (value > 0) {
                    gainControl.setValue((value - 50) * gainControl.getMaximum() / 50.0f);
                }
                else {
                    gainControl.setValue(gainControl.getMinimum());
                }
            }
        }
        catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }
    
    public void setMic(final int value) {
    }
    
    public void setPeerAddress(final String s) {
        this.peerAddress = s;
    }
    
    public void setTxAddress(final String s) {
        this.txAddress = s;
    }
    
    public void setTxPort(final String s) {
        this.txPort = new Integer(s);
    }
    
    public static void addCall(final IAXCall ic) {
        if (ic.scallno == IAXCall.callarray.size()) {
            IAXCall.callarray.add(ic);
        }
        else {
            IAXCall.callarray.setElementAt(ic, ic.scallno);
        }
    }
    
    public static short Dial(final String h, final String callednumber) {
        final IAXCall ic = new IAXCall(h);
        if (callednumber.equals("ad") || callednumber.startsWith("chatreg") || callednumber.startsWith("?") || callednumber.startsWith("register")) {
            ic.metacall = true;
        }
        addCall(ic);
        final LinkedList<IAXIE> ieList = new LinkedList<IAXIE>();
        ieList.add(new IAXIE(IAXIE.IE.VERSION, (short)2));
        ieList.add(new IAXIE(IAXIE.IE.CALLINGNAME, Starphone.username));
        ieList.add(new IAXIE(IAXIE.IE.FORMAT, 2));
        ieList.add(new IAXIE(IAXIE.IE.CAPABILITY, 2));
        ieList.add(new IAXIE(IAXIE.IE.USERNAME, Starphone.username));
        ieList.add(new IAXIE(IAXIE.IE.CALLEDNUMBER, callednumber));
        ieList.add(new IAXIE(IAXIE.IE.DNID, callednumber));
        final IAXFullFrame ifr = new IAXFullFrame(ic, IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.NEW.ordinal(), ieList);
        networkThread.qadd(new qent(ic, ifr));
        return ic.scallno;
    }
    
    public static Vector<IAXCall> getCallarray() {
        if (IAXCall.callarray == null) {
            IAXCall.callarray = new Vector<IAXCall>();
        }
        return IAXCall.callarray;
    }
    
    public static void playDTMF(final char c) {
        final int[] horizontalFrequencies = { 1209, 1336, 1477, 1633 };
        final int[] verticalFrequencies = { 697, 770, 852, 941 };
        int x = 0;
        int y = 0;
        if (c == '*') {
            x = 0;
            y = 3;
        }
        else if (c == '#') {
            x = 2;
            y = 3;
        }
        else if (c > '/' && c < ':') {
            final int i = c - '0';
            if (i % 3 == 0) {
                x = 2;
            }
            else {
                x = i % 3 - 1;
            }
            if (i == 0) {
                x = 1;
                y = 3;
            }
            else if (i < 4) {
                y = 0;
            }
            else if (i < 7) {
                y = 1;
            }
            else {
                y = 2;
            }
        }
        System.out.println("c = " + c + ": (x,y) = (" + x + "," + y + ")");
        playTwoTones(horizontalFrequencies[x], verticalFrequencies[y]);
    }
    
    public static void playTwoTones(final int f1, final int f2) {
        playTwoTones(f1, f2, 1);
    }
    
    public static void playTwoTones(final int f1, final int f2, final int seconds) {
        final float sampleRate = 16000.0f;
        final int sampleSizeInBits = 16;
        final int channels = 1;
        final boolean signed = true;
        final boolean bigEndian = true;
        final byte[] audioData = new byte[4000 * seconds];
        final InputStream bais = new ByteArrayInputStream(audioData);
        final ShortBuffer sb = ByteBuffer.wrap(audioData).asShortBuffer();
        final AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        final AudioInputStream ais = new AudioInputStream(bais, af, audioData.length / af.getFrameSize());
        final DataLine.Info dli = new DataLine.Info(SourceDataLine.class, af);
        SourceDataLine sdl = null;
        try {
            sdl = (SourceDataLine)AudioSystem.getLine(dli);
            sdl.open(af);
            sdl.start();
        }
        catch (LineUnavailableException lue) {
            lue.printStackTrace();
        }
        for (int i = 0; i < audioData.length / 2; ++i) {
            final double time = i / sampleRate;
            final double sinValue = (Math.sin(6.283185307179586 * f1 * time) + Math.sin(6.283185307179586 * f2 * time)) / 2.0;
            sb.put((short)(16000.0 * sinValue));
        }
        int nBytesRead = 0;
        try {
            nBytesRead = ais.read(audioData, 0, audioData.length);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        for (int j = 0; j < 8; ++j) {
            if (nBytesRead >= 0) {
                final int ret = sdl.write(audioData, 0, nBytesRead);
            }
        }
    }
    
    enum STATE
    {
        DOWN, 
        RINGING, 
        HOLD, 
        UP;
    }
}
