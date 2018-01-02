// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connector;

import anon.transport.connection.IConnection;
import anon.transport.address.IAddress;
import anon.transport.connection.ConnectionException;
import com.skype.Stream;
import com.skype.Application;
import anon.transport.connection.IChunkConnection;
import anon.transport.connection.ChunkConnectionAdapter;
import anon.transport.connection.SkypeConnection;
import com.skype.SkypeException;
import anon.transport.connection.CommunicationException;
import com.skype.Skype;
import logging.LogHolder;
import logging.LogType;
import com.skype.connector.Connector;
import anon.transport.connection.IStreamConnection;
import anon.transport.address.SkypeAddress;

public class SkypeConnector implements IConnector
{
    public IStreamConnection connect(final SkypeAddress skypeAddress) throws ConnectionException {
        try {
            Connector.getInstance().setApplicationName(skypeAddress.getApplicationName());
            if (LogHolder.isLogged(7, LogType.TRANSPORT)) {
                Skype.setDebug(true);
            }
        }
        catch (Exception ex) {
            LogHolder.log(7, LogType.NET, "Skye Connector - exception");
        }
        LogHolder.log(7, LogType.NET, "Skye Connector - try to get user id");
        LogHolder.log(7, LogType.TRANSPORT, "Try to register Skype forwarding application");
        Application addApplication;
        try {
            addApplication = Skype.addApplication(skypeAddress.getApplicationName());
        }
        catch (SkypeException ex2) {
            throw new CommunicationException("Unable to create desired Skype Application " + skypeAddress.getApplicationName());
        }
        if (addApplication == null) {
            throw new CommunicationException("Unable to create desired Skype Application " + skypeAddress.getApplicationName());
        }
        LogHolder.log(7, LogType.TRANSPORT, "Try to get a stream from Skype");
        Stream[] connect;
        try {
            connect = addApplication.connect(skypeAddress.getUserID());
        }
        catch (SkypeException ex3) {
            throw new CommunicationException("Unable to connect to User with ID " + skypeAddress.getUserID());
        }
        if (connect == null || connect.length == 0) {
            throw new CommunicationException("Unable to connect to User with ID " + skypeAddress.getUserID());
        }
        LogHolder.log(7, LogType.TRANSPORT, "Setup the base Skype connection");
        return new ChunkConnectionAdapter(new SkypeConnection(connect[0]));
    }
    
    public IConnection connect(final IAddress address) throws ConnectionException {
        if (!(address instanceof SkypeAddress)) {
            throw new IllegalArgumentException("Connector can only handel Address of type SkypeAddress");
        }
        return this.connect((SkypeAddress)address);
    }
}
