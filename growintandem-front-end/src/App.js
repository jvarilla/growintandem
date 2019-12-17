import React from 'react';
import Navigation from './components/Navigation/Navigation';
import WateringScheduleCalendar from './components/WateringScheduleCalendar/WateringScheduleCalendar';


// TODO: The default start date for the schedule is next Monday per the requirements
let today = new Date(Date.now())

// Next Monday
const START_DATE_DURATION_DEFAULT_SETTING = 
  today.setDate(today.getDate() + (1 + 7 - today.getDay()) % 7).toString();



 // plants/123/watering-schedule?weeks=12&start-date=2003-11-20T11:11:11Z&allow-weekends=false'
class App extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      route: 'plants',
      calendarSchedule: {}
    }
  }



  onRouteChange = (route) => {
    this.setState({route: route});
  }

 

  
  render() {
    return (
      <div className="App">
        
        <Navigation onRouteChange={this.onRouteChange} />
        
        <WateringScheduleCalendar 
          calendarSchedule = {this.state.calendarSchedule}
          route={this.state.route}/>
      </div>
    );
  }
  
}

export default App;