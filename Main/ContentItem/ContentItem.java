package Main.ContentItem;
import java.sql.Date;

public abstract class ContentItem {
    private Date publicationDate; 
    private String status; 

    public ContentItem(Date publicationDate, String status) {
        this.publicationDate = publicationDate;
        this.status = status;
    }
}
