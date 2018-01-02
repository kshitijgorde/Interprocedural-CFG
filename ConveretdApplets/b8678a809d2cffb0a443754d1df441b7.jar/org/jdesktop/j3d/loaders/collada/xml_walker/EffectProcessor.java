// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Node;
import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.ProfileCOMMON;
import javax.xml.bind.JAXBElement;
import org.collada.colladaschema.Effect;
import java.util.ArrayList;

public class EffectProcessor extends Processor
{
    private ArrayList<Processor> children;
    
    public EffectProcessor(final Effect effect, final Processor parent) {
        super(effect, parent);
        this.children = new ArrayList<Processor>();
        final String id = effect.getId();
        ElementCache.cache().put(id, this);
        final List<JAXBElement<?>> list = effect.getFxProfileAbstracts();
        for (final JAXBElement obj : list) {
            if (obj.getValue() instanceof ProfileCOMMON) {
                final ProfileCOMMON o = obj.getValue();
                this.children.add(ProcessorFactory.createProcessor(o, this));
            }
        }
    }
    
    @Override
    public void create(final Node parent) {
        for (final Processor p : this.children) {
            p.create(parent);
        }
    }
}
