import React from 'react'
import './WeekSelectorBar.css'
const WeekSelectorBar = ({currentWeek, goToPreviousWeek, goToNextWeek}) => {
    return(
        <div className="WeekSelectorBar">
            <div className="changeWeekButton">
                <button onClick={() => goToPreviousWeek()}>&lt;</button>
            </div>
            <div className="weekDisplay" onClick={() => goToNextWeek()}>Week {currentWeek}</div>
            <div className="changeWeekButton">
                <button onClick={goToNextWeek}>&gt;</button>
            </div>
        </div>
    )
}

export default WeekSelectorBar