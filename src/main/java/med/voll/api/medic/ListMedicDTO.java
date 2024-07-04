package med.voll.api.medic;

public record ListMedicDTO(String name, String email, String crm, Specialty specialty) {

    public ListMedicDTO(Medic medic) {
        this(medic.getName(), medic.getEmail(), medic.getCrm(), medic.getSpecialty());
    }
}
