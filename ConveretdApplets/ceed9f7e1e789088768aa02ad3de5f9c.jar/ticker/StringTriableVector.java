// 
// Decompiled by Procyon v0.5.30
// 

package ticker;

import java.util.Vector;

public class StringTriableVector extends Vector
{
    public void ajoute(final Titre titre) {
        final int size = this.size();
        if (size == 0) {
            this.addElement(titre);
        }
        else if (size == 1) {
            if (this.compare(this.elementAt(0), titre) > 0) {
                this.insertElementAt(titre, 0);
            }
            else {
                this.addElement(titre);
            }
        }
        else if (this.compare(this.elementAt(0), titre) > 0) {
            this.insertElementAt(titre, 0);
        }
        else if (this.compare((Titre)this.elementAt(size - 1), titre) < 0) {
            this.addElement(titre);
        }
        else {
            int n = 0;
            int n2 = size - 1;
            while (n2 - n > 1) {
                final int n3 = n + (n2 - n) / 2;
                if (this.compare((Titre)this.elementAt(n3), titre) > 0) {
                    n2 = n3;
                }
                else {
                    n = n3;
                }
            }
            this.insertElementAt(titre, n + 1);
        }
    }
    
    private int compare(final Titre titre, final Titre titre2) {
        return titre.getID().toLowerCase().compareTo(titre2.getID().toLowerCase());
    }
}
