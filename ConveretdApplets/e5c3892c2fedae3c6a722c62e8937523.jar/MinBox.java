import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class MinBox
{
    static void calculateMinBox(final Point point, final Point point2, final ProjectionTransformation projectionTransformation, final LatLong latLong, final LatLong latLong2) {
        final LatLong projToLatLong = projectionTransformation.projToLatLong(point.x, point.y);
        latLong.latitude = projToLatLong.latitude;
        latLong.longitude = projToLatLong.longitude;
        latLong2.latitude = projToLatLong.latitude;
        latLong2.longitude = projToLatLong.longitude;
        final LatLong projToLatLong2 = projectionTransformation.projToLatLong(point2.x, point2.y);
        latLong.longitude = Math.min(latLong.longitude, projToLatLong2.longitude);
        latLong.latitude = Math.max(latLong.latitude, projToLatLong2.latitude);
        latLong2.longitude = Math.max(latLong2.longitude, projToLatLong2.longitude);
        latLong2.latitude = Math.min(latLong2.latitude, projToLatLong2.latitude);
        final LatLong projToLatLong3 = projectionTransformation.projToLatLong(point.x, point2.y);
        latLong.longitude = Math.min(latLong.longitude, projToLatLong3.longitude);
        latLong.latitude = Math.max(latLong.latitude, projToLatLong3.latitude);
        latLong2.longitude = Math.max(latLong2.longitude, projToLatLong3.longitude);
        latLong2.latitude = Math.min(latLong2.latitude, projToLatLong3.latitude);
        final LatLong projToLatLong4 = projectionTransformation.projToLatLong(point2.x, point.y);
        latLong.longitude = Math.min(latLong.longitude, projToLatLong4.longitude);
        latLong.latitude = Math.max(latLong.latitude, projToLatLong4.latitude);
        latLong2.longitude = Math.max(latLong2.longitude, projToLatLong4.longitude);
        latLong2.latitude = Math.min(latLong2.latitude, projToLatLong4.latitude);
    }
}
