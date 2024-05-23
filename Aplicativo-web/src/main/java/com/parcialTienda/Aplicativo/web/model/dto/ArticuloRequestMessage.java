package com.parcialTienda.Aplicativo.web.model.dto;

import com.parcialTienda.Aplicativo.web.model.Articulo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
public class ArticuloRequestMessage {
    @Valid
    @NotNull(message = "El RequestMessage es obligatorio")
    private RequestMessage requestMessage;

    @Data
    public static class RequestMessage {
        @NotNull(message = "El messageID es obligatorio")
        private String messageID;

        @Valid
        @NotNull(message = "Ingrese un articulo")
        private Articulo articulo;
    }
}
