// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Group;
import javax.media.j3d.Node;
import org.collada.colladaschema.Light;

public class LightProcessor extends Processor
{
    private Light l;
    
    public LightProcessor(final Light light, final Processor parent) {
        super(light, parent);
        this.l = light;
    }
    
    @Override
    public void create(final Node parent) {
        if (parent instanceof Group) {
            final Light.TechniqueCommon tc = this.l.getTechniqueCommon();
            final Light.TechniqueCommon.Ambient al = tc.getAmbient();
            final Light.TechniqueCommon.Directional dl = tc.getDirectional();
            final Light.TechniqueCommon.Point pl = tc.getPoint();
            final Light.TechniqueCommon.Spot sl = tc.getSpot();
            final javax.media.j3d.Light light = null;
            if (al != null || dl != null || pl == null) {}
            if (light != null) {
                ((Group)parent).addChild((Node)light);
            }
        }
    }
}
