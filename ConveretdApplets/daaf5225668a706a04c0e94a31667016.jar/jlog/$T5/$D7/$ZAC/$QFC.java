// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7.$ZAC;

import java.awt.Cursor;
import jlog.$T5.util.$OKD;
import jlog.$BI.$M4;
import java.awt.event.ContainerEvent;
import jlog.awt.$L.$G9;
import java.awt.Rectangle;
import java.util.EventObject;
import jlog.$T5.$D7.ContainerSupport;
import java.util.Enumeration;
import java.util.EventListener;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentListener;
import jlog.awt.$L.$I9;
import jlog.$T5.$D7.$NKD;
import java.util.Vector;
import java.awt.Container;
import java.util.Hashtable;
import jlog.$H4;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ContainerListener;
import jlog.$T5.util.EventDispatcher;

public class $QFC extends EventDispatcher implements ContainerListener, MouseListener, MouseMotionListener, $H4
{
    public static final int $EAC = 0;
    public static final int $FAC = 1;
    public static final int $NYC = 2;
    public static final int $OYC = 3;
    public static final int $PYC = 4;
    public static final int $JND = 5;
    public static final int $KND = 6;
    int $RJ;
    Hashtable $LND;
    Container $TR;
    int $MND;
    $ANB $QO;
    $KMD $NND;
    Vector $RIB;
    $NKD $MMD;
    $NKD $OND;
    $I9 $HOB;
    ComponentListener $PND;
    
    private synchronized void $AOD(final MouseEvent mouseEvent) {
        Component component = mouseEvent.getComponent();
        final Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
        while (component != this.$TR) {
            final Point location = component.getLocation();
            point.translate(location.x, location.y);
            component = component.getParent();
        }
        if (this.$OND == null) {
            this.$OND = new $NKD(point, new Point(point));
            this.$MMD = this.$OND;
            this.$TR.add(this.$MMD, 0);
        }
        else if (!point.equals(this.$MMD.$TKD())) {
            this.$MMD = new $NKD(this.$MMD, new Point(this.$MMD.$UKD()));
            this.$TR.add(this.$MMD, 0);
        }
        this.$MMD.invalidate();
        this.$MMD.validate();
        this.$MMD.repaint();
        this.$RIB.addElement(this.$MMD);
        if (this.$OND != null && mouseEvent.getClickCount() > 1) {
            this.$YND();
        }
    }
    
    public void $DJC(final $OIC $oic) {
        this.removeListener($oic);
    }
    
    public synchronized void $LJC(final int $rj) {
        if ($rj == this.$RJ) {
            return;
        }
        this.$TND();
        this.$OBB(null, false);
        if ($rj == 0 || this.$RJ == 0) {
            this.setEnabled(this.$TR, $rj == 0);
            this.enableMouseMotion(this.$TR, $rj != 0);
            ContainerSupport.doLayout(this.$TR);
            this.$TR.repaint();
        }
        this.$RJ = $rj;
    }
    
    public synchronized void $OBB(final Enumeration enumeration, final boolean b) {
        if (enumeration == null) {
            final Component[] components = this.$TR.getComponents();
            int length = components.length;
            while (length-- != 0) {
                final Component component = components[length];
                if (!(component instanceof $RMD)) {
                    this.setSelectState(component, b);
                }
            }
        }
        else {
            while (enumeration.hasMoreElements()) {
                final Component component2 = enumeration.nextElement();
                if (!(component2 instanceof $RMD)) {
                    this.setSelectState(component2, b);
                }
            }
        }
    }
    
