// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.renderer;

import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.view.impl.file.list.renderer.AbstractFileRendererComponent;

public class AbstractUploadFileRendererComponent extends AbstractFileRendererComponent
{
    public IUploadFile getUploadFile() {
        return (IUploadFile)this.getFile();
    }
}
