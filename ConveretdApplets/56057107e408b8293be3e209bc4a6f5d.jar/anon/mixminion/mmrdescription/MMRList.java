// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.mmrdescription;

import java.text.SimpleDateFormat;
import anon.util.Base64;
import java.io.Reader;
import java.io.LineNumberReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import logging.LogHolder;
import logging.LogType;
import anon.crypto.MyRandom;
import java.util.Hashtable;
import java.util.Vector;

public class MMRList
{
    private Vector m_mixminionrouters;
    private Vector m_exitnodes;
    private Vector m_fragexitnodes;
    private Hashtable m_mixminionroutersWithNames;
    private MyRandom m_rand;
    private MMRListFetcher m_mmrlistFetcher;
    
    public MMRList(final MMRListFetcher mmrlistFetcher) {
        this.m_mixminionrouters = new Vector();
        this.m_fragexitnodes = new Vector();
        this.m_exitnodes = new Vector();
        this.m_mixminionroutersWithNames = new Hashtable();
        this.m_mmrlistFetcher = mmrlistFetcher;
        this.m_rand = new MyRandom();
    }
    
    public synchronized int size() {
        return this.m_mixminionrouters.size();
    }
    
    public synchronized void setFetcher(final MMRListFetcher mmrlistFetcher) {
        this.m_mmrlistFetcher = mmrlistFetcher;
    }
    
    public synchronized boolean updateList() {
        try {
            final byte[] mmrList = this.m_mmrlistFetcher.getMMRList();
            return mmrList != null && this.parseDocument(mmrList);
        }
        catch (Throwable t) {
            LogHolder.log(7, LogType.MISC, "There was a problem with fetching the available MMRouters: " + t.getMessage());
            return false;
        }
    }
    
    public Vector getList() {
        return this.m_mixminionrouters;
    }
    
    public synchronized MMRDescription getByName(final String s) {
        return this.m_mixminionroutersWithNames.get(s);
    }
    
    public synchronized void remove(final String s) {
        final MMRDescription byName = this.getByName(s);
        this.m_mixminionrouters.removeElement(byName);
        this.m_exitnodes.removeElement(byName);
        this.m_mixminionroutersWithNames.remove(s);
    }
    
    public synchronized MMRDescription getByRandom(final Vector vector) {
        return vector.elementAt(this.m_rand.nextInt(vector.size()));
    }
    
    public synchronized MMRDescription getByRandom() {
        return this.m_mixminionrouters.elementAt(this.m_rand.nextInt(this.m_mixminionrouters.size()));
    }
    
    public synchronized Vector getByRandomWithExit(final int n) {
        final Vector vector = new Vector<MMRDescription>();
        MMRDescription mmrDescription = null;
        for (int i = 0; i < n - 1; ++i) {
            boolean contains = true;
            for (int n2 = 0; contains && n2 != 10; ++n2, mmrDescription = this.getByRandom(), contains = vector.contains(mmrDescription)) {}
            vector.addElement(mmrDescription);
        }
        boolean contains2 = true;
        for (int n3 = 0; contains2 && n3 != 10; ++n3, mmrDescription = this.getByRandom(this.m_exitnodes), contains2 = vector.contains(mmrDescription)) {}
        vector.addElement(mmrDescription);
        return vector;
    }
    
    public synchronized Vector getByRandomWithFrag(final int n, final int n2) {
        final Vector<Vector<MMRDescription>> vector = new Vector<Vector<MMRDescription>>();
        MMRDescription byRandom = null;
        final MMRDescription byRandom2 = this.getByRandom(this.m_fragexitnodes);
        for (int i = 0; i < n2; ++i) {
            final Vector<MMRDescription> vector2 = new Vector<MMRDescription>();
            for (int j = 0; j < n - 1; ++j) {
                for (boolean contains = true; contains; contains = vector2.contains(byRandom)) {
                    byRandom = this.getByRandom();
                }
                vector2.addElement(byRandom);
            }
            vector2.addElement(byRandom2);
            vector.addElement(vector2);
        }
        return vector;
    }
    
    public synchronized MMRDescription getMMRDescription(final String s) {
        if (this.m_mixminionroutersWithNames.containsKey(s)) {
            return this.m_mixminionroutersWithNames.get(s);
        }
        return null;
    }
    
