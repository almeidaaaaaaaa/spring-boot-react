package com.start.produtos.contoller;

import com.start.produtos.model.ProdutoModel;
import com.start.produtos.model.RespostaModel;
import com.start.produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoService ps;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ProdutoModel pm){
        return ps.cadastrarAlterar(pm,"cadastrar");
    }

    @PostMapping("/alterar")
    public ResponseEntity<?> alterar(@RequestBody ProdutoModel pm){
        return ps.cadastrarAlterar(pm,"alterar");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<RespostaModel> remover(@PathVariable long id){
        return  ps.excluir(id);
    }

    @GetMapping("/listar")
    public Iterable<ProdutoModel> listar(){
        return ps.listar();
    }

    @GetMapping("/")
    public String rota() {
        return "API de produtos funcionando";
    }
}
