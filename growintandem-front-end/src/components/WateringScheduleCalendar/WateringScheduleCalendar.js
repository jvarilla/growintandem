import React from 'react'
import WateringScheduleWeek from './WateringScheduleWeek/WateringScheduleWeek'
const API_BASE = 'http://localhost:7777/api/v1'

class WateringScheduleCalendar extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            calendarSchedule: {},
            route: props.route
        }
    } 

    
    loadWateringWeeks = () => {
        try {
            return (
                <div>
                {
                    this.state.calendarSchedule.weeks.map((week, idx) => {
                        return( 
                            <div>
                                <h2>{week.weekNum}</h2>
                                <WateringScheduleWeek
                                    key = {idx}
                                    weekSchedule = {week}
                                />
                            </div>
                            )
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
            <div className="WateringScheduleCalendar">
                {
                   this.loadWateringWeeks()
                }
            </div>
        )
    }


}

export default WateringScheduleCalendar