package snicorp.snd.migration;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SnicorpSndMigrationApplication {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());


    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(SandiMigration.class);
        //app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);

    }

    private void test(){
        log.debug("debug message");
    }
}
