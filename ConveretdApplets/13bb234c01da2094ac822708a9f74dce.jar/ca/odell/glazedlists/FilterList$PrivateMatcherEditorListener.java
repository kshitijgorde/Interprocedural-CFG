// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists;

import ca.odell.glazedlists.impl.adt.BarcodeIterator;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.matchers.Matchers;
import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.matchers.Matcher;
import ca.odell.glazedlists.impl.adt.Barcode;
import ca.odell.glazedlists.matchers.MatcherEditor$Event;
import ca.odell.glazedlists.matchers.MatcherEditor$Listener;

class FilterList$PrivateMatcherEditorListener implements MatcherEditor$Listener
{
    final /* synthetic */ FilterList a;
    
    private FilterList$PrivateMatcherEditorListener(final FilterList a) {
        this.a = a;
    }
    
    public void a(final MatcherEditor$Event matcherEditor$Event) {
        this.a.a(matcherEditor$Event.a(), matcherEditor$Event.b(), matcherEditor$Event.c());
    }
}
