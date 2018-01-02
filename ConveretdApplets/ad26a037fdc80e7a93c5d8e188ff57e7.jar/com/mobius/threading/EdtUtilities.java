// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.threading;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import java.lang.reflect.Method;

class EdtUtilities
{
    protected static void runOnEdt(final Object o, final Method method, final Object[] array) {
        final MethodRunner methodRunner = new MethodRunner(o, method, array);
        if (SwingUtilities.isEventDispatchThread()) {
            methodRunner.run();
        }
        else {
            SwingUtilities.invokeLater(methodRunner);
        }
    }
    
    protected static Object runOnEdtAndWait(final Object o, final Method method, final Object[] array) {
        final MethodRunner methodRunner = new MethodRunner(o, method, array);
        if (SwingUtilities.isEventDispatchThread()) {
            methodRunner.run();
        }
        else {
            try {
                SwingUtilities.invokeAndWait(methodRunner);
            }
            catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
            catch (InterruptedException ex2) {
                ex2.printStackTrace();
            }
        }
        return methodRunner.getReturnValue();
    }
    
    protected static void runOffEdt(final Object o, final Method method, final Object[] array, final boolean b) {
        final SwingWorker swingWorker = getSwingWorker(o, method, array, b);
        if (swingWorker != null) {
            swingWorker.start();
        }
    }
    
    protected static void runOffEdt(final Object o, final Method method, final Object[] array, final Object o2, final Method method2, final Object[] array2, final boolean b) {
        final SwingWorker swingWorker = getSwingWorker(o, method, array, o2, method2, array2, b);
        if (swingWorker != null) {
            swingWorker.start();
        }
    }
    
    private static SwingWorker getSwingWorker(final Object o, final Method method, final Object[] array, final boolean b) {
        return getSwingWorker(o, method, array, null, null, null, b);
    }
    
    private static SwingWorker getSwingWorker(final Object o, final Method method, final Object[] array, final Object o2, final Method method2, final Object[] array2, final boolean b) {
        return new SwingWorker() {
            public Object construct() {
                Object invoke = null;
                if (method != null) {
                    try {
                        invoke = method.invoke(o, array);
                    }
                    catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                    catch (IllegalArgumentException ex2) {
                        ex2.printStackTrace();
                    }
                    catch (IllegalAccessException ex3) {
                        ex3.printStackTrace();
                    }
                }
                return invoke;
            }
            
            public void finished() {
                if (method2 == null) {
                    return;
                }
                if (b) {
                    array2[0] = this.get();
                }
                try {
                    method2.invoke(o2, array2);
                }
                catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                }
                catch (IllegalArgumentException ex2) {
                    ex2.printStackTrace();
                }
                catch (IllegalAccessException ex3) {
                    ex3.printStackTrace();
                }
            }
        };
    }
}
