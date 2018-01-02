// 
// Decompiled by Procyon v0.5.30
// 

public class Pyramid extends PolyhedronElement
{
    Pyramid(final PolygonElement polygonElement, final PointElement pointElement) {
        super.dimension = 2;
        super.n = 1 + polygonElement.n;
        (super.P = new PolygonElement[super.n])[0] = polygonElement;
        for (int i = 1; i < super.n; ++i) {
            super.P[i] = new PolygonElement(pointElement, polygonElement.V[i - 1], polygonElement.V[i % polygonElement.n]);
        }
    }
}
