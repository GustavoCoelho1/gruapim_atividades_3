package com.exemplo.api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Desafio 2: Anotações de validação garantem que os dados estejam corretos antes de salvar
    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "O telefone não pode estar vazio")
    @Size(min = 8, max = 15, message = "O telefone deve ter entre 8 e 15 caracteres")
    private String telefone;

    @NotBlank(message = "O e-mail não pode estar vazio")
    @Email(message = "O e-mail deve ter um formato válido")
    private String email;

    // Desafio 1: Relação 1:N com Endereços.
    // mappedBy indica que a relação é gerenciada pelo campo 'contact' na classe Address.
    // cascade = CascadeType.ALL permite salvar endereços junto com o contato.
    // @JsonManagedReference evita o loop infinito ao serializar para JSON.
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Address> addresses = new ArrayList<>();

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Address> getAddresses() { return addresses; }
    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }
}