package github.Sh1rsh1n.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Person {

    @Expose()
    private String firstName;
    @Expose()
    private String lastName;
    @Expose()
    private int age;
    @Expose(serialize = false)
    private Address address;
    @Expose(serialize = false)
    private String email;
    @SerializedName("phones number")
    @Expose(serialize = false)
    private List<String> phones;

    public Person(String firstName, String lastName, int age, Address address, String email, List<String> phones) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.email = email;
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return new EqualsBuilder().append(age, person.age).append(firstName, person.firstName).append(lastName, person.lastName).append(address, person.address).append(email, person.email).append(phones, person.phones).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(firstName).append(lastName).append(age).append(address).append(email).append(phones).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("age", age)
                .append("address", address)
                .append("email", email)
                .append("phones", phones)
                .toString();
    }
}
