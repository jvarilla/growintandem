import React from 'react'

const API_BASE = 'http://localhost:7777/api/v1'

class Plant extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            id: props.id,
            name: props.name,
            waterEveryNumDays: props.waterEveryNumDays, 
            wateringSchedule: []
        }
    }

    componentDidMount() {
        fetch(`${API_BASE}/plants/${this.state.id}/watering-schedule`)
            .then(response => response.json())
            .then(responseObj => {
                  console.log('response', responseObj)
                  return responseObj.watering_schedule
                })
            .then(wateringSchedule => this.setState({wateringSchedule: wateringSchedule}))
    }

    render() {
        return(
            <div>
                <div>Plant: {this.state.id} | {this.state.name} </div>
                {
                    this.state.wateringSchedule.map((date, idx) => {
                        return(<div key={idx}> {date.toString()} </div> )
                    })
                }
            </div>
        );
    }

}

export default Plant