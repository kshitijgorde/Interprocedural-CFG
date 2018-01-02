// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.query;

import com.stonewall.cornerstone.tp.graph.Link;
import com.stonewall.cornerstone.tp.graph.Frame;
import com.stonewall.cornerstone.tp.graph.BasicWalker;
import com.stonewall.cornerstone.tp.graph.Constraint;

public class LinkConstraint implements Constraint
{
    private Mode mode;
    
    LinkConstraint() {
        this.mode = Mode.Disabled;
    }
    
    LinkConstraint(final Mode mode) {
        this.mode = Mode.Disabled;
        this.mode = mode;
    }
    
    @Override
    public boolean match(final BasicWalker w, final Frame frame) {
        boolean result = false;
        if (frame.direction() == Direction.Inbound) {
            return result;
        }
        final Link link = frame.link();
        if (link == null) {
            return result;
        }
        switch (this.mode) {
            case Enabled: {
                result = link.enabled();
                break;
            }
            case Disabled: {
                result = !link.enabled();
                break;
            }
            case None: {
                result = false;
                break;
            }
        }
        return result;
    }
    
    enum Mode
    {
        Enabled("Enabled", 0), 
        Disabled("Disabled", 1), 
        None("None", 2);
        
        private Mode(final String s, final int n) {
        }
    }
}
