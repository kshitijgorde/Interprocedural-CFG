// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.parser;

import java.io.InputStream;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.io.FileInputStream;
import java.io.File;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.dsp.command.DeviceOperation;
import com.stonewall.cornerstone.dsp.loader.Loader;

public class FileParser extends DefaultParser
{
    public FileParser(final Loader loader) {
    }
    
    @Override
    public IModelObject parse(final DeviceOperation op) throws Exception {
        final File f1 = new File("c:/tmp/cornerstone/cachingPolicy/deviceConfigPix501Empty.xml");
        final FileInputStream fis = new FileInputStream(f1);
        final ModelBuilder builder = new ModelBuilder(ModelBuilder.Validation.none);
        return builder.buildModel(fis);
    }
}
