import COM.jscape.mailwidgets.JSMessage;
import java.util.Hashtable;
import java.rmi.RemoteException;
import java.rmi.Remote;

// 
// Decompiled by Procyon v0.5.30
// 

public interface ImapMailServer extends Remote
{
    String getErrorMessage() throws RemoteException;
    
    void cleanup(final String p0, final int[] p1, final int p2, final String p3) throws RemoteException;
    
    int getNumberOfMessages(final String p0) throws RemoteException;
    
    int getNumberOfNewMessages(final String p0) throws RemoteException;
    
    JMessage getMessage(final String p0, final int p1, final String p2, final boolean p3) throws RemoteException;
    
    JMessage[] getMessages(final String p0, final int[] p1, final boolean p2) throws RemoteException;
    
    boolean writeAttachments(final String p0, final int p1, final String p2) throws RemoteException;
    
    void deleteAttachments(final String p0, final String p1) throws RemoteException;
    
    String getHeader(final String p0, final int p1) throws RemoteException;
    
    String getAttachmentData(final String p0, final int p1, final int p2) throws RemoteException;
    
    String[] retrieveMessageIDs(final String p0) throws RemoteException;
    
    boolean verifyMessage(final String p0, final int p1, final String p2) throws RemoteException;
    
    boolean confirm() throws RemoteException;
    
    void unbind() throws RemoteException;
    
    String[] getMailboxes() throws RemoteException;
    
    int select(final String p0) throws RemoteException;
    
    int create(final String p0) throws RemoteException;
    
    int delete(final String p0) throws RemoteException;
    
    int rename(final String p0, final String p1) throws RemoteException;
    
    int deleteMessage(final int p0, final String p1) throws RemoteException;
    
    int deleteMessage(final int p0) throws RemoteException;
    
    String undeleteMessage(final int p0) throws RemoteException;
    
    String[] getCurrentHeaders(final String p0) throws RemoteException;
    
    void copy(final int p0, final String p1) throws RemoteException;
    
    void setMessageStatus(final int p0, final String p1) throws RemoteException;
    
    int saveMessage(final String p0, final Hashtable p1, final String p2, final boolean p3) throws RemoteException;
    
    void emptyTrash() throws RemoteException;
    
    void close() throws RemoteException;
    
    String getRoot() throws RemoteException;
    
    String sendMail(final JSMessage p0, final String p1) throws RemoteException;
    
    String sendMail(final JSMessage p0, final String p1, final int p2, final int p3) throws RemoteException;
}
