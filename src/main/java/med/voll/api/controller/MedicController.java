package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medics")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterMedicDTO medicDTO) {
        repository.save(new Medic(medicDTO));
    }

    @GetMapping
    public Page<ListMedicDTO> list(@PageableDefault(size = 2, sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return repository.findAllByActiveTrue(pageable).map(ListMedicDTO::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateMedicDTO medicDTO) {
        var medic = repository.getReferenceById(medicDTO.id());
        medic.updateData(medicDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var medic = repository.getReferenceById(id);
        medic.delete();
    }
}
