// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.query;

import com.stonewall.cornerstone.tp.graph.Frame;
import com.stonewall.cornerstone.tp.graph.BasicWalker;
import java.util.Collection;
import java.util.HashSet;
import com.stonewall.cornerstone.tp.graph.Endpoint;
import java.util.Set;
import com.stonewall.cornerstone.tp.graph.Constraint;

public class EndpointConstraint implements Constraint
{
    private final Set<Endpoint> endpoints;
    private Mode mode;
    
    public EndpointConstraint(final Endpoint e) {
        this.endpoints = new HashSet<Endpoint>();
        this.mode = Mode.Capture;
        this.endpoints.add(e);
    }
    
    public EndpointConstraint(final Collection<Endpoint> e, final Mode mode) {
        this.endpoints = new HashSet<Endpoint>();
        this.mode = Mode.Capture;
        this.endpoints.addAll(e);
        this.mode = mode;
    }
    
    public EndpointConstraint(final Endpoint e, final Mode mode) {
        this(e);
        this.mode = mode;
    }
    
    @Override
    public boolean match(final BasicWalker w, final Frame frame) {
        boolean result = false;
        switch (this.mode) {
            case Capture: {
                result = this.capture(w, frame);
                break;
            }
            case Restrict: {
                result = this.restrict(w, frame);
                break;
            }
            case Subset: {
                result = this.subset(w, frame);
                break;
            }
        }
        return result;
    }
    
    private boolean capture(final BasicWalker w, final Frame frame) {
        final boolean matched = this.contains(frame) && frame.depth() > 0;
        if (matched) {
            w.markPath().add(frame);
        }
        return matched;
    }
    
    private boolean restrict(final BasicWalker w, final Frame frame) {
        return this.contains(frame);
    }
    
    private boolean subset(final BasicWalker w, final Frame frame) {
        return !this.contains(frame);
    }
    
    private boolean contains(final Frame frame) {
        return this.endpoints.contains(frame.endpoint());
    }
    
    public enum Mode
    {
        Capture("Capture", 0), 
        Restrict("Restrict", 1), 
        Subset("Subset", 2);
        
        private Mode(final String s, final int n) {
        }
    }
}
