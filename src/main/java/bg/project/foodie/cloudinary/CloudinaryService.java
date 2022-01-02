package bg.project.foodie.cloudinary;

import org.springframework.web.multipart.*;

import java.io.*;

public interface CloudinaryService {
    CloudinaryImage upload(MultipartFile file) throws IOException;

    boolean delete(String publicId);
}
