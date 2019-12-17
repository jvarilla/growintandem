import React from 'react'

class DailyView extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            schedule: props.schedule.length < 1 ?  [] : this.reformatSchedule(props.schedule),
            currentDay: Date.now(),
            firstDayOfSchedule: Date.now(),
            lastDayOfSchedule: Date.now()
        }
    }

    reformatSchedule(schedule) {
        // Sort the schedule in asc by date
        schedule.sort(( dateA, dateB) => new Date(dateA.date) - new Date(dateB.date))
        
        // Find the first day and last dayof the schedule
        let firstDate = schedule[0].date !== undefined ? new Date(schedule[0].date) : new Date(Date.now())
        let lastDate = schedule[schedule.length - 1].date !== undefined ? 
                        new Date(schedule[schedule.length - 1].date) : new Date(Date.now())

        // Add all items to the calendar obj
        let scheduleCalendar = [] // 
        // [
        //     {
        //         week: 1,
        //         startDate: "12-16-2019",
        //         endDate:  "12-21-2019",
        //         days: [
        //             {
        //                 day: "Monday",
        //                 date: "12-16-2019",
        //                 plants: [
        //                     { id: "123", name: "Money Plant"  }
        //                 ]
        //             }
        //         ]
                    
        //     }
        // ]

        let currentDate = firstDate
        let weekNumber = 1
        let dayNumber = 1

        let currentWeekNumber = 1
        const daysOfWeek = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"]
        let currentWeek = {}
        let startDate = currentDate
        // Add an entry for each day in the schedule
        while (currentDate <= lastDate) {
            
            let currentWeek = {}
            currentWeek.week = weekNumber
            currentWeek.startDate = currentDate
            while (currentDate.getDay() < 0) {
                let currentDay = {}
                currentDay.day = daysOfWeek[currentDate.getDay()]
                currentDay.date = currentDate
                currentDay.plants = []

                if (currentDate.getDay() == 6) {
                    currentWeek.endDate = currentDate
                }

                currentDate.setDate(currentDate.getDate() + 1)
            }
            
            scheduleCalendar.push(currentWeek)
        }

        console.log('MMM', scheduleCalendar)
        return scheduleCalendar
    }
    componentDidUpdate() {
        // change the
        console.log('AAAAAA', this.state.schedule)
        if (this.state.schedule.length > 1) {
            this.setState({schedule: this.reformatSchedule(this.state.schedule)})
        }
    }

    render() {
        return(
            <div>
                {JSON.stringify(this.state.schedule)}
            </div>
        )
    }


}

export default DailyView