package task2;

import java.util.UUID;

@Table(name = "users")

public class Employee {
    @Column(name = "id", primaryKey = true)
    private UUID id;

    @Column(name = "name")
    private String userName;

    @Column(name = "mail")
    private String email;

    public Employee(String userName, String email) {
        this.id = UUID.randomUUID();
        this.userName = userName;
        this.email = email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
