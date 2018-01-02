// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.event.ActionEvent;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.Point;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.awt.event.MouseAdapter;

class Autoscroller extends MouseAdapter implements Serializable
{
    transient MouseEvent event;
    transient Timer timer;
    JComponent component;
    
    Autoscroller(final JComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("component must be non null");
        }
        this.component = component;
        this.timer = new Timer(100, new AutoScrollTimerAction());
        this.component.addMouseListener(this);
    }
    
    void dispose() {
        this.stop();
        this.component.removeMouseListener(this);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.component.getVisibleRect().contains(mouseEvent.getX(), mouseEvent.getY())) {
            if (this.timer.isRunning()) {
                this.stop();
            }
        }
        else {
            final Point locationOnScreen = this.component.getLocationOnScreen();
            this.event = new MouseEvent(this.component, mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), mouseEvent.getX() + locationOnScreen.x, mouseEvent.getY() + locationOnScreen.y, mouseEvent.getClickCount(), mouseEvent.isPopupTrigger());
            if (!this.timer.isRunning()) {
                this.timer.start();
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.stop();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.timer = new Timer(100, new AutoScrollTimerAction());
    }
    
    void stop() {
        this.timer.stop();
        this.event = null;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }
    
    class AutoScrollTimerAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (!Autoscroller.this.component.isShowing() || Autoscroller.this.event == null) {
                Autoscroller.this.stop();
                return;
            }
            final Point locationOnScreen = Autoscroller.this.component.getLocationOnScreen();
            Autoscroller.this.component.superProcessMouseMotionEvent(new MouseEvent(Autoscroller.this.component, Autoscroller.this.event.getID(), Autoscroller.this.event.getWhen(), Autoscroller.this.event.getModifiers(), Autoscroller.this.event.getX() - locationOnScreen.x, Autoscroller.this.event.getY() - locationOnScreen.y, Autoscroller.this.event.getClickCount(), Autoscroller.this.event.isPopupTrigger()));
        }
    }
}
