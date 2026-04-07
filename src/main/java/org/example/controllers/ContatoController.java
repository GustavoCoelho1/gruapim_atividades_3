package com.exemplo.api.controllers;

import com.exemplo.api.models.Contato;
import com.exemplo.api.repositories.ContatoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;

    // POST tradicional (Inclui o @Valid do Desafio 2)
    @PostMapping
    public ResponseEntity<Contato> createContato(@Valid @RequestBody Contato contato) {
        return ResponseEntity.ok(contatoRepository.save(contato));
    }

    // Exercício 1 - Criando um Novo Endpoint GET (Busca por nome)
    // Usamos @RequestParam para capturar a query string (?name=João)
    @GetMapping("/search")
    public ResponseEntity<List<Contato>> searchByName(@RequestParam String name) {
        List<Contato> contatos = contatoRepository.findByNomeContainingIgnoreCase(name);
        // Retorna a lista (vazia ou populada) com status 200 OK
        return ResponseEntity.ok(contatos);
    }

    // Exercício 2 - Implementando um Método PATCH
    // Recebemos um Map<String, Object> para saber exatamente quais campos foram enviados no JSON
    @PatchMapping("/{id}")
    public ResponseEntity<Contato> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Contato> optionalContato = contatoRepository.findById(id);

        if (optionalContato.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se não achar
        }

        Contato contato = optionalContato.get();

        // Atualiza apenas os campos que vieram no corpo da requisição (Map)
        if (updates.containsKey("nome")) {
            contato.setNome((String) updates.get("nome"));
        }
        if (updates.containsKey("telefone")) {
            contato.setTelefone((String) updates.get("telefone"));
        }
        if (updates.containsKey("email")) {
            contato.setEmail((String) updates.get("email"));
        }

        // Salva e retorna o contato atualizado
        contatoRepository.save(contato);
        return ResponseEntity.ok(contato);
    }
}