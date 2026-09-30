package org.example.reservas_de_sala.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sala {
    private Long id;
    @NotBlank(message = "Informe o nome da Sala")
    @Size(min = 3, max = 50)
    private String nome;
    @NotBlank(message = "Informe a localização da Sala")
    @Size(min = 3, max = 50)
    private String localizacao;
    @NotBlank(message = "Informe o responsavel da Sala")
    @Size(min = 3, max = 50)
    private String responsavel;
    @NotBlank(message = "Informe o email do responsavel")
    @Email
    private String emailResponsavel;
    private TipoSala tipo;
    @NotNull(message = "Informe a capacidade")
    @Min(value = 1, message = "Informe um numero maior que zero")
    private Integer capacidade;
    private StatusSala status;


    public Sala(){
        this.status = StatusSala.DISPONIVEL;
    }

    public void reservar(){
        if (status == StatusSala.DISPONIVEL){
            status = StatusSala.RESERVADA;
        }
    }
    public void iniciarUso(){
        if (status == StatusSala.RESERVADA){
            status = StatusSala.EM_USO;
        }
    }
    public void liberar(){
        if (status == StatusSala.EM_USO){
            status = StatusSala.DISPONIVEL;
        }
    }
    public void bloquear(){
        if (status == StatusSala.RESERVADA || status == StatusSala.DISPONIVEL){
            status = StatusSala.BLOQUEADA;
        }

    }

    public void desbloquear(){
        if (status == StatusSala.BLOQUEADA){
            status = StatusSala.DISPONIVEL;
        }

    }
}
