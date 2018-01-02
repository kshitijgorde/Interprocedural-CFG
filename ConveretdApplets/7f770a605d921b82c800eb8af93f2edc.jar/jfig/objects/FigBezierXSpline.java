// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import jfig.canvas.FigTrafo2D;

public class FigBezierXSpline extends FigXSpline
{
    public void createSfactors() {
        this.sfactors = new double[this.wcp.length];
        for (int i = 0; i < this.sfactors.length; ++i) {
            this.sfactors[i] = -1.0;
        }
        if (!this.is_closed) {
            this.sfactors[0] = 0.0;
            this.sfactors[this.sfactors.length - 1] = 0.0;
        }
    }
    
    public FigObject copy() {
        final FigBezierXSpline figBezierXSpline = new FigBezierXSpline(this.wcp[0].x, this.wcp[0].y, this.is_closed, this.attribs.getClone(), this.trafo);
        figBezierXSpline.setPoints(this.getPoints());
        return figBezierXSpline;
    }
    
    public FigBezierXSpline(final int n, final int n2, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D) {
        super(n, n2, figAttribs, figTrafo2D);
    }
    
    public FigBezierXSpline(final int n, final int n2, final boolean b, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D) {
        super(n, n2, b, figAttribs, figTrafo2D);
    }
}
