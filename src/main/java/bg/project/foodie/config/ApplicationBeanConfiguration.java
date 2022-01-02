package bg.project.foodie.config;

import com.cloudinary.*;
import org.modelmapper.*;
import org.modelmapper.spi.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

@Configuration
public class ApplicationBeanConfiguration {
    private final CloudinaryConfig cloudinaryConfig;

    public ApplicationBeanConfiguration(CloudinaryConfig cloudinaryConfiguration) {
        this.cloudinaryConfig = cloudinaryConfiguration;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();

//        Converter<MultipartFile, Byte[]> byteConverter = new Converter<>() {
//            @Override
//            public Byte[] convert(MappingContext<MultipartFile, Byte[]> mappingContext) {
//                try {
//                    return objectConverter().toObjects(mappingContext.getSource().getBytes());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(
                Map.of(
                        "cloud_name", cloudinaryConfig.getCloudName(),
                        "api_key", cloudinaryConfig.getApiKey(),
                        "api_secret", cloudinaryConfig.getApiSecret()
                )
        );
    }
}
