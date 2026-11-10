package com.mercado.lista.presentation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
class ItemMercadoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void fluxoCompletoDoItem() throws Exception {
        String novo = """
                {"nome":"Leite","quantidade":2,"unidade":"LITRO","categoria":"LATICINIOS"}
                """;

        mvc.perform(post("/api/itens").contentType(MediaType.APPLICATION_JSON).content(novo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("PENDENTE"));

        mvc.perform(get("/api/itens").param("status", "PENDENTE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        String atualizado = """
                {"nome":"Leite integral","quantidade":3,"unidade":"LITRO","categoria":"LATICINIOS"}
                """;
        mvc.perform(put("/api/itens/1").contentType(MediaType.APPLICATION_JSON).content(atualizado))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Leite integral"));

        mvc.perform(patch("/api/itens/1/status").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\":\"COMPRADO\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("COMPRADO"));

        mvc.perform(delete("/api/itens/1")).andExpect(status().isNoContent());
        mvc.perform(get("/api/itens/1")).andExpect(status().isNotFound());
    }

    @Test
    void deveRetornar400ParaDadosInvalidos() throws Exception {
        mvc.perform(post("/api/itens").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"\",\"quantidade\":0}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detalhes").isArray());
    }
}
