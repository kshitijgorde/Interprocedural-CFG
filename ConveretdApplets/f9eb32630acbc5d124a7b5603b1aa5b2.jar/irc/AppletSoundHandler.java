// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.applet.Applet;

public class AppletSoundHandler implements SoundHandler
{
    private Applet _applet;
    
    public AppletSoundHandler(final Applet applet) {
        this._applet = applet;
    }
    
    public void playSound(final String s) {
        this._applet.getAudioClip(this._applet.getCodeBase(), s).play();
    }
}
