// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.util.Stack;

final class CHtmlTagStack extends Stack
{
    int findIndexOfLastID(final int n) {
        for (int i = this.size() - 1; i >= 0; --i) {
            if (((CHtmlTagStackElement)this.elementAt(i)).isTagID(n)) {
                return i;
            }
        }
        return -1;
    }
}
