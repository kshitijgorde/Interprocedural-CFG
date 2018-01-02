// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import java.awt.geom.Point2D;
import java.awt.Point;
import javax.swing.JComponent;
import java.util.Hashtable;

public class HalfPlaneProjector implements Projector
{
    private Hashtable rendererClassNames;
    
    public Point[] getLineSegments(final ModelPoint modelPoint, final ModelPoint modelPoint2, final JComponent component) {
        final ModelPanelUI ui = ((ModelPanel)component).getUI();
        ui.applyViewMatrix(modelPoint, component);
        ui.applyViewMatrix(modelPoint2, component);
        if (ui.isDraft() || ((Complex)modelPoint).dist((Complex)modelPoint2) < 0.01) {
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
        final Complex complex = new Complex(1.0, 0.0);
        complex.add((Complex)modelPoint);
        final Complex complex2 = new Complex(-1.0, 0.0);
        complex2.add((Complex)modelPoint);
        complex2.multiply(-1.0);
        complex.divide(complex2);
        return new Point((int)Math.round(complex.getReal() * 0.5 * component.getWidth()), (int)Math.round(complex.getImag() * 0.5 * component.getHeight() + component.getHeight() * 0.5));
    }
    
    public ModelPoint inversMap(final Point point, final JComponent component) {
        final Complex complex = new Complex(point.getX() * 2.0 / component.getWidth(), (point.getY() - component.getHeight() * 0.5) * 2.0 / component.getHeight());
        if (complex.getReal() <= 0.0) {
            return null;
        }
        final Complex complex2 = (Complex)complex.clone();
        complex.add(-1.0);
        complex2.add(1.0);
        complex.divide(complex2);
        return complex;
    }
    
    public Point2D getScale(final ModelPoint modelPoint, final JComponent component) {
        final double n = 1.0 - ((Complex)modelPoint).norm2();
        return new Point2D.Double(n, n);
    }
}
