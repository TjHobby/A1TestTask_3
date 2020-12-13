package a1.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Login {
    private String application;
    @Id
    private String appAccountName;
    private boolean isActive;
    private String jobTitle;
    private String department;
    public Login(){}
    public Login(List<String> values){
        this.application = values.get(0);
        this.appAccountName = values.get(1);
        this.isActive = Boolean.parseBoolean(values.get(2));
        this.jobTitle = values.get(3);
        this.department = values.get(4);
    }

}
