// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils.synthetic;

import org.apache.xml.utils.synthetic.reflection.Method;
import java.io.OutputStream;

public class TestDriver
{
    public static int sampleField;
    private boolean inTest;
    
    static {
        TestDriver.sampleField = 32;
    }
    
    public TestDriver() {
        this.inTest = false;
    }
    
    static /* synthetic */ void access$1(final TestDriver $0, final boolean $1) {
        $0.inTest = $1;
    }
    
    public static void dumpClass(final Class C) {
        System.out.println("toString(): " + C);
        System.out.println("\tisPrimitive(): " + C.isPrimitive());
        System.out.println("\tisInterface(): " + C.isInterface());
        System.out.println("\tisInstance(\"foo\"): " + C.isInstance("foo"));
        System.out.println("\tisArray(): " + C.isArray());
        System.out.println("\tgetRealClass(): " + C.getRealClass());
    }
    
    public static void main(final String[] args) {
        try {
            System.out.println("Proxying java.awt.Frame...");
            Class myC = Class.forName("java.awt.Frame");
            myC.toSource(System.out, 0);
            System.out.println("\nProxying org.apache.xml.utils.synthetic.TestDriver...");
            myC = Class.forName("com.ibm.org.apache.xml.utils.synthetic.TestDriver");
            myC.toSource(System.out, 0);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Couldn't proxy: ");
            e.printStackTrace();
        }
        try {
            System.out.println("\nBuild a new beast...");
            final Class declareClass = Class.declareClass("com.ibm.org.apache.xml.utils.synthetic.BuildMe");
            final Class inner = declareClass.declareInnerClass("island");
            inner.addExtends(Class.forName("java.lang.String"));
            final Method m = inner.declareMethod("getValue");
            m.setReturnType(Class.forName("java.lang.String"));
            m.getBody().append("return toString();");
            declareClass.toSource(System.out, 0);
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (SynthesisException ex2) {
            ex2.printStackTrace();
        }
        catch (IllegalStateException e2) {
            System.out.println("Unwritten function: " + e2);
            e2.printStackTrace();
        }
    }
    
    public void quickcheck() {
        final Inner a = new Inner();
        a.setTest(a.getTest() ^ true);
    }
    
    private class Inner
    {
        public boolean getTest() {
            return TestDriver.this.inTest;
        }
        
        public void setTest(final boolean test) {
            TestDriver.access$1(TestDriver.this, test);
        }
    }
}
