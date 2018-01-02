import java.awt.LayoutManager;
import java.io.InputStream;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.applet.AudioClip;
import java.awt.event.ItemEvent;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.event.ItemListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BioCeleb extends Applet implements HotSpotListener, ItemListener, Loadable
{
    private int step;
    boolean showAd;
    HotSpot hs1;
    HotSpot hs2;
    HotSpot hs3;
    TextField tfname1;
    Choice Cday1;
    Choice Cmonth1;
    Choice Cyear1;
    Choice Cday2;
    Choice Ctype;
    Choice Cceleb;
    Checkbox cMale;
    Checkbox cFemale;
    CheckboxGroup cGroup;
    Image buff;
    Graphics gbuff;
    Dimension dim;
    boolean dateError1;
    boolean dateError2;
    PersonData ps1;
    PersonData ps2;
    Color fgcolor;
    Color bgcolor;
    Color shadowcolor;
    int emot;
    int intel;
    int phys;
    BioGraph graph;
    BioGraph2 graph2;
    Image bgimage;
    Image bt1;
    Image bt2;
    Image bt3;
    Image help1;
    Image help2;
    Image help3;
    Image logo;
    Image now1;
    Image now2;
    Image now3;
    Font titleFont;
    Font textFont;
    PersonData[] celebs;
    int numberOfPersons;
    PersonData[] top6;
    int gender;
    boolean modeForTwo;
    double mod1;
    double mod2;
    String[] str;
    boolean usePictures;
    Image pict;
    String pic;
    boolean picMode;
    int nos;
    RealMedia mt;
    boolean registered;
    boolean demo;
    String companyname;
    
    public BioCeleb() {
        this.step = 1;
        this.showAd = true;
        this.emot = 0;
        this.intel = 0;
        this.phys = 0;
        this.numberOfPersons = 16;
        this.gender = 1;
        this.modeForTwo = false;
        this.mod1 = 1.0;
        this.mod2 = 1.0;
        this.str = new String[30];
        this.usePictures = true;
        this.picMode = false;
        this.nos = 5;
        this.mt = new RealMedia(this);
        this.registered = false;
        this.demo = false;
        this.companyname = "RealApplets";
    }
    
    private void addAllInput() {
        this.add(this.tfname1);
        this.add(this.Cmonth1);
        this.add(this.Cday1);
        this.add(this.Cyear1);
        this.add(this.cMale);
        this.add(this.cFemale);
        this.add(this.Cceleb);
        this.tfname1.requestFocus();
    }
    
    private void changeScreens(final int n) {
        if (this.step == 1) {
            if (this.showAd) {
                this.remove(this.hs3);
            }
            this.repaint();
            if (this.graph != null) {
                this.remove(this.graph);
                this.remove(this.graph2);
            }
        }
        else if (this.step == 2) {
            this.addAllInput();
            this.repaint();
        }
        else if (this.step == 3) {
            if (this.Cceleb.getSelectedIndex() == 0) {
                this.modeForTwo = false;
                this.fetchData();
                if (!this.dateError1 && !this.dateError2) {
                    this.removeAllInput();
                    this.processData();
                    this.graph.reset();
                    this.graph.setProperties(this.top6[0].getName(), this.top6[0].getMatch(), 0);
                    this.graph.setProperties(this.top6[1].getName(), this.top6[1].getMatch(), 1);
                    this.graph.setProperties(this.top6[2].getName(), this.top6[2].getMatch(), 2);
                    this.graph.setProperties(this.top6[3].getName(), this.top6[3].getMatch(), 3);
                    this.graph.setProperties(this.top6[4].getName(), this.top6[4].getMatch(), 4);
                    this.graph.setProperties(this.top6[5].getName(), this.top6[5].getMatch(), 5);
                    this.add(this.graph);
                    this.graph.animate();
                    this.pic = this.top6[0].getPic();
                }
                this.repaint();
            }
            else {
                this.modeForTwo = true;
                this.processForTwo();
                this.pic = this.ps2.getPic();
            }
        }
        else if (this.step == 4) {
            if (this.graph != null) {
                this.remove(this.graph);
                this.remove(this.graph2);
            }
            this.paint(this.getGraphics());
            final MediaTracker mediaTracker = new MediaTracker(this);
            this.pict = this.getImage(this.getCodeBase(), "pictures/" + this.pic);
            System.out.println(this.pic);
            mediaTracker.addImage(this.pict, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
            this.picMode = true;
            this.repaint();
        }
        else if (this.step == 5) {
            this.add(this.hs3);
            this.hs3.repaint();
            this.repaint();
        }
    }
    
    public void destroy() {
    }
    
    private void emptyTop6() {
        for (int i = 0; i < 6; ++i) {
            this.top6[i].setMatch(-400);
        }
    }
    
    private void fetchData() {
        this.dateError1 = false;
        this.dateError2 = false;
        this.ps1 = new PersonData(this.tfname1.getText());
        try {
            this.ps1.setJulian(Integer.parseInt(this.Cmonth1.getSelectedItem()), Integer.parseInt(this.Cday1.getSelectedItem()), Integer.parseInt(this.Cyear1.getSelectedItem()));
        }
        catch (Exception ex) {
            System.out.println("Error date1: " + ex);
            this.dateError1 = true;
            --this.step;
            this.repaint();
        }
    }
    
    private void fetchDataForTwo() {
        this.dateError1 = false;
        this.dateError2 = false;
        this.ps1 = new PersonData(this.tfname1.getText());
        try {
            this.ps1.setJulian(Integer.parseInt(this.Cmonth1.getSelectedItem()), Integer.parseInt(this.Cday1.getSelectedItem()), Integer.parseInt(this.Cyear1.getSelectedItem()));
        }
        catch (Exception ex) {
            System.out.println("Error date1: " + ex);
            this.dateError1 = true;
            --this.step;
        }
        try {
            final String selectedItem = this.Cceleb.getSelectedItem();
            for (int i = 0; i < this.numberOfPersons; ++i) {
                if (this.celebs[i].getName().equals(selectedItem)) {
                    this.ps2 = this.celebs[i];
                    break;
                }
            }
        }
        catch (Exception ex2) {
            System.out.println("Error date2: " + ex2);
            this.dateError2 = true;
            if (!this.dateError1) {
                --this.step;
            }
        }
        this.emot = this.ps1.compareEmotional(this.ps2);
        this.intel = this.ps1.compareIntellectual(this.ps2);
        this.phys = this.ps1.comparePhysical(this.ps2);
    }
    
    private void fillCeleb(final int n) {
        this.Cceleb.removeAll();
        this.Cceleb.add(this.str[12]);
        for (int i = 0; i < this.numberOfPersons; ++i) {
            if (this.celebs[i].getGender() == n) {
                this.Cceleb.add(this.celebs[i].getName());
            }
        }
    }
    
    public void hotSpotEvent(final HotSpot hotSpot) {
        if (hotSpot == this.hs1) {
            ++this.step;
            if (this.step == this.nos + 1) {
                this.step = 1;
            }
            this.picMode = false;
            this.changeScreens(this.step);
        }
        else if (hotSpot == this.hs2) {
            this.popupHelp();
        }
        else if (hotSpot == this.hs3) {
            this.popupSite("Http://www.realapplets.com");
        }
    }
    
    public void init() {
        final Dimension size = this.getSize();
        this.buff = this.createImage(size.width, size.height);
        this.gbuff = this.buff.getGraphics();
        this.dim = this.getSize();
        final double n = 500.0;
        final double n2 = 350.0;
        this.mod1 = size.width / n;
        this.mod2 = size.height / n2;
        System.out.println("Mod is:" + this.mod1 + " " + this.mod2);
        if (this.usePictures) {
            this.nos = 5;
        }
        this.loadMedia();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        int n = 0;
        if (itemEvent.getSource() == this.cMale) {
            n = 1;
        }
        else if (itemEvent.getSource() == this.cFemale) {
            n = 2;
        }
        this.fillCeleb(n);
        System.out.println("test " + n);
    }
    
    public AudioClip loadAudio(final String s) {
        return this.getAudioClip(this.getCodeBase(), s);
    }
    
    public Image loadImage(final String s) {
        return this.getImage(this.getCodeBase(), s);
    }
    
    private void loadMedia() {
        this.mt.addImage("pictures/bg.gif", 0);
        this.mt.addImage("pictures/logo.gif", 0);
        this.mt.addImage("pictures/bt1c.gif", 0);
        this.mt.addImage("pictures/bt2c.gif", 0);
        this.mt.addImage("pictures/bt3c.gif", 0);
        this.mt.addImage("pictures/help1.gif", 0);
        this.mt.addImage("pictures/help2.gif", 0);
        this.mt.addImage("pictures/help3.gif", 0);
        if (this.showAd) {
            this.mt.addImage("pictures/now1.gif", 0);
            this.mt.addImage("pictures/now2.gif", 0);
            this.mt.addImage("pictures/now3.gif", 0);
        }
        try {
            this.mt.load();
        }
        catch (Exception ex) {}
    }
    
    private void loadPersons() {
        this.top6 = new PersonData[6];
        for (int i = 0; i < 6; ++i) {
            this.top6[i] = new PersonData("Top" + i);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.readFile(String.valueOf(String.valueOf(this.getCodeBase())) + "data1c.txt"), "\n");
        this.numberOfPersons = Integer.parseInt(stringTokenizer.nextToken());
        this.celebs = new PersonData[this.numberOfPersons];
        for (int j = 0; j < this.numberOfPersons; ++j) {
            System.out.println("Trying " + j);
            final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken());
            final String nextToken = stringTokenizer2.nextToken();
            final String nextToken2 = stringTokenizer2.nextToken();
            System.out.println(nextToken2);
            final String nextToken3 = stringTokenizer2.nextToken();
            System.out.println(nextToken3);
            String string = "";
            while (stringTokenizer2.hasMoreTokens()) {
                string = String.valueOf(string) + stringTokenizer2.nextToken() + " ";
                System.out.println(string);
            }
            (this.celebs[j] = new PersonData(string)).setPic(nextToken3);
            try {
                if (nextToken2.equals("male")) {
                    this.celebs[j].setGender(1);
                }
                else {
                    this.celebs[j].setGender(2);
                }
                this.celebs[j].setJulian(Integer.parseInt(nextToken.substring(3, 5)), Integer.parseInt(nextToken.substring(0, 2)), Integer.parseInt(nextToken.substring(6, 10)));
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        this.emptyTop6();
    }
    
    private void loadStrings() {
        for (int i = 0; i < 26; ++i) {
            this.str[i] = this.getParameter("str" + (i + 1));
        }
    }
    
    private void makeChoiceArray(final int n, final int n2, final int n3) {
        for (int i = n; i <= n2; ++i) {
            final String value = String.valueOf(i);
            if (n3 == 1) {
                this.Cday1.add(value);
            }
            else if (n3 == 2) {
                this.Cmonth1.add(value);
            }
            if (n3 == 3) {
                this.Cyear1.add(value);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.gbuff.setColor(new Color(0, 0, 122));
        this.gbuff.fillRect(0, 0, this.dim.width, this.dim.height);
        this.gbuff.setFont(new Font("Arial", 1, 12));
        if (!this.mt.isLoaded()) {
            this.gbuff.setColor(Color.white);
            this.gbuff.drawString("Loading file " + this.mt.getCurrent() + " of " + this.mt.getTotalFiles(), 20, 20);
            this.gbuff.drawRect(20, 60, 200, 20);
            this.gbuff.setColor(Color.white);
            this.gbuff.fillRect(21, 61, 2 * this.mt.getPercent() - 2, 19);
            this.gbuff.setColor(Color.blue);
            this.gbuff.drawString(String.valueOf(this.mt.getPercent()) + " %", 70, 73);
            this.gbuff.setColor(Color.white);
            this.gbuff.drawString("Biorythm Celebrities", 20, 150);
            if (!this.registered) {
                this.gbuff.drawString("Limited Unregistered Version (V1.0)", 20, 170);
                this.gbuff.drawString("Register now at Http://www.realapplets.com", 20, 190);
                this.gbuff.drawString("Programming by Bavo Bruylandt", 300, 70);
            }
            else {
                this.gbuff.drawString("Registrado a: " + this.companyname, 20, 170);
                if (this.demo) {
                    this.gbuff.drawString("DEMO of Registered Version (V1.1)", 20, 190);
                    this.gbuff.setFont(new Font("Arial", 2, 12));
                    this.gbuff.drawString("It is not allowed to link to this page", 20, 230);
                }
            }
        }
        else {
            this.gbuff.setFont(this.textFont);
            this.gbuff.setColor(this.bgcolor);
            this.gbuff.fillRect(0, 0, this.dim.width, this.dim.height);
            this.gbuff.drawImage(this.bgimage, 0, 0, this.bgimage.getWidth(this), this.bgimage.getHeight(this), this);
            this.gbuff.setColor(this.fgcolor);
            if (this.step == 1) {
                this.gbuff.setFont(this.titleFont);
                this.gbuff.drawImage(this.logo, (this.dim.width - this.logo.getWidth(this)) / 2, (this.dim.height - this.logo.getHeight(this)) / 2, this);
                this.gbuff.setFont(this.textFont);
                this.gbuff.setColor(this.fgcolor);
                this.gbuff.drawString(String.valueOf(this.step) + "/" + this.nos, 420, 315);
            }
            else if (this.step == 2) {
                this.gbuff.setColor(this.shadowcolor);
                if (this.dateError1) {
                    this.gbuff.drawString(this.str[6], 101, 271);
                }
                this.gbuff.setColor(this.fgcolor);
                this.gbuff.drawString(this.str[0], 45, 75);
                this.gbuff.drawString(this.str[1], 45, 125);
                this.gbuff.drawString(this.str[2], 45, 175);
                this.gbuff.drawString(this.str[3], 45, 225);
                if (this.dateError1) {
                    this.gbuff.drawString(this.str[6], 100, 270);
                }
                this.gbuff.drawString(String.valueOf(this.step) + "/" + this.nos, 420, 315);
            }
            else if (this.step == 3) {
                this.gbuff.setColor(this.fgcolor);
                if (this.modeForTwo) {
                    this.gbuff.drawString(String.valueOf(this.str[4]) + " " + this.ps1.getName() + " & " + this.ps2.getName() + ":", 39, 29);
                }
                else {
                    this.gbuff.drawString(String.valueOf(this.str[5]) + " " + this.ps1.getName(), 40, 30);
                }
                this.gbuff.drawString(String.valueOf(this.step) + "/" + this.nos, 420, 315);
            }
            else if (this.step == 4) {
                if (this.picMode) {
                    this.gbuff.setColor(this.fgcolor);
                    this.gbuff.drawString(String.valueOf(this.step) + "/" + this.nos, 420, 315);
                    final int width = this.pict.getWidth(this);
                    final int height = this.pict.getHeight(this);
                    double n = 1.0;
                    if (height > 250) {
                        n = 250.0 / height;
                    }
                    else if (height > 400) {
                        n = 400.0 / width;
                    }
                    final int n2 = (int)(width * n);
                    final int n3 = (int)(height * n);
                    this.gbuff.setColor(this.shadowcolor);
                    this.gbuff.fillRect((this.dim.width - n2) / 2 + 4, (this.dim.height - 50 - n3) / 2 + 4, n2, n3);
                    this.gbuff.drawImage(this.pict, (this.dim.width - n2) / 2, (this.dim.height - 50 - n3) / 2, n2, n3, this);
                }
                else {
                    this.gbuff.setColor(this.fgcolor);
                    this.gbuff.drawString(String.valueOf(this.step) + "/" + this.nos, 420, 315);
                    this.gbuff.drawString(this.str[14], 200, 200);
                }
            }
            else if (this.step == 5) {
                this.gbuff.drawString("Created by:", 100, 80);
                this.gbuff.drawString("Http://www.RealApplets.com", 100, 100);
                this.gbuff.drawString("Entertain your visitors!", 100, 120);
                this.gbuff.drawString("Did you like this free applet?", 100, 160);
                this.gbuff.drawString("Go to RealApplets for your own version", 100, 180);
                this.gbuff.drawString("(registered version does not show this message)", 100, 200);
                this.gbuff.drawString("5 / " + this.nos, 420, 315);
            }
        }
        graphics.drawImage(this.buff, 0, 0, (int)(this.buff.getWidth(this) * this.mod1), (int)(this.buff.getHeight(this) * this.mod2), this);
    }
    
    private void popupHelp() {
        final Popup popup = new Popup(String.valueOf(String.valueOf(this.getCodeBase())) + "read.txt");
        popup.setVisible(true);
        popup.setup();
    }
    
    private void popupSite(final String s) {
        try {
            this.getAppletContext().showDocument(new URL(s), "_blank");
        }
        catch (Exception ex) {}
    }
    
    private void processData() {
        this.emptyTop6();
        if (this.cMale.getState()) {
            this.gender = 1;
        }
        else {
            this.gender = 2;
        }
        for (int i = 0; i < this.numberOfPersons; ++i) {
            this.celebs[i].compareAll(this.ps1);
            final int match = this.celebs[i].getMatch();
            if (this.celebs[i].getGender() == this.gender) {
                if (match >= this.top6[0].getMatch()) {
                    for (int j = 0; j < 5; ++j) {
                        this.top6[5 - j].setName(this.top6[5 - (j + 1)].getName());
                        this.top6[5 - j].setGender(this.top6[5 - (j + 1)].getGender());
                        this.top6[5 - j].setMatch(this.top6[5 - (j + 1)].getMatch());
                    }
                    this.top6[0].setPic(this.celebs[i].getPic());
                    this.top6[0].setName(this.celebs[i].getName());
                    this.top6[0].setGender(this.celebs[i].getGender());
                    this.top6[0].setMatch(match);
                }
                else if (match >= this.top6[1].getMatch() && match < this.top6[0].getMatch()) {
                    for (int k = 0; k < 4; ++k) {
                        this.top6[5 - k].setName(this.top6[5 - (k + 1)].getName());
                        this.top6[5 - k].setGender(this.top6[5 - (k + 1)].getGender());
                        this.top6[5 - k].setMatch(this.top6[5 - (k + 1)].getMatch());
                    }
                    this.top6[1].setName(this.celebs[i].getName());
                    this.top6[1].setGender(this.celebs[i].getGender());
                    this.top6[1].setMatch(match);
                }
                else if (match >= this.top6[2].getMatch() && match < this.top6[1].getMatch()) {
                    for (int l = 0; l < 3; ++l) {
                        this.top6[5 - l].setName(this.top6[5 - (l + 1)].getName());
                        this.top6[5 - l].setGender(this.top6[5 - (l + 1)].getGender());
                        this.top6[5 - l].setMatch(this.top6[5 - (l + 1)].getMatch());
                    }
                    this.top6[2].setName(this.celebs[i].getName());
                    this.top6[2].setGender(this.celebs[i].getGender());
                    this.top6[2].setMatch(match);
                }
                else if (match >= this.top6[3].getMatch() && match < this.top6[2].getMatch()) {
                    for (int n = 0; n < 2; ++n) {
                        this.top6[5 - n].setName(this.top6[5 - (n + 1)].getName());
                        this.top6[5 - n].setGender(this.top6[5 - (n + 1)].getGender());
                        this.top6[5 - n].setMatch(this.top6[5 - (n + 1)].getMatch());
                    }
                    this.top6[3].setName(this.celebs[i].getName());
                    this.top6[3].setGender(this.celebs[i].getGender());
                    this.top6[3].setMatch(match);
                }
                else if (match >= this.top6[4].getMatch() && match < this.top6[3].getMatch()) {
                    for (int n2 = 0; n2 < 1; ++n2) {
                        this.top6[5 - n2].setName(this.top6[5 - (n2 + 1)].getName());
                        this.top6[5 - n2].setGender(this.top6[5 - (n2 + 1)].getGender());
                        this.top6[5 - n2].setMatch(this.top6[5 - (n2 + 1)].getMatch());
                    }
                    this.top6[4].setName(this.celebs[i].getName());
                    this.top6[4].setGender(this.celebs[i].getGender());
                    this.top6[4].setMatch(match);
                }
                else if (match >= this.top6[5].getMatch() && match < this.top6[4].getMatch()) {
                    this.top6[5].setName(this.celebs[i].getName());
                    this.top6[5].setGender(this.celebs[i].getGender());
                    this.top6[5].setMatch(match);
                }
            }
        }
    }
    
    private void processForTwo() {
        this.fetchDataForTwo();
        if (!this.dateError1) {
            this.removeAllInput();
            this.processData();
            this.graph2.reset();
            this.graph2.setProperties(this.emot, this.intel, this.phys);
            this.add(this.graph2);
            this.graph2.animate();
        }
        this.repaint();
    }
    
    private String readFile(final String s) {
        int i = 0;
        String string = "";
        try {
            final InputStream openStream = new URL(s).openStream();
            while (i != -1) {
                i = openStream.read();
                if (i != -1 && (char)i != '\r') {
                    string = String.valueOf(string) + (char)i;
                }
            }
            openStream.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Corrupted data file or file is missing");
        }
        return string;
    }
    
    private void removeAllInput() {
        this.remove(this.tfname1);
        this.remove(this.Cday1);
        this.remove(this.cFemale);
        this.remove(this.cMale);
        this.remove(this.Cmonth1);
        this.remove(this.Cyear1);
        this.remove(this.Cceleb);
    }
    
    public void start() {
        (this.graph = new BioGraph(this, 410, 240, this.mod1, this.mod2)).reset();
        this.graph.setBounds((int)(20.0 * this.mod1), (int)(40.0 * this.mod2), (int)(410.0 * this.mod1), (int)(240.0 * this.mod2));
        this.add(this.graph);
        this.remove(this.graph);
        (this.graph2 = new BioGraph2(this, 410, 230, this.mod1, this.mod2, 1)).reset();
        this.graph2.setBounds((int)(20.0 * this.mod1), (int)(50.0 * this.mod2), (int)(410.0 * this.mod1), (int)(230.0 * this.mod2));
        this.add(this.graph2);
        this.remove(this.graph2);
    }
    
    public void startUp() {
        this.bgimage = this.mt.get("pictures/bg.gif");
        this.logo = this.mt.get("pictures/logo.gif");
        this.bt1 = this.mt.get("pictures/bt1c.gif");
        this.bt2 = this.mt.get("pictures/bt2c.gif");
        this.bt3 = this.mt.get("pictures/bt3c.gif");
        this.help1 = this.mt.get("pictures/help1.gif");
        this.help2 = this.mt.get("pictures/help2.gif");
        this.help3 = this.mt.get("pictures/help3.gif");
        this.now1 = this.mt.get("pictures/now1.gif");
        this.now2 = this.mt.get("pictures/now2.gif");
        this.now3 = this.mt.get("pictures/now3.gif");
        this.loadStrings();
        this.loadPersons();
        this.setLayout(null);
        final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("bgcolor"), ",");
        this.bgcolor = new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("fgcolor"), ",");
        this.fgcolor = new Color(Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()));
        final StringTokenizer stringTokenizer3 = new StringTokenizer(this.getParameter("shadowcolor"), ",");
        this.shadowcolor = new Color(Integer.parseInt(stringTokenizer3.nextToken()), Integer.parseInt(stringTokenizer3.nextToken()), Integer.parseInt(stringTokenizer3.nextToken()));
        this.setBackground(this.bgcolor);
        this.setForeground(this.fgcolor);
        (this.tfname1 = new TextField("Name", (int)(30.0 * this.mod1))).setBounds((int)(250.0 * this.mod1), (int)(60.0 * this.mod2), (int)(100.0 * this.mod1), (int)(25.0 * this.mod2));
        (this.Cmonth1 = new Choice()).setBounds((int)(250.0 * this.mod1), (int)(110.0 * this.mod2), (int)(60.0 * this.mod1), (int)(20.0 * this.mod2));
        (this.Cday1 = new Choice()).setBounds((int)(320.0 * this.mod1), (int)(110.0 * this.mod2), (int)(60.0 * this.mod1), (int)(20.0 * this.mod2));
        this.Cday1.add(this.str[9]);
        this.Cmonth1.add(this.str[8]);
        (this.Cceleb = new Choice()).setBounds((int)(250.0 * this.mod1), (int)(210.0 * this.mod2), (int)(120.0 * this.mod1), (int)(20.0 * this.mod2));
        this.cGroup = new CheckboxGroup();
        this.cMale = new Checkbox(this.str[10], true, this.cGroup);
        this.cFemale = new Checkbox(this.str[11], false, this.cGroup);
        this.cMale.setBounds((int)(250.0 * this.mod1), (int)(160.0 * this.mod2), (int)(60.0 * this.mod1), (int)(20.0 * this.mod2));
        this.cFemale.setBounds((int)(350.0 * this.mod1), (int)(160.0 * this.mod2), (int)(60.0 * this.mod1), (int)(20.0 * this.mod2));
        this.cMale.addItemListener(this);
        this.cFemale.addItemListener(this);
        this.cMale.setBackground(this.bgcolor);
        this.cMale.setForeground(this.fgcolor);
        this.cFemale.setBackground(this.bgcolor);
        this.cFemale.setForeground(this.fgcolor);
        (this.Cyear1 = new Choice()).setBounds((int)(390.0 * this.mod1), (int)(110.0 * this.mod2), (int)(60.0 * this.mod1), (int)(20.0 * this.mod2));
        this.Cyear1.setBackground(Color.white);
        this.Cyear1.setForeground(Color.black);
        this.Cday1.setBackground(Color.white);
        this.Cday1.setForeground(Color.black);
        this.Cmonth1.setBackground(Color.white);
        this.Cmonth1.setForeground(Color.black);
        this.tfname1.setBackground(Color.white);
        this.tfname1.setForeground(Color.black);
        this.Cyear1.add(this.str[7]);
        this.makeChoiceArray(1, 31, 1);
        this.makeChoiceArray(1, 12, 2);
        this.makeChoiceArray(1920, 2050, 3);
        this.fillCeleb(1);
        (this.hs1 = new HotSpot(this)).setBounds((int)(220.0 * this.mod1), (int)(295.0 * this.mod2), (int)(52.0 * this.mod1), (int)(30.0 * this.mod2));
        this.hs1.noText(true);
        this.hs1.setImage(this.bt1, 1);
        this.hs1.setImage(this.bt2, 2);
        this.hs1.setImage(this.bt3, 3);
        this.add(this.hs1);
        (this.hs2 = new HotSpot(this)).setBounds(this.dim.width - 80, 20, 52, 25);
        this.hs2.noText(true);
        this.hs2.setImage(this.help1, 1);
        this.hs2.setImage(this.help2, 2);
        this.hs2.setImage(this.help3, 3);
        this.add(this.hs2);
        this.hs3 = new HotSpot(this);
        if (this.showAd) {
            this.hs3.setBounds(100, 240, 100, 25);
            this.hs3.noText(true);
            this.hs3.setImage(this.now1, 1);
            this.hs3.setImage(this.now2, 2);
            this.hs3.setImage(this.now3, 3);
        }
        this.titleFont = new Font("Helvetica", 1, (int)(16.0 * this.mod1));
        this.textFont = new Font("Helvetica", 1, (int)(12.0 * this.mod1));
    }
    
    public void stop() {
        this.graph.kill();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
