// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api;

import jmaster.util.B.D;
import jmaster.jumploader.view.impl.upload.MetadataView;
import jmaster.jumploader.model.api.file.IFile;
import jmaster.jumploader.model.api.file.IFileBrowser;
import jmaster.jumploader.model.api.C.A;
import jmaster.jumploader.model.api.upload.IUploader;
import jmaster.jumploader.model.api.config.ImageConfig;
import jmaster.jumploader.model.api.config.AppletConfig;
import jmaster.jumploader.model.api.config.ViewConfig;
import jmaster.jumploader.model.api.config.UploaderConfig;
import jmaster.jumploader.model.api.config.SystemConfig;
import jmaster.jumploader.app.JumpLoaderMain;

public interface B
{
    void A(final boolean p0);
    
    JumpLoaderMain E();
    
    SystemConfig I();
    
    UploaderConfig B();
    
    ViewConfig H();
    
    AppletConfig F();
    
    ImageConfig J();
    
    IUploader D();
    
    A A();
    
    jmaster.jumploader.model.api.B.A L();
    
    IFileBrowser G();
    
    jmaster.jumploader.model.impl.A.A K();
    
    String A(final IFile p0);
    
    MetadataView M();
    
    D C();
}
