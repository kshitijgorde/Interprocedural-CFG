// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.exception;

import java.awt.Component;
import javax.swing.SwingUtilities;
import com.itt.IASjTools.IASJ2KException;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;

public class J2KExceptionHandler
{
    private static Log log;
    private ViewCentral viewCentral;
    static /* synthetic */ Class class$com$itt$J2KViewer$exception$J2KExceptionHandler;
    
    public J2KExceptionHandler(final ViewCentral viewCentral) throws IASJ2KException {
        this.viewCentral = viewCentral;
    }
    
    public void warningHandler(final String s, final boolean b) {
        J2KExceptionHandler.log.error("Warning: " + s);
        SwingUtilities.invokeLater(new ErrorMessage(this.viewCentral, s));
    }
    
    public void errorHandler(final String s, final boolean b) {
        J2KExceptionHandler.log.error("Error: " + s);
        SwingUtilities.invokeLater(new ErrorMessage(this.viewCentral, s));
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        J2KExceptionHandler.log = new Log((J2KExceptionHandler.class$com$itt$J2KViewer$exception$J2KExceptionHandler == null) ? (J2KExceptionHandler.class$com$itt$J2KViewer$exception$J2KExceptionHandler = class$("com.itt.J2KViewer.exception.J2KExceptionHandler")) : J2KExceptionHandler.class$com$itt$J2KViewer$exception$J2KExceptionHandler);
    }
    
    public class ErrorMessage implements Runnable
    {
        private ViewCentral viewCentral;
        private String message;
        
        public ErrorMessage(final ViewCentral viewCentral, final String message) {
            this.viewCentral = viewCentral;
            this.message = message;
        }
        
        public void run() {
            this.viewCentral.reportError(this.viewCentral.getMainImagePanel(), "Asynchronous Warning", this.message);
        }
    }
}
