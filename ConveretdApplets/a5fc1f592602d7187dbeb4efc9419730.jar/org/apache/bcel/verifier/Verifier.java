// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier;

import org.apache.bcel.classfile.JavaClass;
import java.util.Iterator;
import org.apache.bcel.Repository;
import java.util.ArrayList;
import org.apache.bcel.verifier.structurals.Pass3bVerifier;
import org.apache.bcel.verifier.statics.Pass3aVerifier;
import java.util.HashMap;
import org.apache.bcel.verifier.statics.Pass2Verifier;
import org.apache.bcel.verifier.statics.Pass1Verifier;

public class Verifier
{
    private final String classname;
    private Pass1Verifier p1v;
    private Pass2Verifier p2v;
    private HashMap p3avs;
    private HashMap p3bvs;
    
    public VerificationResult doPass1() {
        if (this.p1v == null) {
            this.p1v = new Pass1Verifier(this);
        }
        return this.p1v.verify();
    }
    
    public VerificationResult doPass2() {
        if (this.p2v == null) {
            this.p2v = new Pass2Verifier(this);
        }
        return this.p2v.verify();
    }
    
    public VerificationResult doPass3a(final int method_no) {
        final String key = Integer.toString(method_no);
        Pass3aVerifier p3av = this.p3avs.get(key);
        if (this.p3avs.get(key) == null) {
            p3av = new Pass3aVerifier(this, method_no);
            this.p3avs.put(key, p3av);
        }
        return p3av.verify();
    }
    
    public VerificationResult doPass3b(final int method_no) {
        final String key = Integer.toString(method_no);
        Pass3bVerifier p3bv = this.p3bvs.get(key);
        if (this.p3bvs.get(key) == null) {
            p3bv = new Pass3bVerifier(this, method_no);
            this.p3bvs.put(key, p3bv);
        }
        return p3bv.verify();
    }
    
    private Verifier() {
        this.p3avs = new HashMap();
        this.p3bvs = new HashMap();
        this.classname = "";
    }
    
    Verifier(final String fully_qualified_classname) {
        this.p3avs = new HashMap();
        this.p3bvs = new HashMap();
        this.classname = fully_qualified_classname;
        this.flush();
    }
    
    public final String getClassName() {
        return this.classname;
    }
    
    public void flush() {
        this.p1v = null;
        this.p2v = null;
        this.p3avs.clear();
        this.p3bvs.clear();
    }
    
    public String[] getMessages() {
        final ArrayList messages = new ArrayList();
        if (this.p1v != null) {
            final String[] p1m = this.p1v.getMessages();
            for (int i = 0; i < p1m.length; ++i) {
                messages.add("Pass 1: " + p1m[i]);
            }
        }
        if (this.p2v != null) {
            final String[] p2m = this.p2v.getMessages();
            for (int i = 0; i < p2m.length; ++i) {
                messages.add("Pass 2: " + p2m[i]);
            }
        }
        for (final Pass3aVerifier pv : this.p3avs.values()) {
            final String[] p3am = pv.getMessages();
            final int meth = pv.getMethodNo();
            for (int j = 0; j < p3am.length; ++j) {
                messages.add("Pass 3a, method " + meth + " ('" + Repository.lookupClass(this.classname).getMethods()[meth] + "'): " + p3am[j]);
            }
        }
        for (final Pass3bVerifier pv2 : this.p3bvs.values()) {
            final String[] p3bm = pv2.getMessages();
            final int meth2 = pv2.getMethodNo();
            for (int k = 0; k < p3bm.length; ++k) {
                messages.add("Pass 3b, method " + meth2 + " ('" + Repository.lookupClass(this.classname).getMethods()[meth2] + "'): " + p3bm[k]);
            }
        }
        final String[] ret = new String[messages.size()];
        for (int l = 0; l < messages.size(); ++l) {
            ret[l] = messages.get(l);
        }
        return ret;
    }
    
    public static void main(final String[] args) {
        System.out.println("JustIce by Enver Haase, (C) 2001. http://bcel.sourceforge.net\n");
        for (int k = 0; k < args.length; ++k) {
            if (args[k].endsWith(".class")) {
                final int dotclasspos = args[k].lastIndexOf(".class");
                if (dotclasspos != -1) {
                    args[k] = args[k].substring(0, dotclasspos);
                }
            }
            args[k] = args[k].replace('/', '.');
            System.out.println("Now verifiying: " + args[k] + "\n");
            final Verifier v = VerifierFactory.getVerifier(args[k]);
            VerificationResult vr = v.doPass1();
            System.out.println("Pass 1:\n" + vr);
            vr = v.doPass2();
            System.out.println("Pass 2:\n" + vr);
            if (vr == VerificationResult.VR_OK) {
                final JavaClass jc = Repository.lookupClass(args[k]);
                for (int i = 0; i < jc.getMethods().length; ++i) {
                    vr = v.doPass3a(i);
                    System.out.println("Pass 3a, method " + i + " ['" + jc.getMethods()[i] + "']:\n" + vr);
                    vr = v.doPass3b(i);
                    System.out.println("Pass 3b, method number " + i + " ['" + jc.getMethods()[i] + "']:\n" + vr);
                }
            }
            System.out.println("Warnings:");
            final String[] warnings = v.getMessages();
            if (warnings.length == 0) {
                System.out.println("<none>");
            }
            for (int j = 0; j < warnings.length; ++j) {
                System.out.println(warnings[j]);
            }
            System.out.println("\n");
            v.flush();
            Repository.clearCache();
            System.gc();
        }
    }
}
