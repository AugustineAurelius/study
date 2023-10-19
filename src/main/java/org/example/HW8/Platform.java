package org.example.HW8;

import lombok.Data;

@Data
public class Platform {
    private String name;
    private boolean isRoscomnadzored;
    public Platform(String name, boolean isRoscomnadzored) {
        this.name = name;
        this.isRoscomnadzored = isRoscomnadzored;
    }
}
