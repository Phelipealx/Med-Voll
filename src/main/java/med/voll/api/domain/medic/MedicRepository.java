package med.voll.api.domain.medic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicRepository extends JpaRepository<Medic, Long> {
    Page<Medic> findAllByActiveTrue(Pageable pageable);

    @Query("""
            select m from Medic m
            where
            m.active = true
            and
            m.specialty = :specialty
            and
            m.id not in(
                select a.medic.id from Appointment a
                where
                a.date = :date
                and
                a.reasonCanceling is null
            )
            order by rand()
            limit 1
        """)
    Medic chooseRandomMedicAvailableInDate(Specialty specialty, LocalDateTime date);

    @Query("""
            select m.active
            from Medic m
            where
            m.id = :id
            """)
    Boolean findActiveById(Long id);
}
