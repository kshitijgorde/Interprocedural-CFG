// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.rmi.RMIException;

public class ValidationException extends RMIException
{
    private String reason;
    private static final long serialVersionUID = 0L;
    
    public ValidationException() {
    }
    
    public ValidationException(final Exception e) {
        super(e);
        this.setReason(e.getMessage());
    }
    
    public void invalidState(final String currentState, final String expectedState, final Entity entity) {
        this.createReason(entity, "Current state (" + currentState + ") is invalid. Expected state (" + expectedState + ")");
    }
    
    public void entityAlreadyExists(final Entity entity) {
        this.createReason(entity, "Already exists.");
    }
    
    public void entityNotFound(final Entity entity) {
        this.createReason(entity, "Does not exist.");
    }
    
    public void entityRequired() {
        this.setReason("Entity is required.");
    }
    
    public void setReason(final String r, final Entity entity) {
        this.createReason(entity, r);
    }
    
    public void limitExceeded(final int limit, final Entity entity) {
        this.createReason(entity, "Limit (" + limit + ") has been reached.");
    }
    
    public void setReason(final String reason) {
        this.reason = reason;
    }
    
    private void createReason(final Entity entity, final String text) {
        final StringBuffer buf = new StringBuffer();
        if (entity != null) {
            buf.append(entity.getEntityType().getDisplayName());
            buf.append(" (");
            buf.append(entity.getDisplayName());
            buf.append("): ");
        }
        buf.append(text);
        this.setReason(buf.toString());
    }
    
    @Override
    public String getMessage() {
        return this.reason;
    }
}
