// 
// Decompiled by Procyon v0.5.30
// 

package cameracontrol;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.awt.Container;
import java.awt.Image;
import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Panel;
import java.awt.CardLayout;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.URLConnection;
import java.net.URL;
import java.applet.Applet;

public class Controler extends Applet implements Runnable
{
    boolean isStandalone;
    private String _$971;
    private String _$976;
    private String _$980;
    private String _$985;
    private int _$994;
    private URL _$1001;
    private URLConnection _$1024;
    private PrintStream _$1049;
    private DataInputStream _$1071;
    private CardLayout _$1087;
    private CardLayout _$1106;
    private CardLayout _$1117;
    private Panel _$1133;
    private Panel _$1143;
    private Panel _$1152;
    private Panel _$1160;
    private Panel _$1172;
    private Panel _$1183;
    private Panel _$1194;
    private Panel _$1202;
    private Panel _$1213;
    private Panel _$1223;
    private ImageCanvas[] _$1241;
    private LineCanvas _$1259;
    private Choice _$1274;
    private Label _$1286;
    private Label _$1296;
    private Label _$1307;
    private Label _$1315;
    private Label _$1324;
    private Thread _$1336;
    private int _$1343;
    private int _$1348;
    private int _$1353;
    private String[] _$1360;
    private boolean _$1367;
    private int _$1376;
    private boolean _$1383;
    private String _$1395;
    private String _$1405;
    private String _$1414;
    private int[] _$1423;
    private boolean _$1433;
    private boolean _$1444;
    private boolean _$1452;
    private boolean _$1459;
    private int _$1464;
    private int _$1470;
    private int _$1476;
    private int _$1484;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public Controler() {
        this.isStandalone = false;
        this._$1087 = new CardLayout();
        this._$1106 = new CardLayout();
        this._$1117 = new CardLayout();
        this._$1133 = new Panel();
        this._$1143 = new Panel();
        this._$1152 = new Panel();
        this._$1160 = new Panel();
        this._$1172 = new Panel();
        this._$1183 = new Panel();
        this._$1194 = new Panel();
        this._$1202 = new Panel();
        this._$1213 = new Panel();
        this._$1223 = new Panel();
        this._$1241 = new ImageCanvas[21];
        this._$1274 = new Choice();
        this._$1286 = new Label();
        this._$1296 = new Label();
        this._$1307 = new Label();
        this._$1315 = new Label();
        this._$1324 = new Label();
        this._$1343 = 0;
        this._$1348 = 0;
        this._$1353 = 0;
        this._$1360 = new String[36];
        this._$1376 = 0;
        this._$1383 = false;
        this._$1395 = "01";
        this._$1405 = "01";
        this._$1414 = "01";
        this._$1423 = new int[17];
        this._$1433 = true;
        this._$1444 = false;
        this._$1452 = false;
        this._$1459 = false;
        this._$1464 = 0;
        this._$1470 = 0;
        this._$1476 = 0;
        this._$1484 = 3;
    }
    
