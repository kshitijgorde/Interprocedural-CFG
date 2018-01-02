import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Arrow
{
    final Color ARROW_COLOR;
    public final double MAX_LENGTH = 10.0;
    final double MIN_LENGTH = 0.5;
    public double m_dAngle;
    public double m_dLength;
    public boolean m_bShow;
    cgModel m_model;
    public cgObject m_object;
    
    public Arrow() {
        this.ARROW_COLOR = Color.red;
        (this.m_model = new cgModel(7, 2)).specifyVertex(0, new cgVector(0.0, -0.1, 0.0));
        this.m_model.specifyVertex(1, new cgVector(0.7, -0.1, 0.0));
        this.m_model.specifyVertex(2, new cgVector(0.7, 0.1, 0.0));
        this.m_model.specifyVertex(3, new cgVector(0.0, 0.1, 0.0));
        this.m_model.specifyVertex(4, new cgVector(0.7, -0.2, 0.0));
        this.m_model.specifyVertex(5, new cgVector(1.0, 0.0, 0.0));
        this.m_model.specifyVertex(6, new cgVector(0.7, 0.2, 0.0));
        final int[] array = { 3, 2, 1, 0 };
        final int[] array2 = { 6, 5, 4 };
        this.m_model.specifyFace(0, new cgFace(4, array, this.ARROW_COLOR, null));
        this.m_model.specifyFace(1, new cgFace(3, array2, this.ARROW_COLOR, null));
        this.m_object = new cgObject(this.m_model);
        this.m_object.m_bSortPolys = false;
        this.m_object.transform();
        this.m_bShow = false;
    }
    
    public void draw(final Graphics graphics) {
        if (this.m_bShow) {
            for (int i = 0; i < this.m_object.m_nPolygons; ++i) {
                this.m_object.m_polygon[i].draw(graphics);
            }
        }
    }
    
    public void aim(final int n, final int n2, final Golfball3D golfball3D, final cgViewpoint cgViewpoint) {
        final cgVector cgVector = new cgVector(-((n - cgViewpoint.m_dXoffset) / cgViewpoint.m_dViewplaneMult), -((n2 - cgViewpoint.m_dYoffset) / cgViewpoint.m_dViewplaneMult), 1.0);
        cgVector.normalize();
        final cgMatrix cgMatrix = new cgMatrix();
        cgMatrix.unitInverse(cgViewpoint.m_matrix);
        final cgVector cgVector2 = new cgVector(cgVector.m_dX * cgMatrix.m_dXx + cgVector.m_dY * cgMatrix.m_dXy + cgVector.m_dZ * cgMatrix.m_dXz, cgVector.m_dX * cgMatrix.m_dYx + cgVector.m_dY * cgMatrix.m_dYy + cgVector.m_dZ * cgMatrix.m_dYz, cgVector.m_dX * cgMatrix.m_dZx + cgVector.m_dY * cgMatrix.m_dZy + cgVector.m_dZ * cgMatrix.m_dZz);
        if (cgVector2.m_dZ >= 0.0) {
            this.m_bShow = false;
            return;
        }
        final double n3 = (cgViewpoint.m_vector.m_dZ - golfball3D.m_dZ0) / Math.abs(cgVector2.m_dZ);
        final double n4 = cgViewpoint.m_vector.m_dX + cgVector2.m_dX * n3;
        final double n5 = cgViewpoint.m_vector.m_dY + cgVector2.m_dY * n3;
        final double n6 = n4 - golfball3D.m_dX0;
        final double n7 = n5 - golfball3D.m_dY0;
        this.m_dLength = Math.sqrt(n6 * n6 + n7 * n7);
        if (this.m_dLength < 0.5) {
            this.m_bShow = false;
            return;
        }
        this.m_dAngle = Math.atan2(n7, n6);
        if (this.m_dLength > 10.0) {
            final double n8 = golfball3D.m_dX0 + 10.0 * Math.cos(this.m_dAngle);
            final double n9 = golfball3D.m_dY0 + 10.0 * Math.sin(this.m_dAngle);
            this.m_dLength = 10.0;
        }
        this.m_object.m_matrix.reset();
        this.m_object.m_matrix.rotateWorld(0.0, 0.0, this.m_dAngle);
        this.m_object.m_matrix.multiply(this.m_dLength);
        this.m_object.m_vector.m_dX = golfball3D.m_dX0;
        this.m_object.m_vector.m_dY = golfball3D.m_dY0;
        this.m_object.m_vector.m_dZ = golfball3D.m_dZ0;
        this.m_object.transform();
        cgViewpoint.renderObject(this.m_object);
        this.m_bShow = true;
    }
}
