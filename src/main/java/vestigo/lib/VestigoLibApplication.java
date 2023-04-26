package vestigo.lib;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VestigoLibApplication {
    private static Logger logger = Logger.getLogger(VestigoLibApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(VestigoLibApplication.class, args);
        logger.debug("Test log message!");
    }

}
