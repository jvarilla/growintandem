import React from 'react'

const daysOfWeek = ['Not a day', 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
class WateringScheduleDay extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            plantsToWater: props.plantsToWater || [],
            dayOfWeek: "Sun",
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
                        <p>You're all good, there are no plant to water</p>
                        </div>)
            } else {
                return (
                    <div>
                    <h4>{this.state.dayOfWeek} -  {this.getReadableDate(this.state.date)}</h4>
                    <p>{
                        this.state.plantsToWater.map((plant, idx) => {
                            return( <div key={idx}> {plant.plantName} </div>)
                    })
                 }</p>
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