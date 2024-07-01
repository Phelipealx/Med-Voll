package med.voll.api.medic;

public record RegisterMedicDTO(String name, String email, String crm, Specialty specialty, Address address) {
}
