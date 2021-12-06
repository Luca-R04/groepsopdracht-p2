package Main.ContentItem;

import java.util.ArrayList;

import Main.ContentItem.Course.Module;

public class Webcast extends ContentItem {
    private String title; 
    private String url; 
    private int duration; 
    private String staffName; 
    private String description; 
    private ArrayList<Module> modules; 
    private ArrayList<Speaker> speakers;
}
