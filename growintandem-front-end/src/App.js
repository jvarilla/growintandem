import React from 'react';
import logo from './logo.svg';
import './App.css';
import WateringSchedule from './components/WateringSchedule/WateringSchedule';
import Plant from './components/Plant/Plant';
import Navigation from './components/Navigation/Navigation';
import PlantView from './components/Views/PlantView/PlantView'
import DailyView from './components/Views/DailyView/DailyView';
import WateringScheduleCalendar from './components/WateringScheduleCalendar/WateringScheduleCalendar';
const API_BASE = 'http://localhost:7777/api/v1'
const ALLOW_WEEKENDS_DEFAULT_SETTING = "false"
const SCHEDULE_WEEKS_DURATION_DEFAULT_SETTING = 12

// TODO: The default start date for the schedule is next Monday per the requirements
let today = Date.now()
const START_DATE_DURATION_DEFAULT_SETTING = Date.now() 



 // plants/123/watering-schedule?weeks=12&start-date=2003-11-20T11:11:11Z&allow-weekends=false'
class App extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      route: 'plants',
      calendarSchedule: {}
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

  onRouteChange = (route) => {
    this.setState({route: route});
  }

 

  
  render() {
    return (
      <div className="App">
        <Navigation onRouteChange={this.onRouteChange} />
        <header className="App-header">
        
        <WateringScheduleCalendar 
          route={this.state.route}/>
    
        </header>
        
      </div>
    );
  }
  
}
// { JSON.stringify(this.state.calendarSchedule) }
export default App;

//  { this.displayView(this.state.route) }
// { 
//   this.state.plants.map((plant, idx) => {
//     return  (
//       <WateringSchedule 
//         key = { idx }
//         schedule={this.state.wateringSchedule[plant.id]} 
//       />);
//   })   
// }