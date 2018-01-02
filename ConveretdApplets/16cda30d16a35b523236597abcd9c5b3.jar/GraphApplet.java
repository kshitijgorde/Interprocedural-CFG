import java.awt.Event;
import java.util.Enumeration;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GraphApplet extends Applet
{
    private final String zzzdn = "5";
    private final String zzzen = "5";
    private final String zzzfn = "240";
    private final String zzzgn = "350";
    private final String zzzhn = "250";
    private final String zzzin = "5";
    private final String zzzjn = "350";
    private final String zzzkn = "350";
    private final String zzzln = "601";
    private final String zzzmn = "5";
    private final String zzznn = "50";
    private final String zzzon = "350";
    private final String zzzpn = "EEEEFF";
    private final String zzzqn = "000000";
    private final String zzzrn = "CCCCCC";
    private final String zzzsn = "000000";
    private final String zzztn = "FFFFFF";
    private final String zzzun = "000000";
    private final String zzzvn = "000000";
    private final String zzzwn = "FFFFFF";
    private final String zzzxn = "CCCCCC";
    private final String zzzyn = "000000";
    int zzzzn;
    int zzzao;
    int zzzbo;
    int zzzco;
    int zzzdo;
    int zzzeo;
    int zzzfo;
    int zzzgo;
    int zzzho;
    int zzzio;
    int zzzjo;
    int zzzko;
    int zzzlo;
    int zzzmo;
    int zzzno;
    int zzzoo;
    int zzzpo;
    int zzzqo;
    int zzzro;
    int zzzso;
    int zzzto;
    int zzzuo;
    zzzjh zzzvo;
    zzzqk zzzwo;
    public List zzzxo;
    Graphics zzzyo;
    public double zzzzo;
    public double zzzap;
    public double zzzbp;
    public double zzzcp;
    public int zzzdp;
    
    public void init() {
        this.zzzbn();
        this.setBackground(new Color(this.zzzmo));
        this.setForeground(new Color(this.zzzgo));
        this.setLayout(null);
        (this.zzzxo = new List()).setBackground(new Color(this.zzzto));
        this.zzzxo.setForeground(new Color(this.zzzuo));
        this.zzzxo.setFont(new Font("Dialog", 1, 12));
        this.zzzwo = new zzzqk(this, new Color(this.zzzmo), new Color(this.zzzgo), new Color(this.zzzno), new Color(this.zzzoo), new Color(this.zzzpo), new Color(this.zzzqo));
        this.zzzvo = new zzzjh(this.zzzwo, this.zzzwo.zzzrh, new Color(this.zzzro), new Color(this.zzzso));
        this.zzzwo.reshape(this.zzzzn, this.zzzao, this.zzzbo, this.zzzco);
        this.zzzvo.reshape(this.zzzdo, this.zzzeo, this.zzzfo, this.zzzho);
        this.zzzxo.reshape(this.zzzio, this.zzzjo, this.zzzko, this.zzzlo);
        this.add(this.zzzwo);
        this.add(this.zzzvo);
        this.add(this.zzzxo);
        try {
            System.out.println("\n\nGraphApplet v 1.0,\nCopyright (c) 1999 Patrik Lundin,\nplundin@kagi.com\n");
        }
        catch (Exception ex) {}
    }
    
    void zzzbn() {
        try {
            this.zzzzn = this.zzzan("calcx", "5", 10);
            this.zzzao = this.zzzan("calcy", "5", 10);
            this.zzzbo = this.zzzan("calcwidth", "240", 10);
            this.zzzco = this.zzzan("calcheight", "350", 10);
            this.zzzdo = this.zzzan("coordx", "250", 10);
            this.zzzeo = this.zzzan("coordy", "5", 10);
            this.zzzfo = this.zzzan("coordwidth", "350", 10);
            this.zzzho = this.zzzan("coordheight", "350", 10);
            this.zzzio = this.zzzan("listx", "601", 10);
            this.zzzjo = this.zzzan("listy", "5", 10);
            this.zzzko = this.zzzan("listwidth", "50", 10);
            this.zzzlo = this.zzzan("listheight", "350", 10);
            this.zzzmo = this.zzzan("appletbg", "EEEEFF", 16);
            this.zzzgo = this.zzzan("appletfg", "000000", 16);
            this.zzzno = this.zzzan("buttonbg", "CCCCCC", 16);
            this.zzzoo = this.zzzan("buttonfg", "000000", 16);
            this.zzzpo = this.zzzan("textfieldbg", "FFFFFF", 16);
            this.zzzqo = this.zzzan("textfieldfg", "000000", 16);
            this.zzzro = this.zzzan("coordbg", "000000", 16);
            this.zzzso = this.zzzan("coordfg", "FFFFFF", 16);
            this.zzzto = this.zzzan("listbg", "CCCCCC", 16);
            this.zzzuo = this.zzzan("listfg", "000000", 16);
        }
        catch (Exception ex) {}
    }
    
    public String zzzvm(final String s, final String s2) {
        if (this.getParameter(s) != null) {
            return this.getParameter(s);
        }
        return s2;
    }
    
    public int zzzan(final String s, final String s2, final int n) {
        int n2;
        try {
            final String parameter = this.getParameter(s);
            if (parameter != null) {
                n2 = Integer.parseInt(parameter, n);
            }
            else {
                n2 = Integer.parseInt(s2, n);
            }
        }
        catch (Exception ex) {
            n2 = Integer.parseInt(s2, n);
        }
        return n2;
    }
    
    public void zzzcn(final String s, final Color color) {
        (this.zzzyo = this.zzzvo.getGraphics()).setColor(color);
        try {
            this.zzzzm();
            this.zzzvo.zzzbg();
            this.zzzvo.zzzxf(s, this.zzzyo);
        }
        catch (Exception ex) {
            this.zzzwo.zzzhp(ex.getMessage());
        }
        this.zzzyo.dispose();
    }
    
    public void zzzwm() {
        try {
            this.zzzzm();
            this.zzzvo.zzzrf();
        }
        catch (Exception ex) {
            this.zzzwo.zzzhp(ex.getMessage());
        }
    }
    
    public void zzzym(final String s) {
        this.zzzvo.zzzzf(s);
    }
    
    public Enumeration zzzum() {
        return this.zzzvo.zzzfg();
    }
    
    void zzzzm() {
        this.zzzvo.zzzng = this.zzzzo;
        this.zzzvo.zzzog = this.zzzap;
        this.zzzvo.zzzpg = this.zzzbp;
        this.zzzvo.zzzqg = this.zzzcp;
        this.zzzvo.zzzrg = this.zzzdp;
    }
    
    public void zzzxm() {
        this.zzzvo.zzzyf();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.zzzxo) {
            final String selectedItem = this.zzzxo.getSelectedItem();
            this.zzzxo.delItem(this.zzzxo.getSelectedIndex());
            this.zzzym(selectedItem);
            return true;
        }
        return false;
    }
    
    public GraphApplet() {
        this.zzzzo = -10.0;
        this.zzzap = 10.0;
        this.zzzbp = -10.0;
        this.zzzcp = 10.0;
        this.zzzdp = 100;
    }
}
