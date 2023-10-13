package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModuleFactory {

    @Autowired
    private List<Module> modules;


    public List<Module> getModule(String filetype) {
        List<Module> result = new ArrayList<>();
        for(Module module : modules) {
            if (module.getSupportedFileTypes().contains(filetype))
                result.add(module);
        }
        return result;
    }

}
