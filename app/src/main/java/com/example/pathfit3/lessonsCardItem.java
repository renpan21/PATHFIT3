package com.example.pathfit3;

public class lessonsCardItem {
    String title;
    String description;
     String topic; // Different content for expandable section
     int imageResource; // Different image for expandable section


    // Constructor
    public lessonsCardItem(String title, String description, String additionalInfo, int imageResource) {
        this.title = title;
        this.description = description;
        this.topic = additionalInfo;
        this.imageResource = imageResource;

    }


}

