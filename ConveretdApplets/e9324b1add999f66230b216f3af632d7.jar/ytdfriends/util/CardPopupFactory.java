// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.util;

import javax.swing.Popup;
import edu.berkeley.guir.prefuse.NodeItem;
import ytdfriends.FriendsPanel;
import javax.swing.PopupFactory;

public class CardPopupFactory extends PopupFactory
{
    private FriendsPanel vizster;
    
    public CardPopupFactory(final FriendsPanel vizster) {
        this.vizster = vizster;
    }
    
    public Popup getPopup(final NodeItem node, final double d, final double e) {
        return null;
    }
}
