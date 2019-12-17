import React from 'react';

const Navigation = ({ onRouteChange}) => {
        return(
        <nav style={{display: 'flex', justifyContent: 'space-around'}}>
            <p onClick={() => onRouteChange('plants')} className='f3 link dim black underline pa3 pointer'>Plants</p>
            <p onClick={() => onRouteChange('daily')} className='f3 link dim black underline pa3 pointer'>Daily</p>
            <p onClick={() => onRouteChange('weekly')} className='f3 link dim black underline pa3 pointer'>Weekly</p>
        </nav>
        );
}

export default Navigation