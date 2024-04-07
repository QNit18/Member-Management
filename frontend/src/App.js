import "./App.css";
import Navbar from "./Page/Navbar/Navbar";
import { darkTheme } from "./theme/darkheme";
import { ThemeProvider } from "@emotion/react";

function App() {
    return (
        <ThemeProvider theme={darkTheme}>
            <Navbar/>
        </ThemeProvider>
    );
}

export default App;
