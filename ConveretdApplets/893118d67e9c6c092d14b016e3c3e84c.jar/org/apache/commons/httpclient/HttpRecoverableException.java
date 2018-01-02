// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import org.apache.commons.httpclient.util.ExceptionUtil;
import java.io.IOException;

public class HttpRecoverableException extends IOException
{
    public HttpRecoverableException() {
    }
    
    public HttpRecoverableException(final String message) {
        super(message);
    }
    
    public HttpRecoverableException(final String message, final Throwable cause) {
        super(message);
        ExceptionUtil.initCause(this, cause);
    }
}
