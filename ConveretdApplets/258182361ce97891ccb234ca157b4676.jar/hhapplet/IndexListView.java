// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.net.URL;
import java.util.Vector;
import java.util.Enumeration;
import XMLConsumer.IEntry;
import java.net.MalformedURLException;
import XMLConsumer.Topic;
import XMLConsumer.IdxData;
import XMLConsumer.IdxEntry;

public class IndexListView extends DynListView
{
    public void adjustPosition(final boolean b, final INumChunkedData[] array, final int[] array2, final int n, final int[] array3) {
        if (b) {
            for (int i = 0; i < n; ++i) {
                final int nextSpan = array[array2[i]].getEntry(array3[array2[i]]).getNextSpan();
                final int n2 = array2[i];
                array3[n2] += 1 + nextSpan;
            }
            return;
        }
        for (int j = 0; j < n; ++j) {
            final int prevSpan = array[array2[j]].getEntry(array3[array2[j]]).getPrevSpan();
            final int n3 = array2[j];
            array3[n3] -= 1 + prevSpan;
        }
    }
    
    public void writeOneItem(final BlockContainer blockContainer, final boolean b, final INumChunkedData[] array, final int[] array2, final int n, final int[] array3, final int n2) {
        final IdxEntry idxEntry = (IdxEntry)array[array2[0]].getEntry(array3[array2[0]]);
        for (int i = 1; i < n; ++i) {
            final IdxData idxData = (IdxData)array[array2[i]];
            final Enumeration topics = ((IdxEntry)idxData.getEntry(array3[array2[i]])).getTopics();
            while (topics != null && topics.hasMoreElements()) {
                final Topic topic = topics.nextElement();
                try {
                    idxEntry.addTopic(new Topic(URLFileHandler.makeURL(idxData.getProjURL(), topic.getURL(), null).toString(), topic.getName()));
                }
                catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        blockContainer.Insert(idxEntry, n, b);
    }
    
    public IndexListView(final Vector vector) {
        super(vector);
    }
    
    public void mergeItems(final BlockContainer blockContainer, final boolean b, final INumChunkedData[] array, final int[] array2, final int n, final int[] array3, final int n2) {
        final BlockContainer blockContainer2 = new BlockContainer(0);
        this.writeOneItem(blockContainer2, b, array, array2, n, array3, n2);
        final int length = array3.length;
        final int[] array4 = new int[length];
        final int[] array5 = new int[length];
        for (int i = 0; i < array3.length; ++i) {
            array4[i] = array3[i];
            array5[i] = -1;
        }
        for (int j = 0; j < n; ++j) {
            final int nextSpan = array[array2[j]].getEntry(array3[array2[j]]).getNextSpan();
            final int[] array6 = array4;
            final int n3 = array2[j];
            ++array6[n3];
            if (nextSpan > 0) {
                array5[array2[j]] = array4[array2[j]] + nextSpan;
            }
        }
        final BlockContainer blockContainer3 = new BlockContainer(0);
        this.writeItems(blockContainer3, array, array4, null, array5, true, n2 + 1);
        blockContainer2.addSub(blockContainer3, true);
        blockContainer.appendSub(blockContainer2, b);
    }
    
    public void accept(final Vector vector) {
        if (vector.size() == 2) {
            final String s = vector.elementAt(0);
            final String s2 = vector.elementAt(1);
            try {
                final URL url = new URL(URLFileHandler.GetNormalizedLocal(s));
                if (s2 != null) {
                    BsscHelpRedirector.showDoc(url, s2);
                    return;
                }
                BsscHelpRedirector.showDoc(url);
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
