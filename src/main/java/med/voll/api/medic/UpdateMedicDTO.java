package med.voll.api.medic;

import jakarta.validation.constraints.NotNull;

public record UpdateMedicDTO(
        @NotNull
        Long id,

        String name,
        String phone,
        AddressDTO address
) {
}
