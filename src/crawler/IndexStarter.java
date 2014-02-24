package crawler;


import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * User: wangjie
 * Date: 14-2-23
 */
public class IndexStarter {

    private static final int BOUND = 10;
    private static final int N_CONSUMERS = 6;

    private static Logger logger = LoggerFactory.getLogger(IndexStarter.class);

    public static void main(String[] args) {

//        BasicConfigurator.configure();

        URL url = IndexStarter.class.getResource("");

        PropertyConfigurator.configure(url.getPath() + "log4j.properties");


        if (args == null || args.length == 0) {
            logger.info("No root file defined");
            return;
        }

        logger.info("Start indexing ...");

        List<File> list = new ArrayList<File>();

        for (String arg : args) {
            File file = new File(arg);
            if (!file.exists()) {
                logger.info("File not exist: " + file.toString());
                continue;
            }
            list.add(file);
        }

        startIndexing(list.toArray(new File[0]));

    }

    private static void startIndexing(File[] roots) {

        BlockingQueue<File> fileQueue = new LinkedBlockingQueue<File>(BOUND);

        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file == null) return false;
                if (file.isDirectory()) return true;
                return file.getName().endsWith(".java");
            }
        };

        for (File root : roots) {
            new Thread(new FileCrawler(fileQueue, filter, root)).start();
        }

        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Indexer(fileQueue)).start();
        }
    }
}
