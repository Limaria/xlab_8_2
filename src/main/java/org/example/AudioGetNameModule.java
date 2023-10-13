package org.example;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

@Component
public class AudioGetNameModule implements Module{
    @Override
    public List<String> getSupportedFileTypes() {
        ArrayList<String> list = new ArrayList<>();
        list.add("mp3");
        return list;
    }

    @Override
    public String getDescription() {
        return "Возвращает название mp3-трека";
    }

    @Override
    public void invoke(Path path) {
        try {
            Process process = Runtime.getRuntime().exec(String.format("D:\\Java\\пересдача\\ffmeg\\ffmpeg-6.0-full_build\\bin\\ffprobe -v quiet -show_format -print_format json %s", path));
            Scanner scanner = new Scanner(process.getInputStream());
            StringJoiner joiner = new StringJoiner("\n");
            while (scanner.hasNextLine()) {
                joiner.add(scanner.nextLine());
            }
            String json = joiner.toString();
            Gson gson = new Gson();
            Map map = gson.fromJson(json, Map.class);
            Map format = (Map) map.get("format");
            String f = (String) format.get("filename");
            System.out.printf("Название трека: %s", f);
            process.destroy();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
