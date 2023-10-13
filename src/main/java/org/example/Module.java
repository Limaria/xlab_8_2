package org.example;

import java.nio.file.Path;
import java.util.List;

public interface Module {

    List<String> getSupportedFileTypes();

    String getDescription();

    void invoke(Path path);

}
