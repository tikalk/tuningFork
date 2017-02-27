import React, {Component} from 'react';
import './App.css';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Sidebar from './components/sidebar/SideBar';
import Table1 from './components/table1/table1';
import AppBar from 'material-ui/AppBar';
import Chart1 from '.components/chart1/chart1';

class App extends Component {
    render() {
        return (
            <MuiThemeProvider>
                <div className="App">
                    <AppBar
                        title="Title"
                        iconClassNameRight="muidocs-icon-navigation-expand-more"
                    />
                    <Sidebar />
                    <p className="App-intro">
                        To get started
                    </p>
                    <Chart1 />
                    <Table1/>

                </div>
            </MuiThemeProvider>
        );
    }
}

export default App;
