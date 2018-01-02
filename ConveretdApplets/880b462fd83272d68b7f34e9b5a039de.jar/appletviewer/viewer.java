// 
// Decompiled by Procyon v0.5.30
// 

package appletviewer;

import java.util.Date;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.PrintStream;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Graphics;
import java.awt.Image;
import java.io.DataInputStream;
import java.net.URLConnection;
import java.net.URL;
import java.applet.Applet;

public class viewer extends Applet implements Runnable
{
    boolean isStandalone;
    private int _$957;
    private int _$968;
    private String _$985;
    private String _$989;
    private String _$995;
    private String _$1000;
    private int _$1004;
    private int _$1012;
    private int _$1020;
    private URL _$1027;
    private URLConnection _$1046;
    private DataInputStream _$1071;
    private int _$1075;
    private byte[] _$1080;
    private boolean _$1086;
    private int _$1093;
    private boolean _$1100;
    private CommandSend _$1121;
    private byte[] _$1132;
    private int _$1140;
    private byte[] _$1145;
    private int _$1153;
    private boolean _$1158;
    private byte[] _$1163;
    private Thread _$1177;
    private Image _$1188;
    private Image _$1191;
    private Graphics _$1203;
    private boolean _$1205;
    private int _$1211;
    private int _$1219;
    private int _$1227;
    private int _$1235;
    private int _$1243;
    private int _$1251;
    private int _$1259;
    private int _$1267;
    private int _$1275;
    private int _$1281;
    private String _$1288;
    private String _$1295;
    private String _$1303;
    private byte[] _$1311;
    private byte[] _$1319;
    private Label _$1333;
    private Frame _$1352;
    private Label _$1358;
    private Button _$1370;
    private boolean _$1377;
    private String _$1385;
    private boolean _$1389;
    private long _$1399;
    private int _$1405;
    private int _$1413;
    private int _$1422;
    private int _$1432;
    private int _$1439;
    private boolean _$1447;
    private long _$1454;
    private long _$1462;
    private byte _$1470;
    private long _$1482;
    private Point _$1493;
    private Point _$1503;
    private Point _$1513;
    private ZoomBar _$1530;
    private Image _$1537;
    private Image _$1544;
    private Image _$1551;
    private Cursor _$1564;
    private Cursor _$1568;
    private String _$1572;
    private int _$1577;
    private Frame _$1581;
    private Button _$1588;
    private Button _$1596;
    private Label _$1604;
    private Label _$1612;
    private Label _$1621;
    private Label _$1631;
    private Label _$1638;
    private Label _$1645;
    private Label _$1655;
    private TextField _$1675;
    private TextField _$1686;
    private char[] _$1697;
    private char[] _$1706;
    private char[] _$1715;
    private int _$1724;
    private int _$1733;
    private char[] _$1742;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public viewer() {
        this.isStandalone = false;
        this._$957 = 15;
        this._$968 = 256000000;
        this._$1086 = true;
        this._$1093 = 0;
        this._$1100 = false;
        this._$1121 = null;
        this._$1140 = 0;
        this._$1153 = 0;
        this._$1158 = false;
        this._$1177 = null;
        this._$1203 = null;
        this._$1205 = false;
        this._$1211 = 0;
        this._$1219 = 0;
        this._$1227 = 0;
        this._$1235 = 0;
        this._$1243 = 0;
        this._$1251 = 0;
        this._$1259 = 0;
        this._$1267 = 0;
        this._$1275 = 0;
        this._$1281 = 0;
        this._$1288 = "";
        this._$1295 = "";
        this._$1303 = "";
        this._$1333 = new Label();
        this._$1358 = new Label();
        this._$1370 = new Button();
        this._$1377 = false;
        this._$1385 = "";
        this._$1389 = false;
        this._$1405 = 1;
        this._$1413 = 0;
        this._$1422 = 99;
        this._$1432 = 0;
        this._$1439 = 0;
        this._$1447 = false;
        this._$1470 = 0;
        this._$1493 = new Point();
        this._$1503 = new Point();
        this._$1513 = new Point();
        this._$1572 = "";
        this._$1577 = 0;
        this._$1588 = new Button();
        this._$1596 = new Button();
        this._$1604 = new Label();
        this._$1612 = new Label();
        this._$1621 = new Label();
        this._$1631 = new Label();
        this._$1638 = new Label();
        this._$1645 = new Label();
        this._$1655 = new Label();
        this._$1675 = new TextField();
        this._$1686 = new TextField();
        this._$1742 = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
    }
    
