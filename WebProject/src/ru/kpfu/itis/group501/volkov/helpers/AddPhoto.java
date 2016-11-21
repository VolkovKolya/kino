package ru.kpfu.itis.group501.volkov.helpers;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by volkov on 17.11.2016.
 */
public class AddPhoto {
    public static void addPhoto(Part filePart, String fileName) throws IOException {
        File file = new File("C:/Users/volkov/Desktop/kino/WebProject/web/images/" + fileName);
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        InputStream filecontent = filePart.getInputStream();

        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = filecontent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        out.close();
        filecontent.close();
    }
}
