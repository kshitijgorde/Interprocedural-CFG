// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import org.xmodel.ModelAlgorithms;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.IPath;

public class L
{
    _A D;
    IPath E;
    int B;
    List<IModelObject> C;
    StackTraceElement[] A;
    
    public L(final _A d, final IPath e, final int b) {
        this.D = d;
        this.E = e;
        this.B = b;
        this.C = new ArrayList<IModelObject>();
        this.A = Thread.currentThread().getStackTrace();
    }
    
    public void A(final IModelObject modelObject) {
        if (!this.C.contains(modelObject)) {
            this.C.add(modelObject);
        }
    }
    
    public void A(final List<IModelObject> list) {
        final Iterator<IModelObject> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.A(iterator.next());
        }
    }
    
    public List<IModelObject> D() {
        return this.C;
    }
    
    public _A A() {
        return this.D;
    }
    
    public int C() {
        return this.B;
    }
    
    public boolean B() {
        return this.B == this.E.length();
    }
    
    public String A(final Pattern pattern) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        String string = "leaf";
        if (this.B < this.E.length()) {
            string = this.E.getPathElement(this.B).toString();
        }
        printStream.printf("%s(%d) '%s' of '%s' at:\n", this.D.toString(), this.B, string, this.E);
        for (int i = 0; i < this.C.size(); ++i) {
            printStream.printf("    %d. '%s'\n", i + 1, ModelAlgorithms.createIdentityPath(this.C.get(i)));
        }
        int n = 0;
        printStream.println("");
        for (int j = 0; j < this.A.length; ++j) {
            if (pattern.matcher(this.A[j].getClassName()).matches()) {
                printStream.printf("    %s\n", this.A[j]);
                n = 0;
            }
            else if (n == 0) {
                printStream.printf("    ...\n", new Object[0]);
                n = 1;
            }
        }
        return byteArrayOutputStream.toString();
    }
    
    @Override
    public String toString() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        String string = "leaf";
        if (this.B < this.E.length()) {
            string = this.E.getPathElement(this.B).toString();
        }
        printStream.printf("%s(%d) '%s' of '%s' on:\n", this.D.toString(), this.B, string, this.E);
        for (int i = 0; i < this.C.size(); ++i) {
            printStream.printf("    %d. '%s'\n", i + 1, ModelAlgorithms.createTypePath(this.C.get(i)));
        }
        printStream.printf("\nStack:\n", new Object[0]);
        for (int j = 0; j < this.A.length; ++j) {
            printStream.printf("    %s\n", this.A[j]);
        }
        return byteArrayOutputStream.toString();
    }
    
    public enum _A
    {
        C("install", 0), 
        A("remove", 1);
        
        static {
            B = new _A[] { _A.C, _A.A };
        }
        
        private _A(final String s, final int n) {
        }
    }
}
