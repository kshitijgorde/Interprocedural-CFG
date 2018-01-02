import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.awt.Button;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.AudioClip;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class lingo extends Applet
{
    URL theURL;
    lines headlines;
    String[] messEnglish;
    int ei;
    String[] messFrench;
    int fi;
    String[] messItalian;
    int ii;
    String[] messGerman;
    int gi;
    String[] messJapanese;
    int ji;
    Image onf;
    Image offf;
    Image ond;
    Image offd;
    Image one;
    Image offe;
    Image oni;
    Image offi;
    Image onj;
    Image offj;
    AudioClip noise;
    Boolean jap;
    Boolean fre;
    Boolean ger;
    Boolean ita;
    Boolean eng;
    MediaTracker tracker;
    
    public void init() {
        this.tracker = new MediaTracker(this);
        this.jap = Boolean.valueOf(this.getParameter("Japanese"));
        this.fre = Boolean.valueOf(this.getParameter("French"));
        this.ger = Boolean.valueOf(this.getParameter("German"));
        this.ita = Boolean.valueOf(this.getParameter("Italian"));
        this.eng = Boolean.valueOf(this.getParameter("English"));
        if (this.fre) {
            this.onf = this.getImage(this.getCodeBase(), "onf.jpg");
            this.tracker.addImage(this.onf, 0);
            this.offf = this.getImage(this.getCodeBase(), "offf.jpg");
            this.tracker.addImage(this.offf, 0);
        }
        if (this.ger) {
            this.ond = this.getImage(this.getCodeBase(), "ond.jpg");
            this.tracker.addImage(this.ond, 0);
            this.offd = this.getImage(this.getCodeBase(), "offd.jpg");
            this.tracker.addImage(this.offd, 0);
        }
        if (this.ita) {
            this.oni = this.getImage(this.getCodeBase(), "oni.jpg");
            this.tracker.addImage(this.oni, 0);
            this.offi = this.getImage(this.getCodeBase(), "offi.jpg");
            this.tracker.addImage(this.offi, 0);
        }
        if (this.eng) {
            this.one = this.getImage(this.getCodeBase(), "one.jpg");
            this.tracker.addImage(this.one, 0);
            this.offe = this.getImage(this.getCodeBase(), "offe.jpg");
            this.tracker.addImage(this.offe, 0);
        }
        if (this.jap) {
            this.onj = this.getImage(this.getCodeBase(), "onj.jpg");
            this.tracker.addImage(this.onj, 0);
            this.offj = this.getImage(this.getCodeBase(), "offj.jpg");
            this.tracker.addImage(this.offj, 0);
        }
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            return;
        }
        this.noise = this.getAudioClip(this.getCodeBase(), "NOISE.AU");
        final String parameter = this.getParameter("MessageSource");
        try {
            this.theURL = new URL(parameter);
        }
        catch (MalformedURLException ex2) {
            System.out.println("Check the MessageSouce parameter in the HTML");
            System.out.println("This URL isn't working as expected - " + this.theURL);
        }
        this.loader();
        final ibut ibut = new ibut(this.onf, this.offf, this.offf, this.noise, "French");
        ibut.resize(60, 25);
        final ibut ibut2 = new ibut(this.ond, this.offd, this.offd, this.noise, "German");
        ibut2.resize(60, 25);
        final ibut ibut3 = new ibut(this.oni, this.offi, this.offi, this.noise, "Italian");
        ibut3.resize(60, 25);
        final ibut ibut4 = new ibut(this.one, this.offe, this.offe, this.noise, "English");
        ibut4.resize(60, 25);
        final ibut ibut5 = new ibut(this.onj, this.offj, this.offj, this.noise, "Japanese");
        ibut5.resize(60, 25);
        this.headlines.resize(Integer.parseInt(this.getParameter("MessageBoxWidth")), 30);
        final Color color = new Color(0, 0, 0);
        this.setBackground(this.parseColor(this.getParameter("AppletBackGroundColor")));
        this.headlines.setBackground(this.parseColor(this.getParameter("MessageBoxColor")));
        this.headlines.setForeground(this.parseColor(this.getParameter("MessageBoxTextColor")));
        this.headlines.start();
        this.headlines.changeMessage(this.messEnglish, this.ei);
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        int gridx = 1;
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        layout.setConstraints(this.headlines, gridBagConstraints);
        this.add(this.headlines);
        if (this.fre) {
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.fill = 0;
            gridBagConstraints2.gridx = gridx;
            gridBagConstraints2.gridy = 2;
            ++gridx;
            layout.setConstraints(ibut, gridBagConstraints2);
            this.add(ibut);
        }
        if (this.ger) {
            final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.fill = 0;
            gridBagConstraints3.gridx = gridx;
            gridBagConstraints3.gridy = 2;
            ++gridx;
            layout.setConstraints(ibut2, gridBagConstraints3);
            this.add(ibut2);
        }
        if (this.ita) {
            final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            gridBagConstraints4.fill = 0;
            gridBagConstraints4.gridx = gridx;
            gridBagConstraints4.gridy = 2;
            ++gridx;
            layout.setConstraints(ibut3, gridBagConstraints4);
            this.add(ibut3);
        }
        if (this.eng) {
            final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
            gridBagConstraints5.fill = 0;
            gridBagConstraints5.gridx = gridx;
            gridBagConstraints5.gridy = 2;
            ++gridx;
            layout.setConstraints(ibut4, gridBagConstraints5);
            this.add(ibut4);
        }
        if (this.jap) {
            final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
            gridBagConstraints6.fill = 0;
            gridBagConstraints6.gridx = gridx;
            gridBagConstraints6.gridy = 2;
            ++gridx;
            layout.setConstraints(ibut5, gridBagConstraints6);
            this.add(ibut5);
        }
        System.out.println("        Thanks for loading lingo ");
        System.out.println("     Copyright 1997 - Matthew Hall");
        System.out.println("    Visit http://brightflow.com/java");
        System.out.println(" for details of this and other applets");
        System.out.println("**** e-mail matthew@brightflow.com *****");
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.changeLang((String)o);
        }
        if (event.target instanceof ibut) {
            this.changeLang((String)o);
        }
        return true;
    }
    
    protected void changeLang(final String s) {
        if (s == "French") {
            this.headlines.changeMessage(this.messFrench, this.fi);
            return;
        }
        if (s == "English") {
            this.headlines.changeMessage(this.messEnglish, this.ei);
            return;
        }
        if (s == "German") {
            this.headlines.changeMessage(this.messGerman, this.gi);
            return;
        }
        if (s == "Italian") {
            this.headlines.changeMessage(this.messItalian, this.ii);
            return;
        }
        if (s == "Japanese") {
            this.headlines.changeMessage(this.messJapanese, this.ji);
        }
    }
    
    protected boolean loader() {
        String s = new String("null");
        try {
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(this.theURL.openStream()));
            try {
                String line;
                while ((line = dataInputStream.readLine()) != null) {
                    if (line.charAt(0) == '@') {
                        if (line.charAt(1) == 'F') {
                            s = "french";
                        }
                        else if (line.charAt(1) == 'G') {
                            s = "german";
                        }
                        else if (line.charAt(1) == 'J') {
                            s = "japanese";
                        }
                        else if (line.charAt(1) == 'I') {
                            s = "italian";
                        }
                        else if (line.charAt(1) == 'E') {
                            s = "english";
                        }
                    }
                    if (s == "french") {
                        this.messFrench[this.fi] = line;
                        ++this.fi;
                    }
                    else if (s == "german") {
                        this.messGerman[this.gi] = line;
                        ++this.gi;
                    }
                    else if (s == "japanese") {
                        this.messJapanese[this.ji] = line;
                        ++this.ji;
                    }
                    else if (s == "italian") {
                        this.messItalian[this.ii] = line;
                        ++this.ii;
                    }
                    else {
                        if (s != "english") {
                            continue;
                        }
                        this.messEnglish[this.ei] = line;
                        ++this.ei;
                    }
                }
            }
            catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        }
        catch (Exception ex2) {
            System.out.println("failed to open file ");
            System.out.println("Error: " + ex2);
        }
        return true;
    }
    
    protected Color parseColor(final String s) {
        final int[] array = new int[3];
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ":");
        while (stringTokenizer.hasMoreTokens()) {
            array[n] = Integer.parseInt(stringTokenizer.nextToken());
            ++n;
        }
        return new Color(array[0], array[1], array[2]);
    }
    
    public lingo() {
        this.headlines = new lines();
        this.messEnglish = new String[25];
        this.messFrench = new String[25];
        this.messItalian = new String[25];
        this.messGerman = new String[25];
        this.messJapanese = new String[25];
    }
}
