package Constants;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class ApplicatioConstants {
    public static final String BASE_URL = "http://greenstone-energy.de/images/logo/";

    public static String getTemplateIfExistent(String tempName) {

        try {

            File file = ResourceUtils.getFile("classpath:templates/" + tempName);
            if (file.exists()) return  tempName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
