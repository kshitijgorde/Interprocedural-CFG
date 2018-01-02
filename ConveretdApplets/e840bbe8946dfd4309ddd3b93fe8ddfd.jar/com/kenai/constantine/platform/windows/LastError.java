// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.windows;

import java.util.EnumMap;
import java.util.Map;
import com.kenai.constantine.Constant;

public enum LastError implements Constant
{
    ERROR_INFLOOP_IN_RELOC_CHAIN(202), 
    ERROR_IOPL_NOT_ENABLED(197), 
    ERROR_FILE_EXISTS(80), 
    ERROR_FAIL_I24(83), 
    ERROR_NEGATIVE_SEEK(131), 
    WSAEACCES(10013), 
    ERROR_NESTING_NOT_ALLOWED(215), 
    ERROR_FILENAME_EXCED_RANGE(206), 
    ERROR_BAD_PATHNAME(161), 
    ERROR_ACCESS_DENIED(5), 
    ERROR_SHARING_BUFFER_EXCEEDED(36), 
    ERROR_NO_PROC_SLOTS(89), 
    ERROR_EXE_MARKED_INVALID(192), 
    ERROR_INVALID_BLOCK(9), 
    ERROR_ITERATED_DATA_EXCEEDS_64k(194), 
    WSAEBADF(10009), 
    ERROR_RING2SEG_MUST_BE_MOVABLE(200), 
    ERROR_LOCK_VIOLATION(33), 
    ERROR_INVALID_DATA(13), 
    ERROR_BAD_COMMAND(22), 
    ERROR_MAX_THRDS_REACHED(164), 
    ERROR_NO_MORE_FILES(18), 
    ERROR_FILE_NOT_FOUND(2), 
    ERROR_CRC(23), 
    ERROR_READ_FAULT(30), 
    ERROR_LOCK_FAILED(167), 
    ERROR_TOO_MANY_OPEN_FILES(4), 
    ERROR_INVALID_ACCESS(12), 
    ERROR_CHILD_NOT_COMPLETE(129), 
    ERROR_RELOC_CHAIN_XEEDS_SEGLIM(201), 
    ERROR_INVALID_SEGDPL(198), 
    ERROR_BAD_FORMAT(11), 
    ERROR_NOT_DOS_DISK(26), 
    ERROR_GEN_FAILURE(31), 
    ERROR_WRONG_DISK(34), 
    ERROR_SEEK_ON_DEVICE(132), 
    ERROR_SEEK(25), 
    ERROR_WRITE_PROTECT(19), 
    ERROR_BAD_LENGTH(24), 
    ERROR_DYNLINK_FROM_INVALID_RING(196), 
    ERROR_INVALID_HANDLE(6), 
    ERROR_INVALID_DRIVE(15), 
    ERROR_DRIVE_LOCKED(108), 
    ERROR_NOT_SAME_DEVICE(17), 
    ERROR_BAD_NET_NAME(67), 
    ERROR_BAD_EXE_FORMAT(193), 
    ERROR_NO_DATA(232), 
    ERROR_BAD_PIPE(230), 
    ERROR_NETWORK_ACCESS_DENIED(65), 
    ERROR_OUT_OF_PAPER(28), 
    ERROR_NOT_READY(21), 
    ERROR_BAD_UNIT(20), 
    ERROR_BROKEN_PIPE(109), 
    ERROR_SHARING_VIOLATION(32), 
    ERROR_ALREADY_EXISTS(183), 
    ERROR_INVALID_STACKSEG(189), 
    ERROR_INVALID_PARAMETER(87), 
    WSAEFAULT(10014), 
    ERROR_PIPE_BUSY(231), 
    WSAEMFILE(10024), 
    ERROR_MOD_NOT_FOUND(126), 
    ERROR_NOT_ENOUGH_QUOTA(1816), 
    ERROR_PIPE_NOT_CONNECTED(233), 
    ERROR_PATH_NOT_FOUND(3), 
    WSAEINTR(10004), 
    ERROR_DIRECT_ACCESS_HANDLE(130), 
    ERROR_INVALID_TARGET_HANDLE(114), 
    ERROR_INVALID_MINALLOCSIZE(195), 
    ERROR_WRITE_FAULT(29), 
    ERROR_CURRENT_DIRECTORY(16), 
    ERROR_NOT_ENOUGH_MEMORY(8), 
    ERROR_INVALID_EXE_SIGNATURE(191), 
    ERROR_DIRECTORY(267), 
    ERROR_INVALID_MODULETYPE(190), 
    WSAENAMETOOLONG(10063), 
    ERROR_OPERATION_ABORTED(995), 
    ERROR_INVALID_STARTING_CODESEG(188), 
    ERROR_SECTOR_NOT_FOUND(27), 
    ERROR_DISK_FULL(112), 
    ERROR_DIR_NOT_EMPTY(145), 
    WSAEINVAL(10022), 
    ERROR_AUTODATASEG_EXCEEDS_64k(199), 
    ERROR_BAD_ENVIRONMENT(10), 
    ERROR_WAIT_NO_CHILDREN(128), 
    ERROR_NOT_LOCKED(158), 
    ERROR_CANNOT_MAKE(82), 
    WSAENOTEMPTY(10066), 
    ERROR_ARENA_TRASHED(7), 
    ERROR_BAD_NETPATH(53), 
    ERROR_INVALID_FUNCTION(1);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 10066L;
    
