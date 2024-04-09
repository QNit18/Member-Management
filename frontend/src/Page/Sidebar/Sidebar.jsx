import React, { useState } from "react";
import Avatar from "@mui/material/Avatar";
import "./Sidebar.css";
import { Button } from "react-bootstrap";
import CreateNewTaskForm from "../Task/TaskCard/CreateTask";

const menu = [
  { name: "HOME", value: "HOME", role: ["ROLE_ADMIN", "ROLE_CUSTOMER"] },
  { name: "DONE", value: "DONE", role: ["ROLE_ADMIN", "ROLE_CUSTOMER"] },
  { name: "ASSIGNED", value: "ASSIGNED", role: ["ROLE_ADMIN"] },
  { name: "NOT ASSIGNED", value: "NOT ASSIGNED", role: ["ROLE_ADMIN"] },
  { name: "CREATE TASK", value: "", role: ["ROLE_ADMIN"] },
  { name: "NOTIFICATION", value: "NOTIFICATION", role: ["ROLE_CUSTOMER"] },
];

const role = "ROLE_ADMIN";

const Sidebar = () => {

  const [openCreateTaskForm, setOpenCreateTaskForm] = useState(false);
  const handleCloseCreateTaskForm = () => {
    setOpenCreateTaskForm(false);
  };
  const handleOpenCreateTaskForm = () => {
    setOpenCreateTaskForm(true);
  };

  const [activeMenu, setActiceMenu] = useState("DONE");

  const handleMenuChange = (item) => {
    if(item.name==="CREATE TASK"){
      handleOpenCreateTaskForm();
    }
    setActiceMenu(item.name);
  };

  const handleLogout = () => {
    console.log("Handle logout");
  };

  return (
    <>
      <div className="card min-h-[85vh] flex flex-col justify-center fixed w-[20vw]">
        <div className="space-y-5 h-full">
          <div className="flex justify-center">
            <Avatar
              sx={{ width: "8rem", height: "8rem" }}
              className="border-2 border-[#c24dd0]"
            />
          </div>
          {menu
            .filter((item) => item.role.includes(role))
            .map((item) => (
              <p
                onClick={() => handleMenuChange(item)}
                className={`py-3 px-5 rounded-full 
          text-center cursor-pointer ${
            activeMenu === item.name ? "activeMenuItem" : "menuItem"
          }`}
              >
                {item.name}
              </p>
            ))}
          <Button onClick={handleLogout} className="logoutButton">
            LOGOUT
          </Button>
        </div>
      </div>
      <CreateNewTaskForm open={openCreateTaskForm} handleClose={handleCloseCreateTaskForm}/>
    </>
  );
};

export default Sidebar;
