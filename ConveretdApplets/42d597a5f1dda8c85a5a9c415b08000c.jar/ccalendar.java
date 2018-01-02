import java.net.URL;
import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;
import java.awt.Cursor;
import netscape.javascript.JSObject;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Button;
import java.util.Calendar;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ccalendar extends Applet implements Runnable, MouseListener, MouseMotionListener, ActionListener
{
    private Thread m_ccalendar;
    private Graphics Off_Graphic;
    private Image Off_Image;
    private Graphics m_Graphics;
    private Image[] m_Images;
    private boolean[] b_load;
    private int iNbImg;
    private int[] bImgIndex;
    private String SListImg;
    private int m_nImgWidth;
    private int m_nImgHeight;
    private final int iNbImage = 12;
    private final String SParamImages = "lst_img";
    private boolean bImage;
    private Calendar CurrentDate;
    private int iCasecx;
    private int iCasecy;
    private int[] iJours;
    private final int iNbcasex = 7;
    private final int iNbcasey = 7;
    private int yMois;
    private int yCase;
    private int yComment;
    private int nlc;
    private boolean bAffiche;
    private final int iBord = 4;
    private String sStrict;
    private FlecheButton BpPrev;
    private FlecheButton BpNext;
    private Button BpAbout1;
    private Button BpAbout2;
    private Color colCase;
    private Color colFull;
    private Color colFond;
    private Color colComment;
    private Color colVide;
    private String sField;
    private String sForm;
    private String sFormat;
    private MediaTracker tracker;
    private CSetAvail sa;
    private String savail;
    private int[] dmonths;
    private String jvf;
    private final String[] sMonth;
    private final String[] sDay;
    
    public ccalendar() {
        this.bImage = false;
        this.nlc = 1;
        this.bAffiche = false;
        this.BpPrev = new FlecheButton(-1);
        this.BpNext = new FlecheButton(1);
        this.BpAbout1 = new Button("?");
        this.BpAbout2 = new Button("?");
        this.dmonths = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.sMonth = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        this.sDay = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
    }
    
    public void init() {
        this.CurrentDate = Calendar.getInstance();
        this.SListImg = this.getParameter("lst_img");
        this.iJours = new int[49];
        this.bImgIndex = new int[12];
        this.sa = new CSetAvail();
        this.sa.base = this.getCodeBase();
        this.sStrict = this.getParameter("STRICT");
        final String parameter = this.getParameter("NLC");
        if (parameter != null) {
            this.nlc = Integer.valueOf(parameter, 10);
        }
        final String parameter2 = this.getParameter("COLOR_FULL");
        if (parameter2 == null) {
            this.colComment = Color.lightGray.brighter();
        }
        else {
            this.colFull = new Color(Integer.valueOf(parameter2, 16));
            this.sa.bschedule = true;
        }
        final String parameter3 = this.getParameter("COLOR_COMMENT");
        if (parameter3 == null) {
            this.colComment = Color.lightGray;
        }
        else {
            this.colComment = new Color(Integer.valueOf(parameter3, 16));
        }
        final String parameter4 = this.getParameter("COLOR_CASE");
        if (parameter4 == null) {
            this.colCase = Color.lightGray.brighter();
        }
        else {
            this.colCase = new Color(Integer.valueOf(parameter4, 16));
        }
        final String parameter5 = this.getParameter("COLOR_FOND");
        if (parameter5 == null) {
            this.colFond = new Color(13421772);
        }
        else {
            this.colFond = new Color(Integer.valueOf(parameter5, 16));
        }
        final String parameter6 = this.getParameter("COLOR_EMPTY");
        if (parameter6 == null) {
            this.colVide = new Color(11184810);
        }
        else {
            this.colVide = new Color(Integer.valueOf(parameter6, 16));
        }
        this.sField = this.getParameter("field");
        this.sForm = this.getParameter("form");
        final String parameter7 = this.getParameter("format");
        if (parameter7 == null) {
            this.sFormat = "d/m/a";
        }
        else {
            this.sFormat = parameter7;
        }
        this.jvf = this.getParameter("jvf");
        for (int i = 0; i < 7; ++i) {
            final String parameter8 = this.getParameter("day" + (i + 1));
            if (parameter8 != null) {
                this.sDay[i] = parameter8;
            }
        }
        for (int j = 0; j < 12; ++j) {
            final String parameter9 = this.getParameter("month" + (j + 1));
            if (parameter9 != null) {
                this.sMonth[j] = parameter9;
            }
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.bAffiche) {
            graphics.drawImage(this.Off_Image, 0, 0, this);
            return;
        }
        graphics.drawString("Loading...", 0, this.getSize().height / 2);
        this.initDisplay();
        this.repaint();
    }
    
    protected void initDisplay() {
        this.m_Graphics = this.getGraphics();
        final FontMetrics fontMetrics = this.m_Graphics.getFontMetrics();
        this.tracker = new MediaTracker(this);
        if (this.SListImg != null && this.iNbImg == 0) {
            this.m_Images = new Image[12];
            int n = 0;
            int i = 0;
            while (i != -1) {
                i = this.SListImg.indexOf(59, n);
                String s;
                if (i == -1) {
                    s = this.SListImg.substring(n);
                }
                else {
                    s = this.SListImg.substring(n, i);
                }
                this.m_Images[this.iNbImg] = this.getImage(this.getDocumentBase(), s);
                this.bImgIndex[this.iNbImg] = this.iNbImg;
                this.tracker.addImage(this.m_Images[this.iNbImg], this.iNbImg);
                n = i + 1;
                ++this.iNbImg;
            }
            this.b_load = new boolean[this.iNbImg];
            for (int j = 0; j < this.iNbImg; ++j) {
                this.b_load[j] = false;
            }
            for (int k = this.iNbImg, n2 = 0; k < 12; this.bImgIndex[k++] = n2, n2 = (n2 + 1) % this.iNbImg) {}
            try {
                this.tracker.waitForID(this.bImgIndex[this.CurrentDate.get(2)]);
                this.b_load[this.bImgIndex[this.CurrentDate.get(2)]] = true;
            }
            catch (InterruptedException ex) {
                this.m_Graphics.drawString("Error loading image " + this.bImgIndex[this.CurrentDate.get(2)], 0, 0);
                return;
            }
            if (this.m_nImgHeight < this.m_Images[this.bImgIndex[this.CurrentDate.get(2)]].getHeight(this)) {
                this.m_nImgHeight = this.m_Images[this.bImgIndex[this.CurrentDate.get(2)]].getHeight(this);
            }
            this.bImage = true;
        }
        else {
            this.m_nImgHeight = 0;
        }
        this.Off_Image = this.createImage(this.getSize().width, this.getSize().height);
        this.Off_Graphic = this.Off_Image.getGraphics();
        this.yCase = this.m_nImgHeight + 8 + fontMetrics.getHeight() + 4;
        this.yComment = this.getSize().height - (fontMetrics.getHeight() + 4) * this.nlc;
        this.iCasecx = this.getSize().width / 7;
        this.iCasecy = (this.yComment - this.yCase) / 7;
        this.yCase = this.yComment - this.iCasecy * 7;
        this.yMois = this.yCase - (fontMetrics.getHeight() + 4);
        final int n3 = this.yCase - this.yMois;
        for (int l = 0; l < 7; ++l) {
            this.iJours[l] = -1;
        }
        this.InitMois();
        this.Affiche(this.Off_Graphic);
        this.bAffiche = true;
        this.BpPrev.setBounds(0, this.yMois, n3, n3);
        this.BpPrev.setActionCommand("P");
        this.BpPrev.addActionListener(this);
        this.BpPrev.setBackground(this.colVide);
        this.BpNext.setBounds(this.getSize().width - n3, this.yMois, n3, n3);
        this.BpNext.setActionCommand("N");
        this.BpNext.addActionListener(this);
        this.BpNext.setBackground(this.colVide);
        this.BpAbout1.setBounds(0, this.yComment, n3, n3);
        this.BpAbout1.setActionCommand("?");
        this.BpAbout1.addActionListener(this);
        this.BpAbout1.setBackground(this.colVide);
        this.BpAbout2.setBounds(this.getSize().width - n3, this.yComment, n3, n3);
        this.BpAbout2.setActionCommand("?");
        this.BpAbout2.addActionListener(this);
        this.BpAbout2.setBackground(this.colVide);
        this.setLayout(null);
        this.add(this.BpPrev);
        this.add(this.BpNext);
        this.add(this.BpAbout1);
        this.add(this.BpAbout2);
        if (this.bImage) {
            (this.m_ccalendar = new Thread(this)).start();
        }
    }
    
    public void run() {
        for (int i = 0; i < this.iNbImg; ++i) {
            if (!this.b_load[i]) {
                try {
                    this.tracker.waitForID(i);
                    this.b_load[i] = true;
                    if (i == this.bImgIndex[this.CurrentDate.get(2)]) {
                        this.Affiche(this.Off_Graphic);
                        this.repaint();
                    }
                }
                catch (Exception ex) {
                    System.out.println("Error loading img n°" + i);
                }
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (!this.bAffiche) {
            return;
        }
        if (y < this.yCase || y >= this.yComment) {
            return;
        }
        final int n = this.iJours[x / this.iCasecx + (y - this.yCase) / this.iCasecy * 7];
        if (n <= 0) {
            return;
        }
        this.CurrentDate.set(5, n);
        try {
            final JSObject window = JSObject.getWindow((Applet)this);
            final JSObject jsObject = (JSObject)window.getMember("document");
            String s = "";
            if (this.sField != null && this.sForm != null) {
                final JSObject jsObject2 = (JSObject)((JSObject)jsObject.getMember(this.sForm)).getMember(this.sField);
                if (this.sStrict != null && this.sa.bschedule && this.savail.charAt(x / this.iCasecx + (y - this.yCase) / this.iCasecy * 7) == '1') {
                    s = this.sStrict;
                }
                else {
                    for (int i = 0; i < this.sFormat.length(); ++i) {
                        switch (this.sFormat.charAt(i)) {
                            case 'd': {
                                if (this.CurrentDate.get(5) < 10) {
                                    s = String.valueOf(s) + "0";
                                }
                                s = String.valueOf(s) + this.CurrentDate.get(5);
                                break;
                            }
                            case 'm': {
                                if (this.CurrentDate.get(2) < 9) {
                                    s = String.valueOf(s) + "0";
                                }
                                s = String.valueOf(s) + (this.CurrentDate.get(2) + 1);
                                break;
                            }
                            case 'a':
                            case 'y': {
                                s = String.valueOf(s) + this.CurrentDate.get(1);
                                break;
                            }
                            default: {
                                s = String.valueOf(s) + this.sFormat.charAt(i);
                                break;
                            }
                        }
                    }
                }
                jsObject2.setMember("value", (Object)s);
            }
            if (this.jvf != null) {
                window.eval(this.jvf);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        int n = -1;
        int n2 = 0;
        if (!this.bAffiche) {
            return;
        }
        if (y > this.yCase && y < this.yComment) {
            n = this.iJours[x / this.iCasecx + (y - this.yCase) / this.iCasecy * 7];
            if (n > 0) {
                n2 = 12;
            }
        }
        this.setCursor(new Cursor(n2));
        this.AfficheComment(this.m_Graphics, n);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void Affiche(final Graphics graphics) {
        graphics.setColor(this.colFond);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        if (this.bImage) {
            if (!this.b_load[this.bImgIndex[this.CurrentDate.get(2)]]) {
                graphics.setColor(Color.black);
                graphics.drawString("Wait for image...", (this.getSize().width - this.m_nImgWidth) / 2, this.yMois / 2);
            }
            else {
                this.m_nImgWidth = this.m_Images[this.bImgIndex[this.CurrentDate.get(2)]].getWidth(this);
                graphics.drawImage(this.m_Images[this.bImgIndex[this.CurrentDate.get(2)]], (this.getSize().width - this.m_nImgWidth) / 2, 4, null);
            }
        }
        this.AfficheMois(graphics);
        for (int i = 0; i < 49; ++i) {
            this.AfficheCase(graphics, i);
        }
        this.AfficheComment(graphics, 0);
    }
    
    public void InitMois() {
        int n = 1;
        final int value = this.CurrentDate.get(2);
        int n2 = this.dmonths[value];
        final int value2 = this.CurrentDate.get(1);
        final GregorianCalendar gregorianCalendar = new GregorianCalendar(value2, value, 1);
        gregorianCalendar.setFirstDayOfWeek(1);
        if (n2 == 28 && value2 % 4 == 0 && (value2 % 100 != 0 || value2 % 400 == 0)) {
            ++n2;
        }
        gregorianCalendar.set(2, value);
        gregorianCalendar.set(5, 1);
        for (int i = 0; i < 42; ++i) {
            if (i < gregorianCalendar.get(7) - 1 || n > n2) {
                this.iJours[i + 7] = 0;
            }
            else {
                this.iJours[i + 7] = n++;
            }
        }
        if (this.sa.bschedule) {
            this.savail = this.sa.returnString(this.iJours, 49, this.CurrentDate.get(1), value);
        }
    }
    
    public void AfficheCase(final Graphics graphics, final int n) {
        final int n2 = n % 7 * this.iCasecx;
        final int n3 = this.yCase + n / 7 * this.iCasecy;
        if (this.iJours[n] > 0) {
            if (this.sa.bschedule && this.savail.charAt(n) == '1') {
                graphics.setColor(this.colFull);
            }
            else {
                graphics.setColor(this.colCase);
            }
        }
        else {
            graphics.setColor(this.colVide);
        }
        graphics.fillRect(n2, n3, this.iCasecx - 1, this.iCasecy - 1);
        if (this.iJours[n] != 0) {
            String s;
            if (this.iJours[n] == -1) {
                s = String.valueOf(this.sDay[n].charAt(0));
            }
            else {
                s = String.valueOf(this.iJours[n]);
            }
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            if (this.sa.isEvntThisDay(this.CurrentDate.get(2), this.iJours[n])) {
                graphics.setColor(Color.red);
            }
            else {
                graphics.setColor(Color.black);
            }
            graphics.drawString(s, n2 + (this.iCasecx - fontMetrics.stringWidth(s)) / 2, n3 + (this.iCasecy + fontMetrics.getHeight()) / 2);
        }
    }
    
    public void AfficheMois(final Graphics graphics) {
        final String string = String.valueOf(this.sMonth[this.CurrentDate.get(2)]) + " " + this.CurrentDate.get(1);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(this.colComment);
        graphics.fillRect(0, this.yMois, this.getSize().width, this.yCase - this.yMois);
        graphics.setColor(new Color(0));
        graphics.drawString(string, (this.getSize().width - fontMetrics.stringWidth(string)) / 2, this.yMois + fontMetrics.getHeight());
    }
    
    public void AfficheComment(final Graphics graphics, final int n) {
        String s = "";
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(this.colComment);
        graphics.fillRect(0, this.yComment, this.getSize().width, this.getSize().height - this.yComment);
        if (n > 0) {
            this.CurrentDate.set(5, n);
            graphics.setColor(new Color(0));
            if (this.sa.isEvntThisDay(this.CurrentDate.get(2), n)) {
                this.TextRect(graphics, this.sa.evntToDay(this.CurrentDate.get(2), n), 4, this.yComment, this.getSize().width - 8);
                return;
            }
            for (int i = 0; i < this.sFormat.length(); ++i) {
                if (s.length() > 0) {
                    s = String.valueOf(s) + " ";
                }
                switch (this.sFormat.charAt(i)) {
                    case 'd': {
                        s = String.valueOf(s) + this.sDay[this.CurrentDate.get(7) - 1] + " " + n;
                        break;
                    }
                    case 'm': {
                        s = String.valueOf(s) + this.sMonth[this.CurrentDate.get(2)];
                        break;
                    }
                    case 'a':
                    case 'y': {
                        s = String.valueOf(s) + this.CurrentDate.get(1);
                        break;
                    }
                }
            }
            graphics.drawString(s, (this.getSize().width - fontMetrics.stringWidth(s)) / 2, this.yComment + fontMetrics.getHeight());
        }
    }
    
    void TextRect(final Graphics graphics, final String s, final int n, int n2, final int n3) {
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        while (n4 + n5 < s.length()) {
            n7 += fontMetrics.charWidth(s.charAt(n4 + n5));
            if (n7 > n3) {
                graphics.drawString(s.substring(n4, n6), n, n2 + height);
                n4 = n6 + 1;
                n7 = 0;
                n5 = 0;
                n2 += height;
            }
            else {
                if (s.charAt(n4 + n5) == ' ') {
                    n6 = n4 + n5;
                }
                ++n5;
            }
        }
        graphics.drawString(s.substring(n4), n, n2 + height);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand().charAt(0)) {
            case '?': {
                try {
                    this.getAppletContext().showDocument(new URL("http://java.arcadevillage.com"), "_top");
                }
                catch (Exception ex) {}
            }
            case 'N': {
                this.CurrentDate.set(5, 1);
                this.CurrentDate.add(2, 1);
                this.InitMois();
                this.Affiche(this.Off_Graphic);
                this.repaint();
            }
            case 'P': {
                this.CurrentDate.set(5, 1);
                if (this.CurrentDate.get(2) > 0) {
                    this.CurrentDate.add(2, -1);
                }
                else {
                    this.CurrentDate.set(2, 11);
                    this.CurrentDate.add(1, -1);
                }
                this.InitMois();
                this.Affiche(this.Off_Graphic);
                this.repaint();
            }
            default: {}
        }
    }
    
    public int getDay() {
        return this.CurrentDate.get(5);
    }
    
    public int getMonth() {
        return this.CurrentDate.get(2) + 1;
    }
    
    public int getYear() {
        return this.CurrentDate.get(1);
    }
}
