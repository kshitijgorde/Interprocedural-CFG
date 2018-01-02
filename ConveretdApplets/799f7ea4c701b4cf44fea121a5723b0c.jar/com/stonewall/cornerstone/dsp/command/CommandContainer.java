// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.command;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class CommandContainer
{
    private List<DeviceCommand> commands;
    
    public CommandContainer() {
        this.commands = new ArrayList<DeviceCommand>();
    }
    
    public void addCommands(final List<DeviceCommand> commands) {
        this.commands.addAll(commands);
    }
    
    public List<DeviceCommand> getCommands() {
        return this.commands;
    }
}