    public boolean $RLC(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            this.$SSB(new $HJC(this, 5, mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getComponent()));
            return true;
        }
        return false;
    }
    
    public void $SSB(final EventObject eventObject, final EventListener eventListener) {
        (($OIC)eventListener).$GJC(($HJC)eventObject);
    }
    
    synchronized void $TND() {
        if (this.$OND != null) {
            this.$OND = null;
            this.$MMD = null;
            final Enumeration<Component> elements = this.$RIB.elements();
            Rectangle rectangle = null;
            while (elements.hasMoreElements()) {
                final Component component = elements.nextElement();
                if (rectangle == null) {
                    rectangle = component.getBounds();
                }
                else {
                    rectangle = rectangle.union(component.getBounds());
                }
                this.$TR.remove(component);
            }
            this.$RIB.removeAllElements();
            if (rectangle != null) {
                this.$TR.validate();
                this.$TR.repaint(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
        if (this.$NND != null) {
            this.$NND.$QMD();
            this.$NND = null;
        }
    }
    
    public boolean $VND(final Component component) {
        return this.$LND.get(component) != null;
    }
    
    public void $WFC(final $OIC $oic) {
        this.addListener($oic);
    }
    
    public boolean $WND(final MouseEvent mouseEvent) {
        return (mouseEvent.getModifiers() & 0x10) == 0x10;
    }
    
    public boolean $XND(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() > 1) {
            this.$SSB(new $HJC(this, 6, mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getComponent()));
            return true;
        }
        return false;
    }
    
    void $YND() {
        if (this.$OND != null && !this.$OND.$TKD().equals(this.$OND.$UKD())) {
            this.$SSB(new $HJC(this, 1, this.$OND.$BLD()));
        }
        if (this.$NND != null) {
            this.$SSB(new $HJC(this, 0, this.$NND.getBounds()));
        }
        this.$TND();
    }
    
    private synchronized void $ZND(final MouseEvent mouseEvent) {
        final Component component = mouseEvent.getComponent();
        if (component instanceof $RMD) {
            return;
        }
        if (!mouseEvent.isShiftDown() && !mouseEvent.isControlDown()) {
            this.$OBB(null, false);
        }
        if (component == this.$TR) {
            return;
        }
        if (mouseEvent.isControlDown()) {
            this.setSelectState(component, this.$VND(component) ^ true);
        }
        else {
            this.setSelectState(component, true);
        }
    }
    
    public $QFC(final Container container, final int n) {
        super(null);
        this.$RJ = 0;
        this.$LND = new Hashtable();
        this.$TR = null;
        this.$MND = 3;
        this.$PND = null;
        this.setSource(this);
        this.$RIB = new Vector();
        (this.$HOB = new $I9()).$W9(new $QND(this));
        this.setContainer(container);
        this.$LJC(n);
    }
    
    public synchronized void componentAdded(final ContainerEvent containerEvent) {
        try {
            final Component child = containerEvent.getChild();
            if (child instanceof $RMD) {
                return;
            }
            child.setEnabled(this.$RJ == 0);
            child.addMouseListener(this);
            if (this.$RJ != 0) {
                child.addMouseMotionListener(this);
            }
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public synchronized void componentRemoved(final ContainerEvent containerEvent) {
        try {
            final Component child = containerEvent.getChild();
            if (child instanceof $RMD) {
                return;
            }
            child.removeMouseListener(this);
            if (this.$RJ != 0) {
                child.removeMouseMotionListener(this);
            }
            final $RMD $rmd = this.$LND.get(child);
            if ($rmd != null) {
                $rmd.setComponent(null);
                $rmd.removeComponentListener(this.$PND);
                this.$LND.remove(child);
            }
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public synchronized void enableMouseMotion(final Container container, final boolean b) {
        final Component[] components = container.getComponents();
        int length = components.length;
        if (b) {
            container.addMouseMotionListener(this);
            while (length-- != 0) {
                components[length].addMouseMotionListener(this);
            }
        }
        else {
            container.removeMouseMotionListener(this);
            while (length-- != 0) {
                components[length].removeMouseMotionListener(this);
            }
        }
    }
    
    public synchronized Enumeration getSelected() {
        return new $OKD(this.$LND.keys());
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            this.$RLC(mouseEvent);
            if (!this.$WND(mouseEvent)) {
                this.$TND();
            }
            else if (this.$XND(mouseEvent)) {
                this.$YND();
            }
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        try {
            if (this.$RJ == 6) {
                this.mousePressed(mouseEvent);
            }
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        try {
            final Component component = mouseEvent.getComponent();
            if (component == this.$TR || this.$RJ != 1 || component instanceof $RMD) {
                return;
            }
            component.setCursor(Cursor.getPredefinedCursor(12));
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        try {
            final Component component = mouseEvent.getComponent();
            if (component == this.$TR || component instanceof $RMD) {
                return;
            }
            component.setCursor(Cursor.getPredefinedCursor(0));
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        try {
            if (this.$RJ != 4 || this.$MMD == null) {
                return;
            }
            Component component = mouseEvent.getComponent();
            final Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
            while (component != this.$TR) {
                final Point location = component.getLocation();
                point.translate(location.x, location.y);
                component = component.getParent();
            }
            this.$MMD.$ZKD(point);
            this.$MMD.invalidate();
            this.$MMD.validate();
            this.$MMD.repaint();
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public synchronized void mousePressed(final MouseEvent mouseEvent) {
        try {
            mouseEvent.getComponent().requestFocus();
            if (this.$NND != null) {
                this.$NND.$QMD();
                this.$NND = null;
            }
            this.$RLC(mouseEvent);
            if (!this.$WND(mouseEvent) && this.$RJ != 1) {
                this.$TND();
                return;
            }
            switch (this.$RJ) {
                case 1: {
                    this.$ZND(mouseEvent);
                    break;
                }
                case 2: {
                    this.$NND = new $KMD(this.$TR, mouseEvent.getComponent(), new Point(mouseEvent.getX(), mouseEvent.getY()), this.$QO, 1);
                    break;
                }
                case 3: {
                    this.$NND = new $KMD(this.$TR, mouseEvent.getComponent(), new Point(mouseEvent.getX(), mouseEvent.getY()), this.$QO, 2);
                    break;
                }
                case 5: {
                    this.$NND = new $KMD(this.$TR, mouseEvent.getComponent(), new Point(mouseEvent.getX(), mouseEvent.getY()), this.$QO, 3);
                    break;
                }
                case 4:
                case 6: {
                    this.$AOD(mouseEvent);
                    break;
                }
            }
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public synchronized void mouseReleased(final MouseEvent mouseEvent) {
        try {
            this.$RLC(mouseEvent);
            if (!this.$WND(mouseEvent)) {
                this.$TND();
            }
            else if (this.$RJ == 6 || this.$RJ == 2 || this.$RJ == 3 || this.$RJ == 5) {
                this.$YND();
            }
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public synchronized void setContainer(final Container $tr) {
        if (this.$TR != null) {
            this.$TR.removeContainerListener(this);
            this.$TR.removeMouseListener(this);
            final Component[] components = this.$TR.getComponents();
            int length = components.length;
            while (length-- != 0) {
                components[length].removeMouseListener(this);
            }
            this.enableMouseMotion(this.$TR, false);
            this.$OBB(null, false);
            this.$HOB.removeComponent(this.$TR);
            this.setEnabled(this.$TR, true);
        }
        this.$TR = $tr;
        if (this.$TR != null) {
            this.$TR.addContainerListener(this);
            this.$TR.addMouseListener(this);
            final Component[] components2 = this.$TR.getComponents();
            int length2 = components2.length;
            while (length2-- != 0) {
                components2[length2].addMouseListener(this);
            }
            this.$HOB.$X9(this.$TR);
            this.enableMouseMotion(this.$TR, this.$RJ != 0);
            this.setEnabled(this.$TR, this.$RJ == 0);
        }
    }
    
    public void setEnabled(final Container container, final boolean enabled) {
        final Component[] components = container.getComponents();
        int length = components.length;
        while (length-- != 0) {
            components[length].setEnabled(enabled);
        }
    }
    
    public synchronized void setSelectState(final Component component, final boolean b) {
        final $RMD $rmd = this.$LND.get(component);
        if (b && $rmd == null) {
            final $RMD $rmd2 = new $RMD(component, this.$MND);
            this.$LND.put(component, $rmd2);
            $rmd2.addComponentListener(this.$PND = new $UND(this, component));
            this.$SSB(new $HJC(this, 3, component));
        }
        else if (!b && $rmd != null) {
            $rmd.setComponent(null);
            $rmd.removeComponentListener(this.$PND);
            this.$LND.remove(component);
            this.$SSB(new $HJC(this, 4, component));
        }
    }
}
