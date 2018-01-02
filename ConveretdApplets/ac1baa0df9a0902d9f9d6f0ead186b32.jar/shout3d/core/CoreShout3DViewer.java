// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public interface CoreShout3DViewer
{
    DeviceListener getDeviceListener();
    
    String getProfile();
    
    Renderer getRenderer();
    
    Searcher getNewSearcher();
    
    ResourceListener getResourceListener();
    
    void loadURL(final String[] p0, final Node p1) throws Shout3DException;
    
    String getVersion();
    
    Clock getClock();
    
    void setScene(final Group p0);
    
    Group getScene();
    
    Picker getNewPicker();
    
    void setSceneFromURL(final String[] p0) throws Shout3DException;
    
    Bindable getCurrentBindableNode(final String p0) throws Shout3DException;
    
    Node getNodeByName(final String p0);
}
