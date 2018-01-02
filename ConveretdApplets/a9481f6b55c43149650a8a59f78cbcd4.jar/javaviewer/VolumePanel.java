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
    private Viewer _$4305;
    private ImageButton _$4518;
    private VolumeControl _$1930;
    private Image _$4572;
    private Image _$4573;
    private Image _$4574;
    private Image _$4575;
    private Image _$4576;
    private Image _$4577;
    private Image _$4578;
    private boolean _$1708;
    private boolean _$4579;
    private Cursor _$1267;
    private Cursor _$1265;
    private Cursor _$1336;
    
    public VolumePanel(final Viewer $4305, final boolean $4306, final ViewModeDetail viewModeDetail) {
        this.color_back = new Color(75, 110, 182);
        this.color_fore = new Color(255, 255, 255);
        this._$1708 = false;
        this._$4579 = false;
        this._$1267 = new Cursor(12);
        this._$1265 = this.getCursor();
        this._$1336 = new Cursor(1);
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
        this._$4305 = $4305;
        this._$1708 = $4306;
        if (this._$1708) {
            this._$4581();
        }
        this.setAllBackground(this.color_back = this._$4305.CtrlBackColor);
        this.setAllForeground(this.color_fore);
        this.reloadIcon(this.color_back);
        this._$4308();
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                VolumePanel.this._$4305.controlGUI.dzoomCanceled();
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                VolumePanel.this.setCursor(VolumePanel.this._$4305.curDCur);
                if (VolumePanel.this._$4305.controler != null) {
                    VolumePanel.this._$4305.controler.toFront();
                }
                VolumePanel.this._$4536(false, true);
            }
        });
    }
    
    private void _$4308() {
        if (this._$4305.pMuteFlg) {
            this.muteButton_DispChange(true);
            this._$4579 = true;
        }
        if (this._$4305.pVolumeFlg) {
            this.volume_dspValue(this._$4305.pVolume);
        }
    }
    
    public void destroy() {
        this._$4518 = null;
        this._$4305 = null;
        this._$4572 = null;
        this._$4573 = null;
        this._$4574 = null;
        this._$4575 = null;
        this._$4576 = null;
        this._$4577 = null;
        this._$4578 = null;
    }
    
    public void setAllBackground(final Color background) {
        this.setBackground(background);
        this._$1930.reloadIcon(background);
    }
    
    public void setAllForeground(final Color foreground) {
        this.setForeground(foreground);
    }
    
    public void reloadIcon(final Color color) {
        this._$1930.reloadIcon(color);
        this._$4518.reloadIcon(color);
    }
    
    private void _$4581() {
        this._$4574 = this._$4305.getImage(this._$4305.getCodeBase(), "sound_bar.gif");
        this._$4572 = this._$4305.getImage(this._$4305.getCodeBase(), "sound_handle.gif");
        this._$4573 = this._$4305.getImage(this._$4305.getCodeBase(), "sound_handle_highlight.gif");
        (this._$1930 = new VolumeControl(this._$4574, this._$4572, this._$4573, this.color_back, this._$4305)).setBounds(new Rectangle(38, 0, 93, 24));
        this._$1930.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                VolumePanel.this._$4305.controlGUI.dzoomCanceled();
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    VolumePanel.this.volume_mouseDragged(mouseEvent.getX() - 4);
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (VolumePanel.this._$4305.curDCur.equals(VolumePanel.this._$4305.curD)) {
                    VolumePanel.this.setCursor(VolumePanel.this._$4305.curH);
                }
                else {
                    VolumePanel.this.setCursor(VolumePanel.this._$4305.curDCur);
                }
                if (VolumePanel.this._$4305.controler != null) {
                    VolumePanel.this._$4305.controler.toFront();
                }
                VolumePanel.this._$4536(false, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                VolumePanel.this._$4536(false, false);
            }
        });
        this._$1930.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    VolumePanel.this.volume_mouseDragged(mouseEvent.getX() - 4);
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (VolumePanel.this._$4305.curDCur.equals(VolumePanel.this._$4305.curD)) {
                    VolumePanel.this.setCursor(VolumePanel.this._$4305.curH);
                }
                else {
                    VolumePanel.this.setCursor(VolumePanel.this._$4305.curDCur);
                }
                VolumePanel.this._$4305.controlGUI.dzoomCanceled();
                if (VolumePanel.this._$4305.controler != null) {
                    VolumePanel.this._$4305.controler.toFront();
                }
                VolumePanel.this._$4536(false, true);
            }
        });
        this._$4575 = this._$4305.getImage(this._$4305.getCodeBase(), "mute_off_highlight.gif");
        this._$4576 = this._$4305.getImage(this._$4305.getCodeBase(), "mute_off.gif");
        this._$4577 = this._$4305.getImage(this._$4305.getCodeBase(), "mute_on_highlight.gif");
        this._$4578 = this._$4305.getImage(this._$4305.getCodeBase(), "mute_on.gif");
        (this._$4518 = new ImageButton(this._$4576, this._$4575, this._$4578, this._$4577, this.color_back)).setBounds(new Rectangle(0, 2, 38, 31));
        this._$4518.addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    VolumePanel.this._$4583();
                }
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                VolumePanel.this._$4305.controlGUI.dzoomCanceled();
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (VolumePanel.this._$4305.curDCur.equals(VolumePanel.this._$4305.curD)) {
                    VolumePanel.this.setCursor(VolumePanel.this._$4305.curH);
                }
                else {
                    VolumePanel.this.setCursor(VolumePanel.this._$4305.curDCur);
                }
                if (VolumePanel.this._$4305.controler != null) {
                    VolumePanel.this._$4305.controler.toFront();
                }
                VolumePanel.this._$4536(true, true);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                VolumePanel.this._$4536(true, false);
            }
        });
        this.add(this._$4518, null);
        this.add(this._$1930, null);
    }
    
    private void _$4536(final boolean b, final boolean b2) {
        if (b) {
            this._$4518.ImCanvas_mouseDsp(b2);
        }
    }
    
    private void _$4583() {
        if (!this._$4579) {
            this._$4518.imageSelect(true);
            this._$4305.audioThread.setMute(true);
            this._$4579 = true;
        }
        else {
            this._$4518.imageSelect(false);
            this._$4305.audioThread.setMute(false);
            this._$4579 = false;
        }
        this._$4518.ImCanvas_mouseDsp(true);
    }
    
    public void muteButton_DispChange(final boolean b) {
        if (this._$4518 != null) {
            this._$4518.imageSelect(b);
            this._$4518.ImCanvas_mouseDsp(false);
        }
    }
    
    public void volume_mouseDragged(final int n) {
        if (n >= 1 && n <= 80) {
            this._$1930.barPos_change(n);
            this._$4305.audioThread.setVolume(n * 100 / 80);
            if (this._$4305.audioThread.mute.getValue()) {
                this._$4305.audioThread.mute.setValue(false);
                this._$4305.audioThread.mute.setValue(true);
            }
        }
    }
    
    private void _$4585(final MouseEvent mouseEvent) {
        final int n = mouseEvent.getX() - 4;
        if (n >= 0 && n <= 80) {
            this._$1930.barPos_change(n);
            this._$4305.audioThread.setVolume(n * 100 / 80);
            if (this._$4305.audioThread.mute.getValue()) {
                this._$4305.audioThread.mute.setValue(false);
                this._$4305.audioThread.mute.setValue(true);
            }
        }
    }
    
    public void volume_position_change(final int n, final int n2) {
        this.setBounds(n, n2, 130, 25);
        this.repaint();
    }
    
    public void volume_dspValue(final int n) {
        if (this._$1930 != null) {
            this._$1930.barPos_change(n * 80 / 100);
        }
    }
}
