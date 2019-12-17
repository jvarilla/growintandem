import React from 'react'
import WateringScheduleWeek from './WateringScheduleWeek/WateringScheduleWeek'
import './WateringScheduleCalendar.css'
import WeekSelectorBar from './WeekSelectorBar/WeekSelectorBar'

const API_BASE = 'http://localhost:7777/api/v1'
const ALLOW_WEEKENDS_DEFAULT_SETTING = "false"
const SCHEDULE_WEEKS_DURATION_DEFAULT_SETTING = 12



class WateringScheduleCalendar extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            calendarSchedule: {},
            route: props.route,
            currentWeek: 1,
        }
    } 

    
    getReadableDate = (date) =>  {
        date = new Date(date)
        return `${date.getMonth() + 1}/${date.getDate()}/${date.getFullYear()}`
    }

    onGoToPreviousWeek = (event) => {
        let newCurrentWeek = this.state.currentWeek
        newCurrentWeek = newCurrentWeek + 1
        if (this.state.currentWeek > 1) {
            this.setState({currentWeek: newCurrentWeek})
        }
    }

    onGoToNextWeek = (event) => {
        let newCurrentWeek = this.state.currentWeek
        newCurrentWeek = newCurrentWeek + 1
            this.setState({currentWeek: newCurrentWeek})
    }

    displayWeek(week) {
        try {
            return (<div>
            <div style={{display:'flex', flexDirection: 'row', justifyContent: 'flex-start'}}> 
                                    <h3> Week {week.weekNum}: 
                                        {this.getReadableDate(week.startDate)}-{this.getReadableDate(week.endDate)}
                                    </h3>
                                </div>
                                <WateringScheduleWeek
                                    weekSchedule = {week}
                                />
            </div>)
            
        } catch (err ) {
            return(<div></div>)
        }
    }

    loadWateringWeeks = () => {
        try {
            return (
                <div className="WateringScheduleCalendar">
                    <h1>Plant Watering Schedule</h1>
                {
                    this.state.calendarSchedule.weeks
                        .map((week, idx) => {
                        return( 
                            <div key = {idx}>
                                <div style={{display:'flex', flexDirection: 'row', justifyContent: 'flex-start'}}> 
                                    <h3> Week {week.weekNum}: 
                                        {this.getReadableDate(week.startDate)}-{this.getReadableDate(week.endDate)}
                                    </h3>
                                </div>
                                    <WateringScheduleWeek
                                    key = {idx}
                                    weekSchedule = {week}
                                    currentWeekNum = {this.state.currentWeek}
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
        let today = new Date(Date.now())
        let nextMonday = new Date(Date.now())
        
        if (nextMonday.getDay() == 1) {
            nextMonday.setDate(nextMonday.getDate() + 1)
        }
        while (nextMonday.getDay() != 1) {
            nextMonday = new Date(nextMonday.setDate(nextMonday.getDate() + 1))
        }

        let START_DATE_DURATION_DEFAULT_SETTING = nextMonday.toISOString()
        // Get all the plant watering schedules
        fetch(`${API_BASE}/plants/all/watering-schedule?weeks=${SCHEDULE_WEEKS_DURATION_DEFAULT_SETTING}&start-date=${START_DATE_DURATION_DEFAULT_SETTING}&allow-weekends=${ALLOW_WEEKENDS_DEFAULT_SETTING}`)
        .then(response => response.json())
        .then(responseObj => {
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