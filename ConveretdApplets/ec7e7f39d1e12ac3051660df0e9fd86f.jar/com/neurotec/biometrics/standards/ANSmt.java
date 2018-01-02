// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

public final class ANSmt
{
    private ANSmtSource source;
    private ANTattooClass tattooClass;
    private ANTattooSubclass tattooSubclass;
    private String description;
    
    public ANSmt(final ANSmtSource source, final ANTattooClass tattooClass, final ANTattooSubclass tattooSubclass, final String description) {
        this.source = source;
        this.tattooClass = tattooClass;
        this.tattooSubclass = tattooSubclass;
        this.description = description;
    }
    
    public ANSmtSource getSource() {
        return this.source;
    }
    
    public void setSource(final ANSmtSource source) {
        this.source = source;
    }
    
    public ANTattooClass getTattooClass() {
        return this.tattooClass;
    }
    
    public void setTattooClass(final ANTattooClass tattooClass) {
        this.tattooClass = tattooClass;
    }
    
    public ANTattooSubclass getTattooSubclass() {
        return this.tattooSubclass;
    }
    
    public void setTattooSubclass(final ANTattooSubclass tattooSubclass) {
        this.tattooSubclass = tattooSubclass;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
}
