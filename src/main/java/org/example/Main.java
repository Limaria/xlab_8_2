package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);
        ModuleFactory bean = ctx.getBean(ModuleFactory.class);
        String filename = args[0];
        File file = new File("D:\\Java\\пересдача\\xlab_8_2",filename);
        Path path = Paths.get(file.getAbsolutePath());
        String fileExtension = getFileExtensions(filename); //возвращает расширение файла
        List<Module> modules = bean.getModule(fileExtension);
        String moduleNames = getModulesNames(modules);
        System.out.printf("Количетсво модулей, которе могут обработать Ваш файл: %d\n%s\n", modules.size(), moduleNames);
        int moduleNumber = getUserSelection(modules);
        modules.get(moduleNumber).invoke(path);
    }

    private static String getFileExtensions(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        return "";
    }

    private static String getModulesNames(List<Module> modules) {
        int i = 0;
        StringJoiner joiner = new StringJoiner("\n");  //соединения строк с использованием разделителя
        for (Module x : modules) {
            String simpleName = x.getDescription();
            joiner.add(String.format("%d) %s" , i++, simpleName));
        }
        return joiner.toString();
    }

    private static int getUserSelection(List<Module> modules) {
        Scanner scanner = new Scanner(System.in);
        int moduleNumber;
        do {
            System.out.print("Введите номер нужного обработчика: ");
            moduleNumber = scanner.nextInt();
        } while (moduleNumber < 0 || moduleNumber >= modules.size());
        return moduleNumber;
    }
}