import React, { useState, useContext, useCallback, useEffect } from "react";
import { useNavigate } from "react-router";
import { useCookies } from "react-cookie";
import axios from "axios";
import { Navigate, useLocation } from "react-router-dom";
import queryString from "query-string";
// context
import { AuthContext } from "../context/AuthProvider";

type AuthProps = {
  children: React.ReactNode
};

export const PrivateRoute: React.FC<AuthProps> = ({ children }) => {
  const [cookies, setCookie, removeCookie] = useCookies();
  const [authState, setAuthState] = useState(useContext(AuthContext).authState);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  const path = useLocation().pathname;
  const query = queryString.parse(useLocation().search);

  const savePath = () => {
    setCookie("path", path);
    setCookie("query", query);
  }

  const loadPath = () => {
    var savedPath = cookies.path;
    var savedQuery = cookies.query;
    if(!savedPath) savedPath = "/";
    if(!savedQuery) savedQuery = "";
    return queryString.stringifyUrl({url: savedPath, query: savedQuery});
  }

  const fetchCurrentUser = useCallback(async () => {
    const tokenFromCookie = cookies.token;
    if(!tokenFromCookie) {
      setLoading(false);
      savePath();
      return;
    }
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + tokenFromCookie;
    await axios
      .get(`/api/v1/auth/current-user`)
      .then((res) => {
        setAuthState({
          authenticated: true,
          user: res.data,
        })
        navigate(loadPath());
      })
      .catch((error) => {
        console.log(error);
        removeCookie("token", tokenFromCookie);
        savePath();
      })
      .finally(() => {
        setLoading(false);
      });
  },[]);

  useEffect(() => {
    setLoading(true);
    fetchCurrentUser();
  }, [path])

  return (
    loading ? <></> :
      authState.authenticated ? (<>{children}</>) :
      (<Navigate to={'sign-in'}/>)
  )
}