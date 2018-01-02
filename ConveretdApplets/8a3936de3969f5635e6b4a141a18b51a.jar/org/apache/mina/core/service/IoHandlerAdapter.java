// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.service;

import org.slf4j.LoggerFactory;
import com.masystem.beergame.debug.Log;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;

public class IoHandlerAdapter implements IoHandler
{
    private static final Logger LOGGER;
    
    @Override
    public void sessionClosed(final IoSession ioSession) throws Exception {
    }
    
    @Override
    public void exceptionCaught(final IoSession ioSession, Throwable t) throws Exception {
        final Logger logger = IoHandlerAdapter.LOGGER;
        final Logger logger2 = IoHandlerAdapter.LOGGER;
        final String string = "EXCEPTION, please implement " + this.getClass().getName() + ".exceptionCaught() for proper handling:";
        t = t;
        Log.warn(string, t);
    }
    
    @Override
    public void messageReceived(final IoSession ioSession, final Object o) throws Exception {
    }
    
    static {
        LOGGER = LoggerFactory.getLogger$4ecaad6a();
    }
}
