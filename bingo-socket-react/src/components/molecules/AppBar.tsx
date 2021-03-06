import React, { useState, useContext, useEffect } from "react";
import { styled } from '@mui/material/styles';
import MuiAppBar, { AppBarProps as MuiAppBarProps } from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import Badge from '@mui/material/Badge';
import MenuIcon from '@mui/icons-material/Menu';
import NotificationsIcon from '@mui/icons-material/Notifications';
// common
import { DrawerContext } from "../../context/DrawerProvider";
// molecules
import AccountMenu from "./AccountMenu";

const drawerWidth: number = 240;

interface AppBarProps extends MuiAppBarProps {
  open?: boolean;
}
const CustomAppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== 'open',
})<AppBarProps>(({ theme, open }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(['width', 'margin'], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));

export const AppBar: React.FC = () => {
  const {drawerState, setDrawer} = useContext(DrawerContext);
  const toggleDrawer = () => {
    setDrawer({
      open: !drawerState.open
    });
  };

  return (
    <CustomAppBar position="absolute" open={drawerState.open}>
      <Toolbar
        sx={{
          pr: '24px', // keep right padding when drawer closed
        }}
      >
        <IconButton
          onClick={toggleDrawer}
          edge="start"
          color="inherit"
          aria-label="open drawer"
          sx={{
            marginRight: '36px',
            ...(drawerState.open && { display: 'none' }),
          }}
        >
          <MenuIcon/>
        </IconButton>
        <Typography
          component="h1"
          variant="h6"
          color="inherit"
          noWrap
          sx={{ flexGrow: 1 }}
        >
          Dashboard
        </Typography>
        <IconButton color="inherit">
          <Badge badgeContent={4} color="secondary">
            <NotificationsIcon/>
          </Badge>
        </IconButton>
        <IconButton color="inherit">
          <Badge color="secondary">
            <AccountMenu/>
          </Badge>
        </IconButton>
      </Toolbar>
    </CustomAppBar>
  );
};

export default AppBar;