import React from 'react';

import NavBarItem from '../homeComponents/NavBarItem';
//Nostros Restaurants Menus Contacto Registro

var items = [ {"code":"0", "name":"Nosotros"},
              {"code":"1", "name":"Restaurant"},
              {"code":"2", "name":"Menus"},
              {"code":"3", "name":"Contacto"},
              {"code":"4", "name":"Registro"}
            ]


export default class Navbar extends React.Component {

render() {
  return (

<nav className="navbar navbar-expand-lg navbar-light bg-light fixed-top">
  <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
    <span className="navbar-toggler-icon"></span>
    <span className="navbar-toggler-icon"></span>
    <span className="navbar-toggler-icon"></span>
    <span className="navbar-toggler-icon"></span>
    <span className="navbar-toggler-icon"></span>
  </button>
  <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
    <a clasNames="navbar-brand" href="#">Home</a>
    <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
    {items.map(function(currentValue, index, array){
      return <NavBarItem key={currentValue.code}
                         name={currentValue.name}
                         isFirstOne={index==0? true : false}/>;  
    })}

    </ul>
    <form className="form-inline my-2 my-lg-0">
      <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"/>
      <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
    </form>
    </div>
    </nav>
    );
  }
}