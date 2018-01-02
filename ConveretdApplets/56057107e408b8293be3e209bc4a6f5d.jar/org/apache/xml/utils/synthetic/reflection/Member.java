// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils.synthetic.reflection;

import org.apache.xml.utils.synthetic.SynthesisException;
import org.apache.xml.utils.synthetic.Class;

public interface Member
{
    Class getDeclaringClass();
    
    int getModifiers();
    
    void setDeclaringClass(final Class p0) throws SynthesisException;
    
    void setModifiers(final int p0) throws SynthesisException;
}
