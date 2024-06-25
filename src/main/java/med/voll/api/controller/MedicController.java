package med.voll.api.controller;

import med.voll.api.medic.RegisterMedicDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medics")
public class MedicController {

    @PostMapping
    public void register(@RequestBody RegisterMedicDTO json) {
        System.out.println(json);
    }
}
