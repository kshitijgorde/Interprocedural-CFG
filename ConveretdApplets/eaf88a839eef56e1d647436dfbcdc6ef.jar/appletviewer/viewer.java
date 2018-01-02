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
    private int _$2225;
    private int _$2236;
    private String _$989;
    private String _$2247;
    private String _$2253;
    private int _$2257;
    private int _$2265;
    private int _$941;
    private URL _$1696;
    private URLConnection _$1715;
    private DataInputStream _$1756;
    private int _$2273;
    private byte[] _$2278;
    private boolean _$2284;
    private int _$2291;
    private boolean _$2298;
    private CommandSend _$2319;
    private byte[] _$2330;
    private int _$2338;
    private byte[] _$2343;
    private int _$2351;
    private boolean _$2356;
    private byte[] _$2361;
    private Thread _$2375;
    private Image _$2381;
    private Image _$2384;
    private Graphics _$2388;
    private boolean _$2390;
    private int _$2396;
    private int _$2404;
    private int _$2412;
    private int _$2420;
    private int _$2428;
    private int _$2436;
    private int _$2444;
    private int _$2452;
    private int _$2460;
    private int _$2466;
    private String _$2473;
    private String _$2480;
    private String _$2488;
    private byte[] _$2496;
    private byte[] _$2504;
    private Label _$2518;
    private Frame _$2529;
    private Label _$2535;
    private Button _$2547;
    private boolean _$2554;
    private String _$2562;
    private boolean _$2566;
    private long _$2576;
    private int _$2582;
    private int _$2590;
    private int _$2599;
    private int _$2609;
    private int _$2616;
    private boolean _$2624;
    private long _$2631;
    private long _$2639;
    private ZoomBar _$2654;
    private Image _$2661;
    private Image _$2668;
    private Image _$2675;
    private Image _$2682;
    private Image _$2691;
    private Image _$2700;
    private String _$2709;
    private int _$2714;
    private int[][] _$2723;
    private Cursor _$2736;
    private Cursor _$2740;
    private String _$2744;
    private int _$2749;
    private Frame _$2753;
    private Button _$2760;
    private Button _$2768;
    private Label _$2776;
    private Label _$2784;
    private Label _$2793;
    private Label _$2803;
    private Label _$2810;
    private Label _$2817;
    private Label _$2827;
    private TextField _$2847;
    private TextField _$2858;
    private char[] _$2869;
    private char[] _$2878;
    private char[] _$2887;
    private int _$2896;
    private int _$2905;
    private char[] _$2914;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public viewer() {
        this.isStandalone = false;
        this._$2225 = 15;
        this._$2236 = 256000000;
        this._$2284 = true;
        this._$2291 = 0;
        this._$2298 = false;
        this._$2319 = null;
        this._$2338 = 0;
        this._$2351 = 0;
        this._$2356 = false;
        this._$2375 = null;
        this._$2388 = null;
        this._$2390 = false;
        this._$2396 = 0;
        this._$2404 = 0;
        this._$2412 = 0;
        this._$2420 = 0;
        this._$2428 = 0;
        this._$2436 = 0;
        this._$2444 = 0;
        this._$2452 = 0;
        this._$2460 = 0;
        this._$2466 = 0;
        this._$2473 = "";
        this._$2480 = "";
        this._$2488 = "";
        this._$2518 = new Label();
        this._$2535 = new Label();
        this._$2547 = new Button();
        this._$2554 = false;
        this._$2562 = "";
        this._$2566 = false;
        this._$2582 = 1;
        this._$2590 = 0;
        this._$2599 = 99;
        this._$2609 = 0;
        this._$2616 = 0;
        this._$2624 = false;
        this._$2709 = "";
        this._$2714 = 320;
        this._$2723 = new int[][] { { 38, 0 }, { 42, 450 }, { 43, 450 }, { 48, 947 }, { 53, 1444 }, { 57, 1941 }, { 62, 2438 }, { 67, 2935 }, { 72, 3432 }, { 76, 3929 }, { 81, 4426 }, { 86, 4923 }, { 91, 5420 }, { 95, 5917 }, { 100, 6414 }, { 105, 6911 }, { 110, 7408 }, { 114, 7905 }, { 119, 8402 }, { 124, 8899 }, { 129, 9396 }, { 133, 9893 }, { 138, 10390 }, { 143, 10887 }, { 148, 11384 }, { 152, 11881 }, { 157, 12378 }, { 162, 12875 }, { 167, 13372 }, { 171, 13869 }, { 176, 14366 }, { 181, 14863 }, { 186, 15360 }, { 190, 15857 }, { 195, 16354 }, { 196, 16383 }, { 234, 16384 }, { 235, 16385 }, { 239, 17884 }, { 245, 19384 }, { 251, 20884 }, { 257, 22384 }, { 261, 23884 }, { 267, 25384 }, { 273, 26884 }, { 280, 28384 }, { 285, 29884 }, { 290, 31384 }, { 291, 31423 }, { 320, 31424 }, { 998, 99999 }, { 999, 99999 } };
        this._$2744 = "";
        this._$2749 = 0;
        this._$2760 = new Button();
        this._$2768 = new Button();
        this._$2776 = new Label();
        this._$2784 = new Label();
        this._$2793 = new Label();
        this._$2803 = new Label();
        this._$2810 = new Label();
        this._$2817 = new Label();
        this._$2827 = new Label();
        this._$2847 = new TextField();
        this._$2858 = new TextField();
        this._$2914 = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
    }
    
    public void init() {
        try {
            this._$2253 = this.getParameter("USER", "");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this._$2257 = Integer.parseInt(this.getParameter("PRIORITY", "0"));
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        try {
            this._$2265 = Integer.parseInt(this.getParameter("RCVSIZE", "256"));
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        try {
            this._$1227();
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
    }
    
    private void _$1227() throws Exception {
        this._$989 = this.getCodeBase().getHost();
        this._$941 = this.getCodeBase().getPort();
        if (this._$941 != 80 && this._$941 != -1) {
            this._$989 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$989))).append(":").append(this._$941)));
        }
        this._$2247 = this._$989;
        final String $2947 = this._$2947("/command/inquiry.cgi", "inq=sysinfo", true);
        final int n = $2947.indexOf("DefFrameRate=") + 13;
        this._$2562 = "0";
        if (n > 12) {
            final int index = $2947.indexOf("&", n + 1);
            if (index > 0) {
                this._$2562 = $2947.substring(n, index);
            }
        }
        if (this._$2253.equals("ADMIN")) {
            this._$989 = String.valueOf(String.valueOf(this._$989)).concat("/administratoronly");
        }
        this._$2661 = this.getImage(this.getCodeBase(), "wide.jpg");
        this._$2668 = this.getImage(this.getCodeBase(), "bar.jpg");
        this._$2675 = this.getImage(this.getCodeBase(), "tele.jpg");
        this._$2682 = this.getImage(this.getCodeBase(), "bar_e.jpg");
        this._$2691 = this.getImage(this.getCodeBase(), "tele_e.jpg");
        this._$2700 = this.getImage(this.getCodeBase(), "indicator.jpg");
        this.setLayout(null);
        this._$2518.setAlignment(0);
        this._$2518.setBackground(Color.black);
        this._$2518.setFont(new Font("Dialog", 1, 13));
        this._$2518.setForeground(Color.white);
        this._$2518.setBounds(new Rectangle(0, 0, 200, 20));
        this._$2518.setText("");
        this._$2535.setAlignment(1);
        this._$2535.setFont(new Font("Monospaced", 0, 13));
        this._$2535.setBounds(new Rectangle(11, 38, 476, 22));
        (this._$2654 = new ZoomBar(this._$2661, this._$2668, this._$2675, this._$2682, this._$2691, this._$2700)).setBackground(Color.black);
        this._$2654.setCursor(null);
        this._$2654.setBounds(new Rectangle(0, 505, 320, 28));
        this._$2654.addMouseListener(new MouseAdapter() {
            public void mouseExited(final MouseEvent mouseEvent) {
                viewer.this._$3020(mouseEvent);
            }
        });
        this._$2654.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent mouseEvent) {
                viewer.this._$3060(mouseEvent);
            }
        });
        this._$2654.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseExited(final MouseEvent mouseEvent) {
                viewer.this._$3020(mouseEvent);
            }
        });
        this._$2654.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                viewer.this._$3174(mouseEvent);
            }
        });
        this._$2654.setVisible(false);
        this.setBackground(Color.black);
        this._$2384 = this.createImage(736, 572);
        (this._$2388 = this._$2384.getGraphics()).setColor(Color.black);
        this._$2504 = new byte[this._$2265 - this._$2225];
        this._$2330 = new byte[400000];
        this._$2343 = new byte[this._$2265 + this._$2225];
        this._$2361 = new byte[this._$2225];
        (this._$2529 = new Frame("SNC-Z20")).setBackground(Color.lightGray);
        this._$2529.setSize(new Dimension(498, 129));
        this._$2529.setResizable(false);
        this._$2529.setLayout(null);
        this._$2547.setLabel("OK");
        this._$2547.setBounds(new Rectangle(197, 74, 105, 25));
        this._$2547.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                viewer.this._$3292(mouseEvent);
            }
        });
        (this._$2753 = new Frame("Enter Network Password")).setLayout(null);
        this._$2753.setBackground(Color.lightGray);
        this._$2753.setSize(new Dimension(450, 250));
        this._$2753.setResizable(false);
        this._$2753.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                viewer.this._$3387();
            }
        });
        this._$2760.setLabel("OK");
        this._$2760.setBounds(new Rectangle(260, 200, 75, 25));
        this._$2760.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                viewer.this._$3466();
            }
        });
        this._$2760.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                viewer.this._$3466();
            }
        });
        this._$2768.setLabel("Cancel");
        this._$2768.setBounds(new Rectangle(345, 200, 75, 25));
        this._$2768.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                viewer.this._$3387();
            }
        });
        this._$2768.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                viewer.this._$3387();
            }
        });
        this._$2776.setText("Please type your user name and password.");
        this._$2776.setBounds(new Rectangle(30, 35, 250, 12));
        this._$2784.setText("Site:");
        this._$2784.setBounds(new Rectangle(30, 70, 90, 12));
        this._$2793.setText("Realm:");
        this._$2793.setBounds(new Rectangle(30, 100, 90, 12));
        this._$2803.setText("User Name:");
        this._$2803.setBounds(new Rectangle(30, 140, 90, 12));
        this._$2810.setText("Password:");
        this._$2810.setBounds(new Rectangle(30, 170, 90, 12));
        this._$2817.setBounds(new Rectangle(160, 65, 260, 20));
        this._$2827.setBounds(new Rectangle(160, 95, 260, 20));
        this._$2847.setBounds(new Rectangle(160, 135, 260, 20));
        this._$2858.setEchoChar('*');
        this._$2858.setBounds(new Rectangle(160, 165, 260, 20));
        this.add(this._$2518, null);
        this.add(this._$2654, null);
        this._$2529.add(this._$2535, null);
        this._$2529.add(this._$2547, null);
        this._$2529.setLocation(263, 319);
        this._$2753.add(this._$2776, null);
        this._$2753.add(this._$2784, null);
        this._$2753.add(this._$2817, null);
        this._$2753.add(this._$2793, null);
        this._$2753.add(this._$2803, null);
        this._$2753.add(this._$2847, null);
        this._$2753.add(this._$2858, null);
        this._$2753.add(this._$2760, null);
        this._$2753.add(this._$2768, null);
        this._$2753.add(this._$2810, null);
        this._$2753.add(this._$2827, null);
        this._$2753.setLocation(263, 319);
        this._$2736 = new Cursor(12);
        this._$2740 = this.getCursor();
        try {
            this._$3588();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String _$2947(final String s, final String s2, final boolean b) {
        final byte[] array = new byte[2048];
        int read = 0;
        if (s2.length() != 0) {
            try {
                final URLConnection openConnection = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$2247).append(s)))).openConnection();
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
        if (this._$2375 == null && !this._$2298) {
            (this._$2375 = new Thread(this)).start();
            (this._$2319 = new CommandSend(this._$2247)).start();
            this._$2319.setAuthorize(this._$2744);
        }
    }
    
    public void stop() {
        if (this._$2375 != null) {
            this._$2375 = null;
            this._$2319 = null;
        }
    }
    
    public void destroy() {
        this._$2298 = true;
        if (this._$2375 != null) {
            this._$2375 = null;
            this._$2319 = null;
        }
        try {
            this._$1756.close();
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
        boolean $3693 = false;
        Thread.currentThread().setPriority(3 + this._$2257);
        while (this._$2375 != null) {
            if (this._$2284) {
                try {
                    this._$2278 = new byte[this._$2265];
                    this._$2273 = this._$1756.read(this._$2278, this._$2225, this._$2265 - this._$2225);
                    this._$2609 += this._$2273;
                    this._$2291 += this._$2273;
                }
                catch (Exception ex) {
                    System.out.println("read() Error!!");
                    continue;
                }
                this._$3678(this._$2278, this._$2273);
                this._$3685();
                $3693 = this._$3693(this._$2273 + this._$2225, this._$2278);
            }
            if (this._$2639 != 0 && this._$2631 != 0) {
                this._$3700();
            }
            if (!this._$2624) {
                this._$2654.setVisible(false);
            }
            else {
                if (this._$2404 != 0) {
                    this._$2654.setBounds(new Rectangle(0, this._$2404 + 25, this._$2714, 28));
                }
                this._$2654.setVisible(true);
            }
            if (!$3693) {
                try {
                    this._$2381 = Toolkit.getDefaultToolkit().createImage(this._$2330);
                }
                catch (Exception ex2) {
                    System.out.println("JPEG Image Error!! (createImage)");
                    try {
                        this._$1756.close();
                    }
                    catch (IOException ex3) {}
                    this._$3737();
                    this._$3588();
                    continue;
                }
                try {
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    mediaTracker.addImage(this._$2381, 0);
                    mediaTracker.waitForAll();
                    this._$2444 = this._$2381.getWidth(null);
                    this._$2452 = this._$2381.getHeight(null);
                    switch (this._$2466) {
                        case 1: {
                            this._$2428 = 640;
                            this._$2436 = 480;
                            this._$2390 = true;
                            break;
                        }
                        case 2: {
                            this._$2428 = 320;
                            this._$2436 = 240;
                            this._$2390 = true;
                            break;
                        }
                        case 3: {
                            this._$2428 = 160;
                            this._$2436 = 120;
                            this._$2390 = true;
                            break;
                        }
                        default: {
                            this._$2428 = this._$2381.getWidth(null);
                            this._$2436 = this._$2381.getHeight(null);
                            this._$2390 = false;
                            break;
                        }
                    }
                }
                catch (Exception ex4) {
                    System.out.println("JPEG Image Error!! (CheckImage)");
                    try {
                        this._$1756.close();
                    }
                    catch (IOException ex5) {}
                    this._$3737();
                    this._$3588();
                    continue;
                }
                if (this._$2428 != this._$2396 || this._$2436 != this._$2404 || this._$2444 != this._$2412 || this._$2452 != this._$2420) {
                    this._$2388.clearRect(0, 0, 736, 572);
                    this._$2396 = this._$2428;
                    this._$2404 = this._$2436;
                    this._$2412 = this._$2444;
                    this._$2420 = this._$2452;
                    if (this._$2428 >= 400 || this._$2436 >= 300) {
                        this._$2460 = 5;
                    }
                    else {
                        this._$2460 = 0;
                    }
                    this._$2654.setBounds(new Rectangle(0, this._$2436 + 25, this._$2714, 28));
                    this._$2654.repaint();
                }
                try {
                    if (!this._$2390) {
                        this._$2388.drawImage(this._$2381, 0, 0, null);
                    }
                    else {
                        this._$2388.drawImage(this._$2381, 0, 0, this._$2428, this._$2436, null);
                    }
                }
                catch (Exception ex6) {
                    System.out.println("JPEG draw Error!!");
                    try {
                        this._$1756.close();
                    }
                    catch (IOException ex7) {}
                    this._$3737();
                    this._$3588();
                    continue;
                }
                this._$2381.flush();
                this.repaint();
                ++this._$2616;
                ++this._$2590;
                this._$2284 = this._$3779();
                this.reloadChk();
                this._$3795();
                if (this._$2375 != null) {
                    this._$3802();
                }
                if (this._$2460 > 0) {
                    try {
                        Thread.sleep(this._$2460);
                    }
                    catch (InterruptedException ex8) {}
                }
                Thread.yield();
            }
        }
        this._$2375 = null;
    }
    
    private void _$3588() {
        int n = 1;
        while (true) {
            if (this._$2562.equals("")) {
                final String s = String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$989).append("/image")));
                break Label_0083;
            }
            final String s = String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$989).append("/image?speed=").append(this._$2562)));
            try {
                while (true) {
                    if (n == 1 || !this._$2744.equals("")) {
                        this._$1696 = new URL(s);
                        (this._$1715 = this._$1696.openConnection()).setAllowUserInteraction(true);
                        this._$1715.setRequestProperty("Connection", "Close");
                        this._$1715.setRequestProperty("Pragma", "no-cache");
                        if (!this._$2744.equals("")) {
                            this._$1715.setRequestProperty("Authorization", "Basic ".concat(String.valueOf(String.valueOf(this._$2744))));
                        }
                        this._$1715.setUseCaches(false);
                        if (this._$3907(this._$1715)) {
                            this._$1756 = new DataInputStream(this._$1715.getInputStream());
                            break;
                        }
                        this._$2744 = "";
                        if (this._$2749 > 3) {
                            continue;
                        }
                        this._$3920(this._$1715);
                        n = 0;
                    }
                    else {
                        if (this._$2749 > 3) {
                            this._$2535.setText("Protected Object. This object is protected.");
                            this._$2554 = true;
                            this._$3685();
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
    
    private boolean _$3907(final URLConnection urlConnection) {
        if (urlConnection.getHeaderField(0).indexOf("401") > 0) {
            ++this._$2749;
            return false;
        }
        return true;
    }
    
    private void _$3920(final URLConnection urlConnection) {
        this._$2817.setText(this._$989);
        final String headerField = urlConnection.getHeaderField("WWW-Authenticate");
        if (!headerField.equals(null)) {
            this._$2827.setText(headerField.substring(headerField.indexOf("realm=") + 7, headerField.length() - 1));
        }
        this._$2847.setText("");
        this._$2858.setText("");
        this._$2753.show();
        this._$2847.requestFocus();
    }
    
    private void _$3678(final byte[] array, final int n) {
        this._$2496 = new byte[(this._$2265 - this._$2225) * 2];
        int i;
        for (i = 0; i < this._$2265 - this._$2225; ++i) {
            this._$2496[i] = this._$2504[i];
        }
        this._$2504 = new byte[this._$2265 - this._$2225];
        for (int j = 0; j < this._$2265 - this._$2225; ++j) {
            this._$2496[i] = array[j + this._$2225];
            this._$2504[j] = array[j + this._$2225];
            ++i;
        }
        this._$2473 = new String(this._$2496);
        final int n2 = this._$2473.indexOf("CamTim: ", 0) + 8;
        if (n2 >= 8) {
            try {
                final int index = this._$2473.indexOf("\r\n", n2);
                if (index != -1) {
                    this._$2518.setText(this._$2473.substring(n2, index));
                }
            }
            catch (Exception ex) {}
        }
        final int n3 = this._$2473.indexOf("PopUp: ", 0) + 7;
        if (n3 >= 7) {
            try {
                final int index2 = this._$2473.indexOf("\r\n", n3);
                if (index2 != -1) {
                    this._$2535.setText(this._$2473.substring(n3, index2));
                    this._$2554 = true;
                }
            }
            catch (Exception ex2) {
                System.out.println("PopUpSch Error!!");
            }
        }
        int int1 = -1;
        final int n4 = this._$2473.indexOf("CamPos: ", 0) + 8;
        if (n4 >= 8) {
            try {
                final int index3 = this._$2473.indexOf("\r\n", n4);
                if (index3 != -1) {
                    final String substring = this._$2473.substring(n4, index3);
                    if (substring.charAt(2) == '1') {
                        int1 = Integer.parseInt(substring.substring(11, 15), 16);
                    }
                }
            }
            catch (Exception ex3) {
                System.out.println("CamPosSch Error!!");
            }
        }
        if (int1 > -1) {
            for (int n5 = 0; this._$2723[n5][0] < 999; ++n5) {
                if (this._$2723[n5][1] > int1) {
                    if (!this._$2709.equals("full") && n5 - 1 > 36) {
                        n5 = 37;
                    }
                    int zoomPoint = 0;
                    switch (n5 - 1) {
                        case 0: {
                            zoomPoint = this._$2661.getWidth(null) / 2 - 2;
                            break;
                        }
                        case 36: {
                            zoomPoint = this._$2661.getWidth(null) + this._$2668.getWidth(null) + this._$2675.getWidth(null) / 2 - 2;
                            break;
                        }
                        case 49: {
                            zoomPoint = this._$2661.getWidth(null) + this._$2668.getWidth(null) + this._$2675.getWidth(null) + this._$2682.getWidth(null) + this._$2691.getWidth(null) / 2 - 3;
                            break;
                        }
                        default: {
                            zoomPoint = this._$2723[n5 - 1][0] - 4;
                            break;
                        }
                    }
                    this._$2654.setZoomPoint(zoomPoint);
                    this._$2654.dspZoomPointer(true);
                    this._$2654.repaint();
                    break;
                }
            }
        }
    }
    
    private boolean _$3693(final int n, final byte[] array) {
        for (int i = 0; i < this._$2225; ++i) {
            array[i] = this._$2361[i];
        }
        int n2;
        for (n2 = 0; n - this._$2225 > n2; ++n2) {
            if (!this._$2356) {
                if (array[n2] == -1 && array[n2 + 1] == -40) {
                    this._$2356 = true;
                    this._$2330[0] = array[n2];
                    this._$2338 = 1;
                }
            }
            else {
                if (array[n2] == 13 && array[n2 + 1] == 10 && array[n2 + 2] == 45 && array[n2 + 3] == 45) {
                    if (array[n2 + 14] == 45 && array[n2 + 15] == 45) {
                        this._$2375 = null;
                    }
                    this._$2356 = false;
                    this._$4089(n, n2, array);
                    this._$2361 = new byte[this._$2225];
                    return false;
                }
                try {
                    this._$2330[this._$2338] = array[n2];
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    this._$3737();
                    System.out.println("Image Cut Error!!");
                    return true;
                }
                ++this._$2338;
            }
        }
        for (int j = 0; j < this._$2225; ++j) {
            this._$2361[j] = array[n2 + j];
        }
        return true;
    }
    
    private void _$4089(final int i, int n, final byte[] array) {
        for (int j = 0; j < this._$2225; ++j) {
            this._$2343[j] = 0;
        }
        this._$2351 = this._$2225;
        while (i > n) {
            try {
                this._$2343[this._$2351] = array[n];
                ++this._$2351;
            }
            catch (IndexOutOfBoundsException ex) {}
            ++n;
        }
    }
    
    private boolean _$3779() {
        this._$2330 = new byte[400000];
        this._$2338 = 0;
        System.gc();
        return this._$3693(this._$2351, this._$2343);
    }
    
    private void _$3737() {
        this._$2330 = new byte[400000];
        this._$2338 = 0;
        this._$2343 = new byte[this._$2265 + this._$2225];
        this._$2351 = 0;
        this._$2361 = new byte[this._$2225];
        this._$2356 = false;
        this._$2284 = true;
        System.gc();
    }
    
    private void _$3685() {
        if (this._$2554) {
            this._$2529.show();
            this._$2554 = false;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this._$2384 != null) {
            graphics.drawImage(this._$2384, 0, 20, this);
            super.paint(graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void reloadChk() {
        if (this._$2291 > this._$2236) {
            this._$2566 = true;
            this._$2291 = 0;
        }
    }
    
    public void rateControl(final String $2562) {
        final int intValue = new Integer($2562);
        if (intValue >= 0 && intValue <= 25) {
            this._$2562 = $2562;
        }
        else {
            this._$2562 = "";
        }
        this._$2566 = true;
    }
    
    private void _$3795() {
        if (this._$2566) {
            try {
                this._$1756.close();
                this._$3737();
                this._$3588();
                this._$2566 = false;
            }
            catch (IOException ex) {
                System.out.print("Socket Close Error!!");
            }
        }
    }
    
    private void _$3802() {
        final long time = new Date().getTime();
        if (time - this._$2576 >= 1000) {
            try {
                if (time - this._$2576 > 2000) {
                    this.showStatus("0 frames/sec, received 0 bytes/sec ...");
                }
                else if (this._$2590 >= this._$2599) {
                    this.showStatus(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$2599))).append(" frames/sec, received ").append(this._$2609).append(" bytes/sec ..."))));
                }
                else {
                    this.showStatus(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$2590))).append(" frames/sec, received ").append(this._$2609).append(" bytes/sec ..."))));
                }
            }
            catch (NullPointerException ex) {}
            if (this._$2582 >= 10) {
                this._$2599 = this._$2616 / 10;
                this._$2590 = 0;
                this._$2616 = 0;
                this._$2582 = 1;
            }
            else {
                this._$2590 = 0;
                ++this._$2582;
            }
            this._$2609 = 0;
            this._$2576 = time;
        }
    }
    
    public void imgSizeChange(final int $2466) {
        if ($2466 >= 0 && $2466 <= 3) {
            this._$2466 = $2466;
        }
        else {
            this._$2466 = 0;
        }
        this._$2396 = 0;
        this._$2404 = 0;
    }
    
    public void setDPTZtime(final String s) {
        final String $2947 = this._$2947("/command/inquiry.cgi", "inq=camera", true);
        final int n = $2947.indexOf("ZoomMode=") + 9;
        this._$2709 = "full";
        if (n > 8) {
            final int index = $2947.indexOf("&", n + 1);
            if (index > 0) {
                this._$2709 = $2947.substring(n, index);
            }
        }
        if (this._$2709.equals("full")) {
            this._$2714 = 320;
        }
        else {
            this._$2714 = 234;
        }
        if (s.equals("Err") || s.equals("")) {
            this._$2631 = 0L;
            this._$2639 = 0L;
            this._$2624 = false;
        }
        else if (s.equals("no limit")) {
            this._$2631 = 0L;
            this._$2639 = 0L;
            this._$2624 = true;
        }
        else {
            final int index2 = s.indexOf(",");
            if (index2 != -1) {
                this._$2631 = new Date().getTime() + Integer.parseInt(s.substring(0, index2)) * 1000;
                this._$2639 = this._$2631 + Integer.parseInt(s.substring(index2 + 1)) * 1000;
            }
        }
    }
    
    private void _$3700() {
        final long time = new Date().getTime();
        if (this._$2631 >= time) {
            this._$2624 = false;
        }
        else if (this._$2639 >= time) {
            this._$2624 = true;
        }
        else {
            this._$2624 = false;
        }
    }
    
    private void _$3292(final MouseEvent mouseEvent) {
        this._$2529.hide();
    }
    
    private void _$3466() {
        this._$2753.hide();
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$2847.getText()))).append(":").append(this._$2858.getText())));
        this._$2896 = value.length();
        this._$2869 = new char[80];
        value.getChars(0, this._$2896, this._$2869, 0);
        this._$2878 = new char[128];
        this._$4312();
        this._$2744 = new String(this._$2878).trim();
    }
    
    private void _$3387() {
        this._$2753.hide();
        this._$2749 = 99;
    }
    
    private void _$4312() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (this._$2896 > 2) {
            this._$4451(n, n2);
            this._$2896 -= 3;
            n += 3;
            n2 += 4;
        }
        char c;
        char c2;
        if (this._$2896 == 2) {
            c = this._$2869[n];
            c2 = this._$2869[n + 1];
            n3 = 0;
        }
        else {
            if (this._$2896 != 1) {
                this._$2878[n2 + 1] = '\0';
                return;
            }
            c = this._$2869[n];
            c2 = '\0';
        }
        final char c3 = (char)(c >> 2);
        final char c4 = (char)((c << 4 & '0') | (c2 >> 4 & '\u000f'));
        this._$2878[n2] = this._$2914[c3];
        ++n2;
        this._$2878[n2] = this._$2914[c4];
        if (this._$2896 == 1) {
            ++n2;
            this._$2878[n2] = '=';
            ++n2;
            this._$2878[n2] = '=';
        }
        else {
            final char c5 = (char)((c2 << 2 & '<') | (n3 >> 6 & 0x3));
            ++n2;
            this._$2878[n2] = this._$2914[c5];
            ++n2;
            this._$2878[n2] = '=';
        }
        this._$2878[n2 + 1] = '\0';
    }
    
    private void _$4451(final int n, final int n2) {
        final char c = (char)(this._$2869[n] >> 2 & '?');
        final char c2 = (char)((this._$2869[n] << 4 & '0') | (this._$2869[n + 1] >> 4 & '\u000f'));
        final char c3 = (char)((this._$2869[n + 1] << 2 & '<') | (this._$2869[n + 2] >> 6 & '\u0003'));
        final char c4 = (char)(this._$2869[n + 2] & '?');
        this._$2878[n2] = this._$2914[c];
        this._$2878[n2 + 1] = this._$2914[c2];
        this._$2878[n2 + 2] = this._$2914[c3];
        this._$2878[n2 + 3] = this._$2914[c4];
    }
    
    private void _$3174(final MouseEvent mouseEvent) {
        String s = "";
        final char[] array = new char[4];
        final int x = mouseEvent.getX();
        for (int n = 0; this._$2723[n][0] < 999; ++n) {
            if (x < this._$2723[n][0]) {
                final char[] charArray = Integer.toHexString(this._$2723[n][1]).toUpperCase().toCharArray();
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
                synchronized (this._$2319) {
                    this._$2319.setCommand("/command/ptzf.cgi", String.valueOf(String.valueOf(new StringBuffer("visca=81010447").append(s).append("FF"))));
                    this._$2319.notify();
                    // monitorexit(this._$2319)
                    break;
                }
            }
        }
    }
    
    private void _$3060(final MouseEvent mouseEvent) {
        if (this._$2624) {
            this.setCursor(this._$2736);
        }
    }
    
    private void _$3020(final MouseEvent mouseEvent) {
        this.setCursor(this._$2740);
    }
}
