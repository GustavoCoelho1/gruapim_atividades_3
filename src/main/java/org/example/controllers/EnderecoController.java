package com.exemplo.api.controllers;

import com.exemplo.api.models.Address;
import com.exemplo.api.models.Contact;
import com.exemplo.api.repositories.AddressRepository;
import com.exemplo.api.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class EnderecoController {

    @Autowired
    private EnderecoRepository addressRepository;

    @Autowired
    private ContatoRepository contactRepository;

    // Desafio 1: Criando rota para adicionar um endereço a um contato
    @PostMapping("/{contactId}/addresses")
    public ResponseEntity<Address> createAddress(@PathVariable Long contactId, @RequestBody Address address) {
        Optional<Contact> contact = contactRepository.findById(contactId);

        if (contact.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Vincula o endereço ao contato e salva
        address.setContact(contact.get());
        Address savedAddress = addressRepository.save(address);

        return ResponseEntity.ok(savedAddress);
    }

    // Desafio 1: Rota GET para listar todos os endereços de um contato específico
    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<Address>> getAddressesByContactId(@PathVariable Long id) {
        // Verifica se o contato existe antes de buscar os endereços
        if (!contactRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        List<Address> addresses = addressRepository.findByContactId(id);
        return ResponseEntity.ok(addresses);
    }
}