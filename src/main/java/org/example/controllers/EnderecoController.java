package com.exemplo.api.controllers;

import com.exemplo.api.models.Endereco;
import com.exemplo.api.models.Contato;
import com.exemplo.api.repositories.EnderecoRepository;
import com.exemplo.api.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    // Desafio 1: Criando rota para adicionar um endereço a um contato
    @PostMapping("/{contatoId}/enderecos")
    public ResponseEntity<Endereco> createEndereco(@PathVariable Long contatoId, @RequestBody Endereco endereco) {
        Optional<Contato> contato = contatoRepository.findById(contatoId);

        if (contato.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Vincula o endereço ao contato e salva
        endereco.setContato(contato.get());
        Endereco savedEndereco = enderecoRepository.save(endereco);

        return ResponseEntity.ok(savedEndereco);
    }

    // Desafio 1: Rota GET para listar todos os endereços de um contato específico
    @GetMapping("/{id}/enderecos")
    public ResponseEntity<List<Endereco>> getEnderecoesByContatoId(@PathVariable Long id) {
        // Verifica se o contato existe antes de buscar os endereços
        if (!contatoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        List<Endereco> enderecos = enderecoRepository.findByContatoId(id);
        return ResponseEntity.ok(enderecos);
    }
}