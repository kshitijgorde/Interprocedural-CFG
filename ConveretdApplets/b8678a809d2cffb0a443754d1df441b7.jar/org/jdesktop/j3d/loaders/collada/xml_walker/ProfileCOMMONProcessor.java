// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Node;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Element;
import org.collada.colladaschema.Technique;
import org.collada.colladaschema.Extra;
import org.collada.colladaschema.CommonNewparamType;
import org.collada.colladaschema.ProfileCOMMON;
import java.util.ArrayList;

public class ProfileCOMMONProcessor extends Processor
{
    private ArrayList<Processor> children;
    private boolean doubleSided;
    
    public ProfileCOMMONProcessor(final ProfileCOMMON profileCommon, final Processor parent) {
        super(profileCommon, parent);
        this.children = new ArrayList<Processor>();
        this.doubleSided = false;
        final String id = profileCommon.getId();
        if (id != null) {
            ElementCache.cache().put(id, this);
        }
        final List<Object> fcnts = profileCommon.getImagesAndNewparams();
        for (final Object o : fcnts) {
            final CommonNewparamType cnt = (CommonNewparamType)o;
            ElementCache.cache().putNewParam(cnt.getSid(), cnt);
        }
        for (final Extra ex : profileCommon.getExtras()) {
            for (final Technique teq : ex.getTechniques()) {
                if (teq.getProfile().equals("GOOGLEEARTH")) {
                    for (final Element e : teq.getAnies()) {
                        if (e.getTagName().equals("double_sided") && e.getTextContent().trim().equals("1")) {
                            this.doubleSided = true;
                        }
                    }
                }
                if (teq.getProfile().equals("MAX3D")) {
                    for (final Element e : teq.getAnies()) {
                        if (e.getTagName().equals("double_sided") && e.getTextContent().trim().equals("1")) {
                            this.doubleSided = true;
                        }
                    }
                }
            }
        }
        final ProfileCOMMON.Technique tech = profileCommon.getTechnique();
        if (tech.getPhong() != null) {
            final Processor p = ProcessorFactory.createProcessor(tech.getPhong(), this);
            if (p != null) {
                this.children.add(p);
            }
        }
        if (tech.getBlinn() != null) {
            final Processor p = ProcessorFactory.createProcessor(tech.getBlinn(), this);
            if (p != null) {
                this.children.add(p);
            }
        }
    }
    
    @Override
    public void create(final Node parent) {
        if (this.doubleSided && parent instanceof Shape3D) {
            final Shape3D s3d = (Shape3D)parent;
            PolygonAttributes pa = s3d.getAppearance().getPolygonAttributes();
            if (pa == null) {
                pa = new PolygonAttributes();
                s3d.getAppearance().setPolygonAttributes(pa);
            }
            pa.setPolygonMode(2);
            pa.setCullFace(0);
            pa.setBackFaceNormalFlip(true);
            pa.setPolygonOffset(1.0f);
            pa.setPolygonOffsetFactor(1.0f);
        }
        for (final Processor p : this.children) {
            p.create(parent);
        }
    }
}
