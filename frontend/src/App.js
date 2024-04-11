import { useState } from "react";
import "./App.css";
import Auth from "./Page/Auth/Auth";
import Home from "./Page/Home/Home";
import Navbar from "./Page/Navbar/Navbar";
import { darkTheme } from "./theme/darkheme";
import { ThemeProvider } from "@emotion/react";

function App() {
  const user = true;
  return (
    <ThemeProvider theme={darkTheme}>
      {user ? <div>
        <Navbar />
        <Home />
      </div> :  <Auth />}
    </ThemeProvider>
  );
}

export default App;
