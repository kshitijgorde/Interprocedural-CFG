// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.parser;

import java.io.File;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.dsp.command.DeviceOperation;

public interface IParser
{
    IModelObject parse(final DeviceOperation p0) throws Exception;
    
    IModelObject parse(final File p0) throws Exception;
}
