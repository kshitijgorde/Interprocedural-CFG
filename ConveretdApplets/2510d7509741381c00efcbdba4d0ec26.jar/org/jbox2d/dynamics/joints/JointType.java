// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

public enum JointType
{
    UNKNOWN_JOINT("UNKNOWN_JOINT", 0), 
    REVOLUTE_JOINT("REVOLUTE_JOINT", 1), 
    PRISMATIC_JOINT("PRISMATIC_JOINT", 2), 
    DISTANCE_JOINT("DISTANCE_JOINT", 3), 
    PULLEY_JOINT("PULLEY_JOINT", 4), 
    MOUSE_JOINT("MOUSE_JOINT", 5), 
    GEAR_JOINT("GEAR_JOINT", 6);
    
    private JointType(final String s, final int n) {
    }
}
