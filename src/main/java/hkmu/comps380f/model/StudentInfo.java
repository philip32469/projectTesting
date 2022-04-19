package hkmu.comps380f.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class StudentInfo implements Serializable {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;*/
    @Id
    private String username;
    private String password;
    private String fullname;
    private String phonenumber;
    private String address;
    private String userrole;

    /*@OneToMany(mappedBy = "user", fetch = FetchType.EARER,
            cascade = CascadeType.ALL, orphanRemoval = true)*/
    public StudentInfo() {
    }

    public StudentInfo(String username, String password, String fullname, String phonenumber, String address, String userrole) {
        this.username = username;
        this.password = "{noop}" + password;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.address = address;
        this.userrole = userrole;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

}
