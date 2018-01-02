import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class sfViewpoint
{
    public sfMatrix m_matrix;
    public sfVector m_vector;
    public double m_dAmbient;
    public sfMatrix m_matrixLight;
    double m_dFratio;
    int m_nViewplaneX;
    int m_nViewplaneY;
    int m_nViewplaneWidth;
    int m_nViewplaneHeight;
    public Image m_imageViewplane;
    public Image m_imageBackgrnd;
    public Color m_colorBackgrnd;
    double m_dViewplaneMult;
    double m_dXoffset;
    double m_dYoffset;
    final double Z_CLIP = 0.1;
    final int MAX_POLYGONS = 300;
    int m_nPolygons;
    sfPolygon[] m_polygon;
    double m_dDistanceMax;
    double m_dDistanceMin;
    
    public sfViewpoint(final int nViewplaneX, final int nViewplaneY, final int nViewplaneWidth, final int nViewplaneHeight, final Component component) {
        this.m_matrix = new sfMatrix();
        this.m_vector = new sfVector();
        this.m_dAmbient = 0.2;
        (this.m_matrixLight = new sfMatrix()).rotateWorld(-0.39269908169872414, -0.39269908169872414, 0.0);
        this.m_nViewplaneX = nViewplaneX;
        this.m_nViewplaneY = nViewplaneY;
        this.m_nViewplaneWidth = nViewplaneWidth;
        this.m_nViewplaneHeight = nViewplaneHeight;
        this.m_imageViewplane = component.createImage(nViewplaneWidth, nViewplaneHeight);
        this.m_imageBackgrnd = null;
        this.m_colorBackgrnd = Color.black;
        this.m_polygon = new sfPolygon[300];
        this.setFratio(1.0);
    }
    
    private void renderObject(final sfObject sfObject) {
        for (int i = 0; i < sfObject.m_nVertices; ++i) {
            final double n = sfObject.m_vectorWorld[i].m_dX - this.m_vector.m_dX;
            final double n2 = sfObject.m_vectorWorld[i].m_dY - this.m_vector.m_dY;
            final double n3 = sfObject.m_vectorWorld[i].m_dZ - this.m_vector.m_dZ;
            final sfVector sfVector = sfObject.m_vectorView[i];
            sfVector.m_dX = n * this.m_matrix.m_dXx + n2 * this.m_matrix.m_dXy + n3 * this.m_matrix.m_dXz;
            sfVector.m_dY = n * this.m_matrix.m_dYx + n2 * this.m_matrix.m_dYy + n3 * this.m_matrix.m_dYz;
            sfVector.m_dZ = n * this.m_matrix.m_dZx + n2 * this.m_matrix.m_dZy + n3 * this.m_matrix.m_dZz;
            if (sfVector.m_dZ <= 0.1) {
                sfObject.m_bVisible[i] = false;
            }
            else {
                sfObject.m_bVisible[i] = true;
                sfObject.m_nXview[i] = (int)(sfVector.m_dX / sfVector.m_dZ * this.m_dViewplaneMult + this.m_dXoffset);
                sfObject.m_nYview[i] = (int)(-sfVector.m_dY / sfVector.m_dZ * this.m_dViewplaneMult + this.m_dYoffset);
            }
        }
        for (int j = 0; j < sfObject.m_nPolygons; ++j) {
            final sfFace sfFace = sfObject.m_model.m_face[j];
            final sfPolygon sfPolygon = sfObject.m_polygon[j];
            sfPolygon.m_dDistance = 0.0;
            int n4 = 1;
            for (int k = 0; k < sfFace.m_nVertices; ++k) {
                final int n5 = sfFace.m_ndxVertex[k];
                if (!sfObject.m_bVisible[n5]) {
                    n4 = 0;
                    break;
                }
                sfPolygon.m_nX[k] = sfObject.m_nXview[n5];
                sfPolygon.m_nY[k] = sfObject.m_nYview[n5];
                sfPolygon.m_dDistance = Math.max(sfPolygon.m_dDistance, sfObject.m_vectorView[n5].m_dZ);
            }
            final sfVector sfVector2 = sfObject.m_vectorView[sfFace.m_ndxVertex[0]];
            final sfVector sfVector3 = sfObject.m_vectorView[sfFace.m_ndxVertex[1]];
            final sfVector sfVector4 = sfObject.m_vectorView[sfFace.m_ndxVertex[2]];
            if (n4 != 0) {
                final double n6 = sfVector3.m_dX - sfVector2.m_dX;
                final double n7 = sfVector3.m_dY - sfVector2.m_dY;
                final double n8 = sfVector3.m_dZ - sfVector2.m_dZ;
                final double n9 = sfVector4.m_dX - sfVector3.m_dX;
                final double n10 = sfVector4.m_dY - sfVector3.m_dY;
                final double n11 = sfVector4.m_dZ - sfVector3.m_dZ;
                final double n12 = n7 * n11 - n8 * n10;
                final double n13 = n8 * n9 - n6 * n11;
                final double n14 = n6 * n10 - n7 * n9;
                if (sfVector3.m_dX * n12 + sfVector3.m_dY * n13 + sfVector3.m_dZ * n14 >= 0.0) {
                    sfPolygon.m_color = sfFace.m_colorFront;
                }
                else {
                    sfPolygon.m_color = sfFace.m_colorBack;
                }
                if (sfPolygon.m_color == null) {
                    n4 = 0;
                }
                else {
                    double n15 = (n12 * this.m_matrixLight.m_dZx + n13 * this.m_matrixLight.m_dZy + n14 * this.m_matrixLight.m_dZz) / Math.sqrt(n12 * n12 + n13 * n13 + n14 * n14);
                    if (n15 < 0.0) {
                        n15 = 0.0;
                    }
                    if (n15 < 1.0) {
                        final double n16 = this.m_dAmbient + (1.0 - this.m_dAmbient) * n15;
                        final int rgb = sfPolygon.m_color.getRGB();
                        sfPolygon.m_color = new Color(((int)((rgb & 0xFF0000) * n16) & 0xFF0000) + ((int)((rgb & 0xFF00) * n16) & 0xFF00) + (int)((rgb & 0xFF) * n16));
                    }
                }
            }
            if (n4 != 0 && this.m_nPolygons < 300) {
                this.m_polygon[this.m_nPolygons] = sfPolygon;
                ++this.m_nPolygons;
                this.m_dDistanceMax = Math.max(this.m_dDistanceMax, sfPolygon.m_dDistance);
                this.m_dDistanceMin = Math.min(this.m_dDistanceMin, sfPolygon.m_dDistance);
            }
        }
    }
    
    private void binSort(final int n, final int n2, double n3, double max, final boolean b) {
        if (n2 - n == 1) {
            if (this.m_polygon[n].m_dDistance > this.m_polygon[n2].m_dDistance) {
                final sfPolygon sfPolygon = this.m_polygon[n];
                this.m_polygon[n] = this.m_polygon[n2];
                this.m_polygon[n2] = sfPolygon;
            }
            return;
        }
        if (b) {
            max = (n3 = this.m_polygon[n].m_dDistance);
            for (int i = n + 1; i <= n2; ++i) {
                max = Math.max(max, this.m_polygon[i].m_dDistance);
                n3 = Math.min(n3, this.m_polygon[i].m_dDistance);
            }
        }
        final double n4 = (max + n3) / 2.0;
        if (n4 - n3 < 0.001) {
            return;
        }
        int j = n;
        int n5 = n2;
        while (j < n5) {
            while (j < n5) {
                if (this.m_polygon[j].m_dDistance > n4) {
                    break;
                }
                ++j;
            }
            while (j < n5 && this.m_polygon[n5].m_dDistance > n4) {
                --n5;
            }
            if (j < n5) {
                final sfPolygon sfPolygon2 = this.m_polygon[j];
                this.m_polygon[j] = this.m_polygon[n5];
                this.m_polygon[n5] = sfPolygon2;
            }
        }
        if ((j == n2 && this.m_polygon[j].m_dDistance <= n4) || (n5 == n && this.m_polygon[n5].m_dDistance > n4)) {
            this.binSort(n, n2, n3, max, true);
            return;
        }
        if (this.m_polygon[n5].m_dDistance <= n4) {
            ++n5;
        }
        if (this.m_polygon[j].m_dDistance > n4) {
            --j;
        }
        if (j > n) {
            this.binSort(n, j, n3, n4, false);
        }
        if (n5 < n2) {
            this.binSort(n5, n2, n3, n4, false);
        }
    }
    
    public void render(final sfObject[] array, final int n, final Component component) {
        this.m_nPolygons = 0;
        this.m_dDistanceMax = -1.0E20;
        this.m_dDistanceMin = 1.0E20;
        for (int i = 0; i < n; ++i) {
            this.renderObject(array[i]);
        }
        if (this.m_nPolygons > 1) {
            this.binSort(0, this.m_nPolygons - 1, this.m_dDistanceMin, this.m_dDistanceMax, false);
        }
        final Graphics graphics = this.m_imageViewplane.getGraphics();
        graphics.setColor(this.m_colorBackgrnd);
        graphics.fillRect(0, 0, this.m_nViewplaneWidth, this.m_nViewplaneHeight);
        if (this.m_imageBackgrnd != null) {
            graphics.drawImage(this.m_imageBackgrnd, 0, 0, null);
        }
        for (int j = this.m_nPolygons - 1; j >= 0; --j) {
            this.m_polygon[j].draw(graphics);
        }
        graphics.dispose();
        final Graphics graphics2 = component.getGraphics();
        graphics2.drawImage(this.m_imageViewplane, this.m_nViewplaneX, this.m_nViewplaneY, null);
        graphics2.dispose();
    }
    
    public void setFratio(final double dFratio) {
        this.m_dFratio = dFratio;
        this.m_dViewplaneMult = Math.max(this.m_nViewplaneWidth, (double)this.m_nViewplaneHeight) * dFratio;
        this.m_dXoffset = this.m_nViewplaneWidth / 2.0;
        this.m_dYoffset = this.m_nViewplaneHeight / 2.0;
    }
}
