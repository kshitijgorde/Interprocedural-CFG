// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.Graphics;
import java.awt.Color;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.awt.Computable;

public class VectorField extends Drawable implements Computable
{
    public static final int ARROWS = 0;
    public static final int LINES = 1;
    public static final int CLAMPED_VECTORS = 2;
    public static final int SCALED_LINES = 3;
    public static final int SCALED_VECTORS = 4;
    private int style;
    private Function xFunc;
    private Function yFunc;
    private Color graphColor;
    private boolean changed;
    private transient int[][] data;
    private int pixelSpacing;
    
    public VectorField() {
        this(null, null, 0);
    }
    
    public VectorField(final Function function, final Function function2) {
        this(function, function2, 0);
    }
    
    public VectorField(final Function xFunc, final Function yFunc, final int style) {
        this.graphColor = Color.lightGray;
        this.pixelSpacing = 30;
        if ((xFunc != null && xFunc.getArity() != 2) || (yFunc != null && yFunc.getArity() != 2)) {
            throw new IllegalArgumentException("Internal Error:  The functions that define a vector must be functions of two variables.");
        }
        this.xFunc = xFunc;
        this.yFunc = yFunc;
        this.style = style;
        this.changed = true;
    }
    
    public void setColor(final Color graphColor) {
        if (graphColor != null & !graphColor.equals(this.graphColor)) {
            this.graphColor = graphColor;
            this.needsRedraw();
        }
    }
    
    public Color getColor() {
        return this.graphColor;
    }
    
    public synchronized void setFunctions(final Function xFunction, final Function yFunction) {
        this.setXFunction(xFunction);
        this.setYFunction(yFunction);
    }
    
    public synchronized void setXFunction(final Function xFunc) {
        if (xFunc != null && xFunc.getArity() != 2) {
            throw new IllegalArgumentException("Internal Error:  VectorField can only use functions of two variables.");
        }
        if (xFunc != this.xFunc) {
            this.xFunc = xFunc;
            this.changed = true;
            this.needsRedraw();
        }
    }
    
    public synchronized void setYFunction(final Function yFunc) {
        if (yFunc != null && yFunc.getArity() != 1) {
            throw new IllegalArgumentException("Internal Error:  VectorField can only use functions of two variables.");
        }
        if (yFunc != this.yFunc) {
            this.yFunc = yFunc;
            this.changed = true;
            this.needsRedraw();
        }
    }
    
    public Function getXFunction() {
        return this.xFunc;
    }
    
    public Function getYFunction() {
        return this.yFunc;
    }
    
    public int getStyle() {
        return this.style;
    }
    
    public void setStyle(final int style) {
        if (this.style != style) {
            this.style = style;
            this.changed = true;
            this.needsRedraw();
        }
    }
    
    public int getPixelSpacing() {
        return this.pixelSpacing;
    }
    
    public void setPixelSpacing(int pixelSpacing) {
        if (pixelSpacing < 5) {
            pixelSpacing = 5;
        }
        else if (pixelSpacing > 200) {
            pixelSpacing = 200;
        }
        if (pixelSpacing != this.pixelSpacing) {
            this.pixelSpacing = pixelSpacing;
            this.changed = true;
            this.needsRedraw();
        }
    }
    
    public synchronized void compute() {
        this.setup();
        this.needsRedraw();
        this.changed = false;
    }
    
    public synchronized void draw(final Graphics graphics, final boolean b) {
        if (this.changed || b || this.data == null) {
            this.setup();
            this.changed = false;
        }
        if (this.data == null) {
            return;
        }
        graphics.setColor(this.graphColor);
        final boolean b2 = this.style == 0 || this.style == 2 || this.style == 4;
        for (int i = 0; i < this.data.length; ++i) {
            final int[] array = this.data[i];
            if (array[0] != Integer.MIN_VALUE) {
                graphics.drawLine(array[0], array[1], array[2], array[3]);
                if (b2 && array[4] != Integer.MIN_VALUE) {
                    graphics.drawLine(array[2], array[3], array[4], array[5]);
                    graphics.drawLine(array[2], array[3], array[6], array[7]);
                }
            }
        }
    }
    
