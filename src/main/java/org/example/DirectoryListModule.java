package org.example;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
@Component
public class DirectoryListModule implements Module{
    @Override
    public List<String> getSupportedFileTypes() {
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        return list;
    }

    @Override
    public String getDescription() {
        return "Выводит сожержание папки";
    }
    @Override
    public void invoke(Path path) {
        File[] files = (new File(path.toString())).listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            System.out.println(file.getName());
        }
    }
}
