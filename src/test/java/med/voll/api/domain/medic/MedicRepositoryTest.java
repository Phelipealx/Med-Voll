package med.voll.api.domain.medic;

import med.voll.api.domain.address.AddressDTO;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.RegisterPatientDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicRepositoryTest {

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    @DisplayName("Should get return null when medic is not available in date.")
    void chooseRandomMedicAvailableInDateScenarioOne() {
        var medic = registerMedic("Medic", "medic@voll.med", "123456", Specialty.CARDIOLOGY);
        var patient = registerPatient("Patient", "paciente@email.com", "00000000000");
        registerAppointment(medic, patient, nextMondayAt10());

        var medicNotAvailable = medicRepository.chooseRandomMedicAvailableInDate(Specialty.CARDIOLOGY, nextMondayAt10());
        assertThat(medicNotAvailable).isNull();
    }

    @Test
    @DisplayName("Should get return a medic when he is available in date.")
    void chooseRandomMedicAvailableInDateScenarioTwo() {
        var medic = registerMedic("Medic", "medic@voll.med", "123456", Specialty.CARDIOLOGY);

        var medicAvailable = medicRepository.chooseRandomMedicAvailableInDate(Specialty.CARDIOLOGY, nextMondayAt10());
        assertThat(medicAvailable).isEqualTo(medic);
    }

    private LocalDateTime nextMondayAt10() {
        return LocalDate
                .now()
                .with(TemporalAdjusters
                        .next(DayOfWeek.MONDAY))
                .atTime(10, 0);
    }

    private void registerAppointment(Medic medic, Patient patient, LocalDateTime date) {
        testEntityManager.persist(new Appointment(null, medic, patient, date, null));
    }

    private Medic registerMedic(String name, String email, String crm, Specialty specialty) {
        var medic = new Medic(registerMedicDTO(name, email, crm, specialty));
        testEntityManager.persist(medic);
        return medic;
    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(registerPatientDTO(name, email, cpf));
        testEntityManager.persist(patient);
        return patient;
    }

    private RegisterMedicDTO registerMedicDTO(String nome, String email, String crm, Specialty specialty) {
        return new RegisterMedicDTO(
                nome,
                email,
                "61999999999",
                crm,
                specialty,
                addressDTO()
        );
    }

    private RegisterPatientDTO registerPatientDTO(String nome, String email, String cpf) {
        return new RegisterPatientDTO(
                nome,
                email,
                "61999999999",
                cpf,
                addressDTO()
        );
    }

    private AddressDTO addressDTO() {
        return new AddressDTO(
                "Rua xpto",
                "Test Bairro",
                "00000000",
                "Ribeirão Preto",
                "SP",
                "Brasil",
                "00",
                "nenhum"
        );
    }
}