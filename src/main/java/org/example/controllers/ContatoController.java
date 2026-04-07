package com.exemplo.api.controllers;

import com.exemplo.api.models.Contact;
import com.exemplo.api.repositories.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContatoController {

    @Autowired
    private ContatoRepository contactRepository;

    // POST tradicional (Inclui o @Valid do Desafio 2)
    @PostMapping
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {
        return ResponseEntity.ok(contactRepository.save(contact));
    }

    // Exercício 1 - Criando um Novo Endpoint GET (Busca por nome)
    // Usamos @RequestParam para capturar a query string (?name=João)
    @GetMapping("/search")
    public ResponseEntity<List<Contact>> searchByName(@RequestParam String name) {
        List<Contact> contacts = contactRepository.findByNomeContainingIgnoreCase(name);
        // Retorna a lista (vazia ou populada) com status 200 OK
        return ResponseEntity.ok(contacts);
    }

    // Exercício 2 - Implementando um Método PATCH
    // Recebemos um Map<String, Object> para saber exatamente quais campos foram enviados no JSON
    @PatchMapping("/{id}")
    public ResponseEntity<Contact> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Contact> optionalContact = contactRepository.findById(id);

        if (optionalContact.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se não achar
        }

        Contact contact = optionalContact.get();

        // Atualiza apenas os campos que vieram no corpo da requisição (Map)
        if (updates.containsKey("nome")) {
            contact.setNome((String) updates.get("nome"));
        }
        if (updates.containsKey("telefone")) {
            contact.setTelefone((String) updates.get("telefone"));
        }
        if (updates.containsKey("email")) {
            contact.setEmail((String) updates.get("email"));
        }

        // Salva e retorna o contato atualizado
        contactRepository.save(contact);
        return ResponseEntity.ok(contact);
    }
}