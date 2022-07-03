import logo from "./logo.svg";
import "./App.css";
import useState from "react-hook-use-state";

function App() {
  const [json, setJSON] = useState();

  fetch("http://localhost:8080/restaurants")
    .then((response) => response.json())
    .then((data) => setJSON(JSON.stringify(data)));

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>Get JSON from server: {json}</p>
      </header>
    </div>
  );
}

export default App;
