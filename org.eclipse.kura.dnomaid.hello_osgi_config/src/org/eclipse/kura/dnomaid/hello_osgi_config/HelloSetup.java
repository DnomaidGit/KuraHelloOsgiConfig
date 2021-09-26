package org.eclipse.kura.dnomaid.hello_osgi_config;

import java.util.Map;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

final class HelloSetup {
	
	//Application identifier
    private static final Boolean DEFAULT_ENABLE_PID = false;
    private static final String DEFAULT_GREETING_TYPE_PID = "??¿¿";
    
    //Property Names
    private static final String ENABLE_PROP_NAME = "enable";
    private static final String GREETING_TYPE_PROP_NAME = "greetingType";
    
    private final Map<String, Object> properties;

    HelloSetup (final Map<String, Object> properties) {
        requireNonNull(properties);
        this.properties = properties;
    }

    String getGreetingTypePid() {
        String greetingTypePid = DEFAULT_GREETING_TYPE_PID;
        Object setupGreetingTyperPid = this.properties.get(GREETING_TYPE_PROP_NAME);
        if (nonNull(setupGreetingTyperPid) && setupGreetingTyperPid instanceof String) {
        	greetingTypePid = (String) setupGreetingTyperPid;
        }
        return greetingTypePid;
    }
    
    Boolean isEnablePid() {
        Boolean enablePid = DEFAULT_ENABLE_PID;
        Object setupEnablePid = this.properties.get(ENABLE_PROP_NAME);
        if (nonNull(setupEnablePid) && setupEnablePid instanceof Boolean) {
        	enablePid = (Boolean) setupEnablePid;
        }
        return enablePid;
    }

}
