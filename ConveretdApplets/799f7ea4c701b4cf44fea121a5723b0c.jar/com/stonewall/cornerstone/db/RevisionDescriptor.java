// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

import java.util.Date;

public interface RevisionDescriptor
{
    String getVersionNumber();
    
    String getUserId();
    
    Date getDate();
    
    String getComment();
    
    String getLabel();
    
    Type getRevisionType();
    
    public enum Type
    {
        LABEL("LABEL", 0), 
        CHECKIN("CHECKIN", 1);
        
        private Type(final String s, final int n) {
        }
    }
}
