import javax.swing.event.ListDataEvent;
import javax.swing.DefaultListModel;
import javax.swing.event.ListDataListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class CombinedSceneList extends SceneList implements ListDataListener
{
    private imgViewer applet;
    private SceneList[] relatedSceneLists;
    private boolean ignoreRemoves;
    
    CombinedSceneList(final imgViewer applet, final Sensor sensor, final SceneList[] relatedSceneLists) {
        super(applet, sensor);
        this.applet = applet;
        this.relatedSceneLists = relatedSceneLists;
        this.ignoreRemoves = false;
        for (int i = 0; i < relatedSceneLists.length; ++i) {
            relatedSceneLists[i].addListDataListener(this);
        }
    }
    
    private SceneList findSceneList(final Metadata metadata) {
        final Sensor sensor = metadata.getSensor();
        SceneList list = null;
        for (int i = 0; i < this.relatedSceneLists.length; ++i) {
            if (sensor == this.relatedSceneLists[i].sensor) {
                list = this.relatedSceneLists[i];
                break;
            }
        }
        return list;
    }
    
    @Override
    public void add(final Metadata metadata) {
        final SceneList sceneList = this.findSceneList(metadata);
        if (sceneList == null) {
            return;
        }
        sceneList.add(metadata);
        if (this.applet.searchLimitDialog.isSceneListFilterEnabled()) {
            this.applet.searchLimitDialog.applyFilter();
        }
    }
    
    @Override
    public void remove(final Metadata metadata) {
        this.findSceneList(metadata).remove(metadata);
        if (this.applet.searchLimitDialog.isSceneListFilterEnabled()) {
            this.applet.searchLimitDialog.applyFilter();
        }
    }
    
    @Override
    public void order() {
        super.order();
        if (this.list.size() == 0) {
            for (int i = 0; i < this.relatedSceneLists.length; ++i) {
                this.relatedSceneLists[i].list.removeAllElements();
            }
        }
        else {
            this.ignoreRemoves = true;
            for (int j = 0; j < this.relatedSceneLists.length; ++j) {
                final DefaultListModel list = this.relatedSceneLists[j].list;
                for (int k = list.size() - 1; k >= 0; --k) {
                    if (this.find(list.elementAt(k)) == -1) {
                        list.removeElementAt(k);
                    }
                }
            }
            this.ignoreRemoves = false;
        }
        if (this.applet.searchLimitDialog.isSceneListFilterEnabled()) {
            this.applet.searchLimitDialog.applyFilter();
        }
    }
    
    @Override
    public boolean containsGapData() {
        for (int i = this.list.size() - 1; i >= 0; --i) {
            if (((Metadata)this.list.elementAt(i)).getSensor().dataHasGaps) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void intervalAdded(final ListDataEvent listDataEvent) {
        final DefaultListModel defaultListModel = (DefaultListModel)listDataEvent.getSource();
        final int index0 = listDataEvent.getIndex0();
        final int index2 = listDataEvent.getIndex1();
        this.restoreEnabledFlag = false;
        for (int i = index0; i <= index2; ++i) {
            this.list.addElement(new Metadata(defaultListModel.elementAt(i)));
        }
    }
    
    @Override
    public void intervalRemoved(final ListDataEvent listDataEvent) {
        if (this.ignoreRemoves) {
            return;
        }
        final DefaultListModel defaultListModel = (DefaultListModel)listDataEvent.getSource();
        Sensor sensor = null;
        SceneList list = null;
        for (int i = 0; i < this.relatedSceneLists.length; ++i) {
            if (defaultListModel == this.relatedSceneLists[i].list) {
                sensor = this.relatedSceneLists[i].sensor;
                list = this.relatedSceneLists[i];
                break;
            }
        }
        if (sensor != null) {
            if (!this.restoreEnabledFlag && list.restoreEnabledFlag) {
                this.savedList.removeAllElements();
                final Object[] array = this.list.toArray();
                for (int j = 0; j < array.length; ++j) {
                    this.savedList.add(array[j]);
                }
                this.restoreEnabledFlag = true;
            }
            final int index0 = listDataEvent.getIndex0();
            final int index2 = listDataEvent.getIndex1();
            int n = 0;
            int k = 0;
            while (k < this.list.size()) {
                if (((Metadata)this.list.elementAt(k)).getSensor() == sensor) {
                    if (n >= index0 && n <= index2) {
                        this.list.removeElementAt(k);
                    }
                    else {
                        ++k;
                    }
                    if (++n > index2) {
                        break;
                    }
                    continue;
                }
                else {
                    ++k;
                }
            }
        }
    }
    
    @Override
    public void contentsChanged(final ListDataEvent listDataEvent) {
    }
}
