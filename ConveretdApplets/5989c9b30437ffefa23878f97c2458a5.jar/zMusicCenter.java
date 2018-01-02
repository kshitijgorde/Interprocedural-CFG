import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class zMusicCenter extends Applet implements ActionListener, Runnable
{
    Dialog dialog;
    Button button1;
    Button button2;
    AudioClip audioclip;
    Thread mythred;
    String audiofile;
    
    public void init() {
        (this.dialog = new Dialog((Frame)this.getParent(), "Music Ceneter")).setBounds(5, 45, 160, 70);
        this.dialog.setLayout(null);
        this.dialog.setBackground(Color.black);
        (this.button1 = new Button("Play Music")).setBounds(10, 27, 70, 20);
        this.button1.addActionListener(this);
        this.dialog.add(this.button1);
        (this.button2 = new Button("Stop Music")).setBounds(80, 27, 70, 20);
        this.button2.addActionListener(this);
        this.dialog.add(this.button2);
        this.dialog.show();
        this.audiofile = this.getParameter("soundfile");
        this.audioclip = this.getAudioClip(this.getCodeBase(), this.audiofile);
    }
    
    public void actionPerformed(final ActionEvent actionevent) {
        if (actionevent.getActionCommand().equals("Play Music")) {
            this.audioclip.loop();
        }
        if (actionevent.getActionCommand().equals("Stop Music")) {
            this.audioclip.stop();
        }
    }
    
    public void start() {
        (this.mythred = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(100L);
                this.showStatus("Applet Zmei Music Center by Plamen Gelev-Unregistered");
            }
            catch (Exception ex) {}
        }
    }
    
    public void stop() {
        this.mythred.stop();
    }
}
