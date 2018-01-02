// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import java.awt.geom.Point2D;
import java.awt.Point;
import javax.swing.JComponent;

public class PoincareProjector implements Projector
{
    public Point[] getLineSegments(final ModelPoint modelPoint, final ModelPoint modelPoint2, final JComponent component) {
        final ModelPanelUI ui = ((ModelPanel)component).getUI();
        ui.applyViewMatrix(modelPoint, component);
        ui.applyViewMatrix(modelPoint2, component);
        if (ui.isDraft() || ((Complex)modelPoint).dist((Complex)modelPoint2) < 0.1) {
            return new Point[] { ui.map(modelPoint, component), ui.map(modelPoint2, component) };
        }
        final Complex complex = (Complex)modelPoint;
        int n = 6;
        if (((Complex)modelPoint).dist((Complex)modelPoint2) > 1.0) {
            n = 30;
        }
        final Point[] array = new Point[n + 1];
        array[0] = ui.map(complex, component);
        final Isometry translation = ((ModelPanel)component).getModel().getTranslation(modelPoint, modelPoint2, 1.0 / n);
        for (int i = 1; i < n + 1; ++i) {
            translation.apply(complex);
            array[i] = ui.map(complex, component);
        }
        return array;
    }
    
    public Point map(final ModelPoint modelPoint, final JComponent component) {
        final Complex complex = (Complex)modelPoint;
        return new Point((int)Math.floor((complex.real + 1.0) * 0.5 * component.getWidth()), (int)Math.floor((1.0 - complex.imag) * 0.5 * component.getHeight()));
    }
    
    public ModelPoint inversMap(final Point point, final JComponent component) {
        final Complex complex = new Complex();
        if (component.getWidth() != 0) {
            complex.real = point.getX() * 2.0 / component.getWidth() - 1.0;
        }
        if (component.getHeight() != 0) {
            complex.imag = 1.0 - point.getY() * 2.0 / component.getHeight();
        }
        if (complex.norm2() < 1.0) {
            return complex;
        }
        return null;
    }
    
    public Point2D getScale(final ModelPoint modelPoint, final JComponent component) {
        final double n = 1.0 - ((Complex)modelPoint).norm2();
        return new Point2D.Double(n, n);
    }
}
