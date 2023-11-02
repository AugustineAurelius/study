package org.example.HW6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StdOutCaller implements Call {
    @AutoCallable
    public void call() {
        try {
            String config = new String(Files.readAllBytes(Paths.get("/home/ulisse/IdeaProjects/study/src/main/java/org/example/HWCollection/config.txt")));
            System.out.println("Calling StdOutCaller with config: " + config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
