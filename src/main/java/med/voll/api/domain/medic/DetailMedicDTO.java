package med.voll.api.domain.medic;

public record DetailMedicDTO(Long id, String name, String email, String crm, String phone, Specialty specialty,
                             Address address) {

    public DetailMedicDTO(Medic medic) {
        this(medic.getId(), medic.getName(), medic.getEmail(), medic.getCrm(), medic.getPhone(), medic.getSpecialty(), medic.getAddress());
    }
}
