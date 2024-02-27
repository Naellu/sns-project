import LoginPage from "./component/login/LoginPage";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import HomePage from "./component/home/HomePage";

function App() {
  return (
      <BrowserRouter>
          <Routes>
              <Route path="/login" Component={LoginPage} />
              <Route path="/home" Component={HomePage} />
          </Routes>
      </BrowserRouter>
  );
}
export default App;
