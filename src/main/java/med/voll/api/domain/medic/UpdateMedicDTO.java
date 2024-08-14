package med.voll.api.domain.medic;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressDTO;

public record UpdateMedicDTO(
        @NotNull
        Long id,

        String name,
        String phone,
        AddressDTO address
) {
}
