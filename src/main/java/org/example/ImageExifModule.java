package org.example;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImageExifModule implements Module{
    @Override
    public List<String> getSupportedFileTypes() {
        ArrayList<String> list = new ArrayList<>();
        list.add("png");
        list.add("jpg");
        return list;
    }

    @Override
    public String getDescription() {
        return "Выводит информацию exif";
    }

    @Override
    public void invoke(Path path) {
        try (InputStream stream = new FileInputStream(path.getFileName().toString())) {
            Metadata metadata = ImageMetadataReader.readMetadata(stream);

            System.out.println("EXIF: ");
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.println(tag);
                }
            }
        } catch (IOException | ImageProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
