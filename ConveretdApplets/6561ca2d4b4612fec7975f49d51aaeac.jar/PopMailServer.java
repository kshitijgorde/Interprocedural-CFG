import COM.jscape.mailwidgets.JSMessage;
import java.rmi.RemoteException;
import java.rmi.Remote;

// 
// Decompiled by Procyon v0.5.30
// 

public interface PopMailServer extends Remote
{
    String getErrorMessage() throws RemoteException;
    
    void cleanup(final String p0, final int[] p1, final int p2, final String p3) throws RemoteException;
    
    boolean authenticate(final String p0, final String p1) throws RemoteException;
    
    int getNumberOfMessages(final String p0) throws RemoteException;
    
    int getNumberOfNewMessages(final String p0) throws RemoteException;
    
    String[] getHeaderList(final String p0) throws RemoteException;
    
    String[] getHeaderList(final String p0, final int[] p1) throws RemoteException;
    
    JMessage getMessage(final String p0, final int p1, final String p2, final boolean p3) throws RemoteException;
    
    JMessage[] getMessages(final String p0, final int[] p1, final boolean p2) throws RemoteException;
    
    boolean writeAttachments(final String p0, final int p1, final String p2) throws RemoteException;
    
    void deleteAttachments(final String p0, final String p1) throws RemoteException;
    
    void rebuildMailbox(final String p0) throws RemoteException;
    
    String getHeader(final String p0, final int p1) throws RemoteException;
    
    String getAttachmentData(final String p0, final int p1, final int p2) throws RemoteException;
    
    void deleteMessage(final String p0, final int p1) throws RemoteException;
    
    String[] retrieveMessageIDs(final String p0) throws RemoteException;
    
    void saveHeaders(final String p0, final String[] p1, final String p2, final int p3, final String p4, final String p5) throws RemoteException;
    
    boolean verifyMessage(final String p0, final int p1, final String p2) throws RemoteException;
    
    String[] getCachedHeaders(final String p0) throws RemoteException;
    
    String[] getCachedHeaders(final String p0, final int[] p1) throws RemoteException;
    
    int getNumberCachedHeaders(final String p0) throws RemoteException;
    
    boolean confirm() throws RemoteException;
    
    void unbind() throws RemoteException;
    
    void close() throws RemoteException;
    
    void logout() throws RemoteException;
    
    String sendMail(final JSMessage p0, final String p1) throws RemoteException;
    
    String sendMail(final JSMessage p0, final String p1, final int p2, final int p3) throws RemoteException;
}
