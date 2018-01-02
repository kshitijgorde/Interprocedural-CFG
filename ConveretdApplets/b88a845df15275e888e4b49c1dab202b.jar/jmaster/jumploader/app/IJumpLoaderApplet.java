// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.app;

import jmaster.jumploader.model.api.config.AppletConfig;
import jmaster.jumploader.model.api.config.UploaderConfig;
import jmaster.jumploader.model.api.config.ViewConfig;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.jsface.api.IJSUploader;

public interface IJumpLoaderApplet
{
    public static final String JS_CALLBACK_APPLET_INITIALIZED = "appletInitialized";
    public static final String JS_CALLBACK_UPLOADER_FILE_ADDED = "uploaderFileAdded";
    public static final String JS_CALLBACK_UPLOADER_FILE_REMOVED = "uploaderFileRemoved";
    public static final String JS_CALLBACK_UPLOADER_FILE_MOVED = "uploaderFileMoved";
    public static final String JS_CALLBACK_UPLOADER_FILE_STATUS_CHANGED = "uploaderFileStatusChanged";
    public static final String JS_CALLBACK_UPLOADER_FILES_RESET = "uploaderFilesReset";
    public static final String JS_CALLBACK_UPLOADER_STATUS_CHANGED = "uploaderStatusChanged";
    public static final String JS_CALLBACK_UPLOADER_SELECTION_CHANGED = "uploaderSelectionChanged";
    
    String getAppletInfo();
    
    IJSUploader getUploader();
    
    IMainView getMainView();
    
    ViewConfig getViewConfig();
    
    UploaderConfig getUploaderConfig();
    
    AppletConfig getAppletConfig();
    
    void showFrame(final boolean p0);
    
    void setUiDefault(final String p0, final String p1);
    
    Object getUiDefault(final String p0);
    
    String injectProperty(final Object p0, final String p1, final String p2);
    
    String injectProperty(final String p0, final String p1);
    
    Object getProperty(final String p0);
    
    boolean isDestroying();
    
    boolean isStopping();
}
