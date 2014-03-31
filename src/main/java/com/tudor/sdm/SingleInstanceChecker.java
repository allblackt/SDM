package com.tudor.sdm;

/**
 * Created by tudor on 3/30/14.
 * Thanks http://www.rgagnon.com/javadetails/java-0288.html
 */

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/**
 * Class used to create and check for a runtime lock represented by a file. This is used to limit the
 * number of running application instances to one. The file is .lock and it is created in the runtime directory.
 */
public class SingleInstanceChecker {
    private File file;
    private FileChannel channel;
    private FileLock lock;

    public SingleInstanceChecker() { }

    public boolean isAppActive() {
        try {
            file = new File (System.getProperty("user.dir"), ".lock");
            channel = new RandomAccessFile(file, "rw").getChannel();

            try {
                lock = channel.tryLock();
            } catch (OverlappingFileLockException e) {
                // already locked
                closeLock();
                return true;
            }

            if (lock == null) {
                closeLock();
                return true;
            }

            Runtime.getRuntime().addShutdownHook(new Thread() {
                // destroy the lock when the JVM is closing
                public void run() {
                    closeLock();
                    deleteFile();
                }
            });
            return false;
        }
        catch (Exception e) {
            closeLock();
            return true;
        }
    }

    private void closeLock() {
        try {
            lock.release();
        }
        catch (Exception e) {  }
        try {
            channel.close();
        }
        catch (Exception e) {  }
    }

    private void deleteFile() {
        try {
            file.delete();
        }
        catch (Exception e) { }
    }
}
