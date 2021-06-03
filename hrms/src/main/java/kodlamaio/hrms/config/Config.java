package kodlamaio.hrms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import kodlamaio.hrms.core.imageUploaders.CloudinaryService;
import kodlamaio.hrms.core.imageUploaders.ImageService;

@Configuration
public class Config {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
    public Cloudinary cloudinaryService(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dgfimpyp9",
                "api_key", "559497441591421",
                "api_secret", "qXZGvlycICDV5FBK3b4KEO0Oz4M"));
    }

    
    public ImageService imageService(){
        return new CloudinaryService();
    }
    
    
	/*
	//@Bean
	public ImageService imageService(){
	    return new CloudinaryService();
	}*/
}
