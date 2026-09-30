package org.example.reservas_de_sala.controller;

import jakarta.validation.Valid;
import org.example.reservas_de_sala.model.Sala;
import org.example.reservas_de_sala.model.TipoSala;
import org.example.reservas_de_sala.repository.SalaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/salas")
// Define /salas como endereço principal

public class SalaController {

    // Guarda o Repository que o Controller vai usar
    private final SalaRepository repository;

    // Construtor
    // Recebe o Repository e guarda ele na variável repository
    public SalaController(SalaRepository repository){
        this.repository = repository;
    }


    // Mostra todas as salas
    @GetMapping
    public String listar(Model model){

        // Busca todas as salas no Repository
        // E envia para o HTML com o nome "salas"
        model.addAttribute("salas", repository.listarTodas());

        // Abre lista.html
        return "lista";
    }


    // Abre o formulário de cadastro
    @GetMapping("/nova")
    public String nova(Model model){

        // Cria uma sala vazia
        Sala sala = new Sala();

        // Envia a sala para o HTML
        model.addAttribute("sala", sala);

        // Envia os tipos de sala para o HTML
        model.addAttribute("tipos", TipoSala.values());

        // Abre formulario.html
        return "formulario";
    }


    // Recebe os dados enviados pelo formulário
    @PostMapping
    public String salvar(
            @Valid @ModelAttribute("sala") Sala sala,
            BindingResult result,
            Model model){

        // Verifica se existe algum erro de validação
        if (result.hasErrors()){

            // Envia os tipos novamente
            // porque o formulário será aberto novamente
            model.addAttribute("tipos", TipoSala.values());

            // Volta para o formulário
            return "formulario";
        }

        // Salva a sala no Repository
        repository.salvar(sala);

        // Volta para a lista de salas
        return "redirect:/salas";
    }


    // Mostra os detalhes de uma sala
    @GetMapping("/{id}")
    public String detalhes(
            @PathVariable Long id,
            Model model){

        // Procura a sala pelo ID
        Sala sala = repository.buscarPorId(id).orElse(null);

        // Envia a sala encontrada para o HTML
        model.addAttribute("sala", sala);

        // Abre detalhes.html
        return "detalhes";
    }


    // Reserva uma sala
    @PostMapping("/{id}/reservar")
    public String reservar(@PathVariable Long id){

        // Procura a sala pelo ID
        Sala sala = repository.buscarPorId(id).orElse(null);

        // Pede para a sala ser reservada
        sala.reservar();

        // Volta para a lista
        return "redirect:/salas";
    }


    // Inicia o uso de uma sala
    @PostMapping("/{id}/iniciar")
    public String iniciar(@PathVariable Long id){

        // Procura a sala pelo ID
        Sala sala = repository.buscarPorId(id).orElse(null);

        // Pede para a sala iniciar o uso
        sala.iniciarUso();

        // Volta para a lista
        return "redirect:/salas";
    }


    // Libera uma sala
    @PostMapping("/{id}/liberar")
    public String liberar(@PathVariable Long id){

        // Procura a sala pelo ID
        Sala sala = repository.buscarPorId(id).orElse(null);

        // Pede para a sala ser liberada
        sala.liberar();

        // Volta para a lista
        return "redirect:/salas";
    }


    // Bloqueia uma sala
    @PostMapping("/{id}/bloquear")
    public String bloquear(@PathVariable Long id){

        // Procura a sala pelo ID
        Sala sala = repository.buscarPorId(id).orElse(null);

        // Pede para a sala ser bloqueada
        sala.bloquear();

        // Volta para a lista
        return "redirect:/salas";
    }

    @PostMapping("/{id}/desbloquear")
    public String desbloquear(@PathVariable Long id){

        // Procura a sala pelo ID
        Sala sala = repository.buscarPorId(id).orElse(null);

        // Pede para a sala ser desbloqueada
        sala.desbloquear();

        // Volta para a lista
        return "redirect:/salas";
    }
}