    private void setup() {
        if (this.xFunc == null || this.yFunc == null || super.coords == null) {
            this.data = null;
            return;
        }
        final boolean b = this.style == 0 || this.style == 2 || this.style == 4;
        final double[] array = new double[2];
        final int n = super.coords.getWidth() / this.pixelSpacing + 2;
        final int n2 = super.coords.getHeight() / this.pixelSpacing + 2;
        final double n3 = this.pixelSpacing * super.coords.getPixelWidth();
        final double n4 = this.pixelSpacing * super.coords.getPixelHeight();
        final double n5 = (super.coords.getXmax() + super.coords.getXmin() - n * n3) / 2.0;
        final double n6 = (super.coords.getYmax() + super.coords.getYmin() - n2 * n4) / 2.0;
        this.data = new int[n * n2][b ? 8 : 4];
        final double[][] array2 = new double[n][n2];
        final double[][] array3 = new double[n][n2];
        final double pixelWidth = super.coords.getPixelWidth();
        final double pixelHeight = super.coords.getPixelHeight();
        double n7 = 0.0;
        for (int i = 0; i < n; ++i) {
            array[0] = n5 + i * n3;
            for (int j = 0; j < n2; ++j) {
                array[1] = n6 + j * n4;
                array2[i][j] = this.xFunc.getVal(array);
                array3[i][j] = this.yFunc.getVal(array);
                if (!Double.isNaN(array2[i][j]) && !Double.isNaN(array3[i][j]) && !Double.isInfinite(array2[i][j]) && !Double.isInfinite(array3[i][j])) {
                    array2[i][j] /= pixelWidth;
                    array3[i][j] = -array3[i][j] / pixelHeight;
                    final double n8 = array2[i][j] * array2[i][j] + array3[i][j] * array3[i][j];
                    if (n8 > n7) {
                        n7 = n8;
                    }
                }
            }
        }
        final double sqrt = Math.sqrt(n7);
        int n9 = 0;
        for (int k = 0; k < n; ++k) {
            final int xToPixel = super.coords.xToPixel(n5 + k * n3);
            for (int l = 0; l < n2; ++l) {
                final int yToPixel = super.coords.yToPixel(n6 + l * n4);
                final int[] array4 = this.data[n9];
                ++n9;
                if (Double.isNaN(array2[k][l]) || Double.isNaN(array3[k][l]) || Double.isInfinite(array2[k][l]) || Double.isInfinite(array3[k][l])) {
                    array4[k] = Integer.MIN_VALUE;
                }
                else {
                    final double sqrt2 = Math.sqrt(array2[k][l] * array2[k][l] + array3[k][l] * array3[k][l]);
                    if (sqrt2 < 1.0E-15 || (sqrt == 0.0 && (this.style == 3 || this.style == 4))) {
                        array4[0] = (array4[2] = xToPixel);
                        array4[1] = (array4[3] = yToPixel);
                        if (b) {
                            array4[4] = Integer.MIN_VALUE;
                        }
                    }
                    else {
                        boolean b2 = false;
                        switch (this.style) {
                            case 0: {
                                final double n10 = 0.8 * this.pixelSpacing * array2[k][l] / sqrt2;
                                final double n11 = 0.8 * this.pixelSpacing * array3[k][l] / sqrt2;
                                array4[0] = xToPixel;
                                array4[1] = yToPixel;
                                array4[2] = (int)(xToPixel + n10);
                                array4[3] = (int)(yToPixel + n11);
                                break;
                            }
                            case 1: {
                                final double n12 = 0.8 * this.pixelSpacing * array2[k][l] / sqrt2 / 2.0;
                                final double n13 = 0.8 * this.pixelSpacing * array3[k][l] / sqrt2 / 2.0;
                                array4[0] = (int)(xToPixel - n12);
                                array4[1] = (int)(yToPixel - n13);
                                array4[2] = (int)(xToPixel + n12);
                                array4[3] = (int)(yToPixel + n13);
                                break;
                            }
                            case 2: {
                                double n14 = sqrt2;
                                if (n14 > 0.9 * this.pixelSpacing) {
                                    n14 = 0.9 * this.pixelSpacing;
                                    b2 = true;
                                }
                                final double n15 = array2[k][l] / sqrt2 * n14;
                                final double n16 = array3[k][l] / sqrt2 * n14;
                                array4[0] = xToPixel;
                                array4[1] = yToPixel;
                                array4[2] = (int)(xToPixel + n15);
                                array4[3] = (int)(yToPixel + n16);
                                break;
                            }
                            case 3: {
                                final double n17 = sqrt2 / sqrt * this.pixelSpacing;
                                final double n18 = array2[k][l] / sqrt2 * n17 / 2.0;
                                final double n19 = array3[k][l] / sqrt2 * n17 / 2.0;
                                array4[0] = (int)(xToPixel - n18);
                                array4[1] = (int)(yToPixel - n19);
                                array4[2] = (int)(xToPixel + n18);
                                array4[3] = (int)(yToPixel + n19);
                                break;
                            }
                            case 4: {
                                final double n20 = sqrt2 / sqrt * this.pixelSpacing;
                                final double n21 = array2[k][l] / sqrt2 * n20;
                                final double n22 = array3[k][l] / sqrt2 * n20;
                                array4[0] = xToPixel;
                                array4[1] = yToPixel;
                                array4[2] = (int)(xToPixel + n21);
                                array4[3] = (int)(yToPixel + n22);
                                break;
                            }
                        }
                        if (b) {
                            final int n23 = (array4[2] - array4[0]) / 5;
                            final int n24 = (array4[3] - array4[1]) / 5;
                            if (b2 || (n23 == 0 && n24 == 0)) {
                                array4[4] = Integer.MIN_VALUE;
                            }
                            else {
                                array4[4] = array4[2] + n24 - n23;
                                array4[5] = array4[3] - n23 - n24;
                                array4[6] = array4[2] - n23 - n24;
                                array4[7] = array4[3] + n23 - n24;
                            }
                        }
                    }
                }
            }
        }
    }
}
