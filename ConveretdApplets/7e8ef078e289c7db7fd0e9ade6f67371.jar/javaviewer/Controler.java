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
    private int _$166037;
    private Image[][] _$166046;
    public ImageButton[] controlButton;
    public Color backColor;
    public Color backColor2;
    public Insets frameInsets;
    public Panel closingPanel;
    private Viewer _$166115;
    private ControlTimer _$209729;
    private boolean _$209734;
    private int _$209742;
    private boolean _$166327;
    private boolean _$209746;
    private String[] _$166121;
    private String[] _$166135;
    private int[] _$166146;
    private String[] _$209759;
    int jawHeight;
    
    public Controler(final Viewer $166115) {
        this._$166037 = 15;
        this._$166046 = new Image[this._$166037][2];
        this.controlButton = new ImageButton[15];
        this.backColor = new Color(75, 110, 182);
        this.backColor2 = new Color(75, 110, 182);
        this._$209734 = false;
        this._$166327 = false;
        this._$209746 = false;
        this._$166121 = new String[] { "up-left", "up", "up-right", "left", "", "right", "down-left", "down", "down-right", "wide", "tele", "near", "far", "onepushaf" };
        this._$166135 = new String[] { "motor", "motor", "motor", "motor", "", "motor", "motor", "motor", "motor", "zoom", "zoom", "focus", "focus", "" };
        this._$166146 = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 0, 0, 0 };
        this._$209759 = new String[] { "07", "08", "09", "04", "", "06", "01", "02", "03", "10", "11", "", "", "" };
        this.jawHeight = 20;
        this._$166115 = $166115;
        this.backColor = $166115.backColor;
        $166115.logger.print("controler beginig color = ".concat(String.valueOf(String.valueOf(this.backColor.toString()))));
        this.setLayout(null);
        this.setVisible(true);
        this.frameInsets = this.getInsets();
        this.setSize(144 + this.frameInsets.left + this.frameInsets.right, 301 + this.frameInsets.top + this.frameInsets.bottom + this.jawHeight);
        this._$166185(this);
        this.setBackground($166115.backColor);
        this.setResizable(false);
        this.setVisible(false);
        final WindowAdapter windowAdapter = new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                Controler.this.setVisible(false);
            }
        };
        if ($166115.camera.ptzfMode) {
            (this._$209729 = new ControlTimer(this)).start();
        }
    }
    
    private void _$71011(final Color color, final Color background) {
        this.setBackground(background);
        for (int i = 0; i < 14; ++i) {
            this.controlButton[i] = new ImageButton(this._$166115.imagesForControler[i][0], this._$166115.imagesForControler[i][1], background);
        }
        this._$166115.logger.print("*************");
        this.controlButton[14] = new ImageButton(this._$166115.imagesForControler[14][0], this._$166115.imagesForControler[14][1], this.backColor2);
        this._$166115.logger.print("===============");
    }
    
    public void changeColor(final Color color, final Color background) {
        this.setBackground(background);
        final Color $166236 = this._$166236(background);
        this.closingPanel.setBackground($166236);
        for (int i = 0; i < 14; ++i) {
            this.controlButton[i].reloadIcon(background);
        }
        this.controlButton[14].reloadIcon($166236);
    }
    
    private Color _$166236(final Color color) {
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
    
    private void _$166185(final Controler controler) {
        this.backColor2 = this._$166236(this.backColor);
        this._$71011(new Color(75, 110, 182), this.backColor);
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
            this.controlButton[n10].setName("ctlbtn".concat(String.valueOf(String.valueOf(String.valueOf(n10)))));
            this.controlButton[n10].addMouseListener(new MouseAdapter() {
                public void mousePressed(final MouseEvent mouseEvent) {
                    Controler.this._$166115.controlGUI.dzoomCanceled();
                    Controler.this._$166283(mouseEvent);
                    Controler.this._$209746 = true;
                }
                
                public void mouseReleased(final MouseEvent mouseEvent) {
                    Controler.this._$166295(mouseEvent);
                    Controler.this._$209746 = false;
                }
                
                public void mouseEntered(final MouseEvent mouseEvent) {
                    ((ImageButton)mouseEvent.getComponent()).ImCanvas_mouseDsp(true);
                    Controler.this.setCursor(Controler.this._$166115.curH);
                }
                
                public void mouseExited(final MouseEvent mouseEvent) {
                    if (Controler.this._$209746) {
                        Controler.this._$166295(mouseEvent);
                        Controler.this._$209746 = false;
                    }
                    ((ImageButton)mouseEvent.getComponent()).ImCanvas_mouseDsp(false);
                    Controler.this.setCursor(Controler.this._$166115.curDCur);
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
                Controler.this.setCursor(Controler.this._$166115.curH);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                ((ImageButton)mouseEvent.getComponent()).ImCanvas_mouseDsp(false);
                Controler.this.setCursor(Controler.this._$166115.curDCur);
            }
        });
        this.closingPanel.setLocation(0, this.getHeight() - 56 - this.jawHeight);
        this.add(this.closingPanel, null);
        if (this._$166115.camera.getFocusType().equals("auto")) {
            this.controlButton[11].setVisible(false);
            this.controlButton[12].setVisible(false);
            this.controlButton[13].setVisible(false);
        }
    }
    
    private void _$166283(final MouseEvent mouseEvent) {
        final int int1 = Integer.parseInt(mouseEvent.getComponent().getName().substring(6));
        if (this._$166115.camera.ptzfMode && this._$209759[int1] != "") {
            if (!this._$209734) {
                this._$209734 = true;
                this._$209742 = int1;
                this._$209729.setTimer(1);
            }
        }
        else {
            this._$166327 = this._$209802(int1);
        }
    }
    
    private void _$166295(final MouseEvent mouseEvent) {
        final int int1 = Integer.parseInt(mouseEvent.getComponent().getName().substring(6));
        if (this._$166115.camera.ptzfMode && this._$209734) {
            this._$209729.clearTimer();
            this._$166327 = this._$209825(int1);
            this._$209734 = false;
            this._$209742 = 0;
            return;
        }
        this._$166327 = this._$209844(int1);
    }
    
    private boolean _$209802(final int n) {
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
                this._$166115.controlGUI.setSelectedPresetPosition(0);
            }
            case 11:
            case 12: {
                this._$5281("/command/ptzf.cgi", String.valueOf(String.valueOf(new StringBuffer("Move=").append(this._$166121[n]).append(",").append(String.valueOf(this._$166146[n])))), false);
                b = true;
                break;
            }
            case 13: {
                this._$5281("/command/ptzf.cgi", String.valueOf(String.valueOf(new StringBuffer("Move=").append(this._$166121[n]).append(",").append(String.valueOf(this._$166146[n])))), false);
                break;
            }
            case 4: {
                this._$166115.controlGUI.setSelectedPresetPosition(0);
                this._$5281("/command/presetposition.cgi", "HomePos=ptz-recall", false);
                break;
            }
        }
        return b;
    }
    
    private boolean _$209844(final int n) {
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
                this._$5281("/command/ptzf.cgi", "Move=stop,".concat(String.valueOf(String.valueOf(this._$166135[n]))), false);
                this._$166327 = true;
                break;
            }
        }
        return b;
    }
    
    private boolean _$209825(final int n) {
        final boolean b = false;
        this._$166115.controlGUI.setSelectedPresetPosition(0);
        if (this._$209759[n] != "") {
            final String concat = "Relative=".concat(String.valueOf(String.valueOf(this._$209759[n])));
            String s;
            if (n <= 8) {
                s = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(this._$166115.camera.relPanTilt)));
            }
            else {
                s = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(this._$166115.camera.relZoom)));
            }
            this._$5281("/command/ptzf.cgi", s, false);
        }
        return b;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            try {
                this._$209729.clearTimer();
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
        if (this._$209734) {
            this._$166327 = this._$209802(this._$209742);
            this._$209742 = 0;
            this._$209734 = false;
        }
    }
    
    private void _$5281(final String s, final String s2, final boolean b) {
        while (!this._$166115.commandSend.ready) {
            try {
                Thread.sleep(0L, 1);
            }
            catch (Exception ex) {}
        }
        synchronized (this._$166115.commandSend) {
            this._$166115.commandSend.setCommand(s, s2);
            this._$166115.commandSend.notify();
        }
        // monitorexit(this._$166115.commandSend)
    }
}
