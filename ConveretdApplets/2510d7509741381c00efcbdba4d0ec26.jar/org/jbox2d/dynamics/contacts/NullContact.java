// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.contacts;

import org.jbox2d.collision.Manifold;
import java.util.List;
import org.jbox2d.dynamics.ContactListener;

public class NullContact extends Contact
{
    public void evaluate(final ContactListener cl) {
    }
    
    public Contact clone() {
        return new NullContact();
    }
    
    public List<Manifold> getManifolds() {
        System.out.println("NullContact.GetManifolds()");
        return null;
    }
}
