import './App.css';
import Formulario from './Formulario';
import Tabela from './Tabela';
import React, { useEffect, useState } from 'react';

function App() {

  const produto = {
    codigo: 0,
    nome: "",
    marca: ""
  }

  const [btnCadastrar, setBtnCadastrar] = useState(true);
  const [produtos, setProdutos] = useState([]);
  const [objProduto, setObjProduto] = useState(produto);

  useEffect(() => {
    fetch("http://localhost:8080/listar")
    .then(retorno => retorno.json())
    .then(retorno_convertido => setProdutos(retorno_convertido));
  }, []);

  const aoDigitar = (evento) => {
    setObjProduto({...objProduto, [evento.target.name]: evento.target.value});
  }

  const cadastrar = () => {
    fetch("http://localhost:8080/cadastrar", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify(objProduto)
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido =>{
      if(retorno_convertido.mensagem !== undefined){
        alert(retorno_convertido.mensagem);
      }else{
        setProdutos([...produtos, retorno_convertido]);
        alert("Produto cadastrado com sucesso!");
        limparFormulario();
      }
    })
  }

  const excluir = () => {
    fetch("http://localhost:8080/excluir/"+ objProduto.codigo, {
      method: "delete",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido => {
      alert(retorno_convertido.mensagem);

      let vetorTemp = [...produtos];

      let index = vetorTemp.findIndex((p)=>{
        return p.codigo === objProduto.codigo;  
      });

      vetorTemp.splice(index, 1);

      setProdutos(vetorTemp);
      limparFormulario();
    })
  }

  const alterar = () => {
    fetch("http://localhost:8080/alterar", {
      method: "post",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify(objProduto)
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido => {
      if(retorno_convertido.mensagem !== undefined){
        alert(retorno_convertido.mensagem);
      }else{
        alert("Produto alterado com sucesso!");

        let vetorTemp = [...produtos];

        let index = vetorTemp.findIndex((p)=>{
          return p.codigo === objProduto.codigo;  
        });
  
        vetorTemp[index] = objProduto;
  
        setProdutos(vetorTemp);

        limparFormulario();
      }
    })
  }

  const limparFormulario = () => {
    setObjProduto(produto);
    setBtnCadastrar(true);
  }

  const selecionarProduto = (indice) => {
    setObjProduto(produtos[indice]);
    setBtnCadastrar(false);
  }


  return (
    <div>
      <Formulario botao={btnCadastrar} eventoTeclado={aoDigitar} cadastrar={cadastrar} obj={objProduto} cancelar={limparFormulario} excluir={excluir} alterar={alterar}/>
      <Tabela vetor={produtos} selecionar={selecionarProduto}/>
    </div>
  );
}

export default App;
