package med.voll.api.medic;

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
}
