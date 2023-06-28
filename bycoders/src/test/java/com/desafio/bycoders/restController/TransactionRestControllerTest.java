package com.desafio.bycoders.restController;

import com.desafio.bycoders.service.implemantion.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = TransactionRestController.class)
class TransactionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService service;
    @Test
    void upload() throws Exception {
        MultipartFile multipartFile = new MockMultipartFile("file.txt",
                new FileInputStream("src/test/java/com/desafio/bycoders/resource/CNAB.txt"));

        mockMvc.perform(MockMvcRequestBuilders.multipart("/transaction/upload")
                        .file("file", multipartFile.getBytes()))
                .andExpect(status().isCreated());
    }
}
