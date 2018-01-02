import java.awt.Window;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.CardLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class anagram extends Applet implements Runnable
{
    public Thread zzzsc;
    public boolean zzztc;
    private zzzb zzzuc;
    private CardLayout zzzvc;
    private Panel zzzwc;
    private TextArea zzzxc;
    private Button zzzyc;
    private Button zzzzc;
    public Button zzzad;
    private Button zzzbd;
    private TextField zzzcd;
    private Choice zzzdd;
    private Choice zzzed;
    private Frame zzzfd;
    private zzzxe zzzgd;
    public boolean zzzhd;
    public boolean zzzid;
    public boolean zzzjd;
    private zzzhb zzzkd;
    public String zzzld;
    public boolean zzzmd;
    public String zzznd;
    public int zzzod;
    public int zzzpd;
    public int zzzqd;
    public int zzzrd;
    public int zzzsd;
    public int zzztd;
    public int zzzud;
    public int zzzvd;
    public int zzzwd;
    public int zzzxd;
    public int zzzyd;
    public int zzzzd;
    public int zzzae;
    private final String zzzbe = "filename";
    private final String zzzce = "msmformat";
    private final String zzzde = "secret";
    private final String zzzee = "compdictsize";
    private final String zzzfe = "back1r";
    private final String zzzge = "back1g";
    private final String zzzhe = "back1b";
    private final String zzzie = "back2r";
    private final String zzzje = "back2g";
    private final String zzzke = "back2b";
    private final String zzzle = "fore1r";
    private final String zzzme = "fore1g";
    private final String zzzne = "fore1b";
    private final String zzzoe = "fore2r";
    private final String zzzpe = "fore2g";
    private final String zzzqe = "fore2b";
    
    public void stop() {
        if (this.zzzsc != null) {
            this.zzzsc.stop();
            this.zzzsc = null;
        }
    }
    
    private static String zzzrc(String s) {
        s = s.toLowerCase();
        final int length = s.length();
        final char[] charArray = s.toCharArray();
        s = "";
        for (final char c : charArray) {
            if (c >= 'a' && c <= 'z') {
                s += c;
            }
            else {
                switch (c) {
                    case '\u00e0':
                    case '\u00e2':
                    case '\u00e4': {
                        s += 'a';
                        break;
                    }
                    case '\u00e7': {
                        s += 'c';
                        break;
                    }
                    case '\u00e8':
                    case '\u00e9':
                    case '\u00ea':
                    case '\u00eb': {
                        s += 'e';
                        break;
                    }
                    case '\u00ee':
                    case '\u00ef': {
                        s += 'i';
                        break;
                    }
                    case '\u00f4':
                    case '\u00f6': {
                        s += 'o';
                        break;
                    }
                    case '\u00f9':
                    case '\u00fb':
                    case '\u00fc': {
                        s += 'u';
                        break;
                    }
                }
            }
        }
        return s;
    }
    
    void zzzpc(final String[] array) {
        final String zzzoc = this.zzzoc("filename", array);
        if (zzzoc != null) {
            this.zzzld = zzzoc;
        }
        final String zzzoc2 = this.zzzoc("msmformat", array);
        if (zzzoc2 != null) {
            this.zzzmd = Boolean.valueOf(zzzoc2);
        }
        final String zzzoc3 = this.zzzoc("secret", array);
        if (zzzoc3 != null) {
            this.zzznd = zzzoc3;
        }
        final String zzzoc4 = this.zzzoc("compdictsize", array);
        if (zzzoc4 != null) {
            this.zzzod = Integer.valueOf(zzzoc4);
        }
        final String zzzoc5 = this.zzzoc("back1r", array);
        if (zzzoc5 != null) {
            this.zzzpd = Integer.valueOf(zzzoc5);
        }
        final String zzzoc6 = this.zzzoc("back1g", array);
        if (zzzoc6 != null) {
            this.zzzqd = Integer.valueOf(zzzoc6);
        }
        final String zzzoc7 = this.zzzoc("back1b", array);
        if (zzzoc7 != null) {
            this.zzzrd = Integer.valueOf(zzzoc7);
        }
        final String zzzoc8 = this.zzzoc("back2r", array);
        if (zzzoc8 != null) {
            this.zzzsd = Integer.valueOf(zzzoc8);
        }
        final String zzzoc9 = this.zzzoc("back2g", array);
        if (zzzoc9 != null) {
            this.zzztd = Integer.valueOf(zzzoc9);
        }
        final String zzzoc10 = this.zzzoc("back2b", array);
        if (zzzoc10 != null) {
            this.zzzud = Integer.valueOf(zzzoc10);
        }
        final String zzzoc11 = this.zzzoc("fore1r", array);
        if (zzzoc11 != null) {
            this.zzzvd = Integer.valueOf(zzzoc11);
        }
        final String zzzoc12 = this.zzzoc("fore1g", array);
        if (zzzoc12 != null) {
            this.zzzwd = Integer.valueOf(zzzoc12);
        }
        final String zzzoc13 = this.zzzoc("fore1b", array);
        if (zzzoc13 != null) {
            this.zzzxd = Integer.valueOf(zzzoc13);
        }
        final String zzzoc14 = this.zzzoc("fore2r", array);
        if (zzzoc14 != null) {
            this.zzzyd = Integer.valueOf(zzzoc14);
        }
        final String zzzoc15 = this.zzzoc("fore2g", array);
        if (zzzoc15 != null) {
            this.zzzzd = Integer.valueOf(zzzoc15);
        }
        final String zzzoc16 = this.zzzoc("fore2b", array);
        if (zzzoc16 != null) {
            this.zzzae = Integer.valueOf(zzzoc16);
        }
    }
    
    public anagram() {
        this.zzzsc = null;
        this.zzztc = false;
        this.zzzhd = false;
        this.zzzld = "compfrench.txt";
        this.zzzmd = true;
        this.zzznd = "";
        this.zzzod = 0;
        this.zzzpd = 128;
        this.zzzqd = 152;
        this.zzzrd = 176;
        this.zzzsd = 192;
        this.zzztd = 204;
        this.zzzud = 216;
        this.zzzvd = 0;
        this.zzzwd = 0;
        this.zzzxd = 0;
        this.zzzyd = 0;
        this.zzzzd = 0;
        this.zzzae = 0;
    }
    
    public static void zzzqc(final Panel panel, final Component component, final GridBagLayout gridBagLayout, final GridBagConstraints gridBagConstraints, final int gridx, final int gridy, final int gridwidth, final int gridheight) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagLayout.setConstraints(component, gridBagConstraints);
        panel.add(component);
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "filename", "String", "Filename of word list to read in" }, { "msmformat", "boolean", "msmformat is a simple compression format, else one word per line" }, { "secret", "String", "when this is correct, code can run off my home page" }, { "compdictsize", "int", "if msmformat compression is used, this specifies size of word list in bytes" }, { "back1r", "int", "main background color red component 0 - 255" }, { "back1g", "int", "main background color green component 0 - 255" }, { "back1b", "int", "main background color blue component 0 - 255" }, { "back2r", "int", "minor background color red component 0 - 255" }, { "back2g", "int", "minor background color green component 0 - 255" }, { "back2b", "int", "minor background color blue component 0 - 255" }, { "fore1r", "int", "main foreground color red component 0 - 255" }, { "fore1g", "int", "main foreground color green component 0 - 255" }, { "fore1b", "int", "main foreground color blue component 0 - 255" }, { "fore2r", "int", "minor foreground color red component 0 - 255" }, { "fore2g", "int", "minor foreground color green component 0 - 255" }, { "fore2b", "int", "minor foreground color blue component 0 - 255" } };
    }
    
    public void destroy() {
        this.zzzfd.dispose();
        ((Window)this.zzzgd).dispose();
    }
    
    String zzzoc(final String s, final String[] array) {
        if (array == null) {
            return this.getParameter(s);
        }
        final String string = s + "=";
        String s2 = null;
        final int length = string.length();
        try {
            int i = 0;
            while (i < array.length) {
                if (string.equalsIgnoreCase(array[i].substring(0, length))) {
                    s2 = array[i].substring(length);
                    if (!s2.startsWith("\"")) {
                        break;
                    }
                    s2 = s2.substring(1);
                    if (s2.endsWith("\"")) {
                        s2 = s2.substring(0, s2.length() - 1);
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public static void main(final String[] array) {
        final zzzye zzzye = new zzzye("Anagrammes");
        ((Window)zzzye).show();
        ((Component)zzzye).hide();
        ((Component)zzzye).resize(((Container)zzzye).insets().left + ((Container)zzzye).insets().right + 640, ((Container)zzzye).insets().top + ((Container)zzzye).insets().bottom + 480);
        final anagram anagram = new anagram();
        ((Container)zzzye).add("Center", anagram);
        anagram.zzztc = true;
        anagram.zzzpc(array);
        anagram.init();
        anagram.start();
        ((Window)zzzye).show();
    }
    
    public void start() {
        if (this.zzzsc == null) {
            (this.zzzsc = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: anagram\r\nAuthor: Martin Mamo\r\nCreated with Microsoft Visual J++ Version 1.1";
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals("  Aide  ")) {
            this.zzzyc.disable();
            this.zzzfd.resize(460, 360);
            this.zzzfd.show();
        }
        else if (o.equals("Rechercher ")) {
            this.zzzzc.disable();
            this.zzzid = true;
        }
        else if (o.equals("   Plus d'anagrammes   ")) {
            this.zzzad.disable();
            this.zzzid = true;
        }
        else if (o.equals("Stop")) {
            this.zzzbd.disable();
            this.zzzhd = true;
            this.zzzid = true;
        }
        else if (event.target.equals(this.zzzed)) {
            if (this.zzzed.getSelectedIndex() + 1 > this.zzzdd.getSelectedIndex() + 1) {
                this.zzzdd.select(15);
            }
        }
        else {
            if (!event.target.equals(this.zzzdd)) {
                return super.action(event, o);
            }
            if (this.zzzdd.getSelectedIndex() + 1 < this.zzzed.getSelectedIndex() + 1) {
                this.zzzed.select(0);
            }
        }
        return true;
    }
    
    public void run() {
        try {
            if (!zzzwf.zzztf) {
                this.zzzkd = null;
                zzzwf.zzzvf = true;
                final zzzpf[] array = new zzzpf[101];
                int n = 0;
                do {
                    array[n] = new zzzpf();
                } while (++n < 101);
                this.zzzvc.show(this.zzzwc, "fuelbar");
                final zzzgf zzzgf = new zzzgf(this, this.zzzuc);
                int n2;
                if (this.zzzmd) {
                    n2 = zzzgf.zzzbf(array);
                }
                else {
                    n2 = zzzgf.zzzze(array);
                }
                final String[] array2 = new String[n2];
                int n3 = 0;
                int n4 = 100;
                do {
                    final zzzpf zzzpf = array[n4];
                    zzzpf.zzzif();
                    while (zzzpf.zzzlf()) {
                        array2[n3++] = zzzpf.zzzhf();
                    }
                } while (--n4 > 0);
                this.zzzkd = new zzzhb(this, array2, n2, this.zzzxc);
                this.repaint();
                zzzwf.zzztf = true;
            }
            this.zzzvc.show(this.zzzwc, "guiinterface");
            this.repaint();
            if (!this.zzznd.equals("apple")) {
                final String s = "                                                       ";
                if (this.zzztc) {
                    throw new zzzre(s);
                }
                final String lowerCase = this.getDocumentBase().toString().toLowerCase();
                if (lowerCase.indexOf("martin") == -1 && lowerCase.indexOf("barbery") == -1 && lowerCase.indexOf("martin") == -1) {
                    throw new zzzre(s);
                }
            }
            while (true) {
                this.zzzad.disable();
                this.zzzbd.disable();
                this.zzzcd.enable();
                this.zzzdd.enable();
                this.zzzed.enable();
                this.zzzid = false;
                this.zzzzc.enable();
                while (!this.zzzid) {
                    Thread.sleep(100L);
                }
                this.zzzhd = false;
                this.zzzbd.enable();
                this.zzzcd.disable();
                this.zzzdd.disable();
                this.zzzed.disable();
                final String zzzrc = zzzrc(this.zzzcd.getText());
                try {
                    this.zzzkd.zzzi(zzzrc, this.zzzed.getSelectedIndex() + 1, this.zzzdd.getSelectedIndex() + 1);
                }
                catch (zzzxf zzzxf) {
                    System.out.println("nonfatalproblem exception successfully caught!");
                    this.zzzjd = false;
                    this.zzzgd.zzzte("Input Problem", "Sorry, your phrase was too long", "for this program to anagram.", "Please enter a different one", "and try again.");
                    this.zzzgd.zzzse();
                    while (!this.zzzjd) {
                        Thread.sleep(100L);
                    }
                }
            }
        }
        catch (ThreadDeath threadDeath) {
            System.out.println("Continuer");
            throw threadDeath;
        }
        catch (Throwable t) {
            System.out.println("Erreur" + t.toString() + " has reared its ugly head!");
            final String s2 = (t.getMessage() != null) ? t.getMessage() : "e.getMessage() returned null";
            if (t instanceof NullPointerException) {
                this.zzzgd.zzzte("Exception Generated", "Sorry, this applet needs more memory or I've messed up.", "This applet will now stop.", t.toString(), s2);
            }
            else {
                this.zzzgd.zzzte("Exception Generated", "Sorry, an exception has occurred.", "This applet will now stop.", t.toString(), s2);
            }
            this.zzzgd.zzzse();
            this.stop();
        }
    }
    
    public void init() {
        if (!this.zzztc) {
            this.zzzpc(null);
        }
        zzznc.zzzyb = new Color(this.zzzpd, this.zzzqd, this.zzzrd);
        zzznc.zzzzb = new Color(this.zzzsd, this.zzztd, this.zzzud);
        zzznc.zzzac = new Color(this.zzzvd, this.zzzwd, this.zzzxd);
        zzznc.zzzbc = new Color(this.zzzyd, this.zzzzd, this.zzzae);
        this.setBackground(zzznc.zzzyb);
        zzzwf.zzztf = false;
        zzzwf.zzzuf = 0;
        zzzwf.zzzvf = false;
        this.setLayout(new GridLayout(1, 1));
        (this.zzzwc = new Panel()).setBackground(zzznc.zzzyb);
        this.zzzvc = new CardLayout();
        this.zzzwc.setLayout(this.zzzvc);
        this.setForeground(zzznc.zzzac);
        final Panel panel = new Panel();
        panel.setBackground(zzznc.zzzyb);
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        final Label label = new Label("Entre ici les lettres (nom, mots, phrase) a anagrammer : ", 0);
        label.setBackground(zzznc.zzzyb);
        (this.zzzcd = new TextField()).setBackground(zzznc.zzzzb);
        this.zzzcd.setForeground(zzznc.zzzbc);
        final Label label2 = new Label("Trouver tous les anagrammes contenant ", 0);
        final Label label3 = new Label("entre", 0);
        final Label label4 = new Label("et", 0);
        final Label label5 = new Label("mot(s).", 0);
        label2.setBackground(zzznc.zzzyb);
        label3.setBackground(zzznc.zzzyb);
        label4.setBackground(zzznc.zzzyb);
        label5.setBackground(zzznc.zzzyb);
        (this.zzzed = new Choice()).addItem("1");
        this.zzzed.addItem("2");
        this.zzzed.addItem("3");
        this.zzzed.addItem("4");
        this.zzzed.addItem("5");
        this.zzzed.addItem("6");
        this.zzzed.addItem("7");
        this.zzzed.addItem("8");
        this.zzzed.addItem("9");
        this.zzzed.addItem("10");
        this.zzzed.addItem("11");
        this.zzzed.addItem("12");
        this.zzzed.addItem("13");
        this.zzzed.addItem("14");
        this.zzzed.addItem("15");
        this.zzzed.select(0);
        this.zzzed.setBackground(zzznc.zzzzb);
        this.zzzed.setForeground(zzznc.zzzbc);
        (this.zzzdd = new Choice()).addItem("1");
        this.zzzdd.addItem("2");
        this.zzzdd.addItem("3");
        this.zzzdd.addItem("4");
        this.zzzdd.addItem("5");
        this.zzzdd.addItem("6");
        this.zzzdd.addItem("7");
        this.zzzdd.addItem("8");
        this.zzzdd.addItem("9");
        this.zzzdd.addItem("10");
        this.zzzdd.addItem("11");
        this.zzzdd.addItem("12");
        this.zzzdd.addItem("13");
        this.zzzdd.addItem("14");
        this.zzzdd.addItem("15");
        this.zzzdd.addItem("16 & plus");
        this.zzzdd.select(7);
        this.zzzdd.setBackground(zzznc.zzzzb);
        this.zzzdd.setForeground(zzznc.zzzbc);
        final Panel panel2 = new Panel();
        panel2.setBackground(zzznc.zzzyb);
        final GridBagLayout layout2 = new GridBagLayout();
        panel2.setLayout(layout2);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 100.0;
        zzzqc(panel2, label, layout2, gridBagConstraints, 0, 0, 50, 1);
        zzzqc(panel2, label2, layout2, gridBagConstraints, 0, 2, 50, 1);
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 17;
        final Panel panel3 = new Panel();
        panel3.setBackground(zzznc.zzzyb);
        panel3.setLayout(new FlowLayout(0));
        panel3.add(label3);
        panel3.add(this.zzzed);
        panel3.add(label4);
        panel3.add(this.zzzdd);
        panel3.add(label5);
        zzzqc(panel2, panel3, layout2, gridBagConstraints, 0, 3, 50, 1);
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 0.0;
        zzzqc(panel2, this.zzzcd, layout2, gridBagConstraints, 0, 1, 30, 1);
        final Panel panel4 = new Panel();
        panel4.setBackground(zzznc.zzzyb);
        final GridBagLayout layout3 = new GridBagLayout();
        panel4.setLayout(layout3);
        this.zzzyc = new Button("  Aide  ");
        this.zzzzc = new Button("Rechercher ");
        this.zzzad = new Button("   Plus d'anagrammes   ");
        this.zzzbd = new Button("Stop");
        this.zzzyc.setBackground(zzznc.zzzyb);
        this.zzzzc.setBackground(zzznc.zzzyb);
        this.zzzad.setBackground(zzznc.zzzyb);
        this.zzzbd.setBackground(zzznc.zzzyb);
        this.zzzyc.setForeground(zzznc.zzzac);
        this.zzzzc.setForeground(zzznc.zzzac);
        this.zzzad.setForeground(zzznc.zzzac);
        this.zzzbd.setForeground(zzznc.zzzac);
        this.zzzyc.enable();
        this.zzzzc.enable();
        this.zzzad.disable();
        this.zzzbd.disable();
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        zzzqc(panel4, this.zzzyc, layout3, gridBagConstraints, 0, 0, 1, 1);
        zzzqc(panel4, this.zzzzc, layout3, gridBagConstraints, 0, 1, 1, 1);
        zzzqc(panel4, this.zzzad, layout3, gridBagConstraints, 0, 2, 1, 1);
        zzzqc(panel4, this.zzzbd, layout3, gridBagConstraints, 0, 3, 1, 1);
        (this.zzzxc = new TextArea()).setBackground(zzznc.zzzzb);
        this.zzzxc.setForeground(zzznc.zzzbc);
        gridBagConstraints.insets = new Insets(14, 4, 4, 4);
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 0.0;
        zzzqc(panel, panel2, layout, gridBagConstraints, 0, 0, 1, 1);
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        zzzqc(panel, panel4, layout, gridBagConstraints, 1, 0, 1, 1);
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        zzzqc(panel, this.zzzxc, layout, gridBagConstraints, 0, 1, 2, 1);
        this.zzzfd = (Frame)new zzzsf(this.zzzyc);
        this.zzzgd = new zzzxe(this);
        ((Component)(this.zzzuc = new zzzb())).setBackground(zzznc.zzzyb);
        this.zzzwc.add("fuelbar", (Component)this.zzzuc);
        this.zzzwc.add("guiinterface", panel);
        this.add(this.zzzwc);
        this.zzzvc.show(this.zzzwc, "fuelbar");
    }
}
