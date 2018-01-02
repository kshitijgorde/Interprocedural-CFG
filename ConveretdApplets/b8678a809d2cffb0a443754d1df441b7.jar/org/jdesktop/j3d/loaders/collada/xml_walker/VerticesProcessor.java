// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.vecmath.Tuple3f;
import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.InputLocal;
import org.collada.colladaschema.Vertices;
import java.util.logging.Logger;

public class VerticesProcessor extends Processor
{
    private Logger logger;
    private SourceProcessor positionSource;
    
    public VerticesProcessor(final Vertices vert, final Processor parent) {
        super(vert, parent);
        this.logger = Logger.getLogger("collada.processor");
        this.positionSource = null;
        this.logger.info("Processing Vertices");
        ElementCache.cache().putVertices(vert.getId(), this);
        final List<InputLocal> inputs = vert.getInputs();
        for (final InputLocal input : inputs) {
            this.logger.info("Semantic " + input.getSemantic() + "  source " + input.getSource());
            if (input.getSemantic().equals("POSITION")) {
                this.positionSource = ElementCache.cache().getSource(input.getSource());
            }
            else {
                this.logger.warning("Ignoring vertices semantic " + input.getSemantic());
            }
        }
    }
    
    Tuple3f getTuple3f(final Tuple3f result, final int index) {
        return this.positionSource.getTuple3f(result, index);
    }
    
    float[] getFloatArray() {
        return this.positionSource.getFloatArray();
    }
}
