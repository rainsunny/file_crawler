package crawler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * User: wangjie
 * Date: 14-2-23
 */
public class Indexer implements Runnable {

    private final BlockingQueue<File> queue;
//    private final File indexFile;

    private final Logger logger = LoggerFactory.getLogger(Indexer.class);

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true)
                indexFile(queue.take());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void indexFile(File entry) throws IOException {
        if (entry == null || entry.isDirectory()) return;

        logger.info(entry.getCanonicalPath());
    }

}
