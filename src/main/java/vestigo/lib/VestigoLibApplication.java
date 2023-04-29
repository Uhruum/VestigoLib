package vestigo.lib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import vestigo.lib.services.apstractions.LetterCounterService;
import vestigo.lib.services.dtos.LetterCounterReadDto;

@SpringBootApplication
public class VestigoLibApplication {

    private static ApplicationContext _context = null;

    private static Environment _env = null;

    public VestigoLibApplication(ApplicationContext context, Environment environment) {
        _context = context;
        _env = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(VestigoLibApplication.class, args);
        try {
            LetterCounterService letterCounterService = _context.getBean(LetterCounterService.class);
            LetterCounterReadDto letterCounterReadDto = letterCounterService.count(_env.getProperty("test.prop"));
            System.out.printf("Number of vowels: %s , number of consonants: %s",
                    letterCounterReadDto.vowelCount,
                    letterCounterReadDto.consonantCount);
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
    }
}
