package anotations.database;

/**
 * Created by evgen on 11.01.16.
 */
@DBTable(title = "member")
public class Member {

    @SQLString(30)
    private String firstName;
    @SQLString(50)
    private String lastName;
    @SQLInteger
    private int age;
    @SQLString(value = 30, constraints = @Constraints(primaryKey = true))
    private String handle;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }
}
