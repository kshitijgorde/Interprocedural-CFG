// 
// Decompiled by Procyon v0.5.30
// 

package irc;

class Nick
{
    public String Name;
    public String Whois;
    public ModeHandler Mode;
    
    public Nick(final String name, final String s, final char[][] array, final char[] array2) {
        this.Name = name;
        this.Mode = new ModeHandler(s, array, array2);
        this.Whois = "";
    }
}
