// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import java.awt.geom.Point2D;
import prefuse.activity.Pacer;
import prefuse.activity.SlowInSlowOutPacer;
import prefuse.activity.Activity;
import java.awt.Cursor;
import prefuse.Display;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.Point;

public class ZoomingPanControl extends ControlAdapter
{
    private boolean repaint;
    private boolean started;
    private Point mouseDown;
    private Point mouseCur;
    private Point mouseUp;
    private int dx;
    private int dy;
    private double d;
    private double v0;
    private double d0;
    private double d1;
    private double s0;
    private UpdateActivity update;
    private FinishActivity finish;
    
    public ZoomingPanControl() {
        this(true);
    }
    
    public ZoomingPanControl(final boolean repaint) {
        this.repaint = true;
        this.started = false;
        this.d = 0.0;
        this.v0 = 75.0;
        this.d0 = 50.0;
        this.d1 = 400.0;
        this.s0 = 0.1;
        this.update = new UpdateActivity();
        this.finish = new FinishActivity();
        this.repaint = repaint;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            ((Display)mouseEvent.getComponent()).setCursor(Cursor.getPredefinedCursor(13));
            this.mouseDown = mouseEvent.getPoint();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            this.mouseCur = mouseEvent.getPoint();
            this.dx = this.mouseCur.x - this.mouseDown.x;
            this.dy = this.mouseCur.y - this.mouseDown.y;
            this.d = Math.sqrt(this.dx * this.dx + this.dy * this.dy);
            if (!this.started) {
                this.update.setDisplay((Display)mouseEvent.getComponent());
                this.update.run();
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            this.update.cancel();
            this.started = false;
            final Display display = (Display)mouseEvent.getComponent();
            this.mouseUp = mouseEvent.getPoint();
            this.finish.setDisplay(display);
            this.finish.run();
            display.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    private class FinishActivity extends Activity
    {
        private Display display;
        private double scale;
        
        public FinishActivity() {
            super(1500L, 15L, 0L);
            this.setPacingFunction(new SlowInSlowOutPacer());
        }
        
        public void setDisplay(final Display display) {
            this.display = display;
            this.scale = display.getTransform().getScaleX();
            this.setDuration((long)(500.0 + 500.0 * Math.log(1.0 + ((this.scale < 1.0) ? (1.0 / this.scale) : this.scale))));
        }
        
        protected void run(final long n) {
            final double pace = this.getPace(n);
            this.display.zoom(ZoomingPanControl.this.mouseUp, (pace + (1.0 - pace) * this.scale) / this.display.getTransform().getScaleX());
            if (ZoomingPanControl.this.repaint) {
                this.display.repaint();
            }
        }
    }
    
    private class UpdateActivity extends Activity
    {
        private Display display;
        private long lastTime;
        
        public UpdateActivity() {
            super(-1L, 15L, 0L);
            this.lastTime = 0L;
        }
        
        public void setDisplay(final Display display) {
            this.display = display;
        }
        
        protected void run(final long lastTime) {
            final double scaleX = this.display.getTransform().getScaleX();
            double n;
            double access$200;
            if (ZoomingPanControl.this.d <= ZoomingPanControl.this.d0) {
                n = 1.0;
                access$200 = ZoomingPanControl.this.v0 * (ZoomingPanControl.this.d / ZoomingPanControl.this.d0);
            }
            else {
                n = ((ZoomingPanControl.this.d >= ZoomingPanControl.this.d1) ? ZoomingPanControl.this.s0 : Math.pow(ZoomingPanControl.this.s0, (ZoomingPanControl.this.d - ZoomingPanControl.this.d0) / (ZoomingPanControl.this.d1 - ZoomingPanControl.this.d0)));
                access$200 = ZoomingPanControl.this.v0;
            }
            final double n2 = n / scaleX;
            final double n3 = access$200 * (lastTime - this.lastTime) / 1000.0;
            this.lastTime = lastTime;
            this.display.pan(-n3 * ZoomingPanControl.this.dx / ZoomingPanControl.this.d, -n3 * ZoomingPanControl.this.dy / ZoomingPanControl.this.d);
            if (n2 != 1.0) {
                this.display.zoom(ZoomingPanControl.this.mouseCur, n2);
            }
            if (ZoomingPanControl.this.repaint) {
                this.display.repaint();
            }
        }
    }
}
