package med.voll.api.medic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
        @NotBlank
        String streetName,

        @NotBlank
        String neighbourhood,

        @NotBlank
        @Pattern(regexp = "\\d{8,10}")
        String postalCode,

        @NotBlank
        String city,

        @NotBlank
        String state,

        @NotBlank
        String country,

        String number,
        String complement
) {
}

