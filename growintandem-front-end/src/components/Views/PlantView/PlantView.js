import React from 'react';

class PlantView extends React.Component {
    constructor(props) {
        super(props)
        this.state = { 

        }

    }

    render() {
        return(
            <div className="PlantView">
                <h1>Plant View</h1>
                {this.props.children}
            </div>
        )
    }
}

export default PlantView