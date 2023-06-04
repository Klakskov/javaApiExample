package com.example.demo.atuacao.controller;

import com.example.demo.Application;
import com.example.demo.atuacao.model.Atuacao;
import com.example.demo.atuacao.model.AtuacaoInput;
import com.example.demo.atuacao.repository.IAtuacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class AtuacaoControllerTest {

    private WebApplicationContext webApplicationContext;


    @Autowired
    private IAtuacaoRepository atuacaoRepository;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    private static long maxAtuacaoId = 0;

    @Autowired
    public AtuacaoControllerTest(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;

    }

    @BeforeEach
    public void setup() throws Exception {
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test
    @Order(1)
    @DisplayName("Given POST to Atuacao " +
            "When Atuacao doesnt have Estados " +
            "Then Should Return BadRequest")
    void givenPostToAtuacao_WhenDataDoesntHaveEstado_ThenShouldReturnBadRequest() throws Exception {

        //GIVEN vendedror exist

        AtuacaoInput input = createAtuacaoInput();
        input.setEstados(null);
        String inputJson = mapper.writeValueAsString(input);

        mockMvc.perform(MockMvcRequestBuilders.post("/atuacao")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();


        Atuacao atuacaoSaved = atuacaoRepository.getByRegiao(input.getRegiao());

        Assertions.assertNull(atuacaoSaved);


    }

    @Test
    @Order(2)
    @DisplayName("Given POST to Atuacao " +
            "When Atuacao is ok " +
            "Then Should Return Created and save on database")
    void givenPostToAtuacao_WhenDataIsOk_ThenShouldReturnCreated() throws Exception {

        //GIVEN vendedror exist

        AtuacaoInput input = createAtuacaoInput();
        String inputJson = mapper.writeValueAsString(input);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/atuacao")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        maxAtuacaoId++;


        // Should return http status 200 and the data;

        Assertions.assertEquals(CREATED.value(), result.getResponse().getStatus());

        Atuacao atuacaoSaved = atuacaoRepository.getByRegiao(input.getRegiao());

        Assertions.assertEquals(maxAtuacaoId, atuacaoSaved.getId());

        Assertions.assertEquals(atuacaoSaved.getEstados(), input.getEstados());
        Assertions.assertEquals(atuacaoSaved.getRegiao(), input.getRegiao());


    }

    private AtuacaoInput createAtuacaoInput() {
        return AtuacaoInput.builder()
                .regiao("Sudeste")
                .estados(List.of("SP", "RJ"))
                .build();
    }


}