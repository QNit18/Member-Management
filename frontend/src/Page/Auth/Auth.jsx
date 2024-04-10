import React, { useState } from "react";
import "./Auth.css"
import Signin from "./Signin";
import Signup from "./Sigup";

const Auth = () => {
  const [isRegister, setIsRegister] = useState(false);
  const togglePanel = () => {
    setIsRegister(!isRegister);
  };

  return (
    <div className="flex justify-center h-screen items-center overflow-hidden">
      <div className="box lg:max-w-4xl">
        <div className={`cover ${isRegister ? "rotate-active" : ""}`}>
          <div className="front">
            <img
              src="https://images.unsplash.com/photo-1710631245967-579e5a5bf021?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwzfHx8ZW58MHx8fHx8"
              alt=""
            />
            <div className="text">
              <span className="text-1">
                Success is built upon well-organized task
              </span>
              <span className="text-2 text-xs">Let's get connected</span>
            </div>
          </div>
          <div className="back">
            <img
              src="https://plus.unsplash.com/premium_photo-1695186450461-777ea482f34b?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwzN3x8fGVufDB8fHx8fA%3D%3D"
              alt=""
            />
          </div>
        </div>
        <div className="forms h-full">
          <div className="form-content h-full">
            <div className="login-form">
              <Signin togglePanel={togglePanel}/>
            </div>
            <div className="signup-form">
              <Signup togglePanel={togglePanel}/>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Auth;
