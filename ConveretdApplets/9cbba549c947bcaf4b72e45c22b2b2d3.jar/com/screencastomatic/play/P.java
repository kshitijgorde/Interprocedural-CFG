// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collection;
import java.awt.FontMetrics;
import java.awt.geom.Area;
import java.util.Iterator;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.RenderingHints;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.util.Map;
import java.awt.Rectangle;
import com.screencastomatic.play.stream.b;
import java.awt.Composite;
import java.awt.Font;
import com.screencastomatic.play.stream.i;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Container;
import java.awt.EventQueue;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.TreeMap;
import java.net.HttpURLConnection;
import java.net.URL;

class P implements Runnable
{
    final /* synthetic */ Display a;
    
    P(final Display a) {
        this.a = a;
    }
    
    public void run() {
        try {
            final URL url = new URL(this.a.m_captionsUrl);
            System.out.println("Opening connection for captions: " + this.a.m_captionsUrl);
            final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setUseCaches(false);
            final int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Failed to get to captions url: " + this.a.m_captionsUrl + " (response: " + responseCode + ")");
                return;
            }
            System.out.println("Starting to loading captions.");
            final TreeMap<Integer, m> treeMap = new TreeMap<Integer, m>();
            final InputStream inputStream = httpURLConnection.getInputStream();
            try {
                String line;
                while ((line = new BufferedReader(new InputStreamReader(new GZIPInputStream(inputStream))).readLine()) != null) {
                    final String[] split = line.split(",", 3);
                    final int intValue = Integer.valueOf(split[0]);
                    treeMap.put(intValue, new m(intValue, intValue + Integer.valueOf(split[1]), split[2]));
                }
                System.out.println("Done loading captions.");
                this.a.m_loadedCaptionsMap = treeMap;
                this.a.m_captionsStatus = Display$CaptionsStatus.b;
                EventQueue.invokeLater(new u(this));
            }
            finally {
                inputStream.close();
            }
        }
        catch (Exception ex) {
            this.a.m_captionsStatus = Display$CaptionsStatus.d;
            System.out.println("Failed to load captions.");
            ex.printStackTrace();
        }
    }
}
