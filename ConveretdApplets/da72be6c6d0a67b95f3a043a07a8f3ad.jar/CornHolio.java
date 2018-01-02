import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CornHolio extends Applet
{
    static AudioClip sound;
    public String _sound;
    Button button;
    
    public void init() {
        final String parameter;
        final String s = ((parameter = this.getParameter("text")) != null) ? parameter : "Sound Clip";
        final String parameter2 = this.getParameter("sound");
        this._sound = parameter2;
        this._sound = ((parameter2 != null) ? this._sound : "cornholio.au");
        this.setLayout(new BorderLayout());
        this.add("Center", this.button = new Button(s));
        CornHolio.sound = this.getAudioClip(this.getCodeBase(), this._sound);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            if (CornHolio.sound != null) {
                CornHolio.sound.play();
            }
            return true;
        }
        return false;
    }
}