    private LastError(final int value) {
        this.value = value;
    }
    
    public final String toString() {
        return StringTable.descriptions.get(this);
    }
    
    public final int value() {
        return this.value;
    }
    
    static final class StringTable
    {
        public static final Map<LastError, String> descriptions;
        
        public static final Map<LastError, String> generateTable() {
            final Map<LastError, String> map = new EnumMap<LastError, String>(LastError.class);
            map.put(LastError.ERROR_INFLOOP_IN_RELOC_CHAIN, "ERROR_INFLOOP_IN_RELOC_CHAIN");
            map.put(LastError.ERROR_IOPL_NOT_ENABLED, "The operating system is not presently configured to run this application");
            map.put(LastError.ERROR_FILE_EXISTS, "The file exists");
            map.put(LastError.ERROR_FAIL_I24, "Fail on INT 24");
            map.put(LastError.ERROR_NEGATIVE_SEEK, "An attempt was made to move the file pointer before the beginning of the file");
            map.put(LastError.WSAEACCES, "An attempt was made to access a socket in a way forbidden by its access permissions");
            map.put(LastError.ERROR_NESTING_NOT_ALLOWED, "Cannot nest calls to LoadModule");
            map.put(LastError.ERROR_FILENAME_EXCED_RANGE, "The filename or extension is too long");
            map.put(LastError.ERROR_BAD_PATHNAME, "The specified path is invalid");
            map.put(LastError.ERROR_ACCESS_DENIED, "Access is denied");
            map.put(LastError.ERROR_SHARING_BUFFER_EXCEEDED, "Too many files opened for sharing");
            map.put(LastError.ERROR_NO_PROC_SLOTS, "The system cannot start another process at this time");
            map.put(LastError.ERROR_EXE_MARKED_INVALID, "ERROR_EXE_MARKED_INVALID");
            map.put(LastError.ERROR_INVALID_BLOCK, "The storage control block address is invalid");
            map.put(LastError.ERROR_ITERATED_DATA_EXCEEDS_64k, "ERROR_ITERATED_DATA_EXCEEDS_64k");
            map.put(LastError.WSAEBADF, "The file handle supplied is not valid");
            map.put(LastError.ERROR_RING2SEG_MUST_BE_MOVABLE, "The code segment cannot be greater than or equal to 64K");
            map.put(LastError.ERROR_LOCK_VIOLATION, "The process cannot access the file because another process has locked a portion of the file");
            map.put(LastError.ERROR_INVALID_DATA, "The data is invalid");
            map.put(LastError.ERROR_BAD_COMMAND, "The device does not recognize the command");
            map.put(LastError.ERROR_MAX_THRDS_REACHED, "No more threads can be created in the system");
            map.put(LastError.ERROR_NO_MORE_FILES, "There are no more files");
            map.put(LastError.ERROR_FILE_NOT_FOUND, "The system cannot find the file specified");
            map.put(LastError.ERROR_CRC, "Data error (cyclic redundancy check)");
            map.put(LastError.ERROR_READ_FAULT, "The system cannot read from the specified device");
            map.put(LastError.ERROR_LOCK_FAILED, "Unable to lock a region of a file");
            map.put(LastError.ERROR_TOO_MANY_OPEN_FILES, "The system cannot open the file");
            map.put(LastError.ERROR_INVALID_ACCESS, "The access code is invalid");
            map.put(LastError.ERROR_CHILD_NOT_COMPLETE, "ERROR_CHILD_NOT_COMPLETE");
            map.put(LastError.ERROR_RELOC_CHAIN_XEEDS_SEGLIM, "ERROR_RELOC_CHAIN_XEEDS_SEGLIM");
            map.put(LastError.ERROR_INVALID_SEGDPL, "ERROR_INVALID_SEGDPL");
            map.put(LastError.ERROR_BAD_FORMAT, "An attempt was made to load a program with an incorrect format");
            map.put(LastError.ERROR_NOT_DOS_DISK, "The specified disk or diskette cannot be accessed");
            map.put(LastError.ERROR_GEN_FAILURE, "A device attached to the system is not functioning");
            map.put(LastError.ERROR_WRONG_DISK, "ERROR_WRONG_DISK");
            map.put(LastError.ERROR_SEEK_ON_DEVICE, "The file pointer cannot be set on the specified device or file");
            map.put(LastError.ERROR_SEEK, "The drive cannot locate a specific area or track on the disk");
            map.put(LastError.ERROR_WRITE_PROTECT, "The media is write protected");
            map.put(LastError.ERROR_BAD_LENGTH, "The program issued a command but the command length is incorrect");
            map.put(LastError.ERROR_DYNLINK_FROM_INVALID_RING, "The operating system cannot run this application program");
            map.put(LastError.ERROR_INVALID_HANDLE, "The handle is invalid");
            map.put(LastError.ERROR_INVALID_DRIVE, "The system cannot find the drive specified");
            map.put(LastError.ERROR_DRIVE_LOCKED, "The disk is in use or locked by another process");
            map.put(LastError.ERROR_NOT_SAME_DEVICE, "The system cannot move the file to a different disk drive");
            map.put(LastError.ERROR_BAD_NET_NAME, "The network name cannot be found");
            map.put(LastError.ERROR_BAD_EXE_FORMAT, "ERROR_BAD_EXE_FORMAT");
            map.put(LastError.ERROR_NO_DATA, "The pipe is being closed");
            map.put(LastError.ERROR_BAD_PIPE, "The pipe state is invalid");
            map.put(LastError.ERROR_NETWORK_ACCESS_DENIED, "Network access is denied");
            map.put(LastError.ERROR_OUT_OF_PAPER, "The printer is out of paper");
            map.put(LastError.ERROR_NOT_READY, "The device is not ready");
            map.put(LastError.ERROR_BAD_UNIT, "The system cannot find the device specified");
            map.put(LastError.ERROR_BROKEN_PIPE, "The pipe has been ended");
            map.put(LastError.ERROR_SHARING_VIOLATION, "The process cannot access the file because it is being used by another process");
            map.put(LastError.ERROR_ALREADY_EXISTS, "Cannot create a file when that file already exists");
            map.put(LastError.ERROR_INVALID_STACKSEG, "ERROR_INVALID_STACKSEG");
            map.put(LastError.ERROR_INVALID_PARAMETER, "The parameter is incorrect");
            map.put(LastError.WSAEFAULT, "The system detected an invalid pointer address in attempting to use a pointer argument in a call");
            map.put(LastError.ERROR_PIPE_BUSY, "All pipe instances are busy");
            map.put(LastError.WSAEMFILE, "Too many open sockets");
            map.put(LastError.ERROR_MOD_NOT_FOUND, "The specified module could not be found");
            map.put(LastError.ERROR_NOT_ENOUGH_QUOTA, "Not enough quota is available to process this command");
            map.put(LastError.ERROR_PIPE_NOT_CONNECTED, "No process is on the other end of the pipe");
            map.put(LastError.ERROR_PATH_NOT_FOUND, "The system cannot find the path specified");
            map.put(LastError.WSAEINTR, "A blocking operation was interrupted by a call to WSACancelBlockingCall");
            map.put(LastError.ERROR_DIRECT_ACCESS_HANDLE, "Attempt to use a file handle to an open disk partition for an operation other than raw disk I/O");
            map.put(LastError.ERROR_INVALID_TARGET_HANDLE, "The target internal file identifier is incorrect");
            map.put(LastError.ERROR_INVALID_MINALLOCSIZE, "ERROR_INVALID_MINALLOCSIZE");
            map.put(LastError.ERROR_WRITE_FAULT, "The system cannot write to the specified device");
            map.put(LastError.ERROR_CURRENT_DIRECTORY, "The directory cannot be removed");
            map.put(LastError.ERROR_NOT_ENOUGH_MEMORY, "Not enough storage is available to process this command");
            map.put(LastError.ERROR_INVALID_EXE_SIGNATURE, "ERROR_INVALID_EXE_SIGNATURE");
            map.put(LastError.ERROR_DIRECTORY, "The directory name is invalid");
            map.put(LastError.ERROR_INVALID_MODULETYPE, "ERROR_INVALID_MODULETYPE");
            map.put(LastError.WSAENAMETOOLONG, "Name component or name was too long");
            map.put(LastError.ERROR_OPERATION_ABORTED, "The I/O operation has been aborted because of either a thread exit or an application request");
            map.put(LastError.ERROR_INVALID_STARTING_CODESEG, "ERROR_INVALID_STARTING_CODESEG");
            map.put(LastError.ERROR_SECTOR_NOT_FOUND, "The drive cannot find the sector requested");
            map.put(LastError.ERROR_DISK_FULL, "There is not enough space on the disk");
            map.put(LastError.ERROR_DIR_NOT_EMPTY, "The directory is not empty");
            map.put(LastError.WSAEINVAL, "An invalid argument was supplied");
            map.put(LastError.ERROR_AUTODATASEG_EXCEEDS_64k, "The operating system cannot run this application program");
            map.put(LastError.ERROR_BAD_ENVIRONMENT, "The environment is incorrect");
            map.put(LastError.ERROR_WAIT_NO_CHILDREN, "There are no child processes to wait for");
            map.put(LastError.ERROR_NOT_LOCKED, "The segment is already unlocked");
            map.put(LastError.ERROR_CANNOT_MAKE, "The directory or file cannot be created");
            map.put(LastError.WSAENOTEMPTY, "Cannot remove a directory that is not empty");
            map.put(LastError.ERROR_ARENA_TRASHED, "The storage control blocks were destroyed");
            map.put(LastError.ERROR_BAD_NETPATH, "The network path was not found");
            map.put(LastError.ERROR_INVALID_FUNCTION, "Incorrect function");
            return map;
        }
        
        static {
            descriptions = generateTable();
        }
    }
}
