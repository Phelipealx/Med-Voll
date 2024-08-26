package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressDTO;
import med.voll.api.domain.medic.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<RegisterMedicDTO> registerMedicDTOJacksonTester;

    @Autowired
    private JacksonTester<DetailMedicDTO> detailMedicDTOJacksonTester;

    @MockBean
    private MedicRepository repository;

    @Test
    @DisplayName("Should get HTTP code 400 when data is invalid.")
    @WithMockUser
    void registerScenarioOne() throws Exception {
        var response = mvc
                .perform(post("/medics"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should get HTTP code 200 when data is valid to successful register.")
    @WithMockUser
    void registerScenarioTwo() throws Exception {
        var registerMedicDTO = new RegisterMedicDTO(
                "Medic",
                "medic@voll.med",
                "61999999999",
                "123456",
                Specialty.CARDIOLOGY,
                addressDTO());

        when(repository.save(any())).thenReturn(new Medic(registerMedicDTO));

        var response = mvc
                .perform(post("/medics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerMedicDTOJacksonTester
                                .write(registerMedicDTO)
                                .getJson()))
                .andReturn().getResponse();

        var detailMedicDTO = new DetailMedicDTO(
                null,
                registerMedicDTO.name(),
                registerMedicDTO.email(),
                registerMedicDTO.crm(),
                registerMedicDTO.phone(),
                registerMedicDTO.specialty(),
                new Address(registerMedicDTO.address())
        );
        var jsonExpected = detailMedicDTOJacksonTester.write(detailMedicDTO).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }

    private AddressDTO addressDTO() {
        return new AddressDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Ribeirao Preto",
                "SP",
                "Brasil",
                "360",
                ""
        );
    }
}