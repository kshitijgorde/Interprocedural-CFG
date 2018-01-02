// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.draw;

import java.io.StreamTokenizer;
import java.io.IOException;
import java.io.InputStream;

public class Markers
{
    protected int last;
    protected int max;
    protected Marker[] vert;
    
    public Markers() {
        this.max = 10;
        this.last = 0;
        this.vert = new Marker[this.max];
    }
    
    public Markers(final InputStream is) throws IOException {
        this();
        this.LoadMarkers(is);
    }
    
    public void AddMarker(int m, final int n, final boolean[] draw, final int[] x, final int[] y) {
        if (m < 1 || m > this.max) {
            return;
        }
        if (n <= 0) {
            return;
        }
        --m;
        this.last = m;
        this.vert[m] = new Marker();
        for (int i = 0; i < n; ++i) {
            this.vert[m].addVertex(new Marker.Vertex(draw[i], x[i], y[i]));
        }
    }
    
    public void AddMarker(final int n, final boolean[] draw, final int[] x, final int[] y) {
        this.AddMarker(this.last + 1, n, draw, x, y);
    }
    
    public void DeleteMarker(final int n) {
        if (n < 1 || n > this.max) {
            return;
        }
        this.vert[n - 1] = null;
    }
    
    public void ClearMarkers() {
        if (this.last == 0) {
            return;
        }
        for (int i = 0; i < this.max; ++i) {
            this.vert[i] = null;
        }
        this.last = 0;
    }
    
    public void LoadMarkers(final InputStream is) throws IOException {
        final StreamTokenizer st = new StreamTokenizer(is);
        st.eolIsSignificant(true);
        st.commentChar(35);
        while (true) {
            switch (st.nextToken()) {
                default: {}
                case 10: {
                    continue;
                }
                case -3: {
                    if ("start".equals(st.sval)) {
                        this.vert[this.last] = new Marker();
                        continue;
                    }
                    if ("end".equals(st.sval)) {
                        ++this.last;
                        continue;
                    }
                    if ("m".equals(st.sval)) {
                        final Marker.Vertex v = new Marker.Vertex();
                        v.draw = false;
                        if (st.nextToken() != -2) {
                            continue;
                        }
                        v.x = (int)st.nval;
                        if (st.nextToken() == -2) {
                            v.y = (int)st.nval;
                            this.vert[this.last].addVertex(v);
                            continue;
                        }
                        continue;
                    }
                    else {
                        if (!"l".equals(st.sval)) {
                            continue;
                        }
                        final Marker.Vertex v = new Marker.Vertex();
                        v.draw = true;
                        if (st.nextToken() != -2) {
                            continue;
                        }
                        v.x = (int)st.nval;
                        if (st.nextToken() == -2) {
                            v.y = (int)st.nval;
                            this.vert[this.last].addVertex(v);
                            continue;
                        }
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    public Marker getMarker(final int m) {
        if (m < 1 || m > this.max) {
            return null;
        }
        return this.vert[m - 1];
    }
}
