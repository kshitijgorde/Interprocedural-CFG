// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

public class CharacterObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$String;
    
    public CharacterObjectDescription() {
        super((CharacterObjectDescription.class$java$lang$Character == null) ? (CharacterObjectDescription.class$java$lang$Character = class$("java.lang.Character")) : CharacterObjectDescription.class$java$lang$Character);
        this.setParameterDefinition("value", (CharacterObjectDescription.class$java$lang$String == null) ? (CharacterObjectDescription.class$java$lang$String = class$("java.lang.String")) : CharacterObjectDescription.class$java$lang$String);
    }
    
    public Object createObject() {
        final String s = (String)this.getParameter("value");
        if (s == null) {
            return null;
        }
        if (s.length() > 0) {
            return new Character(s.charAt(0));
        }
        return null;
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof Character)) {
            throw new ObjectFactoryException("The given object is no java.lang.Character.");
        }
        this.setParameter("value", String.valueOf(o));
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractObjectDescription)) {
            return false;
        }
        final AbstractObjectDescription abstractObjectDescription = (AbstractObjectDescription)o;
        return Character.TYPE.equals(abstractObjectDescription.getObjectClass()) || ((CharacterObjectDescription.class$java$lang$Character == null) ? (CharacterObjectDescription.class$java$lang$Character = class$("java.lang.Character")) : CharacterObjectDescription.class$java$lang$Character).equals(abstractObjectDescription.getObjectClass());
    }
    
    public int hashCode() {
        return this.getObjectClass().hashCode();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
