package ru.roboart.controllers.utils;

/**
 * Created by Kida on 13.02.2017.
 */



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    public FileController() {
    }

    @RequestMapping(
            value = {"/uploadFile"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String name = null;
        if(!file.isEmpty()) {
            try {
                byte[] e = file.getBytes();
                name = file.getOriginalFilename();
                String rootPath = "img";
                File dir = new File(rootPath + File.separator + "loadFiles");
                if(!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(e);
                stream.flush();
                stream.close();
                logger.info("You successfully uploaded file=" + name);
                return uploadedFile.getAbsolutePath();
            } catch (Exception var8) {
                logger.info("You failed to upload " + name + " => " + var8.getMessage());
            }
        } else {
            logger.info("You failed to upload " + name + " because the file was empty.");
        }

        return "";
    }
}

