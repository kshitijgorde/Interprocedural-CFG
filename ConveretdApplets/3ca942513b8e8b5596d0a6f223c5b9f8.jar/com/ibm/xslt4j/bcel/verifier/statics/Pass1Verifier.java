// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.statics;

import com.ibm.xslt4j.bcel.verifier.exc.Utility;
import com.ibm.xslt4j.bcel.verifier.exc.LoadingException;
import com.ibm.xslt4j.bcel.verifier.VerificationResult;
import com.ibm.xslt4j.bcel.Repository;
import com.ibm.xslt4j.bcel.verifier.Verifier;
import com.ibm.xslt4j.bcel.classfile.JavaClass;
import com.ibm.xslt4j.bcel.verifier.PassVerifier;

public final class Pass1Verifier extends PassVerifier
{
    private JavaClass jc;
    private Verifier myOwner;
    
    private JavaClass getJavaClass() {
        if (this.jc == null) {
            this.jc = Repository.lookupClass(this.myOwner.getClassName());
        }
        return this.jc;
    }
    
    public Pass1Verifier(final Verifier owner) {
        this.myOwner = owner;
    }
    
    public VerificationResult do_verify() {
        JavaClass jc;
        try {
            jc = this.getJavaClass();
            if (jc != null && !this.myOwner.getClassName().equals(jc.getClassName())) {
                throw new LoadingException("Wrong name: the internal name of the .class file '" + jc.getClassName() + "' does not match the file's name '" + this.myOwner.getClassName() + "'.");
            }
        }
        catch (LoadingException e) {
            return new VerificationResult(2, e.getMessage());
        }
        catch (ClassFormatError e2) {
            return new VerificationResult(2, e2.getMessage());
        }
        catch (RuntimeException e3) {
            return new VerificationResult(2, "Parsing via BCEL did not succeed. " + e3.getClass().getName() + " occured:\n" + Utility.getStackTrace(e3));
        }
        if (jc != null) {
            return VerificationResult.VR_OK;
        }
        return new VerificationResult(2, "Repository.lookup() failed. FILE NOT FOUND?");
    }
    
    public String[] getMessages() {
        return super.getMessages();
    }
}
