// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

public class TeletextKeyCodeAssociation extends TeletextKeyAssociation
{
    private static /* synthetic */ Class class$Ljava$lang$Character;
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public TeletextKeyCodeAssociation(final int n, final char c, final int n2, final Object o, final char c2) {
        super(n, c, n2, o, "insertCharacter", new Class[] { (TeletextKeyCodeAssociation.class$Ljava$lang$Character != null) ? TeletextKeyCodeAssociation.class$Ljava$lang$Character : (TeletextKeyCodeAssociation.class$Ljava$lang$Character = class$("java.lang.Character")) }, new Object[] { new Character(c2) });
    }
}