package com.example.demo.vendedor.controller;

import com.example.demo.Application;
import com.example.demo.atuacao.model.AtuacaoInput;
import com.example.demo.vendedor.model.Vendedor;
import com.example.demo.vendedor.model.VendedorAtuacaoDto;
import com.example.demo.vendedor.model.VendedorDto;
import com.example.demo.vendedor.model.VendedorInput;
import com.example.demo.vendedor.repository.IVendedorRepository;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.utils.Functions.isNullOrEmpty;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class VendedorControllerTest {

    private WebApplicationContext webApplicationContext;

    @Autowired
    private IVendedorRepository vendedorRepository;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    private VendedorInput vendedorInput;
    private AtuacaoInput atuacaoInput;

    private static long maxVendedorId = 0;

    @Autowired
    public VendedorControllerTest(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
        String regiao = "TesteVendedor";
        vendedorInput = createVendedor(regiao);

        atuacaoInput = AtuacaoInput.builder()
                .estados(Arrays.asList("SP", "RJ", "MG"))
                .regiao(regiao)
                .build();
    }

    @BeforeEach
    public void setup() throws Exception {
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    private VendedorInput createVendedor(String regiao) {
        return VendedorInput.builder()
                .idade(1)
                .nome("nome")
                .estado("Sao Paulo")
                .regiao(regiao)
                .telefone("telefone")
                .cidade("Sao Paulo")
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Given Vendedor Call to Create " +
            "When all data correct Then " +
            "Should Return Created")
    void givenVendedorCallToCreate_WhenAllDataCorrect_ThenShouldReturnCreated() throws Exception {

        //GIVEN vendedror input with all data correct


        createVendedor(vendedorInput);

        Vendedor vendedor = vendedorRepository.getById(maxVendedorId);

        Assertions.assertNotNull(vendedor);

        Assertions.assertEquals(vendedor.getId(), maxVendedorId);
        Assertions.assertEquals(vendedor.getNome(), vendedorInput.getNome());
        Assertions.assertEquals(vendedor.getEstado(), vendedorInput.getEstado());
        Assertions.assertEquals(vendedor.getRegiao(), vendedorInput.getRegiao());
        Assertions.assertEquals(vendedor.getTelefone(), vendedorInput.getTelefone());
        Assertions.assertEquals(vendedor.getIdade(), vendedorInput.getIdade());
        Assertions.assertEquals(vendedor.getCidade(), vendedorInput.getCidade());
        Assertions.assertEquals(vendedor.getDataCriacao(), LocalDate.now());


    }


    @Test
    @Order(2)
    @DisplayName("Given Vendedor Exist " +
            "When Doing Get by they Id " +
            "Then Should Return The Data created")
    void givenVendedorExist_WhenDoingGetByTheyId_ThenShouldReturnTheDataCreated() throws Exception {

        //GIVEN vendedror exist

        long id = maxVendedorId;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/vendedor/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Should return http status 200 and the data;

        Assertions.assertEquals(OK.value(), result.getResponse().getStatus());

        String responseString = result.getResponse().getContentAsString();
        VendedorDto response = mapper.readValue(responseString, VendedorDto.class);
        Assertions.assertNotNull(response);

        Assertions.assertEquals(LocalDate.now(), response.getDataCriacao());
        Assertions.assertEquals(vendedorInput.getNome(), response.getNome());
        Assertions.assertNotNull(response.getEstados());
        Assertions.assertTrue(response.getEstados().isEmpty());


    }

    @Test
    @Order(3)
    @DisplayName("Given Atuacao Add Estados by the Region of Vendedor " +
            " When Doing Get by they Id" +
            " Then Should Return The Data created with estados ")
    void givenVendedorExistAndUpdatedByAtuacao_WhenDoingGetByTheyName_ThenShouldReturnTheDataCreatedAndUpdated()
            throws Exception {

        //GIVEN created atuacao
        String atuacaoInputJson = mapper.writeValueAsString(atuacaoInput);
        mockMvc.perform(MockMvcRequestBuilders.post("/atuacao")
                        .content(atuacaoInputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        long id = maxVendedorId;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/vendedor/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Should return http status 200 and the data;

        Assertions.assertEquals(OK.value(), result.getResponse().getStatus());

        String responseString = result.getResponse().getContentAsString();
        VendedorDto response = mapper.readValue(responseString, VendedorDto.class);
        Assertions.assertNotNull(response);

        Assertions.assertEquals(LocalDate.now(), response.getDataCriacao());
        Assertions.assertEquals(vendedorInput.getNome(), response.getNome());
        Assertions.assertEquals(response.getEstados(), atuacaoInput.getEstados());

    }

    @Test
    @Order(4)
    @DisplayName("Given Vendedor doesnt Exist " +
            "When Doing Get by they Id " +
            "Then Should Return No Content")
    void givenVendedorDoesntExist_WhenDoingGetByTheyId_ThenShouldReturnNoContent() throws Exception {

        //GIVEN vendedror exist

        long id = maxVendedorId + 1;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/vendedor/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        // Should return http status 200 and the data;

        Assertions.assertEquals(NO_CONTENT.value(), result.getResponse().getStatus());

        String responseString = result.getResponse().getContentAsString();
        Assertions.assertTrue(isNullOrEmpty(responseString));

    }



    @Test
    @Order(5)
    @DisplayName("Given Vendedor exist and Add new One " +
            "When Doing Get " +
            "Then Should Return All data with Atuacao Estados")
    void givenVendedorExistAndAddNewOne_WhenDoingGet_ThenShouldAllData() throws Exception {

        //GIVEN add new vendedor

        createVendedor( createVendedor("Norte"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/vendedor/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Should return http status 200 and the data;

        Assertions.assertEquals(OK.value(), result.getResponse().getStatus());

        String responseString = result.getResponse().getContentAsString();
        Assertions.assertFalse(isNullOrEmpty(responseString));
        List<VendedorAtuacaoDto> vendedorAtuacaoDtoList = mapper.readValue(responseString,
                new TypeReference<List<VendedorAtuacaoDto>>(){});

        Assertions.assertEquals(maxVendedorId, vendedorAtuacaoDtoList.size());
        List<Vendedor> vendedorList = vendedorRepository.getAll();
        Assertions.assertEquals(vendedorList.size(), vendedorAtuacaoDtoList.size());


        // dificult to organize and check, because there isnt any value to be unique in each

    }
    @Test
    @Order(6)
    @DisplayName("Given Vendedor Call to Create " +
            "When data is wrong " +
            "Then Should Return Bad Request")
    void givenVendedorCallToCreate_WhenAllDataWrong_ThenShouldReturn_BadRequest() throws Exception {


        vendedorInput = createVendedor("regiao");
        vendedorInput.setCidade(null);
        String inputJson = mapper.writeValueAsString(vendedorInput);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/vendedor")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();


        vendedorInput = createVendedor("regiao");
        vendedorInput.setEstado(null);
        inputJson = mapper.writeValueAsString(vendedorInput);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/vendedor")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        vendedorInput = createVendedor("regiao");
        vendedorInput.setNome(null);
        inputJson = mapper.writeValueAsString(vendedorInput);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/vendedor")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        vendedorInput = createVendedor("regiao");
        vendedorInput.setIdade(null);
        inputJson = mapper.writeValueAsString(vendedorInput);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/vendedor")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        vendedorInput = createVendedor("regiao");
        vendedorInput.setRegiao(null);
        inputJson = mapper.writeValueAsString(vendedorInput);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/vendedor")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        vendedorInput = createVendedor("regiao");
        vendedorInput.setTelefone(null);
        inputJson = mapper.writeValueAsString(vendedorInput);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/vendedor")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }


    private void createVendedor(VendedorInput vendedorInput) throws Exception {
        String inputJson = mapper.writeValueAsString(vendedorInput);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/vendedor")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        maxVendedorId++;

        Assertions.assertEquals(CREATED.value(), result.getResponse().getStatus());
    }
}
