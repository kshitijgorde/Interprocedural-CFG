// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.query;

import com.stonewall.cornerstone.tp.graph.Endpoint;
import com.stonewall.cornerstone.tp.graph.Network;
import com.stonewall.cornerstone.tp.graph.GraphObject;
import com.stonewall.cornerstone.tp.graph.Frame;
import com.stonewall.cornerstone.tp.graph.BasicWalker;
import com.stonewall.cornerstone.tp.graph.Constraint;

public class NetworkConstraint implements Constraint
{
    private final Type type;
    
    public static NetworkConstraint publicConstraint() {
        return new NetworkConstraint(Type.Public);
    }
    
    public static NetworkConstraint privateConstraint() {
        return new NetworkConstraint(Type.Private);
    }
    
    public NetworkConstraint(final Type t) {
        this.type = t;
    }
    
    @Override
    public boolean match(final BasicWalker w, final Frame frame) {
        boolean result = false;
        final Endpoint endpoint = frame.endpoint();
        if (endpoint.type(GraphObject.T.Network)) {
            final Network net = (Network)endpoint;
            switch (this.type) {
                case Public: {
                    result = net.isPublic();
                    break;
                }
                case Private: {
                    result = net.isPrivate();
                    break;
                }
            }
        }
        return result;
    }
    
    public enum Type
    {
        Public("Public", 0), 
        Private("Private", 1);
        
        private Type(final String s, final int n) {
        }
    }
}
