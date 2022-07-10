import React, { useState, useContext, createContext, useCallback, useEffect } from "react";
import { useCookies } from "react-cookie";
import axios from "axios";

// types
import { User } from "../types/User";

type AuthState = {
  authState: {
    authenticated: boolean;
    user: User | null;
  }
  setAuthState: any;
};

export const AuthContext = createContext<AuthState>({
  authState: {
    authenticated: false,
    user: null
  },
  setAuthState: () => {}
});

type ProviderProps = {
  children?: React.ReactNode
}

export const AuthProvider: React.FC<ProviderProps> = ({ children }) => {
  const [authState, setAuthState] = useState(useContext(AuthContext).authState);
  const [cookies, removeCookie] = useCookies();

  const fetchData = useCallback(async () => {
    const tokenFromCookie = cookies.token;
    if(!tokenFromCookie) return;
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + tokenFromCookie;
    await axios
      .get(`/api/v1/auth/current-user`)
      .then((res) => {
        setAuthState({
          authenticated: true,
          user: res.data,
        })
      })
      .catch(() => {
        removeCookie("token", tokenFromCookie);
      })
  },[]);

  useEffect(() => {
    fetchData();
  }, [fetchData])

  return (
    <AuthContext.Provider value = {{ authState, setAuthState}}>
      {children}
    </AuthContext.Provider>
  );
}