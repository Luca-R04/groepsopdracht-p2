package Main.ContentItem.Course;

import Main.ContentItem.ContentItem;

public class Course extends ContentItem {
    private String name; 
    private String topic; 
    private String text; 
    private String level; 
    private int percentageViewed; 

    public Course(String name, String topic, String text, String level, int percentageViewed) {
        this.name = name; 
        this.topic = topic; 
        this.text = text; 
        this.level = level; 
        this.percentageViewed = percentageViewed;
    }
}
