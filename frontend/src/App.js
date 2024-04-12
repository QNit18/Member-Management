import { useState } from "react";
import "./App.css";
import Auth from "./Page/Auth/Auth";
import Home from "./Page/Home/Home";
import Navbar from "./Page/Navbar/Navbar";
import { darkTheme } from "./theme/darkheme";
import { ThemeProvider } from "@emotion/react";
import {useDispatch, useSelector} from "react-redux"
import { useEffect } from "react";
import { fetchTasks } from "./ReduxToolkit/TaskSlice";
import { getUserProfile } from "./ReduxToolkit/AuthSlice";

function App() {
  const user = true;

  const dispatch = useDispatch();
  const {task, auth} = useSelector(store => store);

  useEffect(() => {
    dispatch(fetchTasks({}));
    dispatch(getUserProfile(auth.jwt || localStorage.getItem("jwt")));
  }, [auth.jwt]);

  return (
    <ThemeProvider theme={darkTheme}>
      {auth.user ? <div>
        <Navbar />
        <Home />
      </div> :  <Auth />}
    </ThemeProvider>
  );
}

export default App;
