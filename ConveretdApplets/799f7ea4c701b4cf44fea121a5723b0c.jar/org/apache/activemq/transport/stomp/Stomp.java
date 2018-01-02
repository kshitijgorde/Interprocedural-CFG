// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.stomp;

public interface Stomp
{
    public static final String NULL = "\u0000";
    public static final String NEWLINE = "\n";
    
    public enum Transformations
    {
        JMS_BYTE, 
        JMS_XML, 
        JMS_JSON, 
        JMS_OBJECT_XML, 
        JMS_OBJECT_JSON, 
        JMS_MAP_XML, 
        JMS_MAP_JSON, 
        JMS_ADVISORY_XML, 
        JMS_ADVISORY_JSON;
        
        @Override
        public String toString() {
            return this.name().replaceAll("_", "-").toLowerCase();
        }
        
        public static Transformations getValue(final String value) {
            return valueOf(value.replaceAll("-", "_").toUpperCase());
        }
    }
    
    public interface Headers
    {
        public static final String SEPERATOR = ":";
        public static final String RECEIPT_REQUESTED = "receipt";
        public static final String TRANSACTION = "transaction";
        public static final String CONTENT_LENGTH = "content-length";
        public static final String TRANSFORMATION = "transformation";
        public static final String TRANSFORMATION_ERROR = "transformation-error";
        public static final String AMQ_MESSAGE_TYPE = "amq-msg-type";
        
        public interface Ack
        {
            public static final String MESSAGE_ID = "message-id";
        }
        
        public interface Connected
        {
            public static final String SESSION = "session";
            public static final String RESPONSE_ID = "response-id";
        }
        
        public interface Error
        {
            public static final String MESSAGE = "message";
        }
        
        public interface Connect
        {
            public static final String LOGIN = "login";
            public static final String PASSCODE = "passcode";
            public static final String CLIENT_ID = "client-id";
            public static final String REQUEST_ID = "request-id";
        }
        
        public interface Unsubscribe
        {
            public static final String DESTINATION = "destination";
            public static final String ID = "id";
        }
        
        public interface Subscribe
        {
            public static final String DESTINATION = "destination";
            public static final String ACK_MODE = "ack";
            public static final String ID = "id";
            public static final String SELECTOR = "selector";
            
            public interface AckModeValues
            {
                public static final String AUTO = "auto";
                public static final String CLIENT = "client";
                public static final String INDIVIDUAL = "client-individual";
            }
        }
        
        public interface Message
        {
            public static final String MESSAGE_ID = "message-id";
            public static final String DESTINATION = "destination";
            public static final String CORRELATION_ID = "correlation-id";
            public static final String EXPIRATION_TIME = "expires";
            public static final String REPLY_TO = "reply-to";
            public static final String PRORITY = "priority";
            public static final String REDELIVERED = "redelivered";
            public static final String TIMESTAMP = "timestamp";
            public static final String TYPE = "type";
            public static final String SUBSCRIPTION = "subscription";
            public static final String USERID = "JMSXUserID";
            public static final String ORIGINAL_DESTINATION = "original-destination";
        }
        
        public interface Send
        {
            public static final String DESTINATION = "destination";
            public static final String CORRELATION_ID = "correlation-id";
            public static final String REPLY_TO = "reply-to";
            public static final String EXPIRATION_TIME = "expires";
            public static final String PRIORITY = "priority";
            public static final String TYPE = "type";
            public static final String PERSISTENT = "persistent";
        }
        
        public interface Response
        {
            public static final String RECEIPT_ID = "receipt-id";
        }
    }
    
    public interface Responses
    {
        public static final String CONNECTED = "CONNECTED";
        public static final String ERROR = "ERROR";
        public static final String MESSAGE = "MESSAGE";
        public static final String RECEIPT = "RECEIPT";
    }
    
    public interface Commands
    {
        public static final String CONNECT = "CONNECT";
        public static final String SEND = "SEND";
        public static final String DISCONNECT = "DISCONNECT";
        public static final String SUBSCRIBE = "SUB";
        public static final String UNSUBSCRIBE = "UNSUB";
        public static final String BEGIN_TRANSACTION = "BEGIN";
        public static final String COMMIT_TRANSACTION = "COMMIT";
        public static final String ABORT_TRANSACTION = "ABORT";
        public static final String BEGIN = "BEGIN";
        public static final String COMMIT = "COMMIT";
        public static final String ABORT = "ABORT";
        public static final String ACK = "ACK";
    }
}
