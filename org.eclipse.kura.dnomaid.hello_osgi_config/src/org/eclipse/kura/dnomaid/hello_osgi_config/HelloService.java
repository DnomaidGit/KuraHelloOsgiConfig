package org.eclipse.kura.dnomaid.hello_osgi_config;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.kura.configuration.ConfigurableComponent;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloService implements ConfigurableComponent{
	//Logger /var/log/kura.log or /var/log/kura.log
	private static final Logger S_LOGGER = LoggerFactory.getLogger(HelloService.class);
    private static final String ALIAS_APP_ID = "HelloDnomaid";

    private ScheduledExecutorService worker;
    private ScheduledFuture<?> handle;
            
    private HelloSetup helloSetup;
        
    public HelloService() {		
		super();		
		worker = Executors.newSingleThreadScheduledExecutor();		
	}

	// ----------------------------------------------------------------
    // Activation APIs
    // ----------------------------------------------------------------
    protected void activate(ComponentContext componentContext) {
    	S_LOGGER.info("Activating "+ ALIAS_APP_ID +"......................");     	
    	try {
        	doUpdate();
		} catch (Exception e) {
			S_LOGGER.error("Activating config error "+ ALIAS_APP_ID);
		}	      	
        S_LOGGER.info("Activating "+ ALIAS_APP_ID +"...................... done.");
    }
    /*    
    protected void activate(ComponentContext componentContext, Map<String, Object> properties) {
    	S_LOGGER.info("Activating config"+ ALIAS_APP_ID +"...");    	
        dumpProperties("Activate", properties);        
        this.helloSetup = new HelloSetup(properties); 
        try {
        	doUpdate();
		} catch (Exception e) {
			S_LOGGER.error("Activating config error "+ ALIAS_APP_ID);
		}	    
        S_LOGGER.info("Activating config "+ ALIAS_APP_ID +"... done.");
    }
    */	
    protected void deactivate(ComponentContext componentContext) {
    	S_LOGGER.info("Deactivating "+ ALIAS_APP_ID +"...");
    	if (handle != null) {
            handle.cancel(true);
        }       
        worker.shutdown();
    	S_LOGGER.info("Deactivating "+ ALIAS_APP_ID +"... done.");
    }
    
    public void updated(Map<String, Object> properties) {
        S_LOGGER.info("Updated "+ ALIAS_APP_ID +"...");
        dumpProperties("Update", properties);
        this.helloSetup = new HelloSetup(properties);      
        S_LOGGER.info("Updated "+ ALIAS_APP_ID +"... done.");
    }
    
    // ----------------------------------------------------------------
    // Private methods
    // ----------------------------------------------------------------    
    private static void dumpProperties(final String action, final Map<String, Object> properties) {
        final Set<String> keys = new TreeSet<>(properties.keySet());
        for (final String key : keys) {
            S_LOGGER.info("{} - {}: {}", action, key, properties.get(key));
        }
    }
    
    private void doUpdate() {
        // cancel a current worker handle if one if active
        if (handle != null) {
            handle.cancel(true);
        }       
        // schedule a new worker based on the properties of the service
        int pubrate = 1000;
        handle = worker.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
            	Thread.currentThread().setName(getClass().getSimpleName());
            	if(helloSetup != null) {
	            	if(helloSetup.isEnablePid()) {
	            		S_LOGGER.info("Hello "+helloSetup.getGreetingTypePid()+" !!!!!!!!!!");
	            	}
            	}
            }
        }, 0, pubrate, TimeUnit.MILLISECONDS);
    }

}