// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.dynamics.TimeStep;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public abstract class Joint
{
    public JointType m_type;
    public Joint m_prev;
    public Joint m_next;
    public JointEdge m_node1;
    public JointEdge m_node2;
    public Body m_body1;
    public Body m_body2;
    public boolean m_islandFlag;
    public boolean m_collideConnected;
    public Object m_userData;
    
    public Joint(final JointDef description) {
        this.m_type = description.type;
        this.m_prev = null;
        this.m_next = null;
        this.m_node1 = new JointEdge();
        this.m_node2 = new JointEdge();
        this.m_body1 = description.body1;
        this.m_body2 = description.body2;
        this.m_collideConnected = description.collideConnected;
        this.m_islandFlag = false;
        this.m_userData = description.userData;
    }
    
    public static void destroy(final Joint j) {
        j.destructor();
    }
    
    public void destructor() {
    }
    
    public static Joint create(final JointDef description) {
        Joint joint = null;
        if (description.type == JointType.DISTANCE_JOINT) {
            joint = new DistanceJoint((DistanceJointDef)description);
        }
        else if (description.type == JointType.MOUSE_JOINT) {
            joint = new MouseJoint((MouseJointDef)description);
        }
        else if (description.type == JointType.PRISMATIC_JOINT) {
            joint = new PrismaticJoint((PrismaticJointDef)description);
        }
        else if (description.type == JointType.REVOLUTE_JOINT) {
            joint = new RevoluteJoint((RevoluteJointDef)description);
        }
        else if (description.type == JointType.PULLEY_JOINT) {
            joint = new PulleyJoint((PulleyJointDef)description);
        }
        else if (description.type == JointType.GEAR_JOINT) {
            joint = new GearJoint((GearJointDef)description);
        }
        else {
            assert false;
        }
        return joint;
    }
    
    public JointType getType() {
        return this.m_type;
    }
    
    public Body getBody1() {
        return this.m_body1;
    }
    
    public Body getBody2() {
        return this.m_body2;
    }
    
    public abstract Vec2 getAnchor1();
    
    public abstract Vec2 getAnchor2();
    
    public abstract Vec2 getReactionForce();
    
    public abstract float getReactionTorque();
    
    public Joint getNext() {
        return this.m_next;
    }
    
    public Object getUserData() {
        return this.m_userData;
    }
    
    public abstract void initVelocityConstraints(final TimeStep p0);
    
    public abstract void solveVelocityConstraints(final TimeStep p0);
    
    public void initPositionConstraints() {
    }
    
    public abstract boolean solvePositionConstraints();
}
