// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.io.PrintStream;
import org.xml.sax.SAXException;

public class JDOMException extends Exception
{
    private static final String CVS_ID = "@(#) $RCSfile: JDOMException.java,v $ $Revision: 1.26 $ $Date: 2008/12/10 00:59:51 $ $Name: jdom_1_1_1 $";
    private Throwable cause;
    
    public JDOMException() {
        super("Error occurred in JDOM application.");
    }
    
    public JDOMException(final String message) {
        super(message);
    }
    
    public JDOMException(final String message, final Throwable cause) {
        super(message);
        this.cause = cause;
    }
    
    public Throwable initCause(final Throwable cause) {
        this.cause = cause;
        return this;
    }
    
    public String getMessage() {
        String msg = super.getMessage();
        Throwable child;
        for (Throwable parent = this; (child = getNestedException(parent)) != null; parent = child) {
            String msg2 = child.getMessage();
            if (child instanceof SAXException) {
                final Throwable grandchild = ((SAXException)child).getException();
                if (grandchild != null && msg2 != null && msg2.equals(grandchild.getMessage())) {
                    msg2 = null;
                }
            }
            if (msg2 != null) {
                if (msg != null) {
                    msg = msg + ": " + msg2;
                }
                else {
                    msg = msg2;
                }
            }
            if (child instanceof JDOMException) {
                break;
            }
        }
        return msg;
    }
    
    public void printStackTrace() {
        super.printStackTrace();
        Throwable child;
        for (Throwable parent = this; (child = getNestedException(parent)) != null; parent = child) {
            System.err.print("Caused by: ");
            child.printStackTrace();
            if (child instanceof JDOMException) {
                break;
            }
        }
    }
    
    public void printStackTrace(final PrintStream s) {
        super.printStackTrace(s);
        Throwable child;
        for (Throwable parent = this; (child = getNestedException(parent)) != null; parent = child) {
            s.print("Caused by: ");
            child.printStackTrace(s);
            if (child instanceof JDOMException) {
                break;
            }
        }
    }
    
    public void printStackTrace(final PrintWriter w) {
        super.printStackTrace(w);
        Throwable child;
        for (Throwable parent = this; (child = getNestedException(parent)) != null; parent = child) {
            w.print("Caused by: ");
            child.printStackTrace(w);
            if (child instanceof JDOMException) {
                break;
            }
        }
    }
    
    public Throwable getCause() {
        return this.cause;
    }
    
    private static Throwable getNestedException(final Throwable parent) {
        if (parent instanceof JDOMException) {
            return ((JDOMException)parent).getCause();
        }
        if (parent instanceof SAXException) {
            return ((SAXException)parent).getException();
        }
        if (parent instanceof SQLException) {
            return ((SQLException)parent).getNextException();
        }
        if (parent instanceof InvocationTargetException) {
            return ((InvocationTargetException)parent).getTargetException();
        }
        if (parent instanceof ExceptionInInitializerError) {
            return ((ExceptionInInitializerError)parent).getException();
        }
        Throwable nestedException = getNestedExceptionFromField(parent, "java.rmi.RemoteException", "detail");
        if (nestedException != null) {
            return nestedException;
        }
        nestedException = getNestedException(parent, "javax.naming.NamingException", "getRootCause");
        if (nestedException != null) {
            return nestedException;
        }
        nestedException = getNestedException(parent, "javax.servlet.ServletException", "getRootCause");
        if (nestedException != null) {
            return nestedException;
        }
        return null;
    }
    
    private static Throwable getNestedException(final Throwable parent, final String className, final String methodName) {
        try {
            final Class testClass = Class.forName(className);
            final Class objectClass = parent.getClass();
            if (testClass.isAssignableFrom(objectClass)) {
                final Class[] argClasses = new Class[0];
                final Method method = testClass.getMethod(methodName, (Class[])argClasses);
                final Object[] args = new Object[0];
                return (Throwable)method.invoke(parent, args);
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private static Throwable getNestedExceptionFromField(final Throwable parent, final String className, final String fieldName) {
        try {
            final Class testClass = Class.forName(className);
            final Class objectClass = parent.getClass();
            if (testClass.isAssignableFrom(objectClass)) {
                final Class[] argClasses = new Class[0];
                final Field field = testClass.getField(fieldName);
                return (Throwable)field.get(parent);
            }
        }
        catch (Exception ex) {}
        return null;
    }
}
