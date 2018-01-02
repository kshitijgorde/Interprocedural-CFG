// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.diff;

import org.xmodel.IModelObject;
import org.xmodel.diff.IXmlMatcher;
import com.stonewall.cornerstone.dsp.loader.Loader;
import com.stonewall.cornerstone.entity.DeviceConfig;
import com.stonewall.cornerstone.entity.Device;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.diff.AnnotatingXmlDiffer;

public class AnnotatingConfigDiffer extends AnnotatingXmlDiffer
{
    static final Log log;
    
    static {
        log = Log.getLog(AnnotatingConfigDiffer.class);
    }
    
    public DeviceConfig annotate(final Device device, final DeviceConfig lhs, final DeviceConfig rhs) throws Exception {
        AnnotatingConfigDiffer.log.debug("lhs:\n" + lhs);
        AnnotatingConfigDiffer.log.debug("rhs:\n" + rhs);
        final Loader loader = new Loader(device);
        final IXmlMatcher matcher = (IXmlMatcher)loader.newInstance("diff.DeviceConfigMatcher", loader);
        this.setMatcher(matcher);
        final IModelObject lhsroot = lhs.getRoot();
        this.diffAndApply(lhsroot, rhs.getRoot());
        final DeviceConfig annotated = new DeviceConfig();
        annotated.replaceContent(lhsroot);
        AnnotatingConfigDiffer.log.debug("annotated:\n" + annotated);
        return annotated;
    }
}
