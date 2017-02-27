/**
 * Created by alexc on 27.02.17.
 */
import React from 'react';
import Chart from 'react-c3';

class Chart1 extends React.Component {
    render() {
        let chartData = {
            columns: [
                ['data1', 75],
                ['data2', 42]
            ]
        }

        return (
            <div className="dashboard-component container">
                <div id="testchart"></div>
                <Chart data={chartData} element='testchart' type='pie'/>
            </div>
        )
    }
}