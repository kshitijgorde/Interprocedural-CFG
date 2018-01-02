// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.player;

import javazoom.jl.decoder.JavaLayerException;

public class JavaSoundAudioDeviceFactory extends AudioDeviceFactory
{
    private boolean tested;
    private static final String DEVICE_CLASS_NAME = "javazoom.jl.player.JavaSoundAudioDevice";
    
    public JavaSoundAudioDeviceFactory() {
        this.tested = false;
    }
    
    public synchronized AudioDevice createAudioDevice() throws JavaLayerException {
        if (!this.tested) {
            this.testAudioDevice();
            this.tested = true;
        }
        try {
            return this.createAudioDeviceImpl();
        }
        catch (Exception ex) {
            throw new JavaLayerException("unable to create JavaSound device: " + ex);
        }
        catch (LinkageError linkageError) {
            throw new JavaLayerException("unable to create JavaSound device: " + linkageError);
        }
    }
    
    protected JavaSoundAudioDevice createAudioDeviceImpl() throws JavaLayerException {
        final ClassLoader classLoader = this.getClass().getClassLoader();
        try {
            return (JavaSoundAudioDevice)this.instantiate(classLoader, "javazoom.jl.player.JavaSoundAudioDevice");
        }
        catch (Exception ex) {
            throw new JavaLayerException("Cannot create JavaSound device", ex);
        }
        catch (LinkageError linkageError) {
            throw new JavaLayerException("Cannot create JavaSound device", linkageError);
        }
    }
    
    public void testAudioDevice() throws JavaLayerException {
        this.createAudioDeviceImpl().test();
    }
}
