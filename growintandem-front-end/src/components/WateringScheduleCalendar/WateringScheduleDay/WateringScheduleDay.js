import React from 'react'
import './WateringScheduleDay.css'

const daysOfWeek = ['Not a day', 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
class WateringScheduleDay extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            plantsToWater: props.plantsToWater || [],
            dayOfWeek: props.dayOfWeek || "Sun",
            date: props.date
        }
    } 

    getReadableDate = (date) =>  {
        date = new Date(date)
        return `${date.getMonth() + 1}/${date.getDate()}/${date.getFullYear()}`
    }
    loadPlantsToWater = () => {
        try {
            if (this.state.plantsToWater.length < 1) {
                return(<div>
                        <h4>{this.state.dayOfWeek} -  {this.getReadableDate(this.state.date)}</h4>
                        <p>No plants to water, you're free!</p>
                        </div>)
            } else {
                return (
                    <div>
                    <h4>{this.state.dayOfWeek} -  {this.getReadableDate(this.state.date)}</h4>
                    {
                        this.state.plantsToWater.map((plant, idx) => {
                            return( <div className="WateringScheduleItem" key={idx}> {plant.plantName} </div>)
                    })
                 }
                    </div>)
            }
        } catch(err) {
           return (<div>Loading...</div>)
        }
    }

    render() {
        return(
            <div className="WateringScheduleCalendar">
                {
                    this.loadPlantsToWater()
                }
            </div>
        )
    }


}

export default WateringScheduleDay