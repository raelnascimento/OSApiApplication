/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eti.kge.OSApiApplication.api.controller;

import br.eti.kge.OSApiApplication.api.exceptionhandler.ProblemaException;
import br.eti.kge.OSApiApplication.domain.exception.DomainException;
import br.eti.kge.OSApiApplication.domain.model.Cliente;
import br.eti.kge.OSApiApplication.domain.repository.ClienteRepository;
import br.eti.kge.OSApiApplication.domain.service.ClienteService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author devsys-a
 */

@RestController
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/clientes/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId ){
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
        
        //return clienteRepository.findAll();
        //return clienteRepository.findByNome("KGe");
        //return clienteRepository.findByNomeContaining("Silva");
    }
    
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
    
        return clienteService.salvar(cliente);
    }
    
    @PutMapping("/clientes/{clienteId}")
    public ResponseEntity<Cliente> atualizar (@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){
        
    //Verifica se o cliente Existe
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
    
        cliente.setId(clienteId);
        cliente = clienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    
    }
    
    @DeleteMapping("/clientes/{clienteID}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteID){
        //Verifica se cliente existe ou não
        
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        clienteService.excluir(clienteID);
        return ResponseEntity.noContent().build();
    }
        
    
    
}
