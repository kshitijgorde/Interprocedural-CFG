// 
// Decompiled by Procyon v0.5.30
// 

public class WvEventAdaptor
{
    private WvEventListener listener;
    
    public WvEventAdaptor(final WvEventListener wveventlistener) {
        this.listener = wveventlistener;
    }
    
    public WvEventListener getListener() {
        return this.listener;
    }
    
    public void wvEventHandler(final WvEvent wvevent) {
        final Object obj = wvevent.arg;
        switch (wvevent.id) {
            case 10001: {
                this.listener.kickOff();
                break;
            }
            case 10002: {
                this.listener.connect((String)obj);
                break;
            }
            case 10011:
            case 10012:
            case 10013: {
                this.listener.disconnect((int)obj);
                break;
            }
            case 11: {
                this.listener.videoConnected(true);
                break;
            }
            case 12:
            case 13: {
                this.listener.videoConnected(false);
                break;
            }
            case 14: {
                this.listener.cameraConnected(true);
                break;
            }
            case 15:
            case 16:
            case 201:
            case 204:
            case 209:
            case 211:
            case 212: {
                this.listener.cameraConnected(false);
                break;
            }
        }
        if (this.listener instanceof WvPTZBListener) {
            final WvPTZBListener wvptzblistener = (WvPTZBListener)this.listener;
            switch (wvevent.id) {
                case 41: {
                    wvptzblistener.ptzbChanged((WvPTZB)obj);
                    break;
                }
                case 42: {
                    wvptzblistener.ptzbChangedByAnotherClient((WvPTZB)obj);
                    break;
                }
                case 10020: {
                    final Object obj2 = wvevent.arg;
                    final int i = wvevent.int1;
                    final int j = wvevent.int2;
                    wvptzblistener.ptzbChangedInternal(obj2, i, j);
                    break;
                }
            }
        }
        if (this.listener instanceof WvFloorListener) {
            final WvFloorListener wvfloorlistener = (WvFloorListener)this.listener;
            switch (wvevent.id) {
                case 31: {
                    wvfloorlistener.enabledCameraControl((int)obj);
                    break;
                }
                case 32:
                case 301:
                case 303: {
                    wvfloorlistener.disabledCameraControl();
                    break;
                }
                case 33: {
                    wvfloorlistener.waitingCameraControl((int)obj);
                    break;
                }
                case 34: {
                    wvfloorlistener.failedToGetCameraControl();
                    break;
                }
            }
        }
        if (this.listener instanceof WvPresetListener) {
            final WvPresetListener wvpresetlistener = (WvPresetListener)this.listener;
            switch (wvevent.id) {
                case 43: {
                    wvpresetlistener.cameraSelected((String)obj);
                    break;
                }
                case 44: {
                    wvpresetlistener.cameraChanged((int)obj);
                    break;
                }
                case 45: {
                    wvpresetlistener.presetCameraOperationFinished((String)obj);
                    break;
                }
                case 46: {
                    wvpresetlistener.cameraWillChanged();
                    break;
                }
            }
        }
        if (this.listener instanceof WvUtilListener) {
            final WvUtilListener wvutillistener = (WvUtilListener)this.listener;
            switch (wvevent.id) {
                case 22:
                case 302: {
                    wvutillistener.cameraPower(false);
                }
                case 21: {
                    wvutillistener.cameraPower(true);
                }
                case 61: {
                    wvutillistener.imageSizeChanged((String)obj);
                }
            }
        }
    }
}
