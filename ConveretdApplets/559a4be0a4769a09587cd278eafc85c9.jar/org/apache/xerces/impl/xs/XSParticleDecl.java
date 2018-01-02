// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.psvi.XSTerm;
import org.apache.xerces.impl.xs.psvi.XSParticle;

public class XSParticleDecl implements XSParticle
{
    public static final short PARTICLE_EMPTY = 0;
    public static final short PARTICLE_ELEMENT = 1;
    public static final short PARTICLE_WILDCARD = 2;
    public static final short PARTICLE_MODELGROUP = 3;
    public static final short PARTICLE_ZERO_OR_MORE = 4;
    public static final short PARTICLE_ZERO_OR_ONE = 5;
    public static final short PARTICLE_ONE_OR_MORE = 6;
    public short fType;
    public XSTerm fValue;
    public int fMinOccurs;
    public int fMaxOccurs;
    private String fDescription;
    
    public XSParticleDecl() {
        this.fType = 0;
        this.fValue = null;
        this.fMinOccurs = 1;
        this.fMaxOccurs = 1;
        this.fDescription = null;
    }
    
    public XSParticleDecl makeClone() {
        final XSParticleDecl particle = new XSParticleDecl();
        particle.fType = this.fType;
        particle.fMinOccurs = this.fMinOccurs;
        particle.fMaxOccurs = this.fMaxOccurs;
        particle.fDescription = this.fDescription;
        particle.fValue = this.fValue;
        return particle;
    }
    
    public boolean emptiable() {
        return this.minEffectiveTotalRange() == 0;
    }
    
    public boolean isEmpty() {
        return this.fType == 0 || (this.fType != 1 && this.fType != 2 && ((XSModelGroupImpl)this.fValue).isEmpty());
    }
    
    public int minEffectiveTotalRange() {
        if (this.fType == 3) {
            return ((XSModelGroupImpl)this.fValue).minEffectiveTotalRange() * this.fMinOccurs;
        }
        return this.fMinOccurs;
    }
    
    public int maxEffectiveTotalRange() {
        if (this.fType != 3) {
            return this.fMaxOccurs;
        }
        final int max = ((XSModelGroupImpl)this.fValue).maxEffectiveTotalRange();
        if (max == -1) {
            return -1;
        }
        if (max != 0 && this.fMaxOccurs == -1) {
            return -1;
        }
        return max * this.fMaxOccurs;
    }
    
    public String toString() {
        if (this.fDescription == null) {
            final StringBuffer buffer = new StringBuffer();
            this.appendParticle(buffer);
            if ((this.fMinOccurs != 0 || this.fMaxOccurs != 0) && (this.fMinOccurs != 1 || this.fMaxOccurs != 1)) {
                buffer.append("{" + this.fMinOccurs);
                if (this.fMaxOccurs == -1) {
                    buffer.append("-UNBOUNDED");
                }
                else if (this.fMinOccurs != this.fMaxOccurs) {
                    buffer.append("-" + this.fMaxOccurs);
                }
                buffer.append("}");
            }
            this.fDescription = buffer.toString();
        }
        return this.fDescription;
    }
    
    void appendParticle(final StringBuffer buffer) {
        switch (this.fType) {
            case 0: {
                buffer.append("EMPTY");
                break;
            }
            case 1:
            case 2: {
                buffer.append('(');
                buffer.append(this.fValue.toString());
                buffer.append(')');
                break;
            }
            case 3: {
                buffer.append(this.fValue.toString());
                break;
            }
        }
    }
    
    public void reset() {
        this.fType = 0;
        this.fValue = null;
        this.fMinOccurs = 1;
        this.fMaxOccurs = 1;
        this.fDescription = null;
    }
    
    public short getType() {
        return 8;
    }
    
    public String getName() {
        return null;
    }
    
    public String getNamespace() {
        return null;
    }
    
    public int getMinOccurs() {
        return this.fMinOccurs;
    }
    
    public boolean getIsMaxOccursUnbounded() {
        return this.fMaxOccurs == -1;
    }
    
    public int getMaxOccurs() {
        return this.fMaxOccurs;
    }
    
    public XSTerm getTerm() {
        return this.fValue;
    }
}
