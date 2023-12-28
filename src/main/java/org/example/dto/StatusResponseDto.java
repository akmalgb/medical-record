package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusResponseDto {

    @JsonProperty(index = 1)
    private int status;

    @JsonProperty(index = 2)
    private boolean success;

    @JsonProperty(index = 3)
    private String message;

    @JsonProperty(index = 4)
    private String messageError;


}
