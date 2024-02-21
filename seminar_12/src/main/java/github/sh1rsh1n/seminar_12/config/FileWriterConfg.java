package github.sh1rsh1n.seminar_12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.GenericTransformer;

@Configuration
public class FileWriterConfg {

    @Bean
    @Transformer(inputChannel = "textInChannel", outputChannel = "fileWriterChannel")
    public GenericTransformer<String, String> upperCaseTransformer() {
        return text -> text.toUpperCase();
    }

    
}
