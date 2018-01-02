// 
// Decompiled by Procyon v0.5.30
// 

package anon.proxy;

import java.util.Enumeration;
import java.util.Vector;

public class ProxyCallbackHandler
{
    private Vector callbacks;
    
    public ProxyCallbackHandler() {
        this.callbacks = null;
        this.callbacks = new Vector();
    }
    
    public void deliverUpstream(final AnonProxyRequest anonProxyRequest, final ProxyCallbackBuffer proxyCallbackBuffer) throws ProxyCallbackNotProcessableException, ProxyCallbackDelayException {
        final ProxyCallback[] array = this.toArray();
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                final int handleUpstreamChunk = array[i].handleUpstreamChunk(anonProxyRequest, proxyCallbackBuffer);
                if (handleUpstreamChunk == 1) {
                    throw new ProxyCallbackDelayException();
                }
                if (handleUpstreamChunk == 0) {
                    break;
                }
            }
        }
    }
    
    public void deliverDownstream(final AnonProxyRequest anonProxyRequest, final ProxyCallbackBuffer proxyCallbackBuffer) throws ProxyCallbackNotProcessableException, ProxyCallbackDelayException {
        if (anonProxyRequest == null) {
            throw new NullPointerException("AnonProxyRequest must not be null!");
        }
        final ProxyCallback[] array = this.toArray();
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                final int handleDownstreamChunk = array[i].handleDownstreamChunk(anonProxyRequest, proxyCallbackBuffer);
                if (handleDownstreamChunk == 1) {
                    throw new ProxyCallbackDelayException();
                }
                if (handleDownstreamChunk == 0) {
                    break;
                }
            }
        }
    }
    
    public synchronized void closeRequest(final AnonProxyRequest anonProxyRequest) {
        if (anonProxyRequest == null) {
            throw new NullPointerException("AnonProxyRequest must not be null!");
        }
        final Enumeration<ProxyCallback> elements = this.callbacks.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().closeRequest(anonProxyRequest);
        }
    }
    
    private synchronized ProxyCallback[] toArray() {
        final ProxyCallback[] array = new ProxyCallback[this.callbacks.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (ProxyCallback)this.callbacks.elementAt(i);
        }
        return array;
    }
    
    public synchronized void registerProxyCallback(final ProxyCallback proxyCallback) {
        if (!this.callbacks.contains(proxyCallback)) {
            this.callbacks.addElement(proxyCallback);
        }
    }
    
    public synchronized void removeCallback(final ProxyCallback proxyCallback) {
        this.callbacks.removeElement(proxyCallback);
    }
}
