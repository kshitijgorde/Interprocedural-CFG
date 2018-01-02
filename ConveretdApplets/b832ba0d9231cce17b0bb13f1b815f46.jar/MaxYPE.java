import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.GregorianCalendar;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MaxYPE extends Applet
{
    boolean isStandalone;
    boolean language;
    boolean nedretekstfelt;
    String var1;
    String var2;
    String var3;
    String var4;
    String var5;
    String var6;
    int vari1;
    int vari2;
    int vari3;
    Date klokke;
    GregorianCalendar idag;
    public Graphics g;
    boolean kastet;
    String b1;
    String b2;
    String b3;
    String b4;
    String b5;
    String b6;
    String b1p;
    String b2p;
    String b3p;
    String b3l;
    String b4l;
    String b5l;
    String bls;
    String bss;
    String bfs;
    String b23;
    String b33;
    String b42;
    String bs;
    String by;
    String bvelg;
    String bkast;
    String bnyttspill;
    String Lyatzy;
    String Lwwwadresse;
    String wwwadresse;
    String Lspillere;
    String Lsum;
    String Lbonus;
    String Lsum2;
    String L8;
    String L9;
    String L10;
    String Loppsparte;
    String Lkastigjen;
    String LKastteringer;
    String Lsparekast;
    String Tnavn;
    String Tkode;
    String Tkopier;
    int enere;
    int toere;
    int treere;
    int firere;
    int femmere;
    int seksere;
    int par;
    int topar;
    int trepar;
    int trelike;
    int firelike;
    int femlike;
    int litens;
    int stors;
    int fulls;
    int totre;
    int tretre;
    int fireto;
    int sjanse;
    int yatzy;
    boolean tvungen;
    boolean fri;
    String rekord1;
    String rekord2;
    String rekord3;
    String email;
    TerningAxxP terningkastAxxP;
    int xpos;
    int ypos;
    int jukset;
    int nyttspill;
    int regist;
    int kode;
    String tallet;
    Color KnappefargeBak;
    Color KnappefargeFor;
    Color Bakgrunnsfarge;
    int farge1;
    int farge2;
    int farge3;
    Panel panel1;
    Label label1;
    Panel panel2;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Label label2;
    Label label3;
    Panel panel3;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    Button button12;
    Button button13;
    Button button14;
    Button button15;
    Button button16;
    Button button17;
    Button button18;
    Button button19;
    Button button20;
    Panel panel4;
    Panel panel5;
    Label label4;
    TextField textField1;
    Label label5;
    Panel panel6;
    Panel panel7;
    TextField textField5;
    TextField textField6;
    Panel panel8;
    Panel panel9;
    Panel panel10;
    Panel panel11;
    Panel panel12;
    Panel panel13;
    Panel panel14;
    Panel panel15;
    Panel panel16;
    Panel panel17;
    Panel panel18;
    Panel panel19;
    Panel panel20;
    Panel panel21;
    Panel panel22;
    Panel panel23;
    Panel panel24;
    Panel panel25;
    Panel panel26;
    Panel panel27;
    Panel panel28;
    TextField textField7;
    TextField textField8;
    TextField textField9;
    TextField textField10;
    TextField textField11;
    TextField textField12;
    TextField textField13;
    TextField textField14;
    TextField textField15;
    TextField textField16;
    TextField textField17;
    TextField textField18;
    TextField textField19;
    TextField textField20;
    TextField textField21;
    TextField textField22;
    TextField textField23;
    TextField textField24;
    TextField textField25;
    TextField textField26;
    TextField textField27;
    Panel panel29;
    Button button21;
    Panel panel30;
    Button button22;
    Button button23;
    Button button24;
    Button button25;
    Button button26;
    Button button27;
    Panel panel31;
    int xx6;
    int yy6;
    int bredde;
    int hoyde;
    int av;
    int p1;
    int plassx;
    int plassy;
    int p3;
    int p4;
    Button button28;
    int tellerEn;
    int tellerTo;
    Label label6;
    Label label7;
    int tellerTre;
    int tellerFire;
    int tellerFem;
    int tellerSeks;
    int tellerPar;
    int tellerTopar;
    int toparH;
    int toparL;
    int tellerTrepar;
    int treparL;
    int treparM;
    int treparH;
    int tellerTrelike;
    int tellerFirelike;
    int tellerFemlike;
    int tellerLitens;
    int liten1;
    int liten2;
    int liten3;
    int liten4;
    int liten5;
    int tellerStors;
    int tellerFulls;
    int tellerTotre;
    int totreL;
    int totreH;
    int tellerTretre;
    int tretreH;
    int tretreL;
    int tellerFireto;
    int firetoH;
    int firetoL;
    int tellerSjanse;
    int tellerYatzy;
    Checkbox checkbox1;
    int checkboxkontroll;
    Label label8;
    Label label9;
    Label label10;
    Label label11;
    Label label12;
    Label label13;
    Label label14;
    TextField textField2;
    Label label15;
    String kode2;
    TextField textField3;
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) != null) ? this.getParameter(key) : def);
    }
    
    public String[][] getParameterInfo() {
        final String[][] pinfo = { { "s1", "String", "" }, { "s2", "String", "" }, { "s3", "String", "" }, { "s4", "String", "" }, { "s5", "String", "" }, { "s6", "String", "" }, { "i1", "int", "" }, { "i2", "int", "" }, { "i3", "int", "" } };
        return pinfo;
    }
    
    public void init() {
        try {
            this.var1 = this.getParameter("s1", "");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.var2 = this.getParameter("s2", "");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.var3 = this.getParameter("s3", "");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.var4 = this.getParameter("s4", "    yatzy.sidendin.com");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.var5 = this.getParameter("s5", " yatzy@inbox.as");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.var6 = this.getParameter("s6", "");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.vari1 = Integer.parseInt(this.getParameter("i1", "0"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.vari2 = Integer.parseInt(this.getParameter("i2", "0"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.vari3 = Integer.parseInt(this.getParameter("i3", "0"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public MaxYPE() {
        this.isStandalone = false;
        this.language = false;
        this.nedretekstfelt = false;
        this.textField3 = new TextField();
        this.klokke = new Date();
        this.idag = new GregorianCalendar();
        this.kastet = false;
        this.enere = 0;
        this.toere = 0;
        this.treere = 0;
        this.firere = 0;
        this.femmere = 0;
        this.seksere = 0;
        this.par = 0;
        this.topar = 0;
        this.trepar = 0;
        this.trelike = 0;
        this.firelike = 0;
        this.femlike = 0;
        this.litens = 0;
        this.stors = 0;
        this.fulls = 0;
        this.totre = 0;
        this.tretre = 0;
        this.fireto = 0;
        this.sjanse = 0;
        this.yatzy = 0;
        this.tvungen = true;
        this.fri = false;
        this.email = " yatzy@inbox.as";
        this.terningkastAxxP = new TerningAxxP();
        this.xpos = 0;
        this.ypos = 0;
        this.jukset = 0;
        this.nyttspill = 0;
        this.regist = 0;
        this.kode = 988;
        this.tallet = "0";
        this.KnappefargeBak = new Color(162, 0, 0);
        this.KnappefargeFor = new Color(255, 255, 255);
        this.farge1 = this.vari1;
        this.farge2 = this.vari2;
        this.farge3 = this.vari3;
        this.panel1 = new Panel();
        this.label1 = new Label();
        this.panel2 = new Panel();
        this.button1 = new Button();
        this.button2 = new Button();
        this.button3 = new Button();
        this.button4 = new Button();
        this.button5 = new Button();
        this.button6 = new Button();
        this.label2 = new Label();
        this.label3 = new Label();
        this.panel3 = new Panel();
        this.button7 = new Button();
        this.button8 = new Button();
        this.button9 = new Button();
        this.button10 = new Button();
        this.button11 = new Button();
        this.button12 = new Button();
        this.button13 = new Button();
        this.button14 = new Button();
        this.button15 = new Button();
        this.button16 = new Button();
        this.button17 = new Button();
        this.button18 = new Button();
        this.button19 = new Button();
        this.button20 = new Button();
        this.panel4 = new Panel();
        this.panel5 = new Panel();
        this.label4 = new Label();
        this.textField1 = new TextField();
        this.label5 = new Label();
        this.panel6 = new Panel();
        this.panel7 = new Panel();
        this.textField5 = new TextField();
        this.textField6 = new TextField();
        this.panel8 = new Panel();
        this.panel9 = new Panel();
        this.panel10 = new Panel();
        this.panel11 = new Panel();
        this.panel12 = new Panel();
        this.panel13 = new Panel();
        this.panel14 = new Panel();
        this.panel15 = new Panel();
        this.panel16 = new Panel();
        this.panel17 = new Panel();
        this.panel18 = new Panel();
        this.panel19 = new Panel();
        this.panel20 = new Panel();
        this.panel21 = new Panel();
        this.panel22 = new Panel();
        this.panel23 = new Panel();
        this.panel24 = new Panel();
        this.panel25 = new Panel();
        this.panel26 = new Panel();
        this.panel27 = new Panel();
        this.panel28 = new Panel();
        this.textField7 = new TextField();
        this.textField8 = new TextField();
        this.textField9 = new TextField();
        this.textField10 = new TextField();
        this.textField11 = new TextField();
        this.textField12 = new TextField();
        this.textField13 = new TextField();
        this.textField14 = new TextField();
        this.textField15 = new TextField();
        this.textField16 = new TextField();
        this.textField17 = new TextField();
        this.textField18 = new TextField();
        this.textField19 = new TextField();
        this.textField20 = new TextField();
        this.textField21 = new TextField();
        this.textField22 = new TextField();
        this.textField23 = new TextField();
        this.textField24 = new TextField();
        this.textField25 = new TextField();
        this.textField26 = new TextField();
        this.textField27 = new TextField();
        this.panel29 = new Panel();
        this.button21 = new Button();
        this.panel30 = new Panel();
        this.button22 = new Button();
        this.button23 = new Button();
        this.button24 = new Button();
        this.button25 = new Button();
        this.button26 = new Button();
        this.button27 = new Button();
        this.panel31 = new Panel();
        this.xx6 = 169;
        this.yy6 = 441;
        this.bredde = 27;
        this.hoyde = this.bredde;
        this.av = 30;
        this.p1 = 11;
        this.plassx = 8;
        this.plassy = 11;
        this.button28 = new Button();
        this.tellerEn = 0;
        this.tellerTo = 0;
        this.label6 = new Label();
        this.label7 = new Label();
        this.tellerTre = 0;
        this.tellerFire = 0;
        this.tellerFem = 0;
        this.tellerSeks = 0;
        this.tellerPar = 0;
        this.tellerTopar = 0;
        this.toparH = 0;
        this.toparL = 0;
        this.tellerTrepar = 0;
        this.treparL = 0;
        this.treparM = 0;
        this.treparH = 0;
        this.tellerTrelike = 0;
        this.tellerFirelike = 0;
        this.tellerFemlike = 0;
        this.tellerLitens = 0;
        this.liten1 = 0;
        this.liten2 = 0;
        this.liten3 = 0;
        this.liten4 = 0;
        this.liten5 = 0;
        this.tellerStors = 0;
        this.tellerFulls = 0;
        this.tellerTotre = 0;
        this.totreL = 0;
        this.totreH = 0;
        this.tellerTretre = 0;
        this.tretreH = 0;
        this.tretreL = 0;
        this.tellerFireto = 0;
        this.firetoH = 0;
        this.firetoL = 0;
        this.tellerSjanse = 0;
        this.tellerYatzy = 0;
        this.checkbox1 = new Checkbox();
        this.checkboxkontroll = 1;
        this.label8 = new Label();
        this.label9 = new Label();
        this.label10 = new Label();
        this.label11 = new Label();
        this.label12 = new Label();
        this.label13 = new Label();
        this.label14 = new Label();
        this.textField2 = new TextField();
        this.label15 = new Label();
        this.wwwadresse = "    yatzy.sidendin.com";
        if (this.language) {
            this.b1 = "Enere";
            this.b2 = "Toere";
            this.b3 = "Treere";
            this.b4 = "Firere";
            this.b5 = "Femmere";
            this.b6 = "Seksere";
            this.b1p = "1 par";
            this.b2p = "2 par";
            this.b3p = "3 par";
            this.b3l = "3 like";
            this.b4l = "4 like";
            this.b5l = "5 like";
            this.bls = "Liten Straight";
            this.bss = "Stor Straight";
            this.bfs = "Full Straight";
            this.b23 = "2 + 3";
            this.b33 = "3 + 3";
            this.b42 = "4 + 2";
            this.bs = "Sjanse";
            this.by = "Yatzy";
            this.bvelg = "Velg";
            this.bkast = "Kast terninger";
            this.bnyttspill = "Nytt spill";
            this.Lyatzy = "  Maxi YatzY                ";
            this.Lwwwadresse = this.wwwadresse;
            this.Lspillere = " Spillere:";
            this.Lsum = "     Sum:";
            this.Lbonus = "    Bonus:";
            this.Lsum2 = "     Sum:";
            this.L8 = "Mulig: 649p";
            this.L9 = "For \u00e5 f\u00e5 bonus: 84p ";
            this.L10 = "Bonus, yatzy: 100p";
            this.Loppsparte = "   Oppsparte kast: ";
            this.Lkastigjen = "  kast igjen";
            this.LKastteringer = "   Kast terninger!";
            this.Lsparekast = " Spare kast";
            this.Tnavn = "Navn";
            this.Tkode = "Kode: ";
            this.Tkopier = String.valueOf(String.valueOf("").concat(String.valueOf(this.email))).concat(String.valueOf("   Kopiere: merk tekst og trykk: Ctrl + C"));
        }
        else {
            this.b1 = "Ones";
            this.b2 = "Twos";
            this.b3 = "Threes";
            this.b4 = "Fours";
            this.b5 = "Fives";
            this.b6 = "Sixes";
            this.b1p = "1 pair";
            this.b2p = "2 pair";
            this.b3p = "3 pair";
            this.b3l = "3 of a kind";
            this.b4l = "4 of a kind";
            this.b5l = "5 of a kind";
            this.bls = "Small Straight";
            this.bss = "Large Straight";
            this.bfs = "Full Straight";
            this.b23 = "2 + 3";
            this.b33 = "3 + 3";
            this.b42 = "4 + 2";
            this.bs = "Chance";
            this.by = "Yatzy";
            this.bvelg = "Keep";
            this.bkast = "Roll the dice";
            this.bnyttspill = "New game";
            this.Lyatzy = "  Maxi YatzY                ";
            this.Lwwwadresse = this.wwwadresse;
            this.Lspillere = " Players:";
            this.Lsum = "     Sum:";
            this.Lbonus = "    Bonus:";
            this.Lsum2 = "     Sum:";
            this.L8 = "Possible: 649p";
            this.L9 = "To get bonus: 84p ";
            this.L10 = "Bonus, yatzy: 100p";
            this.Loppsparte = "    Saved rolls: ";
            this.Lkastigjen = " rolls left ";
            this.LKastteringer = "    Roll the dice";
            this.Lsparekast = " Save rolls";
            this.Tnavn = "Name";
            this.Tkode = "Code: ";
            this.Tkopier = String.valueOf(String.valueOf("").concat(String.valueOf(this.email))).concat(String.valueOf("   To copy: select text, press: Ctrl + C"));
        }
    }
    
    private void jbInit() throws Exception {
        this.rekord1 = this.var1;
        this.rekord2 = this.var2;
        this.rekord3 = this.var3;
        this.setBackground(this.Bakgrunnsfarge = new Color(this.vari1, this.vari2, this.vari3));
        if (this.nedretekstfelt) {
            this.setSize(new Dimension(288, 542));
        }
        else {
            this.setSize(new Dimension(288, 506));
        }
        this.panel1.setBackground(Color.black);
        this.panel1.setBounds(new Rectangle(4, 2, 281, 22));
        this.panel1.setLayout(null);
        this.label1.setForeground(Color.white);
        this.label1.setBackground(new Color(162, 0, 0));
        this.label1.setFont(new Font("Helvetica", 1, 18));
        this.label1.setBounds(new Rectangle(2, 3, 113, 17));
        this.label1.setText("  Maxi YatzY      ");
        this.panel2.setBackground(Color.black);
        this.panel2.setBounds(new Rectangle(4, 49, 92, 453));
        this.panel2.setLayout(null);
        this.button1.setForeground(this.KnappefargeFor);
        this.button1.setBackground(this.KnappefargeBak);
        this.button1.setBounds(new Rectangle(3, 4, 88, 18));
        this.button1.setLabel(this.b1);
        this.button2.setForeground(this.KnappefargeFor);
        this.button2.setBackground(this.KnappefargeBak);
        this.button2.setBounds(new Rectangle(3, 23, 88, 18));
        this.button2.setLabel(this.b2);
        this.button3.setForeground(this.KnappefargeFor);
        this.button3.setBackground(this.KnappefargeBak);
        this.button3.setBounds(new Rectangle(3, 42, 88, 18));
        this.button3.setLabel(this.b3);
        this.button4.setForeground(this.KnappefargeFor);
        this.button4.setBackground(this.KnappefargeBak);
        this.button4.setBounds(new Rectangle(3, 61, 88, 18));
        this.button4.setLabel(this.b4);
        this.button5.setForeground(this.KnappefargeFor);
        this.button5.setBackground(this.KnappefargeBak);
        this.button5.setBounds(new Rectangle(3, 80, 88, 18));
        this.button5.setLabel(this.b5);
        this.button6.setForeground(this.KnappefargeFor);
        this.button6.setBackground(this.KnappefargeBak);
        this.button6.setBounds(new Rectangle(3, 99, 88, 18));
        this.button6.setLabel(this.b6);
        this.label2.setFont(new Font("SansSerif", 1, 13));
        this.label2.setBounds(new Rectangle(3, 118, 87, 17));
        this.label2.setForeground(this.KnappefargeFor);
        this.label2.setBackground(Color.black);
        this.label2.setText(this.Lsum);
        this.label3.setFont(new Font("SansSerif", 1, 13));
        this.label3.setBounds(new Rectangle(3, 137, 87, 17));
        this.label3.setForeground(this.KnappefargeFor);
        this.label3.setBackground(Color.black);
        this.label3.setText(this.Lbonus);
        this.panel3.setBackground(Color.black);
        this.panel3.setBounds(new Rectangle(97, 49, 68, 453));
        this.panel3.setLayout(null);
        this.button7.setForeground(this.KnappefargeFor);
        this.button7.setBackground(this.KnappefargeBak);
        this.button7.setBounds(new Rectangle(3, 157, 88, 18));
        this.button7.setLabel(this.b1p);
        this.button8.setForeground(this.KnappefargeFor);
        this.button8.setBackground(this.KnappefargeBak);
        this.button8.setBounds(new Rectangle(3, 176, 88, 18));
        this.button8.setLabel(this.b2p);
        this.button9.setForeground(this.KnappefargeFor);
        this.button9.setBackground(this.KnappefargeBak);
        this.button9.setBounds(new Rectangle(3, 195, 88, 18));
        this.button9.setLabel(this.b3p);
        this.button10.setForeground(this.KnappefargeFor);
        this.button10.setBackground(this.KnappefargeBak);
        this.button10.setBounds(new Rectangle(3, 214, 88, 18));
        this.button10.setLabel(this.b3l);
        this.button11.setForeground(this.KnappefargeFor);
        this.button11.setBackground(this.KnappefargeBak);
        this.button11.setBounds(new Rectangle(3, 233, 88, 18));
        this.button11.setLabel(this.b4l);
        this.button12.setForeground(this.KnappefargeFor);
        this.button12.setBackground(this.KnappefargeBak);
        this.button12.setBounds(new Rectangle(3, 252, 88, 18));
        this.button12.setLabel(this.b5l);
        this.button13.setForeground(this.KnappefargeFor);
        this.button13.setBackground(this.KnappefargeBak);
        this.button13.setBounds(new Rectangle(3, 271, 88, 18));
        this.button13.setLabel(this.bls);
        this.button14.setForeground(this.KnappefargeFor);
        this.button14.setBackground(this.KnappefargeBak);
        this.button14.setBounds(new Rectangle(3, 290, 88, 18));
        this.button14.setLabel(this.bss);
        this.button15.setForeground(this.KnappefargeFor);
        this.button15.setBackground(this.KnappefargeBak);
        this.button15.setBounds(new Rectangle(3, 309, 88, 18));
        this.button15.setLabel(this.bfs);
        this.button16.setForeground(this.KnappefargeFor);
        this.button16.setBackground(this.KnappefargeBak);
        this.button16.setBounds(new Rectangle(3, 328, 88, 18));
        this.button16.setLabel(this.b23);
        this.button17.setForeground(this.KnappefargeFor);
        this.button17.setBackground(this.KnappefargeBak);
        this.button17.setBounds(new Rectangle(3, 347, 88, 18));
        this.button17.setLabel(this.b33);
        this.button18.setForeground(this.KnappefargeFor);
        this.button18.setBackground(this.KnappefargeBak);
        this.button18.setBounds(new Rectangle(3, 366, 88, 18));
        this.button18.setLabel(this.b42);
        this.button19.setForeground(this.KnappefargeFor);
        this.button19.setBackground(this.KnappefargeBak);
        this.button19.setBounds(new Rectangle(3, 385, 88, 18));
        this.button19.setLabel(this.bs);
        this.button20.setForeground(this.KnappefargeFor);
        this.button20.setBackground(this.KnappefargeBak);
        this.button20.setBounds(new Rectangle(3, 404, 88, 18));
        this.button20.setLabel(this.by);
        this.panel4.setBounds(new Rectangle(88, 35, 10, 10));
        this.panel5.setBackground(Color.black);
        this.panel5.setBounds(new Rectangle(4, 26, 281, 21));
        this.panel5.setLayout(null);
        this.label4.setForeground(this.KnappefargeFor);
        this.label4.setFont(new Font("SansSerif", 1, 13));
        this.label4.setBounds(new Rectangle(9, 1, 70, 19));
        this.label4.setText(this.Lspillere);
        this.textField1.setForeground(Color.yellow);
        this.textField1.setFont(new Font("SansSerif", 1, 12));
        this.textField1.setBackground(new Color(0, 0, 0));
        this.textField1.setBounds(new Rectangle(96, 1, 62, 18));
        this.textField1.setText(this.Tnavn);
        this.label5.setForeground(Color.white);
        this.label5.setFont(new Font("SansSerif", 1, 14));
        this.label5.setBackground(Color.black);
        this.label5.setBounds(new Rectangle(3, 423, 87, 25));
        this.label5.setText(this.Lsum);
        this.panel6.setBackground(Color.black);
        this.panel6.setBounds(new Rectangle(1, 4, 183, 18));
        this.panel6.setLayout(null);
        this.panel7.setBackground(Color.black);
        this.panel7.setBounds(new Rectangle(1, 23, 183, 18));
        this.panel7.setLayout(null);
        this.textField5.setForeground(Color.yellow);
        this.textField5.setFont(new Font("SansSerif", 1, 12));
        this.textField5.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField5.setEditable(false);
        this.textField5.setText("");
        this.textField6.setBackground(Color.black);
        this.textField6.setForeground(Color.yellow);
        this.textField6.setFont(new Font("SansSerif", 1, 12));
        this.textField6.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField6.setEditable(false);
        this.textField6.setText("");
        this.panel8.setBackground(Color.black);
        this.panel8.setBounds(new Rectangle(1, 42, 183, 18));
        this.panel8.setLayout(null);
        this.panel9.setBackground(Color.black);
        this.panel9.setBounds(new Rectangle(1, 61, 183, 18));
        this.panel9.setLayout(null);
        this.panel10.setBackground(Color.black);
        this.panel10.setBounds(new Rectangle(1, 80, 183, 18));
        this.panel10.setLayout(null);
        this.panel11.setBackground(Color.black);
        this.panel11.setBounds(new Rectangle(1, 99, 183, 18));
        this.panel11.setLayout(null);
        this.panel12.setBackground(Color.black);
        this.panel12.setBounds(new Rectangle(1, 118, 183, 18));
        this.panel12.setLayout(null);
        this.panel13.setBounds(new Rectangle(1, 137, 183, 18));
        this.panel13.setLayout(null);
        this.panel14.setBounds(new Rectangle(1, 156, 183, 18));
        this.panel14.setLayout(null);
        this.panel15.setBackground(Color.black);
        this.panel15.setBounds(new Rectangle(1, 175, 183, 18));
        this.panel15.setLayout(null);
        this.panel16.setBounds(new Rectangle(1, 194, 183, 18));
        this.panel16.setLayout(null);
        this.panel17.setBounds(new Rectangle(1, 213, 70, 18));
        this.panel17.setLayout(null);
        this.panel18.setBackground(Color.black);
        this.panel18.setBounds(new Rectangle(1, 232, 183, 18));
        this.panel18.setLayout(null);
        this.panel19.setBackground(Color.black);
        this.panel19.setBounds(new Rectangle(1, 251, 116, 18));
        this.panel19.setLayout(null);
        this.panel20.setBounds(new Rectangle(1, 270, 183, 18));
        this.panel20.setLayout(null);
        this.panel21.setBounds(new Rectangle(1, 289, 183, 18));
        this.panel21.setLayout(null);
        this.panel22.setBackground(Color.black);
        this.panel22.setBounds(new Rectangle(1, 308, 183, 18));
        this.panel22.setLayout(null);
        this.panel23.setBackground(Color.black);
        this.panel23.setBounds(new Rectangle(1, 327, 183, 18));
        this.panel23.setLayout(null);
        this.panel24.setBounds(new Rectangle(1, 346, 183, 18));
        this.panel24.setLayout(null);
        this.panel25.setBounds(new Rectangle(1, 365, 183, 18));
        this.panel25.setLayout(null);
        this.panel26.setBackground(Color.black);
        this.panel26.setBounds(new Rectangle(1, 384, 183, 18));
        this.panel26.setLayout(null);
        this.panel27.setBackground(Color.black);
        this.panel27.setBounds(new Rectangle(1, 403, 183, 18));
        this.panel27.setLayout(null);
        this.panel28.setBounds(new Rectangle(1, 422, 183, 26));
        this.panel28.setLayout(null);
        this.textField7.setForeground(Color.yellow);
        this.textField7.setBackground(Color.black);
        this.textField7.setFont(new Font("SansSerif", 1, 12));
        this.textField7.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField7.setEditable(false);
        this.textField7.setText("");
        this.textField8.setForeground(Color.yellow);
        this.textField8.setBackground(Color.black);
        this.textField8.setFont(new Font("SansSerif", 1, 12));
        this.textField8.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField8.setEditable(false);
        this.textField8.setText("");
        this.textField9.setForeground(Color.yellow);
        this.textField9.setBackground(Color.black);
        this.textField9.setFont(new Font("SansSerif", 1, 12));
        this.textField9.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField9.setEditable(false);
        this.textField9.setText("");
        this.textField10.setForeground(Color.yellow);
        this.textField10.setBackground(Color.black);
        this.textField10.setFont(new Font("SansSerif", 1, 12));
        this.textField10.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField10.setEditable(false);
        this.textField10.setText("");
        this.textField11.setForeground(Color.yellow);
        this.textField11.setBackground(Color.black);
        this.textField11.setFont(new Font("SansSerif", 1, 12));
        this.textField11.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField11.setEditable(false);
        this.textField11.setText("");
        this.textField12.setForeground(Color.yellow);
        this.textField12.setBackground(Color.black);
        this.textField12.setFont(new Font("SansSerif", 1, 12));
        this.textField12.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField12.setEditable(false);
        this.textField12.setText("");
        this.textField13.setForeground(Color.yellow);
        this.textField13.setBackground(Color.black);
        this.textField13.setFont(new Font("SansSerif", 1, 12));
        this.textField13.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField13.setEditable(false);
        this.textField13.setText("");
        this.textField14.setForeground(Color.yellow);
        this.textField14.setBackground(Color.black);
        this.textField14.setFont(new Font("SansSerif", 1, 12));
        this.textField14.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField14.setEditable(false);
        this.textField14.setText("");
        this.textField15.setForeground(Color.yellow);
        this.textField15.setBackground(Color.black);
        this.textField15.setFont(new Font("SansSerif", 1, 12));
        this.textField15.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField15.setEditable(false);
        this.textField15.setText("");
        this.textField16.setForeground(Color.yellow);
        this.textField16.setBackground(Color.black);
        this.textField16.setFont(new Font("SansSerif", 1, 12));
        this.textField16.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField16.setEditable(false);
        this.textField16.setText("");
        this.textField17.setForeground(Color.yellow);
        this.textField17.setBackground(Color.black);
        this.textField17.setFont(new Font("SansSerif", 1, 12));
        this.textField17.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField17.setEditable(false);
        this.textField17.setText("");
        this.textField18.setForeground(Color.yellow);
        this.textField18.setBackground(Color.black);
        this.textField18.setFont(new Font("SansSerif", 1, 12));
        this.textField18.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField18.setEditable(false);
        this.textField18.setText("");
        this.textField19.setForeground(Color.yellow);
        this.textField19.setBackground(Color.black);
        this.textField19.setFont(new Font("SansSerif", 1, 12));
        this.textField19.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField19.setEditable(false);
        this.textField19.setText("");
        this.textField20.setForeground(Color.yellow);
        this.textField20.setBackground(Color.black);
        this.textField20.setFont(new Font("SansSerif", 1, 12));
        this.textField20.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField20.setEditable(false);
        this.textField20.setText("");
        this.textField21.setForeground(Color.yellow);
        this.textField21.setBackground(Color.black);
        this.textField21.setFont(new Font("SansSerif", 1, 12));
        this.textField21.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField21.setEditable(false);
        this.textField21.setText("");
        this.textField22.setForeground(Color.yellow);
        this.textField22.setBackground(Color.black);
        this.textField22.setFont(new Font("SansSerif", 1, 12));
        this.textField22.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField22.setEditable(false);
        this.textField22.setText("");
        this.textField23.setForeground(Color.yellow);
        this.textField23.setBackground(Color.black);
        this.textField23.setFont(new Font("SansSerif", 1, 12));
        this.textField23.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField23.setEditable(false);
        this.textField23.setText("");
        this.textField24.setForeground(Color.yellow);
        this.textField24.setBackground(Color.black);
        this.textField24.setFont(new Font("SansSerif", 1, 12));
        this.textField24.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField24.setEditable(false);
        this.textField24.setText("");
        this.textField25.setForeground(Color.yellow);
        this.textField25.setBackground(Color.black);
        this.textField25.setFont(new Font("SansSerif", 1, 12));
        this.textField25.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField25.setEditable(false);
        this.textField25.setText("");
        this.textField26.setForeground(Color.yellow);
        this.textField26.setBackground(Color.black);
        this.textField26.setFont(new Font("SansSerif", 1, 12));
        this.textField26.setBounds(new Rectangle(1, 0, 62, 18));
        this.textField26.setEditable(false);
        this.textField26.setText("");
        this.textField27.setForeground(Color.yellow);
        this.textField27.setBackground(Color.black);
        this.textField27.setFont(new Font("SansSerif", 1, 14));
        this.textField27.setBounds(new Rectangle(1, 0, 62, 26));
        this.textField27.setEditable(false);
        this.textField27.setText("");
        this.panel28.setBackground(Color.black);
        this.panel25.setBackground(Color.black);
        this.panel24.setBackground(Color.black);
        this.panel21.setBackground(Color.black);
        this.panel20.setBackground(Color.black);
        this.panel17.setBackground(Color.black);
        this.panel16.setBackground(Color.black);
        this.panel14.setBackground(Color.black);
        this.panel13.setBackground(Color.black);
        this.setLayout(null);
        this.panel29.setBackground(Color.black);
        this.panel30.setBackground(Color.black);
        this.panel31.setBackground(Color.black);
        this.label6.setForeground(Color.cyan);
        this.label6.setBackground(Color.black);
        this.label6.setBounds(new Rectangle(1, 145, 116, 19));
        this.label6.setText(this.Loppsparte);
        this.checkbox1.setForeground(Color.green);
        this.checkbox1.setBounds(new Rectangle(5, 184, 109, 23));
        this.checkbox1.setState(true);
        this.add(this.panel1, null);
        this.panel1.add(this.label1, null);
        this.panel1.add(this.panel4, null);
        this.panel1.add(this.label15, null);
        this.add(this.panel2, null);
        this.panel2.add(this.button1, null);
        this.panel2.add(this.button2, null);
        this.panel2.add(this.button3, null);
        this.panel2.add(this.button4, null);
        this.panel2.add(this.button5, null);
        this.panel2.add(this.button6, null);
        this.panel2.add(this.button7, null);
        this.panel2.add(this.button8, null);
        this.panel2.add(this.button9, null);
        this.panel2.add(this.button10, null);
        this.panel2.add(this.button11, null);
        this.panel2.add(this.button12, null);
        this.panel2.add(this.button13, null);
        this.panel2.add(this.button14, null);
        this.panel2.add(this.label3, null);
        this.panel2.add(this.label2, null);
        this.panel2.add(this.button15, null);
        this.panel2.add(this.button16, null);
        this.panel2.add(this.button17, null);
        this.panel2.add(this.button18, null);
        this.panel2.add(this.button19, null);
        this.panel2.add(this.button20, null);
        this.panel2.add(this.label5, null);
        this.add(this.panel3, null);
        this.panel3.add(this.panel6, null);
        this.panel6.add(this.textField5, null);
        this.panel3.add(this.panel7, null);
        this.panel7.add(this.textField6, null);
        this.panel3.add(this.panel8, null);
        this.panel8.add(this.textField7, null);
        this.panel3.add(this.panel9, null);
        this.panel9.add(this.textField8, null);
        this.panel3.add(this.panel11, null);
        this.panel11.add(this.textField10, null);
        this.panel3.add(this.panel12, null);
        this.panel12.add(this.textField11, null);
        this.panel3.add(this.panel13, null);
        this.panel13.add(this.textField12, null);
        this.panel3.add(this.panel14, null);
        this.panel14.add(this.textField13, null);
        this.panel3.add(this.panel10, null);
        this.panel10.add(this.textField9, null);
        this.panel3.add(this.panel15, null);
        this.panel15.add(this.textField14, null);
        this.panel3.add(this.panel16, null);
        this.panel16.add(this.textField15, null);
        this.panel3.add(this.panel17, null);
        this.panel17.add(this.textField16, null);
        this.panel3.add(this.panel18, null);
        this.panel18.add(this.textField17, null);
        this.panel3.add(this.panel19, null);
        this.panel19.add(this.textField18, null);
        this.panel3.add(this.panel20, null);
        this.panel20.add(this.textField19, null);
        this.panel3.add(this.panel21, null);
        this.panel21.add(this.textField20, null);
        this.panel3.add(this.panel22, null);
        this.panel22.add(this.textField21, null);
        this.panel3.add(this.panel23, null);
        this.panel23.add(this.textField22, null);
        this.panel3.add(this.panel24, null);
        this.panel24.add(this.textField23, null);
        this.panel3.add(this.panel25, null);
        this.panel25.add(this.textField24, null);
        this.panel3.add(this.panel26, null);
        this.panel26.add(this.textField25, null);
        this.panel3.add(this.panel27, null);
        this.panel27.add(this.textField26, null);
        this.panel3.add(this.panel28, null);
        this.panel28.add(this.textField27, null);
        this.add(this.panel5, null);
        this.panel5.add(this.label4, null);
        this.panel5.add(this.textField1, null);
        this.panel5.add(this.label8, null);
        this.add(this.panel29, null);
        this.panel29.add(this.button21, null);
        this.add(this.panel30, null);
        this.panel30.add(this.button22, null);
        this.panel30.add(this.button23, null);
        this.panel30.add(this.button24, null);
        this.panel30.add(this.button25, null);
        this.panel30.add(this.button26, null);
        this.panel30.add(this.button27, null);
        this.add(this.panel31, null);
        this.panel31.add(this.button28, null);
        this.panel31.add(this.label6, null);
        this.panel31.add(this.label7, null);
        this.panel31.add(this.checkbox1, null);
        this.panel31.add(this.label9, null);
        this.panel31.add(this.label10, null);
        this.panel31.add(this.label11, null);
        this.panel31.add(this.label12, null);
        this.panel31.add(this.label13, null);
        this.panel31.add(this.label14, null);
        this.add(this.textField2, null);
        this.add(this.textField3, null);
        this.panel31.setBounds(new Rectangle(168, 49, 117, 239));
        this.button27.setLabel(this.bvelg);
        this.button27.setBackground(this.KnappefargeBak);
        this.button27.setBounds(new Rectangle(4, 153, 80, 27));
        this.button27.setFont(new Font("Dialog", 1, 13));
        this.button27.setForeground(this.KnappefargeFor);
        this.button26.setLabel(this.bvelg);
        this.button26.setBackground(this.KnappefargeBak);
        this.button26.setBounds(new Rectangle(4, 123, 80, 27));
        this.button26.setFont(new Font("Dialog", 1, 13));
        this.button26.setForeground(this.KnappefargeFor);
        this.button25.setLabel(this.bvelg);
        this.button25.setBackground(this.KnappefargeBak);
        this.button25.setBounds(new Rectangle(4, 93, 80, 27));
        this.button25.setFont(new Font("Dialog", 1, 13));
        this.button25.setForeground(this.KnappefargeFor);
        this.button24.setLabel(this.bvelg);
        this.button24.setBackground(this.KnappefargeBak);
        this.button24.setBounds(new Rectangle(4, 63, 80, 27));
        this.button24.setFont(new Font("Dialog", 1, 13));
        this.button24.setForeground(this.KnappefargeFor);
        this.button23.setLabel(this.bvelg);
        this.button23.setBackground(this.KnappefargeBak);
        this.button23.setBounds(new Rectangle(4, 33, 80, 27));
        this.button23.setFont(new Font("Dialog", 1, 13));
        this.button23.setForeground(this.KnappefargeFor);
        this.button22.setForeground(this.KnappefargeFor);
        this.button22.setFont(new Font("Dialog", 1, 13));
        this.button22.setBounds(new Rectangle(4, 3, 80, 27));
        this.button22.setBackground(this.KnappefargeBak);
        this.button22.setLabel(this.bvelg);
        this.button22.addMouseListener(new MaxYPEABC1(this));
        this.panel30.setBounds(new Rectangle(199, 290, 86, 181));
        this.panel30.setLayout(null);
        this.button21.setForeground(Color.white);
        this.button21.setFont(new Font("Dialog", 1, 13));
        this.button21.setBackground(Color.blue);
        this.button21.setBounds(new Rectangle(1, 3, 115, 25));
        this.button21.setLabel(this.bkast);
        this.button21.addMouseListener(new MaxYPEABC2(this));
        this.panel29.setBounds(new Rectangle(168, 472, 117, 30));
        this.panel29.setLayout(null);
        this.addMouseListener(new MaxYPEABC3(this));
        this.g = this.getGraphics();
        if (this.nedretekstfelt) {
            this.textField3.setForeground(Color.white);
            this.textField3.setFont(new Font("SansSerif", 0, 11));
            this.textField3.setBackground(Color.black);
            this.textField3.setText(this.Tkopier);
            this.textField3.setBounds(new Rectangle(4, 522, 282, 18));
        }
        else {
            this.textField2.setEnabled(false);
        }
        this.checkbox1.setFont(new Font("Dialog", 1, 13));
        this.label15.setForeground(Color.white);
        this.label15.setBackground(new Color(162, 0, 0));
        this.label15.setFont(new Font("Helvetica", 1, 14));
        this.label15.setBounds(new Rectangle(114, 3, 165, 17));
        this.label15.setText(this.var4);
        if (this.nedretekstfelt) {
            this.textField2.setForeground(Color.lightGray);
            this.textField2.setFont(new Font("SansSerif", 1, 12));
            this.textField2.setBackground(new Color(0, 0, 0));
            this.textField2.setText(this.Tkode);
            this.textField2.setBounds(new Rectangle(4, 503, 282, 18));
        }
        else {
            this.textField2.setEnabled(false);
        }
        this.label14.setForeground(Color.red);
        this.label14.setBackground(Color.black);
        this.label14.setBounds(new Rectangle(1, 106, 116, 19));
        this.label14.setText(this.var5);
        this.label14.setFont(new Font("Helvetica", 1, 13));
        this.label13.setForeground(Color.green);
        this.label13.setBackground(Color.black);
        this.label13.setBounds(new Rectangle(1, 85, 116, 19));
        this.label13.setText(this.rekord3);
        this.label13.setFont(new Font("Helvetica", 0, 12));
        this.label12.setForeground(Color.green);
        this.label12.setBackground(Color.black);
        this.label12.setBounds(new Rectangle(1, 64, 116, 19));
        this.label12.setText(this.rekord2);
        this.label12.setFont(new Font("Helvetica", 0, 12));
        this.label11.setForeground(Color.green);
        this.label11.setBackground(Color.black);
        this.label11.setBounds(new Rectangle(1, 43, 116, 19));
        this.label11.setText(this.rekord1);
        this.label11.setFont(new Font("Helvetica", 0, 12));
        this.label10.setForeground(Color.lightGray);
        this.label10.setBackground(Color.black);
        this.label10.setBounds(new Rectangle(1, 22, 116, 19));
        this.label10.setText(this.L10);
        this.label10.setFont(new Font("Helvetica", 0, 12));
        this.label9.setForeground(Color.lightGray);
        this.label9.setBackground(Color.black);
        this.label9.setBounds(new Rectangle(1, 1, 116, 19));
        this.label9.setText(this.L9);
        this.label9.setFont(new Font("Helvetica", 0, 12));
        this.label8.setForeground(Color.green);
        this.label8.setBackground(Color.black);
        this.label8.setBounds(new Rectangle(165, 1, 116, 19));
        this.label8.setText(this.L8);
        this.label8.setFont(new Font("Helvetica", 0, 12));
        this.checkbox1.setLabel(this.Lsparekast);
        this.checkbox1.addItemListener(new MaxYPEABC4(this));
        this.button20.addMouseListener(new MaxYPEABC5(this));
        this.button19.addMouseListener(new MaxYPEABC6(this));
        this.button18.addMouseListener(new MaxYPEABC7(this));
        this.button17.addMouseListener(new MaxYPEABC8(this));
        this.button16.addMouseListener(new MaxYPEABC9(this));
        this.button15.addMouseListener(new MaxYPEABC10(this));
        this.button14.addMouseListener(new MaxYPEABC11(this));
        this.button13.addMouseListener(new MaxYPEABC12(this));
        this.button12.addMouseListener(new MaxYPEABC13(this));
        this.button11.addMouseListener(new MaxYPEABC14(this));
        this.button10.addMouseListener(new MaxYPEABC15(this));
        this.button9.addMouseListener(new MaxYPEABC16(this));
        this.button8.addMouseListener(new MaxYPEABC17(this));
        this.button7.addMouseListener(new MaxYPEABC18(this));
        this.button6.addMouseListener(new MaxYPEABC19(this));
        this.button5.addMouseListener(new MaxYPEABC20(this));
        this.button4.addMouseListener(new MaxYPEABC21(this));
        this.button3.addMouseListener(new MaxYPEABC22(this));
        this.label6.setFont(new Font("Helvetica", 0, 12));
        this.label7.setForeground(Color.cyan);
        this.label7.setBackground(Color.black);
        this.label7.setBounds(new Rectangle(1, 165, 116, 19));
        this.label7.setText(this.LKastteringer);
        this.button2.addMouseListener(new MaxYPEABC23(this));
        this.panel31.setLayout(null);
        this.button28.setForeground(this.KnappefargeFor);
        this.button28.setFont(new Font("Helvetica", 3, 15));
        this.button28.setBounds(new Rectangle(2, 209, 113, 28));
        this.button28.setBackground(this.KnappefargeBak);
        this.button28.setLabel(this.bnyttspill);
        this.button28.addMouseListener(new MaxYPEABC24(this));
        this.button1.addMouseListener(new MaxYPEABC25(this));
        this.button27.addMouseListener(new MaxYPEABC26(this));
        this.button26.addMouseListener(new MaxYPEABC27(this));
        this.button25.addMouseListener(new MaxYPEABC28(this));
        this.button24.addMouseListener(new MaxYPEABC29(this));
        this.button23.addMouseListener(new MaxYPEABC30(this));
    }
    
    void this_mousePressed(final MouseEvent mouseevent) {
    }
    
    public void paint(final Graphics g) {
        if (!this.nedretekstfelt) {
            g.drawString("If you see this,", 5, 515);
            g.drawString("change applet HEIGHT=\"541\" to: HEIGHT=\"506\" ", 5, 530);
        }
        final Font bokstavtern = new Font("Arial", 1, 20);
        final Font skrift = new Font("Arial", 0, 14);
        g.setFont(bokstavtern);
        g.setColor(Color.lightGray);
        if (this.terningkastAxxP.valg6 % 2 != 0) {
            g.setColor(Color.red);
        }
        g.fillRoundRect(this.xx6, this.yy6, this.bredde, this.hoyde, 6, 6);
        g.setColor(Color.blue);
        g.drawRoundRect(this.xx6, this.yy6, this.bredde, this.hoyde, 6, 6);
        g.setColor(Color.black);
        if (this.terningkastAxxP.terning1 == 0) {
            g.drawString("?", this.xx6 + this.plassx, this.yy6 + this.p1 + this.plassy);
        }
        if (this.terningkastAxxP.terning6 == 1) {
            g.fillRect(this.xx6 + this.p1, this.yy6 + this.p1, 5, 5);
        }
        if (this.terningkastAxxP.terning6 == 2) {
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning6 == 3) {
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1, this.yy6 + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning6 == 4) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning6 == 5) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1, this.yy6 + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning6 == 6) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 + this.p1 + 8, 5, 5);
        }
        g.setColor(Color.lightGray);
        if (this.terningkastAxxP.valg5 % 2 != 0) {
            g.setColor(Color.red);
        }
        g.fillRoundRect(this.xx6, this.yy6 - this.av, this.bredde, this.hoyde, 6, 6);
        g.setColor(Color.blue);
        g.drawRoundRect(this.xx6, this.yy6 - this.av, this.bredde, this.hoyde, 6, 6);
        g.setColor(Color.black);
        if (this.terningkastAxxP.terning1 == 0) {
            g.drawString("?", this.xx6 + this.plassx, this.yy6 - this.av + this.p1 + this.plassy);
        }
        if (this.terningkastAxxP.terning5 == 1) {
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av + this.p1, 5, 5);
        }
        if (this.terningkastAxxP.terning5 == 2) {
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning5 == 3) {
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning5 == 4) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning5 == 5) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning5 == 6) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av + this.p1 + 8, 5, 5);
        }
        g.setColor(Color.lightGray);
        if (this.terningkastAxxP.valg4 % 2 != 0) {
            g.setColor(Color.red);
        }
        g.fillRoundRect(this.xx6, this.yy6 - this.av - this.av, this.bredde, this.hoyde, 6, 6);
        g.setColor(Color.blue);
        g.drawRoundRect(this.xx6, this.yy6 - this.av - this.av, this.bredde, this.hoyde, 6, 6);
        g.setColor(Color.black);
        if (this.terningkastAxxP.terning1 == 0) {
            g.drawString("?", this.xx6 + this.plassx, this.yy6 - this.av - this.av + this.p1 + this.plassy);
        }
        if (this.terningkastAxxP.terning4 == 1) {
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av - this.av + this.p1, 5, 5);
        }
        if (this.terningkastAxxP.terning4 == 2) {
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning4 == 3) {
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning4 == 4) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning4 == 5) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning4 == 6) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av + this.p1 + 8, 5, 5);
        }
        g.setColor(Color.lightGray);
        if (this.terningkastAxxP.valg3 % 2 != 0) {
            g.setColor(Color.red);
        }
        g.fillRoundRect(this.xx6, this.yy6 - this.av - this.av - this.av, this.bredde, this.hoyde, 6, 6);
        g.setColor(Color.blue);
        g.drawRoundRect(this.xx6, this.yy6 - this.av - this.av - this.av, this.bredde, this.hoyde, 6, 6);
        g.setColor(Color.black);
        if (this.terningkastAxxP.terning1 == 0) {
            g.drawString("?", this.xx6 + this.plassx, this.yy6 - this.av - this.av - this.av + this.p1 + this.plassy);
        }
        if (this.terningkastAxxP.terning3 == 1) {
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av - this.av - this.av + this.p1, 5, 5);
        }
        if (this.terningkastAxxP.terning3 == 2) {
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning3 == 3) {
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning3 == 4) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning3 == 5) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        if (this.terningkastAxxP.terning3 == 6) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        g.setColor(Color.lightGray);
        if (this.terningkastAxxP.valg2 % 2 != 0) {
            g.setColor(Color.red);
        }
        g.fillRoundRect(this.xx6, this.yy6 - this.av - this.av - this.av - this.av, this.bredde, this.hoyde, 6, 6);
        g.setColor(Color.blue);
        g.drawRoundRect(this.xx6, this.yy6 - this.av - this.av - this.av - this.av, this.bredde, this.hoyde, 6, 6);
        g.setColor(Color.black);
        if (this.terningkastAxxP.terning1 == 0) {
            g.drawString("?", this.xx6 + this.plassx, this.yy6 - this.av - this.av - this.av - this.av + this.p1 + this.plassy);
        }
        if (this.terningkastAxxP.terning2 == 1) {
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av - this.av - this.av - this.av + this.p1, 5, 5);
        }
        else if (this.terningkastAxxP.terning2 == 2) {
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        else if (this.terningkastAxxP.terning2 == 3) {
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av - this.av - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        else if (this.terningkastAxxP.terning2 == 4) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        else if (this.terningkastAxxP.terning2 == 5) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av - this.av - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        else if (this.terningkastAxxP.terning2 == 6) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        g.setColor(Color.lightGray);
        if (this.terningkastAxxP.valg1 % 2 != 0 && this.kastet) {
            g.setColor(Color.red);
        }
        g.fillRoundRect(this.xx6, this.yy6 - this.av - this.av - this.av - this.av - this.av, this.bredde, this.hoyde, 6, 6);
        g.setColor(Color.blue);
        g.drawRoundRect(this.xx6, this.yy6 - this.av - this.av - this.av - this.av - this.av, this.bredde, this.hoyde, 6, 6);
        g.setColor(Color.black);
        if (this.terningkastAxxP.terning1 == 0) {
            g.drawString("?", this.xx6 + this.plassx, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 + this.plassy);
        }
        if (this.terningkastAxxP.terning1 == 1) {
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1, 5, 5);
        }
        else if (this.terningkastAxxP.terning1 == 2) {
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        else if (this.terningkastAxxP.terning1 == 3) {
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        else if (this.terningkastAxxP.terning1 == 4) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        else if (this.terningkastAxxP.terning1 == 5) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
        else if (this.terningkastAxxP.terning1 == 6) {
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 - 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1, 5, 5);
            g.fillRect(this.xx6 + this.p1 - 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
            g.fillRect(this.xx6 + this.p1 + 8, this.yy6 - this.av - this.av - this.av - this.av - this.av + this.p1 + 8, 5, 5);
        }
    }
    
    void button21_mousePressed(final MouseEvent e) {
        this.checkbox1.setEnabled(false);
        if (this.checkboxkontroll % 2 == 0) {
            this.terningkastAxxP.ubenyttet = 0;
            this.terningkastAxxP.count = 0;
        }
        ++this.jukset;
        this.tallet = this.textField1.getText();
        if (this.tallet.length() > 1 && this.tallet.length() > 7 && this.tallet.charAt(0) == '4' && this.tallet.charAt(1) == '5' && this.tallet.charAt(6) == '6' && this.tallet.charAt(7) == '7') {
            this.terningkastAxxP.kasteteller = -3;
            this.textField2.setText(String.valueOf("Kast: ").concat(String.valueOf(this.jukset)));
        }
        final TerningAxxP terningkastAxxP = this.terningkastAxxP;
        ++terningkastAxxP.kasteteller;
        if (this.terningkastAxxP.count == 0) {
            this.terningkastAxxP.ubenyttet = 0;
        }
        if (this.terningkastAxxP.kasteteller <= 3 + this.terningkastAxxP.ubenyttet) {
            this.terningkastAxxP.Kaster();
            if (this.terningkastAxxP.kasteteller > 3 && this.terningkastAxxP.count != 0) {
                final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                --terningkastAxxP2.count;
            }
            this.label7.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf("   ").concat(String.valueOf(this.terningkastAxxP.kastigjen))).concat(String.valueOf("  +  "))).concat(String.valueOf(this.terningkastAxxP.count))).concat(String.valueOf(this.Lkastigjen)));
            this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.count)));
        }
        if (this.terningkastAxxP.kastigjen > 0) {
            final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
            --terningkastAxxP3.kastigjen;
        }
        this.kastet = true;
        this.repaint();
    }
    
    void button22_mousePressed(final MouseEvent e) {
        if (this.kastet) {
            final TerningAxxP terningkastAxxP = this.terningkastAxxP;
            ++terningkastAxxP.valg1;
        }
        this.repaint();
    }
    
    void button23_mousePressed(final MouseEvent e) {
        if (this.kastet) {
            final TerningAxxP terningkastAxxP = this.terningkastAxxP;
            ++terningkastAxxP.valg2;
        }
        this.repaint();
    }
    
    void button24_mousePressed(final MouseEvent e) {
        if (this.kastet) {
            final TerningAxxP terningkastAxxP = this.terningkastAxxP;
            ++terningkastAxxP.valg3;
        }
        this.repaint();
    }
    
    void button25_mousePressed(final MouseEvent e) {
        if (this.kastet) {
            final TerningAxxP terningkastAxxP = this.terningkastAxxP;
            ++terningkastAxxP.valg4;
        }
        this.repaint();
    }
    
    void button26_mousePressed(final MouseEvent e) {
        if (this.kastet) {
            final TerningAxxP terningkastAxxP = this.terningkastAxxP;
            ++terningkastAxxP.valg5;
        }
        this.repaint();
    }
    
    void button27_mousePressed(final MouseEvent e) {
        if (this.kastet) {
            final TerningAxxP terningkastAxxP = this.terningkastAxxP;
            ++terningkastAxxP.valg6;
        }
        this.repaint();
    }
    
    void button1_mousePressed(final MouseEvent e) {
        this.enere = 0;
        if (this.kastet) {
            ++this.tellerEn;
            if (this.tellerEn <= 1) {
                ++this.regist;
                if ((this.terningkastAxxP.valg1 % 2 != 0 && this.terningkastAxxP.terning1 == 1) || this.terningkastAxxP.terning1 == 1) {
                    ++this.enere;
                }
                if ((this.terningkastAxxP.valg2 % 2 != 0 && this.terningkastAxxP.terning2 == 1) || this.terningkastAxxP.terning2 == 1) {
                    ++this.enere;
                }
                if ((this.terningkastAxxP.valg3 % 2 != 0 && this.terningkastAxxP.terning3 == 1) || this.terningkastAxxP.terning3 == 1) {
                    ++this.enere;
                }
                if ((this.terningkastAxxP.valg4 % 2 != 0 && this.terningkastAxxP.terning4 == 1) || this.terningkastAxxP.terning4 == 1) {
                    ++this.enere;
                }
                if ((this.terningkastAxxP.valg5 % 2 != 0 && this.terningkastAxxP.terning5 == 1) || this.terningkastAxxP.terning5 == 1) {
                    ++this.enere;
                }
                if ((this.terningkastAxxP.valg6 % 2 != 0 && this.terningkastAxxP.terning6 == 1) || this.terningkastAxxP.terning6 == 1) {
                    ++this.enere;
                }
                if (this.enere >= 1) {
                    this.textField5.setText(String.valueOf("").concat(String.valueOf(this.enere * 1)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA1 += this.enere * 1;
                    this.textField11.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA1)));
                    if (this.terningkastAxxP.sumA1 <= 84) {
                        if (this.language) {
                            this.label9.setForeground(Color.yellow);
                            this.label9.setText(String.valueOf("For \u00e5 f\u00e5 bonus: ").concat(String.valueOf(84 - this.terningkastAxxP.sumA1)));
                        }
                        else {
                            this.label9.setForeground(Color.yellow);
                            this.label9.setText(String.valueOf("To get bonus: ").concat(String.valueOf(84 - this.terningkastAxxP.sumA1)));
                        }
                    }
                    else if (this.language) {
                        this.label9.setForeground(Color.yellow);
                        this.label9.setText("For \u00e5 f\u00e5 bonus: 0");
                    }
                    else {
                        this.label9.setForeground(Color.yellow);
                        this.label9.setText("To get bonus: 0");
                    }
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField12.setText("100");
                    }
                    else {
                        this.textField12.setText("0");
                    }
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.sumA2 += this.enere * 1;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField5.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP4 = this.terningkastAxxP;
                    terningkastAxxP4.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button29_mousePressed(final MouseEvent mouseevent) {
    }
    
    void button28_mousePressed(final MouseEvent e) {
        this.fri = true;
        this.tvungen = false;
        this.checkbox1.setEnabled(true);
        this.label9.setForeground(Color.lightGray);
        this.label9.setText(this.L9);
        this.terningkastAxxP.terning1 = 0;
        this.terningkastAxxP.terning2 = 0;
        this.terningkastAxxP.terning3 = 0;
        this.terningkastAxxP.terning4 = 0;
        this.terningkastAxxP.terning5 = 0;
        this.terningkastAxxP.terning6 = 0;
        this.terningkastAxxP.valg1 = 2;
        this.terningkastAxxP.valg2 = 2;
        this.terningkastAxxP.valg3 = 2;
        this.terningkastAxxP.valg4 = 2;
        this.terningkastAxxP.valg5 = 2;
        this.terningkastAxxP.valg6 = 2;
        this.terningkastAxxP.kasteteller = 0;
        this.terningkastAxxP.ubenyttet = 0;
        this.terningkastAxxP.ubenytt1 = 1;
        this.terningkastAxxP.ubenytt2 = 2;
        this.terningkastAxxP.kastigjen = 2;
        this.terningkastAxxP.count = 0;
        this.terningkastAxxP.sumA1 = 0;
        this.terningkastAxxP.sumA2 = 0;
        this.kastet = false;
        this.tvungen = true;
        this.fri = false;
        this.enere = 0;
        this.toere = 0;
        this.treere = 0;
        this.firere = 0;
        this.femmere = 0;
        this.seksere = 0;
        this.par = 0;
        this.topar = 0;
        this.trepar = 0;
        this.trelike = 0;
        this.firelike = 0;
        this.femlike = 0;
        this.litens = 0;
        this.stors = 0;
        this.fulls = 0;
        this.totre = 0;
        this.tretre = 0;
        this.fireto = 0;
        this.sjanse = 0;
        this.yatzy = 0;
        this.tallet = "0";
        this.regist = 0;
        this.toparH = 0;
        this.toparL = 0;
        this.treparL = 0;
        this.treparM = 0;
        this.treparH = 0;
        this.liten1 = 0;
        this.liten2 = 0;
        this.liten3 = 0;
        this.liten4 = 0;
        this.liten5 = 0;
        this.totreL = 0;
        this.totreH = 0;
        this.tretreH = 0;
        this.tretreL = 0;
        this.firetoH = 0;
        this.firetoL = 0;
        this.tellerEn = 0;
        this.tellerTo = 0;
        this.tellerTre = 0;
        this.tellerFire = 0;
        this.tellerFem = 0;
        this.tellerSeks = 0;
        this.tellerPar = 0;
        this.tellerTopar = 0;
        this.tellerTrepar = 0;
        this.tellerTrelike = 0;
        this.tellerFirelike = 0;
        this.tellerFemlike = 0;
        this.tellerLitens = 0;
        this.tellerStors = 0;
        this.tellerFulls = 0;
        this.tellerTotre = 0;
        this.tellerTretre = 0;
        this.tellerFireto = 0;
        this.tellerSjanse = 0;
        this.tellerYatzy = 0;
        this.textField5.setText("");
        this.textField6.setText("");
        this.textField7.setText("");
        this.textField8.setText("");
        this.textField9.setText("");
        this.textField10.setText("");
        this.textField11.setText("");
        this.textField12.setText("");
        this.textField13.setText("");
        this.textField14.setText("");
        this.textField15.setText("");
        this.textField16.setText("");
        this.textField17.setText("");
        this.textField18.setText("");
        this.textField19.setText("");
        this.textField20.setText("");
        this.textField21.setText("");
        this.textField22.setText("");
        this.textField23.setText("");
        this.textField24.setText("");
        this.textField25.setText("");
        this.textField26.setText("");
        this.textField27.setText("");
        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
        this.label7.setText(this.LKastteringer);
        this.terningkastAxxP.nullstill();
        this.repaint();
        for (int a = 1; a < 7; ++a) {
            this.terningkastAxxP.par[a] = 0;
            this.terningkastAxxP.topar[a] = 0;
            this.terningkastAxxP.trepar[a] = 0;
            this.terningkastAxxP.trelike[a] = 0;
            this.terningkastAxxP.firelike[a] = 0;
            this.terningkastAxxP.femlike[a] = 0;
            this.terningkastAxxP.litens[a] = 0;
            this.terningkastAxxP.stors[a] = 0;
            this.terningkastAxxP.fulls[a] = 0;
            this.terningkastAxxP.totre[a] = 0;
            this.terningkastAxxP.tretre[a] = 0;
            this.terningkastAxxP.fireto[a] = 0;
            this.terningkastAxxP.sjanse[a] = 0;
            this.terningkastAxxP.yatzy[a] = 0;
        }
    }
    
    void button2_mousePressed(final MouseEvent e) {
        this.toere = 0;
        if (this.kastet) {
            ++this.tellerTo;
            if (this.tellerTo <= 1) {
                ++this.regist;
                if ((this.terningkastAxxP.valg1 % 2 != 0 && this.terningkastAxxP.terning1 == 2) || this.terningkastAxxP.terning1 == 2) {
                    ++this.toere;
                }
                if ((this.terningkastAxxP.valg2 % 2 != 0 && this.terningkastAxxP.terning2 == 2) || this.terningkastAxxP.terning2 == 2) {
                    ++this.toere;
                }
                if ((this.terningkastAxxP.valg3 % 2 != 0 && this.terningkastAxxP.terning3 == 2) || this.terningkastAxxP.terning3 == 2) {
                    ++this.toere;
                }
                if ((this.terningkastAxxP.valg4 % 2 != 0 && this.terningkastAxxP.terning4 == 2) || this.terningkastAxxP.terning4 == 2) {
                    ++this.toere;
                }
                if ((this.terningkastAxxP.valg5 % 2 != 0 && this.terningkastAxxP.terning5 == 2) || this.terningkastAxxP.terning5 == 2) {
                    ++this.toere;
                }
                if ((this.terningkastAxxP.valg6 % 2 != 0 && this.terningkastAxxP.terning6 == 2) || this.terningkastAxxP.terning6 == 2) {
                    ++this.toere;
                }
                if (this.toere >= 1) {
                    this.textField6.setText(String.valueOf("").concat(String.valueOf(this.toere * 2)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA1 += this.toere * 2;
                    this.textField11.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA1)));
                    if (this.terningkastAxxP.sumA1 <= 84) {
                        if (this.language) {
                            this.label9.setForeground(Color.yellow);
                            this.label9.setText(String.valueOf("For \u00e5 f\u00e5 bonus: ").concat(String.valueOf(84 - this.terningkastAxxP.sumA1)));
                        }
                        else {
                            this.label9.setForeground(Color.yellow);
                            this.label9.setText(String.valueOf("To get bonus: ").concat(String.valueOf(84 - this.terningkastAxxP.sumA1)));
                        }
                    }
                    else if (this.language) {
                        this.label9.setForeground(Color.yellow);
                        this.label9.setText("For \u00e5 f\u00e5 bonus: 0");
                    }
                    else {
                        this.label9.setForeground(Color.yellow);
                        this.label9.setText("To get bonus: 0");
                    }
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField12.setText("100");
                    }
                    else {
                        this.textField12.setText("0");
                    }
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.sumA2 += this.toere * 2;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField6.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP4 = this.terningkastAxxP;
                    terningkastAxxP4.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button3_mousePressed(final MouseEvent e) {
        this.treere = 0;
        if (this.kastet) {
            ++this.tellerTre;
            if (this.tellerTre <= 1) {
                ++this.regist;
                if ((this.terningkastAxxP.valg1 % 2 != 0 && this.terningkastAxxP.terning1 == 3) || this.terningkastAxxP.terning1 == 3) {
                    ++this.treere;
                }
                if ((this.terningkastAxxP.valg2 % 2 != 0 && this.terningkastAxxP.terning2 == 3) || this.terningkastAxxP.terning2 == 3) {
                    ++this.treere;
                }
                if ((this.terningkastAxxP.valg3 % 2 != 0 && this.terningkastAxxP.terning3 == 3) || this.terningkastAxxP.terning3 == 3) {
                    ++this.treere;
                }
                if ((this.terningkastAxxP.valg4 % 2 != 0 && this.terningkastAxxP.terning4 == 3) || this.terningkastAxxP.terning4 == 3) {
                    ++this.treere;
                }
                if ((this.terningkastAxxP.valg5 % 2 != 0 && this.terningkastAxxP.terning5 == 3) || this.terningkastAxxP.terning5 == 3) {
                    ++this.treere;
                }
                if ((this.terningkastAxxP.valg6 % 2 != 0 && this.terningkastAxxP.terning6 == 3) || this.terningkastAxxP.terning6 == 3) {
                    ++this.treere;
                }
                if (this.treere >= 1) {
                    this.textField7.setText(String.valueOf("").concat(String.valueOf(this.treere * 3)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA1 += this.treere * 3;
                    this.textField11.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA1)));
                    if (this.terningkastAxxP.sumA1 <= 84) {
                        if (this.language) {
                            this.label9.setForeground(Color.yellow);
                            this.label9.setText(String.valueOf("For \u00e5 f\u00e5 bonus: ").concat(String.valueOf(84 - this.terningkastAxxP.sumA1)));
                        }
                        else {
                            this.label9.setForeground(Color.yellow);
                            this.label9.setText(String.valueOf("To get bonus: ").concat(String.valueOf(84 - this.terningkastAxxP.sumA1)));
                        }
                    }
                    else if (this.language) {
                        this.label9.setForeground(Color.yellow);
                        this.label9.setText("For \u00e5 f\u00e5 bonus: 0");
                    }
                    else {
                        this.label9.setForeground(Color.yellow);
                        this.label9.setText("To get bonus: 0");
                    }
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField12.setText("100");
                    }
                    else {
                        this.textField12.setText("0");
                    }
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.sumA2 += this.treere * 3;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField7.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP4 = this.terningkastAxxP;
                    terningkastAxxP4.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button4_mousePressed(final MouseEvent e) {
        this.firere = 0;
        if (this.kastet) {
            ++this.tellerFire;
            if (this.tellerFire <= 1) {
                ++this.regist;
                if ((this.terningkastAxxP.valg1 % 2 != 0 && this.terningkastAxxP.terning1 == 4) || this.terningkastAxxP.terning1 == 4) {
                    ++this.firere;
                }
                if ((this.terningkastAxxP.valg2 % 2 != 0 && this.terningkastAxxP.terning2 == 4) || this.terningkastAxxP.terning2 == 4) {
                    ++this.firere;
                }
                if ((this.terningkastAxxP.valg3 % 2 != 0 && this.terningkastAxxP.terning3 == 4) || this.terningkastAxxP.terning3 == 4) {
                    ++this.firere;
                }
                if ((this.terningkastAxxP.valg4 % 2 != 0 && this.terningkastAxxP.terning4 == 4) || this.terningkastAxxP.terning4 == 4) {
                    ++this.firere;
                }
                if ((this.terningkastAxxP.valg5 % 2 != 0 && this.terningkastAxxP.terning5 == 4) || this.terningkastAxxP.terning5 == 4) {
                    ++this.firere;
                }
                if ((this.terningkastAxxP.valg6 % 2 != 0 && this.terningkastAxxP.terning6 == 4) || this.terningkastAxxP.terning6 == 4) {
                    ++this.firere;
                }
                if (this.firere >= 1) {
                    this.textField8.setText(String.valueOf("").concat(String.valueOf(this.firere * 4)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA1 += this.firere * 4;
                    this.textField11.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA1)));
                    if (this.terningkastAxxP.sumA1 <= 84) {
                        if (this.language) {
                            this.label9.setForeground(Color.yellow);
                            this.label9.setText(String.valueOf("For \u00e5 f\u00e5 bonus: ").concat(String.valueOf(84 - this.terningkastAxxP.sumA1)));
                        }
                        else {
                            this.label9.setForeground(Color.yellow);
                            this.label9.setText(String.valueOf("To get bonus: ").concat(String.valueOf(84 - this.terningkastAxxP.sumA1)));
                        }
                    }
                    else if (this.language) {
                        this.label9.setForeground(Color.yellow);
                        this.label9.setText("For \u00e5 f\u00e5 bonus: 0");
                    }
                    else {
                        this.label9.setForeground(Color.yellow);
                        this.label9.setText("To get bonus: 0");
                    }
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField12.setText("100");
                    }
                    else {
                        this.textField12.setText("0");
                    }
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.sumA2 += this.firere * 4;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField8.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP4 = this.terningkastAxxP;
                    terningkastAxxP4.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button5_mousePressed(final MouseEvent e) {
        this.femmere = 0;
        if (this.kastet) {
            ++this.tellerFem;
            if (this.tellerFem <= 1) {
                ++this.regist;
                if ((this.terningkastAxxP.valg1 % 2 != 0 && this.terningkastAxxP.terning1 == 5) || this.terningkastAxxP.terning1 == 5) {
                    ++this.femmere;
                }
                if ((this.terningkastAxxP.valg2 % 2 != 0 && this.terningkastAxxP.terning2 == 5) || this.terningkastAxxP.terning2 == 5) {
                    ++this.femmere;
                }
                if ((this.terningkastAxxP.valg3 % 2 != 0 && this.terningkastAxxP.terning3 == 5) || this.terningkastAxxP.terning3 == 5) {
                    ++this.femmere;
                }
                if ((this.terningkastAxxP.valg4 % 2 != 0 && this.terningkastAxxP.terning4 == 5) || this.terningkastAxxP.terning4 == 5) {
                    ++this.femmere;
                }
                if ((this.terningkastAxxP.valg5 % 2 != 0 && this.terningkastAxxP.terning5 == 5) || this.terningkastAxxP.terning5 == 5) {
                    ++this.femmere;
                }
                if ((this.terningkastAxxP.valg6 % 2 != 0 && this.terningkastAxxP.terning6 == 5) || this.terningkastAxxP.terning6 == 5) {
                    ++this.femmere;
                }
                if (this.femmere >= 1) {
                    this.textField9.setText(String.valueOf("").concat(String.valueOf(this.femmere * 5)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA1 += this.femmere * 5;
                    this.textField11.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA1)));
                    if (this.terningkastAxxP.sumA1 <= 84) {
                        if (this.language) {
                            this.label9.setForeground(Color.yellow);
                            this.label9.setText(String.valueOf("For \u00e5 f\u00e5 bonus: ").concat(String.valueOf(84 - this.terningkastAxxP.sumA1)));
                        }
                        else {
                            this.label9.setForeground(Color.yellow);
                            this.label9.setText(String.valueOf("To get bonus: ").concat(String.valueOf(84 - this.terningkastAxxP.sumA1)));
                        }
                    }
                    else if (this.language) {
                        this.label9.setForeground(Color.yellow);
                        this.label9.setText("For \u00e5 f\u00e5 bonus: 0");
                    }
                    else {
                        this.label9.setForeground(Color.yellow);
                        this.label9.setText("To get bonus: 0");
                    }
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField12.setText("100");
                    }
                    else {
                        this.textField12.setText("0");
                    }
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.sumA2 += this.femmere * 5;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField9.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP4 = this.terningkastAxxP;
                    terningkastAxxP4.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button6_mousePressed(final MouseEvent e) {
        this.seksere = 0;
        if (this.kastet) {
            ++this.tellerSeks;
            if (this.tellerSeks <= 1) {
                ++this.regist;
                if ((this.terningkastAxxP.valg1 % 2 != 0 && this.terningkastAxxP.terning1 == 6) || this.terningkastAxxP.terning1 == 6) {
                    ++this.seksere;
                }
                if ((this.terningkastAxxP.valg2 % 2 != 0 && this.terningkastAxxP.terning2 == 6) || this.terningkastAxxP.terning2 == 6) {
                    ++this.seksere;
                }
                if ((this.terningkastAxxP.valg3 % 2 != 0 && this.terningkastAxxP.terning3 == 6) || this.terningkastAxxP.terning3 == 6) {
                    ++this.seksere;
                }
                if ((this.terningkastAxxP.valg4 % 2 != 0 && this.terningkastAxxP.terning4 == 6) || this.terningkastAxxP.terning4 == 6) {
                    ++this.seksere;
                }
                if ((this.terningkastAxxP.valg5 % 2 != 0 && this.terningkastAxxP.terning5 == 6) || this.terningkastAxxP.terning5 == 6) {
                    ++this.seksere;
                }
                if ((this.terningkastAxxP.valg6 % 2 != 0 && this.terningkastAxxP.terning6 == 6) || this.terningkastAxxP.terning6 == 6) {
                    ++this.seksere;
                }
                if (this.seksere >= 1) {
                    this.textField10.setText(String.valueOf("").concat(String.valueOf(this.seksere * 6)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA1 += this.seksere * 6;
                    this.textField11.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA1)));
                    if (this.terningkastAxxP.sumA1 <= 84) {
                        if (this.language) {
                            this.label9.setForeground(Color.yellow);
                            this.label9.setText(String.valueOf("For \u00e5 f\u00e5 bonus: ").concat(String.valueOf(84 - this.terningkastAxxP.sumA1)));
                        }
                        else {
                            this.label9.setForeground(Color.yellow);
                            this.label9.setText(String.valueOf("To get bonus: ").concat(String.valueOf(84 - this.terningkastAxxP.sumA1)));
                        }
                    }
                    else if (this.language) {
                        this.label9.setForeground(Color.yellow);
                        this.label9.setText("For \u00e5 f\u00e5 bonus: 0");
                    }
                    else {
                        this.label9.setForeground(Color.yellow);
                        this.label9.setText("To get bonus: 0");
                    }
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField12.setText("100");
                    }
                    else {
                        this.textField12.setText("0");
                    }
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.sumA2 += this.seksere * 6;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField10.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP4 = this.terningkastAxxP;
                    terningkastAxxP4.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button7_mousePressed(final MouseEvent e) {
        this.par = 0;
        if (this.kastet) {
            ++this.tellerPar;
            if (this.tellerPar <= 1) {
                ++this.regist;
                if (this.terningkastAxxP.terning1 == this.terningkastAxxP.terning2 || this.terningkastAxxP.terning1 == this.terningkastAxxP.terning3) {
                    this.par = this.terningkastAxxP.terning1 * 2;
                    this.terningkastAxxP.par[this.terningkastAxxP.terning1] = this.terningkastAxxP.terning1;
                    if (this.terningkastAxxP.valg1 % 2 != 0) {
                        this.par = this.terningkastAxxP.terning1 * 2;
                        this.terningkastAxxP.par[this.terningkastAxxP.terning1] = this.terningkastAxxP.terning1;
                    }
                }
                else if (this.terningkastAxxP.terning1 == this.terningkastAxxP.terning4 || this.terningkastAxxP.terning1 == this.terningkastAxxP.terning5) {
                    this.par = this.terningkastAxxP.terning1 * 2;
                    this.terningkastAxxP.par[this.terningkastAxxP.terning1] = this.terningkastAxxP.terning1;
                    if (this.terningkastAxxP.valg1 % 2 != 0) {
                        this.par = this.terningkastAxxP.terning1 * 2;
                        this.terningkastAxxP.par[this.terningkastAxxP.terning1] = this.terningkastAxxP.terning1;
                    }
                }
                else if (this.terningkastAxxP.terning1 == this.terningkastAxxP.terning6) {
                    this.par = this.terningkastAxxP.terning1 * 2;
                    this.terningkastAxxP.par[this.terningkastAxxP.terning1] = this.terningkastAxxP.terning1;
                    if (this.terningkastAxxP.valg1 % 2 != 0) {
                        this.par = this.terningkastAxxP.terning1 * 2;
                        this.terningkastAxxP.par[this.terningkastAxxP.terning1] = this.terningkastAxxP.terning1;
                    }
                }
                if (this.terningkastAxxP.terning2 == this.terningkastAxxP.terning3 || this.terningkastAxxP.terning2 == this.terningkastAxxP.terning4) {
                    this.par = this.terningkastAxxP.terning2 * 2;
                    this.terningkastAxxP.par[this.terningkastAxxP.terning2] = this.terningkastAxxP.terning2;
                    if (this.terningkastAxxP.valg2 % 2 != 0) {
                        this.par = this.terningkastAxxP.terning2 * 2;
                        this.terningkastAxxP.par[this.terningkastAxxP.terning2] = this.terningkastAxxP.terning2;
                    }
                }
                else if (this.terningkastAxxP.terning2 == this.terningkastAxxP.terning5 || this.terningkastAxxP.terning1 == this.terningkastAxxP.terning6) {
                    this.par = this.terningkastAxxP.terning2 * 2;
                    this.terningkastAxxP.par[this.terningkastAxxP.terning2] = this.terningkastAxxP.terning2;
                    if (this.terningkastAxxP.valg2 % 2 != 0) {
                        this.par = this.terningkastAxxP.terning2 * 2;
                        this.terningkastAxxP.par[this.terningkastAxxP.terning2] = this.terningkastAxxP.terning2;
                    }
                }
                if (this.terningkastAxxP.terning3 == this.terningkastAxxP.terning4 || this.terningkastAxxP.terning3 == this.terningkastAxxP.terning5) {
                    this.par = this.terningkastAxxP.terning3 * 2;
                    this.terningkastAxxP.par[this.terningkastAxxP.terning3] = this.terningkastAxxP.terning3;
                    if (this.terningkastAxxP.valg3 % 2 != 0) {
                        this.par = this.terningkastAxxP.terning3 * 2;
                        this.terningkastAxxP.par[this.terningkastAxxP.terning3] = this.terningkastAxxP.terning3;
                    }
                }
                else if (this.terningkastAxxP.terning3 == this.terningkastAxxP.terning6) {
                    this.par = this.terningkastAxxP.terning3 * 2;
                    this.terningkastAxxP.par[this.terningkastAxxP.terning3] = this.terningkastAxxP.terning3;
                    if (this.terningkastAxxP.valg3 % 2 != 0) {
                        this.par = this.terningkastAxxP.terning3 * 2;
                        this.terningkastAxxP.par[this.terningkastAxxP.terning3] = this.terningkastAxxP.terning3;
                    }
                }
                if (this.terningkastAxxP.terning4 == this.terningkastAxxP.terning5 || this.terningkastAxxP.terning4 == this.terningkastAxxP.terning6) {
                    this.par = this.terningkastAxxP.terning4 * 2;
                    this.terningkastAxxP.par[this.terningkastAxxP.terning4] = this.terningkastAxxP.terning4;
                    if (this.terningkastAxxP.valg4 % 2 != 0) {
                        this.par = this.terningkastAxxP.terning4 * 2;
                        this.terningkastAxxP.par[this.terningkastAxxP.terning4] = this.terningkastAxxP.terning4;
                    }
                }
                if (this.terningkastAxxP.terning5 == this.terningkastAxxP.terning6) {
                    this.par = this.terningkastAxxP.terning5 * 2;
                    this.terningkastAxxP.par[this.terningkastAxxP.terning5] = this.terningkastAxxP.terning5;
                    if (this.terningkastAxxP.valg5 % 2 != 0) {
                        this.par = this.terningkastAxxP.terning5 * 2;
                        this.terningkastAxxP.par[this.terningkastAxxP.terning5] = this.terningkastAxxP.terning5;
                    }
                }
                if (this.terningkastAxxP.par[6] == 6) {
                    this.par = 12;
                }
                else if (this.terningkastAxxP.par[5] == 5) {
                    this.par = 10;
                }
                else if (this.terningkastAxxP.par[4] == 4) {
                    this.par = 8;
                }
                else if (this.terningkastAxxP.par[3] == 3) {
                    this.par = 6;
                }
                else if (this.terningkastAxxP.par[2] == 2) {
                    this.par = 4;
                }
                else if (this.terningkastAxxP.par[1] == 1) {
                    this.par = 2;
                }
                if (this.par >= 1) {
                    this.textField13.setText(String.valueOf("").concat(String.valueOf(this.par)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA2 += this.par;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField13.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button8_mousePressed(final MouseEvent e) {
        this.topar = 0;
        if (this.kastet) {
            ++this.tellerTopar;
            if (this.tellerTopar <= 1) {
                ++this.regist;
                for (int a = 1; a < 7; ++a) {
                    if (this.terningkastAxxP.terning1 == a) {
                        final int[] topar = this.terningkastAxxP.topar;
                        final int n = a;
                        ++topar[n];
                    }
                    if (this.terningkastAxxP.terning2 == a) {
                        final int[] topar2 = this.terningkastAxxP.topar;
                        final int n2 = a;
                        ++topar2[n2];
                    }
                    if (this.terningkastAxxP.terning3 == a) {
                        final int[] topar3 = this.terningkastAxxP.topar;
                        final int n3 = a;
                        ++topar3[n3];
                    }
                    if (this.terningkastAxxP.terning4 == a) {
                        final int[] topar4 = this.terningkastAxxP.topar;
                        final int n4 = a;
                        ++topar4[n4];
                    }
                    if (this.terningkastAxxP.terning5 == a) {
                        final int[] topar5 = this.terningkastAxxP.topar;
                        final int n5 = a;
                        ++topar5[n5];
                    }
                    if (this.terningkastAxxP.terning6 == a) {
                        final int[] topar6 = this.terningkastAxxP.topar;
                        final int n6 = a;
                        ++topar6[n6];
                    }
                }
                int w = 1;
                for (int y = 1; y < 7; ++y) {
                    if (this.terningkastAxxP.topar[y] >= 2) {
                        this.toparH = 2 * y;
                        w = y;
                    }
                }
                for (int y2 = 1; y2 < w; ++y2) {
                    if (this.terningkastAxxP.topar[y2] >= 2) {
                        this.toparL = 2 * y2;
                    }
                }
                if (this.toparH > 0 && this.toparL > 0) {
                    this.topar = this.toparH + this.toparL;
                }
                else {
                    this.topar = 0;
                }
                if (this.topar >= 1) {
                    this.textField14.setText(String.valueOf("").concat(String.valueOf(this.topar)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA2 += this.topar;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField14.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button9_mousePressed(final MouseEvent e) {
        this.trepar = 0;
        if (this.kastet) {
            ++this.tellerTrepar;
            if (this.tellerTrepar <= 1) {
                ++this.regist;
                for (int a = 1; a < 7; ++a) {
                    if (this.terningkastAxxP.terning1 == a) {
                        final int[] trepar = this.terningkastAxxP.trepar;
                        final int n = a;
                        ++trepar[n];
                    }
                    if (this.terningkastAxxP.terning2 == a) {
                        final int[] trepar2 = this.terningkastAxxP.trepar;
                        final int n2 = a;
                        ++trepar2[n2];
                    }
                    if (this.terningkastAxxP.terning3 == a) {
                        final int[] trepar3 = this.terningkastAxxP.trepar;
                        final int n3 = a;
                        ++trepar3[n3];
                    }
                    if (this.terningkastAxxP.terning4 == a) {
                        final int[] trepar4 = this.terningkastAxxP.trepar;
                        final int n4 = a;
                        ++trepar4[n4];
                    }
                    if (this.terningkastAxxP.terning5 == a) {
                        final int[] trepar5 = this.terningkastAxxP.trepar;
                        final int n5 = a;
                        ++trepar5[n5];
                    }
                    if (this.terningkastAxxP.terning6 == a) {
                        final int[] trepar6 = this.terningkastAxxP.trepar;
                        final int n6 = a;
                        ++trepar6[n6];
                    }
                }
                int w = 1;
                int v = 1;
                for (int y = 1; y < 7; ++y) {
                    if (this.terningkastAxxP.trepar[y] >= 2) {
                        this.treparH = 2 * y;
                        w = y;
                    }
                }
                for (int y2 = 1; y2 < w; ++y2) {
                    if (this.terningkastAxxP.trepar[y2] >= 2) {
                        this.treparM = 2 * y2;
                        v = y2;
                    }
                }
                for (int y3 = 1; y3 < v; ++y3) {
                    if (this.terningkastAxxP.trepar[y3] >= 2) {
                        this.treparL = 2 * y3;
                    }
                }
                if (this.treparH > 0 && this.treparM > 0 && this.treparL > 0) {
                    this.trepar = this.treparH + this.treparM + this.treparL;
                }
                else {
                    this.trepar = 0;
                }
                if (this.trepar >= 1) {
                    this.textField15.setText(String.valueOf("").concat(String.valueOf(this.trepar)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA2 += this.trepar;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField15.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button10_mousePressed(final MouseEvent e) {
        this.trelike = 0;
        if (this.kastet) {
            ++this.tellerTrelike;
            if (this.tellerTrelike <= 1) {
                ++this.regist;
                for (int a = 1; a < 7; ++a) {
                    if (this.terningkastAxxP.terning1 == a) {
                        final int[] trelike = this.terningkastAxxP.trelike;
                        final int n = a;
                        ++trelike[n];
                    }
                    if (this.terningkastAxxP.terning2 == a) {
                        final int[] trelike2 = this.terningkastAxxP.trelike;
                        final int n2 = a;
                        ++trelike2[n2];
                    }
                    if (this.terningkastAxxP.terning3 == a) {
                        final int[] trelike3 = this.terningkastAxxP.trelike;
                        final int n3 = a;
                        ++trelike3[n3];
                    }
                    if (this.terningkastAxxP.terning4 == a) {
                        final int[] trelike4 = this.terningkastAxxP.trelike;
                        final int n4 = a;
                        ++trelike4[n4];
                    }
                    if (this.terningkastAxxP.terning5 == a) {
                        final int[] trelike5 = this.terningkastAxxP.trelike;
                        final int n5 = a;
                        ++trelike5[n5];
                    }
                    if (this.terningkastAxxP.terning6 == a) {
                        final int[] trelike6 = this.terningkastAxxP.trelike;
                        final int n6 = a;
                        ++trelike6[n6];
                    }
                }
                for (int y = 1; y < 7; ++y) {
                    if (this.terningkastAxxP.trelike[y] >= 3) {
                        this.trelike = 3 * y;
                    }
                }
                if (this.trelike > 0) {
                    this.textField16.setText(String.valueOf("").concat(String.valueOf(this.trelike)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA2 += this.trelike;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField16.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button11_mousePressed(final MouseEvent e) {
        this.firelike = 0;
        if (this.kastet) {
            ++this.tellerFirelike;
            if (this.tellerFirelike <= 1) {
                ++this.regist;
                for (int a = 1; a < 7; ++a) {
                    if (this.terningkastAxxP.terning1 == a) {
                        final int[] firelike = this.terningkastAxxP.firelike;
                        final int n = a;
                        ++firelike[n];
                    }
                    if (this.terningkastAxxP.terning2 == a) {
                        final int[] firelike2 = this.terningkastAxxP.firelike;
                        final int n2 = a;
                        ++firelike2[n2];
                    }
                    if (this.terningkastAxxP.terning3 == a) {
                        final int[] firelike3 = this.terningkastAxxP.firelike;
                        final int n3 = a;
                        ++firelike3[n3];
                    }
                    if (this.terningkastAxxP.terning4 == a) {
                        final int[] firelike4 = this.terningkastAxxP.firelike;
                        final int n4 = a;
                        ++firelike4[n4];
                    }
                    if (this.terningkastAxxP.terning5 == a) {
                        final int[] firelike5 = this.terningkastAxxP.firelike;
                        final int n5 = a;
                        ++firelike5[n5];
                    }
                    if (this.terningkastAxxP.terning6 == a) {
                        final int[] firelike6 = this.terningkastAxxP.firelike;
                        final int n6 = a;
                        ++firelike6[n6];
                    }
                }
                for (int y = 1; y < 7; ++y) {
                    if (this.terningkastAxxP.firelike[y] >= 4) {
                        this.firelike = 4 * y;
                    }
                }
                if (this.firelike > 0) {
                    this.textField17.setText(String.valueOf("").concat(String.valueOf(this.firelike)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA2 += this.firelike;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField17.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button12_mousePressed(final MouseEvent e) {
        this.femlike = 0;
        if (this.kastet) {
            ++this.tellerFemlike;
            if (this.tellerFemlike <= 1) {
                ++this.regist;
                for (int a = 1; a < 7; ++a) {
                    if (this.terningkastAxxP.terning1 == a) {
                        final int[] femlike = this.terningkastAxxP.femlike;
                        final int n = a;
                        ++femlike[n];
                    }
                    if (this.terningkastAxxP.terning2 == a) {
                        final int[] femlike2 = this.terningkastAxxP.femlike;
                        final int n2 = a;
                        ++femlike2[n2];
                    }
                    if (this.terningkastAxxP.terning3 == a) {
                        final int[] femlike3 = this.terningkastAxxP.femlike;
                        final int n3 = a;
                        ++femlike3[n3];
                    }
                    if (this.terningkastAxxP.terning4 == a) {
                        final int[] femlike4 = this.terningkastAxxP.femlike;
                        final int n4 = a;
                        ++femlike4[n4];
                    }
                    if (this.terningkastAxxP.terning5 == a) {
                        final int[] femlike5 = this.terningkastAxxP.femlike;
                        final int n5 = a;
                        ++femlike5[n5];
                    }
                    if (this.terningkastAxxP.terning6 == a) {
                        final int[] femlike6 = this.terningkastAxxP.femlike;
                        final int n6 = a;
                        ++femlike6[n6];
                    }
                }
                for (int y = 1; y < 7; ++y) {
                    if (this.terningkastAxxP.femlike[y] >= 5) {
                        this.femlike = 5 * y;
                    }
                }
                if (this.femlike > 0) {
                    this.textField18.setText(String.valueOf("").concat(String.valueOf(this.femlike)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA2 += this.femlike;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField18.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button13_mousePressed(final MouseEvent e) {
        this.litens = 0;
        if (this.kastet) {
            ++this.tellerLitens;
            if (this.tellerLitens <= 1) {
                ++this.regist;
                for (int a = 1; a < 7; ++a) {
                    if (this.terningkastAxxP.terning1 == a && this.terningkastAxxP.terning1 != 6) {
                        final int[] litens = this.terningkastAxxP.litens;
                        final int n = a;
                        ++litens[n];
                    }
                    if (this.terningkastAxxP.terning2 == a && this.terningkastAxxP.terning2 != 6) {
                        final int[] litens2 = this.terningkastAxxP.litens;
                        final int n2 = a;
                        ++litens2[n2];
                    }
                    if (this.terningkastAxxP.terning3 == a && this.terningkastAxxP.terning3 != 6) {
                        final int[] litens3 = this.terningkastAxxP.litens;
                        final int n3 = a;
                        ++litens3[n3];
                    }
                    if (this.terningkastAxxP.terning4 == a && this.terningkastAxxP.terning4 != 6) {
                        final int[] litens4 = this.terningkastAxxP.litens;
                        final int n4 = a;
                        ++litens4[n4];
                    }
                    if (this.terningkastAxxP.terning5 == a && this.terningkastAxxP.terning5 != 6) {
                        final int[] litens5 = this.terningkastAxxP.litens;
                        final int n5 = a;
                        ++litens5[n5];
                    }
                    if (this.terningkastAxxP.terning6 == a && this.terningkastAxxP.terning6 != 6) {
                        final int[] litens6 = this.terningkastAxxP.litens;
                        final int n6 = a;
                        ++litens6[n6];
                    }
                }
                for (int y = 1; y < 7; ++y) {
                    if (this.terningkastAxxP.litens[y] <= 2) {
                        if (this.terningkastAxxP.litens[y] == 2) {
                            this.litens += (this.terningkastAxxP.litens[y] - 1) * y;
                        }
                        else {
                            this.litens += this.terningkastAxxP.litens[y] * y;
                        }
                    }
                }
                if (this.litens == 15) {
                    this.textField19.setText(String.valueOf("").concat(String.valueOf(this.litens)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA2 += this.litens;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField19.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button14_mousePressed(final MouseEvent e) {
        this.stors = 0;
        if (this.kastet) {
            ++this.tellerStors;
            if (this.tellerStors <= 1) {
                ++this.regist;
                for (int a = 1; a < 7; ++a) {
                    if (this.terningkastAxxP.terning1 == a && this.terningkastAxxP.terning1 != 1) {
                        final int[] stors = this.terningkastAxxP.stors;
                        final int n = a;
                        ++stors[n];
                    }
                    if (this.terningkastAxxP.terning2 == a && this.terningkastAxxP.terning2 != 1) {
                        final int[] stors2 = this.terningkastAxxP.stors;
                        final int n2 = a;
                        ++stors2[n2];
                    }
                    if (this.terningkastAxxP.terning3 == a && this.terningkastAxxP.terning3 != 1) {
                        final int[] stors3 = this.terningkastAxxP.stors;
                        final int n3 = a;
                        ++stors3[n3];
                    }
                    if (this.terningkastAxxP.terning4 == a && this.terningkastAxxP.terning4 != 1) {
                        final int[] stors4 = this.terningkastAxxP.stors;
                        final int n4 = a;
                        ++stors4[n4];
                    }
                    if (this.terningkastAxxP.terning5 == a && this.terningkastAxxP.terning5 != 1) {
                        final int[] stors5 = this.terningkastAxxP.stors;
                        final int n5 = a;
                        ++stors5[n5];
                    }
                    if (this.terningkastAxxP.terning6 == a && this.terningkastAxxP.terning6 != 1) {
                        final int[] stors6 = this.terningkastAxxP.stors;
                        final int n6 = a;
                        ++stors6[n6];
                    }
                }
                for (int y = 1; y < 7; ++y) {
                    if (this.terningkastAxxP.stors[y] <= 2) {
                        if (this.terningkastAxxP.stors[y] == 2) {
                            this.stors += (this.terningkastAxxP.stors[y] - 1) * y;
                        }
                        else {
                            this.stors += this.terningkastAxxP.stors[y] * y;
                        }
                    }
                }
                if (this.stors == 20) {
                    this.textField20.setText(String.valueOf("").concat(String.valueOf(this.stors)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA2 += this.stors;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField20.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button15_mousePressed(final MouseEvent e) {
        this.fulls = 0;
        if (this.kastet) {
            ++this.tellerFulls;
            if (this.tellerFulls <= 1) {
                ++this.regist;
                for (int a = 1; a < 7; ++a) {
                    if (this.terningkastAxxP.terning1 == a) {
                        final int[] fulls = this.terningkastAxxP.fulls;
                        final int n = a;
                        ++fulls[n];
                    }
                    if (this.terningkastAxxP.terning2 == a) {
                        final int[] fulls2 = this.terningkastAxxP.fulls;
                        final int n2 = a;
                        ++fulls2[n2];
                    }
                    if (this.terningkastAxxP.terning3 == a) {
                        final int[] fulls3 = this.terningkastAxxP.fulls;
                        final int n3 = a;
                        ++fulls3[n3];
                    }
                    if (this.terningkastAxxP.terning4 == a) {
                        final int[] fulls4 = this.terningkastAxxP.fulls;
                        final int n4 = a;
                        ++fulls4[n4];
                    }
                    if (this.terningkastAxxP.terning5 == a) {
                        final int[] fulls5 = this.terningkastAxxP.fulls;
                        final int n5 = a;
                        ++fulls5[n5];
                    }
                    if (this.terningkastAxxP.terning6 == a) {
                        final int[] fulls6 = this.terningkastAxxP.fulls;
                        final int n6 = a;
                        ++fulls6[n6];
                    }
                }
                for (int y = 1; y < 7; ++y) {
                    if (this.terningkastAxxP.fulls[y] <= 2) {
                        if (this.terningkastAxxP.fulls[y] == 2) {
                            this.fulls += (this.terningkastAxxP.fulls[y] - 1) * y;
                        }
                        else {
                            this.fulls += this.terningkastAxxP.fulls[y] * y;
                        }
                    }
                }
                if (this.fulls == 21) {
                    this.textField21.setText(String.valueOf("").concat(String.valueOf(this.fulls)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA2 += this.fulls;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField21.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button16_mousePressed(final MouseEvent e) {
        this.totre = 0;
        if (this.kastet) {
            ++this.tellerTotre;
            if (this.tellerTotre <= 1) {
                ++this.regist;
                for (int a = 1; a < 7; ++a) {
                    if (this.terningkastAxxP.terning1 == a) {
                        final int[] totre = this.terningkastAxxP.totre;
                        final int n = a;
                        ++totre[n];
                    }
                    if (this.terningkastAxxP.terning2 == a) {
                        final int[] totre2 = this.terningkastAxxP.totre;
                        final int n2 = a;
                        ++totre2[n2];
                    }
                    if (this.terningkastAxxP.terning3 == a) {
                        final int[] totre3 = this.terningkastAxxP.totre;
                        final int n3 = a;
                        ++totre3[n3];
                    }
                    if (this.terningkastAxxP.terning4 == a) {
                        final int[] totre4 = this.terningkastAxxP.totre;
                        final int n4 = a;
                        ++totre4[n4];
                    }
                    if (this.terningkastAxxP.terning5 == a) {
                        final int[] totre5 = this.terningkastAxxP.totre;
                        final int n5 = a;
                        ++totre5[n5];
                    }
                    if (this.terningkastAxxP.terning6 == a) {
                        final int[] totre6 = this.terningkastAxxP.totre;
                        final int n6 = a;
                        ++totre6[n6];
                    }
                }
                int w = 1;
                for (int y = 1; y < 7; ++y) {
                    if (this.terningkastAxxP.totre[y] >= 3) {
                        this.totreH = 3 * y;
                        w = y;
                    }
                }
                if (this.totreL < 18) {
                    w = 7;
                }
                for (int y2 = 1; y2 < w; ++y2) {
                    if (this.terningkastAxxP.totre[y2] >= 2 && this.totreH / 3 != y2) {
                        this.totreL = 2 * y2;
                    }
                }
                if (this.totreH > 0 && this.totreL > 0) {
                    this.totre = this.totreH + this.totreL;
                }
                else {
                    this.totre = 0;
                }
                if (this.totre >= 1) {
                    this.textField22.setText(String.valueOf("").concat(String.valueOf(this.totre)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA2 += this.totre;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField22.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button17_mousePressed(final MouseEvent e) {
        this.tretre = 0;
        if (this.kastet) {
            ++this.tellerTretre;
            if (this.tellerTretre <= 1) {
                ++this.regist;
                for (int a = 1; a < 7; ++a) {
                    if (this.terningkastAxxP.terning1 == a) {
                        final int[] tretre = this.terningkastAxxP.tretre;
                        final int n = a;
                        ++tretre[n];
                    }
                    if (this.terningkastAxxP.terning2 == a) {
                        final int[] tretre2 = this.terningkastAxxP.tretre;
                        final int n2 = a;
                        ++tretre2[n2];
                    }
                    if (this.terningkastAxxP.terning3 == a) {
                        final int[] tretre3 = this.terningkastAxxP.tretre;
                        final int n3 = a;
                        ++tretre3[n3];
                    }
                    if (this.terningkastAxxP.terning4 == a) {
                        final int[] tretre4 = this.terningkastAxxP.tretre;
                        final int n4 = a;
                        ++tretre4[n4];
                    }
                    if (this.terningkastAxxP.terning5 == a) {
                        final int[] tretre5 = this.terningkastAxxP.tretre;
                        final int n5 = a;
                        ++tretre5[n5];
                    }
                    if (this.terningkastAxxP.terning6 == a) {
                        final int[] tretre6 = this.terningkastAxxP.tretre;
                        final int n6 = a;
                        ++tretre6[n6];
                    }
                }
                int w = 1;
                for (int y = 1; y < 7; ++y) {
                    if (this.terningkastAxxP.tretre[y] >= 3) {
                        this.tretreH = 3 * y;
                        w = y;
                    }
                }
                for (int y2 = 1; y2 < w; ++y2) {
                    if (this.terningkastAxxP.tretre[y2] >= 3) {
                        this.tretreL = 3 * y2;
                    }
                }
                if (this.tretreH > 0 && this.tretreL > 0) {
                    this.tretre = this.tretreH + this.tretreL;
                }
                else {
                    this.tretre = 0;
                }
                if (this.tretre >= 1) {
                    this.textField23.setText(String.valueOf("").concat(String.valueOf(this.tretre)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA2 += this.tretre;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField23.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button18_mousePressed(final MouseEvent e) {
        this.fireto = 0;
        if (this.kastet) {
            ++this.tellerFireto;
            if (this.tellerFireto <= 1) {
                ++this.regist;
                for (int a = 1; a < 7; ++a) {
                    if (this.terningkastAxxP.terning1 == a) {
                        final int[] fireto = this.terningkastAxxP.fireto;
                        final int n = a;
                        ++fireto[n];
                    }
                    if (this.terningkastAxxP.terning2 == a) {
                        final int[] fireto2 = this.terningkastAxxP.fireto;
                        final int n2 = a;
                        ++fireto2[n2];
                    }
                    if (this.terningkastAxxP.terning3 == a) {
                        final int[] fireto3 = this.terningkastAxxP.fireto;
                        final int n3 = a;
                        ++fireto3[n3];
                    }
                    if (this.terningkastAxxP.terning4 == a) {
                        final int[] fireto4 = this.terningkastAxxP.fireto;
                        final int n4 = a;
                        ++fireto4[n4];
                    }
                    if (this.terningkastAxxP.terning5 == a) {
                        final int[] fireto5 = this.terningkastAxxP.fireto;
                        final int n5 = a;
                        ++fireto5[n5];
                    }
                    if (this.terningkastAxxP.terning6 == a) {
                        final int[] fireto6 = this.terningkastAxxP.fireto;
                        final int n6 = a;
                        ++fireto6[n6];
                    }
                }
                int w = 1;
                for (int y = 1; y < 7; ++y) {
                    if (this.terningkastAxxP.fireto[y] == 4) {
                        this.firetoH = 4 * y;
                        w = y;
                    }
                }
                if (this.firetoL < 24) {
                    w = 7;
                }
                for (int y2 = 1; y2 < w; ++y2) {
                    if (this.terningkastAxxP.fireto[y2] == 2 && this.firetoH / 4 != y2) {
                        this.firetoL = 2 * y2;
                    }
                }
                if (this.firetoH > 0 && this.firetoL > 0) {
                    this.fireto = this.firetoH + this.firetoL;
                }
                else {
                    this.fireto = 0;
                }
                if (this.fireto >= 1) {
                    this.textField24.setText(String.valueOf("").concat(String.valueOf(this.fireto)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA2 += this.fireto;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField24.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button19_mousePressed(final MouseEvent e) {
        this.sjanse = 0;
        if (this.kastet) {
            ++this.tellerSjanse;
            if (this.tellerSjanse <= 1) {
                ++this.regist;
                for (int a = 1; a < 7; ++a) {
                    if (this.terningkastAxxP.terning1 == a) {
                        final int[] sjanse = this.terningkastAxxP.sjanse;
                        final int n = a;
                        ++sjanse[n];
                    }
                    if (this.terningkastAxxP.terning2 == a) {
                        final int[] sjanse2 = this.terningkastAxxP.sjanse;
                        final int n2 = a;
                        ++sjanse2[n2];
                    }
                    if (this.terningkastAxxP.terning3 == a) {
                        final int[] sjanse3 = this.terningkastAxxP.sjanse;
                        final int n3 = a;
                        ++sjanse3[n3];
                    }
                    if (this.terningkastAxxP.terning4 == a) {
                        final int[] sjanse4 = this.terningkastAxxP.sjanse;
                        final int n4 = a;
                        ++sjanse4[n4];
                    }
                    if (this.terningkastAxxP.terning5 == a) {
                        final int[] sjanse5 = this.terningkastAxxP.sjanse;
                        final int n5 = a;
                        ++sjanse5[n5];
                    }
                    if (this.terningkastAxxP.terning6 == a) {
                        final int[] sjanse6 = this.terningkastAxxP.sjanse;
                        final int n6 = a;
                        ++sjanse6[n6];
                    }
                }
                for (int y = 1; y < 7; ++y) {
                    this.sjanse += this.terningkastAxxP.sjanse[y] * y;
                }
                this.textField25.setText(String.valueOf("").concat(String.valueOf(this.sjanse)));
                final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                terningkastAxxP.sumA2 += this.sjanse;
                if (this.terningkastAxxP.sumA1 >= 84) {
                    this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                }
                else {
                    this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void button20_mousePressed(final MouseEvent e) {
        this.yatzy = 0;
        if (this.kastet) {
            ++this.tellerYatzy;
            if (this.tellerYatzy <= 1) {
                ++this.regist;
                for (int a = 1; a < 7; ++a) {
                    if (this.terningkastAxxP.terning1 == a) {
                        final int[] yatzy = this.terningkastAxxP.yatzy;
                        final int n = a;
                        ++yatzy[n];
                    }
                    if (this.terningkastAxxP.terning2 == a) {
                        final int[] yatzy2 = this.terningkastAxxP.yatzy;
                        final int n2 = a;
                        ++yatzy2[n2];
                    }
                    if (this.terningkastAxxP.terning3 == a) {
                        final int[] yatzy3 = this.terningkastAxxP.yatzy;
                        final int n3 = a;
                        ++yatzy3[n3];
                    }
                    if (this.terningkastAxxP.terning4 == a) {
                        final int[] yatzy4 = this.terningkastAxxP.yatzy;
                        final int n4 = a;
                        ++yatzy4[n4];
                    }
                    if (this.terningkastAxxP.terning5 == a) {
                        final int[] yatzy5 = this.terningkastAxxP.yatzy;
                        final int n5 = a;
                        ++yatzy5[n5];
                    }
                    if (this.terningkastAxxP.terning6 == a) {
                        final int[] yatzy6 = this.terningkastAxxP.yatzy;
                        final int n6 = a;
                        ++yatzy6[n6];
                    }
                }
                for (int y = 1; y < 7; ++y) {
                    if (this.terningkastAxxP.yatzy[y] == 6) {
                        this.yatzy = 100;
                    }
                }
                if (this.yatzy > 0) {
                    this.textField26.setText(String.valueOf("").concat(String.valueOf(this.yatzy)));
                    final TerningAxxP terningkastAxxP = this.terningkastAxxP;
                    terningkastAxxP.sumA2 += this.yatzy;
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2 + 100)));
                    }
                    else {
                        this.textField27.setText(String.valueOf("").concat(String.valueOf(this.terningkastAxxP.sumA2)));
                    }
                }
                else {
                    this.textField26.setText("XXXX");
                }
                if (this.regist == 20) {
                    if (this.terningkastAxxP.sumA1 >= 84) {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 100 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                    else {
                        if (this.checkboxkontroll % 2 == 0) {
                            this.kode = 989;
                        }
                        else {
                            this.kode = 988;
                        }
                        this.kode2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("-My2E-").concat(String.valueOf(this.idag.get(5) * 7))).concat(String.valueOf("-"))).concat(String.valueOf(this.idag.get(2) * 8))).concat(String.valueOf("-"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.idag.get(1) - 987).concat(String.valueOf("-"))).concat(String.valueOf("88"))).concat(String.valueOf(this.klokke.getMinutes() + 20))).concat(String.valueOf("88"))));
                        this.textField2.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Kode: ").concat(String.valueOf(this.kode))).concat(String.valueOf(this.kode2))).concat(String.valueOf(""))).concat(String.valueOf((this.terningkastAxxP.sumA2 + 13) * 2))).concat(String.valueOf("     ")));
                    }
                }
                if (this.terningkastAxxP.kasteteller == 1) {
                    final TerningAxxP terningkastAxxP2 = this.terningkastAxxP;
                    terningkastAxxP2.ubenyttet += this.terningkastAxxP.ubenytt2;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                else if (this.terningkastAxxP.kasteteller == 2) {
                    final TerningAxxP terningkastAxxP3 = this.terningkastAxxP;
                    terningkastAxxP3.ubenyttet += this.terningkastAxxP.ubenytt1;
                    this.terningkastAxxP.count = this.terningkastAxxP.ubenyttet;
                    if (this.checkboxkontroll % 2 != 0) {
                        this.label6.setText(String.valueOf(this.Loppsparte).concat(String.valueOf(this.terningkastAxxP.ubenyttet)));
                    }
                    else {
                        this.label6.setText("  Oppsparte kast: 0");
                    }
                }
                this.label7.setText(this.LKastteringer);
                this.terningkastAxxP.nullstill();
                this.kastet = false;
                this.repaint();
            }
        }
    }
    
    void checkbox1_itemStateChanged(final ItemEvent e) {
        ++this.checkboxkontroll;
        if (this.checkboxkontroll % 2 == 0) {
            this.terningkastAxxP.ubenyttet = 0;
            this.terningkastAxxP.count = 0;
        }
    }
}
