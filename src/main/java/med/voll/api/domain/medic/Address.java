package med.voll.api.domain.medic;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {
    private String streetName;
    private String neighbourhood;
    private String number;
    private String postalCode;
    private String city;
    private String state;
    private String country;
    private String complement;

    public Address(AddressDTO addressDTO) {
        this.streetName = addressDTO.streetName();
        this.neighbourhood = addressDTO.neighbourhood();
        this.number = addressDTO.number();
        this.postalCode = addressDTO.postalCode();
        this.city = addressDTO.city();
        this.state = addressDTO.state();
        this.country = addressDTO.country();
        this.complement = addressDTO.complement();
    }

    public void updateData(AddressDTO address) {
        if (address.streetName() != null) {
            this.streetName = address.streetName();
        }

        if (address.neighbourhood() != null) {
            this.neighbourhood = address.neighbourhood();
        }

        if (address.number() != null) {
            this.number = address.number();
        }


        if (address.postalCode() != null) {
            this.postalCode = address.postalCode();
        }

        if (address.city() != null) {
            this.city = address.city();
        }

        if (address.state() != null) {
            this.state = address.state();
        }

        if (address.country() != null) {
            this.country = address.country();
        }

        if (address.country() != null) {
            this.country = address.country();
        }

        if (address.complement() != null) {
            this.complement = address.complement();
        }
    }
}
