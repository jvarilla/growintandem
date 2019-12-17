import React from 'react'

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
                        return( <div key={idx}> {day.date} | Day of Week: {day.dayOfWeek} </div>)
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