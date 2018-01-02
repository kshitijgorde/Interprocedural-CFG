import java.awt.Event;
import java.util.Enumeration;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import com.javathings.math.zzzzl;
import com.javathings.math.zzznf;
import java.awt.List;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.util.Hashtable;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class zzzqk extends Panel implements zzzip
{
    public Hashtable zzzrh;
    Button zzzsh;
    Button zzzth;
    Button zzzuh;
    Button zzzvh;
    Button zzzwh;
    Button zzzxh;
    Button zzzyh;
    Button zzzzh;
    Button zzzai;
    Button zzzbi;
    Button zzzci;
    Button zzzdi;
    Button zzzei;
    Button zzzfi;
    Button zzzgi;
    Button zzzhi;
    Button zzzii;
    Button zzzji;
    TextField zzzki;
    TextField zzzli;
    TextField zzzmi;
    TextField zzzni;
    Label zzzoi;
    Label zzzpi;
    Label zzzqi;
    Label zzzri;
    Label zzzsi;
    Label zzzti;
    Button zzzui;
    Button zzzvi;
    Button zzzwi;
    Button zzzxi;
    Button zzzyi;
    Button zzzzi;
    Button zzzaj;
    Button zzzbj;
    Button zzzcj;
    Button zzzdj;
    Button zzzej;
    Button zzzfj;
    Button zzzgj;
    Button zzzhj;
    Button zzzij;
    Button zzzjj;
    Button zzzkj;
    Button zzzlj;
    Button zzzmj;
    Button zzznj;
    Button zzzoj;
    Button zzzpj;
    Button zzzqj;
    Button zzzrj;
    Button zzzsj;
    Button zzztj;
    Button zzzuj;
    Button zzzvj;
    Button zzzwj;
    Button zzzxj;
    Button zzzyj;
    Button zzzzj;
    Button zzzak;
    Button zzzbk;
    Button zzzck;
    Button zzzdk;
    Panel zzzek;
    Panel zzzfk;
    GraphApplet zzzgk;
    boolean zzzhk;
    boolean zzzik;
    boolean zzzjk;
    TextArea zzzkk;
    List zzzlk;
    Panel zzzmk;
    zzznf zzznk;
    zzzzl zzzok;
    Font zzzpk;
    
    public zzzqk(final GraphApplet zzzgk, final Color color, final Color color2, final Color background, final Color foreground, final Color background2, final Color foreground2) {
        this.zzzhk = false;
        this.zzzik = false;
        this.zzzjk = false;
        this.zzzgk = zzzgk;
        this.zzzlk = zzzgk.zzzxo;
        this.zzzok = new zzzzl();
        this.zzznk = new zzznf();
        this.zzzrh = new Hashtable();
        this.zzzmk = new Panel();
        this.zzzek = new Panel();
        this.zzzpi = new Label("Xmin");
        this.zzzqi = new Label("Xmax");
        this.zzzri = new Label("Ymin");
        this.zzzsi = new Label("Ymax");
        this.zzzti = new Label("Res");
        this.zzzkk = new TextArea(2, 25);
        this.zzzni = new TextField("10");
        this.zzzmi = new TextField("-10");
        this.zzzli = new TextField("10");
        this.zzzki = new TextField("-10");
        this.zzzkk.setBackground(background2);
        this.zzzni.setBackground(background2);
        this.zzzmi.setBackground(background2);
        this.zzzli.setBackground(background2);
        this.zzzki.setBackground(background2);
        this.zzzkk.setForeground(foreground2);
        this.zzzni.setForeground(foreground2);
        this.zzzmi.setForeground(foreground2);
        this.zzzli.setForeground(foreground2);
        this.zzzki.setForeground(foreground2);
        this.zzzvh = new Button("+");
        this.zzzwh = new Button("-");
        this.zzzyh = new Button("*");
        this.zzzxh = new Button("/");
        this.zzzuh = new Button("Eval");
        this.zzzji = new Button(".");
        this.zzzzh = new Button("0");
        this.zzzai = new Button("1");
        this.zzzbi = new Button("2");
        this.zzzci = new Button("3");
        this.zzzdi = new Button("4");
        this.zzzei = new Button("5");
        this.zzzfi = new Button("6");
        this.zzzgi = new Button("7");
        this.zzzhi = new Button("8");
        this.zzzii = new Button("9");
        this.zzzmj = new Button("Clear");
        this.zzzbj = new Button("exp");
        this.zzzaj = new Button("pi");
        this.zzzhj = new Button("euler");
        this.zzzxi = new Button("sqrt");
        this.zzzdj = new Button("sin");
        this.zzzej = new Button("cos");
        this.zzzfj = new Button("tan");
        this.zzzgj = new Button("atan");
        this.zzzyi = new Button("asin");
        this.zzzzi = new Button("acos");
        this.zzzcj = new Button("acotan");
        this.zzzlj = new Button("PlotF");
        this.zzznj = new Button("Cls");
        this.zzzrj = new Button("M->");
        this.zzzsj = new Button("MCls");
        this.zzzpj = new Button("ZOut");
        this.zzztj = new Button("Reset");
        this.zzzuj = new Button("sfac");
        this.zzzvj = new Button("fac");
        this.zzzwj = new Button("ln");
        this.zzzxj = new Button("10log");
        this.zzzyj = new Button("^");
        this.zzzzj = new Button("(");
        this.zzzak = new Button(")");
        this.zzzbk = new Button("x");
        this.zzzij = new Button("m1");
        this.zzzjj = new Button("m2");
        this.zzzkj = new Button("diff");
        this.zzzvh.setBackground(background);
        this.zzzwh.setBackground(background);
        this.zzzyh.setBackground(background);
        this.zzzxh.setBackground(background);
        this.zzzuh.setBackground(background);
        this.zzzji.setBackground(background);
        this.zzzzh.setBackground(background);
        this.zzzai.setBackground(background);
        this.zzzbi.setBackground(background);
        this.zzzci.setBackground(background);
        this.zzzdi.setBackground(background);
        this.zzzei.setBackground(background);
        this.zzzfi.setBackground(background);
        this.zzzgi.setBackground(background);
        this.zzzhi.setBackground(background);
        this.zzzii.setBackground(background);
        this.zzzmj.setBackground(background);
        this.zzzbj.setBackground(background);
        this.zzzaj.setBackground(background);
        this.zzzhj.setBackground(background);
        this.zzzxi.setBackground(background);
        this.zzzdj.setBackground(background);
        this.zzzej.setBackground(background);
        this.zzzfj.setBackground(background);
        this.zzzgj.setBackground(background);
        this.zzzyi.setBackground(background);
        this.zzzzi.setBackground(background);
        this.zzzcj.setBackground(background);
        this.zzzlj.setBackground(background);
        this.zzznj.setBackground(background);
        this.zzzrj.setBackground(background);
        this.zzzsj.setBackground(background);
        this.zzzpj.setBackground(background);
        this.zzztj.setBackground(background);
        this.zzzuj.setBackground(background);
        this.zzzvj.setBackground(background);
        this.zzzwj.setBackground(background);
        this.zzzxj.setBackground(background);
        this.zzzyj.setBackground(background);
        this.zzzzj.setBackground(background);
        this.zzzak.setBackground(background);
        this.zzzbk.setBackground(background);
        this.zzzij.setBackground(background);
        this.zzzjj.setBackground(background);
        this.zzzkj.setBackground(background);
        this.zzzvh.setForeground(foreground);
        this.zzzwh.setForeground(foreground);
        this.zzzyh.setForeground(foreground);
        this.zzzxh.setForeground(foreground);
        this.zzzuh.setForeground(foreground);
        this.zzzji.setForeground(foreground);
        this.zzzzh.setForeground(foreground);
        this.zzzai.setForeground(foreground);
        this.zzzbi.setForeground(foreground);
        this.zzzci.setForeground(foreground);
        this.zzzdi.setForeground(foreground);
        this.zzzei.setForeground(foreground);
        this.zzzfi.setForeground(foreground);
        this.zzzgi.setForeground(foreground);
        this.zzzhi.setForeground(foreground);
        this.zzzii.setForeground(foreground);
        this.zzzmj.setForeground(foreground);
        this.zzzbj.setForeground(foreground);
        this.zzzaj.setForeground(foreground);
        this.zzzhj.setForeground(foreground);
        this.zzzxi.setForeground(foreground);
        this.zzzdj.setForeground(foreground);
        this.zzzej.setForeground(foreground);
        this.zzzfj.setForeground(foreground);
        this.zzzgj.setForeground(foreground);
        this.zzzyi.setForeground(foreground);
        this.zzzzi.setForeground(foreground);
        this.zzzcj.setForeground(foreground);
        this.zzzlj.setForeground(foreground);
        this.zzznj.setForeground(foreground);
        this.zzzrj.setForeground(foreground);
        this.zzzsj.setForeground(foreground);
        this.zzzpj.setForeground(foreground);
        this.zzztj.setForeground(foreground);
        this.zzzuj.setForeground(foreground);
        this.zzzvj.setForeground(foreground);
        this.zzzwj.setForeground(foreground);
        this.zzzxj.setForeground(foreground);
        this.zzzyj.setForeground(foreground);
        this.zzzzj.setForeground(foreground);
        this.zzzak.setForeground(foreground);
        this.zzzbk.setForeground(foreground);
        this.zzzij.setForeground(foreground);
        this.zzzjj.setForeground(foreground);
        this.zzzkj.setForeground(foreground);
        this.setFont(this.zzzpk = new Font("Times Roman", 1, 11));
        this.setBackground(color);
        this.setForeground(color2);
        this.zzzmk.setLayout(new GridLayout(13, 4, 5, 5));
        this.zzzmk.setBackground(color);
        this.zzzmk.setForeground(color2);
        this.zzzmk.add(this.zzzgi);
        this.zzzmk.add(this.zzzhi);
        this.zzzmk.add(this.zzzii);
        this.zzzmk.add(this.zzzvh);
        this.zzzmk.add(this.zzzdi);
        this.zzzmk.add(this.zzzei);
        this.zzzmk.add(this.zzzfi);
        this.zzzmk.add(this.zzzwh);
        this.zzzmk.add(this.zzzai);
        this.zzzmk.add(this.zzzbi);
        this.zzzmk.add(this.zzzci);
        this.zzzmk.add(this.zzzyh);
        this.zzzmk.add(this.zzzuh);
        this.zzzmk.add(this.zzzzh);
        this.zzzmk.add(this.zzzji);
        this.zzzmk.add(this.zzzxh);
        this.zzzmk.add(this.zzzmj);
        this.zzzmk.add(this.zzzaj);
        this.zzzmk.add(this.zzzhj);
        this.zzzmk.add(this.zzzyj);
        this.zzzmk.add(this.zzzxi);
        this.zzzmk.add(this.zzzej);
        this.zzzmk.add(this.zzzdj);
        this.zzzmk.add(this.zzzfj);
        this.zzzmk.add(this.zzzzi);
        this.zzzmk.add(this.zzzyi);
        this.zzzmk.add(this.zzzgj);
        this.zzzmk.add(this.zzzcj);
        this.zzzmk.add(this.zzzvj);
        this.zzzmk.add(this.zzzbj);
        this.zzzmk.add(this.zzzxj);
        this.zzzmk.add(this.zzzwj);
        this.zzzmk.add(this.zzzkj);
        this.zzzmk.add(this.zzzzj);
        this.zzzmk.add(this.zzzak);
        this.zzzmk.add(this.zzzbk);
        this.zzzmk.add(this.zzzsj);
        this.zzzmk.add(this.zzzrj);
        this.zzzmk.add(this.zzzij);
        this.zzzmk.add(this.zzzjj);
        this.zzzmk.add(this.zzzlj);
        this.zzzmk.add(this.zzzpj);
        this.zzzmk.add(this.zzztj);
        this.zzzmk.add(this.zzznj);
        this.zzzmk.add(this.zzzpi);
        this.zzzmk.add(this.zzzqi);
        this.zzzmk.add(this.zzzri);
        this.zzzmk.add(this.zzzsi);
        this.zzzmk.add(this.zzzmi);
        this.zzzmk.add(this.zzzni);
        this.zzzmk.add(this.zzzki);
        this.zzzmk.add(this.zzzli);
        this.setLayout(new BorderLayout(5, 5));
        this.add("North", this.zzzkk);
        this.add("Center", this.zzzmk);
    }
    
    public void paint(final Graphics graphics) {
        this.zzzkk.requestFocus();
    }
    
    void zzzlh() {
        try {
            final String zzzph = this.zzzph(this.zzzkk.getText().toLowerCase());
            if (zzzph.equals("")) {
                return;
            }
            final int index;
            if ((index = zzzph.indexOf("diff(")) != -1) {
                final String text = this.zzznk.zzzxb(zzzph.substring(index + 5, zzzph.lastIndexOf(")")))[0];
                if (this.zzznk.zzzxe().indexOf(";") != -1) {
                    this.zzzkk.setText("Error, multiple variables");
                    return;
                }
                this.zzzkk.setText(text);
            }
            else {
                if (zzzph.indexOf("memory") != -1) {
                    this.zzzkk.setText(this.zzzoh());
                    return;
                }
                this.zzzkk.setText(String.valueOf(this.zzzok.zzzjl(zzzph, this.zzzrh)));
            }
        }
        catch (Exception ex) {
            if (ex.getMessage().indexOf("No value associated") != -1) {
                this.zzzmh();
                this.zzzgk.zzzcn(this.zzzkk.getText().trim().toLowerCase(), this.zzzkh());
                this.zzznh();
            }
            else {
                this.zzzkk.setText(ex.getMessage());
            }
        }
    }
    
    String zzzoh() {
        String s = "";
        final Enumeration<String> keys = this.zzzrh.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            if (s.equals("")) {
                s = String.valueOf(s2) + "=" + (String)this.zzzrh.get(s2);
            }
            else {
                s = String.valueOf(s) + ";" + s2 + "=" + (String)this.zzzrh.get(s2);
            }
        }
        return s;
    }
    
    public boolean keyDown(final Event event, final int n) {
        final char c = (char)n;
        if (this.zzzjk) {
            this.zzzkk.setText("");
            this.zzzjk = false;
        }
        if (c == '\n') {
            this.zzzlh();
            return true;
        }
        this.zzzhk = false;
        return this.zzzik = false;
    }
    
    public boolean action(final Event event, final Object o) {
        if (this.zzzjk) {
            this.zzzkk.setText("");
            this.zzzjk = false;
        }
        if (event.target == this.zzzlj) {
            this.zzzmh();
            this.zzzgk.zzzcn(this.zzzkk.getText().trim().toLowerCase(), this.zzzkh());
            this.zzznh();
            this.zzzhk = false;
            this.zzzik = false;
            return true;
        }
        if (event.target == this.zzznj) {
            this.zzzmh();
            this.zzzgk.zzzxm();
            this.zzzlk.clear();
            this.zzzlk.addItem("");
            this.zzzhk = false;
            this.zzzik = false;
            return true;
        }
        if (event.target == this.zzzuh) {
            this.zzzlh();
            this.zzzhk = false;
            this.zzzik = false;
            return true;
        }
        if (event.target == this.zzzmj) {
            this.zzzkk.setText("");
            this.zzzhk = false;
            this.zzzik = false;
            return true;
        }
        if (event.target == this.zzzrj) {
            this.zzzhk = true;
            this.zzzik = false;
        }
        else if (event.target == this.zzzsj) {
            this.zzzik = true;
            this.zzzhk = false;
        }
        else if (event.target == this.zzzij) {
            if (this.zzzhk) {
                final String trim = this.zzzkk.getText().trim();
                if (!trim.equals("")) {
                    this.zzzrh.put("m1", trim.toLowerCase());
                }
            }
            else if (this.zzzik) {
                this.zzzrh.remove("m1");
            }
            else if (this.zzzrh.containsKey("m1")) {
                this.zzzkk.setText(String.valueOf(this.zzzkk.getText()) + "m1");
            }
            this.zzzhk = false;
            this.zzzik = false;
        }
        else if (event.target == this.zzzjj) {
            if (this.zzzhk) {
                final String trim2 = this.zzzkk.getText().trim();
                if (!trim2.equals("")) {
                    this.zzzrh.put("m2", trim2.toLowerCase());
                }
            }
            else if (this.zzzik) {
                this.zzzrh.remove("m2");
            }
            else if (this.zzzrh.containsKey("m2")) {
                this.zzzkk.setText(String.valueOf(this.zzzkk.getText()) + "m2");
            }
            this.zzzhk = false;
            this.zzzik = false;
        }
        else {
            if (event.target == this.zzzlk) {
                final String selectedItem = this.zzzlk.getSelectedItem();
                this.zzzlk.delItem(this.zzzlk.getSelectedIndex());
                this.zzzgk.zzzym(selectedItem);
                this.zzzhk = false;
                this.zzzik = false;
                return true;
            }
            if (event.target == this.zzzoj) {
                this.zzzgk.zzzzo /= 2.0;
                this.zzzgk.zzzap /= 2.0;
                this.zzzgk.zzzbp /= 2.0;
                this.zzzgk.zzzcp /= 2.0;
                this.zzzgk.zzzwm();
                this.zzzqh();
                this.zzzhk = false;
                this.zzzik = false;
            }
            else if (event.target == this.zzzpj) {
                this.zzzgk.zzzzo *= 2.0;
                this.zzzgk.zzzap *= 2.0;
                this.zzzgk.zzzbp *= 2.0;
                this.zzzgk.zzzcp *= 2.0;
                this.zzzgk.zzzwm();
                this.zzzqh();
                this.zzzhk = false;
                this.zzzik = false;
            }
            else if (event.target == this.zzztj) {
                this.zzzgk.zzzzo = -10.0;
                this.zzzgk.zzzap = 10.0;
                this.zzzgk.zzzbp = -10.0;
                this.zzzgk.zzzcp = 10.0;
                this.zzzgk.zzzdp = 100;
                this.zzzgk.zzzwm();
                this.zzzqh();
                this.zzzhk = false;
                this.zzzik = false;
            }
            else if (event.target instanceof Button) {
                final String label = ((Button)event.target).getLabel();
                if (label.length() > 1 && !label.equals("euler") && !label.equals("pi")) {
                    this.zzzkk.setText(String.valueOf(this.zzzkk.getText()) + label + "(");
                }
                else {
                    this.zzzkk.setText(String.valueOf(this.zzzkk.getText()) + label);
                }
                this.zzzkk.requestFocus();
                this.zzzhk = false;
                this.zzzik = false;
                return true;
            }
        }
        return false;
    }
    
    void zzzqh() {
        this.zzzmi.setText(String.valueOf(this.zzzgk.zzzzo));
        this.zzzni.setText(String.valueOf(this.zzzgk.zzzap));
        this.zzzki.setText(String.valueOf(this.zzzgk.zzzbp));
        this.zzzli.setText(String.valueOf(this.zzzgk.zzzcp));
    }
    
    public void zzzgp(final double zzzzo, final double zzzap, final double zzzbp, final double zzzcp) {
        this.zzzmi.setText(String.valueOf(zzzzo));
        this.zzzni.setText(String.valueOf(zzzap));
        this.zzzki.setText(String.valueOf(zzzbp));
        this.zzzli.setText(String.valueOf(zzzcp));
        this.zzzgk.zzzzo = zzzzo;
        this.zzzgk.zzzap = zzzap;
        this.zzzgk.zzzbp = zzzbp;
        this.zzzgk.zzzcp = zzzcp;
    }
    
    public void zzzhp(final String text) {
        this.zzzkk.setText(text);
        this.zzzjk = true;
    }
    
    void zzznh() {
        final Enumeration zzzum = this.zzzgk.zzzum();
        this.zzzlk.clear();
        while (zzzum.hasMoreElements()) {
            this.zzzlk.addItem(zzzum.nextElement());
        }
    }
    
    void zzzmh() {
        try {
            final double doubleValue = Double.valueOf(this.zzzmi.getText());
            final double doubleValue2 = Double.valueOf(this.zzzni.getText());
            if (doubleValue < doubleValue2) {
                this.zzzgk.zzzzo = doubleValue;
                this.zzzgk.zzzap = doubleValue2;
            }
            else {
                this.zzzgk.zzzzo = doubleValue2;
                this.zzzgk.zzzap = doubleValue;
            }
            final double doubleValue3 = Double.valueOf(this.zzzki.getText());
            final double doubleValue4 = Double.valueOf(this.zzzli.getText());
            if (doubleValue3 < doubleValue4) {
                this.zzzgk.zzzbp = doubleValue3;
                this.zzzgk.zzzcp = doubleValue4;
            }
            else {
                this.zzzgk.zzzbp = doubleValue4;
                this.zzzgk.zzzcp = doubleValue3;
            }
            this.zzzgk.zzzdp = 100;
        }
        catch (Exception ex) {
            this.zzzgk.zzzzo = -10.0;
            this.zzzgk.zzzap = 10.0;
            this.zzzgk.zzzbp = -10.0;
            this.zzzgk.zzzcp = 10.0;
            this.zzzgk.zzzdp = 100;
        }
        this.zzzqh();
    }
    
    Color zzzkh() {
        return new Color(150 + (int)(105.0 * Math.random()), 150 + (int)(105.0 * Math.random()), 150 + (int)(105.0 * Math.random()));
    }
    
    String zzzph(final String s) {
        final StringBuffer sb = new StringBuffer();
        final int length = s.length();
        int n = 0;
        for (int i = 0; i < length; ++i) {
            if (s.charAt(i) == '(') {
                ++n;
            }
            else if (s.charAt(i) == ')') {
                --n;
            }
            if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            }
        }
        if (n != 0) {
            this.zzzkk.setText("Non matching parantesis: " + s);
            return "";
        }
        return sb.toString();
    }
}
