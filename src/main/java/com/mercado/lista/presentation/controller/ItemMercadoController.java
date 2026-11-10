package com.mercado.lista.presentation.controller;

import com.mercado.lista.application.service.AlterarStatusItemService;
import com.mercado.lista.application.service.AtualizarItemService;
import com.mercado.lista.application.service.BuscarItemPorIdService;
import com.mercado.lista.application.service.CriarItemService;
import com.mercado.lista.application.service.DeletarItemService;
import com.mercado.lista.application.service.ListarItensService;
import com.mercado.lista.domain.enums.Categoria;
import com.mercado.lista.domain.enums.StatusItem;
import com.mercado.lista.presentation.request.AlterarStatusRequest;
import com.mercado.lista.presentation.request.ItemMercadoRequest;
import com.mercado.lista.presentation.resource.ItemMercadoResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/itens")
@Tag(name = "Itens de mercado", description = "CRUD da lista de compras")
public class ItemMercadoController {

    private final CriarItemService criarItemService;
    private final ListarItensService listarItensService;
    private final BuscarItemPorIdService buscarItemPorIdService;
    private final AtualizarItemService atualizarItemService;
    private final AlterarStatusItemService alterarStatusItemService;
    private final DeletarItemService deletarItemService;

    public ItemMercadoController(CriarItemService criarItemService,
                                 ListarItensService listarItensService,
                                 BuscarItemPorIdService buscarItemPorIdService,
                                 AtualizarItemService atualizarItemService,
                                 AlterarStatusItemService alterarStatusItemService,
                                 DeletarItemService deletarItemService) {
        this.criarItemService = criarItemService;
        this.listarItensService = listarItensService;
        this.buscarItemPorIdService = buscarItemPorIdService;
        this.atualizarItemService = atualizarItemService;
        this.alterarStatusItemService = alterarStatusItemService;
        this.deletarItemService = deletarItemService;
    }

    @Operation(summary = "Criar item", description = "Adiciona um item na lista. Todo item novo começa como PENDENTE.")
    @ApiResponse(responseCode = "201", description = "Item criado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<ItemMercadoResource> criar(@Valid @RequestBody ItemMercadoRequest request) {
        var item = criarItemService.executar(request.toInput());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(location).body(ItemMercadoResource.from(item));
    }

    @Operation(summary = "Listar itens", description = "Lista os itens, com filtros opcionais por status e categoria.")
    @GetMapping
    public List<ItemMercadoResource> listar(@Parameter(description = "Filtrar por status") @RequestParam(required = false) StatusItem status,
                                            @Parameter(description = "Filtrar por categoria") @RequestParam(required = false) Categoria categoria) {
        return listarItensService.executar(status, categoria).stream()
                .map(ItemMercadoResource::from)
                .toList();
    }

    @Operation(summary = "Buscar item por id")
    @ApiResponse(responseCode = "200", description = "Item encontrado")
    @ApiResponse(responseCode = "404", description = "Item não encontrado")
    @GetMapping("/{id}")
    public ItemMercadoResource buscarPorId(@PathVariable Long id) {
        return ItemMercadoResource.from(buscarItemPorIdService.executar(id));
    }

    @Operation(summary = "Atualizar item", description = "Substitui nome, quantidade, unidade, categoria e observação.")
    @ApiResponse(responseCode = "200", description = "Item atualizado")
    @ApiResponse(responseCode = "404", description = "Item não encontrado")
    @PutMapping("/{id}")
    public ItemMercadoResource atualizar(@PathVariable Long id,
                                         @Valid @RequestBody ItemMercadoRequest request) {
        return ItemMercadoResource.from(atualizarItemService.executar(id, request.toInput()));
    }

    @Operation(summary = "Alterar status", description = "Marca o item como COMPRADO ou volta para PENDENTE.")
    @ApiResponse(responseCode = "200", description = "Status alterado")
    @ApiResponse(responseCode = "404", description = "Item não encontrado")
    @PatchMapping("/{id}/status")
    public ItemMercadoResource alterarStatus(@PathVariable Long id,
                                             @Valid @RequestBody AlterarStatusRequest request) {
        return ItemMercadoResource.from(alterarStatusItemService.executar(id, request.status()));
    }

    @Operation(summary = "Remover item")
    @ApiResponse(responseCode = "204", description = "Item removido")
    @ApiResponse(responseCode = "404", description = "Item não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarItemService.executar(id);
        return ResponseEntity.noContent().build();
    }
}
