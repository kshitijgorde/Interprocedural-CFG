// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import jfig.java2d.FigEllipseRenderer;
import jfig.java2d.FigTextRenderer;
import jfig.java2d.FigImageRenderer;
import jfig.java2d.FigRectangleRenderer;
import jfig.java2d.FigArcRenderer;
import jfig.java2d.FigSplineRenderer;
import jfig.java2d.FigBezierSplineRenderer;
import jfig.java2d.FigXSplineRenderer;
import jfig.java2d.FigPolylineRenderer;

public class FigObjectFactory
{
    private static FigObjectFactory defaultFactory;
    private FigObjectFactory factory;
    
    public static FigObjectFactory getDefaultObjectFactory() {
        if (FigObjectFactory.defaultFactory == null) {
            final FigObjectFactory figObjectFactory = new FigObjectFactory();
            if (FigAttribs.enableJava2D) {
                final FigObjectFactory figObjectFactory2 = figObjectFactory;
                if (figObjectFactory2 == null) {
                    throw null;
                }
                FigObjectFactory.defaultFactory = figObjectFactory2.new Java2DFactory();
            }
            else {
                final FigObjectFactory figObjectFactory3 = figObjectFactory;
                if (figObjectFactory3 == null) {
                    throw null;
                }
                FigObjectFactory.defaultFactory = figObjectFactory3.new AWTFactory();
            }
        }
        return FigObjectFactory.defaultFactory;
    }
    
    public FigRenderer createPolylineRenderer(final FigPolyline figPolyline) {
        return this.factory.createPolylineRenderer(figPolyline);
    }
    
    public FigRenderer createXSplineRenderer(final FigXSpline figXSpline) {
        return this.factory.createXSplineRenderer(figXSpline);
    }
    
    public FigRenderer createSplineRenderer(final FigSpline figSpline) {
        return this.factory.createSplineRenderer(figSpline);
    }
    
    public FigRenderer createBezierSplineRenderer(final FigBezier figBezier) {
        return this.factory.createBezierSplineRenderer(figBezier);
    }
    
    public FigRenderer createArcRenderer(final FigArc figArc) {
        return this.factory.createArcRenderer(figArc);
    }
    
    public FigRenderer createRectangleRenderer(final FigRectangle figRectangle) {
        return this.factory.createRectangleRenderer(figRectangle);
    }
    
    public FigRenderer createImageRenderer(final FigImage figImage) {
        return this.factory.createImageRenderer(figImage);
    }
    
    public FigRenderer createTextRenderer(final FigText figText) {
        return this.factory.createTextRenderer(figText);
    }
    
    public FigRenderer createEllipseRenderer(final FigEllipse figEllipse) {
        return this.factory.createEllipseRenderer(figEllipse);
    }
    
    class Java2DFactory extends FigObjectFactory
    {
        public FigRenderer createPolylineRenderer(final FigPolyline figPolyline) {
            return new FigPolylineRenderer(figPolyline);
        }
        
        public FigRenderer createXSplineRenderer(final FigXSpline figXSpline) {
            return (FigRenderer)new FigXSplineRenderer(figXSpline);
        }
        
        public FigRenderer createBezierSplineRenderer(final FigBezier figBezier) {
            return (FigRenderer)new FigBezierSplineRenderer(figBezier);
        }
        
        public FigRenderer createSplineRenderer(final FigSpline figSpline) {
            return (FigRenderer)new FigSplineRenderer(figSpline);
        }
        
        public FigRenderer createArcRenderer(final FigArc figArc) {
            return (FigRenderer)new FigArcRenderer(figArc);
        }
        
        public FigRenderer createRectangleRenderer(final FigRectangle figRectangle) {
            return new FigRectangleRenderer(figRectangle);
        }
        
        public FigRenderer createImageRenderer(final FigImage figImage) {
            return (FigRenderer)new FigImageRenderer(figImage);
        }
        
        public FigRenderer createTextRenderer(final FigText figText) {
            return new FigTextRenderer(figText);
        }
        
        public FigRenderer createEllipseRenderer(final FigEllipse figEllipse) {
            return new FigEllipseRenderer(figEllipse);
        }
    }
    
    class AWTFactory extends FigObjectFactory
    {
        public FigRenderer createPolylineRenderer(final FigPolyline figPolyline) {
            return (FigRenderer)new AWTPolylineRenderer(figPolyline);
        }
        
        public FigRenderer createXSplineRenderer(final FigXSpline figXSpline) {
            return (FigRenderer)new AWTXSplineRenderer(figXSpline);
        }
        
        public FigRenderer createBezierSplineRenderer(final FigBezier figBezier) {
            return (FigRenderer)new AWTBezierSplineRenderer(figBezier);
        }
        
        public FigRenderer createSplineRenderer(final FigSpline figSpline) {
            return (FigRenderer)new AWTSplineRenderer(figSpline);
        }
        
        public FigRenderer createArcRenderer(final FigArc figArc) {
            return (FigRenderer)new AWTArcRenderer(figArc);
        }
        
        public FigRenderer createRectangleRenderer(final FigRectangle figRectangle) {
            return (FigRenderer)new AWTRectangleRenderer(figRectangle);
        }
        
        public FigRenderer createImageRenderer(final FigImage figImage) {
            return (FigRenderer)new AWTImageRenderer(figImage);
        }
        
        public FigRenderer createTextRenderer(final FigText figText) {
            return (FigRenderer)new AWTTextRenderer(figText);
        }
        
        public FigRenderer createEllipseRenderer(final FigEllipse figEllipse) {
            return (FigRenderer)new AWTEllipseRenderer(figEllipse);
        }
    }
}
