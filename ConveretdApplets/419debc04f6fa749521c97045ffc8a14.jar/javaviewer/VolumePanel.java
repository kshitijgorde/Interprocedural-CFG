// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.LayoutManager;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

public final class VolumePanel extends Applet
{
    public Color color_back;
    public Color color_fore;
    private Viewer _$1008;
    private ImageButton _$1080;
    private VolumeControl _$6748;
    private Image _$10989;
    private Image _$10998;
    private Image _$11008;
    private Image _$11019;
    private Image _$11030;
    private Image _$11041;
    private Image _$11051;
    private boolean _$4592;
    private boolean _$11061;
    private Cursor _$1238;
    private Cursor _$1253;
    private Cursor _$1266;
    
    public VolumePanel(final Viewer $1008, final boolean $1009, final ViewModeDetail viewModeDetail) {
        this.color_back = new Color(75, 110, 182);
        this.color_fore = new Color(255, 255, 255);
        this._$4592 = false;
        this._$11061 = false;
        this._$1238 = new Cursor(12);
        this._$1253 = this.getCursor();
        this._$1266 = new Cursor(1);
        if (!viewModeDetail.isVolumeActive()) {
            return;
        }
        try {
            Thread.currentThread().setPriority(9);
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
        this.setLayout(null);
        this._$1008 = $1008;
        this._$4592 = $1009;
        if (this._$4592) {
            this._$11072();
        }
        this.setAllBackground(this.color_back = this._$1008.CtrlBackColor);
        this.setAllForeground(this.color_fore);
        this.reloadIcon(this.color_back);
        this._$1565();
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                VolumePanel.this._$1008.controlGUI.dzoomCanceled();
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                VolumePanel.this.setCursor(VolumePanel.this._$1008.curDCur);
                if (VolumePanel.this._$1008.controler != null) {
                    VolumePanel.this._$1008.controler.toFront();
                }
                VolumePanel.this._$2304(false, true);
            }
        });
    }
    
    private void _$1565() {
        if (this._$1008.pMuteFlg) {
            this.muteButton_DispChange(true);
            this._$11061 = true;
        }
        if (this._$1008.pVolumeFlg) {
            this.volume_dspValue(this._$1008.pVolume);
        }
    }
    
    public void destroy() {
        this._$1080 = null;
        this._$1008 = null;
        this._$10989 = null;
        this._$10998 = null;
        this._$11008 = null;
        this._$11019 = null;
        this._$11030 = null;
        this._$11041 = null;
        this._$11051 = null;
    }
    
    public void setAllBackground(final Color background) {
        this.setBackground(background);
        this._$6748.reloadIcon(background);
    }
    
    public void setAllForeground(final Color foreground) {
        this.setForeground(foreground);
    }
    
    public void reloadIcon(final Color color) {
        this._$6748.reloadIcon(color);
        this._$1080.reloadIcon(color);
    }
    
    private void _$11072() {
        this._$11008 = this._$1008.getImage(this._$1008.getCodeBase(), "sound_bar.gif");
        this._$10989 = this._$1008.getImage(this._$1008.getCodeBase(), "sound_handle.gif");
        this._$10998 = this._$1008.getImage(this._$1008.getCodeBase(), "sound_handle_highlight.gif");
        (this._$6748 = new VolumeControl(this._$11008, this._$10989, this._$10998, this.color_back, this._$1008)).setBounds(new Rectangle(38, 0, 93, 24));
        this._$6748.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                VolumePanel.this._$1008.controlGUI.dzoomCanceled();
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    VolumePanel.this.volume_mouseDragged(mouseEvent.getX() - 4);
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (VolumePanel.this._$1008.curDCur.equals(VolumePanel.this._$1008.curD)) {
                    VolumePanel.this.setCursor(VolumePanel.this._$1008.curH);
                }
                else {
                    VolumePanel.this.setCursor(VolumePanel.this._$1008.curDCur);
                }
                if (VolumePanel.this._$1008.controler != null) {
                    VolumePanel.this._$1008.controler.toFront();
                }
                VolumePanel.this._$2304(false, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                VolumePanel.this._$2304(false, false);
            }
        });
        this._$6748.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    VolumePanel.this.volume_mouseDragged(mouseEvent.getX() - 4);
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (VolumePanel.this._$1008.curDCur.equals(VolumePanel.this._$1008.curD)) {
                    VolumePanel.this.setCursor(VolumePanel.this._$1008.curH);
                }
                else {
                    VolumePanel.this.setCursor(VolumePanel.this._$1008.curDCur);
                }
                VolumePanel.this._$1008.controlGUI.dzoomCanceled();
                if (VolumePanel.this._$1008.controler != null) {
                    VolumePanel.this._$1008.controler.toFront();
                }
                VolumePanel.this._$2304(false, true);
            }
        });
        this._$11019 = this._$1008.getImage(this._$1008.getCodeBase(), "mute_off_highlight.gif");
        this._$11030 = this._$1008.getImage(this._$1008.getCodeBase(), "mute_off.gif");
        this._$11041 = this._$1008.getImage(this._$1008.getCodeBase(), "mute_on_highlight.gif");
        this._$11051 = this._$1008.getImage(this._$1008.getCodeBase(), "mute_on.gif");
        (this._$1080 = new ImageButton(this._$11030, this._$11019, this._$11051, this._$11041, this.color_back)).setBounds(new Rectangle(0, 2, 38, 31));
        this._$1080.addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    VolumePanel.this._$11111();
                }
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                VolumePanel.this._$1008.controlGUI.dzoomCanceled();
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (VolumePanel.this._$1008.curDCur.equals(VolumePanel.this._$1008.curD)) {
                    VolumePanel.this.setCursor(VolumePanel.this._$1008.curH);
                }
                else {
                    VolumePanel.this.setCursor(VolumePanel.this._$1008.curDCur);
                }
                if (VolumePanel.this._$1008.controler != null) {
                    VolumePanel.this._$1008.controler.toFront();
                }
                VolumePanel.this._$2304(true, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                VolumePanel.this._$2304(true, false);
            }
        });
        this.add(this._$1080, null);
        this.add(this._$6748, null);
    }
    
    private void _$2304(final boolean b, final boolean b2) {
        if (b) {
            this._$1080.ImCanvas_mouseDsp(b2);
        }
    }
    
    private void _$11111() {
        if (!this._$11061) {
            this._$1080.imageSelect(true);
            this._$1008.audioThread.setMute(true);
            this._$11061 = true;
        }
        else {
            this._$1080.imageSelect(false);
            this._$1008.audioThread.setMute(false);
            this._$11061 = false;
        }
        this._$1080.ImCanvas_mouseDsp(true);
    }
    
    public void muteButton_DispChange(final boolean b) {
        if (this._$1080 != null) {
            this._$1080.imageSelect(b);
            this._$1080.ImCanvas_mouseDsp(false);
        }
    }
    
    public void volume_mouseDragged(final int n) {
        if (n >= 1 && n <= 80) {
            this._$6748.barPos_change(n);
            this._$1008.audioThread.setVolume(n * 100 / 80);
            if (this._$1008.audioThread.mute.getValue()) {
                this._$1008.audioThread.mute.setValue(false);
                this._$1008.audioThread.mute.setValue(true);
            }
        }
    }
    
    private void _$11134(final MouseEvent mouseEvent) {
        final int n = mouseEvent.getX() - 4;
        if (n >= 0 && n <= 80) {
            this._$6748.barPos_change(n);
            this._$1008.audioThread.setVolume(n * 100 / 80);
            if (this._$1008.audioThread.mute.getValue()) {
                this._$1008.audioThread.mute.setValue(false);
                this._$1008.audioThread.mute.setValue(true);
            }
        }
    }
    
    public void volume_position_change(final int n, final int n2) {
        this.setBounds(n, n2, 130, 25);
        this.repaint();
    }
    
    public void volume_dspValue(final int n) {
        if (this._$6748 != null) {
            this._$6748.barPos_change(n * 80 / 100);
        }
    }
}
