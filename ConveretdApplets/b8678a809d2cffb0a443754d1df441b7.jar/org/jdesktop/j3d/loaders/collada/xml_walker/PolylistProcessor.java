// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Shape3D;
import java.math.BigInteger;
import org.collada.colladaschema.InputLocalOffset;
import java.util.List;
import org.collada.colladaschema.Polylist;
import java.util.logging.Logger;

public class PolylistProcessor extends PrimitiveProcessor implements Primitive
{
    private Logger logger;
    
    public PolylistProcessor(final Polylist polylist, final Processor parent) {
        super(polylist, parent);
        (this.logger = Logger.getLogger("collada.processor")).info("Polylist " + polylist.getName());
        final List<InputLocalOffset> inputs = polylist.getInputs();
        System.out.println(polylist.getVcount());
        final List<BigInteger> pList = polylist.getP();
        this.initiate(inputs, pList, polylist.getMaterial());
    }
    
    @Override
    public Shape3D getShape3D() {
        return super.getShape3D(2);
    }
}
