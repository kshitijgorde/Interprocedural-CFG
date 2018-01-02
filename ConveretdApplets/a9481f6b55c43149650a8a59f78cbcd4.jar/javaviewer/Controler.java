// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.Event;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Image;
import java.awt.Frame;

public class Controler extends Frame
{
    private int _$4554;
    private Image[][] _$1493;
    public ImageButton[] controlButton;
    public Color backColor;
    public Color backColor2;
    public Insets frameInsets;
    public Panel closingPanel;
    private Viewer _$4555;
    private ControlTimer _$1501;
    private boolean _$4556;
    private int _$1503;
    private boolean _$1504;
    private boolean _$1505;
    private String[] _$1506;
    private String[] _$1507;
    private int[] _$1508;
    private String[] _$1509;
    int jawHeight;
    
    public Controler(final Viewer $4555) {
        this._$4554 = 15;
        this._$1493 = new Image[this._$4554][2];
        this.controlButton = new ImageButton[15];
        this.backColor = new Color(75, 110, 182);
        this.backColor2 = new Color(75, 110, 182);
        this._$4556 = false;
        this._$1504 = false;
        this._$1505 = false;
        this._$1506 = new String[] { "up-left", "up", "up-right", "left", "", "right", "down-left", "down", "down-right", "wide", "tele", "near", "far", "onepushaf" };
        this._$1507 = new String[] { "motor", "motor", "motor", "motor", "", "motor", "motor", "motor", "motor", "zoom", "zoom", "focus", "focus", "" };
        this._$1508 = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 0, 0, 0 };
        this._$1509 = new String[] { "07", "08", "09", "04", "", "06", "01", "02", "03", "10", "11", "", "", "" };
        this.jawHeight = 20;
        this._$4555 = $4555;
        this.backColor = $4555.backColor;
        $4555.logger.print("controler beginig color = " + this.backColor.toString());
        this.setLayout(null);
        this.setVisible(true);
        this.frameInsets = this.getInsets();
        this.setSize(144 + this.frameInsets.left + this.frameInsets.right, 301 + this.frameInsets.top + this.frameInsets.bottom + this.jawHeight);
        this._$1516(this);
        this.setBackground($4555.backColor);
        this.setResizable(false);
        this.setVisible(false);
        final WindowAdapter windowAdapter = new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                Controler.this.setVisible(false);
            }
        };
        if ($4555.camera.ptzfMode) {
            (this._$1501 = new ControlTimer(this)).start();
        }
    }
    
    private void _$1893(final Color color, final Color background) {
        this.setBackground(background);
        for (int i = 0; i < 14; ++i) {
            this.controlButton[i] = new ImageButton(this._$4555.imagesForControler[i][0], this._$4555.imagesForControler[i][1], background);
        }
        this._$4555.logger.print("*************");
        this.controlButton[14] = new ImageButton(this._$4555.imagesForControler[14][0], this._$4555.imagesForControler[14][1], this.backColor2);
        this._$4555.logger.print("===============");
    }
    
    public void changeColor(final Color color, final Color background) {
        this.setBackground(background);
        final Color $1522 = this._$1522(background);
        this.closingPanel.setBackground($1522);
        for (int i = 0; i < 14; ++i) {
            this.controlButton[i].reloadIcon(background);
        }
        this.controlButton[14].reloadIcon($1522);
    }
    
    private Color _$1522(final Color color) {
        int n = color.getRed() - 19;
        if (n < 0) {
            n = 0;
        }
        int n2 = color.getGreen() - 21;
        if (n2 < 0) {
            n2 = 0;
        }
        int n3 = color.getBlue() - 25;
        if (n3 < 0) {
            n3 = 0;
        }
        return new Color(n, n2, n3);
    }
    
    private void _$1516(final Controler controler) {
        this.backColor2 = this._$1522(this.backColor);
        this._$1893(new Color(75, 110, 182), this.backColor);
        final int n = (this.getWidth() - this.frameInsets.left - this.frameInsets.right - 105) / 2;
        int n2 = this.frameInsets.top + 14;
        for (int i = 0; i < 3; ++i) {
            int n3 = n;
            for (int j = 0; j < 3; ++j) {
                this.controlButton[i * 3 + j].setBounds(new Rectangle(n3, n2, 35, 35));
                n3 += 38;
            }
            n2 += 38;
        }
        int n4 = n2 + 14;
        for (int k = 0; k < 2; ++k) {
            int n5 = n;
            for (int l = 0; l < 2; ++l) {
                this.controlButton[9 + k * 2 + l].setBounds(new Rectangle(n5, n4, 54, 25));
                n5 += 57;
            }
            n4 += 41;
        }
        final int n6 = n;
        final int n7 = n4 - 9;
        this.controlButton[13].setBounds(new Rectangle(n6, n7, 111, 25));
        final int n8 = (this.getWidth() - this.frameInsets.left - this.frameInsets.right - 55) / 2;
        final int n9 = n7 + 50;
        for (int n10 = 0; n10 < 14; ++n10) {
            this.add(this.controlButton[n10], null);
            this.controlButton[n10].setName("ctlbtn" + String.valueOf(n10));
            this.controlButton[n10].addMouseListener(new MouseAdapter() {
                public void mousePressed(final MouseEvent mouseEvent) {
                    Controler.this._$4555.controlGUI.dzoomCanceled();
                    Controler.this._$4415(mouseEvent);
                    Controler.this._$1505 = true;
                }
                
                public void mouseReleased(final MouseEvent mouseEvent) {
                    Controler.this._$4416(mouseEvent);
                    Controler.this._$1505 = false;
                }
                
                public void mouseEntered(final MouseEvent mouseEvent) {
                    ((ImageButton)mouseEvent.getComponent()).ImCanvas_mouseDsp(true);
                    Controler.this.setCursor(Controler.this._$4555.curH);
                }
                
                public void mouseExited(final MouseEvent mouseEvent) {
                    if (Controler.this._$1505) {
                        Controler.this._$4416(mouseEvent);
                        Controler.this._$1505 = false;
                    }
                    ((ImageButton)mouseEvent.getComponent()).ImCanvas_mouseDsp(false);
                    Controler.this.setCursor(Controler.this._$4555.curDCur);
                }
            });
        }
        (this.closingPanel = new Panel()).setLayout(null);
        this.closingPanel.setSize(170, 63 + this.jawHeight);
        this.closingPanel.setBackground(this.backColor2);
        this.closingPanel.add(this.controlButton[14], null);
        this.controlButton[14].setBounds(new Rectangle(n6, n9, 55, 25));
        this.controlButton[14].setLocation((this.getWidth() - 55) / 2, 15);
        this.controlButton[14].addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                mouseEvent.getComponent().getParent().getParent().setVisible(false);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                ((ImageButton)mouseEvent.getComponent()).ImCanvas_mouseDsp(true);
                Controler.this.setCursor(Controler.this._$4555.curH);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                ((ImageButton)mouseEvent.getComponent()).ImCanvas_mouseDsp(false);
                Controler.this.setCursor(Controler.this._$4555.curDCur);
            }
        });
        this.closingPanel.setLocation(0, this.getHeight() - 56 - this.jawHeight);
        this.add(this.closingPanel, null);
        if (this._$4555.camera.getFocusType().equals("auto")) {
            this.controlButton[11].setVisible(false);
            this.controlButton[12].setVisible(false);
            this.controlButton[13].setVisible(false);
        }
    }
    
    private void _$4415(final MouseEvent mouseEvent) {
        final int int1 = Integer.parseInt(mouseEvent.getComponent().getName().substring(6));
        if (this._$4555.camera.ptzfMode && this._$1509[int1] != "") {
            if (!this._$4556) {
                this._$4556 = true;
                this._$1503 = int1;
                this._$1501.setTimer(1);
            }
        }
        else {
            this._$1504 = this._$4565(int1);
        }
    }
    
    private void _$4416(final MouseEvent mouseEvent) {
        final int int1 = Integer.parseInt(mouseEvent.getComponent().getName().substring(6));
        if (this._$4555.camera.ptzfMode && this._$4556) {
            this._$1501.clearTimer();
            this._$1504 = this._$1537(int1);
            this._$4556 = false;
            this._$1503 = 0;
            return;
        }
        this._$1504 = this._$4566(int1);
    }
    
    private boolean _$4565(final int n) {
        boolean b = false;
        switch (n) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10: {
                this._$4555.controlGUI.setSelectedPresetPosition(0);
            }
            case 11:
            case 12: {
                this._$686("/command/ptzf.cgi", "Move=" + this._$1506[n] + "," + String.valueOf(this._$1508[n]), false);
                b = true;
                break;
            }
            case 13: {
                this._$686("/command/ptzf.cgi", "Move=" + this._$1506[n] + "," + String.valueOf(this._$1508[n]), false);
                break;
            }
            case 4: {
                this._$4555.controlGUI.setSelectedPresetPosition(0);
                this._$686("/command/presetposition.cgi", "HomePos=ptz-recall", false);
                break;
            }
        }
        return b;
    }
    
    private boolean _$4566(final int n) {
        final boolean b = false;
        switch (n) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12: {
                this._$686("/command/ptzf.cgi", "Move=stop," + this._$1507[n], false);
                this._$1504 = true;
                break;
            }
        }
        return b;
    }
    
    private boolean _$1537(final int n) {
        final boolean b = false;
        this._$4555.controlGUI.setSelectedPresetPosition(0);
        if (this._$1509[n] != "") {
            final String string = "Relative=" + this._$1509[n];
            String s;
            if (n <= 8) {
                s = string + this._$4555.camera.relPanTilt;
            }
            else {
                s = string + this._$4555.camera.relZoom;
            }
            this._$686("/command/ptzf.cgi", s, false);
        }
        return b;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            try {
                this._$1501.clearTimer();
            }
            catch (Exception ex) {}
            this.hide();
        }
        return true;
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
    
    public void callBackPrc() {
        if (this._$4556) {
            this._$1504 = this._$4565(this._$1503);
            this._$1503 = 0;
            this._$4556 = false;
        }
    }
    
    private void _$686(final String s, final String s2, final boolean b) {
        while (!this._$4555.commandSend.ready) {
            try {
                Thread.sleep(0L, 1);
            }
            catch (Exception ex) {}
        }
        synchronized (this._$4555.commandSend) {
            this._$4555.commandSend.setCommand(s, s2);
            this._$4555.commandSend.notify();
        }
    }
}
