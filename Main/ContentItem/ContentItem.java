package Main.ContentItem;
import java.sql.Date;

import Main.Database.Database;

public abstract class ContentItem {
    private Date publicationDate; 
    private String status; 
    private Database db = new Database();

    public ContentItem(Date publicationDate, String status) {
        this.publicationDate = publicationDate;
        this.status = status;

        db.createContentItem(this.publicationDate, this.status);
    }
}
