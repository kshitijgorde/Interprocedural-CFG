// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

class WordItem
{
    public CharacterGroupItem[] items;
    public String originalstrippedword;
    public String originalword;
    public String decodedword;
    public CharacterInfo lastInfo;
    
    public WordItem(final CharacterGroupItem[] items, final CharacterInfo lastInfo) {
        this.lastInfo = lastInfo;
        this.items = items;
        this.decodedword = "";
        for (int i = 0; i < this.items.length; ++i) {
            this.decodedword += this.items[i].s;
        }
        this.originalword = this.decodedword;
        this.originalstrippedword = this.decodedword;
    }
}
