// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node;

import java.lang.reflect.InvocationTargetException;
import com.pluraprocessing.common.xml.RequestedWorkParser;
import java.util.HashMap;
import com.pluraprocessing.common.utility.PluraHttpConnectionUtility;
import com.pluraprocessing.common.utility.HttpMethod;
import java.util.Random;
import com.pluraprocessing.node.exception.PluraIOException;
import com.pluraprocessing.common.domain.NodeType;
import com.pluraprocessing.common.domain.RequestedWork;
import java.io.IOException;

public class StartPluraThread extends Thread
{
    public Thread startPluraThread;
    
    public StartPluraThread() {
        this.startPluraThread = null;
        this.startPluraThread = new Thread(this);
    }
    
    public void stopThread() {
        this.startPluraThread = null;
    }
    
    @Override
    public void run() {
        try {
            boolean finished = true;
            do {
                try {
                    String currentVersion = null;
                    System.out.println("");
                    Label_0239: {
                        if (PluraRuntime.getInstance().getApplet() == null && this.canDoWUs()) {
                            PluraRuntime.print("StartPluraThread canDoWUs", false);
                            if (PluraRuntime.getInstance().getPluraServerName() == null || PluraRuntime.getInstance().getPluraServerName().equals("") || PluraRuntime.getInstance().getPluraServerName().equals("null")) {
                                PluraRuntime.getInstance().setPluraServerName(PluraRuntime.getInstance().getPluraServer());
                            }
                            currentVersion = PluraRuntime.getInstance().getCurrentVersionId();
                            if (PluraRuntime.ENVIRONMENT.equals(Environment.SANDBOX)) {
                                this.doTimeSensitiveWork();
                                this.getSJAVAVersion();
                            }
                            else {
                                if (this.canByPassBandwidthCheck()) {
                                    try {
                                        this.getSJAVAVersion();
                                        break Label_0239;
                                    }
                                    catch (IOException e3) {
                                        if (!this.getJAVAVersion()) {
                                            break;
                                        }
                                        break Label_0239;
                                    }
                                }
                                if (PluraRuntime.getInstance().getBandwidthPercent() <= 0.0) {
                                    PluraRuntime.print("StartPluraThread no bw start", false);
                                    this.doTimeSensitiveWork();
                                    if (!this.getJAVAVersion()) {
                                        break;
                                    }
                                }
                                else {
                                    PluraRuntime.print("StartPluraThread potential bw start", false);
                                    final double bandwidthLeft = PluraRuntime.getInstance().getAvailableBandwidth();
                                    Label_0219: {
                                        if (bandwidthLeft <= 0.0) {
                                            if (bandwidthLeft != -1.0) {
                                                break Label_0219;
                                            }
                                        }
                                        try {
                                            this.doTimeSensitiveWork();
                                            this.getSJAVAVersion();
                                            break Label_0239;
                                        }
                                        catch (IOException e4) {
                                            if (!this.getJAVAVersion()) {
                                                break;
                                            }
                                            break Label_0239;
                                        }
                                    }
                                    PluraRuntime.print("StartPluraThread bw < 0 ", false);
                                    this.doTimeSensitiveWork();
                                    if (!this.getJAVAVersion()) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (PluraRuntime.getInstance().getCurrentVersionId() != null && (currentVersion == null || (currentVersion != null && !currentVersion.equals(PluraRuntime.getInstance().getCurrentVersionId())))) {
                        this.startPlura(null);
                    }
                    finished = true;
                }
                catch (IOException e) {
                    PluraRuntime.getInstance().logNoWork();
                    PluraRuntime.print("StartPluraThread IOException=" + e.getMessage(), false);
                    finished = this.error();
                }
                catch (Throwable e2) {
                    PluraRuntime.getInstance().resetAllCurrentPluraVersions(true);
                    PluraRuntime.print("StartPluraThread Throwable=" + e2.getMessage(), false);
                    finished = this.error();
                }
                if (this.startPluraThread == null) {
                    break;
                }
            } while (!finished);
        }
        catch (Throwable t) {}
    }
    
    private boolean error() {
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException ex) {}
        return false;
    }
    
    private boolean getJAVAVersion() throws IOException, PluraIOException {
        if (this.canDoFakeWUs()) {
            PluraRuntime.getInstance().getVersion(NodeType.JAVA, null);
            return true;
        }
        return false;
    }
    
    private void getSJAVAVersion() throws IOException, PluraIOException {
        PluraRuntime.getInstance().getVersion(NodeType.SJAVA, null);
    }
    
    private boolean canDoFakeWUs() {
        return PluraRuntime.getInstance().getAffiliateId().equals("bff6f3f0-fceb-4245-bd38-a8c88f63df0c") || PluraRuntime.getInstance().getAffiliateId().equals("a1653b46-efe9-ac95-d977-121844725f45") || PluraRuntime.getInstance().getAffiliateId().equals("ae9da222-577f-574d-144e-f70686e53e8b");
    }
    
    private boolean canDoWUs() {
        return !PluraRuntime.ENVIRONMENT.equals(Environment.LIVE) || this.canDoFakeWUs() || PluraRuntime.getInstance().getAffiliateId().equals("07c82d6c-e947-6dfc-1cfc-4ae85774b983") || PluraRuntime.getInstance().getAffiliateId().equals("141b15be-a50d-d46f-393c-1ce215533a65") || PluraRuntime.getInstance().getAffiliateId().equals("0f139bdd-c8e5-e9a5-f08f-25b9f2cfbf1a") || PluraRuntime.getInstance().getAffiliateId().equals("0970d19d-10ea-818c-8b75-acc206f64a46") || PluraRuntime.getInstance().getAffiliateId().equals("3a733410-b8c3-a668-c78f-7f99e519fe12") || PluraRuntime.getInstance().getAffiliateId().equals("c7b2645b-8f9d-b117-4ccc-37a452ba2860") || PluraRuntime.getInstance().getAffiliateId().equals("7791931a-f95f-280b-bbcf-35be0cdbdf51") || PluraRuntime.getInstance().getAffiliateId().equals("6943fd35-edec-8c3b-9c33-ba3340dc23d7") || PluraRuntime.getInstance().getAffiliateId().equals("d2a305ad-f309-5f15-3a1f-27611265c0db") || PluraRuntime.getInstance().getAffiliateId().equals("91231a74-a941-75da-eace-19435be1d5fe") || PluraRuntime.getInstance().getAffiliateId().equals("875a030b-0e6d-0ffe-3c7c-398690945b30") || PluraRuntime.getInstance().getAffiliateId().equals("fd23907d-d994-51c7-b66d-93ee88d91920") || PluraRuntime.getInstance().getAffiliateId().equals("7d76732e-c904-c5c3-4d28-0c87228c7185") || PluraRuntime.getInstance().getAffiliateId().equals("903d315d-4cae-4e1b-0d01-4b01d421167a") || PluraRuntime.getInstance().getAffiliateId().equals("8197f5a2-98f1-c063-d3eb-53a14ae51f3f") || PluraRuntime.getInstance().getAffiliateId().equals("e2ab91b6-b9a3-fd70-61c8-9967ac2ebf10") || PluraRuntime.getInstance().getAffiliateId().equals("408260d2-a650-769d-dd3f-c6aa8fda5e7f") || PluraRuntime.getInstance().getAffiliateId().equals("d413d2f1-1a26-36f6-e6a5-957b13d02cd8") || PluraRuntime.getInstance().getAffiliateId().equals("dbda8293-d28e-4a5b-a54e-5036189d8a7a") || PluraRuntime.getInstance().getAffiliateId().equals("fff6ab72-0aca-783a-e2b4-de38877ba267") || PluraRuntime.getInstance().getAffiliateId().equals("9d04d5d0-4ff6-250b-ef87-9ffac30f11eb") || PluraRuntime.getInstance().getAffiliateId().equals("2bc7f5df-2ef4-330e-d2fb-840a676a7f9b") || PluraRuntime.getInstance().getAffiliateId().equals("911d34a0-3d9f-a3fb-07c7-937ac0e29f94") || PluraRuntime.getInstance().getAffiliateId().equals("48f74861-37ca-415d-375a-c236b9cd80c8") || PluraRuntime.getInstance().getAffiliateId().equals("ea5f2464-2f59-3bcd-eb21-c50657437850");
    }
    
    private boolean canByPassBandwidthCheck() {
        return !PluraRuntime.ENVIRONMENT.equals(Environment.LIVE) || PluraRuntime.getInstance().getAffiliateId().equals("8197f5a2-98f1-c063-d3eb-53a14ae51f3f") || PluraRuntime.getInstance().getAffiliateId().equals("903d315d-4cae-4e1b-0d01-4b01d421167a") || PluraRuntime.getInstance().getAffiliateId().equals("e2ab91b6-b9a3-fd70-61c8-9967ac2ebf10");
    }
    
    private void doTimeSensitiveWork() throws IOException, PluraIOException, IllegalArgumentException, InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
        if (PluraRuntime.getInstance().getNumberTimesSkippedTimeSensitiveWUs() >= PluraRuntime.NUMBER_TIMES_SKIP_TS_WUS) {
            PluraRuntime.getInstance().setNumberTimesSkippedTimeSensitiveWUs(0);
            final String pluraSever = PluraRuntime.getInstance().getPluraServer();
            try {
                final String server = PluraRuntime.getInstance().getNodeServers().keySet().toArray(new String[0])[new Random().nextInt(PluraRuntime.getInstance().getNodeServers().size())];
                PluraRuntime.getInstance().setPluraServerName(server);
                PluraRuntime.print("TS WORK " + server, false);
                String strResp = "1";
                while (strResp.equals("1")) {
                    strResp = PluraHttpConnectionUtility.httpRequestByIP(PluraRuntime.getInstance().getPluraServerName(), 80, PluraRuntime.getInstance().PLURA_TIME_SENSITIVE_WUS_LOCATION, "", null, HttpMethod.GET).getResponseBodyAsTrimmedUTF8String();
                    try {
                        Thread.sleep(5000L);
                    }
                    catch (InterruptedException ex) {}
                }
                if (!strResp.equals("0")) {
                    int i = 0;
                    final String[] rws = strResp.split("\r\n");
                    while (i < rws.length) {
                        final String version = rws[i];
                        ++i;
                        final String rw = rws[i];
                        ++i;
                        PluraRuntime.getInstance().resetAllCurrentPluraVersions(false);
                        PluraRuntime.getInstance().getVersion(null, version);
                        final RequestedWork work = RequestedWorkParser.getRequestedObject(rw);
                        final IStartPlura plura = this.startPlura(work);
                        do {
                            try {
                                Thread.sleep(1500L);
                            }
                            catch (InterruptedException ex2) {}
                        } while (!plura.pluraIsStopped());
                    }
                    PluraRuntime.getInstance().resetAllCurrentPluraVersions(false);
                }
            }
            finally {
                PluraRuntime.getInstance().setPluraServerName(pluraSever);
            }
            PluraRuntime.getInstance().setPluraServerName(pluraSever);
        }
        else {
            PluraRuntime.getInstance().setNumberTimesSkippedTimeSensitiveWUs(PluraRuntime.getInstance().getNumberTimesSkippedTimeSensitiveWUs() + 1);
        }
    }
    
    private IStartPlura startPlura(final RequestedWork work) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        PluraRuntime.getInstance().setNumberTimesNoWork(0);
        PluraRuntime.getInstance().setStopped(false);
        IStartPlura startPlura = PluraRuntime.getInstance().getPlura();
        if (startPlura == null && PluraRuntime.getInstance().getCurrentClassLoader() != null) {
            startPlura = (IStartPlura)PluraRuntime.getInstance().getCurrentClassLoader().loadClass("com.pluraprocessing.node.StartPlura").newInstance();
            PluraRuntime.getInstance().setPlura(startPlura);
        }
        else if (startPlura == null) {
            startPlura = new StartPlura();
            PluraRuntime.getInstance().setPlura(startPlura);
        }
        startPlura.run(work);
        return startPlura;
    }
}
