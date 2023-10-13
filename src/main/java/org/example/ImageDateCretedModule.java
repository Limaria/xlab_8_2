package org.example;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Component
public class ImageDateCretedModule implements Module{
    @Override
    public List<String> getSupportedFileTypes() {
        ArrayList<String> list = new ArrayList<>();
        list.add("png");
        list.add("jpg");
        return list;
    }

    @Override
    public String getDescription() {
        return "Выводит дату и время создания изображения";
    }
    @Override
    public void invoke(Path path) {
        try {
            FileTime date = (FileTime) Files.getAttribute(path, "creationTime");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateCreated = df.format(date.toMillis());
            System.out.println(dateCreated);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
