// 
// Decompiled by Procyon v0.5.30
// 

package nc.particle;

import sexy.gui.SexyColor;
import java.util.Enumeration;
import sexy.gui.SexyGraphics;
import java.util.Vector;

public class ParticleSystem
{
    Vector mEmitters;
    
    public void Render(final SexyGraphics sexyGraphics, final int n) {
        final Enumeration<ParticleEmitter> elements = this.mEmitters.elements();
        while (elements.hasMoreElements()) {
            final ParticleEmitter particleEmitter = elements.nextElement();
            if (particleEmitter.mEmitterData.mLayer == n) {
                particleEmitter.Render(sexyGraphics);
            }
        }
    }
    
    public boolean Update(final float n) {
        boolean b = false;
        for (int i = this.mEmitters.size() - 1; i >= 0; --i) {
            final ParticleEmitter particleEmitter = this.mEmitters.elementAt(i);
            particleEmitter.Update(n);
            if (particleEmitter.isDead()) {
                this.mEmitters.removeElementAt(i);
            }
            b = true;
        }
        return b;
    }
    
    public ParticleSystem(final int n) {
        this.mEmitters = new Vector(n);
    }
    
    public void RemoveParticleEmitter(final ParticleEmitter particleEmitter) {
        this.mEmitters.removeElement(particleEmitter);
    }
    
    public ParticleEmitter CreateParticleEmitter(final ParticleEmitterData particleEmitterData, final ParticleElementData particleElementData, final SexyColor sexyColor, final float n, final float n2, final float n3, final float n4) {
        final ParticleEmitter particleEmitter = new ParticleEmitter(particleEmitterData, particleElementData, sexyColor, n, n2, n3, n4);
        this.mEmitters.addElement(particleEmitter);
        return particleEmitter;
    }
}
