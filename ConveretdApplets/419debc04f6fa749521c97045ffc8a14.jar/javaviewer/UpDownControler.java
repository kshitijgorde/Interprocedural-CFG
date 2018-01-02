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
    private ImageButton _$170026;
    private ImageButton _$170034;
    private int _$170044;
    final int xpos = 0;
    final int ypos = 1;
    private Viewer _$1008;
    
    public UpDownControler(final Viewer $1008) {
        this._$170044 = 3;
        this._$1008 = $1008;
        this.setLayout(null);
        while ($1008.upward.getWidth(this) == -1) {}
        this.setSize($1008.upward.getWidth(this) * 2 + 3, $1008.upward.getHeight(this));
        this.setBackground(this._$1008.PictFrameColor);
        (this._$170026 = new ImageButton($1008.upward, $1008.upwardH, this._$1008.PictFrameColor)).addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                UpDownControler.this._$1008.imageThread.dZoomOut();
                UpDownControler.this._$1008.controlGUI.dZoomButton_DispChange(false);
                --UpDownControler.this._$170044;
                if (UpDownControler.this._$170044 <= 1) {
                    UpDownControler.this._$170044 = 1;
                    UpDownControler.this._$170026.setVisible(false);
                    UpDownControler.this._$170034.setLocation((UpDownControler.this._$170034.getSize().width + 3) / 2, 0);
                }
                else {
                    UpDownControler.this._$170026.setVisible(true);
                    UpDownControler.this._$170034.setLocation(0, 0);
                    UpDownControler.this._$170026.setLocation(UpDownControler.this._$170034.getSize().width + 3, 0);
                }
                UpDownControler.this._$170034.setVisible(true);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                UpDownControler.this._$170026.ImCanvas_mouseDsp(true);
                if (UpDownControler.this._$1008.curDCur.equals(UpDownControler.this._$1008.curD)) {
                    UpDownControler.this.setCursor(UpDownControler.this._$1008.curH);
                }
                else {
                    UpDownControler.this.setCursor(UpDownControler.this._$1008.curDCur);
                }
                if (UpDownControler.this._$1008.controler != null) {
                    UpDownControler.this._$1008.controler.toFront();
                }
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                UpDownControler.this._$170026.ImCanvas_mouseDsp(false);
            }
        });
        (this._$170034 = new ImageButton($1008.downward, $1008.downwardH, this._$1008.PictFrameColor)).addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                UpDownControler.this._$1008.imageThread.dZoomOut();
                UpDownControler.this._$1008.controlGUI.dZoomButton_DispChange(false);
                ++UpDownControler.this._$170044;
                UpDownControler.this._$170034.setLocation(UpDownControler.this._$170034.getSize().width + 3, 0);
                if (UpDownControler.this._$170044 >= 3) {
                    UpDownControler.this._$170044 = 3;
                    UpDownControler.this._$170034.setVisible(false);
                    UpDownControler.this._$170026.setLocation((UpDownControler.this._$170034.getSize().width + 3) / 2, 0);
                }
                else {
                    UpDownControler.this._$170034.setVisible(true);
                    UpDownControler.this._$170034.setLocation(0, 0);
                    UpDownControler.this._$170026.setLocation(UpDownControler.this._$170034.getSize().width + 3, 0);
                }
                UpDownControler.this._$170026.setVisible(true);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                ((ImageButton)mouseEvent.getComponent()).ImCanvas_mouseDsp(true);
                if (UpDownControler.this._$1008.curDCur.equals(UpDownControler.this._$1008.curD)) {
                    UpDownControler.this.setCursor(UpDownControler.this._$1008.curH);
                }
                else {
                    UpDownControler.this.setCursor(UpDownControler.this._$1008.curDCur);
                }
                if (UpDownControler.this._$1008.controler != null) {
                    UpDownControler.this._$1008.controler.toFront();
                }
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                ((ImageButton)mouseEvent.getComponent()).ImCanvas_mouseDsp(false);
            }
        });
        this._$170026.setSize($1008.downward.getWidth(this), $1008.downward.getHeight(this));
        this.add(this._$170026);
        this._$170034.setSize($1008.downward.getWidth(this), $1008.downward.getHeight(this));
        this.add(this._$170034);
        this._$170026.setLocation((this._$170034.getSize().width + 3) / 2, 0);
        this._$170034.setVisible(false);
    }
    
    public void changeColor(final Color background) {
        this.setBackground(background);
        this._$170026.reloadIcon(background);
        this._$170034.reloadIcon(background);
        this.repaint();
    }
    
    public int getLevel() {
        return this._$170044;
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
}
