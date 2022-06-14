/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eti.kge.OSApiApplication.api.controller;

import br.eti.kge.OSApiApplication.domain.model.OrdemServico;
import br.eti.kge.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.eti.kge.OSApiApplication.domain.service.OrdemServicoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author devsys-a
 */

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {
    
    @Autowired
    private OrdemServicoService ordemServicoService;
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar (@RequestBody OrdemServico ordemServico){
        return ordemServicoService.criar(ordemServico);
    }
    
    @GetMapping
    public List <OrdemServico> lista(){
        return ordemServicoRepository.findAll();
    } 
    
    @GetMapping("/{osID}")
    public OrdemServico listaID(@PathVariable long osID){
        Optional <OrdemServico> ordemServico = ordemServicoRepository.findById(osID);
        return ordemServico.orElse(null);
    } 
    @DeleteMapping("/{osID}")
    public OrdemServico deletar(@PathVariable long osID){
        Optional <OrdemServico> ordemServico = ordemServicoRepository.findById(osID);
        return ordemServico.orElse(null);
    } 
}
