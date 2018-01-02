// 
// Decompiled by Procyon v0.5.30
// 

package com;

import irc.EIRC;
import java.text.DecimalFormat;
import com.vladium.utils.SystemInformation;
import com.vladium.utils.CPUUsageThread;

public class CPUCalculation implements CPUUsageThread.IUsageEventListener
{
    private SystemInformation.CPUUsageSnapshot m_prevSnapshot;
    double count;
    double val;
    
    public CPUCalculation() {
        this.count = 0.0;
        this.val = 0.0;
    }
    
    public static void StartCPUCalculation() {
        final CPUUsageThread cpuThreadUsageThread = CPUUsageThread.getCPUThreadUsageThread();
        cpuThreadUsageThread.addUsageEventListener(new CPUCalculation());
        cpuThreadUsageThread.start();
    }
    
    @Override
    public void accept(final SystemInformation.CPUUsageSnapshot prevSnapshot) {
        final DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(0);
        if (this.m_prevSnapshot != null) {
            EIRC.cpuinst = decimalFormat.format(100.0 * SystemInformation.getProcessCPUUsage(this.m_prevSnapshot, prevSnapshot));
            ++this.count;
            this.val += 100.0 * SystemInformation.getProcessCPUUsage(this.m_prevSnapshot, prevSnapshot);
            EIRC.cpumoyen = decimalFormat.format(this.val / this.count);
            if (this.count > 9.99999999E8) {
                this.val = 0.0;
                this.count = 0.0;
            }
        }
        this.m_prevSnapshot = prevSnapshot;
    }
}
