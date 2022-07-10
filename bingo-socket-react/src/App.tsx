import { BrowserRouter, Route, Routes} from "react-router-dom";
import "./App.css";
import { CookiesProvider } from "react-cookie";

// pages
import HomePage from "./components/pages/HomePage";
import SignInPage from "./components/pages/SignInPage";
// templates
import DashBoardLayout from "./components/templates/DashBoardLayout";
// common
import {PrivateRoute} from "./common/PrivateRoute";
import {AuthProvider} from "./context/AuthProvider";
import {DrawerProvider} from "./context/DrawerProvider";

function App() {
  return (
    <BrowserRouter>
      <CookiesProvider>
        <AuthProvider>
          <DrawerProvider>
            <Routes>
              <Route path="/sign-in" element={<SignInPage/>}/>
              <Route path="/" element={<PrivateRoute><HomePage/></PrivateRoute>}/>
              <Route path="/dash-board" element={<PrivateRoute><DashBoardLayout/></PrivateRoute>}/>
            </Routes>
          </DrawerProvider>
        </AuthProvider>
      </CookiesProvider>
    </BrowserRouter>
  );
}

export default App;
