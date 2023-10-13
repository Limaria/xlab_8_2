package org.example;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
@Component
public class DirectorySizeModule implements Module{
    @Override
    public List<String> getSupportedFileTypes() {
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        return list;
    }

    @Override
    public String getDescription() {
        return "Выводит размер всех файлов и папок в директории";
    }

    @Override
    public void invoke(Path path) {
        System.out.printf("Размер: %s", directorySize(new File(path.getFileName().toString())));
    }

    private long directorySize(File directory) {
        long length = 0;
        File[] files = directory.listFiles();
        if(files != null){
            for (File file : files) {
                if (file.isFile())
                    length += file.length();
                else
                    length += directorySize(file);
            }
        }

        return length;
    }

}
