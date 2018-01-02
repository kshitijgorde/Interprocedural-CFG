// 
// Decompiled by Procyon v0.5.30
// 

package shout3d;

import java.awt.LayoutManager;
import shout3d.core.Picker;
import shout3d.core.ResourceListener;
import shout3d.core.Renderer;
import shout3d.core.Bindable;
import shout3d.core.JSRenderObserver;
import shout3d.core.JSFieldObserver;
import shout3d.core.Group;
import shout3d.core.Field;
import java.awt.Component;
import shout3d.core.Clock;
import shout3d.core.Shout3DException;
import shout3d.core.Node;
import shout3d.core.Searcher;
import shout3d.core.d;
import shout3d.core.JSDeviceObserver;
import shout3d.core.DeviceListener;
import shout3d.core.Shout3DViewer;
import java.applet.Applet;

public class Shout3DApplet extends Applet implements Shout3DViewer
{
    public Shout3DPanel panel;
    
    public final DeviceListener getDeviceListener() {
        return this.panel;
    }
    
    public synchronized void stop() {
        this.panel.n();
    }
    
    public void addJSDeviceObserver(final String s, final String s2) {
        if (System.getProperty("java.version").indexOf("1.0") <= -1) {
            new JSDeviceObserver(this, s, s2);
            return;
        }
        System.err.println("This browser does not support Applet > Javascript communication");
    }
    
    public final void setBilinearFiltering(final boolean bilinearFiltering) {
        this.panel.setBilinearFiltering(bilinearFiltering);
    }
    
    public final d a() {
        return this.panel.a();
    }
    
    public final Searcher getNewSearcher() {
        return this.panel.getNewSearcher();
    }
    
    public final void loadURL(final String[] array, final Node node) throws Shout3DException {
        this.panel.loadURL(array, node);
    }
    
    public final String getVersion() {
        return this.panel.getVersion();
    }
    
    public final Clock getClock() {
        return this.panel.getClock();
    }
    
    public final Component b() {
        return this;
    }
    
    public final boolean isRouted(final Field field, final Field field2) {
        return this.panel.isRouted(field, field2);
    }
    
    public final void setScene(final Group scene) {
        this.panel.setScene(scene);
    }
    
    public final Group getScene() {
        return this.panel.getScene();
    }
    
    public synchronized void start() {
        this.panel.q();
    }
    
    public void addJSFieldObserver(final String s, final String s2, final String s3) {
        if (System.getProperty("java.version").indexOf("1.0") <= -1) {
            new JSFieldObserver(this).addFieldObserver(s, s2, s3);
            return;
        }
        System.err.println("This browser does not support Applet > Javascript communication");
    }
    
    public void addJSRenderObserver(final boolean b, final String s) {
        if (System.getProperty("java.version").indexOf("1.0") <= -1) {
            new JSRenderObserver(this, b, s);
            return;
        }
        System.err.println("This browser does not support Applet > Javascript communication");
    }
    
    public final void setSceneFromURL(final String[] sceneFromURL) throws Shout3DException {
        this.panel.setSceneFromURL(sceneFromURL);
    }
    
    public final boolean isAntiAliased() {
        return this.panel.isAntiAliased();
    }
    
    public final boolean isLoadResourcesInSeparateThread() {
        return this.panel.isLoadResourcesInSeparateThread();
    }
    
    public final Bindable getCurrentBindableNode(final String s) throws Shout3DException {
        return this.panel.getCurrentBindableNode(s);
    }
    
    public void initShout3DPanel() {
        this.panel = new Shout3DPanel(this);
    }
    
    public final boolean addRoute(final Field field, final Field field2) throws Shout3DException {
        return this.panel.addRoute(field, field2);
    }
    
    public final boolean deleteRoute(final Field field, final Field field2) {
        return this.panel.deleteRoute(field, field2);
    }
    
    public final String getProfile() {
        return this.panel.getProfile();
    }
    
    public final Renderer getRenderer() {
        return this.panel.getRenderer();
    }
    
    public final ResourceListener getResourceListener() {
        return this.panel.getResourceListener();
    }
    
    public final void a(final Bindable bindable) {
        this.panel.a(bindable);
    }
    
    public final void setAntiAliased(final boolean antiAliased) {
        this.panel.setAntiAliased(antiAliased);
    }
    
    public final void setLoadResourcesInSeparateThread(final boolean loadResourcesInSeparateThread) {
        this.panel.setLoadResourcesInSeparateThread(loadResourcesInSeparateThread);
    }
    
    public final boolean isBilinearFiltering() {
        return this.panel.isBilinearFiltering();
    }
    
    public final void clearResourceCaches() {
        this.panel.a().c();
    }
    
    public final Picker getNewPicker() {
        return this.panel.getNewPicker();
    }
    
    public synchronized void init() {
        this.setLayout(null);
        this.initShout3DPanel();
        this.add(this.panel);
    }
    
    public final Node getNodeByName(final String s) {
        return this.panel.getNodeByName(s);
    }
}
