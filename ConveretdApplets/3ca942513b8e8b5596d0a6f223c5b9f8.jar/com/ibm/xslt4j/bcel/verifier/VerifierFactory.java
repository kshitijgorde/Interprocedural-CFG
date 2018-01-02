// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier;

import java.util.Iterator;
import java.util.Vector;
import java.util.HashMap;

public class VerifierFactory
{
    private static HashMap hashMap;
    private static Vector observers;
    
    static {
        VerifierFactory.hashMap = new HashMap();
        VerifierFactory.observers = new Vector();
    }
    
    public static Verifier getVerifier(String fully_qualified_classname) {
        fully_qualified_classname = fully_qualified_classname;
        Verifier v = VerifierFactory.hashMap.get(fully_qualified_classname);
        if (v == null) {
            v = new Verifier(fully_qualified_classname);
            VerifierFactory.hashMap.put(fully_qualified_classname, v);
            notify(fully_qualified_classname);
        }
        return v;
    }
    
    private static void notify(final String fully_qualified_classname) {
        for (final VerifierFactoryObserver vfo : VerifierFactory.observers) {
            vfo.update(fully_qualified_classname);
        }
    }
    
    public static Verifier[] getVerifiers() {
        final Verifier[] vs = new Verifier[VerifierFactory.hashMap.values().size()];
        return (Verifier[])VerifierFactory.hashMap.values().toArray(vs);
    }
    
    public static void attach(final VerifierFactoryObserver o) {
        VerifierFactory.observers.addElement(o);
    }
    
    public static void detach(final VerifierFactoryObserver o) {
        VerifierFactory.observers.removeElement(o);
    }
}
