import React from 'react'

class WateringSchedule extends React.Component {
    constructor(props) {
        super(props)
        console.log('props', props)
        this.state = {
            schedule: props.schedule? props.schedule : {watering_schedule: []}
        }
    }

    componentDidMount() {
        console.log('sub', this.state.schedule)
    }
    
    getSchedule = (obj) => {
        if (obj === undefined) {
            return (<div>Loading...</div>)
        } else {
            console.log('SCHEDULE', obj)
            if (obj["watering_schedule"] === undefined) {
                return (<div>{JSON.stringify(obj)}</div>)
            } else {
                return obj["watering_schedule"].map((date,idx) => {
                    return (
                        <div key={idx} >{date}</div>
                    ) })
            }
        }
    }
    render() {
        return(
            <div>
                {this.getSchedule(this.state.schedule)}
            </div>
        )
    }
}

export default WateringSchedule

         //     {
            //        !this.state.schedule === undefined ? this.state.schedule["watering_schedule"].map((date,idx) => {
            //             return (
            //                 <div key={idx} >{date}</div>
            //             ) 
                    
            //     }) : <div> loading </div>
            // }