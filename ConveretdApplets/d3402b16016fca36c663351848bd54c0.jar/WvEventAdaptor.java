// 
// Decompiled by Procyon v0.5.30
// 

public class WvEventAdaptor
{
    private WvEventListener listener;
    
    public WvEventAdaptor(final WvEventListener listener) {
        this.listener = listener;
    }
    
    public WvEventListener getListener() {
        return this.listener;
    }
    
    public void wvEventHandler(final WvEvent wvEvent) {
        final Object arg = wvEvent.arg;
        switch (wvEvent.id) {
            case 10001: {
                this.listener.kickOff();
                break;
            }
            case 10002: {
                this.listener.connect((String)arg);
                break;
            }
            case 10011:
            case 10012:
            case 10013: {
                this.listener.disconnect((int)arg);
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
            final WvPTZBListener wvPTZBListener = (WvPTZBListener)this.listener;
            switch (wvEvent.id) {
                case 41: {
                    wvPTZBListener.ptzbChanged((WvPTZB)arg);
                    break;
                }
                case 42: {
                    wvPTZBListener.ptzbChangedByAnotherClient((WvPTZB)arg);
                    break;
                }
                case 10020: {
                    wvPTZBListener.ptzbChangedInternal(wvEvent.arg, wvEvent.int1, wvEvent.int2);
                    break;
                }
            }
        }
        if (this.listener instanceof WvFloorListener) {
            final WvFloorListener wvFloorListener = (WvFloorListener)this.listener;
            switch (wvEvent.id) {
                case 31: {
                    wvFloorListener.enabledCameraControl((int)arg);
                    break;
                }
                case 32:
                case 301:
                case 303: {
                    wvFloorListener.disabledCameraControl();
                    break;
                }
                case 33: {
                    wvFloorListener.waitingCameraControl((int)arg);
                    break;
                }
                case 34: {
                    wvFloorListener.failedToGetCameraControl();
                    break;
                }
            }
        }
        if (this.listener instanceof WvPresetListener) {
            final WvPresetListener wvPresetListener = (WvPresetListener)this.listener;
            switch (wvEvent.id) {
                case 43: {
                    wvPresetListener.cameraSelected((String)arg);
                    break;
                }
                case 44: {
                    wvPresetListener.cameraChanged((int)arg);
                    break;
                }
                case 45: {
                    wvPresetListener.presetCameraOperationFinished((String)arg);
                    break;
                }
                case 46: {
                    wvPresetListener.cameraWillChanged();
                    break;
                }
            }
        }
        if (this.listener instanceof WvUtilListener) {
            final WvUtilListener wvUtilListener = (WvUtilListener)this.listener;
            switch (wvEvent.id) {
                case 22:
                case 302: {
                    wvUtilListener.cameraPower(false);
                }
                case 21: {
                    wvUtilListener.cameraPower(true);
                }
                case 61: {
                    wvUtilListener.imageSizeChanged((String)arg);
                }
            }
        }
    }
}
