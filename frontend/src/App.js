import './App.css';
import Header from './Header';
import React, { useEffect, useState } from 'react';


function App() {

  const searchBar = {
    search: '',
    keyWord: ''
  }

  const [btnCadastrar, setBtnCadastrar] = useState(true);
  const [searchBars, setsearchBars] = useState([]);
  const [objsearchBar, setObjsearchBar] = useState(searchBar);


  const digit = (event) => {
    setObjsearchBar({...objsearchBar, keyWord: event.target.value, param: event.target.name});
  }
  const search = () => {
    fetch(`http://localhost:8080/locations/search?keyWord=${objsearchBar.keyWord}&searchParam=${objsearchBar.param}`,{
      method: 'Get',
      Headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },    
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido =>{
      console.log(retorno_convertido);
    })
  }
  


  return (
    <div>
      <Header keyBoardEvent={digit} search={search}/>
    </div>
  );
}

export default App;
