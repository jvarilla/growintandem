import React from 'react'

const API_BASE = 'http://localhost:7777/api/v1'

class WateringScheduleCalendar extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            calendarSchedule: {},
            route: props.route
        }
    } 

    
    loadWeekItems = () => {
        try {
            return (
                <div>
                {
                    this.state.calendarSchedule.weeks.map((week, idx) => {
                        return( <div key={idx}> {week.startDate} | Week: {week.weekNum} </div>)
                })
             }
                </div>)
        } catch(err) {
           return (<div>Loading...</div>)
        }
    }
    componentDidMount() {
        // Get all the plant watering schedules
        fetch(`${API_BASE}/plants/all/watering-schedule?weeks=12&start-date=2003-11-20T11:11:11Z&allow-weekends=true`)
        .then(response => response.json())
        .then(responseObj => {
        console.log(responseObj)
        this.setState({calendarSchedule: responseObj})
        })
    }

    render() {
        return(
            <div>
                {
                   this.loadWeekItems()
                }
            </div>
        )
    }


}

export default WateringScheduleCalendar