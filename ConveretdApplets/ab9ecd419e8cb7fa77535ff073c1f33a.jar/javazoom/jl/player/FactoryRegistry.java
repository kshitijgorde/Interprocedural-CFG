// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.player;

import java.util.Enumeration;
import javazoom.jl.decoder.JavaLayerException;
import java.util.Hashtable;

public class FactoryRegistry extends AudioDeviceFactory
{
    private static FactoryRegistry instance;
    protected Hashtable factories;
    
    public FactoryRegistry() {
        this.factories = new Hashtable();
    }
    
    public static synchronized FactoryRegistry systemRegistry() {
        if (FactoryRegistry.instance == null) {
            (FactoryRegistry.instance = new FactoryRegistry()).registerDefaultFactories();
        }
        return FactoryRegistry.instance;
    }
    
    public void addFactory(final AudioDeviceFactory audioDeviceFactory) {
        this.factories.put(audioDeviceFactory.getClass(), audioDeviceFactory);
    }
    
    public void removeFactoryType(final Class clazz) {
        this.factories.remove(clazz);
    }
    
    public void removeFactory(final AudioDeviceFactory audioDeviceFactory) {
        this.factories.remove(audioDeviceFactory.getClass());
    }
    
    public AudioDevice createAudioDevice() throws JavaLayerException {
        AudioDevice audioDevice = null;
        final AudioDeviceFactory[] factoriesPriority = this.getFactoriesPriority();
        if (factoriesPriority == null) {
            throw new JavaLayerException(this + ": no factories registered");
        }
        Throwable t = null;
        for (int n = 0; audioDevice == null && n < factoriesPriority.length; ++n) {
            try {
                audioDevice = factoriesPriority[n].createAudioDevice();
            }
            catch (JavaLayerException ex) {
                t = ex;
            }
        }
        if (audioDevice == null && t != null) {
            throw new JavaLayerException("Cannot create AudioDevice", t);
        }
        return audioDevice;
    }
    
    protected AudioDeviceFactory[] getFactoriesPriority() {
        AudioDeviceFactory[] array = null;
        synchronized (this.factories) {
            final int size = this.factories.size();
            if (size != 0) {
                array = new AudioDeviceFactory[size];
                int n = 0;
                final Enumeration<AudioDeviceFactory> elements = this.factories.elements();
                while (elements.hasMoreElements()) {
                    array[n++] = elements.nextElement();
                }
            }
        }
        return array;
    }
    
    protected void registerDefaultFactories() {
        this.addFactory(new JavaSoundAudioDeviceFactory());
    }
    
    static {
        FactoryRegistry.instance = null;
    }
}
