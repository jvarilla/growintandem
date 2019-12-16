import React from 'react';
import logo from './logo.svg';
import './App.css';
import WateringSchedule from './components/WateringSchedule/WateringSchedule';
import Plant from './components/Plant/Plant';

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
      plants: [],
      wateringSchedule: []
    }
  }

  componentDidMount() {
    fetch(`${API_BASE}/plants/`)
    .then(response => response.json())
    .then(responseObj => {
      console.log(responseObj)
      this.setState({plants: responseObj})
      return responseObj
    })
  }


  render() {
    return (
      <div className="App">
      
     
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
         
          {
            this.state.plants.map((plant) => {
              return (
                <Plant 
                  key={plant.id}
                  id={plant.id}
                  name={plant.name}
                  waterEveryNumDays={plant.waterEveryNumDays}
                  />
              )
            })
          }

          {
            this.state.wateringSchedule.map((plantWateringSchedule, idx) => {
              console.log('plant', plantWateringSchedule)
              return <WateringSchedule 
                key={idx}
                schedule={plantWateringSchedule} />
            })
          }
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
      </div>
    );
  }
  
}

export default App;

// { 
//   this.state.plants.map((plant, idx) => {
//     return  (
//       <WateringSchedule 
//         key = { idx }
//         schedule={this.state.wateringSchedule[plant.id]} 
//       />);
//   })   
// }