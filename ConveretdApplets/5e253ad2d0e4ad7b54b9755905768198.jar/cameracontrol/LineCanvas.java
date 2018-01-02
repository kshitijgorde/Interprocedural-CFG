// 
// Decompiled by Procyon v0.5.30
// 

package cameracontrol;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

class LineCanvas extends Canvas
{
    int sec;
    private Color _$3830;
    private Color _$3834;
    
    public LineCanvas(final Color $3830, final Color $3831) {
        this._$3830 = $3830;
        this._$3834 = $3831;
        this.setBackground(this._$3830);
        this.sec = 0;
    }
    
    public void paint(final Graphics graphics) {
        graphics.clearRect(0, 0, 160, 120);
        graphics.setColor(this._$3834);
        graphics.drawLine(80, 0, 80, 120);
        graphics.drawLine(0, 60, 160, 60);
        graphics.setColor(new Color(75, 110, 182));
        if (this.sec >= 0) {
            String s;
            if (this.sec < 10) {
                s = "  ".concat(String.valueOf(String.valueOf(this.sec)));
            }
            else {
                s = "".concat(String.valueOf(String.valueOf(this.sec)));
            }
            graphics.drawString(s, 135, 115);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
