package a1.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Entity
public class Posting {
    @Id
    private String matDoc;
    private int item;
    private Date docDate;
    private Date postDate;
    private String materialDesc;
    private int quantity;
    private String bun;
    double amountLC;
    private String currency;
    private String userName;
    private boolean isAuthorized;
    @Transient
    private transient SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    public Posting(){}
    public Posting(List<String> values) throws ParseException {
        matDoc = values.get(0);
        item = Integer.parseInt(values.get(1));
        docDate = formatter.parse(values.get(2));
        postDate = formatter.parse(values.get(3));
        materialDesc = values.get(4);
        quantity = Integer.parseInt(values.get(5));
        bun = values.get(6);
        amountLC = Double.parseDouble(values.get(7).replaceAll(",", "."));
        currency = values.get(8);
        userName = values.get(9);
        isAuthorized = Boolean.parseBoolean(values.get(10));
    }
}
