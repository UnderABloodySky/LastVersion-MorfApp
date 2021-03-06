import React from 'react';
import NavBarItem from '../homeComponents/NavBarItem';
import SearchForm from './SearchForm';

var items = [ {"code":"0", "name":"Nosotros", "id":"/us"},
              {"code":"1", "name":"Restaurants", "id":"/all_restaurants"},
              {"code":"2", "name":"Menus", "id":"/all_menus"},
              {"code":"3", "name":"Productos", "id":"/products"},
              {"code":"4", "name":"Contacto", "id":"/contact"},
              {"code":"5", "name":"Registro", "id":"/register"}
            ]

export default class Navbar extends React.Component {
  
render() {
  return (
<nav className="navbar navbar-expand-lg navbar-light bg-light sticky-top">
  <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
    <span className="navbar-toggler-icon"></span>
    <span className="navbar-toggler-icon"></span>
    <span className="navbar-toggler-icon"></span>
    <span className="navbar-toggler-icon"></span>
    <span className="navbar-toggler-icon"></span>
  </button>
  <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
    <a className="navbar-brand" href="/">Home</a>
    <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
    {items.map(function(currentValue, index){
      return <NavBarItem key={currentValue.code}
                         name={currentValue.name}
                         id={currentValue.id}
                         isFirstOne={index===0? true : false}/>;  
    })}

    </ul>
        <SearchForm fromWhichComponent="navbar"/>
    </div>
    </nav>
    );
  }
}
