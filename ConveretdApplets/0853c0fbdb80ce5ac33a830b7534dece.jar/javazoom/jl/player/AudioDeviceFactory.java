// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.player;

import javazoom.jl.decoder.JavaLayerException;

public abstract class AudioDeviceFactory
{
    public abstract AudioDevice createAudioDevice() throws JavaLayerException;
    
    protected AudioDevice instantiate(final ClassLoader classLoader, final String s) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz;
        if (classLoader == null) {
            clazz = Class.forName(s);
        }
        else {
            clazz = classLoader.loadClass(s);
        }
        return (AudioDevice)clazz.newInstance();
    }
}
