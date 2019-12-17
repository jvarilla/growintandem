import React from 'react'
import WateringScheduleDay from '../WateringScheduleDay/WateringScheduleDay'

const daysOfWeek = ['Not a day', 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
class WateringScheduleWeek extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            weekSchedule: props.weekSchedule || []
        }
    } 

    
    loadWateringDays = () => {
        try {
            return (
                <div>
                {
                    this.state.weekSchedule.days.map((day, idx) => {
                        return( 
                            <WateringScheduleDay
                                key = {idx}
                                plantsToWater = {day.plants}
                                dayOfWeek = {daysOfWeek[day.dayOfWeek]}
                                date = {day.date}
                            />)
                })
             }
                </div>)
        } catch(err) {
           return (<div>Loading...</div>)
        }
    }

    render() {
        return(
            <div className="WateringScheduleCalendar">
                {
                   this.loadWateringDays()
                }
            </div>
        )
    }


}

export default WateringScheduleWeek