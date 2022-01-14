package Main.ContentItem;
import java.sql.Date;

import Main.ContentItem.Course.Status;

public abstract class ContentItem {
    private Date publicationDate; 
    private Status status; 

    public ContentItem(Date publicationDate, Status status) {
        this.publicationDate = publicationDate;
        this.status = status;
    }
}
