package org.example;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileSizeModule implements Module {
    @Override
    public List<String> getSupportedFileTypes() {
        ArrayList<String> list = new ArrayList<>();
        list.add("txt");
        return list;
    }

    @Override
    public String getDescription() {
        return "Считает размер файла в байтах";
    }

    @Override
    public void invoke(Path path) {
        File file = new File(path.getFileName().toString());
        System.out.println(file.length());
    }
}
