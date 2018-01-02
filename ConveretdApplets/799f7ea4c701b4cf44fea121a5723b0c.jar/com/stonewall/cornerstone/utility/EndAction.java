// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import org.jdom.Element;

class EndAction extends Action
{
    EndAction(final Element root) {
        super(root);
    }
    
    @Override
    void perform(final Emulation em, final String input) {
        em.simulator.close();
    }
}
