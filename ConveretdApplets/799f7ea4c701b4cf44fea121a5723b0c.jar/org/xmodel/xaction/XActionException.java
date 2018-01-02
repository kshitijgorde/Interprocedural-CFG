// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.Xlate;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import org.xmodel.ModelObjectFactory;
import org.xmodel.IModelObject;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ModelAlgorithms;
import org.xmodel.log.Log;

public class XActionException extends RuntimeException
{
    private static Log B;
    private XActionDocument A;
    
    static {
        XActionException.B = Log.getLog("org.xmodel.xaction");
    }
    
    public XActionException() {
    }
    
    public XActionException(final XActionDocument location, final String s) {
        super(s);
        this.setLocation(location);
    }
    
    public XActionException(final XActionDocument location, final String s, final Throwable t) {
        super(s, t);
        this.setLocation(location);
    }
    
    public XActionException(final String s) {
        super(s);
    }
    
    public XActionException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public XActionException(final Throwable t) {
        super(t);
    }
    
    @Override
    public String getMessage() {
        if (this.A == null) {
            return super.getMessage();
        }
        return String.format("%s: %s", super.getMessage(), ModelAlgorithms.createIdentityPath(this.A.getRoot()).toString());
    }
    
    public void setLocation(final XActionDocument a) {
        this.A = a;
    }
    
    public IModelObject createExceptionFragment(final IModelObjectFactory modelObjectFactory) {
        return createExceptionFragment(this, modelObjectFactory);
    }
    
    public static IModelObject createExceptionFragment(final Throwable t, IModelObjectFactory modelObjectFactory) {
        if (modelObjectFactory == null) {
            modelObjectFactory = new ModelObjectFactory();
        }
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            t.printStackTrace(new PrintStream(byteArrayOutputStream));
            byteArrayOutputStream.flush();
            final IModelObject object = modelObjectFactory.createObject(null, "exception");
            String s = t.getMessage();
            if (s == null || s.length() == 0) {
                s = t.getClass().getCanonicalName();
            }
            Xlate.childSet(object, "message", s);
            Xlate.childSet(object, "stack", byteArrayOutputStream.toString());
            final IModelObject createChild = object.getCreateChild("cause");
            final Throwable cause = t.getCause();
            if (cause != null && cause != t) {
                createChild.addChild(createExceptionFragment(cause, modelObjectFactory));
            }
            byteArrayOutputStream.close();
            return object;
        }
        catch (Exception ex) {
            XActionException.B.exception(ex);
            return null;
        }
    }
}
