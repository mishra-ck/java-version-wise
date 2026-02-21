package concurrency.runnable;

import java.util.logging.Logger;

// Creating this Task class to print Log Message
public class EventLoggingTask implements Runnable{

    private Logger logger = Logger.getLogger("EventLoggingTask.class");
    @Override
    public void run() {
        String message = " This is Runnable Functional Interface Demo Usage.. " ;
        logger.info("Event Logging message is : "+ message);
    }
}




