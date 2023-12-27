package github.Sh1rsh1n.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Address {

    private String city;
    private String street;
    private int buildingNo;

    public Address(String city, String street, int buildingNo) {
        this.city = city;
        this.street = street;
        this.buildingNo = buildingNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return new EqualsBuilder().append(buildingNo, address.buildingNo).append(city, address.city).append(street, address.street).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(city).append(street).append(buildingNo).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("city", city)
                .append("street", street)
                .append("buildingNo", buildingNo)
                .toString();
    }
}
