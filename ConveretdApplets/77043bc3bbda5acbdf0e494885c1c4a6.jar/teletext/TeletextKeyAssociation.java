// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TeletextKeyAssociation
{
    int keyCode;
    char charCode;
    int modifiers;
    Object targetObject;
    String methodName;
    Class[] methodParameterTypes;
    Object[] methodParameters;
    Method method;
    static /* synthetic */ Class class$teletext$TeletextPageCanvas;
    
    public TeletextKeyAssociation(final int keyCode, final char charCode, final int modifiers, final Object targetObject, final String methodName, final Class[] methodParameterTypes, final Object[] methodParameters) {
        this.keyCode = keyCode;
        this.charCode = charCode;
        this.modifiers = modifiers;
        this.targetObject = targetObject;
        this.methodName = methodName;
        this.methodParameterTypes = methodParameterTypes;
        this.methodParameters = methodParameters;
        final Class clazz = (TeletextKeyAssociation.class$teletext$TeletextPageCanvas == null) ? (TeletextKeyAssociation.class$teletext$TeletextPageCanvas = class$("teletext.TeletextPageCanvas")) : TeletextKeyAssociation.class$teletext$TeletextPageCanvas;
        try {
            this.method = clazz.getMethod(this.methodName, (Class[])this.methodParameterTypes);
        }
        catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
    
    public int getKeyCode() {
        return this.keyCode;
    }
    
    public char getCharCode() {
        return this.charCode;
    }
    
    public int getModifiers() {
        return this.modifiers;
    }
    
    public void invoke() {
        try {
            this.method.invoke(this.targetObject, this.methodParameters);
        }
        catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        catch (InvocationTargetException ex2) {
            ex2.printStackTrace();
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
