package med.voll.api.domain.medic;

import jakarta.validation.constraints.NotNull;

public record UpdateMedicDTO(
        @NotNull
        Long id,

        String name,
        String phone,
        AddressDTO address
) {
}
