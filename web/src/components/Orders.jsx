import React from 'react';

import { Redirect } from 'react-router-dom'
import { getPendingOrdersFrom } from '../api/api'
import { getHistoricOrdersFrom } from '../api/api'
import logo, { ReactComponent } from './logo.svg';
import './css/Orders2.css';
import './css/shop-homepage.css';

export default class Orders extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          pendingOrders: [],  
          historicOrders: []
        };
        if (this.props.location.state !== undefined){
          this.state.id = this.props.location.state.id;  //Chequeo que haya venido una props
        }
      }
    priceOfOrder(order){      
      var total = 0;
      order.menus.map(element => { total = total + element.price });
      return total;  
    }

    componentDidMount(){
       getPendingOrdersFrom(this.state.id)
        .then(result => { 
          this.setState({pendingOrders: result})});
      getHistoricOrdersFrom(this.state.id)
        .then(result => {
          this.setState({historicOrders: result})});      
    }

    render() {
      const mappingOrderCode = (order) => (<li key={order.code_order_complete}>
                                          <div className="container">
                                            <div className="row mt-4">
                                             <div className="col-md-3 text-center">
                                                <img src={logo} className="App-logo" alt="logo" />
                                                  <div className = "row"></div>
                                                  <div className="card h-100">
                                                  <h3>Código Orden: {order.code_order_complete}</h3>
                                                  </div>
                                                  <div className="card-body">
                                                    <p><h5>Restaurant: {order.restaurantName}</h5></p>
                                                    <p><mark>{order.menus.map(itMenus => (<li key={itMenus.code}>
                                                                                                            <p><h6>Menú: {itMenus.name}</h6></p>
                                                                                                            <p>Cantidad: {itMenus.ammountOfMenus}</p>
                                                                                                            <p>Precio: {itMenus.price} $</p>

                                                                                         </li>))}
                                                      <br></br>
                                                      <p><h4>Precio Total: {this.priceOfOrder(order)} $</h4></p>
                                                      </mark>
                                                    </p>
                                                    <button className="btn btn-danger">Cancelar</button>
                                                  </div> 
                                                </div>
                                              </div>  
                                            </div>
                                          
                                        </li>)
      
      if (this.state.id === ''){
        return <Redirect to={'/'}/> //Caso que se entre directamente a /orders
      }
        return(
            <div> 
                <div className = "my-4">
                <div><h1>{this.state.id} LOGUEADO </h1></div>  
                <br></br>
                </div>
                <div><h3>Ordenes Pendientes</h3></div>
                  <div>
                    
                    <ul>
                      {this.state.pendingOrders.map(mappingOrderCode)}
                    </ul>
                  </div>
                  <br></br>
                <div><h3>Ordenes Históricas</h3></div>
                  <ul>
                    {this.state.historicOrders.map(mappingOrderCode)}
                  </ul>
            </div>    
        )}
  }