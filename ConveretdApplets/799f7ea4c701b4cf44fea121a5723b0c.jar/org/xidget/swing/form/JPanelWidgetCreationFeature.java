// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.form;

import java.util.Iterator;
import java.util.List;
import org.xidget.layout.IComputeNode;
import org.xidget.ifeature.ILayoutFeature;
import org.xidget.feature.AnchorLayoutFeature;
import java.awt.Container;
import java.awt.Component;
import javax.swing.JComponent;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.Xlate;
import org.xidget.ifeature.IWidgetContainerFeature;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import org.xidget.swing.layout.AnchorLayoutManager;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import java.awt.Cursor;
import org.xidget.layout.AnchorNode;
import javax.swing.JPanel;
import org.xidget.IXidget;
import javax.swing.event.MouseInputListener;
import org.xidget.ifeature.IWidgetCreationFeature;

public class JPanelWidgetCreationFeature implements IWidgetCreationFeature
{
    private MouseInputListener mouseListener;
    private IXidget xidget;
    private JPanel jPanel;
    private AnchorNode grabbed;
    private Cursor cursor;
    
    public JPanelWidgetCreationFeature(final IXidget xidget) {
        this.mouseListener = new MouseInputAdapter() {
            @Override
            public void mousePressed(final MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == 1) {
                    JPanelWidgetCreationFeature.access$1(JPanelWidgetCreationFeature.this, JPanelWidgetCreationFeature.this.mouseGrab(mouseEvent.getX(), mouseEvent.getY()));
                    JPanelWidgetCreationFeature.this.updateCursor(JPanelWidgetCreationFeature.this.grabbed != null);
                }
            }
            
            @Override
            public void mouseReleased(final MouseEvent mouseEvent) {
                JPanelWidgetCreationFeature.access$1(JPanelWidgetCreationFeature.this, null);
                JPanelWidgetCreationFeature.this.mouseGrab(mouseEvent.getX(), mouseEvent.getY());
                JPanelWidgetCreationFeature.this.updateCursor(false);
            }
            
            @Override
            public void mouseExited(final MouseEvent mouseEvent) {
                if (JPanelWidgetCreationFeature.this.grabbed != null) {
                    JPanelWidgetCreationFeature.this.jPanel.grabFocus();
                }
                JPanelWidgetCreationFeature.this.updateCursor(JPanelWidgetCreationFeature.this.grabbed != null);
            }
            
            @Override
            public void mouseDragged(final MouseEvent mouseEvent) {
                if (JPanelWidgetCreationFeature.this.grabbed != null) {
                    final Rectangle bounds = JPanelWidgetCreationFeature.this.jPanel.getBounds();
                    if (JPanelWidgetCreationFeature.this.grabbed.hasXHandle()) {
                        JPanelWidgetCreationFeature.this.grabbed.setFraction(mouseEvent.getX() / bounds.width);
                    }
                    else {
                        JPanelWidgetCreationFeature.this.grabbed.setFraction(mouseEvent.getY() / bounds.height);
                    }
                    JPanelWidgetCreationFeature.this.jPanel.revalidate();
                }
            }
            
            @Override
            public void mouseMoved(final MouseEvent mouseEvent) {
                JPanelWidgetCreationFeature.this.updateCursor(JPanelWidgetCreationFeature.this.mouseGrab(mouseEvent.getX(), mouseEvent.getY()) != null);
            }
        };
        this.xidget = xidget;
    }
    
    @Override
    public void createWidgets() {
        (this.jPanel = new Canvas(this.xidget, new AnchorLayoutManager(this.xidget))).addMouseListener(this.mouseListener);
        this.jPanel.addMouseMotionListener(this.mouseListener);
        final IXidget parent = this.xidget.getParent();
        if (parent != null && this.hasTitle() && !parent.getConfig().isType("tabs")) {
            this.jPanel.setBorder(new TitledBorder(this.getTitle()));
        }
        if (this.xidget.getParent() != null) {
            final IWidgetContainerFeature widgetContainerFeature = this.xidget.getParent().getFeature(IWidgetContainerFeature.class);
            if (widgetContainerFeature != null) {
                widgetContainerFeature.addWidget(this.xidget);
            }
        }
    }
    
    private boolean hasTitle() {
        final IModelObject config = this.xidget.getConfig();
        return Xlate.childGet(config, "title", Xlate.get(config, "title", (IExpression)null)) != null;
    }
    
    private String getTitle() {
        final IModelObject config = this.xidget.getConfig();
        final IExpression childGet = Xlate.childGet(config, "title", Xlate.get(config, "title", (IExpression)null));
        if (childGet != null) {
            return childGet.evaluateString();
        }
        return "";
    }
    
    @Override
    public void destroyWidgets() {
        final JComponent component = this.xidget.getFeature(JComponent.class);
        if (component != null) {
            final Container parent = component.getParent();
            if (parent != null) {
                parent.remove(component);
                parent.validate();
                parent.repaint();
            }
        }
        this.jPanel = null;
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.jPanel };
    }
    
    public JPanel getJPanel() {
        return this.jPanel;
    }
    
    private AnchorNode mouseGrab(final int n, final int n2) {
        AnchorNode anchorNode = null;
        final List<IComputeNode> allNodes = this.xidget.getFeature((Class<AnchorLayoutFeature>)ILayoutFeature.class).getAllNodes();
        if (allNodes != null) {
            for (final IComputeNode computeNode : allNodes) {
                if (computeNode.hasXHandle()) {
                    if (Math.abs(computeNode.getValue() - n) < 4.0f) {
                        anchorNode = (AnchorNode)computeNode;
                        break;
                    }
                    continue;
                }
                else {
                    if (computeNode.hasYHandle() && Math.abs(computeNode.getValue() - n2) < 4.0f) {
                        anchorNode = (AnchorNode)computeNode;
                        break;
                    }
                    continue;
                }
            }
        }
        return anchorNode;
    }
    
    private void updateCursor(final boolean b) {
        if (b) {
            if (this.cursor == null) {
                this.cursor = this.jPanel.getCursor();
                this.jPanel.setCursor(Cursor.getPredefinedCursor(11));
            }
        }
        else if (this.cursor != null) {
            this.jPanel.setCursor(this.cursor);
            this.cursor = null;
        }
    }
    
    static /* synthetic */ void access$1(final JPanelWidgetCreationFeature panelWidgetCreationFeature, final AnchorNode grabbed) {
        panelWidgetCreationFeature.grabbed = grabbed;
    }
}
