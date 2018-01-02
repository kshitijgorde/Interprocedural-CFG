// 
// Decompiled by Procyon v0.5.30
// 

package imaging.math3D;

import java.util.Iterator;
import java.util.Vector;

public class PolygonTilt
{
    public static Polygon3D tilt(final Vector<float[]> points, final float tiltX, final float tiltY) {
        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE;
        float maxY = Float.MIN_VALUE;
        final Polygon3D polygon = new Polygon3D();
        polygon.ensureCapacity(points.size());
        int ix = 0;
        for (final float[] point : points) {
            final Vector3D temp = polygon.getVertex(ix++);
            temp.x = point[0];
            temp.y = point[1];
        }
        if (tiltX > 0.0f) {
            for (final float[] point : points) {
                minX = Math.min(minX, point[0]);
            }
            for (int i = 0; i < polygon.getNumVertices(); ++i) {
                final Vector3D vertex;
                final Vector3D temp = vertex = polygon.getVertex(i);
                vertex.z += (temp.x - minX) * tiltX;
            }
        }
        else if (tiltX < 0.0f) {
            for (final float[] point : points) {
                maxX = Math.max(maxX, point[0]);
            }
            for (int i = 0; i < polygon.getNumVertices(); ++i) {
                final Vector3D vertex2;
                final Vector3D temp = vertex2 = polygon.getVertex(i);
                vertex2.z += (temp.x - maxX) * tiltX;
            }
        }
        if (tiltY > 0.0f) {
            for (final float[] point : points) {
                minY = Math.min(minY, point[1]);
            }
            for (int i = 0; i < polygon.getNumVertices(); ++i) {
                final Vector3D vertex3;
                final Vector3D temp = vertex3 = polygon.getVertex(i);
                vertex3.z += (temp.y - minY) * tiltY;
            }
        }
        else if (tiltY < 0.0f) {
            for (final float[] point : points) {
                maxY = Math.max(maxY, point[1]);
            }
            for (int i = 0; i < polygon.getNumVertices(); ++i) {
                final Vector3D vertex4;
                final Vector3D temp = vertex4 = polygon.getVertex(i);
                vertex4.z += (temp.y - maxY) * tiltY;
            }
        }
        return polygon;
    }
}
