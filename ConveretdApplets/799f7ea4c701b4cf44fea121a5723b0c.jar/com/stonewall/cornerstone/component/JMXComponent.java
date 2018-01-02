// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.component;

import javax.management.ObjectName;
import com.stonewall.cornerstone.db.Profiler;
import java.lang.management.ManagementFactory;
import org.xmodel.log.Log;
import javax.management.MBeanServer;

public class JMXComponent implements Component
{
    protected final MBeanServer mbs;
    static final Log log;
    
    static {
        log = Log.getLog(JMXComponent.class);
    }
    
    public JMXComponent() {
        this.mbs = ManagementFactory.getPlatformMBeanServer();
    }
    
    @Override
    public void init(final ComponentServer container) throws Exception {
        final Profiler p = Profiler.getInstance();
        this.mbs.registerMBean(p, new ObjectName("com.stonewall.cornerstone:type=Profiler"));
        this.mbs.registerMBean(container, new ObjectName("com.stonewall.cornerstone:type=Server"));
    }
    
    @Override
    public void shutdown() {
    }
    
    @Override
    public void trace() {
        JMXComponent.log.warn("Not-Implemented");
    }
}
