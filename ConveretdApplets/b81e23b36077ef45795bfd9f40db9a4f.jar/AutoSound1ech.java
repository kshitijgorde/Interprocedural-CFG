import java.util.StringTokenizer;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class AutoSound1ech extends Applet
{
    AudioClip enter_au;
    AudioClip exit_au;
    boolean loop;
    String[] enter_sounds;
    String[] exit_sounds;
    
    public void init() {
        final String parameter;
        if ((parameter = this.getParameter("ENTER.LOOP")) != null) {
            this.loop = new Boolean(parameter);
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("SOUNDS.ENTER")) != null) {
            this.enter_sounds = this.parseSounds(parameter2);
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("SOUNDS.EXIT")) != null) {
            this.exit_sounds = this.parseSounds(parameter3);
        }
    }
    
    public String[] parseSounds(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final int countTokens = stringTokenizer.countTokens();
        final String[] array = new String[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    public void start() {
        if (this.getParameter("AUTHOR").equals("Eric Harshbarger, http://www.ericharshbarger.org") && this.getParameter("COPYRIGHT").equals("AutoSound applet, Copyright 1999, Eric Harshbarger")) {
            if (this.enter_sounds != null) {
                final int n = (int)(Math.random() * this.enter_sounds.length);
                try {
                    this.enter_au = this.getAudioClip(this.getDocumentBase(), this.enter_sounds[n]);
                    if (!this.loop) {
                        this.enter_au.play();
                    }
                    else {
                        this.enter_au.loop();
                    }
                }
                catch (Exception ex) {}
            }
            else {
                this.enter_au = null;
            }
            if (this.exit_sounds == null) {
                return;
            }
            final int n2 = (int)(Math.random() * this.exit_sounds.length);
            try {
                this.exit_au = this.getAudioClip(this.getDocumentBase(), this.exit_sounds[n2]);
                return;
            }
            catch (Exception ex2) {
                return;
            }
        }
        System.out.println("AUTHOR & COPYRIGHT parameters are incorrect.");
    }
    
    public void stop() {
        if (this.enter_au != null) {
            this.enter_au.stop();
        }
        if (this.exit_au != null) {
            this.exit_au.play();
        }
    }
    
    public AutoSound1ech() {
        this.loop = false;
    }
}
