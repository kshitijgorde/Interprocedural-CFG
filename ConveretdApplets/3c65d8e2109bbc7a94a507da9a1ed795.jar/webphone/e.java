// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Component;
import a.a.a.a.b;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import a.a.a.a.a;
import javax.swing.JFrame;

public class e extends JFrame
{
    a do;
    JLabel char;
    JComboBox if;
    JLabel try;
    JComboBox a;
    JButton else;
    JButton byte;
    JSlider goto;
    JSlider case;
    aw int;
    webphone for;
    JButton new;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    static /* synthetic */ Class class$javax$sound$sampled$TargetDataLine;
    
    public void for() {
        try {
            this.if.addItem("Default");
            this.a.addItem("Default");
            this.if.setSelectedItem("Default");
            this.a.setSelectedItem("Default");
            final Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
            final Line.Info info = new Line.Info((e.class$javax$sound$sampled$TargetDataLine == null) ? (e.class$javax$sound$sampled$TargetDataLine = class$("javax.sound.sampled.TargetDataLine")) : e.class$javax$sound$sampled$TargetDataLine);
            for (int i = 0; i < mixerInfo.length; ++i) {
                final Mixer mixer = AudioSystem.getMixer(mixerInfo[i]);
                if (mixer != null) {
                    if (info != null) {
                        if (mixer.isLineSupported(info)) {
                            this.if.addItem(mixerInfo[i].getName());
                        }
                    }
                }
            }
            this.if.setSelectedItem(this.int.try);
            final Line.Info info2 = new Line.Info((e.class$javax$sound$sampled$SourceDataLine == null) ? (e.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : e.class$javax$sound$sampled$SourceDataLine);
            for (int j = 0; j < mixerInfo.length; ++j) {
                final Mixer mixer2 = AudioSystem.getMixer(mixerInfo[j]);
                if (mixer2 != null) {
                    if (info2 != null) {
                        if (mixer2.isLineSupported(info2)) {
                            this.a.addItem(mixerInfo[j].getName());
                        }
                    }
                }
            }
            this.a.setSelectedItem(this.int.c);
        }
        catch (Exception ex) {
            this.int.a(2, "LoadDevices", ex);
        }
    }
    
    public void a() {
        try {
            if (this.for.color_background.length() > 0) {
                this.setBackground(this.int.a(this.for.color_background, this.getBackground()));
                this.getContentPane().setBackground(this.int.a(this.for.color_background, this.getBackground()));
            }
            if (this.for.color_label_background.length() > 0) {
                this.char.setBackground(this.int.a(this.for.color_label_background, this.char.getBackground()));
                this.try.setBackground(this.int.a(this.for.color_label_background, this.try.getBackground()));
            }
            if (this.for.color_label_foreground.length() > 0) {
                this.char.setForeground(this.int.a(this.for.color_label_foreground, this.char.getForeground()));
                this.try.setForeground(this.int.a(this.for.color_label_foreground, this.try.getForeground()));
            }
            if (this.for.color_edit_background.length() > 0) {
                this.if.setBackground(this.int.a(this.for.color_edit_background, this.if.getBackground()));
                this.a.setBackground(this.int.a(this.for.color_edit_background, this.a.getBackground()));
            }
            if (this.for.color_edit_foreground.length() > 0) {
                this.if.setForeground(this.int.a(this.for.color_edit_foreground, this.if.getForeground()));
                this.a.setForeground(this.int.a(this.for.color_edit_foreground, this.a.getForeground()));
            }
            if (this.for.color_buton_background.length() > 0) {
                this.else.setBackground(this.int.a(this.for.color_buton_background, this.else.getBackground()));
                this.byte.setBackground(this.int.a(this.for.color_buton_background, this.byte.getBackground()));
                this.new.setBackground(this.int.a(this.for.color_buton_background, this.new.getBackground()));
            }
            if (this.for.color_buton_foreground.length() > 0) {
                this.else.setForeground(this.int.a(this.for.color_buton_foreground, this.else.getForeground()));
                this.byte.setForeground(this.int.a(this.for.color_buton_foreground, this.byte.getForeground()));
                this.new.setForeground(this.int.a(this.for.color_buton_foreground, this.new.getForeground()));
            }
            if (this.for.color_other_background.length() > 0) {
                this.goto.setBackground(this.int.a(this.for.color_other_background, this.goto.getBackground()));
                this.try.setBackground(this.int.a(this.for.color_other_background, this.try.getBackground()));
            }
            if (this.for.color_other_foreground.length() > 0) {
                this.goto.setForeground(this.int.a(this.for.color_other_foreground, this.goto.getForeground()));
                this.case.setForeground(this.int.a(this.for.color_other_foreground, this.case.getForeground()));
            }
            this.repaint();
        }
        catch (Exception ex) {
            this.int.a(3, "audiodevcolors", ex);
        }
    }
    
    public e(final aw int1, final webphone for1) {
        this.do = new a();
        this.char = new JLabel();
        this.if = new JComboBox();
        this.try = new JLabel();
        this.a = new JComboBox();
        this.else = new JButton();
        this.byte = new JButton();
        this.goto = new JSlider();
        this.case = new JSlider();
        this.int = null;
        this.for = null;
        this.new = new JButton();
        try {
            this.int = int1;
            this.for = for1;
            this.if();
            this.int.a(this.char);
            this.int.a(this.try);
            this.int.a(this.else);
            this.int.a(this.byte);
            this.a();
        }
        catch (Exception ex) {
            this.int.a(2, "audiodevq3342", ex);
        }
        this.for();
    }
    
    private void if() throws Exception {
        try {
            this.getContentPane().setLayout(this.do);
            this.char.setText("Microphone:");
            this.else.addActionListener(new bx(this));
            this.byte.setToolTipText("Cancel");
            this.byte.setText("Close");
            this.byte.addActionListener(new ae(this));
            this.setTitle("Audio settings");
            this.goto.addChangeListener(new av(this));
            this.case.addChangeListener(new b5(this));
            this.new.setToolTipText("Mute/Activate");
            this.new.setText("Mute");
            this.new.addActionListener(new b7(this));
            this.else.setToolTipText("Save");
            this.getContentPane().add(this.char, new b(10, 16, -1, -1));
            this.do.if(335);
            this.do.a(179);
            this.else.setText("OK");
            this.try.setText("Speaker:");
            this.goto.setToolTipText("Microphone volume");
            this.case.setToolTipText("Speaker volume");
            this.getContentPane().add(this.if, new b(91, 13, 230, -1));
            this.getContentPane().add(this.goto, new b(86, 37, 239, -1));
            this.getContentPane().add(this.a, new b(91, 79, 230, -1));
            this.getContentPane().add(this.try, new b(10, 81, -1, -1));
            this.getContentPane().add(this.case, new b(84, 103, 241, -1));
            this.getContentPane().add(this.byte, new b(239, 145, 83, 23));
            this.getContentPane().add(this.else, new b(148, 145, 83, 23));
            this.getContentPane().add(this.new, new b(7, 145, 83, -1));
        }
        catch (Exception ex) {
            this.int.a(2, "devjbInit", ex);
        }
        this.getContentPane().setBackground(SystemColor.control);
    }
    
    public void do() {
        this.int.a(4, "EVENT,supported playback info:");
        int n = 0;
        try {
            n = 1;
            n = 2;
            final Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
            n = 3;
            for (int i = 0; i < mixerInfo.length; ++i) {
                n = 4;
                final Mixer mixer = AudioSystem.getMixer(mixerInfo[i]);
                n = 5;
                try {
                    mixer.open();
                }
                catch (Exception ex2) {}
                n = 6;
                n = 7;
                final Line.Info[] targetLineInfo = mixer.getTargetLineInfo();
                n = 8;
                for (int j = 0; j < targetLineInfo.length; ++j) {
                    n = 9;
                    final Line.Info info = targetLineInfo[j];
                    n = 10;
                    this.int.a(4, "EVENT, record supported info " + this.int.c(i) + "-" + this.int.c(j) + ": " + info.toString());
                }
            }
        }
        catch (Exception ex) {
            this.int.a(3, "audioplayer.PrintSupportedFormats " + this.int.c(n), ex);
        }
    }
    
    public boolean int() {
        if (this.int.eK < 4) {
            return true;
        }
        try {
            int n = 0;
            final AudioFormat audioFormat = new AudioFormat(8000.0f, 16, 1, true, false);
            SourceDataLine sourceDataLine = null;
            boolean b = false;
            final DataLine.Info info = new DataLine.Info((e.class$javax$sound$sampled$SourceDataLine == null) ? (e.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : e.class$javax$sound$sampled$SourceDataLine, audioFormat);
            this.do();
            Label_0366: {
                if (this.int.c.length() >= 1) {
                    if (!this.int.c.equals("Default")) {
                        int n2 = 109;
                        try {
                            final Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
                            int i = 0;
                            while (i < mixerInfo.length) {
                                if (mixerInfo[i].getName().equals(this.int.c) || i == mixerInfo.length - 1) {
                                    final Mixer mixer = AudioSystem.getMixer(mixerInfo[i]);
                                    if (mixer == null) {
                                        break;
                                    }
                                    n2 = 110;
                                    sourceDataLine = (SourceDataLine)mixer.getLine(info);
                                    if (sourceDataLine != null) {
                                        n2 = 111;
                                        sourceDataLine.open(audioFormat);
                                        n2 = 112;
                                        n2 = 113;
                                        b = true;
                                        break;
                                    }
                                    break;
                                }
                                else {
                                    ++i;
                                }
                            }
                        }
                        catch (Exception ex) {
                            this.int.a(1, "sound device test cannot player (3) (" + this.int.c(n2) + ")", ex);
                            this.do();
                        }
                        break Label_0366;
                    }
                }
                try {
                    n = 5;
                    sourceDataLine = (SourceDataLine)AudioSystem.getLine(info);
                    n = 106;
                    sourceDataLine.open(audioFormat);
                    n = 3210;
                    if (sourceDataLine != null) {
                        n = 107;
                    }
                    else {
                        this.int.a(1, "ERROR,sound device test cannot player (1)");
                    }
                    b = true;
                    n = 108;
                }
                catch (Exception ex2) {
                    this.int.a(1, "sound device test cannot player (2) (" + this.int.c(n) + ")", ex2);
                    this.do();
                }
            }
            if (sourceDataLine != null) {
                try {
                    sourceDataLine.stop();
                }
                catch (Exception ex5) {}
                try {
                    if (this.int.c()) {
                        sourceDataLine.close();
                    }
                }
                catch (Exception ex6) {}
            }
            if (!b) {
                return false;
            }
        }
        catch (Exception ex7) {}
        try {
            int n3 = 0;
            final AudioFormat audioFormat2 = new AudioFormat(8000.0f, 16, 1, true, false);
            TargetDataLine targetDataLine = null;
            boolean b2 = false;
            final DataLine.Info info2 = new DataLine.Info((e.class$javax$sound$sampled$TargetDataLine == null) ? (e.class$javax$sound$sampled$TargetDataLine = class$("javax.sound.sampled.TargetDataLine")) : e.class$javax$sound$sampled$TargetDataLine, audioFormat2);
            this.do();
            Label_0766: {
                if (this.int.c.length() >= 1) {
                    if (!this.int.c.equals("Default")) {
                        int n4 = 109;
                        try {
                            final Mixer.Info[] mixerInfo2 = AudioSystem.getMixerInfo();
                            int j = 0;
                            while (j < mixerInfo2.length) {
                                if (mixerInfo2[j].getName().equals(this.int.c) || j == mixerInfo2.length - 1) {
                                    final Mixer mixer2 = AudioSystem.getMixer(mixerInfo2[j]);
                                    if (mixer2 == null) {
                                        break;
                                    }
                                    n4 = 110;
                                    targetDataLine = (TargetDataLine)mixer2.getLine(info2);
                                    if (targetDataLine != null) {
                                        n4 = 111;
                                        targetDataLine.open(audioFormat2);
                                        n4 = 112;
                                        n4 = 113;
                                        b2 = true;
                                        break;
                                    }
                                    break;
                                }
                                else {
                                    ++j;
                                }
                            }
                        }
                        catch (Exception ex3) {
                            this.int.a(1, "sound device test cannot recorder (6) (" + this.int.c(n4) + ")", ex3);
                            this.do();
                        }
                        break Label_0766;
                    }
                }
                try {
                    n3 = 5;
                    targetDataLine = (TargetDataLine)AudioSystem.getLine(info2);
                    n3 = 106;
                    targetDataLine.open(audioFormat2);
                    n3 = 3210;
                    if (targetDataLine != null) {
                        n3 = 107;
                    }
                    else {
                        this.int.a(1, "EVENT,sound device test cannot recorder (4)");
                    }
                    b2 = true;
                    n3 = 108;
                }
                catch (Exception ex4) {
                    this.int.a(1, "sound device test cannot recorder (5) (" + this.int.c(n3) + ")", ex4);
                    this.do();
                }
            }
            if (targetDataLine != null) {
                try {
                    targetDataLine.stop();
                }
                catch (Exception ex8) {}
                try {
                    if (this.int.c()) {
                        targetDataLine.close();
                    }
                }
                catch (Exception ex9) {}
            }
            return b2;
        }
        catch (Exception ex10) {
            return false;
        }
    }
    
    public void if(final ActionEvent actionEvent) {
        try {
            if (this.if.getSelectedItem() != null) {
                this.int.try = ((String)this.if.getSelectedItem()).trim();
            }
            if (this.a.getSelectedItem() != null) {
                this.int.c = ((String)this.a.getSelectedItem()).trim();
            }
            this.for.volumechanged = this.int.do();
            this.int.a(2, "EVENT, audio device changed");
        }
        catch (Exception ex) {
            this.int.a(2, "audio save", ex);
        }
        try {
            if (this.int()) {
                this.setVisible(false);
            }
        }
        catch (Exception ex2) {
            this.int.a(3, "dev1111", ex2);
        }
    }
    
    public void do(final ActionEvent actionEvent) {
        try {
            this.setVisible(false);
        }
        catch (Exception ex) {
            this.int.a(3, "dev1112", ex);
        }
    }
    
    public void a(final ChangeEvent changeEvent) {
        this.int.aW = this.int.char(this.goto.getValue());
        this.int.a(1, "EVENT,volume in set to " + this.int.c(this.goto.getValue()) + "% " + this.int.if(this.int.aW));
    }
    
    public void if(final ChangeEvent changeEvent) {
        this.int.bK = this.int.char(this.case.getValue());
        this.int.a(1, "EVENT,volume out set to " + this.int.c(this.case.getValue()) + "% " + this.int.if(this.int.bK));
    }
    
    public void a(final ActionEvent actionEvent) {
        this.for.jButton18_actionPerformed(null);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
