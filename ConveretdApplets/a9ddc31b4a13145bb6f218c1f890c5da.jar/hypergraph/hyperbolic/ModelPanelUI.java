// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import javax.swing.JComponent;
import javax.swing.Timer;
import javax.swing.CellRendererPane;
import java.util.Hashtable;
import java.beans.PropertyChangeListener;
import javax.swing.plaf.ComponentUI;

public class ModelPanelUI extends ComponentUI implements PropertyChangeListener
{
    private ModelPoint tempmp;
    private Isometry viewMatrix;
    private Isometry inversViewMatrix;
    private boolean draft;
    private boolean animation;
    protected ModelPanel panel;
    private Hashtable currentRenderer;
    private String projection;
    private CellRendererPane rendererPane;
    Timer timer;
    Animator animator;
    
    public ModelPanelUI() {
        this.animator = new Animator();
    }
    
    public static ComponentUI createUI(final JComponent component) {
        return new ModelPanelUI();
    }
    
    public void installUI(final JComponent component) {
        component.addPropertyChangeListener(this);
        this.panel = (ModelPanel)component;
        this.rendererPane = new CellRendererPane();
        this.tempmp = ((ModelPanel)component).getModel().getOrigin();
    }
    
    public void uninstallUI(final JComponent component) {
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
    }
    
    public CellRendererPane getRendererPane() {
        return this.rendererPane;
    }
    
    public void paintRenderer(final Graphics graphics, final JComponent component, final Renderer renderer) {
        final Component component2 = renderer.getComponent();
        this.getRendererPane().paintComponent(graphics, component2, component, component2.getX(), component2.getY(), component2.getWidth(), component2.getHeight());
    }
    
    public void paintLine(final Graphics graphics, final JComponent component, final ModelPoint modelPoint, final ModelPoint modelPoint2) {
        final LineRenderer lineRenderer = this.panel.getLineRenderer();
        lineRenderer.configure(this.panel, modelPoint, modelPoint2);
        this.paintRenderer(graphics, component, lineRenderer);
    }
    
    public void paintText(final Graphics graphics, final JComponent component, final ModelPoint modelPoint, final String s) {
        final TextRenderer textRenderer = this.panel.getTextRenderer();
        textRenderer.configure(this.panel, modelPoint, s);
        this.paintRenderer(graphics, component, textRenderer);
    }
    
    public boolean isDraft() {
        return this.draft;
    }
    
    public void setDraft(final boolean draft) {
        this.draft = draft;
    }
    
    public boolean isAnimation() {
        return this.animation;
    }
    
    public void setAnimation(final boolean animation) {
        this.animation = animation;
    }
    
    public Point2D getScale(final ModelPoint to, final JComponent component) {
        this.tempmp.setTo(to);
        this.applyViewMatrix(this.tempmp, component);
        return ((ModelPanel)component).getProjector().getScale(this.tempmp, component);
    }
    
    public Point map(final ModelPoint modelPoint, final JComponent component) {
        return ((ModelPanel)component).getProjector().map(modelPoint, component);
    }
    
    public Point project(final ModelPoint to, final JComponent component) {
        this.tempmp.setTo(to);
        this.applyViewMatrix(this.tempmp, component);
        return this.map(this.tempmp, component);
    }
    
    public ModelPoint inversMap(final Point point, final JComponent component) {
        return ((ModelPanel)component).getProjector().inversMap(point, component);
    }
    
    public ModelPoint unProject(final Point point, final JComponent component) {
        final ModelPoint inversMap = this.inversMap(point, component);
        if (inversMap == null) {
            return null;
        }
        this.applyInversViewMatrix(inversMap, component);
        return inversMap;
    }
    
    public void applyViewMatrix(final ModelPoint modelPoint, final JComponent component) {
        ((ModelPanel)component).getViewMatrix().apply(modelPoint);
    }
    
    public void applyInversViewMatrix(final ModelPoint modelPoint, final JComponent component) {
        ((ModelPanel)component).getInversViewMatrix().apply(modelPoint);
    }
    
    public void animate(final Isometry[] isometries, final JComponent component) {
        if (this.timer == null) {
            this.timer = new Timer(0, this.animator);
            this.animator.setTimer(this.timer);
        }
        if (!this.timer.isRunning()) {
            this.animator.setIsometries(isometries);
            this.animator.setComponent(component);
            this.timer.setDelay(50);
            this.timer.setInitialDelay(50);
            this.timer.start();
        }
    }
    
    public Point getCenter(final JComponent component) {
        return new Point(component.getWidth() / 2, component.getHeight() / 2);
    }
    
    public void center(final Point point, final JComponent component) {
        this.move(point, this.getCenter(component), component);
    }
    
    public void center(final ModelPoint modelPoint, final JComponent component) {
        this.move(modelPoint, this.getCenter(component), component);
    }
    
    public void move(final ModelPoint modelPoint, final Point point, final JComponent component) {
        final ModelPoint modelPoint2 = (ModelPoint)modelPoint.clone();
        this.applyViewMatrix(modelPoint2, component);
        final ModelPoint inversMap = this.inversMap(point, component);
        if (inversMap == null) {
            return;
        }
        this.moveOnIntermediateLevel(modelPoint2, inversMap, component);
    }
    
    public void move(final Point point, final Point point2, final JComponent component) {
        ((ModelPanel)component).getModel();
        final ModelPoint inversMap = this.inversMap(point, component);
        if (inversMap == null) {
            return;
        }
        final ModelPoint inversMap2 = this.inversMap(point2, component);
        if (inversMap2 == null) {
            return;
        }
        this.moveOnIntermediateLevel(inversMap, inversMap2, component);
    }
    
    private void moveOnIntermediateLevel(final ModelPoint modelPoint, final ModelPoint modelPoint2, final JComponent component) {
        final Model model = ((ModelPanel)component).getModel();
        if (model.dist(modelPoint, modelPoint2) < 0.01) {
            return;
        }
        int n = 1;
        if (this.isAnimation()) {
            n = 10;
        }
        final Isometry[] array = new Isometry[n];
        for (int i = 0; i < array.length; ++i) {
            (array[i] = model.getTranslation(modelPoint, modelPoint2, (i + 1.0) / n)).multiplyRight(((ModelPanel)component).getViewMatrix());
        }
        this.animate(array, component);
    }
    
    class Animator implements ActionListener
    {
        Timer timer;
        int current;
        Isometry[] isometries;
        JComponent c;
        
        public void setComponent(final JComponent c) {
            this.c = c;
        }
        
        public void setIsometries(final Isometry[] isometries) {
            this.isometries = isometries;
            this.current = 0;
        }
        
        public void setTimer(final Timer timer) {
            this.timer = timer;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            if (this.current < 0 || this.current >= this.isometries.length) {
                this.timer.stop();
                return;
            }
            if (this.current == 0) {
                ModelPanelUI.this.setDraft(true);
            }
            if (this.current == this.isometries.length - 1) {
                ModelPanelUI.this.setDraft(false);
                this.timer.stop();
            }
            ((ModelPanel)this.c).setViewMatrix(this.isometries[this.current]);
            ++this.current;
        }
    }
}
