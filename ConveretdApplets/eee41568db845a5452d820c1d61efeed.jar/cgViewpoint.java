import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class cgViewpoint
{
    public cgMatrix m_matrix;
    public cgVector m_vector;
    public double m_dAmbient;
    public cgMatrix m_matrixLight;
    double m_dFratio;
    public Image m_imageViewplane;
    int m_nViewplaneWidth;
    int m_nViewplaneHeight;
    public Image m_imageBackgrnd;
    public Color m_colorBackgrnd;
    public double m_dViewplaneMult;
    public double m_dXoffset;
    public double m_dYoffset;
    final double Z_CLIP = 1.0;
    final int MAX_POLYGONS = 300;
    int m_nPolygons;
    cgPolygon[] m_polygon;
    double m_dDistanceMax;
    double m_dDistanceMin;
    
    public cgViewpoint(final int nViewplaneWidth, final int nViewplaneHeight, final Component component) {
        this.m_colorBackgrnd = Color.black;
        this.m_matrix = new cgMatrix();
        this.m_vector = new cgVector();
        this.m_dAmbient = 0.2;
        this.m_matrixLight = new cgMatrix();
        this.m_nViewplaneWidth = nViewplaneWidth;
        this.m_nViewplaneHeight = nViewplaneHeight;
        this.m_imageViewplane = component.createImage(nViewplaneWidth, nViewplaneHeight);
        this.m_imageBackgrnd = null;
        this.m_colorBackgrnd = Color.black;
        this.m_polygon = new cgPolygon[300];
        this.setFratio(1.0);
    }
    
    public void draw(final Golfball3D golfball3D, final Arrow arrow) {
        final Graphics graphics = this.m_imageViewplane.getGraphics();
        if (this.m_imageBackgrnd != null) {
            graphics.drawImage(this.m_imageBackgrnd, 0, 0, null);
        }
        else {
            graphics.setColor(this.m_colorBackgrnd);
            graphics.fillRect(0, 0, this.m_nViewplaneWidth, this.m_nViewplaneHeight);
        }
        int n = 1;
        double dz = 0.0;
        if (golfball3D.m_object.m_bVisible[0]) {
            n = 0;
            dz = golfball3D.m_object.m_vectorView[0].m_dZ;
        }
        for (int i = this.m_nPolygons - 1; i >= 0; --i) {
            if (n == 0 && this.m_polygon[i].m_dDistance <= dz) {
                golfball3D.drawBall(graphics);
                n = 1;
            }
            this.m_polygon[i].draw(graphics);
        }
        if (n == 0) {
            golfball3D.drawBall(graphics);
        }
        golfball3D.drawString(graphics);
        arrow.draw(graphics);
    }
    
    public void renderObject(final cgObject cgObject) {
        for (int i = 0; i < cgObject.m_nVertices; ++i) {
            final double n = cgObject.m_vectorWorld[i].m_dX - this.m_vector.m_dX;
            final double n2 = cgObject.m_vectorWorld[i].m_dY - this.m_vector.m_dY;
            final double n3 = cgObject.m_vectorWorld[i].m_dZ - this.m_vector.m_dZ;
            final cgVector cgVector = cgObject.m_vectorView[i];
            cgVector.m_dX = n * this.m_matrix.m_dXx + n2 * this.m_matrix.m_dXy + n3 * this.m_matrix.m_dXz;
            cgVector.m_dY = n * this.m_matrix.m_dYx + n2 * this.m_matrix.m_dYy + n3 * this.m_matrix.m_dYz;
            cgVector.m_dZ = n * this.m_matrix.m_dZx + n2 * this.m_matrix.m_dZy + n3 * this.m_matrix.m_dZz;
            if (cgVector.m_dZ <= 1.0) {
                cgObject.m_bVisible[i] = false;
            }
            else {
                cgObject.m_bVisible[i] = true;
                cgObject.m_nXview[i] = (int)(-cgVector.m_dX / cgVector.m_dZ * this.m_dViewplaneMult + this.m_dXoffset);
                cgObject.m_nYview[i] = (int)(-cgVector.m_dY / cgVector.m_dZ * this.m_dViewplaneMult + this.m_dYoffset);
            }
        }
        for (int j = 0; j < cgObject.m_nPolygons; ++j) {
            final cgFace cgFace = cgObject.m_model.m_face[j];
            final cgPolygon cgPolygon = cgObject.m_polygon[j];
            cgPolygon.m_dDistance = 0.0;
            int n4 = 1;
            int max = -99999;
            int max2 = -99999;
            int min = 99999;
            int min2 = 99999;
            for (int k = 0; k < cgFace.m_nVertices; ++k) {
                final int n5 = cgFace.m_ndxVertex[k];
                if (!cgObject.m_bVisible[n5]) {
                    n4 = 0;
                    break;
                }
                final int n6 = cgObject.m_nXview[n5];
                final int n7 = cgObject.m_nYview[n5];
                max = Math.max(max, n6);
                min = Math.min(min, n6);
                max2 = Math.max(max2, n7);
                min2 = Math.min(min2, n7);
                cgPolygon.m_nX[k] = n6;
                cgPolygon.m_nY[k] = n7;
                if (cgFace.m_nDistanceVertex < 0) {
                    cgPolygon.m_dDistance = Math.max(cgPolygon.m_dDistance, cgObject.m_vectorView[n5].m_dZ);
                }
                else if (k == cgFace.m_nDistanceVertex) {
                    cgPolygon.m_dDistance = cgObject.m_vectorView[n5].m_dZ;
                }
            }
            if (n4 != 0 && (cgFace.m_nFlags & 0x4) == 0x0 && (min > this.m_nViewplaneWidth || max < 0 || min2 > this.m_nViewplaneHeight || max2 < 0)) {
                n4 = 0;
            }
            if (n4 != 0) {
                if (cgFace.m_nVertices < 3) {
                    cgPolygon.m_color = cgFace.m_colorFront;
                }
                else {
                    final cgVector cgVector2 = cgObject.m_vectorView[cgFace.m_ndxVertex[0]];
                    final cgVector cgVector3 = cgObject.m_vectorView[cgFace.m_ndxVertex[1]];
                    final cgVector cgVector4 = cgObject.m_vectorView[cgFace.m_ndxVertex[2]];
                    final double n8 = cgVector3.m_dX - cgVector2.m_dX;
                    final double n9 = cgVector3.m_dY - cgVector2.m_dY;
                    final double n10 = cgVector3.m_dZ - cgVector2.m_dZ;
                    final double n11 = cgVector4.m_dX - cgVector3.m_dX;
                    final double n12 = cgVector4.m_dY - cgVector3.m_dY;
                    final double n13 = cgVector4.m_dZ - cgVector3.m_dZ;
                    final double n14 = cgVector3.m_dX * (n9 * n13 - n10 * n12) + cgVector3.m_dY * (n10 * n11 - n8 * n13) + cgVector3.m_dZ * (n8 * n12 - n9 * n11);
                    boolean b = false;
                    boolean b2;
                    if (n14 >= 0.0) {
                        cgPolygon.m_color = cgFace.m_colorFront;
                        b2 = true;
                        if ((cgFace.m_nFlags & 0x1) != 0x0) {
                            b = true;
                        }
                    }
                    else {
                        cgPolygon.m_color = cgFace.m_colorBack;
                        b2 = false;
                        if ((cgFace.m_nFlags & 0x2) != 0x0) {
                            b = true;
                        }
                    }
                    if (cgPolygon.m_color != null && !b) {
                        double n15 = cgObject.m_vectorFaceNormal[j].m_dX * this.m_matrixLight.m_dZx + cgObject.m_vectorFaceNormal[j].m_dY * this.m_matrixLight.m_dZy + cgObject.m_vectorFaceNormal[j].m_dZ * this.m_matrixLight.m_dZz;
                        if (!b2) {
                            n15 *= -1.0;
                        }
                        final double n16 = this.m_dAmbient + (1.0 - this.m_dAmbient) / 2.0 * (1.0 + n15);
                        final int rgb = cgPolygon.m_color.getRGB();
                        cgPolygon.m_color = new Color(((int)((rgb & 0xFF0000) * n16) & 0xFF0000) + ((int)((rgb & 0xFF00) * n16) & 0xFF00) + (int)((rgb & 0xFF) * n16));
                    }
                }
                if (cgObject.m_bSortPolys && cgPolygon.m_color != null && this.m_nPolygons < 300) {
                    this.m_polygon[this.m_nPolygons] = cgPolygon;
                    ++this.m_nPolygons;
                    this.m_dDistanceMax = Math.max(this.m_dDistanceMax, cgPolygon.m_dDistance);
                    this.m_dDistanceMin = Math.min(this.m_dDistanceMin, cgPolygon.m_dDistance);
                }
            }
        }
    }
    
    private void binSort(final int n, final int n2, double n3, double max, final boolean b) {
        if (n2 - n == 1) {
            if (this.m_polygon[n].m_dDistance > this.m_polygon[n2].m_dDistance) {
                final cgPolygon cgPolygon = this.m_polygon[n];
                this.m_polygon[n] = this.m_polygon[n2];
                this.m_polygon[n2] = cgPolygon;
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
                final cgPolygon cgPolygon2 = this.m_polygon[j];
                this.m_polygon[j] = this.m_polygon[n5];
                this.m_polygon[n5] = cgPolygon2;
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
    
    public void lookAt(final double n, final double n2, final double n3) {
        final cgVector cgVector = new cgVector(n - this.m_vector.m_dX, n2 - this.m_vector.m_dY, n3 - this.m_vector.m_dZ);
        if (cgVector.normalize() == 0.0) {
            return;
        }
        final cgVector cgVector2 = new cgVector(-cgVector.m_dY, cgVector.m_dX, 0.0);
        if (cgVector2.normalize() == 0.0) {
            cgVector2.m_dX = -1.0;
        }
        this.m_matrix = new cgMatrix(cgVector2, new cgVector(cgVector, cgVector2), cgVector);
    }
    
    public void render(final cgObject[] array, final int n) {
        this.m_nPolygons = 0;
        this.m_dDistanceMax = -1.0E20;
        this.m_dDistanceMin = 1.0E20;
        for (int i = 0; i < n; ++i) {
            this.renderObject(array[i]);
        }
        if (this.m_nPolygons > 1) {
            this.binSort(0, this.m_nPolygons - 1, this.m_dDistanceMin, this.m_dDistanceMax, false);
        }
    }
    
    public void setFratio(final double dFratio) {
        this.m_dFratio = dFratio;
        this.m_dViewplaneMult = Math.max(this.m_nViewplaneWidth, (double)this.m_nViewplaneHeight) * dFratio;
        this.m_dXoffset = this.m_nViewplaneWidth / 2.0;
        this.m_dYoffset = this.m_nViewplaneHeight / 2.0;
    }
    
    public double getFratio() {
        return this.m_dFratio;
    }
}
