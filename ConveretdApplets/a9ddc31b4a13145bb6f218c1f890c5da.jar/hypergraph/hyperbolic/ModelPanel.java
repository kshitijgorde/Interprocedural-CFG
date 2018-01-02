// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.geom.Point2D;
import javax.swing.plaf.ComponentUI;
import java.awt.Graphics;
import javax.swing.event.ChangeEvent;
import hypergraph.graphApi.io.CSSColourParser;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.UIManager;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.event.ChangeListener;
import javax.swing.JComponent;

public class ModelPanel extends JComponent implements ChangeListener, MouseListener, MouseMotionListener
{
    private Model model;
    private LineRenderer lineRenderer;
    private TextRenderer textRenderer;
    private Projector projector;
    private PropertyManager properties;
    private ModelPoint draggingStartPoint;
    
    public ModelPanel() {
        this.refreshProperties();
        if (UIManager.get("ModelPanelUI") == null) {
            UIManager.put("ModelPanelUI", "hypergraph.hyperbolic.ModelPanelUI");
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.updateUI();
    }
    
    public void loadProperties(final InputStream inputStream) throws IOException {
        this.properties.load(inputStream);
        this.refreshProperties();
    }
    
    public void refreshProperties() {
        if (this.properties == null) {
            this.properties = new PropertyManager();
        }
        Model model = null;
        try {
            model = this.properties.getClass("model.class").newInstance();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setModel(model);
        this.getModel().addChangeListener(this);
        final String string = this.getPropertyManager().getString("hypergraph.hyperbolic.background.color");
        if (string != null) {
            this.setBackground(CSSColourParser.stringToColor(string));
            this.setOpaque(true);
        }
        else {
            this.setOpaque(false);
        }
    }
    
    public PropertyManager getPropertyManager() {
        return this.properties;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.repaint();
    }
    
    public Isometry getInversViewMatrix() {
        if (this.getModel() == null) {
            return null;
        }
        return this.getModel().getInversViewMatrix();
    }
    
    public Isometry getViewMatrix() {
        if (this.getModel() == null) {
            return null;
        }
        return this.getModel().getViewMatrix();
    }
    
    public void setViewMatrix(final Isometry viewMatrix) {
        if (this.getModel() == null) {
            return;
        }
        this.getModel().setViewMatrix(viewMatrix);
    }
    
    public void setProjector(final Projector projector) {
        this.firePropertyChange("Projector", this.projector, this.projector = projector);
    }
    
    public Projector getProjector() {
        if (this.projector != null) {
            return this.projector;
        }
        try {
            this.projector = this.properties.getClass("projector.class").newInstance();
        }
        catch (Exception ex) {
            System.out.println("Couldn't load projector");
            System.out.println("Classname for projector is : " + this.properties.getString("projector.class"));
            ex.printStackTrace();
            return null;
        }
        return this.projector;
    }
    
    public void paintRenderer(final Graphics graphics, final Renderer renderer) {
        this.getUI().paintRenderer(graphics, this, renderer);
    }
    
    public void paintLine(final Graphics graphics, final ModelPoint modelPoint, final ModelPoint modelPoint2) {
        this.getUI().paintLine(graphics, this, modelPoint, modelPoint2);
    }
    
    public void paintText(final Graphics graphics, final ModelPoint modelPoint, final String s) {
        this.getUI().paintText(graphics, this, modelPoint, s);
    }
    
    public void setModel(final Model model) {
        this.firePropertyChange("Model", this.getModel(), this.model = model);
    }
    
    public Model getModel() {
        return this.model;
    }
    
    public ModelPanelUI getUI() {
        return (ModelPanelUI)this.ui;
    }
    
    public void setUI(final ModelPanelUI ui) {
        super.setUI(ui);
    }
    
    public void updateUI() {
        this.setUI((ModelPanelUI)UIManager.getUI(this));
        this.invalidate();
    }
    
    public String getUIClassID() {
        return "ModelPanelUI";
    }
    
    public Point2D getScale(final ModelPoint modelPoint) {
        return this.getUI().getScale(modelPoint, this);
    }
    
    public Point project(final ModelPoint modelPoint) {
        return this.getUI().project(modelPoint, this);
    }
    
    public ModelPoint unProject(final Point point) {
        return this.getUI().unProject(point, this);
    }
    
    public void center(final Point point) {
        this.getUI().center(point, this);
    }
    
    public LineRenderer getLineRenderer() {
        if (this.lineRenderer == null) {
            try {
                this.lineRenderer = this.properties.getClass("linerenderer.class").newInstance();
            }
            catch (Exception ex) {
                return null;
            }
        }
        return this.lineRenderer;
    }
    
    public TextRenderer getTextRenderer() {
        if (this.textRenderer == null) {
            try {
                this.textRenderer = this.properties.getClass("textrenderer.class").newInstance();
            }
            catch (Exception ex) {
                return null;
            }
        }
        return this.textRenderer;
    }
    
    public void setLineRenderer(final LineRenderer lineRenderer) {
        this.lineRenderer = lineRenderer;
    }
    
    public void setTextRenderer(final TextRenderer textRenderer) {
        this.textRenderer = textRenderer;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1 && (mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.center(mouseEvent.getPoint());
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.draggingStartPoint = this.getUI().unProject(mouseEvent.getPoint(), (JComponent)mouseEvent.getSource());
            this.getUI().setAnimation(false);
            this.getUI().setDraft(true);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final JComponent component = (JComponent)mouseEvent.getSource();
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            component.setCursor(Cursor.getDefaultCursor());
            this.draggingStartPoint = null;
            this.getUI().setAnimation(true);
            this.getUI().setDraft(false);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final JComponent component = (JComponent)mouseEvent.getSource();
        if ((mouseEvent.getModifiers() & 0x10) != 0x0 && this.draggingStartPoint != null) {
            component.setCursor(Cursor.getPredefinedCursor(12));
            this.getUI().move(this.draggingStartPoint, mouseEvent.getPoint(), component);
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
}
