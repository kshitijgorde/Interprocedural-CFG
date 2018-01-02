// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.juniper.screenos.v5_1_0.parser.checksum;

import com.stonewall.cornerstone.utility.Checksum;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.dsp.command.DeviceOperation;
import com.stonewall.cornerstone.dsp.loader.Loader;
import com.stonewall.cornerstone.dsp.parser.DefaultParser;

public class Parser extends DefaultParser
{
    public Parser(final Loader loader) {
    }
    
    public Parser() {
    }
    
    @Override
    public IModelObject parse(final DeviceOperation op) throws Exception {
        final String s = op.getRawResponse();
        final Checksum checksum = new Checksum(s);
        return checksum.getRoot();
    }
}
