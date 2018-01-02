// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

public enum ShapeType
{
    UNKNOWN_SHAPE("UNKNOWN_SHAPE", 0), 
    CIRCLE_SHAPE("CIRCLE_SHAPE", 1), 
    BOX_SHAPE("BOX_SHAPE", 2), 
    POLYGON_SHAPE("POLYGON_SHAPE", 3), 
    MESH_SHAPE("MESH_SHAPE", 4), 
    SHAPE_TYPE_COUNT("SHAPE_TYPE_COUNT", 5);
    
    private ShapeType(final String s, final int n) {
    }
}
