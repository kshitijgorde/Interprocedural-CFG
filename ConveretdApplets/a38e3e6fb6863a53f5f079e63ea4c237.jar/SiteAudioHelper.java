import javax.sound.midi.ControllerEventListener;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SiteAudioHelper extends Applet
{
    Sequencer sequencer;
    Sequence mySeq;
    
    public SiteAudioHelper() {
        this.sequencer = null;
        this.mySeq = null;
    }
    
    @Override
    public void start() {
        final String parameter = this.getParameter("MIDIFILE");
        try {
            (this.sequencer = (Sequencer)MidiSystem.getMidiDevice(MidiSystem.getMidiDeviceInfo()[0])).open();
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(parameter);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = resourceAsStream.read(array)) != -1) {
                byteArrayOutputStream.write(array, 0, read);
            }
            this.sequencer.setSequence(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            this.sequencer.addControllerEventListener(new MyController(), new int[] { 0 });
            this.sequencer.start();
        }
        catch (Exception ex) {
            System.out.println("ERROR! " + ex);
        }
    }
    
    public void run() {
    }
}
