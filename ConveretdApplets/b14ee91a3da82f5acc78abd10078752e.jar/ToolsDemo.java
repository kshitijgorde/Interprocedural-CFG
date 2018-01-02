import javax.sound.midi.ControllerEventListener;
import java.io.InputStream;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ToolsDemo extends Applet
{
    public void init() {
        final String parameter = this.getParameter("URL");
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(parameter);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = resourceAsStream.read(array)) != -1) {
                byteArrayOutputStream.write(array, 0, read);
            }
            final ByteArrayInputStream sequence = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            final ToolsDemoSubClass toolsDemoSubClass = new ToolsDemoSubClass();
            final Sequencer sequencer = (Sequencer)MidiSystem.getMidiDevice(MidiSystem.getMidiDeviceInfo()[0]);
            sequencer.open();
            sequencer.setSequence(sequence);
            sequencer.addControllerEventListener(toolsDemoSubClass, new int[] { 0 });
            sequencer.start();
        }
        catch (Exception ex) {}
    }
    
    public String getAppletInfo() {
        return "Tools Demo";
    }
}
