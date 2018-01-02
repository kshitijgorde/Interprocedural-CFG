// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.Repository;

public class TransitiveHull implements VerifierFactoryObserver
{
    private int indent;
    
    private TransitiveHull() {
        this.indent = 0;
    }
    
    public void update(final String classname) {
        System.gc();
        for (int i = 0; i < this.indent; ++i) {
            System.out.print(" ");
        }
        System.out.println(classname);
        ++this.indent;
        final Verifier v = VerifierFactory.getVerifier(classname);
        VerificationResult vr = v.doPass1();
        if (vr != VerificationResult.VR_OK) {
            System.out.println("Pass 1:\n" + vr);
        }
        vr = v.doPass2();
        if (vr != VerificationResult.VR_OK) {
            System.out.println("Pass 2:\n" + vr);
        }
        if (vr == VerificationResult.VR_OK) {
            final JavaClass jc = Repository.lookupClass(v.getClassName());
            for (int j = 0; j < jc.getMethods().length; ++j) {
                vr = v.doPass3a(j);
                if (vr != VerificationResult.VR_OK) {
                    System.out.println(v.getClassName() + ", Pass 3a, method " + j + " ['" + jc.getMethods()[j] + "']:\n" + vr);
                }
                vr = v.doPass3b(j);
                if (vr != VerificationResult.VR_OK) {
                    System.out.println(v.getClassName() + ", Pass 3b, method " + j + " ['" + jc.getMethods()[j] + "']:\n" + vr);
                }
            }
        }
        --this.indent;
    }
    
    public static void main(final String[] args) {
        if (args.length != 1) {
            System.out.println("Need exactly one argument: The root class to verify.");
            System.exit(1);
        }
        final int dotclasspos = args[0].lastIndexOf(".class");
        if (dotclasspos != -1) {
            args[0] = args[0].substring(0, dotclasspos);
        }
        args[0] = args[0].replace('/', '.');
        final TransitiveHull th = new TransitiveHull();
        VerifierFactory.attach(th);
        VerifierFactory.getVerifier(args[0]);
        VerifierFactory.detach(th);
    }
}
