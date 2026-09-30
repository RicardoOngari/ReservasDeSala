package org.example.reservas_de_sala.repository;

import org.example.reservas_de_sala.model.Sala;
import org.example.reservas_de_sala.model.StatusSala;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class SalaRepository {

    // Lista que guarda as salas
    private List<Sala> salas = new ArrayList<>();

    private Long contador = 1L;


    // Retorna todas as salas
    public List<Sala> listarTodas() {
        return salas;
    }


    // Adiciona uma sala na lista
    public void salvar(Sala sala) {
        sala.setId(contador);
        contador++;
        salas.add(sala);
    }


    // Procura uma sala pelo ID
    public Optional<Sala> buscarPorId(Long id) {

        // Percorre a lista
        return salas.stream()

                // Procura a sala com o ID informado
                .filter(sala -> sala.getId().equals(id))

                // Retorna a primeira encontrada
                .findFirst();
    }


    // Retorna apenas as salas disponíveis
    public List<Sala> listarSalasDisponiveis() {

        // Percorre a lista
        return salas.stream()

                // Filtra as salas disponíveis
                .filter(sala -> sala.getStatus() == StatusSala.DISPONIVEL)

                // Retorna a lista filtrada
                .toList();
    }

}
