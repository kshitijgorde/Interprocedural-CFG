// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import XMLConsumer.IEntry;
import XMLConsumer.GlossaryEntry;
import java.util.Vector;

public class GloListView extends DynListView
{
    private IActionSink m_actionListener;
    private boolean m_bShowDef;
    
    public void adjustPosition(final boolean b, final INumChunkedData[] array, final int[] array2, final int n, final int[] array3) {
        if (b) {
            for (final int n2 : array2) {
                ++array3[n2];
            }
            return;
        }
        for (final int n3 : array2) {
            --array3[n3];
        }
    }
    
    public GloListView(final Vector vector) {
        super(vector);
        this.m_bShowDef = true;
    }
    
    public void mergeItems(final BlockContainer blockContainer, final boolean b, final INumChunkedData[] array, final int[] array2, final int n, final int[] array3, final int n2) {
        if (n > 0) {
            final GlossaryEntry glossaryEntry = (GlossaryEntry)array[array2[0]].getEntry(array3[array2[0]]);
            final String name = glossaryEntry.getName();
            final String value = glossaryEntry.getValue();
            for (int i = 1; i < n; ++i) {
                final GlossaryEntry glossaryEntry2 = (GlossaryEntry)array[array2[i]].getEntry(array3[array2[i]]);
                final String name2 = glossaryEntry2.getName();
                final String value2 = glossaryEntry2.getValue();
                if (name2 != null && Language.compare(name2, name) == 0 && (value == null || !value.equals(value2))) {
                    glossaryEntry.appendValue(value2);
                }
            }
            blockContainer.Insert(glossaryEntry, n, b);
            if (this.m_bShowDef) {
                glossaryEntry.action(this.m_actionListener);
                this.m_bShowDef = false;
            }
        }
    }
    
    public void accept(final Vector vector) {
        if (this.m_actionListener != null) {
            this.m_actionListener.accept(vector);
        }
    }
    
    public void addActionSink(final IActionSink actionListener) {
        this.m_actionListener = actionListener;
    }
}
