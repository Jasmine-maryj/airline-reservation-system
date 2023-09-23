import React from 'react';

export default function Hero() {
    const imageStyle = {
        opacity: 0.5,
    };
    const textStyle = {
        marginTop: '95px', // Use camelCase notation and enclose the value in quotes
    };
    const cardStyle = {
        marginTop: 50,
    }
    return (
        <div class="card" style={cardStyle}>
            <img src="/HeaderImage-1.png" height={580} width={700} class="card-img" alt="AIRLINE RESERVATION SYSTEM" style={imageStyle}/>
                <div class="card-img-overlay">
                    <h1 class="card-title" style={textStyle}>WelCome To AIRLINE RESERVATION SYSTEM</h1>
                </div>
        </div>
    )
}