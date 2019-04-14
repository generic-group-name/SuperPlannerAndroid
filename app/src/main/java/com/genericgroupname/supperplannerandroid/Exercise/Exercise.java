package com.genericgroupname.supperplannerandroid.Exercise;

public class Exercise {
    private ExerciseType exerciseType;
    private String name;
    private String description;
    private String media;//link do YT

    public Exercise(ExerciseType exerciseType, String name, String description, String media) {
        this.exerciseType = exerciseType;
        this.name = name;
        this.description = description;
        this.media = media;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMedia() {
        return media;
    }
}
