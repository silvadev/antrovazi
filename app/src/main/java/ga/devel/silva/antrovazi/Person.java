package ga.devel.silva.antrovazi;

/**
 * Created by zouk on 16/12/17.
 */

public class Person {

    private String cpf;
    private String name;
    private String age;
    private String phone;
    private String email;

    Person (String cpf, String name, String age, String phone, String email) {
        this.cpf = cpf;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
