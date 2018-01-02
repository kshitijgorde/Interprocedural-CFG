// 
// Decompiled by Procyon v0.5.30
// 

package en.javafx;

import java.util.Vector;
import java.applet.Applet;

public class ParameterHandler
{
    Applet applet;
    Vector parameters;
    
    public ParameterHandler(final Applet applet) {
        this.parameters = new Vector();
        this.applet = applet;
    }
    
    public final void setParameter(final String s, final String s2) throws Exception {
        for (int i = 0; i < this.parameters.size(); ++i) {
            final Parameter parameter = this.parameters.elementAt(i);
            if (parameter.name.equals(s)) {
                System.out.println(String.valueOf(String.valueOf(new StringBuffer("JavaScript: ").append(s).append("=").append(s2))));
                parameter.set(s2);
                break;
            }
        }
    }
    
    public final Parameter getParameterObject(final String s) {
        for (int i = 0; i < this.parameters.size(); ++i) {
            final Parameter parameter = this.parameters.elementAt(i);
            if (parameter.name.equals(s)) {
                return parameter;
            }
        }
        return null;
    }
    
    public final void setParameters(final Vector vector, final Vector vector2) throws Exception {
        for (int i = 0; i < vector.size(); ++i) {
            this.setParameter(vector.elementAt(i), vector2.elementAt(i));
        }
    }
    
    public final void getParameters(final Vector vector, final Vector vector2) {
        vector.removeAllElements();
        vector2.removeAllElements();
        for (int i = 0; i < this.parameters.size(); ++i) {
            final Parameter parameter = this.parameters.elementAt(i);
            vector.addElement(parameter.name);
            vector2.addElement(parameter.target.toString());
        }
    }
    
    public final void register(final String s, final boolean b, final df df) {
        this.parameters.addElement(new Parameter(s, df, new df(b)));
    }
    
    public final void register(final String s, final float n, final float n2, final float n3, final dc dc) {
        this.parameters.addElement(new Parameter(s, dc, new dc(n), new dc(n2), new dc(n3)));
    }
    
    public final void register(final String s, final int n, final int n2, final int n3, final db db) {
        this.parameters.addElement(new Parameter(s, db, new db(n), new db(n2), new db(n3)));
    }
    
    public final void register(final String s, final String s2, final de de) {
        this.parameters.addElement(new Parameter(s, de, new de(s2)));
    }
    
    public final void readParameters() throws Exception {
        for (int i = 0; i < this.parameters.size(); ++i) {
            final Parameter parameter = this.parameters.elementAt(i);
            parameter.set(this.applet.getParameter(parameter.name));
        }
    }
    
    public final String toString() {
        String concat = "";
        for (int i = 0; i < this.parameters.size(); ++i) {
            final Parameter parameter = this.parameters.elementAt(i);
            concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(parameter.name))).append(" = ").append(parameter.get()).append(" (").append(parameter.target.getClass().getName()).append(")\n"))))));
        }
        return concat;
    }
}
