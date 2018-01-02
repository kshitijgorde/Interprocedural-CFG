// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.vecmath.Tuple3f;
import org.collada.colladaschema.Accessor;
import org.collada.colladaschema.IDREFArray;
import org.collada.colladaschema.NameArray;
import org.collada.colladaschema.IntArray;
import org.collada.colladaschema.FloatArray;
import org.collada.colladaschema.BoolArray;
import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.Mesh;
import org.collada.colladaschema.Source;
import java.util.logging.Logger;

public class SourceProcessor extends Processor
{
    private Logger logger;
    private Source source;
    private SourceTypeEnum sourceType;
    private float[] processedFloatArray;
    
    public SourceProcessor(final Mesh mesh, final Processor parent) {
        super(mesh, parent);
        this.logger = Logger.getLogger("collada.processor");
        this.source = null;
        this.sourceType = null;
        this.processedFloatArray = null;
        this.logger.info("Processing Mesh ");
        final List<Source> sources = mesh.getSources();
        for (final Source s : sources) {
            ProcessorFactory.createProcessor(s, this);
        }
        ProcessorFactory.createProcessor(mesh.getVertices(), this);
    }
    
    public SourceProcessor(final Source source, final Processor parent) {
        super(source, parent);
        this.logger = Logger.getLogger("collada.processor");
        this.source = null;
        this.sourceType = null;
        this.processedFloatArray = null;
        this.logger.info("Processing Source id=" + source.getId() + " name=" + source.getName());
        this.source = source;
        ElementCache.cache().putSource(source.getId(), this);
        final BoolArray boolArray = source.getBoolArray();
        final FloatArray floatArray = source.getFloatArray();
        final IntArray intArray = source.getIntArray();
        final NameArray nameArray = source.getNameArray();
        final IDREFArray idRefArray = source.getIDREFArray();
        if (boolArray != null) {
            this.logger.info("BoolArray");
        }
        else if (floatArray != null) {
            final List<Double> data = floatArray.getValues();
            this.logger.info("FloatArray " + data.size());
            final float[] floatData = new float[data.size()];
            int i = 0;
            for (final Double d : data) {
                floatData[i++] = (float)(Object)d;
            }
            ElementCache.cache().putFloatArray(floatArray.getId(), floatData);
            this.sourceType = SourceTypeEnum.FLOAT_ARRAY;
        }
        else if (intArray != null) {
            this.logger.info("IntArray ");
        }
        else if (nameArray != null) {
            this.logger.info("NameArray ");
        }
        else if (idRefArray != null) {
            this.logger.info("IdRefArray ");
        }
        final Source.TechniqueCommon techCommon = source.getTechniqueCommon();
        final Accessor accessor = techCommon.getAccessor();
        switch (this.sourceType) {
            case FLOAT_ARRAY: {
                break;
            }
            default: {
                this.logger.warning("Unimplemented SourceType " + this.sourceType);
                break;
            }
        }
    }
    
    Tuple3f getTuple3f(final Tuple3f result, int index) {
        final Source.TechniqueCommon techCommon = this.source.getTechniqueCommon();
        final Accessor accessor = techCommon.getAccessor();
        final int size = accessor.getCount().intValue();
        final int stride = accessor.getStride().intValue();
        final int offset = accessor.getOffset().intValue();
        final float[] data = ElementCache.cache().getFloatArray(accessor.getSource());
        index += offset;
        result.x = data[index];
        result.y = data[index + 1];
        result.z = data[index + 2];
        return result;
    }
    
    float[] getFloatArray() {
        final Source.TechniqueCommon techCommon = this.source.getTechniqueCommon();
        final Accessor accessor = techCommon.getAccessor();
        return ElementCache.cache().getFloatArray(accessor.getSource());
    }
    
    private enum SourceTypeEnum
    {
        BOOL_ARRAY("BOOL_ARRAY", 0), 
        FLOAT_ARRAY("FLOAT_ARRAY", 1), 
        INT_ARRAY("INT_ARRAY", 2), 
        NAME_ARRAY("NAME_ARRAY", 3), 
        ID_REF_ARRAY("ID_REF_ARRAY", 4);
        
        private SourceTypeEnum(final String s, final int n) {
        }
    }
}
