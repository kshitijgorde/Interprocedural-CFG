// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleState;
import java.util.Locale;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Color;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleRole;
import java.awt.Point;
import java.awt.event.FocusListener;
import javax.accessibility.AccessibleComponent;
import java.io.Serializable;
import java.awt.AWTError;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;
import java.awt.Container;

public class Box extends Container implements Accessible
{
    protected AccessibleContext accessibleContext;
    
    public Box(final int n) {
        this.accessibleContext = null;
        super.setLayout(new BoxLayout(this, n));
    }
    
    public static Component createGlue() {
        return new Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 32767));
    }
    
    public static Box createHorizontalBox() {
        return new Box(0);
    }
    
    public static Component createHorizontalGlue() {
        return new Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));
    }
    
    public static Component createHorizontalStrut(final int n) {
        return new Filler(new Dimension(n, 0), new Dimension(n, 0), new Dimension(n, 32767));
    }
    
    public static Component createRigidArea(final Dimension dimension) {
        return new Filler(dimension, dimension, dimension);
    }
    
    public static Box createVerticalBox() {
        return new Box(1);
    }
    
    public static Component createVerticalGlue() {
        return new Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 32767));
    }
    
    public static Component createVerticalStrut(final int n) {
        return new Filler(new Dimension(0, n), new Dimension(0, n), new Dimension(32767, n));
    }
    
    public AccessibleContext getAccessibleContext() {
        if (this.accessibleContext == null) {
            this.accessibleContext = new AccessibleBox();
        }
        return this.accessibleContext;
    }
    
    public void setLayout(final LayoutManager layoutManager) {
        throw new AWTError("Illegal request");
    }
    
    public static class Filler extends Component implements Accessible
    {
        private Dimension reqMin;
        private Dimension reqPref;
        private Dimension reqMax;
        protected AccessibleContext accessibleContext;
        
        public Filler(final Dimension reqMin, final Dimension reqPref, final Dimension reqMax) {
            this.accessibleContext = null;
            this.reqMin = reqMin;
            this.reqPref = reqPref;
            this.reqMax = reqMax;
        }
        
        public void changeShape(final Dimension reqMin, final Dimension reqPref, final Dimension reqMax) {
            this.reqMin = reqMin;
            this.reqPref = reqPref;
            this.reqMax = reqMax;
            this.invalidate();
        }
        
        public AccessibleContext getAccessibleContext() {
            if (this.accessibleContext == null) {
                this.accessibleContext = new AccessibleBoxFiller();
            }
            return this.accessibleContext;
        }
        
        public Dimension getMaximumSize() {
            return this.reqMax;
        }
        
        public Dimension getMinimumSize() {
            return this.reqMin;
        }
        
        public Dimension getPreferredSize() {
            return this.reqPref;
        }
        
        protected class AccessibleBoxFiller extends AccessibleContext implements Serializable, AccessibleComponent
        {
            public void addFocusListener(final FocusListener focusListener) {
                Filler.this.addFocusListener(focusListener);
            }
            
            public boolean contains(final Point point) {
                return Filler.this.contains(point);
            }
            
            public Accessible getAccessibleAt(final Point point) {
                return SwingUtilities.getAccessibleAt(Filler.this, point);
            }
            
            public Accessible getAccessibleChild(final int n) {
                return SwingUtilities.getAccessibleChild(Filler.this, n);
            }
            
            public int getAccessibleChildrenCount() {
                return SwingUtilities.getAccessibleChildrenCount(Filler.this);
            }
            
            public AccessibleComponent getAccessibleComponent() {
                return this;
            }
            
            public int getAccessibleIndexInParent() {
                return SwingUtilities.getAccessibleIndexInParent(Filler.this);
            }
            
            public Accessible getAccessibleParent() {
                if (super.accessibleParent != null) {
                    return super.accessibleParent;
                }
                final Container parent = Filler.this.getParent();
                if (parent instanceof Accessible) {
                    return (Accessible)parent;
                }
                return null;
            }
            
            public AccessibleRole getAccessibleRole() {
                return AccessibleRole.FILLER;
            }
            
            public AccessibleStateSet getAccessibleStateSet() {
                return SwingUtilities.getAccessibleStateSet(Filler.this);
            }
            
            public Color getBackground() {
                return Filler.this.getBackground();
            }
            
            public Rectangle getBounds() {
                return Filler.this.getBounds();
            }
            
            public Cursor getCursor() {
                return Filler.this.getCursor();
            }
            
            public Font getFont() {
                return Filler.this.getFont();
            }
            
            public FontMetrics getFontMetrics(final Font font) {
                return Filler.this.getFontMetrics(font);
            }
            
            public Color getForeground() {
                return Filler.this.getForeground();
            }
            
            public Locale getLocale() {
                return Filler.this.getLocale();
            }
            
            public Point getLocation() {
                return Filler.this.getLocation();
            }
            
            public Point getLocationOnScreen() {
                if (Filler.this.isShowing()) {
                    return Filler.this.getLocationOnScreen();
                }
                return null;
            }
            
            public Dimension getSize() {
                return Filler.this.getSize();
            }
            
            public boolean isEnabled() {
                return Filler.this.isEnabled();
            }
            
            public boolean isFocusTraversable() {
                return Filler.this.isFocusTraversable();
            }
            
            public boolean isShowing() {
                return Filler.this.isShowing();
            }
            
            public boolean isVisible() {
                return Filler.this.isVisible();
            }
            
            public void removeFocusListener(final FocusListener focusListener) {
                Filler.this.removeFocusListener(focusListener);
            }
            
            public void requestFocus() {
                Filler.this.requestFocus();
            }
            
            public void setBackground(final Color background) {
                Filler.this.setBackground(background);
            }
            
            public void setBounds(final Rectangle bounds) {
                Filler.this.setBounds(bounds);
            }
            
            public void setCursor(final Cursor cursor) {
                Filler.this.setCursor(cursor);
            }
            
            public void setEnabled(final boolean enabled) {
                final boolean enabled2 = Filler.this.isEnabled();
                Filler.this.setEnabled(enabled);
                if (enabled != enabled2 && Filler.this.accessibleContext != null) {
                    if (enabled) {
                        Filler.this.accessibleContext.firePropertyChange("AccessibleState", null, AccessibleState.ENABLED);
                    }
                    else {
                        Filler.this.accessibleContext.firePropertyChange("AccessibleState", AccessibleState.ENABLED, null);
                    }
                }
            }
            
            public void setFont(final Font font) {
                Filler.this.setFont(font);
            }
            
            public void setForeground(final Color foreground) {
                Filler.this.setForeground(foreground);
            }
            
            public void setLocation(final Point location) {
                Filler.this.setLocation(location);
            }
            
            public void setSize(final Dimension size) {
                Filler.this.setSize(size);
            }
            
            public void setVisible(final boolean visible) {
                final boolean visible2 = Filler.this.isVisible();
                Filler.this.setVisible(visible);
                if (visible != visible2 && Filler.this.accessibleContext != null) {
                    if (visible) {
                        Filler.this.accessibleContext.firePropertyChange("AccessibleState", null, AccessibleState.VISIBLE);
                    }
                    else {
                        Filler.this.accessibleContext.firePropertyChange("AccessibleState", AccessibleState.VISIBLE, null);
                    }
                }
            }
        }
    }
    
    protected class AccessibleBox extends AccessibleContext implements Serializable, AccessibleComponent
    {
        public void addFocusListener(final FocusListener focusListener) {
            Box.this.addFocusListener(focusListener);
        }
        
        public boolean contains(final Point point) {
            return Box.this.contains(point);
        }
        
        public Accessible getAccessibleAt(final Point point) {
            return SwingUtilities.getAccessibleAt(Box.this, point);
        }
        
        public Accessible getAccessibleChild(final int n) {
            return SwingUtilities.getAccessibleChild(Box.this, n);
        }
        
        public int getAccessibleChildrenCount() {
            return SwingUtilities.getAccessibleChildrenCount(Box.this);
        }
        
        public AccessibleComponent getAccessibleComponent() {
            return this;
        }
        
        public int getAccessibleIndexInParent() {
            return SwingUtilities.getAccessibleIndexInParent(Box.this);
        }
        
        public Accessible getAccessibleParent() {
            final Container parent = Box.this.getParent();
            if (parent instanceof Accessible) {
                return (Accessible)parent;
            }
            return null;
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.FILLER;
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            return SwingUtilities.getAccessibleStateSet(Box.this);
        }
        
        public Color getBackground() {
            return Box.this.getBackground();
        }
        
        public Rectangle getBounds() {
            return Box.this.getBounds();
        }
        
        public Cursor getCursor() {
            return Box.this.getCursor();
        }
        
        public Font getFont() {
            return Box.this.getFont();
        }
        
        public FontMetrics getFontMetrics(final Font font) {
            return Box.this.getFontMetrics(font);
        }
        
        public Color getForeground() {
            return Box.this.getForeground();
        }
        
        public Locale getLocale() {
            return Box.this.getLocale();
        }
        
        public Point getLocation() {
            return Box.this.getLocation();
        }
        
        public Point getLocationOnScreen() {
            return Box.this.getLocationOnScreen();
        }
        
        public Dimension getSize() {
            return Box.this.getSize();
        }
        
        public boolean isEnabled() {
            return Box.this.isEnabled();
        }
        
        public boolean isFocusTraversable() {
            return Box.this.isFocusTraversable();
        }
        
        public boolean isShowing() {
            return Box.this.isShowing();
        }
        
        public boolean isVisible() {
            return Box.this.isVisible();
        }
        
        public void removeFocusListener(final FocusListener focusListener) {
            Box.this.removeFocusListener(focusListener);
        }
        
        public void requestFocus() {
            Box.this.requestFocus();
        }
        
        public void setBackground(final Color background) {
            Box.this.setBackground(background);
        }
        
        public void setBounds(final Rectangle bounds) {
            Box.this.setBounds(bounds);
        }
        
        public void setCursor(final Cursor cursor) {
            Box.this.setCursor(cursor);
        }
        
        public void setEnabled(final boolean enabled) {
            Box.this.setEnabled(enabled);
        }
        
        public void setFont(final Font font) {
            Box.this.setFont(font);
        }
        
        public void setForeground(final Color foreground) {
            Box.this.setForeground(foreground);
        }
        
        public void setLocation(final Point location) {
            Box.this.setLocation(location);
        }
        
        public void setSize(final Dimension size) {
            Box.this.setSize(size);
        }
        
        public void setVisible(final boolean visible) {
            Box.this.setVisible(visible);
        }
    }
}
