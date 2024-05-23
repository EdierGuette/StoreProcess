package com.parcialTienda.Aplicativo.web.model.dto;

import com.parcialTienda.Aplicativo.web.model.Categoria;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class CategoriaRequestMessage {
    @Valid
    @NotNull(message = "RequestMessage is required")
    private RequestMessage requestMessage;

    @Data
    @Builder
    public static class RequestMessage {
        @NotNull(message = "El messageID es obligatorio")
        private String messageID;

        @Valid
        @NotNull(message = "La categoria es obligatoria")
        private Categoria categoria;
    }

}