    public void init() {
        try {
            this._$1000 = this.getParameter("USER", "");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this._$1004 = Integer.parseInt(this.getParameter("PRIORITY", "0"));
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        try {
            this._$1012 = Integer.parseInt(this.getParameter("RCVSIZE", "256"));
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        try {
            this._$1854();
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
    }
    
    private void _$1854() throws Exception {
        this._$985 = this.getCodeBase().getHost();
        this._$1020 = this.getCodeBase().getPort();
        if (this._$1020 != 80 && this._$1020 != -1) {
            this._$985 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$985))).append(":").append(this._$1020)));
        }
        this._$989 = this._$985;
        final String $1911 = this._$1911("/command/inquiry.cgi", "inq=sysinfo", true);
        final int n = $1911.indexOf("Mount=") + 6;
        if (n > 5) {
            this._$995 = $1911.substring(n, n + 7);
        }
        else {
            this._$995 = "ceiling";
        }
        final int n2 = $1911.indexOf("DefFrameRate=") + 13;
        this._$1385 = "0";
        if (n2 > 12) {
            final int index = $1911.indexOf("&", n2 + 1);
            if (index > 0) {
                this._$1385 = $1911.substring(n2, index);
            }
        }
        if (this._$1000.equals("ADMIN")) {
            this._$985 = String.valueOf(String.valueOf(this._$985)).concat("/administratoronly");
        }
        this._$1537 = this.getImage(this.getCodeBase(), "zoom-wide.jpg");
        this._$1544 = this.getImage(this.getCodeBase(), "zoom-bar.jpg");
        this._$1551 = this.getImage(this.getCodeBase(), "zoom-tele.jpg");
        this.setLayout(null);
        this._$1333.setAlignment(0);
        this._$1333.setBackground(Color.black);
        this._$1333.setFont(new Font("Dialog", 1, 13));
        this._$1333.setForeground(Color.white);
        this._$1333.setBounds(new Rectangle(0, 0, 200, 20));
        this._$1333.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent mouseEvent) {
                viewer.this._$2110(mouseEvent);
            }
        });
        this._$1333.setText("");
        this._$1358.setAlignment(1);
        this._$1358.setFont(new Font("Monospaced", 0, 13));
        this._$1358.setBounds(new Rectangle(11, 38, 476, 22));
        (this._$1530 = new ZoomBar(this._$1537, this._$1544, this._$1551)).setBackground(Color.black);
        this._$1530.setCursor(null);
        this._$1530.setBounds(new Rectangle(0, 505, 320, 23));
        this._$1530.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent mouseEvent) {
                viewer.this._$2172(mouseEvent);
            }
        });
        this._$1530.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseEntered(final MouseEvent mouseEvent) {
                viewer.this._$2172(mouseEvent);
            }
        });
        this._$1530.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                viewer.this._$2286(mouseEvent);
            }
        });
        this._$1530.setVisible(false);
        this.setBackground(Color.black);
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent mouseEvent) {
                viewer.this._$2347(mouseEvent);
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent mouseEvent) {
                viewer.this._$2395(mouseEvent);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                viewer.this._$2445(mouseEvent);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                viewer.this._$2475(mouseEvent);
            }
        });
        this._$1191 = this.createImage(736, 572);
        (this._$1203 = this._$1191.getGraphics()).setColor(Color.black);
        this._$1319 = new byte[this._$1012 - this._$957];
        this._$1132 = new byte[400000];
        this._$1145 = new byte[this._$1012 + this._$957];
        this._$1163 = new byte[this._$957];
        (this._$1352 = new Frame("SNC-RZ30")).setBackground(Color.lightGray);
        this._$1352.setSize(new Dimension(498, 129));
        this._$1352.setResizable(false);
        this._$1352.setLayout(null);
        this._$1370.setLabel("OK");
        this._$1370.setBounds(new Rectangle(197, 74, 105, 25));
        this._$1370.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                viewer.this._$2589(mouseEvent);
            }
        });
        (this._$1581 = new Frame("Enter Network Password")).setLayout(null);
        this._$1581.setBackground(Color.lightGray);
        this._$1581.setSize(new Dimension(450, 250));
        this._$1581.setResizable(false);
        this._$1581.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                viewer.this._$2684();
            }
        });
        this._$1588.setLabel("OK");
        this._$1588.setBounds(new Rectangle(260, 200, 75, 25));
        this._$1588.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                viewer.this._$2764();
            }
        });
        this._$1588.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                viewer.this._$2764();
            }
        });
        this._$1596.setLabel("Cancel");
        this._$1596.setBounds(new Rectangle(345, 200, 75, 25));
        this._$1596.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                viewer.this._$2684();
            }
        });
        this._$1596.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                viewer.this._$2684();
            }
        });
        this._$1604.setText("Please type your user name and password.");
        this._$1604.setBounds(new Rectangle(30, 35, 250, 12));
        this._$1612.setText("Site:");
        this._$1612.setBounds(new Rectangle(30, 70, 90, 12));
        this._$1621.setText("Realm:");
        this._$1621.setBounds(new Rectangle(30, 100, 90, 12));
        this._$1631.setText("User Name:");
        this._$1631.setBounds(new Rectangle(30, 140, 90, 12));
        this._$1638.setText("Password:");
        this._$1638.setBounds(new Rectangle(30, 170, 90, 12));
        this._$1645.setBounds(new Rectangle(160, 65, 260, 20));
        this._$1655.setBounds(new Rectangle(160, 95, 260, 20));
        this._$1675.setBounds(new Rectangle(160, 135, 260, 20));
        this._$1686.setEchoChar('*');
        this._$1686.setBounds(new Rectangle(160, 165, 260, 20));
        this.add(this._$1333, null);
        this.add(this._$1530, null);
        this._$1352.add(this._$1358, null);
        this._$1352.add(this._$1370, null);
        this._$1352.setLocation(263, 319);
        this._$1581.add(this._$1604, null);
        this._$1581.add(this._$1612, null);
        this._$1581.add(this._$1645, null);
        this._$1581.add(this._$1621, null);
        this._$1581.add(this._$1631, null);
        this._$1581.add(this._$1675, null);
        this._$1581.add(this._$1686, null);
        this._$1581.add(this._$1588, null);
        this._$1581.add(this._$1596, null);
        this._$1581.add(this._$1638, null);
        this._$1581.add(this._$1655, null);
        this._$1581.setLocation(263, 319);
        this._$1564 = new Cursor(12);
        this._$1568 = this.getCursor();
        try {
            this._$2891();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String _$1911(final String s, final String s2, final boolean b) {
        final byte[] array = new byte[2048];
        int read = 0;
        if (s2.length() != 0) {
            try {
                final URLConnection openConnection = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$989).append(s)))).openConnection();
                openConnection.setDoOutput(true);
                final PrintStream printStream = new PrintStream(openConnection.getOutputStream());
                printStream.print(s2);
                printStream.close();
                final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                if (b) {
                    read = dataInputStream.read(array);
                }
                dataInputStream.close();
            }
            catch (IOException ex) {
                System.out.println("Inq Error!!");
            }
        }
        if (b) {
            return new String(array, 0, read);
        }
        return "";
    }
    
    public void start() {
        if (this._$1177 == null && !this._$1100) {
            (this._$1177 = new Thread(this)).start();
            (this._$1121 = new CommandSend(this._$989)).start();
            this._$1121.setAuthorize(this._$1572);
        }
    }
    
    public void stop() {
        if (this._$1177 != null) {
            this._$1177 = null;
            this._$1121 = null;
        }
    }
    
    public void destroy() {
        this._$1100 = true;
        if (this._$1177 != null) {
            this._$1177 = null;
            this._$1121 = null;
        }
        try {
            this._$1071.close();
        }
        catch (Exception ex) {}
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "USER", "String", "" }, { "PRIORITY", "String", "0" }, { "RCVSIZE", "String", "256" } };
    }
    
    public void run() {
        boolean $3159 = false;
        Thread.currentThread().setPriority(3 + this._$1004);
        while (this._$1177 != null) {
            if (this._$1086) {
                try {
                    this._$1080 = new byte[this._$1012];
                    this._$1075 = this._$1071.read(this._$1080, this._$957, this._$1012 - this._$957);
                    this._$1432 += this._$1075;
                    this._$1093 += this._$1075;
                }
                catch (Exception ex) {
                    System.out.println("read() Error!!");
                    continue;
                }
                this._$3144(this._$1080, this._$1075);
                this._$3151();
                $3159 = this._$3159(this._$1075 + this._$957, this._$1080);
            }
            if (this._$1462 != 0 && this._$1454 != 0) {
                this._$3166();
            }
            if (!this._$1447) {
                this._$1530.setVisible(false);
            }
            else {
                this._$1530.setVisible(true);
            }
            if (!$3159) {
                try {
                    this._$1188 = Toolkit.getDefaultToolkit().createImage(this._$1132);
                }
                catch (Exception ex2) {
                    System.out.println("JPEG Image Error!! (createImage)");
                    try {
                        this._$1071.close();
                    }
                    catch (IOException ex3) {}
                    this._$3203();
                    this._$2891();
                    continue;
                }
                try {
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    mediaTracker.addImage(this._$1188, 0);
                    mediaTracker.waitForAll();
                    this._$1259 = this._$1188.getWidth(null);
                    this._$1267 = this._$1188.getHeight(null);
                    switch (this._$1281) {
                        case 1: {
                            this._$1243 = 640;
                            this._$1251 = 480;
                            this._$1205 = true;
                            break;
                        }
                        case 2: {
                            this._$1243 = 320;
                            this._$1251 = 240;
                            this._$1205 = true;
                            break;
                        }
                        case 3: {
                            this._$1243 = 160;
                            this._$1251 = 120;
                            this._$1205 = true;
                            break;
                        }
                        default: {
                            this._$1243 = this._$1188.getWidth(null);
                            this._$1251 = this._$1188.getHeight(null);
                            this._$1205 = false;
                            break;
                        }
                    }
                }
                catch (Exception ex4) {
                    System.out.println("JPEG Image Error!! (CheckImage)");
                    try {
                        this._$1071.close();
                    }
                    catch (IOException ex5) {}
                    this._$3203();
                    this._$2891();
                    continue;
                }
                if (this._$1243 != this._$1211 || this._$1251 != this._$1219 || this._$1259 != this._$1227 || this._$1267 != this._$1235) {
                    this._$1203.clearRect(0, 0, 736, 572);
                    this._$1211 = this._$1243;
                    this._$1219 = this._$1251;
                    this._$1227 = this._$1259;
                    this._$1235 = this._$1267;
                    if (this._$1243 >= 400 || this._$1251 >= 300) {
                        if (this._$995.equals("desktop")) {
                            this._$1275 = 10;
                        }
                        else {
                            this._$1275 = 5;
                        }
                    }
                    else if (this._$995.equals("desktop")) {
                        this._$1275 = 5;
                    }
                    else {
                        this._$1275 = 0;
                    }
                    this._$1530.setBounds(new Rectangle(0, this._$1251 + 25, 320, 23));
                }
                try {
                    if (!this._$995.equals("desktop")) {
                        if (!this._$1205) {
                            this._$1203.drawImage(this._$1188, 0, 0, null);
                        }
                        else {
                            this._$1203.drawImage(this._$1188, 0, 0, this._$1243, this._$1251, null);
                        }
                    }
                    else {
                        this._$1203.drawImage(this._$1188, 0, 0, this._$1243, this._$1251, this._$1259, this._$1267, 0, 0, null);
                    }
                    if (this._$1470 == 2) {
                        this._$1203.setColor(Color.red);
                        this._$1203.drawRect(this._$1503.x, this._$1503.y, this._$1513.x - this._$1503.x, this._$1513.y - this._$1503.y);
                        this._$1203.setColor(Color.black);
                    }
                }
                catch (Exception ex6) {
                    System.out.println("JPEG draw Error!!");
                    try {
                        this._$1071.close();
                    }
                    catch (IOException ex7) {}
                    this._$3203();
                    this._$2891();
                    continue;
                }
                this._$1188.flush();
                this.repaint();
                this._$1530.repaint();
                ++this._$1439;
                ++this._$1413;
                this._$1086 = this._$3306();
                this.reloadChk();
                this._$3322();
                if (this._$1177 != null) {
                    this._$3329();
                }
                if (this._$1275 > 0) {
                    try {
                        Thread.sleep(this._$1275);
                    }
                    catch (InterruptedException ex8) {}
                }
                Thread.yield();
            }
        }
        this._$1177 = null;
    }
    
    private void _$2891() {
        int n = 1;
        while (true) {
            if (this._$1385.equals("")) {
                final String s = String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$985).append("/image")));
                break Label_0083;
            }
            final String s = String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$985).append("/image?speed=").append(this._$1385)));
            try {
                while (true) {
                    if (n == 1 || !this._$1572.equals("")) {
                        this._$1027 = new URL(s);
                        (this._$1046 = this._$1027.openConnection()).setAllowUserInteraction(true);
                        this._$1046.setRequestProperty("Connection", "Close");
                        this._$1046.setRequestProperty("Pragma", "no-cache");
                        if (!this._$1572.equals("")) {
                            this._$1046.setRequestProperty("Authorization", "Basic ".concat(String.valueOf(String.valueOf(this._$1572))));
                        }
                        this._$1046.setUseCaches(false);
                        if (this._$3434(this._$1046)) {
                            this._$1071 = new DataInputStream(this._$1046.getInputStream());
                            break;
                        }
                        this._$1572 = "";
                        if (this._$1577 > 3) {
                            continue;
                        }
                        this._$3447(this._$1046);
                        n = 0;
                    }
                    else {
                        if (this._$1577 > 3) {
                            this._$1358.setText("Protected Object. This object is protected.");
                            this._$1377 = true;
                            this._$3151();
                            this.destroy();
                            break;
                        }
                        try {
                            Thread.sleep(500L);
                        }
                        catch (InterruptedException ex) {}
                    }
                }
            }
            catch (IOException ex2) {
                System.out.println("Socket Error!!");
                continue;
            }
            break;
        }
    }
    
    private boolean _$3434(final URLConnection urlConnection) {
        if (urlConnection.getHeaderField(0).indexOf("401") > 0) {
            ++this._$1577;
            return false;
        }
        return true;
    }
    
    private void _$3447(final URLConnection urlConnection) {
        this._$1645.setText(this._$985);
        final String headerField = urlConnection.getHeaderField("WWW-Authenticate");
        if (!headerField.equals(null)) {
            this._$1655.setText(headerField.substring(headerField.indexOf("realm=") + 7, headerField.length() - 1));
        }
        this._$1675.setText("");
        this._$1686.setText("");
        this._$1581.show();
        this._$1675.requestFocus();
    }
    
    private void _$3144(final byte[] array, final int n) {
        this._$1311 = new byte[(this._$1012 - this._$957) * 2];
        int i;
        for (i = 0; i < this._$1012 - this._$957; ++i) {
            this._$1311[i] = this._$1319[i];
        }
        this._$1319 = new byte[this._$1012 - this._$957];
        for (int j = 0; j < this._$1012 - this._$957; ++j) {
            this._$1311[i] = array[j + this._$957];
            this._$1319[j] = array[j + this._$957];
            ++i;
        }
        this._$1288 = new String(this._$1311);
        final int n2 = this._$1288.indexOf("CamTim: ", 0) + 8;
        if (n2 >= 8) {
            try {
                final int index = this._$1288.indexOf("\r\n", n2);
                if (index != -1) {
                    this._$1333.setText(this._$1288.substring(n2, index));
                }
            }
            catch (Exception ex) {}
        }
        final int n3 = this._$1288.indexOf("PopUp: ", 0) + 7;
        if (n3 >= 7) {
            try {
                final int index2 = this._$1288.indexOf("\r\n", n3);
                if (index2 != -1) {
                    this._$1358.setText(this._$1288.substring(n3, index2));
                    this._$1377 = true;
                }
            }
            catch (Exception ex2) {
                System.out.println("PopUpSch Error!!");
            }
        }
    }
    
    private boolean _$3159(final int n, final byte[] array) {
        for (int i = 0; i < this._$957; ++i) {
            array[i] = this._$1163[i];
        }
        int n2;
        for (n2 = 0; n - this._$957 > n2; ++n2) {
            if (!this._$1158) {
                if (array[n2] == -1 && array[n2 + 1] == -40) {
                    this._$1158 = true;
                    this._$1132[0] = array[n2];
                    this._$1140 = 1;
                }
            }
            else {
                if (array[n2] == 13 && array[n2 + 1] == 10 && array[n2 + 2] == 45 && array[n2 + 3] == 45) {
                    if (array[n2 + 14] == 45 && array[n2 + 15] == 45) {
                        this._$1177 = null;
                    }
                    this._$1158 = false;
                    this._$3588(n, n2, array);
                    this._$1163 = new byte[this._$957];
                    return false;
                }
                try {
                    this._$1132[this._$1140] = array[n2];
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    this._$3203();
                    System.out.println("Image Cut Error!!");
                    return true;
                }
                ++this._$1140;
            }
        }
        for (int j = 0; j < this._$957; ++j) {
            this._$1163[j] = array[n2 + j];
        }
        return true;
    }
    
    private void _$3588(final int i, int n, final byte[] array) {
        for (int j = 0; j < this._$957; ++j) {
            this._$1145[j] = 0;
        }
        this._$1153 = this._$957;
        while (i > n) {
            try {
                this._$1145[this._$1153] = array[n];
                ++this._$1153;
            }
            catch (IndexOutOfBoundsException ex) {}
            ++n;
        }
    }
    
    private boolean _$3306() {
        this._$1132 = new byte[400000];
        this._$1140 = 0;
        System.gc();
        return this._$3159(this._$1153, this._$1145);
    }
    
    private void _$3203() {
        this._$1132 = new byte[400000];
        this._$1140 = 0;
        this._$1145 = new byte[this._$1012 + this._$957];
        this._$1153 = 0;
        this._$1163 = new byte[this._$957];
        this._$1158 = false;
        this._$1086 = true;
        System.gc();
    }
    
    private void _$3151() {
        if (this._$1377) {
            this._$1352.show();
            this._$1377 = false;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this._$1191 != null) {
            graphics.drawImage(this._$1191, 0, 20, this);
            super.paint(graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void reloadChk() {
        if (this._$1093 > this._$968) {
            this._$1389 = true;
            this._$1093 = 0;
        }
    }
    
    public void rateControl(final String $1385) {
        final int intValue = new Integer($1385);
        if (intValue >= 0 && intValue <= 25) {
            this._$1385 = $1385;
        }
        else {
            this._$1385 = "";
        }
        this._$1389 = true;
    }
    
    private void _$3322() {
        if (this._$1389) {
            try {
                this._$1071.close();
                this._$3203();
                this._$2891();
                this._$1389 = false;
            }
            catch (IOException ex) {
                System.out.print("Socket Close Error!!");
            }
        }
    }
    
    private void _$3329() {
        final long time = new Date().getTime();
        if (time - this._$1399 >= 1000) {
            try {
                if (time - this._$1399 > 2000) {
                    this.showStatus("0 frames/sec, received 0 bytes/sec ...");
                }
                else if (this._$1413 >= this._$1422) {
                    this.showStatus(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$1422))).append(" frames/sec, received ").append(this._$1432).append(" bytes/sec ..."))));
                }
                else {
                    this.showStatus(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$1413))).append(" frames/sec, received ").append(this._$1432).append(" bytes/sec ..."))));
                }
            }
            catch (NullPointerException ex) {}
            if (this._$1405 >= 10) {
                this._$1422 = this._$1439 / 10;
                this._$1413 = 0;
                this._$1439 = 0;
                this._$1405 = 1;
            }
            else {
                this._$1413 = 0;
                ++this._$1405;
            }
            this._$1432 = 0;
            this._$1399 = time;
        }
    }
    
    public void imgSizeChange(final int $1281) {
        if ($1281 >= 0 && $1281 <= 3) {
            this._$1281 = $1281;
        }
        else {
            this._$1281 = 0;
        }
        this._$1211 = 0;
        this._$1219 = 0;
    }
    
    public void setDPTZtime(final String s) {
        if (s.equals("Err") || s.equals("")) {
            this._$1454 = 0L;
            this._$1462 = 0L;
            this._$1447 = false;
        }
        else if (s.equals("no limit")) {
            this._$1454 = 0L;
            this._$1462 = 0L;
            this._$1447 = true;
        }
        else {
            final int index = s.indexOf(",");
            if (index != -1) {
                this._$1454 = new Date().getTime() + Integer.parseInt(s.substring(0, index)) * 1000;
                this._$1462 = this._$1454 + Integer.parseInt(s.substring(index + 1)) * 1000;
            }
        }
    }
    
    private void _$3166() {
        final long time = new Date().getTime();
        if (this._$1454 >= time) {
            this._$1447 = false;
        }
        else if (this._$1462 >= time) {
            this._$1447 = true;
        }
        else {
            this._$1447 = false;
        }
    }
    
    private void _$2445(final MouseEvent mouseEvent) {
        if (this._$1447 && this._$3805(mouseEvent)) {
            this._$1470 = 1;
            this._$1493.x = mouseEvent.getX();
            this._$1493.y = mouseEvent.getY() - 20;
        }
    }
    
    private void _$2395(final MouseEvent mouseEvent) {
        if (this._$1470 >= 1 && (this._$1493.x - mouseEvent.getX() > 2 || this._$1493.x - mouseEvent.getX() < -2)) {
            this._$1470 = 2;
            this._$1503.x = Math.min(this._$1493.x, mouseEvent.getX());
            this._$1513.x = Math.max(this._$1493.x, mouseEvent.getX());
            this._$1503.y = Math.min(this._$1493.y, mouseEvent.getY() - 20);
            this._$1513.y = Math.max(this._$1493.y, mouseEvent.getY() - 20);
            this._$1503.x = Math.max(this._$1503.x, 0);
            this._$1513.x = Math.min(this._$1513.x, this._$1243 - 1);
            this._$1503.y = Math.max(this._$1503.y, 0);
            this._$1513.y = Math.min(this._$1513.y, this._$1251 - 1);
        }
    }
    
    private void _$2475(final MouseEvent mouseEvent) {
        final int n = this._$1227 / 2;
        final int n2 = this._$1235 / 2;
        final float n3 = this._$1211 / this._$1227;
        final float n4 = this._$1219 / this._$1235;
        Label_0464: {
            if (this._$1470 == 1) {
                if (!this._$3805(mouseEvent)) {
                    return;
                }
                final int round = Math.round(mouseEvent.getX() / n3 - n);
                final int round2 = Math.round(mouseEvent.getY() / n4 - 20 - n2);
                final long time = new Date().getTime();
                if (this._$1482 + 2000 > time) {
                    return;
                }
                this._$1482 = time;
                synchronized (this._$1121) {
                    this._$1121.setCommand("/command/ptzf.cgi", String.valueOf(String.valueOf(new StringBuffer("AreaZoom=").append(round).append(",").append(round2))));
                    this._$1121.notify();
                    // monitorexit(this._$1121)
                    break Label_0464;
                }
            }
            if (this._$1470 == 2) {
                final float n5 = this._$1503.x / n3;
                final float n6 = this._$1513.x / n3;
                final float n7 = this._$1503.y / n4;
                final float n8 = this._$1513.y / n4;
                final int round3 = Math.round((n6 - n5) / 2 + n5 - n);
                final int round4 = Math.round((n8 - n7) / 2 + n7 - n2);
                final int round5 = Math.round(n6 - n5);
                final int round6 = Math.round(n8 - n7);
                synchronized (this._$1121) {
                    if (round5 < 4 && round6 < 4) {
                        this._$1121.setCommand("/command/ptzf.cgi", String.valueOf(String.valueOf(new StringBuffer("AreaZoom=").append(round3).append(",").append(round4))));
                    }
                    else {
                        this._$1121.setCommand("/command/ptzf.cgi", String.valueOf(String.valueOf(new StringBuffer("AreaZoom=").append(round3).append(",").append(round4).append(",").append(round5).append(",").append(round6))));
                    }
                    this._$1121.notify();
                }
                // monitorexit(this._$1121)
            }
        }
        this._$1470 = 0;
    }
    
    private boolean _$3805(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int n = mouseEvent.getY() - 20;
        boolean b = true;
        if (!this._$1447 || x > this._$1243 || n > this._$1251 || n < 0) {
            b = false;
        }
        return b;
    }
    
    private void _$2589(final MouseEvent mouseEvent) {
        this._$1352.hide();
    }
    
    private void _$2764() {
        this._$1581.hide();
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$1675.getText()))).append(":").append(this._$1686.getText())));
        this._$1724 = value.length();
        this._$1697 = new char[80];
        value.getChars(0, this._$1724, this._$1697, 0);
        this._$1706 = new char[128];
        this._$3939();
        this._$1572 = new String(this._$1706).trim();
    }
    
    private void _$2684() {
        this._$1581.hide();
        this._$1577 = 99;
    }
    
    private void _$3939() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (this._$1724 > 2) {
            this._$4078(n, n2);
            this._$1724 -= 3;
            n += 3;
            n2 += 4;
        }
        char c;
        char c2;
        if (this._$1724 == 2) {
            c = this._$1697[n];
            c2 = this._$1697[n + 1];
            n3 = 0;
        }
        else {
            if (this._$1724 != 1) {
                this._$1706[n2 + 1] = '\0';
                return;
            }
            c = this._$1697[n];
            c2 = '\0';
        }
        final char c3 = (char)(c >> 2);
        final char c4 = (char)((c << 4 & '0') | (c2 >> 4 & '\u000f'));
        this._$1706[n2] = this._$1742[c3];
        ++n2;
        this._$1706[n2] = this._$1742[c4];
        if (this._$1724 == 1) {
            ++n2;
            this._$1706[n2] = '=';
            ++n2;
            this._$1706[n2] = '=';
        }
        else {
            final char c5 = (char)((c2 << 2 & '<') | (n3 >> 6 & 0x3));
            ++n2;
            this._$1706[n2] = this._$1742[c5];
            ++n2;
            this._$1706[n2] = '=';
        }
        this._$1706[n2 + 1] = '\0';
    }
    
    private void _$4078(final int n, final int n2) {
        final char c = (char)(this._$1697[n] >> 2 & '?');
        final char c2 = (char)((this._$1697[n] << 4 & '0') | (this._$1697[n + 1] >> 4 & '\u000f'));
        final char c3 = (char)((this._$1697[n + 1] << 2 & '<') | (this._$1697[n + 2] >> 6 & '\u0003'));
        final char c4 = (char)(this._$1697[n + 2] & '?');
        this._$1706[n2] = this._$1742[c];
        this._$1706[n2 + 1] = this._$1742[c2];
        this._$1706[n2 + 2] = this._$1742[c3];
        this._$1706[n2 + 3] = this._$1742[c4];
    }
    
    private void _$2286(final MouseEvent mouseEvent) {
        String s = "";
        final char[] array = new char[4];
        final int x = mouseEvent.getX();
        if (x < this._$1537.getWidth(null)) {
            s = "00000000";
        }
        else if (x > 320 - this._$1551.getWidth(null)) {
            s = "04000000";
        }
        else {
            final char[] charArray = Integer.toHexString((mouseEvent.getX() - this._$1537.getWidth(null)) * 67 + 1).toUpperCase().toCharArray();
            switch (charArray.length) {
                case 1: {
                    s = "0000000".concat(String.valueOf(String.valueOf(charArray[0])));
                    break;
                }
                case 2: {
                    s = String.valueOf(String.valueOf(new StringBuffer("00000").append(charArray[0]).append("0").append(charArray[1])));
                    break;
                }
                case 3: {
                    s = String.valueOf(String.valueOf(new StringBuffer("000").append(charArray[0]).append("0").append(charArray[1]).append("0").append(charArray[2])));
                    break;
                }
                case 4: {
                    s = String.valueOf(String.valueOf(new StringBuffer("0").append(charArray[0]).append("0").append(charArray[1]).append("0").append(charArray[2]).append("0").append(charArray[3])));
                    break;
                }
            }
        }
        synchronized (this._$1121) {
            this._$1121.setCommand("/command/ptzf.cgi", String.valueOf(String.valueOf(new StringBuffer("visca=81010447").append(s).append("FF"))));
            this._$1121.notify();
        }
        // monitorexit(this._$1121)
    }
    
    private void _$2347(final MouseEvent mouseEvent) {
        if (this._$1447 && this._$3805(mouseEvent)) {
            this.setCursor(this._$1564);
        }
        else {
            this.setCursor(this._$1568);
        }
    }
    
    private void _$2172(final MouseEvent mouseEvent) {
        if (this._$1447) {
            this.setCursor(this._$1564);
        }
    }
    
    private void _$2110(final MouseEvent mouseEvent) {
        this.setCursor(this._$1568);
    }
}
