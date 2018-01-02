// 
// Decompiled by Procyon v0.5.30
// 

package org.w3c.dom.html;

public interface HTMLOptionElement extends HTMLElement
{
    HTMLFormElement getForm();
    
    boolean getDefaultSelected();
    
    void setDefaultSelected(final boolean p0);
    
    String getText();
    
    int getIndex();
    
    boolean getDisabled();
    
    void setDisabled(final boolean p0);
    
    String getLabel();
    
    void setLabel(final String p0);
    
    boolean getSelected();
    
    void setSelected(final boolean p0);
    
    String getValue();
    
    void setValue(final String p0);
}
