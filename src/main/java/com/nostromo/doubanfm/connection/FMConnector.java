package com.nostromo.doubanfm.connection;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.google.gson.Gson;

public class FMConnector {
    
    public static final Logger logger = Logger.getLogger("FMConnector");
    
     
    public FMConnector() {
        
    }
    
    public Gson autenticateUser() {
        logger.entering(getClass().toString(), "autenticateUser");
        Gson gson = new Gson();
        try {
            
        } catch (Exception e) {
            
        } finally {
            
        }
        logger.exiting(getClass().toString(), "autenticateUser");
        return gson;
    }
    
}
