// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.Container;
import java.net.URLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.URL;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.LayoutManager;
import java.util.Date;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class ControlPanel extends Applet implements MouseListener, FocusListener
{
    private final String _$952 = "Trigger=smtp";
    private final String _$964 = "Trigger=ftp";
    private final String _$975 = "Trigger=memory";
    private final String _$164107 = "Trigger=alarmout1";
    private final String _$164121 = "Trigger=alarmout2";
    private final String _$165200 = "Trigger=daynight";
    private Viewer _$1008;
    private int _$1017;
    public boolean dZoomBtnPush;
    public boolean controlBtnPush;
    public Color color_back;
    public Color color_fore;
    public ImageButton[] IC;
    private Label _$1087;
    private Choice _$1103;
    private Label _$1114;
    private Choice _$1124;
    private Image _$1140;
    private Image _$1150;
    private Choice _$1160;
    private Image _$1169;
    private String _$1178;
    private String _$1181;
    private String _$1185;
    private String _$164135;
    private String _$164141;
    private String _$168519;
    private Image _$1196;
    private Image _$1205;
    private Image _$1214;
    private Label _$1223;
    private Image _$168538;
    private Image _$168550;
    private Image _$168563;
    private Image _$168575;
    private Image _$168588;
    private Label _$168604;
    private boolean _$168616;
    private Label _$168630;
    private Choice _$168649;
    private Cursor _$1238;
    private Cursor _$1253;
    private Cursor _$1266;
    private String _$1286;
    private String _$1291;
    private String _$1300;
    private final int _$168669 = 13;
    private final int _$168675 = 31;
    private final int _$168682 = 22;
    private boolean _$168689;
    private Date _$168709;
    private Date _$168721;
    private int _$168736;
    
    public ControlPanel(final Viewer $1008, final int n, final String s, final ViewModeDetail viewModeDetail) {
        this.dZoomBtnPush = false;
        this.controlBtnPush = false;
        this.color_back = new Color(75, 110, 182);
        this.color_fore = new Color(255, 255, 255);
        this.IC = new ImageButton[3];
        this._$1087 = new Label();
        this._$1103 = new Choice();
        this._$1114 = new Label();
        this._$1124 = new Choice();
        this._$1160 = new Choice();
        this._$1223 = new Label();
        this._$168604 = new Label();
        this._$168616 = false;
        this._$168630 = new Label();
        this._$168649 = new Choice();
        this._$1238 = new Cursor(12);
        this._$1253 = this.getCursor();
        this._$1266 = new Cursor(1);
        this._$1286 = "/command/main.cgi";
        this._$1291 = "";
        this._$168736 = 6;
        try {
            Thread.currentThread().setPriority(9);
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
        this.setLayout(null);
        this._$1008 = $1008;
        this._$1300 = this._$1008.host;
        if (s.equals("PRM_VIEWMODE_FULL")) {
            this._$1017 = 0;
        }
        else if (s.equals("PRM_VIEWMODE_LIGHT")) {}
        $1008.logger.print("ControlPanel constructer/vmDetail.isFRateActive() is ".concat(String.valueOf(String.valueOf(viewModeDetail.isFRateActive()))));
        if (viewModeDetail.isFRateActive()) {
            this._$1477(n);
            this._$168736 += 44;
        }
        if (viewModeDetail.isVSizeActive()) {
            this._$1502();
            this._$168736 += 58;
        }
        if (viewModeDetail.isDZoomActive()) {
            this._$1526();
            this._$168736 += 54;
        }
        if (viewModeDetail.isControlActive()) {
            this._$168741();
            this._$168736 += 54;
            this._$168751();
            this._$168736 += 58;
        }
        else if (viewModeDetail.isPresetPositionActive()) {
            this._$168751();
            this._$168736 += 58;
        }
        if (viewModeDetail.isTriggerActive()) {
            this._$1555();
        }
        this._$1565();
        this.setAllBackground(this.color_back = this._$1008.CtrlBackColor);
        this.setAllForeground(this.color_fore);
        this.reloadAllIcon(this.color_back);
        this.addMouseListener(this);
        (this._$168709 = new Date()).setTime(Long.MAX_VALUE);
        (this._$168721 = new Date()).setTime(new Long(Long.MAX_VALUE));
        this.addFocusListener(this);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.dzoomCanceled();
        if (this._$1008.controler != null) {
            this._$1008.controler.toFront();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(this._$1008.curDCur);
        if (this._$1008.controler != null) {
            this._$1008.controler.toFront();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (this._$1008.controler != null) {
            this._$1008.controler.toFront();
        }
    }
    
    public void repaintIcon() {
        for (int i = 0; i < this.IC.length; ++i) {
            if (this.IC[i] != null) {
                this.IC[i].repaint();
            }
        }
    }
    
    private void _$1565() {
        this.setViewSize(this._$1008.ViewSize);
        if (this._$1008.pFrameRateFlg) {
            this.setFrameRate(Integer.parseInt(this._$1008.pFrameRate));
        }
    }
    
    public void destroy() {
        for (int i = 0; i < this.IC.length; ++i) {
            this.IC[i] = null;
        }
        this.IC = null;
        this._$1008 = null;
        this._$1140 = null;
        this._$1150 = null;
        this._$1160 = null;
        this._$1169 = null;
        this._$1196 = null;
        this._$1205 = null;
        this._$1214 = null;
        this._$168538 = null;
        this._$168550 = null;
        this._$168563 = null;
        this._$168575 = null;
    }
    
    public void dzoomCanceled() {
        if (this.dZoomBtnPush) {
            this.dZoomBtnPush = false;
            this._$1008.setCursor(this._$1253);
            this.setCursor(this._$1253);
            this._$1008.curDCur = this._$1253;
        }
    }
    
    public void setAllBackground(final Color color) {
        this.setBackground(color);
        this._$1087.setBackground(color);
        this._$1114.setBackground(color);
        this._$1223.setBackground(color);
        this._$168604.setBackground(color);
        this._$168630.setBackground(color);
    }
    
    public void setAllForeground(final Color color) {
        this.setForeground(color);
        this._$1087.setForeground(color);
        this._$1114.setForeground(color);
        this._$1223.setForeground(color);
        this._$168604.setForeground(color);
        this._$168630.setForeground(color);
        this._$1103.setForeground(Color.black);
        this._$1124.setForeground(Color.black);
        this._$168649.setForeground(Color.black);
        this._$1160.setForeground(Color.black);
    }
    
    private void _$1477(final int n) {
        this._$1087.setText("Frame rate:");
        this._$1087.setBounds(new Rectangle(2, this._$1017 + this._$168736, 153, 13));
        this._$1087.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$1103.setBounds(new Rectangle(2, this._$1017 + this._$168736 + 13 + 1, 153, 22));
        this._$1103.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                ControlPanel.this._$2000(itemEvent);
            }
        });
        this._$1103.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        if (n == 30) {
            this._$1103.addItem("30 fps");
        }
        if (n >= 25) {
            this._$1103.addItem("25 fps");
        }
        if (n >= 20) {
            this._$1103.addItem("20 fps");
        }
        if (n >= 15) {
            this._$1103.addItem("15 fps");
        }
        if (n >= 10) {
            this._$1103.addItem("10 fps");
        }
        if (n >= 8) {
            this._$1103.addItem("8 fps");
        }
        if (n >= 6) {
            this._$1103.addItem("6 fps");
        }
        if (n >= 5) {
            this._$1103.addItem("5 fps");
        }
        if (n >= 4) {
            this._$1103.addItem("4 fps");
        }
        if (n >= 3) {
            this._$1103.addItem("3 fps");
        }
        if (n >= 2) {
            this._$1103.addItem("2 fps");
        }
        this._$1103.addItem("1 fps");
        this.add(this._$1087, null);
        this.add(this._$1103, null);
    }
    
    private void _$1502() {
        this._$1114.setText("View size:");
        this._$1114.setBounds(new Rectangle(2, this._$1017 + this._$168736, 153, 13));
        this._$1114.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$1124.setBounds(new Rectangle(2, this._$1017 + this._$168736 + 13 + 1, 153, 22));
        this._$1008.logger.print("VSize : ".concat(String.valueOf(String.valueOf(String.valueOf(this._$1017 + this._$168736)))));
        this._$1124.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                ControlPanel.this._$2113(itemEvent);
            }
        });
        this._$1124.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$1124.addItem("AUTO");
        this._$1124.addItem("640x480");
        this._$1124.addItem("320x240");
        this._$1124.addItem("160x120");
        this.add(this._$1114, null);
        this.add(this._$1124, null);
    }
    
    private void _$1526() {
        this._$1223.setText("Digital zoom");
        this._$1223.setBounds(new Rectangle(44, this._$1017 + this._$168736 + 9, 118, 13));
        this._$1223.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$1169 = this._$1008.getImage(this._$1008.getCodeBase(), "zoom_plus_highlight.gif");
        this._$1196 = this._$1008.getImage(this._$1008.getCodeBase(), "zoom_plus.gif");
        this._$1205 = this._$1008.getImage(this._$1008.getCodeBase(), "zoom_minus_highlight.gif");
        this._$1214 = this._$1008.getImage(this._$1008.getCodeBase(), "zoom_minus.gif");
        (this.IC[0] = new ImageButton(this._$1196, this._$1169, this._$1214, this._$1205, this.color_back)).setBounds(new Rectangle(0, this._$1017 + this._$168736, 38, 31));
        this._$1008.logger.print("DigitalZoom : ".concat(String.valueOf(String.valueOf(String.valueOf(this._$1017 + this._$168736)))));
        this.IC[0].addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    ControlPanel.this._$2269(mouseEvent);
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (ControlPanel.this._$1008.curDCur.equals(ControlPanel.this._$1008.curD)) {
                    ControlPanel.this.setCursor(ControlPanel.this._$1008.curH);
                }
                else {
                    ControlPanel.this.setCursor(ControlPanel.this._$1008.curDCur);
                }
                if (ControlPanel.this._$1008.controler != null) {
                    ControlPanel.this._$1008.controler.toFront();
                }
                ControlPanel.this.IC[0].ImCanvas_mouseDsp(true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                ControlPanel.this.IC[0].ImCanvas_mouseDsp(false);
            }
        });
        this.add(this._$1223, null);
        this.add(this.IC[0], null);
    }
    
    private void _$168741() {
        this._$168604.setText("Control");
        this._$168604.setBounds(new Rectangle(44, this._$1017 + this._$168736 + 9, 118, 13));
        this._$168538 = this._$1008.getImage(this._$1008.getCodeBase(), "control1.gif");
        this._$168550 = this._$1008.getImage(this._$1008.getCodeBase(), "control1_highlight.gif");
        this._$168563 = this._$1008.getImage(this._$1008.getCodeBase(), "control2.gif");
        this._$168575 = this._$1008.getImage(this._$1008.getCodeBase(), "control2_highlight.gif");
        this._$168588 = this._$1008.getImage(this._$1008.getCodeBase(), "wait.gif");
        this._$1008.logger.print("Control start");
        this.IC[2] = new ImageButton(this._$168538, this._$168550, this._$168563, this._$168575, this.color_back);
        this._$1008.logger.print("Control end");
        this.IC[2].setBounds(new Rectangle(0, this._$1017 + this._$168736, 38, 31));
        this.IC[2].addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    ControlPanel.this._$168788(mouseEvent);
                }
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                ControlPanel.this._$168616 = true;
                if (ControlPanel.this._$1008.curDCur.equals(ControlPanel.this._$1008.curD)) {
                    ControlPanel.this.setCursor(ControlPanel.this._$1008.curH);
                }
                else {
                    ControlPanel.this.setCursor(ControlPanel.this._$1008.curDCur);
                }
                if (ControlPanel.this._$1008.controler != null) {
                    ControlPanel.this._$1008.controler.toFront();
                }
                ControlPanel.this.IC[2].ImCanvas_mouseDsp(true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                ControlPanel.this._$168616 = false;
                ControlPanel.this.IC[2].ImCanvas_mouseDsp(false);
            }
        });
        this.add(this._$168604, null);
        this.add(this.IC[2], null);
    }
    
    private void _$168751() {
        this._$168630.setText("Preset position:");
        this._$168630.setBounds(new Rectangle(2, this._$1017 + this._$168736, 153, 13));
        this._$168630.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$168649.setBounds(new Rectangle(2, this._$1017 + this._$168736 + 13 + 1, 153, 22));
        this._$168649.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                ControlPanel.this._$168814(itemEvent);
            }
        });
        this._$168649.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$1008.camera.loadPresetPosition();
        this._$168649.addItem("");
        for (int i = 0; i < this._$1008.camera.getPresetPositionNumber(); ++i) {
            this._$168649.addItem(this._$1008.camera.getPresetPositionName(i));
        }
        if (this._$1008.vModeDetail.isPresetPositionIndipendent()) {
            this._$168630.setVisible(true);
            this._$168649.setVisible(true);
        }
        else {
            this._$168630.setVisible(false);
            this._$168649.setVisible(false);
        }
        if (this._$1008.camera.getPresetPositionNumber() <= 0) {
            this._$168630.setVisible(false);
            this._$168649.setVisible(false);
        }
        this.add(this._$168630, null);
        this.add(this._$168649, null);
    }
    
    private void _$1555() {
        this._$2332();
        if (this._$2352(this._$1178, this._$1181, this._$1185, this._$164135, this._$164141, this._$168519) == 0) {
            return;
        }
        this._$1160.setBounds(new Rectangle(40, this._$1017 + this._$168736 + 4, 113, 22));
        this._$1160.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
        });
        this._$1160.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                ControlPanel.this._$2414(itemEvent);
            }
        });
        this._$1140 = this._$1008.getImage(this._$1008.getCodeBase(), "trigger_highlight.gif");
        this._$1150 = this._$1008.getImage(this._$1008.getCodeBase(), "trigger_off.gif");
        (this.IC[1] = new ImageButton(this._$1150, this._$1140, this.color_back)).setBounds(new Rectangle(0, this._$1017 + this._$168736, 38, 31));
        this.IC[1].addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                ControlPanel.this._$2470(mouseEvent);
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                ControlPanel.this.dzoomCanceled();
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (ControlPanel.this._$1008.curDCur.equals(ControlPanel.this._$1008.curD)) {
                    ControlPanel.this.setCursor(ControlPanel.this._$1008.curH);
                }
                else {
                    ControlPanel.this.setCursor(ControlPanel.this._$1008.curDCur);
                }
                if (ControlPanel.this._$1008.controler != null) {
                    ControlPanel.this._$1008.controler.toFront();
                }
                ControlPanel.this.IC[1].ImCanvas_mouseDsp(true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                ControlPanel.this.IC[1].ImCanvas_mouseDsp(false);
            }
        });
        this.add(this._$1160, null);
        this.add(this.IC[1], null);
    }
    
    private void _$2332() {
        final String $2510 = this._$2510("inq=smtp&inq=ftpclient&inq=imagememory");
        final int n = $2510.indexOf("SmManualFunc=") + 13;
        if (n > 12 && $2510.substring(n, n + 2).equals("on")) {
            this._$1181 = "on";
        }
        else {
            this._$1181 = "off";
        }
        final int n2 = $2510.indexOf("FcManualFunc=") + 13;
        if (n2 > 12 && $2510.substring(n2, n2 + 2).equals("on")) {
            this._$1178 = "on";
        }
        else {
            this._$1178 = "off";
        }
        final int n3 = $2510.indexOf("ImManualFunc=") + 13;
        if (n3 > 12 && $2510.substring(n3, n3 + 2).equals("on")) {
            this._$1185 = "on";
        }
        else {
            this._$1185 = "off";
        }
        final String $2511 = this._$2510("inq=alarmout&inq=camera");
        final int n4 = $2511.indexOf("Ao1ManualFunc=") + 14;
        if (n4 > 13 && $2511.substring(n4, n4 + 2).equals("on")) {
            this._$164135 = "on";
        }
        else {
            this._$164135 = "off";
        }
        final int n5 = $2511.indexOf("Ao2ManualFunc=") + 14;
        if (n5 > 13 && $2511.substring(n5, n5 + 2).equals("on")) {
            this._$164141 = "on";
        }
        else {
            this._$164141 = "off";
        }
        final int n6 = $2511.indexOf("DnManualFunc=") + 13;
        if (n6 > 12 && $2511.substring(n6, n6 + 2).equals("on")) {
            this._$168519 = "on";
        }
        else {
            this._$168519 = "off";
        }
    }
    
    private String _$2510(final String s) {
        final byte[] array = new byte[5000];
        int n = 0;
        if (s.length() != 0) {
            try {
                final URLConnection openConnection = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$1300).append("/command/inquiry.cgi")))).openConnection();
                openConnection.setDoOutput(true);
                if (!this._$1008.uCode.equals("")) {
                    openConnection.setRequestProperty("Authorization", "Basic ".concat(String.valueOf(String.valueOf(this._$1008.uCode))));
                }
                final PrintStream printStream = new PrintStream(openConnection.getOutputStream());
                printStream.print(s);
                printStream.close();
                DataInputStream dataInputStream;
                int read;
                for (dataInputStream = new DataInputStream(openConnection.getInputStream()); (read = dataInputStream.read(array, n, 256)) > 0; n += read) {}
                dataInputStream.close();
            }
            catch (IOException ex) {
                System.out.println("Inq Error2!!");
            }
        }
        return new String(array);
    }
    
    private void _$2744() {
        if (this._$1291.length() != 0) {
            try {
                final URLConnection openConnection = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$1300).append(this._$1286)))).openConnection();
                if (!this._$1008.uCode.equals("")) {
                    openConnection.setRequestProperty("Authorization", "Basic ".concat(String.valueOf(String.valueOf(this._$1008.uCode))));
                }
                openConnection.setDoOutput(true);
                final PrintStream printStream = new PrintStream(openConnection.getOutputStream());
                printStream.print(this._$1291);
                printStream.close();
                new DataInputStream(openConnection.getInputStream()).close();
            }
            catch (IOException ex) {
                System.out.println("Socket Error2!!");
            }
        }
    }
    
    private int _$2352(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        int n = 0;
        if (s2.equals("on")) {
            this._$1160.addItem("e-Mail");
            if (this._$1291 == "") {
                this._$1291 = "Trigger=smtp";
            }
            ++n;
        }
        if (s.equals("on")) {
            this._$1160.addItem("FTP");
            if (this._$1291 == "") {
                this._$1291 = "Trigger=ftp";
            }
            ++n;
        }
        if (s3.equals("on")) {
            this._$1160.addItem("Image memory");
            if (this._$1291 == "") {
                this._$1291 = "Trigger=memory";
            }
            ++n;
        }
        if (s4.equals("on")) {
            this._$1160.addItem("Alarm output1");
            if (this._$1291 == "") {
                this._$1291 = "Trigger=alarmout1";
            }
            ++n;
        }
        if (s5.equals("on")) {
            this._$1160.addItem("Alarm output2");
            if (this._$1291 == "") {
                this._$1291 = "Trigger=alarmout2";
            }
            ++n;
        }
        if (s6.equals("on")) {
            this._$1160.addItem("Day/Night");
            if (this._$1291 == "") {
                this._$1291 = "Trigger=daynight";
            }
            ++n;
        }
        return n;
    }
    
    private void _$2414(final ItemEvent itemEvent) {
        if (this._$1160.getSelectedItem().equals("FTP")) {
            this._$1291 = "Trigger=ftp";
        }
        else if (this._$1160.getSelectedItem().equals("e-Mail")) {
            this._$1291 = "Trigger=smtp";
        }
        else if (this._$1160.getSelectedItem().equals("Image memory")) {
            this._$1291 = "Trigger=memory";
        }
        else if (this._$1160.getSelectedItem().equals("Alarm output1")) {
            this._$1291 = "Trigger=alarmout1";
        }
        else if (this._$1160.getSelectedItem().equals("Alarm output2")) {
            this._$1291 = "Trigger=alarmout2";
        }
        else if (this._$1160.getSelectedItem().equals("Day/Night")) {
            this._$1291 = "Trigger=daynight";
        }
        else {
            this._$1291 = "";
        }
    }
    
    private void _$2470(final MouseEvent mouseEvent) {
        this._$2744();
    }
    
    private void _$2269(final MouseEvent mouseEvent) {
        if (!this._$1008.imageThread.digitalZoom && !this.dZoomBtnPush) {
            this._$1008.setCursor(this._$1266);
            this._$1008.curDCur = this._$1266;
            this.dZoomBtnPush = true;
        }
        else {
            this._$1008.imageThread.dZoomOut();
            this._$1008.setCursor(this._$1253);
            this._$1008.curDCur = this._$1253;
            this.IC[0].imageSelect(false);
            this.dZoomBtnPush = false;
        }
        this.IC[0].ImCanvas_mouseDsp(true);
    }
    
    public void dZoomButton_DispChange(final boolean b) {
        if (this.IC[0] != null) {
            this.IC[0].imageSelect(b);
            this.IC[0].ImCanvas_mouseDsp(false);
        }
    }
    
    private void _$168788(final MouseEvent mouseEvent) {
        this.IC[2].ImCanvas_mouseDsp(this._$168616);
        this._$1008.imageThread.panoramaView.enable();
        if (!this.controlBtnPush) {
            switch (this._$1008.camera.getControl()) {
                case -2: {
                    this.IC[2].ImCanvas_mouseDsp(false);
                    final AlarmDialog alarmDialog = new AlarmDialog(this);
                    alarmDialog.setMessage("Change your browser setting to accept cookies.");
                    alarmDialog.show(100, 100);
                    break;
                }
                case -1: {
                    this.IC[2].ImCanvas_mouseDsp(false);
                    final AlarmDialog alarmDialog2 = new AlarmDialog(this);
                    alarmDialog2.setMessage("After a while, please access again.");
                    alarmDialog2.show(100, 100);
                    break;
                }
                case 0: {
                    this._$168709.setTime(0L);
                    this._$168721.setTime(new Long(Long.MAX_VALUE));
                    break;
                }
                case 1: {
                    final long waitTimeToControl = this._$1008.camera.getWaitTimeToControl();
                    final long controlTime = this._$1008.camera.getControlTime();
                    this._$168709.setTime(new Date().getTime() + waitTimeToControl * 1000);
                    this._$168721.setTime(this._$168709.getTime() + waitTimeToControl + controlTime * 1000);
                    this.IC[2].setImage(this._$168588, this._$168588, this._$168588);
                    this.IC[2].repaint();
                    break;
                }
            }
        }
        else {
            if (this._$1008.controler == null) {
                (this._$1008.controler = new Controler(this._$1008)).setLocation(5, 28);
            }
            this._$1008.controler.setVisible(true);
        }
    }
    
    public void controlStart() {
        if (new Date().after(this._$168709)) {
            this.IC[2].imageSelect(true);
            this.controlBtnPush = true;
            this.IC[2].ImCanvas_mouseDsp(this._$168616);
            this._$1008.imageThread.pictFrame.repaint();
            if (this._$1008.camera.getPresetPositionNumber() > 0 && this._$1008.vModeDetail.isPresetPositionActive()) {
                this._$168630.setVisible(true);
                this._$168649.setVisible(true);
            }
            this.IC[2].setVisible(true);
            this._$168604.setText("Control");
            this._$168689 = true;
            this._$168709.setTime(new Long(Long.MAX_VALUE));
        }
        else if (this._$168709.getTime() < new Long(Long.MAX_VALUE)) {
            this._$168604.setText(String.valueOf((this._$168709.getTime() - new Date().getTime()) / 1000));
        }
    }
    
    public void controlEnd() {
        if (this._$168721.getTime() < new Long(Long.MAX_VALUE)) {
            if (new Date().after(this._$168721)) {
                this.controlBtnPush = false;
                this._$168630.setVisible(false);
                this._$168649.setVisible(false);
                this._$168604.setText("Control");
                this._$168689 = true;
                this.controlButton_DispChange(false);
                this._$168721.setTime(new Long(Long.MAX_VALUE));
                this._$1008.imageThread.pictFrame.repaint();
                if (this._$1008.controler != null) {
                    this._$1008.controler.setVisible(false);
                }
                this._$1008.imageThread.pictFrame.repaint();
            }
            else if (this.controlBtnPush) {
                this._$168604.setText(String.valueOf((this._$168721.getTime() - new Date().getTime()) / 1000));
            }
        }
    }
    
    public void controlButton_DispChange(final boolean b) {
        if (this.IC[2] != null) {
            this.IC[2].imageSelect(b);
            this.IC[2].ImCanvas_mouseDsp(this._$168616);
        }
    }
    
    private void _$2000(final ItemEvent itemEvent) {
        this._$1008.rateControl(this._$1103.getSelectedItem());
    }
    
    private void _$2113(final ItemEvent itemEvent) {
        this._$1008.imageThread.viewSizeChanged(this._$1124.getSelectedIndex());
    }
    
    private void _$168814(final ItemEvent itemEvent) {
        this._$1008.camera.setPresetPosition(this._$1008.camera.getPresetPositionNo(this._$168649.getSelectedIndex() - 1));
    }
    
    public void setFrameRate(int n) {
        if (n == 7) {
            n = 6;
        }
        else if (n >= 8 && n <= 9) {
            n = 8;
        }
        else if (n >= 10 && n <= 14) {
            n = 10;
        }
        else if (n >= 15 && n <= 19) {
            n = 15;
        }
        else if (n >= 20 && n <= 24) {
            n = 20;
        }
        else if (n >= 25 && n <= 29) {
            n = 25;
        }
        final String concat = String.valueOf(String.valueOf(n)).concat(" fps");
        if (this._$1103.getItemCount() != 0) {
            this._$1103.select(concat);
            if (!this._$1103.getSelectedItem().equals(concat)) {
                this._$1103.select(0);
            }
        }
    }
    
    public void setViewSize(final int n) {
        if (this._$1124.getItemCount() != 0) {
            switch (n) {
                case 0: {
                    this._$1124.select("AUTO");
                    break;
                }
                case 1: {
                    this._$1124.select("640x480");
                    break;
                }
                case 2: {
                    this._$1124.select("320x240");
                    break;
                }
                case 3: {
                    this._$1124.select("160x120");
                    break;
                }
            }
        }
    }
    
    public void setPresetPosition(final int n) {
        if (this._$168649.getItemCount() != 0) {
            switch (n) {
                case 0: {
                    this._$168649.select("AUTO");
                    break;
                }
                case 1: {
                    this._$168649.select("640x480");
                    break;
                }
                case 2: {
                    this._$168649.select("320x240");
                    break;
                }
                case 3: {
                    this._$168649.select("160x120");
                    break;
                }
            }
        }
    }
    
    public void trigger_send(final String s) {
        this._$1008.logger.print("Trigger send ".concat(String.valueOf(String.valueOf(s))));
        if (s.equals("PRM_TRIGGER_FTP")) {
            if (this._$1178.equals("on")) {
                this._$1160.select("FTP");
                this._$1291 = "Trigger=ftp";
            }
        }
        else if (s.equals("PRM_TRIGGER_SMTP")) {
            if (this._$1181.equals("on")) {
                this._$1160.select("e-Mail");
                this._$1291 = "Trigger=smtp";
            }
        }
        else if (s.equals("PRM_TRIGGER_MEMORY")) {
            if (this._$1185.equals("on")) {
                this._$1160.select("Image memory");
                this._$1291 = "Trigger=memory";
            }
        }
        else if (s.equals("PRM_TRIGGER_ALARM1")) {
            if (this._$164135.equals("on")) {
                this._$1160.select("Alarm output1");
                this._$1291 = "Trigger=alarmout1";
            }
        }
        else if (s.equals("PRM_TRIGGER_ALARM2")) {
            if (this._$164141.equals("on")) {
                this._$1160.select("Alarm output2");
                this._$1291 = "Trigger=alarmout2";
            }
        }
        else if (s.equals("PRM_TRIGGER_DAYNIGHT") && this._$168519.equals("on")) {
            this._$1160.select("Day/Night");
            this._$1291 = "Trigger=daynight";
        }
        this._$2744();
    }
    
    public void reloadAllIcon(final Color color) {
        for (int i = 0; i < this.IC.length; ++i) {
            if (this.IC[i] != null) {
                this.IC[i].reloadIcon(color);
            }
        }
    }
    
    public void setFRateVisible(final boolean b) {
        if (this._$1087 != null && this._$1103 != null) {
            this._$1087.setVisible(b);
            this._$1103.setVisible(b);
        }
    }
    
    public void setVSizeVisible(final boolean b) {
        if (this._$1114 != null && this._$1124 != null) {
            this._$1114.setVisible(b);
            this._$1124.setVisible(b);
        }
    }
    
    public void setDZoomVisible(final boolean b) {
        if (this.IC[0] != null && this._$1223 != null) {
            this.IC[0].setVisible(b);
            this._$1223.setVisible(b);
        }
    }
    
    public void setControlVisible(final boolean b) {
        if (this.IC[2] != null && this._$168604 != null) {
            this.IC[2].setVisible(b);
            this._$168604.setVisible(b);
        }
    }
    
    public void setPresetPositionVisible(final boolean b) {
        if (this._$168630 != null && this._$168649 != null && this._$1008.camera.getPresetPositionNumber() > 0) {
            this._$168630.setVisible(b);
            this._$168649.setVisible(b);
        }
    }
    
    public void setTriggerVisible(final boolean b) {
        if (this.IC[1] != null && this._$1160 != null) {
            this.IC[1].setVisible(b);
            this._$1160.setVisible(b);
        }
    }
    
    public void setVolumeVisible(final boolean visible) {
        if (this._$1008.volumeGUI != null) {
            this._$1008.volumeGUI.setVisible(visible);
        }
    }
    
    public void setDateVisible(final boolean b) {
        if (this._$1008.imageThread.dateLabel != null) {
            this._$1008.imageThread.dateLabel.setVisible(b);
            this._$1008.imageThread.dispDateFlg = b;
        }
    }
    
    public boolean getControlStatusChanged() {
        return this._$168689;
    }
    
    public void resetControlStatusChanged() {
        this._$168689 = false;
    }
    
    public void setSelectedPresetPosition(final int n) {
        this._$168649.select(n);
    }
}
