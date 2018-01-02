// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider;

import java.util.Iterator;
import java.util.ArrayList;

public class InvocationSession
{
    private ArrayList<PostInvoke> list;
    
    public void finish() {
        if (this.list != null) {
            for (final PostInvoke p : this.list) {
                p.postInvoke();
            }
        }
    }
    
    public void addPostInvoke(final PostInvoke postInvoke) {
        if (this.list == null) {
            this.list = new ArrayList<PostInvoke>();
        }
        this.list.add(postInvoke);
    }
    
    public interface PostInvoke
    {
        void postInvoke();
    }
}
