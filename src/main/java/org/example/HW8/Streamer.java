package org.example.HW8;

import lombok.Data;
import java.util.List;

@Data
public class Streamer {
    private String name;
    private double averageMark;
    private int age;
    private int subscribers;
    private List<Platform> platforms;

    public Streamer(String name, double averageMark, int age, int subscribers, List<Platform> platforms) {
        this.name = name;
        this.averageMark = averageMark;
        this.age = age;
        this.subscribers = subscribers;
        this.platforms = platforms;
    }
}
