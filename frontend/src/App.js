import "./App.css";
import Home from "./Page/Home/Home";
import Navbar from "./Page/Navbar/Navbar";
import { darkTheme } from "./theme/darkheme";
import { ThemeProvider } from "@emotion/react";

function App() {
    return (
        <ThemeProvider theme={darkTheme}>
            <Navbar/>
            <Home/>
        </ThemeProvider>
    );
}

export default App;
