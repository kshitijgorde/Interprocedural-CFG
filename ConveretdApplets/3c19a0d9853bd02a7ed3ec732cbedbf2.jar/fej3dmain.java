import java.io.FileInputStream;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Dimension;
import java.util.Locale;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Properties;
import java.awt.Image;
import java.awt.Button;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class fej3dmain extends Applet implements ActionListener, KeyListener, MouseListener, MouseMotionListener
{
    static final String sVersion = "Ver0.33";
    static final long serialVersionUID = 1L;
    int nf;
    int nin;
    int min;
    Button bt;
    fej3dfrm w;
    Image imgth;
    int nthx;
    int nthy;
    int sx;
    int sy;
    String stth;
    String[] stfile;
    String[] stfile1;
    String[] stname;
    String[] stmess;
    int nWF;
    int stmax;
    int stf;
    int swapf;
    int smoothf;
    int spacef;
    int zoomf;
    int menuf;
    int widthf;
    int heightf;
    int xf0;
    int yf0;
    int dltx;
    int dlty;
    int nButton;
    Properties myProp;
    String sLang;
    boolean bStandalone;
    static String[] _args;
    
    public fej3dmain() {
        this.nf = 0;
        this.w = null;
        this.myProp = null;
        this.sLang = "en";
        this.bStandalone = false;
    }
    
    public static void main(final String[] args) {
        fej3dmain._args = args;
        final fej3dmain fej3dmain = new fej3dmain();
        fej3dmain.bStandalone = true;
        fej3dmain.init();
        fej3dmain.framestart(1);
        fej3dmain.start();
    }
    
    public void init() {
        final String readbutton = this.readbutton("Start");
        this.stf = 0;
        this.swapf = 0;
        this.zoomf = 0;
        this.menuf = 0;
        this.widthf = 0;
        this.heightf = 0;
        this.nthx = 1;
        this.nthy = 1;
        this.nButton = 1;
        this.dltx = 0;
        this.dlty = 0;
        this.smoothf = 0;
        this.spacef = 0;
        this.xf0 = 0;
        this.yf0 = 0;
        this.stth = this.getParam("thumbnail");
        this.setLayout(null);
        this.add(this.bt = new Button(readbutton));
        final Dimension size = this.getSize();
        if (size.width == 0) {
            size.width = 70;
        }
        if (size.height == 0) {
            size.height = 20;
        }
        this.nin = 0;
        if (this.stth != null) {
            if (this.stth.length() > 4) {
                this.readtxt(this.getParam("charset"));
                this.imgth = this.getI(this.stth);
                this.nthx = this.getParamint(1, "thumbx");
                this.nthy = this.getParamint(1, "thumby");
                this.nButton = 0;
            }
            else {
                this.bt.setBounds(0, 0, size.width, size.height);
            }
        }
        else {
            this.bt.setBounds(0, 0, size.width, size.height);
        }
        if (this.nthx < 1) {
            this.nthx = 1;
        }
        if (this.nthy < 1) {
            this.nthy = 1;
        }
        this.sx = this.getSize().width / this.nthx;
        this.sy = this.getSize().height / this.nthy;
        this.bt.addActionListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setCursor(Cursor.getPredefinedCursor(12));
        try {
            this.sLang = this.getParam("language");
            if (this.sLang == null || this.sLang.length() != 2) {
                this.sLang = Locale.getDefault().getLanguage();
            }
            else {
                this.sLang = this.sLang.toLowerCase();
            }
            this.myProp = this.loadProperties();
            if (this.myProp == null) {
                this.sLang = "Default";
            }
        }
        catch (Exception ex) {
            final String string = "readPropertyFile --> Exception: " + ex;
            if (!this.bStandalone) {
                this.showStatus(string);
            }
            else {
                System.out.println(string);
            }
            this.sLang = "Default";
        }
    }
    
    protected Properties loadProperties() {
        Properties properties = null;
        InputStream inputStream = null;
        if (!this.bStandalone) {
            try {
                inputStream = new DataInputStream(new URL(this.getCodeBase(), "spva_" + this.sLang + ".properties").openStream());
            }
            catch (Exception ex) {}
            if (inputStream == null) {
                try {
                    inputStream = new DataInputStream(new URL(this.getCodeBase(), "spva_" + this.sLang + ".txt").openStream());
                }
                catch (Exception ex2) {}
            }
        }
        else {
            InputStream inputStream2 = this.getClass().getClassLoader().getResourceAsStream("spva_" + this.sLang + ".properties");
            if (inputStream2 == null) {
                inputStream2 = this.getClass().getClassLoader().getResourceAsStream("spva_" + this.sLang + ".txt");
            }
            if (inputStream2 != null) {
                inputStream = new DataInputStream(inputStream2);
            }
        }
        if (inputStream != null) {
            properties = new Properties();
            try {
                properties.load(inputStream);
            }
            catch (Exception ex3) {
                properties = null;
            }
        }
        return properties;
    }
    
    public void free() {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().runFinalization();
        Runtime.getRuntime().gc();
    }
    
    public void stop() {
        if (this.w != null) {
            this.w.wclose();
        }
        this.w = null;
        if (this.imgth != null) {
            this.imgth.flush();
            this.imgth = null;
        }
        this.free();
    }
    
    public void wclose() {
        this.w.dispose();
        this.w = null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.bt) {
            this.framestart(0);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.requestFocus();
        if (this.min < this.stmax + 1) {
            if (this.nthx * this.nthy > 1) {
                this.framestart(this.min);
            }
            else {
                this.framestart(0);
            }
        }
    }
    
    public void framestart(final int n) {
        if (this.w != null) {
            this.w.toFront();
            this.w.ShowImag(n);
        }
        else {
            int paramint = this.getParamint(800, "fwidth");
            int paramint2 = this.getParamint(533, "fheight");
            (this.w = new fej3dfrm(this, "StereoPhotoViewer", "Ver0.33", n)).setCharset(this.getParam("charset"));
            final Dimension screenSize = this.getToolkit().getScreenSize();
            screenSize.height -= 30;
            if (paramint == 0) {
                paramint = screenSize.width - 15;
            }
            if (paramint2 == 0) {
                paramint2 = screenSize.height - 90;
            }
            int width = paramint + 8;
            int height = paramint2 + 90;
            if (screenSize.width < 100) {
                screenSize.width = width;
            }
            if (screenSize.height < 100) {
                screenSize.height = height;
            }
            if (screenSize.width < width) {
                width = screenSize.width;
            }
            if (screenSize.height < height) {
                height = screenSize.height;
            }
            if (this.xf0 == 0 && this.yf0 == 0) {
                this.w.setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, paramint, paramint2);
            }
            else {
                this.w.setBounds(this.xf0, this.yf0, paramint, paramint2);
            }
            this.w.init(paramint, paramint2);
            final int width2 = this.w.getSize().width;
            final int height2 = this.w.getSize().height;
            this.dltx = width2 - paramint;
            this.dlty = height2 - paramint2;
            if (this.dltx < 0) {
                this.dltx = 0;
            }
            if (this.dlty < 0) {
                this.dlty = 0;
            }
            this.w.start();
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 27:
            case 32: {
                this.repaint();
                break;
            }
            case 72:
            case 112: {
                this.showhelp();
                break;
            }
            case 71: {
                this.showmypage();
                break;
            }
            case 73: {
                this.showStatus("StereoPhotoViewer Applet Ver0.33 / Language: " + this.sLang);
                break;
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.nin == 0) {
            this.nin = 1;
            this.min = 0;
            this.repaint();
        }
        this.nin = 1;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.nin == 1) {
            this.nin = 0;
            this.min = 0;
            this.repaint();
        }
        this.nin = 0;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getModifiers() == 16 && this.min < this.stmax + 1) {
            if (this.nthx * this.nthy > 1) {
                this.framestart(this.min);
            }
            else {
                this.framestart(0);
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.nin == 0) {
            return;
        }
        final int min = mouseEvent.getY() / this.sy * this.nthx + mouseEvent.getX() / this.sx + 1;
        if (this.min != min) {
            this.min = min;
            this.repaint();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public String readbutton(String nextToken) {
        try {
            final InputStream inputstm = this.inputstm();
            if (inputstm == null) {
                return nextToken;
            }
            final InputStreamReader inputStreamReader = new InputStreamReader(inputstm);
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 64000);
            bufferedReader.mark(64000);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(line, ",", false);
                if (stringTokenizer.hasMoreTokens() && stringTokenizer.nextToken().equals("button") && stringTokenizer.hasMoreTokens()) {
                    nextToken = stringTokenizer.nextToken();
                    break;
                }
            }
            inputStreamReader.close();
        }
        catch (Exception ex) {}
        return nextToken;
    }
    
    public void readtxt(final String s) {
        int n = 0;
        try {
            final InputStream inputstm = this.inputstm();
            if (inputstm == null) {
                this.stmax = 1;
                return;
            }
            InputStreamReader inputStreamReader;
            try {
                inputStreamReader = ((s == null) ? new InputStreamReader(inputstm) : new InputStreamReader(inputstm, s));
            }
            catch (UnsupportedEncodingException ex) {
                inputStreamReader = new InputStreamReader(inputstm);
            }
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 64000);
            bufferedReader.mark(64000);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(line, ",", false);
                if (stringTokenizer.hasMoreTokens() && stringTokenizer.nextToken().equals("L&R")) {
                    this.nWF = 1;
                }
                ++n;
            }
            this.stmax = n + 1;
            this.stfile = new String[this.stmax];
            if (this.nWF == 1) {
                this.stfile1 = new String[this.stmax];
            }
            this.stname = new String[this.stmax];
            this.stmess = new String[this.stmax];
            int stmax = 0;
            bufferedReader.reset();
            String line2;
            while ((line2 = bufferedReader.readLine()) != null) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(line2, ",", true);
                if (stringTokenizer2.hasMoreTokens()) {
                    this.stfile[stmax] = stringTokenizer2.nextToken();
                    if (this.stfile[stmax].equals("L&R")) {
                        continue;
                    }
                    if (this.stfile[stmax].equals("button")) {
                        continue;
                    }
                    final int length = this.stfile[stmax].length();
                    if (length < 5) {
                        continue;
                    }
                    final String lowerCase = this.stfile[stmax].substring(length - 4, length).toLowerCase();
                    if (!lowerCase.equals(".jpg") && !lowerCase.equals("jpeg") && !lowerCase.equals(".png") && !lowerCase.equals(".gif") && !lowerCase.equals(".jps")) {
                        continue;
                    }
                    if (stringTokenizer2.hasMoreTokens()) {
                        stringTokenizer2.nextToken();
                    }
                    if (this.nWF == 1) {
                        if (stringTokenizer2.hasMoreTokens()) {
                            this.stfile1[stmax] = stringTokenizer2.nextToken();
                        }
                        else {
                            this.stfile1[stmax] = null;
                        }
                        if (stringTokenizer2.hasMoreTokens()) {
                            stringTokenizer2.nextToken();
                        }
                    }
                    if (stringTokenizer2.hasMoreTokens()) {
                        this.stname[stmax] = stringTokenizer2.nextToken();
                    }
                    else {
                        this.stname[stmax] = " ";
                    }
                    if (this.stname[stmax].equals(",")) {
                        this.stname[stmax] = " ";
                    }
                    else if (stringTokenizer2.hasMoreTokens()) {
                        stringTokenizer2.nextToken();
                    }
                    if (this.stname[stmax].equals(" ")) {
                        this.stname[stmax] = this.stfile[stmax];
                    }
                    if (stringTokenizer2.hasMoreTokens()) {
                        this.stmess[stmax] = stringTokenizer2.nextToken();
                    }
                    else {
                        this.stmess[stmax] = null;
                    }
                    ++stmax;
                }
            }
            this.stmax = stmax;
            inputStreamReader.close();
        }
        catch (Exception ex2) {}
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.nButton == 1) {
            return;
        }
        final int width = this.imgth.getWidth(this);
        final int height = this.imgth.getHeight(this);
        graphics.drawImage(this.imgth, 0, 0, width, height, 0, 0, width - 1, height, this);
        if (this.nin == 1 && this.min < this.stmax + 1) {
            final int n = this.sx * ((this.min - 1) % this.nthx);
            final int n2 = n + this.sx;
            final int n3 = this.sy * ((this.min - 1) / this.nthx);
            final int n4 = n3 + this.sy;
            graphics.setColor(Color.blue);
            graphics.drawRect(n, n3, this.sx - 1, this.sy - 1);
            graphics.drawRect(n + 1, n3 + 1, this.sx - 3, this.sy - 3);
            if (this.nthx * this.nthy > 1 && this.min > 0) {
                graphics.setColor(Color.white);
                graphics.drawString(this.stname[this.min - 1], n + 10, n3 + 20);
                graphics.setColor(Color.black);
                graphics.drawString(this.stname[this.min - 1], n + 11, n3 + 21);
                this.showStatus("" + this.stfile[this.min - 1]);
            }
            else if (this.min > 0) {
                graphics.setColor(Color.white);
                final String property = this.getProperty("lg_ClickToOpen", "Click to open applet!");
                graphics.drawString(property, n + 2, n3 + 15);
                graphics.setColor(Color.black);
                graphics.drawString(property, n + 3, n3 + 16);
            }
        }
    }
    
    public Image getI(final String s) {
        Image image = null;
        final MediaTracker mediaTracker = new MediaTracker(this);
        try {
            if (this.bStandalone) {
                image = Toolkit.getDefaultToolkit().getImage(s);
            }
            else {
                image = this.getImage(this.getCodeBase(), s);
            }
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex2) {}
        catch (Exception ex) {
            System.out.println(ex);
        }
        return image;
    }
    
    public void setst(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        this.stf = n + 1;
        this.swapf = n2 + 1;
        this.zoomf = n3 + 1;
        this.menuf = (n4 + 1) % 2 + 1;
        this.widthf = n7 + 1;
        this.heightf = n8 + 1;
        this.smoothf = n9 + 1;
        this.spacef = n10 + 1;
        this.xf0 = n5 + 1;
        this.yf0 = n6 + 1;
    }
    
    public int getParamint(final int n, final String s) {
        if (s.equals("type") && this.stf > 0) {
            return this.stf - 1;
        }
        if (s.equals("swap") && this.swapf > 0) {
            return this.swapf - 1;
        }
        if (s.equals("fast") && this.smoothf > 0) {
            return this.smoothf - 1;
        }
        if (s.equals("spacing") && this.spacef > 0) {
            return this.spacef - 1;
        }
        if (s.equals("zoom") && this.zoomf > 0) {
            return this.zoomf - 1;
        }
        if (s.equals("menu") && this.menuf > 0) {
            return this.menuf - 1;
        }
        if (s.equals("fwidth") && this.widthf > 0) {
            return this.widthf - this.dltx - 1;
        }
        if (s.equals("fheight") && this.heightf > 0) {
            return this.heightf - this.dlty - 1;
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.getParam(s));
        }
        catch (NumberFormatException ex) {
            int1 = n;
        }
        return int1;
    }
    
    public String getParam(final String s) {
        if (!this.bStandalone) {
            return this.getParameter(s);
        }
        for (int i = 0; i < fej3dmain._args.length; ++i) {
            if (fej3dmain._args[i].equalsIgnoreCase("-" + s)) {
                return (i + 1 < fej3dmain._args.length) ? fej3dmain._args[i + 1] : null;
            }
        }
        return null;
    }
    
    public String getArg(final int n) {
        return (this.bStandalone && fej3dmain._args != null && n < fej3dmain._args.length) ? fej3dmain._args[n] : null;
    }
    
    public void filesave(final String s) {
        try {
            if (this.getParam("savefile").equals("Enable")) {
                this.getAppletContext().showDocument(new URL(this.getCodeBase(), s), "_blank");
            }
        }
        catch (Exception ex) {}
    }
    
    public void showhelp() {
        try {
            String param = this.getParam("helpfile");
            if (param == null) {
                param = "helpwe.htm";
            }
            this.getAppletContext().showDocument(new URL(this.getCodeBase(), param), "_blank");
        }
        catch (Exception ex) {}
    }
    
    public void showmypage() {
        try {
            this.getAppletContext().showDocument(new URL("http://hp.vector.co.jp/authors/VA004161/jump/eng.htm"), "_blank");
        }
        catch (Exception ex) {}
    }
    
    public InputStream inputstm() {
        InputStream inputStream = null;
        String param = this.getParam("filelist");
        if (param == null) {
            param = "filelste.txt";
        }
        try {
            inputStream = (this.bStandalone ? new FileInputStream(param) : new URL(this.getDocumentBase(), param).openStream());
        }
        catch (Exception ex) {}
        return inputStream;
    }
    
    public String getProperty(final String s, final String s2) {
        if (this.myProp == null) {
            return s2;
        }
        return this.myProp.getProperty(s, s2);
    }
    
    static {
        fej3dmain._args = null;
    }
}
