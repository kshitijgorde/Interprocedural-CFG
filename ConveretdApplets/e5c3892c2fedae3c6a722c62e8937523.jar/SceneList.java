import javax.swing.event.ListDataListener;
import java.net.URLEncoder;
import javax.swing.JOptionPane;
import java.net.URL;
import java.awt.Component;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SceneList implements ListModel
{
    protected DefaultListModel list;
    protected Vector savedList;
    protected boolean restoreEnabledFlag;
    protected Sensor sensor;
    private imgViewer applet;
    private int maxOrderSize;
    private int maxUrlLength;
    
    SceneList(final imgViewer applet, final Sensor sensor) {
        this.applet = applet;
        this.sensor = sensor;
        this.maxOrderSize = -1;
        this.list = new DefaultListModel();
        this.savedList = new Vector();
        if (applet.usingIE) {
            this.maxUrlLength = 2083;
        }
        else {
            this.maxUrlLength = 8190;
        }
    }
    
    public int find(final Metadata metadata) {
        int n = -1;
        for (int size = this.list.size(), i = 0; i < size; ++i) {
            if (metadata.entityID.equals(((Metadata)this.list.elementAt(i)).entityID)) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public void add(final Metadata metadata) {
        if (metadata.getSensor() != this.sensor) {
            return;
        }
        final int find = this.find(metadata);
        if (find < 0) {
            this.restoreEnabledFlag = false;
            this.list.addElement(new Metadata(metadata));
            if (this.applet.searchLimitDialog.isSceneListFilterEnabled()) {
                this.applet.searchLimitDialog.applyFilter();
            }
        }
        else {
            this.list.setElementAt(this.list.elementAt(find), find);
        }
    }
    
    public int getSceneCount() {
        return this.list.size();
    }
    
    @Override
    public int getSize() {
        return this.list.getSize();
    }
    
    public int getDownloadableSceneCount() {
        final int size = this.list.size();
        int n = 0;
        for (int i = 0; i < size; ++i) {
            if (((Metadata)this.list.elementAt(i)).isDownloadable) {
                ++n;
            }
        }
        return n;
    }
    
    public Metadata getSceneAt(final int n) {
        return this.list.elementAt(n);
    }
    
    public void remove(final Metadata metadata) {
        final int find = this.find(metadata);
        if (find >= 0) {
            this.restoreEnabledFlag = false;
            this.list.removeElementAt(find);
            if (this.applet.searchLimitDialog.isSceneListFilterEnabled()) {
                this.applet.searchLimitDialog.applyFilter();
            }
        }
    }
    
    private int confirmQuestionableScenes(final Metadata[] array) {
        final int length = array.length;
        int n = 0;
        if (!this.sensor.warnWhenOrderingPoorQuality) {
            for (int i = 0; i < length; ++i) {
                array[i].visible = true;
            }
            return length;
        }
        for (int j = 0; j < length; ++j) {
            boolean visible = true;
            final Metadata metadata = array[j];
            if (metadata.cloudCover > 50) {
                visible = false;
            }
            else if (metadata.quality != null) {
                final int qualityLimit = metadata.getSensor().qualityLimit;
                for (int k = 0; k < metadata.quality.length; ++k) {
                    if (metadata.quality[k] < qualityLimit) {
                        visible = false;
                        break;
                    }
                }
            }
            metadata.visible = visible;
            if (visible) {
                ++n;
            }
        }
        if (n == length) {
            return length;
        }
        int n2 = 0;
        final Metadata[] array2 = new Metadata[length - n];
        for (final Metadata metadata2 : array) {
            if (!metadata2.visible) {
                array2[n2] = metadata2;
                ++n2;
            }
        }
        final ConfirmScenesDialog confirmScenesDialog = new ConfirmScenesDialog(array2, n);
        confirmScenesDialog.setLocationRelativeTo(this.applet);
        confirmScenesDialog.setVisible(true);
        final boolean wasCancelled = confirmScenesDialog.wasCancelled();
        confirmScenesDialog.dispose();
        if (wasCancelled) {
            return -1;
        }
        for (int n3 = 0; n3 < array2.length; ++n3) {
            if (array2[n3].visible) {
                ++n;
            }
        }
        return n;
    }
    
    public void download(final Metadata metadata) {
        final String buildDownloadURL = this.sensor.buildDownloadURL(metadata);
        System.out.println("downloading scene with URL: " + buildDownloadURL);
        new StringBuilder().append("Download ").append(metadata.entityID).toString();
        try {
            this.applet.getAppletContext().showDocument(new URL(buildDownloadURL), this.sensor.orderWindowName);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
        }
    }
    
    public void order() {
        final int size = this.list.size();
        if (size < 1) {
            return;
        }
        if (this.maxOrderSize == -1) {
            this.findMaxOrderSize(this.list.elementAt(0));
        }
        if (this.maxOrderSize < 1) {
            return;
        }
        final int size2 = this.list.size();
        boolean b = true;
        boolean b2 = false;
        final boolean b3 = false;
        final int maxScenesPerOrder = this.sensor.maxScenesPerOrder;
        this.getDownloadableSceneCount();
        final int n = size;
        if (n < 1) {
            return;
        }
        final Metadata[] array = new Metadata[n];
        for (int n2 = 0, i = 0; i < n; array[i++] = this.list.elementAt(n2++)) {}
        final int confirmQuestionableScenes = this.confirmQuestionableScenes(array);
        if (confirmQuestionableScenes <= 0) {
            return;
        }
        if (confirmQuestionableScenes != size2) {
            b = false;
        }
        int maxOrderSize = confirmQuestionableScenes;
        if (maxOrderSize > this.maxOrderSize) {
            maxOrderSize = this.maxOrderSize;
            b = false;
            b2 = true;
        }
        final Metadata[] array2 = new Metadata[maxOrderSize];
        int j = n - 1;
        int n3 = 0;
        while (j >= 0) {
            final Metadata metadata = array[j];
            if (metadata.visible) {
                array2[n3] = metadata;
                if (++n3 >= maxOrderSize) {
                    break;
                }
            }
            --j;
        }
        final String buildOrderURL = this.sensor.buildOrderURL(array2);
        System.out.println("ordering " + maxOrderSize + " scenes with URL: " + buildOrderURL);
        try {
            this.applet.getAppletContext().showDocument(new URL(buildOrderURL), this.sensor.orderWindowName);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
        if (!this.restoreEnabledFlag) {
            this.savedList.removeAllElements();
            final Object[] array3 = this.list.toArray();
            for (int k = 0; k < array3.length; ++k) {
                this.savedList.add(array3[k]);
            }
            this.restoreEnabledFlag = true;
        }
        if (b) {
            this.list.removeAllElements();
        }
        else {
            for (int l = maxOrderSize - 1; l >= 0; --l) {
                final int find = this.find(array2[l]);
                if (find != -1) {
                    this.list.removeElementAt(find);
                }
            }
            if (b3) {
                JOptionPane.showMessageDialog(this.applet.getDialogContainer(), new String[] { "Added " + maxOrderSize + " of " + size + " scenes from your scene list " + "to the shopping cart.  " + this.list.size() + " scene" + ((this.list.size() > 1) ? "s remain." : " remains."), "Note: There is a limit to the number of scenes that can be passed to the shopping cart", "for on-demand processing and your scene list exceeded that limit.", " ", "Please DO NOT press the order button again to submit more scenes to the shopping", "cart until your shopping cart is empty." }, "Partial scene list submitted to shopping cart", 2);
            }
            else if (b2) {
                JOptionPane.showMessageDialog(this.applet.getDialogContainer(), new String[] { "Added " + maxOrderSize + " of " + size + " scenes from your scene list " + "to the shopping cart.  " + this.list.size() + " scene" + ((this.list.size() > 1) ? "s remain." : " remains."), "Note: There is a limit to the length of a URL that can be passed to the shopping cart and your scene list has exceeded that limit.", "Please press the order button again to submit more scenes to the shopping cart." }, "Partial scene list submitted to shopping cart", 2);
            }
        }
        if (this.applet.searchLimitDialog.isSceneListFilterEnabled()) {
            this.applet.searchLimitDialog.applyFilter();
        }
    }
    
    public void restore() {
        if (this.restoreEnabledFlag) {
            for (int i = 0; i < this.savedList.size(); ++i) {
                this.add((Metadata)this.savedList.elementAt(i));
            }
            this.restoreEnabledFlag = false;
            this.savedList.removeAllElements();
        }
    }
    
    public boolean isRestoreEnabled() {
        return this.restoreEnabledFlag;
    }
    
    public int contains(final TOC toc) {
        for (int size = this.list.size(), i = 0; i < size; ++i) {
            final Metadata metadata = this.list.elementAt(i);
            if (metadata.gridCol == toc.gridCol && metadata.gridRow == toc.gridRow) {
                for (int j = 0; j < toc.numImg; ++j) {
                    if (metadata.entityID.equals(toc.scenes[j].entityID)) {
                        return j;
                    }
                }
            }
        }
        return -1;
    }
    
    protected void findMaxOrderSize(final Metadata metadata) {
        final Metadata[] array = { null };
        final String s = new String("US-ASCII");
        final int n = 82;
        array[0] = metadata;
        String encode;
        try {
            encode = URLEncoder.encode(this.sensor.buildOrderURL(array), s);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
            return;
        }
        final Metadata[] array2 = { metadata, metadata };
        String encode2;
        try {
            encode2 = URLEncoder.encode(this.sensor.buildOrderURL(array2), s);
        }
        catch (Exception ex2) {
            System.out.println("Exception: " + ex2.toString());
            return;
        }
        final int n2 = encode2.length() - encode.length();
        this.maxOrderSize = (this.maxUrlLength - (encode.length() - n2 + n)) / n2;
        this.maxOrderSize -= this.maxOrderSize % 10;
    }
    
    public boolean containsGapData() {
        return this.sensor.dataHasGaps;
    }
    
    @Override
    public Object getElementAt(final int n) {
        return this.list.getElementAt(n);
    }
    
    @Override
    public void addListDataListener(final ListDataListener listDataListener) {
        this.list.addListDataListener(listDataListener);
    }
    
    @Override
    public void removeListDataListener(final ListDataListener listDataListener) {
        this.list.removeListDataListener(listDataListener);
    }
    
    public ListModel getModel() {
        return this.list;
    }
}
