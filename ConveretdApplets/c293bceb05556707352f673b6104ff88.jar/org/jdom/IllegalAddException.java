// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

public class IllegalAddException extends IllegalArgumentException
{
    private static final String CVS_ID = "@(#) $RCSfile: IllegalAddException.java,v $ $Revision: 1.17 $ $Date: 2002/02/23 11:30:13 $ $Name: jdom_1_0_b8 $";
    
    public IllegalAddException(final Element base, final Attribute added, final String reason) {
        super("The attribute \"" + added.getQualifiedName() + "\" could not be added to the element \"" + base.getQualifiedName() + "\": " + reason);
    }
    
    public IllegalAddException(final Element base, final Element added, final String reason) {
        super("The element \"" + added.getQualifiedName() + "\" could not be added as a child of \"" + base.getQualifiedName() + "\": " + reason);
    }
    
    public IllegalAddException(final Document base, final Element added, final String reason) {
        super("The element \"" + added.getQualifiedName() + "\" could not be added as the root of the document: " + reason);
    }
    
    public IllegalAddException(final Element base, final ProcessingInstruction added, final String reason) {
        super("The PI \"" + added.getTarget() + "\" could not be added as content to \"" + base.getQualifiedName() + "\": " + reason);
    }
    
    public IllegalAddException(final Document base, final ProcessingInstruction added, final String reason) {
        super("The PI \"" + added.getTarget() + "\" could not be added to the top level of the document: " + reason);
    }
    
    public IllegalAddException(final Element base, final Comment added, final String reason) {
        super("The comment \"" + added.getText() + "\" could not be added as content to \"" + base.getQualifiedName() + "\": " + reason);
    }
    
    public IllegalAddException(final Element base, final CDATA added, final String reason) {
        super("The CDATA \"" + added.getText() + "\" could not be added as content to \"" + base.getQualifiedName() + "\": " + reason);
    }
    
    public IllegalAddException(final Element base, final Text added, final String reason) {
        super("The Text \"" + added.getText() + "\" could not be added as content to \"" + base.getQualifiedName() + "\": " + reason);
    }
    
    public IllegalAddException(final Document base, final Comment added, final String reason) {
        super("The comment \"" + added.getText() + "\" could not be added to the top level of the document: " + reason);
    }
    
    public IllegalAddException(final Element base, final EntityRef added, final String reason) {
        super("The entity reference\"" + added.getName() + "\" could not be added as content to \"" + base.getQualifiedName() + "\": " + reason);
    }
    
    public IllegalAddException(final Element base, final Namespace added, final String reason) {
        super("The namespace xmlns" + ((added.getPrefix() == null || added.getPrefix().equals("")) ? "=" : (":" + added.getPrefix() + "=")) + "\"" + added.getURI() + "\" could not be added as content to \"" + base.getQualifiedName() + "\": " + reason);
    }
    
    public IllegalAddException(final Document base, final DocType added, final String reason) {
        super("The DOCTYPE " + added.toString() + " could not be added to the document: " + reason);
    }
    
    public IllegalAddException(final String reason) {
        super(reason);
    }
}
