import React, {Component} from 'react';
import './App.css';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Table1 from './components/table1/table1';
import AppBar from 'material-ui/AppBar';

class App extends Component {
    render() {
        return (
            <MuiThemeProvider>
                <div className="App">
                    <AppBar
                        title="Tuning Fork"
                        iconClassNameRight="muidocs-icon-navigation-expand-more"
                    />

                    <p className="App-intro">
                        Metrics and Machine Learning Results
                    </p>

                    <Table1/>

                </div>
            </MuiThemeProvider>
        );
    }
}

export default App;
