package south.park.parkshark.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

        @Entity
        @Table(name = "addresses")
        public class Address {
            @Id
            private long adressId;
            private String streetName;
            private String streetNumber;
            private String postalCode;
            private String postalLabel;

            public Address() {
            }

            public long getAdressId() {
                return adressId;
            }

            public String getStreetName() {
                return streetName;
            }

            public String getStreetNumber() {
                return streetNumber;
            }

            public String getPostalCode() {
                return postalCode;
            }

            public String getPostalLabel() {
                return postalLabel;
            }

            @Override
            public String toString() {
                return "Address{" +
                        "id=" + adressId +
                        ", streetName='" + streetName + '\'' +
                        ", streetNumber='" + streetNumber + '\'' +
                        ", postalCode='" + postalCode + '\'' +
                        ", postalLabel='" + postalLabel + '\'' +
                        '}';
            }
}