    public void init() {
        try {
            this._$971 = this.getParameter("CLIENT", "");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this._$1585();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    private void _$1585() throws Exception {
        final byte[] array = new byte[1024];
        this._$976 = this.getCodeBase().getHost();
        this._$994 = this.getCodeBase().getPort();
        if (this._$994 != 80 && this._$994 != -1) {
            this._$976 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$976))).append(":").append(this._$994)));
        }
        final int $1654 = this._$1654("/command/inquiry.cgi", "inq=camera", false, true, array);
        final String s = new String(array);
        final int n = s.indexOf("ZoomMode=") + 9;
        if (n > 8 && s.substring(n, n + 7).equals("optical")) {
            this._$980 = "optical";
        }
        else {
            this._$980 = "full";
        }
        final int n2 = s.indexOf("FocusMode=") + 10;
        if (n2 > 9 && s.substring(n2, n2 + 6).equals("manual")) {
            this._$985 = "manual";
        }
        else {
            this._$985 = "auto";
        }
        final int n3 = s.indexOf("PtzfMode=") + 9;
        if (n3 > 8) {
            final int index = s.indexOf("&", n3 + 1);
            if (index > 0) {
                this._$1383 = s.substring(n3, index).equals("step");
            }
        }
        final int n4 = s.indexOf("RelPanTilt=") + 11;
        if (n4 > 10) {
            final int index2 = s.indexOf("&", n4 + 1);
            if (index2 > 0) {
                this._$1395 = this._$1679(s.substring(n4, index2));
            }
        }
        final int n5 = s.indexOf("RelZoom=") + 8;
        if (n5 > 7) {
            final int index3 = s.indexOf("&", n5 + 1);
            if (index3 > 0) {
                this._$1405 = this._$1679(s.substring(n5, index3));
            }
        }
        final int n6 = s.indexOf("RelFocus=") + 9;
        if (n6 > 8) {
            final int index4 = s.indexOf("&", n6 + 1);
            if (index4 > 0) {
                this._$1414 = this._$1679(s.substring(n6, index4));
            }
            else if ($1654 - n6 <= 2) {
                this._$1414 = this._$1679(s.substring(n6, $1654));
            }
        }
        this._$1286.setAlignment(1);
        this._$1286.setForeground(Color.white);
        this._$1286.setText("Please wait for");
        this._$1286.setBounds(new Rectangle(12, 7, 146, 17));
        this._$1296.setAlignment(1);
        this._$1296.setText("a little.");
        this._$1296.setBounds(new Rectangle(12, 35, 146, 17));
        this._$1296.setAlignment(1);
        this._$1296.setForeground(Color.white);
        this._$1307.setAlignment(1);
        this._$1307.setForeground(Color.white);
        this._$1307.setText("");
        this._$1315.setAlignment(1);
        this._$1315.setAlignment(1);
        this._$1315.setForeground(Color.white);
        this._$1315.setText("");
        this._$1324.setAlignment(1);
        this._$1324.setBackground(new Color(75, 110, 182));
        this._$1324.setFont(new Font("Dialog", 1, 16));
        this._$1324.setForeground(Color.white);
        this._$1324.setBounds(new Rectangle(61, 41, 38, 38));
        final Image image = this.getImage(this.getCodeBase(), "1_over.jpg");
        final Image image2 = this.getImage(this.getCodeBase(), "1_off.jpg");
        final Image image3 = this.getImage(this.getCodeBase(), "2_over.jpg");
        final Image image4 = this.getImage(this.getCodeBase(), "2_off.jpg");
        final Image image5 = this.getImage(this.getCodeBase(), "3_over.jpg");
        final Image image6 = this.getImage(this.getCodeBase(), "3_off.jpg");
        final Image image7 = this.getImage(this.getCodeBase(), "4_over.jpg");
        final Image image8 = this.getImage(this.getCodeBase(), "4_off.jpg");
        final Image image9 = this.getImage(this.getCodeBase(), "5_over.jpg");
        final Image image10 = this.getImage(this.getCodeBase(), "5_off.jpg");
        final Image image11 = this.getImage(this.getCodeBase(), "6_over.jpg");
        final Image image12 = this.getImage(this.getCodeBase(), "6_off.jpg");
        final Image image13 = this.getImage(this.getCodeBase(), "7_over.jpg");
        final Image image14 = this.getImage(this.getCodeBase(), "7_off.jpg");
        final Image image15 = this.getImage(this.getCodeBase(), "8_over.jpg");
        final Image image16 = this.getImage(this.getCodeBase(), "8_off.jpg");
        final Image image17 = this.getImage(this.getCodeBase(), "9_over.jpg");
        final Image image18 = this.getImage(this.getCodeBase(), "9_off.jpg");
        final Image image19 = this.getImage(this.getCodeBase(), "tele_over.jpg");
        final Image image20 = this.getImage(this.getCodeBase(), "tele_off.jpg");
        final Image image21 = this.getImage(this.getCodeBase(), "wide_over.jpg");
        final Image image22 = this.getImage(this.getCodeBase(), "wide_off.jpg");
        final Image image23 = this.getImage(this.getCodeBase(), "near_over.jpg");
        final Image image24 = this.getImage(this.getCodeBase(), "near_off.jpg");
        final Image image25 = this.getImage(this.getCodeBase(), "far_over.jpg");
        final Image image26 = this.getImage(this.getCodeBase(), "far_off.jpg");
        final Image image27 = this.getImage(this.getCodeBase(), "onepushaf_over.jpg");
        final Image image28 = this.getImage(this.getCodeBase(), "onepushaf_off.jpg");
        final Image image29 = this.getImage(this.getCodeBase(), "dzoomx1_over.jpg");
        final Image image30 = this.getImage(this.getCodeBase(), "dzoomx1_off.jpg");
        final Image image31 = this.getImage(this.getCodeBase(), "dzoomx2_over.jpg");
        final Image image32 = this.getImage(this.getCodeBase(), "dzoomx2_off.jpg");
        final Image image33 = this.getImage(this.getCodeBase(), "focus_over.jpg");
        final Image image34 = this.getImage(this.getCodeBase(), "focus_off.jpg");
        final Image image35 = this.getImage(this.getCodeBase(), "preset_over.jpg");
        final Image image36 = this.getImage(this.getCodeBase(), "preset_off.jpg");
        final Image image37 = this.getImage(this.getCodeBase(), "ptmode_over.jpg");
        final Image image38 = this.getImage(this.getCodeBase(), "ptmode_off.jpg");
        (this._$1241[1] = new ImageCanvas(image2, image)).setBounds(new Rectangle(21, 81, 38, 38));
        this._$1241[1].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 1, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 1, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(1, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(1, false);
            }
        });
        (this._$1241[2] = new ImageCanvas(image4, image3)).setBounds(new Rectangle(61, 81, 38, 38));
        this._$1241[2].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 2, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 2, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(2, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(2, false);
            }
        });
        (this._$1241[3] = new ImageCanvas(image6, image5)).setBounds(new Rectangle(101, 81, 38, 38));
        this._$1241[3].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 3, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 3, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(3, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(3, false);
            }
        });
        (this._$1241[4] = new ImageCanvas(image8, image7)).setBounds(new Rectangle(21, 41, 38, 38));
        this._$1241[4].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 4, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 4, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(4, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(4, false);
            }
        });
        (this._$1241[5] = new ImageCanvas(image10, image9)).setBounds(new Rectangle(61, 41, 38, 38));
        this._$1241[5].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 5, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 5, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(5, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(5, false);
            }
        });
        (this._$1241[6] = new ImageCanvas(image12, image11)).setBounds(new Rectangle(101, 41, 38, 38));
        this._$1241[6].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 6, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 6, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(6, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(6, false);
            }
        });
        (this._$1241[7] = new ImageCanvas(image14, image13)).setBounds(new Rectangle(21, 1, 38, 38));
        this._$1241[7].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 7, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 7, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(7, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(7, false);
            }
        });
        (this._$1241[8] = new ImageCanvas(image16, image15)).setBounds(new Rectangle(61, 1, 38, 38));
        this._$1241[8].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 8, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 8, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(8, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(8, false);
            }
        });
        (this._$1241[9] = new ImageCanvas(image18, image17)).setBounds(new Rectangle(101, 1, 38, 38));
        this._$1241[9].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 9, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 9, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(9, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(9, false);
            }
        });
        (this._$1241[11] = new ImageCanvas(image22, image21)).setBounds(new Rectangle(21, 122, 58, 24));
        this._$1241[11].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 11, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 11, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(11, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(11, false);
            }
        });
        (this._$1241[12] = new ImageCanvas(image20, image19)).setBounds(new Rectangle(81, 122, 58, 24));
        this._$1241[12].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 12, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 12, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(12, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(12, false);
            }
        });
        (this._$1241[14] = new ImageCanvas(image24, image23)).setBounds(new Rectangle(21, 0, 58, 24));
        this._$1241[14].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 14, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 14, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(14, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(14, false);
            }
        });
        (this._$1241[15] = new ImageCanvas(image26, image25)).setBounds(new Rectangle(81, 0, 58, 24));
        this._$1241[15].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 15, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 15, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(15, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(15, false);
            }
        });
        (this._$1241[16] = new ImageCanvas(image28, image27)).setBounds(new Rectangle(21, 26, 118, 24));
        this._$1241[16].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 16, true);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2176(mouseEvent, 16, false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(16, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(16, false);
            }
        });
        (this._$1241[17] = new ImageCanvas(image30, image29, image32, image31)).setBounds(new Rectangle(21, 148, 118, 24));
        this._$1241[17].addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Controler.this._$2604(mouseEvent);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(17, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(17, false);
            }
        });
        (this._$1241[19] = new ImageCanvas(image34, image33, image36, image35)).setBounds(new Rectangle(21, 226, 58, 24));
        this._$1241[19].addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                Controler.this._$2664(mouseEvent);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(19, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(19, false);
            }
        });
        (this._$1241[20] = new ImageCanvas(image38, image37)).setBounds(new Rectangle(81, 226, 58, 24));
        this._$1241[20].addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                Controler.this._$2709(mouseEvent);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Controler.this._$2220(20, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Controler.this._$2220(20, false);
            }
        });
        this._$1274.setBounds(new Rectangle(21, 0, 118, 24));
        this._$1274.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                Controler.this._$2806(itemEvent);
            }
        });
        this.setBackground(Color.lightGray);
        this._$1133.setBackground(new Color(75, 110, 182));
        this._$1133.setLayout(null);
        this._$1143.setBackground(new Color(75, 110, 182));
        this._$1172.setBackground(new Color(75, 110, 182));
        this._$1183.setBackground(new Color(75, 110, 182));
        this._$1202.setBackground(new Color(75, 110, 182));
        this._$1213.setBackground(new Color(75, 110, 182));
        this._$1152.setBackground(new Color(75, 110, 182));
        (this._$1259 = new LineCanvas(new Color(245, 245, 245), Color.blue)).setFont(new Font("Dialog", 1, 16));
        this._$1259.setBounds(new Rectangle(0, 0, 160, 120));
        this._$1259.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                Controler.this._$2878(mouseEvent);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                Controler.this._$2898(mouseEvent);
            }
        });
        this._$1259.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent mouseEvent) {
                Controler.this._$2997(mouseEvent);
            }
        });
        this.setLayout(this._$1087);
        this._$1194.setBackground(Color.lightGray);
        this._$1133.add(this._$1296, null);
        this._$1133.add(this._$1286, null);
        this._$1143.setLayout(null);
        this._$1143.setEnabled(true);
        this._$1143.add("Control", this._$1160);
        this._$1143.add("Sub", this._$1194);
        this._$1143.add(this._$1241[11], null);
        this._$1143.add(this._$1241[12], null);
        if (this._$985.equals("manual")) {
            this._$1143.add(this._$1241[19], null);
        }
        if (this._$980.endsWith("optical")) {
            this._$1143.add(this._$1241[17], null);
        }
        this._$1143.add(this._$1241[20], null);
        this._$1160.setBounds(new Rectangle(0, 0, 169, 123));
        this._$1160.setLayout(this._$1106);
        this._$1160.add("Button", this._$1172);
        this._$1160.add("Direct", this._$1183);
        this._$1172.setLayout(null);
        this._$1172.add(this._$1241[1], null);
        this._$1172.add(this._$1241[2], null);
        this._$1172.add(this._$1241[3], null);
        this._$1172.add(this._$1241[4], null);
        this._$1172.add(this._$1241[5], null);
        this._$1172.add(this._$1241[6], null);
        this._$1172.add(this._$1241[7], null);
        this._$1172.add(this._$1241[8], null);
        this._$1172.add(this._$1241[9], null);
        this._$1172.add(this._$1324, null);
        this._$1183.setLayout(null);
        this._$1223.setBounds(new Rectangle(0, 0, 160, 120));
        this._$1183.add(this._$1259, null);
        this._$1194.setBounds(new Rectangle(0, 174, 160, 50));
        this._$1194.setLayout(this._$1117);
        this._$1194.add("Preset", this._$1202);
        this._$1202.add(this._$1274, null);
        this._$1194.add("focusPanel", this._$1213);
        this._$1202.setLayout(null);
        this._$1213.setLayout(null);
        this._$1213.add(this._$1241[14], null);
        this._$1213.add(this._$1241[15], null);
        this._$1213.add(this._$1241[16], null);
        this._$1152.add(this._$1307, null);
        this._$1152.add(this._$1315, null);
        this.add("First", this._$1133);
        this.add("Main", this._$1143);
        this.add("End", this._$1152);
        if (this._$1383) {
            this._$1360[0] = "8101060103030303FF";
            this._$1360[1] = "01";
            this._$1360[2] = "02";
            this._$1360[3] = "03";
            this._$1360[4] = "04";
            this._$1360[5] = "81010604FF";
            this._$1360[6] = "06";
            this._$1360[7] = "07";
            this._$1360[8] = "08";
            this._$1360[9] = "09";
            this._$1360[10] = "8101040700FF";
            this._$1360[11] = "10";
            this._$1360[12] = "11";
            this._$1360[13] = "8101040800FF";
            this._$1360[14] = "12";
            this._$1360[15] = "13";
            this._$1360[16] = "8101041801FF";
            this._$1360[17] = "8101044600000000FF";
            this._$1360[18] = "8101044600000700FF";
            this._$1360[19] = "";
            this._$1360[20] = "";
            this._$1360[21] = "8101060103030201FF";
            this._$1360[22] = "8101060103030301FF";
            this._$1360[23] = "8101060103030101FF";
            this._$1360[24] = "8101060103030203FF";
            this._$1360[25] = "";
            this._$1360[26] = "8101060103030103FF";
            this._$1360[27] = "8101060103030202FF";
            this._$1360[28] = "8101060103030302FF";
            this._$1360[29] = "8101060103030102FF";
            this._$1360[30] = "";
            this._$1360[31] = "8101040734FF";
            this._$1360[32] = "8101040724FF";
            this._$1360[33] = "";
            this._$1360[34] = "8101040833FF";
            this._$1360[35] = "8101040823FF";
        }
        else {
            this._$1360[0] = "8101060103030303FF";
            this._$1360[1] = "8101060103030201FF";
            this._$1360[2] = "8101060103030301FF";
            this._$1360[3] = "8101060103030101FF";
            this._$1360[4] = "8101060103030203FF";
            this._$1360[5] = "81010604FF";
            this._$1360[6] = "8101060103030103FF";
            this._$1360[7] = "8101060103030202FF";
            this._$1360[8] = "8101060103030302FF";
            this._$1360[9] = "8101060103030102FF";
            this._$1360[10] = "8101040700FF";
            this._$1360[11] = "8101040734FF";
            this._$1360[12] = "8101040724FF";
            this._$1360[13] = "8101040800FF";
            this._$1360[14] = "8101040833FF";
            this._$1360[15] = "8101040823FF";
            this._$1360[16] = "8101041801FF";
            this._$1360[17] = "8101044600000000FF";
            this._$1360[18] = "8101044600000700FF";
            this._$1360[19] = "";
            this._$1360[20] = "";
            this._$1360[21] = "";
            this._$1360[22] = "";
            this._$1360[23] = "";
            this._$1360[24] = "";
            this._$1360[25] = "";
            this._$1360[26] = "";
            this._$1360[27] = "";
            this._$1360[28] = "";
            this._$1360[29] = "";
            this._$1360[30] = "";
            this._$1360[31] = "";
            this._$1360[32] = "";
            this._$1360[33] = "";
            this._$1360[34] = "";
            this._$1360[35] = "";
        }
        this._$1367 = false;
    }
    
    public void start() {
        this._$3043();
        this._$3055();
        if (this._$1336 == null) {
            (this._$1336 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this._$1336 != null) {
            this._$1336 = null;
        }
    }
    
    public void destroy() {
        if (this._$1336 != null) {
            this._$1336 = null;
        }
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return new String[0][];
    }
    
    public void run() {
        int n = 0;
        this._$1087.first(this);
        while (this._$1336 != null) {
            if (this._$1343 == 0 && this._$1348 == -1) {
                this._$1259.sec = -1;
                if (this._$980.equals("optical")) {
                    this._$3126();
                }
                this._$1353 = -1;
                this._$1087.next(this);
                break;
            }
            if (this._$1343 == -1 && this._$1348 == -1) {
                this._$1307.setAlignment(0);
                this._$1307.setText("Change your browser     ");
                this._$1315.setText("settings to accept cookies.");
                this._$1087.last(this);
                this.stop();
                break;
            }
            if (this._$1343 == -1 && this._$1348 != -1) {
                this._$1307.setText("After a while,");
                this._$1315.setText("please access again.");
                this._$1087.last(this);
                this.stop();
                break;
            }
            if (this._$1343 > 0) {
                this._$1296.setText(String.valueOf(String.valueOf(this._$1343)).concat(" seconds."));
                --this._$1343;
            }
            else if (this._$1343 == 0 && this._$1348 > 0) {
                if (this._$980.equals("optical")) {
                    this._$3126();
                }
                this._$1087.next(this);
                this._$1324.setText("".concat(String.valueOf(String.valueOf(this._$1348))));
                this._$1259.sec = this._$1348;
                this._$1259.repaint();
                this._$1353 = this._$1348;
                break;
            }
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {
                System.out.println("InterruptedException");
            }
        }
        while (this._$1336 != null) {
            if (this._$1353 > 0) {
                this._$1324.setText("".concat(String.valueOf(String.valueOf(this._$1353))));
                this._$1259.sec = this._$1353;
                this._$1259.repaint();
            }
            else if (this._$1353 != -1) {
                this.stop();
                this._$1087.last(this);
                break;
            }
            if (this._$1376 != 0 && n == 1) {
                this._$2176(null, this._$1376, true);
                this._$1376 = 0;
                n = 0;
            }
            else if (this._$1376 != 0) {
                n = 1;
            }
            else {
                n = 0;
            }
            try {
                Thread.sleep(1000L);
                if (this._$1353 <= 0) {
                    continue;
                }
                --this._$1353;
            }
            catch (InterruptedException ex2) {}
        }
        this._$1336 = null;
    }
    
    private int _$1654(final String s, final String s2, final boolean b, final boolean b2, final byte[] array) {
        int read = 0;
        if (s2.length() != 0) {
            try {
                final URLConnection openConnection = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$976).append(s)))).openConnection();
                if (b && !this._$971.equals("")) {
                    openConnection.setRequestProperty("Cookie", "Client=".concat(String.valueOf(String.valueOf(this._$971))));
                }
                openConnection.setDoOutput(true);
                final PrintStream printStream = new PrintStream(new BufferedOutputStream(openConnection.getOutputStream()));
                printStream.print(s2);
                printStream.flush();
                printStream.close();
                final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                if (b2) {
                    read = dataInputStream.read(array);
                }
                dataInputStream.close();
            }
            catch (IOException ex) {
                System.out.println("SR Socket Error!!");
            }
        }
        return read;
    }
    
    private void _$3043() {
        final byte[] array = new byte[1024];
        this._$3370(array, this._$1654("/command/inquiry.cgi", "inq=presetposition", false, true, array));
    }
    
    private void _$3370(final byte[] array, final int n) {
        this._$1274.add("");
        final String s = new String(array);
        int n2 = s.indexOf("PresetName=") + 11;
        final int index = s.indexOf("&");
        if (index == n2) {
            return;
        }
        for (int i = 0; i < 16; ++i) {
            final int index2 = s.indexOf(",", n2 + 1);
            if (index2 == -1) {
                break;
            }
            final String substring = s.substring(n2, index2);
            if (Integer.parseInt(substring) > 16) {
                break;
            }
            final int n3 = index2 + 1;
            int n4;
            try {
                n4 = s.indexOf(",", n3 + 1);
                if (n4 >= index) {
                    n4 = s.indexOf("&", n3 + 1);
                }
                this._$1274.add(this._$3426(s.substring(n3, n4)));
                this._$1423[i] = Integer.parseInt(substring) - 1;
                if (n4 >= index) {
                    i = 16;
                }
            }
            catch (StringIndexOutOfBoundsException ex) {
                n4 = s.indexOf("&", n3 + 1);
                this._$1274.add(this._$3426(s.substring(n3, n4)));
                this._$1423[i] = Integer.parseInt(substring) - 1;
                i = 16;
            }
            n2 = n4 + 1;
        }
    }
    
    private String _$3426(final String s) {
        int n = 0;
        String s2 = "";
        while (true) {
            final int index = s.indexOf("%", n);
            if (index == -1) {
                break;
            }
            final String concat = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(s.substring(n, index))));
            if (s.substring(index, index + 3).equals("%3d")) {
                s2 = String.valueOf(String.valueOf(concat)).concat("=");
            }
            else if (s.substring(index, index + 3).equals("%26")) {
                s2 = String.valueOf(String.valueOf(concat)).concat("&");
            }
            else if (s.substring(index, index + 3).equals("%25")) {
                s2 = String.valueOf(String.valueOf(concat)).concat("%");
            }
            else {
                s2 = s.substring(index, index + 3);
            }
            n = index + 3;
        }
        return String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(s.substring(n))));
    }
    
    private void _$3055() {
        final byte[] array = new byte[1024];
        this._$1654("/command/ptzfctrlright/inquiry.cgi", "inq=dummy", false, false, null);
        for (int i = 0; i <= 10; ++i) {
            if (this._$3489(array, this._$1654("/command/ptzfctrlright/inquiry.cgi", "inq=time", true, true, array))) {
                return;
            }
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {
                System.out.println("InterruptedException");
            }
        }
        this._$1343 = -1;
        this._$1348 = 0;
    }
    
    private boolean _$3489(final byte[] array, final int n) {
        boolean b = false;
        final String s = new String(array);
        final int n2 = s.indexOf("WaitTime=") + 9;
        if (n2 >= 9) {
            this._$1343 = Integer.parseInt(s.substring(n2, s.indexOf("&", n2 + 1)));
            this._$1348 = Integer.parseInt(s.substring(s.indexOf("CtrlTime=") + 9, n));
            if (this._$1343 != 0 || this._$1348 != 0) {
                if (this._$1348 != -1) {
                    this._$1241[5].setVisible(false);
                }
                b = true;
            }
        }
        return b;
    }
    
    private void _$3126() {
        final byte[] array = new byte[1024];
        this._$1654("/command/visca-inquiry.cgi", "visca=81090446FF", false, true, array);
        try {
            if (array[5] == 48 && array[7] == 48 && array[9] == 48 && array[11] == 48) {
                this._$1241[17].imageselect(true);
                this._$1241[17].repaint();
                this._$1444 = false;
            }
        }
        catch (NullPointerException ex) {
            System.out.println("DZOOM CHECK ERROR!!");
        }
    }
    
    private static String _$3547(final int n) {
        if (n >= 16) {
            return Integer.toHexString(n);
        }
        return "0".concat(String.valueOf(String.valueOf(Integer.toHexString(n))));
    }
    
    private String _$1679(final String s) {
        if (s.length() < 2) {
            return "0".concat(String.valueOf(String.valueOf(s)));
        }
        return s;
    }
    
    private void _$2176(final MouseEvent mouseEvent, final int n, final boolean b) {
        if (b) {
            if (n != 5) {
                if (n < 10 && this._$1383) {
                    this._$1654("/command/ptzf.cgi", String.valueOf(String.valueOf(new StringBuffer("Relative=").append(this._$1360[n]).append(this._$1395))), false, false, null);
                    this._$1376 = n + 20;
                }
                else if (n <= 12 && this._$1383) {
                    this._$1654("/command/ptzf.cgi", String.valueOf(String.valueOf(new StringBuffer("Relative=").append(this._$1360[n]).append(this._$1405))), false, false, null);
                    this._$1376 = n + 20;
                }
                else if (n <= 15 && this._$1383) {
                    this._$1654("/command/ptzf.cgi", String.valueOf(String.valueOf(new StringBuffer("Relative=").append(this._$1360[n]).append(this._$1414))), false, false, null);
                    this._$1376 = n + 20;
                }
                else {
                    this._$1654("/command/ptzf.cgi", "visca=".concat(String.valueOf(String.valueOf(this._$1360[n]))), false, false, null);
                }
            }
        }
        else {
            int n2 = 0;
            switch (n) {
                case 5: {
                    n2 = 5;
                    break;
                }
                case 11:
                case 12: {
                    if (this._$1376 != 0 && this._$1383) {
                        n2 = 99;
                        this._$1376 = 0;
                        break;
                    }
                    n2 = 10;
                    break;
                }
                case 14:
                case 15: {
                    if (this._$1376 != 0 && this._$1383) {
                        n2 = 99;
                        this._$1376 = 0;
                        break;
                    }
                    n2 = 13;
                    break;
                }
                case 16: {
                    n2 = 99;
                    break;
                }
                default: {
                    if (this._$1376 != 0 && this._$1383) {
                        n2 = 99;
                        this._$1376 = 0;
                        break;
                    }
                    n2 = 0;
                    break;
                }
            }
            if (n2 != 99) {
                this._$1654("/command/ptzf.cgi", "visca=".concat(String.valueOf(String.valueOf(this._$1360[n2]))), false, false, null);
            }
            this._$1367 = true;
        }
    }
    
    private void _$2220(final int n, final boolean b) {
        this._$1241[n].imagechang(b);
        this._$1241[n].repaint();
    }
    
    private void _$2878(final MouseEvent mouseEvent) {
        int n = mouseEvent.getX() * 4 + 1;
        if (n >= 640) {
            n = 639;
        }
        int n2 = mouseEvent.getY() * 4 + 1;
        if (n2 >= 480) {
            n2 = 479;
        }
        this._$1654("/command/ptzf.cgi", String.valueOf(String.valueOf(new StringBuffer("directPT=").append(n).append(",").append(n2))), false, false, null);
    }
    
    private void _$2997(final MouseEvent mouseEvent) {
        int n = 0;
        int n2 = 0;
        int $1476 = 0;
        final int n3 = mouseEvent.getX() - this._$1464;
        final int n4 = mouseEvent.getY() - this._$1470;
        if (n3 > this._$1484) {
            n = 1;
        }
        else if (n3 < this._$1484 * -1) {
            n = 2;
        }
        if (n4 > this._$1484) {
            n2 = 1;
        }
        else if (n4 < this._$1484 * -1) {
            n2 = 2;
        }
        if (n == 0 && n2 == 0) {
            $1476 = 0;
        }
        else if (n == 2 && n2 == 1) {
            $1476 = 1;
        }
        else if (n == 0 && n2 == 1) {
            $1476 = 2;
        }
        else if (n == 1 && n2 == 1) {
            $1476 = 3;
        }
        else if (n == 2 && n2 == 0) {
            $1476 = 4;
        }
        else if (n == 1 && n2 == 0) {
            $1476 = 6;
        }
        else if (n == 2 && n2 == 2) {
            $1476 = 7;
        }
        else if (n == 0 && n2 == 2) {
            $1476 = 8;
        }
        else if (n == 1 && n2 == 2) {
            $1476 = 9;
        }
        if ($1476 != 0 && this._$1383) {
            $1476 += 20;
        }
        if ($1476 != 0 && this._$1476 != $1476) {
            this._$1654("/command/ptzf.cgi", "visca=".concat(String.valueOf(String.valueOf(this._$1360[$1476]))), false, false, null);
            this._$1476 = $1476;
        }
        this._$1464 = mouseEvent.getX();
        this._$1470 = mouseEvent.getY();
        this._$1459 = true;
    }
    
    private void _$2898(final MouseEvent mouseEvent) {
        if (this._$1459) {
            this._$1654("/command/ptzf.cgi", "visca=".concat(String.valueOf(String.valueOf(this._$1360[0]))), false, false, null);
            this._$1476 = 0;
            this._$1459 = false;
        }
    }
    
    private void _$2604(final MouseEvent mouseEvent) {
        if (!this._$1444) {
            this._$1241[17].imageselect(false);
            this._$1241[17].imagechang(true);
            this._$1241[17].repaint();
            this._$1654("/command/ptzf.cgi", "visca=".concat(String.valueOf(String.valueOf(this._$1360[18]))), false, false, null);
            this._$1444 = true;
        }
        else {
            this._$1241[17].imageselect(true);
            this._$1241[17].imagechang(true);
            this._$1241[17].repaint();
            this._$1654("/command/ptzf.cgi", "visca=".concat(String.valueOf(String.valueOf(this._$1360[17]))), false, false, null);
            this._$1444 = false;
        }
    }
    
    private void _$2806(final ItemEvent itemEvent) {
        if (this._$1274.getSelectedIndex() > 0) {
            this._$1654("/command/ptzf.cgi", String.valueOf(String.valueOf(new StringBuffer("visca=8101043F02").append(_$3547(this._$1423[this._$1274.getSelectedIndex() - 1])).append("FF"))), false, false, null);
        }
    }
    
    private void _$2664(final MouseEvent mouseEvent) {
        if (this._$1433) {
            this._$1241[19].imageselect(true);
            this._$1241[19].imagechang(true);
            this._$1241[19].repaint();
            this._$1433 = false;
        }
        else {
            this._$1241[19].imageselect(false);
            this._$1241[19].imagechang(true);
            this._$1241[19].repaint();
            this._$1433 = true;
        }
        this._$1117.next(this._$1194);
    }
    
    private void _$2709(final MouseEvent mouseEvent) {
        this._$1106.next(this._$1160);
    }
    
    public String inqDPTZtime() {
        String value = "";
        if (this._$1343 == -1) {
            value = "Err";
        }
        else if (this._$1348 == -1) {
            value = "no limit";
        }
        else if (this._$1348 > 0) {
            value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$1343))).append(",").append(this._$1348)));
        }
        return value;
    }
}
