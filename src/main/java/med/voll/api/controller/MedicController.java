package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medic.ListMedicDTO;
import med.voll.api.medic.Medic;
import med.voll.api.medic.MedicRepository;
import med.voll.api.medic.RegisterMedicDTO;
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
//    @Transactional
    public void register(@RequestBody @Valid RegisterMedicDTO medicDTO) {
        repository.save(new Medic(medicDTO));
    }

    @GetMapping
    public Page<ListMedicDTO> list(@PageableDefault(size = 2, sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return repository.findAll(pageable).map(ListMedicDTO::new);
    }
}
