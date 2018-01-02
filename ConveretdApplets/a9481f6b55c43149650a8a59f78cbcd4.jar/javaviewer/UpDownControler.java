// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.Panel;

public class UpDownControler extends Panel
{
    private ImageButton _$5113;
    private ImageButton _$1244;
    private int _$1245;
    final int xpos = 0;
    final int ypos = 1;
    private Viewer _$4305;
    
    public UpDownControler(final Viewer $4305) {
        this._$1245 = 3;
        this._$4305 = $4305;
        this.setLayout(null);
        while ($4305.upward.getWidth(this) == -1) {}
        this.setSize($4305.upward.getWidth(this) * 2 + 3, $4305.upward.getHeight(this));
        this.setBackground(this._$4305.PictFrameColor);
        (this._$5113 = new ImageButton($4305.upward, $4305.upwardH, this._$4305.PictFrameColor)).addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                UpDownControler.this._$4305.imageThread.dZoomOut();
                UpDownControler.this._$4305.controlGUI.dZoomButton_DispChange(false);
                UpDownControler.this._$1245--;
                if (UpDownControler.this._$1245 <= 1) {
                    UpDownControler.this._$1245 = 1;
                    UpDownControler.this._$5113.setVisible(false);
                    UpDownControler.this._$1244.setLocation((UpDownControler.this._$1244.getSize().width + 3) / 2, 0);
                }
                else {
                    UpDownControler.this._$5113.setVisible(true);
                    UpDownControler.this._$1244.setLocation(0, 0);
                    UpDownControler.this._$5113.setLocation(UpDownControler.this._$1244.getSize().width + 3, 0);
                }
                UpDownControler.this._$1244.setVisible(true);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                UpDownControler.this._$5113.ImCanvas_mouseDsp(true);
                if (UpDownControler.this._$4305.curDCur.equals(UpDownControler.this._$4305.curD)) {
                    UpDownControler.this.setCursor(UpDownControler.this._$4305.curH);
                }
                else {
                    UpDownControler.this.setCursor(UpDownControler.this._$4305.curDCur);
                }
                if (UpDownControler.this._$4305.controler != null) {
                    UpDownControler.this._$4305.controler.toFront();
                }
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                UpDownControler.this._$5113.ImCanvas_mouseDsp(false);
            }
        });
        (this._$1244 = new ImageButton($4305.downward, $4305.downwardH, this._$4305.PictFrameColor)).addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                UpDownControler.this._$4305.imageThread.dZoomOut();
                UpDownControler.this._$4305.controlGUI.dZoomButton_DispChange(false);
                UpDownControler.this._$1245++;
                UpDownControler.this._$1244.setLocation(UpDownControler.this._$1244.getSize().width + 3, 0);
                if (UpDownControler.this._$1245 >= 3) {
                    UpDownControler.this._$1245 = 3;
                    UpDownControler.this._$1244.setVisible(false);
                    UpDownControler.this._$5113.setLocation((UpDownControler.this._$1244.getSize().width + 3) / 2, 0);
                }
                else {
                    UpDownControler.this._$1244.setVisible(true);
                    UpDownControler.this._$1244.setLocation(0, 0);
                    UpDownControler.this._$5113.setLocation(UpDownControler.this._$1244.getSize().width + 3, 0);
                }
                UpDownControler.this._$5113.setVisible(true);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                ((ImageButton)mouseEvent.getComponent()).ImCanvas_mouseDsp(true);
                if (UpDownControler.this._$4305.curDCur.equals(UpDownControler.this._$4305.curD)) {
                    UpDownControler.this.setCursor(UpDownControler.this._$4305.curH);
                }
                else {
                    UpDownControler.this.setCursor(UpDownControler.this._$4305.curDCur);
                }
                if (UpDownControler.this._$4305.controler != null) {
                    UpDownControler.this._$4305.controler.toFront();
                }
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                ((ImageButton)mouseEvent.getComponent()).ImCanvas_mouseDsp(false);
            }
        });
        this._$5113.setSize($4305.downward.getWidth(this), $4305.downward.getHeight(this));
        this.add(this._$5113);
        this._$1244.setSize($4305.downward.getWidth(this), $4305.downward.getHeight(this));
        this.add(this._$1244);
        this._$5113.setLocation((this._$1244.getSize().width + 3) / 2, 0);
        this._$1244.setVisible(false);
    }
    
    public void changeColor(final Color background) {
        this.setBackground(background);
        this._$5113.reloadIcon(background);
        this._$1244.reloadIcon(background);
        this.repaint();
    }
    
    public int getLevel() {
        return this._$1245;
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
}
