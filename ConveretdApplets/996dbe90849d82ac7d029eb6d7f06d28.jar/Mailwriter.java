import java.awt.event.MouseMotionAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.ImageObserver;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mailwriter extends Applet implements Runnable
{
    Thread Faden;
    Image offscreenImage;
    Image offscreenImage2;
    Graphics osg;
    Graphics osg2;
    int AppletW;
    int AppletH;
    boolean busy;
    boolean ready;
    int mouseX;
    int mouseY;
    boolean mousePressed;
    int Zustand;
    String[] Buchstabe;
    String[] Daten;
    int nBuchstaben;
    double[] PunktX;
    double[] PunktY;
    int[] PunktXM;
    int[] PunktYM;
    char[] PunktTyp;
    boolean Drawing;
    int nPunkteMax;
    int nPunkte;
    String Text;
    String[] TextZeichen;
    short[] TextZeichenNummer;
    short[] TextZeichenBreak;
    short[] TextZeichenSize;
    char[] TextZeichenBrush;
    boolean[] TextZeichenItalic;
    short[] TextZeichenWidth;
    short[] TextZeichenXMin;
    Color[] TextZeichenFarbe;
    short TextZeichenSizeT;
    char TextZeichenBrushT;
    boolean TextZeichenItalicT;
    Color TextZeichenFarbeT;
    Color FarbePapier;
    Color FarbeTinte;
    int nTextZeichen;
    int nTextZeichenMax;
    Color FarbeLink;
    int delayLine;
    int delayStroke;
    int delayChar;
    int delaySpace;
    int nLines;
    int nLineCount;
    int dy;
    double scale;
    int Laufweite;
    int x00;
    int x0;
    int xR;
    int y0;
    SmiSchirm_c SS;
    
    public Mailwriter() {
        this.busy = true;
        this.ready = false;
        this.mouseX = 100;
        this.mouseY = 0;
        this.mousePressed = false;
        this.Zustand = 0;
        this.nBuchstaben = 132;
        this.Drawing = false;
        this.nPunkteMax = 1000;
        this.nPunkte = 0;
        this.TextZeichenSizeT = 10;
        this.TextZeichenBrushT = 'a';
        this.TextZeichenItalicT = false;
        this.nTextZeichen = 0;
        this.nTextZeichenMax = 4000;
        this.nLineCount = 0;
        this.dy = -276;
        this.scale = 0.3;
        this.Laufweite = 40;
    }
    
    public void init() {
        int nTextZeichen = -1;
        this.AppletW = this.size().width;
        this.AppletH = this.size().height;
        this.PunktX = new double[this.nPunkteMax];
        this.PunktY = new double[this.nPunkteMax];
        this.PunktXM = new int[this.nPunkteMax];
        this.PunktYM = new int[this.nPunkteMax];
        this.PunktTyp = new char[this.nPunkteMax];
        this.Buchstabe = new String[this.nBuchstaben];
        this.Daten = new String[this.nBuchstaben];
        this.Text = this.getParameter("Text");
        this.TextZeichen = new String[this.nTextZeichenMax];
        this.TextZeichenNummer = new short[this.nTextZeichenMax];
        this.TextZeichenBreak = new short[this.nTextZeichenMax];
        this.TextZeichenSize = new short[this.nTextZeichenMax];
        this.TextZeichenBrush = new char[this.nTextZeichenMax];
        this.TextZeichenItalic = new boolean[this.nTextZeichenMax];
        this.TextZeichenWidth = new short[this.nTextZeichenMax];
        this.TextZeichenXMin = new short[this.nTextZeichenMax];
        this.TextZeichenFarbe = new Color[this.nTextZeichenMax];
        this.Laufweite = 40;
        this.scale = 0.1;
        this.x00 = Integer.parseInt(this.getParameter("xL"));
        this.x0 = this.x00;
        this.xR = Integer.parseInt(this.getParameter("xR"));
        this.y0 = this.AppletH - Integer.parseInt(this.getParameter("yCursor"));
        this.delayLine = Integer.parseInt(this.getParameter("delayLine"));
        this.delayStroke = Integer.parseInt(this.getParameter("delayStroke"));
        this.delayChar = Integer.parseInt(this.getParameter("delayChar"));
        this.delaySpace = Integer.parseInt(this.getParameter("delaySpace"));
        this.nLines = Integer.parseInt(this.getParameter("nLines"));
        this.FarbeTinte = Color.decode("#" + this.getParameter("fgcolor"));
        this.TextZeichenFarbeT = Color.decode("#" + this.getParameter("fgcolor"));
        this.FarbePapier = Color.decode("#" + this.getParameter("bgcolor"));
        if (this.FarbePapier.getRed() / 255.0 * 0.31 + this.FarbePapier.getGreen() / 255.0 * 0.6 + this.FarbePapier.getBlue() / 255.0 * 0.11 < 0.5) {
            this.FarbeLink = Color.white;
        }
        else {
            this.FarbeLink = Color.black;
        }
        this.getData();
        this.x0 = this.x00;
        for (int i = 0; i < this.Text.length(); ++i) {
            boolean b = true;
            if (this.Text.charAt(i) == '<') {
                final int index = this.Text.substring(i).indexOf(62);
                final String substring = this.Text.substring(i, i + index + 1);
                if (substring.equals("<br>")) {
                    b = false;
                    final short[] textZeichenBreak = this.TextZeichenBreak;
                    final int n = this.nTextZeichen - 1;
                    textZeichenBreak[n] += (short)(this.TextZeichenSizeT * 3.5);
                    this.x0 = this.x00;
                }
                else if (substring.equals("<b>")) {
                    b = false;
                    this.TextZeichenBrushT = 'b';
                }
                else if (substring.equals("<color=red>")) {
                    b = false;
                    this.TextZeichenFarbeT = Color.red;
                }
                else if (substring.equals("<color=yellow>")) {
                    b = false;
                    this.TextZeichenFarbeT = Color.yellow;
                }
                else if (substring.equals("<color=orange>")) {
                    b = false;
                    this.TextZeichenFarbeT = Color.orange;
                }
                else if (substring.equals("<color=brown>")) {
                    b = false;
                    this.TextZeichenFarbeT = new Color(200, 120, 60);
                }
                else if (substring.equals("<color=black>")) {
                    b = false;
                    this.TextZeichenFarbeT = Color.black;
                }
                else if (substring.equals("<color=white>")) {
                    b = false;
                    this.TextZeichenFarbeT = Color.white;
                }
                else if (substring.equals("<color=grey>")) {
                    b = false;
                    this.TextZeichenFarbeT = Color.gray;
                }
                else if (substring.equals("<color=lightgrey>")) {
                    b = false;
                    this.TextZeichenFarbeT = new Color(200, 200, 200);
                }
                else if (substring.equals("<color=darkgrey>")) {
                    b = false;
                    this.TextZeichenFarbeT = new Color(70, 70, 70);
                }
                else if (substring.equals("<color=green>")) {
                    b = false;
                    this.TextZeichenFarbeT = Color.green;
                }
                else if (substring.equals("<color=blue>")) {
                    b = false;
                    this.TextZeichenFarbeT = Color.blue;
                }
                else if (substring.equals("<color=magenta>")) {
                    b = false;
                    this.TextZeichenFarbeT = Color.magenta;
                }
                else if (substring.equals("<color=cyan>")) {
                    b = false;
                    this.TextZeichenFarbeT = Color.cyan;
                }
                else if (substring.equals("<color=lightblue>")) {
                    b = false;
                    this.TextZeichenFarbeT = new Color(0, 130, 255);
                }
                else if (substring.equals("<color=darkgreen>")) {
                    b = false;
                    this.TextZeichenFarbeT = new Color(0, 130, 0);
                }
                else if (substring.equals("<bb>")) {
                    b = false;
                    this.TextZeichenBrushT = 'c';
                }
                else if (substring.equals("<c>")) {
                    b = false;
                    this.TextZeichenBrushT = 'd';
                }
                else if (substring.equals("<cc>")) {
                    b = false;
                    this.TextZeichenBrushT = 'e';
                }
                else if (substring.equals("<d>")) {
                    b = false;
                    this.TextZeichenBrushT = 'f';
                }
                else if (substring.equals("<dd>")) {
                    b = false;
                    this.TextZeichenBrushT = 'g';
                }
                else if (substring.equals("<n>")) {
                    b = false;
                    this.TextZeichenBrushT = 'a';
                }
                else if (substring.equals("<i>")) {
                    b = false;
                    this.TextZeichenItalicT = true;
                }
                else if (substring.equals("</i>")) {
                    b = false;
                    this.TextZeichenItalicT = false;
                }
                else if (substring.length() >= 4 && substring.substring(0, 4).equalsIgnoreCase("<br=")) {
                    b = false;
                    final short[] textZeichenBreak2 = this.TextZeichenBreak;
                    final int n2 = this.nTextZeichen - 1;
                    textZeichenBreak2[n2] += (short)Integer.parseInt(substring.substring(4, 6));
                    this.x0 = this.x00;
                }
                else if (substring.length() >= 8 && substring.substring(0, 8).equalsIgnoreCase("<color=#")) {
                    b = false;
                    this.TextZeichenFarbeT = Color.decode("#" + substring.substring(8, 14));
                }
                else if (substring.length() >= 6 && substring.substring(0, 6).equalsIgnoreCase("<size=")) {
                    b = false;
                    this.TextZeichenSizeT = (short)Integer.parseInt(substring.substring(6, 8));
                }
                else {
                    this.TextZeichen[this.nTextZeichen] = substring;
                }
                i += index;
            }
            else {
                this.TextZeichen[this.nTextZeichen] = "" + this.Text.charAt(i);
            }
            if (b) {
                this.TextZeichenNummer[this.nTextZeichen] = -1;
                for (int j = 0; j < this.nBuchstaben; ++j) {
                    if (("" + this.Buchstabe[j]).equals("" + this.TextZeichen[this.nTextZeichen])) {
                        this.TextZeichenNummer[this.nTextZeichen] = (short)j;
                        break;
                    }
                }
                if (this.TextZeichenNummer[this.nTextZeichen] != -1) {
                    char c = '\u03e8';
                    int n3 = -1000;
                    int k = 0;
                    while (k < this.Daten[this.TextZeichenNummer[this.nTextZeichen]].length() - 1) {
                        if (this.Daten[this.TextZeichenNummer[this.nTextZeichen]].charAt(k) == '-') {
                            ++k;
                        }
                        final char c2 = (char)((this.Daten[this.TextZeichenNummer[this.nTextZeichen]].charAt(k) - 'a') * '\u001a' + (this.Daten[this.TextZeichenNummer[this.nTextZeichen]].charAt(k + 1) - 'a'));
                        k += 4;
                        if (c2 < c) {
                            c = c2;
                        }
                        if (c2 > n3) {
                            n3 = c2;
                        }
                    }
                    this.TextZeichenWidth[this.nTextZeichen] = (short)(n3 - c);
                    double n4 = 1.2 - 0.015 * this.TextZeichenSizeT;
                    if (this.TextZeichenBrushT == 'b' || this.TextZeichenBrushT == 'd' || this.TextZeichenBrushT == 'f') {
                        n4 += 0.05;
                    }
                    if (this.TextZeichenBrushT == 'c' || this.TextZeichenBrushT == 'e' || this.TextZeichenBrushT == 'g') {
                        n4 += 0.1;
                    }
                    this.TextZeichenWidth[this.nTextZeichen] = (short)((this.TextZeichenWidth[this.nTextZeichen] + this.Laufweite) * this.scale * n4 * this.TextZeichenSizeT * 0.1);
                    this.TextZeichenXMin[this.nTextZeichen] = (short)c;
                }
                else {
                    this.TextZeichenWidth[this.nTextZeichen] = (short)(100.0 * this.scale * this.TextZeichenSizeT * 0.1);
                    nTextZeichen = this.nTextZeichen;
                }
                this.x0 += this.TextZeichenWidth[this.nTextZeichen];
                if (this.x0 > this.AppletW - this.xR) {
                    if (nTextZeichen != -1) {
                        this.TextZeichenBreak[nTextZeichen] = (short)(this.TextZeichenSizeT * 3.5);
                        this.x0 = this.x00;
                        for (int l = nTextZeichen + 1; l <= this.nTextZeichen; ++l) {
                            this.x0 += this.TextZeichenWidth[l];
                        }
                        nTextZeichen = -1;
                    }
                    else {
                        this.TextZeichenBreak[this.nTextZeichen] = (short)(this.TextZeichenSizeT * 3.5);
                        this.x0 = this.x00 + this.TextZeichenWidth[this.nTextZeichen];
                    }
                }
                else {
                    this.TextZeichenBreak[this.nTextZeichen] = 0;
                }
                this.TextZeichenSize[this.nTextZeichen] = this.TextZeichenSizeT;
                this.TextZeichenBrush[this.nTextZeichen] = this.TextZeichenBrushT;
                this.TextZeichenItalic[this.nTextZeichen] = this.TextZeichenItalicT;
                this.TextZeichenFarbe[this.nTextZeichen] = this.TextZeichenFarbeT;
                ++this.nTextZeichen;
            }
        }
        (this.SS = new SmiSchirm_c(this.AppletW, this.AppletH)).setRealtime(true);
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionEventHandler());
    }
    
    public void start() {
        if (this.Faden == null) {
            (this.Faden = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.Faden != null) {
            this.Faden.stop();
            this.Faden = null;
            this.busy = false;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.Zustand == 1) {
            this.osg2.drawImage(this.offscreenImage, 0, 0, this);
            this.osg2.setColor(this.FarbeLink);
            if (this.mouseY < 20 && this.mouseX < 87) {
                this.osg2.drawString("www.eigelb.at", 6, 15);
            }
            this.osg2.drawString("www.eigelb.at", 5, 15);
            graphics.drawImage(this.offscreenImage2, 0, 0, this);
        }
    }
    
    public void run() {
        this.offscreenImage = this.createImage(this.AppletW, this.AppletH);
        this.osg = this.offscreenImage.getGraphics();
        this.offscreenImage2 = this.createImage(this.AppletW, this.AppletH);
        this.osg2 = this.offscreenImage2.getGraphics();
        this.x0 = this.x00;
        this.SS.fill(this.FarbePapier.getRed(), this.FarbePapier.getGreen(), this.FarbePapier.getBlue());
        this.Zustand = 1;
        for (int i = 0; i < this.nTextZeichen; ++i) {
            if (this.TextZeichenNummer[i] == -1) {
                this.x0 += this.TextZeichenWidth[i];
                if (this.delaySpace > 0) {
                    try {
                        Thread.sleep(this.delaySpace);
                    }
                    catch (InterruptedException ex) {}
                }
            }
            else {
                this.nPunkte = 0;
                int j = 0;
                while (j < this.Daten[this.TextZeichenNummer[i]].length() - 1) {
                    if (this.Daten[this.TextZeichenNummer[i]].charAt(j) == '-') {
                        this.PunktTyp[this.nPunkte] = 'a';
                        ++j;
                        if (this.delayStroke > 0 && !this.mousePressed) {
                            try {
                                Thread.sleep(this.delayStroke);
                            }
                            catch (InterruptedException ex2) {}
                        }
                    }
                    else {
                        this.PunktTyp[this.nPunkte] = 'w';
                    }
                    this.PunktX[this.nPunkte] = (this.Daten[this.TextZeichenNummer[i]].charAt(j) - 'a') * '\u001a' + (this.Daten[this.TextZeichenNummer[i]].charAt(j + 1) - 'a') - this.TextZeichenXMin[i];
                    j += 2;
                    this.PunktY[this.nPunkte] = (this.Daten[this.TextZeichenNummer[i]].charAt(j) - 'a') * '\u001a' + (this.Daten[this.TextZeichenNummer[i]].charAt(j + 1) - 'a') + this.dy;
                    j += 2;
                    if (this.TextZeichenItalic[i]) {
                        final double[] punktX = this.PunktX;
                        final int nPunkte = this.nPunkte;
                        punktX[nPunkte] -= (int)(this.PunktY[this.nPunkte] / 2.0 - 26.0);
                    }
                    this.PunktX[this.nPunkte] = this.TextZeichenSize[i] * 0.1 * this.scale * this.PunktX[this.nPunkte] + this.x0;
                    this.PunktY[this.nPunkte] = this.TextZeichenSize[i] * 0.1 * this.scale * this.PunktY[this.nPunkte] + this.y0;
                    if (this.PunktTyp[this.nPunkte] != 'a') {
                        double n = 0.05 * this.TextZeichenSize[i];
                        if (n > 0.6) {
                            n = 0.6;
                        }
                        this.SS.drawLine2(this.PunktX[this.nPunkte - 1], this.PunktY[this.nPunkte - 1], this.PunktX[this.nPunkte], this.PunktY[this.nPunkte], this.TextZeichenFarbe[i].getRed(), this.TextZeichenFarbe[i].getGreen(), this.TextZeichenFarbe[i].getBlue(), n, this.TextZeichenBrush[i]);
                        ++this.nLineCount;
                        if (this.nLineCount % this.nLines == 0) {
                            this.repaint();
                            if (this.delayLine > 0 && !this.mousePressed) {
                                try {
                                    Thread.sleep(this.delayLine);
                                }
                                catch (InterruptedException ex3) {}
                            }
                        }
                    }
                    ++this.nPunkte;
                }
                this.x0 += this.TextZeichenWidth[i];
            }
            if (this.delayChar > 0 && !this.mousePressed) {
                try {
                    Thread.sleep(this.delayChar);
                }
                catch (InterruptedException ex4) {}
            }
            if (this.TextZeichenBreak[i] > 0) {
                this.osg.setColor(this.FarbePapier);
                for (short n2 = 0; n2 < this.TextZeichenBreak[i]; ++n2) {
                    this.osg.copyArea(0, 0, this.AppletW, this.AppletH, 0, -1);
                    this.osg.fillRect(0, this.AppletH - 1, this.AppletW, 2);
                    if (!this.mousePressed) {
                        try {
                            Thread.sleep(20L);
                        }
                        catch (InterruptedException ex5) {}
                    }
                    this.repaint();
                }
                this.SS.moveUpBy(this.TextZeichenBreak[i]);
                this.x0 = this.x00;
            }
        }
        while (this.busy) {
            this.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex6) {}
        }
    }
    
    public void getData() {
        this.Buchstabe[0] = "A";
        this.Daten[0] = "-jafoiwfoiufuitfziogqifhqhviuhnjrhhknhdligxmigtmvgqnegonigmnrgknzghoi-iwfniwfjizfojbfvjfgljhgwjkhjjoihjujfjzkckfkukkllkomaksmokumukynflbnoldnulgnyljodlmoh-hdlehjldhnldhslchylbiglbinlcjblejxldkfldkjld";
        this.Buchstabe[1] = "B";
        this.Daten[1] = "-hiflhjfphkfuhkgkhkgvhkhihkhxhkimhkjahljmhmjwhokrholjholzhomlhomuhnmzhonfhonkhonphonwhpobhpof-hjfnhoflhufkhyfkijfmiofoitfqjefwjqgdkbgikjgqkohfkohvkmifkjiokcjdjvjkjnjpjejtivjvikjvigjvhyjvhujthqjrhvjqidjqijjpivjpjhjrjtjukfjykqkhkxkrldlalhljljltljmclgmqldmylangktnrkmnykfofjwoljkoojdooiwonioooihophyophtophpophloo";
        this.Buchstabe[2] = "C";
        this.Daten[2] = "-ljheljhalhgvlfgpldgllbghktfxkkfujzfujoftjeftiufxiegihugqhlgzgwhvgniogijhgfkbgglfgjlxgomlgvmzhdnmhlnwhyohikokixokjqoekeoakpnukynolknalpmqlqmklpmf";
        this.Buchstabe[3] = "D";
        this.Daten[3] = "-hjfohjfuhjfyhjgehjgwhihvhijjhjkshjlkhimbhhmnhgmxhgnehhnshgochfohhgol-hlfqhqfnhwfnihfoivfsjjfyjyggkmgrkwhhldhxliirlkjilkkhlikxlelnkymhkrmtkkndkbnljknujanyiqocihofiaoghsohhmoghhoehcodgyoc";
        this.Buchstabe[4] = "E";
        this.Daten[4] = "-hkfihkfmhsfmhyfmiqfkjlfikhffkvfelefgljfj-hmfshmfzhmgehmgmhohihphvhqijhrjjhskuhtlrhumihvmvhwnhhwnlhwnphwnthvnxhvobiaoaieoaiknzixnyjdnyjlnxjtnwkbnvkinulenvllnxlqny-hsjzhxjziejzijjziqjyjkkajxkckekdkjkdkrkckykclckd";
        this.Buchstabe[5] = "F";
        this.Daten[5] = "-hefohifnhmfohxfoiffniofmiyfmjifljsfkkcfjkyfklefllmfllqfo-hifnhjfuhjgahjgihjgrhjhdhjiqhjjphikohhljhimdhjmrhjnehjnnhjnuhioe-hnjohujphyjpiljoitjojbjnjqjlkejjknjikrjj";
        this.Buchstabe[6] = "G";
        this.Daten[6] = "-lpgtlmgpllgllhgglcgaktfskpfqklfpjkfriwfyisgciigjhvhbhohrhiikhejdhbkhhdlahglqhmmehrmpianfihnoipntixnxjkoajunykgnqkqnjkunflcmxlimolmmdlplrlslhltlb-jbkajgkdjkkdjrkckikakvkalakblfkcllkdlpkclskglskmlskqltlaltlnlumclumqlvnblwnjlwnplwnvlwnzlvod";
        this.Buchstabe[7] = "H";
        this.Daten[7] = "-hhfkhjfghjfchifghhfkhggbhghdhfimhejuhdkwhdlvhdmnhdnihdnphdnthdobhdof-hejnhkjmhsjmhzjliijkjjjikcjiktjhlgjelmjd-ltfjlvfqlvfzlvgklvgylvhplsjllrkllqlglslxlumrlunblvnklvnqlwnxlwoflxojlxon";
        this.Buchstabe[8] = "I";
        this.Daten[8] = "-icfsihfqinfpisfpjdfojpfojyfpkefqkjfpkpfn-jjfkjmfojmfsjnfxjngejngmjngxjmhjjlhwjkixjjjtjjkrjjltjjmgjjmrjjnbjjnkjjnvjjoajkof-ioofijohipoiiwohjaogjfofjrofjyogkgofkkoekoogksoe";
        this.Buchstabe[9] = "J";
        this.Daten[9] = "-kafjkcfpkcftkcfxkcgckcgjkcgpkcgxkbhfkbhvkbilkbjakajokakjjzkwjzlhjzlsjzmcjzmrjynbjynkjynsjwofjtoqjppbjnpijapqispsijptiapp";
        this.Buchstabe[10] = "K";
        this.Daten[10] = "-ibfmibfqibfwicgmidhnicidibjjiakzialwhzmqhzmzhznfhznmhznuhzochzohhzolhzoq-mjfmmdfumbfylxgeltgjkyhnknifkdixjsjkjkjxiykoiukvirlaimlgijlkigloidls-jvjkjyjpkcjukjkjkskylblllllvlumdmcmomimtmmmymqndmtnjmvnomynsnbnxndobngof-ngoenioi";
        this.Buchstabe[11] = "L";
        this.Daten[11] = "-iffniefviegbidgvidhiidhwidikidjaibkaialchzlzhymvhynihynthzobiaogiaokiaooibokihoiioohisohiwogjbogjfogjlogjqogjwogkiofkxoglhohlqohlvoflzoemdoe";
        this.Buchstabe[12] = "M";
        this.Daten[12] = "-hkfnhlfjhkfrhjfyhhgphghahfhlhehwhdihhbjdhajxgykrgvlpgtmdgtmogsmvgsncgrnkgqnpgqntgqnxgqob-hmfqhmfmhmfihofphqfuhsgbhugjhxgsichniiifilipiqjhiujviyknjakvjclcjdljjflwjhmbjimfjjmjjkmejmlwjnlqjoljjplcjrkujtkmjyjvkejeklifkphukshjkuhakwgtkwgnkyghkzgdlafxlbftldfplefklhfrlhfwligdllgulrialtillyjrmbkkmflemilvmkmbmnmkmpmpmqmumrnamrnemtnjmtnnmvnsmvnwmwoamwoe";
        this.Buchstabe[13] = "N";
        this.Daten[13] = "-hkfihkfnhkfshkfzhjgrhjhshjishjjnhjkuhhlohfmfhfmthfnahfnihfnqhenzheodheoihfon-hlfshnfohsfuhufzhxggibgnifgxikhhiphriyiojnjsjxkjkflbkmlrkwmilbmrlhnallnhlqnoltnulwoalxoemaoimboemboambnwmbnrmbnimanbmamtlzmklzmalzlqlzlhlykxlykelzjbmailmahxmahmlzgzlygrlxgklxgflxgblxfxlxfslxfo";
        this.Buchstabe[14] = "O";
        this.Daten[14] = "-jeofizogitodilnyihnvhwnjhmmthclzgwligrkogpjjgqitgsicgwhlhdgqhjgghrfyiafqilfkiwfcjlezjwezkgezkpfclbfqlgfwlpgklwgymbhrmfimmgjimfklmdldmbltlymglwmlltmulnnbldnqkxnyksodkoofkjohkeojjwomjqoljjokizojitogiooe";
        this.Buchstabe[15] = "P";
        this.Daten[15] = "-hkfnhmfshmfxhngdhngmhoijhnjjhmkhhmlehmlyhnmrhonbhonjhpnqhpoahooe-hlfnhqflhufkhyfkihfliufnjifrjyfwkmgelagtlegylihelohplqidlpislljklfjwkzkhkrkqkekzjuldjllfjdlgiwlhiqlhihlhialfhuldhplc";
        this.Buchstabe[16] = "Q";
        this.Daten[16] = "-jfofjbogivodipnyilnuignqhsmzhlmmhdlghckxhckfhdjnhfiuhiiehkhyhohkhtgxhygliggbimfwiufsjcfpjmfpjxfpkkfskrfxkzgclkgnlqhblvhrlzigmajjmakdlykvlwlmltlzlnmpljmyldngkynokqnxkkoekgofkcogjxoijrokjloljfoliyoliqoi-kmlnklljkplrkslxkwmelgmvlvnqmcobmhojmlop";
        this.Buchstabe[17] = "R";
        this.Daten[17] = "-hzfnhzfvhzgjhyhfhyichvjvhtkrhtlchsluhrmihrmohrmyhrnhhsnphsnyhsoehsoihsom-hzfniefliifkimfkivfkjhfmjufskjgbkvgjlegvlkhhlnhylmijljiwldjgksjvkkkdkakijlkmjdkniukoinkpiekphxkphrko-juknjykqkbkvkiljkllrkwmplcnblhnmllntlnnxlqodlrohlsol";
        this.Buchstabe[18] = "S";
        this.Daten[18] = "-ldgjlcgfkzfzkvfvkpfsklfrkcfpjtfnjofljkfljgfljcfmiyfoiufqiifyiagfhtgohpgzhnhqhnhzhrikhwitimjeizjkjojpkkjukyjyljkdlskklyksmclbmglomglymfmilzmxlsnklknwlboekmojjqokjgokjaojipofibnuhqnlhlnihcncgwmugtmggrlpgqlk";
        this.Buchstabe[19] = "T";
        this.Daten[19] = "-hhfnhmfohrfohxfoilfnjafnjpfnkpfnlcfplpfqmkfomvfnnffpnmfrntfs-kwfnkwfskwfwkwgbkwgokwhskviqktjlkskgkskxkrlskrmekrmlkrmtkqnekqnikqnmkqnrkqnvkqnz";
        this.Buchstabe[20] = "U";
        this.Daten[20] = "-hjfkhjfphjfuhkfzhkgnhkgwhkhfhkhwhkixhljmhlkdhkkzhklmhllxhnmhhqmohwmzibnfignlinnqiwnwjcnzjkoajyoakhoakonzkwnvlhnolnnhltmxlxmnmbmbmflgmhktmjkfmkjrmkjkmkiymkikmkhymkhhmkgwmkgjmkgfmjfymjftmjfmmjfhmjfc";
        this.Buchstabe[21] = "V";
        this.Daten[21] = "-hqfmhsfqhvfzhwgeiaguidhfijicirjhiwkbjakwjelojimljkmxjlnijonsjrnzjtoejuojjvonjxorjwomjwoijxodkbnmkfmxkkmgkplokukxldjslhjdlliolpiclthqlxhemcgomeggmgfymiftmjfpmkfl";
        this.Buchstabe[22] = "W";
        this.Daten[22] = "-gkflgmfsgnfzgnghgogrgqhcgvipgzjmhbkhhdlbhgmbhimohkmyhmnghonohpnvhpnzhqoehqojhsofhuoahwnuianlicneiemvigmninlwiyktjdjzjijhjnitjrihjuhzjxhnjzhikahokbhskbhykeiqkhjiklkbkpkskslakwlplamdlfmnljmylmnelonjlrntlsnylvodlxohlzommaoimbodmbnxmcnrmdnimgmhmjlpmlkwmpjvmtjgmzijndhwnehrnghgnkgmnmggnmgcnnfynoftnpfpnpfl";
        this.Buchstabe[23] = "X";
        this.Daten[23] = "-lyfhmcfelyfllwfpltfvlkgkkxhikiiijnjrjdkmitleijlvifmchzmohumyhqnhhknrhinvhfobhdoghbok-hifkhefihgfnhifrhkfwhtgiifhajaifjojakejrkukrldlhlmlxlvmimcmsminamonimqnnmrnrmtnvmwnz";
        this.Buchstabe[24] = "Y";
        this.Daten[24] = "-hmfmhkfihnfohqfthugbiggvirhpjbihjljfjrjsjwkbjzkgkbkkkdkfkekbkhjukljlkrizkxiklkhllphcltgulwgolzgimcgcmefwmffsmgfomifkmjfgmkfc-kbkgkekmkekrkflckglvkfmfkfmpkdnikcnzkcoikcotkcoxkcpbkeow";
        this.Buchstabe[25] = "Z";
        this.Daten[25] = "-gxflhdflhlfnhqfnidfoisfojgfnjufmkjfmktfmkzfmlefplifplnfoltfolxfolwfslrfzlngdligkldgskrhlkgifjvizjnjpjfkeiyktinlniglzibmihymohxmshumxhsnchonjhknphhnuhenyhcochaoghgohhnoghsofieocilocitobjaoajgoajuockbockhodkmockrockvobkzocldoclkoeltodlyofmcogmhoi";
        this.Buchstabe[26] = "a";
        this.Daten[26] = "-kflikhlekhkykgkpkekfkajtjrjhjmjcjcjaixizitiyioizidjjhpjwhfkigxkugtljgtmngznghinxhrokicoriwoojhojjoohjsocjxnejzms-khjakhjkkhjskgkbkfkmkekxkclukamlkanakcnmkfnvkjoekkoikmomkrorkvorlcomlmoe";
        this.Buchstabe[27] = "b";
        this.Daten[27] = "-idfniefjiefpiefwiegcifgxieiriejtiekuiembiemqidngicnticocidojidon-ielgielcigkvihkriikmikkhimjwipjmitjfiziyjniojyikkiimkqivkzjnlejylikjlkkxlklolimjlemukznektnlkjnukcnzjwodjpocjjoajdnyixnwirnuiinoiena";
        this.Buchstabe[28] = "c";
        this.Daten[28] = "-ktjvktjrksjnkqjhknjdkiizkeizjwiyjoiyjejaiqjhigjmhyjuhskghqknholbhmlrhlmghnmuhsniidnzimohivomjfonjwoikhobkqnukwnmkynilanc";
        this.Buchstabe[29] = "d";
        this.Daten[29] = "-klfikmfokmfukmgbkmgwkmikkmjjklkekkkzkklqkkmeklmvklnhklnqklnvklobklogkmocknnw-kkljkjlekikzkhktkgkmkgkikgjykfjqkcjkjwjcjrizjljaiyjdipjdihjfhwjkhtjohrjshnkdhjkphglbhelphfmkhjmwhrnghynqikobisoeizofjfofjoobjvnwkbnpkhnkkkngkomzkqmukrmd";
        this.Buchstabe[30] = "e";
        this.Daten[30] = "-ihlmidlniiloinloislojilokmlolblnlllmlnlglnlblnkplmkeljjulfjmlajgkujckjizkbiyjtizjkjeixjtiqkdikkmigkxielniembigmpilnbivnqjenyjqoakeoakpoalboalhnvlnnmlrneltna";
        this.Buchstabe[31] = "f";
        this.Daten[31] = "-kvfrkvfnkqfnklfokgfrjzfwjugejrgjjogpjmgwjgidjfiujejljdkojclgjclvjcmnjdmwjencjenhjfnmjfnrjfnvjgnz-idjeikjdipjdivjdjijdjvjfkgjgkqjgkvjh";
        this.Buchstabe[32] = "g";
        this.Daten[32] = "-kfkykhkskgkmkfkikdkdkcjzkajujwjmjnjgjgjdjajcirjdihjfhzjihvjjhqjphmjzhiknhdlhhcmahfmrhlnhhpnohynzikogiwojjiohjunvkimw-khjfkijmkijskikakhktkglskhnfkjnxkkonkkpfkkpukjqfkhqskeqzjxrejprgjkrhjerjiurmikrnhwrfhnqwhgqohbqehapnhaph";
        this.Buchstabe[33] = "h";
        this.Daten[33] = "-idfgidflidfpidfuidghiehdidiridjpidkmidlfibmeiamrianeianpianuhzochzogiaol-ibkoidkjifkdijjxiljtirjliyjejhiyjyiukjivktizlbjflijklnjtlpkplplhlolzlomolnnilnnslnnzlnoflnoj";
        this.Buchstabe[34] = "i";
        this.Daten[34] = "-jphqjlhqjjhkjlhfjmhbjrgujxgtkcgxkehekchmjwhsjohujkhr-jxjcjwjijwjmjwjsjxkhjwlmjsmjjrmzjrnjjtnrjtocjsohjsomjroq";
        this.Buchstabe[35] = "j";
        this.Daten[35] = "-jmhwjihujfhpjfhkjihejogzjugxkahbkehkkehtkbiajuicjqibjjhwjihs-jrjcjujijujojukejvkojulhjumcjuncjunrjuofjroxjnpijjpsizqaixqeisqn";
        this.Buchstabe[36] = "k";
        this.Daten[36] = "-htflhvfhhvfohvfvhtgphrhthqijhqixhpjyhokvhnlqhmmohknbhknkhknrhknwhkoahjog-kpivkkjckhjgkdjljsjwjnkcjckniskyihlihwluhslyhomdhjmhhfmj-iqlhirldivliiylnjbltjkmfjxmwkfnhklnqksoakwofkzoj";
        this.Buchstabe[37] = "l";
        this.Daten[37] = "-izftjafpjcftjdfyjdgfjegnjegyjfhujeirjbjzjakvizlpiymeiymliymvizneiznljbnsjcnxjdobjhodjlocjpoajunz";
        this.Buchstabe[38] = "m";
        this.Daten[38] = "-gsjbgvjggvjmgvjvgvkfgvkqgulngumkgvnlgwoagwojgwop-hakmhckihdkdhfjzhgjvhijrhnjkhtjeibizitiwjejajnjjjqjwjqkjjrlfjrlzjqmpjqndjqnpjrnx-jrkijtkejwjyjzjskfjmkljgkujakziyljiwlvjambjhmfjtmhkimillmhmgmhmymhnomkofmlojmlod";
        this.Buchstabe[39] = "n";
        this.Daten[39] = "-hkjbhliwhmjbhmjghnjwhnkhhnlphnmkhmmyhmnkhnnphonxhooc-hokdhqjyhsjuhwjnhzjjibjfihiziliwipiuiuisiziqjriokbipkhivknjhkrjxkwkzkxlukxmmkynbkynnkznskznxkzoc";
        this.Buchstabe[40] = "o";
        this.Daten[40] = "-ivocirodinobihnwidnsianohxnjhundhsmxhpmjholtholchrkkhvjzibjqijjjiujdjcizjkiyjrjakajgkijnkmjskukkkvkrkxlekwmaktmokpnbkinskcoajvofjpojjkoljeomixoj";
        this.Buchstabe[41] = "p";
        this.Daten[41] = "-hxiwhyjehyjmhyjvhykvhylzhxnchvophupkhuqahvqqhwrjhxrthysbhzsfhyry-ibksiakoiakiibkeidjxiijniqjfjjiyjqiwkdiwknjbkyjulckklgkzlfmalcmskyniktnwkmohkgokjxoljookjdoiinnyidnuhwnrhtnkhumy";
        this.Buchstabe[42] = "q";
        this.Daten[42] = "-kbjtkbjpkajljzjhjvjdjrjbjmjajejaiyjaiuizinjcihjhibjnhwjvhqkfhmkshjllhklzhnmmhuncianlihnqionviwnzjcnzjhnyjonvjwnqkgnjkinf-kjivkkjckkjhkkjokjjykjkjkjkvkjlikjlvkkmpkknjkkobkkoqkjpfkkqakkqeklqjklqpklqxklrcklrgkmrkknrokort";
        this.Buchstabe[43] = "r";
        this.Daten[43] = "-iqiyirjfirjlirjtirkcirkmislhismbisnbisnoirnxirocirohisol-iskviukqixkiizkejejujjjmjojhjsjcjxizkbix";
        this.Buchstabe[44] = "s";
        this.Daten[44] = "-ktjkkqjgkmjekhjbjxixjmiyjhiziyjeiujfinjkijjpigjxigkhihkripleixlmjilsjvlwkclyklmckrmhkvmpkxmxkynikxnokvntksnykmobkaofjrogjiogixofipnyignshznmhtnfhrmyhsmt";
        this.Buchstabe[45] = "t";
        this.Daten[45] = "-hojahsiyibiwiiiuiyitjmitjtisjyiskfis-jchbiyhaizhiizhniyicixiviwjoixkgiykwizlkizlyizmkixmuixnbizniiznnjdntjinwjnnyjtnyjynzkenxklnnkpng";
        this.Buchstabe[46] = "u";
        this.Daten[46] = "-htizhtjghtjmhsjthqkthpldhplmhpmehpmthrnehxnribnyihodipofizohjrofkcoaklnskpnl-kviskwizkwjhkwjokwjxkvkhkulvkumuktnkktnwkuodkyohlcoklhomlmollqoh";
        this.Buchstabe[47] = "v";
        this.Daten[47] = "-hpixhtjfhujlhwjvicksiomaitmriwngjanwjcoajfnsjgnnjinfjompjrmhjxlrkaljkdlckgkwkikqknkgkqjyksjskujlkwjhkyjblaix";
        this.Buchstabe[48] = "w";
        this.Daten[48] = "-gtiygujfgvjngxkhgzkvhclwhfmrhknrhlobhmohhnolhnophroihuodhxnwienfinmlislyixlljdlcjgkxjhktjjkojkkkjkkpjkktjllbjnlhjslyjvmfjzmmkdmtkfnbkinikmntkpobksoikuomkyokkzoglaoclcnslfnhljmtlmmflqlqltlelwkwlzkkmbkbmdjtmejpmfjkmhjemjja";
        this.Buchstabe[49] = "x";
        this.Daten[49] = "-lbizlfiwkzjckwjhksjoknjvkhkfjdlyivmlionaignmhwobhsoghtob-hxivhtivhuizhzjgicjlinkaizkujmlmjzmbklmoksnakynjldnolinslmnwlooa";
        this.Buchstabe[50] = "y";
        this.Daten[50] = "-htizhvivhwjbhyjhibjpiikiiwlojemgjkmujpnhjtnrjxnzjyodjzoi-ltizlsjdlqjjlnjtllkalgkplalgktlxkommkfnnkaoejvoujppojjqbjeqpizrciprtimrzhzsqhtsshpsq";
        this.Buchstabe[51] = "z";
        this.Daten[51] = "-hmjahqiyhwiyibizigjainjbjcjejsjfkjjhkvjilijhlkjllgjqlcjxkukikpkqjpmajempizmxirnjijnuignyidociaoghxokhtonhxooiboniookjfohjnogjvofkloekyoflkoeltodlzob";
        this.Buchstabe[52] = "0";
        this.Daten[52] = "-idoehzoghvohhroghnobhhnvhenrgwnigmmqgjlxglljgnldgxklhekahmjrhwjjihjeizizjjjbjsjhkajpkikekkkskjlfkhltkcmljymvjtnejnnnjinsiwnyioobigocibodhvoc";
        this.Buchstabe[53] = "1";
        this.Daten[53] = "-hpknhukmhykiigjziljuisjmjdizjgjhjfjpjeknjelajdlnjanmjanvjboajboejboijaom-hsoehoofhtofhxofijogiyoejgodjtobjyockhofkmoikqoj";
        this.Buchstabe[54] = "2";
        this.Daten[54] = "-hpkghokbhqjvhujphwjlidjeinixjdirjqiskeiykpjgkwjolajzkwkxkolpkfmfjtmrjnmwjbneipnmienthsobhmoehiohhmoihtohhxofikoeisodjaocjqobkgobkqodlfoglkojlooklooflmnyllnulhnolfnj";
        this.Buchstabe[55] = "3";
        this.Daten[55] = "-hhjuhhjphgjihgjehfizhdivhjishnishrishwisicitixiujoiukeiukpiwkvixkziwkvjcksjgkkjqkfjxjzkejdlfixlmiolyilmcigmhikmcirlzivlxjalwjglujtlvkglzkqmhkzmrljnllnnzlponlopallpklcqbkvqmklqwjzrcjfreisrbifqvhpqfhjpthgpl";
        this.Buchstabe[56] = "4";
        this.Daten[56] = "-kbjajwjijtjnjeknixkxirlhicmbhqmsgznsgunzgsodgpohguoigyohhdohhoogibofiqogixogjlogjzofkloekyoglhohlpohlwogmhoemmoemrod-kdivkeirkfiykfjekgjwkhkxkhlmkhnfkhoekhpakipqkipzkjqikjqpkjqtkjqx";
        this.Buchstabe[57] = "5";
        this.Daten[57] = "-htjbiajaifjbimjbiujbjdjbkkiyleixmaizmijb-icjlicjsibjyiakfhuljhpmghlmxhinnhfnzheofhfobhhnxhlnthunmijncjbmujumqkemqknmrlemvltnemdnqmnoimqoumqphmpptmhqjlyqulmrgkvrrkkrvjwrujkrriyroidrihsrfhkrahgquhgqp";
        this.Buchstabe[58] = "6";
        this.Daten[58] = "-lhfjldflkxfpkrfukmfxkaghjtgnjmgtimhsibihhsiyhljohdkphalghblzhhmvhmnjhtnuibociqoijaojjloijxoekknxkvnplinalommlslvlpkvlijzlbjokujhkmjekcjcjpjcjejeiojgidjjhujohnjyhkkdhfkohdkuhdkz";
        this.Buchstabe[59] = "7";
        this.Daten[59] = "-gxjbgzjghajnhbjthckehdki-gviwgvisgzishdithiithoiuhwivjpivkjivkziuloitmeiumkiumpivmvivnaivneivmzizmtjgmpjlmkjrlskulflpknmzkcnyjqpgjnpqjhqkizrliwrxiuseiusjitso";
        this.Buchstabe[60] = "8";
        this.Daten[60] = "-jbiyiwiwiritilinihiiibhzhxhohuhchwgpibgcimfpiwfkjnfijtfkkffskngnkohaklhnkihtkbiejriojeiwiqjgicjthoklhikthelchclkhbmchcmnhgmyhmnkhznwinobjdodjsofjzogkmogktockznuldnhlgmklglxlclkkykykrkikmjzkfjqjzjhjsjcjkjb";
        this.Buchstabe[61] = "9";
        this.Daten[61] = "-kplqkrlvkslzksmhkrmrkpmxknndkenqjtobjhokikorhyoqhoolgznygnmzgmmhgmlygnlogrkvgzkchijoidiyiuiujlivjzjbknjxkrkrkslaktlkktmdkrmukonmkgoqjvpljjqeisrhigryhwskhoss-hjsw";
        this.Buchstabe[62] = "@";
        this.Daten[62] = "-khjckgixkfiskbiljzigjvibjmhsjihrjchrithuhwirhtizhpjuhqkfhyliijlvjalyjllojwkwkejzkjivkliikliekjikkiirkgjhkgkekilbkrlxlbmblmlzlxlnmblgmjknmnjqmpismnhumihalugalifrktfmkbfmjsfnjbfrimfwhygchegvgshmgjiegeiygcjtgcldgfmbgomvhcnnhsobicoejaojjxohksoflmnzlunt";
        this.Buchstabe[63] = "#";
        this.Daten[63] = "-fpidfhidfmibfqiafwiagnhyhthxirhxjohvkzhqlrhqmdhtmihu-hmfmhlfqhkfxhjgghhgshfhjgyiygsklgmlzghnaghnsghobghof-kmfykofukqfqkpfukofzkjgukdicjwjsjplejkmjjhngjeogjdoqjcov-fklpfiltfolsftlrgalqgtlnhflmifljjdlgjxlgkmlhkulhkzlhlelhlklflqlflulf";
        this.Buchstabe[64] = "<quote>";
        this.Daten[64] = "-htfmhtfqhtfzhtgihthchthzhtig-jsfojtftjtggjtgyjrhojqibjqikjrio";
        this.Buchstabe[65] = ",";
        this.Daten[65] = "-junrjunwjtobjsohjoovjmpcjipljeprjbpv";
        this.Buchstabe[66] = ".";
        this.Daten[66] = "-iyobiuodiqnzionvipnpisnjixnfjcnfjhnkjjnqjinwjgoajbociwocirob";
        this.Buchstabe[67] = ";";
        this.Daten[67] = "-jmmsjimtjfmpjfmjjhmfjklyjplujvlujzmcjzmnjwmvjqmzjkmvjhmp-jsnzjrnvjrobjqohjoonjipejdprirqcilqh";
        this.Buchstabe[68] = ":";
        this.Daten[68] = "-jmnejingjgncjfmxjfmrjimljomhjumhjymmjzmqjvnejrnjjemt-jokajkjzjijvjgjpjfjjjhjfjjjbjwiskcixkejhkcjqjyjwjrjwjlju";
        this.Buchstabe[69] = "!";
        this.Daten[69] = "-jkfsjmfyjmggjlhfjliljmjpjmkvjllhjllqjllxjlmbjmlv-jinzjenyjcnsjdnmjenijincjnmzjuncjynikanrjwoajqodjkodjgnx";
        this.Buchstabe[70] = "?";
        this.Daten[70] = "-iaiahwhyhuhshthohshjhsgyhtgshugmhzfyigfqiofjixfgjrfhkgfmktfrlggclkgoljhdlfhukziiktiukjjgkejmjyjwjvkgjukljskujsldjslnjslu-jpodjlocjjnwjlnrjqnkjxnfkdnekhnkkinrkhnwkcnzjvobjqob";
        this.Buchstabe[71] = "%";
        this.Daten[71] = "-hahngvhkgphegnhaglgwgkgmgsfvhbfmhmfihvfjicfuifgriahhhshrhihrgwhhgwhc-lmffljfllhfpldfuksgjjohyiujeickfhnlfgtmlgjnbgcnmfynrfwnv-jrnnjqnrjknpjfnmjbndjbmyjgmejpltkalmkllmkwmalammlbmvkxnbkinjkbnkjqnj";
        this.Buchstabe[72] = "$";
        this.Daten[72] = "-kygyktgwkogtkkgrkggqjugoizgrilgwhvhhhkhugyisgwjfhajphkjximkbjhkdkbkhkskmldkulmlnlmmbljmmkymyjunmjbnnirnnhvnjhemygsmlgpmb-jbfkjcfqjcfyjcggjdhijejljfkvjglzjgmrjgnrjgocjhoijhonjhorjhon";
        this.Buchstabe[73] = "<euro>";
        this.Daten[73] = "-lhhtllhqlehllahikvhfkohbkegxjqgxjbhbimhghyhlhjhygzilgsjagpjngokpgslhhclwhvmkilmqjcmujtmsksmgkylylelp-exixfdiyfiizfoizggjagtizhwivjnitkbiskiir-eykifdkifikhfnkhgdkehjkciekaizkajokbkakdkhkf";
        this.Buchstabe[74] = "-";
        this.Daten[74] = "-hrkehxkeickdiikbivjyjpjxkajykkjyktjwlajwlejvliju";
        this.Buchstabe[75] = "<-->";
        this.Daten[75] = "-gvkfhakehfkdhkkehskeidkeipkejckdjpkakdjykpjyktjylcjyljjzlojyltjxlxjv";
        this.Buchstabe[76] = "*";
        this.Daten[76] = "-jshzjshujrhqjshvjsidjsiojtjijukbjwkf-hojfhujhibjkisjqizjujokgjskjjwkh-kakikekfknjyktjulajplijklrjflxjcmcja-jskkjokrjmkvjflaizliitlrillziemhhxmnicmi-jzkojykkkbkokdkskfkykjlgkrlxkymklfmsllmwlpmz";
        this.Buchstabe[77] = "/";
        this.Daten[77] = "-lxfnluftlsfylogelkgnlfgxkkiljsjmjekmiuljilmcicmshznbhvnjhrnqhonwhmochkoh";
        this.Buchstabe[78] = "\\";
        this.Daten[78] = "-ikfnipfrirfxiugejbgvjnidjviykdjqkjkjkplbkulrlbmllfmvljnflonqlqnulrnylsocltog";
        this.Buchstabe[79] = "§";
        this.Daten[79] = "-kqfykpfuklfrkgfpkcfojyfmjufljlfliwfxitghivhbjahpjiicjtipkfizkqjpkwkfkxkkkwktkplckfldjslajoky-jhidjcifiyigitiiipipiniwinjaipjqivkfjdksjplkjwlwkcmhkgmtkjnckmnoknnwknockiohkaojjqomjioljdoe";
        this.Buchstabe[80] = "&";
        this.Daten[80] = "-nloknkofngnzndnvmznqmrnhlxmjlklykvlmkilakckujskjjejsiwjiiniyigipiaifhwhuhuhnhvhbhzgpifgeirfujafrjlfojvfnkhfxkngjkqgykphnkhihjyiujkjeivjoigjzhokwhhllhdlzhcmfhemphknahtnlienvisnyjpnzkgnxktnslgnilrmxmgmdmnlpmslemukx";
        this.Buchstabe[81] = "(";
        this.Daten[81] = "-ksfikpfmkmfqkhfxkdgdjzgjjugrjmhkjfiejbiyizjpizkhjalgjclujfmhjimujjmzjonjjsnqjwnxkaobkfogkhokkgof";
        this.Buchstabe[82] = ")";
        this.Daten[82] = "-jrfnjvfpjwftkbgbkeghkjgykohqktiokujekujtkukjktkqkslckqlpkmmfkimokemyjznijunsjpoajjojjhon";
        this.Buchstabe[83] = "[";
        this.Daten[83] = "-ilfeiofiiufkizfljjflkdfm-irfhirfnirfsiqfyiqghiqgriqhdiphqipjdiokciolhiplxiqmjirmuirneirnnirnwiroairoeixobjeoajinzjmnzjqnzjwoakbobkgob";
        this.Buchstabe[84] = "]";
        this.Daten[84] = "-itfkiyfkiufkiyfljifmjnfmjufokbfokhfoklfpkpfpktfrktfwksgakqgqkohnkmirkkjtkjktkjlokkmgklmskmnekmniklnmklnqkknv-iroeiwobjdobjhoajlnzjqnyjvnxjznwkenwkjnxknnx";
        this.Buchstabe[85] = "{";
        this.Daten[85] = "-jwexjsfcjofgjkfojjftjefzjbggjagpizgyjbhrjeigjfinjgiujgizjejhjajmiujripjwiljyiqjyiukaiykgjakkjdkrjelbjflqjfmejfmqjdnkjbnujboijbopjeoujioyjppajwpdkcpi";
        this.Buchstabe[86] = "}";
        this.Daten[86] = "-jyewkceukiezklfekpfqkpgfkognkngvklhnkjidkjimkkjbknjlktjtlajylgkalckckzkgkwklkskskpkzkolekoljkrlvkvmjkymwlanjlanpkyobktolkoorkhoukbou";
        this.Buchstabe[87] = "_";
        this.Daten[87] = "-hkochgobhmobhqobhvobigoaivnzjjnykenyknoakvoalaoblioblmoblqobluoblzocmdoc";
        this.Buchstabe[88] = "\u00e4";
        this.Daten[88] = "-iygwixhdixhjixhniyhu-kegykhgukghbkfhfkghnkhhr-lbjplgjqlcjqlbjmkyjhktjdkmjakcizjsjajhjeimjoiijrhzkbhtknhpkzholfhnlthrmjhwmyihnuiooeivoljeokjrofkmnukvnlkynf-lcizldivlcizlcjhlckcldkyldltldmlldnalcnoleoblfogljojlpoklwokmjofmonz";
        this.Buchstabe[89] = "\u00f6";
        this.Daten[89] = "-jjgxjlgtjlgyjlhdjlhhjlhnjlhr-kqgwkvgukshekqhjkqhukqhy-jvogjrohjmohjgodjcoaiynwitnsinnjikneiimyigmriemjiclticllidkxihklinkaizjnjjjijvjdkhjakqjdlcjplkkalpkolsldlsmalpmnllmylgnikyntktnzknoekiojkaomjuomjpok";
        this.Buchstabe[90] = "\u00fc";
        this.Daten[90] = "-jagpjbgujbhajahljaht-kiguklhbklhgkkhskjic-hxixhvjbhvjghujnhujvhukfhtlahtlvhvmmianhientikobirohizokjnonjxonkhojkqnxlcmzlimklllx-lfirlgixlgjblgjhlfkalekylelvlhmqljnalknklmnslmoclqohluoilzokmfokmnogmroc";
        this.Buchstabe[91] = "\u00c4";
        this.Daten[91] = "-hwethxezhxfghxfl-lqeslsewlrfclofqlnfw-jifnjmfljjfrjhfwjfgdjagviuhsimiuidjuhzkihtlchplthnmihjmzhinlhgnvheochdohhcol-jofkjpfojrfwjtgdjwgmkghwkjiiktjnlakjlblclglslkmjlnmslqmyltnflxnolznv-hulciblaiikziqkzizkzjilakblckqldlflblklb";
        this.Buchstabe[92] = "\u00d6";
        this.Daten[92] = "-iqedioelinepinet-kpeckteekqelkperknfa-jhogjdofiyodirobinnyicnrhsnfhjmohclwgzlbgxjvgyiohaighciahfhuhlhjhvgxinghjagajlfwjxfvkifwkwgalegjlmgylshpluhylwislwjmlvkglrlglnlwljmjlfmulbnektnskmoakfoijwomjlomjgok";
        this.Buchstabe[93] = "\u00dc";
        this.Daten[93] = "-iceaigdxifeeieeiidemides-lbeakzeekyejkyeokwetkwex-gwflgwfpgwfugvgagtgqgrhzgqikgojrgnkmgnlegolzgqmlgtmwgxndhhnmhonshwnyihobjaobjloajxnwkhnrkpnmkxnflanalhmplmmblrlkltlclxkmlzjtmajamcihmchkmcgumcghmdfxmdfqmcfhmcfcmcex";
        this.Buchstabe[94] = "\u00df";
        this.Daten[94] = "-hzpbibowiboribokidnticmqhzlthxkbhzjiidioihhtikhaiogmirgdizfsjhfljufhklfklafvlpgvlqhnloidldisksizkgjhjtjqjmkbjmkpjskykclfktlmkylololzltmjlvmvlqnkljntkznzknocjsodjlod";
        this.Buchstabe[95] = "<quoteu>";
        this.Daten[95] = "-ilnoiknsijnyiiofifovhzpm-jynojyntjxnzjuonjsovjmpljiprjfpv";
        this.Buchstabe[96] = "<quoteo>";
        this.Daten[96] = "-ldelkzeskwezktfikqgakqggkqgo-mzeqmvevmtezmqfemkfsmhgcmgggmggomhgs";
        this.Buchstabe[97] = "'";
        this.Daten[97] = "-kseokreskrezkrfikqfqkqfxkogokngs";
        this.Buchstabe[98] = "|";
        this.Daten[98] = "-jwekjwetjvezjvfkjvfwjvgjjvicjwjejwkcjwkzjwlsjwmijwnajwnljxntjyofjyomjyorjyovjyozjypdjypi";
        this.Buchstabe[99] = "<star>";
        this.Daten[99] = "-gzoihcochdnxhfnrhgnihjmzhmmqhqlwhwlaidkfiijlioitivhwiyhkjahajagsjbgljegbjhfujifqjjfmjlftjmfyjqgmjwhijzhukgirkojuktklkxkxlblilglwljmcllmjlnmplqmwltnclvnilxnnlzntmanymbocmcohmcollzohlvoelinxlcntkvnpkonlkhnhkancjmmtjfmpizmjitmdinlwihlqiclmhtlihglbgxkugokngjkjgakefwjzfqjtfmjofjjkfhjgfljcfqjbfxizgdizgjiygsixibivikitjciqjripkeipkpiskzitloislyirmhiqmpirnbirniipnnionripnqitnmixniizndjdmkjrlwkflikqkqlfkhlnjxlujomajgmhiymminmvignbhznhhtnnhqnrhmnvhjnzhfod";
        this.Buchstabe[100] = "<skull>";
        this.Daten[100] = "-iqmgikmjilmdillwiklgikkyikkmihkiidkghwkahtjwhljlhfiyhfijhkhthzgxingojegjjxgkkrgslhgzlxhmmbhxmciklzjclvjllpjrlijvlcjykzkdkykhkzknkzkskzkxkzldkzlqlamckzmhkvmjkpmjklmjkamjjgmkivmiinmgigmg-ihldillcislbixlajekzjlkzjtkzkckzklkzlekzljla-jjkxjilbjhlhjhlojhmajgmi-jzkvkckzkcldkbljkalwjzmejzmi-knlckpljkolpkolxkmmiklmm-izinivioiqijiqidiqhzishuivhqiyhmjihkjmhrjlicjhimixioiqijimicinhxiwhpjchnjehrjbhziwihiriiiriciwhujahpjbhuizhyiyiciwiiiziajfhrjjhojkhvjhidjfiijfiejihujnhojohtjnhzjhikjeifjeia-kwifkwijkrilknigkmiaknhukphqkuhlldhnlfhulfibleiilaimkuinklijkiidkjhyknhukyhplchtlcibkwinksiokrikkqigkqibkthrkxhpkxhukvidksijkriekshzkuhtkwhzkuie-jljsjmjnjrjijvjkjwjojvjs-lrhelvgylygtmdgomkghmlgcmkfxmkftmkfnmmfimtfhmzfjndfqncgancgenhgcnmgansganwgbnzgfnzglnxgqnrgunlgtnegrmzgtmvgwmohcmkhgmghkmbhqlxhu-ihksidkxhvlfhqllhflzgzmggsmlgnmpggmvgbmufwmufqmufmmvfimxfgncffniffnnflnvfsnvfwnugfnpgfnuggoaghoegioigqougvoxgzouhcolhdochbnrgynnhbnihgndhjmzhnmuhymiiemdinluirlp-hhhrhdhlgzhggvhbgkgrgcgnfwglfsgofmgpfggpeygpeugleugfeyfyfdfvfkftfrftfvftfwfofxfifyfegcewggergkergpexgqfigoftgnfygrgdgxgjhcgnhggrhpgwhvhahzhd-lllblikxlekulhkylqlelvlimblnmhlsmwmfncmkngmonkmmnpmlnwmmoamnogmsoknboknjognnnxnonsnnnonsnnnznkofneokmxommtommmommiojmknvmnnpmrnkmtngmoncmkmxmdmqlzmnlsmhlnmbljlwlhlrldln";
        this.Buchstabe[101] = "<heart>";
        this.Daten[101] = "-jjokjhofjgnyjbnqiznmixniivndiomsihmhhtluhklmhblfgrkygdkofvkhfojzfijpfdixfdijfehxfihnfnhcfugrgigfgtfzhdfuhqfqhzfsiifxirgejbgpjggxjlhfjohmjphhjphajrgujvgokcgikhggkqgblafxlkfwmafxmkgamsghmzgpnhhenjhqnkieniitnfjenajpmwjvmpkgmgkolslalililaltkumckrmlkpmpkkmykfnhjynsjtobjnonjlor";
        this.Buchstabe[102] = "<heart2>";
        this.Daten[102] = "-kbodjznxjxntjwnojunijsncjgmjitluihlhhvkxhfkjgujzgkjsgejlfxjbfqigfphsfshefxgrgjgbgvfthjfqhxfpirfujbgcjjgljqgtjvgzjyhdjygzjzgtkcgmkegikngbkzfvlofqmmfrnbfvnnfznwgfoegwofhkoehxnxivnpjincjwmokklvleljlqlbmbkwmkktmskrmzkknlkenwjzof-gtfzgwfugrfxglgfgiglfxhafthkfphuflilfniqfriqfyiegehsgnhchcgkhqfwhwfsiefnihftibgdhxgjhsgphhhegwhugmiigfjcggjjgojhguiwgwirhfhyhnhkhxgvijgjixfzjbgbjbghiugsinhfichvhsinhljfhhjrhkkbhpkahrjvhsjrhujliaiyijigjbhjjngwjwgqjygwjxhajuhgjmhrjdiiirjdihjyidkvielbijlaiokpitjzjcjgjoikkbhqkrgqlagdlifulqfnlvfmlvfvlogkldhdkgiljpjljekgizkvjblijdlmjdlqjflijhldjokmjtkcjzjrklitkyhwlxgrmigfmsfxnbfungfwnggdmxgsmmhhltiekljxkckjjqlhjolsjmmbjlmijqmejulykjlckqktldjylijnlojdlximmlhtmvhmnghgnthbnthhnnhtneihmtiwmhjnllktkxlpkomgkhmxkgnfkgnjkknckqmskymelklklrlalxkqmhjzmpjnmyjcnkisnoipnril";
        this.Buchstabe[103] = "<dot>";
        this.Daten[103] = "-jajwiwjxisjtisjpitjjiwjejajajfjbjjjfjkjkjjjqjejvizjvivju";
        this.Buchstabe[104] = "<circle>";
        this.Daten[104] = "-jrlnjmlqjilqjdlniyliiulfiokyigklidkgickaiajoibjiicjcieiximihiwhyjhhqjuhmkbhkkohkkzhrliidloiqlsjelrjwlpkhljkqldkxkslgkklkkclnjplojhlmjblliwli-jfhxjjhujfhyjcidixiiiripiliwifjdhwjphvjvicjsikjjiuivjgifkbhnkihkkjhtkaiijpjbjcjuixkcitkjixkijbkdjijpjojikeipkuhzldhtkzidkmixjujujgkqiylfiyljjflhjmkzjxklkmjsleiylkitlhjakyjnklkhjykzjqlnjwllkclekikxkwkildkblvjmltjqlojxkvkwklljkhlo";
        this.Buchstabe[105] = "<arrowright>";
        this.Daten[105] = "-fokdfojzfukafykbgckbggkbgmkagskagzjzhlkbidkeiqkdjckbjmkbkbkdkjkfkrkflckdlgkdlkke-jmimjtiqjyitkdiwknjdkwjklljulmjzlrkclvkdlukhlpklllkolbkvkrlfkmljkilokclvjylz";
        this.Buchstabe[106] = "<arrowleft>";
        this.Daten[106] = "-ieidikhxihibieigiailhqixhljchgjhhajmgpjtgrjygukdgxkihhkthxliiflpikls-gvjogrjpgwjshajshfjthwjuidjuizjujujukmjwlajylljylzjxmgjymmjymsjymzjynejynijz";
        this.Buchstabe[107] = "<arrowup>";
        this.Daten[107] = "-jhfwjkfsjjfwjggbjdgiiygqithaimhjighsiaiahwifhuijhxif-jlfxjlftjofxjpgcjrgjjxhdkbhnkghwkqijlditlhit-jnftjmgajmgfjmgujmhgjmiwjljtjlkojllfjmmmjmmsjlncjknkjknrjknwjkoa";
        this.Buchstabe[108] = "<arrowdown>";
        this.Daten[108] = "-jmfojofkjofrjofzjogjjphojpiujojyjmljjlmejlmsjlndjmnmjmnqjmnx-hylrhvlmiblqiflxikmeivmsjbnhjfnxjioejlnzjnnvjunhkimnkwlslflgljlb";
        this.Buchstabe[109] = "<spiral>";
        this.Daten[109] = "-jmjtjijqjgjljgjgjjizjoitjviokainkkipkwizlbjilcjslbkekrktkhlajvldjilaiwkuibkchujrhtjehvirifiaiqhrjehkjuhfkshikzhllfhqlqialxiombjemckblzkqlvlelnlrljlwkymekmmljymqjjmriumohymahmlnhakygpkegkizgmifgphlhaglhkfzhzfqiufjjqfckzfclvfmmlgcmygrnjhhnnhpnrihntjbnsjsnokrnjlhnclxmumlmlmxlunolinykxogkionjwopjmopixonirominom";
        this.Buchstabe[110] = "<face>";
        this.Daten[110] = "-faiafbhvfdhpffhlfjhafqgngagcgnfvhffxhkgbhtgoibhfichk-kshjkshfktgzkugvkygllegdlkfxltftmlfumxgbnnhbnohinqhtnria-grklguktgwkzgzlghdlnhhluitmsjlmxkcmzkqmylhmmlrmblzlrmflhmlkumokompkk";
        this.Buchstabe[111] = "<smiley>";
        this.Daten[111] = "-jlofjgogjcogivogirofimoeihociboahwnzhknthdnpgonggcmwfsmlfllxfckwezkbfbjgfeipfohrfxhegjgshbgghqfxiofnjefmjvflkwftlogcmdgnmnhcmvhsmyicnbiwnbjlmzkbmvkqmnlkmglylzmilsmqlhnblanhkunmknnpkantjsnvjnnxjjnyjdnxixnt-hgiphciogxikgwifgxibhchuhihrhmhthpiahpihhmimhdimgzik-klinkhinkciikaiekbhykdhukjhpkthskviaktilkpiokjinkfim-gykygwkugzkzhdldhklnhqlsimlzjbmajqlzjylykglxkwlnlflallkrlnkn";
        this.Buchstabe[112] = "<danger>";
        this.Daten[112] = "-inffitfeiofeimflihfxiegfhwhdhhirgwjrgokkgjkwghldgmkzgqkwhokihzkbiljvixjojjjilhihlmiflkisliixlbjokmkxkhljkclwjumojonejjnxjiod-ixlkivlpivlwiymljamujcncjdnsjfofjfoojgotjkosjnoojzobkinrkrnglamwlmmklqmhlume";
        this.Buchstabe[113] = "<box>";
        this.Daten[113] = "-gwhtgyhngxhrgxhxgyixgzjkhaklhalehblq-gvhkgzhhhehhhihihnhjhwhjiphhjnhgkkhekzhdlihelmhglphllphqlohvlnillkjilikflikxljlq-hillhelohjlpholpihlpinloixlmjrlkkilkkulklblllglklkljlolh";
        this.Buchstabe[114] = "<box2>";
        this.Daten[114] = "-gwhtgyhngxhrgxhxgyixgzjkhaklhalehblq-gvhkgzhhhehhhihihnhjhwhjiphhjnhgkkhekzhdlihelmhglphllphqlohvlnillkjilikflikxljlq-hillhelohjlpholpihlpinloixlmjrlkkilkkulklblllglklkljlolh-hkhhhghohehvhcijhbiohbiuhgirhnifhxhtikhhihhoidhvhriohgjfhfjphjjoigiiioiaiyhoiwhtiriaidivhojrhjkchgkuhlkthrkmidjpiqiwjficjshpkchjkahnjthzjhisiikehtldholshxlliikwipkmjdjqjtitkqhqkzhgkyhmkuhukjiojvjljojwjikgixlgiwlnjbljjikzjnksjsklkfjslaillgiflfilkziwkhkfkckojxlajylfkclfkmktkvkglljllijslfjykzkokwlakxlglcldlfkwlglc";
        this.Buchstabe[115] = "<1/2>";
        this.Daten[115] = "-gfgwgkgrgngngvgdhbfthefphgfjhifnhifthhguhghnhgiehgijhhiqhcisgwirgpiqgjiqgfiqgkiqgtiqgziphkiphpiphuiqhyiqiciqihiq-lvfslrfrlpfvlmfzljgflfgnlagxkgigjrjcjgjuixkniolfhzmehrmqhknahbnsgvnz-jwlmjtlijtlejukzjwkukfkkknkckxjyljkilokvlmlkldmgktmvkinjjxnsjlocjjogjnogjsofjwofkbofkuoflfofloofltoelroalpnulnnpllnl";
        this.Buchstabe[116] = "<1/3>";
        this.Daten[116] = "-hagghegchgfyhjfthqfjhvfchvfghvfshugjhuhnhtidhtishtiwhpixhkivheivgviwgoiygtiwhbiuhgiuhrisicirikisiqitivit-lygjmegjmagjlwgplrgwlmhekxhzkejcjkkbiplhielzhlmzhinehenh-kmkzknkvkmkpkmklkmkfklkbkqkbkukbldkalkjzlqjymrjvmwjvmrkamhknlxlalmlmlqlllwljmalimjlgmnlhnambnbmhnbmrmxncminslsnzlaobklnzkgnwjznrjznm";
        this.Buchstabe[117] = "<1/4>";
        this.Daten[117] = "-gugfgygehdgahgfwhkfrhnfmhvfchvfjhvfohufuhuglhshohshxhsiehsijhsirhoiuhkiuhditgziugvivgpixgvivhbiuhgithuiriaiqijiqiqiq-lufslufxlrgelhgzksibkkirjzjijdkqillyhwmuhmnlhhnr-ltjslxjolsjtlojyljkgkwkzjymejrmkjnmojimsjpmvjumtkcmskwmnmdmjmzmi-lwjtlyjnmajsmajzmakkmbkxmalmlxnclxnylxollxor";
        this.Buchstabe[118] = "<2/3>";
        this.Daten[118] = "-gjgjgjgdgkfzgqfsgvfnhlfghufmiegiidgphxhghmhygzirgmjegdjofzjtgejvgijvgojuhcjthqjuiajvijjviljriijmifji-lkfulgfulbgakzgfkvgoklhlkeiajwiriilrhqmyhhnqhaoc-kbkpkbkikakckajxkgjukkjukujulbjulsjtmkjtmrjtmokamkkfmcktlsljlemdlilzlnlvlslslxlqmclpmolomxlvnbmhnamtmwnfmjnwlxoglgomkpoojtofjlnr";
        this.Buchstabe[119] = "<3/4>";
        this.Daten[119] = "-gafzfyfufxfqfzfmgffigmfhgqfigvfjhgfnhsfoidfnimfninfrikfwihgbicgghxgnhkhdgzhrhehnhkhjhohihyhhiuhoixhsjbiajbiliyiximjsigjxhtkghfkmgfkffrjs-lngklsghlpgmlchjkpijjtjsillqhumrhanygwobgwnu-lwiymciulyjalujfljjxkskujylwjhmnizmwjgmxjnmvkhmplqmjmimlmsmp-mcivmdjcmcjjmckjmblslzndlznvlzoblzoq";
        this.Buchstabe[120] = "<smivieh>";
        this.Daten[120] = "-dkladgkxdbkvczkqcykldcjqdljeeaixenivezjcfjjsfbksewkweslbejlidzljdqlhdiledbkx-ebkgdzkkdykf-fojffkjhfgjhfgjdfjizfnixfriuggipgqiugyjehejyheklhbkwgvldghlkfxlmfnllewlderky-gckcgckg-cwladakycwlacqlecnlicilncdlvbxmebsmobjnlbeofbfolbkoqbqorbzorcgoocnojctodcxnwdanpddngdhmwdrltdpmcdomjdknfdgoddkolebocejnoennfetmlevmcexlqexlvewmaexmreznoffnzfnocfwnzgjnfgnmpgplygplngolsgrmfgxmuhbnbhfnghtnpiamwiamihzmahvlghnkl-culicsldcqlhcwliddlheclegakwhckwhmkxhykyifkx-fkivfgiwfdirfcinfcijfdhygiglgvgdhkfvhzfnjkezkweumceznzfkoyfupsglqfhiqiippzjlplkfoukyoalrmemilrmjkpmfjhlvinlnhxlkhmljhflf-jtmkjvmfjsmjjpmvjondjlnsjknzjony-kkmoksmlkvmrkvmykungkunpkunwkuoa-lvmimfmlmamnmamvmankmbnu-namlnkmknkmpnkmunkninmnvnmnz-pmispniwpsiypyjaqsjcrdjbrmjartjbryjc-ovjworjyovjw";
        this.Buchstabe[121] = "<sad>";
        this.Daten[121] = "-jboiiwoiipogilofigoehuoahhnuhbnrgonjgbmzfpmmfbllevkseqiserihexhpfchgfwghgnfvhifmipfhjmfikkfjlpfsmfgemsgtnbhjngijnejkncjtmykmmsldmklulvmplknbkynmkonvkjnzkaogjrokjjomizopioorhzophsol-hbifgxifguibgvhxgxhrhchnhghmhkhshlhwhkidhgiggzie-klihkhihkgibkhhtkkhnkphlkuhqkvhvktiekoilkiil-gqlygslsgvlohdlghokwhvksiukmjlknkbkukqlikvlqkxlu";
        this.Buchstabe[122] = "<shit>";
        this.Daten[122] = "-jfofjbofiwohiqohimogicoghfoigyoigsohgfoefpobfeobeunyeknpehnhehmyelmneomhewlwfglofsljgnlhgulihploieluitlzjhmdkemjkqmnlgmwljnclkngljnmlgnrldnwkxofkqokkhoojuopiyolikof-golcgklbgfkzgakyfukvfokofjkffijufjjjfoixftisgbipglimgyilhjiphxjbihjkisjqjikajskgkbknkkkskqkxkvlhkvlqkulxkjmbkemcjsmejimd-hdidgzidguhygshrgshmgshggthagvgtgygghefohhfchjevhjfahjfehkflhnftiagmikgviphajahkjkhsjuiakgilkkitkmjbkmjjkjjwkekcjukgjjki-evfkeqfnelfteifxeggdefgheegnefgyejhiephwerieeqileniwekjeekjmemjtenjxeokkenkqelku-gbfagafefxfifvfmfsftfsgafugigagtgchafxhlfvhp-jjehjnehjlemjkeqjieujhfmjjfrjmfvjpgbjqgjjogqjngv-lkexltexloezllfiliftlggcljgmlthdlxhqlwicltinloiwlmjblojllsjslvkalukrlsldlrll";
        this.Buchstabe[123] = "<icecream>";
        this.Daten[123] = "-hmivhmirhlinhniuhrjdhzkeiekwiwnkjcojjipcjlpl-hoivhliqhjimhoimhsilieijinihjoihkkiflcielvihmcilmgikmfiomdislyjdlrjwlokelgkzldljkxmckrmskmnfkhnvkdocjzoijxopjtpajspejrpi-htiihpifhlidhfhxhchtgvhiguhcgtgwgtgqgugjhdfrhjfihsfciufajbfejnfojwgakcglkchcjyhnjthxjjijjfin-kagdkafyjzfukbfnkffgkifckqewlaetltermhfamqfnmygbmygmmugymqhlmnhvmjiemcilloislcisksiskoir-ifezigevigerigenihefiiebildxiqdoiydijqdckedbksdelddjlkdqlneglnerllfclifm";
        this.Buchstabe[124] = "<music>";
        this.Daten[124] = "-iqolilomigomhzomhookhjojgyodgonvghneghmqgmmchclihpkyifkviwkwjmlbkalikllskplyktmjkvmuktnfkonrkhnyjzofjqokjcooikosigoqhzolhtoh-hhlfhmlahllehiljhelqgzlygumhgfnkgenpginmhamrhimihrlziblpillfiskzizkxitljiemfhqnbhhnqhgnuhmnmhwmziomhjglojplfjwlajtlgjgmbiqmyibnyimntizndjgmujwmdkfltkcmbjzmgjtmtjpnbjjnsjiobjmnxjpnsjznfkhmsklmn-kmebkoejkperkpfckrgakthakuiakwjwkxkekxkskxljkxltkxmbkxmikwmpkxmvkxmz-kneekpekkqeokreukufakxfhllfwlwgdmgglmqgumwhhmzihmyizmujompjzmiki-ktgbkygglcgilmgmlrgpmfhamqhvmribmsihmsiymrjhmpjmmnjqmkju";
        this.Buchstabe[125] = "<mail>";
        this.Daten[125] = "-fbgqfbgmfcgvfdheffigfhjsfikkfimhfinbfinm-fagiffghflgifpgjfvgjgbgkgsgkhpgkimgjjkgikpgklggklzgkmtglndgmnzgkoughpjgeprgepvgfptgopsgvprhfpphrpnifpkjhpikiphlfphlzphmmphmxphne-fbnofgnmfknlfwnlgenlhnnlinnijqnglpnemlndmvndnoncocnaonnapanaphnapmna-fbhnfghoflhrfthxggifheixhujjicjpiskdjfkpjskwkclbkhldkolgkvllkwlglalalekxljkslpkmlwkfmdjymtjkniiynuioodihoshzoxhxpchupghq-jekljakmiwksiqkvhzlbhrlehkligvlrghmafvmifemtfamu-lzkhlvkilzkomekrmkkvnaleoglyonmboymipdmmphmpplmr";
        this.Buchstabe[126] = "<tongue>";
        this.Daten[126] = "-fsijfridfrhwfrhrfthmfvhgfyhagjgkgtgehfgbhwghihgriphgiuho-fvijfxidgahzghhsgmhphphghuhgiehjiohmiwhpjbhtjdhy-hghehchehchahhgxhkhbhjhghehjhahjgwhhgwhdgxgzhbgxhfgxhjha-kxhvkxhrkyhklahflegtlkgiltfzmffvmufwncfynrgioagvofhhoihtoiic-lchplghklkhhlphfmchamqgynfhanvhnobhv-mlhbmigxmkgsmqgomugpmygwmxhbmthemphe-gkkdgpkigtkkgyknhjkrhxktipkvjwkvlgkulrksmnkonokgnzkaogjuokjq-irkyisldislhislmislyiumkjandjgnpkfodkmodlboblonxlynpmbnlmfnemgmumimjmklxmklnmildmgkymfku-krkzkrldkrlikrloksmdksmrksnkksntktnykuoc";
        this.Buchstabe[127] = "<madman>";
        this.Daten[127] = "-gyijgsilgmihgfibgdhxgbhsfzhofyhifyhcfzgvgbgogegighgdgmfzgsfvhlfshxfxigggiogniqgriqgyinhhijhpidhwhwichliihfijgyikgriggpic-hihf-kjibkjifkeifjziejthzjnhrjihijghdjfgxjfgsjjgcjpfujwfpkffmkkfmkvfmlefqllfvlsgkltgtlrhdlqhilnhrljialcifkuihkhihkdigjzid-klhc-igifihimihirifiyicjdhwjfhmjehgjchajcgvjegrjggnjmgkjsgkjygnkegukigykjhgkihmkfhqkahujyhwkchwkghukkhtkohskthwkxibkyigkxilktiokpiqkliskhiwkejbkejfkgjfkkjgkojkksjpkwjukwkdkukjktknkqknkmkkkhkikckhjwkfjskbjpjxjljrjcjnixjkisjiiljjie-hglhhclegylbgtkygmksgikpgbkiftkdfjjxeyjremjjegjheajhdvjhdqjhdijqdakccxkicwkocxkscykwdglndllxdrmgebmoermyffndftnggpnkhcnohonsiantimnsiynsjsnrkhnmkvnhlgnelvmxmjmpmrmjnbmcnllunslnnxlhodkvohkeoijxohjsodjonwjlnpjinljingjimxjlmpjpmdjwlwkblokgleknkykrkrkvkglbjxlejplhjiljjdljivljiqlkillliglmhylkhslkhnlkhhlkgzljgvlggslc-eojlenjreljweikdecktdslmdpludoly-fykegckiftkofllffilnewmkeqmxennfernd-gzljhclnhalrgxlxgumfgqmqgmmzglneglnn-inloiolsiolyiomcinmgimmqiindifnoient-jvlcjzldkalhkclpkclvkdmhkdmtkdmzkenhkgnl-ltkglzkilvkplwktlykymcllmjmcmmmi-myjfmyjkncjsnfjynikennkrnrkznule-dqjtdujydxkddzkhedkmegkrekkweolbetlgfelofplufzmagmmjhemrhimrhsmrhxmrinmqizmnjkmkjzmjknmhkvmflcmelimclplzlwlvmcltmilrmrllmwlgnblcngkynkkvnpkrnukmnykj-ejgseegpeagmdtgkdjgidegicnglcfgobzgsbvgxbshgboiibqiwbzjecqjkdjjkecjhepizeqiv-cmhncmhicqhecwgzdagydigydxhgebhkechoechsechxedibehieelif-mogxmpgtmugnmygjnigdnugaohgboxggplgopsgzpthnpqiepmimphiuoyizosjcnujlnojl-olgzohgwocgtnwgsnqgtnmgunggynbhdmzhmnlhxnqicnrihnoinnkip-docidqcddqchededeferehfcejflekfq-enckerceevcnexcxfcdyffexfifmfjfq-gaceggccgicngkdjglelgnfegqfm-hucbhzcbhzcohzdahzdnhyeahxeohxfl-ixcojccljccujbdmjbdwjbegjbepjbfa-jscjjzcekbclkbcskadcjydojxeajweljvfe-kubykxcfkxcmkwcvkvdgkudpkudyktefktelktep-lkcqlqcjlscqlscxlsdgloeblnevlnez-mlcsmpcsmqcwmqdcmjefmgetmfex-mxdanademzdjmydrmwebmtelmpes-fhfjfgfffhfafjewfpelfseggodxhcechkejhneo-fnfgflfkfifffjezfmeufoepgbeegnebhaeehiei-jnepjjeqjmeljreejweakcdwkqdsledvlweklzetlzez-jyenjuepjvejkbeckldwksdvljdzlxekmaeo-cyjvdcjwcyjtctjqcojscljxcjkbcikgcikmcjktcklbcqlscwmgdqnhdynoegnvetobfcocflobfuocgcodglobgyochhoghqojifokiqojjbohjmohkgogkroeldoelpocmjnumnntmwnrnenpnpnhnvnaobmtofmkollqoolfopkxopkp-hmnzhpodhqojhsoqhtphhqpwhoqlhsqohxqm-kboakbofkbojkbookboukbpakbplkbpukcqkkcqs-hfpwhbpygvpygppyglpyggpzgbpzfuqafgqaezqa-kkpwkopykspzkzpzlkpzlppzmbpzmmpy-grpzgnqbgnqhgqqphcrehprkivrqjjrrjqrrkcrrkormkvrhlcrdliqwlsqjlwqelxqa-hkhghfhghchbhdgxhhgthlgvhmgzhkhdhghhhbhh-kihdkggzkkgwkogwkqhakrhekohjkkhlkghkkdhg";
        this.Buchstabe[128] = "<cloud>";
        this.Daten[128] = "-jgmbjcmbiwmbinmdiamehomfhimfhcmfgjmefxmffnmhfemhevmeenmbeglzdvlxdnlsdglmdalfcvkucvkkcwjzdmjhdsjfdzjdekjaexjafhjefrjjfmjhfijcffiyfcitezikeyibfahlffhcfngtfxgmgcgjgqgehegdhrggikgqjbhjjehojfhtjiiajmhujohojqhhjugyjwgtkdgjkofzkwfvlfftlyfumjfymrgfmygondhfnchrmxicmrilmlirmqiomvinmzinnjiontirociwokjdoqjlowkaowkkovksoskyonldoellnxlrnqlvnilxmslzmjmalpmalflzkwlykslykjlyjylzjplzjllzjblzikmaifmaibma-iwksjakljakpiwkwiulcimlrhpnehinqhfnuhbnzhhnxhmnuhznnihnkixnejvmykjmvkrmukwmslamskymxkwnbkpnmkfocjtoujhplixpzimqqiiqw-iopeiopailpgilplilpxilqdikqpijqwiiraimqyiqqviuqsjfqmkaqckmqa";
        this.Buchstabe[129] = "<sun>";
        this.Daten[129] = "-irliiklkigllicllhvlghrlchnkyhjkshbjmhdjfhkiqhpijighwiuhrjjhrkihvkvicleimlliwlojhlpjtlkknlgkzlaljkqlykimdjxmgjkmfinlyhzlphrlh-jmfrjpgajpgijpgsjnhujnif-lsjklxjnmejnmjjnmojomujpnrjq-hgjuhajwgwjwgrjwgbjtfhjserjseljt-jnmhjnmdjlmhjlmljlmujlnujkoojkpd-ldhxldiblcifleiblhhwllhqlthdmhgjmpfz-hpilhmighghyhchtgxhngrhhgmhbgigugdgqgagm-hxlthtlshnlxhjmbhemhgympgrmugknafznmftnqfznh-kwlplbltlimalmmflrmklymomjmxmunfnbnj";
        this.Buchstabe[130] = "=";
        this.Daten[130] = "-gtjxgsjtgwjthajshfjshljshrjsitjsjnjrkkjqlbjqlsjs-gxlchhldhlleialfixlfjpldkhlcktlclcldlildlolelsld";
        this.Buchstabe[131] = "+";
        this.Daten[131] = "-hwjpiajsifjsisjrjijpkajpkrjolgjolzjmmhjlmljmmpjp-khhhkihlkihpkiickiilkijgkijxkiktkildkhllkglukgmc";
    }
    
    class MouseEventHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Mailwriter.this.mousePressed = true;
            Mailwriter.this.mouseX = mouseEvent.getX();
            Mailwriter.this.mouseY = mouseEvent.getY();
            if (Mailwriter.this.mouseY < 20 && Mailwriter.this.mouseX < 87) {
                Mailwriter.this.mousePressed = false;
                try {
                    Mailwriter.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            Mailwriter.this.mousePressed = false;
        }
    }
    
    class MouseMotionEventHandler extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            Mailwriter.this.mouseX = mouseEvent.getX();
            Mailwriter.this.mouseY = mouseEvent.getY();
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Mailwriter.this.mouseX = mouseEvent.getX();
            Mailwriter.this.mouseY = mouseEvent.getY();
        }
    }
    
    class SmiSchirm_c
    {
        protected short[][] Rot;
        protected short[][] Gruen;
        protected short[][] Blau;
        protected int w;
        protected int h;
        protected int x;
        protected int y;
        boolean realtime;
        double fatLineQuality;
        double constDown;
        double constUp;
        
        public SmiSchirm_c(final int w, final int h) {
            this.realtime = false;
            this.fatLineQuality = 1.0;
            this.constDown = 0.007782219916379284;
            this.constUp = 128.49803921568628;
            this.w = w;
            this.h = h;
            this.Rot = new short[this.w][this.h];
            this.Gruen = new short[this.w][this.h];
            this.Blau = new short[this.w][this.h];
        }
        
        public void moveUpBy(final int n) {
            this.x = 0;
            while (this.x < this.w) {
                this.y = 0;
                while (this.y < this.h - n) {
                    this.Rot[this.x][this.y] = this.Rot[this.x][this.y + n];
                    this.Gruen[this.x][this.y] = this.Gruen[this.x][this.y + n];
                    this.Blau[this.x][this.y] = this.Blau[this.x][this.y + n];
                    ++this.y;
                }
                this.y = this.h - n;
                while (this.y < this.h) {
                    this.Rot[this.x][this.y] = (short)(this.constUp * Mailwriter.this.FarbePapier.getRed());
                    this.Gruen[this.x][this.y] = (short)(this.constUp * Mailwriter.this.FarbePapier.getGreen());
                    this.Blau[this.x][this.y] = (short)(this.constUp * Mailwriter.this.FarbePapier.getBlue());
                    ++this.y;
                }
                ++this.x;
            }
        }
        
        public void fill(final int n, final int n2, final int n3) {
            Mailwriter.this.osg.setColor(new Color(n, n2, n3));
            Mailwriter.this.osg.fillRect(0, 0, Mailwriter.this.AppletW, Mailwriter.this.AppletH);
            this.y = 0;
            while (this.y < this.h) {
                this.x = 0;
                while (this.x < this.w) {
                    this.Rot[this.x][this.y] = (short)(this.constUp * n);
                    this.Gruen[this.x][this.y] = (short)(this.constUp * n2);
                    this.Blau[this.x][this.y] = (short)(this.constUp * n3);
                    ++this.x;
                }
                ++this.y;
            }
        }
        
        public void update() {
            this.y = 0;
            while (this.y < this.h) {
                this.x = 0;
                while (this.x < this.w) {
                    Mailwriter.this.osg.setColor(new Color(this.Rot[this.x][this.y], this.Gruen[this.x][this.y], this.Blau[this.x][this.y]));
                    Mailwriter.this.osg.fillRect(this.x, this.y, 1, 1);
                    ++this.x;
                }
                ++this.y;
            }
        }
        
        public void setRealtime(final boolean realtime) {
            this.realtime = realtime;
        }
        
        public void setFatLineQuality(final double fatLineQuality) {
            this.fatLineQuality = fatLineQuality;
        }
        
        public void drawLine2(double n, double n2, final double n3, final double n4, final int n5, final int n6, final int n7, final double n8, final char c) {
            final double n9 = n3 - n;
            final double n10 = n4 - n2;
            final double sqrt = Math.sqrt(n9 * n9 + n10 * n10);
            final double n11 = n9 / sqrt;
            final double n12 = n10 / sqrt;
            for (int n13 = 0; n13 < sqrt; ++n13) {
                if (c == 'a') {
                    this.drawPixel2(n, n2, n5, n6, n7, n8);
                }
                else if (c == 'b') {
                    this.drawPixel2(n, n2, n5, n6, n7, n8);
                    this.drawPixel2(n + 1.0, n2, n5, n6, n7, n8);
                    this.drawPixel2(n, n2 + 1.0, n5, n6, n7, n8);
                }
                else if (c == 'c') {
                    this.drawPixel2(n, n2, n5, n6, n7, n8);
                    this.drawPixel2(n + 1.0, n2, n5, n6, n7, n8);
                    this.drawPixel2(n, n2 + 1.0, n5, n6, n7, n8);
                    this.drawPixel2(n - 1.0, n2, n5, n6, n7, n8);
                    this.drawPixel2(n, n2 - 1.0, n5, n6, n7, n8);
                }
                else if (c == 'd') {
                    this.drawPixel2(n, n2, n5, n6, n7, n8);
                    this.drawPixel2(n + 1.0, n2 - 1.0, n5, n6, n7, n8);
                    this.drawPixel2(n - 1.0, n2 + 1.0, n5, n6, n7, n8);
                }
                else if (c == 'e') {
                    this.drawPixel2(n, n2, n5, n6, n7, n8);
                    this.drawPixel2(n + 1.0, n2 - 1.0, n5, n6, n7, n8);
                    this.drawPixel2(n - 1.0, n2 + 1.0, n5, n6, n7, n8);
                    this.drawPixel2(n + 2.0, n2 - 2.0, n5, n6, n7, n8);
                    this.drawPixel2(n - 2.0, n2 + 2.0, n5, n6, n7, n8);
                }
                else if (c == 'f') {
                    this.drawPixel2(n - 1.0, n2 - 1.0, n5, n6, n7, n8);
                    this.drawPixel2(n + 1.0, n2 - 2.0, n5, n6, n7, n8);
                    this.drawPixel2(n, n2 + 1.0, n5, n6, n7, n8);
                }
                else if (c == 'g') {
                    this.drawPixel2(n - 2.0, n2 - 2.0, n5, n6, n7, n8);
                    this.drawPixel2(n + 2.0, n2 - 3.0, n5, n6, n7, n8);
                    this.drawPixel2(n, n2 + 2.0, n5, n6, n7, n8);
                }
                n += n11;
                n2 += n12;
            }
        }
        
        public void drawPixel2(final double n, final double n2, int n3, int n4, int n5, final double n6) {
            if (n > 0.0 && n < Mailwriter.this.AppletW - 1 && n2 > 0.0 && n2 < Mailwriter.this.AppletH - 1) {
                n3 *= (int)this.constUp;
                n4 *= (int)this.constUp;
                n5 *= (int)this.constUp;
                final int n7 = (int)n;
                final int n8 = n7 + 1;
                final int n9 = (int)n2;
                final int n10 = n9 + 1;
                double n11 = n8 - n;
                double n12 = 1.0 - n11;
                double n13 = n10 - n2;
                double n14 = 1.0 - n13;
                if (n11 > 0.0) {
                    n11 = 0.35 + 0.65 * n11;
                }
                if (n12 > 0.0) {
                    n12 = 0.35 + 0.65 * n12;
                }
                if (n13 > 0.0) {
                    n13 = 0.35 + 0.65 * n13;
                }
                if (n14 > 0.0) {
                    n14 = 0.35 + 0.65 * n14;
                }
                final double n15 = n11 * n13 * n6;
                final double n16 = 1.0 - n11 * n13 * n6;
                this.Rot[n7][n9] = (short)(n15 * n3 + n16 * this.Rot[n7][n9]);
                this.Gruen[n7][n9] = (short)(n15 * n4 + n16 * this.Gruen[n7][n9]);
                this.Blau[n7][n9] = (short)(n15 * n5 + n16 * this.Blau[n7][n9]);
                final double n17 = n12 * n13 * n6;
                final double n18 = 1.0 - n12 * n13 * n6;
                this.Rot[n8][n9] = (short)(n17 * n3 + n18 * this.Rot[n8][n9]);
                this.Gruen[n8][n9] = (short)(n17 * n4 + n18 * this.Gruen[n8][n9]);
                this.Blau[n8][n9] = (short)(n17 * n5 + n18 * this.Blau[n8][n9]);
                final double n19 = n11 * n14 * n6;
                final double n20 = 1.0 - n11 * n14 * n6;
                this.Rot[n7][n10] = (short)(n19 * n3 + n20 * this.Rot[n7][n10]);
                this.Gruen[n7][n10] = (short)(n19 * n4 + n20 * this.Gruen[n7][n10]);
                this.Blau[n7][n10] = (short)(n19 * n5 + n20 * this.Blau[n7][n10]);
                final double n21 = n12 * n14 * n6;
                final double n22 = 1.0 - n12 * n14 * n6;
                this.Rot[n8][n10] = (short)(n21 * n3 + n22 * this.Rot[n8][n10]);
                this.Gruen[n8][n10] = (short)(n21 * n4 + n22 * this.Gruen[n8][n10]);
                this.Blau[n8][n10] = (short)(n21 * n5 + n22 * this.Blau[n8][n10]);
                if (this.realtime) {
                    Mailwriter.this.osg.setColor(new Color((short)(this.constDown * this.Rot[n7][n9]), (short)(this.constDown * this.Gruen[n7][n9]), (short)(this.constDown * this.Blau[n7][n9])));
                    Mailwriter.this.osg.drawLine(n7, n9, n7, n9);
                    Mailwriter.this.osg.setColor(new Color((short)(this.constDown * this.Rot[n8][n9]), (short)(this.constDown * this.Gruen[n8][n9]), (short)(this.constDown * this.Blau[n8][n9])));
                    Mailwriter.this.osg.drawLine(n8, n9, n8, n9);
                    Mailwriter.this.osg.setColor(new Color((short)(this.constDown * this.Rot[n7][n10]), (short)(this.constDown * this.Gruen[n7][n10]), (short)(this.constDown * this.Blau[n7][n10])));
                    Mailwriter.this.osg.drawLine(n7, n10, n7, n10);
                    Mailwriter.this.osg.setColor(new Color((short)(this.constDown * this.Rot[n8][n10]), (short)(this.constDown * this.Gruen[n8][n10]), (short)(this.constDown * this.Blau[n8][n10])));
                    Mailwriter.this.osg.drawLine(n8, n10, n8, n10);
                }
            }
        }
    }
}
