// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Shape3D;
import java.math.BigInteger;
import org.collada.colladaschema.InputLocalOffset;
import java.util.List;
import org.collada.colladaschema.Triangles;
import java.util.logging.Logger;

public class TrianglesProcessor extends PrimitiveProcessor implements Primitive
{
    private Logger logger;
    
    public TrianglesProcessor(final Triangles triangles, final Processor parent) {
        super(triangles, parent);
        (this.logger = Logger.getLogger("collada.processor")).info("Triangles " + triangles.getName());
        final List<InputLocalOffset> inputs = triangles.getInputs();
        final List<BigInteger> pList = triangles.getP();
        this.initiate(inputs, pList, triangles.getMaterial());
    }
    
    @Override
    public Shape3D getShape3D() {
        this.logger.info("TrianglesTypeProcessor#getShape3D");
        return super.getShape3D(1);
    }
}
