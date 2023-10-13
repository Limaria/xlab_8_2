package org.example;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class FileCharCountModule implements Module{
    @Override
    public List<String> getSupportedFileTypes() {
            ArrayList<String> list = new ArrayList<>();
            list.add("txt");
            return list;
    }

    @Override
    public String getDescription() {
            return "Считает количество вхождений символа";
    }

    @Override
    public void invoke(Path path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path.getFileName().toString()))) {
            String str = reader.readLine();
            Scanner scanner = new Scanner(System.in);
            int count=0;
            String my_char;
            do {
                System.out.print("Введите символ: ");
                my_char = scanner.nextLine();
            } while (my_char.length() != 1 );
            while (str != null) {
            for (int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == my_char.charAt(0)){
                    count++;
                }
            }

                str = reader.readLine();
            }

            System.out.printf("%s : %d\n", my_char, count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
