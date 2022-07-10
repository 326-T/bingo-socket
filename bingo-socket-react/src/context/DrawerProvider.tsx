import React, { useState, useContext, createContext, useCallback, useEffect } from "react";

type DrawerState = {
  drawerState: {
    open: boolean;
  }
  setDrawer: any;
}

export const DrawerContext = createContext<DrawerState>({
  drawerState: {
    open: true
  },
  setDrawer: () => {}
});

type ProviderProps = {
  children: React.ReactNode;
}
export const DrawerProvider: React.FC<ProviderProps> = ({ children }) => {
  const [drawerState, setDrawer] = useState(useContext(DrawerContext).drawerState)
  return (
    <DrawerContext.Provider value = {{ drawerState, setDrawer}}>
      {children}
    </DrawerContext.Provider>
  );
}

