import { BrowserRouter, Route, Routes} from "react-router-dom";
import "./App.css";
import { CookiesProvider } from "react-cookie";

// pages
import HomePage from "./components/pages/HomePage"
import SignInPage from "./components/pages/SignInPage"
// common
import {PrivateRoute} from "./common/PrivateRoute";
import {AuthProvider} from "./context/AuthProvider";

function App() {
  return (
    <BrowserRouter>
      <CookiesProvider>
        <AuthProvider>
          <Routes>
            <Route path="/sign-in" element={<SignInPage/>}/>
            <Route path="/" element={<PrivateRoute><HomePage/></PrivateRoute>}/>
          </Routes>
        </AuthProvider>
      </CookiesProvider>
    </BrowserRouter>
  );
}

export default App;
