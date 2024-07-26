package com.start.produtos.service;

import com.start.produtos.model.ProdutoModel;
import com.start.produtos.model.RespostaModel;
import com.start.produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository pr;

    @Autowired
    private RespostaModel rm;

    public Iterable<ProdutoModel> listar(){
        return pr.findAll();
    }

    public ResponseEntity<?> cadastrarAlterar(ProdutoModel pm, String action){
        if(pm.getNome().isEmpty()){
            rm.setMensagem("O nome do produto é obrigatório!");
            return new ResponseEntity<RespostaModel>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getMarca().isEmpty()) {
            rm.setMensagem("O nome da marca é obrigatória!");
            return new ResponseEntity<RespostaModel>(rm, HttpStatus.BAD_REQUEST);
        }else {
            if(action.equals("cadastrar")) {
                return new ResponseEntity<ProdutoModel>(pr.save(pm), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<ProdutoModel>(pr.save(pm), HttpStatus.OK);
            }
        }
    }

    public ResponseEntity<RespostaModel> excluir(Long id){
        pr.deleteById(id);

        rm.setMensagem("O produto foi removido com sucesso!");
        return new ResponseEntity<RespostaModel>(rm, HttpStatus.OK);
    }
}
