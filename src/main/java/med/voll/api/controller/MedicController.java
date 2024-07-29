package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("medics")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterMedicDTO medicDTO, UriComponentsBuilder uriComponentsBuilder) {
        var medic = new Medic(medicDTO);
        repository.save(medic);

        var uri = uriComponentsBuilder.path("/medic/{id}").buildAndExpand(medic.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailMedicDTO(medic));
    }

    @GetMapping
    public ResponseEntity<Page<ListMedicDTO>> list(@PageableDefault(size = 5, sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable) {
        var page = repository.findAllByActiveTrue(pageable).map(ListMedicDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateMedicDTO medicDTO) {
        var medic = repository.getReferenceById(medicDTO.id());
        medic.updateData(medicDTO);

        return ResponseEntity.ok(new DetailMedicDTO(medic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var medic = repository.getReferenceById(id);
        medic.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var medic = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailMedicDTO(medic));
    }
}
