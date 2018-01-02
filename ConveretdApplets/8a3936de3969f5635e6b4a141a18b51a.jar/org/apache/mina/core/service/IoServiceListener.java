// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.service;

import java.util.EventListener;

public class IoServiceListener implements EventListener
{
    public void serviceActivated(final IoService ioService) throws Exception {
        final AbstractIoService abstractIoService;
        final IoServiceStatistics statistics;
        (statistics = (abstractIoService = (AbstractIoService)ioService).getStatistics()).setLastReadTime(abstractIoService.getActivationTime());
        statistics.setLastWriteTime(abstractIoService.getActivationTime());
        abstractIoService.getActivationTime();
    }
    
    public void serviceDeactivated$650f010b() throws Exception {
    }
    
    public void sessionCreated$5e760533() throws Exception {
    }
    
    public void sessionDestroyed$5e760533() throws Exception {
    }
    
    IoServiceListener(final AbstractIoService abstractIoService) {
    }
}
