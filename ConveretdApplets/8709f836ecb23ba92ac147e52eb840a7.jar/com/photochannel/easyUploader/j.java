// 
// Decompiled by Procyon v0.5.30
// 

package com.photochannel.easyUploader;

import z.M;
import z.am;
import java.io.IOException;
import z.au;
import z.Z;
import z.aA;
import java.awt.Image;
import java.awt.Dimension;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import z.aT;
import z.C;
import z.ad;
import z.aU;
import java.util.Observable;
import z.ah;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import java.security.AccessControlException;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;
import z.bg;
import java.util.Timer;
import java.awt.Component;
import java.awt.LayoutManager;
import z.ay;
import z.af;
import z.R;
import z.ax;
import z.V;
import z.z;
import z.G;
import java.io.File;
import z.ab;
import z.aO;
import z.aS;
import java.awt.Container;
import java.io.FileFilter;
import z.aj;
import z.w;
import java.util.concurrent.LinkedBlockingQueue;
import java.net.URL;
import java.util.Observer;
import java.util.TimerTask;

final class j extends TimerTask
{
    private /* synthetic */ b a;
    
    j(final b a) {
        this.a = a;
    }
    
    public final void run() {
        this.a.t.repaint();
    }
}
