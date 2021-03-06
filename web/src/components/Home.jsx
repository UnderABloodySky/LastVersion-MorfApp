import React from 'react';
import Navbar from './homeComponents/Navbar';
import Body from './homeComponents/Body';
import Footer from './homeComponents/Footer';

export default class Home extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: ''
    }
    };

  render() {
    return (
      <div>    
        <Navbar />
        <Body />
        <Footer />
        {this.state.error && <div>{this.state.error}</div>}
      </div>
    );
  }
}