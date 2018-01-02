// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.osgi.utils;

import org.osgi.framework.Bundle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class OSGiBundleClassLoaderHelper
{
    private static boolean identifiedOsgiImpl;
    private static boolean isEquinox;
    private static boolean isFelix;
    private static Method Equinox_BundleHost_getBundleLoader_method;
    private static Method Equinox_BundleLoader_createClassLoader_method;
    private static Field Felix_BundleImpl_m_modules_field;
    private static Field Felix_ModuleImpl_m_classLoader_field;
    
    private static void init(final Bundle bundle) {
        OSGiBundleClassLoaderHelper.identifiedOsgiImpl = true;
        try {
            OSGiBundleClassLoaderHelper.isEquinox = (bundle.getClass().getClassLoader().loadClass("org.eclipse.osgi.framework.internal.core.BundleHost") != null);
        }
        catch (Throwable t) {
            OSGiBundleClassLoaderHelper.isEquinox = false;
        }
        if (!OSGiBundleClassLoaderHelper.isEquinox) {
            try {
                OSGiBundleClassLoaderHelper.isFelix = (bundle.getClass().getClassLoader().loadClass("org.apache.felix.framework.BundleImpl") != null);
            }
            catch (Throwable t2) {
                OSGiBundleClassLoaderHelper.isFelix = false;
            }
        }
    }
    
    public static ClassLoader getBundleClassLoader(final Bundle bundle) {
        String bundleActivator = bundle.getHeaders().get("Bundle-Activator");
        if (bundleActivator == null) {
            bundleActivator = bundle.getHeaders().get("Jetty-ClassInBundle");
        }
        if (bundleActivator != null) {
            try {
                return bundle.loadClass(bundleActivator).getClassLoader();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!OSGiBundleClassLoaderHelper.identifiedOsgiImpl) {
            init(bundle);
        }
        if (OSGiBundleClassLoaderHelper.isEquinox) {
            return internalGetEquinoxBundleClassLoader(bundle);
        }
        if (OSGiBundleClassLoaderHelper.isFelix) {
            return internalGetFelixBundleClassLoader(bundle);
        }
        return null;
    }
    
    private static ClassLoader internalGetEquinoxBundleClassLoader(final Bundle bundle) {
        try {
            if (OSGiBundleClassLoaderHelper.Equinox_BundleHost_getBundleLoader_method == null) {
                (OSGiBundleClassLoaderHelper.Equinox_BundleHost_getBundleLoader_method = bundle.getClass().getClassLoader().loadClass("org.eclipse.osgi.framework.internal.core.BundleHost").getDeclaredMethod("getBundleLoader", (Class<?>[])new Class[0])).setAccessible(true);
            }
            final Object bundleLoader = OSGiBundleClassLoaderHelper.Equinox_BundleHost_getBundleLoader_method.invoke(bundle, new Object[0]);
            if (OSGiBundleClassLoaderHelper.Equinox_BundleLoader_createClassLoader_method == null && bundleLoader != null) {
                (OSGiBundleClassLoaderHelper.Equinox_BundleLoader_createClassLoader_method = bundleLoader.getClass().getClassLoader().loadClass("org.eclipse.osgi.internal.loader.BundleLoader").getDeclaredMethod("createClassLoader", (Class<?>[])new Class[0])).setAccessible(true);
            }
            return (ClassLoader)OSGiBundleClassLoaderHelper.Equinox_BundleLoader_createClassLoader_method.invoke(bundleLoader, new Object[0]);
        }
        catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
    
    private static ClassLoader internalGetFelixBundleClassLoader(final Bundle bundle) {
        try {
            if (OSGiBundleClassLoaderHelper.Felix_BundleImpl_m_modules_field == null) {
                (OSGiBundleClassLoaderHelper.Felix_BundleImpl_m_modules_field = bundle.getClass().getClassLoader().loadClass("org.apache.felix.framework.BundleImpl").getDeclaredField("m_modules")).setAccessible(true);
            }
            final Object[] moduleArray = (Object[])OSGiBundleClassLoaderHelper.Felix_BundleImpl_m_modules_field.get(bundle);
            final Object currentModuleImpl = moduleArray[moduleArray.length - 1];
            if (OSGiBundleClassLoaderHelper.Felix_ModuleImpl_m_classLoader_field == null && currentModuleImpl != null) {
                (OSGiBundleClassLoaderHelper.Felix_ModuleImpl_m_classLoader_field = bundle.getClass().getClassLoader().loadClass("org.apache.felix.framework.ModuleImpl").getDeclaredField("m_classLoader")).setAccessible(true);
            }
            ClassLoader cl = (ClassLoader)OSGiBundleClassLoaderHelper.Felix_ModuleImpl_m_classLoader_field.get(currentModuleImpl);
            if (cl == null) {
                bundle.loadClass("java.lang.Object");
                cl = (ClassLoader)OSGiBundleClassLoaderHelper.Felix_ModuleImpl_m_classLoader_field.get(currentModuleImpl);
                return cl;
            }
            return cl;
        }
        catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
    
    static {
        OSGiBundleClassLoaderHelper.identifiedOsgiImpl = false;
        OSGiBundleClassLoaderHelper.isEquinox = false;
        OSGiBundleClassLoaderHelper.isFelix = false;
    }
}
