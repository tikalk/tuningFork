/**
 * Created by alexc on 27.02.17.
 */
import React from 'react';
import {Table, TableBody, TableFooter, TableHeader, TableHeaderColumn, TableRow, TableRowColumn}
    from 'material-ui/Table';
const tableData = [
                    {
                      "app_id": 1,
                      "i_cpu": 8,
                      "i_ram": 8,
                      "i_xx": 2048,
                      "i_th": 3,
                      "o_cpu": 5,
                      "o_ram": 5,
                      "o_syload": 3,
                      "o_thpic": 4,
                      "o_gc": 5,
                      "o_thput": 240,
                      "o_lt": 0.4
                    },
                    {
                      "app_id": 2,
                      "i_cpu": 8,
                      "i_ram": 8,
                      "i_xx": 2048,
                      "i_th": 3,
                      "o_cpu": 5,
                      "o_ram": 5,
                      "o_syload": 3,
                      "o_thpic": 4,
                      "o_gc": 5,
                      "o_thput": 220,
                      "o_lt": 0.3
                    },
                    {
                      "app_id": 3,
                      "i_cpu": 8,
                      "i_ram": 8,
                      "i_xx": 2048,
                      "i_th": 3,
                      "o_cpu": 5,
                      "o_ram": 5,
                      "o_syload": 3,
                      "o_thpic": 4,
                      "o_gc": 5,
                      "o_thput": 230,
                      "o_lt": 0.1
                    },
                    {
                      "app_id": 4,
                      "i_cpu": 8,
                      "i_ram": 8,
                      "i_xx": 2048,
                      "i_th": 3,
                      "o_cpu": 5,
                      "o_ram": 5,
                      "o_syload": 3,
                      "o_thpic": 4,
                      "o_gc": 5,
                      "o_thput": 230,
                      "o_lt": 0.1
                    }
                  ];


export default class Table1 extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            fixedHeader: true,
            fixedFooter: true,
            stripedRows: false,
            showRowHover: false,
            selectable: true,
            multiSelectable: false,
            enableSelectAll: false,
            deselectOnClickaway: true,
            showCheckboxes: true,
            height: '300px',
        };
    }

    handleToggle = (event, toggled) => {
        this.setState({
            [event.target.name]: toggled,
        });
    };

    handleChange = (event) => {
        this.setState({height: event.target.value});
    };

    render() {
        return (
            <div>
                <Table
                    height={this.state.height}
                    fixedHeader={this.state.fixedHeader}
                    fixedFooter={this.state.fixedFooter}
                    selectable={this.state.selectable}
                    multiSelectable={this.state.multiSelectable}
                >
                    <TableHeader
                        displaySelectAll={this.state.showCheckboxes}
                        adjustForCheckbox={this.state.showCheckboxes}
                        enableSelectAll={this.state.enableSelectAll}
                    >
                        <TableRow>
                            <TableHeaderColumn tooltip="ID">ID</TableHeaderColumn>
                            <TableHeaderColumn tooltip="Input CPU">Input CPU</TableHeaderColumn>
                            <TableHeaderColumn tooltip="Input RAM">Input RAM</TableHeaderColumn>
                            <TableHeaderColumn tooltip="Input Throughput">Threads</TableHeaderColumn>
                            <TableHeaderColumn tooltip="Output CPU">Output CPU</TableHeaderColumn>
                            <TableHeaderColumn tooltip="Output RAM">Output RAM</TableHeaderColumn>
                            <TableHeaderColumn tooltip="System Load">System Load</TableHeaderColumn>
                            <TableHeaderColumn tooltip="Out Throughput">Out Throughput</TableHeaderColumn>
                        </TableRow>
                    </TableHeader>
                    <TableBody
                        displayRowCheckbox={this.state.showCheckboxes}
                        deselectOnClickaway={this.state.deselectOnClickaway}
                        showRowHover={this.state.showRowHover}
                        stripedRows={this.state.stripedRows}
                    >
                        {tableData.map( (row, index) => (
                            <TableRow key={index} selected={row.selected}>
                                <TableRowColumn>{row.app_id}</TableRowColumn>
                                <TableRowColumn>{row.i_cpu}</TableRowColumn>
                                <TableRowColumn>{row.i_ram}</TableRowColumn>
                                <TableRowColumn>{row.i_th}</TableRowColumn>
                                <TableRowColumn>{row.o_cpu}</TableRowColumn>
                                <TableRowColumn>{row.o_ram}</TableRowColumn>
                                <TableRowColumn>{row.o_syload}</TableRowColumn>
                                <TableRowColumn>{row.o_thput}</TableRowColumn>
                            </TableRow>
                        ))}
                    </TableBody>
                    <TableFooter
                        adjustForCheckbox={this.state.showCheckboxes}
                    >
                        <TableRow>
                            <TableRowColumn tooltip="ID">ID</TableRowColumn>
                            <TableRowColumn tooltip="Input CPU">Input CPU</TableRowColumn>
                            <TableRowColumn tooltip="Input RAM">Input RAM</TableRowColumn>
                            <TableRowColumn tooltip="Input Throughput">Threads</TableRowColumn>
                            <TableRowColumn tooltip="Output CPU">Output CPU</TableRowColumn>
                            <TableRowColumn tooltip="Output RAM">Output RAM</TableRowColumn>
                            <TableRowColumn tooltip="System Load">System Load</TableRowColumn>
                            <TableRowColumn tooltip="Out Throughput">Out Throughput</TableRowColumn>
                        </TableRow>
                    </TableFooter>
                </Table>

            </div>
        );
    }
}