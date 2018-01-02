import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class tdViewpoint
{
    public tdMatrix m_matrix;
    public tdVector m_vector;
    public double m_dAmbient;
    public tdMatrix m_matrixLight;
    double m_dFratio;
    int m_nViewplaneX;
    int m_nViewplaneY;
    int m_nViewplaneWidth;
    int m_nViewplaneHeight;
    public Image m_imageViewplane;
    public Image m_imageBackgrnd;
    public Color m_colorBackgrnd;
    public Image m_imagePlanet;
    public int m_nPlanetX;
    public int m_nPlanetY;
    double m_dViewplaneMult;
    double m_dXoffset;
    double m_dYoffset;
    final double Z_CLIP = 0.1;
    final int MAX_POLYGONS = 300;
    int m_nPolygons;
    tdPolygon[] m_polygon;
    double m_dDistanceMax;
    double m_dDistanceMin;
    
    public tdViewpoint(final int nViewplaneX, final int nViewplaneY, final int nViewplaneWidth, final int nViewplaneHeight, final Component component) {
        this.m_matrix = new tdMatrix();
        this.m_vector = new tdVector();
        this.m_dAmbient = 0.2;
        (this.m_matrixLight = new tdMatrix()).rotateWorld(-0.39269908169872414, -0.39269908169872414, 0.0);
        this.m_nViewplaneX = nViewplaneX;
        this.m_nViewplaneY = nViewplaneY;
        this.m_nViewplaneWidth = nViewplaneWidth;
        this.m_nViewplaneHeight = nViewplaneHeight;
        this.m_imageViewplane = component.createImage(nViewplaneWidth, nViewplaneHeight);
        this.m_imageBackgrnd = null;
        this.m_colorBackgrnd = Color.black;
        this.m_imagePlanet = null;
        this.m_polygon = new tdPolygon[300];
        this.setFratio(1.0);
    }
    
    private void renderObject(final tdObject tdObject) {
        for (int i = 0; i < tdObject.m_nVertices; ++i) {
            final double n = tdObject.m_vectorWorld[i].m_dX - this.m_vector.m_dX;
            final double n2 = tdObject.m_vectorWorld[i].m_dY - this.m_vector.m_dY;
            final double n3 = tdObject.m_vectorWorld[i].m_dZ - this.m_vector.m_dZ;
            final tdVector tdVector = tdObject.m_vectorView[i];
            tdVector.m_dX = n * this.m_matrix.m_dXx + n2 * this.m_matrix.m_dXy + n3 * this.m_matrix.m_dXz;
            tdVector.m_dY = n * this.m_matrix.m_dYx + n2 * this.m_matrix.m_dYy + n3 * this.m_matrix.m_dYz;
            tdVector.m_dZ = n * this.m_matrix.m_dZx + n2 * this.m_matrix.m_dZy + n3 * this.m_matrix.m_dZz;
            if (tdVector.m_dZ <= 0.1) {
                tdObject.m_bVisible[i] = false;
            }
            else {
                tdObject.m_bVisible[i] = true;
                tdObject.m_nXview[i] = (int)(-tdVector.m_dX / tdVector.m_dZ * this.m_dViewplaneMult + this.m_dXoffset);
                tdObject.m_nYview[i] = (int)(-tdVector.m_dY / tdVector.m_dZ * this.m_dViewplaneMult + this.m_dYoffset);
            }
        }
        for (int j = 0; j < tdObject.m_nPolygons; ++j) {
            final tdFace tdFace = tdObject.m_model.m_face[j];
            final tdPolygon tdPolygon = tdObject.m_polygon[j];
            tdPolygon.m_dDistance = 0.0;
            int n4 = 1;
            for (int k = 0; k < tdFace.m_nVertices; ++k) {
                final int n5 = tdFace.m_ndxVertex[k];
                if (!tdObject.m_bVisible[n5]) {
                    n4 = 0;
                    break;
                }
                tdPolygon.m_nX[k] = tdObject.m_nXview[n5];
                tdPolygon.m_nY[k] = tdObject.m_nYview[n5];
                tdPolygon.m_dDistance = Math.max(tdPolygon.m_dDistance, tdObject.m_vectorView[n5].m_dZ);
            }
            final tdVector tdVector2 = tdObject.m_vectorView[tdFace.m_ndxVertex[0]];
            final tdVector tdVector3 = tdObject.m_vectorView[tdFace.m_ndxVertex[1]];
            final tdVector tdVector4 = tdObject.m_vectorView[tdFace.m_ndxVertex[2]];
            if (n4 != 0) {
                final double n6 = tdVector3.m_dX - tdVector2.m_dX;
                final double n7 = tdVector3.m_dY - tdVector2.m_dY;
                final double n8 = tdVector3.m_dZ - tdVector2.m_dZ;
                final double n9 = tdVector4.m_dX - tdVector3.m_dX;
                final double n10 = tdVector4.m_dY - tdVector3.m_dY;
                final double n11 = tdVector4.m_dZ - tdVector3.m_dZ;
                final double n12 = n7 * n11 - n8 * n10;
                final double n13 = n8 * n9 - n6 * n11;
                final double n14 = n6 * n10 - n7 * n9;
                boolean b;
                if (tdVector3.m_dX * n12 + tdVector3.m_dY * n13 + tdVector3.m_dZ * n14 >= 0.0) {
                    tdPolygon.m_color = tdFace.m_colorFront;
                    b = true;
                }
                else {
                    tdPolygon.m_color = tdFace.m_colorBack;
                    b = false;
                }
                if (tdPolygon.m_color == null) {
                    n4 = 0;
                }
                else {
                    double n15 = (n12 * this.m_matrixLight.m_dZx + n13 * this.m_matrixLight.m_dZy + n14 * this.m_matrixLight.m_dZz) / Math.sqrt(n12 * n12 + n13 * n13 + n14 * n14);
                    if (!b) {
                        n15 *= -1.0;
                    }
                    if (n15 < 0.0) {
                        n15 = 0.0;
                    }
                    if (n15 < 1.0) {
                        final double n16 = this.m_dAmbient + (1.0 - this.m_dAmbient) * n15;
                        final int rgb = tdPolygon.m_color.getRGB();
                        tdPolygon.m_color = new Color(((int)((rgb & 0xFF0000) * n16) & 0xFF0000) + ((int)((rgb & 0xFF00) * n16) & 0xFF00) + (int)((rgb & 0xFF) * n16));
                    }
                }
            }
            if (n4 != 0 && this.m_nPolygons < 300) {
                this.m_polygon[this.m_nPolygons] = tdPolygon;
                ++this.m_nPolygons;
                this.m_dDistanceMax = Math.max(this.m_dDistanceMax, tdPolygon.m_dDistance);
                this.m_dDistanceMin = Math.min(this.m_dDistanceMin, tdPolygon.m_dDistance);
            }
        }
    }
    
    private void binSort(final int n, final int n2, double n3, double max, final boolean b) {
        if (n2 - n == 1) {
            if (this.m_polygon[n].m_dDistance > this.m_polygon[n2].m_dDistance) {
                final tdPolygon tdPolygon = this.m_polygon[n];
                this.m_polygon[n] = this.m_polygon[n2];
                this.m_polygon[n2] = tdPolygon;
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
                final tdPolygon tdPolygon2 = this.m_polygon[j];
                this.m_polygon[j] = this.m_polygon[n5];
                this.m_polygon[n5] = tdPolygon2;
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
    
    public void render(final tdObject[] array, final int n, final Component component) {
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
        int n2 = 0;
        if (this.m_imagePlanet != null) {
            n2 = 1;
        }
        for (int j = this.m_nPolygons - 1; j >= 0; --j) {
            if (n2 != 0 && this.m_polygon[j].m_dDistance < this.m_vector.m_dZ) {
                graphics.drawImage(this.m_imagePlanet, this.m_nPlanetX, this.m_nPlanetY, null);
                n2 = 0;
            }
            this.m_polygon[j].draw(graphics);
        }
        if (n2 != 0) {
            graphics.drawImage(this.m_imagePlanet, this.m_nPlanetX, this.m_nPlanetY, null);
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