    private boolean parseDocument(final byte[] array) throws Exception {
        final Vector<MMRDescription> mixminionrouters = new Vector<MMRDescription>();
        final Vector exitnodes = new Vector<MMRDescription>();
        final Vector fragexitnodes = new Vector<MMRDescription>();
        final Hashtable mixminionroutersWithNames = new Hashtable<String, MMRDescription>();
        final LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(new ByteArrayInputStream(array)));
        final String line = lineNumberReader.readLine();
        final Vector whoIsDown = new ServerStats().getWhoIsDown();
        if (line == null) {
            return false;
        }
        while (true) {
            final String line2 = lineNumberReader.readLine();
            if (line2 == null) {
                break;
            }
            if (!line2.startsWith("[Server]")) {
                continue;
            }
            final MMRDescription parse = MMRDescription.parse(lineNumberReader);
            if (parse != null && !whoIsDown.contains(parse.getName())) {
                boolean b = true;
                if (mixminionroutersWithNames.containsKey(parse.getName())) {
                    b = false;
                }
                if (b) {
                    if (parse.isExitNode()) {
                        if (parse.allowsFragmented()) {
                            fragexitnodes.addElement(parse);
                        }
                        else {
                            exitnodes.addElement(parse);
                        }
                    }
                    mixminionrouters.addElement(parse);
                    mixminionroutersWithNames.put(parse.getName(), parse);
                }
            }
            LogHolder.log(7, LogType.MISC, "Added: " + parse);
        }
        this.m_exitnodes = exitnodes;
        this.m_fragexitnodes = fragexitnodes;
        LogHolder.log(7, LogType.MISC, "ExitNodes : " + exitnodes.size() + "Frag-Exit-Nodes:" + fragexitnodes.size());
        this.m_mixminionrouters = mixminionrouters;
        this.m_mixminionroutersWithNames = mixminionroutersWithNames;
        if (mixminionroutersWithNames.isEmpty()) {
            System.out.println("Infoservice geht nicht!");
            return false;
        }
        return true;
    }
    
    public void vectortostring(final Vector vector) {
        String string = "";
        for (int i = 0; i < vector.size(); ++i) {
            string = string + vector.elementAt(i).getName() + ",";
        }
        System.out.println(string);
    }
    
    public Vector mytesting() {
        final MMRDescription mmrDescription = new MMRDescription("localhost", "rinos", 48099, Base64.decode("nLrOnRowaQV/U/1XCUlXicIAIKc="), Base64.decode("MK2+xQEe59Zfwd+7nQ17PCgVBlg="), true, true, "egal", null);
        mmrDescription.setIdentityKey(Base64.decode("MIIBCgKCAQEAs6lIEY4Vz2skNL8SHJKkO5hvfernaBkhO/RnowiyFD/TaHQ1kdxYryaIu3dQ3M03eh+k5VoPiU/sX9+OfmHu0hB4vIqm5c5UtOkigSZOhEBDnZ31OgmfrK0+TaQHqNoF9lgT95QC6KXUgdpbhz2Qklg6qNxPWAbKLlewr6g0RBO51pFM/KK4IF9DMu8jQ8dssmWddPWZcdmQuY77njVr83OcPkpP/T8K+heVdkw7/jmlPAJ+wC2iCgkOtM5NJhk6+8NqOA57P5xXkrcEJkA6qRG9pvYYKsN4lor3asETT+X8mMOEuAkkwBTkRkhovqhQ1WPR0MAHTXUKP1wYAjkB4QIDAQAB"));
        mmrDescription.setPacketKey(Base64.decode("MIIBCgKCAQEA0SiCjybZ/+YsuHG9pgAIFNN0j+xF5ZPu3YI1F9MtgGkYQ7xfSrUJksbXprfo+QjJS5izTLkXQfFlUzViy0DMC7JHufofCh1o3lqryGnmE0S0XVD5Cvvz2OLMyRhINLmytp+CXx3E355EVmDebJNtqVRoZaPdZRnvQ2wkB5I6dhiAmhhzIAQVho4DQFf7+2Riv++1VP097TxAww/2gzdq7Pmv3PDd+TI2djAOMDMZO9ZjeZrCX+B7WGZxIBX/hISi9ck1AYq9ss1F4mAOHStgUFoD/iwcONh9OiLyGUhWdmZDrH4HwTutm8thTgt7l3w6LEnvi3Fg8YqeyAp2ocCMOwIDAQAB"));
        final Vector<MMRDescription> vector = new Vector<MMRDescription>();
        vector.addElement(mmrDescription);
        vector.addElement(mmrDescription);
        return vector;
    }
}
