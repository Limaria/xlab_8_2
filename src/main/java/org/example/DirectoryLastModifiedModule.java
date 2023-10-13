package org.example;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Component
public class DirectoryLastModifiedModule implements Module{
    @Override
    public List<String> getSupportedFileTypes() {
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        return list;
    }

    @Override
    public String getDescription() {
        return "Выводит дату и время последнего изменения";
    }

    @Override
    public void invoke(Path path) {
        File file = new File(path.toString());
        String date = (new SimpleDateFormat("MM.dd.yyyy HH:mm:ss")).format(file.lastModified());
        System.out.printf("Дата последней модификации: %s\n", date);
    }
}
