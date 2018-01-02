// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import org.collada.colladaschema.Asset;
import java.util.logging.Logger;

public class AssetProcessor extends Processor
{
    private Logger logger;
    
    public AssetProcessor(final Asset node, final Processor parent) {
        super(node, parent);
        (this.logger = Logger.getLogger("collada.processor")).info("Asset");
        ElementCache.cache().setAsset(node);
    }
}
