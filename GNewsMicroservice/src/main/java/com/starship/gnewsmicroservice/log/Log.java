package com.starship.gnewsmicroservice.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    private static Logger logger = null;

    private static void init(){
        if(logger == null){
            logger = Logger.getLogger("Logger");
            logger.setUseParentHandlers(false);

            try {
                FileHandler handler = new FileHandler("log_news", true);
                logger.addHandler(handler);
                SimpleFormatter formatter = new SimpleFormatter();
                handler.setFormatter(formatter);
            }
            catch (SecurityException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void info(String message){
        init();

        logger.info(message);
    }

    public static void warn(String message){
        init();

        logger.warning(message);
    }

    public static void severe(String message){
        init();

        logger.severe(message);
    }



}
